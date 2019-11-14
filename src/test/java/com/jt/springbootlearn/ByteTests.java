package com.jt.springbootlearn;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class ByteTests {
    @Test
    public void testBytesToImage(){
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\temp\\tfc\\result.txt"), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder builder = new StringBuilder();
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
//                System.out.println(lineTxt);
                builder.append(lineTxt);
            }
            br.close();


            ObjectMapper objectMapper = new ObjectMapper();
            RequestResult<KnowledgeCognitionAtlas> result = objectMapper.readValue(builder.toString(), new TypeReference<RequestResult<KnowledgeCognitionAtlas>>() {});
            KnowledgeCognitionAtlas atlas = result.getValue();
            byte[] imageData = atlas.getImageData();
            System.out.println(imageData);

            FileOutputStream fos = new FileOutputStream("D:\\temp\\tfc\\3-2.png");
            fos.write(imageData);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static class RequestResult<T>{

        @JsonProperty("ApiResultType")
        private Integer apiResultType;
        @JsonProperty("ResultMessage")
        private String resultMessage;
        @JsonProperty("Value")
        private T value;

        public Integer getApiResultType() {
            return apiResultType;
        }

        public void setApiResultType(Integer apiResultType) {
            this.apiResultType = apiResultType;
        }

        public String getResultMessage() {
            return resultMessage;
        }

        public void setResultMessage(String resultMessage) {
            this.resultMessage = resultMessage;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

    public static class KnowledgeCognitionAtlas{
        @JsonProperty("ImageData")
        private byte[] imageData;
        @JsonProperty("KnowledgeNodeRelations")
        private KnowledgeNodeRelation[] knowledgeNodeRelations;

        public byte[] getImageData() {
            return imageData;
        }

        public void setImageData(byte[] imageData) {
            this.imageData = imageData;
        }

        public KnowledgeNodeRelation[] getKnowledgeNodeRelations() {
            return knowledgeNodeRelations;
        }

        public void setKnowledgeNodeRelations(KnowledgeNodeRelation[] knowledgeNodeRelations) {
            this.knowledgeNodeRelations = knowledgeNodeRelations;
        }
    }

    public static class KnowledgeNodeRelation{
        @JsonProperty("StartNode")
        private KnowledgeNode startNode;

        @JsonProperty("StopNode")
        private KnowledgeNode stopNode;

        @JsonProperty("NodeRelationType")
        private Integer nodeRelationType;
    }

    public static class KnowledgeNode{
        @JsonProperty("KnowledgeId")
        private Integer knowledgeId;
        @JsonProperty("KnowledgeName")
        private String knowledgeName;
        @JsonProperty("IsCurKnowledge")
        private Boolean isCurKnowledge;

        @JsonProperty("ScoreRate")
        private Double scoreRate;

        public Integer getKnowledgeId() {
            return knowledgeId;
        }

        public void setKnowledgeId(Integer knowledgeId) {
            this.knowledgeId = knowledgeId;
        }

        public String getKnowledgeName() {
            return knowledgeName;
        }

        public void setKnowledgeName(String knowledgeName) {
            this.knowledgeName = knowledgeName;
        }

        public Boolean getCurKnowledge() {
            return isCurKnowledge;
        }

        public void setCurKnowledge(Boolean curKnowledge) {
            isCurKnowledge = curKnowledge;
        }

        public Double getScoreRate() {
            return scoreRate;
        }

        public void setScoreRate(Double scoreRate) {
            this.scoreRate = scoreRate;
        }
    }
}

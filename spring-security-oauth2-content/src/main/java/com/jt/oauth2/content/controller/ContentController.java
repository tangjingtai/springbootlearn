package com.jt.oauth2.content.controller;

import com.jt.oauth2.content.dto.Content;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ContentController {

    @GetMapping("/detail/{id}")
    public Content content(@PathVariable("id") Long id) {
        Content content = new Content();
        content.setId(id);
        content.setContent("this content with id " + id);
        content.setCreateOn(new Date());

        return content;
    }

    @GetMapping("/all")
    public List<Content> all() {
        List<Content> contents = new ArrayList<>(10);
        for (int i = 1; i <= 10; i++) {
            Content content = new Content();
            content.setId((long) i);
            content.setContent("this content with id " + i);
            content.setCreateOn(new Date());
            contents.add(content);
        }
        return contents;
    }
}

package com.jt.springbootlearn.jdbc;

import com.jt.springbootlearn.bean.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DogRepository {
    @Autowired
    DataSource dataSource;

    public Dog getDogById(long id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement("select Id, name,age from dog where id = ?;");
            stmt.setLong(1, id);
            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Dog dog = new Dog();
                dog.setName(resultSet.getString("name"));
                dog.setAge(resultSet.getInt("age"));
                return dog;
            }
        } catch (SQLException ignored) {
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignored) {
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ignored) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignored) {
                }
            }
        }
        return null;
    }
}

package com.jt.springbootlearn;

import org.junit.Test;

import java.sql.*;

public class JDBCTests {

    @Test
    public void testStatementSelect() {
        Connection conn = getMySqlConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "Select Id,name,age from dog where id = " + 1;
            ResultSet resultSet = stmt.executeQuery(sql);
            resultSet.next();
            String name = resultSet.getString(2);
            System.out.println("dog name: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(stmt);
            closeConnection(conn);
        }
    }

    @Test
    public void testPreparedStatementSelect() {
        Connection conn = getMySqlConnection();
        PreparedStatement stmt = null;
        String sql = "Select Id,name,age from dog where id = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 1);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            String name = resultSet.getString(2);
            System.out.println("dog name: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(stmt);
            closeConnection(conn);
        }
    }

    @Test
    public void testBatchInset() {
        Connection conn = getMySqlConnection();
        PreparedStatement stmt = null;
        String sql = "Insert into dog(name,age) values(?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            for (int i = 1; i <= 100; i++) {
                stmt.setString(1, "旺财_" + i);
                stmt.setInt(2, 3);
                stmt.addBatch();
                if (i % 10 == 0) {
                    int[] resultSet = stmt.executeBatch();
                    System.out.println("resultSet length: " + resultSet.length);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(stmt);
            closeConnection(conn);
        }
    }

    @Test
    public void testGeneratedKey() {
        Connection conn = getMySqlConnection();
        PreparedStatement stmt = null;
        String sql = "Insert into dog(name,age) values(?,?)";
        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, "旺财");
            stmt.setInt(2, 4);
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            resultSet.next();
            int newId = resultSet.getInt(1);
            System.out.println("new Id: " + newId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(stmt);
            closeConnection(conn);
        }

    }

    private Connection getMySqlConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&characterEncoding=UTF-8", "root", "123456");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private void closeStatement(Statement stmt) {
        if (stmt == null)
            return;
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection(Connection conn) {
        if (conn == null) {
            return;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

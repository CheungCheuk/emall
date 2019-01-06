package com.cheung.emall.test;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection
                                    ("jdbc:mysql://localhost:3306/emall" +
                                                    "?useUnicode=true&characterEncoding=utf8",
                    "root", "admin");
            Statement  sqlStatement = connection.createStatement();
           for (int i = 0; i<2; i++){
               String sqlStringFormat = "insert into category value (null,'测试%d')";
               String sqlString = String.format(sqlStringFormat,i);
               sqlStatement.execute(sqlString);
           }
           System.out.println("插入成功，测试完毕");

            // connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

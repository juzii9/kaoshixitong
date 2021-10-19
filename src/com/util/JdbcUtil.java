package com.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;

public class JdbcUtil {
    final String URL = "jdbc:mysql://localhost:3306/node";
    final String USERNAME = "root";
    final String PASSWORD = "5201314";
    Connection conn = null;
    PreparedStatement ps = null;

    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConn(){
        try {
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
    public Connection getConn(HttpServletRequest request){
        ServletContext application = request.getServletContext();
        Map map =(Map)application.getAttribute("key1");
        Iterator it = map.keySet().iterator();
        while(it.hasNext()){
            conn = (Connection) it.next();
            boolean flag = (boolean)map.get(conn);
            if(flag == true){
                map.put(conn,false);
                break;
            }
        }
        return conn;
    }
    public PreparedStatement createStatement(String sql){
        try {
            ps = getConn().prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ps;
    }
    public PreparedStatement createStatement(String sql, HttpServletRequest request){
        try {
            ps = getConn(request).prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ps;
    }
    public void close(){
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public void close(HttpServletRequest request){
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        ServletContext application = request.getServletContext();
        Map map =(Map)application.getAttribute("key1");
        map.put(conn, true);
    }
    public void close(ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        close();
    }
}

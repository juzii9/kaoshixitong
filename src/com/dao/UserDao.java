package com.dao;

import com.entity.User;
import com.util.JdbcUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private JdbcUtil util = new JdbcUtil();

    public int add(User user){
        String sql = "insert into t_user(username,password,sex,email) values(?,?,?,?)";
        PreparedStatement ps = util.createStatement(sql);
        int result = 0;
        try {
            ps.setString(1,user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSex());
            ps.setString(4, user.getEmail());
            result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("插入成功");
            }else{
                System.out.println("插入失败");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            util.close();
        }
        return result;
    }
    public int add(User user, HttpServletRequest request){
        String sql = "insert into t_user(username,password,sex,email) values(?,?,?,?)";
        PreparedStatement ps = util.createStatement(sql,request);
        int result = 0;
        try {
            ps.setString(1,user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSex());
            ps.setString(4, user.getEmail());
            result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("插入成功");
            }else{
                System.out.println("插入失败");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            util.close(request);
        }
        return result;
    }
    public List findAll(){
        String sql = "select * from t_user";
        PreparedStatement ps = util.createStatement(sql);
        List userList = new ArrayList();
        ResultSet rs=null;
        try {
            rs = ps.executeQuery();
            while(rs.next()){
                Integer id = rs.getInt("id");
                String username=rs.getString("username");
                String password=rs.getString("password");
                String sex=rs.getString("sex");
                String email=rs.getString("email");
                User user = new User(id,username,password,sex,email);
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            util.close(rs);
        }
        return userList;
    }

    public int delete(String userid){
        String sql = "delete from t_user where id=?";
        PreparedStatement ps = util.createStatement(sql);
        int result = 0;
        try {
            ps.setInt(1,Integer.valueOf(userid));
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            util.close();
        }
        return result;
    }

    public int login(String username,String password){
        String sql = "select count(*) from t_user where username=? and password=?";
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        int result = 0;
        try {
            ps.setString(1,username);
            ps.setString(2,password);
            rs=ps.executeQuery();
            while(rs.next()){
                result = rs.getInt("count(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        return result;
    }
}

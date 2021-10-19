package com.listner;

import com.util.JdbcUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OneListner implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        JdbcUtil util = new JdbcUtil();
        Map map=new HashMap();
        for (int i = 1; i <= 20; i++) {
            Connection conn = util.getConn();
            System.out.println("创建Conncetion "+conn);
            map.put(conn,true);
        }
        ServletContext application = sce.getServletContext();
        application.setAttribute("key1",map);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        Map map = (Map)application.getAttribute("key1");
        Iterator it = map.keySet().iterator();
        while(it.hasNext()){
            Connection conn=(Connection)it.next();
            if (conn != null) {
                System.out.println("Connection"+conn+"销毁完毕");
            }
        }
    }
}

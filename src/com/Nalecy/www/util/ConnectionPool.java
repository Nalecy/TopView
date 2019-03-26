package com.Nalecy.www.util;

import com.Nalecy.www.service.ProGetter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class ConnectionPool {
    private static ConnectionPool ourInstance = new ConnectionPool();
    private static String url;
    private static String user;
    private static String password;
    private static Integer maxConn;
    private static Integer minConn;
    private static LinkedList<Connection> connPool;

    public static ConnectionPool getInstance() {
        return ourInstance;
    }

    private ConnectionPool() {
        initialize();
    }
    private void initialize(){
        url = ProGetter.getInstance().get("url");
        user = ProGetter.getInstance().get("user");
        password = ProGetter.getInstance().get("password");
        minConn = Integer.parseInt(ProGetter.getInstance().get("minConn"));
        maxConn = Integer.parseInt(ProGetter.getInstance().get("maxConn"));
        connPool = new LinkedList<>();
        try {
            Class.forName(ProGetter.getInstance().get("driver"));
            for (int i = 0; i<minConn; i++){
                Connection conn = DriverManager.getConnection(url,user,password);
                connPool.add(conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection(){

        while(connPool.isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return connPool.remove(0);
    }
    public void returnConn(Connection conn){
        if(conn == null)return;
        if(connPool.size() > maxConn){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            connPool.add(conn);
        }
    }
}

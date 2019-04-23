package com.Nalecy.www.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class ConnectionPool {
    private static ConnectionPool ourInstance = new ConnectionPool();
    public static ConnectionPool getInstance() {
        return ourInstance;
    }
    private ConnectionPool() {
        //在构造前先初始化
        initialize();
    }
    //连接池基本参数
    private String url;
    private String user;
    private String password;
    private Integer maxConn;
    private Integer minConn;
    //连接池的对象
    private LinkedList<Connection> connPool;
    private void initialize(){
        //通过配置文件加载器获取配置数据
        url = ProGetter.getInstance().get("url");
        user = ProGetter.getInstance().get("user");
        password = ProGetter.getInstance().get("password");
        minConn = Integer.parseInt(ProGetter.getInstance().get("minConn"));
        maxConn = Integer.parseInt(ProGetter.getInstance().get("maxConn"));
        connPool = new LinkedList<>();//频繁添加删除使用链式数组
        try {
            //加载驱动
            Class.forName(ProGetter.getInstance().get("driver"));
            for (int i = 0; i<minConn; i++){
                Connection conn = DriverManager.getConnection(url,user,password);
                connPool.add(conn);
            }
        } catch (Exception e) {
            System.err.println("连接池加载失败");
            System.exit(-1);
        }
    }

    /**
     * 获取数据库连接，若数据库连接为空 则阻塞
     * @return Connection 获得的连接
     */
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

    /**
     * 返还连接
     * @param conn 要返还的连接
     */
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

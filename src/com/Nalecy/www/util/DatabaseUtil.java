package com.Nalecy.www.util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class DatabaseUtil {
    private static DatabaseUtil ourInstance = new DatabaseUtil();
    public static DatabaseUtil getInstance() {
        return ourInstance;
    }
    private DatabaseUtil() {
    }

    public LinkedList<String> getOneRowData(String tableName, String dataName, String dataValue){
        LinkedList<String> list = new LinkedList<>();
        ResultSet rs = null;
        Connection conn = ConnectionPool.getInstance().getConnection();
        String sql = "select * from "+tableName+" where "+dataName+" = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dataValue);
            rs = pstmt.executeQuery();
            try {
                while (rs.next()) {
                    for (int i = 1;;i++)
                        list.add(rs.getString(i));
                }
            }catch (SQLException ignored){}
        }catch (Exception e){
            e.printStackTrace();
        }
        ConnectionPool.getInstance().returnConn(conn);
        return list;
    }

    public LinkedList<String> getOneColumnData(String tableName,String columnName){
        LinkedList<String> list = new LinkedList<>();
        Connection conn = ConnectionPool.getInstance().getConnection();
        String sql = "select "+columnName+" from "+tableName;
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString(columnName));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionPool.getInstance().returnConn(conn);
        if(0 == list.size())return null;
        else return list;
    }
    public LinkedList<String> getOneColumnData(String tableName,String columnName, String signKey, String signValue){
        LinkedList<String> list = new LinkedList<>();
        Connection conn = ConnectionPool.getInstance().getConnection();
        String sql = "select "+columnName+" from "+tableName+" where "+signKey+" = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,signValue);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(columnName));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionPool.getInstance().returnConn(conn);
        if(0 == list.size())return null;
        else return list;
    }
    public int addOneRowData(String tableName, String...value){
        Integer number = 0;
        StringBuilder sql = new StringBuilder();
        Connection conn = ConnectionPool.getInstance().getConnection();
        sql.append("insert into "+tableName+" value (");
        for(int i = 0;i<value.length;i++){
            if (i == 0)sql.append("?");
            else sql.append(",?");
        }
        sql.append(")");
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            for (int i = 1; i <= value.length; i++) {
                pstmt.setString(i, value[i - 1]);
            }
            number = pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionPool.getInstance().returnConn(conn);
        return number;
    }

    public int deleteOneRowData(String tableName,String oneOfNames,String oneOfValue) {  //传入表名
        Connection conn = ConnectionPool.getInstance().getConnection();
        Integer number = 0;
        String sql = "delete from "+tableName+" where "+oneOfNames+" = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, oneOfValue);
            number = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionPool.getInstance().returnConn(conn);
        return number;
    }
    public int updateRowsData(String tableName, String signKey, String signValue,LinkedHashMap<String,String> keyValueMap){
        Integer number = 0;
        StringBuilder sql = new StringBuilder();
        String[] keyArray = new String[keyValueMap.size()];
        keyValueMap.keySet().toArray(keyArray);
        Connection conn = ConnectionPool.getInstance().getConnection();
        sql.append("update "+tableName+" set ");
        for( int i = 0; i<keyArray.length; i++){
            if(i == 0) sql.append(keyArray[i]+" = ?");
            else sql.append(","+keyArray[i]+" = ? ");
        }
        sql.append("where "+signKey+" = ?");
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < keyArray.length; i++) {
                pstmt.setString(i + 1, keyValueMap.get(keyArray[i]));
            }
            pstmt.setString(keyArray.length + 1, signValue);
            number = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionPool.getInstance().returnConn(conn);
        return number;
    }

}

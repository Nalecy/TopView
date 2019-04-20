package com.Nalecy.www.util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DatabaseUtil {
    private DatabaseUtil(){}

    public static LinkedList<String> getOneRowData(String tableName, String sighName, String sighValue){
        LinkedList<String> list = new LinkedList<>();
        ResultSet rs = null;
        Connection conn = ConnectionPool.getInstance().getConnection();
        String sql = "select * from "+tableName+" where "+sighName+" = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sighValue);
            rs = pstmt.executeQuery();
            try {
                while (rs.next()) {
                    for (int i = 1;;i++)
                        list.add(rs.getString(i));
                }
            }catch (SQLException ignored){}//当出现异常即说明数据读取完毕
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().returnConn(conn);
        }
        return list;
    }

    public static List<String> getOneColumnData(String tableName,String columnName){
        List<String> list = new LinkedList<>();
        Connection conn = ConnectionPool.getInstance().getConnection();
        String sql = "select "+columnName+" from "+tableName;
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString(columnName));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().returnConn(conn);
        }
        if(0 == list.size())return null;
        else return list;
    }
    public static List<String> getOneColumnData(String tableName, String columnName, String signKey, String signValue){
        List<String> list = new LinkedList<>();
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
        }finally {
            ConnectionPool.getInstance().returnConn(conn);
        }
        if(0 == list.size())return null;
        else return list;
    }
    public static int addOneRowData(String tableName, String...value){      //返回修改的行数
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
        }finally {
            ConnectionPool.getInstance().returnConn(conn);
        }
        return number;
    }

    public static int deleteOneRowData(String tableName,String oneOfNames,String oneOfValue) {  //传入表名
        Connection conn = ConnectionPool.getInstance().getConnection();
        Integer number = 0;
        String sql = "delete from "+tableName+" where "+oneOfNames+" = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, oneOfValue);
            number = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().returnConn(conn);
        }
        return number;
    }
    public static int updateRowsData(String tableName, String signKey, String signValue, Map<String,String> keyValueMap){
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
        }finally {
            ConnectionPool.getInstance().returnConn(conn);
        }
        return number;
    }

}

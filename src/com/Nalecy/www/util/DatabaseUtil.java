package com.Nalecy.www.util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 使用同包下的连接池获取数据库连接
 */
public class DatabaseUtil {
    private DatabaseUtil(){}

    /**
     * 获取某个表中的一行数据
     * 传入 表名,该行某一键名,对应的值
     * 返回一个List<String>集合，按顺序存放表中一行的每一个值
     * @param tableName 表名
     * @param sighName 该行某一键名
     * @param sighValue 对应的值
     * @return List<String>
     */
    public static List<String> getOneRowData(String tableName, String sighName, String sighValue){
        List<String> list = new LinkedList<>();
        ResultSet rs;
        //获取连接
        Connection conn = ConnectionPool.getInstance().getConnection();
        //组装sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ");
        sql.append(tableName);
        sql.append(" where ");
        sql.append(sighName);
        sql.append(" = ?");
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, sighValue);
            rs = pstmt.executeQuery();
            try {
                while (rs.next()) {
                    for (int i = 1;;i++)
                        list.add(rs.getString(i));
                }
            }catch (SQLException ignored){}//当出现异常即说明数据读取完毕
        }catch (SQLException e){
            System.err.println("sql语句执行异常");
        }finally {
            ConnectionPool.getInstance().returnConn(conn);//归还连接
        }
        return list;
    }

    /**
     * 获取某个表中的一列数据
     * 传入 表名,该行的键名
     * 返回一个List<String>集合，按顺序存放表中一列的每一个值
     * @param tableName 表名
     * @param columnName 该行的键名
     * @return List<String>
     */
    public static List<String> getOneColumnData(String tableName,String columnName){
        List<String> list = new LinkedList<>();
        Connection conn = ConnectionPool.getInstance().getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        sql.append(columnName);
        sql.append(" from ");
        sql.append(tableName);
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql.toString());
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

    /**
     * 通过筛选获取某个表中的一列数据
     * 传入 表名,该行的键名,筛选的键名,筛选的键值
     * 返回一个List<String>集合，按顺序存放表中一列的筛选后的每一个值
     * @param tableName 表名
     * @param columnName 该行的键名
     * @param signKey 筛选的键名
     * @param signValue 筛选的键值
     * @return List<String>
     */
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

    /**
     * 在某一个表内增加一行数据，参数value的长度需与表的键的数量相匹配
     * 返回修改的行数(返回1则添加成功，0则添加失败)
     * @param tableName 表名
     * @param value 多个添加的数据
     * @return int
     */
    public static int addOneRowData(String tableName, String...value){      //返回修改的行数
        int number = 0;//用于记录修改的行数
        StringBuilder sql = new StringBuilder();
        Connection conn = ConnectionPool.getInstance().getConnection();
        //动态添加占位符
        sql.append("insert into ").append(tableName).append(" value (");
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

    /**
     * 根据传入的条件删除某行(请选择关键性唯一的键和值作为条件，否则可能误删)，返回修改的行数(返回1为正常删除一行)
     * @param tableName 表名
     * @param signKey 该行的某个键名
     * @param signValue 对应的值
     * @return int
     */
    public static int deleteOneRowData(String tableName,String signKey,String signValue) {  //传入表名
        Connection conn = ConnectionPool.getInstance().getConnection();
        int number = 0;
        StringBuilder sql = new StringBuilder();
        sql.append("delete from ").append(tableName).append(" where ").append(signKey).append(" = ?");
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, signValue);
            number = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().returnConn(conn);
        }
        return number;
    }

    /**
     * 更新某一行数据，返回修改的行数
     * @param tableName 表名
     * @param signKey 该行的某个键名
     * @param signValue 对应的值
     * @param keyValueMap 一个Map key为键名，Value为要修改的键值
     * @return int
     */
    public static int updateRowsData(String tableName, String signKey, String signValue, Map<String,String> keyValueMap){
        int number = 0;
        StringBuilder sql = new StringBuilder();
        String[] keyArray = new String[keyValueMap.size()];
        keyValueMap.keySet().toArray(keyArray);
        Connection conn = ConnectionPool.getInstance().getConnection();
        sql.append("update ").append(tableName).append(" set ");
        for( int i = 0; i<keyArray.length; i++){
            if(i == 0) sql.append(keyArray[i]).append(" = ?");
            else sql.append(",").append(keyArray[i]).append(" = ? ");
        }
        sql.append("where ").append(signKey).append(" = ?");
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

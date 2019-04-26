package com.Nalecy.www.dao;

import com.Nalecy.www.po.Comment;
import com.Nalecy.www.util.DatabaseUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentDao {

    public static void addComment(Comment comment) {
        DatabaseUtil.addOneRowData("comment", null, String.valueOf(comment.getScore()), String.valueOf(comment.getOrderId()), String.valueOf(comment.getHotelId()), comment.getContent());
    }

    public static Comment getComment(Integer id) {
        Comment comment = new Comment();
        List<String> list = DatabaseUtil.getOneRowData("comment", "id", String.valueOf(id));
        if (list == null|| list.size() == 0) return null;
        setInfo(comment, list);
        return comment;
    }

    private static void setInfo(Comment comment, List<String> list) {
        comment.setId(Integer.valueOf(list.get(0)));
        comment.setScore(Integer.valueOf(list.get(1)));
        comment.setOrderId(Integer.valueOf(list.get(2)));
        comment.setHotelId(Integer.valueOf(list.get(3)));
        comment.setContent(list.get(4));
    }

    public static void updateCustomer(Comment comment) {
        Map<String, String> lhm = new HashMap<>();
        lhm.put("score", String.valueOf(comment.getScore()));
        lhm.put("orderId", String.valueOf(comment.getOrderId()));
        lhm.put("hotelId", String.valueOf(comment.getHotelId()));
        lhm.put("content", comment.getContent());
        DatabaseUtil.updateRowsData("comment", "id", String.valueOf(comment.getId()), lhm);
    }

    public static List<Comment> getOrderList() {
        List<String> idList = DatabaseUtil.getOneColumnData("comments", "id");
        List<Comment> comments = new ArrayList<>();
        if (idList == null) return null;
        for (String id : idList) {
            comments.add(getComment(Integer.valueOf(id)));
        }
        return comments;
    }
    public static void deleteOrder(Integer id) {
        DatabaseUtil.deleteOneRowData("comments", "id", String.valueOf(id));
    }
}

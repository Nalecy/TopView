package com.Nalecy.www.dao;

import com.Nalecy.www.po.Comment;

import java.util.List;

public interface CommentDao {
    void addComment(Comment comment);

    Comment getComment(Integer id);

    void updateCustomer(Comment comment);

    List<Comment> getOrderList();

    void deleteOrder(Integer id);
}

package com.Nalecy.www.dao;

import com.Nalecy.www.po.Comment;

import java.util.List;

public interface CommentDao {
    /**
     * 增加评论
     * @param comment 评论对象
     */
    void addComment(Comment comment);

    /**
     * 通过id获取评论
     * @param id 评论对象id
     * @return 评论对象
     */
    Comment getComment(Integer id);

    /**
     * 更新评论信息
     * @param comment 评论对象
     */
    void updateCustomer(Comment comment);

    /**
     * 获取评论对象的列表
     * @return List<Comment>
     */
    List<Comment> getCommentList();

    /**
     * 根据评论id删除评论
     * @param id 评论id
     */
    void deleteOrder(Integer id);
}

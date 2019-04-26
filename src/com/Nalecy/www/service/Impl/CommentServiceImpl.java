package com.Nalecy.www.service.Impl;

import com.Nalecy.www.constantClass.IsComment;
import com.Nalecy.www.dao.CommentDao;
import com.Nalecy.www.dao.OrderDao;
import com.Nalecy.www.po.Comment;
import com.Nalecy.www.po.Hotel;
import com.Nalecy.www.po.Order;
import com.Nalecy.www.service.CommentService;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.OrderService;
import com.Nalecy.www.util.ServiceFactory;

public class CommentServiceImpl implements CommentService {
    private HotelService hotelService = ServiceFactory.getHotelService();
    private OrderService orderService = ServiceFactory.getOrderService();
    @Override
    public void submitComment(Comment comment) {
        Hotel hotel = hotelService.getHotel(comment.getHotelId());
        Order order = orderService.getOrderById(comment.getOrderId());

        hotel.updateScore(comment.getScore());
        hotelService.updateHotel(hotel);

        order.setIsComment(IsComment.YES);
        OrderDao.updateOrder(order);

        CommentDao.addComment(comment);
    }
}

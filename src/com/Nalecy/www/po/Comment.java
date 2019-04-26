package com.Nalecy.www.po;

public class Comment {
    private Integer id;
    private Integer score;
    private Integer orderId;
    private Integer hotelId;
    private String content;


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    public Integer getHotelId() { return hotelId; }
    public void setHotelId(Integer hotelId) { this.hotelId = hotelId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}

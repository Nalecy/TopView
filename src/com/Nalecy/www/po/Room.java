package com.Nalecy.www.po;

import com.Nalecy.www.constantClass.*;


public class Room {
    private Integer id;
    private String name;
    private Integer type;
    private Integer area;
    private Integer bedWidth;
    private Integer price;
    private Integer hotelID;

    public Room() {
    }

    public Room(Integer id, String name, Integer type, Integer area, Integer bedWidth, Integer price, Integer hotelID) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.area = area;
        this.bedWidth = bedWidth;
        this.price = price;
        this.hotelID = hotelID;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("房间名称："+getName()+" 类型：");
        if(type.equals(RoomType.NORMAL))sb.append("普通");
        else if(type.equals(RoomType.ADVANCED))sb.append("高级");
        sb.append("，面积："+area+"平，床宽："+bedWidth+"cm，价格："+price+"元");
        return sb.toString();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getType() { return type; }
    public void setType(Integer type) { this.type = type; }
    public Integer getArea() { return area; }
    public void setArea(Integer area) { this.area = area; }
    public Integer getBedWidth() { return bedWidth; }
    public void setBedWidth(Integer bedWidth) { this.bedWidth = bedWidth; }
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }
    public Integer getHotelID() { return hotelID; }
    public void setHotelID(Integer hotelID) { this.hotelID = hotelID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

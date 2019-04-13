package com.Nalecy.www.discardMenu.customerMenu;

import com.Nalecy.www.discardMenu.Menu;

public class OrderMenu extends Menu {//废弃

    @Override
    public void show() {
        /*ArrayList<Order> orderList = OrderService.getInstance().getCompleteOrder();
        LinkedHashMap<Integer, Order> orderMap = OrderService.getInstance().getIncompleteOrder();
        while(true) {
            System.out.println("您的订单：");
            System.out.println("下列是已完成订单：");
            for (Order order : orderList) {
                String name = HotelService.getInstance().getHotelById(order.getHotelID()).getName();
                Room room = RoomService.getInstance().getRoomById(order.getRoomID());
                System.out.println(name + "酒店 ：" + order.toString() + "房间信息：" + room);
            }
            System.out.println("下列是未完成订单：");
            System.out.println("0、不取消，直接返回");
            for (Integer i : orderMap.keySet()) {
                Order order = orderMap.get(i);
                String name = HotelService.getInstance().getHotelById(order.getHotelID()).getName();
                Room room = RoomService.getInstance().getRoomById(order.getRoomID());
                System.out.println(i + "、" + name + "酒店 ：" + order.toString() + "房间信息：" + room);
            }
            System.out.println("你想要取消的订单序号是：");
            String index = in.nextLine();
            if (index.equals("0"))break;
            if(Pattern.matches("^\\d+$",index)&&orderMap.keySet().contains(Integer.parseInt(index))){
                OrderService.getInstance().cancelOrder(orderMap.get(Integer.parseInt(index)));
            }else System.out.println("请检查输入");
        }*/
    }
}

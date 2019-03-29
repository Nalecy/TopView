package com.Nalecy.www.view.hotelAdminMenu.submenu;

import com.Nalecy.www.po.Room;
import com.Nalecy.www.service.DateService;
import com.Nalecy.www.service.RoomService;
import com.Nalecy.www.view.Menu;

import java.util.regex.Pattern;

public class RoomMenu extends Menu {
    private Room room;

    public RoomMenu(){
        room =  RoomService.getInstance().getCurrentRoom();
    }
    @Override
    public void show() {

        System.out.println("请输入要修改的房间信息的序号：");
        printNowInfo();
        System.out.println("您想要修改(输入1保存，0返回)：");
        while(true){
            String c = in.next();
            if(c.equals("0"))break;
            else if(c.equals("1")){
                if(RoomService.getInstance().saveRoomInfo(room)){
                    System.out.println("保存成功");break;
                }else {
                    System.out.println("保存失败");break;
                }
            }
            if(Pattern.matches("^[2-4]{1}",c)){
                System.out.println("你要将其改为");
                String value = in.next();
                switch (Integer.parseInt(c)){
                    case 2:
                        if(Pattern.matches("^\\d+",value)) room.setArea(Integer.parseInt(value));
                        else System.out.println("格式错误");
                        break;
                    case 3:
                        if(Pattern.matches("^\\d+",value)) room.setBedWidth(Integer.parseInt(value));
                        else System.out.println("格式错误");
                        break;
                    case 4:
                        if(Pattern.matches("^\\d+",value)) room.setPrice(Integer.parseInt(value));
                        else System.out.println("格式错误");
                        break;
                }
            }else System.out.println("请检查输入");
            printNowInfo();

        }
    }

    private void printNowInfo(){
        System.out.println("2、房间面积："+room.getArea() +"平");
        System.out.println("3、床宽："+ room.getBedWidth() +"厘米");
        System.out.println("4、价格："+ room.getPrice() +"元");
    }


}

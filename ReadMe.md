# ReadMe

数据库已存在数据：

顾客账号：customer 酒管账号: 2hadmin  hadmin超管账号 ：admin 均已保存密码

2个酒店各2个房间 

1个顾客 

分别有已完成订单未完成订单 

有已评价的已完成订单 和 未评价的 未完成订单



手机身份证等输入仅做数字以及位数验证

注册时超级管理员的注册码为123456，酒店管理员的注册码为321654。（可通过配置文件修改）

对窗口切换进行了优化，返回时不用重新加载布局资源。

实现了输入已保存密码的用户名后会自动填充正确的密码

充值金额部分未实现验证(用户想充多少充多少...)

默认顾客可以预定明天开始3天内的房间。房间有3个时段可以预约，上午下午晚上。

同一天同一个时段只能有一个用户预约(取消订单后可以重新预约)。

预定房间会扣款，取消预约会还款。

超级管理员可以让时间流逝一天。

预定房间的最晚取消日期是一天前，到了预定房间的那一天就算订单完成了，无法退款。(1970-08-01的订单只能在07-31前去取消)

实现了vip功能(可随意注册) ，设置的折扣策略 满100-30

顾客可以通过订单界面 对本次订单进行评分 和评价 (未实现酒店管理者查看评价的功能，只可看评分)

实现了模糊查找酒店的功能

酒店管理者可以查看账单(交易记录)
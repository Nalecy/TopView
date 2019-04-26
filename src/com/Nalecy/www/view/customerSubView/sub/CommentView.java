package com.Nalecy.www.view.customerSubView.sub;

import com.Nalecy.www.constantClass.IsComment;
import com.Nalecy.www.po.Comment;
import com.Nalecy.www.po.Order;
import com.Nalecy.www.service.CommentService;
import com.Nalecy.www.service.CurrentRecorder;
import com.Nalecy.www.service.OrderService;
import com.Nalecy.www.util.ComponentCreater;
import com.Nalecy.www.util.ServiceFactory;
import com.Nalecy.www.util.ViewManger;
import com.Nalecy.www.view.View;
import com.Nalecy.www.view.popupUtil.PromptAlert;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CommentView extends View {
    private CommentService commentService = ServiceFactory.getCommentService();
    private CurrentRecorder currentRecorder = ServiceFactory.getCurrentRecorder();
    private OrderService orderService = ServiceFactory.getOrderService();


    private Stage stage;
    private Scene scene;
    private VBox vBox;

    private Label starLabel;
    private ToggleGroup starGroup;
    private ToggleButton oneStarButton;
    private ToggleButton twoStarButton;
    private ToggleButton threeStarButton;
    private ToggleButton fourStarButton;
    private ToggleButton fiveStarButton;
    private HBox starHBox;

    private TextArea commentArea;

    private Button submitButton;
    private Button backButton;

    @Override
    public void display() {
        if (!hasInit) {
            init();
            setButtonAction();
            hasInit = true;
        }
        stage.show();
    }

    private void setButtonAction() {
        oneStarButton.setOnAction(e->{
            starLabel.setText("★☆☆☆☆");
        });
        twoStarButton.setOnAction(e->{
            starLabel.setText("★★☆☆☆");
        });
        threeStarButton.setOnAction(e->{
            starLabel.setText("★★★☆☆");
        });
        fourStarButton.setOnAction(e->{
            starLabel.setText("★★★★☆");
        });
        fiveStarButton.setOnAction(e->{
            starLabel.setText("★★★★★");
        });
        submitButton.setOnAction(e->{
            if(submit())ViewManger.back();
        });
        backButton.setOnAction(e->{
            ViewManger.back();
        });
    }

    private boolean submit() {
        Order order = orderService.getOrderById(currentRecorder.getCurrentOrderId());
        String content = commentArea.getText();
        if(content.length() > 100){
            PromptAlert.display("错误","你字数太多啦");
            return false;
        }
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setOrderId(order.getId());
        comment.setHotelId(order.getHotelID());
        comment.setScore((Integer) starGroup.getSelectedToggle().getUserData());
        commentService.submitComment(comment);
        return true;
    }

    private void init() {
        starLabel = ComponentCreater.newLabel("★★★★★");
        starLabel.setFont(new Font(50));

        starGroup = new ToggleGroup();
        oneStarButton = new ToggleButton("一星");
        oneStarButton.setUserData(1);
        twoStarButton = new ToggleButton("二星");
        twoStarButton.setUserData(2);
        threeStarButton = new ToggleButton("三星");
        threeStarButton.setUserData(3);
        fourStarButton = new ToggleButton("四星");
        fourStarButton.setUserData(4);
        fiveStarButton = new ToggleButton("五星");
        fiveStarButton.setUserData(5);
        fiveStarButton.setSelected(true);
        starGroup.getToggles().addAll(oneStarButton, twoStarButton, threeStarButton, fourStarButton, fiveStarButton);
        starHBox = new HBox();
        starHBox.getChildren().addAll(oneStarButton, twoStarButton, threeStarButton, fourStarButton, fiveStarButton);
        starHBox.setAlignment(Pos.CENTER);
        starHBox.setSpacing(10);

        commentArea = new TextArea();
        commentArea.setPromptText("不超过100字");
        commentArea.setWrapText(true);

        backButton = ComponentCreater.newButton("返回");
        submitButton = ComponentCreater.newButton("提交");

        vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(starLabel,starHBox, commentArea, submitButton, backButton);

        scene = new Scene(vBox);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("评价订单");
    }

    @Override
    public void close() {
        stage.close();
    }

    @Override
    public void hide() {
        stage.hide();
    }
}

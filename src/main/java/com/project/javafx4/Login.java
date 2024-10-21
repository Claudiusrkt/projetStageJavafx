package com.project.javafx4;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login extends Application {

    public String face;
    private double xoffset=0;
    private double yoffset=0;


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 550);
        Image icon = new Image(String.valueOf(getClass().getResource("view/image/icone.jpg")));
        stage.getIcons().add(icon);
        stage.initStyle(StageStyle.DECORATED.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xoffset = mouseEvent.getSceneX();
                yoffset = mouseEvent.getSceneY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent Event) {
                double newX = Event.getScreenX() - xoffset;
                double newY = Event.getScreenY() - yoffset;
                stage.setX(newX);
                stage.setY(newY);
            }
        });
    }
    public static void main(String[] args) {
        launch();
    }
}
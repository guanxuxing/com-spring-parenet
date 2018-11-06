package com.java.fx;/**
 * Created by Administrator on 2018-10-26.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ApplicationFxMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("java fx");
        stage.setIconified(false);
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,400,400);
        scene.getStylesheets().add(ApplicationFxMain.class.getClassLoader().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }
}

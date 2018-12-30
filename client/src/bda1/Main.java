package bda1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("/Index.fxml"));
            primaryStage.setTitle("Transport Application GUI");
            primaryStage.setScene(new Scene(root, 800, 400));
            primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}

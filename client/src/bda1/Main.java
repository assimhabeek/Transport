package bda1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Index.fxml"));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Scene scene = new Scene(root, dimension.getWidth(), dimension.getHeight());

        primaryStage.setTitle("Transport");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

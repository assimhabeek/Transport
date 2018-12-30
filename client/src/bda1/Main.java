package bda1;

import bda1.model.Reservation;
import bda1.services.ReservationService;

import java.util.Iterator;
import java.util.Set;

public class Main /*extends Application*/ {
/*

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Index.fxml"));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Scene scene = new Scene(root, dimension.getWidth(), dimension.getHeight());

        primaryStage.setTitle("Transport");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
*/


    public static void main(String[] args) {
/*
        launch(args);
*/


        Set<Reservation> resault = new ReservationService().findAll();
        for (Reservation reservation : resault) {
            System.out.println(reservation.getId());
        };
    }

}

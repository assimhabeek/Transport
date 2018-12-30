package bda1;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;



public class Controller {
    @FXML
    public Button btn_Home, btn_Transport, btn_Voyageur, btn_reservation;

    public void ButtonClicked(ActionEvent event) throws IOException {
        Parent TransportView = FXMLLoader.load(getClass().getResource("/Transport_view.FXML"));
        Scene Transportview = new Scene(TransportView);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(Transportview);
        window.show();
    }

    public void ButtonClicked1(ActionEvent event) throws IOException {
        Parent VoyageurView = FXMLLoader.load(getClass().getResource("/Voyageur_view.FXML"));
        Scene Voyageurview = new Scene(VoyageurView);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(Voyageurview);
        window.show();
    }

    public void ButtonClicked2(ActionEvent event) throws IOException {
        Parent ReservationView = FXMLLoader.load(getClass().getResource("/Reservation_view.FXML"));
        Scene Reservationview = new Scene(ReservationView);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(Reservationview);
        window.show();
    }

    public void ButtonClicked3(ActionEvent event) throws IOException {
        Parent HomeView = FXMLLoader.load(getClass().getResource("/Index.FXML"));
        Scene Homeview = new Scene(HomeView);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(Homeview);
        window.show();


    }
}

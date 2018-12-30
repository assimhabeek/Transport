package bda1.control;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class SideNavController {
    @FXML
    public Button btn_Home, btn_Transport, btn_Voyageur, btn_Reservation;

    public void onTransportAction(ActionEvent event) throws IOException {
        changeScene(event, "TransportView.fxml");
    }

    public void onVoyageurAction(ActionEvent event) throws IOException {
        changeScene(event, "VoyageurView.fxml");
    }

    public void onReservationAction(ActionEvent event) throws IOException {
        changeScene(event, "ReservationView.fxml");
    }

    public void onHomeAction(ActionEvent event) throws IOException {
        changeScene(event, "Index.fxml");
    }

    private void changeScene(ActionEvent event, String viewName) throws IOException {
        Parent view = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(viewName)));

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        // we need this to set the current width not the first one
        Scene scene = new Scene(view, window.getScene().getWidth(), window.getScene().getHeight());
        window.setScene(scene);
        window.show();
    }
}

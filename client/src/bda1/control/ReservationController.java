package bda1.control;

import bda1.model.Reservation;
import bda1.services.ReservationService;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;


public class ReservationController {
    @FXML
    TableView<Reservation> reservationTable;
    @FXML
    TableColumn<Reservation, Integer> id;
    @FXML
    TableColumn<Reservation, String> rue;
    @FXML
    TableColumn<Reservation, String> codePostal;
    @FXML
    TableColumn<Reservation, String> ville;
    @FXML
    TableColumn<Reservation, LocalDate> dateReservation;

    ReservationService reservationService;

    public void initialize() {
        reservationService = new ReservationService();
        id.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getId()).asObject());
        dateReservation.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper<>(itemData.getValue().getDateReservation()));

        ville.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getrAdresse() != null ? itemData.getValue().getrAdresse().getVille() : null));
        rue.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getrAdresse() != null ? itemData.getValue().getrAdresse().getRue() : null));
        codePostal.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getrAdresse() != null ? itemData.getValue().getrAdresse().getCodePostal() : null));


        reservationTable.setItems(FXCollections.observableArrayList(
                reservationService.findAll()
        ));
    }


}

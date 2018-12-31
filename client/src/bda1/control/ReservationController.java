package bda1.control;

import bda1.model.Adresse;
import bda1.model.Facture;
import bda1.model.Reservation;
import bda1.services.ReservationService;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;

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
    @FXML
    TableColumn<Reservation, LocalDate> dateEmission;
    @FXML
    TableColumn<Reservation, Boolean> reglee;
    @FXML
    TableColumn<Reservation, Float> total;

    ReservationService reservationService;

    public void initialize() {
        reservationService = new ReservationService();
        id.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getId()).asObject());
        dateReservation.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper<>(itemData.getValue().getDateReservation()));

        ville.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getrAdresse() != null ? itemData.getValue().getrAdresse().getVille() : null));
        rue.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getrAdresse() != null ? itemData.getValue().getrAdresse().getRue() : null));
        codePostal.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getrAdresse() != null ? itemData.getValue().getrAdresse().getCodePostal() : null));

        dateEmission.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper<>(itemData.getValue().getrFacture() != null ? itemData.getValue().getrFacture().getDateEmission() : null));
        total.setCellValueFactory(itemData -> itemData.getValue().getrFacture() != null ? new ReadOnlyFloatWrapper(itemData.getValue().getrFacture().getTotal()).asObject() : null);
        reglee.setCellFactory(CheckBoxTableCell.forTableColumn(reglee));
        reglee.setCellValueFactory(itemData -> itemData.getValue().getrFacture() != null ? new ReadOnlyBooleanWrapper(itemData.getValue().getrFacture().isReglee()) : null);

        Reservation reservation = new Reservation(1, LocalDate.now());
        reservation.setrFacture(new Facture(1, LocalDate.now(), 12313, true));
        reservation.setrAdresse(new Adresse(1, "dasf", "gdsa", "gdsg"));

        Reservation reservation2 = new Reservation(1, LocalDate.now());
        reservation2.setrFacture(new Facture(2, LocalDate.now(), 12313, false));
        reservation2.setrAdresse(new Adresse(2, "dasf", "gdsa", "gdsg"));

        reservationTable.setItems(FXCollections.observableArrayList(
                reservation,
                reservation2
/*
                reservationService.findAll()
*/
        ));
    }


}

package bda1.control;

import bda1.model.Facture;
import bda1.model.Reservation;
import bda1.services.FactureService;
import bda1.services.ReservationService;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;


public class ReservationController {

    @FXML
    JFXButton delete;
    @FXML
    JFXButton edit;
    @FXML
    JFXButton add;
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

    ObservableList<Reservation> model;

    ReservationService reservationService;
    FactureService factureService;

    private Reservation selectedItem;

    public void initialize() {
        reservationService = new ReservationService();
        factureService = new FactureService();
        setUpTable();
        fillTable();
        listenOnRowSelection();
    }


    public void listenOnRowSelection() {
        reservationTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedItem = newSelection;
            disableEditDeleteButtons(selectedItem == null);
        });
    }

    public void disableEditDeleteButtons(boolean disable) {
        edit.setDisable(disable);
        delete.setDisable(disable);
    }

    public void setUpTable() {
        id.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getId()).asObject());
        dateReservation.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper<>(itemData.getValue().getDateReservation()));
        ville.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getrAdresse() != null ? itemData.getValue().getrAdresse().getVille() : null));
        rue.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getrAdresse() != null ? itemData.getValue().getrAdresse().getRue() : null));
        codePostal.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getrAdresse() != null ? itemData.getValue().getrAdresse().getCodePostal() : null));
        dateEmission.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper<>(itemData.getValue().getrFacture() != null ? itemData.getValue().getrFacture().getDateEmission() : null));
        total.setCellValueFactory(itemData -> itemData.getValue().getrFacture() != null ? new ReadOnlyFloatWrapper(itemData.getValue().getrFacture().getTotal()).asObject() : null);
        reglee.setCellFactory(CheckBoxTableCell.forTableColumn(reglee));
        reglee.setCellValueFactory(itemData -> itemData.getValue().getrFacture() != null ? new ReadOnlyBooleanWrapper(itemData.getValue().getrFacture().isReglee()) : null);
    }

    public void fillTable() {
        model = FXCollections.observableArrayList(
                reservationService.findAll()
        );
        reservationTable.setItems(model);
    }


    public void onAdd(ActionEvent event) throws IOException {
        openFrom(new Reservation());
    }

    public void onUpdate(ActionEvent event) throws IOException {
        openFrom(selectedItem);
    }

    public void onDelete(ActionEvent event) throws IOException {
        if (reservationService.delete(selectedItem))
            model.remove(selectedItem);

    }


    public void openFrom(Reservation item) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent form = loader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("ReservationForm.fxml")).openStream());
        ReservationFromController rfc = loader.getController();
        rfc.setReservation(item);
        Scene FromView = new Scene(form);
        Stage stage = new Stage();
        stage.setScene(FromView);
        rfc.saveBtn.setOnAction(event -> {
            onSave(rfc.getReservation());
            rfc.closeFrom();
        });
        stage.showAndWait();
    }

    public void onSave(Reservation reservation) {
        Facture f = reservation.getrFacture();
        reservation.setrFacture(null);
        f.setrReservation(reservation);

        if (reservation.getId() == 0) {
            int id = reservationService.create(reservation);
            f.getrReservation().setId(id);
            int factureId = factureService.create(f);
            f.setId(factureId);
            f.setrReservation(null);
            reservation.setrFacture(f);
            model.add(reservation);
        } else {
            boolean res = factureService.update(f);
            res = res && reservationService.update(reservation);
            f.setrReservation(null);
            reservation.setrFacture(f);
            if (res)
                reservationTable.refresh();
        }
    }

/*
    public Facture cloneFacture(Reservation reservation) {
        Facture facture = new Facture(reservation.getrFacture().getId(),
                reservation.getrFacture().getDateEmission(),
                reservation.getrFacture().getTotal(),
                reservation.getrFacture().isReglee());
        facture.setrReservation(new Reservation(reservation.getId(), reservation.getDateReservation()));
        facture.getrReservation().setrAdresse(new Adresse(reservation.getrAdresse().getId(),
                reservation.getrAdresse().getRue(),
                reservation.getrAdresse().getCodePostal(),
                reservation.getrAdresse().getVille()));
        return facture;
    }
*/
}


package bda1.control;

import bda1.model.Facture;
import bda1.model.Reservation;
import bda1.model.Transport;
import bda1.model.Voyageur;
import bda1.services.*;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class ReservationController {

    @FXML
    JFXButton addTransport;
    @FXML
    JFXButton deleteTransport;
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

    @FXML
    TableView<Voyageur> voyageurTable;
    @FXML
    TableColumn<Voyageur, String> nom;
    @FXML
    TableColumn<Voyageur, String> prenom;
    @FXML
    TableColumn<Voyageur, LocalDate> dateNaissance;
    @FXML
    ComboBox<Transport> transportComboBox;

    @FXML
    TableView<Transport> transportTable;
    @FXML
    TableColumn<Transport, Integer> tid;
    @FXML
    TableColumn<Transport, LocalDateTime> depart;
    @FXML
    TableColumn<Transport, LocalDateTime> arrive;
    @FXML
    TableColumn<Transport, Integer> ttotal;
    @FXML
    TableColumn<Transport, Float> prix;


    ReservationService reservationService;
    VoyageurService voyageurService;
    AvionService avionService;
    TrainService trainService;
    FactureService factureService;

    private Reservation selectedItem;
    private Transport selectedTransportItem;


    public void initialize() {
        reservationService = new ReservationService();
        voyageurService = new VoyageurService();
        factureService = new FactureService();
        trainService = new TrainService();
        avionService = new AvionService();
        setUpTable();
        setUpVoyageurTable();
        setUpTableTransportTable();
        fillTable();
        listenOnRowSelection();
        Set<Transport> comboModel = new HashSet<>();
        comboModel.addAll(avionService.findAll());
        comboModel.addAll(trainService.findAll());
        transportComboBox.setItems(FXCollections.observableArrayList(comboModel));
        transportComboBox.setCellFactory(new Callback<ListView<Transport>, ListCell<Transport>>() {
            @Override
            public ListCell<Transport> call(ListView<Transport> param) {
                return new ListCell<Transport>() {
                    @Override
                    protected void updateItem(Transport t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getId() + "-" + t.getDateDepart());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

    }


    public void listenOnRowSelection() {
        reservationTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedItem = newSelection;
            disableEditDeleteButtons(selectedItem == null);
            if (selectedItem != null) {
                fillVoyageurTable();
                fillTransportTable();
            }
        });

        transportTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedTransportItem = newSelection;
            deleteTransport.setDisable(selectedTransportItem == null);
        });

        transportComboBox.setOnAction(e -> {
            addTransport.setDisable(selectedItem == null || transportComboBox.getValue() == null);
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

    public void setUpVoyageurTable() {
        prenom.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getPrenom()));
        nom.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNom()));
        dateNaissance.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper<>(itemData.getValue().getDateNaissance()));
    }

    public void setUpTableTransportTable() {
        tid.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getId()).asObject());
        depart.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper<>(itemData.getValue().getDateDepart()));
        arrive.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper<>(itemData.getValue().getDateArrivee()));
        ttotal.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getNbrSiegesTotal()).asObject());
        prix.setCellValueFactory(itemData -> new ReadOnlyFloatWrapper(itemData.getValue().getPrix()).asObject());
    }


    public void fillVoyageurTable() {
        voyageurTable.setItems(FXCollections.observableArrayList(
                voyageurService.findByReservation(selectedItem.getId())
        ));
    }

    public void fillTransportTable() {
        transportTable.setItems(FXCollections.observableArrayList(
                reservationService.findTransportByReservation(selectedItem.getId())
        ));
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


    public void onAddTransport(ActionEvent event) throws IOException {
        reservationService.addTransport(selectedItem.getId(), transportComboBox.getValue().getId());
        fillTransportTable();
    }

    public void onDeleteTransport(ActionEvent event) throws IOException {
        reservationService.removeTransport(selectedItem.getId(), selectedTransportItem.getId());
        fillTransportTable();
    }

}


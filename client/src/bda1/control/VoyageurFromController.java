package bda1.control;

import bda1.model.Reservation;
import bda1.model.Voyageur;
import bda1.services.ReservationService;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

public class VoyageurFromController implements Initializable {


    private Voyageur voyageur;

    @FXML
    public Button saveBtn;
    @FXML
    Button closeBtn;
    @FXML
    JFXTextField nom;
    @FXML
    JFXTextField prenom;
    @FXML
    DatePicker dateNissance;
    @FXML
    ComboBox<Reservation> reservation;
    Set<Reservation> model;
    private ReservationService reservationService;

    public void onClose(ActionEvent event) throws IOException {
        closeFrom();
    }

    public void closeFrom() {
        ((Stage) closeBtn.getScene().getWindow()).close();
    }

    public void setVoyageur(Voyageur voyageur) {
        this.voyageur = voyageur;
        initFrom();
    }


    public Voyageur getVoyageur() {
        voyageur.setNom(nom.getText());
        voyageur.setPrenom(prenom.getText());
        voyageur.setDateNaissance(dateNissance.getValue());
        voyageur.setReservationId(reservation.getSelectionModel().getSelectedItem().getId());
        return voyageur;
    }

    public void initFrom() {
        nom.setText(voyageur.getNom());
        prenom.setText(voyageur.getPrenom());
        dateNissance.setValue(voyageur.getDateNaissance());
        Optional<Object> r = Arrays
                .stream(model.toArray())
                .filter(reservation -> ((Reservation) reservation).getId() == voyageur.getReservationId())
                .findFirst();
        reservation.setValue((Reservation) r.orElse(null));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reservationService = new ReservationService();
        model = reservationService.findAll();
        reservation.setItems(FXCollections.observableArrayList(model));
        reservation.setCellFactory(new Callback<ListView<Reservation>, ListCell<Reservation>>() {
            @Override
            public ListCell<Reservation> call(ListView<Reservation> param) {

                return new ListCell<Reservation>() {
                    @Override
                    protected void updateItem(Reservation t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getId() + "-" + t.getDateReservation());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }
}

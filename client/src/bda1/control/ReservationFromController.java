package bda1.control;

import bda1.model.Adresse;
import bda1.model.Facture;
import bda1.model.Reservation;
import bda1.utils.ValidateInput;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservationFromController {


    private Reservation reservation;

    @FXML
    public Button saveBtn;
    @FXML
    Button closeBtn;
    @FXML
    DatePicker reservationDate;
    @FXML
    DatePicker dateEmission;
    @FXML
    JFXCheckBox reglee;
    @FXML
    JFXTextField total;
    @FXML
    JFXTextField codePostal;
    @FXML
    JFXTextField ville;
    @FXML
    JFXTextField rue;

    public void onClose(ActionEvent event) throws IOException {
        closeFrom();
    }

    public void closeFrom() {
        ((Stage) closeBtn.getScene().getWindow()).close();
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
        initFrom();
    }


    public Reservation getReservation() {
        if (reservation.getrFacture() == null)
            reservation.setrFacture(new Facture());
        if (reservation.getrAdresse() == null)
            reservation.setrAdresse(new Adresse());
        reservation.setDateReservation(reservationDate.getValue());
        reservation.getrFacture().setDateEmission(dateEmission.getValue());
        reservation.getrFacture().setReglee(reglee.isSelected());
        reservation.getrFacture().setTotal(Float.parseFloat(total.getText()));
        reservation.getrAdresse().setRue(rue.getText());
        reservation.getrAdresse().setCodePostal(codePostal.getText());
        reservation.getrAdresse().setVille(ville.getText());
        return reservation;
    }

    public void initFrom() {
        if (reservation.getrFacture() == null)
            reservation.setrFacture(new Facture());
        if (reservation.getrAdresse() == null)
            reservation.setrAdresse(new Adresse());

        reservationDate.setValue(reservation.getDateReservation());
        dateEmission.setValue(reservation.getrFacture().getDateEmission());
        reglee.setSelected(reservation.getrFacture().isReglee());
        total.setText(String.valueOf(reservation.getrFacture().getTotal()));
        ValidateInput.floatValidator(total);
        codePostal.setText(reservation.getrAdresse().getCodePostal());
        rue.setText(reservation.getrAdresse().getRue());
        ville.setText(reservation.getrAdresse().getVille());
    }


}

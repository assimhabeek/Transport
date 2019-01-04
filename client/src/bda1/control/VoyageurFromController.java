package bda1.control;

import bda1.model.Voyageur;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;

public class VoyageurFromController {


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
        return voyageur;
    }

    public void initFrom() {
        nom.setText(voyageur.getNom());
        prenom.setText(voyageur.getPrenom());
        dateNissance.setValue(voyageur.getDateNaissance());
    }

}

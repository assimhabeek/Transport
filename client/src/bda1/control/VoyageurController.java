package bda1.control;

import bda1.model.Voyageur;
import bda1.services.VoyageurService;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;


public class VoyageurController {
    @FXML
    TableView<Voyageur> voyageurTable;
    @FXML
    TableColumn<Voyageur, Integer> id;
    @FXML
    TableColumn<Voyageur, String> nom;
    @FXML
    TableColumn<Voyageur, String> prenom;
    @FXML
    TableColumn<Voyageur, LocalDate> dateNaissance;
    VoyageurService voyageurService;

    public void initialize() {
        voyageurService = new VoyageurService();
        id.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getId()).asObject());
        prenom.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getPrenom()));
        nom.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNom()));
        dateNaissance.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper<>(itemData.getValue().getDateNaissance()));
        voyageurTable.setItems(FXCollections.observableArrayList(
                voyageurService.findAll()
        ));
    }


}

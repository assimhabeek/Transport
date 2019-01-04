package bda1.control;

import bda1.model.Voyageur;
import bda1.services.VoyageurService;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;


public class VoyageurController {
    @FXML
    JFXButton delete;
    @FXML
    JFXButton edit;
    @FXML
    JFXButton add;
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
    private VoyageurService voyageurService;
    private Voyageur selectedItem;
    private ObservableList<Voyageur> model;


    public void initialize() {
        voyageurService = new VoyageurService();
        setUpTable();
        fillTable();
        listenOnRowSelection();
    }


    public void onAdd(ActionEvent event) throws IOException {
        openFrom(new Voyageur());
    }

    public void onUpdate(ActionEvent event) throws IOException {
        openFrom(selectedItem);
    }

    public void onDelete(ActionEvent event) throws IOException {
        if (voyageurService.delete(selectedItem))
            model.remove(selectedItem);

    }

    public void listenOnRowSelection() {
        voyageurTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
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
        prenom.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getPrenom()));
        nom.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNom()));
        dateNaissance.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper<>(itemData.getValue().getDateNaissance()));
    }

    public void fillTable() {
        model = FXCollections.observableArrayList(
                voyageurService.findAll()
        );
        voyageurTable.setItems(model);
    }

    private void openFrom(Voyageur item) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent form = loader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("VoyageurForm.fxml")).openStream());
        VoyageurFromController rfc = loader.getController();
        rfc.setVoyageur(item);
        Scene FromView = new Scene(form);
        Stage stage = new Stage();
        stage.setScene(FromView);

        rfc.saveBtn.setOnAction(event -> {
            onSave(rfc.getVoyageur());
            rfc.closeFrom();
        });

        stage.showAndWait();
    }

    public void onSave(Voyageur voyageur) {
        if (voyageur.getId() == 0) {
            voyageur.setId(voyageurService.create(voyageur));
            model.add(voyageur);
        } else if (voyageurService.update(voyageur)) {
            voyageurTable.refresh();
        }
    }
}

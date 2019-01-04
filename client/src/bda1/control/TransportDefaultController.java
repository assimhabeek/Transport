package bda1.control;

import bda1.model.Transport;
import bda1.services.BaseService;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import java.time.LocalDateTime;
import java.util.Objects;


public abstract class TransportDefaultController<T extends Transport, C extends BaseService<T>> {

    @FXML
    JFXButton delete;
    @FXML
    JFXButton edit;
    @FXML
    JFXButton add;
    @FXML
    TableView<T> transportTable;
    @FXML
    TableColumn<T, Integer> id;
    @FXML
    TableColumn<T, LocalDateTime> depart;
    @FXML
    TableColumn<T, LocalDateTime> arrive;
    @FXML
    TableColumn<T, Integer> total;
    @FXML
    TableColumn<T, Float> prix;
    public String fromViewName;

    C service;

    public T selectedItem;

    ObservableList<T> model;


    public void initialize() {

        setUpTable();
        fillTable();
        listenOnRowSelection();
    }


    public void listenOnRowSelection() {
        transportTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
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
        depart.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper<>(itemData.getValue().getDateDepart()));
        arrive.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper<>(itemData.getValue().getDateArrivee()));
        total.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getNbrSiegesTotal()).asObject());
        prix.setCellValueFactory(itemData -> new ReadOnlyFloatWrapper(itemData.getValue().getPrix()).asObject());
    }

    public void fillTable() {
        model = FXCollections.observableArrayList(
                service.findAll()
        );
        transportTable.setItems(model);
    }


    public abstract void onAdd(ActionEvent event) throws IOException;

    public void onUpdate(ActionEvent event) throws IOException {
        openFrom(selectedItem);
    }

    public void onDelete(ActionEvent event) throws IOException {
        if (service.delete(selectedItem))
            model.remove(selectedItem);
    }


    public void openFrom(T item) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent form = loader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(fromViewName)).openStream());
        TransportFromController<T> rfc = loader.getController();
        rfc.setTransport(item);
        Scene FromView = new Scene(form);
        Stage stage = new Stage();
        stage.setScene(FromView);
        rfc.saveBtn.setOnAction(event -> {
            onSave(rfc.getTransport());
            rfc.closeFrom();
        });
        stage.showAndWait();
    }

    public void onSave(T transport) {
        if (transport.getId() == 0) {
            transport.setId(service.create(transport));
            model.add(transport);
        } else if (service.update(transport)) {
            transportTable.refresh();
        }

    }

}


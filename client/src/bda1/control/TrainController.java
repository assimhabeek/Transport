package bda1.control;

import bda1.model.Train;
import bda1.services.TrainService;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;

import java.io.IOException;


public class TrainController extends TransportDefaultController<Train, TrainService> {

    @FXML
    TableColumn<Train, Integer> nombreWagons;
    @FXML
    TableColumn<Train, Boolean> voitureCafeteria;

    public TrainController() {
        super.fromViewName = "TrainForm.fxml";
    }

    @Override
    public void initialize() {
        service = new TrainService();
        super.initialize();
    }

    @Override
    public void setUpTable() {
        super.setUpTable();
        nombreWagons.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getNbrWagons()).asObject());
        voitureCafeteria.setCellFactory(CheckBoxTableCell.forTableColumn(voitureCafeteria));
        voitureCafeteria.setCellValueFactory(itemData -> new ReadOnlyBooleanWrapper(itemData.getValue().isVoitureCafeteria()));
    }

    @Override
    public void onAdd(ActionEvent event) throws IOException {
        openFrom(new Train());
    }

}


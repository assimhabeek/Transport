package bda1.control;

import bda1.model.Avion;
import bda1.services.AvionService;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import java.io.IOException;


public class AvionController extends TransportDefaultController<Avion, AvionService> {

    @FXML
    TableColumn<Avion, String> compagnie;
    @FXML
    TableColumn<Avion, String> typeAppareil;

    public AvionController() {
        super.fromViewName = "AvionForm.fxml";
    }

    @Override
    public void initialize() {
        service = new AvionService();
        super.initialize();
    }

    @Override
    public void setUpTable() {
        super.setUpTable();
        compagnie.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getCompagnie()));
        typeAppareil.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getTypeAppareil()));
    }

    @Override
    public void onAdd(ActionEvent event) throws IOException {
        openFrom(new Avion());
    }

}


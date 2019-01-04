package bda1.control;

import bda1.model.Avion;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class AvionFromController extends TransportFromController<Avion> {

    @FXML
    JFXTextField compagnie;
    @FXML
    JFXTextField typeAppareil;


    public Avion getTransport() {
        super.getTransport();
        transport.setCompagnie(compagnie.getText());
        transport.setTypeAppareil(typeAppareil.getText());
        return transport;
    }

    public void initFrom() {
        super.initFrom();
        compagnie.setText(transport.getCompagnie());
        typeAppareil.setText(transport.getTypeAppareil());
    }

}

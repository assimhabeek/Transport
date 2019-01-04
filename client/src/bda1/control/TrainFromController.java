package bda1.control;

import bda1.model.Train;
import bda1.utils.ValidateInput;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class TrainFromController extends TransportFromController<Train> {

    @FXML
    JFXTextField nombreWagons;
    @FXML
    JFXCheckBox voitureCafeteria;


    public Train getTransport() {
        super.getTransport();
        transport.setNbrWagons(Integer.parseInt(nombreWagons.getText()));
        transport.setVoitureCafeteria(voitureCafeteria.isSelected());
        return transport;
    }

    public void initFrom() {
        super.initFrom();
        nombreWagons.setText(String.valueOf(transport.getNbrWagons()));
        voitureCafeteria.setSelected(transport.isVoitureCafeteria());
        ValidateInput.intValidator(nombreWagons);
    }

}

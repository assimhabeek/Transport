package bda1.control;

import bda1.model.Transport;
import bda1.utils.DateTimePicker;
import bda1.utils.ValidateInput;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TransportFromController<T extends Transport> {


    protected T transport;

    @FXML
    public Button saveBtn;
    @FXML
    Button closeBtn;
    @FXML
    DateTimePicker depart;
    @FXML
    DateTimePicker arrive;
    @FXML
    JFXTextField total;
    @FXML
    JFXTextField prix;


    public void onClose(ActionEvent event) throws IOException {
        closeFrom();
    }

    public void closeFrom() {
        ((Stage) closeBtn.getScene().getWindow()).close();
    }

    public void setTransport(T transport) {
        this.transport = transport;
        initFrom();
    }


    public T getTransport() {
        transport.setDateDepart(depart.getDateTime());
        transport.setDateArrivee(arrive.getDateTime());
        transport.setNbrSiegesTotal(Integer.parseInt(total.getText()));
        transport.setPrix(Float.parseFloat(total.getText()));
        return transport;
    }

    public void initFrom() {
        depart.setDateTime(transport.getDateDepart());
        arrive.setDateTime(transport.getDateArrivee());
        total.setText(String.valueOf(transport.getNbrSiegesTotal()));
        prix.setText(String.valueOf(transport.getPrix()));
        ValidateInput.floatValidator(prix);
        ValidateInput.intValidator(total);
    }

}

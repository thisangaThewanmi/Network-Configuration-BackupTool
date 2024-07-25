package org.example;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Page32Controller {
    public TextField txtHostName;
    public TextField txtTime;
    public TextField txtDate;
    public TextField txtIpAdd;
    public TextField txtSubnetMask;
    public ComboBox<String> cmbPorts;
    public JFXButton btnFinish;

    public void cmbPortsOnAction(MouseEvent mouseEvent) {
        System.out.println("load cmbDeviceType 5 called");
        cmbPorts.getItems().clear();
        cmbPorts.getItems().addAll("FstEth 0/0" , "FstEth 0/1");
    }

    public void btnFinishOnAction(ActionEvent actionEvent) {
        String port = cmbPorts.getValue();
        String Name = txtHostName.getText();
        String Time=txtTime.getText();
        String Date = txtDate.getText();
        String ipAdd =txtIpAdd.getText();
        String subnetMask =txtSubnetMask.getText();

        Router router = new Router(port,Name,Time,Date,ipAdd,subnetMask);



        System.out.println(router);
    }
}

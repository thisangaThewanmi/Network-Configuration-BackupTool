package org.example;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Page1Controller {

    public JFXButton btnBackupAnExsisting;
    public JFXButton btnBackupAnew;
    public ComboBox <String> cmbDeviceType;
    public ComboBox cmbDevice;
    public TextField txtHostNmae;
    public TextField txtIpAdd;
    public TextField txtSubnetMask;
    public ComboBox cmbPort;
    public TextField txtDate;
    public TextField txtRouterTime;
    public Button btnScheduleBackup;
    public  Button btnBackup;

    private   boolean isButtonClicked = false;
    private  boolean isButtonBackupTypeClicked = false;





    @FXML
    public void cmbDeviceTypeOnClick(MouseEvent actionEvent) {
        System.out.println("method calld");
        if(isButtonClicked){
            loadcmbDeviceTypes();
        }else{
            new Alert(Alert.AlertType.ERROR,"Select A BackupType First :(").show();
        }
    }

    private void loadcmbDeviceTypes() {
        System.out.println("load cmbDeviceType called");
        cmbDeviceType.getItems().clear();
        cmbDeviceType.getItems().addAll("Router","Switch","End Devices");
    }

    public void cmbDeviceOnClick(MouseEvent actionEvent) {
    }


    public void btnBackupAnExsitingOnClick(ActionEvent actionEvent) {
        isButtonClicked = true;
        String BackupStatus  = btnBackupAnExsisting.getId(); // Get the ID of the button
        System.out.println("Button ID: " + BackupStatus); // Print the button ID
    }

    public void btnBackupAnewOnClick(ActionEvent actionEvent) {
        isButtonClicked = true;
        String BackupStatus = btnBackupAnew.getId(); // Get the ID of the button
        System.out.println("Button ID: " + BackupStatus); // Print the button ID
    }

    public void btnBackupOnclick(ActionEvent actionEvent) {
        isButtonBackupTypeClicked = true;
        String BackupType= btnBackup.getId(); // Get the ID of the button
        System.out.println("Button ID: " + BackupType); // Prin
    }

    public void btnSheduleBackupOnClick(ActionEvent actionEvent) {
        isButtonBackupTypeClicked = true;
        String btnBackup = btnScheduleBackup.getId(); // Get the ID of the button
        System.out.println("Button ID: " + btnBackup); // Prin
    }

    public void cmbPortOnAction(MouseEvent mouseEvent) {
        if(isButtonClicked){
            loadcmbPorts();
        }else{
            new Alert(Alert.AlertType.ERROR,"Select A BackupType First :(").show();
        }

    }

    private void loadcmbPorts() {
        System.out.println("load cmbDeviceType called");
        cmbPort.getItems().clear();
        cmbPort.getItems().addAll("FastEthernet 0/0","FastEthernet 0/1","FastEthernet 0/2");
    }
}


package org.example;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Page3Controller {
    public ComboBox <String> cmbBackupStatus;
    public ComboBox <String> cmbDeviceTypes;
    public ComboBox <String> cmbDevice;
    public ComboBox <String>cmbBackupType;
    public JFXButton btnNext;
    public AnchorPane ConfPane;

    /*public List<String> backupStatus = new ArrayList<>();*/

    private String selectedBackupStatus;
    private String selectedDeviceType;
    private String selectedDevice;
    private String selectedBackupType;








    public void cmbBackupStatusOnAction(MouseEvent mouseEvent) {
        System.out.println("load cmbDeviceType 2 called");
        cmbBackupStatus.getItems().clear();
        cmbBackupStatus.getItems().addAll("Backup An Exsisting Device","Backup A new Device");
    }

    public void cmbDeviceTypesOnAction(MouseEvent mouseEvent) {
        System.out.println("load cmbDeviceType 3 called");
        cmbDeviceTypes.getItems().clear();
        cmbDeviceTypes.getItems().addAll("Router","Switch","End Devices");
    }

    public void cmbDeviceOnAction(MouseEvent mouseEvent) {
        /*System.out.println("load cmbDeviceType 4 called");
        cmbBackupStatu.getItems().clear();*/

    }



    public void cmbBackupTypeOnAction(MouseEvent mouseEvent) {
        System.out.println("load cmbDeviceType 5 called");
        cmbBackupType.getItems().clear();
        cmbBackupType.getItems().addAll("Backup" , "Scheduled backup");
    }

    public void btnNextOnAction(ActionEvent actionEvent) throws IOException {
        selectedBackupStatus = cmbBackupStatus.getValue();
        selectedDeviceType = cmbDeviceTypes.getValue();
        selectedDevice = cmbDevice.getValue();
        selectedBackupType = cmbBackupType.getValue();

        Parent node = FXMLLoader.load(this.getClass().getResource("/veiw/Page32.fxml"));

            this.ConfPane.getChildren().clear();
            this.ConfPane.getChildren().add(node);


    }

  /*   public void loadComboBox(List<String> list, ComboBox<String> comboBox) {
        System.out.println("load cmbDeviceType called");
         comboBox.getItems().clear();
         comboBox.getItems().addAll(list);
    }*/
}

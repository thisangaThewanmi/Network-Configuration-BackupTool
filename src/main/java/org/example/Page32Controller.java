package org.example;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public void btnFinishOnAction(ActionEvent actionEvent) throws SQLException {
        String port = cmbPorts.getValue();
        String Name = txtHostName.getText();
        String Time=txtTime.getText();
        String Date = txtDate.getText();
        String ipAdd =txtIpAdd.getText();
        String subnetMask =txtSubnetMask.getText();

        Router router = new Router(port,Name,Time,Date,ipAdd,subnetMask);

       Connection connection = DbConnection.getInstance().getConnection();
       PreparedStatement pstm = connection.prepareStatement("insert into routerBackup (port, name, time, date, ip_address, subnet_mask) values(?,?,?,?,?,?)");
       pstm.setString(1,router.getPort());
        pstm.setString(2,router.getName());
        pstm.setString(3,router.getTime());
        pstm.setString(4,router.getDate());
        pstm.setString(5,router.getIpAdd());
        pstm.setString(6,router.getSubnetMask());

        if(pstm.executeUpdate()>0){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved to Database").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error occored :(").show();
        }


        System.out.println(router);

        cmbPorts.setValue(null);
        txtDate.clear();
        txtHostName.clear();
        txtTime.clear();
        txtIpAdd.clear();
        txtSubnetMask.clear();
    }

}

package org.example;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.event.ActionEvent;
import java.sql.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class Page33Controller {
    public TextField txtHostName;
    public TextField txtTime;
    public TextField txtDate;
    public TextField txtIpAdd;
    public TextField txtSubnetMask;
    public ComboBox<String> cmbPorts;
    public JFXButton btnFinish;
    public TextField txtTimeStamp;



    public void initialize() {
        try {
            checkAndDeleteExpiredConfigurations();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void cmbPortsOnAction(MouseEvent mouseEvent) {
        System.out.println("load cmbDeviceType 5 called");
        cmbPorts.getItems().clear();
        cmbPorts.getItems().addAll("FstEth 0/0" , "FstEth 0/1");
    }






    public void btnFinishOnAction(javafx.event.ActionEvent actionEvent) throws SQLException {
        String port = cmbPorts.getValue();
        String Name = txtHostName.getText();
        String Time = txtTime.getText();
        String Date = txtDate.getText();
        String ipAdd = txtIpAdd.getText();
        String subnetMask = txtSubnetMask.getText();
        String timeStamp = txtTimeStamp.getText();

        // Convert the timestamp string to a Timestamp object
        Timestamp expiration = Timestamp.valueOf(timeStamp);

/*
        Timestamp expiration = Timestamp.from(Instant.now().plus(10, ChronoUnit.MINUTES)); // Set expiration to 10 minutes from now
*/

        Router2 router = new Router2(port, Name, Time, Date, ipAdd, subnetMask, expiration);

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO routerSheduledBackup (port, name, time, date, ip_address, subnet_mask, expiration) VALUES (?, ?, ?, ?, ?, ?, ?)");
        pstm.setString(1, router.getPort());
        pstm.setString(2, router.getName());
        pstm.setString(3, router.getTime());
        pstm.setString(4, router.getDate());
        pstm.setString(5, router.getIpAdd());
        pstm.setString(6, router.getSubnetMask());
        pstm.setTimestamp(7, router.getExpiration()); // Set the expiration timestamp

        if (pstm.executeUpdate() > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved to Database").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error occurred :(").show();
        }

        System.out.println(router);

        cmbPorts.setValue(null);
        txtDate.clear();
        txtHostName.clear();
        txtTime.clear();
        txtIpAdd.clear();
        txtSubnetMask.clear();
        txtTimeStamp.clear();
    }



    public void checkAndDeleteExpiredConfigurations() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id, port, name, time, date, ip_address, subnet_mask, expiration FROM routerSheduledBackup WHERE expiration < CURRENT_TIMESTAMP");
        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String port = resultSet.getString("port");
            String name = resultSet.getString("name");
            String time = resultSet.getString("time");
            String date = resultSet.getString("date");
            String ipAddress = resultSet.getString("ip_address");
            String subnetMask = resultSet.getString("subnet_mask");
            Timestamp expiration = resultSet.getTimestamp("expiration");

            // Show an alert dialog to the user
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Configuration Expired");
            alert.setHeaderText("Router configuration expired");
            alert.setContentText("The router configuration for " + name + " has expired. Do you want to restore or delete it?");

            ButtonType restoreButton = new ButtonType("Restore");
            ButtonType deleteButton = new ButtonType("Delete");
            /*ButtonType cancelButton = new JFXButton.ButtonType("Cancel", ButtonType.CANCEL_CLOSE);*/

            alert.getButtonTypes().setAll(restoreButton, deleteButton/*, cancelButton*/);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == deleteButton) {
                    // Delete the expired configuration
                    deleteConfiguration(id);
                } else if (result.get() == restoreButton) {
                    // Code to restore the configuration (e.g., update expiration time)
                    restoreConfiguration(id);
                }
            }
        }
    }

    private void restoreConfiguration(int id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE routerSheduledBackup SET expiration = CURRENT_TIMESTAMP + INTERVAL '10' MINUTE WHERE id = ?");
        pstm.setInt(1, id);
        pstm.executeUpdate();
    }

    private void deleteConfiguration(int id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM routerSheduledBackup WHERE id = ?");
        pstm.setInt(1, id);
        pstm.executeUpdate();
    }
}

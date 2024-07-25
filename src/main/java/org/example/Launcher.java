package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent node = FXMLLoader.load(getClass().getResource("/veiw/Page3.fxml"));

        Scene scene = new Scene(node);
        stage.setScene(scene);
        stage.setTitle("advsabj");
        stage.centerOnScreen();
        stage.show();

    }
}

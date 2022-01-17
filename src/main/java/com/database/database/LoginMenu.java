package com.database.database;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
public class LoginMenu {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private PasswordField Pass;
    @FXML
    private TextField UserName;

    @FXML
    void instagram(ActionEvent event) throws IOException {

        String url = "https://www.instagram.com/oregano_tulkarm/";
        Desktop.getDesktop().browse(java.net.URI.create(url));
    }

    @FXML
    void facebook(ActionEvent event) throws URISyntaxException, IOException {

        String url = "https://www.facebook.com/oreganotulkarm";
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
    }

    @FXML
    void login(ActionEvent event) throws IOException {

        if(UserName.getText().equals("admin")&& Pass.getText().equals("1234")){

            try {

                root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
                stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter right User name and Password!!!");
            alert.showAndWait();
        }
        //UserName.clear();
        Pass.clear();


    }

}

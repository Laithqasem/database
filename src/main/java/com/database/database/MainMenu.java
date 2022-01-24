package com.database.database;

//#fdc623   #991b1f   -fx-background-color      D88722   af4626

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenu {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    void Employees(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("employee-view.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @FXML
    void Meals(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("MealsPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void Orders(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("OrdersWindow.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void Roles(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("role-view.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

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
    void Customer(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("customerWindow.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }










    @FXML
    void ExpensesWindow(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("expensesWindow.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void SupplieWindow(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("SuppliesWindow.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}


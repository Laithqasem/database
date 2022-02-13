package com.database.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class exp_for_supInsertWindow {

    public Connection connect=null;

    @FXML
    private TextField addBillId;

    @FXML
    private TextField addPricePerUnit;

    @FXML
    private TextField addTypeId;

    @FXML
    private TextField addTypeQuant;


    @FXML
    public void Back(ActionEvent event) {

        System.out.println("Back pressed2");
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exp_for_supWindow.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    void Insert()  throws ClassNotFoundException, SQLException {

        SuppliesWindow.connectDataBase();
        String sql="Insert into exp_for_sup (BillId, TypeId,PricePerUnit, TypeQuant) values (?,?,?,?)";
        PreparedStatement statment = SuppliesWindow.connect.prepareStatement(sql);
        statment.setInt(1, Integer.parseInt(addBillId.getText()));
        statment.setInt(2, Integer.parseInt(addTypeId.getText()));
        statment.setInt(3, Integer.parseInt(addPricePerUnit.getText()));
        statment.setInt(4, Integer.parseInt(addTypeQuant.getText()));
        statment.executeUpdate() ;
        System.out.println("insert done");
        addTypeId.clear();
        addBillId.clear();
        addPricePerUnit.clear();
        addTypeQuant.clear();
    }
}

package com.database.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class SuppliesInsertWindow {

    private Stage stage;
    private Scene scene;
    private Parent root;
    ActionEvent event;
    public Connection connect=null;
    @FXML
    private TextField addQuantity;
    @FXML
    private DatePicker addexpireDate;
    @FXML
    private TextField addTypeId;

    @FXML
    private TextField addTypeName;

    @FXML
    public void Back(ActionEvent event) {

        System.out.println("BAck pressed2");
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

    @FXML
    void Insert(ActionEvent event)  throws ClassNotFoundException, SQLException {

        SuppliesWindow.connectDataBase();
        String sql="Insert into supplies (TypeId, TypeName,Quantity, ExpireDate) values (?,?,?,?)";
        PreparedStatement statment = SuppliesWindow.connect.prepareStatement(sql);
        statment.setInt(1, Integer.valueOf(addTypeId.getText()));
        statment.setString(2, addTypeName.getText());
        statment.setInt(3, Integer.valueOf(addQuantity.getText()));
        statment.setString(4, addexpireDate.getValue().toString());
        statment.executeUpdate() ;
        System.out.println("insert done");
        addTypeId.clear();
        addTypeName.clear();
        addQuantity.clear();
    }

}

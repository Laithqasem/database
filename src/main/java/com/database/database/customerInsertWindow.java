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

public class customerInsertWindow {
    private Stage stage;
    private Scene scene;
    private Parent root;
    ActionEvent event;
    public Connection connect=null;

    @FXML
    private TextField address;

    @FXML
    private TextField cid;

    @FXML
    private TextField cname;

    @FXML
    private TextField phone;

    @FXML
    public void Back(ActionEvent event) {

        System.out.println("BAck pressed2");
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
    void Insert(ActionEvent event)  throws ClassNotFoundException, SQLException {

        SuppliesWindow.connectDataBase();
        String sql="Insert into customer (C_id, C_name, Phone, Address) values (?,?,?,?)";
        PreparedStatement statment = SuppliesWindow.connect.prepareStatement(sql);
        statment.setInt(1, Integer.valueOf(cid.getText()));
        statment.setString(2, cname.getText());
        statment.setInt(3, Integer.valueOf(phone.getText()));
        statment.setString(4, address.getText());
        statment.executeUpdate() ;
        System.out.println("insert done");
        cid.clear();
        cname.clear();
        phone.clear();
        address.clear();
    }

}

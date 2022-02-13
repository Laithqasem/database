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

public class customerInsertWindow {

    public Connection connect=null;

    @FXML
    private TextField address;

    @FXML
    private TextField cname;

    @FXML
    private TextField phone;

    @FXML
    public void Back(ActionEvent event) {

        System.out.println("Back pressed");
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("customerWindow.fxml")));
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
        customer rc;

        String sql="Insert into customer (C_name, Phone, Address) values (?,?,?)";
        PreparedStatement statment = SuppliesWindow.connect.prepareStatement(sql);
        statment.setString(1, cname.getText());
        statment.setInt(2, Integer.parseInt(phone.getText()));
        statment.setString(3, address.getText());
        statment.executeUpdate() ;
        rc = new customer(LoginMenu.customers.get(LoginMenu.customers.size()-1).getC_id() + 1,
                cname.getText(), Integer.parseInt(phone.getText()), address.getText());
        LoginMenu.customers.add(rc);
        System.out.println("insert done");
        cname.clear();
        phone.clear();
        address.clear();
    }

}

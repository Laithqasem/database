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

public class ExpensesInsertWindow {

    private Stage stage;
    private Scene scene;
    private Parent root;
    ActionEvent event;
    public Connection connect=null;
    @FXML
    private TextField addBillId;

    @FXML
    private DatePicker addBillDate;


    @FXML
    private TextField addTotalPay;


    @FXML
    public void Back(ActionEvent event) {

        System.out.println("BAck pressed2");
        try {
            root = FXMLLoader.load(getClass().getResource("ExpensesWindow.fxml"));
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
        String sql="Insert into expenses (BillId, BillDate,TotalPay) values (?,?,?)";
        PreparedStatement statment = SuppliesWindow.connect.prepareStatement(sql);
        statment.setInt(1, Integer.valueOf(addBillId.getText()));
        statment.setString(2, addBillDate.getValue().toString());
        statment.setInt(3, Integer.valueOf(addTotalPay.getText()));
        statment.executeUpdate() ;
        System.out.println("insert done");

        addBillId.clear();
        addTotalPay.clear();
    }

}

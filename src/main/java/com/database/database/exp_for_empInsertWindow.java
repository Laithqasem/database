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

public class exp_for_empInsertWindow {

    public Connection connect=null;

    @FXML
    private TextField addBillId;

    @FXML
    private TextField addEId;

    @FXML
    private TextField addBaseSalary;

    @FXML
    private TextField addOvertimePrice;


    @FXML
    public void Back(ActionEvent event) {

        System.out.println("Back pressed2");
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exp_for_empWindow.fxml")));
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
        String sql="Insert into exp_for_emp (BillId, EId,BaseSalary, OvertimePrice) values (?,?,?,?)";
        PreparedStatement statment = SuppliesWindow.connect.prepareStatement(sql);
        statment.setInt(1, Integer.parseInt(addBillId.getText()));
        statment.setInt(2, Integer.parseInt(addEId.getText()));
        statment.setInt(3, Integer.parseInt(addBaseSalary.getText()));
        statment.setInt(4, Integer.parseInt(addOvertimePrice.getText()));
        statment.executeUpdate() ;
        System.out.println("insert done");
        addBillId.clear();
        addEId.clear();
        addBaseSalary.clear();
        addOvertimePrice.clear();
    }
}

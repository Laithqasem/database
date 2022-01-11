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

public class exp_for_empInsertWindow {

    private Stage stage;
    private Scene scene;
    private Parent root;
    ActionEvent event;
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

        System.out.println("BAck pressed2");
        try {
            root = FXMLLoader.load(getClass().getResource("exp_for_empWindow.fxml"));
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
        String sql="Insert into exp_for_emp (BillId, EId,BaseSalary, OvertimePrice) values (?,?,?,?)";
        PreparedStatement statment = SuppliesWindow.connect.prepareStatement(sql);
        statment.setInt(1, Integer.valueOf(addBillId.getText()));
        statment.setInt(2, Integer.valueOf(addEId.getText()));
        statment.setInt(3, Integer.valueOf(addBaseSalary.getText()));
        statment.setInt(4, Integer.valueOf(addOvertimePrice.getText()));
        statment.executeUpdate() ;
        System.out.println("insert done");
        addBillId.clear();
        addEId.clear();
        addBaseSalary.clear();
        addOvertimePrice.clear();
    }
}

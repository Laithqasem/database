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
import java.sql.*;
import java.util.Objects;

public class ExpensesUpdateWindow {

    public Connection connect=null;

    @FXML
    private TextField updateBillId;

    @FXML
    private DatePicker updateBillDate;

    @FXML
    private TextField updateTotalPay;

    @FXML
    void Back(ActionEvent event) {

        System.out.println("Back pressed2");
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ExpensesWindow.fxml")));
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
    void update() throws SQLException, ClassNotFoundException {


        if(updateBillId!=null){

            try {
                System.out.println("update  expenses set BillDate = '"+updateBillDate.getValue().toString() + "' where BillId = "+updateBillId.getText());

                SuppliesWindow.connectDataBase();
                try {
                    SuppliesWindow.connectDataBase();
                    String sql="update expenses set BillDate=?,TotalPay=? where BillId=?";
                    PreparedStatement statment = SuppliesWindow.connect.prepareStatement(sql);
                    statment.setInt(3, Integer.parseInt(updateBillId.getText()));
                    statment.setString(1, updateBillDate.getValue().toString());
                    statment.setInt(2, Integer.parseInt(updateTotalPay.getText()));

                    if(updateBillDate.getValue().toString().isEmpty()){
                        String temp = null;

                        String SQL = "select BillDate from expenses where BillId="+updateBillId.getText();
                        Statement stmt2 = SuppliesWindow.connect.createStatement();
                        ResultSet rs = stmt2.executeQuery(SQL);


                        while (rs.next())
                            temp= rs.getString(1);

                        statment.setString(1, temp);
                    }

                    if(updateTotalPay.getText().isEmpty()){

                        int temp = 0;

                        String SQL = "select TotalPay from expenses where BillId="+updateBillId.getText();
                        Statement stmt2 = SuppliesWindow.connect.createStatement();
                        ResultSet rs = stmt2.executeQuery(SQL);

                        while (rs.next())
                            temp= Integer.parseInt(rs.getString(1));

                        System.out.println(temp);

                        statment.setInt(    3, temp);
                    }

                    statment.executeUpdate() ;
                    updateBillId.clear();
                    updateTotalPay.clear();

                }
                catch(SQLException s) {
                    s.printStackTrace();
                    System.out.println("SQL statement is not executed!");
                }

                System.out.println("Connection closed");

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        assert updateBillId != null;
        updateBillId.clear();
        updateTotalPay.clear();
    }

}

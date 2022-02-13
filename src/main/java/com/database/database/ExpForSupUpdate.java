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
import java.sql.*;
import java.util.Objects;

public class ExpForSupUpdate {

    PreparedStatement statement;
    public Connection connect=null;

    @FXML
    private TextField updateBillId;

    @FXML
    private TextField updatePricePerUnit;

    @FXML
    private TextField updateTypeId;

    @FXML
    private TextField updateTypeQuant;

    @FXML
    void Back(ActionEvent event) {

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
    void update() {
        if(updateTypeId!=null && updateBillId!=null ){
            try {
                SuppliesWindow.connectDataBase();
                try {

                    SuppliesWindow.connectDataBase();
                    String sql="update exp_for_sup set TypeQuant=?,PricePerUnit=? where TypeId=? AND BillId=?";
                    if(updateTypeQuant.getText().isEmpty()){
                        sql="update exp_for_sup set PricePerUnit=? where TypeId=? AND BillId=?";
                        statement = SuppliesWindow.connect.prepareStatement(sql);
                        statement.setInt(1, Integer.parseInt(updatePricePerUnit.getText()));
                        statement.setInt(2, Integer.parseInt(updateTypeId.getText()));
                        statement.setInt(3, Integer.parseInt(updateBillId.getText()));

                    }else if(updatePricePerUnit.getText().isEmpty()){

                        sql="update exp_for_sup set TypeQuant=? where TypeId=? AND BillId=?";
                        statement = SuppliesWindow.connect.prepareStatement(sql);
                        statement.setInt(1, Integer.parseInt(updateTypeQuant.getText()));
                        statement.setInt(2, Integer.parseInt(updateTypeId.getText()));
                        statement.setInt(3, Integer.parseInt(updateBillId.getText()));

                    }else{
                        statement = SuppliesWindow.connect.prepareStatement(sql);
                        statement.setInt(1, Integer.parseInt(updateTypeQuant.getText()));
                        statement.setInt(2, Integer.parseInt(updatePricePerUnit.getText()));
                        statement.setInt(3, Integer.parseInt(updateTypeId.getText()));
                        statement.setInt(4, Integer.parseInt(updateBillId.getText()));
                    }


/*
                    if(updatePricePerUnit.getText().isEmpty()){
                        int temp = 1;
                        String sql2="select PricePerUnit from exp_for_sup where BillId= "+updateBillId.getText()+" TypeId="+updateTypeId.getText();

                        String SQL = sql2;
                        Statement stmt2 = SuppliesWindow.connect.createStatement();
                        ResultSet rs = stmt2.executeQuery(SQL);


                        while (rs.next())
                            temp= Integer.parseInt(rs.getString(1));

                        statement.setInt(1, temp);
                    }

                    if(updateTypeQuant.getText().isEmpty()){

                        int temp = 0;
                        String sql2="select TypeQuant from exp_for_sup where BillId= "+updateBillId.getText()+" TypeId="+updateTypeId.getText();

                        String SQL = sql2;
                        Statement stmt2 = SuppliesWindow.connect.createStatement();
                        ResultSet rs = stmt2.executeQuery(SQL);


                        while (rs.next())
                            temp= Integer.parseInt(rs.getString(1));

                        System.out.println(temp);

                        statement.setInt(    3, temp);
                    }
*/
                    statement.executeUpdate() ;
                    updateTypeId.clear();
                    updateBillId.clear();
                    updateTypeQuant.clear();
                    updatePricePerUnit.clear();
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
        assert updateTypeId != null;
        updateTypeId.clear();
        updateBillId.clear();
        updateTypeQuant.clear();
        updatePricePerUnit.clear();

    }
}

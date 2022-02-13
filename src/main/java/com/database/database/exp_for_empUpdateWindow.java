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

public class exp_for_empUpdateWindow {

    public Connection connect=null;

    @FXML
    private TextField updateBillId;

    @FXML
    private TextField updateEId;

    @FXML
    private TextField updateBaseSalary;

    @FXML
    private TextField updateOvertimePrice;


    @FXML
    void Back(ActionEvent event) {

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
    void update() throws SQLException, ClassNotFoundException {

/* private int BillId;
        private int EId;
        private int BaseSalary;
        private int  OvertimePrice;
        exp_for_emp*/
        if(updateEId!=null && updateBillId!=null ){

            try {

                SuppliesWindow.connectDataBase();
                try {

                    SuppliesWindow.connectDataBase();
                    String sql="update exp_for_emp set BaseSalary=?,OvertimePrice=? where EId=? AND BillId=?";
                    PreparedStatement statment = SuppliesWindow.connect.prepareStatement(sql);
                    statment.setInt(1, Integer.parseInt(updateBaseSalary.getText()));
                    statment.setInt(2, Integer.parseInt(updateOvertimePrice.getText()));
                    statment.setInt(3, Integer.parseInt(updateEId.getText()));
                    statment.setInt(4, Integer.parseInt(updateBillId.getText()));


                    if(updateBaseSalary.getText().isEmpty()){
                        String temp = null;

                        String SQL = "select BaseSalary from exp_for_emp where BillId= "+updateBillId.getText()+" EId="+updateEId.getText();
                        Statement stmt2 = SuppliesWindow.connect.createStatement();
                        ResultSet rs = stmt2.executeQuery(SQL);

                        while (rs.next())
                            temp= rs.getString(1);

                        statment.setString(1, temp);
                    }

                    if(updateOvertimePrice.getText().isEmpty()){

                        int temp = 0;

                        String SQL = "select OvertimePrice from exp_for_emp where BillId= "+updateBillId.getText()+" EId="+updateEId.getText();
                        Statement stmt2 = SuppliesWindow.connect.createStatement();
                        ResultSet rs = stmt2.executeQuery(SQL);


                        while (rs.next())
                            temp= Integer.parseInt(rs.getString(1));

                        System.out.println(temp);

                        statment.setInt(    3, temp);
                    }

                    statment.executeUpdate() ;
                    updateEId.clear();
                    updateBillId.clear();
                    updateOvertimePrice.clear();
                    updateBaseSalary.clear();

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

        assert updateEId != null;
        updateEId.clear();
        updateBillId.clear();
        updateOvertimePrice.clear();
        updateBaseSalary.clear();

    }

}

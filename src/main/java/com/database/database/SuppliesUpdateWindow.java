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
public class SuppliesUpdateWindow {

    private Stage stage;
    private Scene scene;
    private Parent root;
    ActionEvent event;
    public Connection connect=null;
    @FXML
    private DatePicker updateexpireDate;

    @FXML
    private TextField updateQuantity;

    @FXML
    private TextField updateTypeId;

    @FXML
    private TextField updateTypeName;


    @FXML
    void Back(ActionEvent event) {

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
    void update(ActionEvent event) throws SQLException, ClassNotFoundException {


        if(updateTypeId!=null){

            try {
                System.out.println("update  supplies set TypeName = '"+updateTypeName.getText() + "' where TypeId = "+updateTypeId.getText());

                SuppliesWindow.connectDataBase();
                try {
                    SuppliesWindow.connectDataBase();
                    String sql="update supplies set TypeName=?,Quantity=?,ExpireDate=? where TypeId=?";
                    PreparedStatement statment = SuppliesWindow.connect.prepareStatement(sql);
                    statment.setInt(4, Integer.valueOf(updateTypeId.getText()));
                    statment.setString(1, updateTypeName.getText());
                    statment.setString(2, updateQuantity.getText());
                    statment.setString(3, updateexpireDate.getValue().toString());

                    if(updateTypeName.getText().isEmpty()){
                        String temp = null;
                        String sql2="select TypeName from supplies where TypeId="+updateTypeId.getText();

                        String SQL = sql2;
                        Statement stmt2 = SuppliesWindow.connect.createStatement();
                        ResultSet rs = stmt2.executeQuery(SQL);


                        while (rs.next())
                            temp= rs.getString(1);

                        statment.setString(1, temp);
                    }

                    if(updateQuantity.getText().isEmpty()){

                        int temp = 0;
                        String sql2="select Quantity from supplies where TypeId="+updateTypeId.getText();

                        String SQL = sql2;
                        Statement stmt2 = SuppliesWindow.connect.createStatement();
                        ResultSet rs = stmt2.executeQuery(SQL);


                        while (rs.next())
                            temp= Integer.parseInt(rs.getString(1));

                        System.out.println(temp);

                        statment.setInt(    3, temp);
                    }


                    if(updateexpireDate.getValue().toString().isEmpty()){
                        String temp = null;
                        String sql2="select ExpireDate from supplies where TypeId="+updateTypeId.getText();

                        String SQL = sql2;
                        Statement stmt2 = SuppliesWindow.connect.createStatement();
                        ResultSet rs = stmt2.executeQuery(SQL);


                        while (rs.next())
                            temp= rs.getString(1);

                        statment.setString(3, temp);
                    }
                    statment.executeUpdate() ;
                    updateTypeId.clear();
                    updateTypeName.clear();
                    updateQuantity.clear();



                }
                catch(SQLException s) {
                    s.printStackTrace();
                    System.out.println("SQL statement is not executed!");

                }


                System.out.println("Connection closed");

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }


        updateTypeId.clear();
        updateTypeName.clear();
        updateQuantity.clear();



    }

}

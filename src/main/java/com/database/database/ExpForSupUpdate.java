package com.database.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ExpForSupUpdate {
    private Stage stage;
    private Scene scene;
    private Parent root;
    ActionEvent event;
    PreparedStatement statment;
    public Connection connect=null;
    @FXML
    private ImageView background;

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

        System.out.println("BAck pressed2");
        try {
            root = FXMLLoader.load(getClass().getResource("exp_for_supWindow.fxml"));
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
    void update(ActionEvent event) {
        if(updateTypeId!=null && updateBillId!=null ){

            try {

                SuppliesWindow.connectDataBase();
                try {

                    SuppliesWindow.connectDataBase();
                    String sql="update exp_for_sup set TypeQuant=?,PricePerUnit=? where TypeId=? AND BillId=?";
                    if(updateTypeQuant.getText().isEmpty()){
                        sql="update exp_for_sup set PricePerUnit=? where TypeId=? AND BillId=?";
                        statment = SuppliesWindow.connect.prepareStatement(sql);
                        statment.setInt(1, Integer.valueOf(updatePricePerUnit.getText()));
                        statment.setInt(2, Integer.valueOf(updateTypeId.getText()));
                        statment.setInt(3, Integer.valueOf(updateBillId.getText()));

                    }else if(updatePricePerUnit.getText().isEmpty()){

                        sql="update exp_for_sup set TypeQuant=? where TypeId=? AND BillId=?";
                        statment = SuppliesWindow.connect.prepareStatement(sql);
                        statment.setInt(1, Integer.valueOf(updateTypeQuant.getText()));
                        statment.setInt(2, Integer.valueOf(updateTypeId.getText()));
                        statment.setInt(3, Integer.valueOf(updateBillId.getText()));


                    }else{
                        statment = SuppliesWindow.connect.prepareStatement(sql);
                        statment.setInt(1, Integer.valueOf(updateTypeQuant.getText()));
                        statment.setInt(2, Integer.valueOf(updatePricePerUnit.getText()));
                        statment.setInt(3, Integer.valueOf(updateTypeId.getText()));
                        statment.setInt(4, Integer.valueOf(updateBillId.getText()));
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

                        statment.setInt(1, temp);
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

                        statment.setInt(    3, temp);
                    }
*/
                    statment.executeUpdate() ;
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

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }


        updateTypeId.clear();
        updateBillId.clear();
        updateTypeQuant.clear();
        updatePricePerUnit.clear();



    }

}

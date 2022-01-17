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

public class customerUpdateWindow {
    private Stage stage;
    private Scene scene;
    private Parent root;
    ActionEvent event;
    public Connection connect=null;

    @FXML
    private ImageView background;

    @FXML
    private TextField updateAddress;

    @FXML
    private TextField updateCid;

    @FXML
    private TextField updateCname;

    @FXML
    private TextField updatePhone;

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
    void update(ActionEvent event) throws SQLException, ClassNotFoundException {


        if(updateCid!=null){

            try {

                SuppliesWindow.connectDataBase();
                try {

                    SuppliesWindow.connectDataBase();
                    String sql="update customer set C_name=? ,Phone=?,Address=? where C_id=?";
                    PreparedStatement statment = SuppliesWindow.connect.prepareStatement(sql);
                    statment.setInt(4, Integer.valueOf(updateCid.getText()));
                    statment.setString(1, updateCname.getText());
                    statment.setInt(2, Integer.valueOf(updatePhone.getText()));
                    statment.setString(3, updateAddress.getText());


                    if(updateAddress.getText().isEmpty()){
                        String temp = null;
                        String sql2="select Address from customer where C_id= "+updateCid.getText();

                        String SQL = sql2;
                        Statement stmt2 = SuppliesWindow.connect.createStatement();
                        ResultSet rs = stmt2.executeQuery(SQL);


                        while (rs.next())
                            temp= rs.getString(1);

                        statment.setString(1, temp);
                    }

                    if(updatePhone.getText().isEmpty()){

                        int temp = 0;
                        String sql2="select Phone from customer where C_id= "+updateCid.getText();

                        String SQL = sql2;
                        Statement stmt2 = SuppliesWindow.connect.createStatement();
                        ResultSet rs = stmt2.executeQuery(SQL);


                        while (rs.next())
                            temp= Integer.parseInt(rs.getString(1));

                        System.out.println(temp);

                        statment.setInt(    3, temp);
                    }

                    if(updateCname.getText().isEmpty()){
                        String temp = null;
                        String sql2="select C_name from customer where C_id= "+updateCid.getText();

                        String SQL = sql2;
                        Statement stmt2 = SuppliesWindow.connect.createStatement();
                        ResultSet rs = stmt2.executeQuery(SQL);


                        while (rs.next())
                            temp= rs.getString(1);

                        statment.setString(1, temp);
                    }

                    statment.executeUpdate() ;
                    updateCname.clear();
                    updateAddress.clear();
                    updatePhone.clear();
                    updateCid.clear();



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


        updateCname.clear();
        updateAddress.clear();
        updatePhone.clear();
        updateCid.clear();



    }
    /*@FXML
    void update(ActionEvent event) throws SQLException, ClassNotFoundException {


        if(updateCid!=null){

            try {

                SuppliesWindow.connectDataBase();
                try {

                    SuppliesWindow.connectDataBase();
                    String sql="update customer set C_name=? ,Phone=?,Address=? where C_id=?";
                    PreparedStatement statment = SuppliesWindow.connect.prepareStatement(sql);
                    statment.setInt(4, Integer.valueOf(updateCid.getText()));
                    statment.setString(1, updateCname.getText());
                    statment.setInt(2, Integer.valueOf(updatePhone.getText()));
                    statment.setString(3, updateAddress.getText());


                    if(updateCname.getText().isEmpty()){
                        String temp = null;
                        String sql2="select C_name from customer where C_id= "+updateCid.getText();

                        String SQL = sql2;
                        Statement stmt2 = SuppliesWindow.connect.createStatement();
                        ResultSet rs = stmt2.executeQuery(SQL);


                        while (rs.next())
                            temp= rs.getString(1);

                        statment.setString(1, temp);
                    }

                    if(updateAddress.getText().isEmpty()){

                        int temp = 0;
                        String sql2="select Address from customer where C_id= "+updateCid.getText();

                        String SQL = sql2;
                        Statement stmt2 = SuppliesWindow.connect.createStatement();
                        ResultSet rs = stmt2.executeQuery(SQL);


                        while (rs.next())
                            temp= Integer.parseInt(rs.getString(1));

                        System.out.println(temp);

                        statment.setInt(    3, temp);
                    }
                    if(updatePhone.getText().isEmpty()){

                        int temp = 0;
                        String sql2="select Phone from customer where C_id= "+updateCid.getText();

                        String SQL = sql2;
                        Statement stmt2 = SuppliesWindow.connect.createStatement();
                        ResultSet rs = stmt2.executeQuery(SQL);


                        while (rs.next())
                            temp= Integer.parseInt(rs.getString(1));

                        System.out.println(temp);

                        statment.setInt(    3, temp);
                    }

                    statment.executeUpdate() ;
                    updateCname.clear();
                    updateAddress.clear();
                    updatePhone.clear();
                    updateCid.clear();



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


        updateCname.clear();
        updateAddress.clear();
        updatePhone.clear();
        updateCid.clear();



    }*/

}

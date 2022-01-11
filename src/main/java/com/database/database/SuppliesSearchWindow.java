package com.database.database;
//#fdc623   #991b1f   -fx-background-color      D88722
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class SuppliesSearchWindow {
    private Stage stage;
    private Scene scene;
    private Parent root;
    ActionEvent event;
    public Connection connect=null;
    Statement statement=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;
    String sqlQuery;
    boolean radio[]=new boolean[4];
    ObservableList<Supplies> Searchlist = FXCollections.observableArrayList();
    private static ArrayList<Supplies> data;


    @FXML
    private RadioButton radioDate;

    @FXML
    private RadioButton radioId;

    @FXML
    private RadioButton radioName;

    @FXML
    private RadioButton radioQuan;


    @FXML
    private DatePicker ExpiredDate;

    @FXML
    private TableColumn<Supplies, String> ExpiredDateCol;

    @FXML
    private TextField Quantity;

    @FXML
    private TableColumn<Supplies, Integer> QuantityCol;

    @FXML
    private TextField TypeID;

    @FXML
    private TableColumn<Supplies, Integer> TypeIDCol;

    @FXML
    private TextField TypeName;

    @FXML
    private TableColumn<Supplies, String> TypeNameCol;

    @FXML
    private TableView<Supplies> table;

    @FXML
    void BAck(ActionEvent event) {
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
    void Search(ActionEvent event) throws SQLException, ClassNotFoundException {

         table.getItems().clear();
        radioSelected(event);
        System.out.println(radio[0]);
        PreparedStatement statment = null;

        if((radio[0]==true && TypeID.getText().isEmpty())||(radio[1]==true && TypeName.getText().isEmpty())||(radio[2]==true && Quantity.getText().isEmpty())||(radio[3]==true && ExpiredDate.getValue().toString().isEmpty())){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select the search choice and fill the text field");
            alert.showAndWait();
        }

        try {
            SuppliesWindow.connectDataBase();
            statement=SuppliesWindow.connect.createStatement();
            //String sql ="select * from supplies where TypeId=? AND TypeName=? AND Quantity=? AND ExpireDate=?";
            String sql;
            
            if(radio[0]==true){
                sql ="select * from supplies where TypeId=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.valueOf(TypeID.getText()));
            }else if(radio[0]==false && radio[2]==false&&radio[3]==false && radio[1]==true){
                sql ="select * from supplies where TypeName=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setString(1, TypeName.getText());
            }else if(radio[0]==false && radio[1]==false&&radio[3]==false && radio[2]==true){
                sql ="select * from supplies where Quantity=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.valueOf(Quantity.getText()));
            }else if(radio[0]==false && radio[2]==false&&radio[1]==false && radio[3]==true){
                sql ="select * from supplies where ExpireDate=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setString(1, ExpiredDate.getValue().toString());
            }else if(radio[0]==false && radio[1]==false&&radio[2]==true && radio[3]==true){
                sql ="select * from supplies where Quantity=? AND ExpireDate=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.valueOf(Quantity.getText()));
                statment.setString(2, ExpiredDate.getValue().toString());
            }else if(radio[0]==false && radio[1]==true&&radio[2]==false && radio[3]==true){
                sql ="select * from supplies where TypeName=? AND ExpireDate=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setString(1, TypeName.getText());
                statment.setString(2, ExpiredDate.getValue().toString());
            }else if(radio[0]==false && radio[1]==true&&radio[2]==true && radio[3]==false){
                sql ="select * from supplies where TypeName=? AND Quantity=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setString(1, TypeName.getText());
                statment.setInt(2, Integer.valueOf(Quantity.getText()));

            }else if(radio[0]==false && radio[1]==true&&radio[2]==true && radio[3]==true){
                sql ="select * from supplies where TypeName=? AND Quantity=? AND ExpireDate=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setString(1, TypeName.getText());
                statment.setInt(2, Integer.valueOf(Quantity.getText()));
                statment.setString(3, ExpiredDate.getValue().toString());
            }


            ResultSet resultSet=statment.executeQuery();

            while ( resultSet.next() ) {

                Searchlist.add(new Supplies(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3)),
                        resultSet.getString(4)));


            }
            TypeIDCol.setCellValueFactory(new PropertyValueFactory<Supplies,Integer>("TypeId"));
            TypeNameCol.setCellValueFactory(new PropertyValueFactory<Supplies,String>("TypeName"));
            QuantityCol.setCellValueFactory(new PropertyValueFactory<Supplies,Integer>("Quantity"));
            ExpiredDateCol.setCellValueFactory(new PropertyValueFactory<Supplies,String>("ExpireDate"));
            table.setItems(Searchlist);


            TypeID.clear();
            TypeName.clear();
            Quantity.clear();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }







    }
    @FXML
    void radioSelected(ActionEvent event) {

        if(radioId.isSelected()){
            radio[0]=true;
        }else radio[0]=false;
        if(radioName.isSelected()){
            radio[1]=true;

        }else radio[1]=false;
        if(radioQuan.isSelected()){
            radio[2]=true;

        }else radio[2]=false;
        if(radioDate.isSelected()){
            radio[3]=true;

        }else radio[3]=false;


    }



}

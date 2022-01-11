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

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class exp_for_supSearchWindow {
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
    ObservableList<exp_for_sup> Searchlist = FXCollections.observableArrayList();
    private static ArrayList<exp_for_sup> data;


    @FXML
    private RadioButton radioBillId;

    @FXML
    private RadioButton radioTypeId;

    @FXML
    private RadioButton radioPricePerUnit;

    @FXML
    private RadioButton radioTypeQuant;


    @FXML
    private TextField TypeQuant;


    @FXML
    private TableColumn<exp_for_sup, Integer> BillIdCol;

    @FXML
    private TextField BillId;

    @FXML
    private TableColumn<exp_for_sup, Integer> TypeIdCol;

    @FXML
    private TextField TypeId;

    @FXML
    private TableColumn<exp_for_sup, Integer> PricePerUnitCol;

    @FXML
    private TextField PricePerUnit;

    @FXML
    private TableColumn<exp_for_sup, Integer> TypeQuantCol;

    @FXML
    private TableView<exp_for_sup> table;

    @FXML
    void BAck(ActionEvent event) {
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
    void Search(ActionEvent event) throws SQLException, ClassNotFoundException {

         table.getItems().clear();
        radioSelected(event);
        System.out.println(radio[0]);
        PreparedStatement statment = null;

        if((radio[0]==true && BillId.getText().isEmpty())||(radio[1]==true && TypeId.getText().isEmpty())||(radio[2]==true && PricePerUnit.getText().isEmpty())||(radio[3]==true && TypeQuant.getText().isEmpty())){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select the search choice and fill the text field");
            alert.showAndWait();
        }

        try {
            SuppliesWindow.connectDataBase();
            statement=SuppliesWindow.connect.createStatement();
            String sql;
             {


             if(radio[0]==true && radio[2]==false&&radio[3]==false && radio[1]==false){
                sql ="select * from exp_for_sup where BillId=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.valueOf(BillId.getText()));
            }else if(radio[0]==false && radio[1]==true&&radio[3]==false && radio[2]==false){
                sql ="select * from exp_for_sup where TypeId=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.valueOf(TypeId.getText()));
            }else if(radio[0]==false && radio[2]==true&&radio[1]==false && radio[3]==false){
                sql ="select * from exp_for_sup where PricePerUnit=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.parseInt(PricePerUnit.getText()));
            }else if(radio[0]==false && radio[1]==false&&radio[2]==false && radio[3]==true){
                 sql ="select * from exp_for_sup where TypeQuant=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(TypeQuant.getText()));
            }else if(radio[0]==true && radio[1]==true&&radio[2]==false && radio[3]==false){
                sql ="select * from exp_for_sup where BillId=? AND TypeId=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(TypeId.getText()));

            }else if(radio[0]==true && radio[1]==false&&radio[2]==true && radio[3]==false){
                 sql ="select * from exp_for_sup where BillId=? AND PricePerUnit=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(PricePerUnit.getText()));

             }else if(radio[0]==true && radio[1]==false&&radio[2]==false && radio[3]==true){
                 sql ="select * from exp_for_sup where BillId=? AND TypeQuant=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(TypeQuant.getText()));

             }else if(radio[0]==false && radio[1]==true&&radio[2]==true && radio[3]==false){
                 sql ="select * from exp_for_sup where TypeId=? AND PricePerUnit=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(TypeId.getText()));
                 statment.setInt(2, Integer.parseInt(PricePerUnit.getText()));

             }else if(radio[0]==false && radio[1]==true&&radio[2]==false && radio[3]==true){
                 sql ="select * from exp_for_sup where TypeId=? AND TypeQuant=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(TypeId.getText()));
                 statment.setInt(2, Integer.parseInt(TypeQuant.getText()));

             }

             else if(radio[0]==true && radio[1]==true&&radio[2]==true && radio[3]==false){
                sql ="select * from exp_for_sup where BillId=? AND TypeId=? AND PricePerUnit=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.valueOf(TypeId.getText()));
                 statment.setInt(3, Integer.parseInt(PricePerUnit.getText()));

             }else if(radio[0]==false && radio[1]==true&&radio[2]==true && radio[3]==true){
                 sql ="select * from exp_for_sup where TypeId=? AND PricePerUnit=? AND TypeQuant=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(TypeId.getText()));
                 statment.setInt(2, Integer.valueOf(PricePerUnit.getText()));
                 statment.setInt(3, Integer.parseInt(TypeQuant.getText()));

             }else if(radio[0]==true && radio[1]==true&&radio[2]==false && radio[3]==true){
                 sql ="select * from exp_for_sup where BillId=? AND TypeId=? AND TypeQuant=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.valueOf(TypeId.getText()));
                 statment.setInt(3, Integer.parseInt(TypeQuant.getText()));

             }else if(radio[0]==true && radio[1]==false&&radio[2]==true && radio[3]==true){
                 sql ="select * from exp_for_sup where BillId=? AND PricePerUnit=? AND TypeQuant=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.valueOf(PricePerUnit.getText()));
                 statment.setInt(3, Integer.parseInt(TypeQuant.getText()));

             }else{
                 sql ="select * from exp_for_sup where BillId=? AND TypeId=? AND PricePerUnit=? AND TypeQuant=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(TypeId.getText()));
                 statment.setInt(3, Integer.valueOf(PricePerUnit.getText()));
                 statment.setInt(4, Integer.parseInt(TypeQuant.getText()));
             }


            ResultSet resultSet=statment.executeQuery();

            while ( resultSet.next() ) {

                Searchlist.add(new exp_for_sup(
                        Integer.parseInt(resultSet.getString(1)),
                        Integer.parseInt(resultSet.getString(2)),
                        Integer.parseInt(resultSet.getString(3)),
                        Integer.parseInt(resultSet.getString(4)) ));


            }
            BillIdCol.setCellValueFactory(new PropertyValueFactory<exp_for_sup,Integer>("BillId"));
            TypeIdCol.setCellValueFactory(new PropertyValueFactory<exp_for_sup,Integer>("TypeId"));
            PricePerUnitCol.setCellValueFactory(new PropertyValueFactory<exp_for_sup,Integer>("PricePerUnit"));
            TypeQuantCol.setCellValueFactory(new PropertyValueFactory<exp_for_sup,Integer>("TypeQuant"));
            table.setItems(Searchlist);

            BillId.clear();
            TypeId.clear();
            PricePerUnit.clear();
            TypeQuant.clear();


        }} catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }







    }


    @FXML
    void radioSelected(ActionEvent event) {

        if(radioBillId.isSelected()){
            radio[0]=true;
        }else radio[0]=false;
        if(radioTypeId.isSelected()){
            radio[1]=true;

        }else radio[1]=false;
        if(radioPricePerUnit.isSelected()){
            radio[2]=true;

        }else radio[2]=false;
        if(radioTypeQuant.isSelected()){
            radio[3]=true;

        }else radio[3]=false;


    }


}

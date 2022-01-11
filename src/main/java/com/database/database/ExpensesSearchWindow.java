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

public class ExpensesSearchWindow {
    private Stage stage;
    private Scene scene;
    private Parent root;
    ActionEvent event;
    public Connection connect=null;
    Statement statement=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;
    String sqlQuery;
    boolean radio[]=new boolean[3];
    ObservableList<Expenses> Searchlist = FXCollections.observableArrayList();
    private static ArrayList<Expenses> data;



    @FXML
    private RadioButton radioBillDate;

    @FXML
    private RadioButton radioBillId;

    @FXML
    private RadioButton radioTotalPay;



    @FXML
    private TextField BillId;

    @FXML
    private TableColumn<Expenses, Integer> BillIdCol;

    @FXML
    private TextField TotalPay;

    @FXML
    private TableColumn<Expenses, Integer> TotalPayCol;

    @FXML
    private DatePicker BillDate;

    @FXML
    private TableColumn<Expenses, String> BillDateCol;

    @FXML
    private TableView<Expenses> table;


    @FXML
    void BAck(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("ExpensesWindow.fxml"));
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

        PreparedStatement statment = null;

        if((radio[0]==true && BillId.getText().isEmpty())||(radio[1]==true && BillDate.getValue().toString().isEmpty())||(radio[2]==true && TotalPay.getText().isEmpty())){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select the search choice and fill the text field");
            alert.showAndWait();
        }

        try {
            SuppliesWindow.connectDataBase();
            statement=SuppliesWindow.connect.createStatement();
             String sql;

            if(radio[0]==true){
                sql ="select * from expenses where BillId=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.valueOf(BillId.getText()));
            }else if(radio[0]==false && radio[2]==false&& radio[1]==true){
                sql ="select * from expenses where BillDate=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setString(1, BillDate.getValue().toString());
            }else if(radio[0]==false && radio[1]==false && radio[2]==true){
                sql ="select * from expenses where TotalPay=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.valueOf(TotalPay.getText()));

            }else if(radio[0]==false && radio[1]==true&&radio[2]==true ){
                sql ="select * from expenses where BillDate=? AND TotalPay=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(2, Integer.valueOf(TotalPay.getText()));
                statment.setString(1, BillDate.getValue().toString());
            }


            ResultSet resultSet=statment.executeQuery();

            while ( resultSet.next() ) {

                Searchlist.add(new Expenses(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3))));


            }

            BillIdCol.setCellValueFactory(new PropertyValueFactory<Expenses,Integer>("BillId"));
            BillDateCol.setCellValueFactory(new PropertyValueFactory<Expenses,String>("BillDate"));
            TotalPayCol.setCellValueFactory(new PropertyValueFactory<Expenses,Integer>("TotalPay"));

            table.setItems(Searchlist);



            BillId.clear();
            TotalPay.clear();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }







    }
    @FXML
    void radioSelected(ActionEvent event) {

        if(radioBillId.isSelected()){
            radio[0]=true;
        }else radio[0]=false;
        if(radioBillDate.isSelected()){
            radio[1]=true;

        }else radio[1]=false;
        if(radioTotalPay.isSelected()){
            radio[2]=true;

        }else radio[2]=false;


    }



}

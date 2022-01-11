package com.database.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class exp_for_supWindow implements Initializable {

   public static Connection connect=null;
     Statement statement=null;
      PreparedStatement preparedStatement=null;
     ResultSet resultSet=null;
    private static String dbURL;
    private static ArrayList<Supplies> data;
    private Stage stage;
    private Scene scene;
    private Parent root;


    ActionEvent event;

    @FXML
    private TableColumn<exp_for_sup,Integer> BillId;

    @FXML
    private TableColumn<exp_for_sup,Integer> TypeQuant;

    @FXML
    private TableColumn<exp_for_sup,Integer> TypeId;

    @FXML
    private TableColumn<exp_for_sup,Integer> PricePerUnit;

    @FXML
    private TableView<exp_for_sup> table;
     ObservableList<exp_for_sup> list = FXCollections.observableArrayList();

    @FXML
     public  void readData(ActionEvent event) {

        try {

            connectDataBase() ;
            statement=connect.createStatement();
            resultSet= statement.executeQuery("select * from exp_for_sup ");

            while ( resultSet.next() ) {

                list.add(new exp_for_sup(
                        Integer.parseInt(resultSet.getString(1)),
                        Integer.parseInt(resultSet.getString(2)),
                        Integer.parseInt(resultSet.getString(3)),
                        Integer.parseInt(resultSet.getString(4))));


            }




        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }



       // list = FXCollections.observableArrayList(data);
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }

    }
    @FXML
    void Back(ActionEvent event) {
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
    void UpdateDataExpForSup(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("exp_for_supUpdate.fxml"));
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
    void insertData(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("exp_for_supInsertWindow.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        readData( event);
        BillId.setCellValueFactory(new PropertyValueFactory<exp_for_sup,Integer>("BillId"));
        TypeId.setCellValueFactory(new PropertyValueFactory<exp_for_sup,Integer>("TypeId"));
        PricePerUnit.setCellValueFactory(new PropertyValueFactory<exp_for_sup,Integer>("PricePerUnit"));
        TypeQuant.setCellValueFactory(new PropertyValueFactory<exp_for_sup,Integer>("TypeQuant"));
        table.setItems(list);
    }


    public static void connectDataBase() throws ClassNotFoundException, SQLException {


        dbURL = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "oreganodatabase" + "?verifyServerCertificate=false";
        Properties p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "asd123==");
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");
        //Class.forName("com.mysql.jdbc.Driver");

        connect = DriverManager.getConnection (dbURL, p);
        connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/oreganodatabase?user=root&password=asd123==");


    }


    @FXML
    void Search(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("exp_for_supSearchWindow.fxml"));
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
    void DeleteButton(ActionEvent event)  {
        ObservableList<exp_for_sup> selectedRows = table.getSelectionModel().getSelectedItems();
        ArrayList<exp_for_sup> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            table.getItems().remove(row);
            deleteRow(row);
            table.refresh();
        });
    }
    private void deleteRow(exp_for_sup row) {

        try {
            System.out.println("delete from  exp_for_sup where BillId="+row.getBillId() + "AND TypeId="+row.getTypeId());
            connectDataBase();

            try {
                Statement stmt = connect.createStatement();
                stmt.executeUpdate("delete from  exp_for_sup where BillId="+row.getBillId()+";");
                stmt.close();


            }
            catch(SQLException s) {
                s.printStackTrace();
                System.out.println("SQL statement is not executed!");

            }
            connect.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}

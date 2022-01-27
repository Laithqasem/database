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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

public class CustomerOrdersWindow implements Initializable {

    public static int O_id;

    @FXML
    private Button Statistics;

    @FXML
    private TableColumn<Orders, Integer> CID;

    @FXML
    private TableColumn<Orders, Integer> Discount;

    @FXML
    private TableColumn<Orders, Double> ElapsedTime;

    @FXML
    private TableColumn<Orders, String> Notes;

    @FXML
    private TableColumn<Orders, Integer> OID;

    @FXML
    private TableColumn<Orders, Integer> TableNo;

    @FXML
    private TableView<Orders> table;

    @FXML
    private TableColumn<Orders, Integer> totalPrice;

    public static ObservableList<Orders> list = FXCollections.observableArrayList();
    public static ObservableList<Orders> archivedOrders = FXCollections.observableArrayList();
    public static Connection connect=null;
    Statement statement=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;
    private static String dbURL;
    private Stage stage;
    private Scene scene;
    private Parent root;


    ActionEvent event;

    @FXML
    public  void readData(ActionEvent event) {

        try {

            connectDataBase() ;
            statement=connect.createStatement();
            resultSet= statement.executeQuery("select * from orders ");

            while ( resultSet.next() ) {

                list.add(new Orders(
                        Integer.parseInt(resultSet.getString(1)),
                        Integer.parseInt(resultSet.getString(2)),
                        Integer.parseInt(resultSet.getString(3)),
                        Integer.parseInt(resultSet.getString(4)),
                        resultSet.getString(5),
                        Double.parseDouble(resultSet.getString(4)),
                        Integer.parseInt(resultSet.getString(1))));

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
    void readyOrder(ActionEvent event) {
        ObservableList<Orders> selectedRows = table.getSelectionModel().getSelectedItems();
        ArrayList<Orders> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            archivedOrders.add(row);
            table.getItems().remove(row);
            table.refresh();
        });
    }

    @FXML
    void Back(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
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
    void showLines(ActionEvent event) {
        ObservableList<Orders> selectedRows = table.getSelectionModel().getSelectedItems();
        ArrayList<Orders> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> O_id = row.getO_id());
        try {

            root = FXMLLoader.load(getClass().getResource("OrderMeals.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
   /* @FXML
    void UpdateData(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("SuppliesUpdateWindow.fxml"));
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

            root = FXMLLoader.load(getClass().getResource("SuppliesInsertWindow.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        readData( event);


        OID.setCellValueFactory(new PropertyValueFactory<Orders,Integer>("O_id"));
        CID.setCellValueFactory(new PropertyValueFactory<Orders,Integer>("C_id"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<Orders,Integer>("total_price"));
        Discount.setCellValueFactory(new PropertyValueFactory<Orders,Integer>("discount"));
        Notes.setCellValueFactory(new PropertyValueFactory<Orders,String>("Notes"));
        ElapsedTime.setCellValueFactory(new PropertyValueFactory<Orders,Double>("Elapsed_time"));
        TableNo.setCellValueFactory(new PropertyValueFactory<Orders,Integer>("Table_No"));
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
    void ordersStat(ActionEvent event) {
        try {

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrdersStat.fxml")));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

/*
    @FXML
    void Search(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("SuppliesSearchWindow.fxml"));
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
        ObservableList<Supplies> selectedRows = table.getSelectionModel().getSelectedItems();
        ArrayList<Supplies> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            table.getItems().remove(row);
            deleteRow(row);
            table.refresh();
        });
    }
    private void deleteRow(Supplies row) {

        try {
            System.out.println("delete from  Supplies where TypeId="+row.getTypeId() + ";");
            connectDataBase();

            try {
                Statement stmt = connect.createStatement();
                stmt.executeUpdate("delete from  Supplies where TypeId="+row.getTypeId() + ";");
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
    }*/

}

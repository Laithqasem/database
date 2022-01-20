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

public class ExpensesWindow implements Initializable {

   public static Connection connect=null;
     Statement statement=null;
      PreparedStatement preparedStatement=null;
     ResultSet resultSet=null;
    private static String dbURL;
    private static ArrayList<Expenses> data;
    private Stage stage;
    private Scene scene;
    private Parent root;


    ActionEvent event;

    @FXML
    private TableColumn<Expenses,String> BillDate;

    @FXML
    private TableColumn<Expenses,Integer> BillId;

    @FXML
    private TableColumn<Expenses,Integer> TotalPay;


    @FXML
    private TableView<Expenses> table;
    public static ObservableList<Expenses> list = FXCollections.observableArrayList();

    @FXML
     public  void readData(ActionEvent event) {
        ExpensesWindow.list.clear();
        try {

            connectDataBase() ;
            statement=connect.createStatement();
            resultSet= statement.executeQuery("select * from expenses ");

            while ( resultSet.next() ) {

                list.add(new Expenses(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3)
                        )));


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
    void UpdateData(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("ExpensesUpdateWindow.fxml"));
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
    void goToexpforemp(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("exp_for_empWindow.fxml"));
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
    void goToexpforsub(ActionEvent event) {
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
    void insertData(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("ExpensesInsertWindow.fxml"));
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


        BillId.setCellValueFactory(new PropertyValueFactory<Expenses,Integer>("BillId"));
        BillDate.setCellValueFactory(new PropertyValueFactory<Expenses,String>("BillDate"));
        TotalPay.setCellValueFactory(new PropertyValueFactory<Expenses,Integer>("TotalPay"));

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

            root = FXMLLoader.load(getClass().getResource("ExpensesSearchWindow.fxml"));
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
        ObservableList<Expenses> selectedRows = table.getSelectionModel().getSelectedItems();
        ArrayList<Expenses> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            table.getItems().remove(row);
            deleteRow(row);
            table.refresh();
        });
    }
    private void deleteRow(Expenses row) {

        try {
            System.out.println("delete from  expenses where BillId="+row.getBillId() + ";");
            connectDataBase();

            try {
                Statement stmt = connect.createStatement();
                stmt.executeUpdate("delete from  expenses where BillId="+row.getBillId() + ";");
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

    @FXML
    void Statistics(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("ExpensesStat.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

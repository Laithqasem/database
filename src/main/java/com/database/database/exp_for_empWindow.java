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

public class exp_for_empWindow implements Initializable {

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
    private TableColumn<exp_for_emp,Integer> BillId;

    @FXML
    private TableColumn<exp_for_emp,Integer> EId;

    @FXML
    private TableColumn<exp_for_emp,Integer> BaseSalary;

    @FXML
    private TableColumn<exp_for_emp,Integer> OvertimePrice;

    @FXML
    private TableView<exp_for_emp> table;
     public static ObservableList<exp_for_emp> list = FXCollections.observableArrayList();

    @FXML
     public  void readData(ActionEvent event) {

        try {

            connectDataBase() ;
            statement=connect.createStatement();
            resultSet= statement.executeQuery("select * from exp_for_emp ");

            while ( resultSet.next() ) {

                list.add(new exp_for_emp(
                        Integer.parseInt(resultSet.getString(1)),
                        Integer.parseInt(resultSet.getString(2)),
                        Integer.parseInt(resultSet.getString(3)),
                        Integer.parseInt(resultSet.getString(4))));


            }




        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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
    void UpdateDataexp_for_emp(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("exp_for_empUpdate.fxml"));
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

            root = FXMLLoader.load(getClass().getResource("exp_for_empInsertWindow.fxml"));
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
        BillId.setCellValueFactory(new PropertyValueFactory<exp_for_emp,Integer>("BillId"));
        EId.setCellValueFactory(new PropertyValueFactory<exp_for_emp,Integer>("EId"));
        BaseSalary.setCellValueFactory(new PropertyValueFactory<exp_for_emp,Integer>("BaseSalary"));
        OvertimePrice.setCellValueFactory(new PropertyValueFactory<exp_for_emp,Integer>("OvertimePrice"));
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

            root = FXMLLoader.load(getClass().getResource("exp_for_empSearchWindow.fxml"));
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
        ObservableList<exp_for_emp> selectedRows = table.getSelectionModel().getSelectedItems();
        ArrayList<exp_for_emp> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            table.getItems().remove(row);
            deleteRow(row);
            table.refresh();
        });
    }
    private void deleteRow(exp_for_emp row) {

        try {
            System.out.println("delete from  exp_for_emp where BillId="+row.getBillId() + "AND EId="+row.getEId());
            connectDataBase();

            try {
                Statement stmt = connect.createStatement();
                stmt.executeUpdate("delete from  exp_for_emp where BillId="+row.getBillId()+";");
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
    void expForempStat(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("ExpForEmp.fxml"));
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

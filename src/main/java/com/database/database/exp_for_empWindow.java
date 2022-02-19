package com.database.database;

//import javafx.collections.FXCollections;

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
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

public class exp_for_empWindow implements Initializable {

   public static Connection connect=null;
    Statement statement=null;
    ResultSet resultSet=null;
    private static String dbURL;

    private Stage stage;
    private Scene scene;
    private Parent root;

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
     public  void readData() {

        try {

            connectDataBase() ;
             statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from exp_for_emp ");

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
        for (com.database.database.exp_for_emp exp_for_emp : list) {
            System.out.println(exp_for_emp.toString());
        }

    }



    @FXML
    void Back(ActionEvent event) {
        try {

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ExpensesWindow.fxml")));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // list.clear();
    }

    @FXML
    void UpdateDataExp_for_emp(ActionEvent event) {
        try {

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exp_for_empUpdate.fxml")));
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

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exp_for_empInsertWindow.fxml")));
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

        list.clear();
        readData();
        BillId.setCellValueFactory(new PropertyValueFactory<>("BillId"));
        EId.setCellValueFactory(new PropertyValueFactory<>("EId"));
        BaseSalary.setCellValueFactory(new PropertyValueFactory<>("BaseSalary"));
        OvertimePrice.setCellValueFactory(new PropertyValueFactory<>("OvertimePrice"));
        table.setItems(LoginMenu.expForEmps);
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

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exp_for_empSearchWindow.fxml")));
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
    void DeleteButton()  {
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

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void expForEmpStat(ActionEvent event) {
        try {

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ExpForEmp.fxml")));
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

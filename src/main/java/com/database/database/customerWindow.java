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

public class customerWindow implements Initializable {

    public static void connectDataBase() throws ClassNotFoundException, SQLException {


        dbURL = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "oreganodatabase" + "?verifyServerCertificate=false";
        Properties p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "asd123==");
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");
        //Class.forName("com.mysql.jdbc.Driver");

        connect = DriverManager.getConnection(dbURL, p);
        connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/oreganodatabase?user=root&password=asd123==");


    }


    public static Connection connect = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private static String dbURL;
    private static ArrayList<Supplies> data;
    private Stage stage;
    private Scene scene;
    private Parent root;
    ActionEvent event;
    @FXML
    private TableColumn<customer, Integer> C_id;

    @FXML
    private TableColumn<customer, String> C_name;

    @FXML
    private TableColumn<customer, Integer> Phone;

    @FXML
    private TableColumn<customer, String> Address;

    @FXML
    private TableView<customer> table;
    ObservableList<customer> list = FXCollections.observableArrayList();

    public void readData(ActionEvent event) {

        try {

            connectDataBase();
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from customer ");

            while (resultSet.next()) {

                list.add(new customer(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3)),
                        resultSet.getString(4)
                ));


            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            readData(event);
            C_id.setCellValueFactory(new PropertyValueFactory<customer,Integer>("C_id"));
            C_name.setCellValueFactory(new PropertyValueFactory<customer,String>("C_name"));
            Phone.setCellValueFactory(new PropertyValueFactory<customer,Integer>("Phone"));
            Address.setCellValueFactory(new PropertyValueFactory<customer,String>("Address"));
            table.setItems(list);


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

            root = FXMLLoader.load(getClass().getResource("customerUpdateWindow.fxml"));
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

            root = FXMLLoader.load(getClass().getResource("customerInsertWindow.fxml"));
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
    void Search(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("customerSearchWindow.fxml"));
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
        ObservableList<customer> selectedRows = table.getSelectionModel().getSelectedItems();
        ArrayList<customer> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            table.getItems().remove(row);
            deleteRow(row);
            table.refresh();
        });
    }
    private void deleteRow(customer row) {

        try {
            System.out.println("delete from  customer where CId="+row.getC_id() + ";");
            connectDataBase();

            try {
                Statement stmt = connect.createStatement();
                stmt.executeUpdate("delete from  customer where C_id="+row.getC_id() + ";");
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
    void stat(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("customerStat.fxml"));
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


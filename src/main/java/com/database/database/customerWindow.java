package com.database.database;

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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

public class customerWindow implements Initializable {

    public static void connectDataBase() throws ClassNotFoundException, SQLException {

        String dbURL = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "oreganodatabase" + "?verifyServerCertificate=false";
        Properties p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "asd123==");
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");
        //Class.forName("com.mysql.jdbc.Driver");

        connect = DriverManager.getConnection(dbURL, p);
        // connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/oreganodatabase?user=root&password=asd123==");
    }


    public static Connection connect = null;
    //Statement statement = null;
    //ResultSet resultSet = null;
    private Stage stage;
    private Scene scene;
    private Parent root;

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
    // ObservableList<customer> list = FXCollections.observableArrayList();
/*
    public void readData() {

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
        for (com.database.database.customer customer : list) {
            System.out.println(customer.toString());
        }
    }

 */
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            //readData();
            C_id.setCellValueFactory(new PropertyValueFactory<>("C_id"));
            C_name.setCellValueFactory(new PropertyValueFactory<>("C_name"));
            Phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
            table.setItems(LoginMenu.customers);

            C_name.setCellFactory(TextFieldTableCell.forTableColumn());
            C_name.setOnEditCommit(
                    (TableColumn.CellEditEvent<customer, String> t) -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setC_name(t.getNewValue()); //display only
                        updateName( t.getRowValue().getC_id(),t.getNewValue());
                    });

            Phone.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            Phone.setOnEditCommit(
                    (TableColumn.CellEditEvent<customer, Integer> t) -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setPhone(t.getNewValue()); //display only
                        updatePhone( t.getRowValue().getC_id(),t.getNewValue());
                    });

            Address.setCellFactory(TextFieldTableCell.forTableColumn());
            Address.setOnEditCommit(
                    (TableColumn.CellEditEvent<customer, String> t) -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setAddress(t.getNewValue()); //display only
                        updateAddress( t.getRowValue().getC_id(),t.getNewValue());
                    });
    }

    public void updateName(int id, String name) {

        try {
            System.out.println("update customer set C_name = '"+name + "' where C_id = \""+ id + "\"");
            ExecuteStatement("update customer set C_name = '"+name + "' where C_id = \""+ id + "\"" +";");
            connect.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePhone(int id, int price) {

        try {
            System.out.println("update customer set Phone = '"+ price + "' where C_id = \""+ id + "\"");
            ExecuteStatement("update customer set Phone = '"+ price + "' where C_id = \""+ id + "\"" +";");
            connect.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAddress(int id, String address) {

        try {
            System.out.println("update customer set Address = '"+address + "' where C_id = \""+ id + "\"");
            ExecuteStatement("update customer set Address = '"+address + "' where C_id = \""+ id + "\"" +";");
            connect.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Back(ActionEvent event) {
        try {

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
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

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("customerUpdateWindow.fxml")));
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

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("customerInsertWindow.fxml")));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
            table.refresh();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void Search(ActionEvent event) {
        try {

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("customerSearchWindow.fxml")));
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
        ObservableList<customer> selectedRows = table.getSelectionModel().getSelectedItems();
        ArrayList<customer> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            try {
                System.out.println("delete from customer where CId = \""+row.getC_id() + "\"" + ";");
                ExecuteStatement("delete from customer where C_id = \""+row.getC_id() + "\"" + ";");
                connect.close();
                System.out.println("Connection closed");
                table.getItems().remove(row);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            table.refresh();
        });
    }


    @FXML
    void stat(ActionEvent event) {
        try {

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("customerStat.fxml")));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void ExecuteStatement(String SQL) {

        try {
            connectDataBase();
            Statement stmt = connect.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();
        }
        catch(SQLException | ClassNotFoundException s) {
            s.printStackTrace();
            System.out.println("SQL statement is not executed!");
        }
    }
}


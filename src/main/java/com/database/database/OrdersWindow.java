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
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

public class OrdersWindow implements Initializable {
    public static void connectDB() throws ClassNotFoundException, SQLException {


        dbURL = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "oreganodatabase" + "?verifyServerCertificate=false";
        Properties p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "asd123==");
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");
        //Class.forName("com.mysql.jdbc.Driver");

        connection = DriverManager.getConnection(dbURL, p);
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oreganodatabase?user=root&password=asd123==");


    }
    public static Connection connection = null;
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
    private TableView<Orders> ordersTable;
    @FXML
    private TableColumn<Orders, Integer> O_id;
    @FXML
    private TableColumn<Orders, Integer> C_id;
    @FXML
    private TableColumn<Orders, Integer> total_price;
    @FXML
    private TableColumn<Orders, Integer> discount;
    @FXML
    private TableColumn<Orders, String> Notes;
    @FXML
    private TableColumn<Orders, Double> Elapsed_time;
    @FXML
    private TableColumn<Orders, Integer> Table_No;

    public static ObservableList<Orders> orders;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        orders = orders;

        if(O_id != null && C_id != null && total_price != null && discount != null && Notes != null && Elapsed_time != null && Table_No != null) {
            O_id.setCellValueFactory(new PropertyValueFactory<>("O_id"));
            C_id.setCellValueFactory(new PropertyValueFactory<>("C_id"));
            total_price.setCellValueFactory(new PropertyValueFactory<>("total_price"));
            discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
            Notes.setCellValueFactory(new PropertyValueFactory<>("Notes"));
            Elapsed_time.setCellValueFactory(new PropertyValueFactory<>("Elapsed_time"));
            Table_No.setCellValueFactory(new PropertyValueFactory<>("Table_No"));

            C_id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            C_id.setOnEditCommit(
                    (TableColumn.CellEditEvent<Orders, Integer> t) -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setC_id(t.getNewValue()); //display only
                        updateCust( t.getRowValue().getO_id(),t.getNewValue());
                    });
            total_price.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            total_price.setOnEditCommit(
                    (TableColumn.CellEditEvent<Orders, Integer> t) -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setTotal_price(t.getNewValue()); //display only
                        updatePrice( t.getRowValue().getO_id(),t.getNewValue());
                    });
            discount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            discount.setOnEditCommit(
                    (TableColumn.CellEditEvent<Orders, Integer> t) -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setDiscount(t.getNewValue()); //display only
                        updateDisc( t.getRowValue().getO_id(),t.getNewValue());
                    });
            Table_No.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            Table_No.setOnEditCommit(
                    (TableColumn.CellEditEvent<Orders, Integer> t) -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setTable_No(t.getNewValue()); //display only
                        updateTable( t.getRowValue().getO_id(),t.getNewValue());
                    });

            Elapsed_time.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            Elapsed_time.setOnEditCommit(
                    (TableColumn.CellEditEvent<Orders, Double> t) -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setElapsed_time(t.getNewValue()); //display only
                        updateTime( t.getRowValue().getO_id(),t.getNewValue());
                    });

            Notes.setCellFactory(TextFieldTableCell.forTableColumn());
            Notes.setOnEditCommit(
                    (TableColumn.CellEditEvent<Orders, String> t) -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setNotes(t.getNewValue()); //display only
                        updateNotes( t.getRowValue().getO_id(),t.getNewValue());
                    });

            ordersTable.setItems(orders);
        }
        else
            System.out.println("A value or more may be null!!");
    }

    public void switchToMakeOrder(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MakeOrder-Page.fxml")));
        scene = new Scene(root);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void updateCust(int id, int C_id) {

        try {
            System.out.println("update orders set C_id = '"+ C_id + "' where O_id = "+ id);
            connectDB();
            ExecuteStatement("update orders set C_id = '"+ C_id + "' where O_id = "+ id +";");
            connection.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void updatePrice(int id, int price) {

        try {
            System.out.println("update orders set total_price = '"+ price + "' where O_id = "+ id);
            connectDB();
            ExecuteStatement("update orders set total_price = '"+ price + "' where O_id = "+ id +";");
            connection.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateDisc(int id, int disc) {

        try {
            System.out.println("update orders set discount = '"+ disc + "' where O_id = "+ id);
            connectDB();
            ExecuteStatement("update orders set discount = '"+ disc + "' where O_id = "+ id +";");
            connection.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateTable(int id, int table) {

        try {
            System.out.println("update orders set Table_No = '"+ table + "' where O_id = "+ id);
            connectDB();
            ExecuteStatement("update orders set Table_No = '"+ table + "' where O_id = "+ id +";");
            connection.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateTime(int id, double time) {

        try {
            System.out.println("update orders set Elapsed_time = '"+ time + "' where O_id = "+ id);
            connectDB();
            ExecuteStatement("update orders set Elapsed_time = '"+ time + "' where O_id = "+ id +";");
            connection.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateNotes(int id, String name) {

        try {
            System.out.println("update orders set Notes = '"+name + "' where O_id = "+ id);
            connectDB();
            ExecuteStatement("update orders set Notes = '"+name + "' where O_id = "+ id +";");
            connection.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void DeleteRow() {
        ObservableList<Orders> selectedRows = ordersTable.getSelectionModel().getSelectedItems();
        ArrayList<Orders> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            ordersTable.getItems().remove(row);
            try {
                System.out.println("delete from orders where O_id = "+row.getO_id() + ";");
                connectDB();
                ExecuteStatement("delete from orders where O_id = "+row.getO_id() + ";");
                connection.close();
                System.out.println("Connection closed");

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            ordersTable.refresh();
        });
    }

    public static void ExecuteStatement(String SQL) throws SQLException {

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();
        }
        catch(SQLException s) {
            s.printStackTrace();
            System.out.println("SQL statement is not executed!");
        }
    }

    public void Back(ActionEvent actionEvent) {
    }

    @FXML
    void Insert(ActionEvent event) {

    }
}

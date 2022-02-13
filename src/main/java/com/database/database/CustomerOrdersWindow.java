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

public class CustomerOrdersWindow implements Initializable {

    public static int O_id;

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

    // public static ObservableList<Orders> list = FXCollections.observableArrayList();
    public static ObservableList<Orders> archivedOrders = FXCollections.observableArrayList();
    public static Connection connect = null;
    //Statement statement = null;
    //ResultSet resultSet = null;
    private Stage stage;
    private Scene scene;
    private Parent root;

/*
    @FXML
    public void readData(){

        try {

            connectDataBase();
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from orders ");

            while (resultSet.next()) {

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

        // expenses = FXCollections.observableArrayList(data);
        for (Orders orders : list) {
            System.out.println(orders.toString());
        }

    }
 */

    @FXML
    void readyOrder(){
        ObservableList<Orders> selectedRows = table.getSelectionModel().getSelectedItems();
        ArrayList<Orders> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            archivedOrders.add(row);
            table.getItems().remove(row);
            table.refresh();
        });
    }

    @FXML
    void Back(ActionEvent event){
        try {

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
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

    public void DeleteOrder(){
        ObservableList<Orders> selectedRows = table.getSelectionModel().getSelectedItems();
        ArrayList<Orders> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            try {
                System.out.println("delete from orders where O_id = \""+row.getO_id() + "\"" + ";");
                ExecuteStatement("delete from orders where O_id = \""+row.getO_id() + "\"" + ";");
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
    void showLines(ActionEvent event){
        ObservableList<Orders> selectedRows = table.getSelectionModel().getSelectedItems();
        ArrayList<Orders> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> O_id = row.getO_id());
        try {

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrderMeals.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
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
    public void initialize(URL url, ResourceBundle resourceBundle){

        //readData();
        OID.setCellValueFactory(new PropertyValueFactory<>("O_id"));
        CID.setCellValueFactory(new PropertyValueFactory<>("C_id"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<>("total_price"));
        Discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        Notes.setCellValueFactory(new PropertyValueFactory<>("Notes"));
        ElapsedTime.setCellValueFactory(new PropertyValueFactory<>("Elapsed_time"));
        TableNo.setCellValueFactory(new PropertyValueFactory<>("Table_No"));
        table.setItems(LoginMenu.orders);

        CID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        CID.setOnEditCommit(
                (TableColumn.CellEditEvent<Orders, Integer> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setC_id(t.getNewValue()); //display only
                    updateCust( t.getRowValue().getO_id(),t.getNewValue());
                });
        totalPrice.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        totalPrice.setOnEditCommit(
                (TableColumn.CellEditEvent<Orders, Integer> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setTotal_price(t.getNewValue()); //display only
                    updatePrice( t.getRowValue().getO_id(),t.getNewValue());
                });
        Discount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Discount.setOnEditCommit(
                (TableColumn.CellEditEvent<Orders, Integer> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setDiscount(t.getNewValue()); //display only
                    updateDisc( t.getRowValue().getO_id(),t.getNewValue());
                });
        TableNo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableNo.setOnEditCommit(
                (TableColumn.CellEditEvent<Orders, Integer> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setTable_No(t.getNewValue()); //display only
                    updateTable( t.getRowValue().getO_id(),t.getNewValue());
                });

        ElapsedTime.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        ElapsedTime.setOnEditCommit(
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
    }

    public static void connectDataBase() throws ClassNotFoundException, SQLException{

        String dbURL = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "oreganodatabase" + "?verifyServerCertificate=false";
        Properties p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "asd123==");
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");
        //Class.forName("com.mysql.jdbc.Driver");

        connect = DriverManager.getConnection(dbURL, p);
        //connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/oreganodatabase?user=root&password=asd123==");
    }

    public void updateCust(int id, int C_id) {

        try {
            System.out.println("update orders set C_id = '"+ C_id + "' where O_id = "+ id);
            connectDataBase();
            ExecuteStatement("update orders set C_id = '"+ C_id + "' where O_id = "+ id +";");
            connect.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void updatePrice(int id, int price) {

        try {
            System.out.println("update orders set total_price = '"+ price + "' where O_id = "+ id);
            connectDataBase();
            ExecuteStatement("update orders set total_price = '"+ price + "' where O_id = "+ id +";");
            connect.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateDisc(int id, int disc) {

        try {
            System.out.println("update orders set discount = '"+ disc + "' where O_id = "+ id);
            connectDataBase();
            ExecuteStatement("update orders set discount = '"+ disc + "' where O_id = "+ id +";");
            connect.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateTable(int id, int table) {

        try {
            System.out.println("update orders set Table_No = '"+ table + "' where O_id = "+ id);
            connectDataBase();
            ExecuteStatement("update orders set Table_No = '"+ table + "' where O_id = "+ id +";");
            connect.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateTime(int id, double time) {

        try {
            System.out.println("update orders set Elapsed_time = '"+ time + "' where O_id = "+ id);
            connectDataBase();
            ExecuteStatement("update orders set Elapsed_time = '"+ time + "' where O_id = "+ id +";");
            connect.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateNotes(int id, String name) {

        try {
            System.out.println("update orders set Notes = '"+name + "' where O_id = "+ id);
            connectDataBase();
            ExecuteStatement("update orders set Notes = '"+name + "' where O_id = "+ id +";");
            connect.close();
            System.out.println("Connection closed");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ordersStat(ActionEvent event){
        try {

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrdersStat.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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


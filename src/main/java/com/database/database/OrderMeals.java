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

public class OrderMeals implements Initializable {
    public static ObservableList<OrderLine> orderLines = FXCollections.observableArrayList();
    public static ObservableList<linesTable> orderLine = FXCollections.observableArrayList();
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
    private TableColumn<linesTable, Integer> O_id;

    @FXML
    private TableColumn<linesTable, Integer> Quantity;


    @FXML
    private TableColumn<linesTable, Integer> line_id;

    @FXML
    private TableColumn<linesTable, String> name;

    @FXML
    private TableView<linesTable> table;


    @FXML
    void Back(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("CustomersOrders.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

    public void readData() {

        try {

            connectDataBase();
            System.out.println(CustomerOrdersWindow.O_id);
            resultSet = statement.executeQuery("select * from order_line where O_id = " + CustomerOrdersWindow.O_id);

            while (resultSet.next()) {

                orderLines.add(new OrderLine(
                        Integer.parseInt(resultSet.getString(1)),
                        Integer.parseInt(resultSet.getString(2)),
                        resultSet.getString(3),
                        Integer.parseInt(resultSet.getString(4))
                ));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void fillLines(){
        String meal = "";
        for(int i=0; i < orderLines.size(); i++){
            for(int j=0; j< CreateOrderController.meals.size(); j++){
                if(orderLines.get(i).getM_id().equals(CreateOrderController.meals.get(j).getMeal_id()))
                    meal = CreateOrderController.meals.get(j).getName();
            }
            orderLine.add(new linesTable(orderLines.get(i).getLine_id(), orderLines.get(i).getO_id(),
                    meal, orderLines.get(i).getQuantity()));
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        readData();
        fillLines();
        line_id.setCellValueFactory(new PropertyValueFactory<linesTable,Integer>("line_id"));
        O_id.setCellValueFactory(new PropertyValueFactory<linesTable,Integer>("O_id"));
        name.setCellValueFactory(new PropertyValueFactory<linesTable,String>("name"));
        Quantity.setCellValueFactory(new PropertyValueFactory<linesTable,Integer>("Quantity"));

        table.setItems(orderLine);
    }
}

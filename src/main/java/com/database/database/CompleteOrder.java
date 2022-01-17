package com.database.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class CompleteOrder {

    @FXML
    private TextField AddCid;

    @FXML
    private TextField AddDiscount;

    @FXML
    private TextField AddElapsedTime;

    @FXML
    private TextField AddNotes;

    @FXML
    private TextField AddTableNum;


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


    public void Back(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("OrdersWindow.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    void Insert(ActionEvent event) throws ClassNotFoundException, SQLException {

        connectDataBase();
        String sql = "Insert into orders (C_id, total_price,discount, Notes, Elapsed_time, Table_No ) values (?,?,?,?,?,?)";
        PreparedStatement statment = connect.prepareStatement(sql);
        statment.setInt(1, Integer.valueOf(AddCid.getText()));
        statment.setInt(2, CreateOrderController.totalPrice - Integer.valueOf(AddDiscount.getText()));
        statment.setInt(3, Integer.valueOf(AddDiscount.getText()));
        statment.setString(4, AddNotes.getText());
        statment.setDouble(5, Double.parseDouble(AddElapsedTime.getText()));
        statment.setInt(6, Integer.valueOf(AddTableNum.getText()));

        statment.executeUpdate();
        System.out.println("insert done");
        AddCid.clear();
        AddDiscount.clear();
        AddNotes.clear();
        AddElapsedTime.clear();
        AddTableNum.clear();

        for (int i = 0; i < CreateOrderController.mQuantity.size(); i++) {
            if (CreateOrderController.mQuantity.get(i).getQuantity() > 0) {
                connectDataBase();
                sql = "Insert into order_line (O_id, m_id, quantity ) values (?,?,?)";
                statment = connect.prepareStatement(sql);
                statment.setInt(1, CreateOrderController.orders.get(CreateOrderController.orders.size() - 1).getO_id());
                statment.setString(2, CreateOrderController.mQuantity.get(i).getM_id());
                statment.setInt(3, CreateOrderController.mQuantity.get(i).getQuantity());

                statment.executeUpdate();

            }

        }
    }
}


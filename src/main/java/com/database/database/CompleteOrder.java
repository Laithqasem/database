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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
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

    public static void connectDataBase() throws ClassNotFoundException, SQLException {

        String dbURL = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "oreganodatabase" + "?verifyServerCertificate=false";
        Properties p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "asd123==");
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");
        //Class.forName("com.mysql.jdbc.Driver");

        connect = DriverManager.getConnection(dbURL, p);
        //connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/oreganodatabase?user=root&password=asd123==");
    }

    public void Back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CreateOrdersWindow.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
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
        statment.setInt(1, Integer.parseInt(AddCid.getText()));
        statment.setInt(2, CreateOrderController.totalPrice - Integer.parseInt(AddDiscount.getText()));
        statment.setInt(3, Integer.parseInt(AddDiscount.getText()));
        statment.setString(4, AddNotes.getText());
        statment.setDouble(5, Double.parseDouble(AddElapsedTime.getText()));
        statment.setInt(6, Integer.parseInt(AddTableNum.getText()));

        statment.executeUpdate();

        for (int i = 0; i < CreateOrderController.mQuantity.size(); i++) {
            if (CreateOrderController.mQuantity.get(i).getQuantity() > 0) {
                connectDataBase();
                sql = "Insert into order_line (O_id, m_id, quantity ) values (?,?,?)";
                statment = connect.prepareStatement(sql);
                statment.setInt(1, LoginMenu.orders.get(LoginMenu.orders.size() - 1).getO_id() + 1);
                statment.setString(2, CreateOrderController.mQuantity.get(i).getM_id());
                statment.setInt(3, CreateOrderController.mQuantity.get(i).getQuantity());

                statment.executeUpdate();

            }
        }
        Orders rc;
        rc = new Orders(LoginMenu.orders.get(LoginMenu.orders.size() - 1).getO_id() + 1,
                Integer.parseInt(AddCid.getText()),
                CreateOrderController.totalPrice - Integer.parseInt(AddDiscount.getText()),
                Integer.parseInt(AddDiscount.getText()),
                AddNotes.getText(),
                Double.parseDouble(AddElapsedTime.getText()),
                Integer.parseInt(AddTableNum.getText()));

        LoginMenu.orders.add(rc);
        System.out.println("insert done");

        AddCid.clear();
        AddDiscount.clear();
        AddNotes.clear();
        AddElapsedTime.clear();
        AddTableNum.clear();

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomersOrders.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}


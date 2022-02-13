package com.database.database;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public class LoginMenu {
    public static Connection connect=null;
    public static Statement statement=null;
    public static ResultSet resultSet=null;


    public static ObservableList<Expenses> expenses = FXCollections.observableArrayList();
    public static ObservableList<Meals> meals = FXCollections.observableArrayList();
    public static ObservableList<Orders> orders = FXCollections.observableArrayList();
    public static ObservableList<OrderLine> orderLines = FXCollections.observableArrayList();
    public static ObservableList<customer> customers = FXCollections.observableArrayList();
    public static ObservableList<exp_for_emp> expForEmps = FXCollections.observableArrayList();
    public static ObservableList<exp_for_sup> expForSups = FXCollections.observableArrayList();
    public static ObservableList<Employee> employees = FXCollections.observableArrayList();
    public static ObservableList<Supplies> supplies = FXCollections.observableArrayList();
    public static ObservableList<Role> roles = FXCollections.observableArrayList();



    public static void connectDataBase() throws ClassNotFoundException, SQLException{

        String dbURL = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "oreganodatabase" + "?verifyServerCertificate=false";
        Properties p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "asd123==");
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");
        //Class.forName("com.mysql.jdbc.Driver");

        connect = DriverManager.getConnection (dbURL, p);
        // connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/oreganodatabase?user=root&password=asd123==");
    }

    @FXML
    public void readData() {

        try {

            connectDataBase() ;
            statement=connect.createStatement();
            resultSet= statement.executeQuery("select * from expenses ");

            while ( resultSet.next() ) {

                expenses.add(new Expenses(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3)
                        )));
            }

            resultSet = statement.executeQuery("select * from Meals ");

            while (resultSet.next()) {

                meals.add(new Meals(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3))
                ));

            }
            resultSet = statement.executeQuery("select * from orders ");

            while (resultSet.next()) {

                orders.add(new Orders(
                        Integer.parseInt(resultSet.getString(1)),
                        Integer.parseInt(resultSet.getString(2)),
                        Integer.parseInt(resultSet.getString(3)),
                        Integer.parseInt(resultSet.getString(4)),
                        resultSet.getString(5),
                        Double.parseDouble(resultSet.getString(6)),
                        Integer.parseInt(resultSet.getString(7))));

            }

            resultSet = statement.executeQuery("select * from order_line ");

            while (resultSet.next()) {

                orderLines.add(new OrderLine(
                        Integer.parseInt(resultSet.getString(1)),
                        Integer.parseInt(resultSet.getString(2)),
                        resultSet.getString(3),
                        Integer.parseInt(resultSet.getString(4))
                ));
            }

            resultSet = statement.executeQuery("select * from customer ");

            while (resultSet.next()) {

                customers.add(new customer(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3)),
                        resultSet.getString(4)
                ));
            }

            resultSet= statement.executeQuery("select * from exp_for_emp ");

            while ( resultSet.next() ) {

                expForEmps.add(new exp_for_emp(
                        Integer.parseInt(resultSet.getString(1)),
                        Integer.parseInt(resultSet.getString(2)),
                        Integer.parseInt(resultSet.getString(3)),
                        Integer.parseInt(resultSet.getString(4))));
            }

            resultSet= statement.executeQuery("select * from exp_for_sup ");

            while ( resultSet.next() ) {

                expForSups.add(new exp_for_sup(
                        Integer.parseInt(resultSet.getString(1)),
                        Integer.parseInt(resultSet.getString(2)),
                        Integer.parseInt(resultSet.getString(3)),
                        Integer.parseInt(resultSet.getString(4))));

            }

            resultSet= statement.executeQuery("select e_id,e_name, birthdate, phone,r_id,overtime_hours from employees order by e_id");

            while (resultSet.next()){
                employees.add(new Employee(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        Integer.parseInt(resultSet.getString(4)),
                        resultSet.getString(5),
                        Integer.parseInt(resultSet.getString(6))));
            }

            resultSet= statement.executeQuery("select * from supplies ");

            while ( resultSet.next() ) {

                supplies.add(new Supplies(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3)),
                        resultSet.getString(4)));
            }

            resultSet= statement.executeQuery("select role_id,role_name, base_salary, overtime_hours_price from Roles order by role_id");

            while (resultSet.next()){
                roles.add(new Role(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3)),
                        Integer.parseInt(resultSet.getString(4))
                ));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    @FXML
    private PasswordField Pass;
    @FXML
    private TextField UserName;

    @FXML
    void instagram() throws IOException {

        String url = "https://www.instagram.com/oregano_tulkarm/";
        Desktop.getDesktop().browse(java.net.URI.create(url));
    }

    @FXML
    void facebook() throws IOException {

        String url = "https://www.facebook.com/oreganotulkarm";
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
    }

    @FXML
    void login(ActionEvent event){

        if(UserName.getText().equals("admin")&& Pass.getText().equals("admin")){

            try {
                readData();

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter right User name and Password!!!");
            alert.showAndWait();
        }
        //UserName.clear();
        Pass.clear();
    }

}

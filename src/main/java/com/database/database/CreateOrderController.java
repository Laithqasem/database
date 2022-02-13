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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

public class CreateOrderController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void fillMeals() {
        mQuantity.add(new mealQuantity("B_01",0));
        mQuantity.add(new mealQuantity("B_02",0));
        mQuantity.add(new mealQuantity("B_03",0));
        mQuantity.add(new mealQuantity("C_01",0));
        mQuantity.add(new mealQuantity("C_02",0));
        mQuantity.add(new mealQuantity("C_03",0));
        mQuantity.add(new mealQuantity("P_01",0));
        mQuantity.add(new mealQuantity("P_02",0));
        mQuantity.add(new mealQuantity("P_03",0));
        mQuantity.add(new mealQuantity("W_01",0));
        mQuantity.add(new mealQuantity("W_02",0));
        mQuantity.add(new mealQuantity("Z_01",0));
        mQuantity.add(new mealQuantity("Z_02",0));
    }

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

    public static Connection connect = null;
    //Statement statement = null;
    //ResultSet resultSet = null;

    public static int totalPrice = 0;
    private int mealPrice;
    public static ObservableList<mealQuantity> mQuantity = FXCollections.observableArrayList();
    //public static ObservableList<Meals> meals = FXCollections.observableArrayList();
    //public static ObservableList<Orders> orders = FXCollections.observableArrayList();
    //public static ObservableList<OrderLine> orderLines = FXCollections.observableArrayList();

/*
    public void readData() {

        try {

            connectDataBase();
            statement = connect.createStatement();
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

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

 */

    @FXML
    private Spinner<Integer> B_01;

    @FXML
    private Spinner<Integer> B_02;

    @FXML
    private Spinner<Integer> B_03;

    @FXML
    private Spinner<Integer> C_01;

    @FXML
    private Spinner<Integer> C_02;

    @FXML
    private Spinner<Integer> C_03;

    @FXML
    private Spinner<Integer> P_01;

    @FXML
    private Spinner<Integer> P_02;

    @FXML
    private Spinner<Integer> P_03;

    @FXML
    private Spinner<Integer> W_01;

    @FXML
    private Spinner<Integer> W_02;

    @FXML
    private Spinner<Integer> Z_01;

    @FXML
    private Spinner<Integer> Z_02;

    SpinnerValueFactory<Integer> svf1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
    SpinnerValueFactory<Integer> svf2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
    SpinnerValueFactory<Integer> svf3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
    SpinnerValueFactory<Integer> svf4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
    SpinnerValueFactory<Integer> svf5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
    SpinnerValueFactory<Integer> svf6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
    SpinnerValueFactory<Integer> svf7 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
    SpinnerValueFactory<Integer> svf8 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
    SpinnerValueFactory<Integer> svf9 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
    SpinnerValueFactory<Integer> svf10 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
    SpinnerValueFactory<Integer> svf11 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
    SpinnerValueFactory<Integer> svf12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
    SpinnerValueFactory<Integer> svf13 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        fillMeals();
        B_01.setValueFactory(svf1);
        B_02.setValueFactory(svf2);
        B_03.setValueFactory(svf3);
        C_01.setValueFactory(svf4);
        C_02.setValueFactory(svf5);
        C_03.setValueFactory(svf6);
        P_01.setValueFactory(svf7);
        P_02.setValueFactory(svf8);
        P_03.setValueFactory(svf9);
        W_01.setValueFactory(svf10);
        W_02.setValueFactory(svf11);
        Z_01.setValueFactory(svf12);
        Z_02.setValueFactory(svf13);

    }

    @FXML
    void createOrder(ActionEvent event){

            //readData();

        if (B_01.getValue() > 0) {
            for (Meals meal : LoginMenu.meals) {
                if (Objects.equals(meal.getMeal_id(), "B_01"))
                    mealPrice = meal.getPrice();
            }
            totalPrice += mealPrice * B_01.getValue();
            for (mealQuantity mq : mQuantity) {
                if (Objects.equals(mq.getM_id(), "B_01"))
                    mq.setQuantity(B_01.getValue());
            }

        }
        if (B_02.getValue() > 0) {
            for (Meals meal : LoginMenu.meals) {
                if (Objects.equals(meal.getMeal_id(), "B_02"))
                    mealPrice = meal.getPrice();
            }
            totalPrice += mealPrice * B_02.getValue();
            for (mealQuantity mq : mQuantity) {
                if (Objects.equals(mq.getM_id(), "B_02"))
                    mq.setQuantity(B_02.getValue());
            }
        }
        if (B_03.getValue() > 0) {
            for (Meals meal : LoginMenu.meals) {
                if (Objects.equals(meal.getMeal_id(), "B_03"))
                    mealPrice = meal.getPrice();
            }
            totalPrice += mealPrice * B_03.getValue();
            for (mealQuantity mq : mQuantity) {
                if (Objects.equals(mq.getM_id(), "B_03"))
                    mq.setQuantity(B_03.getValue());
            }
        }
        if (C_01.getValue() > 0) {
            for (Meals meal : LoginMenu.meals) {
                if (Objects.equals(meal.getMeal_id(), "C_01"))
                    mealPrice = meal.getPrice();
            }
            totalPrice += mealPrice * C_01.getValue();
            for (mealQuantity mq : mQuantity) {
                if (Objects.equals(mq.getM_id(), "C_01"))
                    mq.setQuantity(C_01.getValue());
            }
        }
        if (C_02.getValue() > 0) {
            for (Meals meal : LoginMenu.meals) {
                if (Objects.equals(meal.getMeal_id(), "C_02"))
                    mealPrice = meal.getPrice();
            }
            totalPrice += mealPrice * C_02.getValue();
            for (mealQuantity mq : mQuantity) {
                if (Objects.equals(mq.getM_id(), "C_02"))
                    mq.setQuantity(C_02.getValue());
            }
        }
        if (C_03.getValue() > 0) {
            for (Meals meal : LoginMenu.meals) {
                if (Objects.equals(meal.getMeal_id(), "C_03"))
                    mealPrice = meal.getPrice();
            }
            totalPrice += mealPrice * C_03.getValue();
            for (mealQuantity mq : mQuantity) {
                if (Objects.equals(mq.getM_id(), "C_03"))
                    mq.setQuantity(C_03.getValue());
            }
        }
        if (P_01.getValue() > 0) {
            for (Meals meal : LoginMenu.meals) {
                if (Objects.equals(meal.getMeal_id(), "P_01"))
                    mealPrice = meal.getPrice();

            }
            totalPrice += mealPrice * P_01.getValue();
            for (mealQuantity mq : mQuantity) {
                if (Objects.equals(mq.getM_id(), "P_01"))
                    mq.setQuantity(P_01.getValue());
            }
        }
        if (P_02.getValue() > 0) {

            for (Meals meal : LoginMenu.meals) {
                if (Objects.equals(meal.getMeal_id(), "P_02"))
                    mealPrice = meal.getPrice();
            }
            totalPrice += mealPrice * P_02.getValue();
            for (mealQuantity mq : mQuantity) {
                if (Objects.equals(mq.getM_id(), "P_02"))
                    mq.setQuantity(P_02.getValue());
            }
        }
        if (P_03.getValue() > 0) {
            for (Meals meal : LoginMenu.meals) {
                if (Objects.equals(meal.getMeal_id(), "P_03"))
                    mealPrice = meal.getPrice();
            }
            totalPrice += mealPrice * P_03.getValue();
            for (mealQuantity mq : mQuantity) {
                if (Objects.equals(mq.getM_id(), "P_03"))
                    mq.setQuantity(P_03.getValue());
            }
        }
        if (W_01.getValue() > 0) {
            for (Meals meal : LoginMenu.meals) {
                if (Objects.equals(meal.getMeal_id(), "W_01"))
                    mealPrice = meal.getPrice();
            }
            totalPrice += mealPrice * W_01.getValue();
            for (mealQuantity mq : mQuantity) {
                if (Objects.equals(mq.getM_id(), "W_01"))
                    mq.setQuantity(W_01.getValue());
            }
        }
        if (W_02.getValue() > 0) {
            for (Meals meal : LoginMenu.meals) {
                if (Objects.equals(meal.getMeal_id(), "W_02"))
                    mealPrice = meal.getPrice();
            }
            totalPrice += mealPrice * W_02.getValue();
            for (mealQuantity mq : mQuantity) {
                if (Objects.equals(mq.getM_id(), "W_02"))
                    mq.setQuantity(W_02.getValue());
            }
        }
        if (Z_01.getValue() > 0) {
            for (Meals meal : LoginMenu.meals) {
                if (Objects.equals(meal.getMeal_id(), "Z_01"))
                    mealPrice = meal.getPrice();
            }
            totalPrice += mealPrice * Z_01.getValue();
            for (mealQuantity mq : mQuantity) {
                if (Objects.equals(mq.getM_id(), "Z_01"))
                    mq.setQuantity(Z_01.getValue());
            }
        }
        if (Z_02.getValue() > 0) {
            for (Meals meal : LoginMenu.meals) {
                if (Objects.equals(meal.getMeal_id(), "Z_02"))
                    mealPrice = meal.getPrice();
            }
            totalPrice += mealPrice * Z_02.getValue();
            for (mealQuantity mq : mQuantity) {
                if (Objects.equals(mq.getM_id(), "Z_02"))
                    mq.setQuantity(Z_02.getValue());
            }
        }
        System.out.println(totalPrice);
        B_01.getValueFactory().setValue(0);
        B_02.getValueFactory().setValue(0);
        B_03.getValueFactory().setValue(0);
        C_01.getValueFactory().setValue(0);
        C_02.getValueFactory().setValue(0);
        C_03.getValueFactory().setValue(0);
        P_01.getValueFactory().setValue(0);
        P_02.getValueFactory().setValue(0);
        P_03.getValueFactory().setValue(0);
        W_01.getValueFactory().setValue(0);
        W_02.getValueFactory().setValue(0);
        Z_01.getValueFactory().setValue(0);
        Z_02.getValueFactory().setValue(0);

        testInsert(event);

        System.out.println(totalPrice);
    }
    void testInsert(ActionEvent event) {
        try {
            // orderinsertWindow.fxml
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("completeOrderWindowFinal.fxml")));
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
    void viewOrders(ActionEvent event) {
        try {

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomersOrders.fxml")));
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
}
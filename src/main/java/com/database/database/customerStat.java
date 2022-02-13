package com.database.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

public class customerStat {

    @FXML
    private Label GC;

    @FXML
    private Label TS;

    @FXML
    private Label LS;

    @FXML
    private Label TB;

    @FXML
    private Label avg;

    public static Connection connect = null;
    /*
    Statement statement = null;

    ResultSet resultSet = null;
    Statement statement2 = null;
    ResultSet resultSet2 = null;
    Statement statement3 = null;
    ResultSet resultSet3 = null;

     */
    private final ArrayList<String> Cust = new ArrayList<>();
    //public static ObservableList<Orders> orders = FXCollections.observableArrayList();
    //public static ObservableList<customer> customers = FXCollections.observableArrayList();
    public static ObservableList<record> custOrders = FXCollections.observableArrayList();
    //public static ObservableList<OrderLine> orderLine = FXCollections.observableArrayList();
    public static ObservableList<topSeller> topS = FXCollections.observableArrayList();
/*
    public void readData() {

        try {
            connectDataBase() ;
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from customer ");

            while (resultSet.next()) {

                customers.add(new customer(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3)),
                        resultSet.getString(4)
                ));

            }

            statement2 = connect.createStatement();
            resultSet2 = statement2.executeQuery("select * from orders");

            while (resultSet2.next()) {

                orders.add(new Orders(
                        Integer.parseInt(resultSet2.getString(1)),
                        Integer.parseInt(resultSet2.getString(2)),
                        Integer.parseInt(resultSet2.getString(3)),
                        Integer.parseInt(resultSet2.getString(4)),
                        resultSet2.getString(5),
                        Double.parseDouble(resultSet2.getString(6)),
                        Integer.parseInt(resultSet2.getString(7))));

            }

            statement3 = connect.createStatement();

            resultSet3 = statement3.executeQuery("select * from order_line");

            while (resultSet3.next()) {

                orderLine.add(new OrderLine(
                        Integer.parseInt(resultSet3.getString(1)),
                        Integer.parseInt(resultSet3.getString(2)),
                        resultSet3.getString(3),
                        Integer.parseInt(resultSet3.getString(4))));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

 */

    @FXML
    void topB() {

        int price = LoginMenu.orders.get(0).getTotal_price();
        for (int k = 1 ; k < LoginMenu.orders.size() ; k++ ){
            if (LoginMenu.orders.get(k).getTotal_price() > price){
                price = LoginMenu.orders.get(k).getTotal_price();
            }
        }

        for (Orders order : LoginMenu.orders) {
            if (order.getTotal_price() == price) {
                int cid = order.getC_id();
                String cname = findCust(cid);
                Cust.add(cname);

            }
        }
        if(Cust.size() > 1)
            TB.setText(String.valueOf(Cust));
        else
            TB.setText(Cust.get(0));
        System.out.println(Cust);
        Cust.clear();

    }

    @FXML
    void avgTime() {
        int count = 0;
        double totalTime = 0;
        double avgT;
        for(int i=0; i < LoginMenu.orders.size(); i++){
            totalTime += LoginMenu.orders.get(i).getElapsed_time();
            count ++;
        }

        avgT = totalTime / count;
        avg.setText(String.format("%.3f%n", avgT));
    }

    public void fillCust(){
        for (com.database.database.customer customer : LoginMenu.customers) {
            custOrders.add(new record(customer.getC_id(), 0));
        }
    }
    public void fillTop(){
        for (int i = 0 ; i < LoginMenu.meals.size(); i++ ){
            topS.add(new topSeller(LoginMenu.meals.get(i).getMeal_id(), 0));
        }
    }

    @FXML
    void leastSell() {
        fillTop();
        for (OrderLine line : LoginMenu.orderLines) {
            for (topSeller top : topS) {
                if (line.getM_id().equals(top.getM_id())) {
                    top.setQuantity(top.getQuantity() + line.getQuantity());
                }
            }
        }

        int minCount = topS.get(0).getQuantity();
        for (int k = 1 ; k < topS.size() ; k++ ){
            if (topS.get(k).getQuantity() < minCount){
                minCount = topS.get(k).getQuantity();
            }
        }

        for (topSeller top : topS) {
            if (top.getQuantity() == minCount) {
                String m_id = top.getM_id();
                String cname = findMeal(m_id);

                LS.setText(cname);
            }
        }
    }

    @FXML
    void topSell() {
        fillTop();
        for (OrderLine line : LoginMenu.orderLines) {
            for (topSeller top : topS) {
                if (line.getM_id().equals(top.getM_id())) {
                    top.setQuantity(top.getQuantity() + line.getQuantity());
                }
            }
        }

        for (topSeller t : topS){
            System.out.println(t.getM_id() + " " + t.getQuantity());
        }

        int maxCount = topS.get(0).getQuantity();
        for (int k = 1 ; k < topS.size() ; k++ ){
            if (topS.get(k).getQuantity() > maxCount){
                maxCount = topS.get(k).getQuantity();
            }
        }

        for (topSeller top : topS) {
            if (top.getQuantity() == maxCount) {
                String m_id = top.getM_id();
                String cname = findMeal(m_id);

                TS.setText(cname);
            }
        }
    }

    public String findMeal( String m_id){
        for (int i = 0 ; i < LoginMenu.meals.size() ; i++){
            if (LoginMenu.meals.get(i).getMeal_id().equals(m_id)){
                return LoginMenu.meals.get(i).getName();
            }
        }
        return "";
    }

    @FXML
    void goldCust() {

        fillCust();
        for (Orders order : LoginMenu.orders) {
            for (record custOrder : custOrders) {
                if (order.getC_id() == custOrder.getCid()) {
                    custOrder.setCount(custOrder.getCount() + 1);
                }
            }
        }
        int maxCount = custOrders.get(0).getCount();
        for (int k = 1 ; k < custOrders.size() ; k++ ){
            if (custOrders.get(k).getCount() > maxCount){
                maxCount = custOrders.get(k).getCount();
            }
        }

        for (record custOrder : custOrders) {
            if (custOrder.getCount() == maxCount) {
                int cid = custOrder.getCid();
                String cname = findCust(cid);
                Cust.add(cname);

            }
        }
        if(Cust.size() > 1)
            GC.setText(String.valueOf(Cust));
        else
            GC.setText(Cust.get(0));
        System.out.println(Cust);
        Cust.clear();
    }
    public String findCust(int cid){
        for (com.database.database.customer customer : LoginMenu.customers) {
            if (customer.getC_id() == cid) {
                return customer.getC_name();
            }
        }
        return "";
    }

    public static void connectDataBase() throws ClassNotFoundException, SQLException {

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
    void Back(ActionEvent event) {
        System.out.println("Back pressed");
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("customerWindow.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
/*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        readData();
    }
 */
}

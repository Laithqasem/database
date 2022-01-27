package com.database.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class customerStat implements Initializable {

    @FXML
    private ImageView BackIcon1;

    @FXML
    private ImageView BackIcon11;

    @FXML
    private Button Statistics;

    @FXML
    private Label GC;

    @FXML
    private Label TS;

    @FXML
    private Button Statistics3;

    @FXML
    private Label TB;

    public static Connection connect = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement2 = null;
    ResultSet resultSet2 = null;
    Statement statement3 = null;
    ResultSet resultSet3 = null;
    private static String dbURL;
    private ArrayList<String> Cust = new ArrayList<>();
    private static ArrayList<Supplies> data;
    public static ObservableList<Orders> orders = FXCollections.observableArrayList();
    public static ObservableList<customer> customers = FXCollections.observableArrayList();
    public static ObservableList<record> custOrders = FXCollections.observableArrayList();
    public static ObservableList<OrderLine> orderLine = FXCollections.observableArrayList();
    public static ObservableList<topSeller> topS = FXCollections.observableArrayList();
    ActionEvent event;
    public static int totalPrice = 0;
    private int mealPrice;

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

    @FXML
    void topB(ActionEvent event) {

        int price = orders.get(0).getTotal_price();
        for (int k = 1 ; k < orders.size() ; k++ ){
            if (orders.get(k).getTotal_price() > price){
                price = orders.get(k).getTotal_price();
            }
        }

        for (int i = 0 ; i < orders.size() ; i++){
            if (orders.get(i).getTotal_price() == price){
                int cid = orders.get(i).getC_id();
                String cname = findCust(cid);
                Cust.add(cname);

            }
        }
        if(Cust.size() > 1)
            TB.setText(String.valueOf(Cust));
        else
            TB.setText(Cust.get(0));
        System.out.println(Cust.toString());
        Cust.clear();

    }



    public void fillCust(){
        for (int i = 0 ; i < customers.size() ; i++){
            custOrders.add(new record(customers.get(i).getC_id(), 0));
        }
    }
    public void fillTop(){
        for (int i = 0 ; i < CreateOrderController.meals.size(); i++ ){
            topS.add(new topSeller(CreateOrderController.meals.get(i).getMeal_id(), 0));
        }
    }

    @FXML
    void topSell(ActionEvent event) {
        fillTop();
        for(int i=0; i < orderLine.size(); i++){
            for(int j=0; j < topS.size(); j++){
                if(orderLine.get(i).getM_id().equals(topS.get(j).getM_id())){
                    topS.get(j).setQuantity(topS.get(j).getQuantity() + orderLine.get(i).getQuantity());
                }
            }
        }

        int maxCount = topS.get(0).getQuantity();
        for (int k = 1 ; k < topS.size() ; k++ ){
            if (topS.get(k).getQuantity() > maxCount){
                maxCount = topS.get(k).getQuantity();
            }
        }

        for (int i = 0 ; i < topS.size() ; i++){
            if (topS.get(i).getQuantity() == maxCount){
                String m_id = topS.get(i).getM_id();
                String cname = findMeal(m_id);

                TS.setText(cname);
            }
        }


    }

    public String findMeal( String m_id){
        for (int i = 0 ; i < CreateOrderController.meals.size() ; i++){
            if (CreateOrderController.meals.get(i).getMeal_id().equals(m_id)){
                return CreateOrderController.meals.get(i).getName();
            }
        }
        return "";
    }



    @FXML
    void goldCust(ActionEvent event) {

        fillCust();
        for (int i = 0 ; i < orders.size() ; i++){
            for (int j = 0 ; j < custOrders.size() ; j++){
                if (orders.get(i).getC_id() == custOrders.get(j).getCid()){
                    custOrders.get(j).setCount(custOrders.get(j).getCount() + 1);
                }
            }
        }
        int maxCount = custOrders.get(0).getCount();
        for (int k = 1 ; k < custOrders.size() ; k++ ){
            if (custOrders.get(k).getCount() > maxCount){
                maxCount = custOrders.get(k).getCount();
            }
        }

        for (int i = 0 ; i < custOrders.size() ; i++){
            if (custOrders.get(i).getCount() == maxCount){
                int cid = custOrders.get(i).getCid();
                String cname = findCust(cid);
                Cust.add(cname);

            }
        }
        if(Cust.size() > 1)
            GC.setText(String.valueOf(Cust));
        else
            GC.setText(Cust.get(0));
        System.out.println(Cust.toString());
        Cust.clear();
    }
    public String findCust(int cid){
        for (int i = 0 ; i < customers.size() ; i++){
            if (customers.get(i).getC_id() == cid){
                return customers.get(i).getC_name();
            }
        }
        return "";
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        readData();
    }
}

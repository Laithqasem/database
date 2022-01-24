package com.database.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class customerStat {

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

    public static Connection connect = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement2 = null;
    ResultSet resultSet2 = null;
    Statement statement3 = null;
    ResultSet resultSet3 = null;
    private static String dbURL;
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



    public void fillCust(){
        for (int i = 0 ; i < customers.size() ; i++){
            custOrders.add(new record(customers.get(i).getC_id(), 0));
        }
    }
   // public void fillTop(){
    //    for (int i = 0 ; i <  )
    //}

    @FXML
    void topSell(ActionEvent event) {

    }



    @FXML
    void goldCust(ActionEvent event) {
        readData();
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

                GC.setText(cname);
            }
        }



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


}

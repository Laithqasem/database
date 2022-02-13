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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

public class OrderMeals implements Initializable {
    int mealPrice = 0;
    int quan;
    int Oid;
    public static ObservableList<OrderLine> orderLines = FXCollections.observableArrayList();
    public static ObservableList<linesTable> orderLine = FXCollections.observableArrayList();
    public static Connection connect=null;
    Statement statement=null;
    ResultSet resultSet=null;

    @FXML
    private TextField m_name;

    @FXML
    private TextField quant;

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

    public void readData() {

        try {
            connectDataBase();
            System.out.println(CustomerOrdersWindow.O_id);
            statement=connect.createStatement();
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
        for (OrderLine line : orderLines) {
            for (int j = 0; j < LoginMenu.meals.size(); j++) {
                if (line.getM_id().equals(LoginMenu.meals.get(j).getMeal_id()))
                    meal = LoginMenu.meals.get(j).getName();
            }
            orderLine.add(new linesTable(line.getLine_id(), line.getO_id(),
                    meal, line.getQuantity()));
        }
    }

    @FXML
    public void DeleteLine(){
        //AtomicInteger mealPrice = new AtomicInteger();
        ObservableList<linesTable> selectedRows = table.getSelectionModel().getSelectedItems();
        ArrayList<linesTable> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            try {
                System.out.println("delete from order_line where line_id = \""+row.getLine_id() + "\"" + ";");
                ExecuteStatement("delete from order_line where line_id = \""+row.getLine_id() + "\"" + ";");
                connect.close();

                for (Meals meal : LoginMenu.meals) {
                    if (Objects.equals(meal.getName(), row.getM_name()))
                        mealPrice = meal.getPrice();
                }
                int discount = row.getQuantity() * mealPrice;
                int index = 0;
                for (int i = 0; i < LoginMenu.orders.size(); i++) {
                    if (Objects.equals(LoginMenu.orders.get(i).getO_id(), CustomerOrdersWindow.O_id))
                        index = i;
                }
                int totalPrice = LoginMenu.orders.get(index).getTotal_price() - discount;
                LoginMenu.orders.get(index).setTotal_price(totalPrice);

                System.out.println("Meal Price: " + mealPrice + " total = " + totalPrice);
                ExecuteStatement("update orders set total_price = " + totalPrice + " where O_id = "+ row.getO_id()  +";");
                connect.close();
                System.out.println("Connection closed");
                table.getItems().remove(row);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            table.refresh();
        });
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

    public void updateQuantity(int id, int quantity) {

        try {
            System.out.println("update order_line set quantity = '"+ quantity + "' where line_id = \""+ id + "\"");
            ExecuteStatement("update order_line set quantity = '"+ quantity + "' where line_id = \""+ id + "\"" +";");
            connect.close();

            int discount = quan * mealPrice;
            int index = 0;
            for (int i = 0; i < LoginMenu.orders.size(); i++) {
                if (Objects.equals(LoginMenu.orders.get(i).getO_id(), CustomerOrdersWindow.O_id))
                    index = i;
            }
            int totalPrice = LoginMenu.orders.get(index).getTotal_price() - discount;
            discount = quantity * mealPrice;
            totalPrice += discount;
            LoginMenu.orders.get(index).setTotal_price(totalPrice);

            System.out.println("old quan: " + quan + " new quantity: " + quantity + " total = " + totalPrice);
            ExecuteStatement("update orders set total_price = " + totalPrice + " where O_id = " + Oid +";");
            connect.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Insert() throws SQLException, ClassNotFoundException{
        connectDataBase();
        String mealID = "";

        OrderLine rc;

        for (Meals meal : LoginMenu.meals) {
            if (Objects.equals(meal.getName(), m_name.getText())) {
                mealID = meal.getMeal_id();
                mealPrice = meal.getPrice();
            }
        }

        if( !mealID.isEmpty() ) {
            String sql = "Insert into order_line (O_id, m_id, quantity) values (?,?,?)";
            PreparedStatement statment = connect.prepareStatement(sql);
            statment.setInt(1, CustomerOrdersWindow.O_id);
            statment.setString(2, mealID);
            statment.setInt(3, Integer.parseInt(quant.getText()));

            statment.executeUpdate();
            rc = new OrderLine(LoginMenu.orderLines.get(LoginMenu.customers.size() - 1).getLine_id() + 1,
                    CustomerOrdersWindow.O_id, mealID, Integer.parseInt(quant.getText()));
            LoginMenu.orderLines.add(rc);

            int discount = Integer.parseInt(quant.getText()) * mealPrice;
            int index = 0;
            for (int i = 0; i < LoginMenu.orders.size(); i++) {
                if (Objects.equals(LoginMenu.orders.get(i).getO_id(), CustomerOrdersWindow.O_id))
                    index = i;
            }
            int totalPrice = LoginMenu.orders.get(index).getTotal_price() + discount;
            LoginMenu.orders.get(index).setTotal_price(totalPrice);

            System.out.println("total = " + totalPrice);
            ExecuteStatement("update orders set total_price = " + totalPrice + " where O_id = " + CustomerOrdersWindow.O_id +";");
            connect.close();
            System.out.println("Connection closed");
            System.out.println("insert done");
        }
        else
            System.out.println("This meal does not exist in Menu!!");
        m_name.clear();
        quant.clear();
        table.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderLine.clear();
        readData();
        fillLines();
        line_id.setCellValueFactory(new PropertyValueFactory<>("line_id"));
        O_id.setCellValueFactory(new PropertyValueFactory<>("O_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("m_name"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        table.setItems(orderLine);

        Quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Quantity.setOnEditCommit(
                (TableColumn.CellEditEvent<linesTable, Integer> t) -> {
                    quan = t.getOldValue();
                    Oid = t.getRowValue().getO_id();
                    for (Meals meal : LoginMenu.meals) {
                        if (Objects.equals(meal.getName(), t.getRowValue().getM_name()))
                            mealPrice = meal.getPrice();
                    }
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setQuantity(t.getNewValue()); //display only
                    updateQuantity( t.getRowValue().getLine_id(),t.getNewValue());
                });

    }
}
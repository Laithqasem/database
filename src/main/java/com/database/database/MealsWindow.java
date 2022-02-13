package com.database.database;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

public class MealsWindow implements Initializable {
    // public static Connection connect = null;
    //Statement statement = null;
    //ResultSet resultSet = null;
    public static Connection connection;

    @FXML
    private TableView<Meals> mealsTable;
    @FXML
    private TableColumn<Meals, String> meal_id;
    @FXML
    private TableColumn<Meals, String> name;
    @FXML
    private TableColumn<Meals, Integer> Price;
    @FXML
    private TextField addMealId;
    @FXML
    private TextField addName;
    @FXML
    private TextField addPrice;
    @FXML
    private Button AddMeal;

    // public static ObservableList<Meals> meals = FXCollections.observableArrayList();

    public static void connectDataBase() throws ClassNotFoundException, SQLException {

        String dbURL = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "oreganodatabase" + "?verifyServerCertificate=false";
        Properties p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "asd123==");
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");
        //Class.forName("com.mysql.jdbc.Driver");

        connection = DriverManager.getConnection (dbURL, p);
        // connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/oreganodatabase?user=root&password=asd123==");
    }
/*
    public void readData() {

        try {
            connectDataBase() ;
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from meals");

            while (resultSet.next()) {

                meals.add(new Meals(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3))
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

 */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        //readData();

        if(meal_id != null && name != null && Price != null) {
            meal_id.setCellValueFactory(new PropertyValueFactory<>("meal_id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            Price.setCellValueFactory(new PropertyValueFactory<>("Price"));

            name.setCellFactory(TextFieldTableCell.forTableColumn());
            name.setOnEditCommit(
                    (TableColumn.CellEditEvent<Meals, String> t) -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setName(t.getNewValue()); //display only
                        updateName( t.getRowValue().getMeal_id(),t.getNewValue());
                    });

            Price.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            Price.setOnEditCommit(
                    (TableColumn.CellEditEvent<Meals, Integer> t) -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setPrice(t.getNewValue()); //display only
                        updatePrice( t.getRowValue().getMeal_id(),t.getNewValue());
                    });
            mealsTable.setItems(LoginMenu.meals);

            AddMeal.setOnAction((ActionEvent e) -> {
                Meals rc;
                rc = new Meals(addMealId.getText(),
                        addName.getText(),
                        Integer.parseInt(addPrice.getText()));
                LoginMenu.meals.add(rc);
                //mainPage.meals.add(rc);
                insert(rc);
                addMealId.clear();
                addName.clear();
                addPrice.clear();
                mealsTable.refresh();
            });
        } else
            System.out.println("A value or more may be null!!");
    }

    public void updateName(String id, String name) {

        try {
            System.out.println("update meals set name = '"+name + "' where meal_id = \""+ id + "\"");
            ExecuteStatement("update meals set name = '"+name + "' where meal_id = \""+ id + "\"" +";");
            connection.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePrice(String id, int price) {

        try {
            System.out.println("update meals set Price = '"+ price + "' where meal_id = \""+ id + "\"");
            ExecuteStatement("update meals set Price = '"+ price + "' where meal_id = \""+ id + "\"" +";");
            connection.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Meals rc) {
        try {
            System.out.println("insert into meals (meal_id, name, Price) values(" + "\"" +
                    rc.getMeal_id() + "\", " + "\""
                    + rc.getName() + "\", "
                    + rc.getPrice() + ");");

            ExecuteStatement("insert into meals (meal_id, name, Price) values(" + "\"" +
                    rc.getMeal_id() + "\", " + "\""
                    + rc.getName() + "\", "
                    + rc.getPrice() + ");");
            connection.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        ObservableList<Meals> selectedRows = mealsTable.getSelectionModel().getSelectedItems();
        ArrayList<Meals> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            try {
                System.out.println("delete from meals where meal_id = \""+row.getMeal_id() + "\"" + ";");
                ExecuteStatement("delete from meals where meal_id = \""+row.getMeal_id() + "\"" + ";");
                connection.close();
                System.out.println("Connection closed");
                mealsTable.getItems().remove(row);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            mealsTable.refresh();
        });
    }

    public void viewHome(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public static void ExecuteStatement(String SQL) {

        try {
            connectDataBase();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();
        }
        catch(SQLException | ClassNotFoundException s) {
            s.printStackTrace();
            System.out.println("SQL statement is not executed!");
        }
    }

}

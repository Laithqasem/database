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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class SuppliesWindow implements Initializable {
  @FXML
    private ImageView BackIcon;

    @FXML
    private ImageView deleteIcon;

    @FXML
    private ImageView insertIcon;

    @FXML
    private ImageView oreganoLogo;

    @FXML
    private ImageView searchIcon;

    @FXML
    private ImageView suppliesIcon;

    @FXML
    private ImageView updateIcon;

    @FXML
    private TableColumn<Supplies,String> ExpiredDate;

    @FXML
    private TableColumn<Supplies,Integer> Quantity;

    @FXML
    private TableColumn<Supplies,Integer> TypeId;

    @FXML
    private TableColumn<Supplies,String> TypeName;

    @FXML
    private TableView<Supplies> table;

     public static ObservableList<Supplies> list = FXCollections.observableArrayList();
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
     public  void readData(ActionEvent event) {
        list.clear();
        try {

            connectDataBase() ;
            statement=connect.createStatement();
            resultSet= statement.executeQuery("select * from supplies ");

            while ( resultSet.next() ) {

                list.add(new Supplies(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3)),
                        resultSet.getString(4)));


            }




        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }



       // list = FXCollections.observableArrayList(data);
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }

    }
    @FXML
    void Back(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML
    void UpdateData(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("SuppliesUpdateWindow.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @FXML
    void insertData(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("SuppliesInsertWindow.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        readData( event);

        TypeId.setCellValueFactory(new PropertyValueFactory<Supplies,Integer>("TypeId"));
        TypeName.setCellValueFactory(new PropertyValueFactory<Supplies,String>("TypeName"));
        Quantity.setCellValueFactory(new PropertyValueFactory<Supplies,Integer>("Quantity"));
        ExpiredDate.setCellValueFactory(new PropertyValueFactory<Supplies,String>("ExpireDate"));
        table.setItems(list);
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


    @FXML
    void Search(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("SuppliesSearchWindow.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void DeleteButton(ActionEvent event)  {
        ObservableList<Supplies> selectedRows = table.getSelectionModel().getSelectedItems();
        ArrayList<Supplies> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            table.getItems().remove(row);
            deleteRow(row);
            table.refresh();
        });
    }
    private void deleteRow(Supplies row) {

        try {
            System.out.println("delete from  Supplies where TypeId="+row.getTypeId() + ";");
            connectDataBase();

            try {
                Statement stmt = connect.createStatement();
                stmt.executeUpdate("delete from  Supplies where TypeId="+row.getTypeId() + ";");
                stmt.close();


            }
            catch(SQLException s) {
                s.printStackTrace();
                System.out.println("SQL statement is not executed!");

            }
            connect.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void Stats(ActionEvent event) {

        try {

            root = FXMLLoader.load(getClass().getResource("SuppliesStat.fxml"));
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


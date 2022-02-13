package com.database.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class customerSearchWindow {
    public Connection connect=null;

    Statement statement=null;
    boolean[] radio =new boolean[4];
    ObservableList<customer> Searchlist = FXCollections.observableArrayList();

    @FXML
    private RadioButton radioAdd;

    @FXML
    private RadioButton radioId;

    @FXML
    private RadioButton radioName;

    @FXML
    private RadioButton radioPhone;

    @FXML
    private TextField Address;

    @FXML
    private TableColumn<Supplies, String> address;

    @FXML
    private TextField Phone;

    @FXML
    private TableColumn<Supplies, Integer> phone;

    @FXML
    private TextField CID;

    @FXML
    private TableColumn<Supplies, Integer> Cid;

    @FXML
    private TextField Name;

    @FXML
    private TableColumn<Supplies, String> Cname;

    @FXML
    private TableView<customer> table;

    @FXML
    void Back(ActionEvent event) {
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

    @FXML
    void Search() throws SQLException, ClassNotFoundException {

        table.getItems().clear();
        radioSelected();
        //System.out.println(radio[0]);
        PreparedStatement statment = null;

        if((radio[0] && CID.getText().isEmpty())||(radio[1] && Name.getText().isEmpty())||(radio[2] && Phone.getText().isEmpty())||(radio[3] && Address.getText().isEmpty())){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select the search choice and fill the text field");
            alert.showAndWait();
        }

        try {
            SuppliesWindow.connectDataBase();
            statement=SuppliesWindow.connect.createStatement();
            //String sql ="select * from supplies where TypeId=? AND Name=? AND Phone=? AND ExpireDate=?";
            String sql;

            if(radio[0]){
                sql ="select * from customer where C_id=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.parseInt(CID.getText()));
            }else if(!radio[2] && !radio[3] && radio[1]){
                sql ="select * from customer where C_name=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setString(1, Name.getText());
            }else if(!radio[1] && !radio[3] && radio[2]){
                sql ="select * from customer where Phone=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.parseInt(Phone.getText()));
            }else if(!radio[2] && !radio[1] && radio[3]){
                sql ="select * from customer where Address=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setString(1, Address.getText());
            }else if(!radio[1] && radio[2]){
                sql ="select * from customer where Phone=? AND Address=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.parseInt(Phone.getText()));
                statment.setString(2, Address.getText());
            }else if(radio[1] && !radio[2]){
                sql ="select * from customer where C_name=? AND Address=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setString(1, Name.getText());
                statment.setString(2, Address.getText());
            }else if(radio[1] && !radio[3]){
                sql ="select * from customer where C_name=? AND Phone=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setString(1, Name.getText());
                statment.setInt(2, Integer.parseInt(Phone.getText()));

            }else if(radio[1]){
                sql ="select * from customer where C_name=? AND Phone=? AND Address=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setString(1, Name.getText());
                statment.setInt(2, Integer.parseInt(Phone.getText()));
                statment.setString(3, Address.getText());
            }

            assert statment != null;
            ResultSet resultSet=statment.executeQuery();

            while ( resultSet.next() ) {

                Searchlist.add(new customer(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3)),
                        resultSet.getString(4)));

            }
            Cid.setCellValueFactory(new PropertyValueFactory<>("C_id"));
            Cname.setCellValueFactory(new PropertyValueFactory<>("C_name"));
            phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            address.setCellValueFactory(new PropertyValueFactory<>("Address"));
            table.setItems(Searchlist);

            CID.clear();
            Name.clear();
            Phone.clear();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void radioSelected() {

        radio[0]= radioId.isSelected();
        radio[1]= radioName.isSelected();
        radio[2]= radioPhone.isSelected();
        radio[3]= radioAdd.isSelected();

    }
}

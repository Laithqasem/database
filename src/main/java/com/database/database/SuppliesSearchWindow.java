package com.database.database;
//#fdc623   #991b1f   -fx-background-color      D88722

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

public class SuppliesSearchWindow {

    public Connection connect=null;


    Statement statement=null;
    boolean[] radio =new boolean[4];
    ObservableList<Supplies> Searchlist = FXCollections.observableArrayList();

    @FXML
    private RadioButton radioDate;

    @FXML
    private RadioButton radioId;

    @FXML
    private RadioButton radioName;

    @FXML
    private RadioButton radioQuan;


    @FXML
    private DatePicker ExpiredDate;

    @FXML
    private TableColumn<Supplies, String> ExpiredDateCol;

    @FXML
    private TextField Quantity;

    @FXML
    private TableColumn<Supplies, Integer> QuantityCol;

    @FXML
    private TextField TypeID;

    @FXML
    private TableColumn<Supplies, Integer> TypeIDCol;

    @FXML
    private TextField TypeName;

    @FXML
    private TableColumn<Supplies, String> TypeNameCol;

    @FXML
    private TableView<Supplies> table;

    @FXML
    void Back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SuppliesWindow.fxml")));
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
        System.out.println(radio[0]);
        PreparedStatement statment = null;

        if((radio[0] && TypeID.getText().isEmpty())||(radio[1] && TypeName.getText().isEmpty())||(radio[2] && Quantity.getText().isEmpty())||(radio[3] && ExpiredDate.getValue().toString().isEmpty())){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select the search choice and fill the text field");
            alert.showAndWait();
        }

        try {
            SuppliesWindow.connectDataBase();
            statement=SuppliesWindow.connect.createStatement();
            //String sql ="select * from supplies where TypeId=? AND TypeName=? AND Quantity=? AND ExpireDate=?";
            String sql;
            
            if(radio[0]){
                sql ="select * from supplies where TypeId=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.parseInt(TypeID.getText()));
            }else if(!radio[2] && !radio[3] && radio[1]){
                sql ="select * from supplies where TypeName=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setString(1, TypeName.getText());
            }else if(!radio[1] && !radio[3] && radio[2]){
                sql ="select * from supplies where Quantity=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.parseInt(Quantity.getText()));
            }else if(!radio[2] && !radio[1] && radio[3]){
                sql ="select * from supplies where ExpireDate=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setString(1, ExpiredDate.getValue().toString());
            }else if(!radio[1] && radio[2]){
                sql ="select * from supplies where Quantity=? AND ExpireDate=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.parseInt(Quantity.getText()));
                statment.setString(2, ExpiredDate.getValue().toString());
            }else if(radio[1] && !radio[2]){
                sql ="select * from supplies where TypeName=? AND ExpireDate=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setString(1, TypeName.getText());
                statment.setString(2, ExpiredDate.getValue().toString());
            }else if(radio[1] && !radio[3]){
                sql ="select * from supplies where TypeName=? AND Quantity=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setString(1, TypeName.getText());
                statment.setInt(2, Integer.parseInt(Quantity.getText()));

            }else if(radio[1]){
                sql ="select * from supplies where TypeName=? AND Quantity=? AND ExpireDate=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setString(1, TypeName.getText());
                statment.setInt(2, Integer.parseInt(Quantity.getText()));
                statment.setString(3, ExpiredDate.getValue().toString());
            }

            assert statment != null;
            ResultSet resultSet=statment.executeQuery();

            while ( resultSet.next() ) {

                Searchlist.add(new Supplies(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3)),
                        resultSet.getString(4)));

            }
            TypeIDCol.setCellValueFactory(new PropertyValueFactory<>("TypeId"));
            TypeNameCol.setCellValueFactory(new PropertyValueFactory<>("TypeName"));
            QuantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
            ExpiredDateCol.setCellValueFactory(new PropertyValueFactory<>("ExpireDate"));
            table.setItems(Searchlist);

            TypeID.clear();
            TypeName.clear();
            Quantity.clear();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void radioSelected() {

        radio[0]= radioId.isSelected();
        radio[1]= radioName.isSelected();
        radio[2]= radioQuan.isSelected();
        radio[3]= radioDate.isSelected();

    }

}

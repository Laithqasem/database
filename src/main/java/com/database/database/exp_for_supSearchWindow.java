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

public class exp_for_supSearchWindow {

    public Connection connect=null;
    Statement statement=null;
    boolean[] radio =new boolean[4];
    ObservableList<exp_for_sup> SearchList = FXCollections.observableArrayList();

    @FXML
    private RadioButton radioBillId;

    @FXML
    private RadioButton radioTypeId;

    @FXML
    private RadioButton radioPricePerUnit;

    @FXML
    private RadioButton radioTypeQuant;

    @FXML
    private TextField TypeQuant;

    @FXML
    private TableColumn<exp_for_sup, Integer> BillIdCol;

    @FXML
    private TextField BillId;

    @FXML
    private TableColumn<exp_for_sup, Integer> TypeIdCol;

    @FXML
    private TextField TypeId;

    @FXML
    private TableColumn<exp_for_sup, Integer> PricePerUnitCol;

    @FXML
    private TextField PricePerUnit;

    @FXML
    private TableColumn<exp_for_sup, Integer> TypeQuantCol;

    @FXML
    private TableView<exp_for_sup> table;

    @FXML
    void Back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exp_for_supWindow.fxml")));
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
        PreparedStatement statment;

        if((radio[0] && BillId.getText().isEmpty())||(radio[1] && TypeId.getText().isEmpty())||(radio[2] && PricePerUnit.getText().isEmpty())||(radio[3] && TypeQuant.getText().isEmpty())){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select the search choice and fill the text field");
            alert.showAndWait();
        }

        try {
            SuppliesWindow.connectDataBase();
            statement=SuppliesWindow.connect.createStatement();
            String sql;
             {


             if(radio[0] && !radio[2] && !radio[3] && !radio[1]){
                sql ="select * from exp_for_sup where BillId=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
            }else if(!radio[0] && radio[1] && !radio[3] && !radio[2]){
                sql ="select * from exp_for_sup where TypeId=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.parseInt(TypeId.getText()));
            }else if(!radio[0] && radio[2] && !radio[1] && !radio[3]){
                sql ="select * from exp_for_sup where PricePerUnit=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.parseInt(PricePerUnit.getText()));
            }else if(!radio[0] && !radio[1] && !radio[2] && radio[3]){
                 sql ="select * from exp_for_sup where TypeQuant=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(TypeQuant.getText()));
            }else if(radio[0] && radio[1] && !radio[2] && !radio[3]){
                sql ="select * from exp_for_sup where BillId=? AND TypeId=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(TypeId.getText()));

            }else if(radio[0] && !radio[1] && radio[2] && !radio[3]){
                 sql ="select * from exp_for_sup where BillId=? AND PricePerUnit=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(PricePerUnit.getText()));

             }else if(radio[0] && !radio[1] && !radio[2]){
                 sql ="select * from exp_for_sup where BillId=? AND TypeQuant=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(TypeQuant.getText()));

             }else if(!radio[0] && radio[1] && radio[2] && !radio[3]){
                 sql ="select * from exp_for_sup where TypeId=? AND PricePerUnit=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(TypeId.getText()));
                 statment.setInt(2, Integer.parseInt(PricePerUnit.getText()));

             }else if(!radio[0] && radio[1] && !radio[2]){
                 sql ="select * from exp_for_sup where TypeId=? AND TypeQuant=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(TypeId.getText()));
                 statment.setInt(2, Integer.parseInt(TypeQuant.getText()));

             }

             else if(radio[0] && radio[1] && radio[2] && !radio[3]){
                sql ="select * from exp_for_sup where BillId=? AND TypeId=? AND PricePerUnit=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(TypeId.getText()));
                 statment.setInt(3, Integer.parseInt(PricePerUnit.getText()));

             }else if(!radio[0] && radio[1]){
                 sql ="select * from exp_for_sup where TypeId=? AND PricePerUnit=? AND TypeQuant=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(TypeId.getText()));
                 statment.setInt(2, Integer.parseInt(PricePerUnit.getText()));
                 statment.setInt(3, Integer.parseInt(TypeQuant.getText()));

             }else if(radio[0] && radio[1] && !radio[2]){
                 sql ="select * from exp_for_sup where BillId=? AND TypeId=? AND TypeQuant=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(TypeId.getText()));
                 statment.setInt(3, Integer.parseInt(TypeQuant.getText()));

             }else if(radio[0] && !radio[1]){
                 sql ="select * from exp_for_sup where BillId=? AND PricePerUnit=? AND TypeQuant=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(PricePerUnit.getText()));
                 statment.setInt(3, Integer.parseInt(TypeQuant.getText()));

             }else{
                 sql ="select * from exp_for_sup where BillId=? AND TypeId=? AND PricePerUnit=? AND TypeQuant=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(TypeId.getText()));
                 statment.setInt(3, Integer.parseInt(PricePerUnit.getText()));
                 statment.setInt(4, Integer.parseInt(TypeQuant.getText()));
             }

            ResultSet resultSet=statment.executeQuery();

            while ( resultSet.next() ) {

                SearchList.add(new exp_for_sup(
                        Integer.parseInt(resultSet.getString(1)),
                        Integer.parseInt(resultSet.getString(2)),
                        Integer.parseInt(resultSet.getString(3)),
                        Integer.parseInt(resultSet.getString(4)) ));

            }
            BillIdCol.setCellValueFactory(new PropertyValueFactory<>("BillId"));
            TypeIdCol.setCellValueFactory(new PropertyValueFactory<>("TypeId"));
            PricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("PricePerUnit"));
            TypeQuantCol.setCellValueFactory(new PropertyValueFactory<>("TypeQuant"));
            table.setItems(SearchList);

            BillId.clear();
            TypeId.clear();
            PricePerUnit.clear();
            TypeQuant.clear();

        }} catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void radioSelected() {

        radio[0]= radioBillId.isSelected();
        radio[1]= radioTypeId.isSelected();
        radio[2]= radioPricePerUnit.isSelected();
        radio[3]= radioTypeQuant.isSelected();

    }

}

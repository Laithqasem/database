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

public class exp_for_empSearchWindow {

    public Connection connect=null;
    Statement statement=null;

    boolean[] radio =new boolean[4];
    ObservableList<exp_for_emp> Searchlist = FXCollections.observableArrayList();

    @FXML
    private RadioButton radioBillId;

    @FXML
    private RadioButton radioEId;

    @FXML
    private RadioButton radioBaseSalary;

    @FXML
    private RadioButton radioOvertimePrice;


    @FXML
    private TextField BaseSalary;


    @FXML
    private TableColumn<exp_for_emp, Integer> BillIdCol;

    @FXML
    private TextField BillId;

    @FXML
    private TableColumn<exp_for_emp, Integer> EIdCol;

    @FXML
    private TextField EId;

    @FXML
    private TableColumn<exp_for_emp, Integer> OvertimePriceCol;

    @FXML
    private TextField OvertimePrice;

    @FXML
    private TableColumn<exp_for_emp, Integer> BaseSalaryCol;

    @FXML
    private TableView<exp_for_emp> table;

    @FXML
    void BAck(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exp_for_empWindow.fxml")));
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

        if((radio[0] && BillId.getText().isEmpty())||(radio[1] && EId.getText().isEmpty())||(radio[2] && BaseSalary.getText().isEmpty())||(radio[3] && OvertimePrice.getText().isEmpty())){

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
                sql ="select * from exp_for_emp where BillId=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
            }else if(!radio[0] && radio[1] && !radio[3] && !radio[2]){
                sql ="select * from exp_for_emp where EId=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.parseInt(EId.getText()));
            }else if(!radio[0] && radio[2] && !radio[1] && !radio[3]){
                sql ="select * from exp_for_emp where BaseSalary=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.parseInt(BaseSalary.getText()));
            }else if(!radio[0] && !radio[1] && !radio[2] && radio[3]){
                 sql ="select * from exp_for_emp where OvertimePrice=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(OvertimePrice.getText()));
            }else if(radio[0] && radio[1] && !radio[2] && !radio[3]){
                sql ="select * from exp_for_emp where BillId=? AND EId=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(EId.getText()));

            }else if(radio[0] && !radio[1] && radio[2] && !radio[3]){
                 sql ="select * from exp_for_emp where BillId=? AND BaseSalary=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(BaseSalary.getText()));

             }else if(radio[0] && !radio[1] && !radio[2]){
                 sql ="select * from exp_for_emp where BillId=? AND OvertimePrice=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(OvertimePrice.getText()));

             }else if(!radio[0] && radio[1] && radio[2] && !radio[3]){
                 sql ="select * from exp_for_emp where EId=? AND BaseSalary=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(EId.getText()));
                 statment.setInt(2, Integer.parseInt(BaseSalary.getText()));

             }else if(!radio[0] && radio[1] && !radio[2]){
                 sql ="select * from exp_for_emp where EId=? AND OvertimePrice=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(EId.getText()));
                 statment.setInt(2, Integer.parseInt(OvertimePrice.getText()));

             }

             else if(radio[0] && radio[1] && radio[2] && !radio[3]){
                sql ="select * from exp_for_emp where BillId=? AND EId=? AND BaseSalary=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(EId.getText()));
                 statment.setInt(3, Integer.parseInt(BaseSalary.getText()));

             }else if(!radio[0] && radio[1]){
                 sql ="select * from exp_for_emp where EId=? AND BaseSalary=? AND OvertimePrice=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(EId.getText()));
                 statment.setInt(2, Integer.parseInt(BaseSalary.getText()));
                 statment.setInt(3, Integer.parseInt(OvertimePrice.getText()));

             }else if(radio[0] && radio[1] && !radio[2]){
                 sql ="select * from exp_for_emp where BillId=? AND EId=? AND OvertimePrice=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(EId.getText()));
                 statment.setInt(3, Integer.parseInt(OvertimePrice.getText()));

             }else if(radio[0] && !radio[1]){
                 sql ="select * from exp_for_emp where BillId=? AND BaseSalary=? AND OvertimePrice=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(BaseSalary.getText()));
                 statment.setInt(3, Integer.parseInt(OvertimePrice.getText()));

             }else{
                 sql ="select * from exp_for_emp where BillId=? AND EId=? AND BaseSalary=? AND OvertimePrice=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                 statment.setInt(1, Integer.parseInt(BillId.getText()));
                 statment.setInt(2, Integer.parseInt(EId.getText()));
                 statment.setInt(3, Integer.parseInt(BaseSalary.getText()));
                 statment.setInt(4, Integer.parseInt(OvertimePrice.getText()));
             }


            ResultSet resultSet=statment.executeQuery();

            while ( resultSet.next() ) {

                Searchlist.add(new exp_for_emp(
                        Integer.parseInt(resultSet.getString(1)),
                        Integer.parseInt(resultSet.getString(2)),
                        Integer.parseInt(resultSet.getString(3)),
                        Integer.parseInt(resultSet.getString(4)) ));


            }
            BillIdCol.setCellValueFactory(new PropertyValueFactory<>("BillId"));
            EIdCol.setCellValueFactory(new PropertyValueFactory<>("EId"));
            BaseSalaryCol.setCellValueFactory(new PropertyValueFactory<>("BaseSalary"));
            OvertimePriceCol.setCellValueFactory(new PropertyValueFactory<>("OvertimePrice"));
            table.setItems(Searchlist);

            BillId.clear();
            EId.clear();
                 BaseSalary.clear();
                 OvertimePrice.clear();


        }} catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void radioSelected() {

        radio[0]= radioBillId.isSelected();
        radio[1]= radioEId.isSelected();
        radio[2]= radioBaseSalary.isSelected();
        radio[3]= radioOvertimePrice.isSelected();

    }

}

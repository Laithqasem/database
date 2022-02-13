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

public class ExpensesSearchWindow {

    public Connection connect=null;
    Statement statement=null;
    boolean[] radio =new boolean[3];
    ObservableList<Expenses> Searchlist = FXCollections.observableArrayList();

    @FXML
    private RadioButton radioBillDate;

    @FXML
    private RadioButton radioBillId;

    @FXML
    private RadioButton radioTotalPay;

    @FXML
    private TextField BillId;

    @FXML
    private TableColumn<Expenses, Integer> BillIdCol;

    @FXML
    private TextField TotalPay;

    @FXML
    private TableColumn<Expenses, Integer> TotalPayCol;

    @FXML
    private DatePicker BillDate;

    @FXML
    private TableColumn<Expenses, String> BillDateCol;

    @FXML
    private TableView<Expenses> table;

    @FXML
    void BAck(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ExpensesWindow.fxml")));
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

        PreparedStatement statment = null;

        if((radio[0] && BillId.getText().isEmpty())||(radio[1] && BillDate.getValue().toString().isEmpty())||(radio[2] && TotalPay.getText().isEmpty())){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select the search choice and fill the text field");
            alert.showAndWait();
        }

        try {
            SuppliesWindow.connectDataBase();
            statement=SuppliesWindow.connect.createStatement();
             String sql;

            if(radio[0]){
                sql ="select * from expenses where BillId=? ";
                 statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.parseInt(BillId.getText()));
            }else if(!radio[2] && radio[1]){
                sql ="select * from expenses where BillDate=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setString(1, BillDate.getValue().toString());
            }else if(!radio[1] && radio[2]){
                sql ="select * from expenses where TotalPay=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(1, Integer.parseInt(TotalPay.getText()));

            }else if(radio[1]){
                sql ="select * from expenses where BillDate=? AND TotalPay=? ";
                statment = SuppliesWindow.connect.prepareStatement(sql);
                statment.setInt(2, Integer.parseInt(TotalPay.getText()));
                statment.setString(1, BillDate.getValue().toString());
            }
            assert statment != null;
            ResultSet resultSet=statment.executeQuery();

            while ( resultSet.next() ) {

                Searchlist.add(new Expenses(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3))));
            }

            BillIdCol.setCellValueFactory(new PropertyValueFactory<>("BillId"));
            BillDateCol.setCellValueFactory(new PropertyValueFactory<>("BillDate"));
            TotalPayCol.setCellValueFactory(new PropertyValueFactory<>("TotalPay"));

            table.setItems(Searchlist);
            BillId.clear();
            TotalPay.clear();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void radioSelected() {
        radio[0]= radioBillId.isSelected();
        radio[1]= radioBillDate.isSelected();
        radio[2]= radioTotalPay.isSelected();
    }
}

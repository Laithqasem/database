package com.database.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class salesWindow implements Initializable {

    @FXML
    private Label total;
    private int totalP;

    @FXML
    private TableColumn<Orders, Integer> CID;

    @FXML
    private TableColumn<Orders, Integer> Discount;

    @FXML
    private TableColumn<Orders, Double> ElapsedTime;

    @FXML
    private TableColumn<Orders, String> Notes;

    @FXML
    private TableColumn<Orders, Integer> OID;

    @FXML
    private TableColumn<Orders, Integer> TableNo;

    @FXML
    private TableView<Orders> table;

    @FXML
    private TableColumn<Orders, Integer> totalPrice;

    @FXML
    void Back(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OID.setCellValueFactory(new PropertyValueFactory<>("O_id"));
        CID.setCellValueFactory(new PropertyValueFactory<>("C_id"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<>("total_price"));
        Discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        Notes.setCellValueFactory(new PropertyValueFactory<>("Notes"));
        ElapsedTime.setCellValueFactory(new PropertyValueFactory<>("Elapsed_time"));
        TableNo.setCellValueFactory(new PropertyValueFactory<>("Table_No"));
        table.setItems(CustomerOrdersWindow.archivedOrders);

        for(int i=0; i < CustomerOrdersWindow.archivedOrders.size(); i++){
            totalP += CustomerOrdersWindow.archivedOrders.get(i).getTotal_price();
        }
        total.setText(String.valueOf(totalP));
    }
}
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
import java.util.ResourceBundle;

public class salesWindow implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label total;
    ActionEvent event;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OID.setCellValueFactory(new PropertyValueFactory<Orders,Integer>("O_id"));
        CID.setCellValueFactory(new PropertyValueFactory<Orders,Integer>("C_id"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<Orders,Integer>("total_price"));
        Discount.setCellValueFactory(new PropertyValueFactory<Orders,Integer>("discount"));
        Notes.setCellValueFactory(new PropertyValueFactory<Orders,String>("Notes"));
        ElapsedTime.setCellValueFactory(new PropertyValueFactory<Orders,Double>("Elapsed_time"));
        TableNo.setCellValueFactory(new PropertyValueFactory<Orders,Integer>("Table_No"));
        table.setItems(CustomerOrdersWindow.archivedOrders);

        for(int i=0; i < CustomerOrdersWindow.archivedOrders.size(); i++){
            totalP += CustomerOrdersWindow.archivedOrders.get(i).getTotal_price();
        }
        total.setText(String.valueOf(totalP));
    }
}

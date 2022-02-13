package com.database.database;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class RoleView implements Initializable {

    //ObservableList<Role> list = FXCollections.observableArrayList();

    @FXML
    private TableView<Role> roleTable;
    @FXML
    private TextField addBase_salary;
    @FXML
    private TextField addOvertime_hours_price;
    @FXML
    private TextField addRole_id;
    @FXML
    private TextField addRole_name;
    @FXML
    private TableColumn<Role, Integer> base_salary;
    @FXML
    private TableColumn<Role, Integer> overtime_hours_price;
    @FXML
    private TableColumn<Role,String> role_id;
    @FXML
    private TableColumn<Role, String> role_name;


    public void delete() {
        ObservableList<Role> selectedRows = roleTable.getSelectionModel().getSelectedItems();
        ArrayList<Role> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            roleTable.getItems().remove(row);
            JavaMysqlCode.deleteRow(row);
            roleTable.refresh();
        });
    }
/*
    public void fillRoleTable() {
        JavaMysqlCode.getConnection();
        String connectQuery = "select role_id,role_name, base_salary, overtime_hours_price from Roles order by role_id";
        try {
            Statement statement = JavaMysqlCode.connection.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);
            while (queryOutput.next()){
                list.add(new Role(
                        queryOutput.getString(1),
                        queryOutput.getString(2),
                        Integer.parseInt(queryOutput.getString(3)),
                        Integer.parseInt(queryOutput.getString(4))
                ));
            }
            queryOutput.close();
            statement.close();
            JavaMysqlCode.connection.close();
            System.out.println("Connection closed" + list.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
 */

    @FXML
    void back(ActionEvent event) {
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
    public void addRole(){
        Role rc;
        rc = new Role(
                addRole_id.getText(),
                addRole_name.getText(),
                Integer.parseInt(addBase_salary.getText()),
                Integer.parseInt(addOvertime_hours_price.getText()) );
        LoginMenu.roles.add(rc);
        JavaMysqlCode.insertData(rc);
        addRole_id.clear();
        addRole_name.clear();
        addBase_salary.clear();
        addOvertime_hours_price.clear();
    }

    public void editBaseSalary(CellEditEvent<Role, Integer> t){

        t.getTableView().getItems().get(t.getTablePosition().getRow()).setBase_salary(t.getNewValue());
        JavaMysqlCode.updateRoleTable( ""+t.getRowValue().getRole_id(),""+t.getNewValue(),"base_salary");

    }
    public void editOvertime_price(CellEditEvent<Role, Integer> t){

        t.getTableView().getItems().get(t.getTablePosition().getRow()).setOvertime_hours_price(t.getNewValue());
        JavaMysqlCode.updateRoleTable( ""+t.getRowValue().getRole_id(),""+t.getNewValue(),"overtime_hours_price");

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        role_id.setCellValueFactory(new PropertyValueFactory<>("role_id"));
        role_name.setCellValueFactory(new PropertyValueFactory<>("role_name"));
        base_salary.setCellValueFactory(new PropertyValueFactory<>("base_salary"));
        overtime_hours_price.setCellValueFactory(new PropertyValueFactory<>("overtime_hours_price"));
        //fillRoleTable();
        roleTable.setItems(LoginMenu.roles);
        roleTable.setEditable(true);
        base_salary.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        overtime_hours_price.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


    }
}





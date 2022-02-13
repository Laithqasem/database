package com.database.database;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class EmployeeView implements Initializable {

    //ObservableList<Employee> list = FXCollections.observableArrayList();

    @FXML
    private TextField addBirthdate;
    @FXML
    private TextField addE_id;
    @FXML
    private TextField addE_name;
    @FXML
    private TextField addOvertime_hours;
    @FXML
    private TextField addPhone;
    @FXML
    private TextField addR_id;
    @FXML
    private TableColumn<Employee, String> Birthdate;
    @FXML
    private TableColumn<Employee, Integer> e_id;
    @FXML
    private TableColumn<Employee, String> e_name;
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Integer> overtime_hours;
    @FXML
    private TableColumn<Employee, Integer> phone;
    @FXML
    private TableColumn<Employee, String> r_id;

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
    public void delete() {
        ObservableList<Employee> selectedRows = employeeTable.getSelectionModel().getSelectedItems();
        ArrayList<Employee> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> {
            employeeTable.getItems().remove(row);
            JavaMysqlCode.deleteRow(row);
            employeeTable.refresh();
        });
    }
/*
    public void fillEmployeeTable() {
        JavaMysqlCode.getConnection();
        String connectQuery = "select e_id,e_name, birthdate, phone,r_id,overtime_hours from employees order by e_id";
        try {
            Statement statement = JavaMysqlCode.connection.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);
            while (queryOutput.next()){
                list.add(new Employee(
                        Integer.parseInt(queryOutput.getString(1)),
                        queryOutput.getString(2),
                        queryOutput.getString(3),
                        Integer.parseInt(queryOutput.getString(4)),
                        queryOutput.getString(5),
                        Integer.parseInt(queryOutput.getString(6))));
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

    public void addEmployee(){
        Employee rc;
        rc = new Employee(
                Integer.parseInt(addE_id.getText()),
                addE_name.getText(),
                addBirthdate.getText(),
                Integer.parseInt(addPhone.getText()),
                addR_id.getText(),
                Integer.parseInt(addOvertime_hours.getText()));
        LoginMenu.employees.add(rc);
        JavaMysqlCode.insertData(rc);
        addE_id.clear();
        addE_name.clear();
        addBirthdate.clear();
        addPhone.clear();
        addOvertime_hours.clear();
        addR_id.clear();
    }

    public void editPhone(CellEditEvent<Employee, Integer> t){

            t.getTableView().getItems().get(t.getTablePosition().getRow()).setPhone(t.getNewValue());
            JavaMysqlCode.updateEmployeeTable( t.getRowValue().getE_id(),""+t.getNewValue(),"phone");

    }
    public void editBirthdate(CellEditEvent<Employee, String> t){

        t.getTableView().getItems().get(t.getTablePosition().getRow()).setBirthdate(t.getNewValue());
        JavaMysqlCode.updateEmployeeTable( t.getRowValue().getE_id(),""+t.getNewValue(),"birthdate");

    }
    public void editR_id(CellEditEvent<Employee, String> t){

        t.getTableView().getItems().get(t.getTablePosition().getRow()).setR_id(t.getNewValue());
        JavaMysqlCode.updateEmployeeTable( t.getRowValue().getE_id(),""+t.getNewValue(),"r_id");

    }
    public void editOvertime_hours(CellEditEvent<Employee, Integer> t){

        t.getTableView().getItems().get(t.getTablePosition().getRow()).setOvertime_hours(t.getNewValue());
        JavaMysqlCode.updateEmployeeTable( t.getRowValue().getE_id(),""+t.getNewValue(),"overtime_hours");

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        e_name.setCellValueFactory(new PropertyValueFactory<>("e_name"));
        Birthdate.setCellValueFactory(new PropertyValueFactory<>("Birthdate"));
        e_id.setCellValueFactory(new PropertyValueFactory<>("e_id"));
        overtime_hours.setCellValueFactory(new PropertyValueFactory<>("overtime_hours"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        r_id.setCellValueFactory(new PropertyValueFactory<>("r_id"));
        //fillEmployeeTable();
        employeeTable.setItems(LoginMenu.employees);
        employeeTable.setEditable(true);
        phone.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Birthdate.setCellFactory(TextFieldTableCell.forTableColumn());
        r_id.setCellFactory(TextFieldTableCell.forTableColumn());
        overtime_hours.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

    }
}





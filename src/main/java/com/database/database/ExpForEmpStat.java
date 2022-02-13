package com.database.database;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ExpForEmpStat implements Initializable {

    @FXML
    private Label EmployeeOver;

    @FXML
    private TextField Id;

    @FXML
    private Label employeeTotal;

    @FXML
    private Label EmployeeBase;

    @FXML
    private Label totalEmployeesExpensesLabel;

    @FXML
    private BarChart<String, Number> totalChart;

    @FXML
    private NumberAxis EmpExpenses;

    @FXML
    private CategoryAxis EmpIdChartAxis;

    @FXML
    private Label totalEmployeesOvertimeLabel;

    @FXML
    private Label totalEmployeesSalaryLabel;

    @FXML
    private PieChart pieChart;

    @FXML
    void Back(ActionEvent event) {
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
    void EmployeeExpenses() {
        int id=Integer.parseInt(Id.getText());

        int totalExpensesForAnEmployee=0;
        int totalExpensesForAnEmployeeOver=0;
        int totalExpensesForAnEmployeeBase=0;
        for(int i = 0; i < LoginMenu.expForEmps.size(); i++) {
            if(LoginMenu.expForEmps.get(i).getEId()==id){
                totalExpensesForAnEmployee=totalExpensesForAnEmployee+ LoginMenu.expForEmps.get(i).getOvertimePrice()+ LoginMenu.expForEmps.get(i).getBaseSalary();
                totalExpensesForAnEmployeeBase=totalExpensesForAnEmployeeBase+ LoginMenu.expForEmps.get(i).getBaseSalary();
                totalExpensesForAnEmployeeOver=totalExpensesForAnEmployeeOver+ LoginMenu.expForEmps.get(i).getOvertimePrice();
            }
        }
        EmployeeOver.setText("   OverTime expenses: "+totalExpensesForAnEmployeeOver);
        employeeTotal.setText("   Total expenses: "+totalExpensesForAnEmployee);
        EmployeeBase.setText("   Total Base Salaries: "+totalExpensesForAnEmployeeBase);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int totalEmployeesSalary=0;
        int totalEmployeesExpenses;
        int totalEmployeesOvertime=0;
        for(int i = 0; i < LoginMenu.expForEmps.size(); i++) {
            totalEmployeesSalary=totalEmployeesSalary+ LoginMenu.expForEmps.get(i).getBaseSalary();
            totalEmployeesOvertime=totalEmployeesOvertime+ LoginMenu.expForEmps.get(i).getOvertimePrice();
        }

        totalEmployeesSalaryLabel.setText("   Total Employees Salaries: "+ totalEmployeesSalary);
        totalEmployeesExpenses=totalEmployeesOvertime+totalEmployeesSalary;
        totalEmployeesExpensesLabel.setText("   Total Employees Expenses: "+ totalEmployeesExpenses);
        totalEmployeesOvertimeLabel.setText("   Total Employees Overtime: "+ totalEmployeesOvertime);


       int[] listOfTotalExpenses=new int[LoginMenu.expForEmps.size()];
       int[] listOfTotalBaseSalary=new int[LoginMenu.expForEmps.size()];
       int[] listOfTotalovertime=new int[LoginMenu.expForEmps.size()];
       for (int i = 0; i < LoginMenu.expForEmps.size(); i++) {

            for (int j = i + 1; j < LoginMenu.expForEmps.size(); j++) {

                if (LoginMenu.expForEmps.get(j).getEId() < LoginMenu.expForEmps.get(i).getEId()) {
                    LoginMenu.expForEmps.set(i, LoginMenu.expForEmps.set(j, LoginMenu.expForEmps.get(i)));
                }
            }
        }

       int flag=0;
        System.out.println("--------------------------------");

        for(int i = 0; i < LoginMenu.expForEmps.size(); i++) {
            totalEmployeesSalary= LoginMenu.expForEmps.get(i).getBaseSalary();
            totalEmployeesOvertime= LoginMenu.expForEmps.get(i).getOvertimePrice();
            System.out.println("   "+totalEmployeesSalary+"   "+totalEmployeesOvertime);
            for(int j = i+1; j < LoginMenu.expForEmps.size(); j++) {
                if(LoginMenu.expForEmps.get(i).getEId()== LoginMenu.expForEmps.get(j).getEId()){
                    totalEmployeesSalary+= LoginMenu.expForEmps.get(j).getBaseSalary();
                    totalEmployeesOvertime+= LoginMenu.expForEmps.get(j).getOvertimePrice();
                    flag++;
                }
            }
            listOfTotalExpenses[i]=totalEmployeesSalary+totalEmployeesOvertime;
            listOfTotalBaseSalary[i]=totalEmployeesSalary;
            listOfTotalovertime[i]=totalEmployeesOvertime;
            i+=flag;

            flag=0;
        }

        XYChart.Series<String,Number> series = new XYChart.Series<>();
        XYChart.Series<String,Number> series2 = new XYChart.Series<>();
        XYChart.Series<String,Number> series3 = new XYChart.Series<>();
        series.setName("Expenses");
        series2.setName("Base Salaries");
        series3.setName("OverTime");
        for(int i = 0; i <listOfTotalExpenses.length; i++) {
            if(listOfTotalExpenses[i]!=0){
                series.getData().add(new XYChart.Data<>(String.valueOf(  LoginMenu.expForEmps.get(i).getEId()),listOfTotalExpenses[i]));
                series2.getData().add(new XYChart.Data<>(String.valueOf(  LoginMenu.expForEmps.get(i).getEId()),listOfTotalBaseSalary[i]));
                series3.getData().add(new XYChart.Data<>(String.valueOf(  LoginMenu.expForEmps.get(i).getEId()),listOfTotalovertime[i]));

            }
        }

        totalChart.getData().addAll(series,series2,series3);

        //--------------------------------------

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList( );

        for(int i=0; i<listOfTotalExpenses.length; i++){
            if(listOfTotalExpenses[i]!=0){
                pieChartData.add(new PieChart.Data(String.valueOf(LoginMenu.expForEmps.get(i).getEId()), listOfTotalExpenses[i]));
            }
        }
        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(" Eid: ",
                                data.getName(), "(", data.pieValueProperty(), "$)"
                        )
                )
        );
        pieChart.getData().addAll(pieChartData);
    }
}

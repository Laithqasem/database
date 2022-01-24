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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExpForEmpStat implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ImageView BackIcon1;

    @FXML
    private ImageView BackIcon11;
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

            root = FXMLLoader.load(getClass().getResource("exp_for_empWindow.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML
    void EmployeeExpenses(ActionEvent event) {
        int id=Integer.parseInt(Id.getText());

        int totalExpensesForAnEmployee=0;
        int totalExpensesForAnEmployeeOver=0;
        int totalExpensesForAnEmployeeBase=0;
        for(int i = 0; i < exp_for_empWindow.list.size(); i++) {
            if(exp_for_empWindow.list.get(i).getEId()==id){
                totalExpensesForAnEmployee=totalExpensesForAnEmployee+exp_for_empWindow.list.get(i).getOvertimePrice()+exp_for_empWindow.list.get(i).getBaseSalary();
                totalExpensesForAnEmployeeBase=totalExpensesForAnEmployeeBase+exp_for_empWindow.list.get(i).getBaseSalary();
                totalExpensesForAnEmployeeOver=totalExpensesForAnEmployeeOver+exp_for_empWindow.list.get(i).getOvertimePrice();
            }
        }
        EmployeeOver.setText("   OverTime expenses: "+totalExpensesForAnEmployeeOver);
        employeeTotal.setText("   Total expenses: "+totalExpensesForAnEmployee);
        EmployeeBase.setText("   Total Base Salaries: "+totalExpensesForAnEmployeeBase);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int totalEmployeesSalary=0;
        int totalEmployeesExpenses=0;
        int totalEmployeesOvertime=0;
        for(int i = 0; i < exp_for_empWindow.list.size(); i++) {
            totalEmployeesSalary=totalEmployeesSalary+exp_for_empWindow.list.get(i).getBaseSalary();
            totalEmployeesOvertime=totalEmployeesOvertime+exp_for_empWindow.list.get(i).getOvertimePrice();
        }

        totalEmployeesSalaryLabel.setText("   Total Employees Salaries: "+String.valueOf(totalEmployeesSalary));
        totalEmployeesExpenses=totalEmployeesOvertime+totalEmployeesSalary;
        totalEmployeesExpensesLabel.setText("   Total Employees Expenses: "+String.valueOf(totalEmployeesExpenses));
        totalEmployeesOvertimeLabel.setText("   Total Employees Overtime: "+String.valueOf(totalEmployeesOvertime));


       int[] listOfTotalExpenses=new int[exp_for_empWindow.list.size()];
       int[] listOfTotalBaseSalary=new int[exp_for_empWindow.list.size()];
       int[] listOfTotalovertime=new int[exp_for_empWindow.list.size()];
       for (int i = 0; i < exp_for_empWindow.list.size(); i++) {

            for (int j = i + 1; j < exp_for_empWindow.list.size(); j++) {

                if (exp_for_empWindow.list.get(j).getEId() < exp_for_empWindow.list.get(i).getEId()) {
                    exp_for_empWindow.list.set(i, exp_for_empWindow.list.set(j, exp_for_empWindow.list.get(i)));
                }
            }
        }

       int flag=0;
        System.out.println("--------------------------------");


        for(int i = 0; i < exp_for_empWindow.list.size(); i++) {
            totalEmployeesSalary=exp_for_empWindow.list.get(i).getBaseSalary();
            totalEmployeesOvertime=exp_for_empWindow.list.get(i).getOvertimePrice();
            System.out.println("   "+totalEmployeesSalary+"   "+totalEmployeesOvertime);
            for(int j = i+1; j < exp_for_empWindow.list.size(); j++) {
                if(exp_for_empWindow.list.get(i).getEId()==exp_for_empWindow.list.get(j).getEId()){
                    totalEmployeesSalary+=exp_for_empWindow.list.get(j).getBaseSalary();
                    totalEmployeesOvertime+=exp_for_empWindow.list.get(j).getOvertimePrice();
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
                series.getData().add(new XYChart.Data<>(String.valueOf(  exp_for_empWindow.list.get(i).getEId()),listOfTotalExpenses[i]));
                series2.getData().add(new XYChart.Data<>(String.valueOf(  exp_for_empWindow.list.get(i).getEId()),listOfTotalBaseSalary[i]));
                series3.getData().add(new XYChart.Data<>(String.valueOf(  exp_for_empWindow.list.get(i).getEId()),listOfTotalovertime[i]));

            }
        }

        totalChart.getData().addAll(series,series2,series3);

        //--------------------------------------

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList( );

        for(int i=0; i<listOfTotalExpenses.length; i++){
            if(listOfTotalExpenses[i]!=0){
                pieChartData.add(new PieChart.Data(String.valueOf(exp_for_empWindow.list.get(i).getEId()), listOfTotalExpenses[i]));
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

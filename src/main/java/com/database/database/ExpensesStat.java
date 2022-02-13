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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ExpensesStat implements Initializable {

    @FXML
    private BarChart<String, Number> totalChart;

    @FXML
    private Label numBills;

    @FXML
    private Label totalPays;

    @FXML
    private PieChart pieChart;

    @FXML
    void Back(ActionEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int totalpays=0;

        for(int i = 0; i < LoginMenu.expenses.size(); i++) {
            totalpays=totalpays+(LoginMenu.expenses.get(i).getTotalPay());
             }

        totalPays.setText("   Total Pays : "+ totalpays);
        numBills.setText("   Number of Bills : "+ LoginMenu.expenses.size());

       String[] listOfbilldates = new String[LoginMenu.expenses.size()];
       int[] listOfTotalPays = new int[LoginMenu.expenses.size()];

       for (int i = 0; i < LoginMenu.expenses.size(); i++) {

            for (int j = i + 1; j < LoginMenu.expenses.size(); j++) {

                if (LoginMenu.expenses.get(j).getBillDate().compareTo(LoginMenu.expenses.get(i).getBillDate()) < 0) {
                    LoginMenu.expenses.set(i, LoginMenu.expenses.set(j, LoginMenu.expenses.get(i)));
                }
            }
        }

       int flag=0;
        System.out.println("--------------------------------");

        for(int i = 0; i < LoginMenu.expenses.size(); i++) {
            totalpays= LoginMenu.expenses.get(i).getTotalPay();
            System.out.println("   "+totalpays);
            for(int j = i+1; j < LoginMenu.expenses.size(); j++) {
                if(LoginMenu.expenses.get(i).getBillDate().equals(LoginMenu.expenses.get(j).getBillDate())){
                    totalpays+= LoginMenu.expenses.get(j).getTotalPay();

                    flag++;

                }
            }
            listOfTotalPays[i]=totalpays;

            i+=flag;

            flag=0;

        }
        System.out.println("--------------------------------");
        for(int i = 0; i < LoginMenu.expenses.size(); i++) {
            System.out.println(LoginMenu.expenses.get(i));
        }
        for(int i = 0; i < LoginMenu.expenses.size(); i++) {
            System.out.println(listOfTotalPays[i]);
        }

        XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.setName("Bill Date");

        for(int i = 0; i <listOfTotalPays.length; i++) {
            if(listOfTotalPays[i]!=0){
                series.getData().add(new XYChart.Data<>(String.valueOf(  LoginMenu.expenses.get(i).getBillDate()),listOfTotalPays[i]));

            }
        }

        totalChart.getData().add(series);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList( );

        for(int i=0; i<listOfTotalPays.length; i++){
            if(listOfTotalPays[i]!=0){
                pieChartData.add(new PieChart.Data(String.valueOf( LoginMenu.expenses.get(i).getBillDate()), listOfTotalPays[i]));
            }

        }
        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(" Type id: ",
                                data.getName(), "(", data.pieValueProperty(), "$)"
                        )
                )
        );

        pieChart.getData().addAll(pieChartData);

    }
}

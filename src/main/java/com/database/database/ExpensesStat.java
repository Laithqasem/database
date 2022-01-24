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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExpensesStat implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ImageView BackIcon1;
    @FXML
    private Label SupplyName;
    @FXML
    private ImageView BackIcon11;

    @FXML
    private TextField Id;

    @FXML
    private Label totalSuppliesLabel;
    @FXML
    private BarChart<String, Number> totalChart;


    @FXML
    private Label numBills;

    @FXML
    private Label totalPays;
    @FXML
    private Label UnitPrice;



    @FXML
    private PieChart pieChart;
    @FXML
    void Back(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("ExpensesWindow.fxml"));
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
        int totalpays=0;

        for(int i = 0; i < ExpensesWindow.list.size(); i++) {
            totalpays=totalpays+(ExpensesWindow.list.get(i).getTotalPay());
             }

        totalPays.setText("   Total Pays : "+String.valueOf(totalpays));
        numBills.setText("   Number of Bills : "+String.valueOf(ExpensesWindow.list.size()));

       String[] listOfbilldates=new String[ExpensesWindow.list.size()];
       int[] listOfTotalPays=new int[ExpensesWindow.list.size()];

       for (int i = 0; i < ExpensesWindow.list.size(); i++) {

            for (int j = i + 1; j < ExpensesWindow.list.size(); j++) {

                if (ExpensesWindow.list.get(j).getBillDate().compareTo(ExpensesWindow.list.get(i).getBillDate())==-1 ) {
                    ExpensesWindow.list.set(i, ExpensesWindow.list.set(j, ExpensesWindow.list.get(i)));
                }
            }
        }


       int flag=0;
        System.out.println("--------------------------------");

        for(int i = 0; i < ExpensesWindow.list.size(); i++) {
            totalpays=ExpensesWindow.list.get(i).getTotalPay();
            System.out.println("   "+totalpays);
            for(int j = i+1; j < ExpensesWindow.list.size(); j++) {
                if(ExpensesWindow.list.get(i).getBillDate().equals(ExpensesWindow.list.get(j).getBillDate())==true){
                    totalpays+=ExpensesWindow.list.get(j).getTotalPay();

                    flag++;

                }
            }
            listOfTotalPays[i]=totalpays;

            i+=flag;

            flag=0;

        }
        System.out.println("--------------------------------");
        for(int i = 0; i < ExpensesWindow.list.size(); i++) {
            System.out.println(ExpensesWindow.list.get(i));
        }
        for(int i = 0; i < ExpensesWindow.list.size(); i++) {
            System.out.println(listOfTotalPays[i]);
        }



        XYChart.Series<String,Number> series = new XYChart.Series<>();
        XYChart.Series<String,Number> series2 = new XYChart.Series<>();
        XYChart.Series<String,Number> series3 = new XYChart.Series<>();
        series.setName("Bill Date");

        for(int i = 0; i <listOfTotalPays.length; i++) {
            if(listOfTotalPays[i]!=0){
                series.getData().add(new XYChart.Data<>(String.valueOf(  ExpensesWindow.list.get(i).getBillDate()),listOfTotalPays[i]));

            }
        }

        totalChart.getData().add(series);



        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList( );

        for(int i=0; i<listOfTotalPays.length; i++){
            if(listOfTotalPays[i]!=0){
                pieChartData.add(new PieChart.Data(String.valueOf( ExpensesWindow.list.get(i).getBillDate()), listOfTotalPays[i]));
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

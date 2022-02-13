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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ExpForSubStat implements Initializable {

    @FXML
    private Label SupplyName;

    @FXML
    private TextField Id;

    @FXML
    private Label totalSuppliesLabel;
    @FXML
    private BarChart<String, Number> totalChart;

    @FXML
    private Label TotalSupplyQuant;

    @FXML
    private Label TotslSupplyExp;

    @FXML
    private Label UnitPrice;

    @FXML
    private PieChart pieChart;
    @FXML
    void Back(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exp_for_supWindow.fxml")));
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
    void SupplyExpenses() {
        int id=Integer.parseInt(Id.getText());
        String name = null;
        int totalSupplyExpenses=0;
        int totalSupplyQuantity=0;
        int OneUnitPrice=0;
        for(int i = 0; i < LoginMenu.expForSups.size(); i++) {
            if(LoginMenu.expForSups.get(i).getTypeId()==id){
                totalSupplyExpenses=totalSupplyExpenses+(LoginMenu.expForSups.get(i).getPricePerUnit()*LoginMenu.expForSups.get(i).getTypeQuant());
                totalSupplyQuantity=totalSupplyQuantity+ LoginMenu.expForSups.get(i).getTypeQuant();
                OneUnitPrice= LoginMenu.expForSups.get(i).getPricePerUnit();

                 }
        }
        for(int i = 0; i < LoginMenu.supplies.size(); i++) {
            if(LoginMenu.supplies.get(i).getTypeId()==id){
               name = LoginMenu.supplies.get(i).getTypeName();

            }
        }
        TotslSupplyExp.setText("   Total Supplies Expenses: "+totalSupplyExpenses);
        TotalSupplyQuant.setText("   Total Supply Quantity: "+totalSupplyQuantity);
        UnitPrice.setText("   Unit Price: "+OneUnitPrice);
        SupplyName.setText("   Name: "+name);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int totalSuppliesExpenses=0;
        int Unitprice2;
        int Quantity;
        for(int i = 0; i < LoginMenu.expForSups.size(); i++) {
            totalSuppliesExpenses=totalSuppliesExpenses+(LoginMenu.expForSups.get(i).getPricePerUnit()*LoginMenu.expForSups.get(i).getTypeQuant());
             }

        totalSuppliesLabel.setText("   Total Supplies Expenses: "+ totalSuppliesExpenses);


       int[] listOfTotalExpenses=new int[LoginMenu.expForSups.size()];
       int[] listOfTotalquantities=new int[LoginMenu.expForSups.size()];
        for (int i = 0; i < LoginMenu.expForSups.size(); i++) {

            for (int j = i + 1; j < LoginMenu.expForSups.size(); j++) {

                if (LoginMenu.expForSups.get(j).getTypeId() < LoginMenu.expForSups.get(i).getTypeId()) {
                    LoginMenu.expForSups.set(i, LoginMenu.expForSups.set(j, LoginMenu.expForSups.get(i)));
                }
            }
        }
        for (int i = 0; i < LoginMenu.expForSups.size(); i++) {

            System.out.println(LoginMenu.expForSups.get(i));
        }

       int flag=0;
        System.out.println("--------------------------------");

        for(int i = 0; i < LoginMenu.expForSups.size(); i++) {
            Quantity= LoginMenu.expForSups.get(i).getTypeQuant();
            Unitprice2= LoginMenu.expForSups.get(i).getPricePerUnit();
            System.out.println("   "+Quantity+"   "+Unitprice2);
            for(int j = i+1; j < LoginMenu.expForSups.size(); j++) {
                if(LoginMenu.expForSups.get(i).getTypeId()== LoginMenu.expForSups.get(j).getTypeId()){
                    Quantity += LoginMenu.expForSups.get(j).getTypeQuant();

                    flag++;

                }
            }
            listOfTotalExpenses[i]=Quantity*Unitprice2;
            listOfTotalquantities[i]=Quantity;
            i+=flag;

            flag=0;

        }

        XYChart.Series<String,Number> series = new XYChart.Series<>();
        XYChart.Series<String,Number> series2 = new XYChart.Series<>();
        XYChart.Series<String,Number> series3 = new XYChart.Series<>();
        series.setName("Total Expenses");
        series2.setName("Quantity");
        series3.setName("Unit Price");
        for(int i = 0; i <listOfTotalExpenses.length; i++) {
            if(listOfTotalExpenses[i]!=0){
                series.getData().add(new XYChart.Data<>(String.valueOf(  LoginMenu.expForSups.get(i).getTypeId()),listOfTotalExpenses[i]));
                series2.getData().add(new XYChart.Data<>(String.valueOf(  LoginMenu.expForSups.get(i).getTypeId()),listOfTotalquantities[i]));
                series3.getData().add(new XYChart.Data<>(String.valueOf(  LoginMenu.expForSups.get(i).getTypeId()),LoginMenu.expForSups.get(i).getPricePerUnit() ));

            }
        }

        totalChart.getData().addAll(series,series2,series3);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList( );

        for(int i=0; i<listOfTotalExpenses.length; i++){
            if(listOfTotalExpenses[i]!=0){
                pieChartData.add(new PieChart.Data(String.valueOf(LoginMenu.expForSups.get(i).getTypeId()), listOfTotalExpenses[i]));
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

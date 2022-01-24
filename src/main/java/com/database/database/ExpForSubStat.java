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

public class ExpForSubStat implements Initializable {
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
    private NumberAxis EmpExpenses;
    @FXML
    private Label TotalSupplyQuant;

    @FXML
    private Label TotslSupplyExp;

    @FXML
    private Label UnitPrice;
    @FXML
    private CategoryAxis EmpIdChartAxis;


    @FXML
    private PieChart pieChart;
    @FXML
    void Back(ActionEvent event) {
        try {

            root = FXMLLoader.load(getClass().getResource("exp_for_supWindow.fxml"));
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
    void SupplyExpenses(ActionEvent event) {
        int id=Integer.parseInt(Id.getText());
        String name = null;
        int totalSupplyExpenses=0;
        int totalSupplyQuantity=0;
        int OneUnitPrice=0;
        for(int i = 0; i < exp_for_supWindow.list.size(); i++) {
            if(exp_for_supWindow.list.get(i).getTypeId()==id){
                totalSupplyExpenses=totalSupplyExpenses+(exp_for_supWindow.list.get(i).getPricePerUnit()*exp_for_supWindow.list.get(i).getTypeQuant());
                totalSupplyQuantity=totalSupplyQuantity+exp_for_supWindow.list.get(i).getTypeQuant();
                OneUnitPrice=exp_for_supWindow.list.get(i).getPricePerUnit();

                 }
        }
        for(int i = 0; i < SuppliesWindow.list.size(); i++) {
            if(SuppliesWindow.list.get(i).getTypeId()==id){
               name = SuppliesWindow.list.get(i).getTypeName();

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
        for(int i = 0; i < exp_for_supWindow.list.size(); i++) {
            totalSuppliesExpenses=totalSuppliesExpenses+(exp_for_supWindow.list.get(i).getPricePerUnit()*exp_for_supWindow.list.get(i).getTypeQuant());
             }

        totalSuppliesLabel.setText("   Total Supplies Expenses: "+String.valueOf(totalSuppliesExpenses));


       int[] listOfTotalExpenses=new int[exp_for_supWindow.list.size()];
       int[] listOfTotalquantities=new int[exp_for_supWindow.list.size()];
       int[] listOfTotalovertime=new int[exp_for_supWindow.list.size()];
       for (int i = 0; i < exp_for_supWindow.list.size(); i++) {

            for (int j = i + 1; j < exp_for_supWindow.list.size(); j++) {

                if (exp_for_supWindow.list.get(j).getTypeId() < exp_for_supWindow.list.get(i).getTypeId()) {
                    exp_for_supWindow.list.set(i, exp_for_supWindow.list.set(j, exp_for_supWindow.list.get(i)));
                }
            }
        }
        for (int i = 0; i < exp_for_supWindow.list.size(); i++) {

            System.out.println(exp_for_supWindow.list.get(i));
        }

       int flag=0;
        System.out.println("--------------------------------");

        for(int i = 0; i < exp_for_supWindow.list.size(); i++) {
            Quantity=exp_for_supWindow.list.get(i).getTypeQuant();
            Unitprice2=exp_for_supWindow.list.get(i).getPricePerUnit();
            System.out.println("   "+Quantity+"   "+Unitprice2);
            for(int j = i+1; j < exp_for_supWindow.list.size(); j++) {
                if(exp_for_supWindow.list.get(i).getTypeId()==exp_for_supWindow.list.get(j).getTypeId()){
                    Quantity+=exp_for_supWindow.list.get(j).getTypeQuant();

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
                series.getData().add(new XYChart.Data<>(String.valueOf(  exp_for_supWindow.list.get(i).getTypeId()),listOfTotalExpenses[i]));
                series2.getData().add(new XYChart.Data<>(String.valueOf(  exp_for_supWindow.list.get(i).getTypeId()),listOfTotalquantities[i]));
                series3.getData().add(new XYChart.Data<>(String.valueOf(  exp_for_supWindow.list.get(i).getTypeId()),exp_for_supWindow.list.get(i).getPricePerUnit() ));

            }
        }

        totalChart.getData().addAll(series,series2,series3);



        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList( );

        for(int i=0; i<listOfTotalExpenses.length; i++){
            if(listOfTotalExpenses[i]!=0){
                pieChartData.add(new PieChart.Data(String.valueOf(exp_for_supWindow.list.get(i).getTypeId()), listOfTotalExpenses[i]));
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

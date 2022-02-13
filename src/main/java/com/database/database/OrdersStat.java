package com.database.database;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrdersStat {
    @FXML
    private Label avg;
    @FXML
    void avgTime() {
        int count = 0;
        double totalTime = 0;
        double avgT;
        for(int i=0; i < LoginMenu.orders.size(); i++){
            totalTime += LoginMenu.orders.get(i).getElapsed_time();
            count ++;
        }

        avgT = totalTime / count;
        avg.setText(String.format("%.3f%n", avgT));
    }
}
package com.database.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrdersStat {
    @FXML
    private Label avg;
    @FXML
    void avgTime(ActionEvent event) {
        int count = 0;
        double totalTime = 0;
        double avgT;
        for(int i=0; i < CustomerOrdersWindow.list.size(); i++){
            totalTime += CustomerOrdersWindow.list.get(i).getElapsed_time();
            count ++;
        }

        avgT = totalTime / count;
        avg.setText(String.format("%.3f%n", avgT));
    }
}

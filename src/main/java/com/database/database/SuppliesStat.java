package com.database.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SuppliesStat {

    @FXML
    private ImageView BackIcon1;

    @FXML
    private ImageView BackIcon11;

    @FXML
    private Button Statistics;

    @FXML
    private Label totPricelabel;

    @FXML
    void totalPrice(ActionEvent event) {

        totPricelabel.setText("asd");
    }

}

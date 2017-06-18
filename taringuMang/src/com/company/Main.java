package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Eagle on 16.01.2015.
 */

public class Main extends Application {

    ArrayList<String> names = new ArrayList<String>();
    Stage stage;
    TextField name;
    GridPane grid;
    Button add;
    Button begin;

    /**
     * Antud meetod käivitab GUI applikatsiooni
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Siin luuakse mängule mängu alustamise aken
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("Mängijad");
        primaryStage.setScene(new Scene(playersGrid(), 350, 350));
        primaryStage.show();
    }

    GridPane playersGrid() {

        grid = new GridPane();
        //grid.setGridLinesVisible(true);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(10);
        name = new TextField();
        add = new Button("Lisa mängija");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!"".equals(name.getText())) {
                    names.add(name.getText());
                    grid.add(new Text(name.getText()), 0, names.size());
                    name.setText("");
                }
            }
        });
        begin = new Button("Alusta");
        begin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (names.size() > 0) {
                    stage.hide();
                    Game game = new Game();
                    game.start(stage, names);
                }
            }
        });
        grid.add(name, 0, 0);
        grid.add(add, 1, 0);
        grid.add(begin, 2, 0);
        return grid;
    }
}
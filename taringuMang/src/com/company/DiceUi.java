package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;

/**
 * Created by Eagle on 17.01.2015.
 */
public class DiceUi {

    CheckBox[] diceLocks;
    Canvas[] dicePics;
    Button rollButton;
    Button newGameButton;
    Text rerolls;
    Game game;

    public DiceUi(Game game) {
        this.game = game;
    }

    public GridPane dicePane(){
        diceLocks=new CheckBox[5];
        dicePics=new Canvas[5];
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setHgap(10);
        grid.setVgap(10);
        for(int i=0;i<5;i++){
            Canvas canvas=new Canvas(60,60);
            dicePics[i]=canvas;
            grid.add(canvas,i,0);
            CheckBox checkBox =new CheckBox("lukus");
            //checkBox.setAlignment(Pos.BASELINE_RIGHT);
            diceLocks[i]=checkBox;
            grid.add(checkBox,i,1);
        }
        grid.add(new Text("viskeid"),0,2);
        rerolls =new Text("3");
        grid.add(rerolls,1,2);
        rollButton=new Button("viska");
        rollButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game.rollClick();
            }
        });
        grid.add(rollButton, 0, 3);
        newGameButton=new Button("Alusta uut mängu");
        newGameButton.setDisable(true);
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game.startGame();
                newGameButton.setDisable(true);
                rollButton.setDisable(false);
            }
        });
        grid.add(newGameButton,0,20,3,1);
        //grid.setGridLinesVisible(true);
        return grid;
    }

    public void drawDice(){
        int size=7;
        int p1=10;
        int p2=25;
        int p3=40;
        int rect=57;
        for (int i = 0; i < 5; i++) {
            int value=game.dice.values[i];
            GraphicsContext gc = dicePics[i].getGraphicsContext2D();
            gc.clearRect(0,0,rect,rect);
            gc.strokeRoundRect(0, 0, rect, rect, 15, 15);
            if (value==1 || value==3 || value==5)
                gc.fillArc(p2,p2 , size, size, 0, 360, ArcType.ROUND);
            if (value==2 || value==4 || value==5 || value==6){
                gc.fillArc(p1, p1, size, size, 0, 360, ArcType.ROUND);
                gc.fillArc(p3,p3, size, size,0,360, ArcType.ROUND);
            }
            if (value==3 || value==4 || value==5 || value==6){
                gc.fillArc(p1,p3, size, size,0,360, ArcType.ROUND);
                gc.fillArc(p3,p1, size, size,0,360, ArcType.ROUND);
            }
            if (value==6) {
                gc.fillArc(p1, p2,  size, size, 0, 360, ArcType.ROUND);
                gc.fillArc(p3, p2,  size, size, 0, 360, ArcType.ROUND);
            }
        }
    }
}

package com.company;

import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import java.util.ArrayList;
/**
 * Created by Eagle on 16.01.2015.
 */
public class Game {

    ArrayList<Player> players=new ArrayList<Player>();
    Dice dice=new Dice();
    int turn;
    int player;
    ScoreUi scoreUi;
    DiceUi diceUi;

    public void start(Stage stage,ArrayList<String> names){

        for(int i=0;i<names.size();i++)
            players.add(new Player(names.get(i)));
        scoreUi = new ScoreUi(this);
        diceUi = new DiceUi(this);
        SplitPane root=new SplitPane();
        root.getItems().add(scoreUi.scorePane());
        root.getItems().add(diceUi.dicePane());
        stage.setScene(new Scene(root, 50 + 50 * names.size() + 400, 350));
        root.setDividerPosition(0,0.1*names.size()+ 0.1);
        stage.setTitle("Mäng");
        stage.show();
        startGame();
    }

    void endTurn(){
       scoreUi.endTurn(player);
        player=player+1;
        if (player>=players.size()){
            player=0;
            turn++;
        }
        if (turn>14){
            diceUi.rollButton.setDisable(true);
            diceUi.newGameButton.setDisable(false);
        }
    }

    void startTurn(){
        dice.clear();
        diceUi.rerolls.setText("" + dice.rerolls);
        for(int i=0;i<5;i++){
            diceUi.diceLocks[i].setSelected(false);
        }
        diceUi.drawDice();
        scoreUi.nameTexts[player].setUnderline(true);
    }

    void rollClick() {
        if (dice.rerolls>0) {
            for (int i = 0; i < 5; i++) {
                dice.locks[i] =  diceUi.diceLocks[i].isSelected();
            }
            dice.roll();
            diceUi.rerolls.setText("" + dice.rerolls);
            diceUi.drawDice();
            scoreUi.updateScores(player);
        }
    }

    void startGame(){
        turn=0;
        player=0;
        for(int p=0;p<players.size();p++){
            players.get(p).score=new Score();
        }
        startTurn();
    }
}
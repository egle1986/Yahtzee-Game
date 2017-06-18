package com.company;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Created by Eagle on 17.01.2015.
 */
public class ScoreUi {
   // GridPane grid;

    public Text[][] scoreNodes; //player,rida
    public Text[] nameTexts;
    Game game;

    ScoreUi(Game game){
        this.game=game;
    }

    public GridPane scorePane(){
        scoreNodes=new Text[game.players.size()][17];
        GridPane grid = new GridPane();
        //grid.setGridLinesVisible(true);
        grid.setPadding(new Insets(10,10,10,10));
        grid.setHgap(10);
        grid.setVgap(2);
        grid.add(new Text("ühed"),0,1);
        grid.add(new Text("kahed"),0,2);
        grid.add(new Text("kolmed"),0,3);
        grid.add(new Text("neljad"),0,4);
        grid.add(new Text("viied"),0,5);
        grid.add(new Text("kuued"),0,6);
        grid.add(new Text("boonus"),0,7);
        grid.add(new Text("1 paar"),0,8);
        grid.add(new Text("2 paari"),0,9);
        grid.add(new Text("täis maja"),0,10);
        grid.add(new Text("kolmik"),0,11);
        grid.add(new Text("nelik"),0,12);
        grid.add(new Text("v. rida"),0,13);
        grid.add(new Text("s. rida"),0,14);
        grid.add(new Text("summa"),0,15);
        grid.add(new Text("yazee"),0,16);
        grid.add(new Text("kokku"),0,17);
        nameTexts=new Text[game.players.size()];
        for(int p=0;p<game.players.size();p++){
            nameTexts[p]=new Text(game.players.get(p).name);
            grid.add(nameTexts[p],p+1,0);
            for (int i=0;i<17;i++){
                Text text=new Text(".....");
                text.setUserData(new ScoreData(i,p));
                scoreNodes[p][i]=text;
                grid.add(text,p+1,i+1);
                text.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Text node = (Text) mouseEvent.getTarget();
                        ScoreData data = (ScoreData) node.getUserData(); //!!!!!!
                        Score score=game.players.get(game.player).score;
                        if (data.player == game.player && score.numbers[data.line]==-1) {
                            int value = game.dice.calcScore(data.line);
                            score.numbers[data.line] = value;
                            node.setUnderline(false);
                            node.setCursor(Cursor.TEXT);
                            node.setFill(new Color(0, 0, 1, 1));
                            scoreNodes[game.player][6].setText("" + score.calcBonus());
                            scoreNodes[game.player][16].setText("" + score.calcTotal());
                            game.endTurn();
                            game.startTurn();
                        }
                    }
                });
            }
            scoreNodes[p][6].setText("0");
            scoreNodes[p][16].setText("0");
        }
        return grid;
    }

    void endTurn(int player) {
        for (int i = 0; i < 16; i++) {
            if (game.players.get(player).score.numbers[i] == -1) {
                Text text = scoreNodes[player][i];
                text.setText(".....");
                text.setUnderline(false);
                text.setCursor(Cursor.TEXT);
            }
        }
        nameTexts[player].setUnderline(false);
    }
    public void updateScores(int player){
        for(int i=0;i<16;i++){
            if (game.players.get(player).score.numbers[i]==-1) {
                Text text = scoreNodes[player][i];
                text.setText("" + game.dice.calcScore(i));
                text.setUnderline(true);
                text.setCursor(Cursor.HAND);
            }
        }
    }
}

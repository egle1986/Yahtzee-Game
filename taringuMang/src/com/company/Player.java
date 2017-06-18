package com.company;

/**
 * Created by Eagle on 16.01.2015.
 */
public class Player {
    public String name;
    public Score score;

    public Player(String name){
        this.name=name;
        score=new Score();
    }
}

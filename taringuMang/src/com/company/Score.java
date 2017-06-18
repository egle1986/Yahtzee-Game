package com.company;

/**
 * Created by Eagle on 16.01.2015.
 */
public class Score {
    public int[] numbers;

    public Score(){
        numbers=new int[17];
        for(int i=0;i<17;i++){
            numbers[i]=-1;
        }
        numbers[6]=0;//boonus
        numbers[16]=0;//kokku
    }

    public int calcBonus(){
        int sum=0;
        for(int i=0;i<6;i++){
            if (numbers[i]!=-1)
                sum=sum+ numbers[i];
        }
        if (sum>=63)
            numbers[6]=50;
        return numbers[6];
    }

    public int calcTotal(){
        int sum=0;
        for(int i=0;i<16;i++){
            if (numbers[i]!=-1)
                sum=sum+ numbers[i];
        }
        numbers[16]=sum;
        return numbers[16];
    }
}

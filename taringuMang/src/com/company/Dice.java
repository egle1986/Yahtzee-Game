package com.company;

/**
 * Created by Eagle on 16.01.2015.
 */
import java.util.Random;

/**
 * Antud klass kujutab endast täringu konstruktor, see mida õppejõud Pöial ütles, et on suure M'iga
 */

public class Dice {

    Random random=new Random();

    public int[] values=new int[5];
    public boolean[] locks=new boolean[5];
    public int rerolls;

    public void clear(){
        rerolls=3;
        for(int i=0;i<5;i++){
            locks[i]=false;
            values[i]=0;
        }
    }

    public void roll(){
        for(int i=0;i<5;i++){
            if (locks[i]==false || values[i]==0)
                values[i]=random.nextInt(6)+1;
        }
        rerolls=rerolls-1;
    }
    public int count(int n){
        int count=0;
        for(int i=0;i<5;i++)
            if (values[i]==n)
                count++;
        return count;
    }

    public int calcScore(int line){
        int sum=0;
        switch(line){
            case 0: //ühed
            case 1:
            case 2:
            case 3:
            case 4:
            case 5://kuued
                sum = count(line+1)*(line+1);
                break;
            case 7://paar
                for(int i=1;i<7;i++){
                    if (count(i)>1)
                        sum=2*i;
                }
                break;
            case 8://2 paari
                int pair1=0;
                int pair2=0;
                for(int i=1;i<7;i++){
                    if (count(i)>1)
                        pair1=i;
                }
                if (pair1>0) {
                    for(int i=1;i<pair1;i++){
                        if (count(i)>1)
                            pair2=i;
                    }
                }
                if (pair2>0)
                    sum=pair1 * 2 + pair2 * 2;
                break;
            case 9:// maja
                int same2=0;
                int same3=0;
                for(int i=1;i<7;i++){
                    if (count(i)==2)
                        same2=i;
                    if (count(i)==3)
                        same3=i;
                }
                if (same2>0 && same3>0)
                    sum=same2 * 2 + same3 * 3;
                break;
            case 10:// kolmik
                for(int i=1;i<7;i++){
                    if (count(i)==3)
                        sum=3*i;
                }
                break;
            case 11:// nelik
                for(int i=1;i<7;i++){
                    if (count(i)==4)
                        sum=4*i;
                }
                break;
            case 12:// v rida
                if (count(1)==1 && count(2)==1 && count(3)==1 && count(4)==1 && count(5)==1)
                    sum=15;
                break;
            case 13:// s rida
                if (count(2)==1 && count(3)==1 && count(4)==1 && count(5)==1 && count(6)==1)
                    sum=20;
                break;
            case 14:// sum
                for(int i=0;i<5;i++)
                    sum=sum+values[i];
                break;
            case 15:// yazee
                for(int i=1;i<7;i++){
                    if (count(i)==5)
                        sum=50;
                }
                break;
        }
        return sum;
    }
}

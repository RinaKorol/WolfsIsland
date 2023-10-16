package example;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Random;

public class Wolf {
    final int MAXSIZE = 20;
    private Integer point;
    private Integer X;
    private Integer Y;
    private String gender;
    private Integer countOld;

    public void plusCountOld(){
        this.countOld = countOld+1;
    }
    public Integer getCountOld(){return countOld;}
    public void setCountOld(Integer countOld){
        this.countOld = countOld;
    }

    public void setGender(String gender){this.gender = gender;}
    public String getGender(){return gender;}

    public void setX(Integer X){
        this.X=X;
    }
    public Integer getX(){
        return X;
    }

    public void setY(Integer Y){
        this.Y=Y;
    }
    public Integer getY(){
        return Y;
    }

    public void setPoint(Integer point ){
        this.point = point;
    }
    public void plusPoint(){
        this.point = point+10;
    }
    public Integer getPoint(){
        return point;
    }
    public Wolf (Integer X,Integer Y,String gender){
        setX(X);
        setY(Y);
        setGender(gender);
        setPoint(10);
        setCountOld(0);
    }

    public void diy(List<Label>[] island){
        island[X].get(Y).setGraphic(null);
        island[X].get(Y).setBackground(
                new Background(new BackgroundFill(Color.FORESTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public Wolf burn(){
        Random random = new Random();
        int gen = random.nextInt(2);
        int [] C=wolfBurn();
        int newX=C[0];
        int newY = C[1];
        String gender;
        if(gen==0) gender = "male";
        else gender = "female";
        Wolf wolf = new Wolf(newX,newY,gender);
        return wolf;
    }

    public int[] wolfBurn(){
        Random random = new Random();
        int move = random.nextInt(8);
        int newX = X; int newY = Y;
        if(move==1&&X>0&&Y>0){
            newX = X-1;
            newY = Y-1;
        }
        else if (move == 2&&Y>0){
            newY = Y-1;
        }
        else if (move == 3&&Y>0&&X<(MAXSIZE-1)){
            newX = X+1;
            newY = Y-1;
        }
        else if (move == 4&&X<(MAXSIZE-1)){
            newX = X+1;
        }
        else if (move == 5&&X<(MAXSIZE-1)&&Y < (MAXSIZE-1)){
            newX = X+1;
            newY = Y+1;
        }
        else if (move == 6 && Y < (MAXSIZE-1)){
            newY = Y+1;
        }
        else if (move == 7&& X>0 && Y<(MAXSIZE-1)){
            newX = X-1;
            newY = Y+1;
        }
        else if ( X>0){
            newX = X-1;
        }
        int [] C =new int[2];
        C[0]=newX; C[1]=newY;
        return C;
    }

    public void wolfMove(){
        Random random = new Random();
        int move = random.nextInt(9);
        if (move == 1 && X > 0 && Y > 0) {
            setX(X - 1);
            setY(Y - 1);
        } else if (move == 2 && Y > 0) {
            setY(Y - 1);
        } else if (move == 3 && Y > 0 && X < (MAXSIZE-1)) {
            setX(X + 1);
            setY(Y - 1);
        } else if (move == 4 && X < (MAXSIZE-1)) {
            setX(X + 1);
        } else if (move == 5 && X < (MAXSIZE-1) && Y < (MAXSIZE-1)) {
            setX(X + 1);
            setY(Y + 1);
        } else if (move == 6 && Y < (MAXSIZE-1)) {
            setY(Y + 1);
        } else if (move == 7 && X > 0 && Y < (MAXSIZE-1)) {
            setX(X - 1);
            setY(Y + 1);
        } else if (move == 8 && X > 0) {
            setX(X - 1);
        }
    }


}

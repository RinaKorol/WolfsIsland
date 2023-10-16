package example;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Random;

public class Rabbit {
    final int MAXSIZE = 20;
    private String kind = "R";
    private Integer X;
    private Integer Y;

    public Image getRabbitPic (){
        Image rabbitPic = new Image(getClass().getResourceAsStream("Rabbitj.jpeg"));
        return rabbitPic;
    }

    public String getKind(){
        return kind;
    }

    public void setX(Integer X) {
        this.X = X;
    }

    public Integer getX() {
        return X;
    }

    public void setY(Integer Y) {
        this.Y = Y;
    }

    public Integer getY() {
        return Y;
    }

    public Rabbit(Integer X, Integer Y) {
        setX(X);
        setY(Y);
    }

    public void rabbitMove() {
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

    public Rabbit burn(List<Label>[] island){
        Random random = new Random();
        int burn = random.nextInt(10);
        if(burn == 3||burn == 8){
        int [] C=rabbitBurn();
        int newX=C[0];
        int newY = C[1];
        Rabbit rabbit = new Rabbit(newX,newY);
        //String check = island[rabbit.getX()].get(rabbit.getY()).getText();
        /*while (island[rabbit.getX()].get(rabbit.getY()).getGraphic() != null){
            C=rabbitBurn();
            newX=C[0];
            newY = C[1];
            rabbit.setX(newX);
            rabbit.setY(newY);
            //rabbit = new Rabbit(newX,newY);
        }*/
        return rabbit;}
        return null;
    }

    public int[] rabbitBurn(){
        Random random = new Random();
        //int burn = random.nextInt(10);
        int move = random.nextInt(8);
        int newX = X; int newY = Y;
        //if(burn == 3 || burn == 8){
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
        //}
        int [] C =new int[2];
        C[0]=newX; C[1]=newY;
       return C;
    }

    public void diy(List<Label>[] island){
        island[X].get(Y).setGraphic(null);
        island[X].get(Y).setBackground(
                new Background(new BackgroundFill(Color.FORESTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        //island[rabbit.getX()].remove(rabbit.getY());
    }

}

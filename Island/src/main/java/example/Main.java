package example;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.text.Element;
import java.beans.EventHandler;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main extends Application {

    private final Integer SIZE = 20;
    private  List<Label>[] island = new List[SIZE];

    private final ArrayList<Label> islandLine = new ArrayList<>();
    private final Image rabbitPic = new Image("apter2.jpeg");
    private final Image wolfPic = new Image("wolf.jpeg");
    private final ArrayList<Rabbit> rabbits = new ArrayList<>();
    private final ArrayList<Wolf> wolves = new ArrayList<>();
    private  int isFirstStep = 0;

    private void addRabbit(int  number){
        for(int i =0;i<number;++i){
        Random random = new Random();
        int X=random.nextInt(SIZE);
        int Y = random.nextInt(SIZE);
        Rabbit rabbit = new Rabbit(X,Y);
        String check = island[rabbit.getX()].get(rabbit.getY()).getText();      //!check.equals("")
        while (island[rabbit.getX()].get(rabbit.getY()).getGraphic() != null){
            //if(island[rabbit.getX()].get(rabbit.getY()).getBackground().equals(new Background(new BackgroundFill(Color.FORESTGREEN, CornerRadii.EMPTY, Insets.EMPTY))))
           X=random.nextInt(SIZE);
           Y = random.nextInt(SIZE);
           rabbit.setX(X);
           rabbit.setY(Y);
           //rabbit = new Rabbit(X,Y);
           check = island[rabbit.getX()].get(rabbit.getY()).getText();
        }
        rabbits.add(rabbit);
     }
    }

    private void addWolf(int number){
        for(int i =0;i<number;++i) {
            Random random = new Random();
            int X = random.nextInt(SIZE);
            int Y = random.nextInt(SIZE);
            String gender;
            if(i%2==0) gender = "male";
            else gender = "female";
            Wolf wolf = new Wolf(X, Y,gender);
            while (island[wolf.getX()].get(wolf.getY()).getGraphic() != null) {
                //if(island[rabbit.getX()].get(rabbit.getY()).getBackground().equals(new Background(new BackgroundFill(Color.FORESTGREEN, CornerRadii.EMPTY, Insets.EMPTY))))
                X = random.nextInt(SIZE);
                Y = random.nextInt(SIZE);
                wolf.setX(X);
                wolf.setY(Y);
                //rabbit = new Rabbit(X,Y);
            }
            wolves.add(wolf);
        }
    }

    private void showWolf(Wolf wolf){
        Label changed = island[wolf.getX()].get(wolf.getY());
        changed.setGraphic(new ImageView(wolfPic));
        if(wolf.getGender().equals("male"))
            changed.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        else changed.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private Rabbit getRabbit(int index){
        Rabbit val =rabbits.get(index);
        return val;
    }

    private Wolf getWolf(int index){
        Wolf wolf = wolves.get(index);
        return wolf;
    }

    private boolean isRabbit(Label place){
        return place.getBackground().equals(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private int[] nearRabbit(Integer wolfX,Integer wolfY){
        int [] C =new int[2];
        C[0]=-1; C[1]=-1;
        if(wolfX>0&&wolfY>0&&isRabbit(island[wolfX-1].get(wolfY-1))){
            C[0]=wolfX-1; C[1]=wolfY-1;}
        else if(wolfY>0&&isRabbit(island[wolfX].get(wolfY-1))){
            C[0]=wolfX; C[1]=wolfY-1;}
        else if (wolfY>0&&wolfX<(SIZE-1)&&isRabbit(island[wolfX+1].get(wolfY-1))){
            C[0]=wolfX+1; C[1]=wolfY-1;}
        else if(wolfX<(SIZE-1)&&isRabbit(island[wolfX+1].get(wolfY))){
            C[0]=wolfX+1; C[1]=wolfY;}
        else if(wolfX<(SIZE-1)&&wolfY<(SIZE-1)&&isRabbit(island[wolfX+1].get(wolfY+1))){
            C[0]=wolfX+1; C[1]=wolfY+1;}
        else if(wolfY<(SIZE-1)&&isRabbit(island[wolfX].get(wolfY+1))){
            C[0]=wolfX; C[1]=wolfY+1;}
        else if(wolfY<(SIZE-1)&&wolfX>0&&isRabbit(island[wolfX-1].get(wolfY+1))){
            C[0]=wolfX-1; C[1]=wolfY+1;}
        else if(wolfX>0&&isRabbit(island[wolfX-1].get(wolfY))){
            C[0]=wolfX-1; C[1]=wolfY;}
        return C;
    }

    private boolean isWolfess(Label place){
        return place.getBackground().equals(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private boolean nearWolfess(Wolf wolf){
        Integer wolfX = wolf.getX();
        Integer wolfY = wolf.getY();
        boolean ans = false;
        if(wolfX>0&&wolfY>0&&isWolfess(island[wolfX-1].get(wolfY-1))){
            ans = true;}
        else if(wolfY>0&&isWolfess(island[wolfX].get(wolfY-1))){
            ans= true;}
        else if (wolfY>0&&wolfX<(SIZE-1)&&isWolfess(island[wolfX+1].get(wolfY-1))){
            ans = true;}
        else if(wolfX<(SIZE-1)&&isWolfess(island[wolfX+1].get(wolfY))){
            ans = true;}
        else if(wolfX<(SIZE-1)&&wolfY<(SIZE-1)&&isWolfess(island[wolfX+1].get(wolfY+1))){
           ans = true;}
        else if(wolfY<(SIZE-1)&&isWolfess(island[wolfX].get(wolfY+1))){
            ans = true;}
        else if(wolfY<(SIZE-1)&&wolfX>0&&isWolfess(island[wolfX-1].get(wolfY+1))){
            ans = true;}
        else if(wolfX>0&&isWolfess(island[wolfX-1].get(wolfY))){
            ans = true;}
        return ans;
    }

    private void buttonHandler(){
        int wolfCount = wolves.size();
        //System.out.println(wolfCount);
        for(int i =0;i<wolfCount;++i){
            island[getWolf(i).getX()].get(getWolf(i).getY()).setGraphic(null);
            island[getWolf(i).getX()].get(getWolf(i).getY()).setBackground(new Background(new BackgroundFill(Color.FORESTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            int[] C = nearRabbit(getWolf(i).getX(), getWolf(i).getY());
            if (C[0] == -1 && C[1] == -1) {
                if(getWolf(i).getGender().equals("male")&&getWolf(i).getCountOld()>1 && nearWolfess(getWolf(i))){
                    wolves.add(getWolf(i).burn());
                    //System.out.println("yes");
                    getWolf(i).setCountOld(0);
                }
                    if (getWolf(i).getCountOld() <= 1)
                        getWolf(i).plusCountOld();
                    int oldWX=getWolf(i).getX();
                    int oldWY = getWolf(i).getY();
                    getWolf(i).wolfMove();
                    while (island[getWolf(i).getX()].get(getWolf(i).getY()).getGraphic() != null) {
                        getWolf(i).setX(oldWX);
                        getWolf(i).setY(oldWY);
                        getWolf(i).wolfMove();
                    }
                    island[getWolf(i).getX()].get(getWolf(i).getY()).setBackground(
                            new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

            } else {
                getWolf(i).setX(C[0]);
                getWolf(i).setY(C[1]);
                getWolf(i).plusPoint();  //замениить на плюс 1.0
                for (int j = 0; j < rabbits.size(); ++j) {
                    if (getRabbit(j).getX() == C[0] && getRabbit(j).getY() == C[1]) {
                        getRabbit(j).diy(island);
                        rabbits.remove(j);
                    }
                }
            }
            getWolf(i).setPoint(getWolf(i).getPoint() - 1);
            showWolf(getWolf(i));
        }
        for(int i=0;i<wolves.size();++i){
            //System.out.println("Wolf "+i+" "+getWolf(i).getPoint());
            if(getWolf(i).getPoint()<=0){
                getWolf(i).diy(island);
                wolves.remove(i);
                i=i-1;
            }

        }
        //System.out.println();

        int rabCount = rabbits.size();
        for(int i =0;i<rabCount;++i) {
            //island[getRabbit(i).getX()].get(getRabbit(i).getY()).setText("");
            island[getRabbit(i).getX()].get(getRabbit(i).getY()).setGraphic(null);
            island[getRabbit(i).getX()].get(getRabbit(i).getY()).setBackground(new Background(new BackgroundFill(Color.FORESTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            getRabbit(i).rabbitMove();
            while (island[getRabbit(i).getX()].get(getRabbit(i).getY()).getGraphic() != null){
                getRabbit(i).rabbitMove();
            }
            island[getRabbit(i).getX()].get(getRabbit(i).getY()).setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            showRabbit(getRabbit(i));
            if (isFirstStep!=0) {
                Rabbit littleRa = getRabbit(i).burn(island);
                if(littleRa!=null)
                    rabbits.add(littleRa);
            }
        }

        isFirstStep = 1;
    }


    private void showRabbit(Rabbit rabbit){
        Label changed = island[rabbit.getX()].get(rabbit.getY());
        changed.setGraphic(new ImageView(rabbitPic));
        changed.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        //changed.setText(rabbit.getKind());
    }

    private Button createButton(){
        Button but = new Button("Step");
        but.setOnAction(event -> {
            buttonHandler();
        });
        return but;
    }

    private void exHandler(Button read){
        System.out.println("Wrong number");
        read.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Animals");

        BorderPane borderPane = new BorderPane();

        VBox box = new VBox();
        Label rab = new Label("Rabbits:");
        TextField rabbit = new TextField();
        Label wol = new Label("Wolves");
        TextField wolf = new TextField();
        Button read = new Button("Input");
        read.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        read.setOnAction(event -> {
            try {
                Integer numberRab = Integer.parseInt(rabbit.getText());
                Integer numberWol = Integer.parseInt(wolf.getText());
                if (numberRab < 0 || numberWol < 0) {
                    System.out.println("Number must be >0");
                    read.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                } else {
                    addRabbit(numberRab);
                    addWolf(numberWol);
                }
            } catch (NumberFormatException e){
                exHandler(read);
            }
        });
        box.getChildren().addAll(rab,rabbit,wol,wolf, read);

        borderPane.setLeft(createButton());
        borderPane.setPadding(new Insets(25, 25, 25, 25));
        borderPane.setRight(box);

        IslandView isView = new IslandView();
        borderPane.setCenter(isView.getPane());
        island = isView.getIsland();


        Scene scene = new Scene(borderPane, 1300, 700);
        scene.setFill(Color.AQUA);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[]args){
        launch();
    }


}

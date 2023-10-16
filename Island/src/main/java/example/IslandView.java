package example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class IslandView {
    private final Integer SIZE = 20;
    private  List<Label>[] island = new List[SIZE];
    private GridPane grid;


private void createPane(){
    grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setPadding(new Insets(25, 25, 25, 25));
    initIsland();
    createIsland(grid);

}

    public GridPane getPane() {
        return grid;
    }

    public List<Label>[] getIsland(){
    return island;
    }

    public IslandView(){        //List<Label>[] island
        createPane();
    }


    private void initIsland() {
        for (int i = 0; i < SIZE; ++i) {
            island[i] = new ArrayList<>();
        }
        fillIsland();
    }

    private void fillIsland(){

        for(int i =0;i<SIZE;++i)
            for(int j=0;j<SIZE;++j){
                Label mylabel=new Label();
                island[i].add(mylabel);
                mylabel.setPrefHeight(30);
                mylabel.setPrefWidth(50);
                mylabel.setBackground(new Background(new BackgroundFill(Color.FORESTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                mylabel.setTextFill(Color.WHITE);
                mylabel.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                mylabel.setAlignment(Pos.CENTER);

            }
    }

    private void createIsland(GridPane root){
        for(int i =0;i<SIZE;++i)
            for(int j=0;j<SIZE;++j){
                root.add(island[i].get(j),i,j);
            }
    }
}
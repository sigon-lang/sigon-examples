package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int CELL_SIZE = 50;


    int SIZE = 10;
    int length = SIZE;
    int width = SIZE;

    int rowIndex = 0;
    int columnIndex = 0;


    @Override
    public void start(Stage primaryStage) {

        GridPane root = new GridPane();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setHgap(2);
        root.setVgap(2);
        root.getStyleClass().addAll("game-root");

        addChildrens(rowIndex, columnIndex, root);


        Scene scene = new Scene(root, 550, 550);
        scene.getStylesheets().add("sample/game.css");


        primaryStage.setTitle("Exemplo 1");
        primaryStage.setScene(scene);
        primaryStage.show();


        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.RIGHT)){
                moveFront(root);
            } else if(keyEvent.getCode().equals(KeyCode.UP)){
                moveUp(root);
            }

        });


    }

    private void moveUp(GridPane root){
        if(rowIndex < SIZE -1){
            System.out.println("Move up");
            rowIndex++;
            addChildrens(rowIndex, columnIndex, root);
        }
    }

    private void moveFront(GridPane root){
        if(columnIndex < SIZE -1) {
            System.out.println("Move front");
            columnIndex++;
            addChildrens(rowIndex, columnIndex, root);
        }
    }

    private void addChildrens(int rowIndex, int columnIndex, GridPane root) {
        for(int y = 0; y < length; y++){
            for(int x = 0; x < width; x++) {


                Button button = new Button();

                button.setPrefHeight(50);
                button.setPrefWidth(50);
                button.setAlignment(Pos.CENTER);




                if (y == rowIndex && x == columnIndex) {
                    button.setText("A");
                    button.getStyleClass().addAll("game-button");
                } else {
                    button.setText("");
                    button.getStyleClass().addAll("game-button-active");
                }


                root.setRowIndex(button,y);
                root.setColumnIndex(button,x);
                root.getChildren().add(button);
            }
        }
    }


    public static void main(String[] args) {
        launch(args);



    }
}
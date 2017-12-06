package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    int SIZE = 10;
    int length = SIZE;
    int width = SIZE;

    int rowIndex = 0;
    int columnIndex = 0;


    @Override
    public void start(Stage primaryStage) {

        GridPane root = new GridPane();


        addChildrens(rowIndex, columnIndex, root);


        Scene scene = new Scene(root, 500, 500);
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
        System.out.println("Move up");
        rowIndex++;
        addChildrens(rowIndex, columnIndex, root);
    }

    private void moveFront(GridPane root){
        System.out.println("Move front");
        columnIndex++;
        addChildrens(rowIndex, columnIndex, root);
    }

    private void addChildrens(int rowIndex, int columnIndex, GridPane root) {
        for(int y = 0; y < length; y++){
            for(int x = 0; x < width; x++) {


                TextField tf = new TextField();
                tf.setPrefHeight(50);
                tf.setPrefWidth(50);
                tf.setAlignment(Pos.CENTER);
                tf.setEditable(false);


                if (y == rowIndex && x == columnIndex) {
                    tf.setText("A");
                } else {
                    tf.setText("");
                }


                root.setRowIndex(tf,y);
                root.setColumnIndex(tf,x);
                root.getChildren().add(tf);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);



    }
}
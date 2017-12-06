package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) {

        int SIZE = 10;
        int length = SIZE;
        int width = SIZE;

        GridPane root = new GridPane();

        for(int y = 0; y < length; y++){
            for(int x = 0; x < width; x++){

                TextField tf = new TextField();
                tf.setPrefHeight(50);
                tf.setPrefWidth(50);
                tf.setAlignment(Pos.CENTER);
                tf.setEditable(false);


                if(y == 0 && x == 0){
                    tf.setText("A");
                } else {
                    tf.setText("");
                }


                root.setRowIndex(tf,y);
                root.setColumnIndex(tf,x);
                root.getChildren().add(tf);
            }
        }

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Exemplo 1");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {

            if(keyEvent.getCode().equals(KeyCode.RIGHT)){
                System.out.println("Move front");
            } else if(keyEvent.getCode().equals(KeyCode.UP)){
                System.out.println("Move up");
            }


        });






    }

    public static void main(String[] args) {
        launch(args);



    }
}
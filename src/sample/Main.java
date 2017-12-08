package sample;

import agent.AgentLexer;
import agent.AgentParser;
import br.ufsc.ine.agent.Agent;
import br.ufsc.ine.parser.AgentWalker;
import br.ufsc.ine.parser.VerboseListener;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import sensor.PositionSensor;

import java.io.IOException;

public class Main extends Application {

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

    private static void startAgent(){
        try {

            CharStream stream = CharStreams.fromFileName("/home/valdir/Documents/agent-app/src/sample/agent");
            AgentLexer lexer = new AgentLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            AgentParser parser = new AgentParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new VerboseListener());

            ParseTree tree = parser.agent();
            ParseTreeWalker walker = new ParseTreeWalker();

            AgentWalker agentWalker = new AgentWalker();
            walker.walk(agentWalker, tree);

            Agent agent = new Agent();
            agent.run(agentWalker);




        } catch (IOException e) {
            System.out.println("I/O exception.");
        }
    }

    private void moveUp(GridPane root){
        if(rowIndex < SIZE -1){

            try {
                Thread.sleep(1000l);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            rowIndex++;
             
            addChildrens(rowIndex, columnIndex, root);

            String content = "position("+(columnIndex+1)+","+(rowIndex+1) +").";
            PositionSensor.positionObservable.onNext(content);
        }
    }

    private void moveFront(GridPane root){
        if(columnIndex < SIZE -1) {

            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            columnIndex++;
            addChildrens(rowIndex, columnIndex, root);

            String content = "position("+(columnIndex+1)+","+(rowIndex+1) +").";
            PositionSensor.positionObservable.onNext(content);
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

    public  static  void startEnvironment(){
        launch();
    }

    public static void main(String[] args) {

        Thread agentThread = new Thread(){
            @Override
            public void run(){
                startAgent();
            }
        };

        agentThread.start();

        Thread environmentThread = new Thread(){
            @Override
            public void run() {
            Main.startEnvironment();
            }
        };

        environmentThread.start();



    }
}
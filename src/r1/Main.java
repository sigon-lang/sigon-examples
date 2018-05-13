package r1;

import java.io.File;
import java.io.IOException;


import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {

    static GridPane root;

    static int SIZE = 10;
    static int length = SIZE;
    static int width = SIZE;

    public static  int rowIndex = 0;
    public static  int columnIndex = 0;

    @Override
    public void start(Stage primaryStage) {
        root = new GridPane();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setHgap(2);
        root.setVgap(2);
        root.getStyleClass().addAll("game-root");

        addChildrens(rowIndex, columnIndex, root);

        Scene scene = new Scene(root, 550, 550);
        scene.getStylesheets().add("r1/game.css");

        primaryStage.setTitle("R1");
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    private static void startAgent(){
        try {

            File agentFile = new File("r1.on");
            CharStream stream = CharStreams.fromFileName(agentFile.getAbsolutePath());
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




    private static void addChildrens(int rowIndex, int columnIndex, GridPane root) {



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

    public static void main(String[] args) { startAgent();
       startEnvironment();
      // startAgent();

    }




}
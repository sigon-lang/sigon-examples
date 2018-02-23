package actuator;


import br.ufsc.ine.agent.context.communication.Actuator;
import javafx.application.Platform;
import sample.Main;

import java.awt.*;

import java.util.List;

public class MoveUp extends Actuator {
    Robot robot;

    public void act(List<String> args) {



        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Main.moveUp();
            }
        });




    }
}

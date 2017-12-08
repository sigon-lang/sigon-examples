package actuator;

import br.ufsc.ine.actuator.Actuator;
import javafx.application.Platform;
import sample.Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class MoveFront  extends Actuator{

    public void act(List<String> list) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Main.moveFront();
            }
        });

    }
}

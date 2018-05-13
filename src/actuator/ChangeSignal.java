package actuator;


import br.ufsc.ine.agent.context.communication.Actuator;
import javafx.application.Platform;
import r1.Main;

import java.util.List;

public class ChangeSignal extends Actuator {

    public void act(List<String> list) {



        Platform.runLater(new Runnable() {
            @Override
            public void run() {


            }
        });

    }
}

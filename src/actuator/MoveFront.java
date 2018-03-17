package actuator;


import br.ufsc.ine.agent.context.beliefs.BeliefsContextService;
import br.ufsc.ine.agent.context.communication.Actuator;
import javafx.application.Platform;
import sample.Main;

import java.util.List;

public class MoveFront  extends Actuator {

    public void act(List<String> list) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println(BeliefsContextService.getInstance().getTheory());
                Main.moveFront();
            }
        });

    }
}

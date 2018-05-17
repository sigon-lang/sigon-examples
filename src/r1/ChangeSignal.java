package r1;


import java.util.List;

import br.ufsc.ine.agent.context.communication.Actuator;

public class ChangeSignal extends Actuator {

    public void act(List<String> list) {
    	System.out.println("ChangeSignal");
    }
}

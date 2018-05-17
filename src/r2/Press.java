package r2;


import java.util.List;

import br.ufsc.ine.agent.context.communication.Actuator;

public class Press extends Actuator {

    public void act(List<String> list) {
    	System.out.println("Press game button");
    }
}

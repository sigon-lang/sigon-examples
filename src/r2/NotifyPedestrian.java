package r2;

import br.ufsc.ine.agent.context.communication.Actuator;

import java.util.List;

public class NotifyPedestrian  extends Actuator {
    @Override
    public void act(List<String> list) {
    	System.out.println("NotifyPedestrian");
    }
}

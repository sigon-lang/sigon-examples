package r1;

import br.ufsc.ine.agent.context.communication.Actuator;

import java.util.List;

public class NotifyPedestrian  extends Actuator {
    @Override
    public void act(List<String> list) {
    	System.out.println("NotifyPedestrian");
    }
};

public class TextNotification  extends NotifyPedestrian {
    @Override
    public void act(List<String> list) {
    	System.out.println("NotifyPedestrian");
    }
}

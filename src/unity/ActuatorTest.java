package unity;

import br.ufsc.ine.agent.context.communication.Actuator;

import java.util.List;

public class ActuatorTest extends Actuator {
    @Override
    public void act(List<String> list) {
        System.out.println("Hello!");
    }
}

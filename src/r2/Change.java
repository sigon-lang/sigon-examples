package r2;

import java.util.List;

import br.ufsc.ine.agent.context.communication.Actuator;

public class Change extends Actuator {

	public void act(List<String> list) {
		System.out.println("Change lane");
		Main.changeLane();
	}
}

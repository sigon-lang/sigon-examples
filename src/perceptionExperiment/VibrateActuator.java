package perceptionExperiment;

import java.util.List;

import br.ufsc.ine.agent.context.communication.Actuator;

public class VibrateActuator extends Actuator{

	@Override
	public void act(List<String> args) {
		System.out.println("vibrate Actuator");

		
	}

}

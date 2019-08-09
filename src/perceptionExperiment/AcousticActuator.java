package perceptionExperiment;

import java.util.List;

import br.ufsc.ine.agent.context.communication.Actuator;

public class AcousticActuator extends Actuator{

	@Override
	public void act(List<String> args) {
		System.out.println("acoustic Actuator ");
		
	}

}

package perceptionExperiment;

import java.util.List;

import br.ufsc.ine.agent.context.communication.Actuator;

public class BlockActuator extends Actuator{

	@Override
	public void act(List<String> args) {
		// TODO Auto-generated method stub
		System.out.println(args.toString());
		
	}

}

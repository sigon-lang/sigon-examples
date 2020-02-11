package perceptionExperiment;

import java.util.List;

import br.ufsc.ine.agent.context.communication.Actuator;

public class NotifyActuator extends Actuator{
	private int counterMsg = 0;

	@Override
	public void act(List<String> args) {
		System.out.println("tESTE");

		
		MainFinal.setNotify(args);
		
		
	}
	
	
	

}

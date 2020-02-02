package perceptionExperiment;

import java.util.List;

import br.ufsc.ine.agent.context.communication.Actuator;
import jason.stdlib.foreach;

public class ActuatorExperiment extends Actuator{

	@Override
	public void act(List<String> args) {
		// TODO Auto-generated method stub
		System.out.println("teste Plan");
		for (String string : args) {
			System.out.println(string);
		}
		Main.setValue("actuatorExperiment("+args.get(0)+")");
		
	}
	

}

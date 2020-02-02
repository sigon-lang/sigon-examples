package perceptionExperiment;

import br.ufsc.ine.agent.context.communication.Sensor;
import rx.subjects.PublishSubject;

public class CommunicationSensor extends Sensor{
	public static final PublishSubject<String> approachingCar = PublishSubject.create(); //sensor irá receber uma msg contendo a interação com o carro


	@Override
	public void run() {
		// TODO Auto-generated method stub
		approachingCar.subscribe(super.publisher);

		
	}
	

}

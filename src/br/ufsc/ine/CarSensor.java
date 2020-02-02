package perceptionExperiment;

import br.ufsc.ine.agent.context.communication.Sensor;
import rx.subjects.PublishSubject;

public class CarSensor extends Sensor{
	public static final PublishSubject<String> position = PublishSubject.create();
	public static final PublishSubject<String> color = PublishSubject.create();
	public static final PublishSubject<String> license = PublishSubject.create();
	public static final PublishSubject<String> model = PublishSubject.create();
	public static final PublishSubject<String> sound = PublishSubject.create();


	@Override
	public void run() {
		position.subscribe(super.publisher);
		color.subscribe(super.publisher);
		sound.subscribe(super.publisher);
		model.subscribe(super.publisher);
		license.subscribe(super.publisher);
		
	}

}

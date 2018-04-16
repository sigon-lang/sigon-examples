package sensor;

import br.ufsc.ine.agent.context.communication.Sensor;
import rx.subjects.PublishSubject;

public class PositionSensor extends Sensor {

    public static final PublishSubject<String> positionObservable = PublishSubject.create();


    public void run() {
       /// positionObservable.subscribe(super.publisher);
    	while (true) {
			 // observar arq
    		publisher.onNext("p(10).");
    		publisher.onNext("p(11).");
    		publisher.onNext("p(12).");
    		publisher.onNext("p(13).");
			
		}

    }
}

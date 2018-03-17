package stress;

import br.ufsc.ine.agent.context.communication.Sensor;
import rx.subjects.PublishSubject;

public class StressSensor extends Sensor {

    public static final PublishSubject<String> publish = PublishSubject.create();


    public void run() {
        publish.subscribe(super.publisher);

    }
}

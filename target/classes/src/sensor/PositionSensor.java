package sensor;

import br.ufsc.ine.sensor.Sensor;
import rx.subjects.PublishSubject;

public class PositionSensor extends Sensor {

    public static final PublishSubject<String> positionObservable = PublishSubject.create();


    public void run() {
        positionObservable.subscribe(super.publisher);

    }
}

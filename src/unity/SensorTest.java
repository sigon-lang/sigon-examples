package unity;

import br.ufsc.ine.agent.context.communication.Sensor;

public class SensorTest extends Sensor {
    @Override
    public void run() {
        while (true){
            super.publisher.onNext("test.");
        }
    }
}

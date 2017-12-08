package actuator;

import br.ufsc.ine.actuator.Actuator;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class MoveUp extends Actuator {

    public void act(List<String> args) {

        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        r.keyPress(KeyEvent.VK_UP);
        r.keyRelease(KeyEvent.VK_UP);
    }
}

package actuator;

import br.ufsc.ine.actuator.Actuator;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class MoveFront  extends Actuator{

    public void act(List<String> list) {
        System.out.println("move front");



        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        r.keyPress(KeyEvent.VK_RIGHT);
        r.keyRelease(KeyEvent.VK_RIGHT);
    }
}

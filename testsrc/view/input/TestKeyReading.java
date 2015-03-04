package view.input;

import org.junit.Test;
import view.InputListener;

import java.awt.event.KeyEvent;
import java.util.Timer;

/**
 * Created by Cregnacht on 2015-03-03.
 */
public class TestKeyReading
{
    // TODO: FIX TEST TestKeyReading
    @Test
    public void main()
    {
        int keycode;

        while (true)
        {
            keycode = InputListener.getInstance().getKeycode();
            if (keycode == KeyEvent.VK_ESCAPE) break;
            if (keycode != -0x01) System.out.println("Keycode Entered: " + keycode);
        }
    }
}

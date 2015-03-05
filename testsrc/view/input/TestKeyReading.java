package view.input;

import enums.GameState;
import org.junit.Test;
import sample.InputTester;
import view.InputListener;

import java.awt.event.KeyEvent;
import java.util.Timer;

/**
 * Created by Cregnacht on 2015-03-03.
 */
public class TestKeyReading
{
    @Test
    public void main()
    {
        double previousTime = System.currentTimeMillis();

        InputTester inputTester = new InputTester();

        while (true)
        {
            double currentTime = System.currentTimeMillis();
            double elapsedTime = currentTime - previousTime;

            if (elapsedTime <= 1000) continue;

            previousTime = currentTime;

            System.out.println(inputTester.getKeyCode());
        }
    }
}

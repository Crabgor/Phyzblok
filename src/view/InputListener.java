package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Cregnacht on 2015-03-03.
 */
public class InputListener implements KeyListener
{
    private int keycode = 0x00;
    public int getKeycode() { return keycode; }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link java.awt.event.KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e)
    {

    }


    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link java.awt.event.KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        keycode = e.getKeyCode();
    }


    /**
     * Invoked when a key has been released.
     * See the class description for {@link java.awt.event.KeyEvent} for a definition of
     * a key released event.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}

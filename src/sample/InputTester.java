package sample;

import view.InputListener;

import javax.swing.*;

/**
 * Created by Cregnacht on 2015-03-05.
 */
public class InputTester
{
    private JFrame frame;
    private InputListener inputListener;

    public int getKeyCode()
    {
        return inputListener.getKeycode();
    }

    public InputTester()
    {
        frame = new JFrame();
        inputListener = InputListener.getInstance();
        frame.addKeyListener(inputListener);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setSize(200, 200);
        frame.setVisible(true);
        frame.validate();
    }
}

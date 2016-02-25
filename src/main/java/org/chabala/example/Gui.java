package org.chabala.example;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;

/**
 * Example Swing GUI.
 * <p>
 * Inspired by <a href="http://www.java2s.com/Code/Java/Swing-JFC/GUIinvokedfromtheeventdispatchingthread.htm">
 *                      http://www.java2s.com/Code/Java/Swing-JFC/GUIinvokedfromtheeventdispatchingthread.htm</a>
 *
 * @author Greg Chabala
 * @since Sep 29, 2010
 */
public class Gui {

    private static int count = 1;
    private static JFrame lastFrame = null;

    public static void main(String[] args) {
        new Gui();
    }

    public Gui() {
        SwingUtilities.invokeLater(this::showFrame);
    }

    private void showFrame() {
        JFrame frame = new JFrame("Beeper " + count++);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setLayout(new FlowLayout());
        frame.add(new JLabel("Hello World"));
        frame.add(new BeepingButton("Click Me"));

        frame.pack();
        if (lastFrame == null) {
            frame.setLocationByPlatform(true);
        } else {
            Point p = lastFrame.getLocation();
            p.translate(50, 50);
            frame.setLocation(p);
        }
        lastFrame = frame;
        frame.setVisible(true);
    }

    private static class BeepingButton extends JButton {
        BeepingButton(final String text) {
            super(text);
            setPreferredSize(new Dimension(140, 60));
            addActionListener((e) -> {
                Tone.sound(2000, 150);
                new Gui();
            });
        }
    }
}

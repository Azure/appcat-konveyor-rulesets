import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PreJava8KeyStrokeExample {
    public static void main(String[] args) {
        JButton button = new JButton("Click or Press Enter");
        JFrame frame = new JFrame("Pre-Java 8 KeyStroke Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        InputMap inputMap = button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = button.getActionMap();

        // Manual check and creation of AWTKeyStroke
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        if (!(keyStroke instanceof AWTKeyStroke)) {
            keyStroke = AWTKeyStroke.getAWTKeyStrokeForEvent(new KeyEvent(button, KeyEvent.KEY_PRESSED, 
                                            System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n'));
        }

        inputMap.put(keyStroke, "clickAction");

        actionMap.put("clickAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button triggered via ENTER key");
            }
        });

        frame.add(button);
        frame.setSize(300, 100);
        frame.setVisible(true);
    }
}

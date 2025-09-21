package classes;

import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import interfaces.KeyReader;

public class KeyReaderImpl implements KeyReader {

    // Thread-safe storage
    private final AtomicReference<KeyEvent> lastKey = new AtomicReference<>();
    private final AtomicBoolean hasNewKey = new AtomicBoolean(false);

    public KeyReaderImpl() {
        // Create a hidden JFrame to capture key presses
        JFrame frame = new JFrame();
        frame.setSize(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true); // Hide window border
        frame.setOpacity(0f);       // Make fully transparent
        frame.setVisible(true);

        // Listen to key events
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                lastKey.set(e);
                hasNewKey.set(true);
            }
        });

        // Request focus so the frame receives key events
        SwingUtilities.invokeLater(frame::requestFocusInWindow);
    }

    @Override
    public KeyEvent lastInput() {
        // Reset the new key flag once the key is retrieved
        hasNewKey.set(false);
        return lastKey.get();
    }

    @Override
    public Boolean newKey() {
        return hasNewKey.get();
    }

    // Test main method
    public static void main(String[] args) throws InterruptedException {
        KeyReader reader = new KeyReaderImpl();

        System.out.println("Press keys, program will notify if a new key was pressed...");

        while (true) {
            if (reader.newKey()) {
                System.out.println("New key detected: " + reader.lastInput());
            }
            Thread.sleep(200);
        }
    }
}

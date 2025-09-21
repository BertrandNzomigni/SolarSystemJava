package maincode;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;

import classes.SpacePanel1;
import classes.CameraImpl;
import classes.CircleFactory1;
import classes.SolarSystem1;

import interfaces.Camera;
import interfaces.CelestialObject;
import interfaces.Displayable;
import interfaces.SolarSystem;
import interfaces.KeyReader;
import interfaces.NameFactory;
import interfaces.NameManager;
import interfaces.CircleFactory;
import classes.KeyReaderImpl;
import classes.NameFactory1;
import classes.NameManager1;

public class Simulation {

    static Camera camera;
    static SpacePanel1 panel;

    public static void main(String[] args) {
        // 1️⃣ Initialize solar system
        SolarSystem system = new SolarSystem1();

        // 2️⃣ Initialize camera
        camera = new CameraImpl(system, 600, 600);
        camera.follow(system.getObjects().get(1));

        // 3️⃣ Initialize panel
        CircleFactory circleFactory = new CircleFactory1(system,camera);
        NameFactory nameFactory = new NameFactory1(camera,30);
        panel = new SpacePanel1();

        NameManager nameManager = new NameManager1(nameFactory,panel);
        nameManager.toggleName(system.getObjects().get(0));
        nameManager.toggleName(system.getObjects().get(1));
        // 4️⃣ Setup JFrame
        JFrame mainFrame = new JFrame("Solar System Simulation");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 600);
        mainFrame.add(panel);
        mainFrame.setVisible(true);

        // 5️⃣ Keyboard input
        KeyReader keyReader = new KeyReaderImpl();

        // 6️⃣ Start simulation Timer (~60 FPS)
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update simulation
                system.apply_forces();
                system.accelerate();
                system.move();

                // Handle keyboard input
                if (keyReader.newKey()) {
                    KeyEvent key = keyReader.lastInput();
                    switch (key.getKeyCode()) {
                        case KeyEvent.VK_X: camera.zoom(1.2); break;   // zoom in
                        case KeyEvent.VK_C: camera.zoom(1/1.2); break; // zoom out
                        case KeyEvent.VK_F:                               // follow first planet for example
                            List<CelestialObject> objects = system.getObjects();
                            if (!objects.isEmpty()) camera.follow(objects.get(0));
                            break;
                        case KeyEvent.VK_P: camera.follow(null); break;   // stop following
                        case KeyEvent.VK_LEFT: camera.moveX(10); break;
                        case KeyEvent.VK_RIGHT: camera.moveX(-10); break;
                        case KeyEvent.VK_UP: camera.moveY(10); break;
                        case KeyEvent.VK_DOWN: camera.moveY(-10); break;
                    }
                }

                // Update display
                panel.clear();
                List<Displayable> displayables = new ArrayList<Displayable>();
                displayables.addAll(circleFactory.createDisplayables());
                panel.addNewDisplayables(displayables,Long.valueOf(0));
                nameManager.addNames();
                panel.repaint();
            }
        });

        timer.start(); // Start the loop
    }
}

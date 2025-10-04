package maincode;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import classes.SpacePanel1;
import classes.TrajectoriesGenerator1;
import classes.TrajectoriesManager1;
import classes.CameraImpl;
import classes.CircleFactory1;
import classes.FutureStateGeneratorImpl;
import classes.SolarSystem1;
import classes.NavigationMenu1;

import interfaces.Camera;
import interfaces.CelestialObject;
import interfaces.Displayable;
import interfaces.FutureStateGenerator;
import interfaces.SolarSystem;
import interfaces.TrajectoriesGenerator;
import interfaces.TrajectoriesManager;
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
    static TrajectoriesManager trajmanag;
    static CircleFactory circleFactory;
    static KeyReader keyReader;
    static SolarSystem system;
    static NameManager nameManager;
    static Timer timer;


    public static void main(String[] args) {
        construct();

        // 6️⃣ Start simulation Timer (~60 FPS)
        init_timer();

        timer.start(); // Start the loop
    }

    public static void construct(){
        // 1️⃣ Initialize solar system
        system = new SolarSystem1();

        // 2️⃣ Initialize camera
        camera = new CameraImpl(600, 600);
        camera.follow(null);

        // 3️⃣ Initialize panel
        circleFactory = new CircleFactory1(system,camera);
        NameFactory nameFactory = new NameFactory1(camera,30);
        panel = new SpacePanel1();

        FutureStateGenerator futureGen = new FutureStateGeneratorImpl();
        TrajectoriesGenerator trajectoriesGenerator = new TrajectoriesGenerator1(system,futureGen,camera);
        trajmanag = new TrajectoriesManager1(trajectoriesGenerator, panel);
        trajmanag.setInterval(0);
        trajmanag.setSteps(Math.pow(10, 4) * 4);

        nameManager = new NameManager1(nameFactory,panel);
        nameManager.toggleName(system.getObjectByName("Terre"));
        nameManager.toggleName(system.getObjectByName("Lune"));
        // 4️⃣ Setup JFrame
        JFrame mainFrame = new JFrame("Solar System Simulation");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 600);
        mainFrame.add(panel);
        mainFrame.setVisible(true);

        // 5️⃣ Keyboard input
        keyReader = new KeyReaderImpl();        
    }

    public static void init_timer(){
        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update simulation
                system.advanceToNewState();
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
                panel.addNewDisplayables(displayables,Long.valueOf(1));
                
                trajmanag.evaluate();

                nameManager.addNames();

                panel.addNewDisplayable((Displayable)new NavigationMenu1(600,600,system.getObjects()),Long.valueOf(3));
                panel.repaint();
            }
        });
    }
}

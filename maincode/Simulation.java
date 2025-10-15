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
import classes.KeyReaderImpl;
import classes.NameFactory1;
import classes.NameManager1;

import interfaces.Camera;
import interfaces.Displayable;
import interfaces.FutureStateGenerator;
import interfaces.SolarSystem;
import interfaces.TrajectoriesGenerator;
import interfaces.TrajectoriesManager;
import interfaces.KeyReader;
import interfaces.NameFactory;
import interfaces.NameManager;
import interfaces.CircleFactory;
import interfaces.NavigationMenu;


public class Simulation {

    static Camera camera;
    static SpacePanel1 panel;
    static TrajectoriesManager trajmanag;
    static CircleFactory circleFactory;
    static KeyReader keyReader;
    static SolarSystem system;
    static NameManager nameManager;
    static Timer timer;
    static List<Displayable> menus;


    public static void main(String[] args) {
        initialize_simulation();

        // 6️⃣ Start simulation Timer (~60 FPS)
        init_timer();

        timer.start(); // Start the loop
    }

    public static void initialize_simulation(){
        // 1️⃣ Initialize solar system
        system = new SolarSystem1();

        // 2️⃣ Initialize camera
        camera = new CameraImpl(600, 600);
        camera.follow(system.getObjectByName("Lune"));

        // 3️⃣ Initialize panel
        circleFactory = new CircleFactory1(system,camera);
        
        panel = new SpacePanel1();

        menus = new ArrayList<Displayable>();

        setup_name_generation_and_enable_every_planet_name_display();

        setup_future_trajectory_generation();

        create_frame_configure_frame_add_panel_to_frame_show_frame();
        
        keyReader = new KeyReaderImpl(panel);
    }

    public static void init_timer(){
        panel.setFocusable(true);
        panel.requestFocusInWindow(); // so it can receive key input

        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update simulation
                system.advanceToNewState();
                // Handle keyboard input
                if (keyReader.newKey()) {
                    KeyEvent key = keyReader.lastInput();
                    switch (key.getKeyCode()) {
                        case KeyEvent.VK_X: camera.change_zoom_rate_by_factor_multiplication(1.2); break;   // zoom in
                        case KeyEvent.VK_C: camera.change_zoom_rate_by_factor_multiplication(1/1.2); break; // zoom out
                        case KeyEvent.VK_F:                               // follow first planet for example
                            if (navigationMenuPresent()){
                                removeNavigationMenu();
                            }
                            else{
                                menus.add(new NavigationMenu1(600,600,system.getObjects(),camera));
                            }
                            break;
                        case KeyEvent.VK_P: camera.follow(null); break;   // stop following
                        case KeyEvent.VK_LEFT: camera.move_on_x_axis(10); break;
                        case KeyEvent.VK_RIGHT: camera.move_on_x_axis(-10); break;
                        case KeyEvent.VK_UP: camera.move_on_y_axis(10); break;
                        case KeyEvent.VK_DOWN: camera.move_on_y_axis(-10); break;
                    }
                }

                update_panel_content();

            }
        });
    }

    public static void setup_future_trajectory_generation(){
        FutureStateGenerator futureGen = new FutureStateGeneratorImpl();
        TrajectoriesGenerator trajectoriesGenerator = new TrajectoriesGenerator1(system,futureGen,camera);
        trajmanag = new TrajectoriesManager1(trajectoriesGenerator, panel);
        trajmanag.setInterval(0);
        trajmanag.setSteps(Math.pow(10, 4) * 4);
    }


    public static void setup_name_generation_and_enable_every_planet_name_display(){
        NameFactory nameFactory = new NameFactory1(camera,30);
        nameManager = new NameManager1(nameFactory,panel);
        nameManager.toggleName(system.getObjectByName("Terre"));
        nameManager.toggleName(system.getObjectByName("Lune"));
    }

    public static void create_frame_configure_frame_add_panel_to_frame_show_frame(){
        JFrame mainFrame = new JFrame("Solar System Simulation");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 600);
        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }

    public static void update_panel_content(){
        panel.clear();
        List<Displayable> displayables = new ArrayList<Displayable>();

        displayables.addAll(circleFactory.showPlanets());
        panel.addNewDisplayables(displayables,Long.valueOf(1));
        
        trajmanag.drawTrajectoriesOrReduceIterator();

        nameManager.writeNames();
        NavigationMenu nav = new NavigationMenu1(600,600,system.getObjects(),camera);

        for (Displayable menu : menus){
            panel.addNewDisplayable(menu,Long.valueOf(3));
        }

        panel.repaint();
    }

    public static boolean navigationMenuPresent() {
        for (Displayable menu : menus) {
            if (menu instanceof NavigationMenu) {
                return true;
            }
        }
        return false;
    }
    public static void removeNavigationMenu() {
        menus.removeIf(menu -> menu instanceof NavigationMenu);
    }
}






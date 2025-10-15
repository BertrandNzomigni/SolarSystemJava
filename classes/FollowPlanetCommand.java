package classes;

import interfaces.Command;
import interfaces.Camera;
import interfaces.CelestialObject;

public class FollowPlanetCommand implements Command {
    private Camera camera;
    private CelestialObject target;

    public FollowPlanetCommand(Camera camera, CelestialObject target) {
        this.camera = camera;
        this.target = target;
    }

    @Override
    public void execute() {
        camera.follow(target);
    }
}

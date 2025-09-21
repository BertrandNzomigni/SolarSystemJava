package classes;

import java.awt.Point;

import interfaces.Camera;
import interfaces.CelestialObject;
import interfaces.NameFactory;

public class NameFactory1 implements NameFactory{
    Camera camera;
    int textSize;
    public NameFactory1(Camera _camera,int _textSize){
        camera = _camera;
        textSize = _textSize;
    }
    @Override
    public Text createName(CelestialObject object, int pixels) {
        Point point = camera.worldToScreen(object.getLocation());
        return new Text(object.getName(),(int) point.getX(),(int) point.getY() + pixels,20);
    }
}

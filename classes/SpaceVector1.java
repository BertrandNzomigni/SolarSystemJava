package classes;

import interfaces.SpaceVector;

public class SpaceVector1 implements SpaceVector{
    private Double x;
    private Double y;


    public SpaceVector1(){
        x = 0.0;
        y = 0.0;
    }   

    public SpaceVector1(Double magnitude,Double direction){
        x = Math.cos(direction) * magnitude;
        y = Math.sin(direction) * magnitude;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    private void changeX(Double _x){
        x = _x;
    }

    private void changeY(Double _y){
        y = _y;
    }
    @Override
    public SpaceVector summation(SpaceVector vector2) {
        SpaceVector1 sum = new SpaceVector1();
        sum.changeX(x + vector2.getX());
        sum.changeY(y + vector2.getY());
        
        return sum;


    }

    public SpaceVector multiplication(Double number){
        SpaceVector1 product = new SpaceVector1();
        product.changeX(x * number);
        product.changeY(y *  number);
        
        return product;

    }
    
}

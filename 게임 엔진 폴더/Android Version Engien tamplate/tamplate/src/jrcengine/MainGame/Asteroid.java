
package jrcengine.MainGame;

import jrcengine.Interface.Object_Dynamic;
import jrcengine.Math.Math_Vector;

public class Asteroid extends Object_Dynamic {

    public static final float ASTEROID_RADIOUS = 4.0f;

    private float fLenthX;

    private float fLenthY;

    private boolean isOne;

    private Math_Vector vcMovePoint;

    public Asteroid(float center_x, float center_y) {
        super(center_x, center_y, Asteroid.ASTEROID_RADIOUS);
        this.vcMovePoint = new Math_Vector();
        this.isOne = true;
    }

    public void update(float deltaTime) {

     //   fLenthX = getVcMovePoint().getX() - getPosition().getX();
     //   fLenthY = getVcMovePoint().getY() - getPosition().getY();

        getAccel().set(fLenthX * deltaTime, fLenthY * deltaTime);

        getPosition().add(getAccel());
        getRadious_bounds().getCenter().set(getPosition());

        stateTime += deltaTime;

    }

    public Math_Vector getVcMovePoint() {
        return vcMovePoint;
    }

    public void setVcMovePoint(float x, float y) {
        if (isOne) {
            this.vcMovePoint.set(x, y);
            fLenthX = getVcMovePoint().getX() - getPosition().getX();
            fLenthY = getVcMovePoint().getY() - getPosition().getY();
            
            this.isOne = false;
        }
    }

}

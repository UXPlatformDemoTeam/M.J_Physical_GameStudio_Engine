package jrcengine.MainGame;

import jrcengine.Interface.Object_Dynamic;
import jrcengine.Math.Math_Vector;

public class Player extends Object_Dynamic {

    public static final float PLAYER_RADIOUS = 4.0f;
    
    private float fLenthX;
    private float fLenthY;
    
    private Math_Vector vcMovePoint;
    
    public Player(float center_x, float center_y) {
        super(center_x, center_y, Player.PLAYER_RADIOUS);
        vcMovePoint = new Math_Vector();
    }
    
    public void update(float deltaTime) {
        
        fLenthX = getVcMovePoint().getX() - getPosition().getX();
        fLenthY = getVcMovePoint().getY() - getPosition().getY();
        

        getAccel().set(fLenthX * deltaTime, fLenthY * deltaTime);
        getPosition().add(getAccel());
        getRadious_bounds().getCenter().set(getPosition());
        
        stateTime += deltaTime;
        
    }

    public Math_Vector getVcMovePoint() {
        return vcMovePoint;
    }

    public void setVcMovePoint(float x, float y) {
        this.vcMovePoint.set(x, y);
    }
    
    

}

package jrcengine.Interface;

import jrcengine.Math.Math_Vector;

public abstract class Object_Dynamic extends Object_Normal{
    private Math_Vector velocity;

    private final Math_Vector accel;

    public static final int OBJ_D_DEATH = 0;

    public static final int OBJ_D_LEFT = 1;

    public static final int OBJ_D_RIGHT = 2;

    public static final int OBJ_D_STOP = 3;

    public static final int OBJ_D_ATTAK = 4;

    protected int state_Flag;

    public Object_Dynamic(int image_flag, float center_x, float center_y, float width, float height) {
        super(image_flag, center_x, center_y, width, height);
        velocity = new Math_Vector();
        accel = new Math_Vector();
    }

    public Object_Dynamic(float center_x, float center_y, float width, float height,
            int... image_flags) {
        super(center_x, center_y, width, height, image_flags);
        velocity = new Math_Vector();
        accel = new Math_Vector();
    }

    public Object_Dynamic(float center_x, float center_y,float radious) {
        super(center_x, center_y, radious);
        velocity = new Math_Vector();
        accel = new Math_Vector();
    }

    public int getStageFlag() {
        return state_Flag;
    }

    public void setStateFlag(int input) {
        state_Flag = input;
    }

    public Math_Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Math_Vector velocity) {
        this.velocity = velocity;
    }

    public Math_Vector getAccel() {
        return accel;
    }

    abstract public void update(float deltaTime);
    
}

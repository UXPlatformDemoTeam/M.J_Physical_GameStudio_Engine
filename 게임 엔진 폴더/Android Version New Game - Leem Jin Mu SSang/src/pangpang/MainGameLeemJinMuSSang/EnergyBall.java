package pangpang.MainGameLeemJinMuSSang;

import jrcengine.Interface.Object_Dynamic;
import jrcengine.Math.Math_Vector;

public class EnergyBall extends Object_Dynamic {
	
	public static final int BASICDAMAGE = 24;
	public static final int Energy_Ball_WIDTH = 3;
	public static final int Energy_Ball_HEIHGT = 3;
	public static final float ALLY_MONKMAN_VELOCITY = 80f;
	
	public static final int ENERGYBALL = 1;
	public static final int ARROW = 2;
	
	private final float liveTime = 3f;
	private int rotateNumber;
	private boolean isDead;
	int type;

	public EnergyBall(float x, float y,int type) {
		super(x, y, Energy_Ball_WIDTH, Energy_Ball_HEIHGT);
		this.velocity = new Math_Vector(ALLY_MONKMAN_VELOCITY, 0);
		this.rotateNumber = 0;
		this.isDead = false;
		this.type = type;
	}

	public void update(float deltaTime) {

		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);

		stateTime += deltaTime;
		rotateNumber++;
		
		if(stateTime>liveTime)
			isDead = true;

		if (rotateNumber > 15)
			rotateNumber = 0;

	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	int get_Rotate_Number() {
		return this.rotateNumber;
	}

}

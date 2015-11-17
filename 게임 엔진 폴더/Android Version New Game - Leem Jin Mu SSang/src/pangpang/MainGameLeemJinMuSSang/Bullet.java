package pangpang.MainGameLeemJinMuSSang;

import jrcengine.Interface.Object_Dynamic;
import jrcengine.Math.Math_Vector;

public class Bullet extends Object_Dynamic {
	
	public static final int BASICDAMAGE = 24;
	public static final int Star_Missile_WIDTH = 3;
	public static final int Star_Missile_HEIHGT = 3;
	public static final float ENEMY_GUNMAN_VELOCITY = 80f;
	
	private final float liveTime = 3f;
	private int rotateNumber;
	private boolean isDead;

	public Bullet(float x, float y) {
		super(x, y, Star_Missile_WIDTH, Star_Missile_HEIHGT);
		this.velocity = new Math_Vector(-ENEMY_GUNMAN_VELOCITY, 0);
		this.rotateNumber = 0;
		this.isDead = false;
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

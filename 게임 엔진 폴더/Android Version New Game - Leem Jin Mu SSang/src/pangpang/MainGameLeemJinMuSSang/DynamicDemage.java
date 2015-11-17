package pangpang.MainGameLeemJinMuSSang;

import jrcengine.Interface.Object_Dynamic;

public class DynamicDemage extends Object_Dynamic{

	public static final float ENEMY_GUNMAN_WIDTH = 50f;
	public static final float ENEMY_GUNMAN_HEIGHT = 50f;

	private int Demage;
	private float speed;
	
	public DynamicDemage(float center_x, float center_y, int demage, float speed) {
		super(center_x, center_y,ENEMY_GUNMAN_WIDTH,ENEMY_GUNMAN_HEIGHT,0);
		this.setDemage(demage);
		this.setSpeed(speed);
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getDemage() {
		return Demage;
	}

	public void setDemage(int demage) {
		Demage = demage;
	}

	public void update(float deltaTime) {

		velocity.set(this.getSpeed(), 0);
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		stateTime += deltaTime;
		bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
	}


}

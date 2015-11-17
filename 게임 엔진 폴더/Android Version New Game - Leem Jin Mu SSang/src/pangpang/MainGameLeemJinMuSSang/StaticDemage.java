package pangpang.MainGameLeemJinMuSSang;

import jrcengine.Interface.Object_Normal;

public class StaticDemage extends Object_Normal{
	
	public static final float ENEMY_GUNMAN_WIDTH = 50f;
	public static final float ENEMY_GUNMAN_HEIGHT = 50f;
	public static final float ENEMY_GUNMAN_VELOCITY = 40f;

	int Demage;

	public StaticDemage(float center_x, float center_y ,int demage) {
		super(center_x, center_y, ENEMY_GUNMAN_WIDTH, ENEMY_GUNMAN_HEIGHT, 0);
		this.setDemage(demage);
	}
	
	public int getDemage() {
		return Demage;
	}

	public void setDemage(int demage) {
		Demage = demage;
	}
	
	public void update(float deltaTime) {

		stateTime += deltaTime;
		bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
	}


}

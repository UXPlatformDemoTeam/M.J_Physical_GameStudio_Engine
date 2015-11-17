package pangpang.MainGameLeemJinMuSSang;

import jrcengine.Interface.Object_Dynamic;

public class LeeSunSin extends Object_Dynamic {

	public static final float LEESUNSIN_WIDTH = 430f;
	public static final float LEESUNSIN_HEIGHT = 130f;
	public static final float LEESUNSIN_VELOCITY = 120f;
	public static final int LEESUNSHIN_HP = 300;

	public static final int OBJ_D_SKILL01 = 5;
	public static final int OBJ_D_SKILL02 = 6;
	public static final int OBJ_D_SKILL03 = 7;
	public static final int OBJ_D_SKILL04 = 8;
	public static final int OBJ_D_SKILL05 = 9;
	public static final int OBJ_D_SKILL06 = 10;
	
	private HpBar HpBar;
	private int nHealthPoint;
	private int nFullHealThPoint;

	private final float GAPHPBAR = 20f;

	public int getnHealthPoint() {
		return nHealthPoint;
	}

	public void setnHealthPoint(int nHealthPoint) {
		this.nHealthPoint = nHealthPoint;
	}

	public HpBar getHpBar() {
		return HpBar;
	}

	public void setHpBar(HpBar HpBar) {
		this.HpBar = HpBar;
	}

	public int getnFullHealThPoint() {
		return nFullHealThPoint;
	}

	public void setnFullHealThPoint(int nFullHealThPoint) {
		this.nFullHealThPoint = nFullHealThPoint;
	}

	public LeeSunSin(int image_flag, float center_x, float center_y,
			int nHealthPoint) {
		super(image_flag, center_x, center_y, LEESUNSIN_WIDTH/4-30, LEESUNSIN_HEIGHT);
		this.HpBar = new HpBar(center_x, center_y);
		this.setnHealthPoint(nHealthPoint);
		this.setnFullHealThPoint(nHealthPoint);

		MainGame_Manager.hpBar.add(this.HpBar);
	}

	public void update(float deltaTime) {

		// 움직이는 모션에 대한 정의

		if (this.getStageFlag() == OBJ_D_RIGHT
				&& this.position.x <= Screen_MainMenu.GAME_WIDTH
						* Screen_MainGame.nMultipleNumberSizeOfMap
						- LEESUNSIN_WIDTH / 2) {
			velocity.set(+LEESUNSIN_VELOCITY, 0);
		} else if (this.getStageFlag() == OBJ_D_LEFT
				&& this.position.x >= LEESUNSIN_WIDTH / 2) {
			velocity.set(-LEESUNSIN_VELOCITY, 0);
		} else if (this.getStageFlag() == OBJ_D_STOP
				|| this.position.x <= Screen_MainMenu.GAME_WIDTH
						* Screen_MainGame.nMultipleNumberSizeOfMap
						- LEESUNSIN_WIDTH / 2
				|| this.position.x >= LEESUNSIN_WIDTH / 2) {
			velocity.set(0, 0);
		}

		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		stateTime += deltaTime;
		bounds.lowerLeft.set(position).sub(bounds.width / 4, bounds.height / 2);

		this.getHpBar().update(position.x+7, position.y, GAPHPBAR,
				this.getnHealthPoint(), this.getnFullHealThPoint(), deltaTime);
	}

}

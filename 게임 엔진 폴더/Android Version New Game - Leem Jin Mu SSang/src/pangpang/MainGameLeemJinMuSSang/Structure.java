package pangpang.MainGameLeemJinMuSSang;

import jrcengine.Interface.Object_Normal;

public class Structure extends Object_Normal {

	static public float STRUCTURE100 = 1.0f;
	static public float STRUCTURE80 = 0.8f;
	static public float STRUCTURE60 = 0.6f;
	static public float STRUCTURE40 = 0.4f;
	static public float STRUCTURE20 = 0.2f;
	static public float STRUCTURE00 = 0.0f;
	
	private HpBar HPbar;
	int nHealthPoint;
	int nFullHealThPoint;

	public int getnFullHealThPoint() {
		return nFullHealThPoint;
	}

	public void setnFullHealThPoint(int nFullHealThPoint) {
		this.nFullHealThPoint = nFullHealThPoint;
	}

	public int getnHealthPoint() {
		return nHealthPoint;
	}

	public void setnHealthPoint(int nHealthPoint) {
		this.nHealthPoint = nHealthPoint;
	}

	public HpBar getHPbar() {
		return HPbar;
	}

	public void setHPbar(HpBar hPbar) {
		HPbar = hPbar;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	boolean isDead;

	public Structure(float center_x, float center_y, float width, float height,
			int healthPoint) {
		super(center_x, center_y, width, height);

		this.setnFullHealThPoint(healthPoint);
		this.setnHealthPoint(healthPoint);
		this.HPbar = new HpBar(center_x, center_y);
	}

}

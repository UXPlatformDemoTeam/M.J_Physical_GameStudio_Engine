package pangpang.MainGameLeemJinMuSSang;

import jrcengine.Interface.Object_Dynamic;

public abstract class Ally extends Object_Dynamic {

	private int nHealthPoint;
	private int nFullHealThPoint;
	private boolean isCheckExistEnemyIntheScope;
	private HpBar allyHpBar;

	public Ally(float center_x, float center_y, float width, float height,
			int nHealthPoint) {
		super(center_x, center_y, width, height);
		// TODO Auto-generated constructor stub
		this.isCheckExistEnemyIntheScope = false;
		this.allyHpBar = new HpBar(center_x, center_y);
		this.setnHealthPoint(nHealthPoint);
		this.setnFullHealThPoint(nHealthPoint);
		
		MainGame_Manager.hpBar.add(this.allyHpBar);
	}

	public int getnHealthPoint() {
		return nHealthPoint;
	}

	public void setnHealthPoint(int nHealthPoint) {
		this.nHealthPoint = nHealthPoint;
	}

	public int getnFullHealThPoint() {
		return nFullHealThPoint;
	}

	public void setnFullHealThPoint(int nFullHealThPoint) {
		this.nFullHealThPoint = nFullHealThPoint;
	}

	public boolean isCheckExistEnemyIntheScope() {
		return isCheckExistEnemyIntheScope;
	}

	public void setCheckExistEnemyIntheScope(boolean isCheckExistEnemyIntheScope) {
		this.isCheckExistEnemyIntheScope = isCheckExistEnemyIntheScope;
	}

	public HpBar getAllyHpBar() {
		return allyHpBar;
	}

	public void setAllyHpBar(HpBar allyHpBar) {
		this.allyHpBar = allyHpBar;
	}
	
	abstract int skill01();

	abstract int skill02();

	abstract int skill03();

}

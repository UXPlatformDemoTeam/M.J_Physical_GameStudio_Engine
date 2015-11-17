package pangpang.MainGameLeemJinMuSSang;

import jrcengine.Interface.Object_Dynamic;

public abstract class Enemy extends Object_Dynamic {

	private int nHealthPoint;
	private int nFullHealThPoint;
	private boolean isCheckExistEnemyIntheScope;
	private HpBar enemyHpBar;
	
	public Enemy(float center_x, float center_y, float width, float height,
			int nHealthPoint) {
		super(center_x, center_y, width, height);
		this.isCheckExistEnemyIntheScope = false;
		this.enemyHpBar = new HpBar(center_x, center_y);
		this.setnHealthPoint(nHealthPoint);
		this.setnFullHealThPoint(nHealthPoint);

		MainGame_Manager.hpBar.add(this.enemyHpBar);

	}

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

	public HpBar getEnemyHpBar() {
		return enemyHpBar;
	}

	public void setEnemyHpBar(HpBar enemyHpBar) {
		this.enemyHpBar = enemyHpBar;
	}


	public boolean isCheckExistEnemyIntheScope() {
		return isCheckExistEnemyIntheScope;
	}

	public void setCheckExistEnemyIntheScope(boolean isCheckExistEnemyIntheScope) {
		this.isCheckExistEnemyIntheScope = isCheckExistEnemyIntheScope;
	}

	abstract int skill01();

	abstract int skill02();

	abstract int skill03();

}

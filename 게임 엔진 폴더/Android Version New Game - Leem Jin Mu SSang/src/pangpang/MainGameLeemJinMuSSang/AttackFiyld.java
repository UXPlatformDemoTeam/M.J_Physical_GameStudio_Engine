package pangpang.MainGameLeemJinMuSSang;

import jrcengine.Interface.Object_Normal;

public class AttackFiyld extends Object_Normal {

	public static final int ATTACK_FIYLD_WIDTH = 3;
	public static final int ATTACK_FIYLD_HEIHGT = 300;
	public static final float MAXLIVETIME = 0.5f;
	private boolean isDead;
	private int nDamage;
	private float liveTime;
	private int nSkillType;
	
	public int getnSkillType() {
		return nSkillType;
	}

	public void setnSkillType(int nSkillType) {
		this.nSkillType = nSkillType;
	}

	public static final int DEFUALT = 0;
	public static final int TORNADOATTACK =1;
	public static final int POWERATTACK = 2;
	public static final int BASICATTACK = 3;
	public static final int MIDDLEBOSSATTACK01 = 4;
	public static final int MIDDLEBOSSATTACK02 = 5;
	public static final int SPEARMAN = 6;
	public static final int FINALBOSSATTACK = 7;
	
	public static final int BASICDAMAGE = 60;
	public static final int TORNADOATTACKDAMAGE =160;
	public static final int POWERATTACKDAMAGE = 70;
	public static final int BASICATTACKDAMAGE = 50;
	public static final int MIDDLEBOSSATTACKDAMAGE01 = 100;
	public static final int MIDDLEBOSSATTACKDAMAGE02 = 140;
	

	public int getnDamage() {
		return nDamage;
	}

	public void setnDamage(int nDamage) {
		this.nDamage = nDamage;
	}
	
	public AttackFiyld(float center_x, float center_y) {
		super(center_x, center_y, ATTACK_FIYLD_WIDTH, ATTACK_FIYLD_HEIHGT);
		this.isDead = false;
		this.liveTime = 0f;
		this.nSkillType = DEFUALT;
	}

	public AttackFiyld(float center_x, float center_y ,int nSkillType) {
		super(center_x, center_y, ATTACK_FIYLD_WIDTH*10, ATTACK_FIYLD_HEIHGT);
		this.isDead = false;
		this.liveTime = 0f;
		this.nSkillType = nSkillType;
	}

	public void update(float deltaTime) {

		position.add(0, 0);
		bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);

		this.liveTime += deltaTime;
		stateTime += deltaTime;

		if (liveTime > MAXLIVETIME)
			this.setDead(true);

	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

}

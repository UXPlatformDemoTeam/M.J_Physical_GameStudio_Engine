package pangpang.MainGameLeemJinMuSSang;

import jrcengine.Interface.Object_Dynamic;

public class SkillObject extends Object_Dynamic {

	public static final int SKILLWIDTH = 50;
	public static final int SKILLHEIGHT = 300;

	public static final float SKILLSPEED = 40;

	public static final int GIGONGSINPO = 1;
	public static final int CROSSCUTTING = 2;
	public static final int GENERATEMARINE = 3;

	public static final int GICONGSINPODAMAGE = 20;
	public static final int CROSSCUTTINGDAMAGE = 40;
	public static final int GENERATEMARINEDANAGE = 5;
	
	private final int COUNTMAXGENERATE = 200;
	private final int COUNTMAXCROSS = 10;
	
	private int SkillType;
	private boolean isDead;
	private int nCount;

	public SkillObject(float center_x, float center_y, int _sType) {
		super(center_x, center_y, SKILLWIDTH, SKILLHEIGHT);
		this.isDead = false;
		this.SkillType = _sType;
		this.nCount = 0;
	}

	public void update(float deltaTime) {

		if (this.getSkillType() == GIGONGSINPO) {
			velocity.set(80, 0);

			position.add(velocity.x * deltaTime, 0);
			bounds.lowerLeft.set(position).sub(bounds.width / 2,
					bounds.height / 2);
			
		} else if (this.getSkillType() == CROSSCUTTING) {
			velocity.set(80, 0);

			position.add(velocity.x * deltaTime, 0);
			bounds.lowerLeft.set(position).sub(bounds.width / 2,
					bounds.height / 2);
			
			if(nCount>=COUNTMAXCROSS)
				this.setDead(true);
		} else if (this.getSkillType() == GENERATEMARINE) {
			velocity.set(80, 0);

			position.add(velocity.x * deltaTime, 0);
			bounds.lowerLeft.set(position).sub(bounds.width / 2,
					bounds.height / 2);
			
			if(nCount>=COUNTMAXGENERATE)
				this.setDead(true);
		}

		stateTime += deltaTime;
	}
	
	public int getnCount() {
		return nCount;
	}

	public void setnCount() {
		nCount++;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public int getSkillType() {
		return SkillType;
	}

	public void setSkillType(int skillType) {
		SkillType = skillType;
	}

	public int skillDage() {
		return 0;
	}

}

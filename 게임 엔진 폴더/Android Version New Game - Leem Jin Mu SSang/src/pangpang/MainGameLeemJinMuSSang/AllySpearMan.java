package pangpang.MainGameLeemJinMuSSang;

import java.util.Random;

import jrcengine.Manage.Manage_Assets;
import jrcengine.Math.Math_Overlap_Rectangle;

public class AllySpearMan extends Ally {

	public static final float ALLY_SPEAR_WIDTH = 100f;
	public static final float ALLY_SPEAR_HEIGHT = 100f;

	public static final float ALLY_SPEAR_WIDTH_R = 80f;
	public static final float ALLY_SPEAR_HEIGHT_R = 100f;

	public float ALLY_LANCE_VELOCITY = 60f;

	private final float GAPHPBAR = 20f;
	private final float nDelaySwingTime = 1f;
	public Math_Overlap_Rectangle attackBounds;
	public float fCurrentSwingTime;
	private final int SWINGPERSENTAGE = 7;
	private Random rnd;

	public AllySpearMan(float center_x, float center_y, int health, float speed) {
		super(center_x, center_y, ALLY_SPEAR_WIDTH, ALLY_SPEAR_HEIGHT, health);
		this.setStateFlag(EnemySwordMan.OBJ_D_RIGHT);

		this.ALLY_LANCE_VELOCITY = speed;
		this.rnd = new Random();
		this.fCurrentSwingTime = 0f;
		this.attackBounds = new Math_Overlap_Rectangle(center_x
				- ALLY_SPEAR_WIDTH_R / 2, center_y - ALLY_SPEAR_HEIGHT_R / 2,
				ALLY_SPEAR_WIDTH_R, ALLY_SPEAR_HEIGHT_R);
	}

	public void update(float deltaTime) {

		if (this.getStageFlag() == OBJ_D_LEFT) {
			velocity.set(-ALLY_LANCE_VELOCITY, 0);
			this.fCurrentSwingTime = 0f;
		} else if (this.getStageFlag() == OBJ_D_RIGHT) {
			velocity.set(ALLY_LANCE_VELOCITY, 0);
			this.fCurrentSwingTime = 0f;
		} else if (this.getStageFlag() == OBJ_D_STOP) {
			velocity.set(0, 0);
			this.fCurrentSwingTime = 0f;
		} else if (this.getStageFlag() == OBJ_D_ATTAK) {
			velocity.set(0, 0);
			this.fCurrentSwingTime += deltaTime;
		} else if (this.getStageFlag() == OBJ_D_DEATH) {
			velocity.set(0, 0);
			fCurrentSwingTime = 0;
		}

		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		stateTime += deltaTime;
		attackBounds.lowerLeft.set(position).sub(0, attackBounds.height / 2);
		bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);

		if (this.fCurrentSwingTime > nDelaySwingTime) {
			if (SWINGPERSENTAGE > rnd.nextInt(10)/* Skill Effect */) {
				Attack();
				this.fCurrentSwingTime = 0;
			}
		}
		this.getAllyHpBar().update(position.x, position.y, GAPHPBAR,
				this.getnHealthPoint(), this.getnFullHealThPoint(), deltaTime);
	}

	private void Attack() {
		Manage_Assets.playSound(Manage_Assets.soundSwordAttack01thick);
		MainGame_Manager.allyAttackFiyld.add(new AttackFiyld(
				this.position.x + 85, this.position.y,AttackFiyld.SPEARMAN));
	}

	@Override
	int skill01() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int skill02() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int skill03() {
		// TODO Auto-generated method stub
		return 0;
	}

}

package pangpang.MainGameLeemJinMuSSang;

import java.util.Random;

import jrcengine.Manage.Manage_Assets;
import jrcengine.Math.Math_Overlap_Rectangle;

public class EnemyGunMan extends Enemy {

	public static final float ENEMY_GUNMAN_WIDTH = 140f;
	public static final float ENEMY_GUNMAN_HEIGHT = 100f;
	public static final float ENEMY_ATTACK_GUNMAN_WIDTH = 160f;
	public static final float ENEMY_ATTACK_GUNMAN_HEIGHT = 100f;
	public static final float ENEMY_GUNMAN_VELOCITY = 60f;

	private final float GAPHPBAR = 20f;

	private final float nDelayShutTime = 2f;
	public float fCurrentShutTime;
	private final int SHUTPERSENTAGE = 8;
	private Random rnd;
	public Math_Overlap_Rectangle attackBounds;

	public EnemyGunMan(float center_x, float center_y) {
		super(center_x, center_y, ENEMY_GUNMAN_WIDTH, ENEMY_GUNMAN_HEIGHT, 100);
		fCurrentShutTime = 0;
		this.rnd = new Random();
		this.attackBounds = new Math_Overlap_Rectangle(center_x
				- ENEMY_ATTACK_GUNMAN_WIDTH, center_y
				- ENEMY_ATTACK_GUNMAN_HEIGHT / 2, ENEMY_ATTACK_GUNMAN_WIDTH,
				ENEMY_ATTACK_GUNMAN_HEIGHT);
		this.fDeadTime = 0f;
		setStateFlag(OBJ_D_LEFT);
	}

	public void update(float deltaTime) {

		if (this.getStageFlag() == OBJ_D_LEFT) {
			velocity.set(-ENEMY_GUNMAN_VELOCITY, 0);
			fCurrentShutTime = 0;
		} else if (this.getStageFlag() == OBJ_D_ATTAK) {
			velocity.set(0, 0);
			fCurrentShutTime += deltaTime;
		} else if (this.getStageFlag() == OBJ_D_STOP) {
			velocity.set(0, 0);
			fCurrentShutTime = 0;
		} else if (this.getStageFlag() == OBJ_D_RIGHT) {
			velocity.set(ENEMY_GUNMAN_VELOCITY, 0);
			fCurrentShutTime = 0;
		} else if (this.getStageFlag() == OBJ_D_DEATH) {
			velocity.set(0, 0);
			fCurrentShutTime = 0;
		} else if (this.getStageFlag() == OBJ_D_RUNAWAY) {
			velocity.set(ENEMY_GUNMAN_VELOCITY, 0);
			fCurrentShutTime = 0;
		}

		if (fCurrentShutTime > nDelayShutTime) {
			if (SHUTPERSENTAGE > rnd.nextInt(10)/* Skill Effect */) {
				ShutBullet();
				fCurrentShutTime = 0;
			}
		}

		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		stateTime += deltaTime;
		attackBounds.lowerLeft.set(position).sub(attackBounds.width,
				attackBounds.height / 2);
		bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);

		this.getEnemyHpBar().update(position.x, position.y, GAPHPBAR,
				this.getnHealthPoint(), this.getnFullHealThPoint(), deltaTime);
	}

	private void ShutBullet() {
		Manage_Assets.playSound(Manage_Assets.soundShootGun);
		MainGame_Manager.enemyBullet.add(new Bullet(this.position.x - 55,
				this.position.y - 20));
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

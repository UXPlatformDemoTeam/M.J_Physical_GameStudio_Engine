package pangpang.MainGameLeemJinMuSSang;

import java.util.Random;

import jrcengine.Manage.Manage_Assets;
import jrcengine.Math.Math_Overlap_Rectangle;

public class AllyMonkMan extends Ally {

	public static final float ALLY_MONKMAN_WIDTH = 140f;
	public static final float ALLY_MONKMAN_HEIGHT = 100f;
	public static final float ALLY_ATTACK_MONKMAN_WIDTH = 160f;
	public static final float ALLY_ATTACK_MONKMAN_HEIGHT = 100f;
	public float ALLY_MONKMAN_VELOCITY;

	private final float GAPHPBAR = 20f;

	private final float nDelayShutTime = 2f;
	public float fCurrentShutTime;
	private final int SHUTPERSENTAGE = 8;
	private Random rnd;
	public Math_Overlap_Rectangle attackBounds;

	public AllyMonkMan(float center_x, float center_y, int health, float speed) {
		super(center_x, center_y, ALLY_ATTACK_MONKMAN_WIDTH,
				ALLY_ATTACK_MONKMAN_HEIGHT, health);
		this.ALLY_MONKMAN_VELOCITY = speed;
		fCurrentShutTime = 0;
		this.rnd = new Random();
		this.attackBounds = new Math_Overlap_Rectangle(center_x
				+ ALLY_ATTACK_MONKMAN_WIDTH, center_y
				+ ALLY_ATTACK_MONKMAN_HEIGHT / 2, ALLY_ATTACK_MONKMAN_WIDTH,
				ALLY_ATTACK_MONKMAN_HEIGHT);
		this.fDeadTime = 0f;
		setStateFlag(OBJ_D_RIGHT);
	}

	public void update(float deltaTime) {

		if (this.getStageFlag() == OBJ_D_LEFT) {
			velocity.set(ALLY_MONKMAN_VELOCITY, 0);
			fCurrentShutTime = 0;
		} else if (this.getStageFlag() == OBJ_D_ATTAK) {
			velocity.set(0, 0);
			fCurrentShutTime += deltaTime;
		} else if (this.getStageFlag() == OBJ_D_STOP) {
			velocity.set(0, 0);
			fCurrentShutTime = 0;
		} else if (this.getStageFlag() == OBJ_D_RIGHT) {
			velocity.set(ALLY_MONKMAN_VELOCITY, 0);
			fCurrentShutTime = 0;
		} else if (this.getStageFlag() == OBJ_D_DEATH) {
			velocity.set(0, 0);
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
		attackBounds.lowerLeft.set(position).sub(0, attackBounds.height / 2);
		bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);

		this.getAllyHpBar().update(position.x, position.y, GAPHPBAR,
				this.getnHealthPoint(), this.getnFullHealThPoint(), deltaTime);
	}

	private void ShutBullet() {
		Manage_Assets.playSound(Manage_Assets.soundMonkAttack);
		MainGame_Manager.energyBall.add(new EnergyBall(this.position.x + 50,
				this.position.y - 17, EnergyBall.ENERGYBALL));
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

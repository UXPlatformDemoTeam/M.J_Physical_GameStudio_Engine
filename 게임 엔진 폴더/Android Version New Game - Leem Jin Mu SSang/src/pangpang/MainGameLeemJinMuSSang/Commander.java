package pangpang.MainGameLeemJinMuSSang;

public class Commander extends Structure {

	public static final int OURFORCE = 0;
	public static final int ENEMYFORCE = 1;

	public static final int BASICDAMAGE = 60;

	public static final int COMMANDER_ALLY_WIDTH = 320;
	public static final int COMMANDER_ALLY_HEIHGT = 330;

	public static final int COMMANDER_ENEMY_WIDTH = 320;
	public static final int COMMANDER_ENEMY_HEIHGT = 330;
	
	public static final int COMMANDER_WIDTH = 200;
	public static final int COMMANDER_HEIHGT = 200;

	private final float GAPHPBAR = 40f;

	private int Flag;

	public Commander(float center_x, float center_y, float width, float height,
			int healthPoint, int flag) {
		super(center_x, center_y, COMMANDER_WIDTH, COMMANDER_HEIHGT,
				healthPoint);

		this.Flag = flag;
	}

	public void update(float deltaTime) {

		position.add(0, 0);
		bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
		stateTime += deltaTime;

		this.getHPbar().update(position.x, position.y, GAPHPBAR,
				this.getnHealthPoint(), this.getnFullHealThPoint(), deltaTime);
	}

	public int getFlag() {
		return Flag;
	}

	public void setFlag(int flag) {
		Flag = flag;
	}

}

package demo.MainGame;

import jrcengine.Interface.Object_Dynamic;
import jrcengine.Math.Math_Vector;

public class Obj_D_NumberBox extends Object_Dynamic {

	// if click two box ,you can decide to it's flag
	static final int CHANGE_RIGHT_LEFT = 0;
	static final int CHANGE_LEFT_RIGHT = 1;
	static final int CHANGE_DOWN_UP = 2;
	static final int CHANGE_UP_DOWN = 3;

	static final int MOVE_RIGHT = 0;
	static final int MOVE_LEFT = 1;
	static final int MOVE_UP = 2;
	static final int MOVE_DOWN = 3;

	public static final float WIDTH = 36;
	public static final float HEIGHT = 88;

	public static final float GAME_SIZE_WIDTH = 1f;
	public static final float GAME_SIZE_HEIGHT = 1f;

	private int move_dir;
	private boolean move_initialize_flag;
	private Math_Vector move_Desti;

	public Obj_D_NumberBox(int image_flag, float center_x, float center_y,
			float width, float height) {
		super(image_flag, center_x, center_y, width, height);

		this.move_dir = -1;
		this.state_Flag = OBJ_D_DROP;
		this.move_initialize_flag = false;
		this.move_Desti = new Math_Vector();

	}

	public Obj_D_NumberBox(float center_x, float center_y, float width,
			float height, int... image_flags) {
		super(center_x, center_y, width, height, image_flags);

		this.move_dir = -1;
		this.state_Flag = OBJ_D_DROP;
		this.move_initialize_flag = false;
		this.move_Desti = new Math_Vector();

	}

	public boolean moveBox() {
		initial_Position(move_dir);

		if ((MOVE_RIGHT == move_dir) && (this.position.x < move_Desti.x-0.1f)) {

			position.x += 0.1f;

			return true;
		} else if ((MOVE_LEFT == move_dir) && (this.position.x > move_Desti.x+0.1f)) {

			position.x -= 0.1f;

			return true;
		} else if ((MOVE_DOWN == move_dir) && (this.position.y > move_Desti.y+0.1f)) {

			position.y -= 0.1f;
			return true;
		} else if ((MOVE_UP == move_dir) && (this.position.y < move_Desti.y-0.1f)) {

			position.y += 0.1f;
			return true;
		}
		
		
		if(-1<move_dir)
		{
			if(MOVE_RIGHT == move_dir||MOVE_LEFT ==move_dir)
				position.x = move_Desti.x;
			else if(MOVE_DOWN == move_dir || MOVE_UP ==move_dir)
				position.y = move_Desti.y;
		}
		

		move_initialize_flag = false;
		move_dir = -1;
		return false;
	}

	private void initial_Position(int flag) {
		if (false == move_initialize_flag) {

			if (MOVE_RIGHT == flag) {
				move_Desti.set(position.x + 1, position.y);
			} else if (MOVE_LEFT == flag) {
				move_Desti.set(position.x - 1, position.y);
			} else if (MOVE_UP == flag) {
				move_Desti.set(position.x, position.y + 1);
			} else if (MOVE_DOWN == flag) {
				move_Desti.set(position.x, position.y - 1);
			}

			move_initialize_flag = true;
		}
	}

	public void update(float deltaTime) {

		if (state_Flag == OBJ_D_DROP) {
			velocity.add(Game_Window_Manager.gravity.x * deltaTime,
					Game_Window_Manager.gravity.y * deltaTime);
			position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		}
		bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);

		if (state_Flag == OBJ_D_NORMAL) {
			velocity.set(0, 0);
		}

		if (position.y <= (bounds.height / 2)) {
			position.y = bounds.height / 2;
			state_Flag = OBJ_D_NORMAL;
		}

		stateTime += deltaTime;

	}

	public void setMoveDir(int set) {
		this.move_dir = set;
	}

	public int getMoveDir() {
		return this.move_dir;
	}

}

package demo.MainGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;

import jrcengine.Interface.IFace_Input.TouchEvent;
import jrcengine.Interface.Object_Dynamic;
import jrcengine.Interface.Screen_Manager;
import jrcengine.Math.Math_Vector;
import jrcengine.Manage.Manage_Settings;

public class Game_Window_Manager extends Screen_Manager {

	private final int MAX_STACK_LENTH = 3;
	private int stackNum; // stack stacked number

	public static final Math_Vector gravity = new Math_Vector(0, -2f);

	public static final int GAME_STATE_RUNNING = 0;
	public static final int GAME_STATE_GAME_CLEAR = 1;
	public static final int GAME_STATE_GAME_OVER = 2;

	private boolean m_Double_Click = true;

	private final Manage_Settings setting;

	private final Obj_D_NumberBox[][] mapping;
	private final Obj_D_NumberBox[] stack;
	private Math_Vector touchDown;
	
	private final int windows_Width;
	private final int windows_Height;

	private final Random rand;
	private int state;
	private int money_Score;

	private final List<Obj_D_NumberBox> number_Box;

	public Game_Window_Manager(Manage_Settings settings ,int windows_Width, int windows_Height) {

		this.windows_Width = windows_Width;
		this.windows_Height = windows_Height;
		
		this.mapping = new Obj_D_NumberBox[this.windows_Width][this.windows_Height];
		this.stack = new Obj_D_NumberBox[10];
		this.setting = settings;
		this.state = GAME_STATE_RUNNING;
		this.touchDown = new Math_Vector(-1, -1);

		this.rand = new Random();

		this.money_Score = 0;
		this.stackNum = 1;

		this.number_Box = new ArrayList<Obj_D_NumberBox>();

		generate();

	}

	// 게임 세계의 생성
	@Override
	protected void generate() {

	}

	@Override
	public void update(float deltaTime) {
		

		update_checkCollisions(deltaTime);
		update_Objections(deltaTime);
		update_Mapping(deltaTime);
		check_GameOver();

	}

	private void update_Mapping(float deltaTime) {
		int len = number_Box.size();

		for (int i = 0; i < this.windows_Width; i++)
			for (int j = 0; j < this.windows_Height; j++)
				this.mapping[i][j] = null;

		for (int i = 0; i < len; i++) {
			Obj_D_NumberBox box = number_Box.get(i);
			this.mapping[(int) box.position.x][(int) box.position.y] = box;
		}

	}

	private void update_Objections(float deltaTime) {

		update_Number_Box(deltaTime);
		update_Move_Box(deltaTime);

	}
	
	private void update_Move_Box(float deltaTime){
		int len = number_Box.size();
		
		for (int i = 0; i < len; i++) {
			Obj_D_NumberBox box = number_Box.get(i);
		
				box.moveBox();
		}
	}

	private void update_Number_Box(float deltaTime) {
		int len = number_Box.size();

		for (int i = 0; i < len; i++) {
			Obj_D_NumberBox box = number_Box.get(i);
			box.update(deltaTime);
		}
	}

	// 충돌 검사 메서드를 모두 호출
	@Override
	protected void update_checkCollisions(float deltaTime) {

		update_checkCollisions_Number_Box_To_Number_Box(deltaTime);

	}

	private void update_checkCollisions_Number_Box_To_Number_Box(float deltaTime) {
		int len = number_Box.size();
		float y_numb;

		for (int i = 0; i < len; i++) {
			Obj_D_NumberBox box_main = number_Box.get(i);

			y_numb = (int) (box_main.position.y - 1);

			if (y_numb < 0)
				y_numb = 0;

			if (null == mapping[(int) box_main.position.x][(int) y_numb]) {
				box_main.setStateFlag(Object_Dynamic.OBJ_D_DROP);
			} else if (box_main.position.y < (int) box_main.position.y + 0.5f) {
				box_main.position.y = (int) box_main.position.y + 0.5f;
				box_main.setStateFlag(Object_Dynamic.OBJ_D_NORMAL);
			}
		}
	}

	public int getState() {
		return this.state;
	}

	public int getMoney_Score() {
		return this.money_Score;
	}

	private void check_GameOver() {

	}

	public void click_World(float click_X, float click_Y, int touch_type) {
		
		int set_X = (int) click_X;
		int set_Y = (int) click_Y;

		if (false == checkTouchWorldBounds(set_X, set_Y))
			return;

		
		if ((null == mapping[set_X][set_Y])
				&& (TouchEvent.TOUCH_UP == touch_type)) {
			addBox(set_X, set_Y);
		} else if (null != mapping[set_X][set_Y]) {
			if (mapping[set_X][set_Y].getIsTouchDown()
					&& (TouchEvent.TOUCH_UP == touch_type)) {
				clickBox(set_X, set_Y, touch_type);
			} else if (TouchEvent.TOUCH_DRAGGED == touch_type) {

				if (null == stack[0]) {
					
					int len = number_Box.size();
					
					for(int i =0 ; i < len ; i++)
					{
						if(number_Box.get(i).getIsClicked())
						{
							number_Box.get(i).setIsClicked(false);
							break;
						}
					}
					
					stack[0] = mapping[set_X][set_Y];
					stack[0].setIsDragged(true);

				} else {
					if (checkDraggedBoxSide(stack[stackNum - 1], set_X, set_Y)
							&& stackNum < MAX_STACK_LENTH)
						checkStack(mapping[set_X][set_Y], true);
				}

			} else if (false == mapping[set_X][set_Y].getIsTouchDown()
					&& (TouchEvent.TOUCH_UP == touch_type))

			{
				if (null == mapping[(int) touchDown.x][(int) touchDown.y])
					return;
				mapping[(int) touchDown.x][(int) touchDown.y]
						.setIsTouchDown(false);
			}

			if (TouchEvent.TOUCH_DOWN == touch_type) {
				mapping[set_X][set_Y].setIsTouchDown(true);
				touchDown.set(set_X, set_Y);
			}
		}

		if (TouchEvent.TOUCH_UP == touch_type) {

			if (null != stack[0])
				checkStack(null, false);
		}

	}

	private boolean checkDraggedBoxSide(Obj_D_NumberBox box, int click_X,
			int click_Y) {

		if (box.position.equals(click_X - 1 + 0.5f, click_Y + 0.5f)
				|| box.position.equals(click_X + 1 + 0.5f, click_Y + 0.5f)
				|| box.position.equals(click_X + 0.5f, click_Y - 1 + 0.5f)
				|| box.position.equals(click_X + 0.5f, click_Y + 1 + 0.5f)) {

			return true;
		}

		return false;
	}

	private void clickBox(int click_X, int click_Y, int mode) {
		boolean flag = false;

		flag = CheckBoxSide(click_X, click_Y, mode);

		if (false == flag) {

			if (false == m_Double_Click) {
				mapping[click_X][click_Y].setIsTouchDown(false);
				mapping[click_X][click_Y].setIsClicked(true);
				m_Double_Click = true;

			} else {
				m_Double_Click = false;
			}

		}
	}

	private boolean CheckBoxSide(int click_X, int click_Y, int mode) {
		int len = number_Box.size();

		for (int i = 0; i < len; i++) {
			Obj_D_NumberBox box = number_Box.get(i);

			if (((click_X + 1 < this.windows_Width)
					&& (null != mapping[click_X + 1][click_Y]) && (mapping[click_X + 1][click_Y]
					.getIsClicked()))) {

				if (TouchEvent.TOUCH_UP == mode) {
					changeBoxPosition(mapping[click_X][click_Y],
							mapping[click_X + 1][click_Y], Obj_D_NumberBox.CHANGE_LEFT_RIGHT);
				}

				return true;

			} else if (((click_X > 0)
					&& (null != mapping[click_X - 1][click_Y]) && mapping[click_X - 1][click_Y]
					.getIsClicked())) {
				if (TouchEvent.TOUCH_UP == mode) {
					changeBoxPosition(mapping[click_X][click_Y],
							mapping[click_X - 1][click_Y], Obj_D_NumberBox.CHANGE_RIGHT_LEFT);
				}

				return true;
			} else if (((click_Y + 1 < this.windows_Height)
					&& (null != mapping[click_X][click_Y + 1]) && (mapping[click_X][click_Y + 1]
					.getIsClicked()))) {
				if (TouchEvent.TOUCH_UP == mode) {
					changeBoxPosition(mapping[click_X][click_Y],
							mapping[click_X][click_Y + 1], Obj_D_NumberBox.CHANGE_DOWN_UP);
				}

				return true;
			} else if (((click_Y > 0)
					&& (null != mapping[click_X][click_Y - 1]) && (mapping[click_X][click_Y - 1]
					.getIsClicked()))) {
				if (TouchEvent.TOUCH_UP == mode) {
					changeBoxPosition(mapping[click_X][click_Y],
							mapping[click_X][click_Y - 1], Obj_D_NumberBox.CHANGE_UP_DOWN);
				}

				return true;
			}

			else if (TouchEvent.TOUCH_UP == mode) {
				box.setIsClicked(false);
			}
		}

		return false;
	}

	private void checkStack(Obj_D_NumberBox box, boolean flag) {
		if (true == flag) {
			for (int i = 0; i < stackNum; i++) {
				if (stack[i] == box)
					return;
			}

			stack[stackNum] = box;
			box.setIsDragged(true);
			stackNum++;

			if (stackNum >= MAX_STACK_LENTH) {

				if (stack[2].getImage_flags(0) + 1 == stack[0]
						.getImage_flags(0) + 1 + stack[1].getImage_flags(0) + 1) {
					int len = number_Box.size();

					for (int j = 0; j < stackNum; j++) {
						for (int i = 0; i < len; i++) {
							Obj_D_NumberBox remove_box = number_Box.get(i);

							if (remove_box.position.x == stack[j].position.x
									&& remove_box.position.y == stack[j].position.y) {
								number_Box.remove(i);
								break;
							}

						}
					}
				}
			}

		}

		else {
			for (int i = 0; i < stackNum; i++) {
				stack[i].setIsDragged(false);
				stack[i] = null;
			}
			stackNum = 1;
		}

	}

	private void changeBoxPosition(Obj_D_NumberBox box01,
			Obj_D_NumberBox box02, int mode) {
		
		
		if(Obj_D_NumberBox.CHANGE_RIGHT_LEFT==mode)
		{
			box01.setMoveDir(Obj_D_NumberBox.MOVE_LEFT);
			box02.setMoveDir(Obj_D_NumberBox.MOVE_RIGHT);
		}
		
		else if(Obj_D_NumberBox.CHANGE_LEFT_RIGHT== mode)
		{
			box01.setMoveDir(Obj_D_NumberBox.MOVE_RIGHT);
			box02.setMoveDir(Obj_D_NumberBox.MOVE_LEFT);
		}
		
		else if(Obj_D_NumberBox.CHANGE_UP_DOWN == mode)
		{
			box01.setMoveDir(Obj_D_NumberBox.MOVE_DOWN);
			box02.setMoveDir(Obj_D_NumberBox.MOVE_UP);
		}
		else if(Obj_D_NumberBox.CHANGE_DOWN_UP == mode)
		{
			box01.setMoveDir(Obj_D_NumberBox.MOVE_UP);
			box02.setMoveDir(Obj_D_NumberBox.MOVE_DOWN);
		}

		box01.setIsClicked(false);
		box01.setIsTouchDown(false);

		box02.setIsClicked(false);
		box02.setIsTouchDown(false);

		m_Double_Click = false;

	}

	private void addBox(int click_X, int click_Y) {
		int box_number = rand.nextInt(2);// 9);

		float set_x = click_X + 0.5f;
		
		number_Box.add(new Obj_D_NumberBox(set_x, click_Y,
				Obj_D_NumberBox.GAME_SIZE_WIDTH,
				Obj_D_NumberBox.GAME_SIZE_HEIGHT, box_number));

	}

	private boolean checkTouchWorldBounds(int click_X, int click_Y) {
		if (click_X < 0 || click_X >= this.windows_Width || click_Y < 0
				|| click_Y >= this.windows_Height)
			return false;

		return true;
	}

	public List<Obj_D_NumberBox> get_Number_Box() {
		return this.number_Box;
	}

	public int get_Box_World_Width() {
		return this.windows_Width;
	}

	public int get_Box_World_Height() {
		return this.windows_Height;
	}

}
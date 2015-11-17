// 실제 게임 화면(플레이어가 실제 즐길 수 있는 화면)
package demo.MainGame;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import jrcengine.Basic.GL_Screen;
import jrcengine.Basic.Log_TimeCounter;
import jrcengine.GL.GL_Camera2D;
import jrcengine.GL.GL_SpriteBatcher;

import jrcengine.Interface.IFace_Game;
import jrcengine.Interface.IFace_Input.TouchEvent;
import jrcengine.Math.Math_Overlap;
import jrcengine.Math.Math_Vector;
import jrcengine.Manage.Manage_Settings;

public class Screen_MainGame extends GL_Screen {
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;
	private int state;

	private final int GAME_WIDTH = 320;
	private final int GAME_HEIGHT = 480;

	private final int Box_STACK_WIDTH = 6;
	private final int Box_STACK_HEIGHT = 8;

	Log_TimeCounter fpsCounter;

	GL_Camera2D guiCam;
	GL_SpriteBatcher batcher;
	MainGame_Manager manage;
	MainGame_Renderer renderer;
	int money_Last_Score;
	String scoreString;
	Math_Vector touchPoint;
	Screen_Game_Window controller_Bulid;

	Manage_Settings setting;

	public Screen_MainGame(IFace_Game game, Manage_Settings settings) {
		super(game);
		this.state = GAME_RUNNING;

		this.guiCam = new GL_Camera2D(glGraphics, GAME_WIDTH, GAME_HEIGHT);
		this.batcher = new GL_SpriteBatcher(glGraphics, 1000);

		this.fpsCounter = new Log_TimeCounter();
		this.touchPoint = new Math_Vector();

		this.setting = settings;
		this.manage = new MainGame_Manager(setting, GAME_WIDTH, GAME_HEIGHT);
		this.renderer = new MainGame_Renderer(glGraphics, batcher, manage,
				settings);
		this.controller_Bulid = new Screen_Game_Window(setting, glGraphics,
				batcher, this, 192, 236, 234, 335, Box_STACK_WIDTH,
				Box_STACK_HEIGHT);

		this.money_Last_Score = 0;
		scoreString = "score: 0";

	}

	@Override
	public void update(float deltaTime) {
		if (deltaTime > 0.1f)
			deltaTime = 0.1f;

		update_Contoroller(state);

		switch (state) {
		case GAME_READY:
			updateReady();
			break;
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_PAUSED:
			// updatePaused();
			break;
		case GAME_OVER:
			updateGameOver();
			break;
		}
	}

	private void update_controller_Bulid(int x, int y, int touchType) {
		if (touchType == TouchEvent.TOUCH_DOWN) {
			if (GAME_RUNNING == state) {
				{
					controller_Bulid.getGL_cam().touchToWorld_partition(
							touchPoint, guiCam);

					controller_Bulid.getManager().click_World(touchPoint.x, touchPoint.y, touchType);
					return;
				}
			}
		}

		if (touchType == TouchEvent.TOUCH_DRAGGED) {
			if (GAME_RUNNING == state) {

				controller_Bulid.getGL_cam().touchToWorld_partition(touchPoint,
						guiCam);

				controller_Bulid.getManager().click_World(touchPoint.x, touchPoint.y, touchType);
				return;
			}
		}

		if (touchType == TouchEvent.TOUCH_UP) {
			if (GAME_RUNNING == state) {
				controller_Bulid.getGL_cam().touchToWorld_partition(touchPoint,
						guiCam);
				controller_Bulid.getManager().click_World(touchPoint.x, touchPoint.y, touchType);
				return;
			}
		}
	}

	private void update_Contoroller(int state) {

		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();

		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

			if (event.type == TouchEvent.TOUCH_DOWN) {

				touchPoint.set(event.x, event.y);
				guiCam.touchToWorld(touchPoint);

				if (Math_Overlap.pointInRectangle(
						controller_Bulid.getBuildBouns(), touchPoint))
					update_controller_Bulid(event.x, event.y,
							TouchEvent.TOUCH_DOWN);
			}

			if (event.type == TouchEvent.TOUCH_DRAGGED) {
				touchPoint.set(event.x, event.y);
				guiCam.touchToWorld(touchPoint);

				if (Math_Overlap.pointInRectangle(
						controller_Bulid.getBuildBouns(), touchPoint))
					update_controller_Bulid(event.x, event.y,
							TouchEvent.TOUCH_DRAGGED);
			}

			if (event.type == TouchEvent.TOUCH_UP) {
				touchPoint.set(event.x, event.y);
				guiCam.touchToWorld(touchPoint);

				if (Math_Overlap.pointInRectangle(
						controller_Bulid.getBuildBouns(), touchPoint))
					update_controller_Bulid(event.x, event.y,
							TouchEvent.TOUCH_UP);

			}
		}
	}

	private void updateReady() {
		if (game.getInput().getTouchEvents().size() > 0) {
			state = GAME_RUNNING;
		}
	}

	private void updateRunning(float deltaTime) {

		controller_Bulid.update(deltaTime);
		manage.update(renderer, deltaTime, game.getInput().getAccelX(), game
				.getInput().getAccelY());
		if (manage.getMoney_Score() != this.money_Last_Score) {
			this.money_Last_Score = manage.getMoney_Score();
			scoreString = "score: " + this.money_Last_Score;
		}
		if (manage.getState() == this.GAME_LEVEL_END) {
			state = GAME_LEVEL_END;
		}
		if (manage.getState() == this.GAME_OVER) {
			state = GAME_OVER;
		}

	}

	private void updateGameOver() {
	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);

		renderer.render();
		controller_Bulid.render();

		guiCam.setViewportAndMatrices();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		switch (state) {
		case GAME_READY:
			presentReady();
			break;
		case GAME_RUNNING:
			presentRunning();
			break;
		case GAME_PAUSED:
			presentPaused();
			break;
		case GAME_LEVEL_END:
			presentLevelEnd();
			break;
		case GAME_OVER:
			presentGameOver();
			break;
		}

		gl.glDisable(GL10.GL_BLEND);
		fpsCounter.logFrame();
	}

	private void presentReady() {

	}

	private void presentRunning() {

	}

	private void presentPaused() {

	}

	private void presentLevelEnd() {

	}

	private void presentGameOver() {

	}

	@Override
	public void pause() {
		if (state == GAME_RUNNING)
			state = GAME_PAUSED;
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

	public int getGameWidth() {
		return this.GAME_WIDTH;
	}

	public int getGameHeight() {
		return this.GAME_HEIGHT;
	}

}

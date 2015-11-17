package pangpang.MainGameLeemJinMuSSang;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import jrcengine.Basic.GL_Screen;
import jrcengine.GL.GL_Animation;
import jrcengine.GL.GL_Camera2D;
import jrcengine.GL.GL_SpriteBatcher;
import jrcengine.GL.GL_TextureRegion;
import jrcengine.Interface.IFace_Game;
import jrcengine.Interface.IFace_Input.TouchEvent;
import jrcengine.Interface.Object_Dynamic;
import jrcengine.Manage.Manage_Assets;
import jrcengine.Manage.Manage_Settings;
import jrcengine.Math.Math_Overlap;
import jrcengine.Math.Math_Overlap_Rectangle;
import jrcengine.Math.Math_Vector;

public class PrepareWindow extends GL_Screen {
	private GL_Camera2D guiCam;
	private GL_SpriteBatcher batcher;
	private Math_Vector touchPoint;

	private int nStageNum = 1;
	private LeeSunSin mLeeSunShin;
	private ArrayList<Enemy> enemy;

	private boolean isGamePlayButton;
	private Math_Overlap_Rectangle startButtonBounds;

	private boolean isXbutton;
	private Math_Overlap_Rectangle XbuttonBounds;

	public PrepareWindow(IFace_Game game) {
		super(game);

		this.guiCam = new GL_Camera2D(glGraphics, Screen_MainMenu.GAME_WIDTH,
				Screen_MainMenu.GAME_HEIGHT);
		this.touchPoint = new Math_Vector();
		this.batcher = new GL_SpriteBatcher(glGraphics, 1);

		this.enemy = new ArrayList<Enemy>();
		this.mLeeSunShin = new LeeSunSin(0, 80, 138f, 0);

		if (nStageNum == 1) {
			this.enemy.add(new EnemyGunMan(Screen_MainMenu.GAME_WIDTH - 70,
					100f));
			this.enemy.get(0).setStateFlag(Object_Dynamic.OBJ_D_STOP);
			this.enemy.add(new EnemySwordMan(Screen_MainMenu.GAME_WIDTH - 130,
					100f));
			this.enemy.get(1).setStateFlag(Object_Dynamic.OBJ_D_STOP);
		}

		this.XbuttonBounds = new Math_Overlap_Rectangle(
				Screen_MainMenu.GAME_WIDTH - 35,
				Screen_MainMenu.GAME_HEIGHT - 35, 30, 30);

		this.isGamePlayButton = false;
		this.startButtonBounds = new Math_Overlap_Rectangle(
				Screen_MainMenu.GAME_WIDTH / 2 - 50, 30, 100, 60);
		MainGame_Manager.nStageScore = 0;
		this.isXbutton = false;
		
	}

	@Override
	public void resume() {
	}

	@Override
	public void pause() {
		Manage_Settings.save(game.getFileIO());
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			touchPoint.set(event.x, event.y);
			guiCam.touchToWorld(touchPoint);

			if (event.type == TouchEvent.TOUCH_UP) {
				if (Math_Overlap
						.pointInRectangle(startButtonBounds, touchPoint)
						&& true == isGamePlayButton) {
					isGamePlayButton = false;
					game.setScreen(new Screen_MainGame(game));
					return;
				}

				// xButton
				if (Math_Overlap.pointInRectangle(XbuttonBounds, touchPoint)
						&& true == isXbutton) {
					isXbutton = false;
					game.setScreen(new StageWindow(game));
					return;
				}

				this.isXbutton = false;
				this.isGamePlayButton = false;

			}

			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (Math_Overlap
						.pointInRectangle(startButtonBounds, touchPoint)) {
					isGamePlayButton = true;
					return;
				}
				if (Math_Overlap.pointInRectangle(XbuttonBounds, touchPoint)) {

					isXbutton = true;
					return;
				}

				this.isXbutton = false;
				this.isGamePlayButton = false;

			}
		}

		updateObjects(deltaTime);

	}

	private void updateObjects(float deltaTime) {
		this.mLeeSunShin.update(deltaTime);

		for (int i = 0; i < enemy.size(); i++)
			if (enemy.get(i) instanceof EnemySwordMan)
				((EnemySwordMan) enemy.get(i)).update(deltaTime);
			else if (enemy.get(i) instanceof EnemyGunMan)
				((EnemyGunMan) enemy.get(i)).update(deltaTime);
	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);

		batcher.beginBatch(Manage_Assets.ttScrMainMenu);
		batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
				Screen_MainMenu.GAME_HEIGHT / 2, Screen_MainMenu.GAME_WIDTH,
				Screen_MainMenu.GAME_HEIGHT, Manage_Assets.ttgScrScrMainMenu);
		batcher.endBatch();

		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		// this implimentation object.

		// render_chr_enemy_gun();
		render_enemy();
		render_ally();
		drawPoint();
		// Game Start Button
		batcher.beginBatch(Manage_Assets.ttPrepareSceneGameStartButton);
		if (false == isGamePlayButton)
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2, 50, 100, 60,
					Manage_Assets.ttgGamePreparedSceneGameStart);
		else
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2, 50, 100, 60,
					Manage_Assets.ttgGamePreparedSceneGameStartPressed);
		batcher.endBatch();

		// x button
		batcher.beginBatch(Manage_Assets.ttXbutton);
		if (false == isXbutton)
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH - 20,
					Screen_MainMenu.GAME_HEIGHT - 20, 30, 30,
					Manage_Assets.ttgXButton);
		else
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH - 20,
					Screen_MainMenu.GAME_HEIGHT - 20, 30, 30,
					Manage_Assets.ttgXButtonPressed);
		batcher.endBatch();

		gl.glDisable(GL10.GL_BLEND);
	}

	private void render_ally() {
		GL_TextureRegion _keyFrame;
		_keyFrame = Manage_Assets.aniLeesunsinWalking.getKeyFrame(
				mLeeSunShin.getStateTime(), GL_Animation.ANIMATION_LOOPING);

		batcher.beginBatch(Manage_Assets.ttLeesunsinWalking);

		batcher.drawSprite(mLeeSunShin.position.x, mLeeSunShin.position.y, 282,
				175, _keyFrame);
		batcher.endBatch();
	}

	private void render_enemy() {
		GL_TextureRegion _keyFrame;

		for (int i = 0; i < enemy.size(); i++) {

			if (enemy.get(i) instanceof EnemySwordMan) {
				batcher.beginBatch(Manage_Assets.ttChrEnemySword);
				_keyFrame = Manage_Assets.aniChrEnemySwordMov.getKeyFrame(enemy
						.get(i).getStateTime(), GL_Animation.ANIMATION_LOOPING);
			} else {
				batcher.beginBatch(Manage_Assets.ttChrEnemyGun);
				_keyFrame = Manage_Assets.aniChrEnemyGunMov.getKeyFrame(enemy
						.get(i).getStateTime(), GL_Animation.ANIMATION_LOOPING);
			}
			batcher.drawSprite(enemy.get(i).position.x,
					enemy.get(i).position.y, -180, 100, _keyFrame);
			batcher.endBatch();
		}
	}

	@Override
	public void dispose() {
	}

	private void drawPoint() {
		// draw point 02

		if (1 <= StageWindow.SELECTSTABEMAXNUM) {
			batcher.beginBatch(Manage_Assets.ttScrMainMenuPoint);
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2 + 100,
					Screen_MainMenu.GAME_HEIGHT / 2 - 30, 25, 25,
					Manage_Assets.ttgScrMainMenuPoint);
			batcher.endBatch();
		}

		// draw point 03
		if (2 <= StageWindow.SELECTSTABEMAXNUM) {
			batcher.beginBatch(Manage_Assets.ttScrMainMenuPoint);
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2 + 60,
					Screen_MainMenu.GAME_HEIGHT / 2 - 20, 25, 25,
					Manage_Assets.ttgScrMainMenuPoint);
			batcher.endBatch();
		}
		// draw point 04
		if (3 <= StageWindow.SELECTSTABEMAXNUM) {
			batcher.beginBatch(Manage_Assets.ttScrMainMenuPoint);
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2 + 30,
					Screen_MainMenu.GAME_HEIGHT / 2 - 40, 25, 25,
					Manage_Assets.ttgScrMainMenuPoint);
			batcher.endBatch();
		}
		// draw point 05
		if (4 <= StageWindow.SELECTSTABEMAXNUM) {
			batcher.beginBatch(Manage_Assets.ttScrMainMenuPoint);
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
					Screen_MainMenu.GAME_HEIGHT / 2 - 80, 25, 25,
					Manage_Assets.ttgScrMainMenuPoint);
			batcher.endBatch();
		}
		// draw point 06
		if (5 <= StageWindow.SELECTSTABEMAXNUM) {
			batcher.beginBatch(Manage_Assets.ttScrMainMenuPoint);
			batcher.drawSprite(70, Screen_MainMenu.GAME_HEIGHT / 2 - 80, 25,
					25, Manage_Assets.ttgScrMainMenuPoint);
			batcher.endBatch();
		}
		// draw point 07
		if (6 <= StageWindow.SELECTSTABEMAXNUM) {
			batcher.beginBatch(Manage_Assets.ttScrMainMenuPoint);
			batcher.drawSprite(80, 40, 25, 25,
					Manage_Assets.ttgScrMainMenuPoint);
			batcher.endBatch();
		}
		// draw point 08
		if (7 <= StageWindow.SELECTSTABEMAXNUM) {
			batcher.beginBatch(Manage_Assets.ttScrMainMenuPoint);
			batcher.drawSprite(80, 140, 25, 25,
					Manage_Assets.ttgScrMainMenuPoint);
			batcher.endBatch();
		}
	}
}

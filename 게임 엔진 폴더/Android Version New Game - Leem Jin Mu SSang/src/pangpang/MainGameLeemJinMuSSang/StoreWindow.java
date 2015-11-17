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
import jrcengine.Math.Math_Overlap;
import jrcengine.Math.Math_Overlap_Rectangle;
import jrcengine.Math.Math_Vector;

public class StoreWindow extends GL_Screen {
	private GL_Camera2D guiCam;
	private GL_SpriteBatcher batcher;
	private Math_Vector touchPoint;

	private int nStageNum = 1;
	private LeeSunSin mLeeSunShin;
	private ArrayList<Enemy> enemy;

	private boolean isGamePlayButton;
	private Math_Overlap_Rectangle startButtonBounds;

	public StoreWindow(IFace_Game game) {
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

		this.isGamePlayButton = false;
		this.startButtonBounds = new Math_Overlap_Rectangle(
				Screen_MainMenu.GAME_WIDTH / 2 - 50, 30, 100, 60);
	}

	@Override
	public void resume() {
	}

	@Override
	public void pause() {
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
				this.isGamePlayButton = false;
			}

			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (Math_Overlap
						.pointInRectangle(startButtonBounds, touchPoint)) {
					isGamePlayButton = true;
					return;
				}
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

		// Game Start Button
		batcher.beginBatch(Manage_Assets.ttPrepareSceneGameStartButton);
		if (false == isGamePlayButton)
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2, 50, 100, 60,
					Manage_Assets.ttgGamePreparedSceneGameStart);
		else
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2, 50, 100, 60,
					Manage_Assets.ttgGamePreparedSceneGameStartPressed);
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
}

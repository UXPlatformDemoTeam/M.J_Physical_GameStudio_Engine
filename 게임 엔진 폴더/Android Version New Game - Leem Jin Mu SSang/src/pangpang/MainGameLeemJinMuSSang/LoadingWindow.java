package pangpang.MainGameLeemJinMuSSang;

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
import jrcengine.Math.Math_Vector;

public class LoadingWindow extends GL_Screen {
	private GL_Camera2D guiCam;
	private GL_SpriteBatcher batcher;
	private Math_Vector touchPoint;

	private AllyAxeMan ally;
	private EnemyGunMan enemy;

	float current;

	public LoadingWindow(IFace_Game game) {
		super(game);

		this.guiCam = new GL_Camera2D(glGraphics, Screen_MainMenu.GAME_WIDTH,
				Screen_MainMenu.GAME_HEIGHT);
		this.touchPoint = new Math_Vector();
		this.batcher = new GL_SpriteBatcher(glGraphics, 1);
		this.current = 0;

		this.enemy = new EnemyGunMan(Screen_MainMenu.GAME_WIDTH, 100f);
		this.ally = new AllyAxeMan(Screen_MainMenu.GAME_WIDTH+100, 100f,1,1);
		this.ally.setStateFlag(Object_Dynamic.OBJ_D_LEFT);
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

			}

			if (event.type == TouchEvent.TOUCH_DOWN) {

			}
		}

		if (current > 8) {
			current = 0f;
			game.setScreen(new Screen_MainGame(game));
		}
		updateObjects(deltaTime);
		current += deltaTime;

	}

	private void updateObjects(float deltaTime) {
		this.enemy.update(deltaTime);
		if (current > 2)
			this.ally.update(deltaTime);
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
		render_chr_cry_enemy_gun();
		render_chr_enemy_ally_lance();
		
		gl.glDisable(GL10.GL_BLEND);
	}
	
	private void render_chr_enemy_ally_lance(){
		batcher.beginBatch(Manage_Assets.ttLoadingWindowCharecter);
		
		AllyAxeMan allyLance = ally;
		GL_TextureRegion _keyFrame;
		
		switch (allyLance.getStageFlag()) {
		case EnemyGunMan.OBJ_D_LEFT:
			_keyFrame = Manage_Assets.aniAttackLanceAlly.getKeyFrame(
					allyLance.getStateTime(), GL_Animation.ANIMATION_LOOPING);
			break;

		default:
			_keyFrame = Manage_Assets.aniAttackLanceAlly.getKeyFrame(
					allyLance.getStateTime(), GL_Animation.ANIMATION_LOOPING);
			break;
		}
		
		batcher.drawSprite(allyLance.position.x, allyLance.position.y,
				-180, 100, _keyFrame);
		
		batcher.endBatch();
	}
	

	private void render_chr_cry_enemy_gun() {
		batcher.beginBatch(Manage_Assets.ttLoadingWindowCharecter);

		EnemyGunMan enemyGunMan = enemy;
		GL_TextureRegion _keyFrame;

		switch (enemyGunMan.getStageFlag()) {
		case EnemyGunMan.OBJ_D_LEFT:
			_keyFrame = Manage_Assets.aniCryGunEnemy.getKeyFrame(
					enemyGunMan.getStateTime(), GL_Animation.ANIMATION_LOOPING);
			break;

		default:
			_keyFrame = Manage_Assets.aniCryGunEnemy.getKeyFrame(
					enemyGunMan.getStateTime(), GL_Animation.ANIMATION_LOOPING);
			break;
		}

		batcher.drawSprite(enemyGunMan.position.x, enemyGunMan.position.y,
				-180, 100, _keyFrame);

		batcher.endBatch();
	}

	private void render_chr_enemy_gun() {
		batcher.beginBatch(Manage_Assets.ttChrEnemyGun);

		EnemyGunMan enemyGunMan = enemy;
		GL_TextureRegion _keyFrame;

		switch (enemyGunMan.getStageFlag()) {
		case EnemyGunMan.OBJ_D_LEFT:
			_keyFrame = Manage_Assets.aniChrEnemyGunMov.getKeyFrame(
					enemyGunMan.getStateTime(), GL_Animation.ANIMATION_LOOPING);
			break;
		case EnemyGunMan.OBJ_D_ATTAK:
			_keyFrame = Manage_Assets.aniChrEnemyGunShutDown.getKeyFrame(
					enemyGunMan.getStateTime(), GL_Animation.ANIMATION_LOOPING);
			break;
		default:
			_keyFrame = Manage_Assets.aniChrEnemyGunMov.getKeyFrame(
					enemyGunMan.getStateTime(), GL_Animation.ANIMATION_LOOPING);
			break;
		}
		batcher.drawSprite(enemyGunMan.position.x, enemyGunMan.position.y,
				-180, 100, _keyFrame);

		batcher.endBatch();
	}

	@Override
	public void dispose() {
	}
}

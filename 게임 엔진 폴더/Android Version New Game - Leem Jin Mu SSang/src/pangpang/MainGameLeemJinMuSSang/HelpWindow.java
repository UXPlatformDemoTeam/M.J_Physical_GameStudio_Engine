package pangpang.MainGameLeemJinMuSSang;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import jrcengine.Basic.GL_Screen;
import jrcengine.GL.GL_Camera2D;
import jrcengine.GL.GL_SpriteBatcher;
import jrcengine.Interface.IFace_Game;
import jrcengine.Interface.IFace_Input.TouchEvent;
import jrcengine.Manage.Manage_Assets;
import jrcengine.Manage.Manage_Settings;
import jrcengine.Math.Math_Vector;

public class HelpWindow extends GL_Screen {
	private GL_Camera2D guiCam;
	private GL_SpriteBatcher batcher;
	private Math_Vector touchPoint;

	private float fStorySceneInterval = 6.5f;
	private final int HELPBOADRMAXNUM = 7;
	private int helpBoardNum;

	private float current;

	public HelpWindow(IFace_Game game) {
		super(game);

		this.guiCam = new GL_Camera2D(glGraphics, Screen_MainMenu.GAME_WIDTH,
				Screen_MainMenu.GAME_HEIGHT);
		this.touchPoint = new Math_Vector();
		this.batcher = new GL_SpriteBatcher(glGraphics, 100);
		this.current = 0;
		this.helpBoardNum = 0;
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
				helpBoardNum++;
			}

			if (event.type == TouchEvent.TOUCH_DOWN) {

			}
		}

		if (current > fStorySceneInterval) {
			current = 0f;
			helpBoardNum++;
		}

		if (helpBoardNum >= HELPBOADRMAXNUM)
			game.setScreen(new SettingWindow(game));

		current += deltaTime;
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

		batcher.beginBatch(Manage_Assets.ttHelpStoryBoardScene);

		if (0 == helpBoardNum)
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
					Screen_MainMenu.GAME_HEIGHT / 2,
					Screen_MainMenu.GAME_WIDTH, Screen_MainMenu.GAME_HEIGHT,
					Manage_Assets.ttgHelpStoryBoardScene01);

		else if (1 == helpBoardNum)
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
					Screen_MainMenu.GAME_HEIGHT / 2,
					Screen_MainMenu.GAME_WIDTH, Screen_MainMenu.GAME_HEIGHT,
					Manage_Assets.ttgHelpStoryBoardScene02);

		else if (2 == helpBoardNum)
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
					Screen_MainMenu.GAME_HEIGHT / 2,
					Screen_MainMenu.GAME_WIDTH, Screen_MainMenu.GAME_HEIGHT,
					Manage_Assets.ttgHelpStoryBoardScene05);

		else if (3 == helpBoardNum)
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
					Screen_MainMenu.GAME_HEIGHT / 2,
					Screen_MainMenu.GAME_WIDTH, Screen_MainMenu.GAME_HEIGHT,
					Manage_Assets.ttgHelpStoryBoardScene03);
	
		else if (4 == helpBoardNum)
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
					Screen_MainMenu.GAME_HEIGHT / 2,
					Screen_MainMenu.GAME_WIDTH, Screen_MainMenu.GAME_HEIGHT,
					Manage_Assets.ttgHelpStoryBoardScene04);
		else if (5 == helpBoardNum)
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
					Screen_MainMenu.GAME_HEIGHT / 2,
					Screen_MainMenu.GAME_WIDTH, Screen_MainMenu.GAME_HEIGHT,
					Manage_Assets.ttgHelpStoryBoardScene06);
		else if (6 == helpBoardNum)
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
					Screen_MainMenu.GAME_HEIGHT / 2,
					Screen_MainMenu.GAME_WIDTH, Screen_MainMenu.GAME_HEIGHT,
					Manage_Assets.ttgHelpStoryBoardScene07);

		batcher.endBatch();

		gl.glDisable(GL10.GL_BLEND);
	}

	@Override
	public void dispose() {
	}
}

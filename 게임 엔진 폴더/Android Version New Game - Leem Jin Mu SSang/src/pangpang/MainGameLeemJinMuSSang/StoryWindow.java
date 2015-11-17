package pangpang.MainGameLeemJinMuSSang;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import jrcengine.Basic.GL_Screen;
import jrcengine.GL.GL_Camera2D;
import jrcengine.GL.GL_SpriteBatcher;
import jrcengine.Interface.IFace_Game;
import jrcengine.Interface.IFace_Input.TouchEvent;
import jrcengine.Manage.Manage_Assets;
import jrcengine.Math.Math_Vector;

public class StoryWindow extends GL_Screen {
	private GL_Camera2D guiCam;
	private GL_SpriteBatcher batcher;
	private Math_Vector touchPoint;

	private float fStorySceneInterval = 6.5f;
	private final int STORYBOADRMAXNUM = 4;
	private int StoryBoardNum;

	private float current;

	private int initnum = 0;

	public StoryWindow(IFace_Game game) {
		super(game);

		this.guiCam = new GL_Camera2D(glGraphics, Screen_MainMenu.GAME_WIDTH,
				Screen_MainMenu.GAME_HEIGHT);
		this.touchPoint = new Math_Vector();
		this.batcher = new GL_SpriteBatcher(glGraphics, 100);
		this.current = 0;
		this.StoryBoardNum = 0;
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
				StoryBoardNum++;
			}

			if (event.type == TouchEvent.TOUCH_DOWN) {

			}
		}

		if (current > fStorySceneInterval) {
			current = 0f;
			StoryBoardNum++;
		}

		if (StoryBoardNum >= STORYBOADRMAXNUM
				&& Manage_Assets.isSuccessAssetLoading)
			game.setScreen(new Screen_MainMenu(game));

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

		batcher.beginBatch(Manage_Assets.ttStoryBoardScene);

		if (0 == StoryBoardNum)
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
					Screen_MainMenu.GAME_HEIGHT / 2,
					Screen_MainMenu.GAME_WIDTH, Screen_MainMenu.GAME_HEIGHT,
					Manage_Assets.ttgStoryBoardScene01);

		else if (1 == StoryBoardNum)
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
					Screen_MainMenu.GAME_HEIGHT / 2,
					Screen_MainMenu.GAME_WIDTH, Screen_MainMenu.GAME_HEIGHT,
					Manage_Assets.ttgStoryBoardScene02);

		else if (2 == StoryBoardNum)
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
					Screen_MainMenu.GAME_HEIGHT / 2,
					Screen_MainMenu.GAME_WIDTH, Screen_MainMenu.GAME_HEIGHT,
					Manage_Assets.ttgStoryBoardScene03);

		else if (3 == StoryBoardNum)
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
					Screen_MainMenu.GAME_HEIGHT / 2,
					Screen_MainMenu.GAME_WIDTH, Screen_MainMenu.GAME_HEIGHT,
					Manage_Assets.ttgStoryBoardScene04);
		else
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
					Screen_MainMenu.GAME_HEIGHT / 2,
					Screen_MainMenu.GAME_WIDTH, Screen_MainMenu.GAME_HEIGHT,
					Manage_Assets.ttgStoryBoardScene04);

		batcher.endBatch();

		gl.glDisable(GL10.GL_BLEND);
		if (1 == initnum)
			Manage_Assets.load01(glGame);
		else if (2 == initnum)
			Manage_Assets.load02(glGame);
		else if (3 == initnum)
			Manage_Assets.load03(glGame);
		else if (4 == initnum)
			Manage_Assets.load04(glGame);
		else if (5 == initnum)
			Manage_Assets.load05(glGame);
		else if (6 == initnum)
			Manage_Assets.load06(glGame);
		else if (7 == initnum)
			Manage_Assets.load07(glGame);
		else if (8 == initnum)
			Manage_Assets.load08(glGame);
		else if (9 == initnum)
			Manage_Assets.load09(glGame);

		initnum++;
	}

	@Override
	public void dispose() {
	}
}

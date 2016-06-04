// 선택 화면
package jrcengine.MainGame;

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
import android.content.pm.ActivityInfo;

public class Screen_MainMenu extends GL_Screen {

	GL_Camera2D guiCam;
	GL_SpriteBatcher batcher;

	Math_Vector touchPoint;
	String[] keyBoard;

	static boolean is_Debug = false;

	public Screen_MainMenu(IFace_Game game) {
		super(game);
		this.guiCam = new GL_Camera2D(glGraphics, Manage_Settings.GAME_WIDTH,
				Manage_Settings.GAME_HEIGHT);
		this.batcher = new GL_SpriteBatcher(glGraphics, 100);


		this.touchPoint = new Math_Vector();

	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

			if (event.type == TouchEvent.TOUCH_UP) {
				touchPoint.set(event.x, event.y);
				guiCam.touchToWorld(touchPoint);

				game.setScreen(new Screen_MainGame(game));

			}

			if (event.type == TouchEvent.TOUCH_DOWN) {
				touchPoint.set(event.x, event.y);
				guiCam.touchToWorld(touchPoint);

			}
		}

	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);

		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		batcher.beginBatch(Manage_Assets.gtJerryCoLogo);
		batcher.drawSprite(Manage_Settings.GAME_WIDTH / 2,
				Manage_Settings.GAME_HEIGHT / 2, 10,
				10, Manage_Assets.gsJerrycoLogo);
		batcher.endBatch();

		gl.glDisable(GL10.GL_BLEND);
	}

	@Override
	public void pause() {
		Manage_Settings.save(game.getFileIO());
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}

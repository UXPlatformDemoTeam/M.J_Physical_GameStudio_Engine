package pangpang.MainGameLeemJinMuSSang;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import jrcengine.Basic.GL_Game;
import jrcengine.Interface.Abstract_Screen;
import jrcengine.Manage.Manage_Assets;
import jrcengine.Manage.Manage_Settings;
import android.app.Activity;

public class Game_StartManager extends GL_Game {

	boolean firstTimeCreate = true;
	static Activity activity;

	public Abstract_Screen getStartScreen() {
		Game_StartManager.activity = this;
		return new StoryWindow(this);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);

		if (firstTimeCreate) {
			Manage_Settings.load(getFileIO());
			Manage_Assets.init(this);
			firstTimeCreate = false;
		} else {
			Manage_Assets.reload();
		}

	}

	@Override
	public void onPause() {
		super.onPause();
		if (Manage_Settings.isSoundMusicVolume) {
			Manage_Assets.musicFullWindow.pause();
			Manage_Assets.musicGameMainBackground.pause();
		}
	}

}

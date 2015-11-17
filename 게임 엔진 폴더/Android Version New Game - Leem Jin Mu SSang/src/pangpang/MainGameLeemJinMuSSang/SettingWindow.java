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
import jrcengine.Math.Math_Overlap;
import jrcengine.Math.Math_Overlap_Circle;
import jrcengine.Math.Math_Overlap_Rectangle;
import jrcengine.Math.Math_Vector;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

public class SettingWindow extends GL_Screen {
	private GL_Camera2D guiCam;
	private GL_SpriteBatcher batcher;
	private Math_Vector touchPoint;

	private int MAXBACKGROUNDSOUNDVOLUME = 10;
	private int MAXEFFECTSOUNDVOLUME = 10;

	public static boolean isVibration = false;
	public static boolean isLocation = false;

	private boolean isXbutton;

	// up button
	private boolean isBackGroundSoundUpButton;
	private boolean isEffectSoundUpButton;

	// down button

	private boolean isBackGroundSoundDownButton;
	private boolean isEffectSoundDownButton;

	// buttom button

	private boolean isVibrationButton;
	private boolean isLocationButton;

	// is help button
	private boolean isHelpBook;

	private Math_Overlap_Rectangle XbuttonBounds;

	// up button
	private Math_Overlap_Circle BackGroundSoundUpButtonBounds;
	private Math_Overlap_Circle EffectSoundUpButtonBounds;

	// down button
	private Math_Overlap_Circle BackGroundSoundDownButtonBounds;
	private Math_Overlap_Circle EffectSoundDownButtonBounds;

	// vibration and location
	private Math_Overlap_Rectangle VibrationBounds;
	private Math_Overlap_Rectangle LocationBouns;

	// help
	private Math_Overlap_Rectangle helpBounds;

	// vibe
	private Vibrator vibe;

	public SettingWindow(IFace_Game game) {
		super(game);

		this.guiCam = new GL_Camera2D(glGraphics, Screen_MainMenu.GAME_WIDTH,
				Screen_MainMenu.GAME_HEIGHT);
		this.touchPoint = new Math_Vector();
		this.batcher = new GL_SpriteBatcher(glGraphics, 1);

		this.XbuttonBounds = new Math_Overlap_Rectangle(
				Screen_MainMenu.GAME_WIDTH - 35,
				Screen_MainMenu.GAME_HEIGHT - 35, 30, 30);
		this.BackGroundSoundUpButtonBounds = new Math_Overlap_Circle(
				Screen_MainMenu.GAME_WIDTH / 2 + 14,
				Screen_MainMenu.GAME_HEIGHT / 2 + 60 + 7, 11);
		this.EffectSoundUpButtonBounds = new Math_Overlap_Circle(
				Screen_MainMenu.GAME_WIDTH / 2 + 14,
				Screen_MainMenu.GAME_HEIGHT / 2 + 5, 11);

		this.BackGroundSoundDownButtonBounds = new Math_Overlap_Circle(60 + 27,
				Screen_MainMenu.GAME_HEIGHT / 2 + 60 + 7, 11);
		this.EffectSoundDownButtonBounds = new Math_Overlap_Circle(60 + 27,
				Screen_MainMenu.GAME_HEIGHT / 2 + 5, 11);

		this.VibrationBounds = new Math_Overlap_Rectangle(114 - 50,
				Screen_MainMenu.GAME_HEIGHT / 2 - 80 - 32.5f, 100, 65);
		this.LocationBouns = new Math_Overlap_Rectangle(224 - 50,
				Screen_MainMenu.GAME_HEIGHT / 2 - 80 - 32.5f, 100, 65);

		this.helpBounds = new Math_Overlap_Rectangle(300,
				Screen_MainMenu.GAME_HEIGHT / 2 - 80, 100, 160);
		this.vibe = (Vibrator) Game_StartManager.activity
				.getSystemService(Context.VIBRATOR_SERVICE);

		initNegative();
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

				// xButton
				if (Math_Overlap.pointInRectangle(XbuttonBounds, touchPoint)
						&& true == isXbutton) {
					isXbutton = false;
					Manage_Settings.save(game.getFileIO());
					game.setScreen(new Screen_MainMenu(game));
					return;
				}

				// vibration button
				if (Math_Overlap.pointInRectangle(VibrationBounds, touchPoint)
						&& true == isVibrationButton) {
					isVibrationButton = false;
					isVibration = !isVibration;
					vibe.vibrate(200);
					return;
				}

				// location button
				if (Math_Overlap.pointInRectangle(LocationBouns, touchPoint)
						&& true == isLocationButton) {
					isLocationButton = false;
					isLocation = !isLocation;
					return;
				}

				// background up
				if (Math_Overlap.pointInCircle(BackGroundSoundUpButtonBounds,
						touchPoint) && true == isBackGroundSoundUpButton) {
					if (Manage_Settings.nBackGroundSoundVolume < MAXBACKGROUNDSOUNDVOLUME) {
						Manage_Settings.nBackGroundSoundVolume++;
						Manage_Settings.fMusicVolume = (float) Manage_Settings.nBackGroundSoundVolume / 10;
						Manage_Assets.settingMusicVolume();
					}
					isBackGroundSoundUpButton = false;
					return;
				}

				// effect up
				if (Math_Overlap.pointInCircle(EffectSoundUpButtonBounds,
						touchPoint) && true == isEffectSoundUpButton) {
					if (Manage_Settings.nEffectSoundVolume < MAXEFFECTSOUNDVOLUME) {
						Manage_Settings.nEffectSoundVolume++;
						Manage_Settings.fSoundVolume = (float) Manage_Settings.nEffectSoundVolume / 10;
						Manage_Assets.playSound(Manage_Assets.soundClick);
					}
					isEffectSoundUpButton = false;
					return;
				}

				// background down
				if (Math_Overlap.pointInCircle(BackGroundSoundDownButtonBounds,
						touchPoint) && true == isBackGroundSoundDownButton) {
					if (Manage_Settings.nBackGroundSoundVolume > 0) {
						Manage_Settings.nBackGroundSoundVolume--;
						Manage_Settings.fMusicVolume = (float) Manage_Settings.nBackGroundSoundVolume / 10;
						Manage_Assets.settingMusicVolume();
					}
					isBackGroundSoundDownButton = false;
					return;
				}

				// effect down
				if (Math_Overlap.pointInCircle(EffectSoundDownButtonBounds,
						touchPoint) && true == isEffectSoundDownButton) {
					if (Manage_Settings.nEffectSoundVolume > 0) {
						Manage_Settings.nEffectSoundVolume--;
						Manage_Settings.fSoundVolume = (float) Manage_Settings.nEffectSoundVolume / 10;
						Manage_Assets.playSound(Manage_Assets.soundClick);
					}
					isEffectSoundDownButton = false;
					return;
				}

				// help button
				if (Math_Overlap.pointInRectangle(helpBounds, touchPoint)
						&& true == isHelpBook) {
					game.setScreen(new HelpWindow(game));
					isHelpBook = false;
					return;
				}

				initNegative();
			}

			if (event.type == TouchEvent.TOUCH_DOWN) {
				// x button
				if (Math_Overlap.pointInRectangle(XbuttonBounds, touchPoint)) {

					isXbutton = true;
					return;
				}

				// backgroundsound
				if (Math_Overlap.pointInCircle(BackGroundSoundUpButtonBounds,
						touchPoint)) {

					isBackGroundSoundUpButton = true;
					return;
				}

				// effectsound
				if (Math_Overlap.pointInCircle(EffectSoundUpButtonBounds,
						touchPoint)) {

					isEffectSoundUpButton = true;
					return;
				}

				// background down
				if (Math_Overlap.pointInCircle(BackGroundSoundDownButtonBounds,
						touchPoint)) {

					isBackGroundSoundDownButton = true;
					return;
				}
				// effect down
				if (Math_Overlap.pointInCircle(EffectSoundDownButtonBounds,
						touchPoint)) {

					isEffectSoundDownButton = true;
					return;
				}

				// vibration button
				if (Math_Overlap.pointInRectangle(VibrationBounds, touchPoint)) {
					isVibrationButton = true;
					return;
				}

				// location button
				if (Math_Overlap.pointInRectangle(LocationBouns, touchPoint)) {
					isLocationButton = true;
					return;
				}

				// help button
				if (Math_Overlap.pointInRectangle(helpBounds, touchPoint)) {
					isHelpBook = true;
					return;
				}

				initNegative();

			}
		}

	}

	private void initNegative() {
		this.isXbutton = false;
		this.isBackGroundSoundDownButton = false;
		this.isBackGroundSoundUpButton = false;
		this.isEffectSoundDownButton = false;
		this.isEffectSoundUpButton = false;
		this.isVibrationButton = false;
		this.isLocationButton = false;
		this.isHelpBook = false;
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

		// Draw page

		batcher.beginBatch(Manage_Assets.ttSettingWindowsAsset01);
		batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
				Screen_MainMenu.GAME_HEIGHT / 2, Screen_MainMenu.GAME_WIDTH,
				Screen_MainMenu.GAME_HEIGHT, Manage_Assets.ttgSettingWindowPage);
		batcher.endBatch();

		// Draw Background

		// Draw ContorlPanel
		batcher.beginBatch(Manage_Assets.ttSettingWindowsAsset02);
		batcher.drawSprite(170, Screen_MainMenu.GAME_HEIGHT / 2 + 50, 200, 140,
				Manage_Assets.ttgSettingWindowControlPanel);
		batcher.endBatch();

		// draw Setting text
		batcher.beginBatch(Manage_Assets.ttSettingWindowsAsset01);
		batcher.drawSprite(Screen_MainMenu.GAME_WIDTH - 55,
				Screen_MainMenu.GAME_HEIGHT / 2 + 100, 60, 40,
				Manage_Assets.ttgSettingWindowSettingText);
		batcher.endBatch();

		// draw BackGroundSound -----------------------------------------

		// draw BackGroundSound text
		batcher.beginBatch(Manage_Assets.ttSettingWindowsAsset01);
		batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2 - 111,
				Screen_MainMenu.GAME_HEIGHT / 2 + 90 + 5, 50, 30,
				Manage_Assets.ttgSettingWindowBackGroundSoundText);
		batcher.endBatch();

		// draw BackGroundSound guage
		batcher.beginBatch(Manage_Assets.ttSettingWindowsAsset01);
		DrawGuage(Screen_MainMenu.GAME_WIDTH / 2 - 70,
				Screen_MainMenu.GAME_HEIGHT / 2 + 60 + 7,
				Manage_Settings.nBackGroundSoundVolume);
		batcher.endBatch();

		// draw BackGroundSound Down Button
		batcher.beginBatch(Manage_Assets.ttSettingWindowsAsset01);
		if (false == this.isBackGroundSoundDownButton)
			batcher.drawSprite(60 + 27,
					Screen_MainMenu.GAME_HEIGHT / 2 + 60 + 7, 22, 22,
					Manage_Assets.ttgSettingWindowVolumeDown);
		else
			batcher.drawSprite(60 + 27,
					Screen_MainMenu.GAME_HEIGHT / 2 + 60 + 7, 22, 22,
					Manage_Assets.ttgSettingWindowVolumeDownPressed);
		batcher.endBatch();

		// draw BackGroundSound Up Button
		batcher.beginBatch(Manage_Assets.ttSettingWindowsAsset01);
		if (false == this.isBackGroundSoundUpButton)
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2 + 14,
					Screen_MainMenu.GAME_HEIGHT / 2 + 60 + 7, 22, 22,
					Manage_Assets.ttgSettingWindowVolumeUp);
		else
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2 + 14,
					Screen_MainMenu.GAME_HEIGHT / 2 + 60 + 7, 22, 22,
					Manage_Assets.ttgSettingWindowVolumeUpPressed);
		batcher.endBatch();

		// draw EffectSound -----------------------------------------

		// draw EffectSound text
		batcher.beginBatch(Manage_Assets.ttSettingWindowsAsset01);
		batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2 - 111,
				Screen_MainMenu.GAME_HEIGHT / 2 + 30 + 5, 50, 30,
				Manage_Assets.ttgSettingWindowEffectText);
		batcher.endBatch();

		// draw EffectSound guage
		batcher.beginBatch(Manage_Assets.ttSettingWindowsAsset01);
		DrawGuage(Screen_MainMenu.GAME_WIDTH / 2 - 70,
				Screen_MainMenu.GAME_HEIGHT / 2 + 5,
				Manage_Settings.nEffectSoundVolume);
		batcher.endBatch();

		// draw EffectSound Down Button
		batcher.beginBatch(Manage_Assets.ttSettingWindowsAsset01);
		if (false == this.isEffectSoundDownButton)
			batcher.drawSprite(60 + 27, Screen_MainMenu.GAME_HEIGHT / 2 + 5,
					22, 22, Manage_Assets.ttgSettingWindowVolumeDown);
		else
			batcher.drawSprite(60 + 27, Screen_MainMenu.GAME_HEIGHT / 2 + 5,
					22, 22, Manage_Assets.ttgSettingWindowVolumeDownPressed);
		batcher.endBatch();

		// draw EffectSound Up Button
		batcher.beginBatch(Manage_Assets.ttSettingWindowsAsset01);

		if (false == this.isEffectSoundUpButton)
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2 + 14,
					Screen_MainMenu.GAME_HEIGHT / 2 + 5, 22, 22,
					Manage_Assets.ttgSettingWindowVolumeUp);
		else
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2 + 14,
					Screen_MainMenu.GAME_HEIGHT / 2 + 5, 22, 22,
					Manage_Assets.ttgSettingWindowVolumeUpPressed);
		batcher.endBatch();

		// underbutton--------------------------------------------------------

		// Draw Vibration
		batcher.beginBatch(Manage_Assets.ttSettingWindowsAsset01);
		if (false == SettingWindow.isVibration) {
			Manage_Settings.isVibration = true;
			batcher.drawSprite(114, Screen_MainMenu.GAME_HEIGHT / 2 - 80, 100,
					65, Manage_Assets.ttgSettingWindowVibration);
		} else {
			Manage_Settings.isVibration = false;
			batcher.drawSprite(114, Screen_MainMenu.GAME_HEIGHT / 2 - 80, 100,
					65, Manage_Assets.ttgSettingWindowVibrationPressed);
		}
		batcher.endBatch();

		// Draw BackGroundSound
		batcher.beginBatch(Manage_Assets.ttSettingWindowsAsset01);
		if (false == SettingWindow.isLocation) {
			Manage_Settings.isSoundMusicVolume = true;
			batcher.drawSprite(224, Screen_MainMenu.GAME_HEIGHT / 2 - 80, 100,
					65, Manage_Assets.ttgSettingWindowSound);
			Manage_Assets.settingMusicOnOff();
		} else {
			Manage_Settings.isSoundMusicVolume = false;
			batcher.drawSprite(224, Screen_MainMenu.GAME_HEIGHT / 2 - 80, 100,
					65, Manage_Assets.ttgSettingWindowSoundPressed);
			Manage_Assets.settingMusicOnOff();
		}
		batcher.endBatch();

		// Draw Help
		batcher.beginBatch(Manage_Assets.ttSettingWindowsAsset02);
		if (false == isHelpBook)
			batcher.drawSprite(350, Screen_MainMenu.GAME_HEIGHT / 2, 100, 160,
					Manage_Assets.ttgSettingWindowHelp);
		else
			batcher.drawSprite(350, Screen_MainMenu.GAME_HEIGHT / 2, 100, 160,
					Manage_Assets.ttgSettingWindowHelpPressed);
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

	private void DrawGuage(int x, int y, int num) {
		if (0 == num)
			batcher.drawSprite(x, y, 140, 23,
					Manage_Assets.ttgSettingWindowVolume00);
		else if (1 == num)
			batcher.drawSprite(x, y, 140, 23,
					Manage_Assets.ttgSettingWindowVolume01);
		else if (2 == num)
			batcher.drawSprite(x, y, 140, 23,
					Manage_Assets.ttgSettingWindowVolume02);
		else if (3 == num)
			batcher.drawSprite(x, y, 140, 23,
					Manage_Assets.ttgSettingWindowVolume03);
		else if (4 == num)
			batcher.drawSprite(x, y, 140, 23,
					Manage_Assets.ttgSettingWindowVolume04);
		else if (5 == num)
			batcher.drawSprite(x, y, 140, 23,
					Manage_Assets.ttgSettingWindowVolume05);
		else if (6 == num)
			batcher.drawSprite(x, y, 140, 23,
					Manage_Assets.ttgSettingWindowVolume06);
		else if (7 == num)
			batcher.drawSprite(x, y, 140, 23,
					Manage_Assets.ttgSettingWindowVolume07);
		else if (8 == num)
			batcher.drawSprite(x, y, 140, 23,
					Manage_Assets.ttgSettingWindowVolume08);
		else if (9 == num)
			batcher.drawSprite(x, y, 140, 23,
					Manage_Assets.ttgSettingWindowVolume09);
		else if (10 == num)
			batcher.drawSprite(x, y, 140, 23,
					Manage_Assets.ttgSettingWindowVolume10);
		else
			Log.e("ERRORCODE03", "OvertheeffectSoundGuage");

	}

	@Override
	public void dispose() {
	}
}

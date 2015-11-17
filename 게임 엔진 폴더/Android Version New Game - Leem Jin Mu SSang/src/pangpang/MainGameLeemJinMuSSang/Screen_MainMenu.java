// 선택 화면
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
import jrcengine.Manage.Manage_Assets;
import jrcengine.Manage.Manage_Settings;
import jrcengine.Math.Math_Overlap;
import jrcengine.Math.Math_Overlap_Rectangle;
import jrcengine.Math.Math_Vector;

public class Screen_MainMenu extends GL_Screen {

	// 게임 맴 사이즈
	static final int GAME_WIDTH = 480;
	static final int GAME_HEIGHT = 320;

	/* 화면 초기 세팅 */
	final boolean TGSETTING = false;

	/* 화면 버튼 컨디션 */
	private boolean tgGameButton;
	private boolean tgOptionButton;
	private boolean isXbutton;

	float scrFireupdateTime;

	// 충돌체 좌표
	private Math_Overlap_Rectangle morGameStartButtons;
	private Math_Overlap_Rectangle moroptionsButtons;
	private Math_Overlap_Rectangle XbuttonBounds;

	private float fMoveObjectSpeed;

	// slogun position
	private int nSlogunPositionX;
	private float nSlogunPositionY;

	// start button position
	private float nStartGamePositionX;
	private int nStartGamePositionY;

	// setting button position
	private float nSettingGamePositionX;
	private int nSettingGamePositionY;

	private GL_Camera2D guiCam;
	private GL_SpriteBatcher batcher;

	private Math_Vector touchPoint;

	static boolean is_Debug = false;

	private GL_TextureRegion keyFrame;

	public static boolean isFirstLoading = true;

	public Screen_MainMenu(IFace_Game game) {
		super(game);
		this.guiCam = new GL_Camera2D(glGraphics, GAME_WIDTH, GAME_HEIGHT);
		this.batcher = new GL_SpriteBatcher(glGraphics, 100);

		this.morGameStartButtons = new Math_Overlap_Rectangle(85, 40, 100, 40);
		this.moroptionsButtons = new Math_Overlap_Rectangle(305, 40, 100, 40);
		this.XbuttonBounds = new Math_Overlap_Rectangle(
				Screen_MainMenu.GAME_WIDTH - 35,
				Screen_MainMenu.GAME_HEIGHT - 35, 30, 30);

		this.tgGameButton = false;
		this.tgOptionButton = false;
		this.isXbutton = false;

		this.nSlogunPositionX = GAME_WIDTH / 2;
		this.nSlogunPositionY = GAME_HEIGHT + 125f;

		this.nStartGamePositionX = -50f;
		this.nStartGamePositionY = 60;

		this.nSettingGamePositionX = GAME_WIDTH + 50f;
		this.nSettingGamePositionY = 60;

		this.fMoveObjectSpeed = 80f;

		this.scrFireupdateTime = 0f;
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

				if (Math_Overlap.pointInRectangle(morGameStartButtons,
						touchPoint) && this.tgGameButton == true) {
					// game.setScreen(new SettingWindow(game));
					this.tgGameButton = false;
					game.setScreen(new StageWindow(game));
					return;
				}

				// xButton
				if (Math_Overlap.pointInRectangle(XbuttonBounds, touchPoint)
						&& true == isXbutton) {
					isXbutton = false;
					this.game.exit();
					return;
				}

				if (Math_Overlap
						.pointInRectangle(moroptionsButtons, touchPoint)
						&& this.tgOptionButton == true) {
					game.setScreen(new SettingWindow(game));
					this.tgOptionButton = false;
					return;
				}

				// 초기화 버튼 스테이트
				this.isXbutton = false;
				this.tgGameButton = false;
				this.tgOptionButton = false;
			}

			if (event.type == TouchEvent.TOUCH_DOWN) {
				touchPoint.set(event.x, event.y);
				guiCam.touchToWorld(touchPoint);

				if (Math_Overlap.pointInRectangle(morGameStartButtons,
						touchPoint)) {
					this.tgGameButton = true;
					return;
				}
				if (Math_Overlap
						.pointInRectangle(moroptionsButtons, touchPoint)) {
					this.tgOptionButton = true;
					return;
				}

				if (Math_Overlap.pointInRectangle(XbuttonBounds, touchPoint)) {

					isXbutton = true;
					return;
				}

				this.isXbutton = false;
				this.tgGameButton = false;
				this.tgOptionButton = false;

				// button position
				this.nSlogunPositionY = GAME_HEIGHT / 2;
				this.nStartGamePositionX = 135f;
				this.nSettingGamePositionX = 355;

				Screen_MainMenu.isFirstLoading = false;

			}
		}

	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);

		batcher.beginBatch(Manage_Assets.ttScrMainMenu);
		batcher.drawSprite(GAME_WIDTH / 2, GAME_HEIGHT / 2, GAME_WIDTH,
				GAME_HEIGHT, Manage_Assets.ttgScrScrMainMenu);
		batcher.endBatch();

		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		// draw slogun

		batcher.beginBatch(Manage_Assets.ttIntroSrogun);

		this.keyFrame = Manage_Assets.aniIntroSrogun.getKeyFrame(
				scrFireupdateTime, GL_Animation.ANIMATION_LOOPING);

		batcher.drawSprite(nSlogunPositionX, nSlogunPositionY, 40, 250,
				this.keyFrame);

		batcher.endBatch();

		if (isFirstLoading == true) {
			if (GAME_HEIGHT / 2 < nSlogunPositionY) {
				nSlogunPositionY -= fMoveObjectSpeed * deltaTime;
			}
		} else {
			this.nSlogunPositionY = GAME_HEIGHT / 2;
			this.nStartGamePositionX = 135f;
			this.nSettingGamePositionX = 355;
		}

		// draw point

		drawPoint();

		// draw Fire

		batcher.beginBatch(Manage_Assets.ttScrMainFire);

		this.keyFrame = Manage_Assets.aniScrMainMenuFire.getKeyFrame(
				scrFireupdateTime, GL_Animation.ANIMATION_LOOPING);

		batcher.drawSprite(GAME_WIDTH / 2, GAME_HEIGHT / 2, GAME_WIDTH,
				GAME_HEIGHT, this.keyFrame);

		batcher.endBatch();

		// start button
		if (this.tgGameButton == TGSETTING) {
			batcher.beginBatch(Manage_Assets.ttIntroButton);
			batcher.drawSprite(nStartGamePositionX, nStartGamePositionY, 100,
					40, Manage_Assets.ttgIntroGameStartButton);
			batcher.endBatch();
		} else {
			batcher.beginBatch(Manage_Assets.ttIntroButton);
			batcher.drawSprite(nStartGamePositionX, nStartGamePositionY, 100,
					40, Manage_Assets.ttgIntroGameStartButtonPressed);
			batcher.endBatch();

		}

		if (true == isFirstLoading) {
			if (135 > nStartGamePositionX) {
				nStartGamePositionX += fMoveObjectSpeed * deltaTime;
			}
		}

		// control button
		if (this.tgOptionButton == TGSETTING) {
			batcher.beginBatch(Manage_Assets.ttIntroButton);
			batcher.drawSprite(nSettingGamePositionX, nSettingGamePositionY,
					100, 40, Manage_Assets.ttgIntroGameSettingButton);
			batcher.endBatch();
		} else {
			batcher.beginBatch(Manage_Assets.ttIntroButton);
			batcher.drawSprite(nSettingGamePositionX, nSettingGamePositionY,
					100, 40, Manage_Assets.ttgIntroGameSettingButtonPressed);
			batcher.endBatch();

		}

		if (isFirstLoading == true) {
			if (355 < nSettingGamePositionX) {
				nSettingGamePositionX -= fMoveObjectSpeed * deltaTime;
			}
		}

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

		scrFireupdateTime += deltaTime;
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

	private void drawPoint() {
		// draw point 02

		if (1 <= StageWindow.SELECTSTABEMAXNUM) {
			batcher.beginBatch(Manage_Assets.ttScrMainMenuPoint);
			batcher.drawSprite(GAME_WIDTH / 2 + 100, GAME_HEIGHT / 2 - 30, 25,
					25, Manage_Assets.ttgScrMainMenuPoint);
			batcher.endBatch();
		}

		// draw point 03
		if (2 <= StageWindow.SELECTSTABEMAXNUM) {
			batcher.beginBatch(Manage_Assets.ttScrMainMenuPoint);
			batcher.drawSprite(GAME_WIDTH / 2 + 60, GAME_HEIGHT / 2 - 20, 25,
					25, Manage_Assets.ttgScrMainMenuPoint);
			batcher.endBatch();
		}
		// draw point 04
		if (3 <= StageWindow.SELECTSTABEMAXNUM) {
			batcher.beginBatch(Manage_Assets.ttScrMainMenuPoint);
			batcher.drawSprite(GAME_WIDTH / 2 + 30, GAME_HEIGHT / 2 - 40, 25,
					25, Manage_Assets.ttgScrMainMenuPoint);
			batcher.endBatch();
		}
		// draw point 05
		if (4 <= StageWindow.SELECTSTABEMAXNUM) {
			batcher.beginBatch(Manage_Assets.ttScrMainMenuPoint);
			batcher.drawSprite(GAME_WIDTH / 2, GAME_HEIGHT / 2 - 80, 25, 25,
					Manage_Assets.ttgScrMainMenuPoint);
			batcher.endBatch();
		}
		// draw point 06
		if (5 <= StageWindow.SELECTSTABEMAXNUM) {
			batcher.beginBatch(Manage_Assets.ttScrMainMenuPoint);
			batcher.drawSprite(70, GAME_HEIGHT / 2 - 80, 25, 25,
					Manage_Assets.ttgScrMainMenuPoint);
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

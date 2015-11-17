// 실제 게임 화면(플레이어가 실제 즐길 수 있는 화면)
package pangpang.MainGameLeemJinMuSSang;

import java.util.List;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import pangpang.MainGameLeemJinMuSSang.UnitInformation.ALLY.ArcherMan;
import pangpang.MainGameLeemJinMuSSang.UnitInformation.ALLY.AxeMan;
import pangpang.MainGameLeemJinMuSSang.UnitInformation.ALLY.MonkMan;
import pangpang.MainGameLeemJinMuSSang.UnitInformation.ALLY.SpearMan;
import jrcengine.Basic.GL_Screen;
import jrcengine.Basic.Log_TimeCounter;
import jrcengine.GL.GL_Animation;
import jrcengine.GL.GL_Camera2D;
import jrcengine.GL.GL_SpriteBatcher;
import jrcengine.GL.GL_TextureRegion;
import jrcengine.Interface.IFace_Game;
import jrcengine.Interface.IFace_Input.TouchEvent;
import jrcengine.Manage.Manage_Assets;
import jrcengine.Manage.Manage_Settings;
import jrcengine.Math.Math_Overlap;
import jrcengine.Math.Math_Overlap_Circle;
import jrcengine.Math.Math_Overlap_Rectangle;
import jrcengine.Math.Math_Vector;

public class Screen_MainGame extends GL_Screen {
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;

	public static final int nMultipleNumberSizeOfMap = 4;
	public static boolean iSmotionEnd = true;

	public static int state;

	private Log_TimeCounter fpsCounter;

	private GL_Camera2D guiCam;
	private GL_SpriteBatcher batcher;
	private MainGame_Manager manage;
	private MainGame_Renderer renderer;
	private Math_Vector touchPoint;

	private Random rnd;

	public static float fButtonCurrentTime01 = 0f;
	public static float fButtonCurrentTime02 = 0f;
	public static float fButtonCurrentTime03 = 0f;
	public static float fButtonCurrentTime04 = 0f;
	public static float fButtonCurrentTime05 = 0f;
	public static float fButtonCurrentTime06 = 0f;

	/* 화면 버튼 컨디션 */
	public static boolean tgGameSkillbutton01 = false;
	public static boolean tgGameSkillbutton02 = false;
	public static boolean tgGameSkillbutton03 = false;
	public static boolean tgGameSkillbutton04 = false;
	public static boolean tgGameSkillbutton05 = false;
	public static boolean tgGameSkillbutton06 = false;

	/* 유닛 생성 버튼 컨디션 */
	public static boolean tgGameGenerateUnitButtonAXE = false;
	public static boolean tgGameGenerateUnitButtonMONK = false;
	public static boolean tgGameGenerateUnitButtonSPEAR = false;
	public static boolean tgGameGenerateUnitButtonGUN = false;
	public static boolean tgGameGenerateUnitButtonSWORD = false;
	public static boolean tgGameGenerateUnitButtonARCHER = false;

	public static boolean tgGameGenerateUnitButtonAXEACTIVITY = true;
	public static boolean tgGameGenerateUnitButtonMONKACTIVITY = true;
	public static boolean tgGameGenerateUnitButtonSPEARACTIVITY = true;
	public static boolean tgGameGenerateUnitButtonGUNACTIVITY = false;
	public static boolean tgGameGenerateUnitButtonSWORDACTIVITY = false;
	public static boolean tgGameGenerateUnitButtonARCHERACTIVITY = true;

	/* 게임 정지 버튼 */
	private boolean isGameTerminate;
	private boolean isGameContinue;

	// 유닛 스킬 버튼

	private boolean tgGameSkillbuttonAni01;
	private boolean tgGameSkillbuttonAni02;
	private boolean tgGameSkillbuttonAni03;
	private boolean tgGameSkillbuttonAni04;
	private boolean tgGameSkillbuttonAni05;
	private boolean tgGameSkillbuttonAni06;

	// unit Generate Button

	// 종료씬 버튼 클릭
	private boolean isBackButtonClick;
	private boolean isNextButtonClick;

	// move button event

	private boolean isLeftButtonClick;
	private boolean isRightButtonClick;

	// previous score value
	private int nPreviousScore;

	// Game End Score Value
	private int nEndingScore;

	// Previous Coin
	private int nPreviousCoin;

	// Previous End COin
	private int nEndingCoin;

	// x button

	private boolean isXbutton;
	private Math_Overlap_Rectangle XbuttonBounds;

	// 충돌 강체 영역
	private Math_Overlap_Circle morGameSkillButton01;
	private Math_Overlap_Circle morGameSkillButton02;
	private Math_Overlap_Circle morGameSkillButton03;
	private Math_Overlap_Circle morGameSkillButton04;
	private Math_Overlap_Circle morGameSkillButton05;
	private Math_Overlap_Circle morGameSkillButton06;

	// 충돌 강체 영역 시클

	private Math_Overlap_Circle morGameSkillButtonAXE;
	private Math_Overlap_Circle morGameSkillButtonMONK;
	private Math_Overlap_Circle morGameSkillButtonSPEAR;
	private Math_Overlap_Circle morGameSkillButtonGUN;
	private Math_Overlap_Circle morGameSkillButtonSWORD;
	private Math_Overlap_Circle morGameSkillButtonARCHER;

	private Math_Overlap_Circle leftMoveButton;
	private Math_Overlap_Circle rightMoveButton;
	private Math_Overlap_Rectangle backButtonBounds;
	private Math_Overlap_Rectangle nextButtonBounds;

	// 게임 정지 버튼

	private Math_Overlap_Rectangle gameTerminateButton;
	private Math_Overlap_Rectangle gameContinueButton;

	private int GameClearWindowWidth = 250;
	private int GameClearWindowHeight = 200;

	private HpBar hpbar;

	private float fUpdateTime;

	private GL_TextureRegion _keyFrame;

	public Screen_MainGame(IFace_Game game) {
		super(game);
		Screen_MainGame.state = GAME_RUNNING;

		this.guiCam = new GL_Camera2D(glGraphics, Screen_MainMenu.GAME_WIDTH,
				Screen_MainMenu.GAME_HEIGHT);
		this.batcher = new GL_SpriteBatcher(glGraphics, 1000);

		this.fpsCounter = new Log_TimeCounter();
		this.touchPoint = new Math_Vector();

		this.manage = new MainGame_Manager(Screen_MainMenu.GAME_WIDTH,
				Screen_MainMenu.GAME_HEIGHT);
		this.renderer = new MainGame_Renderer(glGraphics, batcher, manage);

		this.morGameSkillButton01 = new Math_Overlap_Circle(
				manage.get_World_Width() - 50, 50, 25);
		this.morGameSkillButton02 = new Math_Overlap_Circle(
				manage.get_World_Width() - 83, 20, 17.5f);
		this.morGameSkillButton03 = new Math_Overlap_Circle(
				manage.get_World_Width() - 20, 83, 17.5f);
		this.morGameSkillButton04 = new Math_Overlap_Circle(
				manage.get_World_Width() - 18.5f, 18.5f, 17.5f);
		this.morGameSkillButton05 = new Math_Overlap_Circle(
				manage.get_World_Width() - 92, 56, 17.5f);
		this.morGameSkillButton06 = new Math_Overlap_Circle(
				manage.get_World_Width() - 56, 92, 17.5f);

		this.morGameSkillButtonAXE = new Math_Overlap_Circle(16.5f, 30 + 59, 33);
		this.morGameSkillButtonMONK = new Math_Overlap_Circle(16.5f, 63 + 59,
				33);
		this.morGameSkillButtonSPEAR = new Math_Overlap_Circle(16.5f, 129 + 59,
				33);
		this.morGameSkillButtonGUN = new Math_Overlap_Circle(16.5f, 162 + 59,
				33);
		this.morGameSkillButtonSWORD = new Math_Overlap_Circle(16.5f, 195 + 59,
				33);
		this.morGameSkillButtonARCHER = new Math_Overlap_Circle(16.5f, 96 + 59,
				33);

		this.backButtonBounds = new Math_Overlap_Rectangle(124, 20, 80, 44);
		this.nextButtonBounds = new Math_Overlap_Rectangle(277, 12, 80, 44);

		this.leftMoveButton = new Math_Overlap_Circle(50, 50, 25);
		this.rightMoveButton = new Math_Overlap_Circle(125, 50, 25);
		this.XbuttonBounds = new Math_Overlap_Rectangle(
				Screen_MainMenu.GAME_WIDTH - 35,
				Screen_MainMenu.GAME_HEIGHT - 35, 30, 30);

		gameTerminateButton = new Math_Overlap_Rectangle(
				manage.get_World_Width() / 2 - 50,
				manage.get_World_Height() / 2 + 40 - 25, 100, 50);
		gameContinueButton = new Math_Overlap_Rectangle(
				manage.get_World_Width() / 2 - 50,
				manage.get_World_Height() / 2 - 40 - 25, 100, 50);

		this.isBackButtonClick = false;
		this.isLeftButtonClick = false;
		this.isRightButtonClick = false;
		this.isXbutton = false;

		this.tgGameSkillbuttonAni01 = false;
		this.tgGameSkillbuttonAni02 = false;
		this.tgGameSkillbuttonAni03 = false;
		this.tgGameSkillbuttonAni04 = false;
		this.tgGameSkillbuttonAni05 = false;
		this.tgGameSkillbuttonAni06 = false;

		this.isGameTerminate = false;
		this.isGameContinue = false;

		this.nPreviousScore = 0;
		this.nEndingScore = 0;
		this.nPreviousCoin = Manage_Settings.nStageCoin;
		this.nEndingCoin = Manage_Settings.nStageCoin;
		this.nEndingCoin = 0;
		this.fUpdateTime = 0;

		this.rnd = new Random();

		hpbar = MainGame_Manager.mLeeSunSin.getHpBar();
		Manage_Assets.PlayMusic(Manage_Assets.musicGameMainBackground);

	}

	@Override
	public void update(float deltaTime) {
		if (deltaTime > 0.1f)
			deltaTime = 0.1f;

		fUpdateTime += deltaTime;

		update_Contoroller(state, deltaTime);

		switch (state) {
		case GAME_READY:
			updateReady();
			break;
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_PAUSED:
			updatePaused();
			break;
		case GAME_LEVEL_END:
			break;
		case GAME_OVER:
			updateGameOver();
			break;
		}
	}

	private void update_Contoroller(int state, float deltaTime) {

		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();

		for (int i = 0; i < len; i++) {

			TouchEvent event = touchEvents.get(i);

			if (event.type == TouchEvent.TOUCH_DOWN) {

				touchPoint.set(event.x, event.y);
				guiCam.touchToWorld(touchPoint);

				if (GAME_RUNNING == state) {

					// generate
					// archer
					if (Math_Overlap.pointInCircle(
							this.morGameSkillButtonARCHER, touchPoint)) {
						if (Screen_MainGame.tgGameGenerateUnitButtonARCHERACTIVITY) {
							Screen_MainGame.tgGameGenerateUnitButtonARCHER = true;
						}
						return;
					}

					// axe
					if (Math_Overlap.pointInCircle(this.morGameSkillButtonAXE,
							touchPoint)) {
						if (Screen_MainGame.tgGameGenerateUnitButtonAXEACTIVITY) {
							Screen_MainGame.tgGameGenerateUnitButtonAXE = true;
						}
						return;
					}

					// monk
					if (Math_Overlap.pointInCircle(this.morGameSkillButtonMONK,
							touchPoint)) {
						if (Screen_MainGame.tgGameGenerateUnitButtonMONKACTIVITY) {
							Screen_MainGame.tgGameGenerateUnitButtonMONK = true;
						}
						return;
					}

					// gun
					if (Math_Overlap.pointInCircle(this.morGameSkillButtonGUN,
							touchPoint)) {
						if (Screen_MainGame.tgGameGenerateUnitButtonGUNACTIVITY) {
							Screen_MainGame.tgGameGenerateUnitButtonGUN = true;
						}
						return;
					}

					// sword
					if (Math_Overlap.pointInCircle(
							this.morGameSkillButtonSWORD, touchPoint)) {
						if (Screen_MainGame.tgGameGenerateUnitButtonSWORDACTIVITY) {
							Screen_MainGame.tgGameGenerateUnitButtonSWORD = true;
						}
						return;
					}

					// spear
					if (Math_Overlap.pointInCircle(
							this.morGameSkillButtonSPEAR, touchPoint)) {
						if (Screen_MainGame.tgGameGenerateUnitButtonSPEARACTIVITY) {
							Screen_MainGame.tgGameGenerateUnitButtonSPEAR = true;
						}
						return;
					}

					// skill
					if (Math_Overlap.pointInCircle(this.morGameSkillButton01,
							touchPoint)) {
						if (this.tgGameSkillbuttonAni01 == false) {
							Screen_MainGame.iSmotionEnd = false;
							Screen_MainGame.tgGameSkillbutton01 = true;
							MainGame_Manager.mLeeSunSin
									.setStateFlag(LeeSunSin.OBJ_D_SKILL01);
						}
						return;
					}

					if (Math_Overlap.pointInCircle(this.morGameSkillButton02,
							touchPoint)) {
						if (this.tgGameSkillbuttonAni02 == false) {
							Screen_MainGame.iSmotionEnd = false;
							Screen_MainGame.tgGameSkillbutton02 = true;
							MainGame_Manager.mLeeSunSin
									.setStateFlag(LeeSunSin.OBJ_D_SKILL02);
						}
						return;
					}

					if (Math_Overlap.pointInCircle(this.morGameSkillButton03,
							touchPoint)) {
						if (this.tgGameSkillbuttonAni03 == false) {
							Screen_MainGame.iSmotionEnd = false;
							Screen_MainGame.tgGameSkillbutton03 = true;
							MainGame_Manager.mLeeSunSin
									.setStateFlag(LeeSunSin.OBJ_D_SKILL03);
						}
						return;
					}

					if (Math_Overlap.pointInCircle(this.morGameSkillButton04,
							touchPoint)) {
						if (this.tgGameSkillbuttonAni04 == false) {
							Screen_MainGame.iSmotionEnd = false;
							Screen_MainGame.tgGameSkillbutton04 = true;
							MainGame_Manager.mLeeSunSin
									.setStateFlag(LeeSunSin.OBJ_D_SKILL04);
						}
						return;
					}

					if (Math_Overlap.pointInCircle(this.morGameSkillButton05,
							touchPoint)) {
						if (this.tgGameSkillbuttonAni05 == false) {
							Screen_MainGame.iSmotionEnd = false;
							Screen_MainGame.tgGameSkillbutton05 = true;
							MainGame_Manager.mLeeSunSin
									.setStateFlag(LeeSunSin.OBJ_D_SKILL05);
						}
						return;
					}

					if (Math_Overlap.pointInCircle(this.morGameSkillButton06,
							touchPoint)) {
						if (this.tgGameSkillbuttonAni06 == false) {
							Screen_MainGame.iSmotionEnd = false;
							Screen_MainGame.tgGameSkillbutton06 = true;
							MainGame_Manager.mLeeSunSin
									.setStateFlag(LeeSunSin.OBJ_D_SKILL06);
						}
						return;
					}

					if (Math_Overlap.pointInCircle(leftMoveButton, touchPoint)) {
						this.isLeftButtonClick = true;
						MainGame_Manager.mLeeSunSin
								.setStateFlag(LeeSunSin.OBJ_D_LEFT);
						return;
					}

					if (Math_Overlap.pointInCircle(rightMoveButton, touchPoint)) {
						this.isRightButtonClick = true;
						MainGame_Manager.mLeeSunSin
								.setStateFlag(LeeSunSin.OBJ_D_RIGHT);
						return;
					}

					if (touchPoint.x < Screen_MainMenu.GAME_WIDTH / 2) {
						MainGame_Manager.mLeeSunSin
								.setStateFlag(LeeSunSin.OBJ_D_LEFT);
					} else {
						MainGame_Manager.mLeeSunSin
								.setStateFlag(LeeSunSin.OBJ_D_RIGHT);
					}

					// x button
					if (Math_Overlap
							.pointInRectangle(XbuttonBounds, touchPoint)) {

						isXbutton = true;
						return;
					}

					Screen_MainGame.tgGameGenerateUnitButtonAXE = false;
					Screen_MainGame.tgGameGenerateUnitButtonMONK = false;
					Screen_MainGame.tgGameGenerateUnitButtonSPEAR = false;
					Screen_MainGame.tgGameGenerateUnitButtonGUN = false;
					Screen_MainGame.tgGameGenerateUnitButtonSWORD = false;
					Screen_MainGame.tgGameGenerateUnitButtonARCHER = false;

					Screen_MainGame.tgGameSkillbutton01 = false;
					Screen_MainGame.tgGameSkillbutton02 = false;
					Screen_MainGame.tgGameSkillbutton03 = false;
					Screen_MainGame.tgGameSkillbutton04 = false;
					Screen_MainGame.tgGameSkillbutton05 = false;
					Screen_MainGame.tgGameSkillbutton06 = false;

					this.isLeftButtonClick = false;
					this.isRightButtonClick = false;
					this.isXbutton = false;

				}

				if (GAME_LEVEL_END == state) {

					// BackButton
					if (Math_Overlap.pointInRectangle(backButtonBounds,
							touchPoint)) {
						this.isBackButtonClick = true;
						return;
					}

					// NextButton
					if (Math_Overlap.pointInRectangle(nextButtonBounds,
							touchPoint)) {
						this.isNextButtonClick = true;
						return;
					}

					this.isNextButtonClick = false;
					this.isBackButtonClick = false;
					this.isXbutton = false;
				}

				if (GAME_OVER == state) {
					// BackButton
					if (Math_Overlap.pointInRectangle(backButtonBounds,
							touchPoint)) {
						this.isBackButtonClick = true;
						return;
					}

					// Replay
					if (Math_Overlap.pointInRectangle(nextButtonBounds,
							touchPoint)) {
						this.isNextButtonClick = true;
						return;
					}

					this.isNextButtonClick = false;
					this.isBackButtonClick = false;
				}

				if (GAME_PAUSED == state) {

					// terminal
					if (Math_Overlap.pointInRectangle(gameTerminateButton,
							touchPoint)) {
						this.isGameTerminate = true;
						return;
					}

					// Replay
					if (Math_Overlap.pointInRectangle(gameContinueButton,
							touchPoint)) {
						this.isGameContinue = true;
						return;
					}

					this.isGameTerminate = false;
					this.isGameContinue = false;
				}

				if (GAME_LEVEL_END == state) {
					nEndingScore = MainGame_Manager.nStageScore;
					nEndingCoin = Manage_Settings.nStageCoin;
				}
			}

			if (event.type == TouchEvent.TOUCH_DRAGGED) {
				touchPoint.set(event.x, event.y);
				guiCam.touchToWorld(touchPoint);

			}

			if (event.type == TouchEvent.TOUCH_UP) {
				touchPoint.set(event.x, event.y);
				guiCam.touchToWorld(touchPoint);

				if (GAME_PAUSED == state) {

					// terminal
					if (Math_Overlap.pointInRectangle(gameTerminateButton,
							touchPoint) && true == this.isGameTerminate) {
						this.isGameTerminate = false;

						Manage_Assets.PlayMusic(Manage_Assets.musicFullWindow);
						game.setScreen(new StageWindow(game));
						return;
					}

					// continue
					if (Math_Overlap.pointInRectangle(gameContinueButton,
							touchPoint) && true == this.isGameContinue) {
						this.isGameContinue = false;

						Screen_MainGame.state = GAME_RUNNING;
						return;
					}

					this.isGameTerminate = false;
					this.isGameContinue = false;

				}

				if (GAME_OVER == state) {
					// BackButton
					if (Math_Overlap.pointInRectangle(backButtonBounds,
							touchPoint) && true == this.isBackButtonClick) {
						this.isBackButtonClick = false;
						Manage_Assets.PlayMusic(Manage_Assets.musicFullWindow);
						game.setScreen(new StageWindow(game));
						return;
					}

					// ReplayButton
					if (Math_Overlap.pointInRectangle(nextButtonBounds,
							touchPoint) && true == this.isNextButtonClick) {
						Manage_Assets.PlayMusic(Manage_Assets.musicFullWindow);
						game.setScreen(new PrepareWindow(game));
						this.isNextButtonClick = false;
						return;
					}

					this.isNextButtonClick = false;
					this.isBackButtonClick = false;
				}

				if (GAME_LEVEL_END == state) {
					// BackButton
					if (Math_Overlap.pointInRectangle(backButtonBounds,
							touchPoint) && true == this.isBackButtonClick) {
						this.isBackButtonClick = false;
						if (StageWindow.SELECTSTABEMAXNUM < StageWindow.SELECTSTAGENUM + 2)
							if (StageWindow.SELECTSTABEMAXNUM < 8)
								StageWindow.SELECTSTABEMAXNUM = StageWindow.SELECTSTAGENUM + 2;
						Manage_Assets.PlayMusic(Manage_Assets.musicFullWindow);
						game.setScreen(new StageWindow(game));
						return;
					}

					// NextButton
					if (Math_Overlap.pointInRectangle(nextButtonBounds,
							touchPoint) && true == this.isNextButtonClick) {
						this.isNextButtonClick = false;
						StageWindow.SELECTSTAGENUM++;
						Manage_Assets.PlayMusic(Manage_Assets.musicFullWindow);
						game.setScreen(new PrepareWindow(game));
						return;
					}

					this.isNextButtonClick = false;
					this.isBackButtonClick = false;
				}

				if (GAME_RUNNING == state) {

					if (iSmotionEnd == true)
						MainGame_Manager.mLeeSunSin
								.setStateFlag(LeeSunSin.OBJ_D_STOP);

					// generate

					// archer
					if (Math_Overlap.pointInCircle(
							this.morGameSkillButtonARCHER, touchPoint)
							&& Screen_MainGame.tgGameGenerateUnitButtonARCHER
							&& Manage_Settings.nStageCoin >= UnitInformation.ALLY.ArcherMan.NEEDSCORE) {
						if (Screen_MainGame.tgGameGenerateUnitButtonARCHERACTIVITY) {
							Manage_Settings.nStageCoin -= UnitInformation.ALLY.ArcherMan.NEEDSCORE;
							MainGame_Manager.allyArchers
									.add(new AllyArcherMan(
											rnd.nextInt(100),
											manage.get_World_Height() / 2 - 10
													+ rnd.nextInt(20),
											((int) ArcherMan
													.Bonus(Manage_Settings.nUnitArcherLv)
													* ArcherMan.HPBasic + ArcherMan.HPBasic),
											((int) SpearMan
													.Bonus(Manage_Settings.nUnitArcherLv)
													* ArcherMan.SpeedBasic + ArcherMan.SpeedBasic)));
							Screen_MainGame.tgGameGenerateUnitButtonARCHER = false;
						}
						return;
					}

					// axe
					if (Math_Overlap.pointInCircle(this.morGameSkillButtonAXE,
							touchPoint)
							&& Screen_MainGame.tgGameGenerateUnitButtonAXE
							&& Manage_Settings.nStageCoin >= UnitInformation.ALLY.AxeMan.NEEDSCORE) {
						if (Screen_MainGame.tgGameGenerateUnitButtonAXEACTIVITY) {
							Manage_Settings.nStageCoin -= UnitInformation.ALLY.AxeMan.NEEDSCORE;
							MainGame_Manager.allyAxeMans
									.add(new AllyAxeMan(
											rnd.nextInt(100),
											manage.get_World_Height() / 2 - 10
													+ rnd.nextInt(20),
											(int) ((int) AxeMan
													.Bonus(Manage_Settings.nUnitAxeManLv)
													* AxeMan.HPBasic + AxeMan.HPBasic),
											(int) ((int) AxeMan
													.Bonus(Manage_Settings.nUnitAxeManLv)
													* AxeMan.SpeedBasic + AxeMan.SpeedBasic)));
							Screen_MainGame.tgGameGenerateUnitButtonAXE = false;
						}
						return;
					}

					// monk
					if (Math_Overlap.pointInCircle(this.morGameSkillButtonMONK,
							touchPoint)
							&& Screen_MainGame.tgGameGenerateUnitButtonMONK
							&& Manage_Settings.nStageCoin >= UnitInformation.ALLY.MonkMan.NEEDSCORE) {
						if (Screen_MainGame.tgGameGenerateUnitButtonMONKACTIVITY) {
							Manage_Settings.nStageCoin -= UnitInformation.ALLY.MonkMan.NEEDSCORE;
							MainGame_Manager.allyMonkMans
									.add(new AllyMonkMan(
											rnd.nextInt(100),
											manage.get_World_Height() / 2 - 10
													+ rnd.nextInt(20),
											((int) MonkMan
													.Bonus(Manage_Settings.nUnitMonkManLv)
													* MonkMan.HPBasic + MonkMan.HPBasic),
											((int) MonkMan
													.Bonus(Manage_Settings.nUnitMonkManLv)
													* MonkMan.SpeedBasic + MonkMan.SpeedBasic)));
							Screen_MainGame.tgGameGenerateUnitButtonMONK = false;
						}
						return;
					}

					// gun
					if (Math_Overlap.pointInCircle(this.morGameSkillButtonGUN,
							touchPoint)
							&& Screen_MainGame.tgGameGenerateUnitButtonGUN) {
						if (Screen_MainGame.tgGameGenerateUnitButtonGUNACTIVITY) {
							Screen_MainGame.tgGameGenerateUnitButtonGUN = false;
						}
						return;
					}

					// sword
					if (Math_Overlap.pointInCircle(
							this.morGameSkillButtonSWORD, touchPoint)
							&& Screen_MainGame.tgGameGenerateUnitButtonSWORD) {
						if (Screen_MainGame.tgGameGenerateUnitButtonSWORDACTIVITY) {
							Screen_MainGame.tgGameGenerateUnitButtonSWORD = false;
						}
						return;
					}

					// spear
					if (Math_Overlap.pointInCircle(
							this.morGameSkillButtonSPEAR, touchPoint)
							&& Screen_MainGame.tgGameGenerateUnitButtonSPEAR
							&& Manage_Settings.nStageCoin >= UnitInformation.ALLY.SpearMan.NEEDSCORE) {
						if (Screen_MainGame.tgGameGenerateUnitButtonSPEARACTIVITY) {
							Manage_Settings.nStageCoin -= UnitInformation.ALLY.SpearMan.NEEDSCORE;
							MainGame_Manager.allySpearMans
									.add(new AllySpearMan(
											rnd.nextInt(100),
											manage.get_World_Height() / 2 - 10
													+ rnd.nextInt(20),
											((int) SpearMan
													.Bonus(Manage_Settings.nUnitSpearLv)
													* SpearMan.HPBasic + SpearMan.HPBasic),
											((int) SpearMan
													.Bonus(Manage_Settings.nUnitSpearLv)
													* SpearMan.SpeedBasic + SpearMan.SpeedBasic)));
							Screen_MainGame.tgGameGenerateUnitButtonSPEAR = false;
						}
						return;
					}

					// xButton
					if (Math_Overlap
							.pointInRectangle(XbuttonBounds, touchPoint)
							&& true == isXbutton) {
						isXbutton = false;
						Screen_MainGame.state = GAME_PAUSED;
						Manage_Settings.save(game.getFileIO());
						if (StageWindow.SELECTSTABEMAXNUM < StageWindow.SELECTSTAGENUM + 1)
							if (StageWindow.SELECTSTABEMAXNUM < 8)
								StageWindow.SELECTSTABEMAXNUM = StageWindow.SELECTSTAGENUM + 1;
						return;
					}

					// skill 01
					if (Math_Overlap.pointInCircle(this.morGameSkillButton01,
							touchPoint)
							&& Screen_MainGame.tgGameSkillbutton01 == true) {
						fButtonCurrentTime01 = 0;
						this.tgGameSkillbuttonAni01 = true;
						Screen_MainGame.tgGameSkillbutton01 = false;
						Screen_MainGame.iSmotionEnd = true;
						return;
					}

					// skill 02
					if (Math_Overlap.pointInCircle(this.morGameSkillButton02,
							touchPoint)
							&& Screen_MainGame.tgGameSkillbutton02 == true) {
						fButtonCurrentTime02 = 0;
						this.tgGameSkillbuttonAni02 = true;
						Screen_MainGame.tgGameSkillbutton02 = false;
						Screen_MainGame.iSmotionEnd = true;
						return;
					}

					// skill 03
					if (Math_Overlap.pointInCircle(this.morGameSkillButton03,
							touchPoint)
							&& Screen_MainGame.tgGameSkillbutton03 == true) {
						fButtonCurrentTime03 = 0;
						this.tgGameSkillbuttonAni03 = true;
						Screen_MainGame.tgGameSkillbutton03 = false;
						Screen_MainGame.iSmotionEnd = true;
						return;
					}

					// skill 04
					if (Math_Overlap.pointInCircle(this.morGameSkillButton04,
							touchPoint)
							&& Screen_MainGame.tgGameSkillbutton04 == true) {
						fButtonCurrentTime04 = 0;
						this.tgGameSkillbuttonAni04 = true;
						Screen_MainGame.tgGameSkillbutton04 = false;
						Screen_MainGame.iSmotionEnd = true;
						return;
					}

					// skill 05
					if (Math_Overlap.pointInCircle(this.morGameSkillButton05,
							touchPoint)
							&& Screen_MainGame.tgGameSkillbutton05 == true) {
						fButtonCurrentTime05 = 0;
						this.tgGameSkillbuttonAni05 = true;
						Screen_MainGame.tgGameSkillbutton05 = false;
						Screen_MainGame.iSmotionEnd = true;
						return;
					}

					// skill 06
					if (Math_Overlap.pointInCircle(this.morGameSkillButton06,
							touchPoint)
							&& Screen_MainGame.tgGameSkillbutton06 == true) {
						fButtonCurrentTime06 = 0;
						this.tgGameSkillbuttonAni06 = true;
						Screen_MainGame.tgGameSkillbutton06 = false;
						Screen_MainGame.iSmotionEnd = true;
						return;
					}

					if (Math_Overlap.pointInCircle(leftMoveButton, touchPoint)
							&& true == this.isLeftButtonClick) {
						this.isLeftButtonClick = false;
						return;
					}

					if (Math_Overlap.pointInCircle(rightMoveButton, touchPoint)
							&& true == this.isRightButtonClick) {
						this.isRightButtonClick = false;
						return;
					}

					this.isLeftButtonClick = false;
					this.isRightButtonClick = false;
					Screen_MainGame.iSmotionEnd = true;
					Screen_MainGame.tgGameSkillbutton01 = false;
					Screen_MainGame.tgGameSkillbutton02 = false;
					Screen_MainGame.tgGameSkillbutton03 = false;
					Screen_MainGame.tgGameSkillbutton04 = false;
					Screen_MainGame.tgGameSkillbutton05 = false;
					Screen_MainGame.tgGameSkillbutton06 = false;

					Screen_MainGame.tgGameGenerateUnitButtonAXE = false;
					Screen_MainGame.tgGameGenerateUnitButtonMONK = false;
					Screen_MainGame.tgGameGenerateUnitButtonSPEAR = false;
					Screen_MainGame.tgGameGenerateUnitButtonGUN = false;
					Screen_MainGame.tgGameGenerateUnitButtonSWORD = false;
					Screen_MainGame.tgGameGenerateUnitButtonARCHER = false;
				}

			}
		}
	}

	private void updateReady() {

	}

	private void updateRunning(float deltaTime) {

		manage.update(renderer, deltaTime, game.getInput().getAccelX(), game
				.getInput().getAccelY(), touchPoint.x, touchPoint.y);

	}

	private void updatePaused() {

	}

	private void updateGameOver() {
	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);

		renderer.render(deltaTime, state);

		guiCam.setViewportAndMatrices();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		switch (state) {
		case GAME_READY:
			presentReady();
			break;
		case GAME_RUNNING:
			presentRunning(deltaTime);
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
		if (Screen_MainMenu.is_Debug)
			fpsCounter.logFrame();
	}

	private void presentReady() {
	}

	private void presentRunning(float deltaTime) {

		render_HPbar();

		// score
		batcher.beginBatch(Manage_Assets.ttScoreFont);
		batcher.drawSprite(40, 304, 35, 32, Manage_Assets.ttgScoreFont);
		batcher.endBatch();

		if (nPreviousScore < MainGame_Manager.nStageScore)
			nPreviousScore += 3;
		else
			nPreviousScore = MainGame_Manager.nStageScore;
		batcher.beginBatch(Manage_Assets.ttFont);
		Manage_Assets.ScoreNumberFont.drawText(batcher, "" + nPreviousScore,
				75, 304, 20, 23, 15);

		batcher.endBatch();

		_keyFrame = Manage_Assets.aniCoin.getKeyFrame(fUpdateTime,
				GL_Animation.ANIMATION_LOOPING);

		// coin
		batcher.beginBatch(Manage_Assets.ttCoin);
		batcher.drawSprite(186, 304, 30, 30, _keyFrame);
		batcher.endBatch();

		if (nPreviousCoin < Manage_Settings.nStageCoin)
			nPreviousCoin += 3;
		else
			nPreviousCoin = Manage_Settings.nStageCoin;
		batcher.beginBatch(Manage_Assets.ttFont);
		Manage_Assets.MoneyNumberFont.drawText(batcher, "" + nPreviousCoin,
				186 + 29, 302, 20, 23, 15);
		batcher.endBatch();

		batcher.beginBatch(Manage_Assets.ttGamePlaySceneButton);

		// lfet Button
		if (!isLeftButtonClick)
			batcher.drawSprite(50, 50, 50, 50,
					Manage_Assets.ttgGamePlaySceneLeftButton);
		else
			batcher.drawSprite(50, 50, 50, 50,
					Manage_Assets.ttgGamePlaySceneLeftButtonPressed);

		// right Button
		if (!isRightButtonClick)
			batcher.drawSprite(125, 50, 50, 50,
					Manage_Assets.ttgGamePlaySceneRightButton);
		else
			batcher.drawSprite(125, 50, 50, 50,
					Manage_Assets.ttgGamePlaySceneRightButtonPressed);
		batcher.endBatch();

		// unit Generate
		// AXE
		batcher.beginBatch(Manage_Assets.ttGameUnitGenerateButton);
		if (tgGameGenerateUnitButtonAXE == false
				&& tgGameGenerateUnitButtonAXEACTIVITY
				&& Manage_Settings.nStageCoin >= UnitInformation.ALLY.AxeMan.NEEDSCORE) {
			batcher.drawSprite(30, 30 + 59, 33, 33,
					Manage_Assets.ttgUnitGenerateButtonAxe);
		} else {
			batcher.drawSprite(30, 30 + 59, 33, 33,
					Manage_Assets.ttgUnitGenerateButtonAxePressed);
		}
		batcher.endBatch();

		// MONK
		batcher.beginBatch(Manage_Assets.ttGameUnitGenerateButton);
		if (tgGameGenerateUnitButtonMONK == false
				&& tgGameGenerateUnitButtonMONKACTIVITY
				&& Manage_Settings.nStageCoin >= UnitInformation.ALLY.MonkMan.NEEDSCORE) {
			batcher.drawSprite(30, 63 + 59, 33, 33,
					Manage_Assets.ttgUnitGenerateButtonMonk);
		} else {
			batcher.drawSprite(30, 63 + 59, 33, 33,
					Manage_Assets.ttgUnitGenerateButtonMonkPressed);
		}
		batcher.endBatch();

		// ARCHER
		batcher.beginBatch(Manage_Assets.ttGameUnitGenerateButton);
		if (tgGameGenerateUnitButtonARCHER == false
				&& tgGameGenerateUnitButtonARCHERACTIVITY
				&& Manage_Settings.nStageCoin >= UnitInformation.ALLY.ArcherMan.NEEDSCORE) {
			batcher.drawSprite(30, 96 + 59, 33, 33,
					Manage_Assets.ttgUnitGenerateButtonArcher);
		} else {
			batcher.drawSprite(30, 96 + 59, 33, 33,
					Manage_Assets.ttgUnitGenerateButtonArcherPressed);
		}
		batcher.endBatch();

		// SPEAR
		batcher.beginBatch(Manage_Assets.ttGameUnitGenerateButton);
		if (tgGameGenerateUnitButtonSPEAR == false
				&& tgGameGenerateUnitButtonSPEARACTIVITY
				&& Manage_Settings.nStageCoin >= UnitInformation.ALLY.SpearMan.NEEDSCORE) {
			batcher.drawSprite(30, 129 + 59, 33, 33,
					Manage_Assets.ttgUnitGenerateButtonSpear);
		} else {
			batcher.drawSprite(30, 129 + 59, 33, 33,
					Manage_Assets.ttgUnitGenerateButtonSpearPressed);
		}
		batcher.endBatch();

		// GUN
		batcher.beginBatch(Manage_Assets.ttGameUnitGenerateButton);
		if (tgGameGenerateUnitButtonGUN == false
				&& tgGameGenerateUnitButtonGUNACTIVITY) {
			batcher.drawSprite(30, 162 + 59, 33, 33,
					Manage_Assets.ttgUnitGenerateButtonGun);
		} else {
			batcher.drawSprite(30, 162 + 59, 33, 33,
					Manage_Assets.ttgUnitGenerateButtonGunPressed);
		}
		batcher.endBatch();

		// SWORD
		batcher.beginBatch(Manage_Assets.ttGameUnitGenerateButton);
		if (tgGameGenerateUnitButtonSWORD == false
				&& tgGameGenerateUnitButtonSWORDACTIVITY) {
			batcher.drawSprite(30, 195 + 59, 33, 33,
					Manage_Assets.ttgUnitGenerateButtonSword);
		} else {
			batcher.drawSprite(30, 195 + 59, 33, 33,
					Manage_Assets.ttgUnitGenerateButtonSwordPressed);
		}
		batcher.endBatch();

		// Main Attack 01
		batcher.beginBatch(Manage_Assets.ttGameButton01);
		if (this.tgGameSkillbuttonAni01 == false) {
			batcher.drawSprite(manage.get_World_Width() - 50, 50, 50, 50,
					Manage_Assets.ttgGameSkillButtonDefault01);
		} else {
			fButtonCurrentTime01 += deltaTime;
			GL_TextureRegion keyFrame = Manage_Assets.aniGameSkillButton01
					.getKeyFrame(fButtonCurrentTime01,
							GL_Animation.ANIMATION_LOOPING);
			batcher.drawSprite(manage.get_World_Width() - 50, 50, 50, 50,
					keyFrame);

			if (Manage_Assets.aniGameSkillButton01.CurrentFrameNum() == Manage_Assets.aniGameSkillButton01
					.EndKeyFrameNum())
				this.tgGameSkillbuttonAni01 = false;

		}

		batcher.endBatch();

		// Main Attack 02
		batcher.beginBatch(Manage_Assets.ttGameButton02);
		if (this.tgGameSkillbuttonAni02 == false) {
			batcher.drawSprite(manage.get_World_Width() - 83, 20, 35, 35,
					Manage_Assets.ttgGameSkillButtonDefault02);
		} else {
			fButtonCurrentTime02 += deltaTime;
			GL_TextureRegion keyFrame = Manage_Assets.aniGameSkillButton02
					.getKeyFrame(fButtonCurrentTime02,
							GL_Animation.ANIMATION_LOOPING);
			batcher.drawSprite(manage.get_World_Width() - 83, 20, 35, 35,
					keyFrame);

			if (Manage_Assets.aniGameSkillButton02.CurrentFrameNum() == Manage_Assets.aniGameSkillButton02
					.EndKeyFrameNum())
				this.tgGameSkillbuttonAni02 = false;

		}
		batcher.endBatch();

		// Main Attack 03
		batcher.beginBatch(Manage_Assets.ttGameButton03);
		if (this.tgGameSkillbuttonAni03 == false) {
			batcher.drawSprite(manage.get_World_Width() - 20, 83, 35, 35,
					Manage_Assets.ttgGameSkillButtonDefault03);
		} else {
			fButtonCurrentTime03 += deltaTime;
			GL_TextureRegion keyFrame = Manage_Assets.aniGameSkillButton03
					.getKeyFrame(fButtonCurrentTime03,
							GL_Animation.ANIMATION_LOOPING);
			batcher.drawSprite(manage.get_World_Width() - 20, 83, 35, 35,
					keyFrame);

			if (Manage_Assets.aniGameSkillButton03.CurrentFrameNum() == Manage_Assets.aniGameSkillButton03
					.EndKeyFrameNum())
				this.tgGameSkillbuttonAni03 = false;

		}
		batcher.endBatch();

		// Main Attack 04
		batcher.beginBatch(Manage_Assets.ttGameButton04);
		if (this.tgGameSkillbuttonAni04 == false) {
			batcher.drawSprite(manage.get_World_Width() - 18.5f, 18.5f, 35, 35,
					Manage_Assets.ttgGameSkillButtonDefault04);
		} else {
			fButtonCurrentTime04 += deltaTime;
			GL_TextureRegion keyFrame = Manage_Assets.aniGameSkillButton04
					.getKeyFrame(fButtonCurrentTime04,
							GL_Animation.ANIMATION_LOOPING);
			batcher.drawSprite(manage.get_World_Width() - 18.5f, 18.5f, 35, 35,
					keyFrame);

			if (Manage_Assets.aniGameSkillButton04.CurrentFrameNum() == Manage_Assets.aniGameSkillButton04
					.EndKeyFrameNum())
				this.tgGameSkillbuttonAni04 = false;

		}
		batcher.endBatch();

		// Main Attack 05
		batcher.beginBatch(Manage_Assets.ttGameButton05);
		if (this.tgGameSkillbuttonAni05 == false) {
			batcher.drawSprite(manage.get_World_Width() - 92, 56, 35, 35,
					Manage_Assets.ttgGameSkillButtonDefault05);
		} else {
			fButtonCurrentTime05 += deltaTime;
			GL_TextureRegion keyFrame = Manage_Assets.aniGameSkillButton05
					.getKeyFrame(fButtonCurrentTime05,
							GL_Animation.ANIMATION_LOOPING);
			batcher.drawSprite(manage.get_World_Width() - 92, 56, 35, 35,
					keyFrame);

			if (Manage_Assets.aniGameSkillButton05.CurrentFrameNum() == Manage_Assets.aniGameSkillButton05
					.EndKeyFrameNum())
				this.tgGameSkillbuttonAni05 = false;

		}
		batcher.endBatch();

		// Main Attack 06
		batcher.beginBatch(Manage_Assets.ttGameButton06);
		if (this.tgGameSkillbuttonAni06 == false) {
			batcher.drawSprite(manage.get_World_Width() - 56, 92, 35, 35,
					Manage_Assets.ttgGameSkillButtonDefault06);
		} else {
			fButtonCurrentTime06 += deltaTime;
			GL_TextureRegion keyFrame = Manage_Assets.aniGameSkillButton06
					.getKeyFrame(fButtonCurrentTime06,
							GL_Animation.ANIMATION_LOOPING);
			batcher.drawSprite(manage.get_World_Width() - 56, 92, 35, 35,
					keyFrame);

			if (Manage_Assets.aniGameSkillButton06.CurrentFrameNum() == Manage_Assets.aniGameSkillButton06
					.EndKeyFrameNum())
				this.tgGameSkillbuttonAni06 = false;

		}
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
	}

	private void render_HPbar() {
		batcher.beginBatch(Manage_Assets.ttEnergyBar);

		if (hpbar.getCurrentStateBar() == HpBar.FULLHP)
			batcher.drawSprite(90, Screen_MainMenu.GAME_HEIGHT - 40,
					HpBar.HPBARWIDTH * 3, HpBar.HPBARHEGIHT * 3,
					Manage_Assets.ttgEneryBar04);
		else if (hpbar.getCurrentStateBar() == HpBar.THREEHP)
			batcher.drawSprite(90, Screen_MainMenu.GAME_HEIGHT - 40,
					HpBar.HPBARWIDTH * 3, HpBar.HPBARHEGIHT * 3,
					Manage_Assets.ttgEneryBar03);
		else if (hpbar.getCurrentStateBar() == HpBar.TWOHP)
			batcher.drawSprite(90, Screen_MainMenu.GAME_HEIGHT - 40,
					HpBar.HPBARWIDTH * 3, HpBar.HPBARHEGIHT * 3,
					Manage_Assets.ttgEneryBar02);
		else if (hpbar.getCurrentStateBar() == HpBar.ONEHP)
			batcher.drawSprite(90, Screen_MainMenu.GAME_HEIGHT - 40,
					HpBar.HPBARWIDTH * 3, HpBar.HPBARHEGIHT * 3,
					Manage_Assets.ttgEneryBar01);
		else if (hpbar.getCurrentStateBar() == HpBar.ZEROHP)
			batcher.drawSprite(90, Screen_MainMenu.GAME_HEIGHT - 40,
					HpBar.HPBARWIDTH * 3, HpBar.HPBARHEGIHT * 3,
					Manage_Assets.ttgEneryBar00);

		batcher.endBatch();
	}

	private void presentPaused() {

		batcher.beginBatch(Manage_Assets.ttGameRunningPuase);
		if (false == this.isGameTerminate)
			batcher.drawSprite(manage.get_World_Width() / 2,
					manage.get_World_Height() / 2 + 40, 100, 50,
					Manage_Assets.ttgGameRunningTerminate);
		else
			batcher.drawSprite(manage.get_World_Width() / 2,
					manage.get_World_Height() / 2 + 40, 100, 50,
					Manage_Assets.ttgGameRunningTerminatePressed);
		batcher.endBatch();

		batcher.beginBatch(Manage_Assets.ttGameRunningPuase);
		if (false == this.isGameContinue)
			batcher.drawSprite(manage.get_World_Width() / 2,
					manage.get_World_Height() / 2 - 40, 100, 50,
					Manage_Assets.ttgGameRunningContinue);
		else
			batcher.drawSprite(manage.get_World_Width() / 2,
					manage.get_World_Height() / 2 - 40, 100, 50,
					Manage_Assets.ttgGameRunningContinuePressed);
		batcher.endBatch();

	}

	private void presentLevelEnd() {

		// win window
		batcher.beginBatch(Manage_Assets.ttStoryBoardWinLose);
		batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
				Screen_MainMenu.GAME_HEIGHT / 2, Screen_MainMenu.GAME_WIDTH,
				Screen_MainMenu.GAME_HEIGHT, Manage_Assets.ttgStoryBoardWin);
		batcher.endBatch();

		// wind window
		batcher.beginBatch(Manage_Assets.ttStageClearItem01);
		batcher.drawSprite(manage.get_World_Width() / 2,
				manage.get_World_Height() / 2, GameClearWindowWidth,
				GameClearWindowHeight, Manage_Assets.ttgClearGameBackGround);
		batcher.endBatch();

		batcher.beginBatch(Manage_Assets.ttStageClearItem02);
		batcher.drawSprite(manage.get_World_Width() / 2 - 2,
				manage.get_World_Height() / 2 + 2, 220, 160,
				Manage_Assets.ttgClearGameBackPage);
		batcher.endBatch();

		// clear text
		batcher.beginBatch(Manage_Assets.ttStageClearItem01);
		batcher.drawSprite(190, 220, 130, 34,
				Manage_Assets.ttgClearGameGameClearText);
		batcher.endBatch();

		// star point

		clearStage();

		// score
		batcher.beginBatch(Manage_Assets.ttScoreFont);
		batcher.drawSprite(156, 165, 35, 32, Manage_Assets.ttgScoreFont);
		batcher.endBatch();

		if (nEndingScore < MainGame_Manager.nStageScore)
			nEndingScore += 3;

		batcher.beginBatch(Manage_Assets.ttFont);
		Manage_Assets.ScoreNumberFont.drawText(batcher, "" + nEndingScore, 190,
				165, 20, 23, 15);

		batcher.endBatch();

		// new score
		if (StageInformation.Stage.s1.Score < MainGame_Manager.nStageScore) {
			batcher.beginBatch(Manage_Assets.ttStageClearItem01);
			batcher.drawSprite(304, 191, 80, 20,
					Manage_Assets.ttgClearGameNewRecordText);
			batcher.endBatch();
		}

		// coin
		_keyFrame = Manage_Assets.aniCoin.getKeyFrame(fUpdateTime,
				GL_Animation.ANIMATION_LOOPING);

		batcher.beginBatch(Manage_Assets.ttCoin);
		batcher.drawSprite(156, 116, 30, 30, _keyFrame);
		batcher.endBatch();

		// coin
		if (nEndingCoin < Manage_Settings.nStageCoin)
			nEndingCoin++;

		batcher.beginBatch(Manage_Assets.ttFont);
		Manage_Assets.MoneyNumberFont.drawText(batcher, "" + nEndingCoin, 191,
				116, 20, 23, 15);

		batcher.endBatch();

		// back button
		batcher.beginBatch(Manage_Assets.ttStageClearItem01);
		if (false == isBackButtonClick)
			batcher.drawSprite(164, 34, 80, 44,
					Manage_Assets.ttgClearGameBackButton);
		else
			batcher.drawSprite(164, 34, 80, 44,
					Manage_Assets.ttgClearGameBackButtonClicked);
		batcher.endBatch();

		// next button
		batcher.beginBatch(Manage_Assets.ttStageClearItem01);
		if (false == isNextButtonClick)
			batcher.drawSprite(317, 34, 80, 44,
					Manage_Assets.ttgClearGameNextButton);
		else
			batcher.drawSprite(317, 34, 80, 44,
					Manage_Assets.ttgClearGameNextButtonClicked);
		batcher.endBatch();

	}

	private void clearStage() {

		if (0 == StageWindow.SELECTSTAGENUM) {
			if (MainGame_Manager.nStageScore > StageInformation.Stage.s1.TwoStarExp
					&& StageInformation.Stage.s1.nStageStar >= 1) {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar02);
				StageInformation.Stage.s1.nStageStar = 2;
				batcher.endBatch();
			} else if (MainGame_Manager.nStageScore > StageInformation.Stage.s1.ThreeStarExp
					&& StageInformation.Stage.s1.nStageStar >= 2) {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar03);
				StageInformation.Stage.s1.nStageStar = 3;
				batcher.endBatch();
			} else {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar01);
				StageInformation.Stage.s1.nStageStar = 1;
				batcher.endBatch();
			}

			batcher.beginBatch(Manage_Assets.ttStageClearItem01);
			batcher.drawSprite(149, 267, 90, 70,
					Manage_Assets.ttgClearGameStageNum);
			batcher.endBatch();
		} else if (1 == StageWindow.SELECTSTAGENUM) {
			if (MainGame_Manager.nStageScore > StageInformation.Stage.s2.TwoStarExp
					&& StageInformation.Stage.s2.nStageStar >= 1) {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar02);
				StageInformation.Stage.s2.nStageStar = 2;
				batcher.endBatch();
			} else if (MainGame_Manager.nStageScore > StageInformation.Stage.s2.ThreeStarExp
					&& StageInformation.Stage.s2.nStageStar >= 2) {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar03);
				StageInformation.Stage.s2.nStageStar = 3;
				batcher.endBatch();
			} else {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar01);
				StageInformation.Stage.s2.nStageStar = 1;
				batcher.endBatch();
			}

			batcher.beginBatch(Manage_Assets.ttStageClearNumberPanel);
			batcher.drawSprite(149, 267, 90, 70,
					Manage_Assets.ttgStageClearNumberPanel02);
			batcher.endBatch();
		} else if (2 == StageWindow.SELECTSTAGENUM) {
			if (MainGame_Manager.nStageScore > StageInformation.Stage.s3.TwoStarExp
					&& StageInformation.Stage.s3.nStageStar >= 1) {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar02);
				StageInformation.Stage.s3.nStageStar = 2;
				batcher.endBatch();
			} else if (MainGame_Manager.nStageScore > StageInformation.Stage.s3.ThreeStarExp
					&& StageInformation.Stage.s3.nStageStar >= 2) {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar03);
				StageInformation.Stage.s3.nStageStar = 3;
				batcher.endBatch();
			} else {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar01);
				StageInformation.Stage.s3.nStageStar = 1;
				batcher.endBatch();
			}

			batcher.beginBatch(Manage_Assets.ttStageClearNumberPanel);
			batcher.drawSprite(149, 267, 90, 70,
					Manage_Assets.ttgStageClearNumberPanel03);
			batcher.endBatch();
		} else if (3 == StageWindow.SELECTSTAGENUM) {
			if (MainGame_Manager.nStageScore > StageInformation.Stage.s4.TwoStarExp
					&& StageInformation.Stage.s4.nStageStar >= 1) {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar02);
				StageInformation.Stage.s4.nStageStar = 2;
				batcher.endBatch();
			} else if (MainGame_Manager.nStageScore > StageInformation.Stage.s4.ThreeStarExp
					&& StageInformation.Stage.s4.nStageStar >= 2) {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar03);
				StageInformation.Stage.s4.nStageStar = 3;
				batcher.endBatch();
			} else {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar01);
				StageInformation.Stage.s4.nStageStar = 1;
				batcher.endBatch();
			}

			batcher.beginBatch(Manage_Assets.ttStageClearNumberPanel);
			batcher.drawSprite(149, 267, 90, 70,
					Manage_Assets.ttgStageClearNumberPanel04);
			batcher.endBatch();
		} else if (4 == StageWindow.SELECTSTAGENUM) {
			if (MainGame_Manager.nStageScore > StageInformation.Stage.s5.TwoStarExp
					&& StageInformation.Stage.s5.nStageStar >= 1) {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar02);
				StageInformation.Stage.s5.nStageStar = 2;
				batcher.endBatch();
			} else if (MainGame_Manager.nStageScore > StageInformation.Stage.s5.ThreeStarExp
					&& StageInformation.Stage.s5.nStageStar >= 2) {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar03);
				StageInformation.Stage.s5.nStageStar = 3;
				batcher.endBatch();
			} else {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar01);
				StageInformation.Stage.s5.nStageStar = 1;
				batcher.endBatch();
			}

			batcher.beginBatch(Manage_Assets.ttStageClearNumberPanel);
			batcher.drawSprite(149, 267, 90, 70,
					Manage_Assets.ttgStageClearNumberPanel05);
			batcher.endBatch();
		} else if (5 == StageWindow.SELECTSTAGENUM) {
			if (MainGame_Manager.nStageScore > StageInformation.Stage.s6.TwoStarExp
					&& StageInformation.Stage.s6.nStageStar >= 1) {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar02);
				StageInformation.Stage.s6.nStageStar = 2;
				batcher.endBatch();
			} else if (MainGame_Manager.nStageScore > StageInformation.Stage.s6.ThreeStarExp
					&& StageInformation.Stage.s6.nStageStar >= 2) {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar03);
				StageInformation.Stage.s6.nStageStar = 3;
				batcher.endBatch();
			} else {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar01);
				StageInformation.Stage.s6.nStageStar = 1;
				batcher.endBatch();
			}

			batcher.beginBatch(Manage_Assets.ttStageClearNumberPanel);
			batcher.drawSprite(149, 267, 90, 70,
					Manage_Assets.ttgStageClearNumberPanel06);
			batcher.endBatch();
		} else if (6 == StageWindow.SELECTSTAGENUM) {
			if (MainGame_Manager.nStageScore > StageInformation.Stage.s7.TwoStarExp
					&& StageInformation.Stage.s7.nStageStar >= 1) {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar02);
				StageInformation.Stage.s7.nStageStar = 2;
				batcher.endBatch();
			} else if (MainGame_Manager.nStageScore > StageInformation.Stage.s7.ThreeStarExp
					&& StageInformation.Stage.s7.nStageStar >= 2) {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar03);
				StageInformation.Stage.s7.nStageStar = 3;
				batcher.endBatch();
			} else {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar01);
				StageInformation.Stage.s7.nStageStar = 1;
				batcher.endBatch();
			}

			batcher.beginBatch(Manage_Assets.ttStageClearNumberPanel);
			batcher.drawSprite(149, 267, 90, 70,
					Manage_Assets.ttgStageClearNumberPanel07);
			batcher.endBatch();
		} else if (7 == StageWindow.SELECTSTAGENUM) {
			if (MainGame_Manager.nStageScore > StageInformation.Stage.s8.TwoStarExp
					&& StageInformation.Stage.s8.nStageStar >= 1) {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar02);
				StageInformation.Stage.s8.nStageStar = 2;
				batcher.endBatch();
			} else if (MainGame_Manager.nStageScore > StageInformation.Stage.s8.ThreeStarExp
					&& StageInformation.Stage.s8.nStageStar >= 2) {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar03);
				StageInformation.Stage.s8.nStageStar = 3;
				batcher.endBatch();
			} else {
				batcher.beginBatch(Manage_Assets.ttStageClearItem02);
				batcher.drawSprite(303, 220, 80, 34,
						Manage_Assets.ttgClearGameStar01);
				StageInformation.Stage.s8.nStageStar = 1;
				batcher.endBatch();
			}

			batcher.beginBatch(Manage_Assets.ttStageClearNumberPanel);
			batcher.drawSprite(149, 267, 90, 70,
					Manage_Assets.ttgStageClearNumberPanel08);
			batcher.endBatch();
		}
	}

	private void presentGameOver() {

		// lose window
		batcher.beginBatch(Manage_Assets.ttStoryBoardWinLose);
		batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
				Screen_MainMenu.GAME_HEIGHT / 2, Screen_MainMenu.GAME_WIDTH,
				Screen_MainMenu.GAME_HEIGHT, Manage_Assets.ttgStoryBoardLose);
		batcher.endBatch();

		/*
		 * batcher.beginBatch(Manage_Assets.ttStageClearItem01);
		 * batcher.drawSprite(manage.get_World_Width() / 2,
		 * manage.get_World_Height() / 2, GameClearWindowWidth,
		 * GameClearWindowHeight, Manage_Assets.ttgClearGameBackGround);
		 * batcher.endBatch();
		 * 
		 * batcher.beginBatch(Manage_Assets.ttStageClearItem02);
		 * batcher.drawSprite(manage.get_World_Width() / 2 - 2,
		 * manage.get_World_Height() / 2 + 2, 220, 160,
		 * Manage_Assets.ttgClearGameBackPage); batcher.endBatch();
		 * 
		 * // clear text batcher.beginBatch(Manage_Assets.ttStageClearItem01);
		 * batcher.drawSprite(190, 220, 130, 34,
		 * Manage_Assets.ttgClearGameGameFailText); batcher.endBatch();
		 * 
		 * // stage num batcher.beginBatch(Manage_Assets.ttStageClearItem01);
		 * batcher.drawSprite(149, 267, 90, 70,
		 * Manage_Assets.ttgClearGameStageNum); batcher.endBatch();
		 */
		// back button
		batcher.beginBatch(Manage_Assets.ttStageClearItem01);
		if (false == isBackButtonClick)
			batcher.drawSprite(164, 34, 80, 44,
					Manage_Assets.ttgClearGameBackButton);
		else
			batcher.drawSprite(164, 34, 80, 44,
					Manage_Assets.ttgClearGameBackButtonClicked);
		batcher.endBatch();

		// next button
		batcher.beginBatch(Manage_Assets.ttStageClearItem01);
		if (false == isNextButtonClick)
			batcher.drawSprite(317, 34, 80, 44,
					Manage_Assets.ttgClearGameReplayButton);
		else
			batcher.drawSprite(317, 34, 80, 44,
					Manage_Assets.ttgClearGameReplayButtonClicked);
		batcher.endBatch();
	}

	@Override
	public void pause() {
		if (state == GAME_RUNNING)
			state = GAME_PAUSED;
		Manage_Settings.save(game.getFileIO());
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
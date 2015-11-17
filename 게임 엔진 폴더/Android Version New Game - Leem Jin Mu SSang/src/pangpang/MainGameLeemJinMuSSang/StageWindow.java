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
import jrcengine.Math.Math_Overlap_Rectangle;
import jrcengine.Math.Math_Vector;
import android.util.Log;

public class StageWindow extends GL_Screen {

	public static int SELECTSTAGENUM;
	public static int SELECTSTABEMAXNUM;

	private final int WINDOWSTAGEWITDHMAXNUM = 4;
	private final int WINDOWSTAGEHEIGHTMAXNUM = 2;
	private final int MAXSTAGEPAGENUMBER = 5;

	private final int STAGEPANALSTARGAB = 17;
	private final int STAGESTARWIDTH = 12;
	private final int STAGESTARHEIGHT = 32;
	private final int STAGESELECTMARGEN = 10;

	private GL_Camera2D guiCam;
	private GL_SpriteBatcher batcher;
	private Math_Vector touchPoint;
	private StageSlots[] stageSlots = new StageSlots[WINDOWSTAGEHEIGHTMAXNUM
			* WINDOWSTAGEWITDHMAXNUM];
	private Math_Overlap_Rectangle[] stageBounds = new Math_Overlap_Rectangle[WINDOWSTAGEWITDHMAXNUM
			* WINDOWSTAGEHEIGHTMAXNUM];

	boolean isLeftButton;
	boolean isRightButton;
	boolean isTraningCenterButton;
	boolean isStoreCenterButton;
	boolean isXbutton;

	int nPageNum;

	private Math_Overlap_Rectangle unitTraingBounds;
	private Math_Overlap_Rectangle storeCenterBounds;
	private Math_Overlap_Rectangle nextStageWindowBounds;
	private Math_Overlap_Rectangle previousStageWindowBounds;
	private Math_Overlap_Rectangle XbuttonBounds;

	public StageWindow(IFace_Game game) {
		super(game);

		this.guiCam = new GL_Camera2D(glGraphics, Screen_MainMenu.GAME_WIDTH,
				Screen_MainMenu.GAME_HEIGHT);
		this.previousStageWindowBounds = new Math_Overlap_Rectangle(33,
				Screen_MainMenu.GAME_HEIGHT / 2 - 20, 40, 40);
		this.nextStageWindowBounds = new Math_Overlap_Rectangle(
				Screen_MainMenu.GAME_WIDTH - 65,
				Screen_MainMenu.GAME_HEIGHT / 2 - 20, 40, 40);
		this.unitTraingBounds = new Math_Overlap_Rectangle(50, 20, 100, 100);
		this.storeCenterBounds = new Math_Overlap_Rectangle(
				Screen_MainMenu.GAME_WIDTH - 150, 20, 100, 100);

		this.XbuttonBounds = new Math_Overlap_Rectangle(
				Screen_MainMenu.GAME_WIDTH - 35,
				Screen_MainMenu.GAME_HEIGHT - 35, 30, 30);

		this.touchPoint = new Math_Vector();
		this.batcher = new GL_SpriteBatcher(glGraphics, 1);

		// init Stage Room
		for (int j = 0; j < WINDOWSTAGEHEIGHTMAXNUM; j++)
			for (int i = 0; i < WINDOWSTAGEWITDHMAXNUM; i++) {
				stageBounds[j * WINDOWSTAGEWITDHMAXNUM + i] = new Math_Overlap_Rectangle(
						145 + 60 * i - StageSlots.SLOTWIDTH / 2, 195 - 60 * j
								- StageSlots.SLOTHEIGHT / 2,
						StageSlots.SLOTWIDTH, StageSlots.SLOTHEIGHT);
				stageSlots[j * WINDOWSTAGEWITDHMAXNUM + i] = new StageSlots(
						145 + 60 * i, 195 - 60 * j);
			}

		for (int i = 0; i < SELECTSTABEMAXNUM; i++)
			stageSlots[i].setnStageNum(i + 1);
		// stage Init 1,2 and 0
		for (int i = SELECTSTABEMAXNUM; i < WINDOWSTAGEWITDHMAXNUM
				* WINDOWSTAGEHEIGHTMAXNUM; i++)
			stageSlots[i].setnStageNum(StageSlots.NOTEXIST);

		this.isLeftButton = false;
		this.isRightButton = false;
		this.isTraningCenterButton = false;
		this.isStoreCenterButton = false;
		this.isXbutton = false;
		this.nPageNum = 1;

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

		updateScene();

		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			touchPoint.set(event.x, event.y);
			guiCam.touchToWorld(touchPoint);

			if (event.type == TouchEvent.TOUCH_UP) {

				for (int j = 0; j < WINDOWSTAGEHEIGHTMAXNUM
						* WINDOWSTAGEWITDHMAXNUM; j++)
					if (Math_Overlap.pointInRectangle(stageBounds[j],
							touchPoint) && stageSlots[j].getIsClicked()) {
						stageSlots[j].setIsClicked(false);
						if (stageSlots[j].getnStageNum() != 0) {
							StageWindow.SELECTSTAGENUM = j;
							game.setScreen(new PrepareWindow(game));
						}
						return;
					}

				// x button
				if (Math_Overlap.pointInRectangle(XbuttonBounds, touchPoint)
						&& true == isXbutton) {
					isXbutton = false;
					Manage_Settings.save(game.getFileIO());
					game.setScreen(new Screen_MainMenu(game));
					return;
				}
				if (Math_Overlap.pointInRectangle(nextStageWindowBounds,
						touchPoint) && true == isRightButton) {
					if (nPageNum < MAXSTAGEPAGENUMBER)
						nPageNum++;
					isRightButton = false;
					return;
				}

				if (Math_Overlap.pointInRectangle(unitTraingBounds, touchPoint)
						&& true == isTraningCenterButton) {
					isTraningCenterButton = false;
					game.setScreen(new PlaySettingWindow(game));
					return;
				}

				if (Math_Overlap
						.pointInRectangle(storeCenterBounds, touchPoint)
						&& true == isStoreCenterButton) {
					isStoreCenterButton = false;
					return;
				}

				if (Math_Overlap.pointInRectangle(previousStageWindowBounds,
						touchPoint) && true == isLeftButton) {
					if (nPageNum > 1)
						nPageNum--;
					isLeftButton = false;
					return;
				}
				for (int j = 0; j < WINDOWSTAGEHEIGHTMAXNUM
						* WINDOWSTAGEWITDHMAXNUM; j++)
					stageSlots[j].setIsClicked(false);

				this.isLeftButton = false;
				this.isRightButton = false;
				this.isTraningCenterButton = false;
				this.isXbutton = false;
				this.isStoreCenterButton = false;

			}

			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (Math_Overlap.pointInRectangle(nextStageWindowBounds,
						touchPoint)) {
					// Assets.playSound(Assets.clickSound);
					this.isRightButton = true;
					return;
				}

				if (Math_Overlap.pointInRectangle(previousStageWindowBounds,
						touchPoint)) {
					this.isLeftButton = true;
					return;
				}

				if (Math_Overlap.pointInRectangle(unitTraingBounds, touchPoint)) {
					isTraningCenterButton = true;
					return;
				}

				if (Math_Overlap.pointInRectangle(XbuttonBounds, touchPoint)) {

					isXbutton = true;
					return;
				}

				if (Math_Overlap
						.pointInRectangle(storeCenterBounds, touchPoint)) {
					isStoreCenterButton = true;
					return;
				}

				for (int j = 0; j < WINDOWSTAGEHEIGHTMAXNUM
						* WINDOWSTAGEWITDHMAXNUM; j++)
					if (Math_Overlap.pointInRectangle(stageBounds[j],
							touchPoint)) {
						// Assets.playSound(Assets.clickSound);
						stageSlots[j].setIsClicked(true);
						return;
					}

				for (int j = 0; j < WINDOWSTAGEHEIGHTMAXNUM
						* WINDOWSTAGEWITDHMAXNUM; j++)
					stageSlots[j].setIsClicked(false);

				this.isTraningCenterButton = false;
				this.isStoreCenterButton = false;
				this.isLeftButton = false;
				this.isRightButton = false;
				this.isXbutton = false;

			}
		}

	}

	private void updateScene() {
		if (1 != nPageNum)
			for (int i = 0; i < WINDOWSTAGEWITDHMAXNUM
					* WINDOWSTAGEHEIGHTMAXNUM; i++)
				stageSlots[i].setnStageNum(0);
		else
			for (int i = 0; i < SELECTSTABEMAXNUM; i++)
				stageSlots[i].setnStageNum(i + 1);
	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);

		// Draw back ground
		batcher.beginBatch(Manage_Assets.ttScrMainMenu);
		batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
				Screen_MainMenu.GAME_HEIGHT / 2, Screen_MainMenu.GAME_WIDTH,
				Screen_MainMenu.GAME_HEIGHT, Manage_Assets.ttgScrScrMainMenu);
		batcher.endBatch();

		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		// Draw Paper Ground
		batcher.beginBatch(Manage_Assets.ttSettingPage);
		batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
				Screen_MainMenu.GAME_HEIGHT / 2, 455, 340,
				Manage_Assets.ttgSettingPage);
		batcher.endBatch();

		// Draw Stage Text

		batcher.beginBatch(Manage_Assets.ttStageItems);
		batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2, 240, 100, 30,
				Manage_Assets.ttgStageText);
		batcher.endBatch();

		// Draw Stage Slots
		for (int i = 0; i < WINDOWSTAGEWITDHMAXNUM * WINDOWSTAGEHEIGHTMAXNUM; i++) {
			if (stageSlots[i].getnStageNum() == StageSlots.NOTEXIST) {
				batcher.beginBatch(Manage_Assets.ttStageItems);
				batcher.drawSprite(stageSlots[i].position.x,
						stageSlots[i].position.y, StageSlots.SLOTWIDTH,
						StageSlots.SLOTHEIGHT, Manage_Assets.ttgStageblankStage);
				batcher.endBatch();

			} else {
				drawStage(stageSlots[i].position.x, stageSlots[i].position.y,
						stageSlots[i].getnStageNum());
			}
		}

		drawArrowButton();
		drawPageNum(nPageNum);

		// draw stage slot boundry clicked

		for (int i = 0; i < WINDOWSTAGEHEIGHTMAXNUM * WINDOWSTAGEWITDHMAXNUM; i++)
			if (stageSlots[i].getIsClicked()) {
				batcher.beginBatch(Manage_Assets.ttStageItems);
				batcher.drawSprite(stageSlots[i].position.x,
						stageSlots[i].position.y, StageSlots.SLOTWIDTH
								+ STAGESELECTMARGEN, StageSlots.SLOTHEIGHT
								+ STAGESELECTMARGEN,
						Manage_Assets.ttgStageSelect);
				batcher.endBatch();
			}

		// training camp
		batcher.beginBatch(Manage_Assets.ttStageTraingCamp);
		if (false == isTraningCenterButton)
			batcher.drawSprite(100, 70, 100, 100,
					Manage_Assets.ttgStageTraningCenter);
		else
			batcher.drawSprite(100, 70, 100, 100,
					Manage_Assets.ttgStageTraningCenterPressed);
		batcher.endBatch();
		/*
		 * // Store Center batcher.beginBatch(Manage_Assets.ttGameSceneStore);
		 * if (false == isStoreCenterButton)
		 * batcher.drawSprite(Screen_MainMenu.GAME_WIDTH - 100, 70, 100, 100,
		 * Manage_Assets.ttgStageTraningCenter); else
		 * batcher.drawSprite(Screen_MainMenu.GAME_WIDTH - 100, 70, 100, 100,
		 * Manage_Assets.ttgStageTraningCenterPressed); batcher.endBatch();
		 */
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

	private void drawPageNum(int nPageNum) {
		batcher.beginBatch(Manage_Assets.ttStageItemStarCycle);
		if (1 == nPageNum) {
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2, 83, 100, 18,
					Manage_Assets.ttgStageCycle01);
		} else if (2 == nPageNum) {
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2, 83, 100, 18,
					Manage_Assets.ttgStageCycle02);
		} else if (3 == nPageNum) {
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2, 83, 100, 18,
					Manage_Assets.ttgStageCycle03);
		} else if (4 == nPageNum) {
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2, 83, 100, 18,
					Manage_Assets.ttgStageCycle04);
		} else if (5 == nPageNum) {
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2, 83, 100, 18,
					Manage_Assets.ttgStageCycle05);
		} else {
			Log.e("errorcode03", "notExistStagePage");
		}
		batcher.endBatch();

	}

	private void drawArrowButton() {
		batcher.beginBatch(Manage_Assets.ttStageItems);
		if (!isLeftButton) {
			batcher.drawSprite(53 - 2, Screen_MainMenu.GAME_HEIGHT / 2 - 1,
					-40, 40, Manage_Assets.ttgStageNextButtonPressed);
		} else {
			batcher.drawSprite(53, Screen_MainMenu.GAME_HEIGHT / 2, -40, 40,
					Manage_Assets.ttgStageNextButton);
		}
		batcher.endBatch();

		batcher.beginBatch(Manage_Assets.ttStageItems);
		if (!isRightButton) {
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH - 45 + 2,
					Screen_MainMenu.GAME_HEIGHT / 2 - 1, 40, 40,
					Manage_Assets.ttgStageNextButtonPressed);
		} else {
			batcher.drawSprite(Screen_MainMenu.GAME_WIDTH - 45,
					Screen_MainMenu.GAME_HEIGHT / 2, 40, 40,
					Manage_Assets.ttgStageNextButton);
		}
		batcher.endBatch();

	}

	private void drawStage(float x, float y, int index) {
		if (1 == index) {
			batcher.beginBatch(Manage_Assets.ttStageItems);
			batcher.drawSprite(x, y, StageSlots.SLOTWIDTH,
					StageSlots.SLOTHEIGHT, Manage_Assets.ttgStageStage01);
			batcher.endBatch();
			drawStageStar(x, y, StageInformation.Stage.s1.nStageStar);

		} else if (2 == index) {
			batcher.beginBatch(Manage_Assets.ttStageItems);
			batcher.drawSprite(x, y, StageSlots.SLOTWIDTH,
					StageSlots.SLOTHEIGHT, Manage_Assets.ttgStageStage02);
			batcher.endBatch();
			drawStageStar(x, y, StageInformation.Stage.s2.nStageStar);

		} else if (3 == index) {
			batcher.beginBatch(Manage_Assets.ttStageItems);
			batcher.drawSprite(x, y, StageSlots.SLOTWIDTH,
					StageSlots.SLOTHEIGHT, Manage_Assets.ttgStageStage03);
			batcher.endBatch();
			drawStageStar(x, y, StageInformation.Stage.s3.nStageStar);
		} else if (4 == index) {
			batcher.beginBatch(Manage_Assets.ttStageItems);
			batcher.drawSprite(x, y, StageSlots.SLOTWIDTH,
					StageSlots.SLOTHEIGHT, Manage_Assets.ttgStageStage04);
			batcher.endBatch();
			drawStageStar(x, y, StageInformation.Stage.s4.nStageStar);
		} else if (5 == index) {
			batcher.beginBatch(Manage_Assets.ttStageItems);
			batcher.drawSprite(x, y, StageSlots.SLOTWIDTH,
					StageSlots.SLOTHEIGHT, Manage_Assets.ttgStageStage05);
			batcher.endBatch();
			drawStageStar(x, y, StageInformation.Stage.s5.nStageStar);
		} else if (6 == index) {
			batcher.beginBatch(Manage_Assets.ttStageItems);
			batcher.drawSprite(x, y, StageSlots.SLOTWIDTH,
					StageSlots.SLOTHEIGHT, Manage_Assets.ttgStageStage06);
			batcher.endBatch();
			drawStageStar(x, y, StageInformation.Stage.s6.nStageStar);
		} else if (7 == index) {
			batcher.beginBatch(Manage_Assets.ttStageItems);
			batcher.drawSprite(x, y, StageSlots.SLOTWIDTH,
					StageSlots.SLOTHEIGHT, Manage_Assets.ttgStageStage07);
			batcher.endBatch();
			drawStageStar(x, y, StageInformation.Stage.s7.nStageStar);
		} else if (8 == index) {
			batcher.beginBatch(Manage_Assets.ttStageItems);
			batcher.drawSprite(x, y, StageSlots.SLOTWIDTH,
					StageSlots.SLOTHEIGHT, Manage_Assets.ttgStageStage08);
			batcher.endBatch();
			drawStageStar(x, y, StageInformation.Stage.s8.nStageStar);
		}
	}

	private void drawStageStar(float x, float y, int level) {
		batcher.beginBatch(Manage_Assets.ttStageItemStarCycle);
		if (3 == level)
			batcher.drawSprite(x + STAGEPANALSTARGAB, y, STAGESTARWIDTH,
					STAGESTARHEIGHT, Manage_Assets.ttgStageStar03);
		else if (2 == level)
			batcher.drawSprite(x + STAGEPANALSTARGAB, y, STAGESTARWIDTH,
					STAGESTARHEIGHT, Manage_Assets.ttgStageStar02);
		else if (1 == level)
			batcher.drawSprite(x + STAGEPANALSTARGAB, y, STAGESTARWIDTH,
					STAGESTARHEIGHT, Manage_Assets.ttgStageStar01);
		else if (0 == level)
			batcher.drawSprite(x + STAGEPANALSTARGAB, y, STAGESTARWIDTH,
					STAGESTARHEIGHT, Manage_Assets.ttgStageStar00);
		else {
			Log.e("errorcode04", "overFlowStageStar");
		}
		batcher.endBatch();
	}

	@Override
	public void dispose() {
	}
}

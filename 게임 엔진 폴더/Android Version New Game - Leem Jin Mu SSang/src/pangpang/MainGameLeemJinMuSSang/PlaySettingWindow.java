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
import pangpang.MainGameLeemJinMuSSang.UnitInformation.ALLY.ArcherMan;
import pangpang.MainGameLeemJinMuSSang.UnitInformation.ALLY.AxeMan;
import pangpang.MainGameLeemJinMuSSang.UnitInformation.ALLY.MonkMan;
import pangpang.MainGameLeemJinMuSSang.UnitInformation.ALLY.SpearMan;
import android.util.Log;

public class PlaySettingWindow extends GL_Screen {
	private GL_Camera2D guiCam;
	private GL_SpriteBatcher batcher;
	private Math_Overlap_Rectangle nextBounds;
	private Math_Overlap_Rectangle upgradeBounds;
	private Math_Overlap_Rectangle XbuttonBounds;

	private Math_Overlap_Rectangle[] unitsBounds = new Math_Overlap_Rectangle[9];
	private Math_Vector touchPoint;
	private UnitSlots[] unitSlots = new UnitSlots[9];

	private final int MAXUNITSLOTSLAYER = 3;
	private boolean isGameStartClick;
	private boolean isUnitUpgrade;
	private boolean isXbutton;

	private final int AXEMAN = 1;
	private final int MONKMAN = 2;
	private final int ARCHER = 3;
	private final int SPEAR = 4;
	private float fUpdateTime;

	private int selectedSlot;
	private final int MAXLEVELBAR = 7;

	private GL_TextureRegion _keyFrame;

	private int nBasicCoin;
	private int cost;

	public PlaySettingWindow(IFace_Game game) {
		super(game);

		this.guiCam = new GL_Camera2D(glGraphics, Screen_MainMenu.GAME_WIDTH,
				Screen_MainMenu.GAME_HEIGHT);
		this.nextBounds = new Math_Overlap_Rectangle(300, 70, 80, 30);
		this.upgradeBounds = new Math_Overlap_Rectangle(210, 70, 80, 30);
		this.XbuttonBounds = new Math_Overlap_Rectangle(
				Screen_MainMenu.GAME_WIDTH - 35,
				Screen_MainMenu.GAME_HEIGHT - 35, 30, 30);
		this.touchPoint = new Math_Vector();
		this.batcher = new GL_SpriteBatcher(glGraphics, 100);
		this.fUpdateTime = 0;

		this.isGameStartClick = false;
		this.isUnitUpgrade = false;
		this.isXbutton = false;

		for (int i = 0; i < MAXUNITSLOTSLAYER; i++)
			for (int j = 0; j < MAXUNITSLOTSLAYER; j++) {
				this.unitsBounds[j + 3 * i] = new Math_Overlap_Rectangle(105
						+ 40 * j - (UnitSlots.SLOTWIDTH / 2), 205 - 55 * i
						- (UnitSlots.SLOTHEIGHT / 2), UnitSlots.SLOTWIDTH,
						UnitSlots.SLOTHEIGHT);
				this.unitSlots[j + 3 * i] = new UnitSlots(105 + 40 * j,
						205 - 55 * i);
			}

		this.unitSlots[0].setnUnitNum(AXEMAN);
		this.unitSlots[1].setnUnitNum(MONKMAN);
		this.unitSlots[2].setnUnitNum(ARCHER);
		this.unitSlots[3].setnUnitNum(SPEAR);


		for (int i = 4; i < 9; i++)
			this.unitSlots[i].setnUnitNum(UnitSlots.NOTEXIST);

		this.unitSlots[0].setIsClicked(true);
		this.nBasicCoin = Manage_Settings.nStageCoin;
		this.cost = 0;
		selectedSlot = 0;
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

				if (Math_Overlap.pointInRectangle(nextBounds, touchPoint)
						&& true == isGameStartClick) {
					StageWindow.SELECTSTAGENUM = StageWindow.SELECTSTABEMAXNUM - 1;
					isGameStartClick = false;
					game.setScreen(new PrepareWindow(game));
					return;
				}

				// xButton
				if (Math_Overlap.pointInRectangle(XbuttonBounds, touchPoint)
						&& true == isXbutton) {
					isXbutton = false;
					game.setScreen(new StageWindow(game));
					return;
				}
				// update
				if (Math_Overlap.pointInRectangle(upgradeBounds, touchPoint)
						&& true == isUnitUpgrade) {
					isUnitUpgrade = false;

					if (Manage_Settings.nStageCoin >= cost
							&& returnSelectLv(selectedSlot) < 7) {
						Manage_Assets
								.playSound(Manage_Assets.soundLvUp);
						Manage_Settings.nStageCoin -= cost;
						unitUpdate(selectedSlot);
					}
					return;
				}

				// setting
				for (int k = 0; k < MAXUNITSLOTSLAYER * MAXUNITSLOTSLAYER; k++) {
					if (Math_Overlap.pointInRectangle(unitsBounds[k],
							touchPoint)
							&& true == this.unitSlots[k].getIsClicked()) {
						this.unitSlots[k].setIsClicked(true);
						this.selectedSlot = k;
						return;
					}
				}

				this.isXbutton = false;
				this.isGameStartClick = false;
				this.isUnitUpgrade = false;
			}

			if (event.type == TouchEvent.TOUCH_DOWN) {
				touchPoint.set(event.x, event.y);
				guiCam.touchToWorld(touchPoint);

				nBasicCoin = Manage_Settings.nStageCoin;

				if (Math_Overlap.pointInRectangle(nextBounds, touchPoint)) {
					this.isGameStartClick = true;
					return;
				}

				if (Math_Overlap.pointInRectangle(upgradeBounds, touchPoint)) {
					// Assets.playSound(Assets.clickSound);
					this.isUnitUpgrade = true;
					return;
				}

				if (Math_Overlap.pointInRectangle(XbuttonBounds, touchPoint)) {

					isXbutton = true;
					return;
				}

				for (int k = 0; k < MAXUNITSLOTSLAYER * MAXUNITSLOTSLAYER; k++) {
					if (Math_Overlap.pointInRectangle(unitsBounds[k],
							touchPoint)) {
						for (int kl = 0; kl < MAXUNITSLOTSLAYER
								* MAXUNITSLOTSLAYER; kl++)
							this.unitSlots[kl].setIsClicked(false);

						this.unitSlots[k].setIsClicked(true);
						return;
					}
				}

				this.isGameStartClick = false;
				this.isUnitUpgrade = false;
				this.isXbutton = false;
			}

		}
	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);

		batcher.beginBatch(Manage_Assets.ttSettingBackGround);
		batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
				Screen_MainMenu.GAME_HEIGHT / 2, Screen_MainMenu.GAME_WIDTH,
				Screen_MainMenu.GAME_HEIGHT, Manage_Assets.ttgSettingBackground);
		batcher.endBatch();

		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		// Draw Page Image
		batcher.beginBatch(Manage_Assets.ttSettingPage);
		batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
				Screen_MainMenu.GAME_HEIGHT / 2, 455, 340,
				Manage_Assets.ttgSettingPage);
		batcher.endBatch();

		// Draw Page BackGround
		batcher.beginBatch(Manage_Assets.ttSettingUnitBackGround);
		batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2 - 5,
				Screen_MainMenu.GAME_HEIGHT / 2 - 15, 299, 170,
				Manage_Assets.ttgSettingUnitBackGround);
		batcher.endBatch();

		// Draw Item Page
		batcher.beginBatch(Manage_Assets.ttSettingItemPage);
		batcher.drawSprite(296, 167, 170, 100, Manage_Assets.ttgSettingItemPage);
		batcher.endBatch();

		// Draw Unit Highlight
		batcher.beginBatch(Manage_Assets.ttSettingUnitMudae);
		batcher.drawSprite(241, Screen_MainMenu.GAME_HEIGHT / 2 + 8, 50, 85,
				Manage_Assets.ttgSettingUnitMudae);
		batcher.endBatch();

		// Draw Unit-text Text

		batcher.beginBatch(Manage_Assets.ttSettingItem);
		batcher.drawSprite(110, 247, 40, 30, Manage_Assets.ttgSettingUnitText);
		batcher.endBatch();

		// Draw Coin
		_keyFrame = Manage_Assets.aniCoin.getKeyFrame(fUpdateTime,
				GL_Animation.ANIMATION_LOOPING);

		batcher.beginBatch(Manage_Assets.ttCoin);
		batcher.drawSprite(Screen_MainMenu.GAME_WIDTH / 2,
				Screen_MainMenu.GAME_HEIGHT - 70, 30, 30, _keyFrame);
		batcher.endBatch();

		if (nBasicCoin > Manage_Settings.nStageCoin)
			nBasicCoin--;

		batcher.beginBatch(Manage_Assets.ttFont);
		Manage_Assets.MoneyNumberFont.drawText(batcher, "" + nBasicCoin,
				Screen_MainMenu.GAME_WIDTH / 2 + 24,
				Screen_MainMenu.GAME_HEIGHT - 72, 20, 23, 15);

		batcher.endBatch();

		// Draw Cost

		drawUnitCost(selectedSlot);

		// Draw the Unit Slot
		for (int i = 0; i < MAXUNITSLOTSLAYER * MAXUNITSLOTSLAYER; i++) {
			batcher.beginBatch(Manage_Assets.ttSettingItem);
			if (unitSlots[i].getnUnitNum() != UnitSlots.NOTEXIST)
				batcher.drawSprite(unitSlots[i].position.x,
						unitSlots[i].position.y, UnitSlots.SLOTWIDTH,
						UnitSlots.SLOTHEIGHT, Manage_Assets.ttgSettingItemOn);
			else
				batcher.drawSprite(unitSlots[i].position.x,
						unitSlots[i].position.y, UnitSlots.SLOTWIDTH,
						UnitSlots.SLOTHEIGHT, Manage_Assets.ttgSettingItemOn);
			batcher.endBatch();
		}

		for (int i = 0; i < MAXUNITSLOTSLAYER * MAXUNITSLOTSLAYER; i++) {
			if (unitSlots[i].getIsClicked()) {
				batcher.beginBatch(Manage_Assets.ttSettingItem);
				batcher.drawSprite(unitSlots[i].position.x,
						unitSlots[i].position.y, UnitSlots.SLOTWIDTH,
						UnitSlots.SLOTHEIGHT,
						Manage_Assets.ttgSettingSelectUnit);
				batcher.endBatch();
			}
		}

		// draw the Unit Animation

		if (AXEMAN == unitSlots[AXEMAN - 1].getnUnitNum()) {
			batcher.beginBatch(Manage_Assets.ttAlly_AxeMan);

			_keyFrame = Manage_Assets.aniChrAllyAxeMove.getKeyFrame(
					fUpdateTime, GL_Animation.ANIMATION_LOOPING);

			batcher.drawSprite(unitSlots[AXEMAN - 1].position.x,
					unitSlots[AXEMAN - 1].position.y + 8,
					UnitSlots.SLOTWIDTH + 40, UnitSlots.SLOTHEIGHT + 10,
					_keyFrame);
			batcher.endBatch();
		}

		if (MONKMAN == unitSlots[MONKMAN - 1].getnUnitNum()) {
			batcher.beginBatch(Manage_Assets.ttAlly_Monk);
			_keyFrame = Manage_Assets.aniChrAllyMonkMove.getKeyFrame(
					fUpdateTime, GL_Animation.ANIMATION_LOOPING);
			batcher.drawSprite(unitSlots[MONKMAN - 1].position.x,
					unitSlots[MONKMAN - 1].position.y + 8,
					UnitSlots.SLOTWIDTH + 40, UnitSlots.SLOTHEIGHT + 10,
					_keyFrame);

			batcher.endBatch();

		}

		if (ARCHER == unitSlots[ARCHER - 1].getnUnitNum()) {
			batcher.beginBatch(Manage_Assets.ttAllyArcherMan);
			_keyFrame = Manage_Assets.anichrAllyArcherMov.getKeyFrame(
					fUpdateTime, GL_Animation.ANIMATION_LOOPING);
			batcher.drawSprite(unitSlots[ARCHER - 1].position.x,
					unitSlots[ARCHER - 1].position.y + 8,
					UnitSlots.SLOTWIDTH + 40, UnitSlots.SLOTHEIGHT + 10,
					_keyFrame);

			batcher.endBatch();

		}

		if (SPEAR == unitSlots[SPEAR - 1].getnUnitNum()) {
			batcher.beginBatch(Manage_Assets.ttAllySpearMan);
			_keyFrame = Manage_Assets.anichrAllySpearmanMov.getKeyFrame(
					fUpdateTime, GL_Animation.ANIMATION_LOOPING);
			batcher.drawSprite(unitSlots[SPEAR - 1].position.x,
					unitSlots[SPEAR - 1].position.y + 8,
					UnitSlots.SLOTWIDTH + 40, UnitSlots.SLOTHEIGHT + 10,
					_keyFrame);

			batcher.endBatch();

		}

		// Draw Icon
		// Lv
		batcher.beginBatch(Manage_Assets.ttSettingIcons);
		batcher.drawSprite(280, 196, 13, 13, Manage_Assets.ttgSettingLvIcon);
		batcher.endBatch();
		// HP
		batcher.beginBatch(Manage_Assets.ttSettingIcons);
		batcher.drawSprite(280, 177, 13, 13, Manage_Assets.ttgSettingHpIcon);
		batcher.endBatch();
		// Attack
		batcher.beginBatch(Manage_Assets.ttSettingIcons);
		batcher.drawSprite(280, 157, 13, 13, Manage_Assets.ttgSettingAttackIcon);
		batcher.endBatch();
		// Speed
		batcher.beginBatch(Manage_Assets.ttSettingIcons);
		batcher.drawSprite(280, 137, 14, 13, Manage_Assets.ttgSettingSpeedIcon);
		batcher.endBatch();

		// Draw Unit imformation

		selectCharecter(selectedSlot);

		// Draw Start Button

		batcher.beginBatch(Manage_Assets.ttSettingButton);
		if (false == isGameStartClick)
			batcher.drawSprite(340, 85, 80, 30,
					Manage_Assets.ttgSettingStartButton);
		else
			batcher.drawSprite(340, 85, 80, 30,
					Manage_Assets.ttgSettingStartButtonPressed);

		batcher.endBatch();

		// Draw Upgrade Button
		batcher.beginBatch(Manage_Assets.ttSettingButton);

		if (false == isUnitUpgrade)
			batcher.drawSprite(250, 85, 80, 30,
					Manage_Assets.ttgSettingUpgradeButton);
		else
			batcher.drawSprite(250, 85, 80, 30,
					Manage_Assets.ttgSettingUpgradeButtonPressed);

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

		fUpdateTime += deltaTime;
	}

	@Override
	public void dispose() {
	}

	private void selectStateBar(int location_x, int location_y, int index) {
		if (0 == index)
			batcher.drawSprite(location_x, location_y, 80, 10,
					Manage_Assets.ttgSettingStateBar00);
		else if (1 == index)
			batcher.drawSprite(location_x, location_y, 80, 10,
					Manage_Assets.ttgSettingStateBar01);
		else if (2 == index)
			batcher.drawSprite(location_x, location_y, 80, 10,
					Manage_Assets.ttgSettingStateBar02);
		else if (3 == index)
			batcher.drawSprite(location_x, location_y, 80, 10,
					Manage_Assets.ttgSettingStateBar03);
		else if (4 == index)
			batcher.drawSprite(location_x, location_y, 80, 10,
					Manage_Assets.ttgSettingStateBar04);
		else if (5 == index)
			batcher.drawSprite(location_x, location_y, 80, 10,
					Manage_Assets.ttgSettingStateBar05);
		else if (6 == index)
			batcher.drawSprite(location_x, location_y, 80, 10,
					Manage_Assets.ttgSettingStateBar06);
		else if (7 == index)
			batcher.drawSprite(location_x, location_y, 80, 10,
					Manage_Assets.ttgSettingStateBar07);
		else{
			batcher.drawSprite(location_x, location_y, 80, 10,
					Manage_Assets.ttgSettingStateBar07);
			Log.e("ErrorCode01_SettingWindow", "stateBar over Flow");
		}

	}

	private void selectCharecter(int index) {
		GL_TextureRegion _keyFrame;
		int _index = index + 1;
		int _hpLv;
		int _speedLv;
		int _DamageLv;
		int _unitLv;

		if (AXEMAN == _index) {
			batcher.beginBatch(Manage_Assets.ttAlly_AxeMan);
			_keyFrame = Manage_Assets.aniChrAllyAxeMove.getKeyFrame(
					fUpdateTime, GL_Animation.ANIMATION_LOOPING);
			batcher.drawSprite(242, 184, UnitSlots.SLOTWIDTH * 3.5f,
					UnitSlots.SLOTHEIGHT * 2.5f, _keyFrame);

			batcher.endBatch();
			_unitLv = Manage_Settings.nUnitAxeManLv;
			_hpLv = (int) ((int) AxeMan.Bonus(Manage_Settings.nUnitAxeManLv)
					* AxeMan.HPBasic + AxeMan.HPBasic);
			_speedLv = (int) ((int) AxeMan.Bonus(Manage_Settings.nUnitAxeManLv)
					* AxeMan.SpeedBasic + AxeMan.SpeedBasic);
			_DamageLv = (int) ((int) AxeMan
					.Bonus(Manage_Settings.nUnitAxeManLv) * AxeMan.DamageBasic + AxeMan.DamageBasic);

		} else if (MONKMAN == _index) {

			batcher.beginBatch(Manage_Assets.ttAlly_Monk);
			_keyFrame = Manage_Assets.aniChrAllyMonkMove.getKeyFrame(
					fUpdateTime, GL_Animation.ANIMATION_LOOPING);
			batcher.drawSprite(242, 184, UnitSlots.SLOTWIDTH * 3.5f,
					UnitSlots.SLOTHEIGHT * 2.5f, _keyFrame);

			batcher.endBatch();
			_unitLv = Manage_Settings.nUnitMonkManLv;
			_hpLv = (int) ((int) MonkMan.Bonus(Manage_Settings.nUnitMonkManLv)
					* MonkMan.HPBasic + MonkMan.HPBasic);
			_speedLv = (int) ((int) MonkMan
					.Bonus(Manage_Settings.nUnitMonkManLv) * MonkMan.SpeedBasic + MonkMan.SpeedBasic);
			_DamageLv = (int) ((int) MonkMan
					.Bonus(Manage_Settings.nUnitMonkManLv)
					* MonkMan.DamageBasic + MonkMan.DamageBasic);
		} else if (ARCHER == _index) {

			batcher.beginBatch(Manage_Assets.ttAllyArcherMan);
			_keyFrame = Manage_Assets.anichrAllyArcherMov.getKeyFrame(
					fUpdateTime, GL_Animation.ANIMATION_LOOPING);
			batcher.drawSprite(242, 184, UnitSlots.SLOTWIDTH * 3.5f,
					UnitSlots.SLOTHEIGHT * 2.5f, _keyFrame);

			batcher.endBatch();
			_unitLv = Manage_Settings.nUnitArcherLv;
			_hpLv = (int) ((int) ArcherMan.Bonus(Manage_Settings.nUnitArcherLv)
					* ArcherMan.HPBasic + ArcherMan.HPBasic);
			_speedLv = (int) ((int) ArcherMan
					.Bonus(Manage_Settings.nUnitArcherLv)
					* ArcherMan.SpeedBasic + ArcherMan.SpeedBasic);
			_DamageLv = (int) ((int) ArcherMan
					.Bonus(Manage_Settings.nUnitArcherLv)
					* ArcherMan.DamageBasic + ArcherMan.DamageBasic);
		} else if (SPEAR == _index) {

			batcher.beginBatch(Manage_Assets.ttAllySpearMan);
			_keyFrame = Manage_Assets.anichrAllySpearmanMov.getKeyFrame(
					fUpdateTime, GL_Animation.ANIMATION_LOOPING);
			batcher.drawSprite(242, 184, UnitSlots.SLOTWIDTH * 3.5f,
					UnitSlots.SLOTHEIGHT * 2.5f, _keyFrame);

			batcher.endBatch();
			_unitLv = Manage_Settings.nUnitSpearLv;
			_hpLv = (int) ((int) MonkMan.Bonus(Manage_Settings.nUnitSpearLv)
					* SpearMan.HPBasic + SpearMan.HPBasic);
			_speedLv = (int) ((int) SpearMan
					.Bonus(Manage_Settings.nUnitSpearLv) * SpearMan.SpeedBasic + SpearMan.SpeedBasic);
			_DamageLv = (int) ((int) SpearMan
					.Bonus(Manage_Settings.nUnitSpearLv) * SpearMan.DamageBasic + SpearMan.DamageBasic);
		} else {
			_hpLv = 0;
			_speedLv = 0;
			_DamageLv = 0;
			_unitLv = 0;
		}

		// Log.e("debugCode01", " L: " + _unitLv + " h:"
		// + (int) (_hpLv * MAXLEVELBAR / UnitInformation.MAXHP) + " a:"
		// + (int) (_DamageLv * MAXLEVELBAR / UnitInformation.MAXDAMAGE)
		// + " s:"
		// + (int) (_speedLv * MAXLEVELBAR / UnitInformation.MAXHP));

		// lv
		batcher.beginBatch(Manage_Assets.ttStateBar);
		selectStateBar(330, 196, _unitLv);
		batcher.endBatch();

		// hp
		batcher.beginBatch(Manage_Assets.ttStateBar);
		selectStateBar(330, 176,
				(int) (_hpLv * MAXLEVELBAR / UnitInformation.MAXHP));
		batcher.endBatch();

		// attack
		batcher.beginBatch(Manage_Assets.ttStateBar);
		selectStateBar(330, 156,
				(int) (_DamageLv * MAXLEVELBAR / UnitInformation.MAXDAMAGE));
		batcher.endBatch();

		// speed
		batcher.beginBatch(Manage_Assets.ttStateBar);
		selectStateBar(330, 136,
				(int) (_speedLv * MAXLEVELBAR / UnitInformation.MAXSPEED));
		batcher.endBatch();

	}

	private void unitUpdate(int index) {

		int _index = index + 1;

		if (AXEMAN == _index) {
			if (Manage_Settings.nUnitAxeManLv < MAXLEVELBAR)
				Manage_Settings.nUnitAxeManLv++;
		} else if (MONKMAN == _index) {
			if (Manage_Settings.nUnitMonkManLv < MAXLEVELBAR)
				Manage_Settings.nUnitMonkManLv++;
		} else if (ARCHER == _index) {
			if (Manage_Settings.nUnitArcherLv < MAXLEVELBAR)
				Manage_Settings.nUnitArcherLv++;
		} else if (SPEAR == _index) {
			if (Manage_Settings.nUnitSpearLv < MAXLEVELBAR)
				Manage_Settings.nUnitSpearLv++;
		} else
			Log.e("ErrorCode03_SettingWindow", "update over Flow");
	}

	private void drawUnitCost(int index) {
		int _index = index + 1;
		if (AXEMAN == _index) {
			if (1 == Manage_Settings.nUnitAxeManLv)
				cost = UnitInformation.ALLY.AxeMan.UPGRADECOST01;
			else if (2 == Manage_Settings.nUnitAxeManLv)
				cost = UnitInformation.ALLY.AxeMan.UPGRADECOST02;
			else if (3 == Manage_Settings.nUnitAxeManLv)
				cost = UnitInformation.ALLY.AxeMan.UPGRADECOST03;
			else if (4 == Manage_Settings.nUnitAxeManLv)
				cost = UnitInformation.ALLY.AxeMan.UPGRADECOST04;
			else if (5 == Manage_Settings.nUnitAxeManLv)
				cost = UnitInformation.ALLY.AxeMan.UPGRADECOST05;
			else if (6 == Manage_Settings.nUnitAxeManLv)
				cost = UnitInformation.ALLY.AxeMan.UPGRADECOST06;
			else if (7 == Manage_Settings.nUnitAxeManLv)
				cost = UnitInformation.ALLY.AxeMan.UPGRADECOST07;

		} else if (MONKMAN == _index) {
			if (1 == Manage_Settings.nUnitMonkManLv)
				cost = UnitInformation.ALLY.MonkMan.UPGRADECOST01;
			else if (2 == Manage_Settings.nUnitMonkManLv)
				cost = UnitInformation.ALLY.MonkMan.UPGRADECOST02;
			else if (3 == Manage_Settings.nUnitMonkManLv)
				cost = UnitInformation.ALLY.MonkMan.UPGRADECOST03;
			else if (4 == Manage_Settings.nUnitMonkManLv)
				cost = UnitInformation.ALLY.MonkMan.UPGRADECOST04;
			else if (5 == Manage_Settings.nUnitMonkManLv)
				cost = UnitInformation.ALLY.MonkMan.UPGRADECOST05;
			else if (6 == Manage_Settings.nUnitMonkManLv)
				cost = UnitInformation.ALLY.MonkMan.UPGRADECOST06;
			else if (7 == Manage_Settings.nUnitMonkManLv)
				cost = UnitInformation.ALLY.MonkMan.UPGRADECOST07;

		}  else if (ARCHER == _index) {
			if (1 == Manage_Settings.nUnitArcherLv)
				cost = UnitInformation.ALLY.ArcherMan.UPGRADECOST01;
			else if (2 == Manage_Settings.nUnitArcherLv)
				cost = UnitInformation.ALLY.ArcherMan.UPGRADECOST02;
			else if (3 == Manage_Settings.nUnitArcherLv)
				cost = UnitInformation.ALLY.ArcherMan.UPGRADECOST03;
			else if (4 == Manage_Settings.nUnitArcherLv)
				cost = UnitInformation.ALLY.ArcherMan.UPGRADECOST04;
			else if (5 == Manage_Settings.nUnitArcherLv)
				cost = UnitInformation.ALLY.ArcherMan.UPGRADECOST05;
			else if (6 == Manage_Settings.nUnitArcherLv)
				cost = UnitInformation.ALLY.ArcherMan.UPGRADECOST06;
			else if (7 == Manage_Settings.nUnitArcherLv)
				cost = UnitInformation.ALLY.ArcherMan.UPGRADECOST07;

		} else if (SPEAR == _index) {
			if (1 == Manage_Settings.nUnitSpearLv)
				cost = UnitInformation.ALLY.SpearMan.UPGRADECOST01;
			else if (2 == Manage_Settings.nUnitSpearLv)
				cost = UnitInformation.ALLY.SpearMan.UPGRADECOST02;
			else if (3 == Manage_Settings.nUnitSpearLv)
				cost = UnitInformation.ALLY.SpearMan.UPGRADECOST03;
			else if (4 == Manage_Settings.nUnitSpearLv)
				cost = UnitInformation.ALLY.SpearMan.UPGRADECOST04;
			else if (5 == Manage_Settings.nUnitSpearLv)
				cost = UnitInformation.ALLY.SpearMan.UPGRADECOST05;
			else if (6 == Manage_Settings.nUnitSpearLv)
				cost = UnitInformation.ALLY.SpearMan.UPGRADECOST06;
			else if (7 == Manage_Settings.nUnitSpearLv)
				cost = UnitInformation.ALLY.SpearMan.UPGRADECOST07;

		}else {
			Log.e("ErrorCode04_SettingWindow", "DrawCost over Flow");
			cost = 0;
		}

		batcher.beginBatch(Manage_Assets.ttFont);
		Manage_Assets.ScoreNumberFont.drawText(batcher, "" + cost,
				Screen_MainMenu.GAME_WIDTH / 2 + 70,
				Screen_MainMenu.GAME_HEIGHT / 2 - 50, 15, 17, 13);

		batcher.endBatch();
	}

	private int returnSelectLv(int index) {
		int _index = index + 1;

		int _returnlv;
		if (AXEMAN == _index) {
			_returnlv = Manage_Settings.nUnitAxeManLv;
		} else if (MONKMAN == _index) {
			_returnlv = Manage_Settings.nUnitMonkManLv;
		} else {
			_returnlv = -1;
		}
		return _returnlv;
	}
}

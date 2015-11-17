package jrcengine.Manage;

import jrcengine.Basic.GL_Game;
import jrcengine.GL.GL_Animation;
import jrcengine.GL.GL_Font;
import jrcengine.GL.GL_Texture;
import jrcengine.GL.GL_TextureRegion;
import jrcengine.Interface.IFace_Music;
import jrcengine.Interface.IFace_Sound;
import android.util.Log;

public class Manage_Assets {

	// TEXTURE Part
	// GameSetting Button
	public static GL_Texture ttBtnGameSetting;
	public static GL_Texture ttBtnGameSettingPressed;

	// GameStart Button
	public static GL_Texture ttBtnGameStart;
	public static GL_Texture ttBtnGameStartPressed;

	// MainMenu Screen
	public static GL_Texture ttScrMainMenu;

	// MainMenu Point
	public static GL_Texture ttScrMainMenuPoint;

	// MainMenu Screen Fire
	public static GL_Texture ttScrMainFire;

	// Game Skill 1 Button
	public static GL_Texture ttGameButton01;
	// Game Skill 2 Button
	public static GL_Texture ttGameButton02;
	// Game Skill 2 Button
	public static GL_Texture ttGameButton03;
	// Game Skill 2 Button
	public static GL_Texture ttGameButton04;
	// Game Skill 2 Button
	public static GL_Texture ttGameButton05;
	// Game Skill 2 Button
	public static GL_Texture ttGameButton06;

	// chr enemy gun
	public static GL_Texture ttChrEnemyGun;

	// chr enemy sword
	public static GL_Texture ttChrEnemySword;

	// chr enemy middleboss01
	public static GL_Texture ttChrMiddleBoss01;

	// chr enemy middleboss02
	public static GL_Texture ttChrMiddleBoss02;

	// img bullet Gun
	public static GL_Texture ttImgBulletGun;

	// img enery bar
	public static GL_Texture ttEnergyBar;

	// img Loading Charecter

	public static GL_Texture ttLoadingWindowCharecter;

	// img intro Scene Srogun

	public static GL_Texture ttIntroSrogun;
	public static GL_Texture ttIntroButton;

	// img setting scene

	public static GL_Texture ttSettingItem;
	public static GL_Texture ttSettingPage;
	public static GL_Texture ttSettingButton;
	public static GL_Texture ttSettingBackGround;
	public static GL_Texture ttSettingItemPage;
	public static GL_Texture ttSettingUnitBackGround;
	public static GL_Texture ttSettingUnitMudae;

	// stage clear number panel
	public static GL_Texture ttStageClearNumberPanel;

	// img setting statebar

	public static GL_Texture ttStateBar;

	// img Setting Icon

	public static GL_Texture ttSettingIcons;

	// img stage Items

	public static GL_Texture ttStageItems;

	// image stage Items02

	public static GL_Texture ttStageItemStarCycle;

	// image stage TraningCamp

	public static GL_Texture ttStageTraingCamp;

	// image X_button

	public static GL_Texture ttXbutton;

	// image Game stage clear

	public static GL_Texture ttStageClearItem01;

	// image Game stage clear

	public static GL_Texture ttStageClearItem02;

	// Leesunsin Walking

	public static GL_Texture ttLeesunsinWalking;

	// Leesunsin Damaged

	public static GL_Texture ttLeesunsinDamaged;

	// ally Structure

	public static GL_Texture ttAllyStructure;

	// enemy Structure

	public static GL_Texture ttEnemyStructure;

	// game Play Scene Button

	public static GL_Texture ttGamePlaySceneButton;

	// story board Scene

	public static GL_Texture ttStoryBoardScene;

	// story board win and lose Scene

	public static GL_Texture ttStoryBoardWinLose;

	// game Setting Scene

	public static GL_Texture ttSettingWindowsAsset01;
	public static GL_Texture ttSettingWindowsAsset02;

	// Game BackGround

	public static GL_Texture ttGameBackGround;

	// game Unit Generate Button

	public static GL_Texture ttGameUnitGenerateButton;

	// Game Store

	public static GL_Texture ttGameSceneStore;

	// LeeSunShin Skill 01 ~ 06

	public static GL_Texture ttLeeSunShinSkill01;
	public static GL_Texture ttLeeSunShinSkill02;
	public static GL_Texture ttLeeSunShinSkill03;
	public static GL_Texture ttLeeSunShinSkill04;
	public static GL_Texture ttLeeSunShinSkill05;
	public static GL_Texture ttLeeSunShinSkill06;

	// LeeSunShin Skill Effect 01, 03

	public static GL_Texture ttLeeSunShinSKillEffect0103;

	// firde boat
	public static GL_Texture ttFiredBoatMainGame;

	// ally Structure Particle

	public static GL_Texture ttallyStructureParticle;

	// enemy Structure Particle

	public static GL_Texture ttEnemyStructureParticle;

	// game Prapared Scene Gamestart;

	public static GL_Texture ttPrepareSceneGameStartButton;

	// ally unit

	public static GL_Texture ttAlly_AxeMan;
	public static GL_Texture ttAlly_Monk;

	// Font
	public static GL_Texture ttScoreFont;

	// Coin
	public static GL_Texture ttCoin;

	// game running pause

	public static GL_Texture ttGameRunningPuase;

	// helpstoryboard

	public static GL_Texture ttHelpStoryBoardScene;

	// ally ArcherMan

	public static GL_Texture ttAllyArcherMan;

	// ally SpearMan

	public static GL_Texture ttAllySpearMan;

	// Back Ground

	public static GL_Texture ttGameSceneBackGround02;
	public static GL_Texture ttFont;

	// DoyoTomi

	public static GL_Texture ttDoyoMove;
	public static GL_Texture ttDoyoAttack01;
	public static GL_Texture ttDoyoAttack02;
	public static GL_Texture ttDoyoAttack03;
	public static GL_Texture ttDoyoAttack04;
	public static GL_Texture ttDoyoDie;
	public static GL_Texture ttDoyoSkill01;

	public static GL_Animation aniDoyoMov;
	public static GL_Animation aniDoyoAttack01;
	public static GL_Animation aniDoyoAttack02;
	public static GL_Animation aniDoyoAttack03;
	public static GL_Animation aniDoyoAttack04;
	public static GL_Animation aniDoyoDie;
	public static GL_Animation aniDoyoSkill01;

	// ANIMATION Part

	// Ally Archerman
	// Background Scene
	public static GL_Animation aniGameSceneBackGround02;

	// die
	public static GL_Animation anichrAllyArcherDie;
	// move
	public static GL_Animation anichrAllyArcherMov;
	// attacke
	public static GL_Animation anichrAllyArcherAttack;

	// Ally Spearman
	// die
	public static GL_Animation anichrAllySpearmanDie;
	// move
	public static GL_Animation anichrAllySpearmanMov;
	// attacke
	public static GL_Animation anichrAllySpearmanAttack;

	// Ani Coin
	public static GL_Animation aniCoin;
	// SrcMainMenuFire
	public static GL_Animation aniScrMainMenuFire;

	// chr Enemy Gun
	// die
	public static GL_Animation aniChrEnemyGunDie;
	// move
	public static GL_Animation aniChrEnemyGunMov;
	// shutdown
	public static GL_Animation aniChrEnemyGunShutDown;
	// shutup
	public static GL_Animation aniChrEnemyGunShutUp;

	// chr Enemy Sword
	// die
	public static GL_Animation aniChrEnemySwordDie;
	// move
	public static GL_Animation aniChrEnemySwordMov;
	// attack
	public static GL_Animation aniChrEnemySwordAttack;

	// chr Enemy MiddleBoss 01
	public static GL_Animation aniChrMiddleBoss01Die;
	// move
	public static GL_Animation aniChrMiddleBoss01Mov;
	// attack
	public static GL_Animation aniChrMiddleBoss01Attack;

	// chr Enemy MiddleBoss 02
	public static GL_Animation aniChrMiddleBoss02Die;
	// move
	public static GL_Animation aniChrMiddleBoss02Mov;
	// attack
	public static GL_Animation aniChrMiddleBoss02Attack;

	// ally Unit
	// monk
	// die
	public static GL_Animation aniChrAllyMonkDie;
	// move
	public static GL_Animation aniChrAllyMonkMove;
	// atack
	public static GL_Animation aniChrAllyMonkAttack;
	// ally axe
	// die
	public static GL_Animation aniChrAllyAxeDie;
	// move
	public static GL_Animation aniChrAllyAxeMove;
	// attack
	public static GL_Animation aniChrAllyAxeAttack;

	// Game Skill01 ~ 06 button
	public static GL_Animation aniGameSkillButton01;
	public static GL_Animation aniGameSkillButton02;
	public static GL_Animation aniGameSkillButton03;
	public static GL_Animation aniGameSkillButton04;
	public static GL_Animation aniGameSkillButton05;
	public static GL_Animation aniGameSkillButton06;

	// Loading Windows

	public static GL_Animation aniCryGunEnemy;
	public static GL_Animation aniCryLanceAlly;
	public static GL_Animation aniCrySwordEnemy;
	public static GL_Animation aniAttackLanceAlly;

	// intoSrogunAnimation

	public static GL_Animation aniIntroSrogun;

	// LeesunsinDamage

	public static GL_Animation aniLeesunsinDamaged;

	// LeesunsinWalking

	public static GL_Animation aniLeesunsinWalking;

	// Game BackGround

	public static GL_Animation aniGameBackGround;

	// LeeSunShin Skill 01 ~ 06

	public static GL_Animation aniLeeSunShinSkill01;
	public static GL_Animation aniLeeSunShinSkill02;
	public static GL_Animation aniLeeSunShinSkill03;
	public static GL_Animation aniLeeSunShinSkill04;
	public static GL_Animation aniLeeSunShinSkill05;
	public static GL_Animation aniLeeSunShinSkill06;

	// LeeSunSHin Skill Effect 01 ~ 06

	public static GL_Animation aniLeeSunShinSkillEffect01;
	public static GL_Animation aniLeeSunShinSkillEffect02;
	public static GL_Animation aniLeeSunShinSkillEffect03;
	public static GL_Animation aniLeeSunShinSkillEffect04;
	public static GL_Animation aniLeeSunShinSKillEffect05;
	public static GL_Animation aniLeeSunShinSKillEffect06;

	// fire 02

	public static GL_Animation aniFire02;

	// TEXTUREREGION Part

	// Main Game Back Ground

	public static GL_TextureRegion ttgGameBackGroundJung;
	public static GL_TextureRegion ttgGameBackGroundHu;

	// help storyboard

	public static GL_TextureRegion ttgHelpStoryBoardScene01;
	public static GL_TextureRegion ttgHelpStoryBoardScene02;
	public static GL_TextureRegion ttgHelpStoryBoardScene03;
	public static GL_TextureRegion ttgHelpStoryBoardScene04;
	public static GL_TextureRegion ttgHelpStoryBoardScene05;
	public static GL_TextureRegion ttgHelpStoryBoardScene06;
	public static GL_TextureRegion ttgHelpStoryBoardScene07;

	// unit Generate
	public static GL_TextureRegion ttgUnitGenerateButtonAxe;
	public static GL_TextureRegion ttgUnitGenerateButtonAxePressed;
	public static GL_TextureRegion ttgUnitGenerateButtonMonk;
	public static GL_TextureRegion ttgUnitGenerateButtonMonkPressed;
	public static GL_TextureRegion ttgUnitGenerateButtonArcher;
	public static GL_TextureRegion ttgUnitGenerateButtonArcherPressed;
	public static GL_TextureRegion ttgUnitGenerateButtonSpear;
	public static GL_TextureRegion ttgUnitGenerateButtonSpearPressed;
	public static GL_TextureRegion ttgUnitGenerateButtonGun;
	public static GL_TextureRegion ttgUnitGenerateButtonGunPressed;
	public static GL_TextureRegion ttgUnitGenerateButtonSword;
	public static GL_TextureRegion ttgUnitGenerateButtonSwordPressed;

	// Score Font
	public static GL_TextureRegion ttgScoreFont;

	public static GL_TextureRegion ttgFiredBoar01;
	public static GL_TextureRegion ttgFiredBoar02;
	public static GL_TextureRegion ttgFiredBoar03;
	public static GL_TextureRegion ttgFiredBoar04;

	// story GL_TEXTUREREGION

	public static GL_TextureRegion ttgStoryBoardWin;
	public static GL_TextureRegion ttgStoryBoardLose;

	public static GL_TextureRegion ttgStageClearNumberPanel02;
	public static GL_TextureRegion ttgStageClearNumberPanel03;
	public static GL_TextureRegion ttgStageClearNumberPanel04;
	public static GL_TextureRegion ttgStageClearNumberPanel05;
	public static GL_TextureRegion ttgStageClearNumberPanel06;
	public static GL_TextureRegion ttgStageClearNumberPanel07;
	public static GL_TextureRegion ttgStageClearNumberPanel08;
	public static GL_TextureRegion ttgStageClearNumberPanel09;
	// GameSetting Button
	public static GL_TextureRegion ttgBtnGameSetting;
	public static GL_TextureRegion ttgBtnGameSettingPressed;

	// GameStart Button
	public static GL_TextureRegion ttgBtnGameStart;
	public static GL_TextureRegion ttgBtnGameStartPressed;

	// MainMenu Screen
	public static GL_TextureRegion ttgScrScrMainMenu;

	// Game Skill01 !06 Defualt button
	public static GL_TextureRegion ttgGameSkillButtonDefault01;
	public static GL_TextureRegion ttgGameSkillButtonDefault02;
	public static GL_TextureRegion ttgGameSkillButtonDefault03;
	public static GL_TextureRegion ttgGameSkillButtonDefault04;
	public static GL_TextureRegion ttgGameSkillButtonDefault05;
	public static GL_TextureRegion ttgGameSkillButtonDefault06;

	// Game Bullet 01~ 03
	public static GL_TextureRegion ttgGameImgBullet01;
	public static GL_TextureRegion ttgGameImgBullet02;
	public static GL_TextureRegion ttgGameImgBullet03;

	// EnergyBar 01 ~ 04

	public static GL_TextureRegion ttgEneryBar00;
	public static GL_TextureRegion ttgEneryBar01;
	public static GL_TextureRegion ttgEneryBar02;
	public static GL_TextureRegion ttgEneryBar03;
	public static GL_TextureRegion ttgEneryBar04;

	// intro button
	public static GL_TextureRegion ttgIntroGameStartButton;
	public static GL_TextureRegion ttgIntroGameStartButtonPressed;
	public static GL_TextureRegion ttgIntroGameSettingButton;
	public static GL_TextureRegion ttgIntroGameSettingButtonPressed;

	// setting Window

	public static GL_TextureRegion ttgSettingItemOn;
	public static GL_TextureRegion ttgSettingUnitText;

	public static GL_TextureRegion ttgSettingPage;

	public static GL_TextureRegion ttgSettingStartButton;
	public static GL_TextureRegion ttgSettingUpgradeButton;
	public static GL_TextureRegion ttgSettingStartButtonPressed;
	public static GL_TextureRegion ttgSettingUpgradeButtonPressed;

	public static GL_TextureRegion ttgSettingBackground;

	public static GL_TextureRegion ttgSettingItemPage;

	public static GL_TextureRegion ttgSettingSelectUnit;

	public static GL_TextureRegion ttgSettingStateBar00;
	public static GL_TextureRegion ttgSettingStateBar01;
	public static GL_TextureRegion ttgSettingStateBar02;
	public static GL_TextureRegion ttgSettingStateBar03;
	public static GL_TextureRegion ttgSettingStateBar04;
	public static GL_TextureRegion ttgSettingStateBar05;
	public static GL_TextureRegion ttgSettingStateBar06;
	public static GL_TextureRegion ttgSettingStateBar07;

	public static GL_TextureRegion ttgSettingAttackIcon;
	public static GL_TextureRegion ttgSettingHpIcon;
	public static GL_TextureRegion ttgSettingSpeedIcon;
	public static GL_TextureRegion ttgSettingLvIcon;

	public static GL_TextureRegion ttgSettingUnitBackGround;
	public static GL_TextureRegion ttgSettingUnitMudae;

	// StageWindow

	public static GL_TextureRegion ttgStageStage01;
	public static GL_TextureRegion ttgStageStage02;
	public static GL_TextureRegion ttgStageStage03;
	public static GL_TextureRegion ttgStageStage04;
	public static GL_TextureRegion ttgStageStage05;
	public static GL_TextureRegion ttgStageStage06;
	public static GL_TextureRegion ttgStageStage07;
	public static GL_TextureRegion ttgStageStage08;
	public static GL_TextureRegion ttgStageblankStage;
	public static GL_TextureRegion ttgStageSelect;

	public static GL_TextureRegion ttgStageNextButton;
	public static GL_TextureRegion ttgStageNextButtonPressed;

	public static GL_TextureRegion ttgStageText;

	// StageWindowStar
	public static GL_TextureRegion ttgStageStar00;
	public static GL_TextureRegion ttgStageStar01;
	public static GL_TextureRegion ttgStageStar02;
	public static GL_TextureRegion ttgStageStar03;

	public static GL_TextureRegion ttgStageCycle01;
	public static GL_TextureRegion ttgStageCycle02;
	public static GL_TextureRegion ttgStageCycle03;
	public static GL_TextureRegion ttgStageCycle04;
	public static GL_TextureRegion ttgStageCycle05;

	// Stage TraingCenter

	public static GL_TextureRegion ttgStageTraningCenter;
	public static GL_TextureRegion ttgStageTraningCenterPressed;

	// image X_button

	public static GL_TextureRegion ttgXButton;
	public static GL_TextureRegion ttgXButtonPressed;

	// Game Stage Clear Item 01

	public static GL_TextureRegion ttgClearGameBackButton;
	public static GL_TextureRegion ttgClearGameBackButtonClicked;
	public static GL_TextureRegion ttgClearGameBackGround;
	public static GL_TextureRegion ttgClearGameNextButton;
	public static GL_TextureRegion ttgClearGameNextButtonClicked;
	public static GL_TextureRegion ttgClearGameStageNum;
	public static GL_TextureRegion ttgClearGameGameClearText;
	public static GL_TextureRegion ttgClearGameGameFailText;
	public static GL_TextureRegion ttgClearGameNewRecordText;
	public static GL_TextureRegion ttgClearGameReplayButton;
	public static GL_TextureRegion ttgClearGameReplayButtonClicked;

	// Game Stage Clear Item 02

	public static GL_TextureRegion ttgClearGameBackPage;
	public static GL_TextureRegion ttgClearGameStar00;
	public static GL_TextureRegion ttgClearGameStar01;
	public static GL_TextureRegion ttgClearGameStar02;
	public static GL_TextureRegion ttgClearGameStar03;

	// ally Structure

	public static GL_TextureRegion ttgAllyStructure100;
	public static GL_TextureRegion ttgAllyStructureDamaged100;
	public static GL_TextureRegion ttgAllyStructure80;
	public static GL_TextureRegion ttgAllyStructureDamaged80;
	public static GL_TextureRegion ttgAllyStructure60;
	public static GL_TextureRegion ttgAllyStructureDamaged60;
	public static GL_TextureRegion ttgAllyStructure40;
	public static GL_TextureRegion ttgAllyStructureDamaged40;
	public static GL_TextureRegion ttgAllyStructure20;
	public static GL_TextureRegion ttgAllyStructureDamaged20;

	// enemy Structure

	public static GL_TextureRegion ttgEnemyStructure100;
	public static GL_TextureRegion ttgEnemyStructureDamaged100;
	public static GL_TextureRegion ttgEnemyStructure80;
	public static GL_TextureRegion ttgEnemyStructureDamaged80;
	public static GL_TextureRegion ttgEnemyStructure60;
	public static GL_TextureRegion ttgEnemyStructureDamaged60;
	public static GL_TextureRegion ttgEnemyStructure40;
	public static GL_TextureRegion ttgEnemyStructureDamaged40;
	public static GL_TextureRegion ttgEnemyStructure20;
	public static GL_TextureRegion ttgEnemyStructureDamaged20;

	// game Play Scene Button

	public static GL_TextureRegion ttgGamePlaySceneLeftButton;
	public static GL_TextureRegion ttgGamePlaySceneLeftButtonPressed;
	public static GL_TextureRegion ttgGamePlaySceneRightButton;
	public static GL_TextureRegion ttgGamePlaySceneRightButtonPressed;
	public static GL_TextureRegion ttgGamePlaySceneUnitLock;
	public static GL_TextureRegion ttgGamePlaySceneOption;
	public static GL_TextureRegion ttgGamePlaySceneOptionPressed;
	public static GL_TextureRegion ttgGamePlaySceneOptionExcution;

	// StoryBoard Scene

	public static GL_TextureRegion ttgStoryBoardScene01;
	public static GL_TextureRegion ttgStoryBoardScene02;
	public static GL_TextureRegion ttgStoryBoardScene03;
	public static GL_TextureRegion ttgStoryBoardScene04;

	public static GL_TextureRegion ttgScrMainMenuPoint;

	// game Setting Scene02

	public static GL_TextureRegion ttgSettingWindowPage;
	public static GL_TextureRegion ttgSettingWindowVolume00;
	public static GL_TextureRegion ttgSettingWindowVolume01;
	public static GL_TextureRegion ttgSettingWindowVolume02;
	public static GL_TextureRegion ttgSettingWindowVolume03;
	public static GL_TextureRegion ttgSettingWindowVolume04;
	public static GL_TextureRegion ttgSettingWindowVolume05;
	public static GL_TextureRegion ttgSettingWindowVolume06;
	public static GL_TextureRegion ttgSettingWindowVolume07;
	public static GL_TextureRegion ttgSettingWindowVolume08;
	public static GL_TextureRegion ttgSettingWindowVolume09;
	public static GL_TextureRegion ttgSettingWindowVolume10;

	public static GL_TextureRegion ttgSettingWindowVolumeUp;
	public static GL_TextureRegion ttgSettingWindowVolumeUpPressed;

	public static GL_TextureRegion ttgSettingWindowVolumeDown;
	public static GL_TextureRegion ttgSettingWindowVolumeDownPressed;

	public static GL_TextureRegion ttgSettingWindowVibration;
	public static GL_TextureRegion ttgSettingWindowVibrationPressed;

	public static GL_TextureRegion ttgSettingWindowSound;
	public static GL_TextureRegion ttgSettingWindowSoundPressed;

	public static GL_TextureRegion ttgSettingWindowEffectText;
	public static GL_TextureRegion ttgSettingWindowSettingText;
	public static GL_TextureRegion ttgSettingWindowBackGroundSoundText;

	// game Setting Scene02

	public static GL_TextureRegion ttgSettingWindowHelp;
	public static GL_TextureRegion ttgSettingWindowHelpPressed;
	public static GL_TextureRegion ttgSettingWindowControlPanel;

	// game 중경
	public static GL_TextureRegion ttgGameSceneBakcGroundJung;
	// game 하늘
	public static GL_TextureRegion ttgGameSceneBakcGroundHu01;
	public static GL_TextureRegion ttgGameSceneBakcGroundHu02;

	public static GL_TextureRegion ttgGameSceneSky;

	// Store Center

	public static GL_TextureRegion ttgGameSceneStoreCenter;
	public static GL_TextureRegion ttgGameSceneStoreCenterPressed;

	// ally Structure Particle

	public static GL_TextureRegion ttgAllyStructureParticle100;
	public static GL_TextureRegion ttgAllyStructureParticle80;
	public static GL_TextureRegion ttgAllyStructureParticle60;
	public static GL_TextureRegion ttgAllyStructureParticle40;

	// ally

	public static GL_TextureRegion ttgAllyMonkAttack;

	// enemy Structure Particle

	public static GL_TextureRegion ttgEnemyStructureParticle100;
	public static GL_TextureRegion ttgEnemyStructureParticleDamaged100;
	public static GL_TextureRegion ttgEnemyStructureParticle80;
	public static GL_TextureRegion ttgEnemyStructureParticleDamaged80;
	public static GL_TextureRegion ttgEnemyStructureParticle60;
	public static GL_TextureRegion ttgEnemyStructureParticleDamaged60;
	public static GL_TextureRegion ttgEnemyStructureParticle40;
	public static GL_TextureRegion ttgEnemyStructureParticleDamaged40;
	public static GL_TextureRegion ttgEnemyStructureParticle20;
	public static GL_TextureRegion ttgEnemyStructureParticleDamaged20;

	// game Prapared Scene Gamestart;

	public static GL_TextureRegion ttgGamePreparedSceneGameStart;
	public static GL_TextureRegion ttgGamePreparedSceneGameStartPressed;

	// game running pause
	public static GL_TextureRegion ttgGameRunningTerminate;
	public static GL_TextureRegion ttgGameRunningTerminatePressed;
	public static GL_TextureRegion ttgGameRunningContinue;
	public static GL_TextureRegion ttgGameRunningContinuePressed;

	// archer arrow
	public static GL_TextureRegion ttgAllyArcherArrow;

	// 외부 자원 //
	// music
	public static IFace_Music musicFullWindow;
	public static IFace_Music musicGameMainBackground;
	public static IFace_Music musicGameClear;
	public static IFace_Music musicGameLose;

	// sound
	public static IFace_Sound soundClick;
	public static IFace_Sound soundLvUp;
	public static IFace_Sound soundMonkAttack;
	public static IFace_Sound soundLeeSkillTonado;
	public static IFace_Sound soundLeeSkillSpread;
	public static IFace_Sound soundLeeSkillBasic;
	public static IFace_Sound soundLeeSkill04;
	public static IFace_Sound soundLeesunsinDie;
	public static IFace_Sound soundEnemyDie;
	public static IFace_Sound soundShootGun;
	public static IFace_Sound soundSwordAttack01thick;
	public static IFace_Sound soundSwordAttack02thin;

	public static GL_Font ScoreNumberFont;
	public static GL_Font MoneyNumberFont;

	public static boolean isSuccessAssetLoading = false;

	public static void init(GL_Game game) {
		ttScrMainMenu = new GL_Texture(game, "scr_main_menu.png");
		ttgScrScrMainMenu = new GL_TextureRegion(ttScrMainMenu, 0, 0, 1680, 914);

		// Story Board Scene
		ttStoryBoardScene = new GL_Texture(game, "storyboard.png");
		ttgStoryBoardScene01 = new GL_TextureRegion(ttStoryBoardScene, 0, 0,
				480, 320);
		ttgStoryBoardScene02 = new GL_TextureRegion(ttStoryBoardScene, 480, 0,
				480, 320);
		ttgStoryBoardScene03 = new GL_TextureRegion(ttStoryBoardScene, 0, 320,
				480, 320);
		ttgStoryBoardScene04 = new GL_TextureRegion(ttStoryBoardScene, 480,
				320, 480, 320);

		musicFullWindow = game.getAudio().newMusic(
				"window_background_music.mp3");
		musicFullWindow.setVolume(Manage_Settings.fMusicVolume);
		musicFullWindow.setLooping(true);

		musicFullWindow.play();
	}

	public static void load01(GL_Game game) {
		ttFont = new GL_Texture(game, "asset_number.png");

		ttBtnGameSetting = new GL_Texture(game, "btn_game_setting.png");
		ttgBtnGameSetting = new GL_TextureRegion(ttBtnGameSetting, 0, 0, 400,
				119);

		ttBtnGameStart = new GL_Texture(game, "btn_game_start.png");
		ttgBtnGameStart = new GL_TextureRegion(ttBtnGameStart, 0, 0, 400, 119);

		ttBtnGameSettingPressed = new GL_Texture(game,
				"btn_game_setting_pressed.png");
		ttgBtnGameSettingPressed = new GL_TextureRegion(ttBtnGameSetting, 0, 0,
				400, 119);

		ttBtnGameStartPressed = new GL_Texture(game,
				"btn_game_start_pressed.png");
		ttgBtnGameStartPressed = new GL_TextureRegion(ttBtnGameSetting, 0, 0,
				400, 119);

		ttScrMainFire = new GL_Texture(game, "scr_main_menu_fire.png");

		// Button01~06

		ttGameButton01 = new GL_Texture(game, "skill_button_01.png");
		ttGameButton02 = new GL_Texture(game, "skill_button_02.png");
		ttGameButton03 = new GL_Texture(game, "skill_button_03.png");
		ttGameButton04 = new GL_Texture(game, "skill_button_04.png");
		ttGameButton05 = new GL_Texture(game, "skill_button_05.png");
		ttGameButton06 = new GL_Texture(game, "skill_button_06.png");

		ttgGameSkillButtonDefault01 = new GL_TextureRegion(ttGameButton01, 513,
				171, 171, 171);
		ttgGameSkillButtonDefault02 = new GL_TextureRegion(ttGameButton02, 0,
				0, 342, 342);
		ttgGameSkillButtonDefault03 = new GL_TextureRegion(ttGameButton03, 0,
				0, 342, 342);
		ttgGameSkillButtonDefault04 = new GL_TextureRegion(ttGameButton04, 0,
				0, 342, 342);
		ttgGameSkillButtonDefault05 = new GL_TextureRegion(ttGameButton05, 0,
				0, 342, 342);
		ttgGameSkillButtonDefault06 = new GL_TextureRegion(ttGameButton06, 0,
				0, 342, 342);

		aniGameSkillButton01 = new GL_Animation(new GL_TextureRegion(
				ttGameButton01, 0, 0, 171, 171), 0.1f, new GL_TextureRegion(
				ttGameButton01, 0, 0, 171, 171), new GL_TextureRegion(
				ttGameButton01, 171, 0, 171, 171), new GL_TextureRegion(
				ttGameButton01, 342, 0, 171, 171), new GL_TextureRegion(
				ttGameButton01, 513, 0, 171, 171), new GL_TextureRegion(
				ttGameButton01, 684, 0, 171, 171), new GL_TextureRegion(
				ttGameButton01, 0, 171, 171, 171), new GL_TextureRegion(
				ttGameButton01, 171, 171, 171, 171), new GL_TextureRegion(
				ttGameButton01, 342, 171, 171, 171), new GL_TextureRegion(
				ttGameButton01, 514, 171, 171, 171));
		aniGameSkillButton02 = new GL_Animation(new GL_TextureRegion(
				ttGameButton02, 1026, 342, 342, 342), 0.1f,
				new GL_TextureRegion(ttGameButton02, 1026, 342, 342, 342),
				new GL_TextureRegion(ttGameButton02, 684, 342, 342, 342),
				new GL_TextureRegion(ttGameButton02, 342, 342, 342, 342),
				new GL_TextureRegion(ttGameButton02, 0, 342, 342, 342),
				new GL_TextureRegion(ttGameButton02, 1368, 0, 342, 342),
				new GL_TextureRegion(ttGameButton02, 1026, 0, 342, 342),
				new GL_TextureRegion(ttGameButton02, 684, 0, 342, 342),
				new GL_TextureRegion(ttGameButton02, 342, 0, 342, 342),
				new GL_TextureRegion(ttGameButton02, 0, 0, 342, 342));
		aniGameSkillButton03 = new GL_Animation(new GL_TextureRegion(
				ttGameButton03, 1026, 342, 342, 342), 0.1f,
				new GL_TextureRegion(ttGameButton03, 1026, 342, 342, 342),
				new GL_TextureRegion(ttGameButton03, 684, 342, 342, 342),
				new GL_TextureRegion(ttGameButton03, 342, 342, 342, 342),
				new GL_TextureRegion(ttGameButton03, 0, 342, 342, 342),
				new GL_TextureRegion(ttGameButton03, 1368, 0, 342, 342),
				new GL_TextureRegion(ttGameButton03, 1026, 0, 342, 342),
				new GL_TextureRegion(ttGameButton03, 684, 0, 342, 342),
				new GL_TextureRegion(ttGameButton03, 342, 0, 342, 342),
				new GL_TextureRegion(ttGameButton03, 0, 0, 342, 342));
		aniGameSkillButton04 = new GL_Animation(new GL_TextureRegion(
				ttGameButton04, 1026, 342, 342, 342), 0.1f,
				new GL_TextureRegion(ttGameButton04, 1026, 342, 342, 342),
				new GL_TextureRegion(ttGameButton04, 684, 342, 342, 342),
				new GL_TextureRegion(ttGameButton04, 342, 342, 342, 342),
				new GL_TextureRegion(ttGameButton04, 0, 342, 342, 342),
				new GL_TextureRegion(ttGameButton04, 1368, 0, 342, 342),
				new GL_TextureRegion(ttGameButton04, 1026, 0, 342, 342),
				new GL_TextureRegion(ttGameButton04, 684, 0, 342, 342),
				new GL_TextureRegion(ttGameButton04, 342, 0, 342, 342),
				new GL_TextureRegion(ttGameButton04, 0, 0, 342, 342));
		aniGameSkillButton05 = new GL_Animation(new GL_TextureRegion(
				ttGameButton05, 1026, 342, 342, 342), 0.1f,
				new GL_TextureRegion(ttGameButton05, 1026, 342, 342, 342),
				new GL_TextureRegion(ttGameButton05, 684, 342, 342, 342),
				new GL_TextureRegion(ttGameButton05, 342, 342, 342, 342),
				new GL_TextureRegion(ttGameButton05, 0, 342, 342, 342),
				new GL_TextureRegion(ttGameButton05, 1368, 0, 342, 342),
				new GL_TextureRegion(ttGameButton05, 1026, 0, 342, 342),
				new GL_TextureRegion(ttGameButton05, 684, 0, 342, 342),
				new GL_TextureRegion(ttGameButton05, 342, 0, 342, 342),
				new GL_TextureRegion(ttGameButton05, 0, 0, 342, 342));
		aniGameSkillButton06 = new GL_Animation(new GL_TextureRegion(
				ttGameButton06, 1026, 342, 342, 342), 0.1f,
				new GL_TextureRegion(ttGameButton06, 1026, 342, 342, 342),
				new GL_TextureRegion(ttGameButton06, 684, 342, 342, 342),
				new GL_TextureRegion(ttGameButton06, 342, 342, 342, 342),
				new GL_TextureRegion(ttGameButton06, 0, 342, 342, 342),
				new GL_TextureRegion(ttGameButton06, 1368, 0, 342, 342),
				new GL_TextureRegion(ttGameButton06, 1026, 0, 342, 342),
				new GL_TextureRegion(ttGameButton06, 684, 0, 342, 342),
				new GL_TextureRegion(ttGameButton06, 342, 0, 342, 342),
				new GL_TextureRegion(ttGameButton06, 0, 0, 342, 342));

		// ally archer
		ttAllyArcherMan = new GL_Texture(game, "ally_archer.png");

		// ally SpearMan
		ttAllySpearMan = new GL_Texture(game, "ally_spearman.png");

		// Ally Archerman
		// die
		anichrAllyArcherDie = new GL_Animation(new GL_TextureRegion(
				ttAllyArcherMan, 0, 0, 475, 284), 0.1f, new GL_TextureRegion(
				ttAllyArcherMan, 0, 0, 475, 284), new GL_TextureRegion(
				ttAllyArcherMan, 475, 0, 475, 284), new GL_TextureRegion(
				ttAllyArcherMan, 950, 0, 475, 284), new GL_TextureRegion(
				ttAllyArcherMan, 1425, 0, 475, 284), new GL_TextureRegion(
				ttAllyArcherMan, 1900, 0, 475, 284), new GL_TextureRegion(
				ttAllyArcherMan, 2375, 0, 475, 284), new GL_TextureRegion(
				ttAllyArcherMan, 2850, 0, 475, 284));
		// move
		anichrAllyArcherMov = new GL_Animation(new GL_TextureRegion(
				ttAllyArcherMan, 0, 284, 475, 284), 0.1f, new GL_TextureRegion(
				ttAllyArcherMan, 0, 284, 475, 284), new GL_TextureRegion(
				ttAllyArcherMan, 475, 284, 475, 284), new GL_TextureRegion(
				ttAllyArcherMan, 950, 284, 475, 284));
		// attack
		anichrAllyArcherAttack = new GL_Animation(new GL_TextureRegion(
				ttAllyArcherMan, 1425, 284, 475, 284), 0.1f,
				new GL_TextureRegion(ttAllyArcherMan, 1425, 284, 475, 284),
				new GL_TextureRegion(ttAllyArcherMan, 1900, 284, 475, 284),
				new GL_TextureRegion(ttAllyArcherMan, 2375, 284, 475, 284),
				new GL_TextureRegion(ttAllyArcherMan, 2850, 284, 475, 284));

		// arrow
		ttgAllyArcherArrow = new GL_TextureRegion(ttAllyArcherMan, 0, 568, 118,
				19);

		// Ally Spearman
		// die
		anichrAllySpearmanDie = new GL_Animation(new GL_TextureRegion(
				ttAllySpearMan, 0, 284, 475, 284), 0.1f, new GL_TextureRegion(
				ttAllySpearMan, 0, 284, 475, 284), new GL_TextureRegion(
				ttAllySpearMan, 475, 284, 475, 284), new GL_TextureRegion(
				ttAllySpearMan, 950, 284, 475, 284), new GL_TextureRegion(
				ttAllySpearMan, 1425, 284, 475, 284), new GL_TextureRegion(
				ttAllySpearMan, 1900, 284, 475, 284), new GL_TextureRegion(
				ttAllySpearMan, 2375, 284, 475, 284), new GL_TextureRegion(
				ttAllySpearMan, 2850, 284, 475, 284));
		// move
		anichrAllySpearmanMov = new GL_Animation(new GL_TextureRegion(
				ttAllySpearMan, 0, 568, 475, 284), 0.1f, new GL_TextureRegion(
				ttAllySpearMan, 0, 568, 475, 284), new GL_TextureRegion(
				ttAllySpearMan, 475, 568, 475, 284), new GL_TextureRegion(
				ttAllySpearMan, 950, 568, 475, 284));
		// attack
		anichrAllySpearmanAttack = new GL_Animation(new GL_TextureRegion(
				ttAllySpearMan, 0, 0, 475, 284), 0.1f, new GL_TextureRegion(
				ttAllySpearMan, 0, 0, 475, 284), new GL_TextureRegion(
				ttAllySpearMan, 475, 0, 475, 284), new GL_TextureRegion(
				ttAllySpearMan, 950, 0, 475, 284), new GL_TextureRegion(
				ttAllySpearMan, 1425, 0, 475, 284), new GL_TextureRegion(
				ttAllySpearMan, 1900, 0, 475, 284), new GL_TextureRegion(
				ttAllySpearMan, 2375, 0, 475, 284), new GL_TextureRegion(
				ttAllySpearMan, 2850, 0, 475, 284));

	}

	public static void load02(GL_Game game) {

		aniScrMainMenuFire = new GL_Animation(new GL_TextureRegion(
				ttScrMainFire, 0, 0, 480, 270), 0.1f, new GL_TextureRegion(
				ttScrMainFire, 0, 0, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 480, 0, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 960, 0, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 1440, 0, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 1920, 0, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 2400, 0, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 0, 270, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 480, 270, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 960, 270, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 1440, 270, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 1920, 270, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 2400, 270, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 0, 540, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 480, 540, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 960, 540, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 1440, 540, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 1920, 540, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 2400, 540, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 0, 810, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 480, 810, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 960, 810, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 1440, 810, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 1920, 810, 480, 270), new GL_TextureRegion(
				ttScrMainFire, 2400, 810, 480, 270));

		// chr enemy gun
		ttChrEnemyGun = new GL_Texture(game, "chr_enemy_gun.png");

		// die
		aniChrEnemyGunDie = new GL_Animation(new GL_TextureRegion(
				ttChrEnemyGun, 0, 122, 237, 122), 0.125f, new GL_TextureRegion(
				ttChrEnemyGun, 0, 0, 237, 122), new GL_TextureRegion(
				ttChrEnemyGun, 237, 0, 237, 122), new GL_TextureRegion(
				ttChrEnemyGun, 474, 0, 237, 122), new GL_TextureRegion(
				ttChrEnemyGun, 711, 0, 237, 122), new GL_TextureRegion(
				ttChrEnemyGun, 0, 122, 237, 122));

		// mov
		aniChrEnemyGunMov = new GL_Animation(new GL_TextureRegion(
				ttChrEnemyGun, 711, 244, 237, 122), 0.1f, new GL_TextureRegion(
				ttChrEnemyGun, 237, 122, 237, 122), new GL_TextureRegion(
				ttChrEnemyGun, 474, 122, 237, 122), new GL_TextureRegion(
				ttChrEnemyGun, 711, 122, 237, 122), new GL_TextureRegion(
				ttChrEnemyGun, 0, 244, 237, 122), new GL_TextureRegion(
				ttChrEnemyGun, 237, 244, 237, 122), new GL_TextureRegion(
				ttChrEnemyGun, 474, 244, 237, 122), new GL_TextureRegion(
				ttChrEnemyGun, 711, 244, 237, 122));

		// Attack down
		aniChrEnemyGunShutDown = new GL_Animation(new GL_TextureRegion(
				ttChrEnemyGun, 237, 122, 237, 122), 0.125f,
				new GL_TextureRegion(ttChrEnemyGun, 0, 366, 237, 122),
				new GL_TextureRegion(ttChrEnemyGun, 237, 366, 237, 122),
				new GL_TextureRegion(ttChrEnemyGun, 474, 366, 237, 122),
				new GL_TextureRegion(ttChrEnemyGun, 711, 366, 237, 122),
				new GL_TextureRegion(ttChrEnemyGun, 0, 488, 237, 122));

		// Attack Up
		aniChrEnemyGunShutUp = new GL_Animation(new GL_TextureRegion(
				ttChrEnemyGun, 237, 122, 237, 122), 0.125f,
				new GL_TextureRegion(ttChrEnemyGun, 237, 488, 237, 122),
				new GL_TextureRegion(ttChrEnemyGun, 474, 488, 237, 122),
				new GL_TextureRegion(ttChrEnemyGun, 711, 488, 237, 122),
				new GL_TextureRegion(ttChrEnemyGun, 0, 610, 237, 122),
				new GL_TextureRegion(ttChrEnemyGun, 237, 610, 237, 122),
				new GL_TextureRegion(ttChrEnemyGun, 474, 610, 237, 122));

		// chr enemy sword
		ttChrEnemySword = new GL_Texture(game, "chr_enemy_sword.png");

		// die
		aniChrEnemySwordDie = new GL_Animation(new GL_TextureRegion(
				ttChrEnemySword, 552, 284, 276, 142), 0.125f,
				new GL_TextureRegion(ttChrEnemySword, 552, 142, 276, 142),
				new GL_TextureRegion(ttChrEnemySword, 828, 142, 276, 142),
				new GL_TextureRegion(ttChrEnemySword, 0, 284, 276, 142),
				new GL_TextureRegion(ttChrEnemySword, 276, 284, 276, 142),
				new GL_TextureRegion(ttChrEnemySword, 552, 284, 276, 142));
		// move
		aniChrEnemySwordMov = new GL_Animation(new GL_TextureRegion(
				ttChrEnemySword, 828, 284, 276, 142), 0.125f,
				new GL_TextureRegion(ttChrEnemySword, 828, 284, 276, 142),
				new GL_TextureRegion(ttChrEnemySword, 0, 426, 276, 142),
				new GL_TextureRegion(ttChrEnemySword, 276, 426, 276, 142),
				new GL_TextureRegion(ttChrEnemySword, 552, 426, 276, 142),
				new GL_TextureRegion(ttChrEnemySword, 828, 426, 276, 142),
				new GL_TextureRegion(ttChrEnemySword, 0, 568, 276, 142),
				new GL_TextureRegion(ttChrEnemySword, 276, 568, 276, 142),
				new GL_TextureRegion(ttChrEnemySword, 552, 568, 276, 142));

		// attack
		aniChrEnemySwordAttack = new GL_Animation(new GL_TextureRegion(
				ttChrEnemySword, 828, 284, 276, 142), 0.125f,
				new GL_TextureRegion(ttChrEnemySword, 0, 0, 276, 142),
				new GL_TextureRegion(ttChrEnemySword, 276, 0, 276, 142),
				new GL_TextureRegion(ttChrEnemySword, 552, 0, 276, 142),
				new GL_TextureRegion(ttChrEnemySword, 828, 0, 276, 142),
				new GL_TextureRegion(ttChrEnemySword, 0, 142, 276, 142),
				new GL_TextureRegion(ttChrEnemySword, 276, 142, 276, 142));

		// middle boss 01
		ttChrMiddleBoss01 = new GL_Texture(game, "enemy_middleboss_01.png");

		// die
		aniChrMiddleBoss01Die = new GL_Animation(new GL_TextureRegion(
				ttChrMiddleBoss01, 0, 0, 475, 245), 0.125f,
				new GL_TextureRegion(ttChrMiddleBoss01, 0, 0, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss01, 475, 0, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss01, 950, 0, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss01, 1425, 0, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss01, 1900, 0, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss01, 2375, 0, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss01, 2850, 0, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss01, 3325, 0, 475, 245));

		// move
		aniChrMiddleBoss01Mov = new GL_Animation(new GL_TextureRegion(
				ttChrMiddleBoss01, 0, 490, 475, 245), 0.125f,
				new GL_TextureRegion(ttChrMiddleBoss01, 0, 490, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss01, 475, 490, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss01, 950, 490, 475, 245));

		// attack
		aniChrMiddleBoss01Attack = new GL_Animation(new GL_TextureRegion(
				ttChrMiddleBoss01, 0, 245, 475, 245), 0.125f,
				new GL_TextureRegion(ttChrMiddleBoss01, 0, 245, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss01, 475, 245, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss01, 950, 245, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss01, 1425, 245, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss01, 1900, 245, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss01, 2375, 245, 475, 245));

		// middle boss 02
		ttChrMiddleBoss02 = new GL_Texture(game, "enemy_middleboss_02.png");

		// die
		aniChrMiddleBoss02Die = new GL_Animation(new GL_TextureRegion(
				ttChrMiddleBoss02, 0, 0, 475, 245), 0.125f,
				new GL_TextureRegion(ttChrMiddleBoss02, 0, 0, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss02, 475, 0, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss02, 950, 0, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss02, 1425, 0, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss02, 1900, 0, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss02, 2375, 0, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss02, 2850, 0, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss02, 3325, 0, 475, 245));
		// move
		aniChrMiddleBoss02Mov = new GL_Animation(new GL_TextureRegion(
				ttChrMiddleBoss02, 0, 490, 475, 245), 0.125f,
				new GL_TextureRegion(ttChrMiddleBoss02, 0, 490, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss02, 475, 490, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss02, 950, 490, 475, 245));

		// attack
		aniChrMiddleBoss02Attack = new GL_Animation(new GL_TextureRegion(
				ttChrMiddleBoss02, 0, 245, 475, 245), 0.125f,
				new GL_TextureRegion(ttChrMiddleBoss02, 0, 245, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss02, 475, 245, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss02, 950, 245, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss02, 1425, 245, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss02, 1900, 245, 475, 245),
				new GL_TextureRegion(ttChrMiddleBoss02, 2375, 245, 475, 245));
	}

	public static void load03(GL_Game game) {

		// backGround
		ttGameBackGround = new GL_Texture(game, "game_sceneback_ground.png");
		aniGameBackGround = new GL_Animation(new GL_TextureRegion(
				ttGameBackGround, 0, 0, 1920, 320), 0.2f, new GL_TextureRegion(
				ttGameBackGround, 0, 0, 1920, 320), new GL_TextureRegion(
				ttGameBackGround, 0, 320, 1920, 320), new GL_TextureRegion(
				ttGameBackGround, 0, 640, 1920, 320), new GL_TextureRegion(
				ttGameBackGround, 0, 960, 1920, 320), new GL_TextureRegion(
				ttGameBackGround, 0, 1280, 1920, 320), new GL_TextureRegion(
				ttGameBackGround, 0, 1600, 1920, 320));
		ttgGameSceneBakcGroundJung = new GL_TextureRegion(ttGameBackGround, 0,
				1920, 1920, 320);
		ttgGameSceneBakcGroundHu01 = new GL_TextureRegion(ttGameBackGround, 0,
				2240, 1920, 320);
		ttgGameSceneBakcGroundHu02 = new GL_TextureRegion(ttGameBackGround, 0,
				2560, 1920, 320);
		ttgGameSceneSky = new GL_TextureRegion(ttGameBackGround, 0, 2880, 1920,
				320);

		// ani Back Ground

		// bullet
		ttImgBulletGun = new GL_Texture(game, "img_bullet_gun.png");
		ttgGameImgBullet01 = new GL_TextureRegion(ttImgBulletGun, 0, 0, 25, 25);
		ttgGameImgBullet02 = new GL_TextureRegion(ttImgBulletGun, 25, 0, 25, 25);
		ttgGameImgBullet03 = new GL_TextureRegion(ttImgBulletGun, 50, 0, 25, 25);

		// energybar

		ttEnergyBar = new GL_Texture(game, "energyBar.png");
		ttgEneryBar00 = new GL_TextureRegion(ttEnergyBar, 0, 0, 200, 23); // energy
																			// 0
		ttgEneryBar01 = new GL_TextureRegion(ttEnergyBar, 0, 23, 200, 23); // energy
																			// 1
		ttgEneryBar02 = new GL_TextureRegion(ttEnergyBar, 0, 46, 200, 23); // energy
																			// 2
		ttgEneryBar03 = new GL_TextureRegion(ttEnergyBar, 0, 69, 200, 23); // energy
																			// 3
		ttgEneryBar04 = new GL_TextureRegion(ttEnergyBar, 0, 92, 200, 23); // energy
																			// full

		ttLoadingWindowCharecter = new GL_Texture(game, "loading_charecter.png");

		// sword
		aniCrySwordEnemy = new GL_Animation(
				new GL_TextureRegion(ttLoadingWindowCharecter, 0, 366, 237, 122),
				0.125f,
				new GL_TextureRegion(ttLoadingWindowCharecter, 0, 366, 237, 122),
				new GL_TextureRegion(ttLoadingWindowCharecter, 237, 366, 237,
						122), new GL_TextureRegion(ttLoadingWindowCharecter,
						474, 366, 237, 122));
		// gun
		aniCryGunEnemy = new GL_Animation(
				new GL_TextureRegion(ttLoadingWindowCharecter, 0, 0, 237, 122),
				0.125f,
				new GL_TextureRegion(ttLoadingWindowCharecter, 0, 0, 237, 122),
				new GL_TextureRegion(ttLoadingWindowCharecter, 237, 0, 237, 122),
				new GL_TextureRegion(ttLoadingWindowCharecter, 474, 0, 237, 122));

		// lance
		aniCryLanceAlly = new GL_Animation(
				new GL_TextureRegion(ttLoadingWindowCharecter, 0, 244, 237, 122),
				0.125f,
				new GL_TextureRegion(ttLoadingWindowCharecter, 0, 244, 237, 122),
				new GL_TextureRegion(ttLoadingWindowCharecter, 237, 244, 237,
						122), new GL_TextureRegion(ttLoadingWindowCharecter,
						474, 244, 237, 122));

		// attack
		aniAttackLanceAlly = new GL_Animation(
				new GL_TextureRegion(ttLoadingWindowCharecter, 0, 122, 237, 122),
				0.125f,
				new GL_TextureRegion(ttLoadingWindowCharecter, 0, 122, 237, 122),
				new GL_TextureRegion(ttLoadingWindowCharecter, 237, 122, 237,
						122), new GL_TextureRegion(ttLoadingWindowCharecter,
						474, 122, 237, 122));

		// crysword

		ttIntroSrogun = new GL_Texture(game, "intro_slogun.png");

		ttFiredBoatMainGame = new GL_Texture(game, "fired_boat.png");

		ttgFiredBoar01 = new GL_TextureRegion(ttFiredBoatMainGame, 0, 0, 323,
				57);
		ttgFiredBoar02 = new GL_TextureRegion(ttFiredBoatMainGame, 0, 57, 277,
				72);
		ttgFiredBoar03 = new GL_TextureRegion(ttFiredBoatMainGame, 0, 129, 262,
				35);
		ttgFiredBoar04 = new GL_TextureRegion(ttFiredBoatMainGame, 0, 164, 148,
				26);

		ttHelpStoryBoardScene = new GL_Texture(game, "helpstoryboard.png");
		ttgHelpStoryBoardScene01 = new GL_TextureRegion(ttHelpStoryBoardScene,
				0, 0, 480, 320);
		ttgHelpStoryBoardScene02 = new GL_TextureRegion(ttHelpStoryBoardScene,
				480, 0, 480, 320);
		ttgHelpStoryBoardScene03 = new GL_TextureRegion(ttHelpStoryBoardScene,
				960, 0, 480, 320);
		ttgHelpStoryBoardScene04 = new GL_TextureRegion(ttHelpStoryBoardScene,
				1440, 0, 480, 320);
		ttgHelpStoryBoardScene05 = new GL_TextureRegion(ttHelpStoryBoardScene,
				0, 320, 480, 320);
		ttgHelpStoryBoardScene06 = new GL_TextureRegion(ttHelpStoryBoardScene,
				480, 320, 480, 320);
		ttgHelpStoryBoardScene07 = new GL_TextureRegion(ttHelpStoryBoardScene,
				960, 320, 480, 320);

		ttGameSceneBackGround02 = new GL_Texture(game,
				"game_sceneback_ground02.png");
		ttgGameBackGroundJung = new GL_TextureRegion(ttGameSceneBackGround02,
				0, 0, 1920, 320);
		ttgGameBackGroundHu = new GL_TextureRegion(ttGameSceneBackGround02, 0,
				320, 1920, 320);

		aniGameSceneBackGround02 = new GL_Animation(
				new GL_TextureRegion(ttGameSceneBackGround02, 0, 640, 1920, 320),
				0.15f,
				new GL_TextureRegion(ttGameSceneBackGround02, 0, 640, 1920, 320),
				new GL_TextureRegion(ttGameSceneBackGround02, 0, 960, 1920, 320),
				new GL_TextureRegion(ttGameSceneBackGround02, 0, 1280, 1920,
						320), new GL_TextureRegion(ttGameSceneBackGround02, 0,
						1600, 1920, 320), new GL_TextureRegion(
						ttGameSceneBackGround02, 0, 1920, 1920, 320));

		musicGameMainBackground = game.getAudio().newMusic(
				"window_game_background_music.mp3");
		musicGameMainBackground.setVolume(Manage_Settings.fMusicVolume);
		musicGameMainBackground.setLooping(true);

		musicGameClear = game.getAudio().newMusic("window_game_clear.mp3");
		musicGameMainBackground.setVolume(Manage_Settings.fMusicVolume);
		musicGameMainBackground.setLooping(true);

		musicGameLose = game.getAudio().newMusic("window_game_lose.mp3");
		musicGameMainBackground.setVolume(Manage_Settings.fMusicVolume);
		musicGameMainBackground.setLooping(true);

		soundClick = game.getAudio().newSound("click.ogg");
		//effect Sound
		soundLvUp = game.getAudio().newSound("sound_lvup.wav");
		soundMonkAttack = game.getAudio().newSound("sound_monk_attack.wav");
		soundLeeSkillTonado = game.getAudio().newSound(
				"sound_skill_lee_tonado.wav");
		soundLeeSkillSpread = game.getAudio().newSound(
				"sound_skill_lee_spread.wav");
		soundLeeSkillBasic = game.getAudio().newSound(
				"sound_skill_lee_basic.wav");
		soundLeesunsinDie = game.getAudio().newSound("sound_lee_die.wav");
		soundEnemyDie = game.getAudio().newSound("sound_enemy_die.wav");
		soundShootGun = game.getAudio().newSound("sound_gun_shooting.wav");
		soundSwordAttack01thick = game.getAudio().newSound(
				"sound_sword_01_thick.wav");
		soundSwordAttack02thin = game.getAudio()
				.newSound("sound_sword_02_thin.wav");

	}

	public static void load04(GL_Game game) {
		// setting

		aniIntroSrogun = new GL_Animation(new GL_TextureRegion(ttIntroSrogun,
				0, 0, 84, 470), 0.15f, new GL_TextureRegion(ttIntroSrogun, 0,
				0, 84, 470),
				new GL_TextureRegion(ttIntroSrogun, 84, 0, 84, 470),
				new GL_TextureRegion(ttIntroSrogun, 168, 0, 84, 470),
				new GL_TextureRegion(ttIntroSrogun, 252, 0, 84, 470),
				new GL_TextureRegion(ttIntroSrogun, 336, 0, 84, 470),
				new GL_TextureRegion(ttIntroSrogun, 420, 0, 84, 470),
				new GL_TextureRegion(ttIntroSrogun, 504, 0, 84, 470));

		ttIntroButton = new GL_Texture(game, "intro_button.png");
		ttgIntroGameStartButton = new GL_TextureRegion(ttIntroButton, 0, 0,
				300, 90);
		ttgIntroGameStartButtonPressed = new GL_TextureRegion(ttIntroButton, 0,
				90, 300, 90);
		ttgIntroGameSettingButton = new GL_TextureRegion(ttIntroButton, 0, 180,
				300, 90);
		ttgIntroGameSettingButtonPressed = new GL_TextureRegion(ttIntroButton,
				0, 270, 300, 90);

		ttSettingItem = new GL_Texture(game, "setting_item.png");

		ttgSettingItemOn = new GL_TextureRegion(ttSettingItem, 0, 0, 43, 43);
		ttgSettingUnitText = new GL_TextureRegion(ttSettingItem, 0, 86, 36, 29);
		ttgSettingSelectUnit = new GL_TextureRegion(ttSettingItem, 0, 43, 43,
				43);

		ttSettingPage = new GL_Texture(game, "setting_page.png");

		ttgSettingPage = new GL_TextureRegion(ttSettingPage, 0, 0, 480, 320);

		ttSettingButton = new GL_Texture(game, "setting_button.png");

		ttgSettingStartButton = new GL_TextureRegion(ttSettingButton, 0, 0,
				222, 64);
		ttgSettingUpgradeButton = new GL_TextureRegion(ttSettingButton, 0, 64,
				222, 64);
		ttgSettingStartButtonPressed = new GL_TextureRegion(ttSettingButton, 0,
				128, 222, 64);
		ttgSettingUpgradeButtonPressed = new GL_TextureRegion(ttSettingButton,
				0, 192, 222, 64);

		ttSettingBackGround = new GL_Texture(game, "setting_background.png");

		ttgSettingBackground = new GL_TextureRegion(ttSettingBackGround, 0, 0,
				600, 337);

		ttSettingItemPage = new GL_Texture(game, "setting_item_character.png");

		ttgSettingItemPage = new GL_TextureRegion(ttSettingItemPage, 0, 0, 240,
				130);

		ttSettingUnitBackGround = new GL_Texture(game,
				"setting_unit_background.png");

		ttgSettingUnitBackGround = new GL_TextureRegion(
				ttSettingUnitBackGround, 0, 0, 314, 154);

		ttSettingUnitMudae = new GL_Texture(game, "setting_unit_mudae.png");

		ttgSettingUnitMudae = new GL_TextureRegion(ttSettingUnitMudae, 0, 0,
				75, 75);

		ttStateBar = new GL_Texture(game, "setting_state.png");
		ttgSettingStateBar00 = new GL_TextureRegion(ttStateBar, 0, 0, 210, 30);

		ttgSettingStateBar01 = new GL_TextureRegion(ttStateBar, 0, 30, 210, 30);
		ttgSettingStateBar02 = new GL_TextureRegion(ttStateBar, 0, 60, 210, 30);
		ttgSettingStateBar03 = new GL_TextureRegion(ttStateBar, 0, 90, 210, 30);
		ttgSettingStateBar04 = new GL_TextureRegion(ttStateBar, 0, 120, 210, 30);
		ttgSettingStateBar05 = new GL_TextureRegion(ttStateBar, 0, 150, 210, 30);
		ttgSettingStateBar06 = new GL_TextureRegion(ttStateBar, 0, 180, 210, 30);
		ttgSettingStateBar07 = new GL_TextureRegion(ttStateBar, 0, 210, 210, 30);

		// Icon
		ttSettingIcons = new GL_Texture(game, "setting_icon.png");
		ttgSettingAttackIcon = new GL_TextureRegion(ttSettingIcons, 0, 0, 30,
				30);
		ttgSettingHpIcon = new GL_TextureRegion(ttSettingIcons, 60, 0, 30, 30);
		ttgSettingSpeedIcon = new GL_TextureRegion(ttSettingIcons, 30, 0, 29,
				30);
		ttgSettingLvIcon = new GL_TextureRegion(ttSettingIcons, 90, 0, 28, 30);
	}

	public static void load05(GL_Game game) {
		// stage
		ttStageItems = new GL_Texture(game, "stage_items.png");
		ttgStageStage01 = new GL_TextureRegion(ttStageItems, 0, 0, 64, 47);
		ttgStageStage02 = new GL_TextureRegion(ttStageItems, 64, 0, 64, 47);
		ttgStageStage03 = new GL_TextureRegion(ttStageItems, 128, 0, 64, 47);
		ttgStageStage04 = new GL_TextureRegion(ttStageItems, 192, 0, 64, 47);
		ttgStageStage05 = new GL_TextureRegion(ttStageItems, 256, 0, 64, 47);
		ttgStageStage06 = new GL_TextureRegion(ttStageItems, 0, 47, 64, 47);
		ttgStageStage07 = new GL_TextureRegion(ttStageItems, 64, 47, 64, 47);
		ttgStageStage08 = new GL_TextureRegion(ttStageItems, 128, 47, 64, 47);
		ttgStageblankStage = new GL_TextureRegion(ttStageItems, 192, 47, 64, 47);
		ttgStageSelect = new GL_TextureRegion(ttStageItems, 266, 79, 74, 54);

		ttgStageNextButton = new GL_TextureRegion(ttStageItems, 256, 47, 38, 33);
		ttgStageNextButtonPressed = new GL_TextureRegion(ttStageItems, 294, 47,
				38, 33);

		ttgStageText = new GL_TextureRegion(ttStageItems, 0, 94, 150, 40);

		// stage Cycle and Star

		ttStageItemStarCycle = new GL_Texture(game, "stage_starcycle.png");

		ttgStageStar00 = new GL_TextureRegion(ttStageItemStarCycle, 0, 0, 11,
				36);
		ttgStageStar01 = new GL_TextureRegion(ttStageItemStarCycle, 11, 0, 11,
				36);
		ttgStageStar02 = new GL_TextureRegion(ttStageItemStarCycle, 22, 0, 11,
				36);
		ttgStageStar03 = new GL_TextureRegion(ttStageItemStarCycle, 33, 0, 11,
				36);

		ttgStageCycle01 = new GL_TextureRegion(ttStageItemStarCycle, 52, 0, 85,
				10);
		ttgStageCycle02 = new GL_TextureRegion(ttStageItemStarCycle, 52, 10,
				85, 10);
		ttgStageCycle03 = new GL_TextureRegion(ttStageItemStarCycle, 52, 20,
				85, 10);
		ttgStageCycle04 = new GL_TextureRegion(ttStageItemStarCycle, 52, 30,
				85, 10);
		ttgStageCycle05 = new GL_TextureRegion(ttStageItemStarCycle, 52, 40,
				85, 10);

		// image stage TraningCamp

		ttStageTraingCamp = new GL_Texture(game, "stage_trainingcenter.png");
		ttgStageTraningCenter = new GL_TextureRegion(ttStageTraingCamp, 0, 0,
				146, 107);
		ttgStageTraningCenterPressed = new GL_TextureRegion(ttStageTraingCamp,
				0, 107, 146, 107);

		// Game Clear Scene

		ttStageClearItem01 = new GL_Texture(game, "clear_popup_items_01.png");
		ttgClearGameNextButton = new GL_TextureRegion(ttStageClearItem01, 0,
				171, 91, 34);
		ttgClearGameNextButtonClicked = new GL_TextureRegion(
				ttStageClearItem01, 91, 171, 91, 34);
		ttgClearGameBackGround = new GL_TextureRegion(ttStageClearItem01, 0, 0,
				278, 169);
		ttgClearGameBackButton = new GL_TextureRegion(ttStageClearItem01, 182,
				171, 91, 34);
		ttgClearGameBackButtonClicked = new GL_TextureRegion(
				ttStageClearItem01, 0, 205, 91, 34);
		ttgClearGameGameClearText = new GL_TextureRegion(ttStageClearItem01,
				91, 205, 147, 30);
		ttgClearGameStageNum = new GL_TextureRegion(ttStageClearItem01, 185,
				235, 93, 58);
		ttgClearGameNewRecordText = new GL_TextureRegion(ttStageClearItem01, 0,
				239, 84, 14);
		ttgClearGameGameFailText = new GL_TextureRegion(ttStageClearItem01, 0,
				253, 118, 25);
		ttgClearGameReplayButton = new GL_TextureRegion(ttStageClearItem01, 0,
				279, 91, 34);
		ttgClearGameReplayButtonClicked = new GL_TextureRegion(
				ttStageClearItem01, 91, 279, 91, 34);

		// Coin

		ttCoin = new GL_Texture(game, "coin.png");
		aniCoin = new GL_Animation(new GL_TextureRegion(ttCoin, 0, 0, 44, 48),
				0.15f, new GL_TextureRegion(ttCoin, 0, 0, 44, 48),
				new GL_TextureRegion(ttCoin, 44, 0, 44, 48),
				new GL_TextureRegion(ttCoin, 88, 0, 44, 48),
				new GL_TextureRegion(ttCoin, 132, 0, 44, 48),
				new GL_TextureRegion(ttCoin, 0, 48, 44, 48),
				new GL_TextureRegion(ttCoin, 44, 48, 44, 48),
				new GL_TextureRegion(ttCoin, 88, 48, 44, 48),
				new GL_TextureRegion(ttCoin, 132, 48, 44, 48));

		// story GL_TEXTUREREGION Win Lose

		ttStoryBoardWinLose = new GL_Texture(game, "story_win_lose.png");
		ttgStoryBoardWin = new GL_TextureRegion(ttStoryBoardWinLose, 0, 0, 480,
				320);
		ttgStoryBoardLose = new GL_TextureRegion(ttStoryBoardWinLose, 0, 320,
				480, 320);

		ttScrMainMenuPoint = new GL_Texture(game, "scr_main_menu_point.png");
		ttgScrMainMenuPoint = new GL_TextureRegion(ttScrMainMenuPoint, 0, 0,
				83, 72);
	}

	public static void load06(GL_Game game) {
		ttStageClearItem02 = new GL_Texture(game, "clear_popup_items_02.png");
		ttgClearGameBackPage = new GL_TextureRegion(ttStageClearItem02, 114,
				91, 246, 136);
		ttgClearGameStar00 = new GL_TextureRegion(ttStageClearItem02, 0, 320,
				89, 27);
		ttgClearGameStar01 = new GL_TextureRegion(ttStageClearItem02, 0, 347,
				89, 27);
		ttgClearGameStar02 = new GL_TextureRegion(ttStageClearItem02, 0, 374,
				89, 27);
		ttgClearGameStar03 = new GL_TextureRegion(ttStageClearItem02, 0, 401,
				89, 27);

		// X Button

		ttXbutton = new GL_Texture(game, "scene_total_x_button.png");
		ttgXButton = new GL_TextureRegion(ttXbutton, 0, 0, 33, 33);
		ttgXButtonPressed = new GL_TextureRegion(ttXbutton, 0, 33, 33, 33);

		// Lee Sun Sin

		ttLeesunsinDamaged = new GL_Texture(game,
				"leesunshin_damaged_merge.png");
		ttLeesunsinWalking = new GL_Texture(game, "leesunshin_walking.png");
		aniLeesunsinDamaged = new GL_Animation(new GL_TextureRegion(
				ttLeesunsinWalking, 0, 0, 475, 245), 0.15f,
				new GL_TextureRegion(ttLeesunsinDamaged, 0, 0, 475, 245),
				new GL_TextureRegion(ttLeesunsinDamaged, 475, 0, 475, 245));

		aniLeesunsinWalking = new GL_Animation(new GL_TextureRegion(
				ttLeesunsinWalking, 0, 0, 475, 245), 0.15f,
				new GL_TextureRegion(ttLeesunsinWalking, 0, 0, 475, 245),
				new GL_TextureRegion(ttLeesunsinWalking, 475, 0, 475, 245),
				new GL_TextureRegion(ttLeesunsinWalking, 950, 0, 475, 245),
				new GL_TextureRegion(ttLeesunsinWalking, 1425, 0, 475, 245));

		// ally Structure

		ttAllyStructure = new GL_Texture(game, "ally_structure.png");
		ttgAllyStructure100 = new GL_TextureRegion(ttAllyStructure, 0, 320,
				330, 320);
		ttgAllyStructureDamaged100 = new GL_TextureRegion(ttAllyStructure, 0,
				0, 330, 320);
		ttgAllyStructure80 = new GL_TextureRegion(ttAllyStructure, 330, 320,
				330, 320);
		ttgAllyStructureDamaged80 = new GL_TextureRegion(ttAllyStructure, 330,
				0, 330, 320);
		ttgAllyStructure60 = new GL_TextureRegion(ttAllyStructure, 660, 320,
				330, 320);
		ttgAllyStructureDamaged60 = new GL_TextureRegion(ttAllyStructure, 660,
				0, 330, 320);
		ttgAllyStructure40 = new GL_TextureRegion(ttAllyStructure, 990, 320,
				330, 320);
		ttgAllyStructureDamaged40 = new GL_TextureRegion(ttAllyStructure, 990,
				0, 330, 320);
		ttgAllyStructure20 = new GL_TextureRegion(ttAllyStructure, 1320, 320,
				330, 320);
		ttgAllyStructureDamaged20 = new GL_TextureRegion(ttAllyStructure, 1320,
				0, 330, 320);

		// enemy Structure

		ttEnemyStructure = new GL_Texture(game, "enemy_structure.png");
		ttgEnemyStructure100 = new GL_TextureRegion(ttEnemyStructure, 0, 0,
				353, 320);
		ttgEnemyStructureDamaged100 = new GL_TextureRegion(ttEnemyStructure, 0,
				320, 353, 320);
		ttgEnemyStructure80 = new GL_TextureRegion(ttEnemyStructure, 353, 0,
				353, 320);
		ttgEnemyStructureDamaged80 = new GL_TextureRegion(ttEnemyStructure,
				353, 320, 353, 320);
		ttgEnemyStructure60 = new GL_TextureRegion(ttEnemyStructure, 706, 0,
				353, 320);
		ttgEnemyStructureDamaged60 = new GL_TextureRegion(ttEnemyStructure,
				706, 320, 353, 320);
		ttgEnemyStructure40 = new GL_TextureRegion(ttEnemyStructure, 1059, 0,
				353, 320);
		ttgEnemyStructureDamaged40 = new GL_TextureRegion(ttEnemyStructure,
				1059, 320, 353, 320);
		ttgEnemyStructure20 = new GL_TextureRegion(ttEnemyStructure, 1412, 0,
				353, 320);
		ttgEnemyStructureDamaged20 = new GL_TextureRegion(ttEnemyStructure,
				1412, 320, 353, 320);

		// Game Play Scene Button sets

		ttGamePlaySceneButton = new GL_Texture(game, "game_scene_button.png");

		ttgGamePlaySceneLeftButton = new GL_TextureRegion(
				ttGamePlaySceneButton, 200, 0, 200, 200);
		ttgGamePlaySceneLeftButtonPressed = new GL_TextureRegion(
				ttGamePlaySceneButton, 0, 0, 200, 200);
		ttgGamePlaySceneRightButton = new GL_TextureRegion(
				ttGamePlaySceneButton, 200, 200, 200, 200);
		ttgGamePlaySceneRightButtonPressed = new GL_TextureRegion(
				ttGamePlaySceneButton, 0, 200, 200, 200);
		ttgGamePlaySceneUnitLock = new GL_TextureRegion(ttGamePlaySceneButton,
				400, 0, 200, 200);
		ttgGamePlaySceneOption = new GL_TextureRegion(ttGamePlaySceneButton,
				400, 200, 200, 200);
		ttgGamePlaySceneOptionPressed = new GL_TextureRegion(
				ttGamePlaySceneButton, 600, 0, 200, 200);
		ttgGamePlaySceneOptionExcution = new GL_TextureRegion(
				ttGamePlaySceneButton, 600, 200, 200, 200);

		// Setting Window
		ttSettingWindowsAsset01 = new GL_Texture(game,
				"control_windows_asset_01.png");
		ttSettingWindowsAsset02 = new GL_Texture(game,
				"control_windows_asset_02.png");

		// game Setting Scene01

		ttgSettingWindowPage = new GL_TextureRegion(ttSettingWindowsAsset01, 0,
				0, 480, 320);

		ttgSettingWindowVolume00 = new GL_TextureRegion(
				ttSettingWindowsAsset01, 0, 320, 122, 22);
		ttgSettingWindowVolume01 = new GL_TextureRegion(
				ttSettingWindowsAsset01, 122, 320, 122, 22);
		ttgSettingWindowVolume02 = new GL_TextureRegion(
				ttSettingWindowsAsset01, 244, 320, 122, 22);
		ttgSettingWindowVolume03 = new GL_TextureRegion(
				ttSettingWindowsAsset01, 0, 342, 122, 22);
		ttgSettingWindowVolume04 = new GL_TextureRegion(
				ttSettingWindowsAsset01, 122, 342, 122, 22);
		ttgSettingWindowVolume05 = new GL_TextureRegion(
				ttSettingWindowsAsset01, 244, 342, 122, 22);
		ttgSettingWindowVolume06 = new GL_TextureRegion(
				ttSettingWindowsAsset01, 0, 364, 122, 22);
		ttgSettingWindowVolume07 = new GL_TextureRegion(
				ttSettingWindowsAsset01, 122, 364, 122, 22);
		ttgSettingWindowVolume08 = new GL_TextureRegion(
				ttSettingWindowsAsset01, 244, 364, 122, 22);
		ttgSettingWindowVolume09 = new GL_TextureRegion(
				ttSettingWindowsAsset01, 0, 386, 122, 22);
		ttgSettingWindowVolume10 = new GL_TextureRegion(
				ttSettingWindowsAsset01, 122, 386, 122, 22);
	}

	public static void load07(GL_Game game) {
		ttgSettingWindowVolumeUp = new GL_TextureRegion(
				ttSettingWindowsAsset01, 401, 375, 33, 33);
		ttgSettingWindowVolumeUpPressed = new GL_TextureRegion(
				ttSettingWindowsAsset01, 366, 375, 33, 33);

		ttgSettingWindowVolumeDown = new GL_TextureRegion(
				ttSettingWindowsAsset01, 366, 408, 33, 33);
		ttgSettingWindowVolumeDownPressed = new GL_TextureRegion(
				ttSettingWindowsAsset01, 366, 342, 33, 33);

		ttgSettingWindowVibration = new GL_TextureRegion(
				ttSettingWindowsAsset01, 118, 408, 118, 58);
		ttgSettingWindowVibrationPressed = new GL_TextureRegion(
				ttSettingWindowsAsset01, 0, 408, 118, 58);
		ttgSettingWindowSound = new GL_TextureRegion(ttSettingWindowsAsset01,
				0, 466, 118, 58);
		ttgSettingWindowSoundPressed = new GL_TextureRegion(
				ttSettingWindowsAsset01, 236, 408, 118, 58);

		ttgSettingWindowEffectText = new GL_TextureRegion(
				ttSettingWindowsAsset01, 423, 342, 57, 26);
		ttgSettingWindowSettingText = new GL_TextureRegion(
				ttSettingWindowsAsset01, 434, 369, 46, 40);
		ttgSettingWindowBackGroundSoundText = new GL_TextureRegion(
				ttSettingWindowsAsset01, 423, 409, 57, 26);

		// game Setting Scene02

		ttgSettingWindowHelp = new GL_TextureRegion(ttSettingWindowsAsset02,
				98, 0, 98, 133);
		ttgSettingWindowHelpPressed = new GL_TextureRegion(
				ttSettingWindowsAsset02, 0, 0, 98, 133);
		ttgSettingWindowControlPanel = new GL_TextureRegion(
				ttSettingWindowsAsset02, 0, 133, 194, 131);

		// Store Center
		ttGameSceneStore = new GL_Texture(game, "stage_scene_store.png");
		ttgGameSceneStoreCenter = new GL_TextureRegion(ttGameSceneStore, 0, 0,
				166, 121);
		ttgGameSceneStoreCenterPressed = new GL_TextureRegion(ttGameSceneStore,
				0, 121, 166, 121);

		// Lee Sun Shin Skill 01 ~ 03

		ttLeeSunShinSkill01 = new GL_Texture(game, "skill01.png");
		aniLeeSunShinSkill01 = new GL_Animation(new GL_TextureRegion(
				ttLeesunsinWalking, 0, 0, 475, 245), 0.2f,
				new GL_TextureRegion(ttLeeSunShinSkill01, 0, 0, 476, 245),
				new GL_TextureRegion(ttLeeSunShinSkill01, 476, 0, 476, 245),
				new GL_TextureRegion(ttLeeSunShinSkill01, 952, 0, 476, 245),
				new GL_TextureRegion(ttLeeSunShinSkill01, 1428, 0, 476, 245),
				new GL_TextureRegion(ttLeeSunShinSkill01, 1904, 0, 476, 245),
				new GL_TextureRegion(ttLeeSunShinSkill01, 0, 245, 476, 245),
				new GL_TextureRegion(ttLeeSunShinSkill01, 476, 245, 476, 245),
				new GL_TextureRegion(ttLeeSunShinSkill01, 952, 245, 476, 245),
				new GL_TextureRegion(ttLeeSunShinSkill01, 1428, 245, 476, 245),
				new GL_TextureRegion(ttLeeSunShinSkill01, 1904, 245, 476, 245));

		ttLeeSunShinSkill02 = new GL_Texture(game, "skill02.png");
		aniLeeSunShinSkill02 = new GL_Animation(new GL_TextureRegion(
				ttLeesunsinWalking, 0, 0, 475, 245), 0.15f,
				new GL_TextureRegion(ttLeeSunShinSkill02, 0, 0, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill02, 475, 0, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill02, 950, 0, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill02, 1425, 0, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill02, 1900, 0, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill02, 0, 245, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill02, 475, 245, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill02, 950, 245, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill02, 1425, 245, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill02, 1900, 245, 475, 245));

		ttLeeSunShinSkill03 = new GL_Texture(game, "skill03.png");
		aniLeeSunShinSkill03 = new GL_Animation(new GL_TextureRegion(
				ttLeesunsinWalking, 0, 0, 475, 245), 0.15f,
				new GL_TextureRegion(ttLeeSunShinSkill03, 0, 0, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill03, 475, 0, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill03, 950, 0, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill03, 1425, 0, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill03, 1900, 0, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill03, 2375, 0, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill03, 2850, 0, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill03, 3325, 0, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill03, 0, 245, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill03, 475, 245, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill03, 950, 245, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill03, 1425, 245, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill03, 1900, 245, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill03, 2375, 245, 475, 245),
				new GL_TextureRegion(ttLeeSunShinSkill03, 2850, 245, 475, 245));

		// leesunshin skill 04

		ttLeeSunShinSkill04 = new GL_Texture(game, "skill04.png");
		aniLeeSunShinSkill04 = new GL_Animation(new GL_TextureRegion(
				ttLeesunsinWalking, 0, 0, 475, 245), 0.15f,
				new GL_TextureRegion(ttLeeSunShinSkill04, 0, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill04, 250, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill04, 500, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill04, 750, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill04, 1000, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill04, 1250, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill04, 1500, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill04, 1750, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill04, 2000, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill04, 0, 129, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill04, 250, 129, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill04, 500, 129, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill04, 750, 129, 250, 129));

		// 기공 신포
		aniLeeSunShinSkillEffect04 = new GL_Animation(new GL_TextureRegion(
				ttLeesunsinWalking, 0, 0, 475, 245), 0.15f,
				new GL_TextureRegion(ttLeeSunShinSkill04, 1000, 129, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill04, 1250, 129, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill04, 1500, 129, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill04, 1750, 129, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill04, 2000, 129, 250, 129));

		ttLeeSunShinSkill05 = new GL_Texture(game, "skill05.png");
		aniLeeSunShinSkill05 = new GL_Animation(new GL_TextureRegion(
				ttLeesunsinWalking, 0, 0, 475, 245), 0.15f,
				new GL_TextureRegion(ttLeeSunShinSkill05, 0, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill05, 250, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill05, 500, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill05, 750, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill05, 1000, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill05, 1250, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill05, 1500, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill05, 1750, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSkill05, 2000, 0, 250, 129));

		ttLeeSunShinSKillEffect0103 = new GL_Texture(game,
				"skill01_03_Effect.png");
		// 크로스
		aniLeeSunShinSkillEffect01 = new GL_Animation(new GL_TextureRegion(
				ttLeesunsinWalking, 0, 0, 475, 245), 0.15f,
				new GL_TextureRegion(ttLeeSunShinSKillEffect0103, 0, 0, 250,
						129), new GL_TextureRegion(ttLeeSunShinSKillEffect0103,
						250, 0, 250, 129), new GL_TextureRegion(
						ttLeeSunShinSKillEffect0103, 500, 0, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSKillEffect0103, 750, 0, 250,
						129), new GL_TextureRegion(ttLeeSunShinSKillEffect0103,
						1000, 0, 250, 129));
		// 때거지
		aniLeeSunShinSkillEffect02 = new GL_Animation(new GL_TextureRegion(
				ttLeesunsinWalking, 0, 0, 475, 245), 0.15f,
				new GL_TextureRegion(ttLeeSunShinSKillEffect0103, 1250, 0, 250,
						129), new GL_TextureRegion(ttLeeSunShinSKillEffect0103,
						1500, 0, 250, 129), new GL_TextureRegion(
						ttLeeSunShinSKillEffect0103, 0, 129, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSKillEffect0103, 250, 129,
						250, 129), new GL_TextureRegion(
						ttLeeSunShinSKillEffect0103, 500, 129, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSKillEffect0103, 750, 129,
						250, 129), new GL_TextureRegion(
						ttLeeSunShinSKillEffect0103, 1000, 129, 250, 129),
				new GL_TextureRegion(ttLeeSunShinSKillEffect0103, 1250, 129,
						250, 129));
	}

	public static void load08(GL_Game game) {
		ttLeeSunShinSkill06 = new GL_Texture(game, "skill06.png");
		aniLeeSunShinSkill06 = new GL_Animation(new GL_TextureRegion(
				ttLeesunsinWalking, 0, 0, 475, 245), 0.15f,
				new GL_TextureRegion(ttLeeSunShinSkill06, 0, 0, 300, 154),
				new GL_TextureRegion(ttLeeSunShinSkill06, 300, 0, 300, 154),
				new GL_TextureRegion(ttLeeSunShinSkill06, 600, 0, 300, 154),
				new GL_TextureRegion(ttLeeSunShinSkill06, 900, 0, 300, 154),
				new GL_TextureRegion(ttLeeSunShinSkill06, 1200, 0, 300, 154),
				new GL_TextureRegion(ttLeeSunShinSkill06, 1500, 0, 300, 154),
				new GL_TextureRegion(ttLeeSunShinSkill06, 1800, 0, 300, 154),
				new GL_TextureRegion(ttLeeSunShinSkill06, 2100, 0, 300, 154),
				new GL_TextureRegion(ttLeeSunShinSkill06, 2400, 0, 300, 154),
				new GL_TextureRegion(ttLeeSunShinSkill06, 0, 154, 300, 154),
				new GL_TextureRegion(ttLeeSunShinSkill06, 300, 154, 300, 154),
				new GL_TextureRegion(ttLeeSunShinSkill06, 600, 154, 300, 154),
				new GL_TextureRegion(ttLeeSunShinSkill06, 900, 154, 300, 154),
				new GL_TextureRegion(ttLeeSunShinSkill06, 1200, 154, 300, 154),
				new GL_TextureRegion(ttLeeSunShinSkill06, 1500, 154, 300, 154),
				new GL_TextureRegion(ttLeeSunShinSkill06, 1800, 154, 300, 154),
				new GL_TextureRegion(ttLeeSunShinSkill06, 2100, 154, 300, 154),
				new GL_TextureRegion(ttLeeSunShinSkill06, 2400, 154, 300, 154));

		// AllyArcher
		// 141*142

		ttallyStructureParticle = new GL_Texture(game,
				"ally_structure_particle.png");

		ttgAllyStructureParticle100 = new GL_TextureRegion(
				ttallyStructureParticle, 0, 0, 330, 320);
		ttgAllyStructureParticle80 = new GL_TextureRegion(
				ttallyStructureParticle, 330, 0, 330, 320);
		ttgAllyStructureParticle60 = new GL_TextureRegion(
				ttallyStructureParticle, 660, 0, 330, 320);
		ttgAllyStructureParticle40 = new GL_TextureRegion(
				ttallyStructureParticle, 990, 0, 330, 320);

		ttDoyoMove = new GL_Texture(game, "doyo_move.png");
		ttDoyoAttack01 = new GL_Texture(game, "doyo_attack_01.png");
		ttDoyoAttack02 = new GL_Texture(game, "doyo_attack_02.png");
		ttDoyoAttack03 = new GL_Texture(game, "doyo_attack_03.png");
		ttDoyoAttack04 = new GL_Texture(game, "doyo_attack_04.png");
		ttDoyoDie = new GL_Texture(game, "doyo_die.png");
		ttDoyoSkill01 = new GL_Texture(game, "doyo_skill_01.png");

		aniDoyoMov = new GL_Animation(new GL_TextureRegion(ttDoyoMove, 0, 0,
				475, 245), 0.15f, new GL_TextureRegion(ttDoyoMove, 0, 0, 475,
				245), new GL_TextureRegion(ttDoyoMove, 475, 0, 475, 245),
				new GL_TextureRegion(ttDoyoMove, 950, 0, 475, 245),
				new GL_TextureRegion(ttDoyoMove, 1425, 0, 475, 245),
				new GL_TextureRegion(ttDoyoMove, 1900, 0, 475, 245),
				new GL_TextureRegion(ttDoyoMove, 2375, 0, 475, 245),
				new GL_TextureRegion(ttDoyoMove, 2850, 0, 475, 245),
				new GL_TextureRegion(ttDoyoMove, 0, 245, 475, 245),
				new GL_TextureRegion(ttDoyoMove, 475, 245, 475, 245),
				new GL_TextureRegion(ttDoyoMove, 950, 245, 475, 245),
				new GL_TextureRegion(ttDoyoMove, 1425, 245, 475, 245),
				new GL_TextureRegion(ttDoyoMove, 1900, 245, 475, 245),
				new GL_TextureRegion(ttDoyoMove, 2375, 245, 475, 245),
				new GL_TextureRegion(ttDoyoMove, 2850, 245, 475, 245));

		aniDoyoAttack01 = new GL_Animation(new GL_TextureRegion(ttDoyoMove, 0,
				0, 475, 245), 0.15f, new GL_TextureRegion(ttDoyoAttack01, 0, 0,
				475, 245), new GL_TextureRegion(ttDoyoAttack01, 475, 0, 475,
				245), new GL_TextureRegion(ttDoyoAttack01, 950, 0, 475, 245),
				new GL_TextureRegion(ttDoyoAttack01, 1425, 0, 475, 245),
				new GL_TextureRegion(ttDoyoAttack01, 1900, 0, 475, 245));

		aniDoyoAttack02 = new GL_Animation(new GL_TextureRegion(ttDoyoMove, 0,
				0, 475, 245), 0.15f, new GL_TextureRegion(ttDoyoAttack02, 0, 0,
				475, 245), new GL_TextureRegion(ttDoyoAttack02, 475, 0, 475,
				245), new GL_TextureRegion(ttDoyoAttack02, 950, 0, 475, 245),
				new GL_TextureRegion(ttDoyoAttack02, 1425, 0, 475, 245),
				new GL_TextureRegion(ttDoyoAttack02, 1900, 0, 475, 245),
				new GL_TextureRegion(ttDoyoAttack02, 0, 245, 475, 245),
				new GL_TextureRegion(ttDoyoAttack02, 475, 245, 475, 245),
				new GL_TextureRegion(ttDoyoAttack02, 950, 245, 475, 245),
				new GL_TextureRegion(ttDoyoAttack02, 1425, 245, 475, 245));

		aniDoyoAttack03 = new GL_Animation(new GL_TextureRegion(ttDoyoMove, 0,
				0, 475, 245), 0.15f, new GL_TextureRegion(ttDoyoAttack03, 0, 0,
				475, 245), new GL_TextureRegion(ttDoyoAttack03, 475, 0, 475,
				245), new GL_TextureRegion(ttDoyoAttack03, 950, 0, 475, 245),
				new GL_TextureRegion(ttDoyoAttack03, 1425, 0, 475, 245),
				new GL_TextureRegion(ttDoyoAttack03, 1900, 0, 475, 245));

		aniDoyoAttack04 = new GL_Animation(new GL_TextureRegion(ttDoyoMove, 0,
				0, 475, 245), 0.15f, new GL_TextureRegion(ttDoyoAttack04, 0, 0,
				475, 245), new GL_TextureRegion(ttDoyoAttack04, 475, 0, 475,
				245), new GL_TextureRegion(ttDoyoAttack04, 950, 0, 475, 245),
				new GL_TextureRegion(ttDoyoAttack04, 1425, 0, 475, 245),
				new GL_TextureRegion(ttDoyoAttack04, 1900, 0, 475, 245),
				new GL_TextureRegion(ttDoyoAttack04, 2375, 0, 475, 245),
				new GL_TextureRegion(ttDoyoAttack04, 2850, 0, 475, 245));

		aniDoyoDie = new GL_Animation(new GL_TextureRegion(ttDoyoDie, 0, 0,
				475, 245), 0.15f, new GL_TextureRegion(ttDoyoDie, 0, 0, 475,
				245), new GL_TextureRegion(ttDoyoDie, 475, 0, 475, 245),
				new GL_TextureRegion(ttDoyoDie, 950, 0, 475, 245),
				new GL_TextureRegion(ttDoyoDie, 1425, 0, 475, 245),
				new GL_TextureRegion(ttDoyoDie, 1900, 0, 475, 245));

		aniDoyoSkill01 = new GL_Animation(new GL_TextureRegion(ttDoyoSkill01,
				0, 0, 308, 123), 0.15f, new GL_TextureRegion(ttDoyoSkill01, 0,
				0, 308, 123), new GL_TextureRegion(ttDoyoSkill01, 308, 0, 308,
				123), new GL_TextureRegion(ttDoyoSkill01, 616, 0, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 924, 0, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 1232, 0, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 1540, 0, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 1848, 0, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 2156, 0, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 2464, 0, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 0, 123, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 308, 123, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 616, 123, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 924, 123, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 1232, 123, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 1540, 123, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 1848, 123, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 2156, 123, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 2464, 123, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 0, 246, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 308, 246, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 616, 246, 308, 123),
				new GL_TextureRegion(ttDoyoSkill01, 924, 246, 308, 123));
	}

	public static void load09(GL_Game game) {

		ttGameUnitGenerateButton = new GL_Texture(game,
				"unit_generate_buttons.png");

		ttgUnitGenerateButtonAxe = new GL_TextureRegion(
				ttGameUnitGenerateButton, 0, 0, 52, 52);
		ttgUnitGenerateButtonAxePressed = new GL_TextureRegion(
				ttGameUnitGenerateButton, 0, 52, 52, 52);
		ttgUnitGenerateButtonMonk = new GL_TextureRegion(
				ttGameUnitGenerateButton, 52, 0, 52, 52);
		ttgUnitGenerateButtonMonkPressed = new GL_TextureRegion(
				ttGameUnitGenerateButton, 52, 52, 52, 52);
		ttgUnitGenerateButtonArcher = new GL_TextureRegion(
				ttGameUnitGenerateButton, 260, 0, 52, 52);
		ttgUnitGenerateButtonArcherPressed = new GL_TextureRegion(
				ttGameUnitGenerateButton, 260, 52, 52, 52);
		ttgUnitGenerateButtonSpear = new GL_TextureRegion(
				ttGameUnitGenerateButton, 104, 0, 52, 52);
		ttgUnitGenerateButtonSpearPressed = new GL_TextureRegion(
				ttGameUnitGenerateButton, 104, 52, 52, 52);
		ttgUnitGenerateButtonGun = new GL_TextureRegion(
				ttGameUnitGenerateButton, 156, 0, 52, 52);
		ttgUnitGenerateButtonGunPressed = new GL_TextureRegion(
				ttGameUnitGenerateButton, 156, 52, 52, 52);
		ttgUnitGenerateButtonSword = new GL_TextureRegion(
				ttGameUnitGenerateButton, 208, 0, 52, 52);
		ttgUnitGenerateButtonSwordPressed = new GL_TextureRegion(
				ttGameUnitGenerateButton, 208, 52, 52, 52);

		ttEnemyStructureParticle = new GL_Texture(game,
				"enemy_structure_particle.png");
		ttgEnemyStructureParticle100 = new GL_TextureRegion(
				ttEnemyStructureParticle, 0, 0, 353, 320);
		ttgEnemyStructureParticle80 = new GL_TextureRegion(
				ttEnemyStructureParticle, 353, 0, 353, 320);
		ttgEnemyStructureParticle60 = new GL_TextureRegion(
				ttEnemyStructureParticle, 706, 0, 353, 320);
		ttgEnemyStructureParticle40 = new GL_TextureRegion(
				ttEnemyStructureParticle, 1059, 0, 353, 320);
		ttgEnemyStructureParticle20 = new GL_TextureRegion(
				ttEnemyStructureParticle, 1412, 0, 353, 320);

		// prepareGameStart

		ttPrepareSceneGameStartButton = new GL_Texture(game,
				"preparebutton.png");
		ttgGamePreparedSceneGameStart = new GL_TextureRegion(
				ttPrepareSceneGameStartButton, 0, 0, 118, 58);
		ttgGamePreparedSceneGameStartPressed = new GL_TextureRegion(
				ttPrepareSceneGameStartButton, 0, 58, 118, 58);

		// Game Running Terminate running
		ttGameRunningPuase = new GL_Texture(game, "gamerunningpausebutton.png");

		ttgGameRunningContinue = new GL_TextureRegion(ttGameRunningPuase, 0,
				116, 118, 58);
		ttgGameRunningContinuePressed = new GL_TextureRegion(
				ttGameRunningPuase, 0, 174, 118, 58);

		ttgGameRunningTerminate = new GL_TextureRegion(ttGameRunningPuase, 0,
				0, 118, 58);
		ttgGameRunningTerminatePressed = new GL_TextureRegion(
				ttGameRunningPuase, 0, 58, 118, 58);
		// ally unit
		ttAlly_AxeMan = new GL_Texture(game, "ally_axeman.png");
		ttAlly_Monk = new GL_Texture(game, "ally_monk.png");

		// ally Unit
		// monk
		// die
		aniChrAllyMonkDie = new GL_Animation(new GL_TextureRegion(ttAlly_Monk,
				3325, 0, 475, 245), 0.15f, new GL_TextureRegion(ttAlly_Monk, 0,
				0, 475, 245), new GL_TextureRegion(ttAlly_Monk, 475, 0, 475,
				245), new GL_TextureRegion(ttAlly_Monk, 950, 0, 475, 245),
				new GL_TextureRegion(ttAlly_Monk, 1425, 0, 475, 245),
				new GL_TextureRegion(ttAlly_Monk, 1900, 0, 475, 245),
				new GL_TextureRegion(ttAlly_Monk, 2375, 0, 475, 245),
				new GL_TextureRegion(ttAlly_Monk, 2850, 0, 475, 245),
				new GL_TextureRegion(ttAlly_Monk, 3325, 0, 475, 245));
		// move
		aniChrAllyMonkMove = new GL_Animation(new GL_TextureRegion(ttAlly_Monk,
				0, 490, 475, 245), 0.15f, new GL_TextureRegion(ttAlly_Monk, 0,
				490, 475, 245), new GL_TextureRegion(ttAlly_Monk, 475, 490,
				475, 245),
				new GL_TextureRegion(ttAlly_Monk, 950, 490, 475, 245));
		// atack
		aniChrAllyMonkAttack = new GL_Animation(new GL_TextureRegion(
				ttAlly_Monk, 0, 490, 475, 245), 0.15f, new GL_TextureRegion(
				ttAlly_Monk, 0, 245, 475, 245), new GL_TextureRegion(
				ttAlly_Monk, 475, 245, 475, 245), new GL_TextureRegion(
				ttAlly_Monk, 950, 245, 475, 245), new GL_TextureRegion(
				ttAlly_Monk, 1425, 245, 475, 245), new GL_TextureRegion(
				ttAlly_Monk, 1900, 245, 475, 245), new GL_TextureRegion(
				ttAlly_Monk, 2375, 245, 475, 245), new GL_TextureRegion(
				ttAlly_Monk, 2850, 245, 475, 245));

		ttgAllyMonkAttack = new GL_TextureRegion(ttAlly_Monk, 3730, 665, 70, 70);
		// ally axe
		// die
		aniChrAllyAxeDie = new GL_Animation(new GL_TextureRegion(ttAlly_AxeMan,
				2850, 0, 475, 245), 0.15f, new GL_TextureRegion(ttAlly_AxeMan,
				0, 0, 475, 245), new GL_TextureRegion(ttAlly_AxeMan, 475, 0,
				475, 245),
				new GL_TextureRegion(ttAlly_AxeMan, 950, 0, 475, 245),
				new GL_TextureRegion(ttAlly_AxeMan, 1425, 0, 475, 245),
				new GL_TextureRegion(ttAlly_AxeMan, 1900, 0, 475, 245),
				new GL_TextureRegion(ttAlly_AxeMan, 2375, 0, 475, 245),
				new GL_TextureRegion(ttAlly_AxeMan, 2850, 0, 475, 245));
		// move
		aniChrAllyAxeMove = new GL_Animation(new GL_TextureRegion(
				ttAlly_AxeMan, 0, 490, 475, 245), 0.15f, new GL_TextureRegion(
				ttAlly_AxeMan, 0, 490, 475, 245), new GL_TextureRegion(
				ttAlly_AxeMan, 475, 490, 475, 245), new GL_TextureRegion(
				ttAlly_AxeMan, 950, 490, 475, 245));
		// attack
		aniChrAllyAxeAttack = new GL_Animation(new GL_TextureRegion(
				ttAlly_AxeMan, 0, 490, 475, 245), 0.15f, new GL_TextureRegion(
				ttAlly_AxeMan, 0, 245, 475, 245), new GL_TextureRegion(
				ttAlly_AxeMan, 475, 245, 475, 245), new GL_TextureRegion(
				ttAlly_AxeMan, 950, 245, 475, 245), new GL_TextureRegion(
				ttAlly_AxeMan, 1425, 245, 475, 245), new GL_TextureRegion(
				ttAlly_AxeMan, 1900, 245, 475, 245), new GL_TextureRegion(
				ttAlly_AxeMan, 2375, 245, 475, 245), new GL_TextureRegion(
				ttAlly_AxeMan, 2850, 245, 475, 245));

		ttScoreFont = new GL_Texture(game, "scorefont.png");
		ttgScoreFont = new GL_TextureRegion(ttScoreFont, 0, 0, 48, 36);

		ScoreNumberFont = new GL_Font(ttFont, 0, 40, 10, 34, 40);
		MoneyNumberFont = new GL_Font(ttFont, 0, 80, 10, 34, 40);

		ttStageClearNumberPanel = new GL_Texture(game, "stage_num_panel.png");

		ttgStageClearNumberPanel02 = new GL_TextureRegion(
				ttStageClearNumberPanel, 0, 0, 93, 58);
		ttgStageClearNumberPanel03 = new GL_TextureRegion(
				ttStageClearNumberPanel, 0, 58, 93, 58);
		ttgStageClearNumberPanel04 = new GL_TextureRegion(
				ttStageClearNumberPanel, 0, 116, 93, 58);
		ttgStageClearNumberPanel05 = new GL_TextureRegion(
				ttStageClearNumberPanel, 0, 174, 93, 58);
		ttgStageClearNumberPanel06 = new GL_TextureRegion(
				ttStageClearNumberPanel, 0, 232, 93, 58);
		ttgStageClearNumberPanel07 = new GL_TextureRegion(
				ttStageClearNumberPanel, 0, 290, 93, 58);
		ttgStageClearNumberPanel08 = new GL_TextureRegion(
				ttStageClearNumberPanel, 0, 348, 93, 58);
		ttgStageClearNumberPanel09 = new GL_TextureRegion(
				ttStageClearNumberPanel, 0, 406, 93, 58);

		Log.e("asset Load Success", "Asset Load success");
		isSuccessAssetLoading = true;
	}

	public static void reload() {
		ttBtnGameSetting.reload();
		ttBtnGameSettingPressed.reload();

		// GameStart Button
		ttBtnGameStart.reload();
		ttBtnGameStartPressed.reload();

		// MainMenu Screen
		ttScrMainMenu.reload();

		// MainMenu Point
		ttScrMainMenuPoint.reload();

		// MainMenu Screen Fire
		ttScrMainFire.reload();

		// Game Skill 1 Button
		ttGameButton01.reload();
		// Game Skill 2 Button
		ttGameButton02.reload();
		// Game Skill 2 Button
		ttGameButton03.reload();
		// Game Skill 2 Button
		ttGameButton04.reload();
		// Game Skill 2 Button
		ttGameButton05.reload();
		// Game Skill 2 Button
		ttGameButton06.reload();

		// chr enemy gun
		ttChrEnemyGun.reload();

		// chr enemy sword
		ttChrEnemySword.reload();

		// chr enemy middleboss01
		ttChrMiddleBoss01.reload();

		// chr enemy middleboss02
		ttChrMiddleBoss02.reload();

		// img bullet Gun
		ttImgBulletGun.reload();

		// img enery bar
		ttEnergyBar.reload();

		// img Loading Charecter

		ttLoadingWindowCharecter.reload();

		// img intro Scene Srogun

		ttIntroSrogun.reload();
		ttIntroButton.reload();

		// img setting scene

		ttSettingItem.reload();
		ttSettingPage.reload();
		ttSettingButton.reload();
		ttSettingBackGround.reload();
		ttSettingItemPage.reload();
		ttSettingUnitBackGround.reload();
		ttSettingUnitMudae.reload();

		// stage clear number panel
		ttStageClearNumberPanel.reload();

		// img setting statebar

		ttStateBar.reload();

		// img Setting Icon

		ttSettingIcons.reload();

		// img stage Items

		ttStageItems.reload();

		// image stage Items02

		ttStageItemStarCycle.reload();

		// image stage TraningCamp

		ttStageTraingCamp.reload();

		// image X_button

		ttXbutton.reload();

		// image Game stage clear

		ttStageClearItem01.reload();

		// image Game stage clear

		ttStageClearItem02.reload();

		// Leesunsin Walking

		ttLeesunsinWalking.reload();

		// Leesunsin Damaged

		ttLeesunsinDamaged.reload();

		// ally Structure

		ttAllyStructure.reload();

		// enemy Structure

		ttEnemyStructure.reload();

		// game Play Scene Button

		ttGamePlaySceneButton.reload();

		// story board Scene

		ttStoryBoardScene.reload();

		// story board win and lose Scene

		ttStoryBoardWinLose.reload();

		// game Setting Scene

		ttSettingWindowsAsset01.reload();
		ttSettingWindowsAsset02.reload();

		// Game BackGround

		ttGameBackGround.reload();

		// game Unit Generate Button

		ttGameUnitGenerateButton.reload();

		// Game Store

		ttGameSceneStore.reload();

		// LeeSunShin Skill 01 ~ 06

		ttLeeSunShinSkill01.reload();
		ttLeeSunShinSkill02.reload();
		ttLeeSunShinSkill03.reload();
		ttLeeSunShinSkill04.reload();
		ttLeeSunShinSkill05.reload();
		ttLeeSunShinSkill06.reload();

		// LeeSunShin Skill Effect 01, 03

		ttLeeSunShinSKillEffect0103.reload();

		// firde boat
		ttFiredBoatMainGame.reload();

		// ally Structure Particle

		ttallyStructureParticle.reload();

		// enemy Structure Particle

		ttEnemyStructureParticle.reload();

		// game Prapared Scene Gamestart;

		ttPrepareSceneGameStartButton.reload();

		// ally unit

		ttAlly_AxeMan.reload();
		ttAlly_Monk.reload();

		// Font
		ttScoreFont.reload();

		// Coin
		ttCoin.reload();

		// game running pause

		ttGameRunningPuase.reload();

		// helpstoryboard

		ttHelpStoryBoardScene.reload();

		// ally ArcherMan

		ttAllyArcherMan.reload();

		// ally SpearMan

		ttAllySpearMan.reload();

		// Back Ground

		ttGameSceneBackGround02.reload();
		ttFont.reload();
		ttDoyoMove.reload();
		ttDoyoAttack01.reload();
		ttDoyoAttack02.reload();
		ttDoyoAttack03.reload();
		ttDoyoAttack04.reload();
		ttDoyoDie.reload();
		ttDoyoSkill01.reload();
	}

	public static void playSound(IFace_Sound sound) {
		if (Manage_Settings.isSoundMusicVolume)
			sound.play(Manage_Settings.fSoundVolume);
	}

	public static void settingMusicVolume() {
		musicFullWindow.setVolume(Manage_Settings.fMusicVolume);
		musicGameMainBackground.setVolume(Manage_Settings.fMusicVolume);
		musicGameClear.setVolume(Manage_Settings.fMusicVolume);
		musicGameLose.setVolume(Manage_Settings.fMusicVolume);
	}

	public static void settingMusicOnOff() {
		if (Manage_Settings.isSoundMusicVolume)
			musicFullWindow.play();
		else {
			musicFullWindow.stop();
		}
	}

	public static void PlayMusic(IFace_Music play) {
		if (Manage_Settings.isSoundMusicVolume) {
			musicFullWindow.stop();
			musicGameMainBackground.stop();
			musicGameClear.stop();
			musicGameLose.stop();
			play.restart();
		}
	}

}

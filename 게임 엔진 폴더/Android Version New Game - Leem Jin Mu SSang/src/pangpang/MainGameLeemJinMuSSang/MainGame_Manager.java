// 각 객체의 여러 인스턴스를 추적하고
// 매 프레임마다 객체를 업데이트 하며 객체와 Bob 사이의 충돌을 검사하고 반응을 수행
package pangpang.MainGameLeemJinMuSSang;

import java.util.ArrayList;
import java.util.Random;

import pangpang.MainGameLeemJinMuSSang.UnitInformation.ALLY.ArcherMan;
import pangpang.MainGameLeemJinMuSSang.UnitInformation.ALLY.AxeMan;
import pangpang.MainGameLeemJinMuSSang.UnitInformation.ALLY.MonkMan;
import pangpang.MainGameLeemJinMuSSang.UnitInformation.ALLY.SpearMan;
import jrcengine.Interface.Object_Dynamic;
import jrcengine.Interface.Screen_Manager;
import jrcengine.Manage.Manage_Assets;
import jrcengine.Manage.Manage_Settings;
import jrcengine.Math.Math_Overlap;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

public class MainGame_Manager extends Screen_Manager {

	public static final int MANAGE_STATE_RUNNING = 0;
	public static final int MANAGE_STATE_WAIT_TIME = 1;
	public static final int MANAGE_STATE_NEXT_LEVEL = 2;
	public static final int MANAGE_STATE_GAME_OVER = 3;

	// max num 10
	public static final int PARCANTAGERUNAWAY = 5;

	private int nLeesunshinPositionMargen = 7;
	private int nAllyStructureHP = 100;
	private int nEnemyStructureHP = 100;

	private final int world_Width;
	private final int world_Height;
	private Random rnd;
	private int nchoice;

	public static LeeSunSin mLeeSunSin;

	public static final ArrayList<EnemyGunMan> enemyGunMans = new ArrayList<EnemyGunMan>();
	public static final ArrayList<EnemySwordMan> enemySwordMans = new ArrayList<EnemySwordMan>();
	public static final ArrayList<Bullet> enemyBullet = new ArrayList<Bullet>();
	public static final ArrayList<AttackFiyld> enemyAttackFiyld = new ArrayList<AttackFiyld>();
	public static final ArrayList<EnemyMiddleBossMan> enemyMiddleBoss01 = new ArrayList<EnemyMiddleBossMan>();
	public static final ArrayList<HpBar> hpBar = new ArrayList<HpBar>();
	public static final ArrayList<Commander> commander = new ArrayList<Commander>();
	public static final ArrayList<SkillObject> leesunsinSkillObjects = new ArrayList<SkillObject>();
	public static final ArrayList<EnergyBall> energyBall = new ArrayList<EnergyBall>();
	public static final ArrayList<AllyMonkMan> allyMonkMans = new ArrayList<AllyMonkMan>();
	public static final ArrayList<AllyAxeMan> allyAxeMans = new ArrayList<AllyAxeMan>();
	public static final ArrayList<AttackFiyld> allyAttackFiyld = new ArrayList<AttackFiyld>();
	public static final ArrayList<AllyArcherMan> allyArchers = new ArrayList<AllyArcherMan>();
	public static final ArrayList<AllySpearMan> allySpearMans = new ArrayList<AllySpearMan>();
	public static final ArrayList<EnemyFinalBossMan> enemyFinalBoss = new ArrayList<EnemyFinalBossMan>();

	private int nGenerateEnemyGun;
	private int nGenerateEnemySword;
	private int nGenerateEnemyBoss01;
	private int nGenerateEnemyBoss02;
	private int nGenerateAllyMonk;
	private int nGenerateAllyAxe;
	private int nGenerateAllyArcherMan;
	private int nGenerateAllySpearMan;
	private int nGenerateFinalBoss;

	public static int nStageScore = 0;

	private Vibrator vibe;

	public MainGame_Manager(int world_Width, int world_Height) {

		this.world_Width = world_Width;
		this.world_Height = world_Height;
		this.rnd = new Random();
		this.vibe = (Vibrator) Game_StartManager.activity
				.getSystemService(Context.VIBRATOR_SERVICE);
		init();
		mLeeSunSin = new LeeSunSin(1, this.get_World_Width()
				* Screen_MainGame.nMultipleNumberSizeOfMap / 2,
				this.get_World_Height() / 2 + nLeesunshinPositionMargen,
				LeeSunSin.LEESUNSHIN_HP);
		StageInformation.Stage.STAGEGENERATE
				.SettingStage(StageWindow.SELECTSTAGENUM);

		this.nGenerateEnemyGun = StageInformation.Stage.STAGEGENERATE.generateEnemyGunManTime;
		this.nGenerateEnemySword = StageInformation.Stage.STAGEGENERATE.generateEnemySwordManTime;
		this.nGenerateEnemyBoss01 = StageInformation.Stage.STAGEGENERATE.generateEnemyBOSS01Time;
		this.nGenerateEnemyBoss02 = StageInformation.Stage.STAGEGENERATE.generateEnemyBOSS02Time;
		this.nGenerateFinalBoss = StageInformation.Stage.STAGEGENERATE.generateEnemyFinalBossTime;

		generate();

	}

	private void init() {
		mLeeSunSin = null;

		MainGame_Manager.enemyGunMans.clear();
		MainGame_Manager.enemySwordMans.clear();
		MainGame_Manager.enemyBullet.clear();
		MainGame_Manager.enemyAttackFiyld.clear();
		MainGame_Manager.enemyMiddleBoss01.clear();
		MainGame_Manager.hpBar.clear();
		MainGame_Manager.commander.clear();
		MainGame_Manager.leesunsinSkillObjects.clear();
		MainGame_Manager.energyBall.clear();
		MainGame_Manager.allyMonkMans.clear();
		MainGame_Manager.allyAxeMans.clear();
		MainGame_Manager.allyAttackFiyld.clear();
		MainGame_Manager.allyArchers.clear();
		MainGame_Manager.allySpearMans.clear();
		MainGame_Manager.enemyFinalBoss.clear();
	}

	// 게임 세계의 생성
	@Override
	protected void generate() {

		commander.add(new Commander(Commander.COMMANDER_ALLY_WIDTH / 2,
				world_Height / 2, Commander.COMMANDER_WIDTH,
				Commander.COMMANDER_HEIHGT, nAllyStructureHP,
				Commander.OURFORCE));
		commander.add(new Commander(this.get_World_Width()
				* Screen_MainGame.nMultipleNumberSizeOfMap
				- Commander.COMMANDER_WIDTH / 2, world_Height / 2 + 50,
				Commander.COMMANDER_WIDTH, Commander.COMMANDER_HEIHGT,
				nEnemyStructureHP, Commander.ENEMYFORCE));

	}

	public void update(MainGame_Renderer rander, float deltaTime,
			float accel_X, float accel_Y, float click_X, float click_Y) {

		update_checkCollisions(deltaTime);
		update_Objections(deltaTime, accel_X, accel_Y, click_X, click_Y);
	}

	private void update_Objections(float deltaTime, float accel_X,
			float accel_Y, float click_X, float click_Y) {
		update_structure(deltaTime);
		update_enemy(deltaTime);
		update_LeeSunSin(deltaTime);

	}

	private void update_structure(float deltaTime) {
		update_commander(deltaTime);
	}

	private void update_commander(float deltaTime) {
		int len = commander.size();
		if (len <= 0)
			return;

		for (int i = 0; i < len; i++)
			commander.get(i).update(deltaTime);
	}

	private void update_enemy(float deltaTime) {
		update_Limit_position(deltaTime);
		update_level_generation(deltaTime);
		update_enemy_gun(deltaTime);
		update_enemy_sword(deltaTime);
		update_enemy_bullet_AttackFiyld_monkAttack(deltaTime);
		update_ally_monkMan(deltaTime);
		update_ally_axeMan(deltaTime);
		update_ally_archerMan(deltaTime);
		update_ally_spearMan(deltaTime);
		update_leesunshin_skill(deltaTime);
		update_middleBoss(deltaTime);
		update_final_boss(deltaTime);
		// update_energy_bar(deltaTime);
	}

	private void update_energy_bar(float deltaTime) {

		// 0 되면 지우게 따로 오출하는 것.
		int len = hpBar.size();
		if (len <= 0)
			return;

		for (int i = 0; i < len; i++) {
			HpBar hpbarTemp = hpBar.get(i);
			if (hpbarTemp.getnHp() <= 0) {
				hpBar.remove(hpbarTemp);
				len = hpBar.size();
			}
		}
	}

	private void generateAllyArcherMan() {
		if (allyArchers.size() < nGenerateAllyArcherMan) {
			if (rnd.nextInt(50) == 1) {
				allyArchers
						.add(new AllyArcherMan(
								rnd.nextInt(100),
								this.get_World_Height() / 2 - 10
										+ rnd.nextInt(20),
								((int) ArcherMan
										.Bonus(Manage_Settings.nUnitArcherLv)
										* ArcherMan.HPBasic + ArcherMan.HPBasic),
								((int) ArcherMan
										.Bonus(Manage_Settings.nUnitArcherLv)
										* ArcherMan.SpeedBasic + ArcherMan.SpeedBasic)));
			}
		}
	}

	private void generateAllySpearMan() {
		if (allySpearMans.size() < nGenerateAllySpearMan) {
			if (rnd.nextInt(50) == 1) {
				allySpearMans.add(new AllySpearMan(rnd.nextInt(100), this
						.get_World_Height() / 2 - 10 + rnd.nextInt(20),
						((int) SpearMan.Bonus(Manage_Settings.nUnitSpearLv)
								* SpearMan.HPBasic + SpearMan.HPBasic),
						((int) SpearMan.Bonus(Manage_Settings.nUnitSpearLv)
								* SpearMan.SpeedBasic + SpearMan.SpeedBasic)));
			}
		}
	}

	private void generateAllyMonkMan() {
		if (allyMonkMans.size() < nGenerateAllyMonk) {
			if (rnd.nextInt(50) == 1) {
				allyMonkMans.add(new AllyMonkMan(rnd.nextInt(100), this
						.get_World_Height() / 2 - 10 + rnd.nextInt(20),
						((int) MonkMan.Bonus(Manage_Settings.nUnitMonkManLv)
								* MonkMan.HPBasic + MonkMan.HPBasic),
						((int) MonkMan.Bonus(Manage_Settings.nUnitMonkManLv)
								* MonkMan.SpeedBasic + MonkMan.SpeedBasic)));
			}
		}
	}

	private void generateAllyAxeMan() {
		if (allyAxeMans.size() < nGenerateAllyAxe) {
			if (rnd.nextInt(50) == 1) {
				allyAxeMans.add(new AllyAxeMan(rnd.nextInt(100), this
						.get_World_Height() / 2 - 10 + rnd.nextInt(20),
						(int) ((int) AxeMan
								.Bonus(Manage_Settings.nUnitAxeManLv)
								* AxeMan.HPBasic + AxeMan.HPBasic),
						(int) ((int) AxeMan
								.Bonus(Manage_Settings.nUnitAxeManLv)
								* AxeMan.SpeedBasic + AxeMan.SpeedBasic)));
			}
		}
	}

	// 무한으로 회전하게 만드느 것
	private void update_Limit_position(float deltaTime) {
		int len01 = enemyGunMans.size();
		int len02 = enemySwordMans.size();

		if (len01 > 0)
			for (int i = 0; i < len01; i++)
				if (0 > enemyGunMans.get(i).position.x)
					enemyGunMans.get(i).position.x = this.get_World_Width()
							* Screen_MainGame.nMultipleNumberSizeOfMap
							- rnd.nextInt(100);

		if (len02 > 0)
			for (int i = 0; i < len02; i++)
				if (0 > enemySwordMans.get(i).position.x)
					enemySwordMans.get(i).position.x = this.get_World_Width()
							* Screen_MainGame.nMultipleNumberSizeOfMap
							- rnd.nextInt(100);

		if (len01 > 0)
			for (int i = 0; i < len01; i++)
				if (this.get_World_Width()
						* Screen_MainGame.nMultipleNumberSizeOfMap + 20 < enemyGunMans
							.get(i).position.x) {
					MainGame_Manager.enemyGunMans.remove(enemyGunMans.get(i));
					len01 = MainGame_Manager.enemyGunMans.size();
				}

		if (len02 > 0)
			for (int i = 0; i < len02; i++)
				if (this.get_World_Width()
						* Screen_MainGame.nMultipleNumberSizeOfMap + 20 < enemySwordMans
							.get(i).position.x) {
					MainGame_Manager.enemySwordMans.remove(enemySwordMans
							.get(i));
					len02 = MainGame_Manager.enemySwordMans.size();
				}

	}

	private void update_level_generation(float deltaTime) {
		generateEnemySwordMan();
		generateEnemyGunMan();
		generateEnemyBossMan01();
		generateEnemyBossMan02();
		nGenerateFinalBoss();
		// generateAllySpearMan();
		// generateAllyArcherMan();
		// generateAllyMonkMan();
		// generateAllyAxeMan();
	}

	private void generateEnemyBossMan02() {
		int count = 0;

		for (int i = 0; i < enemyMiddleBoss01.size(); i++)
			if (enemyMiddleBoss01.get(i).getType() == EnemyMiddleBossMan.TYPE02)
				count++;

		if (count < nGenerateEnemyBoss02) {
			if (rnd.nextInt(50) == 1) {
				enemyMiddleBoss01.add(new EnemyMiddleBossMan(this
						.get_World_Width()
						* Screen_MainGame.nMultipleNumberSizeOfMap
						- rnd.nextInt(100), this.get_World_Height() / 2 - 10
						+ rnd.nextInt(20), EnemyMiddleBossMan.TYPE02));
			}
		}
	}

	private void nGenerateFinalBoss() {
		if (enemyFinalBoss.size() < nGenerateFinalBoss) {
			if (rnd.nextInt(50) == 1) {
				enemyFinalBoss.add(new EnemyFinalBossMan(this.get_World_Width()
						* Screen_MainGame.nMultipleNumberSizeOfMap
						- rnd.nextInt(100), this.get_World_Height() / 2 - 10
						+ rnd.nextInt(20)));
			}
		}
	}

	private void generateEnemyBossMan01() {
		int count = 0;

		for (int i = 0; i < enemyMiddleBoss01.size(); i++)
			if (enemyMiddleBoss01.get(i).getType() == EnemyMiddleBossMan.TYPE01)
				count++;

		if (count < nGenerateEnemyBoss01) {
			if (rnd.nextInt(50) == 1) {
				enemyMiddleBoss01.add(new EnemyMiddleBossMan(this
						.get_World_Width()
						* Screen_MainGame.nMultipleNumberSizeOfMap
						- rnd.nextInt(100), this.get_World_Height() / 2 - 10
						+ rnd.nextInt(20), EnemyMiddleBossMan.TYPE01));
			}
		}
	}

	private void generateEnemyGunMan() {
		if (enemyGunMans.size() < nGenerateEnemyGun) {
			if (rnd.nextInt(50) == 1) {
				enemyGunMans.add(new EnemyGunMan(this.get_World_Width()
						* Screen_MainGame.nMultipleNumberSizeOfMap
						- rnd.nextInt(100), this.get_World_Height() / 2 - 10
						+ rnd.nextInt(20)));
			}
		}
	}

	private void generateEnemySwordMan() {
		if (enemySwordMans.size() < nGenerateEnemySword) {
			if (rnd.nextInt(50) == 1) {
				enemySwordMans.add(new EnemySwordMan(this.get_World_Width()
						* Screen_MainGame.nMultipleNumberSizeOfMap
						- rnd.nextInt(100), this.get_World_Height() / 2 - 10
						+ rnd.nextInt(20)));
			}
		}
	}

	private void update_leesunshin_skill(float deltaTime) {
		if (leesunsinSkillObjects.size() > 0)
			for (int i = 0; i < leesunsinSkillObjects.size(); i++)
				leesunsinSkillObjects.get(i).update(deltaTime);
	}

	private void update_enemy_bullet_AttackFiyld_monkAttack(float deltaTime) {
		if (enemyBullet.size() > 0)
			for (int i = 0; i < enemyBullet.size(); i++)
				enemyBullet.get(i).update(deltaTime);

		if (enemyAttackFiyld.size() > 0)
			for (int i = 0; i < enemyAttackFiyld.size(); i++)
				enemyAttackFiyld.get(i).update(deltaTime);

		if (energyBall.size() > 0)
			for (int i = 0; i < energyBall.size(); i++)
				energyBall.get(i).update(deltaTime);

		if (allyAttackFiyld.size() > 0)
			for (int i = 0; i < allyAttackFiyld.size(); i++)
				allyAttackFiyld.get(i).update(deltaTime);
	}

	private void update_enemy_gun(float deltaTime) {
		if (enemyGunMans.size() > 0)
			for (int i = 0; i < enemyGunMans.size(); i++)
				enemyGunMans.get(i).update(deltaTime);
	}

	private void update_enemy_sword(float deltaTime) {
		if (enemySwordMans.size() > 0)
			for (int i = 0; i < enemySwordMans.size(); i++)
				enemySwordMans.get(i).update(deltaTime);
	}

	private void update_middleBoss(float deltaTime) {
		if (enemyMiddleBoss01.size() > 0)
			for (int i = 0; i < enemyMiddleBoss01.size(); i++)
				enemyMiddleBoss01.get(i).update(deltaTime);
	}

	private void update_ally_monkMan(float deltaTime) {
		if (allyMonkMans.size() > 0)
			for (int i = 0; i < allyMonkMans.size(); i++)
				allyMonkMans.get(i).update(deltaTime);
	}

	private void update_ally_axeMan(float deltaTime) {
		if (allyAxeMans.size() > 0)
			for (int i = 0; i < allyAxeMans.size(); i++)
				allyAxeMans.get(i).update(deltaTime);
	}

	private void update_ally_archerMan(float deltaTime) {
		if (allyArchers.size() > 0)
			for (int i = 0; i < allyArchers.size(); i++)
				allyArchers.get(i).update(deltaTime);
	}

	private void update_ally_spearMan(float deltaTime) {
		if (allySpearMans.size() > 0)
			for (int i = 0; i < allySpearMans.size(); i++)
				allySpearMans.get(i).update(deltaTime);
	}

	private void update_final_boss(float deltaTime) {
		if (enemyFinalBoss.size() > 0)
			for (int i = 0; i < enemyFinalBoss.size(); i++)
				enemyFinalBoss.get(i).update(deltaTime);
	}

	private void update_LeeSunSin(float deltaTime) {
		mLeeSunSin.update(deltaTime);
	}

	// 충돌 검사 메서드를 모두 호출
	@Override
	protected void update_checkCollisions(float deltaTime) {
		allyCommanderToEnemy(deltaTime);
		enemySwordToTheAlly(deltaTime);
		enemyGunToTheAlly(deltaTime);
		enemyBulletToTheAlly(deltaTime);
		enemyAttackFiyldToTheAlly(deltaTime);
		enemyMiddleBossToAlly(deltaTime);
		enemyFinalBossToAlly(deltaTime);
		allyMonkToTheEnemy(deltaTime);
		allyAxeManToTheEnemy(deltaTime);
		allyArcherManToTheEnemy(deltaTime);
		allySpearManToTheEnemy(deltaTime);
		allyAttackfiyldToTheEnemy(deltaTime);
		energyBallToEnemy(deltaTime);
		leesunshinSkillToEnemy(deltaTime);

	}

	private void enemyFinalBossToAlly(float deltaTime) {
		int len01 = enemyFinalBoss.size();
		int len02 = allyMonkMans.size();
		int len03 = allyAxeMans.size();
		int len04 = allyArchers.size();
		int len05 = allySpearMans.size();

		for (int i = 0; i < len01; i++) {
			if (enemyFinalBoss.get(i).getStageFlag() != EnemyFinalBossMan.OBJ_D_DEATH) {
				enemyFinalBoss.get(i).setCheckExistEnemyIntheScope(false);

				// spearman
				for (int j = 0; j < len05; j++) {

					if (len01 <= 0 || len05 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							enemyFinalBoss.get(i).attackBounds,
							allySpearMans.get(j).bounds)) {
						enemyFinalBoss.get(i)
								.setCheckExistEnemyIntheScope(true);
						if (enemyFinalBoss.get(i).getStageFlag() == EnemyFinalBossMan.OBJ_D_STOP
								|| enemyFinalBoss.get(i).getStageFlag() == EnemyFinalBossMan.OBJ_D_LEFT) {
							int _nPersent = rnd.nextInt(5);

							if (_nPersent == 0)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_SKill01);
							else if (_nPersent == 1)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK01);
							else if (_nPersent == 2)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK02);
							else if (_nPersent == 3)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK03);
							else if (_nPersent == 4)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK04);


						}
					}
					if (enemyFinalBoss.get(i).isCheckExistEnemyIntheScope() == false
							&& enemyFinalBoss.get(i).getStageFlag() == EnemyFinalBossMan.OBJ_D_STOP) {
						enemyFinalBoss.get(i).setStateFlag(
								EnemyFinalBossMan.OBJ_D_LEFT);
					}
				}

				// archer
				for (int j = 0; j < len04; j++) {

					if (len01 <= 0 || len04 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							enemyFinalBoss.get(i).attackBounds,
							allyArchers.get(j).bounds)) {
						enemyFinalBoss.get(i)
								.setCheckExistEnemyIntheScope(true);
						if (enemyFinalBoss.get(i).getStageFlag() == EnemyFinalBossMan.OBJ_D_STOP
								|| enemyFinalBoss.get(i).getStageFlag() == EnemyFinalBossMan.OBJ_D_LEFT) {
							int _nPersent = rnd.nextInt(5);

							if (_nPersent == 0)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_SKill01);
							else if (_nPersent == 1)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK01);
							else if (_nPersent == 2)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK02);
							else if (_nPersent == 3)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK03);
							else if (_nPersent == 4)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK04);
						}
					}
					if (enemyFinalBoss.get(i).isCheckExistEnemyIntheScope() == false
							&& enemyFinalBoss.get(i).getStageFlag() == EnemyFinalBossMan.OBJ_D_STOP) {
						enemyFinalBoss.get(i).setStateFlag(
								EnemyFinalBossMan.OBJ_D_LEFT);
					}
				}

				// monk
				for (int j = 0; j < len02; j++) {

					if (len01 <= 0 || len02 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							enemyFinalBoss.get(i).attackBounds,
							allyMonkMans.get(j).bounds)) {
						enemyFinalBoss.get(i)
								.setCheckExistEnemyIntheScope(true);
						if (enemyFinalBoss.get(i).getStageFlag() == EnemyFinalBossMan.OBJ_D_STOP
								|| enemyFinalBoss.get(i).getStageFlag() == EnemyFinalBossMan.OBJ_D_LEFT) {
							int _nPersent = rnd.nextInt(5);

							if (_nPersent == 0)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_SKill01);
							else if (_nPersent == 1)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK01);
							else if (_nPersent == 2)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK02);
							else if (_nPersent == 3)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK03);
							else if (_nPersent == 4)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK04);
						}
					}
					if (enemyFinalBoss.get(i).isCheckExistEnemyIntheScope() == false
							&& enemyFinalBoss.get(i).getStageFlag() == EnemyFinalBossMan.OBJ_D_STOP) {
						enemyFinalBoss.get(i).setStateFlag(
								EnemyFinalBossMan.OBJ_D_LEFT);
					}
				}

				// axe
				for (int j = 0; j < len03; j++) {

					if (len01 <= 0 || len03 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							enemyFinalBoss.get(i).attackBounds,
							allyAxeMans.get(j).bounds)) {
						enemyFinalBoss.get(i)
								.setCheckExistEnemyIntheScope(true);
						if (enemyFinalBoss.get(i).getStageFlag() == EnemyFinalBossMan.OBJ_D_STOP
								|| enemyFinalBoss.get(i).getStageFlag() == EnemyFinalBossMan.OBJ_D_LEFT) {
							int _nPersent = rnd.nextInt(5);

							if (_nPersent == 0)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_SKill01);
							else if (_nPersent == 1)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK01);
							else if (_nPersent == 2)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK02);
							else if (_nPersent == 3)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK03);
							else if (_nPersent == 4)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK04);
						}
					}
					if (enemyFinalBoss.get(i).isCheckExistEnemyIntheScope() == false
							&& enemyFinalBoss.get(i).getStageFlag() == EnemyFinalBossMan.OBJ_D_STOP) {
						enemyFinalBoss.get(i).setStateFlag(
								EnemyFinalBossMan.OBJ_D_LEFT);
					}
				}

				// lee
				if (len01 > 0 && mLeeSunSin.getnHealthPoint() > 0) {
					if (Math_Overlap.overlapRectangles(
							enemyFinalBoss.get(i).attackBounds,
							mLeeSunSin.bounds)) {
						enemyFinalBoss.get(i)
								.setCheckExistEnemyIntheScope(true);
						if (enemyFinalBoss.get(i).getStageFlag() == EnemyFinalBossMan.OBJ_D_STOP
								|| enemyFinalBoss.get(i).getStageFlag() == EnemyFinalBossMan.OBJ_D_LEFT) {
							int _nPersent = rnd.nextInt(2);

							if (_nPersent == 0)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_SKill01);

							else if (_nPersent == 1)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK01);
							else if (_nPersent == 2)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK02);
							else if (_nPersent == 3)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK03);
							else if (_nPersent == 4)
								enemyFinalBoss.get(i).setStateFlag(
										EnemyFinalBossMan.OBJ_D_ATTAK04);
						}
					}
					if (enemyFinalBoss.get(i).isCheckExistEnemyIntheScope() == false
							&& enemyFinalBoss.get(i).getStageFlag() == EnemyFinalBossMan.OBJ_D_STOP) {
						enemyFinalBoss.get(i).setStateFlag(
								EnemyFinalBossMan.OBJ_D_LEFT);
					}
				}
			}
		}

	}

	private void enemyMiddleBossToAlly(float deltaTime) {
		int len01 = enemyMiddleBoss01.size();
		int len02 = allyMonkMans.size();
		int len03 = allyAxeMans.size();
		int len04 = allyArchers.size();
		int len05 = allySpearMans.size();

		for (int i = 0; i < len01; i++) {
			if (enemyMiddleBoss01.get(i).getStageFlag() != EnemyMiddleBossMan.OBJ_D_DEATH) {
				enemyMiddleBoss01.get(i).setCheckExistEnemyIntheScope(false);

				// spearman
				for (int j = 0; j < len05; j++) {

					if (len01 <= 0 || len05 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							enemyMiddleBoss01.get(i).attackBounds,
							allySpearMans.get(j).bounds)) {
						enemyMiddleBoss01.get(i).setCheckExistEnemyIntheScope(
								true);
						enemyMiddleBoss01.get(i).setStateFlag(
								EnemyMiddleBossMan.OBJ_D_ATTAK);

					}
					if (enemyMiddleBoss01.get(i).isCheckExistEnemyIntheScope() == false) {
						enemyMiddleBoss01.get(i).setStateFlag(
								EnemyMiddleBossMan.OBJ_D_LEFT);
					}
				}

				// archer
				for (int j = 0; j < len04; j++) {

					if (len01 <= 0 || len04 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							enemyMiddleBoss01.get(i).attackBounds,
							allyArchers.get(j).bounds)) {
						enemyMiddleBoss01.get(i).setCheckExistEnemyIntheScope(
								true);
						enemyMiddleBoss01.get(i).setStateFlag(
								EnemyMiddleBossMan.OBJ_D_ATTAK);

					}
					if (enemyMiddleBoss01.get(i).isCheckExistEnemyIntheScope() == false) {
						enemyMiddleBoss01.get(i).setStateFlag(
								EnemyMiddleBossMan.OBJ_D_LEFT);
					}
				}

				// monk
				for (int j = 0; j < len02; j++) {

					if (len01 <= 0 || len02 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							enemyMiddleBoss01.get(i).attackBounds,
							allyMonkMans.get(j).bounds)) {
						enemyMiddleBoss01.get(i).setCheckExistEnemyIntheScope(
								true);
						enemyMiddleBoss01.get(i).setStateFlag(
								EnemyMiddleBossMan.OBJ_D_ATTAK);

					}
					if (enemyMiddleBoss01.get(i).isCheckExistEnemyIntheScope() == false) {
						enemyMiddleBoss01.get(i).setStateFlag(
								EnemyMiddleBossMan.OBJ_D_LEFT);
					}
				}

				// axe
				for (int j = 0; j < len03; j++) {

					if (len01 <= 0 || len03 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							enemyMiddleBoss01.get(i).attackBounds,
							allyAxeMans.get(j).bounds)) {
						enemyMiddleBoss01.get(i).setCheckExistEnemyIntheScope(
								true);
						enemyMiddleBoss01.get(i).setStateFlag(
								EnemyMiddleBossMan.OBJ_D_ATTAK);

					}
					if (enemyMiddleBoss01.get(i).isCheckExistEnemyIntheScope() == false) {
						enemyMiddleBoss01.get(i).setStateFlag(
								EnemyMiddleBossMan.OBJ_D_LEFT);
					}
				}

				// lee
				if (len01 > 0 && mLeeSunSin.getnHealthPoint() > 0) {
					if (Math_Overlap.overlapRectangles(
							enemyMiddleBoss01.get(i).attackBounds,
							mLeeSunSin.bounds)) {
						enemyMiddleBoss01.get(i).setCheckExistEnemyIntheScope(
								true);
						enemyMiddleBoss01.get(i).setStateFlag(
								EnemyMiddleBossMan.OBJ_D_ATTAK);

					}
					if (enemyMiddleBoss01.get(i).isCheckExistEnemyIntheScope() == false) {
						enemyMiddleBoss01.get(i).setStateFlag(
								EnemyMiddleBossMan.OBJ_D_LEFT);
					}
				}

			}
		}
	}

	private void leesunshinSkillToEnemy(float deltaTime) {
		int len01 = leesunsinSkillObjects.size();
		int len02 = enemyGunMans.size();
		int len03 = enemySwordMans.size();
		int len04 = commander.size();
		int len05 = enemyMiddleBoss01.size();
		int len06 = enemyFinalBoss.size();

		for (int i = 0; i < len01; i++) {
			SkillObject skillObject = leesunsinSkillObjects.get(i);

			if (skillObject.isDead() == true) {
				leesunsinSkillObjects.remove(skillObject);
				len01 = leesunsinSkillObjects.size();
			}
		}

		// gun
		for (int j = 0; j < len02; j++) {
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len02 <= 0)
					return;

				SkillObject leesunShinSkill = leesunsinSkillObjects.get(i);

				if (enemyGunMans.size() > 0
						&& Math_Overlap.overlapRectangles(
								leesunShinSkill.bounds,
								enemyGunMans.get(j).bounds)) {

					if (leesunShinSkill.getSkillType() == SkillObject.GIGONGSINPO) {
						enemyGunMans.get(j).setnHealthPoint(
								enemyGunMans.get(j).getnHealthPoint()
										- SkillObject.GICONGSINPODAMAGE);
					} else if (leesunShinSkill.getSkillType() == SkillObject.CROSSCUTTING) {
						enemyGunMans.get(j).setnHealthPoint(
								enemyGunMans.get(j).getnHealthPoint()
										- SkillObject.CROSSCUTTINGDAMAGE);
					} else if (leesunShinSkill.getSkillType() == SkillObject.GENERATEMARINE) {
						enemyGunMans.get(j).setnHealthPoint(
								enemyGunMans.get(j).getnHealthPoint()
										- SkillObject.GENERATEMARINEDANAGE);
					}

					enemyGunMans.get(j).getEnemyHpBar().setVisible(true);

					if (enemyGunMans.get(j).getnHealthPoint() < 0
							&& (enemyGunMans.get(j).getStageFlag() != EnemyGunMan.OBJ_D_RUNAWAY && enemyGunMans
									.get(j).getStageFlag() != EnemyGunMan.OBJ_D_DEATH)) {
						hpBar.remove(enemyGunMans.get(j).getEnemyHpBar());

						nchoice = rnd.nextInt(10);
						if (nchoice < PARCANTAGERUNAWAY)
							enemyGunMans.get(j).setStateFlag(
									EnemyGunMan.OBJ_D_RUNAWAY);
						else {
							enemyGunMans.get(j).setStateFlag(
									EnemyGunMan.OBJ_D_DEATH);
					//		Manage_Assets
				//					.playSound(Manage_Assets.soundEnemyDie);
						}

						MainGame_Manager.nStageScore += UnitInformation.ENEMY.GunMan.Exp
								+ rnd.nextInt(50);
						Manage_Settings.nStageCoin += UnitInformation.ENEMY.GunMan.Money
								+ rnd.nextInt(50);
					}

					if (leesunShinSkill.getSkillType() == SkillObject.GIGONGSINPO) {
						leesunsinSkillObjects.remove(leesunShinSkill);
						len01 = leesunsinSkillObjects.size();
					} else
						leesunShinSkill.setnCount();
				}
			}

		}

		// sword
		for (int j = 0; j < len03; j++) {
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len03 <= 0)
					return;

				SkillObject leesunShinSkill = leesunsinSkillObjects.get(i);

				if (enemySwordMans.size() > 0
						&& Math_Overlap.overlapRectangles(
								leesunShinSkill.bounds,
								enemySwordMans.get(j).bounds)) {

					if (leesunShinSkill.getSkillType() == SkillObject.GIGONGSINPO)
						enemySwordMans.get(j).setnHealthPoint(
								enemySwordMans.get(j).getnHealthPoint()
										- SkillObject.GICONGSINPODAMAGE);
					else if (leesunShinSkill.getSkillType() == SkillObject.CROSSCUTTING)
						enemySwordMans.get(j).setnHealthPoint(
								enemySwordMans.get(j).getnHealthPoint()
										- SkillObject.CROSSCUTTINGDAMAGE);
					else if (leesunShinSkill.getSkillType() == SkillObject.GENERATEMARINE)
						enemySwordMans.get(j).setnHealthPoint(
								enemySwordMans.get(j).getnHealthPoint()
										- SkillObject.GENERATEMARINEDANAGE);

					enemySwordMans.get(j).getEnemyHpBar().setVisible(true);

					if (enemySwordMans.get(j).getnHealthPoint() < 0
							&& (enemySwordMans.get(j).getStageFlag() != EnemySwordMan.OBJ_D_RUNAWAY && enemySwordMans
									.get(j).getStageFlag() != EnemySwordMan.OBJ_D_DEATH)) {
						hpBar.remove(enemySwordMans.get(j).getEnemyHpBar());

						nchoice = rnd.nextInt(10);
						if (nchoice < PARCANTAGERUNAWAY)
							enemySwordMans.get(j).setStateFlag(
									AllyAxeMan.OBJ_D_RUNAWAY);
						else {
						//	Manage_Assets
						//			.playSound(Manage_Assets.);
							enemySwordMans.get(j).setStateFlag(
									AllyAxeMan.OBJ_D_DEATH);
						}

						MainGame_Manager.nStageScore += UnitInformation.ENEMY.SwordMan.Exp
								+ rnd.nextInt(25);
						Manage_Settings.nStageCoin += UnitInformation.ENEMY.SwordMan.Money
								+ rnd.nextInt(25);
					}

					if (leesunShinSkill.getSkillType() == SkillObject.GIGONGSINPO) {
						leesunsinSkillObjects.remove(leesunShinSkill);
						len01 = leesunsinSkillObjects.size();
					} else
						leesunShinSkill.setnCount();
				}
			}

		}

		// middleBoss
		for (int j = 0; j < len05; j++) {
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len05 <= 0)
					return;

				SkillObject leesunShinSkill = leesunsinSkillObjects.get(i);

				if (enemyMiddleBoss01.size() > 0
						&& Math_Overlap.overlapRectangles(
								leesunShinSkill.bounds,
								enemyMiddleBoss01.get(j).bounds)) {

					if (leesunShinSkill.getSkillType() == SkillObject.GIGONGSINPO)
						enemyMiddleBoss01.get(j).setnHealthPoint(
								enemyMiddleBoss01.get(j).getnHealthPoint()
										- SkillObject.GICONGSINPODAMAGE);
					else if (leesunShinSkill.getSkillType() == SkillObject.CROSSCUTTING)
						enemyMiddleBoss01.get(j).setnHealthPoint(
								enemyMiddleBoss01.get(j).getnHealthPoint()
										- SkillObject.CROSSCUTTINGDAMAGE);
					else if (leesunShinSkill.getSkillType() == SkillObject.GENERATEMARINE)
						enemyMiddleBoss01.get(j).setnHealthPoint(
								enemyMiddleBoss01.get(j).getnHealthPoint()
										- SkillObject.GENERATEMARINEDANAGE);

					enemyMiddleBoss01.get(j).getEnemyHpBar().setVisible(true);

					if (enemyMiddleBoss01.get(j).getnHealthPoint() < 0
							&& (enemyMiddleBoss01.get(j).getStageFlag() != EnemyMiddleBossMan.OBJ_D_DEATH)) {
						hpBar.remove(enemyMiddleBoss01.get(j).getEnemyHpBar());
						enemyMiddleBoss01.get(j).setStateFlag(
								AllyAxeMan.OBJ_D_DEATH);
					//	Manage_Assets.playSound(Manage_Assets.soundEnemyDie);
						MainGame_Manager.nStageScore += UnitInformation.ENEMY.MiddleBoss.Exp
								+ rnd.nextInt(25);
						Manage_Settings.nStageCoin += UnitInformation.ENEMY.MiddleBoss.Money
								+ rnd.nextInt(25);
					}

					if (leesunShinSkill.getSkillType() == SkillObject.GIGONGSINPO) {
						leesunsinSkillObjects.remove(leesunShinSkill);
						len01 = leesunsinSkillObjects.size();
					} else
						leesunShinSkill.setnCount();
				}
			}

		}

		// finalBoss
		for (int j = 0; j < len06; j++) {
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len06 <= 0)
					return;

				SkillObject leesunShinSkill = leesunsinSkillObjects.get(i);

				if (enemyFinalBoss.size() > 0
						&& Math_Overlap.overlapRectangles(
								leesunShinSkill.bounds,
								enemyFinalBoss.get(j).bounds)) {

					if (leesunShinSkill.getSkillType() == SkillObject.GIGONGSINPO)
						enemyFinalBoss.get(j).setnHealthPoint(
								enemyFinalBoss.get(j).getnHealthPoint()
										- SkillObject.GICONGSINPODAMAGE);
					else if (leesunShinSkill.getSkillType() == SkillObject.CROSSCUTTING)
						enemyFinalBoss.get(j).setnHealthPoint(
								enemyFinalBoss.get(j).getnHealthPoint()
										- SkillObject.CROSSCUTTINGDAMAGE);
					else if (leesunShinSkill.getSkillType() == SkillObject.GENERATEMARINE)
						enemyFinalBoss.get(j).setnHealthPoint(
								enemyFinalBoss.get(j).getnHealthPoint()
										- SkillObject.GENERATEMARINEDANAGE);

					enemyFinalBoss.get(j).getEnemyHpBar().setVisible(true);

					if (enemyFinalBoss.get(j).getnHealthPoint() < 0
							&& (enemyFinalBoss.get(j).getStageFlag() != EnemyFinalBossMan.OBJ_D_DEATH)) {
						hpBar.remove(enemyFinalBoss.get(j).getEnemyHpBar());
						enemyFinalBoss.get(j).setStateFlag(
								AllyAxeMan.OBJ_D_DEATH);
					//	Manage_Assets.playSound(Manage_Assets.soundEnemyDie);
						MainGame_Manager.nStageScore += UnitInformation.ENEMY.MiddleBoss.Exp
								+ rnd.nextInt(25);
						Manage_Settings.nStageCoin += UnitInformation.ENEMY.MiddleBoss.Money
								+ rnd.nextInt(25);
					}

					if (leesunShinSkill.getSkillType() == SkillObject.GIGONGSINPO) {
						leesunsinSkillObjects.remove(leesunShinSkill);
						len01 = leesunsinSkillObjects.size();
					} else
						leesunShinSkill.setnCount();
				}
			}

		}

		// command
		for (int j = 0; j < len04; j++)
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len04 <= 0)
					return;

				SkillObject leesunShinSkill = leesunsinSkillObjects.get(i);

				if (Math_Overlap.overlapRectangles(leesunShinSkill.bounds,
						commander.get(j).bounds)) {

					if (leesunShinSkill.getSkillType() == SkillObject.GIGONGSINPO)
						commander.get(j).setnHealthPoint(
								commander.get(j).getnHealthPoint()
										- SkillObject.GICONGSINPODAMAGE);
					else if (leesunShinSkill.getSkillType() == SkillObject.CROSSCUTTING)
						commander.get(j).setnHealthPoint(
								commander.get(j).getnHealthPoint()
										- SkillObject.CROSSCUTTINGDAMAGE);
					else if (leesunShinSkill.getSkillType() == SkillObject.GENERATEMARINE)
						commander.get(j).setnHealthPoint(
								commander.get(j).getnHealthPoint()
										- SkillObject.GENERATEMARINEDANAGE);

					if (commander.get(j).getnHealthPoint() < 0) {
						{
							Screen_MainGame.state = Screen_MainGame.GAME_LEVEL_END;
							Manage_Assets
									.PlayMusic(Manage_Assets.musicGameClear);
						}
						// commander.remove(commander.get(j));
						// len03 = commander.size();
					}

					if (leesunShinSkill.getSkillType() == SkillObject.GIGONGSINPO) {
						leesunsinSkillObjects.remove(leesunShinSkill);
						len01 = leesunsinSkillObjects.size();
					} else
						leesunShinSkill.setnCount();
				}
			}

	}

	private void allyAttackfiyldToTheEnemy(float deltaTime) {
		int len01 = allyAttackFiyld.size();
		int len02 = enemyGunMans.size();
		int len03 = enemySwordMans.size();
		int len04 = commander.size();
		int len05 = enemyMiddleBoss01.size();
		int len06 = enemyFinalBoss.size();

		for (int i = 0; i < len01; i++) {
			AttackFiyld enAttackFiyld = allyAttackFiyld.get(i);

			if (enAttackFiyld.isDead() == true) {
				allyAttackFiyld.remove(enAttackFiyld);
				len01 = allyAttackFiyld.size();
			}
		}
		// gun
		for (int j = 0; j < len02; j++) {
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len02 <= 0)
					return;

				AttackFiyld mAllyFiyld = allyAttackFiyld.get(i);

				if (enemyGunMans.size() > 0
						&& Math_Overlap.overlapRectangles(mAllyFiyld.bounds,
								enemyGunMans.get(j).bounds)) {

					if (mAllyFiyld.getnSkillType() == AttackFiyld.DEFUALT)
						enemyGunMans
								.get(j)
								.setnHealthPoint(
										enemyGunMans.get(j).getnHealthPoint()
												- (int) ((int) AxeMan
														.Bonus(Manage_Settings.nUnitAxeManLv)
														* AxeMan.DamageBasic + AxeMan.DamageBasic));
					else if (mAllyFiyld.getnSkillType() == AttackFiyld.TORNADOATTACK)
						enemyGunMans.get(j).setnHealthPoint(
								enemyGunMans.get(j).getnHealthPoint()
										- AttackFiyld.TORNADOATTACKDAMAGE);
					else if (mAllyFiyld.getnSkillType() == AttackFiyld.POWERATTACK)
						enemyGunMans.get(j).setnHealthPoint(
								enemyGunMans.get(j).getnHealthPoint()
										- AttackFiyld.POWERATTACKDAMAGE);
					else if (mAllyFiyld.getnSkillType() == AttackFiyld.BASICATTACK)
						enemyGunMans.get(j).setnHealthPoint(
								enemyGunMans.get(j).getnHealthPoint()
										- AttackFiyld.BASICATTACKDAMAGE);
					else
						enemyGunMans
								.get(j)
								.setnHealthPoint(
										enemyGunMans.get(j).getnHealthPoint()
												- (int) ((int) SpearMan
														.Bonus(Manage_Settings.nUnitSpearLv)
														* SpearMan.DamageBasic + SpearMan.DamageBasic));

					enemyGunMans.get(j).getEnemyHpBar().setVisible(true);

					if (enemyGunMans.get(j).getnHealthPoint() < 0
							&& (enemyGunMans.get(j).getStageFlag() != EnemyGunMan.OBJ_D_RUNAWAY && enemyGunMans
									.get(j).getStageFlag() != EnemyGunMan.OBJ_D_DEATH)) {
						hpBar.remove(enemyGunMans.get(j).getEnemyHpBar());
						nchoice = rnd.nextInt(10);
						if (nchoice < PARCANTAGERUNAWAY)
							enemyGunMans.get(j).setStateFlag(
									EnemyGunMan.OBJ_D_RUNAWAY);
						else {
						//	Manage_Assets
						//			.playSound(Manage_Assets.soundEnemyDie);
							enemyGunMans.get(j).setStateFlag(
									EnemyGunMan.OBJ_D_DEATH);
						}

						MainGame_Manager.nStageScore += UnitInformation.ENEMY.GunMan.Exp
								+ rnd.nextInt(50);
						Manage_Settings.nStageCoin += UnitInformation.ENEMY.GunMan.Money
								+ rnd.nextInt(50);
					}

					allyAttackFiyld.remove(mAllyFiyld);
					len01 = allyAttackFiyld.size();
				}
			}

		}

		// sword
		for (int j = 0; j < len03; j++) {
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len03 <= 0)
					return;

				AttackFiyld mAllyFiyld = allyAttackFiyld.get(i);

				if (enemySwordMans.size() > 0
						&& Math_Overlap.overlapRectangles(mAllyFiyld.bounds,
								enemySwordMans.get(j).bounds)) {

					if (mAllyFiyld.getnSkillType() == AttackFiyld.DEFUALT)
						enemySwordMans
								.get(j)
								.setnHealthPoint(
										enemySwordMans.get(j).getnHealthPoint()
												- (int) ((int) AxeMan
														.Bonus(Manage_Settings.nUnitAxeManLv)
														* AxeMan.DamageBasic + AxeMan.DamageBasic));
					else if (mAllyFiyld.getnSkillType() == AttackFiyld.TORNADOATTACK)
						enemySwordMans.get(j).setnHealthPoint(
								enemySwordMans.get(j).getnHealthPoint()
										- AttackFiyld.TORNADOATTACKDAMAGE);
					else if (mAllyFiyld.getnSkillType() == AttackFiyld.POWERATTACK)
						enemySwordMans.get(j).setnHealthPoint(
								enemySwordMans.get(j).getnHealthPoint()
										- AttackFiyld.POWERATTACKDAMAGE);
					else if (mAllyFiyld.getnSkillType() == AttackFiyld.BASICATTACK)
						enemySwordMans.get(j).setnHealthPoint(
								enemySwordMans.get(j).getnHealthPoint()
										- AttackFiyld.BASICATTACKDAMAGE);
					else
						enemySwordMans
								.get(j)
								.setnHealthPoint(
										enemySwordMans.get(j).getnHealthPoint()
												- (int) ((int) SpearMan
														.Bonus(Manage_Settings.nUnitSpearLv)
														* SpearMan.DamageBasic + SpearMan.DamageBasic));

					enemySwordMans.get(j).getEnemyHpBar().setVisible(true);

					if (enemySwordMans.get(j).getnHealthPoint() < 0
							&& (enemySwordMans.get(j).getStageFlag() != EnemySwordMan.OBJ_D_RUNAWAY && enemySwordMans
									.get(j).getStageFlag() != EnemySwordMan.OBJ_D_DEATH)) {
						hpBar.remove(enemySwordMans.get(j).getEnemyHpBar());

						nchoice = rnd.nextInt(10);
						if (nchoice < PARCANTAGERUNAWAY)
							enemySwordMans.get(j).setStateFlag(
									AllyAxeMan.OBJ_D_RUNAWAY);
						else {
					//		Manage_Assets
					//				.playSound(Manage_Assets.soundEnemyDie);
							enemySwordMans.get(j).setStateFlag(
									AllyAxeMan.OBJ_D_DEATH);
						}
						MainGame_Manager.nStageScore += UnitInformation.ENEMY.SwordMan.Exp
								+ rnd.nextInt(25);
						Manage_Settings.nStageCoin += UnitInformation.ENEMY.SwordMan.Money
								+ rnd.nextInt(25);
					}

					allyAttackFiyld.remove(mAllyFiyld);
					len01 = allyAttackFiyld.size();
				}
			}
		}

		// final boss
		for (int j = 0; j < len06; j++) {
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len06 <= 0)
					return;

				AttackFiyld mAllyFiyld = allyAttackFiyld.get(i);

				if (enemyFinalBoss.size() > 0
						&& Math_Overlap.overlapRectangles(mAllyFiyld.bounds,
								enemyFinalBoss.get(j).bounds)) {

					if (mAllyFiyld.getnSkillType() == AttackFiyld.DEFUALT)
						enemyFinalBoss
								.get(j)
								.setnHealthPoint(
										enemyFinalBoss.get(j).getnHealthPoint()
												- (int) ((int) AxeMan
														.Bonus(Manage_Settings.nUnitAxeManLv)
														* AxeMan.DamageBasic + AxeMan.DamageBasic));
					else if (mAllyFiyld.getnSkillType() == AttackFiyld.TORNADOATTACK)
						enemyFinalBoss.get(j).setnHealthPoint(
								enemyFinalBoss.get(j).getnHealthPoint()
										- AttackFiyld.TORNADOATTACKDAMAGE);
					else if (mAllyFiyld.getnSkillType() == AttackFiyld.POWERATTACK)
						enemyFinalBoss.get(j).setnHealthPoint(
								enemyFinalBoss.get(j).getnHealthPoint()
										- AttackFiyld.POWERATTACKDAMAGE);
					else if (mAllyFiyld.getnSkillType() == AttackFiyld.BASICATTACK)
						enemyFinalBoss.get(j).setnHealthPoint(
								enemyFinalBoss.get(j).getnHealthPoint()
										- AttackFiyld.BASICATTACKDAMAGE);
					else
						enemyFinalBoss
								.get(j)
								.setnHealthPoint(
										enemyFinalBoss.get(j).getnHealthPoint()
												- (int) ((int) SpearMan
														.Bonus(Manage_Settings.nUnitSpearLv)
														* SpearMan.DamageBasic + SpearMan.DamageBasic));

					enemyFinalBoss.get(j).getEnemyHpBar().setVisible(true);

					if (enemyFinalBoss.get(j).getnHealthPoint() < 0
							&& (enemyFinalBoss.get(j).getStageFlag() != EnemyFinalBossMan.OBJ_D_DEATH)) {
						hpBar.remove(enemyFinalBoss.get(j).getEnemyHpBar());
						enemyFinalBoss.get(j).setStateFlag(
								AllyAxeMan.OBJ_D_DEATH);
					//	Manage_Assets.playSound(Manage_Assets.soundEnemyDie);
						MainGame_Manager.nStageScore += UnitInformation.ENEMY.MiddleBoss.Exp
								+ rnd.nextInt(25);
						Manage_Settings.nStageCoin += UnitInformation.ENEMY.MiddleBoss.Money
								+ rnd.nextInt(25);
					}

					allyAttackFiyld.remove(mAllyFiyld);
					len01 = allyAttackFiyld.size();
				}
			}
		}

		// middle boss
		for (int j = 0; j < len05; j++) {
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len05 <= 0)
					return;

				AttackFiyld mAllyFiyld = allyAttackFiyld.get(i);

				if (enemyMiddleBoss01.size() > 0
						&& Math_Overlap.overlapRectangles(mAllyFiyld.bounds,
								enemyMiddleBoss01.get(j).bounds)) {

					if (mAllyFiyld.getnSkillType() == AttackFiyld.DEFUALT)
						enemyMiddleBoss01
								.get(j)
								.setnHealthPoint(
										enemyMiddleBoss01.get(j)
												.getnHealthPoint()
												- (int) ((int) AxeMan
														.Bonus(Manage_Settings.nUnitAxeManLv)
														* AxeMan.DamageBasic + AxeMan.DamageBasic));
					else if (mAllyFiyld.getnSkillType() == AttackFiyld.TORNADOATTACK)
						enemyMiddleBoss01.get(j).setnHealthPoint(
								enemyMiddleBoss01.get(j).getnHealthPoint()
										- AttackFiyld.TORNADOATTACKDAMAGE);
					else if (mAllyFiyld.getnSkillType() == AttackFiyld.POWERATTACK)
						enemyMiddleBoss01.get(j).setnHealthPoint(
								enemyMiddleBoss01.get(j).getnHealthPoint()
										- AttackFiyld.POWERATTACKDAMAGE);
					else if (mAllyFiyld.getnSkillType() == AttackFiyld.BASICATTACK)
						enemyMiddleBoss01.get(j).setnHealthPoint(
								enemyMiddleBoss01.get(j).getnHealthPoint()
										- AttackFiyld.BASICATTACKDAMAGE);
					else
						enemyMiddleBoss01
								.get(j)
								.setnHealthPoint(
										enemyMiddleBoss01.get(j)
												.getnHealthPoint()
												- (int) ((int) SpearMan
														.Bonus(Manage_Settings.nUnitSpearLv)
														* SpearMan.DamageBasic + SpearMan.DamageBasic));

					enemyMiddleBoss01.get(j).getEnemyHpBar().setVisible(true);

					if (enemyMiddleBoss01.get(j).getnHealthPoint() < 0
							&& (enemyMiddleBoss01.get(j).getStageFlag() != EnemyMiddleBossMan.OBJ_D_DEATH)) {
						hpBar.remove(enemyMiddleBoss01.get(j).getEnemyHpBar());
						enemyMiddleBoss01.get(j).setStateFlag(
								AllyAxeMan.OBJ_D_DEATH);
					//	Manage_Assets.playSound(Manage_Assetie);
						MainGame_Manager.nStageScore += UnitInformation.ENEMY.MiddleBoss.Exp
								+ rnd.nextInt(25);
						Manage_Settings.nStageCoin += UnitInformation.ENEMY.MiddleBoss.Money
								+ rnd.nextInt(25);
					}

					allyAttackFiyld.remove(mAllyFiyld);
					len01 = allyAttackFiyld.size();
				}
			}
		}

		// command
		for (int j = 0; j < len04; j++)
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len04 <= 0)
					return;

				AttackFiyld mAllyFiyld = allyAttackFiyld.get(i);

				if (Math_Overlap.overlapRectangles(mAllyFiyld.bounds,
						commander.get(j).bounds)) {

					if (mAllyFiyld.getnSkillType() == AttackFiyld.DEFUALT)
						commander
								.get(j)
								.setnHealthPoint(
										commander.get(j).getnHealthPoint()
												- (int) ((int) AxeMan
														.Bonus(Manage_Settings.nUnitAxeManLv)
														* AxeMan.DamageBasic + AxeMan.DamageBasic));
					else if (mAllyFiyld.getnSkillType() == AttackFiyld.TORNADOATTACK)
						commander.get(j).setnHealthPoint(
								commander.get(j).getnHealthPoint()
										- AttackFiyld.TORNADOATTACKDAMAGE);
					else if (mAllyFiyld.getnSkillType() == AttackFiyld.POWERATTACK)
						commander.get(j).setnHealthPoint(
								commander.get(j).getnHealthPoint()
										- AttackFiyld.POWERATTACKDAMAGE);
					else if (mAllyFiyld.getnSkillType() == AttackFiyld.BASICATTACK)
						commander.get(j).setnHealthPoint(
								commander.get(j).getnHealthPoint()
										- AttackFiyld.BASICATTACKDAMAGE);
					else
						commander
								.get(j)
								.setnHealthPoint(
										commander.get(j).getnHealthPoint()
												- (int) ((int) SpearMan
														.Bonus(Manage_Settings.nUnitSpearLv)
														* SpearMan.DamageBasic + SpearMan.DamageBasic));

					if (commander.get(j).getnHealthPoint() < 0) {
						Screen_MainGame.state = Screen_MainGame.GAME_LEVEL_END;
						Manage_Assets.PlayMusic(Manage_Assets.musicGameClear);
					}

					allyAttackFiyld.remove(mAllyFiyld);
					len01 = allyAttackFiyld.size();
				}
			}

	}

	private void allyArcherManToTheEnemy(float deltaTime) {
		int len01 = allyArchers.size();
		int len02 = enemyGunMans.size();
		int len03 = enemySwordMans.size();
		int len04 = enemyMiddleBoss01.size();
		int len05 = enemyFinalBoss.size();

		for (int i = 0; i < len01; i++) {
			if (allyArchers.get(i).getStageFlag() != AllyArcherMan.OBJ_D_DEATH) {
				allyArchers.get(i).setCheckExistEnemyIntheScope(false);

				// enemygun
				for (int j = 0; j < len02; j++) {

					if (len01 <= 0 || len02 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							allyArchers.get(i).attackBounds,
							enemyGunMans.get(j).bounds)) {
						allyArchers.get(i).setCheckExistEnemyIntheScope(true);
						allyArchers.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_ATTAK);
					}

					if (allyArchers.get(i).isCheckExistEnemyIntheScope() == false) {
						allyArchers.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_RIGHT);
					}
				}

				// enemygun
				for (int j = 0; j < len03; j++) {

					if (len01 <= 0 || len03 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							allyArchers.get(i).attackBounds,
							enemySwordMans.get(j).bounds)) {
						allyArchers.get(i).setCheckExistEnemyIntheScope(true);
						allyArchers.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_ATTAK);
					}

					if (allyArchers.get(i).isCheckExistEnemyIntheScope() == false) {
						allyArchers.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_RIGHT);
					}
				}

				// enemyFinalBoss
				for (int j = 0; j < len05; j++) {

					if (len01 <= 0 || len05 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							allyArchers.get(i).attackBounds,
							enemyFinalBoss.get(j).bounds)) {
						allyArchers.get(i).setCheckExistEnemyIntheScope(true);
						allyArchers.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_ATTAK);
					}

					if (allyArchers.get(i).isCheckExistEnemyIntheScope() == false) {
						allyArchers.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_RIGHT);
					}
				}

				// enemyMiddleBoss
				for (int j = 0; j < len04; j++) {

					if (len01 <= 0 || len04 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							allyArchers.get(i).attackBounds,
							enemyMiddleBoss01.get(j).bounds)) {
						allyArchers.get(i).setCheckExistEnemyIntheScope(true);
						allyArchers.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_ATTAK);
					}

					if (allyArchers.get(i).isCheckExistEnemyIntheScope() == false) {
						allyArchers.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_RIGHT);
					}
				}
			}
		}
	}

	private void allySpearManToTheEnemy(float deltaTime) {
		int len01 = allySpearMans.size();
		int len02 = enemyGunMans.size();
		int len03 = enemySwordMans.size();
		int len04 = enemyMiddleBoss01.size();
		int len05 = enemyFinalBoss.size();

		for (int i = 0; i < len01; i++) {
			if (allySpearMans.get(i).getStageFlag() != AllySpearMan.OBJ_D_DEATH) {
				allySpearMans.get(i).setCheckExistEnemyIntheScope(false);

				// gun
				for (int j = 0; j < len02; j++) {

					if (len01 <= 0 || len02 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							allySpearMans.get(i).attackBounds,
							enemyGunMans.get(j).bounds)) {
						allySpearMans.get(i).setCheckExistEnemyIntheScope(true);
						allySpearMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_ATTAK);
					}
					if (allySpearMans.get(i).isCheckExistEnemyIntheScope() == false) {
						allySpearMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_RIGHT);
					}
				}

				// sword
				for (int j = 0; j < len03; j++) {

					if (len01 <= 0 || len03 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							allySpearMans.get(i).attackBounds,
							enemySwordMans.get(j).bounds)) {
						allySpearMans.get(i).setCheckExistEnemyIntheScope(true);
						allySpearMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_ATTAK);
					}
					if (allySpearMans.get(i).isCheckExistEnemyIntheScope() == false) {
						allySpearMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_RIGHT);
					}
				}

				// finalBoss
				for (int j = 0; j < len05; j++) {

					if (len01 <= 0 || len05 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							allySpearMans.get(i).attackBounds,
							enemyFinalBoss.get(j).bounds)) {
						allySpearMans.get(i).setCheckExistEnemyIntheScope(true);
						allySpearMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_ATTAK);
					}
					if (allySpearMans.get(i).isCheckExistEnemyIntheScope() == false) {
						allySpearMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_RIGHT);
					}
				}

				// middleboss
				for (int j = 0; j < len04; j++) {

					if (len01 <= 0 || len04 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							allySpearMans.get(i).attackBounds,
							enemyMiddleBoss01.get(j).bounds)) {
						allySpearMans.get(i).setCheckExistEnemyIntheScope(true);
						allySpearMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_ATTAK);
					}
					if (allySpearMans.get(i).isCheckExistEnemyIntheScope() == false) {
						allySpearMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_RIGHT);
					}
				}
			}
		}
	}

	private void allyAxeManToTheEnemy(float deltaTime) {
		int len01 = allyAxeMans.size();
		int len02 = enemyGunMans.size();
		int len03 = enemySwordMans.size();
		int len04 = enemyMiddleBoss01.size();
		int len05 = enemyFinalBoss.size();

		for (int i = 0; i < len01; i++) {
			if (allyAxeMans.get(i).getStageFlag() != AllyAxeMan.OBJ_D_DEATH) {
				allyAxeMans.get(i).setCheckExistEnemyIntheScope(false);

				// gun
				for (int j = 0; j < len02; j++) {

					if (len01 <= 0 || len02 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							allyAxeMans.get(i).attackBounds,
							enemyGunMans.get(j).bounds)) {
						allyAxeMans.get(i).setCheckExistEnemyIntheScope(true);
						allyAxeMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_ATTAK);
					}
					if (allyAxeMans.get(i).isCheckExistEnemyIntheScope() == false) {
						allyAxeMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_RIGHT);
					}
				}

				// sword
				for (int j = 0; j < len03; j++) {

					if (len01 <= 0 || len03 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							allyAxeMans.get(i).attackBounds,
							enemySwordMans.get(j).bounds)) {
						allyAxeMans.get(i).setCheckExistEnemyIntheScope(true);
						allyAxeMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_ATTAK);
					}
					if (allyAxeMans.get(i).isCheckExistEnemyIntheScope() == false) {
						allyAxeMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_RIGHT);
					}
				}

				// finalboss
				for (int j = 0; j < len05; j++) {

					if (len01 <= 0 || len05 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							allyAxeMans.get(i).attackBounds,
							enemyFinalBoss.get(j).bounds)) {
						allyAxeMans.get(i).setCheckExistEnemyIntheScope(true);
						allyAxeMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_ATTAK);
					}
					if (allyAxeMans.get(i).isCheckExistEnemyIntheScope() == false) {
						allyAxeMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_RIGHT);
					}
				}

				// middleboss
				for (int j = 0; j < len04; j++) {

					if (len01 <= 0 || len04 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							allyAxeMans.get(i).attackBounds,
							enemyMiddleBoss01.get(j).bounds)) {
						allyAxeMans.get(i).setCheckExistEnemyIntheScope(true);
						allyAxeMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_ATTAK);
					}
					if (allyAxeMans.get(i).isCheckExistEnemyIntheScope() == false) {
						allyAxeMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_RIGHT);
					}
				}
			}
		}
	}

	private void allyMonkToTheEnemy(float deltaTime) {
		int len01 = allyMonkMans.size();
		int len02 = enemyGunMans.size();
		int len03 = enemySwordMans.size();
		int len04 = enemyMiddleBoss01.size();
		int len05 = enemyFinalBoss.size();

		for (int i = 0; i < len01; i++) {
			if (allyMonkMans.get(i).getStageFlag() != AllyMonkMan.OBJ_D_DEATH) {
				allyMonkMans.get(i).setCheckExistEnemyIntheScope(false);

				// enemygun
				for (int j = 0; j < len02; j++) {

					if (len01 <= 0 || len02 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							allyMonkMans.get(i).attackBounds,
							enemyGunMans.get(j).bounds)) {
						allyMonkMans.get(i).setCheckExistEnemyIntheScope(true);
						allyMonkMans.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_ATTAK);
					}

					if (allyMonkMans.get(i).isCheckExistEnemyIntheScope() == false) {
						allyMonkMans.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_RIGHT);
					}
				}

				// enemygun
				for (int j = 0; j < len03; j++) {

					if (len01 <= 0 || len03 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							allyMonkMans.get(i).attackBounds,
							enemySwordMans.get(j).bounds)) {
						allyMonkMans.get(i).setCheckExistEnemyIntheScope(true);
						allyMonkMans.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_ATTAK);
					}

					if (allyMonkMans.get(i).isCheckExistEnemyIntheScope() == false) {
						allyMonkMans.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_RIGHT);
					}
				}

				// enemyFinalBoss
				for (int j = 0; j < len05; j++) {

					if (len01 <= 0 || len05 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							allyMonkMans.get(i).attackBounds,
							enemyFinalBoss.get(j).bounds)) {
						allyMonkMans.get(i).setCheckExistEnemyIntheScope(true);
						allyMonkMans.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_ATTAK);
					}

					if (allyMonkMans.get(i).isCheckExistEnemyIntheScope() == false) {
						allyMonkMans.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_RIGHT);
					}
				}

				// enemyMiddleBoss
				for (int j = 0; j < len04; j++) {

					if (len01 <= 0 || len04 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							allyMonkMans.get(i).attackBounds,
							enemyMiddleBoss01.get(j).bounds)) {
						allyMonkMans.get(i).setCheckExistEnemyIntheScope(true);
						allyMonkMans.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_ATTAK);
					}

					if (allyMonkMans.get(i).isCheckExistEnemyIntheScope() == false) {
						allyMonkMans.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_RIGHT);
					}
				}
			}
		}
	}

	private void allyCommanderToEnemy(float deltaTime) {

		int len01 = enemyGunMans.size();
		int len02 = enemySwordMans.size();
		int len03 = commander.size();
		int len04 = enemyMiddleBoss01.size();
		int len05 = enemyFinalBoss.size();

		// sword
		for (int i = 0; i < len02; i++) {
			if (enemySwordMans.get(i).getStageFlag() != EnemySwordMan.OBJ_D_DEATH
					&& enemySwordMans.get(i).getStageFlag() != EnemySwordMan.OBJ_D_RUNAWAY) {
				enemySwordMans.get(i).setCheckExistEnemyIntheScope(false);
				for (int j = 0; j < len03; j++) {

					if (len02 <= 0 || len03 <= 0)
						continue;

					if (commander.get(j).getFlag() == Commander.OURFORCE)
						if (Math_Overlap.overlapRectangles(
								enemySwordMans.get(i).attackBounds,
								commander.get(j).bounds)) {
							enemySwordMans.get(i).setCheckExistEnemyIntheScope(
									true);
							enemySwordMans.get(i).setStateFlag(
									EnemySwordMan.OBJ_D_ATTAK);
						}
				}
				if (enemySwordMans.get(i).isCheckExistEnemyIntheScope() == false) {
					enemySwordMans.get(i)
							.setStateFlag(EnemySwordMan.OBJ_D_LEFT);
				}
			}

		}

		// gun
		for (int i = 0; i < len01; i++) {
			if (enemyGunMans.get(i).getStageFlag() != EnemyGunMan.OBJ_D_DEATH
					&& enemyGunMans.get(i).getStageFlag() != EnemyGunMan.OBJ_D_RUNAWAY) {
				enemyGunMans.get(i).setCheckExistEnemyIntheScope(false);
				for (int j = 0; j < len03; j++) {

					if (len01 <= 0 || len03 <= 0)
						continue;

					if (commander.get(j).getFlag() == Commander.OURFORCE)
						if (Math_Overlap.overlapRectangles(
								enemyGunMans.get(i).attackBounds,
								commander.get(j).bounds)) {
							enemyGunMans.get(i).setCheckExistEnemyIntheScope(
									true);
							enemyGunMans.get(i).setStateFlag(
									EnemyGunMan.OBJ_D_ATTAK);
						}
				}
				if (enemyGunMans.get(i).isCheckExistEnemyIntheScope() == false) {
					enemyGunMans.get(i).setStateFlag(EnemyGunMan.OBJ_D_LEFT);
				}
			}
		}

		// final boss
		for (int i = 0; i < len05; i++) {
			if (enemyFinalBoss.get(i).getStageFlag() != EnemyFinalBossMan.OBJ_D_DEATH
					&& enemyFinalBoss.get(i).getStageFlag() != EnemyFinalBossMan.OBJ_D_ATTAK01
					&& enemyFinalBoss.get(i).getStageFlag() != EnemyFinalBossMan.OBJ_D_ATTAK02
					&& enemyFinalBoss.get(i).getStageFlag() != EnemyFinalBossMan.OBJ_D_ATTAK03
					&& enemyFinalBoss.get(i).getStageFlag() != EnemyFinalBossMan.OBJ_D_ATTAK04
					&& enemyFinalBoss.get(i).getStageFlag() != EnemyFinalBossMan.OBJ_D_SKill01) {
				enemyFinalBoss.get(i).setCheckExistEnemyIntheScope(false);
				for (int j = 0; j < len03; j++) {

					if (len01 <= 0 || len03 <= 0)
						continue;

					if (commander.get(j).getFlag() == Commander.OURFORCE)
						if (Math_Overlap.overlapRectangles(
								enemyFinalBoss.get(i).attackBounds,
								commander.get(j).bounds)) {
							enemyFinalBoss.get(i).setCheckExistEnemyIntheScope(
									true);
							enemyFinalBoss.get(i).setStateFlag(
									EnemyGunMan.OBJ_D_ATTAK);
						}
				}
				if (enemyFinalBoss.get(i).isCheckExistEnemyIntheScope() == false) {
					enemyFinalBoss.get(i).setStateFlag(
							EnemyMiddleBossMan.OBJ_D_LEFT);
				}
			}
		}

		// middle boss
		for (int i = 0; i < len04; i++) {
			if (enemyMiddleBoss01.get(i).getStageFlag() != EnemyMiddleBossMan.OBJ_D_DEATH) {
				enemyMiddleBoss01.get(i).setCheckExistEnemyIntheScope(false);
				for (int j = 0; j < len03; j++) {

					if (len01 <= 0 || len03 <= 0)
						continue;

					if (commander.get(j).getFlag() == Commander.OURFORCE)
						if (Math_Overlap.overlapRectangles(
								enemyMiddleBoss01.get(i).attackBounds,
								commander.get(j).bounds)) {
							enemyMiddleBoss01.get(i)
									.setCheckExistEnemyIntheScope(true);
							enemyMiddleBoss01.get(i).setStateFlag(
									EnemyGunMan.OBJ_D_ATTAK);
						}
				}
				if (enemyMiddleBoss01.get(i).isCheckExistEnemyIntheScope() == false) {
					enemyMiddleBoss01.get(i).setStateFlag(
							EnemyMiddleBossMan.OBJ_D_LEFT);
				}
			}
		}

	}

	private void enemyAttackFiyldToTheAlly(float deltaTime) {
		int len01 = enemyAttackFiyld.size();
		int len02 = allyMonkMans.size();
		int len03 = commander.size();
		int len04 = allyAxeMans.size();
		int len05 = allyArchers.size();
		int len06 = allySpearMans.size();

		for (int i = 0; i < len01; i++) {
			AttackFiyld enAttackFiyld = enemyAttackFiyld.get(i);

			if (enAttackFiyld.isDead() == true) {
				enemyAttackFiyld.remove(enAttackFiyld);
				len01 = enemyAttackFiyld.size();
			}
		}

		// spear
		for (int j = 0; j < len06; j++) {
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len06 <= 0)
					return;

				AttackFiyld mAttackFiyld = enemyAttackFiyld.get(i);

				if (allySpearMans.size() > 0
						&& Math_Overlap.overlapRectangles(mAttackFiyld.bounds,
								allySpearMans.get(j).bounds)) {

					if (mAttackFiyld.getnSkillType() == AttackFiyld.MIDDLEBOSSATTACK01)
						allySpearMans.get(j).setnHealthPoint(
								allySpearMans.get(j).getnHealthPoint()
										- AttackFiyld.MIDDLEBOSSATTACKDAMAGE01);
					else if (mAttackFiyld.getnSkillType() == AttackFiyld.MIDDLEBOSSATTACK02)
						allySpearMans.get(j).setnHealthPoint(
								allySpearMans.get(j).getnHealthPoint()
										- AttackFiyld.MIDDLEBOSSATTACKDAMAGE02);
					else
						allySpearMans.get(j).setnHealthPoint(
								allySpearMans.get(j).getnHealthPoint()
										- AttackFiyld.BASICDAMAGE);

					allySpearMans.get(j).getAllyHpBar().setVisible(true);

					if (allySpearMans.get(j).getnHealthPoint() < 0) {
						hpBar.remove(allySpearMans.get(j).getAllyHpBar());
						allySpearMans.get(j).setStateFlag(
								EnemyGunMan.OBJ_D_DEATH);
					}

					enemyAttackFiyld.remove(mAttackFiyld);
					len01 = enemyAttackFiyld.size();
				}
			}

		}

		// archer
		for (int j = 0; j < len05; j++) {
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len05 <= 0)
					return;

				AttackFiyld mAttackFiyld = enemyAttackFiyld.get(i);

				if (allyArchers.size() > 0
						&& Math_Overlap.overlapRectangles(mAttackFiyld.bounds,
								allyArchers.get(j).bounds)) {

					if (mAttackFiyld.getnSkillType() == AttackFiyld.MIDDLEBOSSATTACK01)
						allyArchers.get(j).setnHealthPoint(
								allyArchers.get(j).getnHealthPoint()
										- AttackFiyld.MIDDLEBOSSATTACKDAMAGE01);
					else if (mAttackFiyld.getnSkillType() == AttackFiyld.MIDDLEBOSSATTACK02)
						allyArchers.get(j).setnHealthPoint(
								allyArchers.get(j).getnHealthPoint()
										- AttackFiyld.MIDDLEBOSSATTACKDAMAGE02);
					else
						allyArchers.get(j).setnHealthPoint(
								allyArchers.get(j).getnHealthPoint()
										- AttackFiyld.BASICDAMAGE);

					allyArchers.get(j).getAllyHpBar().setVisible(true);

					if (allyArchers.get(j).getnHealthPoint() < 0) {
						hpBar.remove(allyArchers.get(j).getAllyHpBar());
						allyArchers.get(j)
								.setStateFlag(EnemyGunMan.OBJ_D_DEATH);
					}

					enemyAttackFiyld.remove(mAttackFiyld);
					len01 = enemyAttackFiyld.size();
				}
			}

		}

		// monk
		for (int j = 0; j < len02; j++) {
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len02 <= 0)
					return;

				AttackFiyld mAttackFiyld = enemyAttackFiyld.get(i);

				if (allyMonkMans.size() > 0
						&& Math_Overlap.overlapRectangles(mAttackFiyld.bounds,
								allyMonkMans.get(j).bounds)) {

					if (mAttackFiyld.getnSkillType() == AttackFiyld.MIDDLEBOSSATTACK01)
						allyMonkMans.get(j).setnHealthPoint(
								allyMonkMans.get(j).getnHealthPoint()
										- AttackFiyld.MIDDLEBOSSATTACKDAMAGE01);
					else if (mAttackFiyld.getnSkillType() == AttackFiyld.MIDDLEBOSSATTACK02)
						allyMonkMans.get(j).setnHealthPoint(
								allyMonkMans.get(j).getnHealthPoint()
										- AttackFiyld.MIDDLEBOSSATTACKDAMAGE02);
					else
						allyMonkMans.get(j).setnHealthPoint(
								allyMonkMans.get(j).getnHealthPoint()
										- AttackFiyld.BASICDAMAGE);

					allyMonkMans.get(j).getAllyHpBar().setVisible(true);

					if (allyMonkMans.get(j).getnHealthPoint() < 0) {
						hpBar.remove(allyMonkMans.get(j).getAllyHpBar());
						allyMonkMans.get(j).setStateFlag(
								EnemyGunMan.OBJ_D_DEATH);
					}

					enemyAttackFiyld.remove(mAttackFiyld);
					len01 = enemyAttackFiyld.size();
				}
			}

		}

		// axe
		for (int j = 0; j < len04; j++) {
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len04 <= 0)
					return;

				AttackFiyld mAttackFiyld = enemyAttackFiyld.get(i);

				if (allyAxeMans.size() > 0
						&& Math_Overlap.overlapRectangles(mAttackFiyld.bounds,
								allyAxeMans.get(j).bounds)) {
					if (mAttackFiyld.getnSkillType() == AttackFiyld.MIDDLEBOSSATTACK01)
						allyAxeMans.get(j).setnHealthPoint(
								allyAxeMans.get(j).getnHealthPoint()
										- AttackFiyld.MIDDLEBOSSATTACKDAMAGE01);
					else if (mAttackFiyld.getnSkillType() == AttackFiyld.MIDDLEBOSSATTACK02)
						allyAxeMans.get(j).setnHealthPoint(
								allyAxeMans.get(j).getnHealthPoint()
										- AttackFiyld.MIDDLEBOSSATTACKDAMAGE02);
					else
						allyAxeMans.get(j).setnHealthPoint(
								allyAxeMans.get(j).getnHealthPoint()
										- AttackFiyld.BASICDAMAGE);

					allyAxeMans.get(j).getAllyHpBar().setVisible(true);

					if (allyAxeMans.get(j).getnHealthPoint() < 0) {
						hpBar.remove(allyAxeMans.get(j).getAllyHpBar());
						allyAxeMans.get(j).setStateFlag(AllyAxeMan.OBJ_D_DEATH);
					}

					enemyAttackFiyld.remove(mAttackFiyld);
					len01 = enemyAttackFiyld.size();
				}
			}

		}

		// command
		for (int j = 0; j < len03; j++)
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len03 <= 0)
					return;

				AttackFiyld mAttackFiyld = enemyAttackFiyld.get(i);

				if (Math_Overlap.overlapRectangles(mAttackFiyld.bounds,
						commander.get(j).bounds)) {

					if (mAttackFiyld.getnSkillType() == AttackFiyld.MIDDLEBOSSATTACK01)
						commander.get(j).setnHealthPoint(
								commander.get(j).getnHealthPoint()
										- AttackFiyld.MIDDLEBOSSATTACKDAMAGE01);
					else if (mAttackFiyld.getnSkillType() == AttackFiyld.MIDDLEBOSSATTACK02)
						commander.get(j).setnHealthPoint(
								commander.get(j).getnHealthPoint()
										- AttackFiyld.MIDDLEBOSSATTACKDAMAGE02);
					else
						commander.get(j).setnHealthPoint(
								commander.get(j).getnHealthPoint()
										- AttackFiyld.BASICDAMAGE);

					if (commander.get(j).getnHealthPoint() < 0) {
						Screen_MainGame.state = Screen_MainGame.GAME_OVER;
						Manage_Assets.playSound(Manage_Assets.soundLeesunsinDie);
						Manage_Assets.PlayMusic(Manage_Assets.musicGameLose);
						commander.remove(commander.get(j));
						len03 = commander.size();
					}

					enemyAttackFiyld.remove(mAttackFiyld);
					len01 = enemyAttackFiyld.size();
				}
			}

		// lee
		for (int i = 0; i < len01; i++) {

			if (len01 <= 0)
				continue;

			AttackFiyld mAttackFiyld = enemyAttackFiyld.get(i);
			if (Math_Overlap.overlapRectangles(mAttackFiyld.bounds,
					mLeeSunSin.bounds)) {

				mLeeSunSin.getHpBar().setVisible(true);

				if (Manage_Settings.isVibration)
					vibe.vibrate(400);
				if (mAttackFiyld.getnSkillType() == AttackFiyld.MIDDLEBOSSATTACK01) {
					mLeeSunSin.setnHealthPoint(mLeeSunSin.getnHealthPoint()
							- AttackFiyld.MIDDLEBOSSATTACKDAMAGE01);
				} else if (mAttackFiyld.getnSkillType() == AttackFiyld.MIDDLEBOSSATTACK02) {
					mLeeSunSin.setnHealthPoint(mLeeSunSin.getnHealthPoint()
							- AttackFiyld.MIDDLEBOSSATTACKDAMAGE02);
				} else {
					mLeeSunSin.setnHealthPoint(mLeeSunSin.getnHealthPoint()
							- Bullet.BASICDAMAGE);
				}

				mLeeSunSin.setStateFlag(LeeSunSin.OBJ_D_DAMAGED);

				if (mLeeSunSin.getnHealthPoint() < 0) {
					Screen_MainGame.state = Screen_MainGame.GAME_OVER;
					Manage_Assets.playSound(Manage_Assets.soundLeesunsinDie);
					Manage_Assets.PlayMusic(Manage_Assets.musicGameLose);
				}

				enemyAttackFiyld.remove(mAttackFiyld);
				len01 = enemyAttackFiyld.size();
			}
		}

	}

	private void enemySwordToTheAlly(float deltaTime) {
		int len01 = enemySwordMans.size();
		int len02 = allyMonkMans.size();
		int len03 = allyAxeMans.size();
		int len04 = allyArchers.size();
		int len05 = allySpearMans.size();

		for (int i = 0; i < len01; i++) {
			if (enemySwordMans.get(i).getStageFlag() != EnemySwordMan.OBJ_D_DEATH
					&& enemySwordMans.get(i).getStageFlag() != EnemySwordMan.OBJ_D_RUNAWAY) {
				enemySwordMans.get(i).setCheckExistEnemyIntheScope(false);

				// Spear
				for (int j = 0; j < len05; j++) {

					if (len01 <= 0 || len05 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							enemySwordMans.get(i).attackBounds,
							allySpearMans.get(j).bounds)) {
						enemySwordMans.get(i)
								.setCheckExistEnemyIntheScope(true);
						enemySwordMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_ATTAK);
						}
					if (enemySwordMans.get(i).isCheckExistEnemyIntheScope() == false) {
						enemySwordMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_LEFT);
					}
				}

				// Archer
				for (int j = 0; j < len04; j++) {

					if (len01 <= 0 || len04 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							enemySwordMans.get(i).attackBounds,
							allyArchers.get(j).bounds)) {
						enemySwordMans.get(i)
								.setCheckExistEnemyIntheScope(true);
						enemySwordMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_ATTAK);
					}
					if (enemySwordMans.get(i).isCheckExistEnemyIntheScope() == false) {
						enemySwordMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_LEFT);
					}
				}

				// monk
				for (int j = 0; j < len02; j++) {

					if (len01 <= 0 || len02 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							enemySwordMans.get(i).attackBounds,
							allyMonkMans.get(j).bounds)) {
						enemySwordMans.get(i)
								.setCheckExistEnemyIntheScope(true);
						enemySwordMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_ATTAK);
					}
					if (enemySwordMans.get(i).isCheckExistEnemyIntheScope() == false) {
						enemySwordMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_LEFT);
					}
				}

				// axe
				for (int j = 0; j < len03; j++) {

					if (len01 <= 0 || len03 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							enemySwordMans.get(i).attackBounds,
							allyAxeMans.get(j).bounds)) {
						enemySwordMans.get(i)
								.setCheckExistEnemyIntheScope(true);
						enemySwordMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_ATTAK);
					}
					if (enemySwordMans.get(i).isCheckExistEnemyIntheScope() == false) {
						enemySwordMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_LEFT);
					}
				}

				// lee
				if (len01 > 0 && mLeeSunSin.getnHealthPoint() > 0) {
					if (Math_Overlap.overlapRectangles(
							enemySwordMans.get(i).attackBounds,
							mLeeSunSin.bounds)) {
						enemySwordMans.get(i)
								.setCheckExistEnemyIntheScope(true);
						enemySwordMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_ATTAK);
					}
					if (enemySwordMans.get(i).isCheckExistEnemyIntheScope() == false) {
						enemySwordMans.get(i).setStateFlag(
								EnemySwordMan.OBJ_D_LEFT);
					}
				}

			}
		}

	}

	private void energyBallToEnemy(float deltaTime) {
		int len01 = energyBall.size();
		int len02 = enemyGunMans.size();
		int len03 = enemySwordMans.size();
		int len04 = commander.size();
		int len05 = enemyMiddleBoss01.size();
		int len06 = enemyFinalBoss.size();

		for (int i = 0; i < len01; i++) {
			EnergyBall mEnergyBall = energyBall.get(i);

			if (mEnergyBall.isDead() == true) {
				energyBall.remove(mEnergyBall);
				len01 = energyBall.size();
			}
		}
		// 적 총병
		for (int j = 0; j < len02; j++)
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len02 <= 0)
					continue;

				EnergyBall mEnergyBall = energyBall.get(i);

				if (Math_Overlap.overlapRectangles(mEnergyBall.bounds,
						enemyGunMans.get(j).bounds)) {

					enemyGunMans.get(j).getEnemyHpBar().setVisible(true);

					if (mEnergyBall.getType() == EnergyBall.ENERGYBALL)
						enemyGunMans
								.get(j)
								.setnHealthPoint(
										enemyGunMans.get(j).getnHealthPoint()
												- (int) ((int) MonkMan
														.Bonus(Manage_Settings.nUnitMonkManLv)
														* MonkMan.DamageBasic + MonkMan.DamageBasic));
					else
						enemyGunMans
								.get(j)
								.setnHealthPoint(
										enemyGunMans.get(j).getnHealthPoint()
												- (int) ((int) ArcherMan
														.Bonus(Manage_Settings.nUnitArcherLv)
														* ArcherMan.DamageBasic + ArcherMan.DamageBasic));
					if (enemyGunMans.get(j).getnHealthPoint() < 0
							&& (enemyGunMans.get(j).getStageFlag() != EnemyGunMan.OBJ_D_RUNAWAY && enemyGunMans
									.get(j).getStageFlag() != EnemyGunMan.OBJ_D_DEATH)) {
						hpBar.remove(enemyGunMans.get(j).getEnemyHpBar());

						nchoice = rnd.nextInt(10);
						if (nchoice < PARCANTAGERUNAWAY)
							enemyGunMans.get(j).setStateFlag(
									EnemyGunMan.OBJ_D_RUNAWAY);
						else
							enemyGunMans.get(j).setStateFlag(
									EnemyGunMan.OBJ_D_DEATH);

						MainGame_Manager.nStageScore += UnitInformation.ENEMY.GunMan.Exp
								+ rnd.nextInt(50);
						Manage_Settings.nStageCoin += UnitInformation.ENEMY.GunMan.Money
								+ rnd.nextInt(50);
					}

					energyBall.remove(mEnergyBall);
					len01 = energyBall.size();
				}
			}

		// final Boss
		for (int j = 0; j < len06; j++)
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len06 <= 0)
					continue;

				EnergyBall mEnergyBall = energyBall.get(i);

				if (Math_Overlap.overlapRectangles(mEnergyBall.bounds,
						enemyFinalBoss.get(j).bounds)) {

					enemyFinalBoss.get(j).getEnemyHpBar().setVisible(true);

					if (mEnergyBall.getType() == EnergyBall.ENERGYBALL)
						enemyFinalBoss
								.get(j)
								.setnHealthPoint(
										enemyFinalBoss.get(j).getnHealthPoint()
												- (int) ((int) MonkMan
														.Bonus(Manage_Settings.nUnitMonkManLv)
														* MonkMan.DamageBasic + MonkMan.DamageBasic));
					else
						enemyFinalBoss
								.get(j)
								.setnHealthPoint(
										enemyFinalBoss.get(j).getnHealthPoint()
												- (int) ((int) ArcherMan
														.Bonus(Manage_Settings.nUnitArcherLv)
														* ArcherMan.DamageBasic + ArcherMan.DamageBasic));

					if (enemyFinalBoss.get(j).getnHealthPoint() < 0
							&& (enemyFinalBoss.get(j).getStageFlag() != EnemyFinalBossMan.OBJ_D_DEATH)) {
						hpBar.remove(enemyFinalBoss.get(j).getEnemyHpBar());
						enemyFinalBoss.get(j).setStateFlag(
								EnemyFinalBossMan.OBJ_D_DEATH);
						MainGame_Manager.nStageScore += UnitInformation.ENEMY.MiddleBoss.Exp
								+ rnd.nextInt(50);
						Manage_Settings.nStageCoin += UnitInformation.ENEMY.MiddleBoss.Money
								+ rnd.nextInt(50);
					}

					energyBall.remove(mEnergyBall);
					len01 = energyBall.size();
				}
			}

		// middle Boss
		for (int j = 0; j < len05; j++)
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len05 <= 0)
					continue;

				EnergyBall mEnergyBall = energyBall.get(i);

				if (Math_Overlap.overlapRectangles(mEnergyBall.bounds,
						enemyMiddleBoss01.get(j).bounds)) {

					enemyMiddleBoss01.get(j).getEnemyHpBar().setVisible(true);

					if (mEnergyBall.getType() == EnergyBall.ENERGYBALL)
						enemyMiddleBoss01
								.get(j)
								.setnHealthPoint(
										enemyMiddleBoss01.get(j)
												.getnHealthPoint()
												- (int) ((int) MonkMan
														.Bonus(Manage_Settings.nUnitMonkManLv)
														* MonkMan.DamageBasic + MonkMan.DamageBasic));
					else
						enemyMiddleBoss01
								.get(j)
								.setnHealthPoint(
										enemyMiddleBoss01.get(j)
												.getnHealthPoint()
												- (int) ((int) ArcherMan
														.Bonus(Manage_Settings.nUnitArcherLv)
														* ArcherMan.DamageBasic + ArcherMan.DamageBasic));

					if (enemyMiddleBoss01.get(j).getnHealthPoint() < 0
							&& (enemyMiddleBoss01.get(j).getStageFlag() != EnemyMiddleBossMan.OBJ_D_DEATH)) {
						hpBar.remove(enemyMiddleBoss01.get(j).getEnemyHpBar());
						enemyMiddleBoss01.get(j).setStateFlag(
								EnemyMiddleBossMan.OBJ_D_DEATH);
						MainGame_Manager.nStageScore += UnitInformation.ENEMY.MiddleBoss.Exp
								+ rnd.nextInt(50);
						Manage_Settings.nStageCoin += UnitInformation.ENEMY.MiddleBoss.Money
								+ rnd.nextInt(50);
					}

					energyBall.remove(mEnergyBall);
					len01 = energyBall.size();
				}
			}

		// 적 칼병
		for (int j = 0; j < len03; j++)
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len03 <= 0)
					continue;

				EnergyBall mEnergyBall = energyBall.get(i);

				if (Math_Overlap.overlapRectangles(mEnergyBall.bounds,
						enemySwordMans.get(j).bounds)) {

					enemySwordMans.get(j).getEnemyHpBar().setVisible(true);

					if (mEnergyBall.getType() == EnergyBall.ENERGYBALL)
						enemySwordMans
								.get(j)
								.setnHealthPoint(
										enemySwordMans.get(j).getnHealthPoint()
												- (int) ((int) MonkMan
														.Bonus(Manage_Settings.nUnitMonkManLv)
														* MonkMan.DamageBasic + MonkMan.DamageBasic));
					else
						enemySwordMans
								.get(j)
								.setnHealthPoint(
										enemySwordMans.get(j).getnHealthPoint()
												- (int) ((int) ArcherMan
														.Bonus(Manage_Settings.nUnitArcherLv)
														* ArcherMan.DamageBasic + ArcherMan.DamageBasic));

					if (enemySwordMans.get(j).getnHealthPoint() < 0
							&& (enemySwordMans.get(j).getStageFlag() != EnemySwordMan.OBJ_D_RUNAWAY && enemySwordMans
									.get(j).getStageFlag() != EnemySwordMan.OBJ_D_DEATH)) {
						hpBar.remove(enemySwordMans.get(j).getEnemyHpBar());

						nchoice = rnd.nextInt(10);
						if (nchoice < PARCANTAGERUNAWAY)
							enemySwordMans.get(j).setStateFlag(
									AllyAxeMan.OBJ_D_RUNAWAY);
						else
							enemySwordMans.get(j).setStateFlag(
									AllyAxeMan.OBJ_D_DEATH);

						MainGame_Manager.nStageScore += UnitInformation.ENEMY.SwordMan.Exp
								+ rnd.nextInt(25);
						Manage_Settings.nStageCoin += UnitInformation.ENEMY.SwordMan.Money
								+ rnd.nextInt(25);
					}

					energyBall.remove(mEnergyBall);
					len01 = energyBall.size();
				}
			}

		// 본진
		for (int j = 0; j < len04; j++)
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len04 <= 0)
					continue;

				EnergyBall mEnergyBall = energyBall.get(i);
				if (Commander.ENEMYFORCE == commander.get(j).getFlag())
					if (Math_Overlap.overlapRectangles(mEnergyBall.bounds,
							commander.get(j).bounds)) {

						if (mEnergyBall.getType() == EnergyBall.ENERGYBALL)
							commander
									.get(j)
									.setnHealthPoint(
											commander.get(j).getnHealthPoint()
													- (int) ((int) MonkMan
															.Bonus(Manage_Settings.nUnitMonkManLv)
															* MonkMan.DamageBasic + MonkMan.DamageBasic));
						else
							commander
									.get(j)
									.setnHealthPoint(
											commander.get(j).getnHealthPoint()
													- (int) ((int) ArcherMan
															.Bonus(Manage_Settings.nUnitArcherLv)
															* ArcherMan.DamageBasic + ArcherMan.DamageBasic));

						if (commander.get(j).getnHealthPoint() < 0) {
							// hpBar.remove(commander.get(j).getEnemyHpBar());
							Screen_MainGame.state = Screen_MainGame.GAME_LEVEL_END;
							Manage_Assets
									.PlayMusic(Manage_Assets.musicGameClear);
							commander.remove(commander.get(j));
							len03 = commander.size();
						}

						enemyBullet.remove(mEnergyBall);
						len01 = enemyBullet.size();
					}
			}

	}

	private void enemyBulletToTheAlly(float deltaTime) {
		int len01 = enemyBullet.size();
		int len02 = allyMonkMans.size();
		int len03 = commander.size();
		int len04 = allyAxeMans.size();
		int len05 = allyArchers.size();
		int len06 = allySpearMans.size();

		for (int i = 0; i < len01; i++) {
			Bullet mGunBullet = enemyBullet.get(i);

			if (mGunBullet.isDead() == true) {
				enemyBullet.remove(mGunBullet);
				len01 = enemyBullet.size();
			}
		}

		// archer
		for (int j = 0; j < len06; j++)
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len06 <= 0)
					continue;

				Bullet mGunBullet = enemyBullet.get(i);

				if (Math_Overlap.overlapRectangles(mGunBullet.bounds,
						allySpearMans.get(j).bounds)) {

					allySpearMans.get(j).getAllyHpBar().setVisible(true);

					allySpearMans.get(j).setnHealthPoint(
							allySpearMans.get(j).getnHealthPoint()
									- Bullet.BASICDAMAGE);

					if (allySpearMans.get(j).getnHealthPoint() < 0) {
						hpBar.remove(allySpearMans.get(j).getAllyHpBar());
						allySpearMans.get(j).setStateFlag(
								EnemySwordMan.OBJ_D_DEATH);
						// enemySwordMans.remove(enemySwordMans.get(j));
						// len02 = enemySwordMans.size();
					}

					enemyBullet.remove(mGunBullet);
					len01 = enemyBullet.size();
				}
			}

		// archer
		for (int j = 0; j < len05; j++)
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len05 <= 0)
					continue;

				Bullet mGunBullet = enemyBullet.get(i);

				if (Math_Overlap.overlapRectangles(mGunBullet.bounds,
						allyArchers.get(j).bounds)) {

					allyArchers.get(j).getAllyHpBar().setVisible(true);

					allyArchers.get(j).setnHealthPoint(
							allyArchers.get(j).getnHealthPoint()
									- Bullet.BASICDAMAGE);

					if (allyArchers.get(j).getnHealthPoint() < 0) {
						hpBar.remove(allyArchers.get(j).getAllyHpBar());
						allyArchers.get(j).setStateFlag(
								EnemySwordMan.OBJ_D_DEATH);
						// enemySwordMans.remove(enemySwordMans.get(j));
						// len02 = enemySwordMans.size();
					}

					enemyBullet.remove(mGunBullet);
					len01 = enemyBullet.size();
				}
			}

		// 몽크
		for (int j = 0; j < len02; j++)
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len02 <= 0)
					continue;

				Bullet mGunBullet = enemyBullet.get(i);

				if (Math_Overlap.overlapRectangles(mGunBullet.bounds,
						allyMonkMans.get(j).bounds)) {

					allyMonkMans.get(j).getAllyHpBar().setVisible(true);

					allyMonkMans.get(j).setnHealthPoint(
							allyMonkMans.get(j).getnHealthPoint()
									- Bullet.BASICDAMAGE);

					if (allyMonkMans.get(j).getnHealthPoint() < 0) {
						hpBar.remove(allyMonkMans.get(j).getAllyHpBar());
						allyMonkMans.get(j).setStateFlag(
								EnemySwordMan.OBJ_D_DEATH);
						// enemySwordMans.remove(enemySwordMans.get(j));
						// len02 = enemySwordMans.size();
					}

					enemyBullet.remove(mGunBullet);
					len01 = enemyBullet.size();
				}
			}

		// 도끼
		for (int j = 0; j < len04; j++)
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len04 <= 0)
					continue;

				Bullet mGunBullet = enemyBullet.get(i);

				if (Math_Overlap.overlapRectangles(mGunBullet.bounds,
						allyAxeMans.get(j).bounds)) {

					allyAxeMans.get(j).getAllyHpBar().setVisible(true);

					allyAxeMans.get(j).setnHealthPoint(
							allyAxeMans.get(j).getnHealthPoint()
									- Bullet.BASICDAMAGE);

					if (allyAxeMans.get(j).getnHealthPoint() < 0) {
						hpBar.remove(allyAxeMans.get(j).getAllyHpBar());
						allyAxeMans.get(j).setStateFlag(
								EnemySwordMan.OBJ_D_DEATH);
					}

					enemyBullet.remove(mGunBullet);
					len01 = enemyBullet.size();
				}
			}
		// 본진
		for (int j = 0; j < len03; j++)
			for (int i = 0; i < len01; i++) {

				if (len01 <= 0 || len03 <= 0)
					continue;

				Bullet mGunBullet = enemyBullet.get(i);
				if (Commander.OURFORCE == commander.get(j).getFlag())
					if (Math_Overlap.overlapRectangles(mGunBullet.bounds,
							commander.get(j).bounds)) {

						// commander.get(j).getEnemyHpBar().setVisible(true);

						commander.get(j).setnHealthPoint(
								commander.get(j).getnHealthPoint()
										- Bullet.BASICDAMAGE);

						if (commander.get(j).getnHealthPoint() < 0) {
							Screen_MainGame.state = Screen_MainGame.GAME_OVER;
							Manage_Assets.playSound(Manage_Assets.soundLeesunsinDie);
							Manage_Assets
									.PlayMusic(Manage_Assets.musicGameLose);
							commander.remove(commander.get(j));
							len03 = commander.size();
						}

						enemyBullet.remove(mGunBullet);
						len01 = enemyBullet.size();
					}
			}

		// lee
		for (int i = 0; i < len01; i++) {

			if (len01 <= 0)
				continue;

			Bullet mGunBullet = enemyBullet.get(i);
			if (Math_Overlap.overlapRectangles(mGunBullet.bounds,
					mLeeSunSin.bounds)) {

				mLeeSunSin.getHpBar().setVisible(true);

				if (Manage_Settings.isVibration)
					vibe.vibrate(400);

				mLeeSunSin.setnHealthPoint(mLeeSunSin.getnHealthPoint()
						- Bullet.BASICDAMAGE);

				if (mLeeSunSin.getnHealthPoint() < 0) {
					Screen_MainGame.state = Screen_MainGame.GAME_OVER;
					Manage_Assets.playSound(Manage_Assets.soundLeesunsinDie);
					Manage_Assets.PlayMusic(Manage_Assets.musicGameLose);
				}

				enemyBullet.remove(mGunBullet);
				len01 = enemyBullet.size();
			}
		}

	}

	private void enemyGunToTheAlly(float deltaTime) {
		int len01 = enemyGunMans.size();
		int len02 = allyMonkMans.size();
		int len03 = allyAxeMans.size();

		for (int i = 0; i < len01; i++) {
			if (enemyGunMans.get(i).getStageFlag() != EnemyGunMan.OBJ_D_DEATH
					&& enemyGunMans.get(i).getStageFlag() != EnemyGunMan.OBJ_D_RUNAWAY) {
				enemyGunMans.get(i).setCheckExistEnemyIntheScope(false);

				// monk
				for (int j = 0; j < len02; j++) {

					if (len01 <= 0 || len02 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							enemyGunMans.get(i).attackBounds,
							allyMonkMans.get(j).bounds)) {
						enemyGunMans.get(i).setCheckExistEnemyIntheScope(true);
						enemyGunMans.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_ATTAK);
					}

					if (enemyGunMans.get(i).isCheckExistEnemyIntheScope() == false) {
						enemyGunMans.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_LEFT);
					}
				}

				// Axe
				for (int j = 0; j < len03; j++) {

					if (len01 <= 0 || len03 <= 0)
						continue;
					if (Math_Overlap.overlapRectangles(
							enemyGunMans.get(i).attackBounds,
							allyAxeMans.get(j).bounds)) {
						enemyGunMans.get(i).setCheckExistEnemyIntheScope(true);
						enemyGunMans.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_ATTAK);
					}

					if (enemyGunMans.get(i).isCheckExistEnemyIntheScope() == false) {
						enemyGunMans.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_LEFT);
					}
				}

				// lee
				if (len01 > 0 && mLeeSunSin.getnHealthPoint() > 0) {
					if (Math_Overlap
							.overlapRectangles(
									enemyGunMans.get(i).attackBounds,
									mLeeSunSin.bounds)) {
						enemyGunMans.get(i).setCheckExistEnemyIntheScope(true);
						enemyGunMans.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_ATTAK);
					}
					if (enemyGunMans.get(i).isCheckExistEnemyIntheScope() == false) {
						enemyGunMans.get(i).setStateFlag(
								Object_Dynamic.OBJ_D_LEFT);
					}
				}

			}
		}
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	public int get_World_Width() {
		return this.world_Width;
	}

	public int get_World_Height() {
		return this.world_Height;
	}

}
package pangpang.MainGameLeemJinMuSSang;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import jrcengine.Basic.GL_Graphics;
import jrcengine.GL.GL_Animation;
import jrcengine.GL.GL_Camera2D;
import jrcengine.GL.GL_SpriteBatcher;
import jrcengine.GL.GL_TextureRegion;
import jrcengine.Interface.Object_Dynamic;
import jrcengine.Manage.Manage_Assets;
import android.util.Log;

public class MainGame_Renderer {

	private GL_Graphics glGraphics;
	private MainGame_Manager manage;
	private GL_Camera2D game_cam;
	private GL_SpriteBatcher batcher;

	private final int MAXATTACKTONADO = 8;
	private final int MAXATTACKPOWRATTACK = 3;
	private final GL_TextureRegion backGroundObject[];
	private final GL_TextureRegion backGroundObjects[];

	float updateTime;
	Random rnd;

	private int backGroundObjectPosition[];

	public MainGame_Renderer(GL_Graphics glGraphics, GL_SpriteBatcher batcher,
			MainGame_Manager manage) {

		this.glGraphics = glGraphics;
		this.manage = manage;
		this.game_cam = new GL_Camera2D(glGraphics, manage.get_World_Width()
				* Screen_MainGame.nMultipleNumberSizeOfMap / 2,
				manage.get_World_Height());
		this.batcher = batcher;

		this.game_cam.position.x = manage.get_World_Width()
				* Screen_MainGame.nMultipleNumberSizeOfMap / 2;
		this.game_cam.position.y = manage.get_World_Height() / 2;
		this.rnd = new Random();

		this.backGroundObjectPosition = new int[10];
		backGroundObject = new GL_TextureRegion[4];
		backGroundObjects = new GL_TextureRegion[5];

		for (int i = 0; i < 10; i++)
			this.backGroundObjectPosition[i] = rnd.nextInt(50);

		backGroundObject[0] = Manage_Assets.ttgFiredBoar01;
		backGroundObject[1] = Manage_Assets.ttgFiredBoar02;
		backGroundObject[2] = Manage_Assets.ttgFiredBoar03;
		backGroundObject[3] = Manage_Assets.ttgFiredBoar04;

		for (int i = 0; i < 5; i++)
			backGroundObjects[i] = backGroundObject[rnd.nextInt(4)];

	}

	public void render(float deltaTime, int state) {
		game_cam.setViewportAndMatrices();

		rederCameraUpdate();
		renderObjects(deltaTime);

	}

	private void rederCameraUpdate() {

		if (MainGame_Manager.mLeeSunSin.position.x >= game_cam
				.getfrustum_Width() / 2
				&& MainGame_Manager.mLeeSunSin.position.x <= manage
						.get_World_Width()
						* Screen_MainGame.nMultipleNumberSizeOfMap
						- game_cam.getfrustum_Width() / 2)
			game_cam.position.x = MainGame_Manager.mLeeSunSin.position.x;
	}

	private void renderBackgroundObject() {
		batcher.beginBatch(Manage_Assets.ttFiredBoatMainGame);
		// fire 01
		batcher.drawSprite(300 + backGroundObjectPosition[4],
				manage.get_World_Height() / 2 + 30
						+ backGroundObjectPosition[0], 200, 60,
				backGroundObjects[0]);
		batcher.endBatch();

		// fire 02
		batcher.beginBatch(Manage_Assets.ttFiredBoatMainGame);
		batcher.drawSprite(650 + backGroundObjectPosition[5],
				manage.get_World_Height() / 2 + 30
						+ backGroundObjectPosition[1], 200, 60,
				backGroundObjects[1]);
		batcher.endBatch();

		// fire 03
		batcher.beginBatch(Manage_Assets.ttFiredBoatMainGame);
		batcher.drawSprite(1000 + backGroundObjectPosition[6],
				manage.get_World_Height() / 2 + 30
						+ backGroundObjectPosition[2], 200, 60,
				backGroundObjects[2]);
		batcher.endBatch();

		// fire 04
		batcher.beginBatch(Manage_Assets.ttFiredBoatMainGame);
		batcher.drawSprite(1350 + backGroundObjectPosition[7],
				manage.get_World_Height() / 2 + 30
						+ backGroundObjectPosition[3], 200, 60,
				backGroundObjects[3]);
		batcher.endBatch();

		// fire 05
		batcher.beginBatch(Manage_Assets.ttFiredBoatMainGame);
		batcher.drawSprite(1600 + backGroundObjectPosition[8],
				manage.get_World_Height() / 2 + 30
						+ backGroundObjectPosition[9], 200, 60,
				backGroundObjects[4]);
		batcher.endBatch();
	}

	private void renderBackground() {
		GL_TextureRegion _keyFrame;

		_keyFrame = Manage_Assets.aniGameBackGround.getKeyFrame(
				MainGame_Manager.mLeeSunSin.getStateTime(),
				GL_Animation.ANIMATION_LOOPING);

		batcher.beginBatch(Manage_Assets.ttGameBackGround);

		batcher.drawSprite(manage.get_World_Width()
				* Screen_MainGame.nMultipleNumberSizeOfMap / 2,
				manage.get_World_Height() / 2, manage.get_World_Width()
						* Screen_MainGame.nMultipleNumberSizeOfMap,
				manage.get_World_Height(), _keyFrame);
		batcher.endBatch();

	}

	private void renderObjects(float deltaTime) {

		updateTime += deltaTime;

		GL10 gl = glGraphics.getGL();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		renderBackGround();
		renderBackground();
		renderBackgroundObject();
		renderObjectStructures(deltaTime);
		render_chr_enemy_gun(deltaTime);
		render_LeeSunSin(deltaTime);
		render_chr_enemy_sword(deltaTime);
		render_chr_enemy_middleBoss(deltaTime);
		render_chr_enemy_finalBoss(deltaTime);
		render_chr_enemy_bullet();
		render_chr_ally_monkMan(deltaTime);
		render_chr_ally_AxeMan(deltaTime);
		render_chr_ally_archerMan(deltaTime);
		render_chr_ally_spearMan(deltaTime);
		render_chr_ally_energyBall(deltaTime);
		render_chr_LeeSunShin_Skill(deltaTime);

		// render_chr_ally_Attack_fiyld();
		// render_chr_enemy_Attack_fiyld();
		renderObjectStructuresParticle(deltaTime);
		render_HPbar();

		gl.glDisable(GL10.GL_BLEND);

	}

	private void renderBackGround() {

		batcher.beginBatch(Manage_Assets.ttGameSceneBackGround02);

		// background jung
		batcher.drawSprite(manage.get_World_Width()
				* Screen_MainGame.nMultipleNumberSizeOfMap / 2,
				manage.get_World_Height() / 2, manage.get_World_Width()
						* Screen_MainGame.nMultipleNumberSizeOfMap,
				manage.get_World_Height(), Manage_Assets.ttgGameBackGroundJung);
		batcher.endBatch();

		// sky

		GL_TextureRegion _keyFrame = Manage_Assets.aniGameSceneBackGround02
				.getKeyFrame(MainGame_Manager.mLeeSunSin.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
		batcher.beginBatch(Manage_Assets.ttGameSceneBackGround02);
		batcher.drawSprite(manage.get_World_Width()
				* Screen_MainGame.nMultipleNumberSizeOfMap / 2,
				manage.get_World_Height() / 2, manage.get_World_Width()
						* Screen_MainGame.nMultipleNumberSizeOfMap,
				manage.get_World_Height(), _keyFrame);

		batcher.endBatch();

	}

	private void render_chr_LeeSunShin_Skill(float _deltaTime) {
		int len = MainGame_Manager.leesunsinSkillObjects.size();

		if (len <= 0)
			return;

		for (int i = 0; i < MainGame_Manager.leesunsinSkillObjects.size(); i++) {
			SkillObject temp = MainGame_Manager.leesunsinSkillObjects.get(i);

			if (SkillObject.CROSSCUTTING == temp.getSkillType()) {

				GL_TextureRegion _keyFrame = Manage_Assets.aniLeeSunShinSkillEffect01
						.getKeyFrame(temp.getStateTime(),
								GL_Animation.ANIMATION_LOOPING);
				batcher.beginBatch(Manage_Assets.ttLeeSunShinSKillEffect0103);
				batcher.drawSprite(temp.position.x, temp.position.y, 400, 140,
						_keyFrame);
				batcher.endBatch();
			} else if (SkillObject.GENERATEMARINE == temp.getSkillType()) {

				GL_TextureRegion _keyFrame = Manage_Assets.aniLeeSunShinSkillEffect02
						.getKeyFrame(temp.getStateTime(),
								GL_Animation.ANIMATION_LOOPING);
				batcher.beginBatch(Manage_Assets.ttLeeSunShinSKillEffect0103);
				batcher.drawSprite(temp.position.x, temp.position.y, 410, 140,
						_keyFrame);
				batcher.endBatch();
			} else if (SkillObject.GIGONGSINPO == temp.getSkillType()) {
				GL_TextureRegion _keyFrame = Manage_Assets.aniLeeSunShinSkillEffect04
						.getKeyFrame(temp.getStateTime(),
								GL_Animation.ANIMATION_LOOPING);

				batcher.beginBatch(Manage_Assets.ttLeeSunShinSkill04);

				batcher.drawSprite(temp.position.x, temp.position.y, 400, 140,
						_keyFrame);

				batcher.endBatch();

			}

		}

	}

	private void renderObjectStructuresParticle(float deltaTime) {
		int len = MainGame_Manager.commander.size();

		if (len <= 0)
			return;

		for (int i = 0; i < len; i++) {
			Commander commender = MainGame_Manager.commander.get(i);
			if (commender.getFlag() == Commander.OURFORCE) {
				batcher.beginBatch(Manage_Assets.ttallyStructureParticle);

				if ((float) (commender.getnHealthPoint() / (commender
						.getnFullHealThPoint())) > Structure.STRUCTURE80)
					batcher.drawSprite(commender.position.x,
							commender.position.y,
							Commander.COMMANDER_ALLY_WIDTH,
							Commander.COMMANDER_ALLY_HEIHGT,
							Manage_Assets.ttgAllyStructureParticle100);
				else if ((float) (commender.getnHealthPoint() / (commender
						.getnFullHealThPoint())) > Structure.STRUCTURE60)
					batcher.drawSprite(commender.position.x,
							commender.position.y,
							Commander.COMMANDER_ALLY_WIDTH,
							Commander.COMMANDER_ALLY_HEIHGT,
							Manage_Assets.ttgAllyStructureParticle80);
				else if ((float) (commender.getnHealthPoint() / (commender
						.getnFullHealThPoint())) > Structure.STRUCTURE40)
					batcher.drawSprite(commender.position.x,
							commender.position.y,
							Commander.COMMANDER_ALLY_WIDTH,
							Commander.COMMANDER_ALLY_HEIHGT,
							Manage_Assets.ttgAllyStructureParticle60);
				else if ((float) (commender.getnHealthPoint() / (commender
						.getnFullHealThPoint())) > Structure.STRUCTURE20)
					batcher.drawSprite(commender.position.x,
							commender.position.y,
							Commander.COMMANDER_ALLY_WIDTH,
							Commander.COMMANDER_ALLY_HEIHGT,
							Manage_Assets.ttgAllyStructureParticle60);
				else
					batcher.drawSprite(commender.position.x,
							commender.position.y,
							Commander.COMMANDER_ALLY_WIDTH,
							Commander.COMMANDER_ALLY_HEIHGT,
							Manage_Assets.ttgAllyStructureParticle40);
				batcher.endBatch();
			} else {
				if (commender.getnHealthPoint() > 0) {
					batcher.beginBatch(Manage_Assets.ttEnemyStructureParticle);
					if ((float) (commender.getnHealthPoint() / (commender
							.getnFullHealThPoint())) > Structure.STRUCTURE80)
						batcher.drawSprite(commender.position.x,
								commender.position.y,
								Commander.COMMANDER_ENEMY_WIDTH,
								Commander.COMMANDER_ALLY_HEIHGT,
								Manage_Assets.ttgEnemyStructureParticle100);
					else if ((float) (commender.getnHealthPoint() / (commender
							.getnFullHealThPoint())) > Structure.STRUCTURE60)
						batcher.drawSprite(commender.position.x,
								commender.position.y,
								Commander.COMMANDER_ENEMY_WIDTH,
								Commander.COMMANDER_ALLY_HEIHGT,
								Manage_Assets.ttgEnemyStructureParticle80);
					else if ((float) (commender.getnHealthPoint() / (commender
							.getnFullHealThPoint())) > Structure.STRUCTURE40)
						batcher.drawSprite(commender.position.x,
								commender.position.y,
								Commander.COMMANDER_ENEMY_WIDTH,
								Commander.COMMANDER_ALLY_HEIHGT,
								Manage_Assets.ttgEnemyStructureParticle60);
					else if ((float) (commender.getnHealthPoint() / (commender
							.getnFullHealThPoint())) > Structure.STRUCTURE20)
						batcher.drawSprite(commender.position.x,
								commender.position.y,
								Commander.COMMANDER_ENEMY_WIDTH,
								Commander.COMMANDER_ALLY_HEIHGT,
								Manage_Assets.ttgEnemyStructureParticle40);
					else
						batcher.drawSprite(commender.position.x,
								commender.position.y,
								Commander.COMMANDER_ENEMY_WIDTH,
								Commander.COMMANDER_ALLY_HEIHGT,
								Manage_Assets.ttgEnemyStructureParticle20);
					batcher.endBatch();
				}
			}
		}

	}

	private void renderObjectStructures(float deltaTime) {
		int len = MainGame_Manager.commander.size();

		if (len <= 0)
			return;

		for (int i = 0; i < len; i++) {
			Commander commender = MainGame_Manager.commander.get(i);
			if (commender.getFlag() == Commander.OURFORCE) {
				batcher.beginBatch(Manage_Assets.ttAllyStructure);

				if ((float) (commender.getnHealthPoint() / (commender
						.getnFullHealThPoint())) > Structure.STRUCTURE80)
					batcher.drawSprite(commender.position.x,
							commender.position.y,
							Commander.COMMANDER_ALLY_WIDTH,
							Commander.COMMANDER_ALLY_HEIHGT,
							Manage_Assets.ttgAllyStructure100);
				else if ((float) (commender.getnHealthPoint() / (commender
						.getnFullHealThPoint())) > Structure.STRUCTURE60)
					batcher.drawSprite(commender.position.x,
							commender.position.y,
							Commander.COMMANDER_ALLY_WIDTH,
							Commander.COMMANDER_ALLY_HEIHGT,
							Manage_Assets.ttgAllyStructure80);
				else if ((float) (commender.getnHealthPoint() / (commender
						.getnFullHealThPoint())) > Structure.STRUCTURE40)
					batcher.drawSprite(commender.position.x,
							commender.position.y,
							Commander.COMMANDER_ALLY_WIDTH,
							Commander.COMMANDER_ALLY_HEIHGT,
							Manage_Assets.ttgAllyStructure60);
				else if ((float) (commender.getnHealthPoint() / (commender
						.getnFullHealThPoint())) > Structure.STRUCTURE20)
					batcher.drawSprite(commender.position.x,
							commender.position.y,
							Commander.COMMANDER_ALLY_WIDTH,
							Commander.COMMANDER_ALLY_HEIHGT,
							Manage_Assets.ttgAllyStructure40);
				else
					batcher.drawSprite(commender.position.x,
							commender.position.y,
							Commander.COMMANDER_ALLY_WIDTH,
							Commander.COMMANDER_ALLY_HEIHGT,
							Manage_Assets.ttgAllyStructure20);
				batcher.endBatch();
			} else {
				if (commender.getnHealthPoint() > 0) {
					batcher.beginBatch(Manage_Assets.ttEnemyStructure);
					if ((float) (commender.getnHealthPoint() / (commender
							.getnFullHealThPoint())) > Structure.STRUCTURE80)
						batcher.drawSprite(commender.position.x,
								commender.position.y,
								Commander.COMMANDER_ENEMY_WIDTH,
								Commander.COMMANDER_ENEMY_HEIHGT,
								Manage_Assets.ttgEnemyStructure100);
					else if ((float) (commender.getnHealthPoint() / (commender
							.getnFullHealThPoint())) > Structure.STRUCTURE60)
						batcher.drawSprite(commender.position.x,
								commender.position.y,
								Commander.COMMANDER_ENEMY_WIDTH,
								Commander.COMMANDER_ENEMY_HEIHGT,
								Manage_Assets.ttgEnemyStructure80);
					else if ((float) (commender.getnHealthPoint() / (commender
							.getnFullHealThPoint())) > Structure.STRUCTURE40)
						batcher.drawSprite(commender.position.x,
								commender.position.y,
								Commander.COMMANDER_ENEMY_WIDTH,
								Commander.COMMANDER_ENEMY_HEIHGT,
								Manage_Assets.ttgEnemyStructure60);
					else if ((float) (commender.getnHealthPoint() / (commender
							.getnFullHealThPoint())) > Structure.STRUCTURE20)
						batcher.drawSprite(commender.position.x,
								commender.position.y,
								Commander.COMMANDER_ENEMY_WIDTH,
								Commander.COMMANDER_ENEMY_HEIHGT,
								Manage_Assets.ttgEnemyStructure40);
					else
						batcher.drawSprite(commender.position.x,
								commender.position.y,
								Commander.COMMANDER_ENEMY_WIDTH,
								Commander.COMMANDER_ENEMY_HEIHGT,
								Manage_Assets.ttgEnemyStructure20);
					batcher.endBatch();
				}
			}
		}

	}

	private void render_HPbar() {
		int len = MainGame_Manager.hpBar.size();
		if (len <= 0)
			return;

		batcher.beginBatch(Manage_Assets.ttEnergyBar);

		batcher.drawSprite(-1000, -1000, HpBar.HPBARWIDTH, HpBar.HPBARHEGIHT,
				Manage_Assets.ttgEneryBar04);

		for (int i = 0; i < len; i++) {
			HpBar hpbar = MainGame_Manager.hpBar.get(i);

			if (true == hpbar.isVisible())
				if (hpbar.getCurrentStateBar() == HpBar.FULLHP)
					batcher.drawSprite(hpbar.position.x, hpbar.position.y,
							HpBar.HPBARWIDTH, HpBar.HPBARHEGIHT,
							Manage_Assets.ttgEneryBar04);
				else if (hpbar.getCurrentStateBar() == HpBar.THREEHP)
					batcher.drawSprite(hpbar.position.x, hpbar.position.y,
							HpBar.HPBARWIDTH, HpBar.HPBARHEGIHT,
							Manage_Assets.ttgEneryBar03);
				else if (hpbar.getCurrentStateBar() == HpBar.TWOHP)
					batcher.drawSprite(hpbar.position.x, hpbar.position.y,
							HpBar.HPBARWIDTH, HpBar.HPBARHEGIHT,
							Manage_Assets.ttgEneryBar02);
				else if (hpbar.getCurrentStateBar() == HpBar.ONEHP)
					batcher.drawSprite(hpbar.position.x, hpbar.position.y,
							HpBar.HPBARWIDTH, HpBar.HPBARHEGIHT,
							Manage_Assets.ttgEneryBar01);
				else if (hpbar.getCurrentStateBar() == HpBar.ZEROHP)
					batcher.drawSprite(hpbar.position.x, hpbar.position.y,
							HpBar.HPBARWIDTH, HpBar.HPBARHEGIHT,
							Manage_Assets.ttgEneryBar00);
		}

		batcher.endBatch();
	}

	private void render_chr_ally_Attack_fiyld() {
		int len = MainGame_Manager.allyAttackFiyld.size();

		if (len <= 0)
			return;

		batcher.beginBatch(Manage_Assets.ttImgBulletGun);

		for (int i = 0; i < len; i++) {
			AttackFiyld allyattackfiyld = MainGame_Manager.allyAttackFiyld
					.get(i);
			batcher.drawSprite(allyattackfiyld.position.x,
					allyattackfiyld.position.y, allyattackfiyld.getfObjWidth(),
					allyattackfiyld.getfObjHeight(),
					Manage_Assets.ttgGameImgBullet02);
		}

		batcher.endBatch();
	}

	private void render_chr_enemy_Attack_fiyld() {
		int len = MainGame_Manager.enemyAttackFiyld.size();

		if (len <= 0)
			return;

		batcher.beginBatch(Manage_Assets.ttImgBulletGun);

		for (int i = 0; i < len; i++) {
			AttackFiyld attackfiyld = MainGame_Manager.enemyAttackFiyld.get(i);
			batcher.drawSprite(attackfiyld.position.x, attackfiyld.position.y,
					AttackFiyld.ATTACK_FIYLD_WIDTH,
					AttackFiyld.ATTACK_FIYLD_HEIHGT,
					Manage_Assets.ttgGameImgBullet02);
		}

		batcher.endBatch();
	}

	private void render_chr_enemy_bullet() {

		int len = MainGame_Manager.enemyBullet.size();

		if (len <= 0)
			return;

		batcher.beginBatch(Manage_Assets.ttImgBulletGun);

		for (int i = 0; i < len; i++) {
			Bullet enemyBullet = MainGame_Manager.enemyBullet.get(i);
			batcher.drawSprite(enemyBullet.position.x, enemyBullet.position.y,
					10, 10, (float) (enemyBullet.get_Rotate_Number() * -22.5),
					Manage_Assets.ttgGameImgBullet01);

		}

		batcher.endBatch();
	}

	private void render_chr_ally_energyBall(float deltaTime) {

		int len = MainGame_Manager.energyBall.size();

		if (len <= 0)
			return;

		for (int i = 0; i < len; i++) {

			EnergyBall energyBall = MainGame_Manager.energyBall.get(i);

			if (energyBall.getType() == EnergyBall.ENERGYBALL) {
				batcher.beginBatch(Manage_Assets.ttAlly_Monk);
				batcher.drawSprite(energyBall.position.x,
						energyBall.position.y, 10, 10,
						(float) (energyBall.get_Rotate_Number() * -11.25),
						Manage_Assets.ttgAllyMonkAttack);
			} else {
				batcher.beginBatch(Manage_Assets.ttAllyArcherMan);
				batcher.drawSprite(energyBall.position.x,
						energyBall.position.y, 30, 20,
						Manage_Assets.ttgAllyArcherArrow);
			}
			batcher.endBatch();

		}

	}

	private void render_chr_ally_archerMan(float deltaTime) {
		int len = MainGame_Manager.allyArchers.size();

		if (len <= 0)
			return;

		batcher.beginBatch(Manage_Assets.ttAllyArcherMan);

		for (int i = 0; i < len; i++) {
			AllyArcherMan allyArcher = MainGame_Manager.allyArchers.get(i);
			GL_TextureRegion _keyFrame;

			switch (allyArcher.getStageFlag()) {
			case EnemyGunMan.OBJ_D_LEFT:
				_keyFrame = Manage_Assets.anichrAllyArcherMov.getKeyFrame(
						allyArcher.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				break;
			case EnemyGunMan.OBJ_D_ATTAK:
				_keyFrame = Manage_Assets.anichrAllyArcherAttack.getKeyFrame(
						allyArcher.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				break;
			case EnemyGunMan.OBJ_D_DEATH:
				allyArcher.setfDeadTime(allyArcher.getfDeadTime() + deltaTime);
				_keyFrame = Manage_Assets.anichrAllyArcherDie.getKeyFrame(
						allyArcher.getfDeadTime(), allyArcher.getfDeadTime());
				if (true == Manage_Assets.anichrAllyArcherDie
						.getIsMaxFrameNum()) {
					Manage_Assets.anichrAllyArcherDie.intNframeNumber();
					MainGame_Manager.allyArchers.remove(allyArcher);
					len = MainGame_Manager.allyArchers.size();
				}
				break;
			default:
				_keyFrame = Manage_Assets.anichrAllyArcherMov.getKeyFrame(
						allyArcher.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				break;
			}

			batcher.drawSprite(allyArcher.position.x, allyArcher.position.y,
					250, 120, _keyFrame);

		}

		batcher.endBatch();
	}

	private void render_chr_ally_spearMan(float deltaTime) {
		batcher.beginBatch(Manage_Assets.ttAllySpearMan);

		int len = MainGame_Manager.allySpearMans.size();

		if (len <= 0)
			return;

		for (int i = 0; i < len; i++) {
			AllySpearMan allySpearMan = MainGame_Manager.allySpearMans.get(i);
			GL_TextureRegion _keyFrame;

			switch (allySpearMan.getStageFlag()) {
			case AllyAxeMan.OBJ_D_LEFT:
				_keyFrame = Manage_Assets.anichrAllySpearmanMov.getKeyFrame(
						allySpearMan.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				break;

			case AllyAxeMan.OBJ_D_ATTAK:
				_keyFrame = Manage_Assets.anichrAllySpearmanAttack.getKeyFrame(
						allySpearMan.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				break;
			case AllyAxeMan.OBJ_D_DEATH:
				allySpearMan.setfDeadTime(allySpearMan.getfDeadTime()
						+ deltaTime);
				_keyFrame = Manage_Assets.anichrAllySpearmanDie.getKeyFrame(
						allySpearMan.getfDeadTime(),
						allySpearMan.getfDeadTime());
				if (true == Manage_Assets.anichrAllySpearmanDie
						.getIsMaxFrameNum()) {
					Manage_Assets.anichrAllySpearmanDie.intNframeNumber();
					MainGame_Manager.allySpearMans.remove(allySpearMan);
					len = MainGame_Manager.allySpearMans.size();
				}
				break;
			default:
				_keyFrame = Manage_Assets.anichrAllySpearmanMov.getKeyFrame(
						allySpearMan.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				break;

			}
			batcher.drawSprite(allySpearMan.position.x,
					allySpearMan.position.y,
					AllySpearMan.ALLY_SPEAR_WIDTH_R * 3 + 30,
					AllySpearMan.ALLY_SPEAR_HEIGHT_R + 20, _keyFrame);
		}

		batcher.endBatch();
	}

	private void render_chr_ally_monkMan(float deltaTime) {
		int len = MainGame_Manager.allyMonkMans.size();

		if (len <= 0)
			return;

		batcher.beginBatch(Manage_Assets.ttAlly_Monk);

		for (int i = 0; i < len; i++) {
			AllyMonkMan allyMonk = MainGame_Manager.allyMonkMans.get(i);
			GL_TextureRegion _keyFrame;

			switch (allyMonk.getStageFlag()) {
			case EnemyGunMan.OBJ_D_LEFT:
				_keyFrame = Manage_Assets.aniChrAllyMonkMove
						.getKeyFrame(allyMonk.getStateTime(),
								GL_Animation.ANIMATION_LOOPING);
				break;
			case EnemyGunMan.OBJ_D_ATTAK:
				_keyFrame = Manage_Assets.aniChrAllyMonkAttack
						.getKeyFrame(allyMonk.getStateTime(),
								GL_Animation.ANIMATION_LOOPING);
				break;
			case EnemyGunMan.OBJ_D_DEATH:
				allyMonk.setfDeadTime(allyMonk.getfDeadTime() + deltaTime);
				_keyFrame = Manage_Assets.aniChrAllyMonkDie.getKeyFrame(
						allyMonk.getfDeadTime(), allyMonk.getfDeadTime());
				if (true == Manage_Assets.aniChrAllyMonkDie.getIsMaxFrameNum()) {
					Manage_Assets.aniChrAllyMonkDie.intNframeNumber();
					MainGame_Manager.allyMonkMans.remove(allyMonk);
					len = MainGame_Manager.allyMonkMans.size();
				}
				break;
			default:
				_keyFrame = Manage_Assets.aniChrAllyMonkMove
						.getKeyFrame(allyMonk.getStateTime(),
								GL_Animation.ANIMATION_LOOPING);
				break;
			}

			batcher.drawSprite(allyMonk.position.x, allyMonk.position.y, 250,
					100, _keyFrame);

		}

		batcher.endBatch();

	}

	private void render_chr_ally_AxeMan(float deltaTime) {
		batcher.beginBatch(Manage_Assets.ttAlly_AxeMan);

		int len = MainGame_Manager.allyAxeMans.size();

		if (len <= 0)
			return;

		for (int i = 0; i < len; i++) {
			AllyAxeMan allyAxeMan = MainGame_Manager.allyAxeMans.get(i);
			GL_TextureRegion _keyFrame;

			switch (allyAxeMan.getStageFlag()) {
			case AllyAxeMan.OBJ_D_LEFT:
				_keyFrame = Manage_Assets.aniChrAllyAxeMove.getKeyFrame(
						allyAxeMan.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				break;

			case AllyAxeMan.OBJ_D_ATTAK:
				_keyFrame = Manage_Assets.aniChrAllyAxeAttack.getKeyFrame(
						allyAxeMan.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				break;
			case AllyAxeMan.OBJ_D_DEATH:
				allyAxeMan.setfDeadTime(allyAxeMan.getfDeadTime() + deltaTime);
				_keyFrame = Manage_Assets.aniChrAllyAxeDie.getKeyFrame(
						allyAxeMan.getfDeadTime(), allyAxeMan.getfDeadTime());
				if (true == Manage_Assets.aniChrAllyAxeDie.getIsMaxFrameNum()) {
					Manage_Assets.aniChrAllyAxeDie.intNframeNumber();
					MainGame_Manager.allyAxeMans.remove(allyAxeMan);
					len = MainGame_Manager.allyAxeMans.size();
				}
				break;
			default:
				_keyFrame = Manage_Assets.aniChrAllyAxeMove.getKeyFrame(
						allyAxeMan.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				break;

			}
			batcher.drawSprite(allyAxeMan.position.x, allyAxeMan.position.y,
					EnemySwordMan.ENEMY_WIDTH + 25,
					EnemySwordMan.ENEMY_SWORDMAN_HEIGHT, _keyFrame);
		}

		batcher.endBatch();
	}

	private void render_chr_enemy_gun(float deltaTime) {
		int len = MainGame_Manager.enemyGunMans.size();

		if (len <= 0)
			return;

		for (int i = 0; i < len; i++) {
			EnemyGunMan enemyGunMan = MainGame_Manager.enemyGunMans.get(i);
			GL_TextureRegion _keyFrame;

			if (enemyGunMan.getStageFlag() != EnemyGunMan.OBJ_D_RUNAWAY)
				batcher.beginBatch(Manage_Assets.ttChrEnemyGun);
			else
				batcher.beginBatch(Manage_Assets.ttLoadingWindowCharecter);

			switch (enemyGunMan.getStageFlag()) {
			case EnemyGunMan.OBJ_D_LEFT:
				_keyFrame = Manage_Assets.aniChrEnemyGunMov.getKeyFrame(
						enemyGunMan.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				break;
			case EnemyGunMan.OBJ_D_ATTAK:
				_keyFrame = Manage_Assets.aniChrEnemyGunShutDown.getKeyFrame(
						enemyGunMan.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				break;
			case EnemyGunMan.OBJ_D_RUNAWAY:
				_keyFrame = Manage_Assets.aniCryGunEnemy.getKeyFrame(
						enemyGunMan.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				break;
			case EnemyGunMan.OBJ_D_DEATH:
				enemyGunMan
						.setfDeadTime(enemyGunMan.getfDeadTime() + deltaTime);
				_keyFrame = Manage_Assets.aniChrEnemyGunDie.getKeyFrame(
						enemyGunMan.getfDeadTime(), enemyGunMan.getfDeadTime());
				if (true == Manage_Assets.aniChrEnemyGunDie.getIsMaxFrameNum()) {
					Manage_Assets.aniChrEnemyGunDie.intNframeNumber();
					MainGame_Manager.enemyGunMans.remove(enemyGunMan);
					len = MainGame_Manager.enemyGunMans.size();
				}
				break;
			default:
				_keyFrame = Manage_Assets.aniChrEnemyGunMov.getKeyFrame(
						enemyGunMan.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				break;
			}

			if (enemyGunMan.getStageFlag() == EnemyGunMan.OBJ_D_DEATH)
				batcher.drawSprite(enemyGunMan.position.x,
						enemyGunMan.position.y, 250, 100, _keyFrame);
			else if (enemyGunMan.getStageFlag() == EnemyGunMan.OBJ_D_RUNAWAY)
				batcher.drawSprite(enemyGunMan.position.x,
						enemyGunMan.position.y, 250, 100, _keyFrame);
			else
				batcher.drawSprite(enemyGunMan.position.x,
						enemyGunMan.position.y, -250, 100, _keyFrame);

			batcher.endBatch();

		}

	}

	private void render_chr_enemy_sword(float deltaTime) {
		int len = MainGame_Manager.enemySwordMans.size();

		if (len <= 0)
			return;

		for (int i = 0; i < len; i++) {
			EnemySwordMan enemySwordMan = MainGame_Manager.enemySwordMans
					.get(i);
			GL_TextureRegion _keyFrame;

			if (enemySwordMan.getStageFlag() != EnemyGunMan.OBJ_D_RUNAWAY)
				batcher.beginBatch(Manage_Assets.ttChrEnemySword);
			else
				batcher.beginBatch(Manage_Assets.ttLoadingWindowCharecter);

			switch (enemySwordMan.getStageFlag()) {
			case EnemySwordMan.OBJ_D_LEFT:
				_keyFrame = Manage_Assets.aniChrEnemySwordMov.getKeyFrame(
						enemySwordMan.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				break;

			case EnemySwordMan.OBJ_D_ATTAK:
				_keyFrame = Manage_Assets.aniChrEnemySwordAttack.getKeyFrame(
						enemySwordMan.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				break;
			case EnemyGunMan.OBJ_D_RUNAWAY:
				_keyFrame = Manage_Assets.aniCrySwordEnemy.getKeyFrame(
						enemySwordMan.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				break;
			case EnemySwordMan.OBJ_D_DEATH:
				enemySwordMan.setfDeadTime(enemySwordMan.getfDeadTime()
						+ deltaTime);
				_keyFrame = Manage_Assets.aniChrEnemySwordDie.getKeyFrame(
						enemySwordMan.getfDeadTime(),
						enemySwordMan.getfDeadTime());
				if (true == Manage_Assets.aniChrEnemySwordDie
						.getIsMaxFrameNum()) {
					Manage_Assets.aniChrEnemySwordDie.intNframeNumber();
					MainGame_Manager.enemySwordMans.remove(enemySwordMan);
					len = MainGame_Manager.enemySwordMans.size();
				}
				break;
			default:
				_keyFrame = Manage_Assets.aniChrEnemySwordMov.getKeyFrame(
						enemySwordMan.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				break;

			}
			if (enemySwordMan.getStageFlag() == EnemySwordMan.OBJ_D_DEATH)
				batcher.drawSprite(enemySwordMan.position.x,
						enemySwordMan.position.y, EnemySwordMan.ENEMY_WIDTH,
						EnemySwordMan.ENEMY_SWORDMAN_HEIGHT, _keyFrame);
			else if (enemySwordMan.getStageFlag() == EnemySwordMan.OBJ_D_RUNAWAY)
				batcher.drawSprite(enemySwordMan.position.x,
						enemySwordMan.position.y, EnemySwordMan.ENEMY_WIDTH,
						EnemySwordMan.ENEMY_SWORDMAN_HEIGHT, _keyFrame);
			else
				batcher.drawSprite(enemySwordMan.position.x,
						enemySwordMan.position.y, -EnemySwordMan.ENEMY_WIDTH,
						EnemySwordMan.ENEMY_SWORDMAN_HEIGHT, _keyFrame);
			batcher.endBatch();
		}
	}

	private void render_chr_enemy_finalBoss(float deltaTime) {
		int len = MainGame_Manager.enemyFinalBoss.size();

		if (len <= 0)
			return;

		for (int i = 0; i < len; i++) {
			EnemyFinalBossMan enemyFinalBoss = MainGame_Manager.enemyFinalBoss
					.get(i);
			GL_TextureRegion keyFrame;

			switch (enemyFinalBoss.getStageFlag()) {
			case EnemyFinalBossMan.OBJ_D_LEFT:
				keyFrame = Manage_Assets.aniDoyoMov.getKeyFrame(
						enemyFinalBoss.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				Manage_Assets.aniDoyoAttack01.intNframeNumber();
				Manage_Assets.aniDoyoAttack02.intNframeNumber();
				Manage_Assets.aniDoyoAttack03.intNframeNumber();
				Manage_Assets.aniDoyoAttack04.intNframeNumber();
				Manage_Assets.aniDoyoSkill01.intNframeNumber();
				break;
			case EnemyFinalBossMan.OBJ_D_RIGHT:
				keyFrame = Manage_Assets.aniDoyoMov.getKeyFrame(
						enemyFinalBoss.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				Manage_Assets.aniDoyoAttack01.intNframeNumber();
				Manage_Assets.aniDoyoAttack02.intNframeNumber();
				Manage_Assets.aniDoyoAttack03.intNframeNumber();
				Manage_Assets.aniDoyoAttack04.intNframeNumber();
				Manage_Assets.aniDoyoSkill01.intNframeNumber();
				break;

			// attack01
			case EnemyFinalBossMan.OBJ_D_ATTAK01:
				keyFrame = Manage_Assets.aniDoyoAttack01.getKeyFrame(deltaTime);
				break;
			// attack02
			case EnemyFinalBossMan.OBJ_D_ATTAK02:
				keyFrame = Manage_Assets.aniDoyoAttack02.getKeyFrame(deltaTime);
				break;
			// attack03
			case EnemyFinalBossMan.OBJ_D_ATTAK03:
				keyFrame = Manage_Assets.aniDoyoAttack03.getKeyFrame(deltaTime);
				break;
			// attack04
			case EnemyFinalBossMan.OBJ_D_ATTAK04:
				keyFrame = Manage_Assets.aniDoyoAttack04.getKeyFrame(deltaTime);
				break;
			// skill01
			case EnemyFinalBossMan.OBJ_D_SKill01:
				keyFrame = Manage_Assets.aniDoyoSkill01.getKeyFrame(deltaTime);
				break;
			case EnemyMiddleBossMan.OBJ_D_DEATH:
				enemyFinalBoss.setfDeadTime(enemyFinalBoss.getfDeadTime()
						+ deltaTime);
				keyFrame = Manage_Assets.aniDoyoDie.getKeyFrame(
						enemyFinalBoss.getfDeadTime(),
						enemyFinalBoss.getfDeadTime());
				if (true == Manage_Assets.aniDoyoDie.getIsMaxFrameNum()) {
					Manage_Assets.aniDoyoDie.intNframeNumber();
					MainGame_Manager.enemyFinalBoss.remove(enemyFinalBoss);
					len = MainGame_Manager.enemyFinalBoss.size();
				}
				break;

			default:
				keyFrame = Manage_Assets.aniDoyoMov.getKeyFrame(
						enemyFinalBoss.getStateTime(),
						GL_Animation.ANIMATION_LOOPING);
				Manage_Assets.aniDoyoAttack01.intNframeNumber();
				Manage_Assets.aniDoyoAttack02.intNframeNumber();
				Manage_Assets.aniDoyoAttack03.intNframeNumber();
				Manage_Assets.aniDoyoAttack04.intNframeNumber();
				Manage_Assets.aniDoyoSkill01.intNframeNumber();
			}

			// attack01
			if (enemyFinalBoss.getStageFlag() == EnemyFinalBossMan.OBJ_D_ATTAK01) {

				if (Manage_Assets.aniDoyoAttack01.getIsMaxFrameNum() == false) {
					batcher.beginBatch(Manage_Assets.ttDoyoAttack01);
				} else {
					batcher.beginBatch(Manage_Assets.ttDoyoAttack01);

					MainGame_Manager.enemyAttackFiyld.add(new AttackFiyld(
							enemyFinalBoss.position.x - 55,
							enemyFinalBoss.position.y, AttackFiyld.DEFUALT));
				}
				// attack02
			} else if (enemyFinalBoss.getStageFlag() == EnemyFinalBossMan.OBJ_D_ATTAK02) {

				if (Manage_Assets.aniDoyoAttack02.getIsMaxFrameNum() == false) {
					batcher.beginBatch(Manage_Assets.ttDoyoAttack02);
				} else {
					batcher.beginBatch(Manage_Assets.ttDoyoAttack02);
					MainGame_Manager.enemyAttackFiyld.add(new AttackFiyld(
							enemyFinalBoss.position.x - 55,
							enemyFinalBoss.position.y, AttackFiyld.DEFUALT));
				}

				// attack03
			} else if (enemyFinalBoss.getStageFlag() == EnemyFinalBossMan.OBJ_D_ATTAK03) {

				if (Manage_Assets.aniDoyoAttack03.getIsMaxFrameNum() == false) {
					batcher.beginBatch(Manage_Assets.ttDoyoAttack03);
				} else {
					batcher.beginBatch(Manage_Assets.ttDoyoAttack03);
					MainGame_Manager.enemyAttackFiyld.add(new AttackFiyld(
							enemyFinalBoss.position.x - 55,
							enemyFinalBoss.position.y, AttackFiyld.DEFUALT));
				}

				// attack04
			} else if (enemyFinalBoss.getStageFlag() == EnemyFinalBossMan.OBJ_D_ATTAK04) {

				if (Manage_Assets.aniDoyoAttack04.getIsMaxFrameNum() == false) {
					batcher.beginBatch(Manage_Assets.ttDoyoAttack04);
				} else {
					batcher.beginBatch(Manage_Assets.ttDoyoAttack04);
					MainGame_Manager.enemyAttackFiyld.add(new AttackFiyld(
							enemyFinalBoss.position.x - 55,
							enemyFinalBoss.position.y, AttackFiyld.DEFUALT));
				}

				// skill01
			} else if (enemyFinalBoss.getStageFlag() == EnemyFinalBossMan.OBJ_D_SKill01) {

				if (Manage_Assets.aniDoyoSkill01.getIsMaxFrameNum() == false) {
					batcher.beginBatch(Manage_Assets.ttDoyoSkill01);
				} else {
					batcher.beginBatch(Manage_Assets.ttDoyoSkill01);

					for (int ij = 0; ij < 10; ij++)
						MainGame_Manager.enemyBullet.add(new Bullet(
								enemyFinalBoss.position.x - 55,
								enemyFinalBoss.position.y - 30
										+ rnd.nextInt(20)));

				}

				// doyo Deadth
			} else if (enemyFinalBoss.getStageFlag() == EnemyFinalBossMan.OBJ_D_DEATH) {

				if (Manage_Assets.aniDoyoDie.getIsMaxFrameNum() == false) {
					batcher.beginBatch(Manage_Assets.ttDoyoDie);
				}
			} else {
				batcher.beginBatch(Manage_Assets.ttDoyoMove);

			}
			if (enemyFinalBoss.getStageFlag() != EnemyFinalBossMan.OBJ_D_SKill01) {
				batcher.drawSprite(enemyFinalBoss.position.x,
						enemyFinalBoss.position.y,
						EnemyFinalBossMan.ENEMY_WIDTH,
						EnemyFinalBossMan.ENEMY_SWORDMAN_HEIGHT, keyFrame);
				batcher.endBatch();
			} else {
				batcher.drawSprite(enemyFinalBoss.position.x,
						enemyFinalBoss.position.y,
						EnemyFinalBossMan.ENEMY_WIDTH + 100,
						EnemyFinalBossMan.ENEMY_SWORDMAN_HEIGHT + 5, keyFrame);
				batcher.endBatch();
			}

			// Log.e("test","test :"+ enemyFinalBoss.getStageFlag());

			if (Manage_Assets.aniDoyoAttack01.getIsMaxFrameNum()) {
				enemyFinalBoss.setStateFlag(Object_Dynamic.OBJ_D_STOP);
				Manage_Assets.aniDoyoAttack01.intNframeNumber();
			}
			if (Manage_Assets.aniDoyoAttack02.getIsMaxFrameNum()) {
				enemyFinalBoss.setStateFlag(Object_Dynamic.OBJ_D_STOP);
				Manage_Assets.aniDoyoAttack02.intNframeNumber();
			}
			if (Manage_Assets.aniDoyoAttack03.getIsMaxFrameNum()) {
				enemyFinalBoss.setStateFlag(Object_Dynamic.OBJ_D_STOP);
				Manage_Assets.aniDoyoAttack03.intNframeNumber();
			}
			if (Manage_Assets.aniDoyoAttack04.getIsMaxFrameNum()) {
				enemyFinalBoss.setStateFlag(Object_Dynamic.OBJ_D_STOP);
				Manage_Assets.aniDoyoAttack04.intNframeNumber();
			}
			if (Manage_Assets.aniDoyoSkill01.getIsMaxFrameNum()) {
				enemyFinalBoss.setStateFlag(Object_Dynamic.OBJ_D_STOP);
				Manage_Assets.aniDoyoSkill01.intNframeNumber();
			}
		}

	}

	private void render_chr_enemy_middleBoss(float deltaTime) {

		int len = MainGame_Manager.enemyMiddleBoss01.size();

		if (len <= 0)
			return;

		for (int i = 0; i < len; i++) {
			EnemyMiddleBossMan enemyMiddleBoss = MainGame_Manager.enemyMiddleBoss01
					.get(i);
			GL_TextureRegion _keyFrame;
			if (enemyMiddleBoss.getType() == EnemyMiddleBossMan.TYPE01) {
				batcher.beginBatch(Manage_Assets.ttChrMiddleBoss01);
				switch (enemyMiddleBoss.getStageFlag()) {
				case EnemyMiddleBossMan.OBJ_D_LEFT:
					_keyFrame = Manage_Assets.aniChrMiddleBoss01Mov
							.getKeyFrame(enemyMiddleBoss.getStateTime(),
									GL_Animation.ANIMATION_LOOPING);
					break;

				case EnemyMiddleBossMan.OBJ_D_ATTAK:
					_keyFrame = Manage_Assets.aniChrMiddleBoss01Attack
							.getKeyFrame(enemyMiddleBoss.getStateTime(),
									GL_Animation.ANIMATION_LOOPING);
					break;
				case EnemyMiddleBossMan.OBJ_D_DEATH:
					enemyMiddleBoss.setfDeadTime(enemyMiddleBoss.getfDeadTime()
							+ deltaTime);
					_keyFrame = Manage_Assets.aniChrMiddleBoss01Die
							.getKeyFrame(enemyMiddleBoss.getfDeadTime(),
									enemyMiddleBoss.getfDeadTime());
					if (true == Manage_Assets.aniChrMiddleBoss01Die
							.getIsMaxFrameNum()) {
						Manage_Assets.aniChrMiddleBoss01Die.intNframeNumber();
						MainGame_Manager.enemyMiddleBoss01
								.remove(enemyMiddleBoss);
						len = MainGame_Manager.enemyMiddleBoss01.size();
					}
					break;
				default:
					_keyFrame = Manage_Assets.aniChrMiddleBoss01Mov
							.getKeyFrame(enemyMiddleBoss.getStateTime(),
									GL_Animation.ANIMATION_LOOPING);
					break;

				}
				batcher.drawSprite(enemyMiddleBoss.position.x,
						enemyMiddleBoss.position.y,
						EnemyMiddleBossMan.ENEMY_WIDTH,
						EnemyMiddleBossMan.ENEMY_SWORDMAN_HEIGHT, _keyFrame);
				batcher.endBatch();
			} else {
				batcher.beginBatch(Manage_Assets.ttChrMiddleBoss02);
				switch (enemyMiddleBoss.getStageFlag()) {
				case EnemyMiddleBossMan.OBJ_D_LEFT:
					_keyFrame = Manage_Assets.aniChrMiddleBoss02Mov
							.getKeyFrame(enemyMiddleBoss.getStateTime(),
									GL_Animation.ANIMATION_LOOPING);
					break;

				case EnemyMiddleBossMan.OBJ_D_ATTAK:
					_keyFrame = Manage_Assets.aniChrMiddleBoss02Attack
							.getKeyFrame(enemyMiddleBoss.getStateTime(),
									GL_Animation.ANIMATION_LOOPING);
					break;
				case EnemyMiddleBossMan.OBJ_D_DEATH:
					enemyMiddleBoss.setfDeadTime(enemyMiddleBoss.getfDeadTime()
							+ deltaTime);
					_keyFrame = Manage_Assets.aniChrMiddleBoss02Die
							.getKeyFrame(enemyMiddleBoss.getfDeadTime(),
									enemyMiddleBoss.getfDeadTime());
					if (true == Manage_Assets.aniChrMiddleBoss02Die
							.getIsMaxFrameNum()) {
						Manage_Assets.aniChrMiddleBoss01Die.intNframeNumber();
						MainGame_Manager.enemyMiddleBoss01
								.remove(enemyMiddleBoss);
						len = MainGame_Manager.enemyMiddleBoss01.size();
					}
					break;
				default:
					_keyFrame = Manage_Assets.aniChrMiddleBoss02Mov
							.getKeyFrame(enemyMiddleBoss.getStateTime(),
									GL_Animation.ANIMATION_LOOPING);
					break;

				}
				batcher.drawSprite(enemyMiddleBoss.position.x,
						enemyMiddleBoss.position.y,
						EnemyMiddleBossMan.ENEMY_WIDTH,
						EnemyMiddleBossMan.ENEMY_SWORDMAN_HEIGHT, _keyFrame);
				batcher.endBatch();
			}
		}

	}

	private void render_LeeSunSin(float deltaTime) {

		GL_TextureRegion keyFrame;

		switch (MainGame_Manager.mLeeSunSin.getStageFlag()) {
		case LeeSunSin.OBJ_D_LEFT:
			keyFrame = Manage_Assets.aniLeesunsinWalking.getKeyFrame(
					MainGame_Manager.mLeeSunSin.getStateTime(),
					GL_Animation.ANIMATION_LOOPING);
			Manage_Assets.aniLeeSunShinSkill01.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill02.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill03.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill04.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill05.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill06.intNframeNumber();
			break;
		case LeeSunSin.OBJ_D_RIGHT:
			keyFrame = Manage_Assets.aniLeesunsinWalking.getKeyFrame(
					MainGame_Manager.mLeeSunSin.getStateTime(),
					GL_Animation.ANIMATION_LOOPING);
			Manage_Assets.aniLeeSunShinSkill01.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill02.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill03.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill04.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill05.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill06.intNframeNumber();
			break;

		case LeeSunSin.OBJ_D_DAMAGED:
			keyFrame = Manage_Assets.aniLeesunsinDamaged.getKeyFrame(
					MainGame_Manager.mLeeSunSin.getStateTime(),
					GL_Animation.ANIMATION_LOOPING);
			Manage_Assets.aniLeeSunShinSkill01.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill02.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill03.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill04.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill05.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill06.intNframeNumber();
			break;

		// 창 병 소환
		case LeeSunSin.OBJ_D_SKILL04:
			keyFrame = Manage_Assets.aniLeeSunShinSkill01
					.getKeyFrame(deltaTime);
			break;
		// 위에서 내려치기
		case LeeSunSin.OBJ_D_SKILL02:
			keyFrame = Manage_Assets.aniLeeSunShinSkill02
					.getKeyFrame(deltaTime);
			break;
		// x자 배기
		case LeeSunSin.OBJ_D_SKILL03:
			keyFrame = Manage_Assets.aniLeeSunShinSkill03
					.getKeyFrame(deltaTime);
			break;
		// 기공 신포
		case LeeSunSin.OBJ_D_SKILL06:
			keyFrame = Manage_Assets.aniLeeSunShinSkill04
					.getKeyFrame(deltaTime);
			break;
		// 기모아 불바람
		case LeeSunSin.OBJ_D_SKILL01:
			keyFrame = Manage_Assets.aniLeeSunShinSkill05
					.getKeyFrame(deltaTime);
			break;
		// 회오리 칼날
		case LeeSunSin.OBJ_D_SKILL05:
			keyFrame = Manage_Assets.aniLeeSunShinSkill06
					.getKeyFrame(deltaTime);
			break;

		default:
			keyFrame = Manage_Assets.aniLeesunsinWalking.getKeyFrame(
					MainGame_Manager.mLeeSunSin.getStateTime(),
					GL_Animation.ANIMATION_LOOPING);
			Manage_Assets.aniLeeSunShinSkill01.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill02.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill03.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill04.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill05.intNframeNumber();
			Manage_Assets.aniLeeSunShinSkill06.intNframeNumber();
		}

		// 전군 소환
		if (MainGame_Manager.mLeeSunSin.getStageFlag() == LeeSunSin.OBJ_D_SKILL04) {

			if (Manage_Assets.aniLeeSunShinSkill01.getIsMaxFrameNum() == false) {
				batcher.beginBatch(Manage_Assets.ttLeeSunShinSkill01);
			} else {
				batcher.beginBatch(Manage_Assets.ttLeesunsinWalking);
				MainGame_Manager.leesunsinSkillObjects.add(new SkillObject(
						MainGame_Manager.mLeeSunSin.position.x,
						MainGame_Manager.mLeeSunSin.position.y + 4,
						SkillObject.GENERATEMARINE));
			}
			// skill 02 +78

			if (MainGame_Manager.mLeeSunSin.getStageFlag() == LeeSunSin.OBJ_D_LEFT)
				batcher.drawSprite(MainGame_Manager.mLeeSunSin.position.x,
						MainGame_Manager.mLeeSunSin.position.y,
						-LeeSunSin.LEESUNSIN_WIDTH, LeeSunSin.LEESUNSIN_HEIGHT,
						keyFrame);
			else
				batcher.drawSprite(MainGame_Manager.mLeeSunSin.position.x,
						MainGame_Manager.mLeeSunSin.position.y,
						LeeSunSin.LEESUNSIN_WIDTH, LeeSunSin.LEESUNSIN_HEIGHT,
						keyFrame);
			batcher.endBatch();
			// 내려치지
		} else if (MainGame_Manager.mLeeSunSin.getStageFlag() == LeeSunSin.OBJ_D_SKILL02) {

			if (Manage_Assets.aniLeeSunShinSkill02.getIsMaxFrameNum() == false) {
				batcher.beginBatch(Manage_Assets.ttLeeSunShinSkill02);
			} else {
				batcher.beginBatch(Manage_Assets.ttLeesunsinWalking);
				for (int i = 0; i < MAXATTACKPOWRATTACK; i++)
					MainGame_Manager.allyAttackFiyld.add(new AttackFiyld(
							MainGame_Manager.mLeeSunSin.position.x + 85 + 40
									* i,
							MainGame_Manager.mLeeSunSin.position.y,
							AttackFiyld.POWERATTACK));
				Manage_Assets.playSound(Manage_Assets.soundLeeSkillBasic);
			}
			// skill 02 +78

			if (MainGame_Manager.mLeeSunSin.getStageFlag() == LeeSunSin.OBJ_D_LEFT)
				batcher.drawSprite(
						MainGame_Manager.mLeeSunSin.position.x + 130,
						MainGame_Manager.mLeeSunSin.position.y,
						-LeeSunSin.LEESUNSIN_WIDTH, LeeSunSin.LEESUNSIN_HEIGHT,
						keyFrame);
			else
				batcher.drawSprite(
						MainGame_Manager.mLeeSunSin.position.x + 130,
						MainGame_Manager.mLeeSunSin.position.y,
						LeeSunSin.LEESUNSIN_WIDTH, LeeSunSin.LEESUNSIN_HEIGHT,
						keyFrame);
			batcher.endBatch();

			// 십자 배기
		} else if (MainGame_Manager.mLeeSunSin.getStageFlag() == LeeSunSin.OBJ_D_SKILL03) {

			if (Manage_Assets.aniLeeSunShinSkill03.getIsMaxFrameNum() == false) {
				batcher.beginBatch(Manage_Assets.ttLeeSunShinSkill03);
			} else {
				batcher.beginBatch(Manage_Assets.ttLeesunsinWalking);
				MainGame_Manager.leesunsinSkillObjects.add(new SkillObject(
						MainGame_Manager.mLeeSunSin.position.x + 100,
						MainGame_Manager.mLeeSunSin.position.y,
						SkillObject.CROSSCUTTING));

				Manage_Assets.playSound(Manage_Assets.soundLeeSkillBasic);
			}
			// skill 02 +78

			if (MainGame_Manager.mLeeSunSin.getStageFlag() == LeeSunSin.OBJ_D_LEFT)
				batcher.drawSprite(MainGame_Manager.mLeeSunSin.position.x,
						MainGame_Manager.mLeeSunSin.position.y,
						-LeeSunSin.LEESUNSIN_WIDTH, LeeSunSin.LEESUNSIN_HEIGHT,
						keyFrame);
			else
				batcher.drawSprite(MainGame_Manager.mLeeSunSin.position.x,
						MainGame_Manager.mLeeSunSin.position.y,
						LeeSunSin.LEESUNSIN_WIDTH, LeeSunSin.LEESUNSIN_HEIGHT,
						keyFrame);
			batcher.endBatch();
			// 기공 신포
		} else if (MainGame_Manager.mLeeSunSin.getStageFlag() == LeeSunSin.OBJ_D_SKILL06) {

			if (Manage_Assets.aniLeeSunShinSkill04.getIsMaxFrameNum() == false) {
				batcher.beginBatch(Manage_Assets.ttLeeSunShinSkill04);
			} else {
				batcher.beginBatch(Manage_Assets.ttLeesunsinWalking);
				MainGame_Manager.leesunsinSkillObjects.add(new SkillObject(
						MainGame_Manager.mLeeSunSin.position.x + 100,
						MainGame_Manager.mLeeSunSin.position.y + 5,
						SkillObject.GIGONGSINPO));

				Manage_Assets.playSound(Manage_Assets.soundLeeSkillSpread);
			}
			// skill 02 +78

			if (MainGame_Manager.mLeeSunSin.getStageFlag() == LeeSunSin.OBJ_D_LEFT)
				batcher.drawSprite(MainGame_Manager.mLeeSunSin.position.x,
						MainGame_Manager.mLeeSunSin.position.y,
						-LeeSunSin.LEESUNSIN_WIDTH, LeeSunSin.LEESUNSIN_HEIGHT,
						keyFrame);
			else
				batcher.drawSprite(MainGame_Manager.mLeeSunSin.position.x,
						MainGame_Manager.mLeeSunSin.position.y,
						LeeSunSin.LEESUNSIN_WIDTH, LeeSunSin.LEESUNSIN_HEIGHT,
						keyFrame);
			batcher.endBatch();

			// 강 공격
		} else if (MainGame_Manager.mLeeSunSin.getStageFlag() == LeeSunSin.OBJ_D_SKILL01) {

			if (Manage_Assets.aniLeeSunShinSkill05.getIsMaxFrameNum() == false) {
				batcher.beginBatch(Manage_Assets.ttLeeSunShinSkill05);
			} else {
				batcher.beginBatch(Manage_Assets.ttLeesunsinWalking);
				MainGame_Manager.allyAttackFiyld.add(new AttackFiyld(
						MainGame_Manager.mLeeSunSin.position.x + 85,
						MainGame_Manager.mLeeSunSin.position.y,
						AttackFiyld.BASICATTACK));

				Manage_Assets.playSound(Manage_Assets.soundLeeSkillBasic);
				// skill 02 +78
			}

			if (MainGame_Manager.mLeeSunSin.getStageFlag() == LeeSunSin.OBJ_D_LEFT)
				batcher.drawSprite(MainGame_Manager.mLeeSunSin.position.x + 80,
						MainGame_Manager.mLeeSunSin.position.y,
						-LeeSunSin.LEESUNSIN_WIDTH, LeeSunSin.LEESUNSIN_HEIGHT,
						keyFrame);
			else
				batcher.drawSprite(MainGame_Manager.mLeeSunSin.position.x + 80,
						MainGame_Manager.mLeeSunSin.position.y,
						LeeSunSin.LEESUNSIN_WIDTH, LeeSunSin.LEESUNSIN_HEIGHT,
						keyFrame);
			batcher.endBatch();
			// 폭풍 어택
		} else if (MainGame_Manager.mLeeSunSin.getStageFlag() == LeeSunSin.OBJ_D_SKILL05) {

			if (Manage_Assets.aniLeeSunShinSkill06.getIsMaxFrameNum() == false) {
				batcher.beginBatch(Manage_Assets.ttLeeSunShinSkill06);
			} else {
				batcher.beginBatch(Manage_Assets.ttLeesunsinWalking);
				for (int i = 0; i < MAXATTACKTONADO; i++)
					MainGame_Manager.allyAttackFiyld.add(new AttackFiyld(
							MainGame_Manager.mLeeSunSin.position.x + 85 + 15
									* i,
							MainGame_Manager.mLeeSunSin.position.y,
							AttackFiyld.TORNADOATTACK));

				Manage_Assets.playSound(Manage_Assets.soundLeeSkillTonado);
				// skill 02 +78
			}

			if (MainGame_Manager.mLeeSunSin.getStageFlag() == LeeSunSin.OBJ_D_LEFT)
				batcher.drawSprite(MainGame_Manager.mLeeSunSin.position.x,
						MainGame_Manager.mLeeSunSin.position.y,
						-LeeSunSin.LEESUNSIN_WIDTH, LeeSunSin.LEESUNSIN_HEIGHT,
						keyFrame);
			else
				batcher.drawSprite(MainGame_Manager.mLeeSunSin.position.x,
						MainGame_Manager.mLeeSunSin.position.y + 20,
						LeeSunSin.LEESUNSIN_WIDTH + 120,
						LeeSunSin.LEESUNSIN_HEIGHT + 50, keyFrame);
			batcher.endBatch();
			// head bangbang
		} else if (MainGame_Manager.mLeeSunSin.getStageFlag() == LeeSunSin.OBJ_D_DAMAGED) {

			batcher.beginBatch(Manage_Assets.ttLeesunsinDamaged);

			if (MainGame_Manager.mLeeSunSin.getStageFlag() == LeeSunSin.OBJ_D_LEFT)
				batcher.drawSprite(MainGame_Manager.mLeeSunSin.position.x,
						MainGame_Manager.mLeeSunSin.position.y,
						-LeeSunSin.LEESUNSIN_WIDTH, LeeSunSin.LEESUNSIN_HEIGHT,
						keyFrame);
			else
				batcher.drawSprite(MainGame_Manager.mLeeSunSin.position.x,
						MainGame_Manager.mLeeSunSin.position.y,
						LeeSunSin.LEESUNSIN_WIDTH, LeeSunSin.LEESUNSIN_HEIGHT,
						keyFrame);
			batcher.endBatch();
		} else {
			batcher.beginBatch(Manage_Assets.ttLeesunsinWalking);

			if (MainGame_Manager.mLeeSunSin.getStageFlag() == LeeSunSin.OBJ_D_LEFT)
				batcher.drawSprite(MainGame_Manager.mLeeSunSin.position.x,
						MainGame_Manager.mLeeSunSin.position.y,
						-LeeSunSin.LEESUNSIN_WIDTH, LeeSunSin.LEESUNSIN_HEIGHT,
						keyFrame);
			else
				batcher.drawSprite(MainGame_Manager.mLeeSunSin.position.x,
						MainGame_Manager.mLeeSunSin.position.y,
						LeeSunSin.LEESUNSIN_WIDTH, LeeSunSin.LEESUNSIN_HEIGHT,
						keyFrame);
			batcher.endBatch();
		}

		if (Manage_Assets.aniLeeSunShinSkill01.getIsMaxFrameNum()) {
			MainGame_Manager.mLeeSunSin.setStateFlag(Object_Dynamic.OBJ_D_STOP);
			Manage_Assets.aniLeeSunShinSkill01.intNframeNumber();
		}
		if (Manage_Assets.aniLeeSunShinSkill02.getIsMaxFrameNum()) {
			MainGame_Manager.mLeeSunSin.setStateFlag(Object_Dynamic.OBJ_D_STOP);
			Manage_Assets.aniLeeSunShinSkill02.intNframeNumber();
		}
		if (Manage_Assets.aniLeeSunShinSkill03.getIsMaxFrameNum()) {
			MainGame_Manager.mLeeSunSin.setStateFlag(Object_Dynamic.OBJ_D_STOP);
			Manage_Assets.aniLeeSunShinSkill03.intNframeNumber();
		}
		if (Manage_Assets.aniLeeSunShinSkill04.getIsMaxFrameNum()) {
			MainGame_Manager.mLeeSunSin.setStateFlag(Object_Dynamic.OBJ_D_STOP);
			Manage_Assets.aniLeeSunShinSkill04.intNframeNumber();
		}
		if (Manage_Assets.aniLeeSunShinSkill05.getIsMaxFrameNum()) {
			MainGame_Manager.mLeeSunSin.setStateFlag(Object_Dynamic.OBJ_D_STOP);
			Manage_Assets.aniLeeSunShinSkill05.intNframeNumber();
		}
		if (Manage_Assets.aniLeeSunShinSkill06.getIsMaxFrameNum()) {
			MainGame_Manager.mLeeSunSin.setStateFlag(Object_Dynamic.OBJ_D_STOP);
			Manage_Assets.aniLeeSunShinSkill06.intNframeNumber();
		}
	}

	public GL_Camera2D get_Game_Cam() {
		return this.game_cam;
	}

}
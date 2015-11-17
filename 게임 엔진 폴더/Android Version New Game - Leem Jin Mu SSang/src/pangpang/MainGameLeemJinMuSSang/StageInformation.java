package pangpang.MainGameLeemJinMuSSang;

public class StageInformation {

	public static class Stage {

		public static class STAGEGENERATE {
			public static int generateEnemyGunManTime;
			public static int generateEnemySwordManTime;
			public static int generateEnemyBOSS01Time;
			public static int generateEnemyBOSS02Time;
			public static int generateEnemyFinalBossTime;

			public static void SettingStage(int stageNum) {
				if (stageNum == 0) {
					generateEnemyGunManTime = 1;
					generateEnemySwordManTime = 1;
					generateEnemyBOSS01Time = 1;
					generateEnemyBOSS02Time = 1;
					generateEnemyFinalBossTime = 0;

				} else if (stageNum == 1) {
					generateEnemyGunManTime = 1;
					generateEnemySwordManTime = 2;
					generateEnemyBOSS01Time = 1;
					generateEnemyBOSS02Time = 1;
					generateEnemyFinalBossTime = 0;

				} else if (stageNum == 2) {
					generateEnemyGunManTime = 2;
					generateEnemySwordManTime = 2;
					generateEnemyBOSS01Time = 1;
					generateEnemyBOSS02Time = 1;
					generateEnemyFinalBossTime = 0;

				} else if (stageNum == 3) {
					generateEnemyGunManTime = 2;
					generateEnemySwordManTime = 4;
					generateEnemyBOSS01Time = 1;
					generateEnemyBOSS02Time = 1;
					generateEnemyFinalBossTime = 1;

				} else if (stageNum == 4) {
					generateEnemyGunManTime = 3;
					generateEnemySwordManTime = 5;
					generateEnemyBOSS01Time = 1;
					generateEnemyBOSS02Time = 2;
					generateEnemyFinalBossTime = 0;

				} else if (stageNum == 5) {
					generateEnemyGunManTime = 3;
					generateEnemySwordManTime = 5;
					generateEnemyBOSS01Time = 2;
					generateEnemyBOSS02Time = 2;
					generateEnemyFinalBossTime = 0;

				} else if (stageNum == 6) {
					generateEnemyGunManTime = 4;
					generateEnemySwordManTime = 5;
					generateEnemyBOSS01Time = 1;
					generateEnemyBOSS02Time = 2;
					generateEnemyFinalBossTime = 0;

				} else if (stageNum == 7) {
					generateEnemyGunManTime = 4;
					generateEnemySwordManTime = 5;
					generateEnemyBOSS01Time = 1;
					generateEnemyBOSS02Time = 2;
					generateEnemyFinalBossTime = 1;

				} else {
					generateEnemyGunManTime = 3;
					generateEnemySwordManTime = 4;
					generateEnemyBOSS01Time = 2;
					generateEnemyBOSS02Time = 1;
				}

			}
		}

		public static class s1 {
			public static final int generateEnemyGunMan = 10;
			public static final int generateEnemySwordMan = 10;
			public static final int generateBoss = 3;

			public static final int oneStarMoney = 1000;
			public static final int TwoStarMoney = 1400;
			public static final int ThreeStarMoney = 2000;

			public static final int oneStarExp = 10000;
			public static final int TwoStarExp = 20000;
			public static final int ThreeStarExp = 30000;

			public static int Score = 0;
			public static int nStageStar = 0;
		}

		public static class s2 {
			public static final int generateEnemyGunMan = 15;
			public static final int generateEnemySwordMan = 15;
			public static final int generateBoss = 8;

			public static final int oneStarMoney = 1500;
			public static final int TwoStarMoney = 1900;
			public static final int ThreeStarMoney = 2500;

			public static final int oneStarExp = 15000;
			public static final int TwoStarExp = 25000;
			public static final int ThreeStarExp = 35000;

			public static int Score = 0;
			public static int nStageStar = 0;
		}

		public static class s3 {
			public static final int generateEnemyGunMan = 20;
			public static final int generateEnemySwordMan = 20;
			public static final int generateBoss = 9;

			public static final int oneStarMoney = 3000;
			public static final int TwoStarMoney = 4200;
			public static final int ThreeStarMoney = 8000;

			public static final int oneStarExp = 20000;
			public static final int TwoStarExp = 30000;
			public static final int ThreeStarExp = 40000;

			public static int Score = 0;
			public static int nStageStar = 0;
		}

		public static class s4 {
			public static final int generateEnemyGunMan = 20;
			public static final int generateEnemySwordMan = 20;
			public static final int generateBoss = 9;

			public static final int oneStarMoney = 3000;
			public static final int TwoStarMoney = 4200;
			public static final int ThreeStarMoney = 8000;

			public static final int oneStarExp = 20000;
			public static final int TwoStarExp = 30000;
			public static final int ThreeStarExp = 40000;

			public static int Score = 0;
			public static int nStageStar = 0;
		}

		public static class s5 {
			public static final int generateEnemyGunMan = 20;
			public static final int generateEnemySwordMan = 20;
			public static final int generateBoss = 9;

			public static final int oneStarMoney = 3000;
			public static final int TwoStarMoney = 4200;
			public static final int ThreeStarMoney = 8000;

			public static final int oneStarExp = 20000;
			public static final int TwoStarExp = 30000;
			public static final int ThreeStarExp = 40000;

			public static int Score = 0;
			public static int nStageStar = 0;
		}

		public static class s6 {
			public static final int generateEnemyGunMan = 20;
			public static final int generateEnemySwordMan = 20;
			public static final int generateBoss = 9;

			public static final int oneStarMoney = 3000;
			public static final int TwoStarMoney = 4200;
			public static final int ThreeStarMoney = 8000;

			public static final int oneStarExp = 20000;
			public static final int TwoStarExp = 30000;
			public static final int ThreeStarExp = 40000;

			public static int Score = 0;
			public static int nStageStar = 0;
		}

		public static class s7 {
			public static final int generateEnemyGunMan = 20;
			public static final int generateEnemySwordMan = 20;
			public static final int generateBoss = 9;

			public static final int oneStarMoney = 3000;
			public static final int TwoStarMoney = 4200;
			public static final int ThreeStarMoney = 8000;

			public static final int oneStarExp = 20000;
			public static final int TwoStarExp = 30000;
			public static final int ThreeStarExp = 40000;

			public static int Score = 0;
			public static int nStageStar = 0;
		}

		public static class s8 {
			public static final int generateEnemyGunMan = 20;
			public static final int generateEnemySwordMan = 20;
			public static final int generateBoss = 9;

			public static final int oneStarMoney = 3000;
			public static final int TwoStarMoney = 4200;
			public static final int ThreeStarMoney = 8000;

			public static final int oneStarExp = 20000;
			public static final int TwoStarExp = 30000;
			public static final int ThreeStarExp = 40000;

			public static int Score = 0;
			public static int nStageStar = 0;
		}
	}

}

package pangpang.MainGameLeemJinMuSSang;

import android.util.Log;

public class UnitInformation {

	static final int MAXDAMAGE = 70;
	static final int MAXHP = 700;
	static final int MAXSPEED = 200;

	static class ENEMY {

		static class GunMan {
			static final int Money = 100;
			static final int Exp = 100;

			static final int Damage01 = 10;
			static final int Damage02 = 20;
			static final int Damage03 = 30;

			static final int HP01 = 100;
			static final int HP02 = 200;
			static final int HP03 = 300;

		}

		static class SwordMan {
			static final int Money = 200;
			static final int Exp = 120;

			static final int Damage01 = 10;
			static final int Damage02 = 20;
			static final int Damage03 = 30;

			static final int HP01 = 100;
			static final int HP02 = 200;
			static final int HP03 = 300;
		}

		static class MiddleBoss {
			static final int Money = 400;
			static final int Exp = 100;

			static final int Damage01 = 10;
			static final int Damage02 = 20;
			static final int Damage03 = 30;

			static final int HP01 = 100;
			static final int HP02 = 200;
			static final int HP03 = 300;
		}

	}

	static class ALLY {
		static class MonkMan {
			static final int DamageBasic = 20;
			static final int HPBasic = 100;
			static final int Delay = 20;
			static final int SpeedBasic = 60;
			static final int NEEDSCORE = 1500;

			static final int UPGRADECOST01 = 1500;
			static final int UPGRADECOST02 = 3000;
			static final int UPGRADECOST03 = 4500;
			static final int UPGRADECOST04 = 6000;
			static final int UPGRADECOST05 = 7500;
			static final int UPGRADECOST06 = 9000;
			static final int UPGRADECOST07 = 10500;

			static float Bonus(int input) {
				float _result;
				if (1 == input) {
					_result = 0.1f;
				} else if (2 == input) {
					_result = 0.4f;
				} else if (3 == input) {
					_result = 0.9f;
				} else if (4 == input) {
					_result = 1.1f;
				} else if (5 == input) {
					_result = 1.4f;
				} else if (6 == input) {
					_result = 1.7f;
				} else if (7 == input) {
					_result = 2.0f;
				} else {
					Log.e("errorCode02_UnitInform", "outOfInitCharecterIndex");
					_result = 0f;
				}

				return _result;

			}

		}

		static class AxeMan {
			static final int DamageBasic = 10;
			static final int HPBasic = 100;
			static final int Delay = 10;
			static final int SpeedBasic = 60;
			static final int NEEDSCORE = 1000;

			static final int UPGRADECOST01 = 1000;
			static final int UPGRADECOST02 = 2000;
			static final int UPGRADECOST03 = 3000;
			static final int UPGRADECOST04 = 4000;
			static final int UPGRADECOST05 = 5000;
			static final int UPGRADECOST06 = 6000;
			static final int UPGRADECOST07 = 7000;

			static float Bonus(int input) {
				float _result;
				if (1 == input) {
					_result = 0.1f;
				} else if (2 == input) {
					_result = 0.4f;
				} else if (3 == input) {
					_result = 0.9f;
				} else if (4 == input) {
					_result = 1.1f;
				} else if (5 == input) {
					_result = 1.4f;
				} else if (6 == input) {
					_result = 1.7f;
				} else if (7 == input) {
					_result = 2.0f;
				} else {
					Log.e("errorCode02_UnitInform", "outOfInitCharecterIndex");
					_result = 0f;
				}

				return _result;

			}
		}

		static class ArcherMan {
			static final int DamageBasic = 10;
			static final int HPBasic = 70;
			static final int Delay = 10;
			static final int SpeedBasic = 60;
			static final int NEEDSCORE = 1800;

			static final int UPGRADECOST01 = 1800;
			static final int UPGRADECOST02 = 3800;
			static final int UPGRADECOST03 = 5400;
			static final int UPGRADECOST04 = 7200;
			static final int UPGRADECOST05 = 9000;
			static final int UPGRADECOST06 = 10800;
			static final int UPGRADECOST07 = 12600;

			static float Bonus(int input) {
				float _result;
				if (1 == input) {
					_result = 0.1f;
				} else if (2 == input) {
					_result = 0.4f;
				} else if (3 == input) {
					_result = 0.9f;
				} else if (4 == input) {
					_result = 1.1f;
				} else if (5 == input) {
					_result = 1.4f;
				} else if (6 == input) {
					_result = 1.7f;
				} else if (7 == input) {
					_result = 2.0f;
				} else {
					Log.e("errorCode02_UnitInform", "outOfInitCharecterIndex");
					_result = 0f;
				}

				return _result;

			}
		}

		static class SpearMan {
			static final int DamageBasic = 100;
			static final int HPBasic = 700;
			static final int Delay = 10;
			static final int SpeedBasic = 60;
			static final int NEEDSCORE = 18000;

			static final int UPGRADECOST01 = 18000;
			static final int UPGRADECOST02 = 38000;
			static final int UPGRADECOST03 = 54000;
			static final int UPGRADECOST04 = 72000;
			static final int UPGRADECOST05 = 90000;
			static final int UPGRADECOST06 = 108000;
			static final int UPGRADECOST07 = 126000;

			static float Bonus(int input) {
				float _result;
				if (1 == input) {
					_result = 0.1f;
				} else if (2 == input) {
					_result = 0.4f;
				} else if (3 == input) {
					_result = 0.9f;
				} else if (4 == input) {
					_result = 1.1f;
				} else if (5 == input) {
					_result = 1.4f;
				} else if (6 == input) {
					_result = 1.7f;
				} else if (7 == input) {
					_result = 2.0f;
				} else {
					Log.e("errorCode02_UnitInform", "outOfInitCharecterIndex");
					_result = 0f;
				}

				return _result;

			}
		}

		static class LeeSunSin {
		}
	}

}

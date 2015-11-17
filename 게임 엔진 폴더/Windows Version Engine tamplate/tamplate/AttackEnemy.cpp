#include "AttackEnemy.h"


AttackEnemy::AttackEnemy(Enemy** ap, Map_controller& mp)
{
	mmp = &mp;
	ey = ap;
	this->loop = 0;
}

void AttackEnemy::ResetAttack()
{
	mmp->dir;
	this->loop = 0;
}

void AttackEnemy::Attack(int enemyNum)
{
	int randomNumber;
	srand(time(NULL));

	if (mmp->getEnemyLiveCount() <= AttackEnemyNS::MAX_CRITICAL_ATTACK_NUMBER)
	{
		AttackAll();
		return;
	}

	loop++;
	int n = loop - (mmp->getAttackTIme() + 180);
	if (n < 0) return;					

	switch (n % 1800) {
	case 0:
		r1 = rand()%10 + 1;
		AttackPath(3, 1, r1);			
		AttackPath(3, 3, r1);
		AttackPath(2, 1, r1);
		break;
	case 150:
		r1 = rand() % 10 + 1;
		AttackPath(5, 4, r1);			
		AttackPath(5, 2, r1);
		AttackPath(4, 0, r1);
		break;
	case 300:
		r1 = rand() % 10 + 1;
		AttackPath(3, 0, r1);
		AttackPath(3, 2, r1);
		AttackPath(2, 4, r1);
		break;
	case 450:
		r1 = rand() % 10 + 1;
		AttackPath(0, 2, r1);
		AttackPath(1, 3, r1);
		AttackPath(1, 4, r1);
		break;
	case 600:
		r1 = rand() % 10 + 1;
		AttackPath(5, 3, r1);
		AttackPath(5, 5, r1);
		AttackPath(4, 6, r1);
		break;
	case 750:
		r1 = rand() % 10 + 1;
		AttackPath(3, 6, r1);
		AttackPath(3, 4, r1);
		AttackPath(2, 2, r1);
		break;
	case 900:
		r1 = rand() % 10 + 1;
		r2 = rand() % 10 + 1;
		AttackPath(2, 7, r1);
		AttackPath(2, 5, r1);
		AttackPath(0, 5, r2);
		AttackPath(1, 1, r2);
		break;
	case 1050:
		r1 = rand() % 10 + 1;
		r2 = rand() % 10 + 1;
		AttackPath(4, 6, r1);
		AttackPath(4, 5, r1);
		AttackPath(3, 5, r1);
		AttackPath(3, 7, r2);
		AttackPath(4, 4, r2);
		break;
	case 1200:
		r1 = rand() % 10 + 1;
		r2 = rand() % 10 + 1;
		AttackPath(5, 6, r1);
		AttackPath(5, 1, r1);
		AttackPath(2, 6, r2);
		AttackPath(2, 3, r2);
		break;
	case 1350:
		r1 = rand() % 10 + 1;
		r2 = rand() % 10 + 1;
		AttackPath(1, 2, r1);
		AttackPath(1, 6, r1);
		AttackPath(2, 0, r2);
		AttackPath(4, 3, r2);
		break;
	case 1600:
		r1 = rand() % 10 + 1;
		r2 = rand() % 10 + 1;
		AttackPath(4, 2, r1);
		AttackPath(4, 1, r1);
		AttackPath(1, 5, r2);
		AttackPath(5, 7, r2);
		break;
	}
}


AttackEnemy::~AttackEnemy()
{
}


void AttackEnemy::AttackPath(int kind, int num, int aKind) {
	ey[kind * 8 + num]->beginAttack(aKind);
}

void AttackEnemy::AttackAll() {
	for (int i = 0; i < 6; i++) {
		for (int j = 0; j < 8; j++) {
			if (ey[i * 8 + j]->getStatue() == EnemyNS::DIRECTION::SYNC)
				AttackPath(i, j, rand() % 10 + 1);			
		}
	}
}
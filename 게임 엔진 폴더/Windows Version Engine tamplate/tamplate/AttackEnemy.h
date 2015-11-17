#pragma once
class PangPang;

#include <stdio.h>    
#include <stdlib.h>    
#include <time.h>      
#include "Map_controller.h"
#include "Enemy.h"
#include "pangpang.h"

namespace AttackEnemyNS{
	const int MAX_CRITICAL_ATTACK_NUMBER = 10;
}

class AttackEnemy
{
private:
	int r1, r2, loop;
	Map_controller *mmp;
	Enemy **ey;

	void AttackPath(int kind, int num, int aKind);
	void AttackAll();
public:
	AttackEnemy(Enemy** ap,Map_controller& mp);
	~AttackEnemy();

	void ResetAttack();
	void Attack(int enemyNum);
};


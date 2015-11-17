#pragma once
#include <vector>

class Enemy_life
{
private :
	int enemyLife[6][8];
public:
	Enemy_life(char * map_data);
	~Enemy_life();
	int get_Enemy_Life(int kind, int num);
};


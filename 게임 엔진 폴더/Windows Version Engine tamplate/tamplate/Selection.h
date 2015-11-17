#pragma once
#include <vector>

namespace SelectionNS{
	const int ENEMY_ROW_MAX_NUMBER = 8;
}

class Selection
{
private :
	int pathNum[6][8];
	int enemy_ToTal_Cnt;

public:
	Selection(char * map_data);
	int getSelection(int kind, int num);
	int getEnemyCount();
	~Selection();
};


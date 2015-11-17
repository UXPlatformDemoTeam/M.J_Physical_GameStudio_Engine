#pragma once
#include <vector>


class Position
{
private:
	int positionX[6][8];
	int positionY[6][8];
	int enemy[6][8];
public:
	Position(char * map_data);
	~Position();
	int getEnemyNum(int kind, int num);
	int get_Pos_X(int kind, int num);
	int get_Pos_Y(int kind, int num);
};


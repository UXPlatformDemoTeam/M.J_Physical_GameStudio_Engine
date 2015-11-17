#include "Position.h"
#include "Map_controller.h"

Position::Position(char * map_data)
{
	std::vector<std::string> arr = split(map_data, "\n");
	std::string s;
	char ch;

	for (int i = 1; i < arr.size(); i++){
		s = arr[i];
		for (int j = 0; j < SelectionNS::ENEMY_ROW_MAX_NUMBER; j++)
		{
			ch = s.at(j);
			if (ch == '-')
				enemy[i - 1][j] = -1;
			else if (ch <= '9')
				enemy[i - 1][j] = ch - 48; 
			else
				enemy[i - 1][j] = ch - 87;	
		}
	}

	arr.clear();

	int top = 50; 
	int left = 190;
	int wid = 25;
	int x;

	
	for (int i = 0; i < 6; i++) {
		if (i <= 1) {
			for (int j = 0; j < 8; j++) {
				this->positionX[i][j] = j * wid + left;
				this->positionY[i][j] = ( i) * wid + top;
			} 
		}
		else {
			for (int j = 0; j < 8; j++) {
				if (j % 2 == 0)
					x = 3 - j / 2;
				else
					x = j / 2 + 4;
				this->positionX[i][j] = x * wid + left;
				this->positionY[i][j] = ( i) * wid + top;
			}
		} 
	}
}


Position::~Position()
{
}

int Position::getEnemyNum(int kind, int num)
{
	return enemy[kind][num];
}
int Position::get_Pos_X(int kind, int num)
{
	return this->positionX[kind][num];
}
int Position::get_Pos_Y(int kind, int num)
{
	return this->positionY[kind][num];
}
#include "Enemy_life.h"
#include "Map_controller.h"

Enemy_life::Enemy_life(char * map_data)
{
	std::vector<std::string> arr = split(map_data, "\n");
	std::string s;
	char ch;

	for (int i = 1; i < arr.size()-1; i++){
		s = arr[i];
		for (int j = 0; j < SelectionNS::ENEMY_ROW_MAX_NUMBER; j++)
		{
			ch = s.at(j);
			switch (ch)
			{
			case '-':
				enemyLife[i - 1][j] = -1;
				break;

			default:
				enemyLife[i - 1][j] = ch - 48;		
			}
		}
	}
}

int Enemy_life ::get_Enemy_Life(int kind, int num) {
	return enemyLife[kind][num];
}

Enemy_life::~Enemy_life()
{
}

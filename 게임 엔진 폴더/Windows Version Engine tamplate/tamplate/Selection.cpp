#include "Selection.h"
#include "Map_controller.h"

Selection::Selection(char * map_data)
{
	std::vector<std::string> arr = split(map_data, "\n");
	std::string s;
	enemy_ToTal_Cnt = 0;
	char ch;

	for (int i = 1; i < arr.size(); i++){
		s = arr[i];
		for (int j = 0; j < SelectionNS::ENEMY_ROW_MAX_NUMBER; j++)
		{
			ch = s.at(j);
			switch (ch)
			{
			case '-':
				pathNum[i - 1][j] = -1;
				break;

			default:
				enemy_ToTal_Cnt++;
				if (ch <= '9')
					pathNum[i - 1][j] = ch - 48;		
				else
					pathNum[i - 1][j] = ch - 87;		
			}
		}
	}

	arr.clear();
}

int Selection::getSelection(int kind, int num)
{
	return this->pathNum[kind][num];
}

int Selection::getEnemyCount(){
	return this->enemy_ToTal_Cnt;
}

Selection::~Selection()
{
}

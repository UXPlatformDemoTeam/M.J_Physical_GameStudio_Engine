#include "Delay.h"
#include "Map_controller.h"

Delay::Delay(char * map_data)
{
	std::vector<std::string> arr = split(map_data, "\n");
	std::string s;
	for (int i = 1; i < arr.size(); i++){
		for (int j = 0; j < 8; j++){
	
			s = trim(arr[i].substr(j*4, 4));
			if (s.compare("---") == 0 || s.compare("---\r") == 0)
				delay[i - 1][j] = -1;
			else
				delay[i - 1][j] = std::stoi(s);
		}
	}
}


int Delay::getDelay(int kind, int num)
{
	return this->delay[kind][num];
}


Delay::~Delay()
{
}

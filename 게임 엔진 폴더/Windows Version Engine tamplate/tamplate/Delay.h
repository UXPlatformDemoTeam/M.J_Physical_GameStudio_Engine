#pragma once
#include <vector>

class Delay
{
private :
	int delay[6][8];

public:
	Delay(char * map_data);
	~Delay();
	int getDelay(int kind, int num);
};


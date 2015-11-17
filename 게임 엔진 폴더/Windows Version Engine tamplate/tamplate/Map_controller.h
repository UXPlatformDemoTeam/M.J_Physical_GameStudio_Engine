#pragma once
#define WIN32_LEAN_AND_MEAN

#include <fstream>
#include <string>
#include <vector>
#include <string.h>
#include <stdio.h>
#include "gameError.h"
#include "Path.h"
#include "Selection.h"
#include "Delay.h"
#include "Position.h"
#include "Enemy_life.h"


namespace MAP_controllerNS{
	const int   MAXBUFFERSIZE = 1000;
	const float direction_dis_X[] = { 0.0f, 0.39f, 0.75f, 0.93f, 1.0f, 0.93f,
		0.75f, 0.39f, 0.0f, -0.39f, -0.75f, -0.93f, -1.0f, -0.93f, -0.75f,
		-0.39f };
	const float dircetion_dis_Y[] = { 1.0f, 0.93f, 0.75f, 0.39f, 0.0f, -0.39f,
		-0.75f, -0.93f, -1.0f, -0.93f, -0.75f, -0.39f, 0.0f, 0.39f, 0.75f,
		0.93f };

	const char STARGFILE[3][16] = { "stage01.winstg", "stage02.winstg", "stage03.winstg" };
}

class Map_controller
{
private:


	int enemyCnt;		
	int attackTime;		
	Path* mPath;			
	Selection *mSelect;		
	Delay *mDelay;		
	Position *mPosition;
	Enemy_life *mEnemy_Life; 

public:
	int syncCnt = 0;	
	int dirlen = 75;		
	int dir = 4;		
	int dirCnt = 0;		

	Map_controller();
	~Map_controller();
	void	readMap(int map_Number);
	void	makeMap(char *data);
	int		getEnemyLiveCount(){ return this->enemyCnt; }
	int		getAttackTIme(){ return this->attackTime; }
	SinglePath* getPath(int num){ return mPath->getPath(num); }
	float	getDirectionDisX(int tag){ return MAP_controllerNS::direction_dis_X[tag]; }
	float	getDIrectionDisY(int tag){ return MAP_controllerNS::dircetion_dis_Y[tag]; }
	int		getDelay(int kind, int num){ return this->mDelay->getDelay(kind, num); }
	int		getSelection(int kind, int num){ return mSelect->getSelection(kind, num); }
	int		getEnemyLife(int kind, int num){ return this->mEnemy_Life->get_Enemy_Life(kind, num); }
	int		getEnemyNum(int kind, int num){ return this->mPosition->getEnemyNum(kind, num); }
	int		getPositionX(int kind, int num){ return this->mPosition->get_Pos_X(kind, num); }
	int		getPositionY(int kind, int num){ return this->mPosition->get_Pos_Y(kind, num); }
	void	setEnemyCntMinus(){ this->enemyCnt--; }
	void	setEnemyCnt(int number){ this->enemyCnt = number; }
};

std::vector<std::string> split(std::string str, std::string sep);
std::string trim(std::string& str);
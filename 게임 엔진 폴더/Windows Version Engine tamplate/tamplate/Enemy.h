#pragma once
#include <stdio.h>    
#include <stdlib.h>    
#include <time.h>      

#include "constants.h"
#include "entity.h"
#include "Path.h"
#include "Map_controller.h"
#include "torpedo.h"




namespace EnemyNS
{
	const int	MAX_BULLET_NUMBER = 200;	
	const int   WIDTH = 50;                
	const int   HEIGHT = 50;              
	const int   X = GAME_WIDTH / 2 - WIDTH / 2; 
	const int   Y = GAME_HEIGHT - HEIGHT + 23;
	const float ROTATION_RATE = (float)PI; 
	const float SPEED = 10;                
	enum DIRECTION { ENTER, BEGINPOS, POSITION, SYNC, ATTACK, BEGINBACK, BACKPOS };    
	const int   TEXTURE_COLS = 4;          
	const int   WOMAN_FRONT_FRAME = 7;     
	const int	WOMAN_BACK_FRAM = 6;		
	const int	WOMAN_RIGHT_START_FRAME = 0;	
	const int	WOMAN_RIGHT_END_FRAME = 2;	
	const int	WOMAN_LEFT_START_FRAME = 3;
	const int	WOMAN_LEFT_END_FRAME = 5;		
	const float WOMAN_ANIMATION_DELAY = 0.2f;    
	const float TORPEDO_DAMAGE = 46;       
	const float WOMAN_SCALE = 0.7f;			
	const float DELAY_BULLET_TIME = 0.5f;	

	const int   SHIELD_START_FRAME = 24;    
	const int   SHIELD_END_FRAME = 27;      
	const float SHIELD_ANIMATION_DELAY = 0.1f; 

	const int MAX_MOVE_LIMITATION_SCOPE = 50;

	const int SCORE_PANEL_Y = 10;
	const int SCORE_PANEL_X = 20;
}

class Enemy : public Entity
{
private:
	float   oldX, oldY, oldAngle;
	float   rotation;				 
	EnemyNS::DIRECTION direction;  
	float   explosionTimer;
	bool    explosionOn;
	bool    engineOn;              
	bool    shieldOn;
	Image   engine;
	Image   shield;
	Image   explosion;
	int		loaf;
	bool	isDead;				
	int		status;					

	int		diff[4];				
	int		df;
	int		sncX;					
	SinglePath *sPath;			
	float	sx, sy;				
	int		a_Kind;					
	int		enemy_Img_number;		
	int		sKind, sNum;			
	int		pNum, col;				
	int		delay, dir, len;		
	int		posX, posY;				
	int		nShield;
	Map_controller *mmp;
	Torpedo *tp;

public:
	Enemy(Map_controller& mp, Torpedo &k);
	void makeEnemy(int kind, int num);
	void resetEnemy();
	void beginAttack(int aKind);

	int getCharacterNumber(){ return this->enemy_Img_number; }
	int getDir(){ return this->dir; }
	bool getIsDead(){ return this->isDead; }
	int getStatue(){ return this->status; }
	int getnShield() { return this->nShield; }
	void setnShield(int n) {this->nShield = n;}
	void setIsDead(boolean is) { this->nShield = 0; this->isDead = is; }

	virtual void draw();
	virtual bool initialize(Game *gamePtr, int width, int height, int ncols,
		TextureManager *textureM);

	void update(float frameTime);

	void setDirection(EnemyNS::DIRECTION dr){ this->direction = dr; }

	EnemyNS::DIRECTION getDirection()
	{
		return this->direction;
	}

private:
	void getPath(int num);
	void getDir(int col);
	void move();
	void enterEnemy();
	void beginPos();
	void position();
	void makeSync();
	void attack();
	void beginBackPos();
	void backPosition();
	void shootMissile(int dir);

	public :
	void damage(WEAPON weapon);
	void explode();
};


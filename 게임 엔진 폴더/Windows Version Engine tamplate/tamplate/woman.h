#ifndef _WOMAN_H             
#define _WOMAN_H                
#define WIN32_LEAN_AND_MEAN

#include "entity.h"
#include "constants.h"

namespace WomanNS
{
	const int   WIDTH = 70;                 
	const int   HEIGHT = 70;                
	const int   X = GAME_WIDTH / 2 - WIDTH / 2; 
	const int   Y = GAME_HEIGHT - HEIGHT+23;
	const float ROTATION_RATE = (float)PI; 
	const float SPEED = 100;               
	const float MASS = 300.0f;              
	enum DIRECTION { NONE, LEFT, RIGHT, UP };     
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
	const int MAX_BULLET_NUMBER = 30;		
	const float DELAY_BULLET_TIME = 0.5f;
	const COLOR_ARGB WOMAN_SCOLE_COLOR = graphicsNS::ORANGE;
	const int SCORE_PANEL_Y = 10;
	const int SCORE_PANEL_X = 20;
}

class Woman : public Entity
{
private:
	float   oldX, oldY, oldAngle;
	float   rotation;               
	WomanNS::DIRECTION direction;    
	float   explosionTimer;
	bool    explosionOn;
	bool    engineOn;              
	bool    shieldOn;
	Image   engine;
	Image   shield;
	Image   explosion;

public:
	Woman();

	virtual void draw();
	virtual bool initialize(Game *gamePtr, int width, int height, int ncols,
		TextureManager *textureM);
e
	void update(float frameTime);

	void setDirection(WomanNS::DIRECTION dr)
	{
		this->direction = dr;
	}

	WomanNS::DIRECTION getDirection()
	{
		return this->direction;
	}

};
#endif


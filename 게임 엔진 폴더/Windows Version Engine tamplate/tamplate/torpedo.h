#ifndef _TORPEDO_H             
#define _TORPEDO_H             
#define WIN32_LEAN_AND_MEAN

#include "entity.h"
#include "constants.h"

namespace torpedoNS
{
    const int   WIDTH = 32;             
    const int   HEIGHT = 32 ;         
    const int   COLLISION_RADIUS = 4;   
    const float SPEED = 400;           
    const float MASS = 300.0f;          
    const float FIRE_DELAY = 4.0f;      
    const int   TEXTURE_COLS = 8;      
    const int   START_FRAME = 43;      
    const int   END_FRAME = 46;         
    const float ANIMATION_DELAY = 0.1f; 

	const float BULLET_SPEED = -100;
}

class Torpedo : public Entity         
{
private:
    float   fireTimer;                  
public:
    Torpedo();

    void update(float frameTime);
    float getMass()    const {return torpedoNS::MASS;}

	void fire(Entity *ship,float fSpeed);         
};
#endif


#include "torpedo.h"
=
Torpedo::Torpedo() : Entity()
{
	active = false;                                
	spriteData.width = torpedoNS::WIDTH;     
	spriteData.height = torpedoNS::HEIGHT;
	spriteData.rect.bottom = torpedoNS::HEIGHT;   
	spriteData.rect.right = torpedoNS::WIDTH;
	cols = torpedoNS::TEXTURE_COLS;
	frameDelay = torpedoNS::ANIMATION_DELAY;
	startFrame = torpedoNS::START_FRAME;    
	endFrame = torpedoNS::END_FRAME;         
	currentFrame = startFrame;
	radius = torpedoNS::COLLISION_RADIUS;  
	visible = false;
	fireTimer = 0.0f;
	mass = torpedoNS::MASS;
	collisionType = entityNS::CIRCLE;
}

void Torpedo::update(float frameTime)
{
	fireTimer -= frameTime;                    

	if (visible == false)
		return;

	if (fireTimer < -2)                         
	{
		visible = false;                       
		active = false;
	}

	Image::update(frameTime);

	spriteData.x += frameTime * velocity.x;    
	spriteData.y += frameTime * velocity.y;     
}

void Torpedo::fire(Entity *ship, float fSpeed)
{
	if (fireTimer <= 4.0f)                      
	{

		if (fSpeed == 0)
			velocity.y = torpedoNS::BULLET_SPEED;
		else
			velocity.y = fSpeed;
		spriteData.x = ship->getCenterX() - spriteData.width / 2;
		spriteData.y = ship->getCenterY() - spriteData.height / 2;
		visible = true;                        
		active = true;                        
		fireTimer = torpedoNS::FIRE_DELAY;     
		audio->playCue(TORPEDO_FIRE);
	}
}


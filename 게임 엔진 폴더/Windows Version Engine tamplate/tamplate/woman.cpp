#include "woman.h"

Woman::Woman() : Entity()
{
	spriteData.width = WomanNS::WIDTH;        
	spriteData.height = WomanNS::HEIGHT;
	spriteData.x = WomanNS::X;                  
	spriteData.y = WomanNS::Y;
	spriteData.rect.bottom = WomanNS::HEIGHT;    
	spriteData.rect.right = WomanNS::WIDTH;
	oldAngle = 0.0f;
	rotation = 0.0f;
	velocity.x = 0;
	velocity.y = 0;
	frameDelay = WomanNS::WOMAN_ANIMATION_DELAY;
	startFrame = WomanNS::WOMAN_FRONT_FRAME;     
	endFrame = WomanNS::WOMAN_BACK_FRAM;    
	currentFrame = startFrame;
	collisionType = entityNS::BOX;
	direction = WomanNS::NONE;                 
	engineOn = false;
	shieldOn = false;
	explosionOn = false;
}

bool Woman::initialize(Game *gamePtr, int width, int height, int ncols,
	TextureManager *textureM)
{
	return(Entity::initialize(gamePtr, width, height, ncols, textureM));
}

void Woman::draw()
{
		Image::draw();             
}


void Woman::update(float frameTime)
{
	Entity::update(frameTime);
	
	switch (this->direction)                         
	{
	case WomanNS::LEFT:
		velocity.x = -WomanNS::SPEED;
		this->setFrameDelay(0.1f);
		this->setFrames(WomanNS::WOMAN_LEFT_START_FRAME, WomanNS::WOMAN_LEFT_END_FRAME);
		break;
	case WomanNS::RIGHT:
		this->setFrameDelay(0.1f);
		this->setFrames(WomanNS::WOMAN_RIGHT_START_FRAME, WomanNS::WOMAN_RIGHT_END_FRAME);
		velocity.x = +WomanNS::SPEED;
		break;

	case WomanNS::UP:
		velocity.x = 0;
		this->setCurrentFrame(WomanNS::WOMAN_FRONT_FRAME);
		break;
	}


	spriteData.x += frameTime * velocity.x;     
	spriteData.y += frameTime * velocity.y;     
}
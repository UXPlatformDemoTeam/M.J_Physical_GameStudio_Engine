#include "Enemy.h"


Enemy::Enemy(Map_controller& mp, Torpedo &k) : Entity()
{
	spriteData.width = EnemyNS::WIDTH;           
	spriteData.height = EnemyNS::HEIGHT;
	spriteData.x = EnemyNS::X;                  
	spriteData.y = EnemyNS::Y;
	spriteData.rect.bottom = EnemyNS::HEIGHT;    
	spriteData.rect.right = EnemyNS::WIDTH;
	oldAngle = 0.0f;
	rotation = 0.0f;
	velocity.x = 0;
	velocity.y = 0;
	frameDelay = EnemyNS::WOMAN_ANIMATION_DELAY;
	startFrame = EnemyNS::WOMAN_FRONT_FRAME;   
	endFrame = EnemyNS::WOMAN_BACK_FRAM;    
	currentFrame = startFrame;
	collisionType = entityNS::CIRCLE;        

	radius = EnemyNS::WIDTH / 2.0;
	engineOn = false;
	shieldOn = false;
	explosionOn = false;
	this->loaf = EnemyNS::MAX_MOVE_LIMITATION_SCOPE;
	mmp = &mp;
	tp = &k;
}

bool Enemy::initialize(Game *gamePtr, int width, int height, int ncols,
	TextureManager *textureM)
{
	shield.initialize(gamePtr->getGraphics(), width, height, ncols, textureM);
	shield.setFrames(EnemyNS::SHIELD_START_FRAME, EnemyNS::SHIELD_END_FRAME);
	shield.setCurrentFrame(EnemyNS::SHIELD_START_FRAME);
	shield.setFrameDelay(EnemyNS::SHIELD_ANIMATION_DELAY);
shield.setLoop(false);                


	return(Entity::initialize(gamePtr, width, height, ncols, textureM));
}

void Enemy::draw()
{
	Image::draw();            
}

void Enemy::makeEnemy(int kind, int num){
	this->df = 0;
	this->sKind = kind;
	this->sNum = num;

	if (mmp->getSelection(kind, num) == -1){
		isDead = true;
		return;
	}
	this->enemy_Img_number = mmp->getEnemyNum(sKind, num);

	resetEnemy();

	getPath(pNum);
}

void Enemy::resetEnemy() {
	velocity.x = 0;
	velocity.y = 0;

	this->pNum = mmp->getSelection(sKind, sNum);
	this->delay = mmp->getDelay(sKind, sNum);
	this->nShield = mmp->getEnemyLife(sKind, sNum);

	this->posX = mmp->getPositionX(sKind, sNum);
	this->posY = mmp->getPositionY(sKind, sNum);

	this->status = EnemyNS::DIRECTION::ENTER;
	this->isDead = false;
}


void Enemy::update(float frameTime)
{

	move();

	spriteData.x += frameTime * velocity.x;     
	spriteData.y += frameTime * velocity.y;    

	oldX = spriteData.x;                        
	oldY = spriteData.y;

	Entity::update(frameTime);

	if (shieldOn)
	{
		shield.update(frameTime);
		if (shield.getAnimationComplete())
		{
			shieldOn = false;
			shield.setAnimationComplete(false);
		}
	}
}

void Enemy::getPath(int num){
	sPath = mmp->getPath(num);
	
	if (sPath->getStartX() != -99)
		this->spriteData.x = sPath->getStartX();
	if (sPath->getStartY() != -99)
		this->spriteData.y = sPath->getStartY();

	this->col = 0;
	this->getDir(col);
}

void Enemy::getDir(int col){
	this->dir = sPath->getDirections()[col];
	this->len = sPath->getLens()[col];

	this->sx = mmp->getDirectionDisX(dir);
	this->sy = mmp->getDIrectionDisY(dir);
}

void Enemy::move() {
	if (this->isDead && (sKind != 5 || sNum != 0))
		return;

	switch (status) {
	case EnemyNS::DIRECTION::ENTER: 
		enterEnemy();
		break;
	case EnemyNS::DIRECTION::BEGINPOS: 
		beginPos();
		break;
	case EnemyNS::DIRECTION::POSITION: 
		position();
		break;
	case EnemyNS::DIRECTION::SYNC:
		makeSync();
		break;
	case EnemyNS::DIRECTION::ATTACK: 
		attack();
		break;
	case EnemyNS::DIRECTION::BEGINBACK:
		beginBackPos();
		break;
	case EnemyNS::DIRECTION::BACKPOS:
		backPosition();
	}
}

void Enemy::enterEnemy() {
	if (--delay >= 0)
		return;

	srand(time(NULL));

	velocity.x = (int)(sx * 100);
	velocity.y = (int)(sy * 100);

	int dr = rand()%5 + 6; 
	if (len % 500 == 0)
		shootMissile(dr);

	len--;
	if (len >= 0)
		return;

	col++;
	if (col < sPath->getSize()) {
		getDir(col);
	}
	else {
		status = EnemyNS::DIRECTION::BEGINPOS; 
	}
}

void Enemy::beginPos() {
	if (this->spriteData.x< posX + mmp->syncCnt) 

		dir = 2;
	else
		dir = 14; 

	if (this->spriteData.y < posY)
		dir = (dir == 2) ? 6 : 10;

	sx = mmp->getDirectionDisX(dir);
	sy = mmp->getDIrectionDisY(dir);
	status = EnemyNS::DIRECTION::POSITION; 
}

void Enemy::position() {
	velocity.x = (int)(sx * 170);
	velocity.y = (int)(sy * 170);
	if (this->spriteData.x < posX + mmp->syncCnt) 
		dir = 2;
	else
		dir = 14; 

	if (this->spriteData.y < posY)
		dir = (dir == 2) ? 2 : 14;

	if (std::abs(this->spriteData.y - posY) <= 4) {
		this->spriteData.y = posY;
		velocity.y = 0;
		if (this->spriteData.x < posX + mmp->syncCnt)
			dir = 4;
		else
			dir = 12; 
	}

	if (std::abs(this->spriteData.x - (posX + mmp->syncCnt)) <= 4) {
		this->spriteData.x = posX + mmp->syncCnt;
		velocity.x = 0;
		dir = 0; 
	}

	if (this->spriteData.y == posY
		&& this->spriteData.x == posX + mmp->syncCnt) {
		dir = 0; 
		sx = 1;
		status = EnemyNS::DIRECTION::SYNC;
		return; 
	}

	sx = mmp->getDirectionDisX(dir); 
	sy = mmp->getDIrectionDisY(dir); 
}

void Enemy::makeSync() {
	sncX = (int)mmp->getDirectionDisX(mmp->dir); 
	this->spriteData.x += sncX; 

	if (sKind == 5 && sNum == 0) { 
		mmp->syncCnt += sncX; 
		mmp->dirCnt++; 
		if (mmp->dirCnt >= mmp->dirlen) {
			mmp->dirCnt = 0; 
			mmp->dirlen = 150;
			mmp->dir = 16 - mmp->dir; 
		}
	}
}

void Enemy::beginAttack(int aKind) {

	if (isDead || (sKind == 5 && sNum == 0))
		return; 
	a_Kind = aKind; 
	getPath(a_Kind + 10);
	status = EnemyNS::DIRECTION::ATTACK;
}

void Enemy::attack() {

	velocity.x = (int)(sx * 100);
	velocity.y = (int)(sy * 100);

	if (this->spriteData.y < -loaf
		|| this->spriteData.y > GAME_HEIGHT + loaf
		|| this->spriteData.x < -loaf
		|| this->spriteData.x > GAME_WIDTH + loaf) {
		status = EnemyNS::DIRECTION::BEGINBACK;
		return;
	}

	len--;
	if (len >= 0)
		return;

	col++; 
	if (col < sPath->getSize()) {
		getDir(col); 
		if (dir >= 6 && dir <= 10)
			shootMissile(dir);
	}
	else {
		status = status = EnemyNS::DIRECTION::BEGINPOS; 
	}
}

void Enemy::beginBackPos() {
	this->spriteData.y = GAME_HEIGHT; ;
	this->spriteData.x = posX + mmp->syncCnt;
	velocity.x = 0;
	velocity.y = 0;

	status = EnemyNS::DIRECTION::BACKPOS;
}

void Enemy::backPosition() {
	
	sncX = (int)mmp->getDirectionDisX(mmp->dir);
	velocity.y = -60;
	this->spriteData.x += sncX;

	if (std::abs(this->spriteData.y - posY) <= 4) {
		getPath(a_Kind + 10);
		status = EnemyNS::DIRECTION::ATTACK;
	}

}

void Enemy::shootMissile(int dir) {
	if (rand() % 5 > 3)
	{

		for (int i = 0; i < EnemyNS::MAX_BULLET_NUMBER; i++)
			if (false == tp[i].getActive() )
			{
				tp[i].setVelocity(VECTOR2(mmp->getDirectionDisX(dir) * 60, -(mmp->getDIrectionDisY(dir) * 60)));
				tp[i].fire(this, -(mmp->getDIrectionDisY(dir) * 60));
				break;
			}
	}
	
}

void Enemy::damage(WEAPON weapon)
{
	if (shieldOn)
		return;

	switch (weapon)
	{
	case TORPEDO:
		audio->playCue(TORPEDO_HIT);
		nShield--;
		break;
	case SHIP:
		audio->playCue(COLLIDE);    
		nShield--;
		break;
	case PLANET:
		health = 0;
		break;
	}
	if (nShield <= 0)
		explode();
	else
		shieldOn = true;
}

void Enemy::explode()
{
	audio->playCue(EXPLODE);
	active = false;
	health = 0;
	explosionOn = true;
	engineOn = false;
	shieldOn = false;
	velocity.x = 0.0f;
	velocity.y = 0.0f;
	nShield = 0;
	isDead = true;
	mmp->setEnemyCntMinus();
}
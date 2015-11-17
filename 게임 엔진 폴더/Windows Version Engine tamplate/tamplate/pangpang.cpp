#include "pangpang.h"
using namespace pangPangNS;

PangPang::PangPang()
{
	mapController.readMap(0);
	this->startGameOn = true;
	this->menuOn = false;
	this->countDownOn = false;
	this->roundOver = false;
	this->nGameScole = 0;
	this->initialized = false;
	srand((UINT)time(NULL));
	this->loadGameDialog = false;
	this->bulletDelay = 0;
	this->nCountScole = 0;
	this->nStageNumber = pangPangNS::START_STAGE_NUMBER;
	this->countDownTimer = 3;

	for (int i = 0; i < 6; i++)
		for (int j = 0; j < 8; j++)
		{
			mEnemy[i * 8 + j] = new Enemy(mapController, *bullets02);
			mEnemy[i * 8 + j]->makeEnemy(i, j);
		}

	mAttack = new AttackEnemy(mEnemy, mapController);
	mAttack->ResetAttack();

}
PangPang::~PangPang()
{
	saveGame();            
	releaseAll();        
}
void PangPang::initialize(HWND hwnd)
{
	Game::initialize(hwnd);
	fontBig.initialize(graphics, FONT_BIG_SIZE, false, false, FONT);
	fontBig.setFontColor(::FONT_COLOR);
	fontScore.initialize(graphics, FONT_SCORE_SIZE, false, TRUE, ::FONT);

	if (!menuTexture.initialize(graphics, MENU_IMAGE))
		throw(GameError(gameErrorNS::FATAL_ERROR, "Error initializing menu texture"));

	if (!spaceTexture.initialize(graphics, SPACE_IMAGE))
		throw(GameError(gameErrorNS::FATAL_ERROR, "Error initializing space texture"));

	if (!gameTextures.initialize(graphics, TEXTURES_IMAGE))
		throw(GameError(gameErrorNS::FATAL_ERROR, "Error initializing game textures"));

	if (!backGroundTextures.initialize(graphics, BACKGROUND_IMAGE))
		throw(GameError(gameErrorNS::FATAL_ERROR, "Error initializing Background Textures"));

	if (!womanTextures.initialize(graphics, WOMAN_IMAGE))
		throw(GameError(gameErrorNS::FATAL_ERROR, "Error initializing Woman Textures"));

	if (!background.initialize(graphics, 0, 0, 0, &backGroundTextures))
		throw(GameError(gameErrorNS::FATAL_ERROR, "Error initializing background"));
	background.setScale(1 / 1.7046875f);

	RECT rect;
	rect.left = 0;
	rect.right = RADAR_SIZE;
	rect.top = RADAR_TEXTURE_TOP;
	rect.bottom = RADAR_TEXTURE_TOP + RADAR_SIZE;


	if (!earthBlip.initialize(graphics, BLIP_SIZE, BLIP_SIZE, 8, &gameTextures))
		throw(GameError(gameErrorNS::FATAL_ERROR, "Error initializing Earth blip"));
	earthBlip.setFrames(EARTH_BLIP_FRAME, EARTH_BLIP_FRAME);
	earthBlip.setCurrentFrame(EARTH_BLIP_FRAME);


	if (!hero.initialize(this, WomanNS::WIDTH, WomanNS::HEIGHT, WomanNS::TEXTURE_COLS, &womanTextures))
		throw(GameError(gameErrorNS::FATAL_ERROR, "Error initializing woman"));
	hero.setScale(WomanNS::WOMAN_SCALE);




	for (int i = 0; i < WomanNS::MAX_BULLET_NUMBER; i++)
	{
		if (!bullets01[i].initialize(this, torpedoNS::WIDTH, torpedoNS::HEIGHT, torpedoNS::TEXTURE_COLS, &gameTextures))
			throw(GameError(gameErrorNS::FATAL_ERROR, "Error initializing torpedo1"));

		bullets01[i].setColorFilter(SETCOLOR_ARGB(255, 128, 128, 255));   
	}


	for (int i = 0; i < EnemyNS::MAX_BULLET_NUMBER; i++)
	{
		if (!bullets02[i].initialize(this, torpedoNS::WIDTH, torpedoNS::HEIGHT, torpedoNS::TEXTURE_COLS, &gameTextures))
			throw(GameError(gameErrorNS::FATAL_ERROR, "Error initializing torpedo2"));

		bullets02[i].setColorFilter(SETCOLOR_ARGB(255, 128, 128, 255));   // light blue
		bullets02[i].setScale(1.4);
	}

	for (int i = 0; i < 6; i++)
		for (int j = 0; j < 8; j++)
		{
			if (!mEnemy[i * 8 + j]->initialize(this, WomanNS::WIDTH, WomanNS::HEIGHT, WomanNS::TEXTURE_COLS, &womanTextures))
				throw(GameError(gameErrorNS::FATAL_ERROR, "Error initializing woman"));
			mEnemy[i * 8 + j]->setScale(WomanNS::WOMAN_SCALE);
		}


	healthBar.initialize(graphics, &gameTextures, 0, ::HEALTHBAR_Y, 2.0f, graphicsNS::WHITE);



	countDownOn = true;
	roundOver = false;


	return;
}

void PangPang::update()
{
	if (loadGameDialog)
	{
		if (messageDialog->getButtonClicked() == 1) 
			loadGame();                        
		if (messageDialog->getVisible() == false)
			loadGameDialog = false;
	}
	else if (startGameOn == false)
	{
		if (input->getMouseLButton())
			hero.setDirection(WomanNS::LEFT);
		else if (input->getMouseRButton())
			hero.setDirection(WomanNS::RIGHT);
		else if (input->getMouseMButton())
		{
			hero.setDirection(WomanNS::UP);

			for (int i = 0; i < WomanNS::MAX_BULLET_NUMBER; i++)
				if (false == bullets01[i].getActive() && bulletDelay > WomanNS::DELAY_BULLET_TIME)
				{
					bullets01[i].fire(&hero,0);
					bulletDelay = 0;
				}
		}



		if (input->isKeyDown(HERO_LEFT_KEY) || input->getGamepadDPadLeft(0))   
			hero.setDirection(WomanNS::LEFT);

		if (input->isKeyDown(HERO_RIGHT_KEY) || input->getGamepadDPadRight(0)) 
			hero.setDirection(WomanNS::RIGHT);

		if (input->isKeyDown(HERO_UP_KEY) || input->getGamepadDPadUp(0)) 
		{
			hero.setDirection(WomanNS::UP);

			for (int i = 0; i < WomanNS::MAX_BULLET_NUMBER; i++)
				if (false == bullets01[i].getActive() && bulletDelay > WomanNS::DELAY_BULLET_TIME)
				{
					bullets01[i].fire(&hero,0);
					bulletDelay = 0;
				}
		}

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				mEnemy[i * 8 + j]->update(frameTime);
			}
		}

		mAttack->Attack(0);
if (input->isKeyDown(HERO_DOWN_KEY) || input->getGamepadDPadDown(0))
		{
	
		}




	}
	else if (countDownOn)
	{
		countDownTimer -= frameTime;
		if (countDownTimer <= 0)
		{
			countDownOn = false;
			startGameOn = false;
		}
	}
	else
	{
		if (ship1.getActive())
		{
			if (input->isKeyDown(SHIP1_FORWARD_KEY) || input->getGamepadDPadUp(0))   
			{
				ship1.setEngineOn(true);
				audio->playCue(ENGINE1);
			}
			else
			{
				ship1.setEngineOn(false);
				audio->stopCue(ENGINE1);
			}
			ship1.rotate(shipNS::NONE);
			if (input->isKeyDown(SHIP1_LEFT_KEY) || input->getGamepadDPadLeft(0))  
				ship1.rotate(shipNS::LEFT);
			if (input->isKeyDown(SHIP1_RIGHT_KEY) || input->getGamepadDPadRight(0))
				ship1.rotate(shipNS::RIGHT);
		}
		if (roundOver)
		{
			roundTimer -= frameTime;
			if (roundTimer <= 0)
				roundStart();
		}
	}

	ship1.update(frameTime);

	hero.update(frameTime);

	for (int i = 0; i < WomanNS::MAX_BULLET_NUMBER; i++)
		bullets01[i].update(frameTime);

	for (int i = 0; i < EnemyNS::MAX_BULLET_NUMBER; i++)
		bullets02[i].update(frameTime);

	if ((hero.getX() < HERO_LIMIT_LEFT) || (hero.getX() > HERO_LIMIT_RIGHT))
	{
		if (hero.getX() < HERO_LIMIT_LEFT)
		{
			hero.setDirection(WomanNS::UP);
			hero.setX(HERO_LIMIT_LEFT);
		}
		if (hero.getX() > HERO_LIMIT_RIGHT)
		{
			hero.setDirection(WomanNS::UP);
			hero.setX(HERO_LIMIT_RIGHT);
		}
	}

	if ((ship1.getX() < SHIP_LIMIT_LEFT) || (ship1.getX() > SHIP_LIMIT_RIGHT))
	{
		if (ship1.getX() < SHIP_LIMIT_LEFT)
			ship1.setX(SHIP_LIMIT_LEFT);
		if (ship1.getX() > SHIP_LIMIT_RIGHT)
			ship1.setX(SHIP_LIMIT_RIGHT);
	}
	if ((ship1.getY() < SHIP_LIMIT_TOP) || (ship1.getY() > SHIP_LIMIT_BOTTOM))
	{
		if (ship1.getY() < SHIP_LIMIT_TOP)
			ship1.setY(SHIP_LIMIT_TOP);
		if (ship1.getY() > SHIP_LIMIT_BOTTOM)
			ship1.setY(SHIP_LIMIT_BOTTOM);
	}

	this->bulletDelay += frameTime;

	if(mapController.getEnemyLiveCount() <= 0)
		roundStart();
}

void PangPang::roundStart()
{

	mAttack->ResetAttack();


	for (int i = 0; i < 6; i++)
		for (int j = 0; j < 8; j++)
		{
			mEnemy[i * 8 + j]->makeEnemy(i, j);
			mEnemy[i * 8 + j]->setActive(true);
		}

	mapController.setEnemyCnt(6 * 8);

	countDownTimer = 3;
	countDownOn = true;
	ship1.repair();
	roundOver = false;
	startGameOn = true;
}

void PangPang::ai()
{}

void PangPang::collisions()
{
	VECTOR2 collisionVector;


	for (int i = 0; i < EnemyNS::MAX_BULLET_NUMBER; i++)
	{
		if (bullets02[i].collidesWith(hero, collisionVector))
		{
			bullets02[i].setVisible(false);
			bullets02[i].setActive(false);
			input->gamePadVibrateRight(0, 20000, 0.5);
		}
	}

	for (int i = 0; i < WomanNS::MAX_BULLET_NUMBER; i++)
	{
		if (bullets01[i].collidesWith(ship1, collisionVector))
		{
			ship1.damage(TORPEDO);
			bullets01[i].setVisible(false);
			bullets01[i].setActive(false);
			input->gamePadVibrateRight(0, 20000, 0.5);

			if (ship1.getHealth() <= 0)
			{
				char string1[30] = "Enemy Die Point : ";
				char string2[30];

				int nRamdom_value;

				nRamdom_value = (pangPangNS::SCOLE_BASIC_ENEMY_DIE + rand() % pangPangNS::SCOLE_RANDOM_MAX_ENEMY_DIE);

				itoa(nRamdom_value, string2, 10);

				strcat_s(string1, 30, string2);

				nGameScole += nRamdom_value;
				console->print(string1);
			}
			break;
		}
		for (int j = 0; j < 48; j++)
			if (bullets01[i].collidesWith(*mEnemy[j], collisionVector))
			{
				mEnemy[j]->damage(TORPEDO);
				bullets01[i].setVisible(false);
				bullets01[i].setActive(false);
				input->gamePadVibrateRight(0, 20000, 0.5);
				

				if (mEnemy[j]->getnShield() <= 0)
				{
					char string1[50] = "Enemy Die ";
					char string2[30];

					int nRamdom_value;

					itoa(j/8, string2, 10);

					strcat_s(string1, 50, string2);
					strcat_s(string1, 50, " ");
					itoa(j%8, string2, 10);

					strcat_s(string1, 50, string2);
					strcat_s(string1, 50, " : ");
					nRamdom_value = (pangPangNS::SCOLE_BASIC_ENEMY_DIE + rand() % pangPangNS::SCOLE_RANDOM_MAX_ENEMY_DIE);

					itoa(nRamdom_value, string2, 10);

					strcat_s(string1, 50, string2);

					nGameScole += nRamdom_value;
					console->print(string1);

				}
				break;
			}
	}
}

void PangPang::render()
{

	graphics->spriteBegin();             
	background.draw();

	fontScore.setFontColor(WomanNS::WOMAN_SCOLE_COLOR);
	if (nCountScole < (int)nGameScole)
		nCountScole++;
	_snprintf_s(buffer, ::BUF_SIZE, "%d", nCountScole);
	fontScore.print(buffer, WomanNS::SCORE_PANEL_X, WomanNS::SCORE_PANEL_Y);


	hero.draw();
	

	for (int i = 0; i < 6; i++) {
		for (int j = 0; j < 8; j++) {
			if (mEnemy[i * 8 + j]->getIsDead() == false)
				mEnemy[i * 8 + j]->draw();
		}
	}

	for (int i = 0; i < WomanNS::MAX_BULLET_NUMBER; i++)
		bullets01[i].draw(graphicsNS::FILTER);    

	for (int i = 0; i < EnemyNS::MAX_BULLET_NUMBER; i++)
		bullets02[i].draw(graphicsNS::FILTER);

	earthBlip.draw();


	if (countDownOn)
	{
		int time = (int)countDownTimer;

		_snprintf_s(buffer, ::BUF_SIZE, "%d", (int)(ceil(countDownTimer)));
		fontBig.print(buffer, ::COUNT_DOWN_X, ::COUNT_DOWN_Y);
	}

	graphics->spriteEnd();                  
}

void PangPang::saveGame()
{
	std::ofstream outFile(SAVE_NAME);
	if (outFile.fail())                  
	{
		messageDialog->print("Error creating save file.");
		return;
	}
	outFile << nGameScole << '\n'
		<< ship1.getX() << '\n'
		<< ship1.getY() << '\n'
		<< ship1.getHealth() << '\n'
		<< ship1.getVelocity().x << '\n'
		<< ship1.getVelocity().y << '\n';

	outFile.close();
}
void PangPang::loadGame()
{
	std::ifstream inFile(SAVE_NAME);
	float n, x, y;

	if (inFile.fail())                 
	{
		messageDialog->print("Error opening save file.");
		return;
	}
	inFile >> nGameScole;
	inFile >> n;
	ship1.setX(n);
	inFile >> n;
	ship1.setY(n);
	inFile >> n;
	ship1.setHealth(n);
	inFile >> x;
	inFile >> y;
	ship1.setVelocity(D3DXVECTOR2(x, y));

	inFile.close();
}

bool PangPang::foundGame()
{
	std::ifstream inFile(SAVE_NAME);

	if (inFile.fail())                   
		return false;                  
	inFile.close();
	return true;                      
}

void PangPang::consoleCommand()
{
	command = console->getCommand();   
	if (command == "")                 
		return;

	if (command == "help")             
	{
		console->print("Console Commands:");
		console->print("fps - toggle display of frames per second");
		console->print("death - All Enemy Object Death");
		return;
	}

	if (command == "fps")
	{
		fpsOn = !fpsOn;                 
		if (fpsOn)
			console->print("fps On");
		else
			console->print("fps Off");
	}

	if (command == "death")
	{
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 8; j++)
			{
				mEnemy[i * 8 + j]->setnShield(0);
				mEnemy[i * 8 + j]->setIsDead(true);
				mapController.setEnemyCnt(0);
			}
	}

}


void PangPang::releaseAll()
{
	menuTexture.onLostDevice();
	spaceTexture.onLostDevice();
	gameTextures.onLostDevice();

	womanTextures.onLostDevice();
	backGroundTextures.onLostDevice();

	fontScore.onLostDevice();
	fontBig.onLostDevice();

	Game::releaseAll();
	return;
}

void PangPang::resetAll()
{
	fontBig.onResetDevice();
	fontScore.onResetDevice();
	gameTextures.onResetDevice();

	womanTextures.onLostDevice();
	backGroundTextures.onLostDevice();

	spaceTexture.onResetDevice();
	menuTexture.onResetDevice();

	Game::resetAll();
	return;
}

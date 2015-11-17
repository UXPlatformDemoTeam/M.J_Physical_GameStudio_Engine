#ifndef _PANGPANG_H        
#define _PANGPANG_H     
#define WIN32_LEAN_AND_MEAN

class AttackEnemy;

#include <string>
#include <time.h>
#include <fstream>
#include "Map_controller.h"
#include "game.h"
#include "textureManager.h"
#include "image.h"
#include "dashboard.h"
#include "planet.h"
#include "ship.h"
#include "torpedo.h"
#include "cow.h"
#include "woman.h"
#include "Enemy.h"
#include "AttackEnemy.h"


namespace pangPangNS
{
    const char FONT[] = "Arial Bold";  
    const int FONT_BIG_SIZE = 256;     
    const int FONT_SCORE_SIZE = 20;
    const COLOR_ARGB FONT_COLOR = graphicsNS::YELLOW;
    const COLOR_ARGB SHIP2_COLOR = graphicsNS::YELLOW;
    const int HEALTHBAR_Y = 30;
    const int SHIP1_HEALTHBAR_X = 40;
    const int COUNT_DOWN_X = GAME_WIDTH/2 - FONT_BIG_SIZE/4;
    const int COUNT_DOWN_Y = GAME_HEIGHT/2 - FONT_BIG_SIZE/2;
    const int COUNT_DOWN = 5;           
    const int BUF_SIZE = 20;
    const int ROUND_TIME = 5;          
    const int SPACE_SCALE = 2;                 
    const int SPACE_WIDTH = 640 * SPACE_SCALE; 
    const int SPACE_HEIGHT = 480 * SPACE_SCALE; 

	const float HERO_LIMIT_LEFT = 0;
	const float HERO_LIMIT_RIGHT = GAME_WIDTH - WomanNS::WIDTH*WomanNS::WOMAN_SCALE;


    const float SHIP_LIMIT_LEFT = 0;
	const float SHIP_LIMIT_RIGHT = GAME_WIDTH - shipNS::WIDTH;
	const float SHIP_LIMIT_TOP = 0;
	const float SHIP_LIMIT_BOTTOM = GAME_HEIGHT - shipNS::HEIGHT;


    const int MAX_PIRATES = 10;         
    const int RADAR_SIZE = 96;
    const int RADAR_TEXTURE_TOP = 160;
    const float RADAR_X = GAME_WIDTH-RADAR_SIZE;   
    const float RADAR_Y = 10;
    const float RADAR_CENTER_X = RADAR_X + RADAR_SIZE/2;
    const float RADAR_CENTER_Y = RADAR_Y + RADAR_SIZE/2;
    const int BLIP_SIZE = 32;
    const int EARTH_BLIP_FRAME = 51;
    const int COW_BLIP_FRAME = 52;
    const float SECTOR_SIZE = 800;      
    const char SAVE_NAME[] = "SpacePiratesSave.txt";   

	const int SCOLE_BASIC_ENEMY_DIE = 50;
	const int SCOLE_RANDOM_MAX_ENEMY_DIE = 100;

	const int START_STAGE_NUMBER = 0;
	const int END_STAGE_NUMBER = 2;
}
class PangPang : public Game
{
private:
    TextureManager menuTexture, spaceTexture, gameTextures, womanTextures, backGroundTextures;  
	Woman	hero;
	Torpedo bullets01[WomanNS::MAX_BULLET_NUMBER];	
	Torpedo bullets02[EnemyNS::MAX_BULLET_NUMBER];	
	Image	background;			
    Image   earthBlip;        

    Bar     healthBar;         
    TextDX  fontBig;           
    TextDX  fontScore;
    bool    menuOn;
	bool	startGameOn;		
    bool    countDownOn;       
    bool    loadGameDialog;    
    float   countDownTimer;
	char buffer[pangPangNS::BUF_SIZE];
    bool    roundOver;          
    float   roundTimer;        
    int     nGameScole; 		
	float	bulletDelay;		
	int		nCountScole;	
	int		nStageNumber;		
	AttackEnemy *mAttack;	
	

public:

    PangPang();
   
    virtual ~PangPang();
  
    void initialize(HWND hwnd);
    void update();     
    void ai();          
    void collisions();
    void render();    
    void saveGame();   
    void loadGame();   
    bool foundGame();
    void consoleCommand();
    void roundStart();  
    void releaseAll();
    void resetAll();
	void loadDelayTime();
	
};
static Map_controller mapController;	
static Enemy	*mEnemy[48];		

#endif

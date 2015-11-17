#ifndef _CONSTANTS_H         
#define _CONSTANTS_H            
#define WIN32_LEAN_AND_MEAN

#include <windows.h>


template <typename T>
inline void safeRelease(T& ptr)
{
    if (ptr)
    { 
        ptr->Release(); 
        ptr = NULL;
    }
}
#define SAFE_RELEASE safeRelease            

template <typename T>
inline void safeDelete(T& ptr)
{
    if (ptr)
    { 
        delete ptr; 
        ptr = NULL;
    }
}
#define SAFE_DELETE safeDelete             
inline void safeDeleteArray(T& ptr)
{
    if (ptr)
    { 
        delete[] ptr; 
        ptr = NULL;
    }
}
#define SAFE_DELETE_ARRAY safeDeleteArray   
template <typename T>
inline void safeOnLostDevice(T& ptr)
{
    if (ptr)
        ptr->onLostDevice(); 
}
#define SAFE_ON_LOST_DEVICE safeOnLostDevice    
template <typename T>
inline void safeOnResetDevice(T& ptr)
{
    if (ptr)
        ptr->onResetDevice(); 
}
#define SAFE_ON_RESET_DEVICE safeOnResetDevice  
const char CLASS_NAME[] = "PangPang";
const char GAME_TITLE[] = "PangPang 0.0.1v";
const bool FULLSCREEN = false;              
const UINT GAME_WIDTH =  640;             
const UINT GAME_HEIGHT = 390;             

const double PI = 3.14159265;
const float FRAME_RATE = 240.0f;            
const float MIN_FRAME_RATE = 10.0f;            
const float MIN_FRAME_TIME = 1.0f/FRAME_RATE;   
const float MAX_FRAME_TIME = 1.0f/MIN_FRAME_RATE; 
const float FULL_HEALTH = 100;

const char SPACE_IMAGE[] =    "pictures\\space.jpg";
const char BACKGROUND_IMAGE[] = "pictures\\background.png"; 
const char TEXTURES_IMAGE[] = "pictures\\textures.png"; 
const char MENU_IMAGE[] =     "pictures\\menu.png";      

const char WOMAN_IMAGE[] = "pictures\\woman.png";		
const char WAVE_BANK[]  = "audio\\Win\\waveBank.xwb";
const char SOUND_BANK[] = "audio\\Win\\soundBank.xsb";


const char CHEER[]          = "cheer";
const char COLLIDE[]        = "collide";
const char EXPLODE[]        = "explode";
const char ENGINE1[]        = "engine1";
const char ENGINE2[]        = "engine2";
const char TORPEDO_CRASH[]  = "torpedoCrash";
const char TORPEDO_FIRE[]   = "torpedoFire";
const char TORPEDO_HIT[]    = "torpedoHit";


const UCHAR CONSOLE_KEY  = '`';       
const UCHAR ESC_KEY      = VK_ESCAPE; 
const UCHAR ALT_KEY      = VK_MENU; 
const UCHAR ENTER_KEY    = VK_RETURN;  
const UCHAR SHIP1_LEFT_KEY    = VK_LEFT; 
const UCHAR SHIP1_RIGHT_KEY   = VK_RIGHT; 
const UCHAR SHIP1_FORWARD_KEY = VK_UP;   
const UCHAR SHIP1_FIRE_KEY    = VK_DOWN;

const UCHAR HERO_LEFT_KEY = VK_LEFT; 
const UCHAR HERO_RIGHT_KEY = VK_RIGHT;
const UCHAR HERO_UP_KEY = VK_UP;   
const UCHAR HERO_DOWN_KEY = VK_DOWN;

enum WEAPON {TORPEDO, SHIP, PLANET, COW};

#endif

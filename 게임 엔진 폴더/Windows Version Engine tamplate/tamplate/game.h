#ifndef _GAME_H                
#define _GAME_H                
#define WIN32_LEAN_AND_MEAN

class Game;

#include <windows.h>
#include <Mmsystem.h>
#include "graphics.h"
#include "input.h"
#include "audio.h"
#include "entity.h"
#include "constants.h"
#include "textDX.h"
#include "console.h"
#include "gameError.h"
#include "messageDialog.h"
#include "inputDialog.h"


namespace gameNS
{
    const char FONT[] = "Courier New";  
    const int POINT_SIZE = 14;          
    const COLOR_ARGB FONT_COLOR = SETCOLOR_ARGB(255,255,255,255);  
}

class Game
{
protected:
    Graphics *graphics;             
    Input   *input;                 
    Audio   *audio;                 
    Console *console;               
    MessageDialog *messageDialog;   
    InputDialog *inputDialog;       
    HWND    hwnd;                  
    HRESULT hr;                    
    LARGE_INTEGER timeStart;        
    LARGE_INTEGER timeEnd;         
    LARGE_INTEGER timerFreq;        
    float   frameTime;              
    float   fps;                   
    TextDX  dxFont;                 
    bool    fpsOn;                 
    DWORD   sleepTime;              
    bool    paused;                 
    bool    initialized;
    std::string  command;           

public:
    Game();
    virtual ~Game();
    LRESULT messageHandler( HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam );

    virtual void initialize(HWND hwnd);

    virtual void run(HWND);

    virtual void releaseAll();

    virtual void resetAll();

    virtual void deleteAll();

    virtual void consoleCommand();

    virtual void renderGame();

    virtual void handleLostGraphicsDevice();

    void setDisplayMode(graphicsNS::DISPLAY_MODE mode = graphicsNS::TOGGLE);

    Graphics* getGraphics() {return graphics;}

    Input* getInput()       {return input;}

    void exitGame()         {PostMessage(hwnd, WM_DESTROY, 0, 0);}

    Audio* getAudio()       {return audio;}

    virtual void update() = 0;

    virtual void ai() = 0;

    virtual void collisions() = 0;

    virtual void render() = 0;
};

#endif

#include "game.h"

Game::Game()
{
    input = new Input();        
    paused = false;          
    graphics = NULL;
    audio = NULL;
    console = NULL;
    messageDialog = NULL;
    inputDialog = NULL;
    fps = 100;
    fpsOn = false;            
    initialized = false;
}

Game::~Game()
{
    deleteAll();                
    ShowCursor(true);          
}

LRESULT Game::messageHandler( HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam )
{
    if(initialized)    
    {
        switch( msg )
        {
            case WM_DESTROY:
                PostQuitMessage(0);       
                return 0;
            case WM_KEYDOWN: case WM_SYSKEYDOWN:   
                input->keyDown(wParam);
                return 0;
            case WM_KEYUP: case WM_SYSKEYUP:       
                input->keyUp(wParam);
                return 0;
            case WM_CHAR:                          
                input->keyIn(wParam);
                return 0;
            case WM_MOUSEMOVE:                    
                input->mouseIn(lParam);
                return 0;
            case WM_INPUT:                         
                input->mouseRawIn(lParam);
                return 0;
            case WM_LBUTTONDOWN:                  
                input->setMouseLButton(true);
                input->mouseIn(lParam);           
                return 0;
            case WM_LBUTTONUP:                     
                input->setMouseLButton(false);
                input->mouseIn(lParam);          
                return 0;
            case WM_MBUTTONDOWN:                   
                input->setMouseMButton(true);
                input->mouseIn(lParam);            
                return 0;
            case WM_MBUTTONUP:                      
                input->setMouseMButton(false);
                input->mouseIn(lParam);            
                return 0;
            case WM_RBUTTONDOWN:                    
                input->setMouseRButton(true);
                input->mouseIn(lParam);           
                return 0;
            case WM_RBUTTONUP:                     
                input->setMouseRButton(false);
                input->mouseIn(lParam);             
                return 0;
            case WM_XBUTTONDOWN: case WM_XBUTTONUP: 
                input->setMouseXButton(wParam);
                input->mouseIn(lParam);             
                return 0;
            case WM_MOUSEWHEEL:                  
                input->mouseWheelIn(wParam);
                return 0;
            case WM_DEVICECHANGE:                   
                input->checkControllers();
                return 0;
        }
    }
    return DefWindowProc( hwnd, msg, wParam, lParam );  
}

void Game::initialize(HWND hw)
{
    hwnd = hw;                        
    graphics = new Graphics();
    graphics->initialize(hwnd, GAME_WIDTH, GAME_HEIGHT, FULLSCREEN);

    input->initialize(hwnd, false);           

    console = new Console();
    console->initialize(graphics, input);      
    console->print("---Console---");
	console->getVisible();
    messageDialog = new MessageDialog();
    messageDialog->initialize(graphics, input, hwnd);

    inputDialog = new InputDialog();
    inputDialog->initialize(graphics, input, hwnd);

    if(dxFont.initialize(graphics, gameNS::POINT_SIZE, false, false, gameNS::FONT) == false)
        throw(GameError(gameErrorNS::FATAL_ERROR, "Failed to initialize DirectX font."));

    dxFont.setFontColor(gameNS::FONT_COLOR);

    audio = new Audio();
    if (*WAVE_BANK != '\0' && *SOUND_BANK != '\0') 
    {
        if( FAILED( hr = audio->initialize() ) )
        {
            if( hr == HRESULT_FROM_WIN32( ERROR_FILE_NOT_FOUND ) )
                throw(GameError(gameErrorNS::FATAL_ERROR, "Failed to initialize sound system because media file not found."));
            else
                throw(GameError(gameErrorNS::FATAL_ERROR, "Failed to initialize sound system."));
        }
    }

    if(QueryPerformanceFrequency(&timerFreq) == false)
        throw(GameError(gameErrorNS::FATAL_ERROR, "Error initializing high resolution timer"));

    QueryPerformanceCounter(&timeStart);        

    initialized = true;
}

void Game::renderGame()
{
    const int BUF_SIZE = 20;
    static char buffer[BUF_SIZE];

    if (SUCCEEDED(graphics->beginScene()))
    {
        render();           

        graphics->spriteBegin();   
        if(fpsOn)        
        {
            _snprintf_s(buffer, BUF_SIZE, "fps %d ", (int)fps);
            dxFont.print(buffer,GAME_WIDTH-100,GAME_HEIGHT-28);
        }
        graphics->spriteEnd();      

        console->draw();    
        messageDialog->draw();  
        inputDialog->draw();   

        graphics->endScene();
    }
    handleLostGraphicsDevice();

    graphics->showBackbuffer();
}

void Game::handleLostGraphicsDevice()
{
    
    hr = graphics->getDeviceState();
    if(FAILED(hr))                 
    {
        if(hr == D3DERR_DEVICELOST)
        {
            Sleep(100);          
            return;
        } 
        else if(hr == D3DERR_DEVICENOTRESET)
        {
            releaseAll();
            hr = graphics->reset();
            if(FAILED(hr))       
                return;
            resetAll();
        }
        else
            return;              
    }
}

void Game::setDisplayMode(graphicsNS::DISPLAY_MODE mode)
{
    releaseAll();
    graphics->changeDisplayMode(mode);
    resetAll();                    
}

void Game::run(HWND hwnd)
{
    if(graphics == NULL)           
        return;

    QueryPerformanceCounter(&timeEnd);
    frameTime = (float)(timeEnd.QuadPart - timeStart.QuadPart ) / (float)timerFreq.QuadPart;

    if (frameTime < MIN_FRAME_TIME) 
    {
        sleepTime = (DWORD)((MIN_FRAME_TIME - frameTime)*1000);
        timeBeginPeriod(1);       
        Sleep(sleepTime);           
        timeEndPeriod(1);          
        return;
    }

    if (frameTime > 0.0)
        fps = (fps*0.99f) + (0.01f/frameTime);  
    if (frameTime > MAX_FRAME_TIME)
        frameTime = MAX_FRAME_TIME; 
    timeStart = timeEnd;

    if (!paused)                    
    {
        update();                 
        ai();                      
        collisions();              
        input->vibrateControllers(frameTime); 
    }
    renderGame();                  

    if (input->getCharIn() == CONSOLE_KEY)
    {
        input->clearCharIn();       
        console->showHide();
        paused = console->getVisible();
    }
    consoleCommand();               

    input->readControllers();       

    messageDialog->update();
    inputDialog->update();

    audio->run();                   
    if (input->wasKeyPressed(VK_PAUSE))
        paused = !paused;

    input->clear(inputNS::KEYS_PRESSED);
}

void Game::consoleCommand()
{
    command = console->getCommand();   
    if(command == "")                   
        return;

    if (command == "help")             
    {
        console->print("Console Commands:");
        console->print("fps - toggle display of frames per second");
        return;
    }

    if (command == "fps")
    {
        fpsOn = !fpsOn;              
        if(fpsOn)
            console->print("fps On");
        else
            console->print("fps Off");
    }
}

void Game::releaseAll()
{
    safeOnLostDevice(inputDialog);
    safeOnLostDevice(messageDialog);
    safeOnLostDevice(console);
    dxFont.onLostDevice();
    return;
}

void Game::resetAll()
{
    dxFont.onResetDevice();
    safeOnResetDevice(console);
    safeOnResetDevice(messageDialog);
    safeOnResetDevice(inputDialog);
    return;
}

void Game::deleteAll()
{
    releaseAll();              
    safeDelete(audio);
    safeDelete(graphics);
    safeDelete(input);
    safeDelete(console);
    safeDelete(messageDialog);
    safeDelete(inputDialog);
    initialized = false;
}
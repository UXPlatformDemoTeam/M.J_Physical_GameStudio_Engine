#ifndef _INPUT_H             
#define _INPUT_H                
#define WIN32_LEAN_AND_MEAN

class Input;

#include <windows.h>
#include <WindowsX.h>
#include <string>
#include <XInput.h>
#include "constants.h"
#include "gameError.h"


#ifndef HID_USAGE_PAGE_GENERIC
#define HID_USAGE_PAGE_GENERIC      ((USHORT) 0x01)
#endif
#ifndef HID_USAGE_GENERIC_MOUSE
#define HID_USAGE_GENERIC_MOUSE     ((USHORT) 0x02)
#endif

namespace inputNS
{
    const int KEYS_ARRAY_LEN = 256;    
    
    const UCHAR KEYS_DOWN = 1;
    const UCHAR KEYS_PRESSED = 2;
    const UCHAR MOUSE = 4;
    const UCHAR TEXT_IN = 8;
    const UCHAR KEYS_MOUSE_TEXT = KEYS_DOWN + KEYS_PRESSED + MOUSE + TEXT_IN;
}

const short GAMEPAD_THUMBSTICK_DEADZONE = (short)(0.20f * 0X7FFF);   
const short GAMEPAD_TRIGGER_DEADZONE = 20;                    
const DWORD MAX_CONTROLLERS = 4;                               
const DWORD GAMEPAD_DPAD_UP        = 0x0001;
const DWORD GAMEPAD_DPAD_DOWN      = 0x0002;
const DWORD GAMEPAD_DPAD_LEFT      = 0x0004;
const DWORD GAMEPAD_DPAD_RIGHT     = 0x0008;
const DWORD GAMEPAD_START_BUTTON   = 0x0010;
const DWORD GAMEPAD_BACK_BUTTON    = 0x0020;
const DWORD GAMEPAD_LEFT_THUMB     = 0x0040;
const DWORD GAMEPAD_RIGHT_THUMB    = 0x0080;
const DWORD GAMEPAD_LEFT_SHOULDER  = 0x0100;
const DWORD GAMEPAD_RIGHT_SHOULDER = 0x0200;
const DWORD GAMEPAD_A              = 0x1000;
const DWORD GAMEPAD_B              = 0x2000;
const DWORD GAMEPAD_X              = 0x4000;
const DWORD GAMEPAD_Y              = 0x8000;

struct ControllerState
{
    XINPUT_STATE        state;
    XINPUT_VIBRATION    vibration;
    float               vibrateTimeLeft;    
    float               vibrateTimeRight;   
    bool                connected;
};

class Input
{
private:
    bool keysDown[inputNS::KEYS_ARRAY_LEN];    
    bool keysPressed[inputNS::KEYS_ARRAY_LEN]; 
    std::string textIn;                         
    char charIn;                              
    bool newLine;                               
    int  mouseX, mouseY;                        
    int  mouseRawX, mouseRawY;                  
    int  mouseWheel;                          

    RAWINPUTDEVICE Rid[1];                     
    bool mouseCaptured;                       
    bool mouseLButton;                         
    bool mouseMButton;                         
    bool mouseRButton;                        
    bool mouseX1Button;                       
    bool mouseX2Button;                         
    ControllerState controllers[MAX_CONTROLLERS];    
    short thumbstickDeadzone;
    short triggerDeadzone;

public:
    Input();

    virtual ~Input();
    void initialize(HWND hwnd, bool capture);

    void keyDown(WPARAM);

    void keyUp(WPARAM);

    void keyIn(WPARAM);

    bool isKeyDown(UCHAR vkey) const;
    bool wasKeyPressed(UCHAR vkey) const;
    bool anyKeyPressed() const;
    void clearKeyPress(UCHAR vkey);
    void clear(UCHAR what);
    void clearAll() {clear(inputNS::KEYS_MOUSE_TEXT);}
    void clearTextIn() {textIn.clear();}
    void clearCharIn()      {charIn = 0;}
    std::string getTextIn() {return textIn;}
    void setTextIn(std::string str) {textIn = str;}
    char getCharIn()        {return charIn;}
    void mouseIn(LPARAM);
    void mouseRawIn(LPARAM);
    void mouseWheelIn(WPARAM);

    void setMouseLButton(bool b) { mouseLButton = b; }

    void setMouseMButton(bool b) { mouseMButton = b; }

    void setMouseRButton(bool b) { mouseRButton = b; }

    void setMouseXButton(WPARAM wParam) {mouseX1Button = (wParam & MK_XBUTTON1) ? true:false;
                                         mouseX2Button = (wParam & MK_XBUTTON2) ? true:false;}

    int  getMouseX()        const { return mouseX; }

    int  getMouseY()        const { return mouseY; }

    int  getMouseRawX()
    { 
        int rawX = mouseRawX;
        mouseRawX = 0;
        return rawX; 
    }

    int  getMouseRawY()
    { 
        int rawY = mouseRawY;
        mouseRawY = 0;
        return rawY; 
    }

    int  getMouseWheel()
    { 
        int wheel = mouseWheel;
        mouseWheel = 0;
        return wheel; 
    }

    bool getMouseLButton()  const { return mouseLButton; }

    bool getMouseMButton()  const { return mouseMButton; }

    bool getMouseRButton()  const { return mouseRButton; }

    bool getMouseX1Button() const { return mouseX1Button; }

    bool getMouseX2Button() const { return mouseX2Button; }

    void checkControllers();

    void readControllers();

    void setThumbstickDeadzone(short dz) { thumbstickDeadzone = abs(dz); }

    void setTriggerDeadzone(BYTE dz) { triggerDeadzone = dz; }

    short getThumbstickDeadzone() { return thumbstickDeadzone; }

    BYTE getTriggerDeadzone() { return static_cast<BYTE>(triggerDeadzone); }

    const ControllerState* getControllerState(UINT n)
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return &controllers[n];
    }

    bool getGamepadConnected(UINT n)
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return controllers[n].connected;
    }

    const WORD getGamepadButtons(UINT n)
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return controllers[n].state.Gamepad.wButtons;
    }

    bool getGamepadDPadUp(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return ((controllers[n].state.Gamepad.wButtons&GAMEPAD_DPAD_UP) != 0);
    }

    bool getGamepadDPadDown(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return ((controllers[n].state.Gamepad.wButtons&GAMEPAD_DPAD_DOWN) != 0);
    }

    bool getGamepadDPadLeft(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return ((controllers[n].state.Gamepad.wButtons&GAMEPAD_DPAD_LEFT) != 0);
    }

    bool getGamepadDPadRight(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return bool((controllers[n].state.Gamepad.wButtons&GAMEPAD_DPAD_RIGHT) != 0);
    }

    bool getGamepadStart(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return bool((controllers[n].state.Gamepad.wButtons&GAMEPAD_START_BUTTON) != 0);
    }

    bool getGamepadBack(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return bool((controllers[n].state.Gamepad.wButtons&GAMEPAD_BACK_BUTTON) != 0);
    }

    bool getGamepadLeftThumb(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return bool((controllers[n].state.Gamepad.wButtons&GAMEPAD_LEFT_THUMB) != 0);
    }

    bool getGamepadRightThumb(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return bool((controllers[n].state.Gamepad.wButtons&GAMEPAD_RIGHT_THUMB) != 0);
    }

    bool getGamepadLeftShoulder(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return bool((controllers[n].state.Gamepad.wButtons&GAMEPAD_LEFT_SHOULDER) != 0);
    }

    bool getGamepadRightShoulder(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return bool((controllers[n].state.Gamepad.wButtons&GAMEPAD_RIGHT_SHOULDER) != 0);
    }

    bool getGamepadA(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return bool((controllers[n].state.Gamepad.wButtons&GAMEPAD_A) != 0);
    }

    bool getGamepadB(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return bool((controllers[n].state.Gamepad.wButtons&GAMEPAD_B) != 0);
    }

    bool getGamepadX(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return bool((controllers[n].state.Gamepad.wButtons&GAMEPAD_X) != 0);
    }

    bool getGamepadY(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return bool((controllers[n].state.Gamepad.wButtons&GAMEPAD_Y) != 0);
    }

    BYTE getGamepadLeftTrigger(UINT n);

    BYTE getGamepadLeftTriggerUndead(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return controllers[n].state.Gamepad.bLeftTrigger;
    }

    BYTE getGamepadRightTrigger(UINT n);

    BYTE getGamepadRightTriggerUndead(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return controllers[n].state.Gamepad.bRightTrigger;
    }

    SHORT getGamepadThumbLX(UINT n);

    SHORT getGamepadThumbLXUndead(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return controllers[n].state.Gamepad.sThumbLX;
    }

    SHORT getGamepadThumbLY(UINT n);

    SHORT getGamepadThumbLYUndead(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return controllers[n].state.Gamepad.sThumbLY;
    }

    SHORT getGamepadThumbRX(UINT n);

    SHORT getGamepadThumbRXUndead(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)   
            n=MAX_CONTROLLERS-1;    
        return controllers[n].state.Gamepad.sThumbRX;
    }

    SHORT getGamepadThumbRY(UINT n);

    SHORT getGamepadThumbRYUndead(UINT n) 
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        return controllers[n].state.Gamepad.sThumbRY;
    }

   void gamePadVibrateLeft(UINT n, WORD speed, float sec)
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        controllers[n].vibration.wLeftMotorSpeed = speed;
        controllers[n].vibrateTimeLeft = sec;
    }

    void gamePadVibrateRight(UINT n, WORD speed, float sec)
    {
        if(n > MAX_CONTROLLERS-1)
            n=MAX_CONTROLLERS-1;
        controllers[n].vibration.wRightMotorSpeed = speed;
        controllers[n].vibrateTimeRight = sec;
    }

    void vibrateControllers(float frameTime);
};

#endif


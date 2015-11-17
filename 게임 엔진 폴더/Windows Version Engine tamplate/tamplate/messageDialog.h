#ifndef _MESSAGEDIALOG_H       
#define _MESSAGEDIALOG_H        
#define WIN32_LEAN_AND_MEAN

class MessageDialog;

#include <string>
#include "constants.h"
#include "textDX.h"
#include "graphics.h"
#include "input.h"

namespace messageDialogNS
{
    const UINT WIDTH = 400;            
    const UINT HEIGHT = 100;           
    const UINT BORDER = 5;
    const UINT MARGIN = 5;              
    const char FONT[] = "Arial";       
    const int FONT_HEIGHT = 18;       
    const COLOR_ARGB FONT_COLOR = graphicsNS::WHITE;      
    const COLOR_ARGB BORDER_COLOR = D3DCOLOR_ARGB(192,192,192,192);
    const COLOR_ARGB BACK_COLOR = SETCOLOR_ARGB(255,100,100,192);   
    const UINT X = GAME_WIDTH/4;        
    const UINT Y = GAME_HEIGHT/4;
    const UINT BUTTON_WIDTH = (UINT)(FONT_HEIGHT * 4.5);
    const UINT BUTTON_HEIGHT = FONT_HEIGHT + 4;
    const int MAX_TYPE = 2;
    const int OK_CANCEL = 0;          
    const int YES_NO = 1;              
    static const char* BUTTON1_TEXT[MAX_TYPE] = {"OK", "YES"};
    static const char* BUTTON2_TEXT[MAX_TYPE] = {"CANCEL", "NO"};
    const byte DIALOG_CLOSE_KEY = VK_RETURN;  
    const COLOR_ARGB BUTTON_COLOR = graphicsNS::GRAY;    
    const COLOR_ARGB BUTTON_FONT_COLOR = graphicsNS::WHITE; 
}

class MessageDialog
{
protected:
    Graphics    *graphics;             
    Input       *input;                
    TextDX      dxFont;               
    float       x,y;                   
    UINT        height;               
    UINT        width;                 
    std::string text;                 
    RECT        textRect;              
    RECT        buttonRect;          
    RECT        button2Rect;           
    COLOR_ARGB  fontColor;            
    COLOR_ARGB  borderColor;            
    COLOR_ARGB  backColor;             
    COLOR_ARGB  buttonColor;           
    COLOR_ARGB  buttonFontColor;       
    VertexC vtx[4];                  
    LP_VERTEXBUFFER dialogVerts;
    LP_VERTEXBUFFER borderVerts;      
    LP_VERTEXBUFFER buttonVerts;      
    LP_VERTEXBUFFER button2Verts;    
    int buttonClicked;                
    int buttonType;                    
    bool    initialized;             
    bool    visible;                    
    HWND    hwnd;                   
    float   screenRatioX, screenRatioY;

public:
    MessageDialog();
    virtual ~MessageDialog();

   
    bool initialize(Graphics *g, Input *in, HWND hwnd);

    void prepareVerts();

    const void draw();

    int getButtonClicked()  {return buttonClicked;}

    bool getVisible() {return visible;}

    void setFontColor(COLOR_ARGB fc)    {fontColor = fc;}

    void setBorderColor(COLOR_ARGB bc)  {borderColor = bc;}

    void setBackColor(COLOR_ARGB bc)    {backColor = bc;}

    void setButtonColor(COLOR_ARGB bc)  {buttonColor = bc;}

    void setButtonFontColor(COLOR_ARGB bfc) {buttonFontColor = bfc;}

    void setVisible(bool v) {visible = v;}

    void setButtonType(UINT t)
    {
        if(t < messageDialogNS::MAX_TYPE)
            buttonType = t;
    }

    void print(const std::string &str);

    void update();

    void onLostDevice();

    void onResetDevice();
};

#endif


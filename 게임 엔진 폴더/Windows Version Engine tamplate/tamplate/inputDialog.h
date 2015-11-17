#ifndef _INPUTDIALOG_H         
#define _INPUTDIALOG_H        
#define WIN32_LEAN_AND_MEAN

class InputDialog;

#include <string>
#include "constants.h"
#include "textDX.h"
#include "graphics.h"
#include "input.h"
#include "messageDialog.h"

namespace inputDialogNS
{
    const COLOR_ARGB TEXT_BACK_COLOR = graphicsNS::WHITE;   
    const COLOR_ARGB TEXT_COLOR = graphicsNS::BLACK;        
}

class InputDialog : public MessageDialog
{
private:
    std::string inText;                
    RECT        inTextRect;
    RECT        tempRect;
    COLOR_ARGB  textBackColor;         
    COLOR_ARGB  textFontColor;          
    LP_VERTEXBUFFER inTextVerts;       

public:
    InputDialog();
    virtual ~InputDialog();

    void prepareVerts();

    const void draw();

    std::string getText()   
    {
        if(!visible)
            return inText;
        else
            return "";
    }

    void setTextFontColor(COLOR_ARGB fc)  {textFontColor = fc;}

    void setTextBackColor(COLOR_ARGB bc)  {textBackColor = bc;}

    void print(const std::string &str);

    void update();

    void onLostDevice();
};

#endif


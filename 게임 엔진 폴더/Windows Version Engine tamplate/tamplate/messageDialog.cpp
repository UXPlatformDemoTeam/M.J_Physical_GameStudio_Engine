#include "messageDialog.h"

MessageDialog::MessageDialog()
{
    initialized = false;            
    graphics = NULL;
    visible = false;                   
    fontColor = messageDialogNS::FONT_COLOR;
    borderColor = messageDialogNS::BORDER_COLOR;
    backColor = messageDialogNS::BACK_COLOR;
    buttonColor = messageDialogNS::BUTTON_COLOR;
    buttonFontColor = messageDialogNS::BUTTON_FONT_COLOR;
    x = static_cast<float>(messageDialogNS::X); 
    y = static_cast<float>(messageDialogNS::Y);
    height = messageDialogNS::HEIGHT;
    width = messageDialogNS::WIDTH;
    textRect.bottom = messageDialogNS::Y + messageDialogNS::HEIGHT - messageDialogNS::MARGIN;
    textRect.left = messageDialogNS::X + messageDialogNS::MARGIN;
    textRect.right = messageDialogNS::X + messageDialogNS::WIDTH - messageDialogNS::MARGIN;
    textRect.top = messageDialogNS::Y + messageDialogNS::MARGIN;
    dialogVerts = NULL;
    borderVerts = NULL;
    buttonVerts = NULL;
    button2Verts = NULL;
    buttonType = 0;    
}

MessageDialog::~MessageDialog()
{
    onLostDevice();           
}

bool MessageDialog::initialize(Graphics *g, Input *in, HWND h)
{
    try {
        graphics = g;                   
        input = in;                     
        hwnd = h;

        if(dxFont.initialize(graphics, messageDialogNS::FONT_HEIGHT, false, 
                             false, messageDialogNS::FONT) == false)
            return false;               
        dxFont.setFontColor(fontColor);
    } catch(...) {
        return false;
    }

    initialized = true;
    return true;
}

void MessageDialog::prepareVerts()
{
    safeRelease(dialogVerts);
    safeRelease(borderVerts);
    safeRelease(buttonVerts);
    safeRelease(button2Verts);

    vtx[0].x = x;
    vtx[0].y = y;
    vtx[0].z = 0.0f;
    vtx[0].rhw = 1.0f;
    vtx[0].color = borderColor;

    vtx[1].x = x + width;
    vtx[1].y = y;
    vtx[1].z = 0.0f;
    vtx[1].rhw = 1.0f;
    vtx[1].color = borderColor;

    vtx[2].x = x + width;
    vtx[2].y = y + height;
    vtx[2].z = 0.0f;
    vtx[2].rhw = 1.0f;
    vtx[2].color = borderColor;

    vtx[3].x = x;
    vtx[3].y = y + height;
    vtx[3].z = 0.0f;
    vtx[3].rhw = 1.0f;
    vtx[3].color = borderColor;

    graphics->createVertexBuffer(vtx, sizeof vtx, borderVerts);

    vtx[0].x = x + messageDialogNS::BORDER;
    vtx[0].y = y + messageDialogNS::BORDER;
    vtx[0].z = 0.0f;
    vtx[0].rhw = 1.0f;
    vtx[0].color = backColor;

    vtx[1].x = x + width - messageDialogNS::BORDER;
    vtx[1].y = y + messageDialogNS::BORDER;
    vtx[1].z = 0.0f;
    vtx[1].rhw = 1.0f;
    vtx[1].color = backColor;

    vtx[2].x = x + width - messageDialogNS::BORDER;
    vtx[2].y = y + height - messageDialogNS::BORDER;
    vtx[2].z = 0.0f;
    vtx[2].rhw = 1.0f;
    vtx[2].color = backColor;

    vtx[3].x = x + messageDialogNS::BORDER;
    vtx[3].y = y + height - messageDialogNS::BORDER;
    vtx[3].z = 0.0f;
    vtx[3].rhw = 1.0f;
    vtx[3].color = backColor;

    graphics->createVertexBuffer(vtx, sizeof vtx, dialogVerts);

    vtx[0].x = x + width/2.0f - messageDialogNS::BUTTON_WIDTH/2.0f;
    vtx[0].y = y + height - messageDialogNS::BORDER - messageDialogNS::MARGIN - messageDialogNS::BUTTON_HEIGHT;
    vtx[0].z = 0.0f;
    vtx[0].rhw = 1.0f;
    vtx[0].color = buttonColor;

    vtx[1].x = x + width/2.0f + messageDialogNS::BUTTON_WIDTH/2.0f;
    vtx[1].y = vtx[0].y;
    vtx[1].z = 0.0f;
    vtx[1].rhw = 1.0f;
    vtx[1].color = buttonColor;

    vtx[2].x =  vtx[1].x;
    vtx[2].y = vtx[0].y + messageDialogNS::BUTTON_HEIGHT;
    vtx[2].z = 0.0f;
    vtx[2].rhw = 1.0f;
    vtx[2].color = buttonColor;

    vtx[3].x = vtx[0].x;
    vtx[3].y = vtx[2].y;
    vtx[3].z = 0.0f;
    vtx[3].rhw = 1.0f;
    vtx[3].color = buttonColor;

    graphics->createVertexBuffer(vtx, sizeof vtx, buttonVerts);

    buttonRect.left   = (long)vtx[0].x;
    buttonRect.right  = (long)vtx[1].x;
    buttonRect.top    = (long)vtx[0].y;
    buttonRect.bottom = (long)vtx[2].y;

    vtx[0].x = x + width - messageDialogNS::BUTTON_WIDTH*1.2f;
    vtx[0].y = y + height - messageDialogNS::BORDER - messageDialogNS::MARGIN - messageDialogNS::BUTTON_HEIGHT;
    vtx[0].z = 0.0f;
    vtx[0].rhw = 1.0f;
    vtx[0].color = buttonColor;
  vtx[1].x = vtx[0].x + messageDialogNS::BUTTON_WIDTH;
    vtx[1].y = vtx[0].y;
    vtx[1].z = 0.0f;
    vtx[1].rhw = 1.0f;
    vtx[1].color = buttonColor;
    vtx[2].x =  vtx[1].x;
    vtx[2].y = vtx[0].y + messageDialogNS::BUTTON_HEIGHT;
    vtx[2].z = 0.0f;
    vtx[2].rhw = 1.0f;
    vtx[2].color = buttonColor;
    vtx[3].x = vtx[0].x;
    vtx[3].y = vtx[2].y;
    vtx[3].z = 0.0f;
    vtx[3].rhw = 1.0f;
    vtx[3].color = buttonColor;
    graphics->createVertexBuffer(vtx, sizeof vtx, button2Verts);

    button2Rect.left   = (long)vtx[0].x;
    button2Rect.right  = (long)vtx[1].x;
    button2Rect.top    = (long)vtx[0].y;
    button2Rect.bottom = (long)vtx[2].y;
}
const void MessageDialog::draw()
{
    if (!visible || graphics == NULL || !initialized)
        return;

    graphics->drawQuad(borderVerts);       
    graphics->drawQuad(dialogVerts);       
    graphics->drawQuad(buttonVerts);       
    graphics->drawQuad(button2Verts);       

    graphics->spriteBegin();                

    if(text.size() == 0)
        return;
    dxFont.setFontColor(fontColor);
    dxFont.print(text,textRect,DT_CENTER|DT_WORDBREAK);

    dxFont.setFontColor(buttonFontColor);
    dxFont.print(messageDialogNS::BUTTON1_TEXT[buttonType],buttonRect,
                 DT_SINGLELINE|DT_CENTER|DT_VCENTER);
    dxFont.print(messageDialogNS::BUTTON2_TEXT[buttonType],button2Rect,
                 DT_SINGLELINE|DT_CENTER|DT_VCENTER);

    graphics->spriteEnd();                 
}

void MessageDialog::update()
{
    if (!initialized || !visible)
        return;
    if (input->wasKeyPressed(messageDialogNS::DIALOG_CLOSE_KEY))
    {
        visible = false;
        buttonClicked = 1;              
        return;
    }

    if (graphics->getFullscreen() == false) 
    {
        RECT clientRect;
        GetClientRect(hwnd, &clientRect);
        screenRatioX = (float)GAME_WIDTH / clientRect.right;
        screenRatioY = (float)GAME_HEIGHT / clientRect.bottom;
    }

    if (input->getMouseLButton())     
    {
        if (input->getMouseX()*screenRatioX >= buttonRect.left &&
            input->getMouseX()*screenRatioX <= buttonRect.right &&
            input->getMouseY()*screenRatioY >= buttonRect.top &&
            input->getMouseY()*screenRatioY <= buttonRect.bottom)
        {
            visible = false;            
            buttonClicked = 1;         
            return;
        }

        if (input->getMouseX()*screenRatioX >= button2Rect.left &&
            input->getMouseX()*screenRatioX <= button2Rect.right &&
            input->getMouseY()*screenRatioY >= button2Rect.top &&
            input->getMouseY()*screenRatioY <= button2Rect.bottom)
        {
            visible = false;           
            buttonClicked = 2;          
        }
    }
}

void MessageDialog::print(const std::string &str)         
{
    if (!initialized || visible)
        return;
    text = str + "\n\n\n\n";     

    textRect.left   = (long)(x + messageDialogNS::MARGIN);
    textRect.right  = (long)(x + messageDialogNS::WIDTH - messageDialogNS::MARGIN);
    textRect.top    = (long)(y + messageDialogNS::MARGIN);
    textRect.bottom = (long)(y + messageDialogNS::HEIGHT - messageDialogNS::MARGIN);

    dxFont.print(text,textRect,DT_CENTER|DT_WORDBREAK|DT_CALCRECT);
    height = textRect.bottom - (int)y + messageDialogNS::BORDER + messageDialogNS::MARGIN;

    prepareVerts();                 
    buttonClicked = 0;             
    visible = true;
}

void MessageDialog::onLostDevice()
{
    if (!initialized)
        return;
    dxFont.onLostDevice();
    safeRelease(dialogVerts);
    safeRelease(borderVerts);
    safeRelease(buttonVerts);
    safeRelease(button2Verts);
}

void MessageDialog::onResetDevice()
{
    if (!initialized)
        return;
    prepareVerts();
    dxFont.onResetDevice();
}


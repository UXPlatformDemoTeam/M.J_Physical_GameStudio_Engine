#include "inputDialog.h"

InputDialog::InputDialog()
{
    textBackColor = inputDialogNS::TEXT_BACK_COLOR;
    textFontColor = inputDialogNS::TEXT_COLOR;
    inTextVerts = NULL;
    inText = "";
}

InputDialog::~InputDialog()
{
    onLostDevice();            
}

void InputDialog::prepareVerts()
{
    MessageDialog::prepareVerts();  
    safeRelease(inTextVerts);

    vtx[0].x = x + messageDialogNS::BORDER*2;
    vtx[0].y = y + height - messageDialogNS::BORDER - messageDialogNS::MARGIN - messageDialogNS::BUTTON_HEIGHT*2.5f;
    vtx[0].z = 0.0f;
    vtx[0].rhw = 1.0f;
    vtx[0].color = textBackColor;
    vtx[1].x = x + width - messageDialogNS::BORDER*2;
    vtx[1].y = vtx[0].y;
    vtx[1].z = 0.0f;
    vtx[1].rhw = 1.0f;
    vtx[1].color = textBackColor;
    vtx[2].x =  vtx[1].x;
    vtx[2].y = vtx[0].y + messageDialogNS::BUTTON_HEIGHT;
    vtx[2].z = 0.0f;
    vtx[2].rhw = 1.0f;
    vtx[2].color = textBackColor;
    vtx[3].x = vtx[0].x;
    vtx[3].y = vtx[2].y;
    vtx[3].z = 0.0f;
    vtx[3].rhw = 1.0f;
    vtx[3].color = textBackColor;
    graphics->createVertexBuffer(vtx, sizeof vtx, inTextVerts);

    inTextRect.left   = (long)vtx[0].x;
    inTextRect.right  = (long)vtx[1].x;
    inTextRect.top    = (long)vtx[0].y;
    inTextRect.bottom = (long)vtx[2].y;
}

const void InputDialog::draw()
{
    if (!visible || graphics == NULL || !initialized)
        return;

    graphics->drawQuad(borderVerts);        
    graphics->drawQuad(dialogVerts);        
    graphics->drawQuad(buttonVerts);      
    graphics->drawQuad(button2Verts);      
    graphics->drawQuad(inTextVerts);      

    graphics->spriteBegin();                

    if(text.size() == 0)
        return;
    dxFont.setFontColor(fontColor);
    dxFont.print(text,textRect,DT_CENTER|DT_WORDBREAK);

    dxFont.setFontColor(buttonFontColor);
    dxFont.print(messageDialogNS::BUTTON1_TEXT[buttonType],buttonRect,DT_SINGLELINE|DT_CENTER|DT_VCENTER);
    dxFont.print(messageDialogNS::BUTTON2_TEXT[buttonType],button2Rect,DT_SINGLELINE|DT_CENTER|DT_VCENTER);

    dxFont.setFontColor(textFontColor);
    tempRect = inTextRect; 
    dxFont.print(inText,tempRect,DT_SINGLELINE|DT_LEFT|DT_VCENTER|DT_CALCRECT);
    if(tempRect.right > inTextRect.right)  
        dxFont.print(inText,inTextRect,DT_SINGLELINE|DT_RIGHT|DT_VCENTER);
    else    
        dxFont.print(inText,inTextRect,DT_SINGLELINE|DT_LEFT|DT_VCENTER);

    graphics->spriteEnd();                 
}

void InputDialog::update()
{
    MessageDialog::update();      
    if (!initialized || !visible)
    {
        if(buttonClicked == 2)     
            inText = "";           
        return;
    }
    inText = input->getTextIn();    
}

void InputDialog::print(const std::string &str)         
{
    if (!initialized || visible)   
        return;
    text = str + "\n\n\n\n\n";   

    textRect.left   = (long)(x + messageDialogNS::MARGIN);
    textRect.right  = (long)(x + messageDialogNS::WIDTH - messageDialogNS::MARGIN);
    textRect.top    = (long)(y + messageDialogNS::MARGIN);
    textRect.bottom = (long)(y + messageDialogNS::HEIGHT - messageDialogNS::MARGIN);

      dxFont.print(text,textRect,DT_CENTER|DT_WORDBREAK|DT_CALCRECT);
    height = textRect.bottom - (int)y + messageDialogNS::BORDER + messageDialogNS::MARGIN;

    prepareVerts();                
    inText = "";                 
    input->clearTextIn();
    buttonClicked = 0;             
    visible = true;
}

void InputDialog::onLostDevice()
{
    if (!initialized)
        return;
    MessageDialog::onLostDevice();
    safeRelease(inTextVerts);
}


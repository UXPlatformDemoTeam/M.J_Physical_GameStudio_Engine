#include "graphics.h"

Graphics::Graphics()
{
    direct3d = NULL;
    device3d = NULL;
    sprite = NULL;
    fullscreen = false;
    stencilSupport = false;
    width = GAME_WIDTH;    
    height = GAME_HEIGHT;
    backColor = graphicsNS::BACK_COLOR;
    pOcclusionQuery = NULL;
    numberOfPixelsColliding = 0;
}
Graphics::~Graphics()
{
    releaseAll();
}

void Graphics::releaseAll()
{
    safeRelease(pOcclusionQuery);
    safeRelease(sprite);
    safeRelease(device3d);
    safeRelease(direct3d);
}

void Graphics::initialize(HWND hw, int w, int h, bool full)
{
    hwnd = hw;
    width = w;
    height = h;
    fullscreen = full;

    direct3d = Direct3DCreate9(D3D_SDK_VERSION);
    if (direct3d == NULL)
        throw(GameError(gameErrorNS::FATAL_ERROR, "Error initializing Direct3D"));

    initD3Dpp();       
    if(fullscreen)     
    {
        if(isAdapterCompatible())   
            d3dpp.FullScreen_RefreshRateInHz = pMode.RefreshRate;
        else
            throw(GameError(gameErrorNS::FATAL_ERROR, 
            "The graphics device does not support the specified resolution and/or format."));
    }

    D3DCAPS9 caps;
    DWORD behavior;
    result = direct3d->GetDeviceCaps(D3DADAPTER_DEFAULT, D3DDEVTYPE_HAL, &caps);
    if( (caps.DevCaps & D3DDEVCAPS_HWTRANSFORMANDLIGHT) == 0 ||
            caps.VertexShaderVersion < D3DVS_VERSION(1,1) )
        behavior = D3DCREATE_SOFTWARE_VERTEXPROCESSING;  
    else
        behavior = D3DCREATE_HARDWARE_VERTEXPROCESSING;  

    result = direct3d->CreateDevice(
        D3DADAPTER_DEFAULT,
        D3DDEVTYPE_HAL,
        hwnd,
        behavior,
        &d3dpp, 
        &device3d);

    if (FAILED(result))
        throw(GameError(gameErrorNS::FATAL_ERROR, "Error creating Direct3D device"));
 
    result = D3DXCreateSprite(device3d, &sprite);
    if (FAILED(result))
        throw(GameError(gameErrorNS::FATAL_ERROR, "Error creating Direct3D sprite"));

    device3d->SetRenderState(D3DRS_BLENDOP, D3DBLENDOP_ADD);
    device3d->SetRenderState(D3DRS_SRCBLEND, D3DBLEND_SRCALPHA);
    device3d->SetRenderState(D3DRS_DESTBLEND, D3DBLEND_INVSRCALPHA);
    if( FAILED( direct3d->CheckDeviceFormat(caps.AdapterOrdinal,
                                            caps.DeviceType,  
                                            d3dpp.BackBufferFormat,  
                                            D3DUSAGE_DEPTHSTENCIL, 
                                            D3DRTYPE_SURFACE,
                                            D3DFMT_D24S8 ) ) )
        stencilSupport = false;
    else
        stencilSupport = true;
    device3d->CreateQuery(D3DQUERYTYPE_OCCLUSION, &pOcclusionQuery);
}

void Graphics::initD3Dpp()
{
    try{
        ZeroMemory(&d3dpp, sizeof(d3dpp));             
        d3dpp.BackBufferWidth   = width;
        d3dpp.BackBufferHeight  = height;
        if(fullscreen)                                  
            d3dpp.BackBufferFormat  = D3DFMT_X8R8G8B8;  
        else
            d3dpp.BackBufferFormat  = D3DFMT_UNKNOWN;   
        d3dpp.BackBufferCount   = 1;
        d3dpp.SwapEffect        = D3DSWAPEFFECT_DISCARD;
        d3dpp.hDeviceWindow     = hwnd;
        d3dpp.Windowed          = (!fullscreen);
        d3dpp.PresentationInterval   = D3DPRESENT_INTERVAL_IMMEDIATE;
        d3dpp.EnableAutoDepthStencil = true;
        d3dpp.AutoDepthStencilFormat = D3DFMT_D24S8;   
    } catch(...)
    {
        throw(GameError(gameErrorNS::FATAL_ERROR, 
                "Error initializing D3D presentation parameters"));
    }
}

HRESULT Graphics::loadTexture(const char *filename, COLOR_ARGB transcolor,
                                UINT &width, UINT &height, LP_TEXTURE &texture)
{
    D3DXIMAGE_INFO info;
    result = E_FAIL;

    try{
        if(filename == NULL)
        {
            texture = NULL;
            return D3DERR_INVALIDCALL;
        }
    
        result = D3DXGetImageInfoFromFile(filename, &info);
        if (result != D3D_OK)
            return result;
        width = info.Width;
        height = info.Height;
    
        result = D3DXCreateTextureFromFileEx( 
            device3d,           
            filename,           
            info.Width,       
            info.Height,       
            1,                
            0,                  
            D3DFMT_UNKNOWN,     
            D3DPOOL_DEFAULT,    
            D3DX_DEFAULT,      
            D3DX_DEFAULT,       
            transcolor,         
            &info,             
            NULL,             
            &texture );         

    } catch(...)
    {
        throw(GameError(gameErrorNS::FATAL_ERROR, "Error in Graphics::loadTexture"));
    }
    return result;
}

HRESULT Graphics::loadTextureSystemMem(const char *filename, COLOR_ARGB transcolor, 
                                    UINT &width, UINT &height, LP_TEXTURE &texture)
{
    D3DXIMAGE_INFO info;
    result = E_FAIL;        

    try{
        if(filename == NULL)
        {
            texture = NULL;
            return D3DERR_INVALIDCALL;
        }
        result = D3DXGetImageInfoFromFile(filename, &info);
        if (result != D3D_OK)
            return result;
        width = info.Width;
        height = info.Height;

        result = D3DXCreateTextureFromFileEx( 
            device3d,          
            filename,         
            info.Width,        
            info.Height,       
            1,                  
            0,                
            D3DFMT_UNKNOWN,   
            D3DPOOL_SYSTEMMEM, 
            D3DX_DEFAULT,      
            D3DX_DEFAULT,     
            transcolor,        
            &info,              
            NULL,               
            &texture );        

    } catch(...)
    {
        throw(GameError(gameErrorNS::FATAL_ERROR, "Error in Graphics::loadTexture"));
    }
    return result;
}

HRESULT Graphics::createVertexBuffer(VertexC verts[], UINT size, LP_VERTEXBUFFER &vertexBuffer)
{
    HRESULT result = E_FAIL;

    result = device3d->CreateVertexBuffer(size, D3DUSAGE_WRITEONLY, D3DFVF_VERTEX,
                                            D3DPOOL_DEFAULT, &vertexBuffer, NULL);
    if(FAILED(result))
        return result;

    void *ptr;
    result = vertexBuffer->Lock(0, size, (void**)&ptr, 0);
    if(FAILED(result))
        return result;
    memcpy(ptr, verts, size);   
    vertexBuffer->Unlock();     

    return result;
}

bool Graphics::drawQuad(LP_VERTEXBUFFER vertexBuffer)
{
    HRESULT result = E_FAIL;   

    if(vertexBuffer == NULL)
        return false;
     
    device3d->SetRenderState(D3DRS_ALPHABLENDENABLE, true);

    device3d->SetStreamSource(0, vertexBuffer, 0, sizeof(VertexC));
    device3d->SetFVF(D3DFVF_VERTEX);
    result = device3d->DrawPrimitive(D3DPT_TRIANGLEFAN, 0, 2);

    device3d->SetRenderState(D3DRS_ALPHABLENDENABLE, false); 
    
    if(FAILED(result))
        return false;

    return true;
}

HRESULT Graphics::showBackbuffer()
{
    result = device3d->Present(NULL, NULL, NULL, NULL);
    return result;
}

bool Graphics::isAdapterCompatible()
{
    UINT modes = direct3d->GetAdapterModeCount(D3DADAPTER_DEFAULT, 
                                            d3dpp.BackBufferFormat);
    for(UINT i=0; i<modes; i++)
    {
        result = direct3d->EnumAdapterModes( D3DADAPTER_DEFAULT, 
                                        d3dpp.BackBufferFormat,
                                        i, &pMode);
        if( pMode.Height == d3dpp.BackBufferHeight &&
            pMode.Width  == d3dpp.BackBufferWidth &&
            pMode.RefreshRate >= d3dpp.FullScreen_RefreshRateInHz)
            return true;
    }
    return false;
}

void Graphics::drawSprite(const SpriteData &spriteData, COLOR_ARGB color)
{
    if(spriteData.texture == NULL)    
        return;

    D3DXVECTOR2 spriteCenter=D3DXVECTOR2((float)(spriteData.width/2*spriteData.scale),
                                        (float)(spriteData.height/2*spriteData.scale));

    D3DXVECTOR2 translate=D3DXVECTOR2((float)spriteData.x,(float)spriteData.y);
 
    D3DXVECTOR2 scaling(spriteData.scale,spriteData.scale);
    if (spriteData.flipHorizontal) 
    {
        scaling.x *= -1;           
   
        spriteCenter.x -= (float)(spriteData.width*spriteData.scale);
         translate.x += (float)(spriteData.width*spriteData.scale);
    }
    if (spriteData.flipVertical)    
    {
        scaling.y *= -1;        
        spriteCenter.y -= (float)(spriteData.height*spriteData.scale);
        translate.y += (float)(spriteData.height*spriteData.scale);
    }
     D3DXMATRIX matrix;
    D3DXMatrixTransformation2D(
        &matrix,              
        NULL,                 
        0.0f,               
        &scaling,             
        &spriteCenter,       
        (float)(spriteData.angle), 
        &translate);          
 sprite->SetTransform(&matrix);

    sprite->Draw(spriteData.texture,&spriteData.rect,NULL,NULL,color);
}
void Graphics::changeDisplayMode(graphicsNS::DISPLAY_MODE mode)
{
    try{
        switch(mode)
        {
        case graphicsNS::FULLSCREEN:
            if(fullscreen)      
                return;
            fullscreen = true; break;
        case graphicsNS::WINDOW:
            if(fullscreen == false) 
                return;
            fullscreen = false; break;
        default:        
            fullscreen = !fullscreen;
        }
        reset();
        if(fullscreen)
        {
            SetWindowLong(hwnd, GWL_STYLE,  WS_EX_TOPMOST | WS_VISIBLE | WS_POPUP);
        }
        else         
        {
            SetWindowLong(hwnd, GWL_STYLE, WS_OVERLAPPEDWINDOW);
            SetWindowPos(hwnd, HWND_TOP, 0,0,GAME_WIDTH,GAME_HEIGHT,
                SWP_FRAMECHANGED | SWP_NOMOVE | SWP_NOSIZE | SWP_SHOWWINDOW);

             RECT clientRect;
            GetClientRect(hwnd, &clientRect); 
            MoveWindow(hwnd,
                       0,                                           
                       0,                                           
                       GAME_WIDTH+(GAME_WIDTH-clientRect.right),  
                       GAME_HEIGHT+(GAME_HEIGHT-clientRect.bottom),
                       TRUE);                                      
        }

    } catch(...)
    {
        SetWindowLong(hwnd, GWL_STYLE, WS_OVERLAPPEDWINDOW);
        SetWindowPos(hwnd, HWND_TOP, 0,0,GAME_WIDTH,GAME_HEIGHT,
            SWP_FRAMECHANGED | SWP_NOMOVE | SWP_NOSIZE | SWP_SHOWWINDOW);
  RECT clientRect;
        GetClientRect(hwnd, &clientRect); 
        MoveWindow(hwnd,
                    0,                                          
                    0,                                           
                    GAME_WIDTH+(GAME_WIDTH-clientRect.right),    
                    GAME_HEIGHT+(GAME_HEIGHT-clientRect.bottom),
                    TRUE);                                       
    }
}

DWORD Graphics::pixelCollision(const SpriteData &sprite1, const SpriteData &sprite2)
{
    if(!stencilSupport)    
        return 0;

    beginScene();

 
    device3d->SetRenderState(D3DRS_STENCILENABLE,   true);
    device3d->SetRenderState(D3DRS_STENCILFUNC,     D3DCMP_ALWAYS);
    device3d->SetRenderState(D3DRS_STENCILREF,      0x1);
    device3d->SetRenderState(D3DRS_STENCILMASK,     0xffffffff);
    device3d->SetRenderState(D3DRS_STENCILWRITEMASK,0xffffffff);
    device3d->SetRenderState(D3DRS_STENCILFAIL,     D3DSTENCILOP_KEEP);
    device3d->SetRenderState(D3DRS_STENCILPASS,     D3DSTENCILOP_REPLACE);

    spriteBegin();
    device3d->SetRenderState(D3DRS_STENCILENABLE,   true);
    drawSprite(sprite2);           
    spriteEnd();

    device3d->SetRenderState(D3DRS_STENCILFUNC, D3DCMP_EQUAL);
    device3d->SetRenderState(D3DRS_STENCILPASS, D3DSTENCILOP_KEEP);
    
    pOcclusionQuery->Issue(D3DISSUE_BEGIN);

    spriteBegin();
    device3d->SetRenderState(D3DRS_STENCILENABLE,   true);
    drawSprite(sprite1);           
    spriteEnd();
    pOcclusionQuery->Issue(D3DISSUE_END);

    while(S_FALSE == pOcclusionQuery->GetData( &numberOfPixelsColliding, 
                                  sizeof(DWORD), D3DGETDATA_FLUSH ))
    {}
    device3d->SetRenderState(D3DRS_STENCILENABLE, false);

    endScene();
    return numberOfPixelsColliding;
}

HRESULT Graphics::getDeviceState()
{ 
    result = E_FAIL;   
    if (device3d == NULL)
        return  result;
    result = device3d->TestCooperativeLevel(); 
    return result;
}

HRESULT Graphics::reset()
{
    safeRelease(pOcclusionQuery);     
    initD3Dpp();                      
    sprite->OnLostDevice();           
    result = device3d->Reset(&d3dpp);  

    device3d->SetRenderState(D3DRS_BLENDOP, D3DBLENDOP_ADD);
    device3d->SetRenderState(D3DRS_SRCBLEND, D3DBLEND_SRCALPHA);
    device3d->SetRenderState(D3DRS_DESTBLEND, D3DBLEND_INVSRCALPHA);

    device3d->CreateQuery(D3DQUERYTYPE_OCCLUSION, &pOcclusionQuery);
    sprite->OnResetDevice();           
    return result;
}

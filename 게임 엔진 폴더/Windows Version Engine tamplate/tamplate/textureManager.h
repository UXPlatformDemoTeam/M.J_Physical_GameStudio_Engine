#ifndef _TEXTUREMANAGER_H     
#define _TEXTUREMANAGER_H       
#define WIN32_LEAN_AND_MEAN

class TextureManager;

#include <vector>
#include <string>
#include <fstream>
#include "graphics.h"
#include "constants.h"

class TextureManager
{
  
  private:
    std::vector<UINT>       width;   
    std::vector<UINT>       height;    
    std::vector<LP_TEXTURE> texture; 
    std::vector<std::string> fileNames; 
    Graphics *graphics;    
    bool    initialized;    
    HRESULT hr;          

  public:
    TextureManager();

    virtual ~TextureManager();

    LP_TEXTURE getTexture(UINT n=0) const 
    {
        if(n >= texture.size())
            return NULL;
        return texture[n];
    }

    UINT getWidth(UINT n=0) const 
    {
        if(n >= texture.size())
            return 0;
        return width[n];
    }

    UINT getHeight(UINT n=0) const
    {
        if(n >= texture.size())
            return 0;
        return height[n];
    }


    virtual bool initialize(Graphics *g, std::string file);

    virtual void onLostDevice();

    virtual void onResetDevice();

    void safeReleaseTexture(LP_TEXTURE& ptr)
    {
        if (ptr)
        { 
            ptr->Release(); 
            ptr = NULL;
        }
    }
};

#endif


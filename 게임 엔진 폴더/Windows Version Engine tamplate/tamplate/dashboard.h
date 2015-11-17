#ifndef _DASHBOARD_H          
#define _DASHBOARD_H           
#define WIN32_LEAN_AND_MEAN

class Dashboard;

#include "image.h"
#include "constants.h"
#include "textureManager.h"

namespace dashboardNS
{
    const int   IMAGE_SIZE = 32;       
    const int   TEXTURE_COLS = 8;      
    const int   BAR_FRAME = 44;        
}

class Bar : public Image
{
    public:
    bool initialize(Graphics *graphics, TextureManager *textureM, int left, int top,
                    float scale, COLOR_ARGB color);
    void set(float percentOn);
     virtual void update(float frameTime)    {}
};

#endif


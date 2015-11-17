#include "textureManager.h"

TextureManager::TextureManager()
{
    graphics = NULL;
    initialized = false;            
}

TextureManager::~TextureManager()
{
    for(UINT i=0; i<texture.size(); i++)
        safeReleaseTexture(texture[i]);
}


bool TextureManager::initialize(Graphics *g, std::string file)
{
    bool success = true;
    try{
        graphics = g;                      
        for(UINT i=0; i<file.size(); i++)   
            file.at(i) = tolower(file.at(i));
        if(file.rfind(".txt") == file.size()-4) 
        {
            std::ifstream infile(file.c_str());   
            if(!infile)                   
                return false;
            std::string name;
            while(getline(infile,name))
            {
                fileNames.push_back(name); 
                width.push_back(0);        
                height.push_back(0);        
                texture.push_back(NULL);   
            }
            infile.close();
        } 
        else  
        {
            fileNames.push_back(file);    
            width.push_back(0);       
            height.push_back(0);       
            texture.push_back(NULL);    
        }

        for(UINT i=0; i<fileNames.size(); i++)
        {
            hr = graphics->loadTexture(fileNames[i].c_str(), 
                 graphicsNS::TRANSCOLOR, width[i], height[i], texture[i]);
            if (FAILED(hr))
                success = false;    
        }
    }
    catch(...) {return false;}
    initialized = true;                   
    return success;
}

void TextureManager::onLostDevice()
{
    try
    {
        if (!initialized)
            return;
        for(UINT i=0; i<texture.size(); i++)
            safeReleaseTexture(texture[i]);
    }catch(...)
    {
        throw(GameError(gameErrorNS::WARNING, 
            "Warning, TextureManager onLostDevice attempted to access an invalid texture."));
    }
}

void TextureManager::onResetDevice()
{
    if (!initialized)
        return;
    for(UINT i=0; i<fileNames.size(); i++)
    {
        hr = graphics->loadTexture(fileNames[i].c_str(), 
             graphicsNS::TRANSCOLOR, width[i], height[i], texture[i]);
        if (FAILED(hr))
            safeReleaseTexture(texture[i]);
    }
}



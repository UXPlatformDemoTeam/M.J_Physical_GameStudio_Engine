#include "entity.h"

Entity::Entity() : Image()
{
    radius = 1.0;
    edge.left = -1;
    edge.top = -1;
    edge.right = 1;
    edge.bottom = 1;
    mass = 1.0;
    velocity.x = 0.0;
    velocity.y = 0.0;
    deltaV.x = 0.0;
    deltaV.y = 0.0;
    active = true;                  
    rotatedBoxReady = false;
    collisionType = entityNS::CIRCLE;
    health = 100;
    gravity = entityNS::GRAVITY;
    pixelsColliding = 0;
}

bool Entity::initialize(Game *gamePtr, int width, int height, int ncols,
                            TextureManager *textureM)
{
    input = gamePtr->getInput();              
    audio = gamePtr->getAudio();            
    return(Image::initialize(gamePtr->getGraphics(), width, height, ncols, textureM));
}

void Entity::activate()
{
    active = true;
}

void Entity::update(float frameTime)
{
    velocity += deltaV;
    deltaV.x = 0;
    deltaV.y = 0;
    Image::update(frameTime);
    rotatedBoxReady = false;   
}

void Entity::ai(float frameTime, Entity &ent)
{}

bool Entity::collidesWith(Entity &ent, VECTOR2 &collisionVector)
{ 
    if (!active || !ent.getActive())    
        return false;

    if (collisionType == entityNS::CIRCLE && ent.getCollisionType() == entityNS::CIRCLE)
        return collideCircle(ent, collisionVector);
     if (collisionType == entityNS::BOX && ent.getCollisionType() == entityNS::BOX)
        return collideBox(ent, collisionVector);
    if (collisionType == entityNS::PIXEL_PERFECT || ent.getCollisionType() == entityNS::PIXEL_PERFECT)
        return collidePixelPerfect(ent, collisionVector);
    if (collisionType != entityNS::CIRCLE && ent.getCollisionType() != entityNS::CIRCLE)
        return collideRotatedBox(ent, collisionVector);
    else    
        if (collisionType == entityNS::CIRCLE) 
        {
         
            bool collide = ent.collideRotatedBoxCircle(*this, collisionVector); 
       
            collisionVector *= -1;           
            return collide;
        }
        else  
            return collideRotatedBoxCircle(ent, collisionVector);
    return false;
}

bool Entity::collideCircle(Entity &ent, VECTOR2 &collisionVector)
{
    distSquared = *getCenter() - *ent.getCenter();
    distSquared.x = distSquared.x * distSquared.x;    
    distSquared.y = distSquared.y * distSquared.y;

    sumRadiiSquared = (radius*getScale()) + (ent.radius*ent.getScale());
    sumRadiiSquared *= sumRadiiSquared;                 

    if(distSquared.x + distSquared.y <= sumRadiiSquared)
    {
        collisionVector = *ent.getCenter() - *getCenter();
        return true;
    }
    return false; 
}
bool Entity::collideBox(Entity &ent, VECTOR2 &collisionVector)
{
    if (!active || !ent.getActive())
        return false;

     if( (getCenterX() + edge.right*getScale() >= ent.getCenterX() + ent.getEdge().left*ent.getScale()) && 
        (getCenterX() + edge.left*getScale() <= ent.getCenterX() + ent.getEdge().right*ent.getScale()) &&
        (getCenterY() + edge.bottom*getScale() >= ent.getCenterY() + ent.getEdge().top*ent.getScale()) && 
        (getCenterY() + edge.top*getScale() <= ent.getCenterY() + ent.getEdge().bottom*ent.getScale()) )
    {
        collisionVector = *ent.getCenter() - *getCenter();
        return true;
    }
    return false;
}

bool Entity::collideRotatedBox(Entity &entB, VECTOR2 &collisionVector)
{
    float overlap01, overlap03;
    computeRotatedBox();                 
    entB.computeRotatedBox();           
    if (projectionsOverlap(entB, collisionVector) && entB.projectionsOverlap(*this, collisionVector))
    {
 

        if (entA01min < entB01min)
        {
            overlap01 = entA01max - entB01min;
            collisionVector = corners[1] - corners[0];
        }
        else   
        {
            overlap01 = entB01max - entA01min;
            collisionVector = corners[0] - corners[1];
        }
        if (entA03min < entB03min) 
        {
            overlap03 = entA03max - entB03min;
            if (overlap03 < overlap01)
                collisionVector = corners[3] - corners[0];
        }
        else   
        {
            overlap03 = entB03max - entA03min;
            if (overlap03 < overlap01)
                collisionVector = corners[0] - corners[3];
        }
        return true;
    }
    return false;
}

bool Entity::projectionsOverlap(Entity &entB, VECTOR2 &collisionVector)
{
    float projection;

    projection = graphics->Vector2Dot(&edge01, entB.getCorner(0));
    entB01min = projection;
    entB01max = projection;
    for(int c=1; c<4; c++)
    {
        projection = graphics->Vector2Dot(&edge01, entB.getCorner(c));
        if (projection < entB01min)
            entB01min = projection;
        else if (projection > entB01max)
            entB01max = projection;
    }
    if (entB01min > entA01max || entB01max < entA01min)
        return false;                      
    projection = graphics->Vector2Dot(&edge03, entB.getCorner(0)); 
    entB03min = projection;
    entB03max = projection;
    for(int c=1; c<4; c++)
    {
        projection = graphics->Vector2Dot(&edge03, entB.getCorner(c));
        if (projection < entB03min)
            entB03min = projection;
        else if (projection > entB03max)
            entB03max = projection;
    }
    if (entB03min > entA03max || entB03max < entA03min) 
        return false;                      
    return true;                          
}

bool Entity::collideRotatedBoxCircle(Entity &entB, VECTOR2 &collisionVector)
{
    float center01, center03, overlap01, overlap03;

    computeRotatedBox();                  

    center01 = graphics->Vector2Dot(&edge01, entB.getCenter());
    entB01min = center01 - entB.getRadius()*entB.getScale();
    entB01max = center01 + entB.getRadius()*entB.getScale();
    if (entB01min > entA01max || entB01max < entA01min)
        return false;                      
    center03 = graphics->Vector2Dot(&edge03, entB.getCenter());
    entB03min = center03 - entB.getRadius()*entB.getScale();
    entB03max = center03 + entB.getRadius()*entB.getScale();
    if (entB03min > entA03max || entB03max < entA03min) 
        return false;                      
    if(center01 < entA01min && center03 < entA03min)   
        return collideCornerCircle(corners[0], entB, collisionVector);
    if(center01 > entA01max && center03 < entA03min)   
        return collideCornerCircle(corners[1], entB, collisionVector);
    if(center01 > entA01max && center03 > entA03max)    
        return collideCornerCircle(corners[2], entB, collisionVector);
    if(center01 < entA01min && center03 > entA03max)    
        return collideCornerCircle(corners[3], entB, collisionVector);

    if (entA01min < entB01min)  
    {
        overlap01 = entA01max - entB01min;
        collisionVector = corners[1] - corners[0];
    }
    else   
    {
        overlap01 = entB01max - entA01min;
        collisionVector = corners[0] - corners[1];
    }
    if (entA03min < entB03min)  
    {
        overlap03 = entA03max - entB03min;
        if (overlap03 < overlap01)
            collisionVector = corners[3] - corners[0];
    }
    else   
    {
        overlap03 = entB03max - entA03min;
        if (overlap03 < overlap01)
            collisionVector = corners[0] - corners[3];
    }
    return true;
}

bool Entity::collideCornerCircle(VECTOR2 corner, Entity &ent, VECTOR2 &collisionVector)
{
    distSquared = corner - *ent.getCenter();            
    distSquared.x = distSquared.x * distSquared.x;      
    distSquared.y = distSquared.y * distSquared.y;

    sumRadiiSquared = ent.getRadius()*ent.getScale();  
    sumRadiiSquared *= sumRadiiSquared;                 

    if(distSquared.x + distSquared.y <= sumRadiiSquared)
    {
        collisionVector = *ent.getCenter() - corner;
        return true;
    }
    return false;
}

void Entity::computeRotatedBox()
{
    if(rotatedBoxReady)
        return;
    float projection;

    VECTOR2 rotatedX(cos(spriteData.angle), sin(spriteData.angle));
    VECTOR2 rotatedY(-sin(spriteData.angle), cos(spriteData.angle));

    const VECTOR2 *center = getCenter();
    corners[0] = *center + rotatedX * ((float)edge.left*getScale())  +
                           rotatedY * ((float)edge.top*getScale());
    corners[1] = *center + rotatedX * ((float)edge.right*getScale()) + 
                           rotatedY * ((float)edge.top*getScale());
    corners[2] = *center + rotatedX * ((float)edge.right*getScale()) + 
                           rotatedY * ((float)edge.bottom*getScale());
    corners[3] = *center + rotatedX * ((float)edge.left*getScale())  +
                           rotatedY * ((float)edge.bottom*getScale());

    edge01 = VECTOR2(corners[1].x - corners[0].x, corners[1].y - corners[0].y);
    graphics->Vector2Normalize(&edge01);
    edge03 = VECTOR2(corners[3].x - corners[0].x, corners[3].y - corners[0].y);
    graphics->Vector2Normalize(&edge03);

    projection = graphics->Vector2Dot(&edge01, &corners[0]);
    entA01min = projection;
    entA01max = projection;
    projection = graphics->Vector2Dot(&edge01, &corners[1]);
    if (projection < entA01min)
        entA01min = projection;
    else if (projection > entA01max)
        entA01max = projection;
    projection = graphics->Vector2Dot(&edge03, &corners[0]);
    entA03min = projection;
    entA03max = projection;
    projection = graphics->Vector2Dot(&edge03, &corners[3]);
    if (projection < entA03min)
        entA03min = projection;
    else if (projection > entA03max)
        entA03max = projection;

    rotatedBoxReady = true;
}

bool Entity::collidePixelPerfect(Entity &ent, VECTOR2 &collisionVector)
{
    if(graphics->getStencilSupport() == false)  
        return (collideCircle(ent, collisionVector)); 

    ent.spriteData.texture = ent.textureManager->getTexture();
    spriteData.texture = textureManager->getTexture();

    pixelsColliding = graphics->pixelCollision(ent.getSpriteData(), this->getSpriteData());
    if(pixelsColliding > 0)
    {
        collisionVector = *ent.getCenter() - *getCenter();
        return true;
    }
    return false;
}

bool Entity::outsideRect(RECT rect)
{
    if( spriteData.x + spriteData.width*getScale() < rect.left || 
        spriteData.x > rect.right ||
        spriteData.y + spriteData.height*getScale() < rect.top || 
        spriteData.y > rect.bottom)
        return true;
    return false;
}

void Entity::damage(int weapon)
{}

void Entity::bounce(VECTOR2 &collisionVector, Entity &ent)
{
    VECTOR2 Vdiff = ent.getVelocity() - velocity;
    VECTOR2 cUV = collisionVector;        
    Graphics::Vector2Normalize(&cUV);
    float cUVdotVdiff = Graphics::Vector2Dot(&cUV, &Vdiff);
    float massRatio = 2.0f;
    if (getMass() != 0)
        massRatio *= (ent.getMass() / (getMass() + ent.getMass()));
    if(massRatio < 0.1f)
        massRatio = 0.1f;

    VECTOR2 cv;
    int count=10;   
    do
    {
        setX(getX() - cUV.x);
        setY(getY() - cUV.y);
        rotatedBoxReady = false;
        count--;
    } while( this->collidesWith(ent, cv) && count);

 
    deltaV += ((massRatio * cUVdotVdiff) * cUV);
}

void Entity::gravityForce(Entity *ent, float frameTime)
{
    if (!active || !ent->getActive())
        return ;

    rr = pow((ent->getCenterX() - getCenterX()),2) + 
            pow((ent->getCenterY() - getCenterY()),2);
    force = gravity * ent->getMass() * mass/rr;
 VECTOR2 gravityV(ent->getCenterX() - getCenterX(),
                        ent->getCenterY() - getCenterY());
    Graphics::Vector2Normalize(&gravityV);
     gravityV *= force * frameTime;
     velocity += gravityV;
}

#ifndef _PARTICLEHEADER
#define _PARTICLEHEADER

#include "mymath.h"

#define	 _GRAVITYACCELERATION		-1.8f		
#define  _DRAGCOEFFICIENT			0.6f	
#define  _AIRDENSITY				1.23f	
#define  _WINDSPEED					0.1f		
#define  _RESTITUTION				0.2f		


typedef struct _Spring{
	int End1;
	int End2;
	float k;
	float d;
	float InitialLength;
}Spring, *pSpring;

class Particle {
public:
	float	fMass;						
	float	fLength;					
	Vector	vPosition;					
	Vector	vVelocity;					
	float	fSpeed;						
	Vector	vForces;					
	float	fRadius;				
	Vector	vGravity;					
	Vector  vPreviousPosition;		
	Vector  vImpactForces;				
	Vector	vSprings;					
	bool	bCollision;
	bool	bLocked;

	Particle(void);					
	void	CalcLoads(void);			
	void	UpdateBodyEuler(double dt);
	void	Draw();					
	void	DrawObjectLine(Vector &);
};

Vector	VRotate2D( float angle, Vector u);

#endif
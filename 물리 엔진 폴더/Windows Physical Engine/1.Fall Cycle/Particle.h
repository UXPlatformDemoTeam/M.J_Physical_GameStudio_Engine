#ifndef _PARTICLEHEADER
#define _PARTICLEHEADER

#include "mymath.h"

#define	 _GRAVITYACCELERATION		-9.8f		
#define  _DRAGCOEFFICIENT			0.6f		
#define  _AIRDENSITY				1.23f		
#define  _WINDSPEED					0.1f		
#define  _RESTITUTION				0.2f		


class Particle {
public:
	float	fMass;						
	Vector	vPosition;					
	Vector	vVelocity;					
	float	fSpeed;						
	Vector	vForces;					
	float	fRadius;					
	Vector	vGravity;					
	Vector  vPreviousPosition;			
	Vector  vImpactForces;				
	bool	bCollision;

	Particle(void);						
	void	CalcLoads(void);			
	void	UpdateBodyEuler(double dt);
	void	Draw(void);				
};

Vector	VRotate2D( float angle, Vector u);

#endif
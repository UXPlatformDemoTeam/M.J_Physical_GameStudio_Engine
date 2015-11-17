#ifndef _RIGIDBODY2DHEADER
#define _RIGIDBODY2DHEADER

#include "mymath.h"

#define	 _THRUSTFORCE				20.0f/2
#define	 _MAXTHRUST					80.0f/2
#define	 _MINTHRUST					0.0f
#define	 _DTHRUST					0.01f
#define  _STEERINGFORCE				6.0f
#define  _LINEARDRAGCOEFFICIENT		0.3f
#define  _ANGULARDRAGCOEFFICIENT	5000.0f

class RigidBody2D {
public:
	float	fMass;				
	float	fInertia;			
	float	fInertiaInverse;	
	
	Vector	vPosition;			
	Vector	vVelocity;		
	Vector	vVelocityBody;	
	Vector	vAngularVelocity;	
		
	float	fSpeed;				
	float	fOrientation;		

	Vector	vForces;			
	Vector	vMoment;			

	float	ThrustForce;		
	Vector	PThrust, SThrust;

	float	fWidth;				
	float	fLength;
	float	fHeight;

	Vector	CD;
	Vector	CT;
	Vector	CPT;
	Vector	CST;


	float	ProjectedArea;

	Vector	Fa;
	Vector  Pa;


	RigidBody2D(void);
	void	CalcLoads(void);
	void	UpdateBodyEuler(double dt);
	void	SetThrusters(bool p, bool s);
	void	ModulateThrust(bool up);



};

Vector	VRotate2D( float angle, Vector u);

#endif
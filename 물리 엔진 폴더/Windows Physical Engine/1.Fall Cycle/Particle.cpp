
#include "Particle.h"
#include "winmain.h"

Particle::Particle(void)
{
	fMass = 1.0;				
	vPosition.x = 0.0;
	vPosition.y = 0.0;
	vPosition.z = 0.0;
	vVelocity.x = 0.0;
	vVelocity.y = 0.0;
	vVelocity.z = 0.0;
	fSpeed = 0.0;						
	vForces.x = 0.0;
	vForces.y = 0.0;
	vForces.z = 0.0;
	fRadius = 0.1;					
	vGravity.x = 0;
	vGravity.y = fMass * _GRAVITYACCELERATION; 
	vPreviousPosition = vPosition;
}

void	Particle::CalcLoads(void)
{
	vForces.x = 0.0f;
	vForces.y = 0.0f;
	
	if(bCollision) {
		vForces += vImpactForces;
	} else {
		vForces += vGravity;

		Vector	vDrag;
		float	fDrag;
		vDrag-=vVelocity;
		vDrag.Normalize();
		fDrag = 0.5 * _AIRDENSITY * fSpeed * fSpeed * (3.14159 * fRadius * fRadius) * _DRAGCOEFFICIENT;
		vDrag*=fDrag;

		vForces += vDrag;

		Vector	vWind;
		vWind.x = 0.5 * _AIRDENSITY * _WINDSPEED * _WINDSPEED * (3.14159 * fRadius * fRadius) * _DRAGCOEFFICIENT;
		vForces += vWind;
	}
}

void	Particle::UpdateBodyEuler(double dt)
{
		Vector a;
		Vector dv;
		Vector ds;

		vPreviousPosition = vPosition; 

		a = vForces / fMass;
		
		dv = a * dt;
		vVelocity += dv;

		ds = vVelocity * dt;
		vPosition += ds;
		
		fSpeed = vVelocity.Magnitude();		
}


void	Particle::Draw(void)
{
	RECT	r;
	float	drawRadius = max(2, fRadius);

	SetRect(&r, vPosition.x - drawRadius, _WINHEIGHT - (vPosition.y - drawRadius), vPosition.x + drawRadius, _WINHEIGHT - (vPosition.y + drawRadius));
	DrawEllipse(&r, 2, RGB(0,0,255));

	if(ShowVectors)
	{
		Vector	v, u;
		double	f = 0.025;

		DrawLine(vPosition.x, vPosition.y, vPosition.x + vVelocity.x, vPosition.y + vVelocity.y, 3, RGB(0,255,0));
	}

}

Vector	VRotate2D( float angle, Vector u)
{
	float	x,y;

	x = u.x * cos(DegreesToRadians(-angle)) + u.y * sin(DegreesToRadians(-angle));
	y = -u.x * sin(DegreesToRadians(-angle)) + u.y * cos(DegreesToRadians(-angle));

	return Vector( x, y, 0);
}

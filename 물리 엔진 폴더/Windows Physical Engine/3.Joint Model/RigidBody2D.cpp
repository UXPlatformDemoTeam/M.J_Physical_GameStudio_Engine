#include "RigidBody2D.h"

RigidBody2D::RigidBody2D(void)
{
	
	

}

void	RigidBody2D::CalcLoads(void)
{
	Vector	Fb;				
	Vector	Mb;				
	Vector	Thrust;			
	
	vForces.x = 0.0f;
	vForces.y = 0.0f;
	vForces.z = 0.0f;	

	vMoment.x = 0.0f;	
	vMoment.y = 0.0f;	
	vMoment.z = 0.0f;

	Fb.x = 0.0f;	
	Fb.y = 0.0f;	
	Fb.z = 0.0f;	

	Mb.x = 0.0f;
	Mb.y = 0.0f;
	Mb.z = 0.0f;

	Thrust.x = 0.0f;
	Thrust.y = 1.0f;
	Thrust.z = 0.0f;  
	Thrust *= ThrustForce;
	
	Vector	vLocalVelocity;
	float	fLocalSpeed;
	Vector	vDragVector;	
	float	tmp;
	Vector	vResultant;	
	Vector	vtmp;	
	vtmp = vAngularVelocity^CD;
		vLocalVelocity = vVelocityBody + vtmp; 

		fLocalSpeed = vLocalVelocity.Magnitude();

	if(fLocalSpeed > tol) 
		{
			vLocalVelocity.Normalize();
			vDragVector = -vLocalVelocity;		

			double f;
			if((Thrust * vLocalVelocity)/(Thrust.Magnitude() * vLocalVelocity.Magnitude()) > 0)
				f = 2;	
			else
				f = 1;

			tmp = 0.5f * rho * fLocalSpeed*fLocalSpeed * ProjectedArea * f;		
			vResultant = vDragVector * _LINEARDRAGCOEFFICIENT * tmp;

				Fb += vResultant;
		
			vtmp = CD^vResultant; 
			Mb += vtmp;
		}

		Fb += 3*PThrust;
		

		vtmp = CPT^PThrust; 
		Mb += vtmp;

		Fb += 3*SThrust;

	    vtmp = CST^SThrust; 		
		Mb += vtmp;

		Fb += Fa;
		vtmp = Pa ^ Fa;
		Mb += vtmp;

		if(vAngularVelocity.Magnitude() > tol)
		{
			vtmp.x = 0;
			vtmp.y = 0;
			tmp = 0.5f * rho * vAngularVelocity.z*vAngularVelocity.z * ProjectedArea;
			if(vAngularVelocity.z > 0.0)
				vtmp.z = -_ANGULARDRAGCOEFFICIENT * tmp;		
			else
				vtmp.z = _ANGULARDRAGCOEFFICIENT * tmp;		

			Mb += vtmp;
		}


	Fb += Thrust; 

	vForces = VRotate2D(fOrientation, Fb);

	vMoment += Mb;	
}

void	RigidBody2D::UpdateBodyEuler(double dt)
{
		Vector a;
		Vector dv;
		Vector ds;
		float  aa;
		float  dav;
		float  dr;
	
		CalcLoads();
		
		a = vForces / fMass;
		
		dv = a * dt;
		vVelocity += dv;

		ds = vVelocity * dt;
		vPosition += ds;

		aa = vMoment.z / fInertia;

		dav = aa * dt;
		
		vAngularVelocity.z += dav;
		
		dr = RadiansToDegrees(vAngularVelocity.z * dt);
		fOrientation += dr; 
		
		fSpeed = vVelocity.Magnitude();		
		vVelocityBody = VRotate2D(-fOrientation, vVelocity);	
}

void	RigidBody2D::SetThrusters(bool p, bool s)
{
	PThrust.x = 0;
	PThrust.y = 0;
	SThrust.x = 0;
	SThrust.y = 0;
	
	if(p)
		PThrust.x = -_STEERINGFORCE;
	if(s)
		SThrust.x = _STEERINGFORCE;
}


Vector	VRotate2D( float angle, Vector u)
{
	float	x,y;

	x = u.x * cos(DegreesToRadians(-angle)) + u.y * sin(DegreesToRadians(-angle));
	y = -u.x * sin(DegreesToRadians(-angle)) + u.y * cos(DegreesToRadians(-angle));

	return Vector( x, y, 0);
}

void	RigidBody2D::ModulateThrust(bool up)
{
	double	dT = up ? _DTHRUST:-_DTHRUST;

	ThrustForce += dT;

	if(ThrustForce > _MAXTHRUST) ThrustForce = _MAXTHRUST;
	if(ThrustForce < _MINTHRUST) ThrustForce = _MINTHRUST;
}




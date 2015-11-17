#include "main.h"
#include "time.h"

#define		_TIMESTEP						0.1
#define		_TOL							1e-10
#define		_FWDTIME						10
#define		_MAX_NUM_UNITS					1
#define		_OBSTACLE_RADIUS				20
#define		_NUM_OBSTACLES					1
#define		_SPAWN_AREA_R					50
#define		_GROUND_PLANE					100
#define		_COLLISION_TOL					1

int				FrameCounter = 0;
Particle		Obstacles[_NUM_OBSTACLES];

Particle Objects[_NUM_OBJECTS];
Spring Springs[_NUM_SPRINGS];

bool	Initialize(void)
{
	Vector r;
	int i;

	Objects[0].bLocked = true;
	Objects[_NUM_OBJECTS - 1].bLocked = true;

	for (i = 0; i < _NUM_OBJECTS; i++)
	{
		Objects[i].vPosition.x = _WINWIDTH / 2 + Objects[0].fLength *i;
		Objects[i].vPosition.y = _WINHEIGHT / 2;
	}

	for (i = 0; i < _NUM_SPRINGS; i++)
	{
		Springs[i].End1 = i;
		Springs[i].End2 = i + 1;
		r = Objects[i + 1].vPosition - Objects[i].vPosition;
		Springs[i].InitialLength = r.Magnitude();
		Springs[i].k = _SPRING_K;
		Springs[i].d = _SPRING_D;
	}


	return true;
}

void	UpdateSimulation(void)
{
	double	dt = _TIMESTEP;
	int		i;
	double f, dl;
	Vector pt1, pt2;
	int j;
	Vector r;
	Vector F;
	Vector v1, v2, vr;

	if (FrameCounter >= _RENDER_FRAME_COUNT)
	{
		ClearBackBuffer();
		DrawLine(0, _WINHEIGHT - _GROUND_PLANE, _WINWIDTH, _WINHEIGHT - _GROUND_PLANE, 3, RGB(0, 0, 0));
		DrawObstacles();

	}

	for (i = 0; i < _NUM_OBJECTS; i++)
	{
		Objects[i].vSprings.x = 0;
		Objects[i].vSprings.y = 0;
		Objects[i].vSprings.z = 0;
	}


	for (i = 0; i < _NUM_SPRINGS; i++)
	{
		j = Springs[i].End1;
		pt1 = Objects[j].vPosition;
		v1 = Objects[j].vVelocity;

		j = Springs[i].End2;
		pt2 = Objects[j].vPosition;
		v2 = Objects[j].vVelocity;

		vr = v2 - v1; 
		r = pt2 - pt1;
		dl = r.Magnitude() - Springs[i].InitialLength;
		f = Springs[i].k* dl;
		r.Normalize();

		F = (r*f) + (Springs[i].d*(vr*r))*r;
		j = Springs[i].End1;

		if (Objects[j].bLocked == false)
			Objects[j].vSprings += F;

		j = Springs[i].End2;
		if (Objects[j].bLocked == false)
			Objects[j].vSprings -= F;
	}

	for (i = 0; i < _NUM_OBJECTS; i++)
	{
		Objects[i].bCollision = CheckForCollisions(&(Objects[i]));
		Objects[i].CalcLoads();
		Objects[i].UpdateBodyEuler(dt);

		if (FrameCounter >= _RENDER_FRAME_COUNT)
		{
			Objects[i].Draw();

			if (i < _NUM_OBJECTS - 1)
				Objects[i].DrawObjectLine(Objects[i + 1].vPosition);
		}

		if (Objects[i].vPosition.x > _WINWIDTH) Objects[i].vPosition.x = 0;
		if (Objects[i].vPosition.x < 0) Objects[i].vPosition.x = _WINWIDTH;
		if (Objects[i].vPosition.y < 0) Objects[i].vPosition.y = _WINHEIGHT;
	} 

	if (FrameCounter >= _RENDER_FRAME_COUNT) {
		CopyBackBufferToWindow();
		FrameCounter = 0;
	}
	else
		FrameCounter++;
}

bool	CheckForCollisions(Particle* p)
{
	int		i;
	Vector	n; 
	Vector  vr; 
	float  vrn;
	float	J; 
	Vector	Fi; 
	bool	hasCollision = false;


	p->vImpactForces.x = 0;
	p->vImpactForces.y = 0;
if (p->vPosition.y <= (_GROUND_PLANE + p->fRadius)) {
		n.x = 0;
		n.y = 1;
		vr = p->vVelocity;
		vrn = vr * n;
		if (vrn < 0.0) {
			J = -(vr*n) * (_RESTITUTION + 1) * p->fMass;
			Fi = n;
			Fi *= J / _TIMESTEP;
			p->vImpactForces += Fi;

			p->vPosition.y = _GROUND_PLANE + p->fRadius;
			p->vPosition.x = (_GROUND_PLANE + p->fRadius - p->vPreviousPosition.y) / (p->vPosition.y - p->vPreviousPosition.y) * (p->vPosition.x - p->vPreviousPosition.x) + p->vPreviousPosition.x;

			hasCollision = true;
		}
	}


		float r;
	Vector d;
	float s;

	for (i = 0; i < _NUM_OBSTACLES; i++)
	{
		r = p->fRadius + Obstacles[i].fRadius;
		d = p->vPosition - Obstacles[i].vPosition;
		s = d.Magnitude() - r;

		if (s <= _COLLISION_TOL)
		{
			d.Normalize();
			n = d;
			vr = p->vVelocity - Obstacles[i].vVelocity;
			vrn = vr*n;

			if (vrn < 0.0)
			{
				J = -(vr*n) * (_RESTITUTION + 1) / (1 / p->fMass + 1 / Obstacles[i].fMass);
				Fi = n;
				p->vImpactForces += Fi;

				p->vPosition -= n*s;

				hasCollision = true;
			}
		}
	}

	return hasCollision;
}

void DrawObstacles(void)
{
	int		i;

	for (i = 0; i < _NUM_OBSTACLES; i++)
	{

		Obstacles[i].Draw();
	}

}

int GetRandomNumber(int min, int max, bool seed)
{
	int	number;

	if (seed)
		srand((unsigned)time(NULL));

	number = (((abs(rand()) % (max - min + 1)) + min));

	if (number > max)
		number = max;

	if (number < min)
		number = min;

	return number;
}



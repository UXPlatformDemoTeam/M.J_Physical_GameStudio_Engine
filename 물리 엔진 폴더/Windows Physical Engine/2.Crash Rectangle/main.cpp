#include "main.h"
#include "time.h"

int				FrameCounter = 0;
RigidBody2D		Craft;
RigidBody2D		Craft2;

RigidBody2D *CollisionBody1;
RigidBody2D *CollisionBody2;

#define		_TIMESTEP		0.001
#define		_TOL			1e-10
#define		_FWDTIME		10

#define PENETRATING -1
#define COLLISION 1

Vector vCollisionNormal;
Vector vCollisionPoint;
Vector vRelativeVelocity;
float fCr = COEFFICIENTOFRESTITUTION;
float const ctol = COLLISIONTOLERANCE;

bool	Initialize(void)
{
	
	Craft.vPosition.x = _WINWIDTH / 10;
	Craft.vPosition.y = _WINHEIGHT / 2;
	Craft.fOrientation = 0;
	
	Craft2.vPosition.x = _WINWIDTH / 2;
	Craft2.vPosition.y = _WINHEIGHT / 2;
	Craft2.fOrientation = 180;
	
	return true;
}


void	UpdateSimulation(void)
{
	double	dt = _TIMESTEP;
	float dtime = dt;
	bool tryAgain = true;
	int check = 0;
	RigidBody2D craft1Copy, craft2Copy;
	bool didPen = false;
	int count;

	Craft.SetThrusters(false, false);

	if (IsKeyDown(VK_UP))
		Craft.ModulateThrust(true);

	if (IsKeyDown(VK_DOWN))
		Craft.ModulateThrust(false);

	if (IsKeyDown(VK_RIGHT))
		Craft.SetThrusters(true, false);

	if (IsKeyDown(VK_LEFT))
		Craft.SetThrusters(false, true);

	while (tryAgain && dtime > 0.0001)
	{
		tryAgain = false;
		memcpy(&craft1Copy, &Craft, sizeof(RigidBody2D));
		memcpy(&craft2Copy, &Craft2, sizeof(RigidBody2D));

		Craft.UpdateBodyEuler(dtime);
		Craft2.UpdateBodyEuler(dtime);

		check = CheckForCollision(&Craft, &Craft2);

		if (check == PENETRATING)
		{
			dtime = dtime / 2;
			tryAgain = true;
			didPen = true;
		}
		else if (check == COLLISION)
		{
			ApplyImpulse(&Craft, &Craft2);
		}
	}

	if (FrameCounter >= _RENDER_FRAME_COUNT)
	{
		ClearBackBuffer();

		DrawCraft(Craft, RGB(0, 0, 255));

		DrawCraft(Craft2, RGB(200, 200, 0));

		CopyBackBufferToWindow();
		FrameCounter = 0;
	}
	else
		FrameCounter++;

	if (Craft.vPosition.x > _WINWIDTH) Craft.vPosition.x = 0;
	if (Craft.vPosition.x < 0) Craft.vPosition.x = _WINWIDTH;
	if (Craft.vPosition.y > _WINHEIGHT) Craft.vPosition.y = 0;
	if (Craft.vPosition.y < 0) Craft.vPosition.y = _WINHEIGHT;

	if (Craft2.vPosition.x > _WINWIDTH) Craft2.vPosition.x = 0;
	if (Craft2.vPosition.x < 0) Craft2.vPosition.x = _WINWIDTH;
	if (Craft2.vPosition.y > _WINHEIGHT) Craft2.vPosition.y = 0;
	if (Craft2.vPosition.y < 0) Craft2.vPosition.y = _WINHEIGHT;


}

int CheckForCollision(RigidBody2D  *body1, RigidBody2D *body2)
{
	Vector d;
	float r;
	int retval = 0;
	float s;
	Vector vList1[4], vList2[4];
	float wd, lg;
	bool haveNodeNode = false;
	bool interpenetrating = false;
	bool haveNodeEdge = false;
	Vector v1, v2, u;
	Vector edge, p, proj;
	float dist, dot;
	float Vrn;

	r = body1->fLength / 2 + body2->fLength / 2;
	d = body1->vPosition - body2->vPosition;
	s = d.Magnitude() - r;

	if (s <= ctol)
	{
		wd = body1->fWidth;
		lg = body1->fLength;
		vList1[0].y = wd / 2; vList1[0].x = lg / 2;
		vList1[1].y = -wd / 2; vList1[1].x = lg / 2;
		vList1[2].y = -wd / 2; vList1[2].x = -lg / 2;
		vList1[3].y = wd / 2; vList1[3].x = -lg / 2;

		for (int i = 0; i < 4; i++)
		{
			VRotate2D(body1->fOrientation, vList1[i]);
			vList1[i] = vList1[i] + body1->vPosition;
		}

		wd = body2->fWidth;
		lg = body2->fLength;
		vList2[0].y = wd / 2; vList2[0].x = lg / 2;
		vList2[1].y = -wd / 2; vList2[1].x = lg / 2;
		vList2[2].y = -wd / 2; vList2[2].x = -lg / 2;
		vList2[3].y = wd / 2; vList2[3].x = -lg / 2;

		for (int i = 0; i < 4; i++)
		{
			VRotate2D(body2->fOrientation, vList2[i]);
			vList2[i] = vList2[i] + body2->vPosition;
		}

		for (int i = 0; i < 4 && !haveNodeNode; i++)
		{
			for (int j = 0; j < 4 && !haveNodeNode; j++)
			{
				vCollisionPoint = vList1[i];
				body1->vCollisionPoint = vCollisionPoint - body1->vPosition;
				body2->vCollisionPoint = vCollisionPoint - body2->vPosition;

				vCollisionNormal = body1->vPosition - body2->vPosition;
				vCollisionNormal.Normalize();

				v1 = body1->vVelocityBody +
					(body2->vAngularVelocity^body1->vCollisionPoint);
				v2 = body2->vVelocityBody +
					(body2->vAngularVelocity^body2->vCollisionPoint);

				v1 = VRotate2D(body1->fOrientation, v1);
				v2 = VRotate2D(body2->fOrientation, v2);

				vRelativeVelocity = v1 - v2;
				Vrn = vRelativeVelocity * vCollisionNormal;

				if (ArePointsEqual(vList1[i], vList2[j]) && (Vrn < 0.0))
					haveNodeNode = true;
			}
		}

		if (!haveNodeNode)
		{
			for (int i = 0; i < 4 && !haveNodeEdge; i++)
			{
				for (int j = 0; j < 3 && !haveNodeEdge; j++)
				{
					if (j == 2)
						edge = vList2[0] - vList2[j];
					else
						edge = vList2[j + 1] - vList2[j];
					u = edge;
					u.Normalize();

					p = vList1[i] - vList2[j];
					proj = (p*u)*u;

					d = p^u;
					dist = d.Magnitude();

					vCollisionPoint = vList1[i];
					body1->vCollisionPoint = vCollisionPoint - body1->vPosition;
					body2->vCollisionPoint = vCollisionPoint - body2->vPosition;

					vCollisionNormal = ((u^p) ^ u);
					vCollisionNormal.Normalize();

					v1 = body1->vVelocityBody + (body1->vAngularVelocity ^ body1->vCollisionPoint);

					v2 = body2->vVelocityBody + (body2->vAngularVelocity ^ body2->vCollisionPoint);

					v1 = VRotate2D(body1->fOrientation, v1);
					v2 = VRotate2D(body2->fOrientation, v2);

					vRelativeVelocity = (v1 - v2);
					Vrn = vRelativeVelocity * vCollisionNormal;

					if ((proj.Magnitude() > 0.0f) && (proj.Magnitude() <= edge.Magnitude()) && (dist <= ctol) && (Vrn < 0.0))
						haveNodeEdge = true;
				}
			}
		}

		if (!haveNodeNode && !haveNodeEdge)
		{
			for (int i = 0; i < 4 && !interpenetrating; i++)
			{
				for (int j = 0; j < 4 && !interpenetrating; j++)
				{
					if (j == 3)
						edge = vList2[0] - vList2[j];
					else
						edge = vList2[j + 1] - vList2[j];
					p = vList1[i] - vList2[j];
					dot = p *edge;

					if (dot < 0)
					{
						interpenetrating = true;
					}
				}
			}
		}

		if (interpenetrating)
		{
			retval = -1;
		}
		else if (haveNodeNode || haveNodeEdge)
		{
			retval = 1;
		}
		else
			retval = 0;
	}
	else
	{
		retval = 0;
	}
	return retval;

}

bool ArePointsEqual(Vector p1, Vector p2)
{ 
	if ((fabs(p1.x - p2.x) <= ctol) && (fabs(p1.y - p2.y) <= ctol) && (fabs(p1.z - p2.z) <= ctol))
		return true;
	else
		return false;
}

void ApplyImpulse(RigidBody2D *body1, RigidBody2D *body2)
{
	float j;
	
	j = (-(1 + fCr)* (vRelativeVelocity*vCollisionNormal)) / ((1 / body1->fMass + 1 / body2->fMass) + (vCollisionNormal *(((body1->vCollisionPoint^
		vCollisionNormal / body1->fInertia) ^ body1->vCollisionPoint)) + vCollisionNormal * (((body2->vCollisionPoint ^ vCollisionNormal) / body2->fInertia) ^ body2->vCollisionPoint)));
	 
	body1->vVelocity += (j * vCollisionNormal) / body1->fMass;
	body1->vAngularVelocity += (body1->vCollisionPoint ^ (j * vCollisionNormal)) / body1->fInertia;
	
	body2->vVelocity -= (j * vCollisionNormal) / body2->fMass;
	body2->vAngularVelocity -= (body2->vCollisionPoint ^ (j * vCollisionNormal)) / body2->fInertia;


}

void	DrawCraft(RigidBody2D	craft, COLORREF clr)
{
	Vector	vList[5];
	double	wd, lg;
	int		i;
	Vector	v1;

	wd = craft.fWidth;
	lg = craft.fLength;
	vList[0].x = lg / 2;	vList[0].y = wd / 2;
	vList[1].x = -lg / 2;	vList[1].y = wd / 2;
	vList[2].x = -lg / 2;	vList[2].y = -wd / 2;
	vList[3].x = lg / 2;	vList[3].y = -wd / 2;
	vList[4].x = lg / 2 * 1.5; vList[4].y = 0;
	for (i = 0; i < 5; i++)
	{
		v1 = VRotate2D(craft.fOrientation, vList[i]);
		vList[i] = v1 + craft.vPosition;
	}

	DrawLine(vList[0].x, vList[0].y, vList[1].x, vList[1].y, 2, clr);
	DrawLine(vList[1].x, vList[1].y, vList[2].x, vList[2].y, 2, clr);
	DrawLine(vList[2].x, vList[2].y, vList[3].x, vList[3].y, 2, clr);
	DrawLine(vList[3].x, vList[3].y, vList[4].x, vList[4].y, 2, clr);
	DrawLine(vList[4].x, vList[4].y, vList[0].x, vList[0].y, 2, clr);


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
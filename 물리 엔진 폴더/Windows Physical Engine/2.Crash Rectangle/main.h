#ifndef _MAINHEADER
#define _MAINHEADER

#include "winmain.h"
#include "mymath.h"
#include "RigidBody2D.h"
#include "Particle.h"


#define	_RENDER_FRAME_COUNT		330


void	UpdateSimulation(void);
bool	Initialize(void);
void	DrawCraft(RigidBody2D	craft, COLORREF clr);
int		GetRandomNumber(int min, int max, bool seed);
int		CheckForCollision(RigidBody2D *body1, RigidBody2D *body2);
void	ApplyImpulse(RigidBody2D *body1, RigidBody2D *body2);
bool	ArePointsEqual(Vector p1, Vector p2);

#endif
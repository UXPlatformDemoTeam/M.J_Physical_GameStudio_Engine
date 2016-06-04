
package jrcengine.Math;

public class Math_Overlap {
    public static final int Left_Crash = 0;

    public static final int Right_Crash = 1;

    public static final int Up_Crash = 2;

    public static final int Down_Crash = 3;

    public static final int Error_Crash = 7;

    private static final float CRASH_WIDTH = 0.2f;

    public static boolean overlapCircles(Math_Overlap_Circle c1, Math_Overlap_Circle c2) {
        float distance = c1.getCenter().distSquared(c2.getCenter());
        float radiusSum = c1.getRadius() + c2.getRadius();
        return distance <= radiusSum * radiusSum;
    }

    public static boolean overlapRectangles(Math_Overlap_Rectangle r1, Math_Overlap_Rectangle r2) {
        if (r1.getLowerLeft().getX() < r2.getLowerLeft().getX() + r2.getWidth()
                && r1.getLowerLeft().getY() + r1.getWidth() > r2.getLowerLeft().getX()
                && r1.getLowerLeft().getY() < r2.getLowerLeft().getY() + r2.getHeight()
                && r1.getLowerLeft().getY() + r1.getHeight() > r2.getLowerLeft().getY()) {

            return true;
        } else
            return false;
    }

    public static int overlapRectangles(Math_Overlap_Rectangle r1, Math_Overlap_Rectangle r2,
            boolean tag) {

        // LEFT RIGHT REGION SEARCH
        if ((r1.getLowerLeft().getX() < r2.getLowerLeft().getX() + r2.getWidth())
                && (r1.getLowerLeft().getX() > r2.getLowerLeft().getX() + r2.getWidth()
                        - CRASH_WIDTH)
                && (r2.getLowerLeft().getY() + r2.getHeight() - CRASH_WIDTH > r1.getLowerLeft()
                        .getY())
                && (r1.getLowerLeft().getY() + r1.getHeight() - CRASH_WIDTH > r2.getLowerLeft()
                        .getY())) {
            return Right_Crash;
        } else if ((r1.getLowerLeft().getX() + r1.getWidth() > r2.getLowerLeft().getX())
                && (r1.getLowerLeft().getX() + r1.getWidth() - CRASH_WIDTH < r2.getLowerLeft()
                        .getX())
                && (r2.getLowerLeft().getY() + r2.getHeight() - CRASH_WIDTH > r1.getLowerLeft()
                        .getY())
                && (r1.getLowerLeft().getY() + r1.getHeight() - CRASH_WIDTH > r2.getLowerLeft()
                        .getY())) {
            return Left_Crash;
        } else if ((r1.getLowerLeft().getY() < r2.getLowerLeft().getY() + r2.getHeight())
                && (r1.getLowerLeft().getY() > r2.getLowerLeft().getY() + r2.getHeight()
                        - CRASH_WIDTH)
                && (r1.getLowerLeft().getX() + r1.getWidth() > r2.getLowerLeft().getX())
                && (r2.getLowerLeft().getX() + r2.getWidth() > r1.getLowerLeft().getX())) {
            return Up_Crash;
        } else if ((r1.getLowerLeft().getY() + r1.getHeight() > r2.getLowerLeft().getY())
                && (r1.getLowerLeft().getY() + r1.getHeight() - CRASH_WIDTH < r2.getLowerLeft()
                        .getY())
                && (r1.getLowerLeft().getX() + r1.getWidth() > r2.getLowerLeft().getX())
                && (r2.getLowerLeft().getX() + r2.getWidth() > r1.getLowerLeft().getX())) {
            return Down_Crash;
        } else
            return Error_Crash;
    }

    public static boolean overlapCircleRectangle(Math_Overlap_Circle c, Math_Overlap_Rectangle r) {
        float closestX = c.getCenter().getX();
        float closestY = c.getCenter().getY();

        if (c.getCenter().getX() < r.getLowerLeft().getX()) {
            closestX = r.getLowerLeft().getX();
        } else if (c.getCenter().getX() > r.getLowerLeft().getX() + r.getWidth()) {
            closestX = r.getLowerLeft().getX() + r.getWidth();
        }

        if (c.getCenter().getY() < r.getLowerLeft().getY()) {
            closestY = r.getLowerLeft().getY();
        } else if (c.getCenter().getY() > r.getLowerLeft().getY() + r.getHeight()) {
            closestY = r.getLowerLeft().getY() + r.getHeight();
        }

        return c.getCenter().distSquared(closestX, closestY) < c.getRadius() * c.getRadius();
    }

    public static boolean pointInCircle(Math_Overlap_Circle c, Math_Vector p) {
        return c.getCenter().distSquared(p) < c.getRadius() * c.getRadius();
    }

    public static boolean pointInCircle(Math_Overlap_Circle c, float x, float y) {
        return c.getCenter().distSquared(x, y) < c.getRadius() * c.getRadius();
    }

    public static boolean pointInRectangle(Math_Overlap_Rectangle r, Math_Vector p) {
        return r.getLowerLeft().getX() <= p.getX()
                && r.getLowerLeft().getX() + r.getWidth() >= p.getX()
                && r.getLowerLeft().getY() <= p.getY()
                && r.getLowerLeft().getY() + r.getHeight() >= p.getY();
    }

    public static boolean pointInRectangle(Math_Overlap_Rectangle r, float x, float y) {
        return r.getLowerLeft().getX() <= x && r.getLowerLeft().getX() + r.getWidth() >= x
                && r.getLowerLeft().getY() <= y && r.getLowerLeft().getY() + r.getHeight() >= y;
    }
}

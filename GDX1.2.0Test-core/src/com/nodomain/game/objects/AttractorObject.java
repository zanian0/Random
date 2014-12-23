package com.nodomain.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class AttractorObject
{
    Vector2 pos;
    Vector2 velo;

    float mass;

    float radius;
    float diameter;

    float xAccel;
    float yAccel;

    public final float ACCEL = 150.0f;

    private final float GRAV_DIST = 100.0f;

    public AttractorObject (float x, float y, float radius)
    {
        pos = new Vector2();
        velo = new Vector2();

        pos.x = x;
        pos.y = y;

        xAccel = 0.0f;
        yAccel = 0.0f;

        mass = 25.0f;

        this.radius = radius;
        diameter = radius * 2.0f;
    }

    public void update( float dt, Vector2 targetPos )
    {
        // Calculate distance to target.
        float ASquare = ( pos.x - targetPos.x ) * ( pos.x - targetPos.x );
        float BSquare = ( pos.y - targetPos.y ) * ( pos.y - targetPos.y );

        float distance = (float) Math.abs( Math.sqrt( ASquare + BSquare ) );

        // If within max force distance of target
        if (distance < GRAV_DIST)
        {
            xAccel = (distance / GRAV_DIST) * 100;
            yAccel = (distance / GRAV_DIST) * 100;

            if (targetPos.x < pos.x)
            {
                xAccel = xAccel * -1.0f;
            }

            if (targetPos.y < pos.y)
            {
                yAccel = yAccel * -1.0f;
            }
        }

        // Not within gravity distance.
        else
        {
            // deaccelerate

            if (velo.x < 0.0f)
            {
                velo.x = velo.x - (velo.x / mass);
                velo.y = velo.y - (velo.y / mass);
            }
            else if (velo.x > 0.0f)
            {
                velo.x = velo.x + (velo.x / mass);
                velo.y = velo.y + (velo.y / mass);
            }

            System.out.println(velo.x);

            if (velo.x < 2.0f && velo.x > -2.0f)
            {
                velo.x = 0.0f;
            }
            if (velo.y < 2.0f && velo.y > -2.0f)
            {
                velo.y = 0.0f;
            }
        }

        velo.add( xAccel * dt, yAccel * dt );
        pos.add( velo.x * dt, velo.y * dt );
    }

    public Vector2 getPos()
    {
        return pos;
    }

    public float getPosX()
    {
        return pos.x;
    }

    public float getPosY()
    {
        return pos.y;
    }

    public float getRadius()
    {
        return radius;
    }

    public void updatePlayer( float dt )
    {
        int moveX = 0;
        int moveY = 0;

        // Determine move directions.
        if ( Gdx.input.isKeyPressed( Input.Keys.D ) )
        {
            moveX++;
        }
        if ( Gdx.input.isKeyPressed( Input.Keys.A ) )
        {
            moveX--;
        }
        if ( Gdx.input.isKeyPressed( Input.Keys.W ) )
        {
            moveY++;
        }
        if ( Gdx.input.isKeyPressed( Input.Keys.S ) )
        {
            moveY--;
        }

        velo.add( (moveX * ACCEL) * dt, (moveY * ACCEL) * dt );
        pos.add( velo.x * dt, velo.y * dt );

    }
}

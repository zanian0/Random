package com.nodomain.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Seeker
{
    Sprite seeker;

    Vector2 targetPos;
    Vector2 seekerPos;
    Vector2 seekerVelo;
    Vector2 desiredVelo;

    int counter;

    public void init(float x, float y, Texture texture)
    {
        seeker = new Sprite(texture);

        seeker.setX( x );

        seeker.setY( y );

        targetPos = new Vector2(0.0f, 0.0f);
        seekerPos = new Vector2(x, y);

        seekerVelo = new Vector2();
        desiredVelo = new Vector2();

        counter = 0;
    }

    public void update(float targetX, float targetY)
    {
        //targetPos = targetPos.set( targetX, targetY );
        targetPos.x = targetX;
        targetPos.y = targetY;

        desiredVelo.set( targetPos.sub( seekerPos ) );
        desiredVelo.scl( 1.0f );

        seekerVelo.set( desiredVelo.sub( seekerVelo ) );
        seekerVelo.clamp( 0.0f, 400.0f );

        System.out.println(seekerVelo.x + " " + seekerVelo.y);

        counter++;

        if ( (counter % 2) != 0 )
        {
            float dt = Gdx.graphics.getDeltaTime();
            seekerPos.add( seekerVelo.x * dt, seekerVelo.y * dt );

            seeker.setX( seekerPos.x );
            seeker.setY( seekerPos.y );
        }

    }

    public void draw(SpriteBatch sb)
    {

        seeker.draw( sb );

    }


}

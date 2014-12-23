package com.nodomain.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class Ship
{

    // Size vars.
    public final float SHIP_WIDTH = 20.0f;
        public final float SHIP_WIDTH_H = 10.0f;
    public final float SHIP_HEIGHT = 60.0f;
        public final float SHIP_HEIGHT_H = 30.0f;
    public final float SG_RADIUS = 10.0f;

    // Movement vars.
    public final float MOVE_SPEED = 100.0f;
    public final float ACCEL = 150.0f;
    public final float MAX_ACCEL = 150.0f;

    public Vector2 pos;
    public Vector2 velo;

    public LeftTurret leftTurret;
    public RightTurret rightTurret;

    public ShieldGen sg;

    public boolean isWarping;

    public Array<Controller> connected;
    public int totalConnected;


    public void Init()
    {
        pos = new Vector2( (Gdx.graphics.getWidth() / 2.0f) - SHIP_WIDTH_H,
                           (Gdx.graphics.getHeight() / 2.0f) - SHIP_HEIGHT_H );
        velo = new Vector2();

        leftTurret = new LeftTurret( SHIP_HEIGHT_H );
        rightTurret = new RightTurret( SHIP_WIDTH, SHIP_HEIGHT_H );

        sg = new ShieldGen(SHIP_HEIGHT_H, SHIP_WIDTH_H);

        connected = new Array<Controller>();
        totalConnected = 0;


        connected = Controllers.getControllers();
        for(Controller controller: Controllers.getControllers())
        {
            System.out.println("Connect: " + controller.getName());
            //Gdx.app.log("Test", controller.getName());
            totalConnected++;
        }

        System.out.println("TC: " + totalConnected);

    }

    public void Move()
    {
        int moveX = 0;
        int moveY = 0;

        isWarping = false;


        float dt = Gdx.graphics.getDeltaTime();

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
        if ( Gdx.input.isKeyPressed( Input.Keys.X ) )
        {
            isWarping = true;
        }

        if (totalConnected > 0)
        {
            if (connected.first().getAxis( 1 ) > 0.2)
            {
                moveX++;
                //System.out.println("XAxis: " + connected.first().getAxis( 1 ));

            }
            if (connected.first().getAxis( 1 ) < -0.2)
            {
                moveX--;
                //System.out.println("XAxis: " + connected.first().getAxis( 1 ));

            }
            if (connected.first().getAxis( 0 ) > 0.2)
            {
                moveY--;
                //System.out.println("YAxis: " + connected.first().getAxis( 0 ));

            }
            if (connected.first().getAxis( 0 ) < -0.2)
            {
                moveY++;
                //System.out.println("YAxis: " + connected.first().getAxis( 0 ));

            }
        }




        // Set and cap velocity.
        velo.add( (moveX * ACCEL) * dt, (moveY * ACCEL) * dt );

        if ( velo.x > MAX_ACCEL )
            velo.x = MAX_ACCEL;
        else if ( velo.x < -MAX_ACCEL )
            velo.x = -MAX_ACCEL;

        if ( velo.y > MAX_ACCEL )
            velo.y = MAX_ACCEL;
        else if ( velo.y < -MAX_ACCEL )
            velo.y = -MAX_ACCEL;

        // Set position don't let the ship leave the screen.
        pos.add( velo.x * dt, velo.y * dt );

        // Block leaving on the left/right sides.
        if ( (pos.x + SHIP_WIDTH + rightTurret.tWidth) > Gdx.graphics.getWidth() )
        {
            pos.x = Gdx.graphics.getWidth() - SHIP_WIDTH - rightTurret.tWidth;
            velo.x = 0.0f;
        }
        else if ( (pos.x - rightTurret.tWidth) < 0.0f )
        {
            pos.x = 0.0f + rightTurret.tWidth;
            velo.x = 0.0f;
        }
        // Block leaving on the top/bottom sides.
        if ( pos.y + SHIP_HEIGHT > Gdx.graphics.getHeight() )
        {
            pos.y = Gdx.graphics.getHeight() - SHIP_HEIGHT;
            velo.y = 0.0f;
        }
        else if ( pos.y < 0.0f )
        {
            pos.y = 0.0f;
            velo.y = 0.0f;
        }

        leftTurret.Move( pos );
        rightTurret.Move( pos );

        sg.Move( pos );

    }

    public void Draw( ShapeRenderer sr, SpriteBatch sb )
    {
        sr.begin( ShapeType.Line );
            sr.setColor( 0.50f, 0.50f, 0.50f, 0.50f );
            sr.circle( pos.x + SHIP_WIDTH_H, pos.y + SHIP_HEIGHT_H, 100.0f );
        sr.end();

        sr.begin( ShapeType.Filled );
            sr.setColor( 1, 1, 1, 1 );
            sr.rect( pos.x, pos.y, SHIP_WIDTH, SHIP_HEIGHT );
            //sr.filledRect( sg.sgShield.getX(), sg.sgShield.getY(), sg.sgShield.getWidth(), sg.sgShield.getHeight() );
            sr.setColor( 0.25f, 1, 1, 1 );
            sr.rect( sg.TestRect.getX() - (sg.tWidth / 2.0f), sg.TestRect.getY() - (sg.tHeight / 2.0f), sg.TestRect.getWidth(), sg.TestRect.getHeight() );
            sr.rect( sg.TestRect2.getX() - (sg.tWidth / 2.0f), sg.TestRect2.getY() - (sg.tHeight / 2.0f), sg.TestRect2.getWidth(), sg.TestRect2.getHeight() );
            sr.rect( sg.TestRect3.getX() - (sg.tWidth / 2.0f), sg.TestRect3.getY() - (sg.tHeight / 2.0f), sg.TestRect3.getWidth(), sg.TestRect3.getHeight() );
        sr.end();

        sr.begin( ShapeRenderer.ShapeType.Line );
            sr.setColor( 1, 0, 0, 1 );
            sr.line( leftTurret.tImg.getX() + (leftTurret.tImg.getWidth() / 2.0f),
                     leftTurret.tImg.getY() + (leftTurret.tImg.getHeight() / 2.0f),
                     leftTurret.lineEnd.x,
                     leftTurret.lineEnd.y );
            sr.line( rightTurret.tImg.getX() + (rightTurret.tImg.getWidth() / 2.0f),
                     rightTurret.tImg.getY() + (rightTurret.tImg.getHeight() / 2.0f),
                     rightTurret.lineEnd.x,
                     rightTurret.lineEnd.y );
        sr.end();

        sb.begin();
            sg.sgShield.setRotation( sg.rotation );
            sg.sgShield.draw( sb );
            sg.sgImg.setRotation( sg.rotation );
            sg.sgImg.draw( sb );
            leftTurret.tImg.setRotation( leftTurret.rotation );
            leftTurret.tImg.draw( sb );
            rightTurret.tImg.setRotation( rightTurret.rotation );
            rightTurret.tImg.draw( sb );
        sb.end();

    }

}
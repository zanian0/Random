package com.nodomain.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.nodomain.game.objects.Ship;
import com.nodomain.game.objects.StarManager;

public class StarFieldTest implements Screen
{
    // The base game to use to switch to other screens.
    Game game;

    // The flag to tell if this was created so we know to run dispose().
    boolean wasCreated;

    StarManager sm;

    ShapeRenderer sr;
    SpriteBatch sb;

    Ship ship;


    public StarFieldTest(Game game)
    {
        this.game = game;

        // Don't instantiate anything in here.
    }

    @Override
    public void show()
    {
        // If show is called then disposable objects are created.
        wasCreated = true;

        sm = new StarManager();
        sm.Init();

        sr = new ShapeRenderer();
        sb = new SpriteBatch();

        ship = new Ship();
        ship.Init();

    }

    @Override
    public void render( float delta )
    {
        //Blank out the screen and make the new canvas black to draw upon.
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        sm.Draw( sr, ship.isWarping );

        ship.Move();
        ship.Draw( sr, sb );

    }

    @Override
    public void dispose()
    {
        if ( wasCreated )
        {
            // Dispose here.
            sr.dispose();
            sb.dispose();
            ship.sg.sgTxtr.dispose();
            ship.sg.sgShieldTxtr.dispose();
            ship.leftTurret.tImg.getTexture().dispose();
            ship.leftTurret.turretTxtr.dispose();
            ship.rightTurret.tImg.getTexture().dispose();
            ship.rightTurret.turretTxtr.dispose();
            ship.sg.sgShield.getTexture().dispose();
            ship.sg.sgImg.getTexture().dispose();
        }

    }




    @Override
    public void resize( int width, int height )
    {

    }


    @Override
    public void hide()
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }



}

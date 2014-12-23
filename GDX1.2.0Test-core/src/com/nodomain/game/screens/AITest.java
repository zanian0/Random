package com.nodomain.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nodomain.game.objects.Seeker;

public class AITest implements Screen
{
    // The base game to use to switch to other screens.
    Game game;

    Sprite target;

    SpriteBatch sb;

    // The flag to tell if this was created so we know to run dispose().
    boolean wasCreated;

    Seeker seeker;


    public AITest(Game game)
    {
        this.game = game;

        // Don't instantiate anything in here.
    }

    @Override
    public void show()
    {
        // If show is called then disposable objects are created.
        wasCreated = true;

        target = new Sprite();

        Texture turretTxtr;
        turretTxtr = new Texture( Gdx.files.internal( "img/sg.png" ) );
        target = new Sprite( turretTxtr );

        sb = new SpriteBatch();

        seeker = new Seeker();
        seeker.init(0.0f, 0.0f, turretTxtr);


    }

    @Override
    public void render( float delta )
    {
        //Blank out the screen and make the new canvas black to draw upon.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if ( Gdx.input.isButtonPressed( Input.Buttons.LEFT ))
        {
            target.setX( Gdx.input.getX() - (target.getWidth() / 2));
            target.setY( (Gdx.graphics.getHeight() - Gdx.input.getY()) - (target.getHeight() / 2) );
        }

        seeker.update( target.getX(), target.getY() );

        sb.begin();
        target.draw( sb );
        seeker.draw( sb );
        sb.end();

    }

    @Override
    public void dispose()
    {
        if ( wasCreated )
        {
            // Dispose here.
            target.getTexture().dispose();
            sb.dispose();
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

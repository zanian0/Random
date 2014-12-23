package com.nodomain.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class ScreenTemplate implements Screen
{
    // The base game to use to switch to other screens. 
    Game game;
    
    // The flag to tell if this was created so we know to run dispose().
    boolean wasCreated;
    
    
    public ScreenTemplate(Game game)
    {
        this.game = game;
        
        // Don't instantiate anything in here.
    }
    
    @Override
    public void show()
    {
        // If show is called then disposable objects are created. 
        wasCreated = true;
        
    }
    
    @Override
    public void render( float delta )
    {
        //Blank out the screen and make the new canvas black to draw upon.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }
    
    @Override
    public void dispose()
    {
        if ( wasCreated )
        {
            // Dispose here. 
            
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

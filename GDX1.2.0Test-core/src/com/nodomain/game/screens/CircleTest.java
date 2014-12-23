package com.nodomain.game.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class CircleTest implements Screen
{
    // The base game to use to switch to other screens. 
    Game game;
    
    // The flag to tell if this was created so we know to run dispose().
    boolean wasCreated;
    
    ShapeRenderer sr;
    
    Vector2 anchorPoint, movingPoint;
    
    float angle;
    
    
    class Test
    {
        public int x;
        
    }
    
    Test test;
    
    public CircleTest(Game game)
    {
        this.game = game;

        // Don't instantiate anything in here.
    }
    
    @Override
    public void show()
    {
        // If show is called then disposable objects are created. 
        wasCreated = true;
        
        sr = new ShapeRenderer();
        
        anchorPoint = new Vector2();
        movingPoint = new Vector2();
        
        anchorPoint.set( (Gdx.graphics.getWidth() / 2.0f), (Gdx.graphics.getHeight() / 2.0f) );
        movingPoint.set( Gdx.graphics.getWidth(), (Gdx.graphics.getHeight() / 2.0f) );
        
        angle = 0.0f;
        
        test = new Test();
        test.x = 0;
        
        
    }
    
    @Override
    public void render( float delta )
    {
        //Blank out the screen and make the new canvas black to draw upon.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        float dt = Gdx.graphics.getDeltaTime();
        
        angle += 90.0f * dt;

        double radiansRotation = Math.toRadians( angle );
        movingPoint.x = (float) (Math.cos(radiansRotation) * Gdx.graphics.getWidth() + anchorPoint.x);
        movingPoint.y = (float) (Math.sin(radiansRotation) * Gdx.graphics.getWidth() + anchorPoint.y);
        
        test(test);
        System.out.println(test.x);

        sr.begin( ShapeRenderer.ShapeType.Line );
            sr.line( anchorPoint.x, anchorPoint.y, movingPoint.x, movingPoint.y );
        sr.end();

    }
    
    void test (Test testin)
    {
        testin.x = 2;
        
    }
    
    @Override
    public void dispose()
    {
        if ( wasCreated )
        {
            // Dispose here.
            sr.dispose();
            
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

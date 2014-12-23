package com.nodomain.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nodomain.game.objects.StateMachinePlayer;

public class RampageTest implements Screen
{
    // The base game to use to switch to other screens.
    Game game;

    // The flag to tell if this was created so we know to run dispose().
    boolean wasCreated;

    ShapeRenderer sr;

    Vector2 playerMotion;
    boolean playerIsJumping;

    Rectangle building;
    float w;

    public final float GRAVITY = 0.5f;
    public final float FLOOR = 100.0f;
    public final float MOVE_SPEED = 200.0f;
    public final float JUMP_SPEED = 600.0f;

    final float JUMP_DIFF = 10.0f;

    StateMachinePlayer smPlayer;

    Rectangle ground;

    public RampageTest(Game game)
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



        playerMotion = new Vector2();

        building = new Rectangle();
        building.x = 400;
        building.y = 100;
        building.width = 100;
        building.height = 250;

        ground = new Rectangle();
        ground.x = 0;
        ground.y = 95;
        ground.width = 500;
        ground.height = 5;

        w = Gdx.graphics.getWidth();

        playerIsJumping = false;

        smPlayer = new StateMachinePlayer();
        smPlayer.init();
    }

    @Override
    public void render( float delta )
    {
        //Blank out the screen and make the new canvas black to draw upon.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        input();

        update();

        sr.begin( ShapeType.Line );
            sr.rect( smPlayer.body.x, smPlayer.body.y, smPlayer.body.width, smPlayer.body.height );

            sr.rect( building.x, building.y, building.width, building.height );

            sr.rect( ground.x, ground.y, ground.width, ground.height );
        sr.end();

        smPlayer.update();
    }

    private void update()
    {

        boolean collideBottom = false;
        boolean collideLeft = false;
        boolean collideRight = false;

        if ( smPlayer.body.overlaps( building ) )
        {
            smPlayer.currentState.Collider( building, smPlayer.body );
        }

        if ( smPlayer.body.overlaps( ground ) )
        {
            smPlayer.currentState.Collider( ground, smPlayer.body );
        }
    }

    private void input()
    {
        int move = 0;

        if ( Gdx.input.isKeyPressed( Input.Keys.D ) )
            move++;

        if ( Gdx.input.isKeyPressed( Input.Keys.A ) )
            move--;

        playerMotion.x = MOVE_SPEED * move;


        if ( Gdx.input.isKeyPressed( Input.Keys.W ) && ( !playerIsJumping ) )
        {
            playerMotion.y = JUMP_SPEED;
            playerIsJumping = true;
        }
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

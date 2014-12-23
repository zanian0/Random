package com.nodomain.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DTest implements Screen
{
    // The base game to use to switch to other screens.
    Game game;

    // The flag to tell if this was created so we know to run dispose().
    boolean wasCreated;

    World world;
    Box2DDebugRenderer dr;
    OrthographicCamera camera;
    Body box;

    private final float TIME_STEP = 1.0f / 60.0f;
    private final float BOX_FORCE = 200.0f;

    public Box2DTest(Game game)
    {
        this.game = game;

        // Don't instantiate anything in here.
    }

    @Override
    public void show()
    {
        // If show is called then disposable objects are created.
        wasCreated = true;

        world = new World(new Vector2(0, -9.81f), true);
        dr = new Box2DDebugRenderer();

        camera = new OrthographicCamera(Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 8);

        BodyDef bodyDef = new BodyDef();

        // Ground
        // Chain body
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set( 0, -10 );
        // Chain shape
        ChainShape pshape = new ChainShape();
        Vector2[] v2array = new Vector2[4];
        v2array[0] = new Vector2();
        v2array[1] = new Vector2();
        v2array[2] = new Vector2();
        v2array[3] = new Vector2();
        v2array[0].x = -5;
        v2array[0].y = 0;
        v2array[1].x = 5;
        v2array[1].y = 0;
        v2array[2].x = 10;
        v2array[2].y = 2;
        v2array[3].x = 15;
        v2array[3].y = 2;
        pshape.createChain( v2array );
        // Chain fixture
        FixtureDef bFix = new FixtureDef();
        bFix.shape = pshape;
        bFix.density = 50.0f;
        bFix.friction = 0.0f;
        bFix.restitution = 0.25f;

        world.createBody( bodyDef ).createFixture( bFix );
        pshape.dispose();

        // Ball body
        //bodyDef.type = BodyType.DynamicBody;
        //bodyDef.position.set( 0, 0 );

        // ball shape
        CircleShape shape = new CircleShape();
        shape.setRadius( 1 );
        shape.setPosition( new Vector2(0, -2) );

        // fixture definition
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.density = 2.5f;
        fixture.friction = 0.0f;
        fixture.restitution = 0.25f;

        //world.createBody( bodyDef ).createFixture( fixture );
        //shape.dispose();

        // Block body
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set( 0, 10 );
        bodyDef.fixedRotation = true;

        // Block shape
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox( 1, 2 );
        // Block fixture
        FixtureDef blockFix = new FixtureDef();
        blockFix.shape = boxShape;
        blockFix.density = 1.0f;
        blockFix.friction = 1.0f;
        blockFix.restitution = 0.1f;

        box = world.createBody( bodyDef );
        box.createFixture( blockFix );

        box.createFixture( fixture );

        shape.dispose();
        boxShape.dispose();

    }

    @Override
    public void render( float delta )
    {
        //Blank out the screen and make the new canvas black to draw upon.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        input();

        dr.render( world, camera.combined );

        world.step( TIME_STEP, 8, 5 );

    }

    private void input()
    {
        int moveX = 0;
        int moveY = 0;

        if ( Gdx.input.isKeyPressed( Input.Keys.W ))
        {
            moveY++;
            //box.applyLinearImpulse( new Vector2(0, 50), box.getLocalCenter() );
        }
        if ( Gdx.input.isKeyPressed( Input.Keys.S ))
        {
            moveY--;
            //box.applyLinearImpulse( new Vector2(0, -50), box.getLocalCenter() );
        }
        if ( Gdx.input.isKeyPressed( Input.Keys.A ))
        {
            moveX--;
        }
        if ( Gdx.input.isKeyPressed( Input.Keys.D ))
        {
            moveX++;
        }

        box.applyForceToCenter( BOX_FORCE * moveX, BOX_FORCE * moveY, true );


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

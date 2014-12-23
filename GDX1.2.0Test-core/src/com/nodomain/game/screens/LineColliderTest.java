package com.nodomain.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class LineColliderTest implements Screen
{
    // The base game to use to switch to other screens. 
    Game game;
    
    // The flag to tell if this was created so we know to run dispose().
    boolean wasCreated;
    
    public class Line
    {
        public Line ()
        {
            start = new Vector2();
            end = new Vector2();
        }
        
        public Vector2 start;
        public Vector2 end;
    }
    
    Line testLine;
    Rectangle obstacle;
    
    public final float LINE_START_X = 150.0f;
    public final float LINE_END_X = 450.0f;
    public final float LINE_END_Y = 250.0f;
    
    public final float RECT_X = 325.0f;
    public final float RECT_Y = 200.0f;
    public final float RECT_W = 10.0f;
    public final float RECT_H = 100.0f;
    
    public final float PADDLE_SPEED = 200.0f;
    public final float LINE_SPEED = 200.0f;
    
    public final int NUM_LINES = 3;
    
    Line[] lArray = new Line[NUM_LINES];
    
    ShapeRenderer sr;
    
    Vector2 endPoint;
    
    public LineColliderTest(Game game)
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
        for (int i = 0; i < NUM_LINES; i++)
            lArray[i] = new Line();
        
        lArray[0].start.x = LINE_START_X;
        lArray[0].start.y = 300.0f;
        lArray[0].end.x = LINE_END_X;
        lArray[0].end.y = LINE_END_Y;
        
        lArray[1].start.x = LINE_START_X;
        lArray[1].start.y = 250.0f;
        lArray[1].end.x = LINE_END_X;
        lArray[1].end.y = LINE_END_Y;
        
        lArray[2].start.x = LINE_START_X;
        lArray[2].start.y = 200.0f;
        lArray[2].end.x = LINE_END_X;
        lArray[2].end.y = LINE_END_Y;
        
        obstacle = new Rectangle();
        obstacle.set( RECT_X, RECT_Y, RECT_W, RECT_H );
        
        endPoint = new Vector2(0.0f, 0.0f);
    }
    
    @Override
    public void render( float delta )
    {
        //Blank out the screen and make the new canvas black to draw upon.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        move();
        
        sr.setColor( 1.0f, 1.0f, 1.0f, 1.0f );
        
        sr.begin( ShapeType.Filled );
        sr.rect( obstacle.x, obstacle.y, obstacle.width, obstacle.height );
        sr.end();
        
        sr.begin( ShapeType.Line );
      
        for (int i = 0; i < NUM_LINES; i++)
        {
            if ( LineCheck( lArray[i], obstacle, endPoint, sr ) )
            {
                sr.setColor( 1.0f, 0.0f, 0.0f, 1.0f );
                //Intersector.intersectLines( p1, p2, p3, p4, intersection );
                sr.line( lArray[i].start.x, lArray[i].start.y, endPoint.x, endPoint.y );
            }
            else
                sr.line( lArray[i].start.x, lArray[i].start.y, lArray[i].end.x, lArray[i].end.y );
            sr.setColor( 1.0f, 1.0f, 1.0f, 1.0f );
        }
        sr.end();

    }
    
    void move()
    {
        int moveY = 0;
        if ( Gdx.input.isKeyPressed( Input.Keys.T ) )
            moveY++;
        if ( Gdx.input.isKeyPressed( Input.Keys.G ) )
            moveY--;
        
        if ( moveY != 0 )
        {
            float move = (Gdx.graphics.getDeltaTime() * LINE_SPEED) * moveY;
            lArray[0].start.y += move;
            lArray[1].start.y += move;
            lArray[2].start.y += move;
        }
        
        
        int moveColY = 0;
        if ( Gdx.input.isKeyPressed( Input.Keys.W ) )
            moveColY++;
        if ( Gdx.input.isKeyPressed( Input.Keys.S ) )
            moveColY--;
        int moveColX = 0;
        if ( Gdx.input.isKeyPressed( Input.Keys.D ) )
            moveColX++;
        if ( Gdx.input.isKeyPressed( Input.Keys.A ) )
            moveColX--;  
            
        if (  moveColY != 0 )
            obstacle.y += (Gdx.graphics.getDeltaTime() * PADDLE_SPEED) * moveColY;
        if (  moveColX != 0 )
            obstacle.x += (Gdx.graphics.getDeltaTime() * PADDLE_SPEED) * moveColX; 
    }
    
    boolean LineCheck(Line sourceLine, Rectangle collisionTarget, Vector2 endP, ShapeRenderer sr)
    {
        boolean colTrue = false;
        Line collider = new Line();
        
        sr.setColor( 0.0f, 1.0f, 0.0f, 1.0f );
        
        collider.start.x = collisionTarget.x;
        collider.start.y = collisionTarget.y;
        collider.end.x = collisionTarget.x;
        collider.end.y = collisionTarget.y + collisionTarget.height;
        
        colTrue = LineCol2(sourceLine, collider, endP);
        sr.line( collider.start, collider.end );
  
        if ( !colTrue )
        {
            collider.start.x = collisionTarget.x;
            collider.start.y = collisionTarget.y + collisionTarget.height;
            collider.end.x = collisionTarget.x + collisionTarget.width;
            collider.end.y = collisionTarget.y + collisionTarget.height;
            colTrue = LineCol2(sourceLine, collider, endP);
            sr.line( collider.start, collider.end );
        }
        
        if ( !colTrue )
        {
            collider.start.x = collisionTarget.x;
            collider.start.y = collisionTarget.y;
            collider.end.x = collisionTarget.x + collisionTarget.width;
            collider.end.y = collisionTarget.y;
            colTrue = LineCol2(sourceLine, collider, endP);
            sr.line( collider.start, collider.end );
        }
        
        if ( !colTrue )
        {
            collider.start.x = collisionTarget.x + collisionTarget.width;
            collider.start.y = collisionTarget.y + collisionTarget.height;
            collider.end.x = collisionTarget.x + collisionTarget.width;
            collider.end.y = collisionTarget.y;
            colTrue = LineCol2(sourceLine, collider, endP);
            sr.line( collider.start, collider.end );
        }
        sr.setColor( 1.0f, 1.0f, 1.0f, 1.0f );
        return colTrue;
    }
    
    boolean LineCol(Line line1, Line line2)
    {
        float denominator = ((line1.end.x - line1.start.x) * (line2.end.y - line2.start.y)) - ((line1.end.y - line1.start.y) * (line2.end.x - line2.start.x));
        float numerator1 = ((line1.start.y - line2.start.y) * (line2.end.x - line2.start.x)) - ((line1.start.x - line2.start.x) * (line2.end.y - line2.start.y));
        float numerator2 = ((line1.start.y - line2.start.y) * (line1.end.x - line1.start.x)) - ((line1.start.x - line2.start.x) * (line1.end.y - line1.start.y));

        // Detect coincident lines (has a problem, read below)
        if (denominator == 0) return numerator1 == 0 && numerator2 == 0;

        float r = numerator1 / denominator;
        float s = numerator2 / denominator;

        return (r >= 0 && r <= 1) && (s >= 0 && s <= 1);
    }
    
    boolean LineCol2(Line line1, Line line2, Vector2 colPoint)
    {
        float s1_x, s1_y, s2_x, s2_y;
        s1_x = line1.end.x - line1.start.x;     s1_y = line1.end.y - line1.start.y;
        s2_x = line2.end.x - line2.start.x;     s2_y = line2.end.y - line2.start.y;

        float s, t;
        s = (-s1_y * (line1.start.x - line2.start.x) + s1_x * (line1.start.y - line2.start.y)) / (-s2_x * s1_y + s1_x * s2_y);
        t = ( s2_x * (line1.start.y - line2.start.y) - s2_y * (line1.start.x - line2.start.x)) / (-s2_x * s1_y + s1_x * s2_y);

        if (s >= 0 && s <= 1 && t >= 0 && t <= 1)
        {
            // Collision detected
            
            colPoint.x = line1.start.x + (t * s1_x);
            
            colPoint.y = line1.start.y + (t * s1_y);
            return true;
        }

        return false; // No collision
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



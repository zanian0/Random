package com.nodomain.game.objects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class StarManager
{
    
    public final int NUM_S_STARS = 100;
    public final int NUM_M_STARS = 70;
    public final int NUM_L_STARS = 5;

    /*
    public final float S_STAR_SPEED = -250.0f;
    public final float M_STAR_SPEED = -125.0f;
    public final float L_STAR_SPEED = -70.0f;
    
    public final int S_STAR_VELO_MOD = 50;
    public final int M_STAR_VELO_MOD = 25;
    public final int L_STAR_VELO_MOD = 20;
    
    public final float S_STAR_INTERVAL = 2.0f;
    public final float M_STAR_INTERVAL = 3.0f;
    public final float L_STAR_INTERVAL = 7.0f;
    */
    
    public final float S_STAR_SPEED = -70.0f;
    public final float M_STAR_SPEED = -125.0f;
    public final float L_STAR_SPEED = -250.0f;
    
    public final int S_STAR_VELO_MOD = 20;
    public final int M_STAR_VELO_MOD = 25;
    public final int L_STAR_VELO_MOD = 50;
    
    public final float S_STAR_INTERVAL = 7.0f;
    public final float M_STAR_INTERVAL = 3.0f;
    public final float L_STAR_INTERVAL = 7.0f;
    
    Random rand;
    
    float timer;

    Star[] starSmall;
    Star[] starMedium;
    Star[] starLarge;
    
    class Star
    {
        public Vector2 pos;
        public Vector2 velo;
        public float radius;
        boolean isActive;
        float startTime;

        
        Star()
        {
            pos = new Vector2();
            velo = new Vector2();
            radius = 0;
        }
        
        public void integrate(float dt)
        {
            pos.add( velo.x * dt, velo.y * dt );
        }
    }
    
    public void Init()
    {
        rand = new Random();
        
        starSmall = new Star[NUM_S_STARS];
        starMedium = new Star[NUM_M_STARS];
        starLarge = new Star[NUM_L_STARS];
        
        for( int i = 0; i < NUM_S_STARS; i++ )
        {
            starSmall[i] = new Star();
            starSmall[i].isActive = false;
            starSmall[i].pos.x = rand.nextInt( Gdx.graphics.getWidth() );
            starSmall[i].pos.y = Gdx.graphics.getHeight();
            starSmall[i].velo.y = S_STAR_SPEED + (float)rand.nextInt( S_STAR_VELO_MOD );
            starSmall[i].radius = 1.0f;
            starSmall[i].startTime = rand.nextFloat( ) * S_STAR_INTERVAL;
        }
        
        for( int i = 0; i < NUM_M_STARS; i++ )
        {
            starMedium[i] = new Star();
            starMedium[i].isActive = false;
            starMedium[i].pos.x = rand.nextInt( Gdx.graphics.getWidth() );
            starMedium[i].pos.y = Gdx.graphics.getHeight();
            starMedium[i].velo.y = M_STAR_SPEED + (float)rand.nextInt( M_STAR_VELO_MOD );
            starMedium[i].radius = 1.5f;
            starMedium[i].startTime = rand.nextFloat( ) * M_STAR_INTERVAL; 
        }
        
        for( int i = 0; i < NUM_L_STARS; i++ )
        {
            starLarge[i] = new Star();
            starLarge[i].isActive = false;
            starLarge[i].pos.x = rand.nextInt( Gdx.graphics.getWidth() );
            starLarge[i].pos.y = Gdx.graphics.getHeight();
            starLarge[i].velo.y = L_STAR_SPEED + (float)rand.nextInt( L_STAR_VELO_MOD );
            starLarge[i].radius = 2.0f;
            starLarge[i].startTime = rand.nextFloat( ) * L_STAR_INTERVAL; 
        }
    }
    
    public void Draw(ShapeRenderer sr, boolean warp)
    {
        
        float dt = Gdx.graphics.getDeltaTime();
        float top = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();
        
        timer += dt;
        
        sr.begin( ShapeType.Filled );        
        
        sr.setColor( 0.35f, 0.35f, 0.35f, 1.0f );
        
        if (!warp)
        {
            for ( Star ss: starSmall )
            {
                if ( timer > ss.startTime )
                    ss.isActive = true;
                
                if ( ss.isActive )
                {
                    ss.integrate( dt );
                    sr.circle( ss.pos.x, ss.pos.y, ss.radius );
                }
                
                if ( ss.pos.y < 0.0f )
                {
                    ss.pos.y = top;
                    ss.pos.x = rand.nextInt( (int)width );
                }
            }
            
            sr.setColor( 0.5f, 0.5f, 0.5f, 1.0f );
            
            for ( Star ms: starMedium )
            {
                if ( timer > ms.startTime )
                    ms.isActive = true;
                
                if ( ms.isActive )
                {
                    ms.integrate( dt );
                    sr.circle( ms.pos.x, ms.pos.y, ms.radius );
                }
                
                if ( ms.pos.y < 0.0f )
                {
                    ms.pos.y = top;
                    ms.pos.x = rand.nextInt( (int)width );
                }
            }
            
            sr.setColor( 1, 1, 1, 1 );
            
            for ( Star ls: starLarge )
            {
                if ( timer > ls.startTime )
                    ls.isActive = true;
                
                if ( ls.isActive )
                {
                    ls.integrate( dt );
                    sr.circle( ls.pos.x, ls.pos.y, ls.radius );
                }
                
                if ( ls.pos.y < 0.0f )
                {
                    ls.pos.y = top;
                    ls.pos.x = rand.nextInt( (int)width );
                }
            }
        }
        else
        {
            for ( Star ss: starSmall )
            {
                if ( timer > ss.startTime )
                    ss.isActive = true;
                
                if ( ss.isActive )
                {
                    ss.integrate( dt * 2.0f );
                    sr.ellipse( ss.pos.x, ss.pos.y, ss.radius, ss.radius * 5.0f );
                }
                
                if ( ss.pos.y < 0.0f )
                {
                    ss.pos.y = top;
                    ss.pos.x = rand.nextInt( (int)width );
                }
            }
            
            sr.setColor( 0.5f, 0.5f, 0.5f, 1.0f );
            
            for ( Star ms: starMedium )
            {
                if ( timer > ms.startTime )
                    ms.isActive = true;
                
                if ( ms.isActive )
                {
                    ms.integrate( dt * 2.0f );
                    sr.ellipse( ms.pos.x, ms.pos.y, ms.radius, ms.radius * 5.0f );
                }
                
                if ( ms.pos.y < 0.0f )
                {
                    ms.pos.y = top;
                    ms.pos.x = rand.nextInt( (int)width );
                }
            }
            
            sr.setColor( 1, 1, 1, 1 );
            
            for ( Star ls: starLarge )
            {
                if ( timer > ls.startTime )
                    ls.isActive = true;
                
                if ( ls.isActive )
                {
                    ls.integrate( dt * 2.0f );
                    sr.ellipse( ls.pos.x, ls.pos.y, ls.radius, ls.radius * 5.0f );
                }
                
                if ( ls.pos.y < 0.0f )
                {
                    ls.pos.y = top;
                    ls.pos.x = rand.nextInt( (int)width );
                }
            }
        }
        
        sr.end();

    }

    
}

package com.nodomain.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ShieldGen
{
    public final float SG_ROTATE_SPEED = 150.0f;
    public final float SHIELD_RADIUS = 100.0f;
    
    public final float SHIELD_W = 20.0f;
    public final float SHIELD_H = 20.0f;
    
    public Sprite sgImg;
    public Sprite sgShield;
    public Texture sgTxtr;
    public Texture sgShieldTxtr;
    float rotation;
    float tWidth;
    float tHeight;
    
    Rectangle TestRect;
    Rectangle TestRect2;
    Rectangle TestRect3;

    
    float shipHeight;
    float shipWidth;
    
    public ShieldGen(float shipHeight, float shipWidth)
    {
        this.shipHeight = shipHeight;
        this.shipWidth = shipWidth;
        sgTxtr = new Texture( Gdx.files.internal( "img/sg.png" ) );
        sgShieldTxtr = new Texture( Gdx.files.internal( "img/shield.png" ) );
        sgImg = new Sprite( sgTxtr );
        sgShield = new Sprite( sgShieldTxtr );
        tWidth = sgImg.getWidth();
        tHeight = sgImg.getHeight();
        rotation = 90.0f;
        
        TestRect = new Rectangle();
        TestRect2 = new Rectangle();
        TestRect3 = new Rectangle();
    }
    
    public void Move(Vector2 shipPos)
    {
        int sgMove = 0;
        
        if ( Gdx.input.isKeyPressed( Input.Keys.N ) )
        {
            sgMove++;
        }
        if ( Gdx.input.isKeyPressed( Input.Keys.M ) )
        {
            sgMove--;
        }
        
            rotation += (SG_ROTATE_SPEED * Gdx.graphics.getDeltaTime()) * sgMove;
            // Stick the shield generator on the hull.
            sgImg.setPosition( shipPos.x, shipPos.y + shipHeight - (sgImg.getHeight() / 2.0f));
         
            double radiansRotation = Math.toRadians( rotation );
            
            float centerX = sgImg.getX() + (sgImg.getWidth() / 2.0f);
            float centerY = sgImg.getY() + (sgImg.getHeight() / 2.0f);
            
            float circleX = (float) (Math.cos(radiansRotation) * (SHIELD_RADIUS) + centerX);
            float circleY = (float) (Math.sin(radiansRotation) * (SHIELD_RADIUS) + centerY);
    
            sgShield.setPosition(circleX-10, circleY-30);
            
            TestRect.set( circleX, circleY, SHIELD_W, SHIELD_H );
            
            circleX = (float) (Math.cos(Math.toRadians( rotation + 15 )) * (SHIELD_RADIUS) + centerX);
            circleY = (float) (Math.sin(Math.toRadians( rotation + 15 )) * (SHIELD_RADIUS) + centerY);
            
            TestRect2.set( circleX, circleY, SHIELD_W, SHIELD_H );
            
            circleX = (float) (Math.cos(Math.toRadians( rotation - 15 )) * (SHIELD_RADIUS) + centerX);
            circleY = (float) (Math.sin(Math.toRadians( rotation - 15 )) * (SHIELD_RADIUS) + centerY);
            
            TestRect3.set( circleX, circleY, SHIELD_W, SHIELD_H );

    }
}

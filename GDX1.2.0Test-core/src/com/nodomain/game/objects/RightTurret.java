package com.nodomain.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class RightTurret
{
    public final float TURRET_ROTATE_SPEED = 100.0f;
    
    public Sprite tImg;
    float rotation;
    float tWidth;
    float tHeight;
    public Vector2 lineEnd;
    
    float shipWidth;
    float shipHeightHalf;
    
    public Texture turretTxtr;
    
    RightTurret( float shipW, float shipHgtH )
    {
        turretTxtr = new Texture( Gdx.files.internal( "img/turret.png" ) );
        tImg = new Sprite( turretTxtr );
        tWidth = tImg.getWidth();
        tHeight = tImg.getHeight();
        rotation = 0.0f;
        
        shipWidth = shipW;
        shipHeightHalf = shipHgtH;
        
        lineEnd = new Vector2( (tImg.getX() + (tImg.getWidth() / 2.0f)), Gdx.graphics.getHeight() );
       
    }
    public void Move( Vector2 pos )
    {
        int rightTurretMove = 0;
        
        if ( Gdx.input.isKeyPressed( Input.Keys.T ) )
        {
            rightTurretMove++;
        }
        if ( Gdx.input.isKeyPressed( Input.Keys.G ) )
        {
            rightTurretMove--;
        }
        
        // Stick the right turret to the right side of the ship.
        tImg.setPosition( (pos.x + shipWidth), 
                (pos.y + shipHeightHalf - (tImg.getHeight() / 2.0f)) );

        rotation += (TURRET_ROTATE_SPEED * Gdx.graphics.getDeltaTime()) * rightTurretMove;
               
        if ( rotation > 0.0f )
            rotation = 0.0f;
        else if ( rotation < -180.0f )
            rotation = -180.0f;

        double radiansRotation = Math.toRadians( rotation + 90 );
        lineEnd.x = (float) (Math.cos(radiansRotation) * Gdx.graphics.getWidth()+ (tImg.getX() + (tImg.getWidth() / 2.0f)));
        lineEnd.y = (float) (Math.sin(radiansRotation) * Gdx.graphics.getHeight() + (tImg.getY() + (tImg.getHeight() / 2.0f)));
    }
    
}

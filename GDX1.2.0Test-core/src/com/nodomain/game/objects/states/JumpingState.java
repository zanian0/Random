package com.nodomain.game.objects.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.nodomain.game.objects.StateMachinePlayer;

public class JumpingState extends State
{
    float yVectorSpeed;
    boolean setWalk;

    private final float SLIDE_SPEED = 100.0f;
    private final float JUMP_SPEED = 600.0f;

    @Override
    public State update(StateMachinePlayer state)
    {

        System.out.println( "Jumping, " + yVectorSpeed );

        if ( setWalk == true )
            return new WalkingState();


        int moveLeftRight = 0;

        if ( Gdx.input.isKeyPressed( Input.Keys.A ) )
        {
            moveLeftRight--;
        }
        if ( Gdx.input.isKeyPressed( Input.Keys.D ) )
        {
            moveLeftRight++;
        }

        state.body.x += ( SLIDE_SPEED * Gdx.graphics.getDeltaTime() ) * moveLeftRight;


        //JUMP
        state.body.y += ( yVectorSpeed * Gdx.graphics.getDeltaTime() );

        yVectorSpeed -= 10;

        return null;
    }

    @Override
    public void enter( StateMachinePlayer state )
    {
        yVectorSpeed = JUMP_SPEED;

        setWalk = false;

    }

    @Override
    public void exit()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void Collider( Rectangle target, Rectangle player )
    {
        if ((target.y + target.height) < (player.y + player.height))
        {
            yVectorSpeed = 0.0f;
            player.y = target.y + target.height;
            setWalk = true;
        }
            // Need to check the state's body in this message.

    }

}

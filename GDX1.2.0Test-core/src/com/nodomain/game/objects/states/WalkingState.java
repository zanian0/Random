package com.nodomain.game.objects.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.nodomain.game.objects.StateMachinePlayer;


public class WalkingState extends State
{
    private final float WALK_SPEED = 100.0f;

    @Override
    public State update(StateMachinePlayer state)
    {
        // TODO Auto-generated method stub

        System.out.println("Walking");

        if ( Gdx.input.isKeyPressed( Input.Keys.W ))
        {
            return new JumpingState();

        }

        int moveLeftRight = 0;

        if (Gdx.input.isKeyPressed( Input.Keys.A ))
        {
            moveLeftRight--;
        }
        if (Gdx.input.isKeyPressed( Input.Keys.D ))
        {
            moveLeftRight++;
        }

        state.body.x += (WALK_SPEED * Gdx.graphics.getDeltaTime()) * moveLeftRight;


        return null;

    }

    @Override
    public void enter(StateMachinePlayer state)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void exit()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void Collider( Rectangle target, Rectangle player )
    {
        // if target is above
        // if target is below
        // if target is left
        // if target is right

    }


}

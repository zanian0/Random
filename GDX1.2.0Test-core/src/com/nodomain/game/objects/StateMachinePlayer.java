package com.nodomain.game.objects;

import com.badlogic.gdx.math.Rectangle;
import com.nodomain.game.objects.states.State;
import com.nodomain.game.objects.states.WalkingState;


public class StateMachinePlayer
{
    public Rectangle body;


    public State currentState;

    private State swap;

    public void init()
    {
        body = new Rectangle();
        body.x = 200;
        body.y = 100;
        body.width = 50;
        body.height = 100;

        currentState = new WalkingState();
    }

    public void update()
    {
        swap = currentState.update(this);

        if (swap != null)
        {
            currentState.exit();
            //currentState.
            currentState = swap;
            currentState.enter(this);
        }


    }


}




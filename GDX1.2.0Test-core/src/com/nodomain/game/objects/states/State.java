package com.nodomain.game.objects.states;

import com.badlogic.gdx.math.Rectangle;
import com.nodomain.game.objects.StateMachinePlayer;

public abstract class State
{
    abstract public State update( StateMachinePlayer state );

    abstract public void enter( StateMachinePlayer state );

    abstract public void exit();

    abstract public void Collider( Rectangle target, Rectangle player );
}
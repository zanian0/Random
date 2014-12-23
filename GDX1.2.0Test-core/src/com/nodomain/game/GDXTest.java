package com.nodomain.game;

import com.badlogic.gdx.Game;
import com.nodomain.game.screens.AITest;
import com.nodomain.game.screens.Attractor;
import com.nodomain.game.screens.Box2DTest;
import com.nodomain.game.screens.CircleTest;
import com.nodomain.game.screens.GoodBox2DTest;
import com.nodomain.game.screens.LineColliderTest;
import com.nodomain.game.screens.RampageBox2DTest;
import com.nodomain.game.screens.RampageTest;
import com.nodomain.game.screens.StarFieldTest;

public class GDXTest extends Game {
    private CircleTest circleTest;
    private Box2DTest box2DTest;
    private StarFieldTest sfTest;
    private LineColliderTest lcTest;
    private RampageTest rTest;
    private RampageBox2DTest r2Test;
    private GoodBox2DTest goodbox2dTest;
    private AITest aiTest;
    private Attractor atTest;

    @Override
    public void create()
    {
        circleTest = new CircleTest(this);
        box2DTest = new Box2DTest(this);
        sfTest = new StarFieldTest(this);
        lcTest = new LineColliderTest(this);
        rTest = new RampageTest(this);
        r2Test = new RampageBox2DTest(this);
        goodbox2dTest = new GoodBox2DTest(this);
        aiTest = new AITest(this);
        atTest = new Attractor(this);


        setScreen(atTest);
    }

    @Override
    public void dispose()
    {
        circleTest.dispose();
        box2DTest.dispose();
        sfTest.dispose();
        lcTest.dispose();
        rTest.dispose();
        r2Test.dispose();
        goodbox2dTest.dispose();
        aiTest.dispose();
        atTest.dispose();

    }
}




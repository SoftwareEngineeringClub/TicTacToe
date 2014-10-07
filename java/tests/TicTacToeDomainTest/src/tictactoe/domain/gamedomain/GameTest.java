package tictactoe.domain.gamedomain;

import static org.junit.Assert.*;

import tictactoe.domain.playerdomain.Player;

import strata1.common.datetime.DateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public 
class GameTest
{
    private Player itsX;
    private Player itsO;
    private Game   itsTarget;
    
    @Before
    public void 
    setUp() throws Exception
    {
        itsX      = new Player();
        itsO      = new Player();
        itsTarget = new Game();
        
        itsX.setPlayerId( 1L );
        itsX.setUserId(  1L );
        
        itsO.setPlayerId( 2L );
        itsO.setUserId(  2L );
        
        itsTarget.setGameId( 3L );
        itsTarget.setPlayerIdX( itsX.getPlayerId() );
        itsTarget.setPlayerIdO( itsO.getPlayerId() );
        itsTarget.setCurrentPlayerId( itsX.getPlayerId() );
        itsTarget.setStart( new DateTime() );
    }

    @After
    public void 
    tearDown() throws Exception
    {
        itsTarget = null;
        itsX      = null;
        itsO      = null;
    }

    @Test
    public void 
    testMakeMove() throws Exception
    {
        Move move1 = new Move( itsX.getPlayerId(),1,1,Game.X_MARKER );
        Move move2 = new Move( itsO.getPlayerId(),1,0,Game.O_MARKER );
        Move move3 = new Move( itsX.getPlayerId(),2,1,Game.X_MARKER );
        Move move4 = new Move( itsO.getPlayerId(),0,1,Game.O_MARKER );
        Move move5 = new Move( itsX.getPlayerId(),0,2,Game.X_MARKER );
        Move move6 = new Move( itsO.getPlayerId(),2,0,Game.O_MARKER );
        Move move7 = new Move( itsX.getPlayerId(),0,0,Game.X_MARKER );
        Move move8 = new Move( itsO.getPlayerId(),2,2,Game.O_MARKER );
        
        System.out.println( itsTarget.toString() );
        itsTarget.makeMove( move1 );
        System.out.println( itsTarget.toString() );
        itsTarget.makeMove( move2 );
        System.out.println( itsTarget.toString() );
        itsTarget.makeMove( move3 );
        System.out.println( itsTarget.toString() );
        itsTarget.makeMove( move4 );
        System.out.println( itsTarget.toString() );
        itsTarget.makeMove( move5 );
        System.out.println( itsTarget.toString() );
        itsTarget.makeMove( move6 );
        System.out.println( itsTarget.toString() );
        itsTarget.makeMove( move7 );
        System.out.println( itsTarget.toString() );
        itsTarget.makeMove( move8 );
        System.out.println( itsTarget.toString() );
    }

}

// ##########################################################################

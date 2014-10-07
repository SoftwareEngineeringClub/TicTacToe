// ##########################################################################
// # File Name:	GameRepositoryTest.java
// ##########################################################################

package tictactoe.domain.gamedomain;

import static org.junit.Assert.*;

import tictactoe.domain.persistence.PersistenceModule;
import tictactoe.domain.playerdomain.PlayerDomainModule;
import tictactoe.domain.sessiondomain.SessionDomainModule;

import strata1.entity.repository.IRepository;
import strata1.entity.repository.IRepositoryContext;
import strata1.entity.repository.IUnitOfWork;
import strata1.injector.container.Container;
import strata1.injector.container.IContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/****************************************************************************
 * 
 */
public abstract
class GameRepositoryTest
{
    private IContainer      itsContainer;
    private IGameRepository itsTarget;
    private Set<Long>       itsGameIds;
    
    /************************************************************************
     *  
     *
     * @throws Exception
     */
    @Before
    public void 
    setUp() throws Exception
    {
        try
        {
            itsContainer = new Container();
            getPersistenceModule().initialize( itsContainer );
            new SessionDomainModule().initialize( itsContainer );
            new PlayerDomainModule().initialize( itsContainer );
            new GameDomainModule().initialize( itsContainer );
        }
        catch (Exception e)
        {
            itsContainer = null;
            throw e;
        }
        
        itsTarget = 
            itsContainer.getInstance( IGameRepository.class );
        itsGameIds = new HashSet<Long>();
    }

    /************************************************************************
     *  
     *
     * @throws Exception
     */
    @After
    public void 
    tearDown() throws Exception
    {
        for (Long gameId : itsGameIds)
        {
            Game game = itsTarget.getGame( gameId );
            
            itsTarget.removeGame( game );
        }
        
        itsTarget
            .getUnitOfWork()
            .commit();
        
        itsTarget = null;
        itsContainer = null;
    }

    /**
     * Test method for {@link IRepository#getContext()}.
     */
    @Test
    public void 
    testGetContext()
    {
        IRepositoryContext expected = 
            itsContainer.getInstance( IRepositoryContext.class );
        
        assertNotNull(  itsTarget.getContext() );
        assertEquals( expected,itsTarget.getContext() );
    }

    /**
     * Test method for {@link IRepository#getUnitOfWork()}.
     */
    @Test
    public void 
    testGetUnitOfWork()
    {
        IRepositoryContext context = 
            itsContainer.getInstance( IRepositoryContext.class );
        IUnitOfWork expected = context.getUnitOfWork();
        
        assertNotNull( itsTarget.getUnitOfWork() );
        assertEquals( expected,itsTarget.getUnitOfWork() );
    }

    /**
     * Test method for {@link IGameRepository#insertGame(Game)}.
     */
    @Test
    public void 
    testInsertGame()
        throws Exception
    {
        Game expected = new Game();
        Game actual   = null;
        
        expected.setPlayerIdX( 1L );
        expected.setPlayerIdO(  2L );
        expected.setCurrentPlayerId( 1L );
        
        actual = itsTarget.insertGame( expected );
        itsGameIds.add( actual.getGameId()  );
        
        assertEquals( expected.getPlayerIdX(),actual.getPlayerIdX() );
        assertEquals( expected.getPlayerIdO(),actual.getPlayerIdO() );
        assertEquals( expected.getCurrentPlayerId(),actual.getCurrentPlayerId() );
        assertEquals( expected.getStart(),actual.getStart() );
        assertNull( actual.getFinish() );
        assertEquals( expected.getState(),actual.getState() );
    }

    /**
     * Test method for {@link IGameRepository#updateGame(Game)}.
     */
    @Test
    public void 
    testUpdateGame() throws Exception
    {
        Game expected = new Game();
        Game actual   = null;
        Move move     = new Move( 1L,1,1,Game.X_MARKER );
        
        expected.setPlayerIdX( 1L );
        expected.setPlayerIdO(  2L );
        expected.setCurrentPlayerId( 1L );
        
        expected = itsTarget.insertGame( expected );
        itsGameIds.add( expected.getGameId()  );
        expected.makeMove( move );
        actual = itsTarget.updateGame( expected );
        itsTarget
            .getUnitOfWork()
            .commit();
        
        assertEquals( expected.getPlayerIdX(),actual.getPlayerIdX() );
        assertEquals( expected.getPlayerIdO(),actual.getPlayerIdO() );
        assertEquals( expected.getCurrentPlayerId(),actual.getCurrentPlayerId() );
        assertEquals( expected.getStart(),actual.getStart() );
        assertNull( actual.getFinish() );
        assertEquals( expected.getState(),actual.getState() );
    }

    /**
     * Test method for {@link IGameRepository#removeGame(Game)}.
     */
    @Test
    public void 
    testRemoveGame() throws Exception
    {
        Game game = new Game();
        
        game.setPlayerIdX( 1L );
        game.setPlayerIdO(  2L );
        game.setCurrentPlayerId( 1L );
        
        game = itsTarget.insertGame( game );
        assertTrue( itsTarget.hasGame( game.getGameId() ) );
        itsTarget.removeGame( game );
        assertFalse( itsTarget.hasGame( game.getGameId() ) );
    }

    /**
     * Test method for {@link IGameRepository#getGame(Long)}.
     */
    @Test
    public void 
    testGetGame() throws Exception
    {
        Game expected = new Game();
        Game actual   = null;
        Move move     = new Move( 1L,1,1,Game.X_MARKER );
        
        expected.setPlayerIdX( 1L );
        expected.setPlayerIdO(  2L );
        expected.setCurrentPlayerId( 1L );
        
        expected = itsTarget.insertGame( expected );
        itsGameIds.add( expected.getGameId()  );
        expected.makeMove( move );
        itsTarget.updateGame( expected );
        itsTarget
            .getUnitOfWork()
            .commit();
        
        actual = itsTarget.getGame( expected.getGameId() );
        assertEquals( expected.getPlayerIdX(),actual.getPlayerIdX() );
        assertEquals( expected.getPlayerIdO(),actual.getPlayerIdO() );
        assertEquals( expected.getCurrentPlayerId(),actual.getCurrentPlayerId() );
        assertEquals( expected.getStart(),actual.getStart() );
        assertNull( actual.getFinish() );
        assertEquals( expected.getState(),actual.getState() );
    }

    /**
     * Test method for {@link IGameRepository#hasGame(Long)}.
     */
    @Test
    public void 
    testHasGame() throws Exception
    {
        Game game = new Game();
        
        game.setPlayerIdX( 1L );
        game.setPlayerIdO(  2L );
        game.setCurrentPlayerId( 1L );
        
        game = itsTarget.insertGame( game );
        assertTrue( itsTarget.hasGame( game.getGameId() ) );
        itsTarget.removeGame( game );
        assertFalse( itsTarget.hasGame( game.getGameId() ) );
    }
    
    protected abstract PersistenceModule
    getPersistenceModule();

}

// ##########################################################################

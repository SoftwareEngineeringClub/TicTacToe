// ##########################################################################
// # File Name:	PlayerRepositoryTest.java
// ##########################################################################

package tictactoe.domain.playerdomain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import tictactoe.domain.persistence.PersistenceModule;
import tictactoe.domain.sessiondomain.IUserRepository;
import tictactoe.domain.sessiondomain.SessionDomainModule;
import tictactoe.domain.sessiondomain.User;

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
class PlayerRepositoryTest
{
    private IContainer        itsContainer;
    private IUserRepository   itsUserRepository;
    private IPlayerRepository itsTarget;
    private User              itsUserA;
    private User              itsUserB;
    private User              itsUserC;
    private Set<Long>         itsPlayerIds;

    /************************************************************************
     *  
     *
     * @throws java.lang.Exception
     */
    @Before
    public void 
    setUp() throws Exception
    {
        IUnitOfWork unitOfWork = null;
        
        try
        {
            itsContainer = new Container();
            getPersistenceModule().initialize( itsContainer );
            new SessionDomainModule().initialize( itsContainer );
            new PlayerDomainModule().initialize( itsContainer );
        }
        catch (Exception e)
        {
            itsContainer = null;
            throw e;
        }
        
        
        itsUserRepository = 
            itsContainer.getInstance( IUserRepository.class );
        itsTarget = 
            itsContainer.getInstance( IPlayerRepository.class );

        assertNotNull( itsUserRepository );
        
        unitOfWork = 
            itsContainer
                .getInstance( IRepositoryContext.class )
                .getUnitOfWork();
        
        itsUserA = new User();
        itsUserA.setUserName( "Alexander Beck" );
        itsUserA.setPassword( "passwordA" );
        itsUserA = itsUserRepository.insertUser( itsUserA );
        
        itsUserB = new User();
        itsUserB.setUserName( "Bertram Carter" );
        itsUserB.setPassword( "passwordB" );
        itsUserB = itsUserRepository.insertUser( itsUserB );
        
        itsUserC = new User();
        itsUserC.setUserName( "Carol Davis" );
        itsUserC.setPassword( "passwordC" );
        itsUserC = itsUserRepository.insertUser( itsUserC );
       
        unitOfWork.commit();
        itsPlayerIds = new HashSet<Long>();
    }

    /************************************************************************
     *  
     *
     * @throws java.lang.Exception
     */
    @After
    public void 
    tearDown() throws Exception
    {
        IUnitOfWork unitOfWork = 
            itsContainer
                .getInstance( IRepositoryContext.class )
                .getUnitOfWork();
        
        itsUserRepository.removeUser( itsUserA );
        itsUserRepository.removeUser( itsUserB );
        itsUserRepository.removeUser( itsUserC );
        
        for (Long playerId : itsPlayerIds)
        {
            Player player = itsTarget.getPlayer( playerId );
            
            itsTarget.removePlayer( player );
        }
        
        unitOfWork.commit();
        
        itsUserRepository = null;
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
     * Test method for {@link IPlayerRepository#insertPlayer(Player)}.
     */
    @Test
    public void 
    testInsertPlayer()
        throws Exception
    {
        Player expected = new Player();
        Player actual   = null;
        
        expected.setUserId( itsUserA.getUserId() );
        actual = itsTarget.insertPlayer( expected );
        itsPlayerIds.add( actual.getPlayerId() );
        
        assertEquals( expected.getUserId(),actual.getUserId() );
        assertEquals( expected.getWins(),actual.getWins() );
        assertEquals( expected.getLosses(),actual.getLosses() );
        assertEquals( expected.getTies(),actual.getTies() );
        assertEquals( expected.getCurrentRank(),actual.getCurrentRank() );
        assertEquals( expected.getAverageRank(),actual.getAverageRank() );   
    }

    /**
     * Test method for {@link IPlayerRepository#updatePlayer(Player)}.
     */
    @Test
    public void 
    testUpdatePlayer()
        throws Exception
    {
        Player expected = new Player();
        Player actual   = null;
        
        expected.setUserId( itsUserA.getUserId() );
        expected = itsTarget.insertPlayer( expected );
        itsTarget
            .getUnitOfWork()
            .commit();
        itsPlayerIds.add( expected.getPlayerId() );

        expected.incrementWins();
        actual = itsTarget.updatePlayer( expected );
        
        assertEquals( expected.getUserId(),actual.getUserId() );
        assertEquals( expected.getWins(),actual.getWins() );
        assertEquals( expected.getLosses(),actual.getLosses() );
        assertEquals( expected.getTies(),actual.getTies() );
        assertEquals( expected.getCurrentRank(),actual.getCurrentRank() );
        assertEquals( expected.getAverageRank(),actual.getAverageRank() );   
        
    }

    /**
     * Test method for {@link IPlayerRepository#removePlayer(Player)}.
     */
    @Test
    public void 
    testRemovePlayer()
        throws Exception
    {
        Player player = new Player();
        
        player.setUserId( itsUserB.getUserId() );
        player = itsTarget.insertPlayer( player );
        assertTrue( itsTarget.hasPlayer( player.getPlayerId() ) );
        itsTarget.removePlayer( player );
        assertFalse( itsTarget.hasPlayer( player.getPlayerId() ) );
    }

    /**
     * Test method for {@link IPlayerRepository#getPlayer(Long)}.
     */
    @Test
    public void 
    testGetPlayer() throws Exception
    {
        Player expected = new Player();
        Player actual   = null;
        
        expected.setUserId( itsUserA.getUserId() );
        expected = itsTarget.insertPlayer( expected );
        itsTarget
            .getUnitOfWork()
            .commit();
        itsPlayerIds.add( expected.getPlayerId() );

        actual = itsTarget.getPlayer( expected.getPlayerId() );
        
        assertEquals( expected.getUserId(),actual.getUserId() );
        assertEquals( expected.getWins(),actual.getWins() );
        assertEquals( expected.getLosses(),actual.getLosses() );
        assertEquals( expected.getTies(),actual.getTies() );
        assertEquals( expected.getCurrentRank(),actual.getCurrentRank() );
        assertEquals( expected.getAverageRank(),actual.getAverageRank() );   
        
    }

    /**
     * Test method for {@link IPlayerRepository#getPlayerFor(User)}.
     */
    @Test
    public void 
    testGetPlayerFor()
        throws Exception
    {
        Player expected = new Player();
        Player actual   = null;
        
        expected.setUserId( itsUserC.getUserId() );
        expected = itsTarget.insertPlayer( expected );
        itsTarget
            .getUnitOfWork()
            .commit();
        itsPlayerIds.add( expected.getPlayerId() );

        actual = itsTarget.getPlayerFor( itsUserC );
        
        assertEquals( expected.getUserId(),actual.getUserId() );
        assertEquals( expected.getWins(),actual.getWins() );
        assertEquals( expected.getLosses(),actual.getLosses() );
        assertEquals( expected.getTies(),actual.getTies() );
        assertEquals( expected.getCurrentRank(),actual.getCurrentRank() );
        assertEquals( expected.getAverageRank(),actual.getAverageRank() );   
        
    }

    /**
     * Test method for {@link IPlayerRepository#hasPlayer(Long)}.
     */
    @Test
    public void 
    testHasPlayer() throws Exception
    {
        Player player = new Player();
        
        player.setUserId( itsUserB.getUserId() );
        player = itsTarget.insertPlayer( player );
        assertTrue( itsTarget.hasPlayer( player.getPlayerId() ) );
        itsTarget.removePlayer( player );
        assertFalse( itsTarget.hasPlayer( player.getPlayerId() ) );
    }

    /**
     * Test method for {@link IPlayerRepository#hasPlayerFor(User)}.
     */
    @Test
    public void 
    testHasPlayerFor()
        throws Exception
    {
        Player player = new Player();
        
        player.setUserId( itsUserB.getUserId() );
        player = itsTarget.insertPlayer( player );
        itsTarget
            .getUnitOfWork()
            .commit();
        assertTrue( itsTarget.hasPlayerFor( itsUserB ) );
        itsTarget.removePlayer( player );
        itsTarget
            .getUnitOfWork()
            .commit();
        assertFalse( itsTarget.hasPlayerFor( itsUserB ) );
    }

    protected abstract PersistenceModule
    getPersistenceModule();

}

// ##########################################################################

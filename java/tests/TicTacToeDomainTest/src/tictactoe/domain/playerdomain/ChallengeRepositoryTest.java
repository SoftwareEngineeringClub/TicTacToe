// ##########################################################################
// # File Name:	ChallengeRepositoryTest.java
// ##########################################################################

package tictactoe.domain.playerdomain;

import static org.junit.Assert.*;

import tictactoe.domain.persistence.PersistenceModule;
import tictactoe.domain.sessiondomain.IUserRepository;
import tictactoe.domain.sessiondomain.SessionDomainModule;
import tictactoe.domain.sessiondomain.User;

import strata1.entity.repository.IRepositoryContext;
import strata1.entity.repository.IUnitOfWork;
import strata1.injector.container.Container;
import strata1.injector.container.IContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/****************************************************************************
 * 
 */
public abstract 
class ChallengeRepositoryTest
{
    private IContainer           itsContainer;
    private IChallengeRepository itsTarget;
    private Set<Long>            itsChallengeIds;
    
    /************************************************************************
     *  
     *
     * @throws Exception
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
            new PlayerDomainModule().initialize( itsContainer );
        }
        catch (Exception e)
        {
            itsContainer = null;
            throw e;
        }
        
        itsTarget = 
            itsContainer.getInstance( IChallengeRepository.class );

        assertNotNull( itsTarget );
        itsChallengeIds = new HashSet<Long>();
        
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
        IUnitOfWork unitOfWork = 
            itsContainer
                .getInstance( IRepositoryContext.class )
                .getUnitOfWork();
        
        
        for (Long challengeId : itsChallengeIds)
        {
            Challenge challenge = itsTarget.getChallenge( challengeId );
            
            itsTarget.removeChallenge( challenge );
        }
        
        unitOfWork.commit();
        
        itsTarget = null;
        itsContainer = null;

    }

    /**
     * Test method for {@link IChallengeRepository#insertChallenge(Challenge)}.
     */
    @Test
    public void 
    testInsertChallenge()
        throws Exception
    {
        Challenge expected = new Challenge();
        Challenge actual   = null;
        
        expected.setOriginatingRequestId( 1L );
        expected.setChallengerUserId( 2L );
        expected.setChallengedUserId( 3L );
        expected.setSessionKey( "Session1" );
        expected.setReplyChannelId( "ReplyChannelI" );
        expected.setReturnAddress( "130494583" );
        expected.setStatus( ChallengeStatus.REPLY_PENDING );
        
        actual = itsTarget.insertChallenge( expected );
        itsChallengeIds.add( actual.getChallengeId() );
        assertEquals(expected.getOriginatingRequestId(),actual.getOriginatingRequestId());
        assertEquals(expected.getChallengerUserId(),actual.getChallengerUserId());
        assertEquals(expected.getChallengedUserId(),actual.getChallengedUserId());
        assertEquals(expected.getSessionKey(),actual.getSessionKey());
        assertEquals(expected.getReplyChannelId(),actual.getReplyChannelId());
        assertEquals(expected.getReturnAddress(),actual.getReturnAddress());
        assertEquals(expected.getStatus(),actual.getStatus());
    }

    /**
     * Test method for {@link IChallengeRepository#updateChallenge(Challenge)}.
     */
    @Test
    public void 
    testUpdateChallenge() 
        throws Exception
    {
        Challenge expected = new Challenge();
        Challenge actual   = null;
        
        expected.setOriginatingRequestId( 1L );
        expected.setChallengerUserId( 2L );
        expected.setChallengedUserId( 3L );
        expected.setSessionKey( "Session1" );
        expected.setReplyChannelId( "ReplyChannelI" );
        expected.setReturnAddress( "130494583" );
        expected.setStatus( ChallengeStatus.REPLY_PENDING );
        
        actual = itsTarget.insertChallenge( expected );
        itsChallengeIds.add( actual.getChallengeId() );
        itsTarget
            .getUnitOfWork()
            .commit();
        
        expected = actual;
        expected.setStatus( ChallengeStatus.ACCEPTED );
        actual = itsTarget.updateChallenge( expected );
        
        itsTarget
            .getUnitOfWork()
            .commit();
        
        assertEquals(expected.getOriginatingRequestId(),actual.getOriginatingRequestId());
        assertEquals(expected.getChallengerUserId(),actual.getChallengerUserId());
        assertEquals(expected.getChallengedUserId(),actual.getChallengedUserId());
        assertEquals(expected.getSessionKey(),actual.getSessionKey());
        assertEquals(expected.getReplyChannelId(),actual.getReplyChannelId());
        assertEquals(expected.getReturnAddress(),actual.getReturnAddress());
        assertEquals(expected.getStatus(),actual.getStatus());
    }

    /**
     * Test method for {@link IChallengeRepository#removeChallenge(Challenge)}.
     */
    @Test
    public void 
    testRemoveChallenge()
        throws Exception
    {
        Challenge expected = new Challenge();
        Challenge actual   = null;
        
        expected.setOriginatingRequestId( 1L );
        expected.setChallengerUserId( 2L );
        expected.setChallengedUserId( 3L );
        expected.setSessionKey( "Session1" );
        expected.setReplyChannelId( "ReplyChannelI" );
        expected.setReturnAddress( "130494583" );
        expected.setStatus( ChallengeStatus.REPLY_PENDING );
        
        actual = itsTarget.insertChallenge( expected );
        itsTarget
            .getUnitOfWork()
            .commit();        
        assertTrue( itsTarget.hasChallenge( actual.getChallengeId() ));
        itsTarget.removeChallenge( actual );
        itsTarget
            .getUnitOfWork()
            .commit();        
        assertFalse( itsTarget.hasChallenge( actual.getChallengeId() ));
        
        
    }

    /**
     * Test method for {@link IChallengeRepository#getChallenges()}.
     */
    @Test
    public void 
    testGetChallenges()
        throws Exception
    {
        Challenge       expected = new Challenge();
        List<Challenge> actual   = null;
        
        expected.setOriginatingRequestId( 1L );
        expected.setChallengerUserId( 2L );
        expected.setChallengedUserId( 3L );
        expected.setSessionKey( "Session1" );
        expected.setReplyChannelId( "ReplyChannelI" );
        expected.setReturnAddress( "130494583" );
        expected.setStatus( ChallengeStatus.REPLY_PENDING );
        
        expected = itsTarget.insertChallenge( expected );
        itsTarget
            .getUnitOfWork()
            .commit();
        itsChallengeIds.add( expected.getChallengeId() );
        actual = itsTarget.getChallenges();
        
        assertEquals(expected.getOriginatingRequestId(),actual.get( 0 ).getOriginatingRequestId());
        assertEquals(expected.getChallengerUserId(),actual.get( 0 ).getChallengerUserId());
        assertEquals(expected.getChallengedUserId(),actual.get( 0 ).getChallengedUserId());
        assertEquals(expected.getSessionKey(),actual.get( 0 ).getSessionKey());
        assertEquals(expected.getReplyChannelId(),actual.get( 0 ).getReplyChannelId());
        assertEquals(expected.getReturnAddress(),actual.get( 0 ).getReturnAddress());
        assertEquals(expected.getStatus(),actual.get( 0 ).getStatus());
    }

    /**
     * Test method for {@link IChallengeRepository#getChallengeLong)}.
     */
    @Test
    public void 
    testGetChallenge()
        throws Exception
    {
        Challenge expected = new Challenge();
        Challenge actual   = null;
        
        expected.setOriginatingRequestId( 1L );
        expected.setChallengerUserId( 2L );
        expected.setChallengedUserId( 3L );
        expected.setSessionKey( "Session1" );
        expected.setReplyChannelId( "ReplyChannelI" );
        expected.setReturnAddress( "130494583" );
        expected.setStatus( ChallengeStatus.REPLY_PENDING );
        
        expected = itsTarget.insertChallenge( expected );
        itsTarget
            .getUnitOfWork()
            .commit();
        itsChallengeIds.add( expected.getChallengeId() );
        
        actual = itsTarget.getChallenge( expected.getChallengeId() );
        assertEquals(expected.getOriginatingRequestId(),actual.getOriginatingRequestId());
        assertEquals(expected.getChallengerUserId(),actual.getChallengerUserId());
        assertEquals(expected.getChallengedUserId(),actual.getChallengedUserId());
        assertEquals(expected.getSessionKey(),actual.getSessionKey());
        assertEquals(expected.getReplyChannelId(),actual.getReplyChannelId());
        assertEquals(expected.getReturnAddress(),actual.getReturnAddress());
        assertEquals(expected.getStatus(),actual.getStatus());
    }

    /**
     * Test method for {@link IChallengeRepository#getPendingChallengeFor(Player,Player)}.
     */
    @Test
    public void 
    testGetPendingChallengeFor()
        throws Exception
    {
        Challenge expected = new Challenge();
        Challenge actual   = null;
        Player    challenger = new Player();
        Player    challenged = new Player();
        
        challenger.setUserId( 2L );
        challenged.setUserId( 3L );
        
        expected.setOriginatingRequestId( 1L );
        expected.setChallengerUserId( 2L );
        expected.setChallengedUserId( 3L );
        expected.setSessionKey( "Session1" );
        expected.setReplyChannelId( "ReplyChannelI" );
        expected.setReturnAddress( "130494583" );
        expected.setStatus( ChallengeStatus.REPLY_PENDING );
        
        expected = itsTarget.insertChallenge( expected );
        itsTarget
            .getUnitOfWork()
            .commit();
        itsChallengeIds.add( expected.getChallengeId() );
        
        actual = itsTarget.getPendingChallengeFor( challenger,challenged );
        assertEquals(expected.getOriginatingRequestId(),actual.getOriginatingRequestId());
        assertEquals(expected.getChallengerUserId(),actual.getChallengerUserId());
        assertEquals(expected.getChallengedUserId(),actual.getChallengedUserId());
        assertEquals(expected.getSessionKey(),actual.getSessionKey());
        assertEquals(expected.getReplyChannelId(),actual.getReplyChannelId());
        assertEquals(expected.getReturnAddress(),actual.getReturnAddress());
        assertEquals(expected.getStatus(),actual.getStatus());
    }

    /**
     * Test method for {@link IChallengeRepository#hasChallenge(Long)}.
     */
    @Test
    public void 
    testHasChallenge()
        throws Exception
    {
        Challenge expected = new Challenge();
        Challenge actual   = null;
        
        expected.setOriginatingRequestId( 1L );
        expected.setChallengerUserId( 2L );
        expected.setChallengedUserId( 3L );
        expected.setSessionKey( "Session1" );
        expected.setReplyChannelId( "ReplyChannelI" );
        expected.setReturnAddress( "130494583" );
        expected.setStatus( ChallengeStatus.REPLY_PENDING );
        
        actual = itsTarget.insertChallenge( expected );
        itsTarget
            .getUnitOfWork()
            .commit();        
        assertTrue( itsTarget.hasChallenge( actual.getChallengeId() ));
        itsTarget.removeChallenge( actual );
        itsTarget
            .getUnitOfWork()
            .commit();        
        assertFalse( itsTarget.hasChallenge( actual.getChallengeId() ));
    }

    /**
     * Test method for {@link IChallengeRepository#hasPendingChallengeFor(Long,Long)}.
     */
    @Test
    public void 
    testHasPendingChallengeFor()
        throws Exception
    {
        Challenge expected = new Challenge();
        Challenge actual   = null;
        Player    challenger = new Player();
        Player    challenged = new Player();
        
        challenger.setUserId( 2L );
        challenged.setUserId( 3L );
        
        expected.setOriginatingRequestId( 1L );
        expected.setChallengerUserId( 2L );
        expected.setChallengedUserId( 3L );
        expected.setSessionKey( "Session1" );
        expected.setReplyChannelId( "ReplyChannelI" );
        expected.setReturnAddress( "130494583" );
        expected.setStatus( ChallengeStatus.REPLY_PENDING );
        
        expected = itsTarget.insertChallenge( expected );
        itsTarget
            .getUnitOfWork()
            .commit();
        itsChallengeIds.add( expected.getChallengeId() );
        
        assertTrue( itsTarget.hasPendingChallengeFor( challenger,challenged ) );
    }

    protected abstract PersistenceModule
    getPersistenceModule();

}

// ##########################################################################

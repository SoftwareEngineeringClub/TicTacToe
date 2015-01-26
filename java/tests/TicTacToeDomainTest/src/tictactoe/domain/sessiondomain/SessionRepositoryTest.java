// ##########################################################################
// # File Name:	SessionRepositoryTest.java
// ##########################################################################

package tictactoe.domain.sessiondomain;

import static org.junit.Assert.*;

import tictactoe.domain.persistence.PersistenceModule;

import strata1.entity.repository.CommitFailedException;
import strata1.entity.repository.IRepositoryContext;
import strata1.entity.repository.IUnitOfWork;
import strata1.entity.repository.InsertFailedException;
import strata1.entity.repository.RemoveFailedException;
import strata1.entity.repository.UpdateFailedException;
import strata1.injector.container.Binder;
import strata1.injector.container.Container;
import strata1.injector.container.IContainer;
import strata1.injector.container.IModule;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;

/****************************************************************************
 * 
 */
public abstract
class SessionRepositoryTest
{
    private IContainer         itsContainer;
    private ISessionRepository itsTarget;
    
    /************************************************************************
     *  
     *
     * @throws java.lang.Exception
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
        }
        catch (Exception e)
        {
            itsContainer = null;
            throw e;
        }
        
        itsTarget = itsContainer.getInstance( ISessionRepository.class );
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
        itsTarget = null;
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
     * Test method for {@link ISessionRepository#insertSession(Session)}.
     */
    @Test
    public void 
    testInsertSession() 
        throws InsertFailedException
    {
        Session expected = new Session();
        Session actual   = null;
        
        expected.setVersion( new Integer( 1 ) );
        expected.setUserId( new Long(2) );
        
        actual = itsTarget.insertSession( expected );
        //assertNotEquals( expected.getSessionId(),actual.getSessionId() );
        assertEquals( expected.getVersion(),actual.getVersion() );
        assertEquals( expected.getUserId(),actual.getUserId() );
    }

    /**
     * Test method for {@link ISessionRepository#updateSession(Session)}.
     */
    @Test
    public void 
    testUpdateSession() throws InsertFailedException, UpdateFailedException
    {
        Session expected = new Session();
        Session actual   = null;
        
        expected.setVersion( new Integer( 1 ) );
        expected.setUserId( new Long(2) );
        
        expected = itsTarget.insertSession( expected );
        
        expected.setUserId( new Long(3) );
        expected.setSessionReturnAddress( "Session-12345" );
        expected.setPlayerReturnAddress( "Player-12345" );
        actual = itsTarget.updateSession( expected );
        
        //assertNotEquals( expected.getSessionId(),actual.getSessionId() );
        assertEquals( expected.getVersion(),actual.getVersion() );
        assertEquals( expected.getUserId(),actual.getUserId() );
        assertEquals( expected.getSessionReturnAddress(),actual.getSessionReturnAddress() );
        assertEquals( expected.getPlayerReturnAddress(),actual.getPlayerReturnAddress() );
    }

    /**
     * Test method for {@link ISessionRepository#removeSession(Session)}.
     */
    @Test
    public void 
    testRemoveSession() throws InsertFailedException, RemoveFailedException
    {
        Session expected = new Session();
        
        expected.setVersion( new Integer( 1 ) );
        expected.setUserId( new Long(2) );
        
        expected = itsTarget.insertSession( expected );
        assertTrue( itsTarget.hasSession( expected.getSessionId() ) );
        
        itsTarget.removeSession( expected );
        assertFalse( itsTarget.hasSession( expected.getSessionId() ) );
    }

    /**
     * Test method for {@link ISessionRepository#getSession(Long)}.
     */
    @Test
    public void 
    testGetSession() throws InsertFailedException, CommitFailedException
    {
        Session expected = new Session();
        Session actual1   = null;
        Session actual2   = null;
        
        expected.setVersion( new Integer( 1 ) );
        expected.setUserId( new Long(2) );
        
        actual1 = itsTarget.insertSession( expected );
        itsTarget
            .getUnitOfWork()
            .commit();
        actual2 = itsTarget.getSession( actual1.getSessionId() );
        
        //assertNotEquals( expected.getSessionId(),actual.getSessionId() );
        assertEquals( actual2.getVersion(),actual1.getVersion() );
        assertEquals( actual2.getUserId(),actual1.getUserId() );
    }

    protected abstract PersistenceModule
    getPersistenceModule();

}

// ##########################################################################

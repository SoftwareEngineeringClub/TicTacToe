// ##########################################################################
// # File Name:	UserRepositoryTest.java
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
import strata1.injector.container.Container;
import strata1.injector.container.IContainer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

/****************************************************************************
 * 
 */
public abstract
class UserRepositoryTest
{
    private IContainer         itsContainer;
    private ISessionRepository itsSessionRepository;
    private IUserRepository    itsTarget;
    private Long               itsUserId;

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
        }
        catch (Exception e)
        {
            itsContainer = null;
            throw e;
        }
        
        itsSessionRepository = 
            itsContainer.getInstance( ISessionRepository.class );
        itsTarget = 
            itsContainer.getInstance( IUserRepository.class );
        itsUserId = new Long(0);
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
        User user = itsTarget.getUser( itsUserId );
        
        if ( user != null )
            itsTarget.removeUser( user );
        
        itsTarget
            .getUnitOfWork()
            .commit();
        
        itsSessionRepository = null;
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
     * Test method for {@link IUserRepository#insertUser(User)}.
     */
    @Test
    public void 
    testInsertUser() throws InsertFailedException
    {
        User expected = new User();
        User actual   = null;
        
        expected.setVersion( new Integer( 1 ) );
        expected.setUserName( "AlbertBaker" );
        expected.setPassword( "FooBar" );
        
        actual = itsTarget.insertUser( expected );
        itsUserId = actual.getUserId();
        
        //assertNotEquals( expected.getUserId(),actual.getUserId() );
        assertEquals( expected.getVersion(),actual.getVersion() );
        assertEquals( expected.getUserName(),actual.getUserName() );
        assertEquals( expected.getPassword(),actual.getPassword() );
    }

    /**
     * Test method for {@link IUserRepository#updateUser(User)}.
     */
    @Test
    public void 
    testUpdateUser() throws InsertFailedException, UpdateFailedException
    {
        User expected = new User();
        User actual   = null;
        
        expected.setVersion( new Integer( 1 ) );
        expected.setUserName( "AlbertBaker" );
        expected.setPassword( "FooBar" );
        
        expected = itsTarget.insertUser( expected );
        
        expected.setPassword( "f!o@o#b$a%r" );
        actual = itsTarget.updateUser( expected );
        itsUserId = actual.getUserId();
        
        //assertNotEquals( expected.getUserId(),actual.getUserId() );
        assertEquals( expected.getVersion(),actual.getVersion() );
        assertEquals( expected.getUserName(),actual.getUserName() );
        assertEquals( expected.getPassword(),actual.getPassword() );
    }

    /**
     * Test method for {@link IUserRepository#removeUser(User)}.
     */
    @Test
    public void 
    testRemoveUser() throws InsertFailedException, RemoveFailedException
    {
        User expected = new User();
        User actual   = null;
        
        expected.setVersion( new Integer( 1 ) );
        expected.setUserName( "AlbertBaker" );
        expected.setPassword( "FooBar" );
        
        actual = itsTarget.insertUser( expected );
        assertTrue( itsTarget.hasUser( actual.getUserId() ) );
        
        itsTarget.removeUser( actual );
        assertFalse( itsTarget.hasUser( actual.getUserId() ));
    }

    /**
     * Test method for {@link IUserRepository#getUsers()}.
     */
    @Test
    public void 
    testGetUsers() throws Exception
    {
        User       expected = new User();
        List<User> actual   = null;
        
        expected.setVersion( new Integer( 1 ) );
        expected.setUserName( "AlbertBaker" );
        expected.setPassword( "FooBar" );
        
        expected = itsTarget.insertUser( expected );
        itsUserId = expected.getUserId();
        
        itsTarget
            .getUnitOfWork()
            .commit();
        
        actual = itsTarget.getUsers();
        
        assertEquals( 1,actual.size() );
        assertEquals( expected.getVersion(),actual.get(0).getVersion() );
        assertEquals( expected.getUserName(),actual.get(0).getUserName() );
        assertEquals( expected.getPassword(),actual.get(0).getPassword() );
    }

    /**
     * Test method for {@link IUserRepository#getUser(Long)}.
     */
    @Test
    public void 
    testGetUser() throws InsertFailedException, CommitFailedException
    {
        User expected = new User();
        User actual   = null;
        
        expected.setVersion( new Integer( 1 ) );
        expected.setUserName( "AlbertBaker" );
        expected.setPassword( "FooBar" );
        
        actual = itsTarget.insertUser( expected );
        itsUserId = actual.getUserId();
        
        itsTarget
            .getUnitOfWork()
            .commit();
        
        actual = itsTarget.getUser( itsUserId );
        
        //assertNotEquals( expected.getUserId(),actual.getUserId() );
        assertEquals( expected.getVersion(),actual.getVersion() );
        assertEquals( expected.getUserName(),actual.getUserName() );
        assertEquals( expected.getPassword(),actual.getPassword() );
    }

    /**
     * Test method for {@link IUserRepository#getUserFor(Session)}.
     */
    @Test
    public void 
    testGetUserFor() throws Exception
    {
        User    expected = new User();
        User    actual   = null;
        Session session  = new Session();
        boolean inserted = false;
        
        try
        {
            expected.setVersion( new Integer( 1 ) );
            expected.setUserName( "AlbertBaker" );
            expected.setPassword( "FooBar" );
            
            actual = itsTarget.insertUser( expected );
            itsUserId = actual.getUserId();
            session.setUserId( itsUserId );
            session = itsSessionRepository.insertSession( session );
            
            itsTarget
                .getUnitOfWork()
                .commit();
            
            inserted = true;
            actual = itsTarget.getUserFor( session );
            
            //assertNotEquals( expected.getUserId(),actual.getUserId() );
            assertEquals( expected.getVersion(),actual.getVersion() );
            assertEquals( expected.getUserName(),actual.getUserName() );
            assertEquals( expected.getPassword(),actual.getPassword() );
        }
        finally
        {
            if ( inserted )
                itsSessionRepository.removeSession( session );
        }
    }
    
    /**
     * Test method for {@link IUserRepository#hasUser(Long)}.
     */
    @Test
    public void
    testHasUser()
    {
        
    }

    protected abstract PersistenceModule
    getPersistenceModule();

}

// ##########################################################################

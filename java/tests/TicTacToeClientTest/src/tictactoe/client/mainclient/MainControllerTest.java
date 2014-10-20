// ##########################################################################
// # File Name:	MainControllerTest.java
// ##########################################################################

package tictactoe.client.mainclient;

import static org.junit.Assert.*;

import tictactoe.client.gameclient.GameClientTestModule;
import tictactoe.client.homeclient.HomeClientTestModule;
import tictactoe.client.playersclient.PlayersClientTestModule;

import strata1.client.command.ExecutionException;
import strata1.client.controller.IController;
import strata1.injector.container.Container;
import strata1.injector.container.IContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/****************************************************************************
 * 
 */
public 
class MainControllerTest
{
    private IContainer      itsContainer;
    private IMainController itsTarget;
    
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
            new MainClientTestModule().initialize( itsContainer );
            new HomeClientTestModule().initialize( itsContainer );
            new PlayersClientTestModule().initialize( itsContainer );
            new GameClientTestModule().initialize( itsContainer );
        }
        catch (Exception e)
        {
            itsContainer = null;
            throw e;
        }
        
        itsContainer.getInstance( ISessionController.class );
        itsTarget = itsContainer.getInstance( IMainController.class );
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
        itsTarget = null;
        itsContainer = null;
    }

    /**
     * Test method for {@link IController#start()}.
     */
    @Test
    public void 
    testStart() throws Exception
    {
        IMainView     mainView = itsContainer.getInstance( IMainView.class );
        IMainModel    mainModel = itsContainer.getInstance( IMainModel.class );
        ISessionView  sessionView = itsContainer.getInstance( ISessionView.class );
        ISessionModel sessionModel = itsContainer.getInstance(  ISessionModel.class );
        
        assertNotNull( itsTarget );
        itsTarget.start();
        assertFalse( sessionModel.isRegistered() );
        sessionView.invoke( "Register" );
        assertTrue( sessionModel.isRegistered() );
        assertFalse( sessionModel.isLoggedIn() );
        sessionView.invoke( "Login" );
        assertTrue( sessionModel.isLoggedIn() );
        assertEquals( MainTabKind.HOME_TAB,mainModel.getActiveTab() );
        assertEquals( MainTabKind.HOME_TAB,mainView.getActiveTab() );
        assertEquals( sessionModel,mainModel.getSession() );
    }

}

// ##########################################################################

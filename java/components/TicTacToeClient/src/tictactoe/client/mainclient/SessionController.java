// ##########################################################################
// # File Name:	SessionController.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.client.command.ExecutionException;
import strata1.client.command.ICommand;
import strata1.client.controller.AbstractController;
import strata1.client.controller.IController;
import strata1.client.controller.IHandler;
import strata1.client.event.IChangeEvent;
import strata1.common.logger.ILogger;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class SessionController 
    extends    AbstractController 
    implements ISessionController
{
    private final IMainController itsMainController;
    private final ISessionView    itsView;
    private final ISessionModel   itsModel;
    private final ILogger         itsLogger;
    
    /************************************************************************
     * Creates a new SessionController. 
     *
     * @param view
     * @param model
     * @param mainController
     */
    @Inject
    public 
    SessionController(
        IMainController   mainController,
        ISessionView      view,
        ISessionModel     model,
        ILogger           logger)
    {
        itsMainController = mainController;
        itsView           = view;
        itsModel          = model;
        itsLogger         = logger;
        
        itsMainController.setSessionController( this );
        itsView.setProvider( this );
        itsModel.setProcessor( this );
        
        setCommands();
        setHandlers();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    start()
    {
        itsLogger.logInfo( "Starting session controller." );
        itsView.start();
    }
    
    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainSubController 
    setSessionId(Long sessionId)
    {
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainSubController 
    setUserId(Long userId)
    {
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    startListening()
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ISessionView 
    getLoginView()
    {
        return itsView;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public ISessionModel 
    getLoginModel()
    {
        return itsModel;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainController 
    getMainController()
    {
        return itsMainController;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainSubController 
    showView()
    {
        itsView.show();
        return this;
    }

    /************************************************************************
     *  
     *
     */
    protected void
    setCommands()
    {
        setCommand( 
            "Login",
            new ICommand()
            {
                @Override
                public void 
                execute() 
                    throws ExecutionException
                {
                    doLogin();
                }
            } );
        
        setCommand( 
            "Logout",
            new ICommand()
            {
                @Override
                public void 
                execute() 
                    throws ExecutionException
                {
                    doLogout();
                }
            } );
        
        setCommand( 
            "Register",
            new ICommand()
            {
                @Override
                public void 
                execute() 
                    throws ExecutionException
                {
                    doRegister();
                }
            } );        
        
        setCommand( 
            "Exit",
            new ICommand()
            {
                @Override
                public void 
                execute() 
                    throws ExecutionException
                {
                    itsMainController.exit();
                }
            } );        
    }
    
    /************************************************************************
     *  
     *
     */
    protected void
    setHandlers()
    {
        setHandler(
            RegisterEvent.class,
            new IHandler<IChangeEvent> ()
            {
                @Override
                public void 
                handle(IChangeEvent event)
                {
                    doHandleRegister((RegisterEvent)event);
                }
            } );
        setHandler(
            LoginEvent.class,
            new IHandler<IChangeEvent> ()
            {
                @Override
                public void 
                handle(IChangeEvent event)
                {
                    doHandleLogin((LoginEvent)event);
                }
            } );
        setHandler(
            LogoutEvent.class,
            new IHandler<IChangeEvent> ()
            {
                @Override
                public void 
                handle(IChangeEvent event)
                {
                    doHandleLogout((LogoutEvent)event);
                }
            } );
            
    }
    
    /************************************************************************
     *  
     *
     */
    protected void
    doLogin()
    {
        itsLogger.logInfo( "Performing login." );
        itsModel.login( itsView.getLoginCredential() );
    }
    
    /************************************************************************
     *  
     *
     */
    protected void
    doLogout()
    {
        itsLogger.logInfo( "Performing logout." );
        itsModel.logout();
    }

    /************************************************************************
     *  
     *
     */
    protected void
    doHandleLogin(LoginEvent event)
    {
        ISessionModel sender = event.getSender( ISessionModel.class );
        
        if ( sender.isLoggedIn() )
        {
            itsLogger.logInfo( "Handling login event." );
       
            itsMainController
                .setSession( sender )
                .completeLogin();
        }
        else
            itsView.displayMessage( sender.getLoginError() );
    }

    /************************************************************************
     *  
     *
     */
    protected void
    doHandleLogout(LogoutEvent event)
    {
        ISessionModel sender = event.getSender( ISessionModel.class );
        
        if ( !sender.isLoggedIn() )
        {
            itsLogger.logInfo( "Handling logout event." );
            itsMainController.completeLogout();
        }
        else
            itsView.displayMessage( sender.getLoginError() );
    }

    /************************************************************************
     *  
     *
     */
    protected void
    doRegister()
    {
        itsLogger.logInfo( "Peforming register." );
        itsModel.register( itsView.getRegisterCredential() );
    }
    
    /************************************************************************
     *  
     *
     */
    protected void
    doHandleRegister(RegisterEvent event)
    {
        ISessionModel sender = event.getSender( ISessionModel.class );
        
        if ( sender.isRegistered() )
        {
            itsLogger.logInfo( "Handling register event." );
        }
        else
            itsView.displayMessage( sender.getLoginError() );
    }

    
}

// ##########################################################################

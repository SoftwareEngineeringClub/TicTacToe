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
        ISessionModel     model)
    {
        itsMainController = mainController;
        itsView           = view;
        itsModel          = model;
        
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
        itsView.show();
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
            
    }
    
    /************************************************************************
     *  
     *
     */
    protected void
    doLogin()
    {
        itsModel.login( itsView.getLoginCredential() );
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
            itsMainController
                .setSession( sender )
                .completeLogin();
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
        }
        else
            itsView.displayMessage( sender.getLoginError() );
    }

    
}

// ##########################################################################

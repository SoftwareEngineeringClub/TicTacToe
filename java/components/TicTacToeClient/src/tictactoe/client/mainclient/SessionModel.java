// ##########################################################################
// # File Name:	SessionModel.java
// ##########################################################################

package tictactoe.client.mainclient;

import tictactoe.service.sessionservice.ISessionReplyReceiver;
import tictactoe.service.sessionservice.ISessionService;
import tictactoe.service.sessionservice.LoginReply;
import tictactoe.service.sessionservice.LoginRequest;
import tictactoe.service.sessionservice.LogoutReply;
import tictactoe.service.sessionservice.LogoutRequest;
import tictactoe.service.sessionservice.RegisterReply;
import tictactoe.service.sessionservice.RegisterRequest;
import tictactoe.service.sessionservice.SessionException;

import strata1.client.event.IChangeEvent;
import strata1.client.event.IChangeEventProcessor;
import strata1.common.authentication.ICredential;
import strata1.common.logger.ILogger;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class SessionModel 
    implements ISessionModel,ISessionReplyReceiver
{
    private final ISessionService itsService;
    private final ILogger         itsLogger;
    private IChangeEventProcessor itsProcessor;
    private boolean               itsLoggedInFlag;
    private Long                  itsSessionId;
    private Long                  itsUserId;
    private LoginReply            itsLoginReply;
    private RegisterReply         itsRegisterReply;
    private KeepAliveTimer        itsTimer;
    
    /************************************************************************
     * Creates a new SessionModel. 
     *
     */
    @Inject
    public 
    SessionModel(ISessionService service,ILogger logger)
    {
        itsService       = service;
        itsLogger        = logger;
        itsProcessor     = null;
        itsLoginReply    = null;
        itsRegisterReply = null;
        itsTimer         = null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    notifyChange(IChangeEvent event)
    {
        itsProcessor.processChange( event );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    setProcessor(IChangeEventProcessor processor)
    {
        itsProcessor = processor;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    login(ICredential credential)
    {
        LoginRequest request = 
            new LoginRequest()
                .setUserName(credential.getField(String.class,"UserName"))
                .setPassword(credential.getField(String.class,"Password"));
        
        itsService.login( this,request );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    logout()
    {
        LogoutRequest request = 
            new LogoutRequest()
                .setSessionId( getSessionId() );
        
        itsService.logout( this,request );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    register(ICredential credential)
    {
        RegisterRequest request = 
            new RegisterRequest()
                .setUserName(credential.getField(String.class,"UserName"))
                .setPassword(credential.getField(String.class,"Password"));
        
        itsService.register( this,request );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Long 
    getSessionId()
    {
        return itsSessionId;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Long 
    getUserId()
    {
        return itsUserId;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public String 
    getLoginError()
    {
        return 
            itsLoginReply != null 
                ? itsLoginReply.getMessage() : null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public String 
    getRegisterError()
    {
        return 
            itsRegisterReply != null
                ? itsRegisterReply.getMessage() : null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    isLoggedIn()
    {
        return itsLoggedInFlag;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    isRegistered()
    {
        return 
            itsRegisterReply != null
                ? itsRegisterReply.isRegistered() : false;
    }

    /************************************************************************
     *  
     *
     * @param reply
     */
    @Override
    public void 
    onRegister(RegisterReply reply)
    {
        itsLogger.logInfo( 
            "Receiving register reply:" + reply.getReplyId() + 
            " for request: " + reply.getOriginatingRequestId() );
        itsRegisterReply = reply;
        notifyChange( new RegisterEvent(this) );
    }

    /************************************************************************
     *  
     *
     * @param reply
     */
    @Override
    public void 
    onLogin(LoginReply reply)
    {
        itsLogger.logInfo( 
            "Receiving login reply:" + reply.getReplyId() + 
            " for request: " + reply.getOriginatingRequestId() );
        
        itsLoginReply   = reply;
        itsLoggedInFlag = reply.isLoggedIn();
        itsSessionId    = reply.getSessionId();
        itsUserId       = reply.getUserId();
        
        if ( reply.isLoggedIn() )
        {
            itsTimer = new KeepAliveTimer( itsService,reply.getSessionId() );
            itsTimer.start();
        }
        
        notifyChange( new LoginEvent(this) );
    }

    /************************************************************************
     *  
     *
     * @param reply
     */
    @Override
    public void 
    onLogout(LogoutReply reply)
    {
        itsLogger.logInfo( 
            "Receiving logout reply:" + reply.getReplyId() + 
            " for request: " + reply.getOriginatingRequestId() );

        if ( reply.isLoggedOut() )
        {
            if ( itsTimer != null )
            {
                itsTimer.stop();
                itsTimer = null;
            }
            
            itsLoggedInFlag = false;
            itsSessionId    = 0L;
            itsUserId       = 0L;
        }
        
        notifyChange( new LogoutEvent(this) );
    }

    /************************************************************************
     *  
     *
     * @param exception
     */
    @Override
    public void 
    onSessionException(SessionException exception)
    {
        itsLogger.logError( 
            "Receiving session exception:" + exception.getMessage() );
        exception.printStackTrace( System.out );
    }

    /************************************************************************
     *  
     *
     * @param throwable
     */
    @Override
    public void 
    onThrowable(Throwable throwable)
    {
        itsLogger.logError( 
            "Receiving throwable:" + throwable.getMessage() );
        throwable.printStackTrace( System.out );
    }

}

// ##########################################################################

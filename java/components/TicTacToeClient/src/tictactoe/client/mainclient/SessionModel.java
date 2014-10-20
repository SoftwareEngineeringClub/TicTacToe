// ##########################################################################
// # File Name:	SessionModel.java
// ##########################################################################

package tictactoe.client.mainclient;

import tictactoe.service.sessionservice.ISessionReplyReceiver;
import tictactoe.service.sessionservice.ISessionService;
import tictactoe.service.sessionservice.LoginReply;
import tictactoe.service.sessionservice.LoginRequest;
import tictactoe.service.sessionservice.LogoutReply;
import tictactoe.service.sessionservice.RegisterReply;
import tictactoe.service.sessionservice.RegisterRequest;
import tictactoe.service.sessionservice.SessionException;

import strata1.client.event.IChangeEvent;
import strata1.client.event.IChangeEventProcessor;
import strata1.common.authentication.ICredential;
import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class SessionModel 
    implements ISessionModel,ISessionReplyReceiver
{
    private final ISessionService itsService;
    private IChangeEventProcessor itsProcessor;
    private LoginReply            itsLoginReply;
    private RegisterReply         itsRegisterReply;
    
    /************************************************************************
     * Creates a new SessionModel. 
     *
     */
    @Inject
    public 
    SessionModel(ISessionService service)
    {
        itsService       = service;
        itsProcessor     = null;
        itsLoginReply    = null;
        itsRegisterReply = null;
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
        return 
            itsLoginReply != null 
                ? itsLoginReply.getSessionId() : null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Long 
    getUserId()
    {
        return 
            itsLoginReply != null 
                ? itsLoginReply.getUserId() : null;
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
        return 
            itsLoginReply != null 
                ? itsLoginReply.isLoggedIn() : false;
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
        itsLoginReply = reply;
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
        throw 
            new IllegalStateException( "This model does not handle logout");
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
    }

}

// ##########################################################################

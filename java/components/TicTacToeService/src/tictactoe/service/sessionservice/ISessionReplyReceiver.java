// ##########################################################################
// # File Name:	ISessionReplyReceiver.java
// ##########################################################################

package tictactoe.service.sessionservice;

/****************************************************************************
 * 
 */
public 
interface ISessionReplyReceiver
{
    public void
    onRegister(RegisterReply reply);
    
    public void
    onLogin(LoginReply reply);
    
    public void
    onLogout(LogoutReply reply);
    
    public void
    onSessionException(SessionException exception);
    
    public void
    onThrowable(Throwable throwable);
}

// ##########################################################################

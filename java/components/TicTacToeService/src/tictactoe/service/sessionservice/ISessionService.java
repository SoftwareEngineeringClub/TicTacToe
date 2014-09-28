// ##########################################################################
// # File Name:	ISessionService.java
// ##########################################################################

package tictactoe.service.sessionservice;

/****************************************************************************
 * 
 */
public 
interface ISessionService
{
	public LoginReply
	login(LoginRequest request);
	
	public LogoutReply
	logout(LogoutRequest request);
}

// ##########################################################################

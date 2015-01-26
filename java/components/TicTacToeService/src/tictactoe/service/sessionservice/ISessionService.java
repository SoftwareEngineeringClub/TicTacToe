// ##########################################################################
// # File Name:	ISessionService.java
// ##########################################################################

package tictactoe.service.sessionservice;

/****************************************************************************
 * Provides session level operations for:
 * <p>
 * <ul>
 * <li>registering users with the system</li>
 * <li>logging in to the system</li>
 * <li>logging out of the system</li>
 * </ul>
 */
public 
interface ISessionService
{
    /************************************************************************
     * Sends a {@code RegisterRequest} in order to register
     * a new user with the system.
     *
     * @param reply     callback to asynchronously receive reply
     * @param request   request to register user with system
     */
    public void
    register(ISessionReplyReceiver reply,RegisterRequest request);
    
	/************************************************************************
	 * Sends a {@code LoginRequest} in order to login to the system. 
	 *
	 * @param reply    callback to asynchronously receive reply
	 * @param request  request to login to the system
	 */
	public void
	login(ISessionReplyReceiver reply,LoginRequest request);
	
	/************************************************************************
	 * Sends a {@code LogoutRequest} in order to logout of the system. 
	 *
	 * @param reply    callback to asynchronously receive reply
	 * @param request  request to logout of the system
	 */
	public void
	logout(ISessionReplyReceiver reply,LogoutRequest request);
	
	/************************************************************************
	 * Sends a {@code KeepAliveRequest} to maintain
	 * the specified session. 
	 *
	 * @param request
	 */
	public void
	keepAlive(KeepAliveRequest request);
}

// ##########################################################################

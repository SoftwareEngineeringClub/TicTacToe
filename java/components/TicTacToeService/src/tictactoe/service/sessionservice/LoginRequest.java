// ##########################################################################
// # File Name:	LoginRequest.java
// ##########################################################################

package tictactoe.service.sessionservice;

/****************************************************************************
 * 
 */
public 
class LoginRequest 
	extends SessionRequest
{
	private static final long   serialVersionUID = 8518679406286996581L;

	private final String itsUserName;
	private final String itsPassword;
	
	/************************************************************************
	 * Creates a new LoginRequest. 
	 *
	 */
	public 
	LoginRequest(String userName,String password)
	{
		itsUserName = userName;
		itsPassword = password;
	}

	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public String
	getUserName()
	{
		return itsUserName;
	}
	
	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public String
	getPassword()
	{
		return itsPassword;
	}
}

// ##########################################################################

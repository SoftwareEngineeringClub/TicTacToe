// ##########################################################################
// # File Name:	User.java
// ##########################################################################

package tictactoe.domain.sessiondomain;

/****************************************************************************
 * 
 */
public 
class User
{
	private Long    itsUserId;
	private Integer itsVersion;
	private String  itsUserName;
	private String  itsPassword;
	
	/************************************************************************
	 * Creates a new User. 
	 *
	 */
	public 
	User()
	{
		itsUserId  = 0L;
		itsVersion = 0;
	}
	
	/************************************************************************
	 * Creates a new User. 
	 *
	 * @param other
	 */
	public
	User(User other)
	{
	    itsUserId   = other.itsUserId;
	    itsVersion  = other.itsVersion;
	    itsUserName = other.itsUserName;
	    itsPassword = other.itsPassword;
	}
	
	/************************************************************************
	 *  
	 *
	 * @param userId
	 */
	public void
	setUserId(Long userId)
	{
		itsUserId = userId;
	}
	
	/************************************************************************
	 *  
	 *
	 * @param version
	 */
	public void
	setVersion(Integer version)
	{
	    itsVersion = version;
	}
	
	/************************************************************************
	 *  
	 *
	 * @param userName
	 */
	public void
	setUserName(String userName)
	{
		itsUserName = userName;
	}
	
	/************************************************************************
	 *  
	 *
	 * @param password
	 */
	public void
	setPassword(String password)
	{
		itsPassword = password;
	}
	
	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public Long
	getUserId()
	{
		return itsUserId;
	}
	
	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public Integer
	getVersion()
	{
	    return itsVersion;
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

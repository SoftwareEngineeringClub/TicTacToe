// ##########################################################################
// # File Name:	Session.java
// ##########################################################################

package tictactoe.domain.sessiondomain;

/****************************************************************************
 * 
 */
public 
class Session
{
	private Long    itsSessionId;
	private Integer itsVersion;
	private Long    itsUserId;
	
	/************************************************************************
	 * Creates a new Session. 
	 *
	 */
	public 
	Session()
	{
		itsSessionId = new Long(0);
		itsVersion   = new Integer(0);
		itsUserId    = new Long(0);
	}

	/************************************************************************
	 * Creates a new Session. 
	 *
	 * @param other
	 */
	public 
	Session(Session other)
	{
	    itsSessionId = new Long(other.itsSessionId);
	    itsVersion   = new Integer(other.itsVersion);
	    itsUserId    = new Long(other.itsUserId);
	}
	
	/************************************************************************
	 *  
	 *
	 * @param sessionId
	 */
	public void
	setSessionId(Long sessionId)
	{
		itsSessionId = sessionId;
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
	 * @return
	 */
	public Long 
	getSessionId()
	{
		return itsSessionId;
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
	public Long
	getUserId()
	{
		return itsUserId;
	}
}

// ##########################################################################

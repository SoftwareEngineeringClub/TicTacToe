// ##########################################################################
// # File Name:	Session.java
// ##########################################################################

package tictactoe.domain.sessiondomain;

import strata1.common.datetime.DateTime;

/****************************************************************************
 * 
 */
public 
class Session
{
	private Long     itsSessionId;
	private Integer  itsVersion;
	private Long     itsUserId;
	private String   itsSessionReturnAddress;
	private String   itsPlayerReturnAddress;
	private String   itsGameReturnAddress;
	private DateTime itsLastHeartbeat;
	
	/************************************************************************
	 * Creates a new Session. 
	 *
	 */
	public 
	Session()
	{
		itsSessionId            = new Long(0);
		itsVersion              = new Integer(0);
		itsUserId               = new Long(0);
        itsSessionReturnAddress = "";
        itsPlayerReturnAddress  = "";
		itsGameReturnAddress    = "";
		itsLastHeartbeat        = new DateTime();
	}

	/************************************************************************
	 * Creates a new Session. 
	 *
	 * @param other
	 */
	public 
	Session(Session other)
	{
	    itsSessionId            = other.itsSessionId;
	    itsVersion              = other.itsVersion;
	    itsUserId               = other.itsUserId;
        itsSessionReturnAddress = other.itsSessionReturnAddress;
        itsPlayerReturnAddress  = other.itsPlayerReturnAddress;
	    itsGameReturnAddress    = other.itsGameReturnAddress;
	    itsLastHeartbeat        = new DateTime(other.itsLastHeartbeat);
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
     * @param returnAddress
     */
    public void
    setSessionReturnAddress(String returnAddress)
    {
        itsSessionReturnAddress = returnAddress;
    }
    
    /************************************************************************
     *  
     *
     * @param returnAddress
     */
    public void
    setPlayerReturnAddress(String returnAddress)
    {
        itsPlayerReturnAddress = returnAddress;
    }
	
	/************************************************************************
	 *  
	 *
	 * @param returnAddress
	 */
	public void
	setGameReturnAddress(String returnAddress)
	{
	    itsGameReturnAddress = returnAddress;
	}
	
	/************************************************************************
	 *  
	 *
	 * @param lastHeartbeat
	 */
	public void
	setLastHeartbeat(DateTime lastHeartbeat)
	{
	    itsLastHeartbeat = lastHeartbeat;
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
    
    /************************************************************************
     *  
     *
     * @return
     */
    public String
    getSessionReturnAddress()
    {
        return itsSessionReturnAddress;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public String
    getPlayerReturnAddress()
    {
        return itsPlayerReturnAddress;
    }
	
	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public String
	getGameReturnAddress()
	{
	    return itsGameReturnAddress;
	}
	
	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public DateTime
	getLastHeartbeat()
	{
	    return itsLastHeartbeat;
	}
}

// ##########################################################################

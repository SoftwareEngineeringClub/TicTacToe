// ##########################################################################
// # File Name:	IssueChallengeRequest.java
// ##########################################################################

package tictactoe.service.playerservice;

/****************************************************************************
 * 
 */
public 
class IssueChallengeRequest 
	extends PlayerRequest
{

	private static final long serialVersionUID	= 1196302618233541350L;
	
	private Long itsChallengedUserId;

	/************************************************************************
	 * Creates a new IssueChallengeRequest. 
	 *
	 */
	public 
	IssueChallengeRequest(
	    final Long sessionId,
	    final Long userId)
	{
	    super( sessionId,userId );
	    itsChallengedUserId = 0L;
	}

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IssueChallengeRequest 
    setReturnAddress(String returnAddress)
    {
        super.setReturnAddress( returnAddress );
        return this;
    }

    /************************************************************************
     *  
     *
     * @param challengedUserId
     * @return
     */
    public IssueChallengeRequest
    setChallengedUserId(Long challengedUserId)
    {
        itsChallengedUserId = challengedUserId;
        return this;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public Long
    getChallengedUserId()
    {
        return itsChallengedUserId;
    }

}

// ##########################################################################

// ##########################################################################
// # File Name:	ChallengePlayerRequest.java
// ##########################################################################

package tictactoe.service.playerservice;

/****************************************************************************
 * 
 */
public 
class ChallengePlayerRequest 
	extends PlayerRequest
{

	private static final long serialVersionUID	= 1196302618233541350L;
	
	private Long itsChallengedUserId;

	/************************************************************************
	 * Creates a new ChallengePlayerRequest. 
	 *
	 */
	public 
	ChallengePlayerRequest(Long sessionId,Long userId)
	{
	    super( sessionId,userId );
	    itsChallengedUserId = 0L;
	}

    /************************************************************************
     *  
     *
     * @param challengedUserId
     * @return
     */
    public ChallengePlayerRequest
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

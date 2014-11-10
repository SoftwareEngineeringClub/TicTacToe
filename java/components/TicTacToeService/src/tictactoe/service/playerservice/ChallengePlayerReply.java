// ##########################################################################
// # File Name:	ChallengePlayerReply.java
// ##########################################################################

package tictactoe.service.playerservice;

/****************************************************************************
 * 
 */
public 
class ChallengePlayerReply 
	extends PlayerReply
{

	private static final long serialVersionUID	= -6974704368529876128L;
	
	private Long    itsChallengedUserId;
	private boolean itsAcceptedFlag;

	/************************************************************************
	 * Creates a new ChallengePlayerReply. 
	 *
	 */
	public 
	ChallengePlayerReply(ChallengePlayerRequest origRequest)
	{
	    super( origRequest.getRequestId() );
	    itsChallengedUserId = 0L;
	    itsAcceptedFlag = false;
	}

	/************************************************************************
	 *  
	 *
	 * @param challengedUserId
	 * @return
	 */
	public ChallengePlayerReply
	setChallengedUserId(Long challengedUserId)
	{
	    itsChallengedUserId = challengedUserId;
	    return this;
	}
	
	/************************************************************************
	 *  
	 *
	 * @param accepted
	 * @return
	 */
	public ChallengePlayerReply
	setChallengeAccepted(boolean accepted)
	{
	    itsAcceptedFlag = accepted;
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
	
	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public boolean
	isChallengeAccepted()
	{
	    return itsAcceptedFlag;
	}
}

// ##########################################################################

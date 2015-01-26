// ##########################################################################
// # File Name:	IssueChallengeReply.java
// ##########################################################################

package tictactoe.service.playerservice;

/****************************************************************************
 * 
 */
public 
class IssueChallengeReply 
	extends PlayerReply
{

	private static final long serialVersionUID	= -6974704368529876128L;
	
	private Long    itsChallengedUserId;
    private Long    itsGameId;
	private boolean itsAcceptedFlag;

	/************************************************************************
	 * Creates a new IssueChallengeReply. 
	 *
	 * @param request
	 */
	public 
	IssueChallengeReply(AcceptChallengeRequest request)
	{
	    super( request.getOriginatingRequestId(),null );
	    
	    itsChallengedUserId = request.getChallengedUserId();
	    itsGameId = 0L;
	    itsAcceptedFlag = true;
	}

    /************************************************************************
     * Creates a new IssueChallengeReply. 
     *
     * @param request
     */
    public 
    IssueChallengeReply(DeclineChallengeRequest request)
    {
        super( request.getOriginatingRequestId(),null );
        
        itsChallengedUserId = request.getChallengedUserId();
        itsGameId = 0L;
        itsAcceptedFlag = false;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IssueChallengeReply 
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
	public IssueChallengeReply
	setChallengedUserId(Long challengedUserId)
	{
	    itsChallengedUserId = challengedUserId;
	    return this;
	}
	
	/************************************************************************
	 *  
	 *
	 * @param gameId
	 */
	public void 
    setGameId(Long gameId)
    {
        itsGameId = gameId;
    }

    /************************************************************************
	 *  
	 *
	 * @param accepted
	 * @return
	 */
	public IssueChallengeReply
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
	public Long
	getGameId()
	{
	    return itsGameId;
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

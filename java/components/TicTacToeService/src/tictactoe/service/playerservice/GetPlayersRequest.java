// ##########################################################################
// # File Name:	GetPlayersRequest.java
// ##########################################################################

package tictactoe.service.playerservice;



/****************************************************************************
 * 
 */
public 
class GetPlayersRequest 
	extends PlayerRequest
{

	private static final long serialVersionUID	= -1966789538643597240L;
	
	private boolean itsSinglePlayerFlag;

	/************************************************************************
	 * Creates a new GetPlayersRequest. 
	 *
	 */
	public 
	GetPlayersRequest(
	    final Long sessionId,
	    final Long userId)
	{
	    super( sessionId,userId );
	    itsSinglePlayerFlag = false;
	}

	/************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public GetPlayersRequest 
    setReturnAddress(String returnAddress)
    {
        super.setReturnAddress( returnAddress );
        return this;
    }

    /************************************************************************
	 *  
	 *
	 * @param playerUserId
	 * @return
	 */
	public GetPlayersRequest
	setSinglePlayer(boolean singlePlayer)
	{
	    itsSinglePlayerFlag = singlePlayer;
	    return this;
	}
	
	
	/************************************************************************
	 *  
	 *
	 * @return
	 */
	public boolean
	isSinglePlayer()
	{
	    return itsSinglePlayerFlag;
	}
}

// ##########################################################################

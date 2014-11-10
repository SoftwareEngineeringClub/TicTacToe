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
	GetPlayersRequest(Long sessionId,Long userId)
	{
	    super( sessionId,userId );
	    itsSinglePlayerFlag = false;
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

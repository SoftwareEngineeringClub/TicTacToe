// ##########################################################################
// # File Name:	GetGameStateRequest.java
// ##########################################################################

package tictactoe.service.gameservice;

/****************************************************************************
 * 
 */
public 
class GetGameStateRequest 
	extends GameRequest
{
	private static final long serialVersionUID	= 5202528494522284304L;

	/************************************************************************
	 * Creates a new GetGameStateRequest. 
	 *
	 */
	public 
	GetGameStateRequest(
	    final long sessionId,
	    final long userId)
	{
	    super( sessionId,userId );
	}

}

// ##########################################################################

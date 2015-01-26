// ##########################################################################
// # File Name:	GetGameStateReply.java
// ##########################################################################

package tictactoe.service.gameservice;

/****************************************************************************
 * 
 */
public 
class GetGameStateReply 
	extends GameReply
{

	private static final long serialVersionUID	= 2660598304313845803L;

	/************************************************************************
	 * Creates a new GetGameStateReply. 
	 *
	 */
	public 
	GetGameStateReply(final GetGameStateRequest request)
	{
	    super( request.getRequestId(),request.getReturnAddress() );
	}

}

// ##########################################################################

// ##########################################################################
// # File Name:	IGameReplyReceiver.java
// ##########################################################################

package tictactoe.service.gameservice;

/****************************************************************************
 * 
 */
public 
interface IGameReplyReceiver
{
	public void
	onGetGameState(GameStateReply reply);
	
	public void
	onMakeMove(MakeMoveReply reply);
}

// ##########################################################################

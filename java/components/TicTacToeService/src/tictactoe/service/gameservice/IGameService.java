// ##########################################################################
// # File Name:	IGameService.java
// ##########################################################################

package tictactoe.service.gameservice;

/****************************************************************************
 * 
 */
public 
interface IGameService
{
	public void
	getGameState(IGameReplyReceiver reply,GetGameStateRequest request);
	
	public void
	makeMove(IGameReplyReceiver reply,MakeMoveRequest request);
}

// ##########################################################################

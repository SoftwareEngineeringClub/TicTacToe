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
    startGame(IGameReplyReceiver receiver,StartGameRequest request);
    
	public void
	getGameState(IGameReplyReceiver receiver,GetGameStateRequest request);
	
	public void
	makeMove(IGameReplyReceiver receiver,MakeMoveRequest request);
	
	public void
	startListening(
	    IGameReplyReceiver    receiver,
	    StartListeningRequest request);
}

// ##########################################################################

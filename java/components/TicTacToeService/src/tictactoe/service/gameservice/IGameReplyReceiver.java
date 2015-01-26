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
    onStartGame(StartGameReply reply);
    
	public void
	onGetGameState(GetGameStateReply reply);
	
	public void
	onMakeMove(MakeMoveReply reply);
	
	public void
	onStartListening(StartListeningReply reply);
	
	public void
	onGameException(GameException exception);
}

// ##########################################################################

// ##########################################################################
// # File Name:	IGameProcessor.java
// ##########################################################################

package tictactoe.application.gameapp;

import tictactoe.service.gameservice.GameUpdatedEvent;
import tictactoe.service.gameservice.GetGameStateReply;
import tictactoe.service.gameservice.GetGameStateRequest;
import tictactoe.service.gameservice.MakeMoveReply;
import tictactoe.service.gameservice.MakeMoveRequest;
import tictactoe.service.gameservice.StartGameReply;
import tictactoe.service.gameservice.StartGameRequest;
import tictactoe.service.gameservice.StartListeningReply;
import tictactoe.service.gameservice.StartListeningRequest;

import strata1.common.utility.Pair;

/****************************************************************************
 * 
 */
public 
interface IGameProcessor
{
    public StartGameReply
    startGame(StartGameRequest request);
    
    public GetGameStateReply
    getGameState(GetGameStateRequest request);
    
    public Pair<MakeMoveReply,GameUpdatedEvent>
    makeMove(MakeMoveRequest request);
    
    public StartListeningReply
    startListening(StartListeningRequest request);
}

// ##########################################################################

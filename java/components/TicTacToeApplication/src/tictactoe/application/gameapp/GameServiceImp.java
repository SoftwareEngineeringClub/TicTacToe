// ##########################################################################
// # File Name:	GameServiceImp.java
// ##########################################################################

package tictactoe.application.gameapp;

import tictactoe.service.gameservice.GameException;
import tictactoe.service.gameservice.GameUpdatedEvent;
import tictactoe.service.gameservice.GetGameStateReply;
import tictactoe.service.gameservice.GetGameStateRequest;
import tictactoe.service.gameservice.IGameEventListener;
import tictactoe.service.gameservice.IGameReplyReceiver;
import tictactoe.service.gameservice.IGameService;
import tictactoe.service.gameservice.MakeMoveReply;
import tictactoe.service.gameservice.MakeMoveRequest;
import tictactoe.service.gameservice.StartGameReply;
import tictactoe.service.gameservice.StartGameRequest;
import tictactoe.service.gameservice.StartListeningReply;
import tictactoe.service.gameservice.StartListeningRequest;

import strata1.common.logger.ILogger;
import strata1.common.utility.Pair;
import strata1.entity.repository.IRepositoryContext;
import strata1.entity.repository.IUnitOfWork;
import strata1.entity.repository.RepositoryException;
import strata1.entity.repository.RollbackFailedException;

import javax.inject.Inject;

import java.io.PrintWriter;
import java.io.StringWriter;

/****************************************************************************
 * 
 */
public 
class GameServiceImp 
    implements IGameService
{
    private final IRepositoryContext itsContext;
    private final IGameProcessor     itsProcessor;
    private final IGameEventListener itsListener;
    private final ILogger            itsLogger;

    /************************************************************************
     * Creates a new GameServiceImp. 
     *
     */
    @Inject
    public 
    GameServiceImp(
        IRepositoryContext context,
        IGameProcessor     processor,
        IGameEventListener listener,
        ILogger            logger)
    {
        itsContext   = context;
        itsProcessor = processor;
        itsListener  = listener;
        itsLogger    = logger;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    startGame(IGameReplyReceiver receiver,StartGameRequest request)
    {
        IUnitOfWork unitOfWork = itsContext.getUnitOfWork();
        
        try
        {
            StartGameReply reply = itsProcessor.startGame( request );
            
            itsLogger.logDebug( "Committing start game request." );
            unitOfWork.commit();
            itsLogger.logDebug( "Committed start game request." );
            receiver.onStartGame( reply );
        }
        catch (RepositoryException e1)
        {
            itsLogger.logError( e1.getMessage() );
            receiver.onGameException( 
                new GameException( request,e1.getMessage() ) );           
            rollback( unitOfWork );
        }
        catch (Throwable t)
        {
            StringWriter writer = new StringWriter();
            PrintWriter  output = new PrintWriter( writer );
            
            t.printStackTrace( output );
            itsLogger.logError( writer.toString() );
            receiver.onGameException( 
                new GameException( request,writer.toString() ) );
        }
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    getGameState(
        IGameReplyReceiver  receiver,
        GetGameStateRequest request)
    {
        IUnitOfWork unitOfWork = itsContext.getUnitOfWork();
        
        try
        {
            GetGameStateReply reply = itsProcessor.getGameState( request );
            
            itsLogger.logDebug( "Committing get game state request." );
            unitOfWork.commit();
            itsLogger.logDebug( "Committed get game state request." );
            receiver.onGetGameState( reply );
        }
        catch (RepositoryException e1)
        {
            itsLogger.logError( e1.getMessage() );
            receiver.onGameException( 
                new GameException( request,e1.getMessage() ) );           
            rollback( unitOfWork );
        }
        catch (Throwable t)
        {
            StringWriter writer = new StringWriter();
            PrintWriter  output = new PrintWriter( writer );
            
            t.printStackTrace( output );
            itsLogger.logError( writer.toString() );
            receiver.onGameException( 
                new GameException( request,writer.toString() ) );
        }
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    makeMove(IGameReplyReceiver receiver,MakeMoveRequest request)
    {
        IUnitOfWork unitOfWork = itsContext.getUnitOfWork();
        
        try
        {
            Pair<MakeMoveReply,GameUpdatedEvent> pair = 
                itsProcessor.makeMove( request );
            
            itsLogger.logDebug( "Committing make move request." );
            unitOfWork.commit();
            itsLogger.logDebug( "Committed make move request." );
            receiver.onMakeMove( pair.getFirst() );
            itsListener.onGameUpdated( pair.getSecond() );
        }
        catch (RepositoryException e1)
        {
            itsLogger.logError( e1.getMessage() );
            receiver.onGameException( 
                new GameException( request,e1.getMessage() ) );           
            rollback( unitOfWork );
        }
        catch (Throwable t)
        {
            StringWriter writer = new StringWriter();
            PrintWriter  output = new PrintWriter( writer );
            
            t.printStackTrace( output );
            itsLogger.logError( writer.toString() );
            receiver.onGameException( 
                new GameException( request,writer.toString() ) );
        }
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    startListening(
        IGameReplyReceiver    receiver,
        StartListeningRequest request)
    {
        IUnitOfWork unitOfWork = itsContext.getUnitOfWork();
        
        try
        {
            StartListeningReply reply = itsProcessor.startListening( request );
            
            itsLogger.logDebug( "Committing start listening request." );
            unitOfWork.commit();
            itsLogger.logDebug( "Committed start listening request." );
            receiver.onStartListening( reply );
        }
        catch (RepositoryException e1)
        {
            itsLogger.logError( e1.getMessage() );
            receiver.onGameException( 
                new GameException( request,e1.getMessage() ) );           
            rollback( unitOfWork );
        }
        catch (Throwable t)
        {
            StringWriter writer = new StringWriter();
            PrintWriter  output = new PrintWriter( writer );
            
            t.printStackTrace( output );
            itsLogger.logError( writer.toString() );
            receiver.onGameException( 
                new GameException(request,writer.toString()) );
        }
    }

    /************************************************************************
     *  
     *
     * @param unitOfWork
     */
    private void 
    rollback(IUnitOfWork unitOfWork)
    {
        try
        {
            unitOfWork.rollback();
        }
        catch (RollbackFailedException e2)
        {
            itsLogger.logError( e2.getMessage() );
        }
    }

    /************************************************************************
     *  
     *
     * @param t
     * @return
     */
    private String 
    getStackTrace(Throwable t)
    {
        StringWriter stringWriter = new StringWriter();
        PrintWriter  printWriter  = new PrintWriter(stringWriter);
        
        t.printStackTrace( printWriter );
        return stringWriter.toString();
    }

}

// ##########################################################################

// ##########################################################################
// # File Name:	PlayerServiceImp.java
// ##########################################################################

package tictactoe.application.playerapp;

import tictactoe.service.playerservice.ChallengePlayerReply;
import tictactoe.service.playerservice.ChallengePlayerRequest;
import tictactoe.service.playerservice.PlayerEventListenerId;
import tictactoe.service.playerservice.GetPlayersReply;
import tictactoe.service.playerservice.GetPlayersRequest;
import tictactoe.service.playerservice.IPlayerEventListener;
import tictactoe.service.playerservice.IPlayerReplyReceiver;
import tictactoe.service.playerservice.IPlayerService;
import tictactoe.service.playerservice.PlayerException;

import strata1.common.logger.ILogger;
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
class PlayerServiceImp 
    implements IPlayerService
{
    private final IRepositoryContext itsContext;
    private final IPlayerProcessor   itsProcessor;
    private final ILogger            itsLogger;

    /************************************************************************
     * Creates a new PlayerServiceImp. 
     *
     */
    @Inject
    public 
    PlayerServiceImp(
        IRepositoryContext context,
        IPlayerProcessor   processor,
        ILogger            logger)
    {
        itsContext   = context;
        itsProcessor = processor;
        itsLogger    = logger;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    getPlayers(IPlayerReplyReceiver receiver,GetPlayersRequest request)
    {
        IUnitOfWork unitOfWork = itsContext.getUnitOfWork();
        
        try
        {
            GetPlayersReply reply = itsProcessor.getPlayers( request );
            
            itsLogger.logDebug( "Committing get players request." );
            unitOfWork.commit();
            itsLogger.logDebug( "Committed get players request." );
            receiver.onGetPlayers( reply );
        }
        catch (RepositoryException e1)
        {
            itsLogger.logError( e1.getMessage() );
            receiver.onPlayerException( new PlayerException( e1.getMessage() ) );           
            rollback( unitOfWork );
        }
        catch (Throwable t)
        {
            StringWriter writer = new StringWriter();
            PrintWriter  output = new PrintWriter( writer );
            
            t.printStackTrace( output );
            itsLogger.logError( writer.toString() );
            receiver.onThrowable( new Exception(t.getMessage()) );
        }
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    challengePlayer(
        IPlayerReplyReceiver   receiver,
        ChallengePlayerRequest request)
    {
        IUnitOfWork unitOfWork = itsContext.getUnitOfWork();
        
        try
        {
            ChallengePlayerReply reply = itsProcessor.challengePlayer( request );
            
            itsLogger.logDebug( "Committing challenge player request." );
            unitOfWork.commit();
            itsLogger.logDebug( "Committed challenge player request." );
            receiver.onChallengePlayer( reply );
        }
        catch (RepositoryException e1)
        {
            itsLogger.logError( getStackTrace(e1) );
            receiver.onPlayerException( 
                new PlayerException( getStackTrace(e1) ) );           
            rollback( unitOfWork );
        }
        catch (Throwable t)
        {
            itsLogger.logError( getStackTrace(t) );
            receiver.onThrowable( new Exception(getStackTrace(t)) );
        }
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public PlayerEventListenerId 
    startListeningForEvents(IPlayerEventListener listener)
    {
        return null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    stopListeningForEvents(PlayerEventListenerId listenerId)
    {
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

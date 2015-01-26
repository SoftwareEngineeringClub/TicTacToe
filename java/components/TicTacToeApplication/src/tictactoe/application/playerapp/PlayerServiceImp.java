// ##########################################################################
// # File Name:	PlayerServiceImp.java
// ##########################################################################

package tictactoe.application.playerapp;

import tictactoe.service.playerservice.AcceptChallengeReply;
import tictactoe.service.playerservice.AcceptChallengeRequest;
import tictactoe.service.playerservice.ChallengeEvent;
import tictactoe.service.playerservice.DeclineChallengeReply;
import tictactoe.service.playerservice.DeclineChallengeRequest;
import tictactoe.service.playerservice.IPlayerNotifierHost;
import tictactoe.service.playerservice.IssueChallengeReply;
import tictactoe.service.playerservice.IssueChallengeRequest;
import tictactoe.service.playerservice.PlayerEventListenerId;
import tictactoe.service.playerservice.GetPlayersReply;
import tictactoe.service.playerservice.GetPlayersRequest;
import tictactoe.service.playerservice.IPlayerEventListener;
import tictactoe.service.playerservice.IPlayerReplyReceiver;
import tictactoe.service.playerservice.IPlayerService;
import tictactoe.service.playerservice.PlayerException;
import tictactoe.service.playerservice.StartListeningReply;
import tictactoe.service.playerservice.StartListeningRequest;

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
class PlayerServiceImp 
    implements IPlayerService,IPlayerNotifierHost
{
    private final IRepositoryContext itsContext;
    private final IPlayerProcessor   itsProcessor;
    private final ILogger            itsLogger;
    private IPlayerEventListener     itsNotifier;

    /************************************************************************
     * Creates a new PlayerServiceImp. 
     *
     */
    @Inject
    public 
    PlayerServiceImp(
        IRepositoryContext   context,
        IPlayerProcessor     processor,
        ILogger              logger)
    {
        itsContext   = context;
        itsProcessor = processor;
        itsLogger    = logger;
        itsNotifier  = null;
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
            receiver.onPlayerException( 
                new PlayerException( request,e1.getMessage() ) );           
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
    issueChallenge(
        IPlayerReplyReceiver  receiver,
        IssueChallengeRequest request)
    {
        IUnitOfWork unitOfWork = itsContext.getUnitOfWork();
        
        try
        {
            ChallengeEvent event = itsProcessor.issueChallenge( request );
            
            itsLogger.logDebug( "Committing issue challenge request." );
            unitOfWork.commit();
            itsLogger.logDebug( "Committed issue challenge request." );
            itsNotifier.onChallenge( event );
        }
        catch (RepositoryException e1)
        {
            itsLogger.logError( getStackTrace(e1) );
            receiver.onPlayerException( 
                new PlayerException( request,getStackTrace(e1) ) );           
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
    public void 
    acceptChallenge(
        IPlayerReplyReceiver   receiver,
        AcceptChallengeRequest request)
    {
        IUnitOfWork unitOfWork = itsContext.getUnitOfWork();
        
        try
        {
            Pair<IssueChallengeReply,AcceptChallengeReply> pair = 
                itsProcessor.acceptChallenge( request );
            
            itsLogger.logDebug( "Committing accept challenge request." );
            unitOfWork.commit();
            itsLogger.logDebug( "Committed accept challenge request." );
            receiver.onIssueChallenge( pair.getFirst() );
            receiver.onAcceptChallenge( pair.getSecond() );
        }
        catch (RepositoryException e1)
        {
            itsLogger.logError( getStackTrace(e1) );
            receiver.onPlayerException( 
                new PlayerException( request,getStackTrace(e1) ) );           
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
    public void 
    declineChallenge(
        IPlayerReplyReceiver    receiver,
        DeclineChallengeRequest request)
    {
        IUnitOfWork unitOfWork = itsContext.getUnitOfWork();
        
        try
        {
            Pair<IssueChallengeReply,DeclineChallengeReply> pair = 
                itsProcessor.declineChallenge( request );
            
            itsLogger.logDebug( "Committing decline challenge request." );
            unitOfWork.commit();
            itsLogger.logDebug( "Committed decline challenge request." );
            receiver.onIssueChallenge( pair.getFirst() );
            receiver.onDeclineChallenge( pair.getSecond() );
        }
        catch (RepositoryException e1)
        {
            itsLogger.logError( getStackTrace(e1) );
            receiver.onPlayerException( 
                new PlayerException( request,getStackTrace(e1) ) );           
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
    public void 
    startListening(IPlayerReplyReceiver receiver,StartListeningRequest request)
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
            receiver.onPlayerException( 
                new PlayerException( request,e1.getMessage() ) );           
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
    stopListeningForEvents(PlayerEventListenerId listenerId)
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    setPlayerNotifier(IPlayerEventListener notifier)
    {
        itsNotifier = notifier;
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

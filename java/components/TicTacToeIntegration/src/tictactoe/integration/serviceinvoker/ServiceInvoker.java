// ##########################################################################
// # File Name:	SessionInvoker.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import tictactoe.service.playerservice.ChallengePlayerRequest;
import tictactoe.service.playerservice.GetPlayersRequest;
import tictactoe.service.playerservice.IPlayerReplyReceiver;
import tictactoe.service.playerservice.PlayerRequest;
import tictactoe.service.sessionservice.ISessionReplyReceiver;
import tictactoe.service.sessionservice.LoginRequest;
import tictactoe.service.sessionservice.LogoutRequest;
import tictactoe.service.sessionservice.RegisterRequest;
import tictactoe.service.sessionservice.SessionRequest;

import strata1.common.logger.ILogger;
import strata1.common.task.AbstractTaskProducer;
import strata1.common.task.ITaskDispatcher;
import strata1.common.task.ITaskProducer;
import strata1.injector.container.IContainer;
import strata1.integrator.messaging.IBytesMessage;
import strata1.integrator.messaging.IMapMessage;
import strata1.integrator.messaging.IMessageListener;
import strata1.integrator.messaging.IMessageReceiver;
import strata1.integrator.messaging.IMessagingSession;
import strata1.integrator.messaging.IObjectMessage;
import strata1.integrator.messaging.IStringMessage;
import strata1.integrator.messaging.MixedModeException;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class ServiceInvoker 
    extends    AbstractTaskProducer 
    implements ITaskProducer,IMessageListener
{    
    private final IContainer        itsContainer;
    private final String            itsRequestChannel;
    private final String            itsReplyChannel;
    private final IMessagingSession itsSession;
    private final ILogger           itsLogger;
    private IMessageReceiver        itsReceiver;
    
    /************************************************************************
     * Creates a new SessionInvoker. 
     *
     */
    public 
    ServiceInvoker(
        IContainer container,
        String     session,
        String     requestChannelId,
        String     replyChannelId)
    {        
        itsContainer = container;
        itsRequestChannel = 
            itsContainer.getInstance( String.class,requestChannelId );
        itsReplyChannel = 
            itsContainer.getInstance( String.class,replyChannelId );
        itsSession  = 
            itsContainer.getInstance( IMessagingSession.class,session );
        itsLogger = 
            itsContainer.getInstance(  ILogger.class );
        itsReceiver = null;
        
        setDispatcher(itsContainer.getInstance(ITaskDispatcher.class));
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    startProducing()
    {        
        try
        {
            if ( itsReceiver == null )
            {
                itsReceiver = 
                    itsSession.createMessageReceiver( itsRequestChannel );
                itsReceiver.setListener( this );
            }
            
            itsReceiver.startListening();
        }
        catch (MixedModeException e)
        {
            throw new IllegalStateException( e );
        }
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    stopProducing()
    {
        if ( itsReceiver != null ) 
        {
            try
            {
                itsReceiver.stopListening();
                itsReceiver.close();
                itsReceiver = null;
            }
            catch (MixedModeException e)
            {
                throw new IllegalStateException( e );
            }
        }
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public boolean 
    isProducing()
    {
        return itsReceiver.isListening();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onMessage(IStringMessage message)
    {
        throw new UnsupportedOperationException();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onMessage(IMapMessage message)
    {
        throw new UnsupportedOperationException();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onMessage(IObjectMessage message)
    {
        try
        {
            Object payload   = message.getPayload();
            
            if ( payload instanceof SessionRequest ) 
                dispatchSessionRequest( message,payload );
            else if ( payload instanceof PlayerRequest )
                dispatchPlayerRequest( message,payload );
            else
            {
                itsLogger.logError( 
                    "Unknown payload type: "  + 
                    payload.getClass().getSimpleName() );
            }               
        }
        catch (Exception e)
        {
            itsLogger.logError( e.getMessage() );
        }
    }

    /************************************************************************
     *  
     *
     * @param message
     * @param payload
     */
    private void 
    dispatchSessionRequest(IObjectMessage message,Object payload)
    {
        Long   requestId = ((SessionRequest)payload).getRequestId();
        ISessionReplyReceiver receiver = 
            new SessionReplyReceiver(
                itsSession,
                itsReplyChannel,
                message.getReturnAddress(),
                message.getCorrelationId(),
                itsLogger); 
        
        itsLogger.logInfo( 
            "Receiving message: " + message.getMessageId() );
        
        if ( payload instanceof RegisterRequest )
        {
            itsLogger.logInfo( 
                "Receiving register request: " + requestId );
            getDispatcher()
                .route(
                    new RegisterTask(
                        itsContainer,
                        (RegisterRequest)payload,
                        receiver ) );
        }
        else if ( payload instanceof LoginRequest )
        {
            itsLogger.logInfo( "Receiving login request: " + requestId );
            getDispatcher()
                .route(
                    new LoginTask(
                        itsContainer,
                        (LoginRequest)payload,
                        receiver ) );
        }
        else if ( payload instanceof LogoutRequest )
        {
            itsLogger.logInfo( "Receiving logout request: " + requestId );
            getDispatcher()
                .route(
                    new LogoutTask(
                        itsContainer,
                        (LogoutRequest)payload,
                        receiver ) );
        }
        else
        {
            itsLogger.logError( 
                "Unknown payload type: "  + 
                payload.getClass().getSimpleName() );
        }
    }
    
    /************************************************************************
     *  
     *
     * @param message
     * @param payload
     */
    private void 
    dispatchPlayerRequest(IObjectMessage message,Object payload)
    {
        Long   requestId = ((PlayerRequest)payload).getRequestId();
        IPlayerReplyReceiver receiver = 
            new PlayerReplyReceiver(
                itsSession,
                itsReplyChannel,
                message.getReturnAddress(),
                message.getCorrelationId(),
                itsLogger); 
        
        itsLogger.logInfo( 
            "Receiving message: " + message.getMessageId() );
        
        if ( payload instanceof GetPlayersRequest )
        {
            itsLogger.logInfo( 
                "Receiving get players request: " + requestId );
            getDispatcher()
                .route(
                    new GetPlayersTask(
                        itsContainer,
                        (GetPlayersRequest)payload,
                        receiver ) );
        }
        else if ( payload instanceof ChallengePlayerRequest )
        {
            itsLogger.logInfo( "Receiving challenge player request: " + requestId );
            getDispatcher()
                .route(
                    new ChallengePlayerTask(
                        itsContainer,
                        (ChallengePlayerRequest)payload,
                        receiver ) );
        }
        else
        {
            itsLogger.logError( 
                "Unknown payload type: "  + 
                payload.getClass().getSimpleName() );
        }
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    onMessage(IBytesMessage message)
    {
        throw new UnsupportedOperationException();
    }

}

// ##########################################################################

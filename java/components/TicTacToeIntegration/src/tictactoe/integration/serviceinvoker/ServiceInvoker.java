// ##########################################################################
// # File Name:	SessionInvoker.java
// ##########################################################################

package tictactoe.integration.serviceinvoker;

import tictactoe.service.sessionservice.LoginRequest;
import tictactoe.service.sessionservice.LogoutRequest;
import tictactoe.service.sessionservice.RegisterRequest;

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
    private final IMessagingSession itsSession;
    private IMessageReceiver        itsReceiver;
    private final String            itsRequestChannel;
    
    /************************************************************************
     * Creates a new SessionInvoker. 
     *
     */
    @Inject
    public 
    ServiceInvoker(IContainer container)
    {
        
        itsContainer = container;
        
        itsRequestChannel = 
            itsContainer.getInstance( String.class,"RequestChannel" );
        
        itsSession  = itsContainer.getInstance( IMessagingSession.class );
        itsReceiver = null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    startProducing()
    {
        if ( itsReceiver == null )
            itsReceiver = 
                itsSession.createMessageReceiver( itsRequestChannel );
        
        try
        {
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
        Object payload = message.getPayload();
        
        if ( payload instanceof RegisterRequest )
            getDispatcher()
                .route(
                    new RegisterTask(
                        itsContainer,
                        (RegisterRequest)payload));
        else if ( payload instanceof LoginRequest )
            getDispatcher()
                .route(
                    new LoginTask(
                        itsContainer,
                        (LoginRequest)payload));
        else if ( payload instanceof LogoutRequest )
            getDispatcher()
                .route(
                    new LogoutTask(
                        itsContainer,
                        (LogoutRequest)payload));
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

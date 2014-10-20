// ##########################################################################
// # File Name:	MessagingModule.java
// ##########################################################################

package tictactoe.integration.messaging;

import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.integrator.messaging.IMessagingSession;

/****************************************************************************
 * 
 */
public abstract 
class MessagingModule 
extends AbstractModule
{

    /************************************************************************
     * Creates a new MessagingModule. 
     *
     * @param name
     */
    public 
    MessagingModule(String name)
    {
        super( name );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    initialize(IContainer container)
    {
        container
            .insertBinding( 
                bindType(IMessagingSession.class)
                    .withKey( "MessagingSession1a" )
                    .toInstance(createMessagingSession1a()))
            .insertBinding( 
                bindType(IMessagingSession.class)
                    .withKey( "MessagingSession1b" )
                    .toInstance(createMessagingSession1b()))
            .insertBinding( 
                bindType(IMessagingSession.class)
                    .withKey( "MessagingSession2a" )
                    .toInstance(createMessagingSession2a()))
            .insertBinding( 
                bindType(IMessagingSession.class)
                    .withKey( "MessagingSession2b" )
                    .toInstance(createMessagingSession2b()));
    }

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract IMessagingSession 
    createMessagingSession1a();

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract IMessagingSession 
    createMessagingSession1b();

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract IMessagingSession 
    createMessagingSession2a();

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract IMessagingSession 
    createMessagingSession2b();

}

// ##########################################################################

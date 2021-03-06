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
                    .withKey( "CommandSession1" )
                    .toInstance(createCommandSession1()))
            .insertBinding( 
                bindType(IMessagingSession.class)
                    .withKey( "EventSession1" )
                    .toInstance(createEventSession1()))
            .insertBinding( 
                bindType(IMessagingSession.class)
                    .withKey( "CommandSession2" )
                    .toInstance(createCommandSession2()))
            .insertBinding( 
                bindType(IMessagingSession.class)
                    .withKey( "EventSession2" )
                    .toInstance(createEventSession2()));
    }

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract IMessagingSession 
    createCommandSession1();

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract IMessagingSession 
    createEventSession1();

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract IMessagingSession 
    createCommandSession2();

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract IMessagingSession 
    createEventSession2();

}

// ##########################################################################

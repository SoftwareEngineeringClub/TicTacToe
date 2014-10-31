// ##########################################################################
// # File Name:	MessagingModule.java
// ##########################################################################

package tictactoe.desktopplatform.jmsmessaging;

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
                    .withKey( "CommandSession" )
                    .toInstance(createCommandSession()))
            .insertBinding( 
                bindType(IMessagingSession.class)
                    .withKey( "EventSession" )
                    .toInstance(createEventSession()));
    }

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract IMessagingSession 
    createCommandSession();

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract IMessagingSession 
    createEventSession();

}

// ##########################################################################

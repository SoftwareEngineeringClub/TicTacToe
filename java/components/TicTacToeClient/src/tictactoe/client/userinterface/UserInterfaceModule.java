// ##########################################################################
// # File Name:	UserInterfaceModule.java
// ##########################################################################

package tictactoe.client.userinterface;

import strata1.client.shell.IDispatcher;
import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.injector.container.SingletonScope;

/****************************************************************************
 * 
 */
public abstract 
class UserInterfaceModule 
    extends AbstractModule
{

    /************************************************************************
     * Creates a new UserInterfaceModule. 
     *
     * @param name
     */
    public 
    UserInterfaceModule(String name)
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
                bindType(IDispatcher.class)
                    .toType( getDispatcherType() )
                    .withScope( new SingletonScope<IDispatcher>() ) );
    }

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract Class<? extends IDispatcher>
    getDispatcherType();
}

// ##########################################################################

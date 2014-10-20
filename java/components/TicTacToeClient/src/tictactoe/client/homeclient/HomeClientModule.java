// ##########################################################################
// # File Name:	GameClientModule.java
// ##########################################################################

package tictactoe.client.homeclient;

import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.injector.container.SingletonScope;

/****************************************************************************
 * 
 */
public abstract 
class HomeClientModule 
    extends AbstractModule
{

    /************************************************************************
     * Creates a new GameClientModule. 
     *
     * @param name
     */
    public 
    HomeClientModule(String name)
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
                bindType(IHomeController.class)
                    .toType(getHomeControllerType())
                    .withScope(new SingletonScope<IHomeController>()));
    }

    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract Class<? extends IHomeController>
    getHomeControllerType();
}

// ##########################################################################

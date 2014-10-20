// ##########################################################################
// # File Name:	MainClientModule.java
// ##########################################################################

package tictactoe.client.mainclient;

import strata1.injector.container.AbstractModule;
import strata1.injector.container.IContainer;
import strata1.injector.container.SingletonScope;

/****************************************************************************
 * 
 */
public abstract 
class MainClientModule 
    extends AbstractModule
{

    /************************************************************************
     * Creates a new MainClientModule. 
     *
     * @param name
     */
    public 
    MainClientModule(String name)
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
                bindType(IMainView.class)
                    .toType(getMainViewType()) 
                    .withScope(new SingletonScope<IMainView>()))
            .insertBinding( 
                bindType(IMainModel.class)
                    .toType(getMainModelType()) 
                    .withScope(new SingletonScope<IMainModel>()))
            .insertBinding( 
                bindType(IMainController.class)
                    .toType(MainController.class) 
                    .withScope(new SingletonScope<IMainController>()))
            .insertBinding( 
                bindType(ISessionView.class)
                    .toType(getSessionViewType()) 
                    .withScope(new SingletonScope<ISessionView>()))
            .insertBinding( 
                bindType(ISessionModel.class)
                    .toType(getSessionModelType()) 
                    .withScope(new SingletonScope<ISessionModel>()))
            .insertBinding( 
                bindType(ISessionController.class)
                    .toType(SessionController.class) 
                    .withScope(new SingletonScope<ISessionController>()));
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract Class<? extends ISessionView>
    getSessionViewType();
    
    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract Class<? extends ISessionModel>
    getSessionModelType();
    
    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract Class<? extends IMainView>
    getMainViewType();
    
    /************************************************************************
     *  
     *
     * @return
     */
    protected abstract Class<? extends IMainModel>
    getMainModelType();
}

// ##########################################################################

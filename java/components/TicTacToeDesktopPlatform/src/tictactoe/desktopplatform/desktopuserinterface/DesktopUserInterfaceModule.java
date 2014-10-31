// ##########################################################################
// # File Name:	DesktopUserInterfaceModule.java
// ##########################################################################

package tictactoe.desktopplatform.desktopuserinterface;

import tictactoe.client.userinterface.UserInterfaceModule;

import strata1.client.shell.IDispatcher;
import strata1.swtclient.swtshell.SwtDispatcher;

/****************************************************************************
 * 
 */
public 
class DesktopUserInterfaceModule 
    extends UserInterfaceModule
{

    /************************************************************************
     * Creates a new DesktopUserInterfaceModule. 
     *
     */
    public 
    DesktopUserInterfaceModule()
    {
        super( "DesktopUserInterfaceModule" );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    protected Class<? extends IDispatcher> 
    getDispatcherType()
    {
        return SwtDispatcher.class;
    }

}

// ##########################################################################

// ##########################################################################
// # File Name:	TicTacToeServerHttpServletHost.java
// ##########################################################################

package tictactoe.server.main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/****************************************************************************
 * 
 */
public 
class TicTacToeServerHttpServletHost extends HttpServlet
{

    private static final long serialVersionUID = -3501868107813274459L;
    
    private TicTacToeServerApp itsApplication;

    /************************************************************************
     * Creates a new TicTacToeServerHttpServletHost. 
     *
     */
    public 
    TicTacToeServerHttpServletHost()
    {
        itsApplication = null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    init() 
        throws ServletException
    {
        super.init();
        
        try
        {
            itsApplication = new TicTacToeServerApp();
            itsApplication.start();
        }
        catch (Exception e)
        {
            throw new ServletException( e );
        }
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    destroy()
    {
        try
        {
            itsApplication.stop();
            super.destroy();
        }
        catch (Exception e)
        {
            e.printStackTrace( System.err );
        }
    }

}

// ##########################################################################

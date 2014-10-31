// ##########################################################################
// # File Name:	SwtMainView.java
// ##########################################################################

package tictactoe.desktopplatform.maindesktop;

import tictactoe.client.mainclient.IMainView;
import tictactoe.client.mainclient.MainTabKind;

import strata1.client.command.ExecutionException;
import strata1.client.shell.IDispatcher;
import strata1.client.view.AbstractView;
import strata1.swtclient.swtshell.ISwtDispatcher;
import strata1.swtclient.swtview.ISwtView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.graphics.Point;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class SwtMainView 
    extends    AbstractView 
    implements IMainView,ISwtView
{
    private ISwtDispatcher itsDispatcher;
    private Shell          itsShell;
    private TabFolder      itsTabFolder;
    private TabItem        itsFileTab;
    private TabItem        itsHomeTab;
    private TabItem        itsPlayersTab;
    private TabItem        itsGameTab;
    private TabItem        itsHelpTab;

    /************************************************************************
     * Creates a new SwtMainView. 
     *
     */
    @Inject
    public 
    SwtMainView(IDispatcher dispatcher)
    {
        itsDispatcher = (ISwtDispatcher)dispatcher;
        
        itsShell = new Shell( itsDispatcher.getDisplay() );
        itsShell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsShell.setMinimumSize(new Point(770, 300));
        itsShell.setSize(new Point(770, 300));
        itsShell.setImage(SWTResourceManager.getImage("C:\\Users\\John\\Pictures\\tictactoe.png"));
        itsShell.addShellListener(
            new ShellAdapter() 
            {
                @Override
                public void 
                shellClosed(ShellEvent arg0) 
                {
                    System.exit( 0 );
                }
            });
        itsShell.setText("Tic Tac Toe");
        itsShell.setSize(755, 427);
        
        Monitor primary = itsDispatcher.getDisplay().getPrimaryMonitor();
        Rectangle bounds = primary.getBounds();
        Rectangle rect = itsShell.getBounds();
        int x = bounds.x + (bounds.width - rect.width) / 2;
        int y = bounds.y + (bounds.height - rect.height) / 2;
        itsShell.setLocation(x, y);
        RowLayout rl_itsShell = new RowLayout(SWT.HORIZONTAL);
        itsShell.setLayout(rl_itsShell);
        
        itsTabFolder = new TabFolder(itsShell, SWT.NONE);
        itsTabFolder.setLayoutData(new RowData(740, 355));
        itsTabFolder.setBackgroundMode(SWT.INHERIT_DEFAULT);
        itsTabFolder.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
        itsTabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        
        itsFileTab = new TabItem(itsTabFolder, SWT.NONE);
        itsFileTab.setImage(SWTResourceManager.getImage(SwtMainView.class, "/resources/file-icon-small.png"));
        itsFileTab.setToolTipText("Login, Logout, Exit");
        itsFileTab.setText("File");
        
        itsHomeTab = new TabItem(itsTabFolder, SWT.NONE);
        itsHomeTab.setImage(SWTResourceManager.getImage(SwtMainView.class, "/resources/home-icon-small.png"));
        itsHomeTab.setText("Home");
        
        itsPlayersTab = new TabItem(itsTabFolder, SWT.NONE);
        itsPlayersTab.setImage(SWTResourceManager.getImage(SwtMainView.class, "/resources/player-icon-small.png"));
        itsPlayersTab.setText("Players");
        
        itsGameTab = new TabItem(itsTabFolder, SWT.NONE);
        itsGameTab.setImage(SWTResourceManager.getImage(SwtMainView.class, "/resources/game-icon-small.png"));
        itsGameTab.setText("Game");
                
        itsHelpTab = new TabItem(itsTabFolder, SWT.NONE);
        itsHelpTab.setImage(SWTResourceManager.getImage(SwtMainView.class, "/resources/help-icon-small.png"));
        itsHelpTab.setText("Help");      
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    hide()
    {
        itsTabFolder.setVisible( false );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    show()
    {
        itsTabFolder.setVisible( true );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    start()
    {
        itsShell.open();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    stop()
    {
        itsShell.close();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Composite 
    getComposite()
    {
        return itsTabFolder;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Widget 
    getWidget()
    {
        return getComposite();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IMainView 
    setActiveTab(MainTabKind activeTab)
    {
        switch ( activeTab )
        {
        case FILE_TAB:
            itsTabFolder.setSelection( itsFileTab );
            break;
            
        case HOME_TAB:
            itsTabFolder.setSelection( itsHomeTab );
            break;
            
        case PLAYERS_TAB:
            itsTabFolder.setSelection( itsPlayersTab );
            break;
            
        case GAME_TAB:
            itsTabFolder.setSelection( itsGameTab );
            break;
            
        case HELP_TAB:
            itsTabFolder.setSelection( itsHelpTab );
            break;
        }
        
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public MainTabKind 
    getActiveTab()
    {
        switch ( itsTabFolder.getSelectionIndex() )
        {
        case 0: 
            return MainTabKind.FILE_TAB;
            
        case 1:
            return MainTabKind.HOME_TAB;
            
        case 2:
            return MainTabKind.PLAYERS_TAB;
            
        case 3:
            return MainTabKind.GAME_TAB;
            
        default:
            return MainTabKind.HELP_TAB;
        }
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public TabFolder
    getTabFolder()
    {
        return itsTabFolder;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public TabItem
    getFileTab()
    {
        return itsFileTab;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public TabItem
    getHomeTab()
    {
        return itsHomeTab;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public TabItem
    getPlayersTab()
    {
        return itsPlayersTab;
    }
    
    /************************************************************************
     *  
     *
     * @return
     */
    public TabItem
    getGameTab()
    {
        return itsGameTab;
    }
}

// ##########################################################################

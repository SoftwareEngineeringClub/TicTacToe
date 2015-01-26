// ##########################################################################
// # File Name:	SwtPlayersView.java
// ##########################################################################

package tictactoe.desktopplatform.playersdesktop;

import tictactoe.client.mainclient.IMainView;
import tictactoe.client.playersclient.IPlayersView;
import tictactoe.desktopplatform.maindesktop.SwtMainView;
import tictactoe.service.playerservice.PlayerData;

import strata1.client.command.ExecutionException;
import strata1.client.shell.IDispatcher;
import strata1.client.view.AbstractView;
import strata1.swtclient.swtshell.ISwtDispatcher;
import strata1.swtclient.swtview.ISwtView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.wb.swt.SWTResourceManager;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.layout.RowData;

/****************************************************************************
 * 
 */
public 
class SwtPlayersView 
    extends    AbstractView 
    implements IPlayersView,ISwtView
{
    private IDispatcher     itsDispatcher;
    private CTabFolder      itsTabFolder;
    private CTabItem        itsPlayersTab;
    private Composite       itsPlayersComposite;
    private Table           itsPlayersTable;
    private TableColumn     itsPlayerColumn;
    private TableColumn     itsStatusColumn;
    private TableColumn     itsWinsColumn;
    private TableColumn     itsLossesColumn;
    private TableColumn     itsTiesColumn;
    private TableColumn     itsRankColumn;
    private TableColumn     itsAvgRankColumn;
    private Button          itsChallengeButton;
    private List<TableItem> itsPlayerRows;

    /************************************************************************
     * Creates a new SwtPlayersView. 
     *
     * @param mainView
     */
    @Inject
    public
    SwtPlayersView(IDispatcher dispatcher,IMainView mainView)
    {
        this(
            dispatcher,
            ((SwtMainView)mainView).getTabFolder(),
            ((SwtMainView)mainView).getPlayersTab() );
    }

    /************************************************************************
     * @wbp.parser.constructor 
     * Creates a new SwtPlayersView. 
     *
     * @param mainView
     */
    public
    SwtPlayersView(CTabFolder folder,CTabItem item)
    {
        this(null,folder,item );
    }
        
    /************************************************************************
     * Creates a new SwtPlayersView. 
     *
     * @param folder
     * @param item
     */
    public 
    SwtPlayersView(IDispatcher dispatcher,CTabFolder folder,CTabItem item)
    {
        itsDispatcher = dispatcher;
        itsTabFolder = folder;
        itsPlayersTab = item;
        
        itsPlayersComposite = new Composite(itsTabFolder, SWT.BORDER);
        itsPlayersComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsPlayersTab.setControl(itsPlayersComposite);
        itsPlayersComposite.setLayout(new RowLayout(SWT.VERTICAL));
        
        itsPlayersTable = new Table(itsPlayersComposite, SWT.BORDER | SWT.FULL_SELECTION);
        itsPlayersTable.setLayoutData(new RowData(SWT.DEFAULT, 277));
        itsPlayersTable.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
        itsPlayersTable.setHeaderVisible(true);
        itsPlayersTable.setLinesVisible(true);
        itsPlayersTable.addSelectionListener( 
            new SelectionAdapter() 
            {

                @Override
                public void 
                widgetSelected(SelectionEvent event)
                {
                    int       i = itsPlayersTable.getSelectionIndex();
                    TableItem row = null;
                    
                    if ( i < 0 )
                        itsChallengeButton.setEnabled( false );
                    else
                    {
                        row = itsPlayersTable.getItem( i );
                        
                        if ( row.getText(1).equals( "ONLINE" ) )
                            itsChallengeButton.setEnabled( true );
                        else
                            itsChallengeButton.setEnabled( false );
                    }
                }
                
            } );

        
        itsPlayerColumn = new TableColumn(itsPlayersTable, SWT.NONE);
        itsPlayerColumn.setWidth(100);
        itsPlayerColumn.setText("Player");
        
        itsStatusColumn = new TableColumn(itsPlayersTable, SWT.NONE);
        itsStatusColumn.setWidth(100);
        itsStatusColumn.setText("Status");
        
        itsWinsColumn = new TableColumn(itsPlayersTable, SWT.NONE);
        itsWinsColumn.setWidth(100);
        itsWinsColumn.setText("Wins");
        
        itsLossesColumn = new TableColumn(itsPlayersTable, SWT.NONE);
        itsLossesColumn.setWidth(100);
        itsLossesColumn.setText("Losses");
        
        itsTiesColumn = new TableColumn(itsPlayersTable, SWT.NONE);
        itsTiesColumn.setWidth(100);
        itsTiesColumn.setText("Ties");
        
        itsRankColumn = new TableColumn(itsPlayersTable, SWT.NONE);
        itsRankColumn.setWidth(100);
        itsRankColumn.setText("Rank");
        
        itsAvgRankColumn = new TableColumn(itsPlayersTable, SWT.NONE);
        itsAvgRankColumn.setWidth(100);
        itsAvgRankColumn.setText("Avg Rank");
        
        itsChallengeButton = new Button(itsPlayersComposite, SWT.NONE);
        itsChallengeButton.setLayoutData(new RowData(70, SWT.DEFAULT));
        itsChallengeButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
        itsChallengeButton.setText("Challenge");
        itsChallengeButton.setEnabled( false );
        itsChallengeButton.addSelectionListener( 
            new SelectionAdapter() 
            {
                @Override
                public void 
                widgetSelected(SelectionEvent arg0)
                {
                    try
                    {
                        invoke( "IssueChallenge" );
                    }
                    catch (ExecutionException e)
                    {
                        MessageBox dialog = 
                            new MessageBox(new Shell(), SWT.ICON_ERROR | SWT.OK );
                          dialog.setText("Error");
                          dialog.setMessage(e.getMessage());
                          dialog.open();                     
                    }
                }                
            } );
        itsPlayerRows = new ArrayList<TableItem>();
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    hide()
    {
        itsDispatcher.invokeAsynchronous( 
            new Runnable() 
            {
                @Override
                public void run()
                {
                    itsPlayersComposite.setVisible( false );
                }
            } );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    show()
    {
        itsDispatcher.invokeAsynchronous( 
            new Runnable() 
            {
                @Override
                public void run()
                {
                    itsPlayersComposite.setVisible( true );
                }
            } );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    start()
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    stop()
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Composite 
    getComposite()
    {
        return itsPlayersComposite;
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
    public IPlayersView 
    insertPlayer(final PlayerData playerData)
    {
        itsDispatcher.invokeAsynchronous( 
            new Runnable() 
            {
                @Override
                public void 
                run()
                {
                    TableItem row = new TableItem( itsPlayersTable,SWT.NONE);
                    Display   display = ((ISwtDispatcher)itsDispatcher).getDisplay();
                    Color     green = new Color(display,160,200,150 );
                    Color     red   = new Color(display,230,150,150);
                    
                    row.setText( 0,playerData.getUserName() );
                    row.setText( 1,playerData.getStatus() );
                    row.setText( 2,playerData.getWins().toString() );
                    row.setText( 3,playerData.getLosses().toString() );
                    row.setText( 4,playerData.getTies().toString() );
                    row.setText( 5,playerData.getCurrentRank().toPlainString() );
                    row.setText( 6,playerData.getAverageRank().toPlainString() );
                    
                    if ( playerData.getStatus().equals( "ONLINE" ) )
                    {
                        row.setBackground( 1,green );
                        row.setGrayed( false );
                    }
                    else
                    {
                        row.setBackground( 1,red );
                        row.setGrayed( false );
                    }
                    
                    itsPlayerRows.add( row );
                }
            } );
        
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IPlayersView 
    displayChallenge(PlayerData challenger)
    {
        final String message = 
            "Accept challenge from " + challenger.getUserName() + "?";
        
        itsDispatcher.invokeAsynchronous( 
            new Runnable() 
            {
                @Override
                public void 
                run()
                {
                    MessageBox dialog = 
                        new MessageBox(new Shell(), SWT.ICON_QUESTION|SWT.YES|SWT.NO );
                    int        answer;
                    
                    dialog.setText("Challenge");
                    dialog.setMessage(message);
                    answer = dialog.open();                     

                    try
                    {
                        if ( answer == SWT.YES )
                            invoke("AcceptChallenge");
                        else
                           invoke("DeclineChallenge");
                    }
                    catch (ExecutionException e)
                    {
                        MessageBox error = 
                            new MessageBox(new Shell(), SWT.ICON_ERROR | SWT.OK );
                        error.setText("Error");
                        error.setMessage(e.getMessage());
                        error.open();                     
                    }
                }
            } );
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IPlayersView 
    displayChallengeAccepted(PlayerData challenged)
    {
        final String message = 
            "Challenge accepted from " + challenged.getUserName() + ".";
        
        itsDispatcher.invokeAsynchronous( 
            new Runnable() 
            {
                @Override
                public void 
                run()
                {
                    MessageBox dialog = 
                        new MessageBox(new Shell(), SWT.ICON_QUESTION|SWT.OK );
                    
                    dialog.setText("Challenge Accepted");
                    dialog.setMessage(message);
                    dialog.open();                     

                    try
                    {
                        invoke("StartGame");
                    }
                    catch (ExecutionException e)
                    {
                        MessageBox error = 
                            new MessageBox(new Shell(), SWT.ICON_ERROR | SWT.OK );
                        error.setText("Error");
                        error.setMessage(e.getMessage());
                        error.open();                     
                    }
                }
            } );
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IPlayersView 
    displayChallengeDeclined(PlayerData challenged)
    {
        final String message = 
            "Challenge declined from " + challenged.getUserName() + ".";
        
        itsDispatcher.invokeAsynchronous( 
            new Runnable() 
            {
                @Override
                public void 
                run()
                {
                    MessageBox dialog = 
                        new MessageBox(new Shell(), SWT.ICON_INFORMATION|SWT.OK);
                    
                    dialog.setText("Challenge Declined");
                    dialog.setMessage(message);
                    dialog.open();                     
                }
            } );
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IPlayersView 
    removePlayers()
    {
        itsDispatcher.invokeAsynchronous( 
            new Runnable() 
            {
                @Override
                public void 
                run()
                {
                    itsPlayersTable.removeAll();
                    itsPlayerRows.clear();
                }
            } );
        
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public String 
    getSelectedPlayer()
    {
        TableItem[] selected = itsPlayersTable.getSelection();
        
        return selected[0].getText( 0 );
    }
    
}

// ##########################################################################

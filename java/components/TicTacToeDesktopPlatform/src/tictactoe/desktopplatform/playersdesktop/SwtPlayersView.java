// ##########################################################################
// # File Name:	SwtPlayersView.java
// ##########################################################################

package tictactoe.desktopplatform.playersdesktop;

import tictactoe.client.mainclient.IMainView;
import tictactoe.client.playersclient.IPlayersView;
import tictactoe.desktopplatform.maindesktop.SwtMainView;

import strata1.client.command.ExecutionException;
import strata1.client.view.AbstractView;
import strata1.swtclient.swtview.ISwtView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
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
    private TabFolder       itsTabFolder;
    private TabItem         itsPlayersTab;
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
    SwtPlayersView(IMainView mainView)
    {
        this(
            ((SwtMainView)mainView).getTabFolder(),
            ((SwtMainView)mainView).getPlayersTab() );
    }
    
    /************************************************************************
     * @wbp.parser.constructor 
     * Creates a new SwtPlayersView. 
     *
     * @param folder
     * @param item
     */
    public 
    SwtPlayersView(TabFolder folder,TabItem item)
    {
        itsTabFolder = folder;
        itsPlayersTab = item;
        
        itsPlayersComposite = new Composite(itsTabFolder, SWT.BORDER);
        itsPlayersComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsPlayersTab.setControl(itsPlayersComposite);
        itsPlayersComposite.setLayout(new RowLayout(SWT.VERTICAL));
        
        itsPlayersTable = new Table(itsPlayersComposite, SWT.BORDER | SWT.FULL_SELECTION);
        itsPlayersTable.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
        itsPlayersTable.setHeaderVisible(true);
        itsPlayersTable.setLinesVisible(true);
        
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
        itsChallengeButton.addSelectionListener( 
            new SelectionAdapter() 
            {

                /************************************************************************
                 * {@inheritDoc} 
                 */
                @Override
                public void 
                widgetSelected(SelectionEvent arg0)
                {
                    try
                    {
                        invoke( "Challenge" );
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
        itsPlayersComposite.setVisible( false );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    show()
    {
        itsPlayersComposite.setVisible( true );
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
    insertPlayer(String playerName,String status)
    {
        TableItem row = new TableItem( itsPlayersTable,SWT.NONE);
        
        row.setText( 0,playerName );
        row.setText( 1,status );
        row.setText(  2,"# wins" );
        row.setText(  3,"# losses" );
        row.setText(  4,"# ties" );
        row.setText(  5,"rank" );
        row.setText(  6,"avg rank" );
        
        itsPlayerRows.add( row );
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

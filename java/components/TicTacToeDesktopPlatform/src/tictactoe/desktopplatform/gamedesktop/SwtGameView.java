// ##########################################################################
// # File Name:	SwtGameView.java
// ##########################################################################

package tictactoe.desktopplatform.gamedesktop;

import tictactoe.client.gameclient.IGameView;
import tictactoe.client.gameclient.Move;
import tictactoe.client.mainclient.IMainView;
import tictactoe.desktopplatform.maindesktop.SwtMainView;
import tictactoe.service.gameservice.GameStateData;

import strata1.client.command.ExecutionException;
import strata1.client.view.AbstractView;
import strata1.swtclient.swtview.ISwtView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.wb.swt.SWTResourceManager;

import javax.inject.Inject;

/****************************************************************************
 * 
 */
public 
class SwtGameView 
    extends    AbstractView 
    implements IGameView,ISwtView,SelectionListener
{
    private TabFolder     itsTabFolder;
    private TabItem       itsGameTab;
    private Composite     itsGameComposite;
    private Label         itsStartTimeLabel;
    private Label         itsStartTimeValueLabel;
    private Label         itsPlayerXLabel;
    private Label         itsPlayerXValueLabel;
    private Label         itsPlayerOLabel;
    private Label         itsPlayerOValueLabel;
    private Label         itsCurrentMoveLabel;
    private Label         itsCurrentMoveValueLabel;
    private Composite     itsBoardComposite;
    private Button        itsCellButton00;
    private Button        itsCellButton01;
    private Button        itsCellButton02;
    private Button        itsCellButton10;
    private Button        itsCellButton11;
    private Button        itsCellButton12;
    private Button        itsCellButton20;
    private Button        itsCellButton21;
    private Button        itsCellButton22;
    
    private Button[][]    itsCellButtons;
    private GameStateData itsGameState;
    private String        itsThisPlayer;
    private Move          itsCurrentMove;

    /************************************************************************
     * Creates a new SwtGameView. 
     *
     * @param mainView
     */
    @Inject
    public 
    SwtGameView(IMainView mainView)
    {
        this(
            ((SwtMainView)mainView).getTabFolder(),
            ((SwtMainView)mainView).getGameTab() );
    }
    
    /************************************************************************
     * @wbp.parser.constructor 
     * Creates a new SwtGameView. 
     * 
     * @param folder
     * @param item
     */
    public 
    SwtGameView(TabFolder folder,TabItem item)
    {        
        itsTabFolder = folder;     
        itsGameTab = item;
        
        itsGameComposite = new Composite(itsTabFolder, SWT.NONE);
        itsGameComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsGameComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);
        itsGameTab.setControl(itsGameComposite);
        itsGameComposite.setLayout(new GridLayout(3, false));
        
        itsStartTimeLabel = new Label(itsGameComposite, SWT.NONE);
        itsStartTimeLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        itsStartTimeLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsStartTimeLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        itsStartTimeLabel.setText("Start Time:");
        
        itsStartTimeValueLabel = new Label(itsGameComposite, SWT.NONE);
        itsStartTimeValueLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        itsStartTimeValueLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsStartTimeValueLabel.setText("<startTime>");
        
        itsBoardComposite = new Composite(itsGameComposite, SWT.NONE);
        itsBoardComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        itsBoardComposite.setLayout(new GridLayout(3, false));
        itsBoardComposite.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 4));
        
        itsCellButton00 = new Button(itsBoardComposite, SWT.FLAT);
        GridData gd_itsCellButton00 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_itsCellButton00.heightHint = 50;
        gd_itsCellButton00.widthHint = 50;
        gd_itsCellButton00.minimumWidth = 15;
        gd_itsCellButton00.minimumHeight = 15;
        itsCellButton00.setLayoutData(gd_itsCellButton00);
        itsCellButton00.setSize(new Point(20, 20));
        itsCellButton00.setBounds(0, 0, 75, 25);
        itsCellButton00.setText(" ");
        
        itsCellButton01 = new Button(itsBoardComposite, SWT.FLAT);
        GridData gd_itsCellButton01 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_itsCellButton01.heightHint = 50;
        gd_itsCellButton01.widthHint = 50;
        gd_itsCellButton01.minimumWidth = 50;
        gd_itsCellButton01.minimumHeight = 50;
        itsCellButton01.setLayoutData(gd_itsCellButton01);
        itsCellButton01.setSize(new Point(20, 20));
        itsCellButton01.setText(" ");
        
        itsCellButton02 = new Button(itsBoardComposite, SWT.FLAT);
        GridData gd_itsCellButton02 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_itsCellButton02.widthHint = 50;
        gd_itsCellButton02.minimumWidth = 50;
        gd_itsCellButton02.minimumHeight = 50;
        gd_itsCellButton02.heightHint = 50;
        itsCellButton02.setLayoutData(gd_itsCellButton02);
        itsCellButton02.setSize(new Point(20, 20));
        itsCellButton02.setText(" ");
        
        itsCellButton10 = new Button(itsBoardComposite, SWT.FLAT);
        GridData gd_itsCellButton10 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_itsCellButton10.widthHint = 50;
        gd_itsCellButton10.minimumWidth = 50;
        gd_itsCellButton10.minimumHeight = 50;
        gd_itsCellButton10.heightHint = 50;
        itsCellButton10.setLayoutData(gd_itsCellButton10);
        itsCellButton10.setSize(new Point(20, 20));
        itsCellButton10.setText(" ");
        
        itsCellButton11 = new Button(itsBoardComposite, SWT.FLAT);
        GridData gd_itsCellButton11 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_itsCellButton11.widthHint = 50;
        gd_itsCellButton11.minimumWidth = 50;
        gd_itsCellButton11.minimumHeight = 50;
        gd_itsCellButton11.heightHint = 50;
        itsCellButton11.setLayoutData(gd_itsCellButton11);
        itsCellButton11.setSize(new Point(20, 20));
        itsCellButton11.setText(" ");
        
        itsCellButton12 = new Button(itsBoardComposite, SWT.FLAT);
        GridData gd_itsCellButton12 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_itsCellButton12.widthHint = 50;
        gd_itsCellButton12.minimumWidth = 50;
        gd_itsCellButton12.minimumHeight = 50;
        gd_itsCellButton12.heightHint = 50;
        itsCellButton12.setLayoutData(gd_itsCellButton12);
        itsCellButton12.setSize(new Point(20, 20));
        itsCellButton12.setText(" ");
        
        itsCellButton20 = new Button(itsBoardComposite, SWT.FLAT);
        GridData gd_itsCellButton20 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_itsCellButton20.widthHint = 50;
        gd_itsCellButton20.minimumWidth = 50;
        gd_itsCellButton20.minimumHeight = 50;
        gd_itsCellButton20.heightHint = 50;
        itsCellButton20.setLayoutData(gd_itsCellButton20);
        itsCellButton20.setSize(new Point(20, 20));
        itsCellButton20.setText(" ");
        
        itsCellButton21 = new Button(itsBoardComposite, SWT.FLAT);
        GridData gd_itsCellButton21 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_itsCellButton21.widthHint = 50;
        gd_itsCellButton21.minimumWidth = 50;
        gd_itsCellButton21.minimumHeight = 50;
        gd_itsCellButton21.heightHint = 50;
        itsCellButton21.setLayoutData(gd_itsCellButton21);
        itsCellButton21.setSize(new Point(20, 20));
        itsCellButton21.setText(" ");
        
        itsCellButton22 = new Button(itsBoardComposite, SWT.FLAT);
        GridData gd_itsCellButton22 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_itsCellButton22.widthHint = 50;
        gd_itsCellButton22.minimumWidth = 50;
        gd_itsCellButton22.minimumHeight = 50;
        gd_itsCellButton22.heightHint = 50;
        itsCellButton22.setLayoutData(gd_itsCellButton22);
        itsCellButton22.setSize(new Point(20, 20));
        itsCellButton22.setText(" ");
        
        itsPlayerXLabel = new Label(itsGameComposite, SWT.NONE);
        itsPlayerXLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        itsPlayerXLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsPlayerXLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        itsPlayerXLabel.setText("Player X:");
        
        itsPlayerXValueLabel = new Label(itsGameComposite, SWT.NONE);
        itsPlayerXValueLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        itsPlayerXValueLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsPlayerXValueLabel.setText("<playerX>");
        
        itsPlayerOLabel = new Label(itsGameComposite, SWT.NONE);
        itsPlayerOLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        itsPlayerOLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsPlayerOLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        itsPlayerOLabel.setText("Player O:");
        
        itsPlayerOValueLabel = new Label(itsGameComposite, SWT.NONE);
        itsPlayerOValueLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        itsPlayerOValueLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsPlayerOValueLabel.setText("<playerO>");
        
        itsCurrentMoveLabel = new Label(itsGameComposite, SWT.NONE);
        itsCurrentMoveLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
        itsCurrentMoveLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsCurrentMoveLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        itsCurrentMoveLabel.setText("Current Move:");
        
        itsCurrentMoveValueLabel = new Label(itsGameComposite, SWT.NONE);
        itsCurrentMoveValueLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        itsCurrentMoveValueLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        itsCurrentMoveValueLabel.setText("<currentPlayer>");
        
        itsCellButtons = new Button[3][3];
        itsCellButtons[0][0] = itsCellButton00;
        itsCellButtons[0][1] = itsCellButton01;
        itsCellButtons[0][2] = itsCellButton02;
        itsCellButtons[1][0] = itsCellButton10;
        itsCellButtons[1][1] = itsCellButton11;
        itsCellButtons[1][2] = itsCellButton12;
        itsCellButtons[2][0] = itsCellButton20;
        itsCellButtons[2][1] = itsCellButton21;
        itsCellButtons[2][2] = itsCellButton22;
        
        itsCurrentMove = null;
        itsThisPlayer  = "";
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    hide()
    {
        itsGameComposite.setVisible( false );
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    show()
    {
        itsGameComposite.setVisible( true );
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
        return itsGameComposite;
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
    public IGameView 
    setPlayerX(String playerX)
    {
        itsPlayerXValueLabel.setText( playerX );
        return null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IGameView 
    setPlayerO(String playerO)
    {
        itsPlayerOValueLabel.setText( playerO );
        return null;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IGameView 
    setThisPlayer(String thisPlayer)
    {
        itsThisPlayer = thisPlayer;
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public IGameView 
    setGameState(GameStateData state)
    {   
        itsGameState = state;
        
        for (int i=0;i<3;i++)
            for (int j=0;j<3;j++)
                setCellState(i,j);
        
        return this;
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public Move 
    getCurrentMove()
    {
        return itsCurrentMove;
    }

    @Override
    public void 
    widgetDefaultSelected(SelectionEvent event)
    {
    }

    /************************************************************************
     * {@inheritDoc} 
     */
    @Override
    public void 
    widgetSelected(SelectionEvent event)
    {
        Object source = event.getSource();
        
        if ( source == itsCellButton00 )
            itsCurrentMove = new Move( itsThisPlayer,0,0 );
        else if ( source == itsCellButton01 )
            itsCurrentMove = new Move( itsThisPlayer,0,1 );
        else if ( source == itsCellButton02 )
            itsCurrentMove = new Move( itsThisPlayer,0,2 );
        else if ( source == itsCellButton10 )
            itsCurrentMove = new Move( itsThisPlayer,1,0 );
        else if ( source == itsCellButton11 )
            itsCurrentMove = new Move( itsThisPlayer,1,1 );
        else if ( source == itsCellButton12 )
            itsCurrentMove = new Move( itsThisPlayer,1,2 );
        else if ( source == itsCellButton20 )
            itsCurrentMove = new Move( itsThisPlayer,2,0 );
        else if ( source == itsCellButton21 )
            itsCurrentMove = new Move( itsThisPlayer,2,1 );
        else if ( source == itsCellButton22 )
            itsCurrentMove = new Move( itsThisPlayer,2,2 );
        
        try
        {
            invoke( "MakeMove" );
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

    /************************************************************************
     *  
     *
     * @param i
     * @param j
     */
    private void 
    setCellState(int i,int j)
    {
        Button cellButton = itsCellButtons[i][j];
        
        switch ( itsGameState.getCell( i,j ) )
        {
        case GameStateData.X_MARKER:
            cellButton.setText( "X" );
            cellButton.setEnabled( false );
            break;
            
        case GameStateData.O_MARKER:
            cellButton.setText( "O" );
            cellButton.setEnabled( false );
            break;
            
        default:
            cellButton.setText(  " " );
            cellButton.setEnabled( true );
            break;
        }
    }
}

// ##########################################################################

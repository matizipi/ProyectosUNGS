package com.app.ui.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.app.ui.view.adaptation.CommonTableCellRenderer;
import com.app.ui.view.adaptation.CustomTableModel;
import com.app.ui.view.adaptation.LogTableCellRenderer;

public class AutomatonDFView extends AutomatonFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String[] ALPHABET_TITLE = new String[] { "Simbol" };
	public static String[] STATE_TITLE = new String[] { "Estado", "Inicial", "Final" };
	public static String[] TF_TITLE = new String[] { "Estado" };
	public static String[] LOG_TITLE = new String[] { "Tipo", "Descipción" };
	
	private int _maintablesY = 0;
	private int _maintablesH = 300;
	
	private JTextField _txtInput;
	private JButton _btnInput;
	
	private JButton _btnEquivalenceTable;
	
	private JButton _btnReturn;
	
	private JButton _btnReturnMainMenu;
	
	/* Alphabet set. */
	private JScrollPane _spAlphabet;
	private DefaultTableModel _dtmAlphabet;
	private JTable _tblAlphabet;
	
	/* State set. */
	private JScrollPane _spState;
	private DefaultTableModel _dtmState;
	private JTable _tblState;
	
	/* Transaction function set. */
	private JScrollPane _spTf;
	private DefaultTableModel _dtmTf;
	private JTable _tblTf;
	
	/* Log set. */
	private JScrollPane _spLog;
	private DefaultTableModel _dtmLog;
	private JTable _tblLog;
	
	public AutomatonDFView() {
		super();
		
		this.setTitle( ".: Automata Finito Deterministico :." );
		
		/* Input components. */
		this._txtInput = new JTextField();
		this._txtInput.setColumns( 10 );
		this._txtInput.setBounds( spX, spY, 200, fldH );
		this._contentPanel.add( this._txtInput );
		
		
		this._btnInput = new JButton( "Test" );
		this._btnInput.setBounds( this._txtInput.getX() + this._txtInput.getWidth() + spX
				, spY
				, 70
				, fldH );
		this._contentPanel.add( this._btnInput );
		
		
		this._btnEquivalenceTable = new JButton();
		this._btnEquivalenceTable.setBounds( this._btnInput.getX() + this._btnInput.getWidth() + this.spX
				, this.spY
				, this.fldH
				, this.fldH );
		this._btnEquivalenceTable.setToolTipText( "Muestra trabla de equivalencias de los estados." );
		this._contentPanel.add( this._btnEquivalenceTable );
		
		
		this._btnReturnMainMenu = new JButton( "Volver al Menú Principal" );
		this._btnReturnMainMenu.setBounds( this._frameW - spY - 150
				, this.spY
				, 150
				, this.fldH );
		this._contentPanel.add( this._btnReturnMainMenu );
		
		
		this._btnReturn = new JButton( "Volver" );
		this._btnReturn.setBounds( this._btnReturnMainMenu.getX() - spY - 70
				, spY
				, 70
				, fldH);
		this._contentPanel.add( this._btnReturn );
		
		
		/* Set main tables. */
		this._maintablesY = this._txtInput.getY() + this._txtInput.getHeight() + spY;
		/* Set alphabet table. */
		this._spAlphabet = new JScrollPane();
		this._spAlphabet.setBounds( spX
				, this._maintablesY
				, 80
				, this._maintablesH );
		this._spAlphabet.setBorder( BorderFactory.createTitledBorder( null, "Alfabeto", TitledBorder.LEFT, TitledBorder.TOP ) );
		this._spAlphabet.setOpaque( false );
		this._contentPanel.add( this._spAlphabet );
		
		
		this._dtmAlphabet = new CustomTableModel( null, ALPHABET_TITLE );
		this._tblAlphabet = new JTable( this._dtmAlphabet );
		this._tblAlphabet.setDefaultRenderer( String.class , new CommonTableCellRenderer() );
		this._spAlphabet.setViewportView( this._tblAlphabet );
		
		
		/* Set state table. */
		this._spState = new JScrollPane();
		this._spState.setBounds( this._spAlphabet.getX() + this._spAlphabet.getWidth() + spX
				, this._maintablesY
				, 180
				, this._maintablesH );
		this._spState.setBorder( BorderFactory.createTitledBorder( null, "Estados", TitledBorder.LEFT, TitledBorder.TOP ) );
		this._spState.setOpaque( false );
		this._contentPanel.add( this._spState );
		
		
		this._dtmState = new CustomTableModel( null, STATE_TITLE );
		this._tblState = new JTable( this._dtmState );
		this._tblState.setDefaultRenderer( String.class, new CommonTableCellRenderer() );
		this._spState.setViewportView( this._tblState );
		
		
		/* Set transaction function. */
		this._spTf = new JScrollPane( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		this._spTf.setBounds( this._spState.getX() + this._spState.getWidth() + spX
				, this._maintablesY
				, this._frameW - this._spState.getX() - this._spState.getWidth() - spX
				, this._maintablesH );
		this._spTf.setBorder( BorderFactory.createTitledBorder( null, "Tabla de Funciones de Transacción", TitledBorder.LEFT, TitledBorder.TOP ) );
		this._spTf.setOpaque( false );
		this._contentPanel.add( this._spTf );
		
		
		this._dtmTf = new CustomTableModel( null, TF_TITLE );
		this._tblTf = new JTable( this._dtmTf );
		this._tblTf.setDefaultRenderer( String.class, new CommonTableCellRenderer() );
		this._tblTf.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		this._spTf.setViewportView( this._tblTf );
		
		
		/* Table log. */
		this._spLog = new JScrollPane( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		this._spLog.setBounds( spX
				, this._maintablesY + this._maintablesH + spY
				, this._frameW - spX
				, this._frameH - this._spAlphabet.getY() - this._spAlphabet.getHeight() - spY );
		this._spLog.setBorder( BorderFactory.createTitledBorder( null, "Tabla Log", TitledBorder.LEFT, TitledBorder.TOP ) );
		this._spLog.setOpaque( false );
		this._contentPanel.add( this._spLog );
		
		
		this._dtmLog = new CustomTableModel( null, LOG_TITLE );
		this._tblLog = new JTable( this._dtmLog );
		this._tblLog.setDefaultRenderer( String.class, new LogTableCellRenderer() );
		this._tblLog.getColumnModel().getColumn( 0 ).setPreferredWidth( 70 );
		this._tblLog.getColumnModel().getColumn( 1 ).setPreferredWidth( this._frameW - spX - 75 - spX );
		this._tblLog.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		this._spLog.setViewportView( this._tblLog );
	}
	
	public JTextField getTxtInput() {
		return this._txtInput;
	}
	
	public JButton getBtnInput() {
		return this._btnInput;
	}
	
	public JButton getBtnEquivalenceTable() {
		return this._btnEquivalenceTable;
	}

	public JButton getBtnReturn() {
		return this._btnReturn;
	}
	
	public JButton getBtnReturnToMainMenu() {
		return this._btnReturnMainMenu;
	}
	
	public JScrollPane getSpAlphabet() {
		return _spAlphabet;
	}

	public DefaultTableModel getDtmAlphabet() {
		return _dtmAlphabet;
	}

	public JTable getTblAlphabet() {
		return _tblAlphabet;
	}

	public JScrollPane getSpState() {
		return _spState;
	}

	public DefaultTableModel getDtmState() {
		return _dtmState;
	}

	public JTable getTblState() {
		return _tblState;
	}

	public JScrollPane getSpTf() {
		return _spTf;
	}

	public DefaultTableModel getDtmTf() {
		return _dtmTf;
	}

	public JTable getTblTf() {
		return _tblTf;
	}

	public JScrollPane getSpLog() {
		return _spLog;
	}

	public DefaultTableModel getDtmLog() {
		return _dtmLog;
	}

	public JTable getTblLog() {
		return _tblLog;
	}
}
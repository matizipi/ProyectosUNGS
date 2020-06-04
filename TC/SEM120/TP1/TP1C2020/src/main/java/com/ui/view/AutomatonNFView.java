package com.ui.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.ui.view.AutomatonFrame;

public class AutomatonNFView extends AutomatonFrame {
	
	public static String[] ALPHABET_TITLE = new String[] { "Simbol" };
	public static String[] STATE_TITLE = new String[] { "Estado", "Inicial", "Final" };
	public static String[] TF_TITLE = new String[] { "Estado" };
	public static String[] LOG_TITLE = new String[] { "Tipo", "Descipción" };
	
	private int _maintablesY = 0;
	private int _maintablesH = 300;
	
	private JTextField _txtFilePath;
	private JButton _btnLoadFile;
	private JButton _btnConvertToDFA;
	
	private JLabel _lblInput;
	private JTextField _txtInput;
	private JButton _btnInput;
	
	private JScrollPane _spAlphabet;
	private DefaultTableModel _dtmAlphabet;
	private JTable _tblAlphabet;
	
	private JScrollPane _spStates;
	private DefaultTableModel _dtmStates;
	private JTable _tblStates;
	
	private JScrollPane _spTransactionFunction;
	private DefaultTableModel _dtmTransactionFunction;
	private JTable _tblTransactionFunction;
	
	private JScrollPane _spLog;
	private DefaultTableModel _dtmLog;
	private JTable _tblLog;
	
	public AutomatonNFView() {
		super();
		
		/* Set properties to file path. */
		this._txtFilePath = new JTextField();
		this._txtFilePath.setColumns( 10 );
		this._txtFilePath.setBounds( spX, spY, 300, 27 );
		this._contentPanel.add( this._txtFilePath );
		
		this._txtFilePath.setText("C:/Users/user/Desktop/Nicolas/02-Proyecto/01-Informatic/07-UNGS/Teoria_de_la_Computaci�n/eclips-workspace/TP1C2020/src/main/resources/exampleAutomaton.txt");
		
		/* Set load properties. */
		this._btnLoadFile = new JButton( "Cargar" );
		this._btnLoadFile.setBounds( this._txtFilePath.getX() + this._txtFilePath.getWidth() + spX
				, spY
				, 150
				, 27);
		this._contentPanel.add( this._btnLoadFile );

		/* Convert button */
		this._btnConvertToDFA = new JButton( "Convertir a AFD" );
		this._btnConvertToDFA.setBounds( this._btnLoadFile.getX() + this._btnLoadFile.getWidth() + spX, spY, 150, 27);
		this._contentPanel.add( this._btnConvertToDFA );
		
		/* Set input set fields. */
		this._lblInput = new JLabel( "Input: " );
		this._lblInput.setBounds( spX
				, this._txtFilePath.getY() + this._txtFilePath.getHeight() + spY
				, 45
				, 27 );
		this._contentPanel.add( this._lblInput );
		
		this._txtInput = new JTextField();
		this._txtInput.setColumns( 10 );
		this._txtInput.setBounds( 55
				, /*35*/ this._txtFilePath.getY() + this._txtFilePath.getHeight() + spY
				, 150
				, 27 );
		this._contentPanel.add( this._txtInput );
		
		this._btnInput = new JButton( "Test" );
		this._btnInput.setBounds( 210
				, /*35*/this._txtFilePath.getY() + this._txtFilePath.getHeight() + spY
				, 50
				, 27 );
		this._contentPanel.add( this._btnInput );
		
		/* Set main tables. */
		this._maintablesY = this._txtInput.getY() + this._txtFilePath.getHeight() + spY;
		/* Set alphabet table. */
		this._spAlphabet = new JScrollPane();
		this._spAlphabet.setBounds( spX
				, this._maintablesY
				, 80
				, this._maintablesH );
		this._spAlphabet.setBorder( BorderFactory.createTitledBorder( null, "Alfabeto", TitledBorder.LEFT, TitledBorder.TOP ) );
		this._contentPanel.add( this._spAlphabet );

		this._dtmAlphabet = new DefaultTableModel( null, ALPHABET_TITLE );
		this._tblAlphabet = new JTable( this._dtmAlphabet );
		this._spAlphabet.setViewportView( this._tblAlphabet );
		
		/* Set state table. */
		this._spStates = new JScrollPane();
		this._spStates.setBounds( this._spAlphabet.getX() + this._spAlphabet.getWidth() + spX
				, this._maintablesY
				, 180
				, this._maintablesH );
		this._spStates.setBorder( BorderFactory.createTitledBorder( null, "Estados", TitledBorder.LEFT, TitledBorder.TOP ) );
		this._contentPanel.add( this._spStates );
		
		this._dtmStates = new DefaultTableModel( null, STATE_TITLE );
		this._tblStates = new JTable( this._dtmStates );
		this._spStates.setViewportView( this._tblStates );
		
		/* Set transaction function. */
		this._spTransactionFunction = new JScrollPane( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		this._spTransactionFunction.setBounds( /*180*/this._spStates.getX() + this._spStates.getWidth() + spX
				, this._maintablesY
				, this._frameW - this._spStates.getX() - this._spStates.getWidth() - spX
				, this._maintablesH );
		this._spTransactionFunction.setBorder( BorderFactory.createTitledBorder( null, "Tabla de Funciones de Transacción", TitledBorder.LEFT, TitledBorder.TOP ) );
		this._contentPanel.add( this._spTransactionFunction );
		
		this._dtmTransactionFunction = new DefaultTableModel( null, TF_TITLE );
		this._tblTransactionFunction = new JTable( this._dtmTransactionFunction );
		this._tblTransactionFunction.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		this._spTransactionFunction.setViewportView( this._tblTransactionFunction );
		
		/* Table log. */
		this._spLog = new JScrollPane( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		this._spLog.setBounds( spX
				, this._maintablesY + this._maintablesH + spY
				, this._frameW - spX
				, this._frameH - this._spAlphabet.getY() - this._spAlphabet.getHeight() - spY );
		this._spLog.setBorder( BorderFactory.createTitledBorder( null, "Tabla Log", TitledBorder.LEFT, TitledBorder.TOP ) );
		this._contentPanel.add( this._spLog );
		
		this._dtmLog = new DefaultTableModel( null, LOG_TITLE );
		this._tblLog = new JTable( this._dtmLog );
		this._tblLog.getColumnModel().getColumn( 0 ).setPreferredWidth( 70 );
		this._tblLog.getColumnModel().getColumn( 1 ).setPreferredWidth( this._frameW - spX - 75 - spX );
		this._tblLog.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		this._spLog.setViewportView( this._tblLog );
		
	}

	public JTextField getTxtLoadFile() {
		return _txtFilePath;
	}

	public JButton getBtnLoadFile() {
		return _btnLoadFile;
	}
	
	public JButton getBtnConvertToDFA() {
		return this._btnConvertToDFA;
	}

	public JLabel getLblInput() {
		return _lblInput;
	}

	public JTextField getTxtInput() {
		return _txtInput;
	}

	public JButton getBtnInput() {
		return _btnInput;
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

	public JScrollPane getSpStates() {
		return _spStates;
	}

	public DefaultTableModel getDtmStates() {
		return _dtmStates;
	}

	public JTable getTblStates() {
		return _tblStates;
	}

	public JScrollPane getSpTransactionFunction() {
		return _spTransactionFunction;
	}

	public DefaultTableModel getDtmTransactionFunction() {
		return _dtmTransactionFunction;
	}

	public JTable getTblTransactionFunction() {
		return _tblTransactionFunction;
	}
	
	public JScrollPane getSpLog() {
		return this._spLog;
	}
	
	public DefaultTableModel getDtmLog() {
		return this._dtmLog;
	}
	
	public JTable getTblLog() {
		return this._tblLog;
	}
}
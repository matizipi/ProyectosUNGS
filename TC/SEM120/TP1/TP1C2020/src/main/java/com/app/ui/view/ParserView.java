package com.app.ui.view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.app.ui.view.adaptation.CommonTableCellRenderer;
import com.app.ui.view.adaptation.CustomTableModel;
import com.app.ui.view.adaptation.LogTableCellRenderer;

public class ParserView extends NFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel _contentPanel;
	
	private static String IMGPATH = "";
	
	private ImageIcon _imgBackground;
	
	/* Dimensions. */
	int w = 1000;
	int h = 600;
	
	/* Components to import parser from file. */
	private JLabel _lblFile;
	private JTextField _txtFile;
	private JButton _btnFile;

	/* Return */
	private JButton _btnReturn;
	
	/* Input to accept. */
	private JLabel _lblInput;
	private JTextField _txtInput;
	private JButton _btnInput;
	
	/* Button validation table */
	private JButton _btnParse;
	
	/* Tables first and follow. */
	public static String[] FIRST_TITLE = new String[] {"First"};
	private JScrollPane _spFirst;
	private DefaultTableModel _dtmFirst;
	private JTable _tblFirst;
	
	public static String[] FOLLOW_TITLE = new String[] {"Follow"};
	private JScrollPane _spFollow;
	private DefaultTableModel _dtmFollow;
	private JTable _tblFollow;
	
	/* Table parsing. */
	public static String[] PARSING_TITLE = new String[] {"Parsing Table"};
	private JScrollPane _spParsingTable;
	private DefaultTableModel _dtmParsingTable;
	private JTable _tblParsingTable;
	
	/* Log table */
	public static String[] LOG_TITLE = new String[] {"Info", "Descripción"};
	private JScrollPane _spLog;
	private DefaultTableModel _dtmLog;
	private JTable _tblLog;
	
	public ParserView() {
		
		this._imgBackground = new ImageIcon( IMGPATH );
		
		this.setTitle( ".: Trabajo Practico - Parser :." );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
/*
		this.setBounds( 0
				, 0
				,  this._imgBackground.getIconWidth() + this._xOsDifference - spX
				,  this._imgBackground.getIconHeight() + this._yOsDifference - spY);
*/
		this.setBounds( 0
				, 0
				, this.w + this._xOsDifference/*600*/
				, this.h + this._yOsDifference/*427*/);
		this._contentPanel = new JPanel();
		this._contentPanel.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this._contentPanel.setLayout( null );
		this.setContentPane( this._contentPanel );
		this.setLocationRelativeTo( null );
		
		
		/* Load File. */
		this._lblFile = new JLabel( "File:" );
		this._lblFile.setBounds( this.spX
				, this.spY
				, 50
				, this.fldH );
		this._contentPanel.add( this._lblFile );
		
		
		this._txtFile = new JTextField();
		this._txtFile.setColumns( 10 );
		this._txtFile.setBounds( this._lblFile.getX() + this._lblFile.getWidth() + this.spX
				, this.spY
				, 650
				, this.fldH );
		this._contentPanel.add( this._txtFile );
		
		
		this._btnFile = new JButton( " Importar " );
		this._btnFile.setBounds( this._txtFile.getX() + this._txtFile.getWidth() + this.spX
				, this.spY
				, 90
				, this.fldH );
		this._contentPanel.add( this._btnFile );
		
		
		/* Return button. */
		this._btnReturn = new JButton( "Volver" );
		this._btnReturn.setBounds( this.w - 100 - this.spX
				, this.spY
				, 100
				, this.fldH );
		this._contentPanel.add( this._btnReturn );
		
		
		/* Input to process. */
		this._lblInput = new JLabel( "Input: " );
		this._lblInput.setBounds( this.spX
				, this._lblFile.getY() + this.fldH + this.spY
				, 50
				, this.fldH );
		this._contentPanel.add( this._lblInput );
		
		
		this._txtInput = new JTextField();
		this._txtInput.setColumns( 10 );
		this._txtInput.setBounds( this._lblInput.getX() + this._lblInput.getWidth()  + this.spX
				, this._lblFile.getY() + this.fldH + this.spY
				, 200
				, this.fldH );
		this._contentPanel.add( this._txtInput );
		
		
		this._btnInput = new JButton( "Procesar" );
		this._btnInput.setBounds( this._txtInput.getX() + this._txtInput.getWidth() + this.spX
				, this._lblFile.getY() + this.fldH + this.spY
				, 80
				, this.fldH );
		this._contentPanel.add( this._btnInput );
		
		
		/* Button to show table of validation. */
		this._btnParse = new JButton();
		this._btnParse.setBounds( this._btnInput.getX() + this._btnInput.getWidth() + this.spX
				, this._lblFile.getY() + this.fldH + this.spY
				, this.fldH
				, this.fldH);
		this._btnParse.setIcon( null );
		this._contentPanel.add( this._btnParse );
		
		
		/* Tables of firsts and follows. */
		/* First. */
		this._spFirst = new JScrollPane();
		this._spFirst.setBounds( this.spX
				, this._txtInput.getY() + this._txtInput.getHeight() + this.spY
				, ( this.w - this.spX * 2 ) * 30 / 100
				, 170 );
		this._spFirst.setBorder( BorderFactory.createTitledBorder( null, "Tabla Firt", TitledBorder.LEFT, TitledBorder.TOP ) );
		this._spFirst.setOpaque( false );
		this._contentPanel.add( this._spFirst );
		
		this._dtmFirst = new CustomTableModel( null, FIRST_TITLE );
		this._tblFirst = new JTable( this._dtmFirst );
		this._tblFirst.setDefaultRenderer( String.class,  new CommonTableCellRenderer() );
		this._spFirst.setViewportView( this._tblFirst );
		
		
		/* Follow. */
		this._spFollow = new JScrollPane();
		this._spFollow.setBounds( this.spX
				, this._spFirst.getY() + this._spFirst.getHeight() + this.spY
				, ( this.w - this.spX * 2 ) * 30 / 100
				, 170 );
		this._spFollow.setBorder( BorderFactory.createTitledBorder( null, "Tabla Follow", TitledBorder.LEFT, TitledBorder.TOP ) );
		this._spFollow.setOpaque( false );
		this._contentPanel.add( this._spFollow );
		
		this._dtmFollow = new CustomTableModel( null, FOLLOW_TITLE );
		this._tblFollow = new JTable( this._dtmFollow );
		this._tblFollow.setDefaultRenderer( String.class, new CommonTableCellRenderer() );
		this._spFollow.setViewportView( this._tblFollow );
		
		
		/* Parsing table. */
		this._spParsingTable = new JScrollPane();
		this._spParsingTable.setBounds( this._spFirst.getX() + this._spFirst.getWidth() + this.spX
				, this._spFirst.getY()
				, (this.w - this.spX * 2 ) * 70 / 100 - ( this.spX * 100 / this.w )
				, this._spFirst.getHeight() * 2 + this.spY );
		this._spParsingTable.setBorder( BorderFactory.createTitledBorder( null, "Tabla de Parsing.", TitledBorder.LEFT, TitledBorder.TOP ) );
		this._spParsingTable.setOpaque( false );
		this._contentPanel.add( this._spParsingTable );
		
		this._dtmParsingTable = new CustomTableModel( null, PARSING_TITLE );
		this._tblParsingTable = new JTable( this._dtmParsingTable );
		this._tblParsingTable.setDefaultRenderer( String.class, new CommonTableCellRenderer() );
		this._spParsingTable.setViewportView( this._tblParsingTable );
		
		
		/* Log table. */
		this._spLog = new JScrollPane();
		this._spLog.setBounds( this.spX
				, this._spFollow.getY() + this._spFollow.getHeight() + this.spY
				, this.w - this.spY * 2
				, this.h - ( this._spFollow.getY() + this._spFollow.getHeight() ) - 2 * this.spY );
		this._spLog.setBorder( BorderFactory.createTitledBorder( null, "Tabla de Seguimiento", TitledBorder.LEFT, TitledBorder.TOP )  );
		this._spLog.setOpaque( false );
		this._contentPanel.add( this._spLog );
		
		this._dtmLog = new CustomTableModel( null, LOG_TITLE);
		this._tblLog = new JTable( this._dtmLog );
		this._tblLog.setDefaultRenderer( String.class, new LogTableCellRenderer() );
		this._spLog.setViewportView( this._tblLog );
	}

	public JPanel getContentPanel() {
		return this._contentPanel;
	}

	public ImageIcon getImgBackground() {
		return this._imgBackground;
	}

	public JLabel getLblFile() {
		return this._lblFile;
	}

	public JTextField getTxtFile() {
		return this._txtFile;
	}

	public JButton getBtnFile() {
		return this._btnFile;
	}

	public JButton getBtnReturn() {
		return this._btnReturn;
	}
	
	public JLabel getLblInput() {
		return this._lblInput;
	}

	public JTextField getTxtInput() {
		return this._txtInput;
	}

	public JButton getBtnInput() {
		return this._btnInput;
	}
	
	public JButton getBtnParse() {
		return this._btnParse;
	}
	
	public JScrollPane getSpFirst() {
		return this._spFirst;
	}
	
	public DefaultTableModel getDtmFirst() {
		return this._dtmFirst;
	}
	
	public JTable getTblFirst() {
		return this._tblFirst;
	}
	
	public JScrollPane getSpFollow() {
		return this._spFollow;
	}
	
	public DefaultTableModel getDtmFollow() {
		return this._dtmFollow;
	}
	
	public JTable getTblFollow() {
		return this._tblFollow;
	}
	
	public JScrollPane getSpParsingTable() {
		return this._spParsingTable;
	}
	
	public DefaultTableModel getDtmParsingTable() {
		return this._dtmParsingTable;
	}
	
	public JTable getTblParsingTable() {
		return this._tblParsingTable;
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
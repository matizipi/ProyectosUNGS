package com.app.ui.dialog;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.app.ui.view.adaptation.CommonTableCellRenderer;
import com.app.ui.view.adaptation.CustomTableModel;

public class TableOfEquivalenceDialog extends CustomDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private int _w = 500;
	private int _h = 400;
	
	private JPanel _contentPanel;
	
	public static String[] TABLE_TITLE = new String[] { "Stado DFA", "Estadod NFA" };
	private JScrollPane _spEquivalence;
	private DefaultTableModel _dtmEquivalence;
	private JTable _tblEquivalence;
	
	private JButton _btnClose;

	
	public TableOfEquivalenceDialog( JFrame parent ) {
		super( parent );
		this.setTitle( ".: Tabla de equivalencias :." );
		this.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		this.setBounds( 0
				, 0
				, this._w + this._xOsDifference
				, this._h + this._yOsDifference );
		this._contentPanel = new JPanel();
		this._contentPanel.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this._contentPanel.setLayout( null );
		this.setContentPane( this._contentPanel );
		this.setLocationRelativeTo( null );
		
		this.setModalityType( DEFAULT_MODALITY_TYPE );
		
		this._spEquivalence = new JScrollPane();
		this._spEquivalence.setBounds( this.spX
				, this.spY
				, this._w - this.spX
				, this._h - 3 * this.spY - this.fldH );
		this._contentPanel.add( this._spEquivalence );
		
		this._dtmEquivalence = new CustomTableModel( null, TABLE_TITLE );
		this._tblEquivalence = new JTable( this._dtmEquivalence );
		this._tblEquivalence.setDefaultRenderer( String.class, new CommonTableCellRenderer() );
		this._spEquivalence.setViewportView( this._tblEquivalence );
		
		this._btnClose = new JButton( "Cerrar" );
		this._btnClose.setBounds( this._w - 100
				, this._spEquivalence.getY() + this._spEquivalence.getHeight() + this.spY
				, 100
				, this.fldH );
		this._contentPanel.add( this._btnClose );
	}
	
	public JScrollPane getSpEquivalence() {
		return this._spEquivalence;
	}
	
	public DefaultTableModel getDtmEquivalence() {
		return this._dtmEquivalence;
	}
	
	public JTable getTblEquivalence() {
		return this._tblEquivalence;
	}
	
	public JButton getBtnClose() {
		return this._btnClose;
	}
}
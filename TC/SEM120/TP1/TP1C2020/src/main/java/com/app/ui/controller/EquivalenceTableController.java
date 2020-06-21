package com.app.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import com.app.ui.dialog.TableOfEquivalenceDialog;

public class EquivalenceTableController extends Controller {

	private TableOfEquivalenceDialog _dialog;
	private JFrame _parent;
	private String[][] _table;
	
	public EquivalenceTableController( JFrame parent, String[][] table ) {
		this._parent = parent;
		this._table = table;
		this._dialog = new TableOfEquivalenceDialog( this._parent );
		
		this.addActionListener();
	}
	
	private void addActionListener() {
		this._dialog.getBtnClose().addActionListener( this );
	}
	
	@Override
	public void start() {
		this.loadTable();
		this._dialog.setVisible( true );
	}

	@Override
	public void finish() {
		this._dialog.setVisible( false );
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if( arg0.getSource() == this._dialog.getBtnClose() ) {
			this.finish();
		}
	}
	
	private void loadTable() {
	
		DefaultTableModel dtm = this._dialog.getDtmEquivalence();
		
		dtm.setColumnCount( 0 );
		dtm.setRowCount( 0 );
		dtm.setColumnIdentifiers( TableOfEquivalenceDialog.TABLE_TITLE );
		
		for( int i = 0; i < this._table.length; i++ ) {
			dtm.addRow( this._table[i] );
		}
		
		this._dialog.getTblEquivalence().setModel( dtm );
	}
}
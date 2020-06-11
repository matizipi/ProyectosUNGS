package com.app.ui.view.adaptation;

import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel {
    
	public CustomTableModel( String[][] data, String[] titles ) {
		super( data, titles );
	}
	
    @Override
    public Class getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
	
}
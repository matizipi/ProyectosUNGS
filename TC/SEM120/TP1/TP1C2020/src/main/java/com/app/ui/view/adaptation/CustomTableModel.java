package com.app.ui.view.adaptation;

import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomTableModel( String[][] data, String[] titles ) {
		super( data, titles );
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Class getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
	
}
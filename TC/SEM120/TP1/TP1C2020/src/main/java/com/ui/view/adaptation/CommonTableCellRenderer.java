package com.ui.view.adaptation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CommonTableCellRenderer extends DefaultTableCellRenderer {
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		Component c;
		c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		c.setBackground( row % 2 == 0 ? Color.WHITE/*getBackground()*/ : new Color( 210, 239, 255 ) );
		
		c.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
		
		return c;
	}

}
package com.ui.view.adaptation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import helper.Msg;

public class LogTableCellRenderer extends DefaultTableCellRenderer {
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		Component c;
		c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		String type;
		
		c.setBackground( row % 2 == 0 ? Color.WHITE/*getBackground()*/ : new Color( 220, 220, 220 ) );
		
		int columnCheck = column;
		
		if ( column != 0 ) {
			columnCheck = 0;
		}
		
		type = table.getValueAt(row, columnCheck).toString();
		
		if ( type.equals( Msg.getType( Msg.INFO ) ) ) {
			//c.setForeground( new Color( 119, 185, 94 ) );
			c.setForeground( new Color( 50, 150, 50 ) );
		}
		
		if( type.equals( Msg.getType( Msg.ERROR ) ) ) {
			c.setForeground( Color.RED );
		}
		
		c.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
		
		return c;
	}

}
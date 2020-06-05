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
		
		if( row % 2 != 0 ) {
			c.setBackground( Color.LIGHT_GRAY );
		}
		
		int columnCheck = column;
		
		if ( column != 0 ) {
			columnCheck = 0;
		}
		
		type = table.getValueAt(row, columnCheck).toString();
		
		if ( type.equals( Msg.getType( Msg.INFO ) ) ) {
			c.setForeground( Color.GREEN );
//			c.setFont( new Font( "Dialog", Font.BOLD, 12 ) );
			System.out.println( c.getFont().toString() );
		}
		
		if( type.equals( Msg.getType( Msg.ERROR ) ) ) {
			c.setForeground( Color.RED );
		}
		
		return c;
	}

}
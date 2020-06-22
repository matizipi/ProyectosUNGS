package com.app.ui.view.adaptation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.app.common.Msg;
import com.app.helper.TpConfiguration;

public class LogTableCellRenderer extends DefaultTableCellRenderer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		Component c;
		c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		String type;
		
		TpConfiguration tc = TpConfiguration.getInstance();
		
		c.setBackground( row % 2 == 0 ? tc.getColorTo( TpConfiguration.LOG_PAR_ROW )/*new Color( 255, 255, 255)*/ : tc.getColorTo( TpConfiguration.LOG_INPAR_ROW )/*new Color( 220, 220, 220 )*/ );
		
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
		
		if( isSelected ) {
			if ( tc.isActiva( TpConfiguration.LOG_SELECTED ) == true ){
				c.setBackground( tc.getColorTo( TpConfiguration.LOG_SELECTED ) );
			}
			c.setFont( new Font( "Tahoma", Font.BOLD, 12 ) );
		} else {
			c.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
		}
		
		return c;
	}

}
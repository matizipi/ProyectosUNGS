package com.app.ui.view.adaptation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.app.helper.TpConfiguration;

public class CommonTableCellRenderer extends DefaultTableCellRenderer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		Component c;
		c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		TpConfiguration tc = TpConfiguration.getInstance();
		
		if( isSelected ) {

			c.setFont( new Font( "Tahoma", Font.BOLD, 12 ) );
			c.setForeground( Color.BLACK );
			c.setBackground( tc.getColorTo( TpConfiguration.COMMON_SELECTED )/*new Color( 190, 190, 190 )*/ );
			
		} else {
			c.setBackground( row % 2 == 0 ? tc.getColorTo( TpConfiguration.COMMON_PAR_ROW )/*new Color( 255, 255, 255 )*/ : tc.getColorTo( TpConfiguration.COMMON_INPAR_ROW ) /*new Color( 210, 239, 255 )*/ );
			
			c.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
		}
		
		return c;
	}

}
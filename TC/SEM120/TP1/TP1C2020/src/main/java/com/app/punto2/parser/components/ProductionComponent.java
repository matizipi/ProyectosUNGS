package com.app.punto2.parser.components;

import java.util.ArrayList;
import java.util.List;

import com.app.common.Msg;
import com.app.common.log.Log;

public abstract class ProductionComponent {

	public ProductionComponent() {
	}

	public static ProductionComponent[] arrayPrdCompFromString(String string) {
		
		List< ProductionComponent > lstPrd = new ArrayList<ProductionComponent>();
		
		for( int i = 0; i < string.length(); i++ ) {
			if ( string.charAt( i ) == 'X' ) {
				Log.WriteFileLog( new Msg( Msg.INFO, ProductionComponent.class, "i: " + i + " - " + string.substring( i , string.indexOf( "}" , i ) + 1 ) ) );
				lstPrd.add( new Variable( string.substring( i , string.indexOf( "}" , i ) +1 ) ) );
				i += string.substring( i , string.indexOf( "}" , i ) ).length();
				Log.WriteFileLog( new Msg( Msg.INFO, ProductionComponent.class, "i: " + i ) );
			} else {
				Log.WriteFileLog( new Msg( Msg.INFO, ProductionComponent.class, "i: " + i + " - " + String.valueOf( string.charAt( i ) ) ) );
				lstPrd.add( new Terminal( String.valueOf( string.charAt( i ) ) ) );
			}
		}
		
		return (ProductionComponent[]) lstPrd.toArray();
	}
}
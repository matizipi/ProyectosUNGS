package com.app.punto2.parser.components;

public abstract class ProductionComponent {

	public ProductionComponent() {
	}
	
	public static ProductionComponent[] arrayPrdCompFromString(String string) {
		
		int length = 0;
		int a = 0;
		
		while( a < string.length() ) {
//			Log.WriteFileLog( new Msg( Msg.INFO, ProductionComponent.class, "a0: " + a ) );
			if( string.charAt( a ) == 'X' ) {
				a += ( string.indexOf( "}", a) - a ) + 1;
//				Log.WriteFileLog( new Msg( Msg.INFO, ProductionComponent.class, "a1: " + a ) );
			} else {
				a++;
//				Log.WriteFileLog( new Msg( Msg.INFO, ProductionComponent.class, "a2: " + a ) );
			}
			length++;
		}
		
		ProductionComponent[] array = new ProductionComponent[ length ];
//		Log.WriteFileLog( new Msg( Msg.INFO, null, "largo: " + array.length ) );
		
		int stringIterator = 0;
		
		for( int i = 0; i < array.length; i++ ) {
			if ( string.charAt( stringIterator ) == 'X' ) {
//				Log.WriteFileLog( new Msg( Msg.INFO, ProductionComponent.class, "posicion " + i + ": " + stringIterator + " - " + string.substring( stringIterator , string.indexOf( "}" , stringIterator ) + 1 ) ) );
				array[i] = new Variable( string.substring( stringIterator , string.indexOf( "}" , stringIterator ) + 1 ) );
				stringIterator += string.substring( stringIterator , string.indexOf( "}" , stringIterator ) + 1 ).length();
//				Log.WriteFileLog( new Msg( Msg.INFO, ProductionComponent.class, "posiscion " + i + " stringIterator: " + stringIterator ) );
			} else {
//				Log.WriteFileLog( new Msg( Msg.INFO, ProductionComponent.class, "posiscion " + i + " stringiterator: " + stringIterator + " - " + String.valueOf( string.charAt( stringIterator ) ) ) );
				array[i] = new Terminal( String.valueOf( string.charAt( stringIterator ) ) );
				stringIterator++;
			}
		}
		
		return array;
	}
	
	public static ProductionComponent getFinalComponent() {
		return new Terminal( "$" );
	}
	
	abstract public boolean isTerminal();
	
	abstract public boolean isInitSymbol();
	
	@Override
	abstract public String toString();
	
	@Override
	abstract public boolean equals( Object obj );	
}
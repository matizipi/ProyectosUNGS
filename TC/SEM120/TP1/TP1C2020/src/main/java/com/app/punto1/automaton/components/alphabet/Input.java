package com.app.punto1.automaton.components.alphabet;

public class Input {
	
	private Symbol[] _coInput;
	private int _pivot;
	
	public Input( String input ) {
		
		this._coInput = new Symbol[ input.length() ];
		
		for( int i = 0; i < input.length(); i++ ) {
			this._coInput[ i ] = new Symbol( input.charAt( i ) );
		}
		
		this._pivot = 0;
	}
	
	public Input(Symbol[] in) {
		this._coInput = in;
	}

	private Input( Symbol[] in, int pivot ) {
		this._coInput = in;
		this._pivot = pivot;
	}
	
	public boolean hasNextSymbol() {
		return this._coInput.length>this._pivot?true:false;
	}
	
	public Symbol readSymbol() {
		int pivotAux = this._pivot;
		this._pivot++;
		return this._coInput[ pivotAux ];
	}
	
	public void resetRead() {
		this._pivot = 0;
	}
	
	public Input copy() {
		
		Input inp = new Input( this.getSimbolsOfInput(), this._pivot);
		
		return inp;
	}
	
	public Symbol[] getSimbolsOfInput() {
		return this._coInput;
	}
	
	public int getPivot() {
		return this._pivot;
	}
	
	@Override
	public String toString() {
		
		String str = "";
		
		for( Symbol sbm : this._coInput ) {
			str += sbm.getSimbol();
		}
		
		return str;
	}
}
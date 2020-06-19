package com.app.punto1.automaton.components.alphabet;

import java.util.ArrayList;
import java.util.List;

import com.app.common.Msg;

public class Alphabet {

	private List< Symbol > _lstSimbols;
	private List< Msg > _lstMsg;

	/** Take a lot of symbols to generate a new alphabet. **/
	public Alphabet(Symbol... symbols) {

		/* Init internal objects. */
		this._lstSimbols = new ArrayList< Symbol >();
		this._lstMsg = new ArrayList< Msg >();

		/* Add all symbols into alphabet. */
		for ( int i = 0; i < symbols.length; i++ ) {
			if( this._lstSimbols.contains( symbols[i] ) == false ) {
				this._lstSimbols.add( symbols[i] );
				this._lstMsg.add( new Msg( Msg.INFO, this, "Simbolo agregado alfabeto: " + symbols[ i ].getSimbol() + "." ) );
			}
		}
	}

	/** Add new symbols to alphabet. **/
	public void addSimbols( Symbol... symbols ) {

		this._lstMsg.clear();

		for( int i = 0; i < symbols.length; i++ ) {
			/* If symbols exists into alphabet dont add this again this symbol. */
			if( this._lstSimbols.contains( symbols[i] ) == false ) {
				this._lstSimbols.add( symbols[i] );
				this._lstMsg.add( new Msg( Msg.INFO, this, "Simbolo agregado alfabeto: " + symbols[ i ].getSimbol() + "." ) );
			}
		}
	}

	/** Return true if the input have all symbols into the alphabet. **/
	public boolean accept(Input input) {

		this._lstMsg.clear();

		boolean inputExists = false;
		Symbol[] symbolsOfInput = input.getSimbolsOfInput();

		/* For each symbol in input. */
		for( int i = 0; i < symbolsOfInput.length; i++ ) {

			inputExists = false;
			/* Search the symbol into the alphabet. */
			for( int j = 0; j < this._lstSimbols.size(); j++ ) {
				if( symbolsOfInput[ i ].equals( this._lstSimbols.get( j ) ) ) {
					inputExists = true;
					break;
				}
			}
			/* If the symbol not exists into the alphabet, no accept the input and add a description message.. */
			if ( inputExists == false ) {
				this._lstMsg.add( new Msg( Msg.ERROR, this, "El simbolo: " + symbolsOfInput[i].getSimbol() + " no pertenece al alfabeto." ) );
				return false;
			}
		}

		return true;
	}

	/**
	 *
	 * @param line
	 * @return Alphabet.
	 */
	public static Alphabet generateFrom( String line ) {

		/* Erase the empty spaces and split by ",". */
		String[] stringSimbols = line.replaceAll(" ", "").split(",");
		Symbol[] smb = new Symbol[ stringSimbols.length ];

		/* For each char create a new symbol. */
		for( int i = 0; i < smb.length; i++ ) {
			smb[i] = new Symbol( stringSimbols[i] );
		}

		return new Alphabet( smb );
	}

	/** Return a array of String for each symbol. **/
	public String[] getArrayStringOfSimbols() {

		String[] array = new String[ this._lstSimbols.size() ];

		int i = 0;
		/* For each symbol get the string of symbol. */
		for ( Symbol smb: this._lstSimbols ) {
			array[i] = smb.getSimbol();
			i++;
		}

		return array;
	}

	/** Return a list of symbols into the alphabet. **/
	public List<Symbol> getSimbols() {
		return this._lstSimbols;
	}

	/** return all messages from last function. **/
	public List< Msg > getMsgs() {
		return this._lstMsg;
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return false;
		}

		if( Alphabet.class.isAssignableFrom( obj.getClass() ) == false ) {
			return false;
		}

		Alphabet alph = ( Alphabet ) obj;
		if( this.haveSameSymbolsThat( alph ) == false ) {
			return false;
		}

		return true;
	}

	/** Return true if have the same size and symbols. **/
	private boolean haveSameSymbolsThat( Alphabet alph ) {

		boolean exists = false;

		/* Verify the sizes. */
		if( ( this.getSimbols().size() == alph.getSimbols().size() ) == false ) {
			return false;
		}

		/* Verify the symbols. */
		for( Symbol smb: alph.getSimbols() ) {
			exists = false;
			for( Symbol thisSmb : this.getSimbols() ) {
				if( smb.equals( thisSmb ) ) {
					exists = true;
				}
			}
			/* If any symbol not exists, return false. */
			if( exists == false ) {
				return false;
			}
		}

		/* All symbols in both alphabets are the same. */
		return true;
	}
}
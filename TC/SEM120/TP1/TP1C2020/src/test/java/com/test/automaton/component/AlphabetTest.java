package com.test.automaton.component;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.punto1.automaton.components.alphabet.Alphabet;
import com.app.punto1.automaton.components.alphabet.Symbol;

class AlphabetTest {

	static String _line;
	static Alphabet _alphabet;
	
	@BeforeAll
	static void setup() {
		_line = "a,b,c,d";
		_alphabet = Alphabet.generateFrom( _line );
	}
	
	@Test
	void alphabetCountTest() {
		int actual = _alphabet.getSimbols().size();
		int expected = 4;
		assertEquals( expected, actual );
	}
	
	@Test
	void alphabetContainTest() {
		Symbol actual = _alphabet.getSimbols().get(0);
		Symbol expected = new Symbol( 'a' );
		assertEquals( expected, actual );
	}
	
	@Test
	void alphabetNoContainTest() {
		
		Symbol symbol = new Symbol( 'e' );
		boolean actual = false;
		
		for( Symbol smb : _alphabet.getSimbols() ) {
			if( smb.equals( symbol ) ) {
				actual = true;
			}
		}
		
		boolean expected = false;
		
		assertEquals( expected,actual );
	}
	
	@Test
	void sameAlphabetTest() {
		Alphabet actual = _alphabet;
		Alphabet expected = Alphabet.generateFrom( "d,c,b,a " );
		
		assertEquals( expected, actual );
		
	}
	
	@Test
	void sameAlphabetTest02(){
		Alphabet actual = _alphabet;
		Alphabet expected = Alphabet.generateFrom( "a, a, b, b,c,c,   d,d " );
		
		assertEquals( expected, actual );
	}
}
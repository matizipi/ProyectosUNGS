package com.test.Automata.DeterministicFinite;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.helper.ObjectConverter;
import com.app.punto1.automaton.NFA;
import com.app.punto1.automaton.components.alphabet.Input;

class DFATest {

	static String FILE = "src/test/resources/automatonFiles/NoDeterministicAutomaton.txt";
	static ObjectConverter _oc;
	static NFA _noDeterministicAutomaton;
	
	@BeforeAll
	static void setup() {
		_oc = new ObjectConverter();
		File file = new File( FILE );
		_noDeterministicAutomaton = _oc.newNFAFromFile( file );
	}
	
	@Test
	void testInput_raa() {
		boolean accept = _noDeterministicAutomaton.accept( new Input( "raa" ) );
		assertTrue( accept );
	}
	
	@Test
	void testInput_arr() {
		boolean accept = _noDeterministicAutomaton.accept( new Input( "arr" ) );
		assertFalse( accept );
	}
}

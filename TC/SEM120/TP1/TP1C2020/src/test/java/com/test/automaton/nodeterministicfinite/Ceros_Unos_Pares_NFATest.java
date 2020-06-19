package com.test.automaton.nodeterministicfinite;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.helper.ObjectConverter;
import com.app.punto1.automaton.NFA;
import com.app.punto1.automaton.components.alphabet.Input;

class Ceros_Unos_Pares_NFATest {

	static String FILE = "src/test/resources/automatonFiles/DFA_0sy_1sPares.txt";
	static File _file;
	static ObjectConverter _oc;
	static NFA _noDeterministicAutomaton;
	
	@BeforeAll
	static void setup() {
		_oc = new ObjectConverter();
		_file = new File( FILE );
		_noDeterministicAutomaton = _oc.newNFAFromFile( _file );
	}
	

	@Test
	void notNullFileTest() {
		assertNotNull( _file );
	}
	
	@Test
	void automatonAccept_1() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "1" ) );
		
		assertFalse( condition );
	}
	
	@Test
	void automatonAccept_0() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "0" ) );
		
		assertFalse( condition );
	}
	
	void automatonAccept_11() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "11" ) );
		
		assertTrue( condition );
	}
	
	@Test
	void automatonAccept_00() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "00" ) );
		
		assertTrue( condition );
	}
	
	@Test
	void automatonAccept_001() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "001" ) );
		
		assertFalse( condition );
	}
	
	@Test
	void automatonAccept_110() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "110" ) );
		
		assertFalse( condition );
	}
	
	@Test
	void automatonAccept_10() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "10" ) );
		
		assertFalse( condition );
	}
	
	@Test
	void automatonAccept_01() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "01" ) );
		
		assertFalse( condition );
	}
	
	@Test
	void automatonAccept() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "" ) );
		
		assertTrue( condition );
	}
	
	@Test
	void automatonAccept_1010() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "1010" ) );
		
		assertTrue( condition );
	}
	
	@Test
	void automatonAccept_0101() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "0101" ) );
		
		assertTrue( condition );
	}
	
	@Test
	void automatonAccept_1100() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "1100" ) );
		
		assertTrue( condition );
	}
	
	@Test
	void automatonAccept_0011() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "0011" ) );
		
		assertTrue( condition );
	}
}

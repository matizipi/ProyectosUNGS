package com.test.automaton.nodeterministicfinite;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.helper.ObjectConverter;
import com.app.punto1.automaton.NFA;
import com.app.punto1.automaton.components.alphabet.Input;

class SinUnosConsecutivosNFATest {

	static String FILE = "src/test/resources/automatonFiles/DFA_sin_1sConsecutivos.txt";
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
	void automatonAccept_11() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "11" ) );
		
		assertFalse( condition );
	}
	
	@Test
	void automatonAccept_110() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "110" ) );
		
		assertFalse( condition );
	}
	
	@Test
	void automatonAccept_011() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "011" ) );
		
		assertFalse( condition );
	}
	
	@Test
	void automatonAccept() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "" ) );
		
		assertTrue( condition );
	}
	
	@Test
	void automatonAccept_1() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "1" ) );
		
		assertTrue( condition );
	}
	
	@Test
	void automatonAccept_10() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "10" ) );
		
		assertTrue( condition );
	}
	
	@Test
	void automatonAccept_01() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "01" ) );
		
		assertTrue( condition );
	}
	
	@Test
	void automatonAccept_101() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "101" ) );
		
		assertTrue( condition );
	}
	
	@Test
	void automatonAccept_010() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "010" ) );
		System.out.println( condition + "sss" );
		assertTrue( condition );
	}
	
	@Test
	void automatonAccept_0101() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "0101" ) );
		
		assertTrue( condition );
	}
}

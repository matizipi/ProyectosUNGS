package com.test.automaton.nodeterministicfinite;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.helper.ObjectConverter;
import com.app.punto1.automaton.NFA;
import com.app.punto1.automaton.components.alphabet.Input;

class TableroNFATest {

	static String FILE = "src/test/resources/automatonFiles/NoDeterministicAutomatonTablero.txt";
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
	void automatonAccept_a() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "a" ) );
		
		assertFalse( condition );
	}
	
	@Test
	void automatonAccept_r() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "r" ) );
		
		assertFalse( condition );
	}
	
	@Test
	void automatonAccept_ra() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "ra" ) );
		
		assertFalse( condition );
	}
	
	@Test
	void automatonAccept_ar() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "ar" ) );
		
		assertFalse( condition );
	}
	
	@Test
	void automatonAccept_rr() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "rr" ) );
		
		assertFalse( condition );
	}
	
	@Test
	void automatonAccept_rar() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "rar" ) );
		
		assertFalse( condition );
	}
	
	@Test
	void automatonAccept_arr() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "arr" ) );
		
		assertFalse( condition );
	}
	
	@Test
	void automatonAccept_raa() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "raa" ) );
		
		assertTrue( condition );
	}
	
	@Test
	void automatonAccept_rra() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "rra" ) );

		assertTrue( condition );
	}
	
	@Test
	void automatonAccept_aa() {
		boolean condition = _noDeterministicAutomaton.accept( new Input( "aa" ) );

		assertTrue( condition );
	}
}
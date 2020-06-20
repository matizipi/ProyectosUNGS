package com.test.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.helper.ObjectConverter;
import com.app.punto2.parser.Parser;

class ParentesisParserTest {
	
	static String _filePath = "src/test/resources/parserFiles/parentesisParser.txt";
	static File _file;
	static ObjectConverter _oc;
	static Parser _parser;
	
	/* No accept. */
	static String _str01 = "(";
	static String _str02 = ")";
	static String _str03 = ")(";
	
	/* Accept */
	static String _str04 = "()";
	static String _str05 = "()()";
	static String _str06 = "(())()";
	static String _str07 = "(())(())";
	static String _str08 = "(())";
	
	@BeforeAll
	static void setUp() {
		_file = new File( _filePath );
		_oc = new ObjectConverter();
		_parser = _oc.parserTopDownNoRecursiveFromFile( _file, "@" );
	}
	
	@Test
	void fileExistsTest() {
		boolean condition = _file.exists();
		
		assertTrue( condition );
	}
	
	@Test
	void accept_string01_Test() {
		boolean condition = _parser.AcceptString( _str01 );
		
		assertFalse( condition );
	}
	
	@Test
	void accept_string02_Test() {
		boolean condition = _parser.AcceptString( _str02 );
		
		assertFalse( condition );
	}
	
	@Test
	void accept_string03_Test() {
		boolean condition = _parser.AcceptString( _str03 );
		
		assertFalse( condition );
	}
	
	@Test
	void accept_string04_Test() {
		boolean condition = _parser.AcceptString( _str04 );
		
		assertTrue( condition );
	}
	
	@Test
	void accept_string05_Test() {
		boolean condition = _parser.AcceptString( _str05 );
		
		assertTrue( condition );
	}
	
	@Test
	void accept_string06_Test() {
		boolean condition = _parser.AcceptString( _str06 );
		
		assertTrue( condition );
	}
	
	@Test
	void accept_string07_Test() {
		boolean condition = _parser.AcceptString( _str07 );
		
		assertTrue( condition );
	}
	
	@Test
	void accept_string08_Test() {
		boolean condition = _parser.AcceptString( _str08 );
		
		assertTrue( condition );
	}
}
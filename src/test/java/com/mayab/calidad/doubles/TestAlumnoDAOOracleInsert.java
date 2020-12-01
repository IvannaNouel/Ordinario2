package com.mayab.calidad.DbUnit;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mayab.calidad.dao.Alumno;
import com.mayab.calidad.dao.AlumnoDAOforTest;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

public class TestAlumnoDAOOracleInsert extends DBTestCase{
	
	public TestAlumnoDAOOracleInsert(String name) {
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.cj.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/DBUnit"
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		IDatabaseConnection conn = getConnection();
		try {
			DatabaseOperation.CLEAN_INSERT.execute(conn, getDataSet());
		}finally {
			conn.close();
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsert() throws Exception {
		IDatabaseConnection conn = getConnection();
		Alumno alumno = new Alumno("Melissa", 20, 1, (float) 9.5, "Melissa@gmail.com");		
		AlumnoDAOforTest functions = new AlumnoDAOforTest();
		int size = conn.getRowCount("Alumnos");
		functions.addAlumno(alumno);
		assertEquals(size + 1, conn.getRowCount("Alumnos"));
		conn.close();
	}
	
	@Test
	public void testDelete() throws Exception{
		IDatabaseConnection conn = getConnection();
		Alumno alumno = new Alumno("Melissa", 20, 1, (float) 9.5, "Melissa@gmail.com");		
		AlumnoDAOforTest functions = new AlumnoDAOforTest();
		int size = conn.getRowCount("Alumnos");
		functions.deleteAlumno(alumno);
		assertEquals(size - 1, conn.getRowCount("Alumnos"));
		conn.close();
	}
	
	@Test
	public void testUpdateAvg() throws Exception{
		Alumno alumno = new Alumno("Melissa", 20, 1, (float) 9.5, "Melissa@gmail.com");		
		AlumnoDAOforTest functions = new AlumnoDAOforTest();
		functions.updatePromedio(alumno);
		
		IDataSet connection = getConnection().createDataSet();
		ITable actualTable = connection.getTable("Alumnos");
		InputStream xmlFile = getClass().getResourceAsStream("/alumno.xml");
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(xmlFile);
	    ITable expectedTable = expectedDataSet.getTable("Alumnos");
		
		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	
	@Test
	public void testGetALL()throws Exception{
		IDataSet connection = getConnection().createDataSet();
		ITable actualTable = connection.getTable("Alumnos");
		InputStream xmlFile = getClass().getResourceAsStream("/alumno.xml");
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(xmlFile);
	    ITable expectedTable = expectedDataSet.getTable("Alumnos");
		
		Assertion.assertEquals(expectedTable, actualTable);
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		InputStream xmlFile = getClass().getResourceAsStream("/alumno.xml");
		return new FlatXmlDataSetBuilder().build(xmlFile);
	}

}

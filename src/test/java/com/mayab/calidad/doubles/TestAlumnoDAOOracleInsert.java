package com.mayab.calidad.doubles;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.mayab.calidad.dao.AlumnoDAOoracle;
import com.mayab.calidad.doubles.Alumno;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.*;

import org.dbunit.DBTestCase;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.dbunit.Assertion;

public class TestAlumnoDAOOracleInsert extends DBTestCase {
	public TestAlumnoDAOOracleInsert(String name)
	{
			super(name);
			System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "oracle.jdbc.driver.OracleDriver"); //.driver.Or...
			System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:oracle:thin:@localhost:1522:xe");
			System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "dbunit");
			System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "dbunit");	
	}
		
	@Before
	public void setUp() throws Exception{
		
		super.setUp();
		IDatabaseConnection con = getConnection();
		try {
			DatabaseOperation.CLEAN_INSERT.execute(con, getDataSet());
		}
		finally {
			con.close();	
		}
	}
	

	@After
	public void tearDown() throws Exception{
		
	}
	
	
	@Test
	public void testInsert() throws Exception {
		
				Alumno a = new Alumno();
				a.setId(2);
				a.setNombre("Melissa");
				a.setEdad(20);
				a.setPromedio(0);
				a.setEmail("prueba@asd.com");
				
				
				AlumnoDAOoracle dao = new AlumnoDAOoracle();
				dao.addAlumno(a);
				IDatabaseConnection con = null;
				int actualRows = 0;
				try {
					con = getConnection();
					//System.out.println(con.getRowCount("alumno"));
					actualRows =  con.getRowCount("alumno");
					
					dao.addAlumno(a);
					
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				assertEquals(actualRows+1, con.getRowCount("alumno"));
				con.close();
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception{
		return new FlatXmlDataSetBuilder().build(new File("src\\resources\\alumno_init.xml"));
	}
}

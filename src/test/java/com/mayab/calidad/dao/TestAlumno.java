package com.mayab.calidad.dao;
import com.mayab.calidad.dao.Alumno;
import com.mayab.calidad.dao.AlumnoDAOforTest;
import static org.junit.Assert.*;

import static org.mockito.Mockito.when;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.hamcrest.Matchers.equalTo;
//import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Matchers.any;

import java.util.HashMap;



public class TestAlumno {
	
	AlumnoDAOforTest dao;
	HashMap<Integer, Alumno> alumnos = new HashMap<Integer, Alumno>();
	
	@Before
	public void setUp() throws Exception {
		dao = Mockito.mock(AlumnoDAOforTest.class);
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testUpdate() {
		when(dao.updatePromedio(any(Alumno.class))).thenAnswer(new Answer<String>() {

			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				alumnos.put(0, new Alumno("Melissa", 20, 1, (float) 9.5, "Melissa@gmail.com"));	
				Alumno alternative = alumnos.get(0);
				double before = alternative.getPromedio();
				alternative.setPromedio((float) 9.5);
				double after = alternative.getPromedio();
				
				if(before != after) {
					return "Se actualizo promedio";
				}else {
					return "No se pudo actualizar promedio";
				}
				
			}
			
		});
		assertThat(dao.updatePromedio(new Alumno("Melissa", 20, 1, (float) 10.0, "Melissa@gmail.com")), is(equalTo("Se actualizo promedio")));
	}
	
	
	@Test
	public void testAddAlumno() {
		when(dao.addAlumno(any(Alumno.class))).thenAnswer(new Answer<String>() {

			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				int size = alumnos.size();
				alumnos.put(0, new Alumno("Ivanna", 19, 2, (float) 8.0, "Ivanna@gmail.com"));
				int newSize = alumnos.size();
				if(size == newSize)
					return "No se pudo agregar";
				else
					return "Se agrego alumno";
				
			}
			
		});
		assertThat(dao.addAlumno(new Alumno("Ivanna", 19, 2, (float) 8.0, "Ivanna@gmail.com"), is(equalTo("Se agrego alumno")));
	}
	
	
	@Test
	public void testRemoveAlumno() {
		when(dao.deleteAlumno(any(Alumno.class))).thenAnswer(new Answer<String>() {

			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				
				alumnos.put(0, new Alumno("Ivanna", 19, 2, (float) 8.0, "Ivanna@gmail.com"));
				int firstSize = alumnos.size();
				
				alumnos.remove(0);
				int lastSize = alumnos.size();
				if(lastSize == firstSize - 1) {
					return "Se elimino alumno";
				}
					
				else {
					return "No se pudo eliminar alumno";
				}
					
			}
			
		});
		assertThat(dao.deleteAlumno(new Alumno("Ivanna", 19, 2, (float) 8.0, "Ivanna@gmail.com")), is(equalTo("Se elimino alumno")));
	}
	
	@Test
	public void testGetAll() {
		when(dao.getAll()).thenAnswer(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				boolean returned = false;
				
				alumnos.put(0, new Alumno("Melissa", 20, 1, (float) 10.0, "Melissa@gmail.com"));
				alumnos.put(1, new Alumno("Ivanna", 19, 2, (float) 8.0, "Ivanna@gmail.com"));
				alumnos.put(2, new Alumno("Vero", 21, 3, (float) 9.5, "vero@gmail.com"));
				
				for(int i = 0; i < alumnos.size(); i++) {
					
					System.out.println(alumnos.get(i));
					
				}
				
				returned = true;
				
				if(returned) {
					return true;
				}else {
					return false;
				}
				
			}
			
		});
	}
	
	

}

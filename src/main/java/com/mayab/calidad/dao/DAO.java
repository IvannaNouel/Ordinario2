package com.mayab.calidad.dao;

import com.mayab.calidad.doubles.Alumno;

public interface DAO {
//public String addAlumno(Alumno alumno);
//public String deleteAlumno(Alumno alumno);
//public String updatePromedio(Alumno alumno);
public boolean getAll();
String addAlumno(com.mayab.calidad.dao.Alumno alumno);
String deleteAlumno(com.mayab.calidad.dao.Alumno alumno);
String updatePromedio(com.mayab.calidad.dao.Alumno alumno);


}

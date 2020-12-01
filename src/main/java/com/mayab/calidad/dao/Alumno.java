package com.mayab.calidad.dao;

public class Alumno {
private String nombre;
private int edad;
private int id;
private float promedio;
private String email;



public Alumno(String nombre, int edad, int id, float promedio, String email) {
	this.nombre = nombre;
	this.edad = edad;
	this.id = id;
	this.promedio = promedio;
	this.email = email;
}

public Alumno() {
	this.nombre = "Doe";
	this.edad = 0;
	this.id = 0;
	this.promedio = 0;
	this.email = "johndoe@aksd.com";
}

public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public int getEdad() {
	return edad;
}
public void setEdad(int edad) {
	this.edad = edad;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public float getPromedio() {
	return promedio;
}
public void setPromedio(float promedio) {
	this.promedio = promedio;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}


	
	
}

package com.maveric.project.pojos;

import com.opencsv.bean.CsvBindByName;

public class EmployeeCsvPojo {
	@CsvBindByName(column="name")
	private String name;
	
	@CsvBindByName(column="salary")
	private int salary;
	
	@CsvBindByName(column="age")
	private int age;

	public EmployeeCsvPojo() {
	}

	public EmployeeCsvPojo(String name, int salary, int age) {
		super();
		this.name = name;
		this.salary = salary;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "EmployeeCsvPojo [name=" + name + ", salary=" + salary + ", age=" + age + "]";
	}
}

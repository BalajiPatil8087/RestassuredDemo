package com.maveric.project.pojos;

public class JsontoList {
      String name,salary;
      int id,age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "JsontoList [name=" + name + ", salary=" + salary + ", id=" + id + ", age=" + age + "]";
	}
	
	
}

package entity;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = 5997970737618137343L;

	public static enum Gender {
		Male("Male"), Female("Female");

		private final String value;

		private Gender(String value) {
			this.value = value;
		}

		public String toStrValue() {
			return value;
		}
	}

	private String name;

	private int age;

	private Gender gender;


	public Person(String name, int age, Gender gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}


	public String getName() {
		return name;
	}


	public void setName(String newName) {
		name = newName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int newAge) {
		age = newAge;
	}


	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender newGender) {
		gender = newGender;
	}
}

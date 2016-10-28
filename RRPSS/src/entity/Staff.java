package entity;


public class Staff {
	private String name;
	private String gender;
	private String employeeID;
	private String jobTitle;
	
	/**
	 * 
	 * @param name The name of the Staff member
	 * @param gender The gender of the Staff member
	 * @param employeeID The Identification Number of the Staff member
	 * @param jobTitle The position of the Staff member
	 */
	public Staff (String name, String gender, String employeeID, String jobTitle){
		this.name = name;
		this.gender = gender;
		this.employeeID = employeeID;
		this.jobTitle = jobTitle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	
	
	
	
	
}

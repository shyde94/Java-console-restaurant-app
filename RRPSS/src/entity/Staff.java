package entity;

/**
 * Staff class defines staff members working in the restaurant
 * @since 9/11/2016
 *
 * @author Shide
 * @author Eeyern
 * @author Grace
 * @author Xi Tong
 */
public class Staff {

    /**
     * name string of the staff
     */
    private String name;

    /**
     * gender string of the staff
     */
    private String gender;
    /**
     * employeeID string of the staff
     */
    private String employeeID;
    /**
     * jobTitle string of the staff
     */
    private String jobTitle;

    /**
     * Staff Constructor contains information about the staff member
     *
     * @param name The name of the Staff member
     * @param gender The gender of the Staff member
     * @param employeeID The Identification Number of the Staff member
     * @param jobTitle The position of the Staff member
     */
    public Staff(String name, String gender, String employeeID, String jobTitle) {
        this.name = name;
        this.gender = gender;
        this.employeeID = employeeID;
        this.jobTitle = jobTitle;
    }

    /**
     * getName method will retrieve the staff name
     *
     * @return the staff name
     */
    public String getName() {
        return name;
    }

    /**
     * setName method will assign the staff name
     *
     * @param name assigned the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getGender method will retrieve the gender of the staff
     *
     * @return gender of the staff
     */
    public String getGender() {
        return gender;
    }

    /**
     * setGender method will assign the gender of the staff
     *
     * @param gender assigned the gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * getEmployeeID method will retrieve the employees id
     *
     * @return the employees id
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * setEmployeeID method will assign the employees id
     *
     * @param employeeID assigned the employee id
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * getJobTitle method will retrieve the job title of the employee
     *
     * @return the job title of the employee
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * setJobTitle method will assign the job title of the employee
     *
     * @param jobTitle assigned the job title of the employee
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    public void printStaffDetails(){
    	System.out.println("Name: " + name);
    	System.out.println("Gender: "+ gender);
    	System.out.println("Employee ID: " + employeeID);
    	System.out.println("Job title: " + jobTitle);
    }
}

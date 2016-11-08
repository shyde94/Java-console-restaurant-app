package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Reservation Class contains all the information related to a reservation
 * 9/11/2016
 *
 * @author Shide
 * @author Eeyern
 * @author Grace
 * @author Xi Tong
 */
public class Reservation implements Serializable {

    private int tableNumber;
    private int numberOfPeople;
    private String hpNumber;
    private String customerName;
    //private Date start;
    private String dateReserved;
    private String timeOfArrival;
    private String slot;

    //Constructors 
    /**
     * Reservation Constructor to create the reservation object
     *
     * @param tableNumber The tables number assigned to the reservation
     * @param numberOfPeople The number of people attached to the reservation
     * @param hpNumber The handphone number of the customer
     * @param customerName The name of the customer
     * @param dateReserved The date reserved
     * @param timeOfArrival The time of the reservation
     * @param slot The AM/PM slot
     */
    public Reservation(int tableNumber, int numberOfPeople, String hpNumber, String customerName, String dateReserved, String timeOfArrival, String slot) {
        this.setTableNumber(tableNumber);
        this.setNumberOfPeople(numberOfPeople);
        this.setHpNumber(hpNumber);
        this.setCustomerName(customerName);
        this.setDateReserved(dateReserved);
        this.setTimeOfArrival(timeOfArrival);
        this.setSlot(slot);
    }

    /**
     * getSlot method retrieves AM or PM for the reservation
     *
     * @return AM or PM
     */
    public String getSlot() {
        return slot;
    }

    /**
     * setSlot method will assign the AM or PM slot to the reservation
     *
     * @param slot assigned AM or PM slot
     */
    public void setSlot(String slot) {
        this.slot = slot;
    }

    /**
     * getTableNumber method retrieves the table number
     *
     * @return the table number
     */
    public int getTableNumber() {
        return tableNumber;
    }

    /**
     * setTableNumber method assigns the table number
     *
     * @param tableNumber the assigned table number
     */
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    /**
     * getNumberOfPeople method retrieves number of people
     *
     * @return the number of people
     */
    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    /**
     * setNumberOfPeople method assigns the number of people
     * @param numberOfPeople the assigned number of people
     */
    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    /**
     * getHpNumber method retrieves the handphone number
     * @return the handphone number 
     */
    public String getHpNumber() {
        return hpNumber;
    }

    
    /**
     * setHpNumber method assigns the handphone of the customer
     * @param hpNumber the assigned handphone of the customer
     */
    public void setHpNumber(String hpNumber) {
        this.hpNumber = hpNumber;
    }

    /**
     * getCustomerName method retrieves the customers name
     * @return the customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * setCustomerName assigns the customer name
     * @param customerName the assigned customer name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * setDateReserved method assigns the date of the reservation
     * @param dateReserved the assigned date of the reservation
     */
    public void setDateReserved(String dateReserved) {
        this.dateReserved = dateReserved;

    }

    /**
     * getDateReserved method retrieves the date reserved for the reservation
     * @return the date reserved for the reservation
     */
    public String getDateReserved() {
        return dateReserved;
    }

    /**
     * getTimeOfArrival method retrieves the time of arrival of the reservation
     * @return the time of arrival for the reservation
     */
    public String getTimeOfArrival() {
        return this.timeOfArrival;
    }

    /**
     * setTimeOfArrival method assigns the time of arrival for the reservation
     * @param timeOfArrival assigns the time of arrival for the reservation
     */
    public void setTimeOfArrival(String timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    /**
     * printReservation method prints details regarding the reservation
     */
    public void printReservation() {
        System.out.println("date reserved: " + dateReserved + ", time slot: " + slot + ", Time of arrival: " + timeOfArrival + ", Table: " + tableNumber + ", Number of people: " + numberOfPeople + ", Customer name: " + customerName);
    }

}

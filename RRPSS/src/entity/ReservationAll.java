package entity;

import java.util.ArrayList;

import java.util.Date;

/**
 * ReservationAll Class contains functionality for all the reservations
 * @since 9/11/2016
 *
 * @author Shide
 * @author Eeyern
 * @author Grace
 * @author Xi Tong
 */
public class ReservationAll {

    /**
     * Reservation ArrayList
     */
    private ArrayList<Reservation> reservationList;

    /**
     * Constructor for Reservation All to create an ArrayList of Reservations
     */
    public ReservationAll() {
        reservationList = new ArrayList<Reservation>();
    }

    /**
     * getReservationList method retrieves the reservation list
     *
     * @return the reservation list
     */
    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    /**
     * setReservation method assigns all the reservations to a list
     *
     * @param reservation assigned reservations to list
     */
    public void setReservation(ArrayList<Reservation> reservation) {
        this.reservationList = reservation;
    }

    /**
     * createReservation allows a reservation to be made
     *
     * @param tableNumber The tables number assigned to the reservation
     * @param numberOfPeople The number of people attached to the reservation
     * @param hpNumber The handphone number of the customer
     * @param customerName The name of the customer
     * @param dateReserved The date reserved
     * @param timeOfArrival The time of the reservation
     * @param slot The AM/PM slot
     */
    public void createReservation(int tableNumber, int numberOfPeople, String hpNumber, String customerName, String dateReserved, String timeOfArrival, String slot) {
        Reservation newRes = new Reservation(tableNumber, numberOfPeople, hpNumber, customerName, dateReserved, timeOfArrival, slot);
        reservationList.add(newRes);
        System.out.println("Reservation made");
        newRes.printReservation();
    }

    /**
     * deleteReservation method removes a reservation from the list
     *
     * @param todelete Reservation object to remove from reservationList
     */
    public void deleteReservation(Reservation todelete) {
        int msg = 0;
        for (int i = 0; i < reservationList.size(); i++) {
            if (reservationList.get(i) == todelete) {
                reservationList.remove(i);
                msg = 1;
            }
        }
        if (msg == 1) {
            System.out.println("Reservation has been removed.");
        } else {
            System.out.println("Error. No changes have been made");
        }

    }

    /**
     * checkReservationsOnDate will check the reservation list by date
     *
     * @param input date object to check which tables are being reserved.
     */
    public void checkReservationsOnDate(String input) {
        System.out.println("Date to check: " + input);
        ArrayList<Reservation> tempList = new ArrayList<Reservation>();
        for (int i = 0; i < reservationList.size(); i++) {
            Reservation temp = reservationList.get(i);
            if (temp.getDateReserved().equals(input)) {
                tempList.add(temp); //In case customer makes multiple reservations.
            }
        }
        if (tempList.isEmpty()) {
            System.out.println("There are no reservations on " + input + ". ");
        } else {
            System.out.println("Reservations made on: " + input);
            for (int i = 0; i < tempList.size(); i++) {
                System.out.print(i + 1 + ". ");
                tempList.get(i).printReservation();
            }
        }

    }

    /**
     * reservedTablesonDate will check the tables that have a reservation on
     * that date
     *
     * @param input the date
     * @param slot time in AM/PM
     * @return all the reserved tables
     */
    public ArrayList<Integer> reservedTablesOnDate(String input, String slot) {
        //System.out.println("Input date:" + input);
        ArrayList<Integer> reservedTables = new ArrayList<Integer>();
        ArrayList<Reservation> tempList = reservationsMadeOnDate(input, slot);
        //System.out.println("Reservations made on: " + input);
        if (!(tempList.isEmpty())) {
            for (int i = 0; i < tempList.size(); i++) {
                int x = tempList.get(i).getTableNumber();
                reservedTables.add(x);
            }
        }
        return reservedTables;
    }

    /**
     * reservationsMadeOnDate will check the reservations made on a specific
     * date
     *
     * @param input is the date
     * @param slot AM/PM slot
     * @return the list of reservations
     */
    public ArrayList<Reservation> reservationsMadeOnDate(String input, String slot) {
        //System.out.println("Input date:" + input);
        ArrayList<Reservation> tempList = new ArrayList<Reservation>();
        for (int i = 0; i < reservationList.size(); i++) {
            Reservation temp = reservationList.get(i);
            if (temp.getDateReserved().equals(input) && (temp.getSlot().equals(slot))) {
                tempList.add(temp); //In case customer makes multiple reservations.
            }
        }
        if (tempList.isEmpty()) {
            //System.out.println("There are no reservations on " + input + ". ");
        }

        return tempList;
    }

    /**
     * showAllReservations method will display all the reservations
     */
    public void showAllReservations() {
        for (int i = 0; i < reservationList.size(); i++) {
            reservationList.get(i).printReservation();
        }
    }

}

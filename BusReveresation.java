package com.busReversation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class BusReveresation {
	
	
	   


	    public void addBus(Connection con, Scanner sc) throws SQLException
	    {
	    	System.out.print("Enter Bus Name: ");
	        String busName = sc.nextLine();

	        System.out.print("Enter Source: ");
	        String source = sc.nextLine();

	        System.out.print("Enter Destination: ");
	        String destination = sc.nextLine();

	        System.out.print("Enter Departure Time (HH:MM:SS): ");
	        String departureTime = sc.nextLine();

	        System.out.print("Enter Arrival Time (HH:MM:SS): ");
	        String arrivalTime = sc.nextLine();

	        System.out.print("Enter Total Seats: ");
	        int totalSeats = sc.nextInt();

	        System.out.print("Enter Fare: ");
	        double fare = sc.nextDouble();
	        sc.nextLine();
	        
	         String sql = "INSERT INTO buses "+ "(bus_name, source, destination, departure_time, "+ "arrival_time, total_seats, available_seats, fare) "+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setString(1, busName);
	            ps.setString(2, source);
	            ps.setString(3, destination);
	            ps.setString(4, departureTime);
	            ps.setString(5, arrivalTime);
	            ps.setInt(6, totalSeats);
	            ps.setInt(7,totalSeats);
	            ps.setDouble(8, fare);

	            int result = ps.executeUpdate();

	            if (result > 0) {
	                System.out.println("Bus Added Successfully");
	            }

	    
	}
	    public void viewAllBuses(Connection con) throws SQLException 
	    {

	            String sql = "SELECT * FROM buses";

	            PreparedStatement ps = con.prepareStatement(sql);

	            ResultSet rs = ps.executeQuery();

	            System.out.println("\n------------------------------------------ BUS DETAILS ---------------------------------------------");

	            System.out.printf("%-5s %-18s %-12s %-12s %-10s %-10s %-8s %-10s%n",
	                    "ID", "BUS NAME", "SOURCE", "DESTINATION",
	                    "DEPART", "ARRIVE", "SEATS", "FARE");

	            System.out.println("------------------------------------------------------------------------------------------------------");

	            while (rs.next()) {

	                System.out.printf("%-5d %-18s %-12s %-12s %-10s %-10s %-8d %.2f%n",
	                        rs.getInt("bus_id"),
	                        rs.getString("bus_name"),
	                        rs.getString("source"),
	                        rs.getString("destination"),
	                        rs.getTime("departure_time"),
	                        rs.getTime("arrival_time"),
	                        rs.getInt("available_seats"),
	                        rs.getDouble("fare"));
	            }
	            System.out.println("-----------------------------------------------------------------------------------------------------");

	        
	    }
	   
            public void searchBus(Connection con, Scanner sc)  throws SQLException{

	   
	    	System.out.print("Enter Source: ");
	        String source = sc.nextLine();

	        System.out.print("Enter Destination: ");
	        String destination = sc.nextLine();
	            String sql = "SELECT * FROM buses " + "WHERE source = ? AND destination = ?";

	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setString(1, source);
	            ps.setString(2, destination);

	            ResultSet rs = ps.executeQuery();

	            boolean found = false;

	            System.out.println("\n----------------------------------------- AVAILABLE BUSES ---------------------------------------------------");

	            System.out.printf("%-5s %-18s %-12s %-12s %-10s %-10s %-8s %-10s%n","ID", "BUS NAME", "SOURCE", "DESTINATION","DEPART", "ARRIVE", "SEATS", "FARE");
	            System.out.println("---------------------------------------------------------------------------------------------------------------");

	            while (rs.next()) {

	                found = true;

	                System.out.printf("%-5d %-18s %-12s %-12s %-10s %-10s %-8d %.2f%n",
	                        rs.getInt("bus_id"),
	                        rs.getString("bus_name"),
	                        rs.getString("source"),
	                        rs.getString("destination"),
	                        rs.getTime("departure_time"),
	                        rs.getTime("arrival_time"),
	                        rs.getInt("available_seats"),
	                        rs.getDouble("fare"));
	            }
	            System.out.println("----------------------------------------------------------------------------------------------------------------");

	            if (!found) {
	                System.out.println("No Buses Available");
	            }

	  
	    }
       public void registerPassenger(Connection con, Scanner sc) throws SQLException
       {
	 
    	   String sqls = "CREATE TABLE IF NOT EXISTS passengers ("
                   + "passenger_id INT PRIMARY KEY AUTO_INCREMENT, "
                   + "passenger_name VARCHAR(50) NOT NULL, "
                   + "age INT NOT NULL, "
                   + "gender VARCHAR(10), "
                   + "phone VARCHAR(15)"
                   + ")";

           PreparedStatement psl = con.prepareStatement(sqls);
           psl.executeUpdate();
           
           System.out.print("Enter Passenger Name: ");
           String passengerName = sc.nextLine();

           System.out.print("Enter Age: ");
           int age = sc.nextInt();
           sc.nextLine();

           System.out.print("Enter Gender: ");
           String gender = sc.nextLine();

           System.out.print("Enter Phone Number: ");
           String phone = sc.nextLine();
	        String sql = "INSERT INTO passengers " + "(passenger_name, age, gender, phone) "+ "VALUES (?, ?, ?, ?)";

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setString(1, passengerName);
	        ps.setInt(2, age);
	        ps.setString(3, gender);
	        ps.setString(4, phone);

	        int result = ps.executeUpdate();

	        if (result > 0) {
	            System.out.println("Passenger Registered Successfully");
	        }

	    } 
       
       public void viewPassengerDetails(Connection con) throws SQLException
       {

    	   
    	        String sql = "SELECT * FROM passengers";

    	        PreparedStatement ps = con.prepareStatement(sql);

    	        ResultSet rs = ps.executeQuery();

    	        boolean found = false;

    	        System.out.println("\n----------------------------- PASSENGER DETAILS ------------------------------");

    	        System.out.printf("%-12s %-20s %-6s %-10s %-15s%n",
    	                "ID", "NAME", "AGE", "GENDER", "PHONE");

    	        System.out.println("--------------------------------------------------------------------------------");

    	        while (rs.next()) {

    	            found = true;

    	            System.out.printf("%-12d %-20s %-6d %-10s %-15s%n",
    	                    rs.getInt("passenger_id"),
    	                    rs.getString("passenger_name"),
    	                    rs.getInt("age"),
    	                    rs.getString("gender"),
    	                    rs.getString("phone"));
    	        }
    	        System.out.println("--------------------------------------------------------------------------------");

    	        if (!found) {
    	            System.out.println("No Passenger Details Found");
    	        }

    	    
    	}
                  public void displaySeats(Connection con,int busId,LocalDate travelDate) throws SQLException
                  
                  {

                  String sql ="SELECT seat_number FROM bookings " + "WHERE bus_id = ? "+ "AND travel_date = ? " + "AND status = 'CONFIRMED'";
                  PreparedStatement ps =con.prepareStatement(sql);

                  ps.setInt(1, busId);
                  ps.setDate(2, Date.valueOf(travelDate));

                  ResultSet rs = ps.executeQuery();

                   ArrayList<String> bookedSeats =new ArrayList<>();

                  while (rs.next()) {

                   bookedSeats.add(
                   rs.getString("seat_number"));
                      }

                   System.out.println();
                   System.out.println("=========== BUS SEAT LAYOUT ===========");

                   System.out.println();
                   System.out.println("GATE                         DRIVER");

                   System.out.println();



                   for (char row = 'A'; row <= 'I'; row++) {

                   String seat1 = row + "1";
                   String seat2 = row + "2";
   				   String seat3 = row + "3";
   				   String seat4 = row + "4";


   				   String display1 = bookedSeats.contains(seat1) ? "X" : seat1;
   				   String display2 = bookedSeats.contains(seat2)? "X" : seat2;
   				   String display3 =bookedSeats.contains(seat3)? "X" : seat3;
   				   String display4 =bookedSeats.contains(seat4)? "X" : seat4;

   				   System.out.printf("%-6s %-6s          %-6s %-6s%n",display1,display2, display3,display4);
                    }


                   String[] lastRow = {"J1", "J2", "J3", "J4", "J5"};

	                   for (String seat : lastRow) {
	
	                	   if (bookedSeats.contains(seat)) {
	
	                		   System.out.printf("%-6s", "X");
	                		   
	                	   } else {
	
	                		   System.out.printf("%-6s", seat);
	                	   }
	                   }

	                   System.out.println();
	                   System.out.println("\nX = Already Booked");
	                   System.out.println("=======================================");
                  

                  } 
                  public void bookSeat(Connection con, Scanner sc) {

                	    try {

                	        System.out.println("\n========== BOOK BUS TICKET ==========");
                	        System.out.print("Enter Source: ");
                	        String source =sc.nextLine();
                	        System.out.print("Enter Destination: ");
                	        String destination =sc.nextLine();
                	        String searchSql = "SELECT * FROM buses "+ "WHERE LOWER(source) = LOWER(?) "+ "AND LOWER(destination) = LOWER(?)";
                	        PreparedStatement searchPs =con.prepareStatement(searchSql);
                	        searchPs.setString( 1,source);
                	        searchPs.setString(2, destination);
                	        ResultSet searchRs =searchPs.executeQuery();
                	        boolean busFound = false;
                	        System.out.println();
                	        System.out.println("------------------------------------- AVAILABLE BUSES -----------------------------------------");
                	        System.out.printf("%-5s %-20s %-10s %-10s%n","ID", "BUS NAME", "SEATS", "FARE");
                	        System.out.println("-----------------------------------------------------------------------------------------------");

                	        while (searchRs.next()) {
                	            busFound = true;
                	            System.out.printf("%-5d %-20s %-10d %.2f%n",searchRs.getInt( "bus_id"),searchRs.getString( "bus_name"),searchRs.getInt("available_seats"),searchRs.getDouble("fare"));
                	        }
                	        System.out.println("------------------------------------------------------------------------------------------------");

                	        if (!busFound) {
                	            System.out.println( "No Buses Available");
                	            return;
                	        }
                	        System.out.print("\nEnter Bus ID from above list: ");
                	        int busId = sc.nextInt();
                	        
                	        String busSql = "SELECT * FROM buses " + "WHERE bus_id = ? "+ "AND LOWER(source) = LOWER(?) "+ "AND LOWER(destination) = LOWER(?)";

                	        PreparedStatement busPs =con.prepareStatement(busSql);

                	        busPs.setInt( 1,busId);
                	        busPs.setString(2,source);
                	        busPs.setString(3,destination);


                	        ResultSet busRs =busPs.executeQuery();

                	        if (!busRs.next()) {

                	            System.out.println( "Invalid Bus ID");
                	            return;
                	        }

                	        String busName =busRs.getString("bus_name");
                	        
                	        int availableSeats =busRs.getInt( "available_seats");
                	        double fare =busRs.getDouble("fare");

                	        if (availableSeats <= 0) {
                	            System.out.println("Sorry! No Seats Available");
                	            return;
                	        }
                             sc.nextLine();
                             System.out.print("Enter Travel Date (YYYY-MM-DD): ");
                             String date = sc.nextLine().trim();
                             LocalDate travelDate;

                             try {

                                 travelDate = LocalDate.parse(date);

                             } catch (Exception e) {
                                 System.out.println("Invalid Date Format!");
                                 System.out.println( "Please enter like: 2026-10-01");
                                 return;
                             }

                	        displaySeats( con,busId,travelDate);

                	        System.out.print( "\nHow many passengers: ");
                	        int passengerCount =sc.nextInt();

                	        sc.nextLine();

                	        if (passengerCount <= 0) {
                	            System.out.println("Invalid Passenger Count");
                	            return;
                	        }


                	        if (passengerCount > availableSeats) {

                	            System.out.println("Only " + availableSeats+ " seats available");
                	            return;
                	        }
                	        ArrayList<String> selectedSeats = new ArrayList<>();
                	        ArrayList<String> passengerNames = new ArrayList<>();
                	        ArrayList<Integer> passengerIds = new ArrayList<>();
                	        ArrayList<Integer> bookingIds = new ArrayList<>();
                	        con.setAutoCommit(false);


                	        for (int i = 1;i <= passengerCount;i++) {


                	            System.out.println();
                	            System.out.println( "===================================");
                	            System.out.println( "PASSENGER " + i);
                	            System.out.println( "===================================");
                	            System.out.print("Select Seat Number: ");
                	            String seatNumber =sc.nextLine().toUpperCase();
                	            boolean validSeat = false;


                	            if (seatNumber.matches("[A-I][1-4]")) {
                	                validSeat = true;
                	            }

                	            if (seatNumber.matches( "J[1-5]")) {
                	                validSeat = true;
                	            }

                	            if (!validSeat) {

                	                System.out.println( "Invalid Seat Number");
                	                con.rollback();
                	                con.setAutoCommit(true);

                	                return;
                	            }

                	            if (selectedSeats.contains(seatNumber)) {

                	                System.out.println( "Seat " + seatNumber + " already selected");
                	                con.rollback();
                	                con.setAutoCommit(true);
                	                return;
                	            }

                	            String seatSql = "SELECT booking_id "+ "FROM bookings "+ "WHERE bus_id = ?  "+ "AND seat_number = ? "+ "AND travel_date = ? "  + "AND status = 'CONFIRMED'";
                	            PreparedStatement seatPs =con.prepareStatement(seatSql);
                	            seatPs.setInt( 1,busId);
                	            seatPs.setString(2,seatNumber);
                	            seatPs.setDate(3, Date.valueOf(travelDate));
                	            ResultSet seatRs =seatPs.executeQuery();

                	            if (seatRs.next()) {

                	                System.out.println( "Seat " + seatNumber + " is Already Booked");
                	                con.rollback();
                	                con.setAutoCommit(true);
                	                return;
                	            }
                	            selectedSeats.add(seatNumber);
                	            System.out.print("Enter Passenger Name: ");
                	            String passengerName = sc.nextLine();
                	            System.out.print("Enter Age: ");
                	            int age = sc.nextInt();
                	            sc.nextLine();
                	            System.out.print( "Enter Gender: ");
                	            String gender =sc.nextLine();
                	            System.out.print( "Enter Phone Number: ");
                	            String phone =sc.nextLine();
            
                	            String passengerSql = "INSERT INTO passengers " + "(passenger_name, age, gender, phone) "+ "VALUES (?, ?, ?, ?)";
                	            PreparedStatement passengerPs = con.prepareStatement(passengerSql,Statement.RETURN_GENERATED_KEYS);
                	            passengerPs.setString( 1,passengerName);
                	            passengerPs.setInt( 2,age);
                	            passengerPs.setString(3, gender);
                	            passengerPs.setString( 4, phone);
                	            passengerPs.executeUpdate();

                	            ResultSet passengerRs = passengerPs.getGeneratedKeys();
                	            int passengerId =0;
                	            
                	            if (passengerRs.next()) {
                	                passengerId = passengerRs.getInt(1);
                	            }
                	            String bookingSql ="INSERT INTO bookings "+ "(bus_id, passenger_id, "+ "seat_number, booking_date, " + "travel_date, status) " + "VALUES (?, ?, ?, ?, ?, ?)";
                	            PreparedStatement bookingPs =con.prepareStatement(bookingSql, Statement.RETURN_GENERATED_KEYS);
                	            bookingPs.setInt( 1, busId);
                	            bookingPs.setInt( 2,passengerId);
                	            bookingPs.setString(3,seatNumber);
                	            bookingPs.setDate( 4, Date.valueOf(LocalDate.now()));
                	            bookingPs.setDate(5,Date.valueOf( travelDate));
                	            bookingPs.setString( 6,"CONFIRMED");
                	            bookingPs.executeUpdate();
                	            ResultSet bookingRs = bookingPs.getGeneratedKeys();
                	            int bookingId = 0;
                	            if (bookingRs.next()) {
                	                bookingId = bookingRs.getInt(1);
                	            }
                	            passengerNames.add(passengerName);
                	            passengerIds.add( passengerId);
                	            bookingIds.add( bookingId);
                	        }

                	        String updateSql = "UPDATE buses " + "SET available_seats = " + "available_seats - ? " + "WHERE bus_id = ?";
                	        PreparedStatement updatePs =con.prepareStatement(updateSql);
                	        updatePs.setInt(1,passengerCount);
                	        updatePs.setInt( 2,busId);
                	        updatePs.executeUpdate();
                	        con.commit();
                	        con.setAutoCommit(true);
                	        double totalFare =fare * passengerCount;
                	        
                	        System.out.println();
                	        System.out.println( "============================================");
                	        System.out.println( "                BUS TICKET");
                	        System.out.println("============================================");
                	        System.out.println( "Bus          : "+ busName);
                	        System.out.println("Source       : " + source);
                	        System.out.println("Destination  : " + destination);
                	        System.out.println("Travel Date  : " + travelDate);
                	        System.out.println("Fare / Seat  : Rs."+ fare);
                	        System.out.println("No. of Seats : "+ passengerCount);
                	        System.out.println( "Seats        : "+ selectedSeats);
                	        System.out.println("Total Fare   : Rs."+ totalFare);
                	        System.out.println("Status       : CONFIRMED");
                	        System.out.println("============================================");
                	        System.out.println();
                	        System.out.println("----------------------------------- PASSENGERS ----------------------------------");
                	        System.out.printf("%-12s %-12s %-20s %-8s%n","BOOKING ID", "PASSENGER ID","NAME","SEAT");
                	        System.out.println("---------------------------------------------------------------------------------");

                	        for (int i = 0;i < passengerCount;i++) {
                	        
                	            System.out.printf("%-12d %-12d %-20s %-8s%n",bookingIds.get(i),passengerIds.get(i),passengerNames.get(i),selectedSeats.get(i));
                	        }
                	        System.out.println("---------------------------------------------------------------------------------");
               	      
                	    } catch (Exception e) {

                	        try {
                	            con.rollback();
                	            con.setAutoCommit(true);
                	        } catch (Exception ex) {
                	            System.out.println( ex.getMessage());
                	        }
                	        System.out.println("Booking Failed: "+ e.getMessage());
                	    }
                	}
                  
                  public void cancelTicket(Connection con, Scanner sc)
                  {
                       try {
                    	   
                	        System.out.println("\n========== CANCEL TICKET ==========");
                	        System.out.print("Enter Booking ID: ");
                	        int bookingId = sc.nextInt();
                	        sc.nextLine();
                	        String checkSql = "SELECT b.booking_id, " + "b.bus_id, "  + "b.passenger_id, " + "b.seat_number, " + "b.travel_date, " + "b.status, "+ "p.passenger_name, "+ "bs.bus_name, "+ "bs.source, " + "bs.destination " + "FROM bookings b "+ "JOIN passengers p " + "ON b.passenger_id = p.passenger_id " + "JOIN buses bs " + "ON b.bus_id = bs.bus_id "+ "WHERE b.booking_id = ?";
                	        PreparedStatement checkPs = con.prepareStatement(checkSql);
                	        checkPs.setInt(1,bookingId);
                	        ResultSet rs =checkPs.executeQuery();

                	        if (!rs.next()) {
                	            System.out.println( "Booking ID Not Found");
                	            return;
                	        }

                	        int busId =rs.getInt("bus_id");
                	        int passengerId =rs.getInt("passenger_id");
                	        String passengerName = rs.getString("passenger_name");
                	        String seatNumber =rs.getString("seat_number");
                	        Date travelDate =rs.getDate("travel_date");
                	        String status =rs.getString("status");
                	        String busName = rs.getString("bus_name");
                	        String source =rs.getString("source");
                	        String destination = rs.getString("destination");

                	        if (status.equalsIgnoreCase("CANCELLED")) {
                	            System.out.println("This Ticket is Already Cancelled");
                	            return;
                	        }
                	        System.out.println();
                	        System.out.println( "===================================");
                	        System.out.println("        BOOKING DETAILS");
                	        System.out.println( "===================================");
                	        System.out.println("Booking ID   : " + bookingId);
                	        System.out.println( "Passenger ID : " + passengerId);
                	        System.out.println( "Passenger    : " + passengerName);
                	        System.out.println( "Bus          : " + busName);
                	        System.out.println("Source       : " + source);
                	        System.out.println( "Destination  : " + destination);
                	        System.out.println("Seat Number  : " + seatNumber);
                	        System.out.println("Travel Date  : " + travelDate);
                	        System.out.println("Status       : " + status);
                	        System.out.println( "===================================");

                	        System.out.print("\nDo you want to cancel this ticket? (yes/no): ");

                	        String confirm = sc.nextLine();
                	        if (!confirm.equalsIgnoreCase("yes")) {
                	            System.out.println( "Ticket Cancellation Stopped");

                	            return;
                	        }
                	        con.setAutoCommit(false);

                	        String cancelSql = "UPDATE bookings " + "SET status = 'CANCELLED' " + "WHERE booking_id = ? " + "AND status = 'CONFIRMED'";
                	        PreparedStatement cancelPs =con.prepareStatement(cancelSql);
                	        cancelPs.setInt(1, bookingId);
                	        int result = cancelPs.executeUpdate();
                	        if (result == 0) {
                	            con.rollback();
                	            con.setAutoCommit(true);
                	            System.out.println("Ticket Cannot Be Cancelled");
                	            return;
                	        }

                	        String updateSeatSql ="UPDATE buses "+ "SET available_seats = "+ "available_seats + 1 " + "WHERE bus_id = ?";
                	        PreparedStatement updateSeatPs =con.prepareStatement(updateSeatSql);
                	        updateSeatPs.setInt(1,busId);
                	        updateSeatPs.executeUpdate();
                	        con.commit();
                	        con.setAutoCommit(true);                	       
                	        System.out.println();
                	        System.out.println( "===================================");
                	        System.out.println( "       TICKET CANCELLED");
                	        System.out.println( "===================================");
                	        System.out.println("Booking ID   : " + bookingId);
                	        System.out.println("Passenger    : " + passengerName);
                	        System.out.println("Bus          : " + busName);
                	        System.out.println("Seat Number  : " + seatNumber);
                	        System.out.println( "Travel Date  : " + travelDate);
                	        System.out.println("Status       : CANCELLED");
                	        System.out.println( "===================================");
                	        System.out.println("Seat "+ seatNumber+ " is now available");
                	        System.out.println("====================================");


                	    } catch (Exception e) {

                	        try {
                	            con.rollback();
                	            con.setAutoCommit(true);

                	        } catch (Exception ex) 
                	        {
                	            System.out.println( ex.getMessage());
                	        }
                	        System.out.println( "Cancellation Failed: " + e.getMessage());
                	    }
                	}
                  
                  public void viewBookingDetails(Connection con) throws SQLException 
                  {
                	  
                          	        String sql ="SELECT b.booking_id, "+ "b.booking_date, " + "b.travel_date, "+ "b.seat_number, "+ "b.status, " + "p.passenger_id, " + "p.passenger_name, " + "bs.bus_name, " + "bs.source, "+ "bs.destination, " + "bs.fare "  + "FROM bookings b " + "JOIN passengers p " + "ON b.passenger_id = p.passenger_id "+ "JOIN buses bs " + "ON b.bus_id = bs.bus_id " + "ORDER BY b.booking_id";
                	        PreparedStatement ps = con.prepareStatement(sql);
                	        ResultSet rs =ps.executeQuery();
                	        boolean found = false;
                	        System.out.println();
                	        System.out.println("==================================================== BOOKING DETAILS =====================================================");
                	        System.out.printf( "%-10s %-12s %-18s %-18s %-12s %-12s %-8s %-10s%n", "BOOK ID","PASS ID", "PASSENGER","BUS", "SOURCE", "DESTINATION","SEAT","STATUS");

                	        System.out.println("--------------------------------------------------------------------------------------------------------------------------");

                	        while (rs.next()) 
                	        {
                	            found = true;
                	            System.out.printf("%-10d %-12d %-18s %-18s %-12s %-12s %-8s %-10s%n", rs.getInt("booking_id"),rs.getInt("passenger_id"), rs.getString("passenger_name"), rs.getString("bus_name"), rs.getString("source"), rs.getString( "destination"), rs.getString("seat_number"), rs.getString( "status"));
                	        }
                	        System.out.println("-------------------------------------------------------------------------------------------------------------");
                	        if (!found) {
                	            System.out.println("No Booking Details Found");
                	        }


                	    } 
                	}


package com.busReversation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {

    static final String url ="jdbc:mysql://localhost:3306/bus_reservation_db";

    static final String username = "root";
    static final String password = "root";

    public static void main(String[] args) {

    	Connection con;
    	int choice;
        Scanner sc = new Scanner(System.in);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, username, password);

            System.out.println("Database Connected Successfully");
            BusReveresation busreve=new BusReveresation();
            do{

                System.out.println("\n===== BUS RESERVATION SYSTEM =====");
                System.out.println("1. Add Bus");
                System.out.println("2. View All Buses");
                System.out.println("3. Search Buses");
                System.out.println("4. Register Passenger");
                System.out.println("5. Book Seat");
                System.out.println("6. Cancel Ticket");
                System.out.println("7. View Passenger Details");
                System.out.println("8. View Booking Details");
                System.out.println("9. Exit");

                System.out.print("\nEnter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                    	busreve.addBus(con, sc);
                        break;

                    case 2:
                        busreve.viewAllBuses(con);
                        break;

                    case 3:
                        
                        busreve.searchBus(con, sc);
                        break;

                    case 4:
                        busreve.registerPassenger(con,sc);;
                        break;

                    case 5:
                        busreve.bookSeat(con, sc);
                        break;

                    case 6:
                        busreve.cancelTicket(con, sc);
                        break;

                    case 7:
                    	busreve.viewPassengerDetails(con);
                        break;

                    case 8:
                        busreve.viewBookingDetails(con);
                        break;

                    case 9:
                        System.out.println("Thank You!");
                        con.close();
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid Choice!");
                }
            }while(choice!=9);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
    
}
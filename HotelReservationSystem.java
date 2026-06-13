import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HotelReservationSystem {

    static String[] rooms = {
            "101 - Standard",
            "102 - Standard",
            "201 - Deluxe",
            "202 - Deluxe",
            "301 - Suite"
    };

    static boolean[] booked = new boolean[5];
    static String[] customerNames = new String[5];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Booking Details");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {

                System.out.println("\n===== AVAILABLE ROOMS =====");

                for (int i = 0; i < rooms.length; i++) {

                    if (!booked[i]) {
                        System.out.println((i + 1) + ". " + rooms[i]);
                    }
                }
            }

            else if (choice == 2) {

                System.out.print("Enter Customer Name: ");
                String name = sc.nextLine();

                System.out.println("\nAvailable Rooms:");

                for (int i = 0; i < rooms.length; i++) {

                    if (!booked[i]) {
                        System.out.println((i + 1) + ". " + rooms[i]);
                    }
                }

                System.out.print("Select Room Number: ");
                int roomChoice = sc.nextInt();
                sc.nextLine();

                if (roomChoice < 1 || roomChoice > rooms.length) {
                    System.out.println("Invalid Room!");
                    continue;
                }

                if (booked[roomChoice - 1]) {
                    System.out.println("Room Already Booked!");
                    continue;
                }

                System.out.println("Room Price = Rs.5000");
                System.out.print("Proceed Payment? (yes/no): ");

                String payment = sc.nextLine();

                if (payment.equalsIgnoreCase("yes")) {

                    booked[roomChoice - 1] = true;
                    customerNames[roomChoice - 1] = name;

                    System.out.println("Payment Successful!");
                    System.out.println("Booking Confirmed!");
                    try {

    FileWriter fw = new FileWriter("bookings.txt", true);

    fw.write(
        "Customer: " + name +
        " | Room: " + rooms[roomChoice - 1] +
        " | Status: Confirmed" +
        "\n"
    );

    fw.close();

    System.out.println("Booking saved to file!");

} catch (IOException e) {

    System.out.println("Error saving booking!");
}
                } else {
                    System.out.println("Payment Cancelled!");
                }
            }

            else if (choice == 3) {

                System.out.print("Enter Room Number To Cancel: ");
                int cancelRoom = sc.nextInt();
                sc.nextLine();

                if (cancelRoom >= 1 && cancelRoom <= rooms.length) {

                    booked[cancelRoom - 1] = false;
                    customerNames[cancelRoom - 1] = null;

                    System.out.println("Reservation Cancelled Successfully!");
                } else {
                    System.out.println("Invalid Room!");
                }
            }

            else if (choice == 4) {

                System.out.println("\n===== BOOKING DETAILS =====");

                boolean found = false;

                for (int i = 0; i < rooms.length; i++) {

                    if (booked[i]) {

                        found = true;

                        System.out.println(
                                rooms[i] + " | Customer: " + customerNames[i]);
                    }
                }

                if (!found) {
                    System.out.println("No Bookings Found!");
                }
            }

            else if (choice == 5) {

                System.out.println("Thank You!");
                break;
            }

            else {
                System.out.println("Invalid Choice!");
            }
        }

        sc.close();
    }
}
# Hotel Management System

A desktop-based Hotel Management System built in Java Swing. This application provides a graphical user interface (GUI) for managing hotel operations. It features separate functionalities for Administrators (staff) and Customers.

This project was developed using the **Apache NetBeans IDE** and uses **Apache Ant** as its build tool.

## Key Features

The system is divided into two main user roles:

### Admin (Staff) Features
* **Admin Dashboard:** A central control panel for all hotel management tasks.
* **Room Management:** Add new rooms, update room details (e.g., price, bed type, status), and manage the complete inventory of hotel rooms.
* **Customer Check-In:** Manage and process guest check-ins.
* **Customer Check-Out:** Manage and process guest check-outs, including final billing.
* **View Reservations:** View and track all current and upcoming guest reservations.

### Customer Features
* **Login & Registration:** Secure sign-in portal for all users and a self-service registration form for new customers.
* **Customer Dashboard:** A personalized home screen for customers.
* **Book a Room:** Browse available rooms and make a new reservation.
* **View My Bookings:** Check the status of personal room reservations.
* **Cancel Booking:** Allows a customer to cancel an existing reservation.

## Technology Stack
* **Language:** Java
* **Framework:** Java Swing (for the GUI)
* **Build Tool:** Apache Ant (identified by `build.xml`)
* **IDE:** Apache NetBeans (identified by `nbproject/project.xml`)

## Getting Started

### Prerequisites
* Java Development Kit (JDK) 8 or higher
* Apache NetBeans IDE (recommended)

### Running the Project

1.  **Clone the Repository**
    ```bash
    git clone https://github.com/aldrieBQRN/hotelManagement.git
    ```
2.  **Open in NetBeans**
    * Open Apache NetBeans.
    * Go to `File` > `Open Project...`.
    * Navigate to and select the `hotelManagement` folder (which contains the `nbproject` directory).
    * NetBeans will automatically recognize and load the project.

3.  **Database Setup (Crucial!)**
    * This application requires a database to store user, room, and booking data.
    * Check the Java source files (especially `signIn.java`, `signUp.java`, or a database connection class) for the JDBC connection string, database name, and required table structures.
    * You **must** set up this database and its tables before the application can run successfully.

4.  **Build and Run**
    * Right-click on the `hotelManagement` project in the NetBeans project explorer.
    * Select **"Clean and Build"**. This will compile the code and create a runnable `.jar` file in the `dist/` directory.
    * Right-click on the project again and select **"Run"**. This will start the application and open the `signIn` window.

## Login
* **Admin:** You will need to check the database or the `signIn.java` logic for any default admin credentials.
* **Customer:** New customers can register an account using the "Sign up" button on the login screen.

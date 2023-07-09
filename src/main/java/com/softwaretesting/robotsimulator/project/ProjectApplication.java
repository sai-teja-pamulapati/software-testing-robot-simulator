package com.softwaretesting.robotsimulator.project;

import java.util.Scanner;

public class ProjectApplication {

	public static void main(String[] args) {
		
		
		Scanner Userin = new Scanner(System.in);
		
		
		System.out.print("Welcome to RobotSimulator!!!\n"
				+ "Use below commands!!!\n"
				+ "[1] [U|u]     => Pen up\r\n"
				+ "[2] [D|d]     => Pen down\r\n"
				+ "[3] [R|r]     => Turn right\r\n"
				+ "[4] [L|l]     => Turn left\r\n"
				+ "[5] [M s|m s] => Move forward s spaces (s is a non-negative integer)\r\n"
				+ "[6] [P|p]     => Print the floor mapped\r\n"
				+ "[7] [C|c]     => Print current position of the pen and whether it is up or down and its facing direction\r\n"
				+ "[8] [Q|q]     => Stop the program\r\n"
				+ "[9] [I n|i n] => Initialize the system: The values of the array floor are zeros and the robot\r\n"
				+ "                  is back to [0, 0], pen up and facing north. x size of the array, an integer greater than zero\r\n");
		System.out.print("Enter command: ");
		String command = Userin.nextLine();
		//System.out.println(command);
		
	}

}

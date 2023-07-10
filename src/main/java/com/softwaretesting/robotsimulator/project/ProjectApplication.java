package com.softwaretesting.robotsimulator.project;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ProjectApplication {

	private static Matrix matrix = new Matrix();

	public static void main(String[] args) {


		Scanner scanner = new Scanner(System.in);
		System.out.print("""
				Welcome to RobotSimulator!!!
				Use below commands!!!
				[1] [U|u]     => Pen up\r
				[2] [D|d]     => Pen down\r
				[3] [R|r]     => Turn right\r
				[4] [L|l]     => Turn left\r
				[5] [M s|m s] => Move forward s spaces (s is a non-negative integer)\r
				[6] [P|p]     => Print the floor mapped\r
				[7] [C|c]     => Print current position of the pen and whether it is up or down and its facing direction\r
				[8] [Q|q]     => Stop the program\r
				[9] [I n|i n] => Initialize the system: The values of the array floor are zeros and the robot\r
				                  is back to [0, 0], pen up and facing north. x size of the array, an integer greater than zero\r
				""");

		initializeSystem(scanner);
		moveRobot(scanner);
	}

	private static void moveRobot(Scanner scanner) {

		int x = 0;
		int y = 0;
		boolean penDown = false;
		int facing = 0; // 0: north, 1: east, 2: south, 3: west

		ArrayList<String> commandHistory = new ArrayList<>();

		while (true) {
			System.out.print("Enter command: ");
			String secondCommand = scanner.nextLine();
			String[] commandSplit = secondCommand.split("\\s+");
			commandHistory.add(secondCommand);
			if (commandSplit.length < 1) {
				System.out.println("Please enter a command");
			}
			switch (commandSplit[0]) {

				case "c":
				case "C":
					matrix.printPosition();					
					break;

				case "d":
				case "D":
					matrix.changePenPosition(PEN_POSITION.DOWN);
					matrix.printPosition();
					break;

				case "u":
				case "U":
					matrix.changePenPosition(PEN_POSITION.UP);
					matrix.printPosition();
					break;

				case "m":
				case "M":
					String[] splitStrings = secondCommand.split("\\s+");
					if (splitStrings.length == 2 && StringUtils.isNumeric(splitStrings[1])) {
						matrix.move(Integer.valueOf(splitStrings[1]));
					} else {
						System.out.println("Invalid command!");
					}
					matrix.printPosition();
					break;

				case "r":
				case "R":
					matrix.rotate(ROTATION.RIGHT);
					matrix.printPosition();

				case "l":
				case "L":
					matrix.rotate(ROTATION.LEFT);
					matrix.printPosition();

				case "p":
				case "P":
					matrix.show();
					break;

				case "q":
				case "Q":
					System.out.println("Bye");
					System.exit(0);


				default:
					System.out.println("Invalid command!");

			}
		}
	}

	private static void initializeSystem(Scanner scanner) {
		while (true) {
			System.out.print("Enter command: ");
			String firstCommand = scanner.nextLine();
			if (firstCommand == null) {
				System.out.println("Please enter valid input!!");
				continue;
			}
			if ("q".equalsIgnoreCase(firstCommand)) {
				System.out.println("Bye");
				System.exit(0);
			}
			String[] splitStrings = firstCommand.split("\\s+");
			if (splitStrings.length == 2 && "i".equalsIgnoreCase(splitStrings[0]) && StringUtils.isNumeric(splitStrings[1])) {
				initializeMatrix(Integer.parseInt(splitStrings[1]));
				break;
			} else {
				System.out.println("Please Initialize the system first");

			}
		}
	}







	private static void initializeMatrix(Integer size) {


		int[][] arrayOfArray = new int[size][size];
		for (int[] array : arrayOfArray) {
			Arrays.fill(array , 0);
		}

		matrix.setSize(size);
		matrix.setxPosition(0);
		matrix.setyPosition(0);
		matrix.setPenPosition(PEN_POSITION.UP);
		matrix.setDirection(DIRECTION.NORTH);
		matrix.setMatrix(arrayOfArray);
//		matrix.show();

	}

}

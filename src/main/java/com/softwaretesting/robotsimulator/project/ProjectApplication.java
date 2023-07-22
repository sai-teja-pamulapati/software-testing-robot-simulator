package com.softwaretesting.robotsimulator.project;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class ProjectApplication {

	private static Matrix matrix = new Matrix();

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		initializeSystem();
		execute();
	}

	private static void execute() {

		while (true) {
			try {
				commandTheRobot();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void commandTheRobot() {
		System.out.print("Enter command: ");
		String secondCommand = scanner.nextLine();
		processCommands(secondCommand);
	}

	public static void processCommands(String secondCommand) {
		if (secondCommand == null) {
			throw new IllegalArgumentException("Invalid command!");
		}
		secondCommand = secondCommand.trim();
		String[] commandSplit = secondCommand.split("\\s+");
		if (!"m".equalsIgnoreCase(commandSplit[0]) && commandSplit.length != 1) {
			throw new IllegalArgumentException("Invalid command!");
		}
		switch (commandSplit[0]) {
			case "c" , "C" -> matrix.printPosition();
			case "d" , "D" -> matrix.changePenPosition(PEN_POSITION.DOWN);
			case "u" , "U" -> matrix.changePenPosition(PEN_POSITION.UP);
			case "m" , "M" -> {
				String[] splitStrings = secondCommand.split("\\s+");
				if (splitStrings.length == 2 && StringUtils.isNumeric(splitStrings[1])) {
					matrix.move(Integer.valueOf(splitStrings[1]));
				} else {
					throw new IllegalArgumentException("Invalid command!");
				}
			}
			case "r" , "R" -> matrix.rotate(ROTATION.RIGHT);
			case "l" , "L" -> matrix.rotate(ROTATION.LEFT);
			case "p" , "P" -> matrix.show();
			case "q" , "Q" -> {
				System.out.println("Bye");
				System.exit(0);
			}
			default -> throw new IllegalArgumentException("Invalid command!");
		}
	}

	private static void initializeSystem() {
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

		while (true) {
			System.out.print("Enter command: ");
			String firstCommand = scanner.nextLine();
			try {
				boolean isSystemInitialized = processFirstCommand(firstCommand);
				if (isSystemInitialized) {
					break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static boolean processFirstCommand(String firstCommand) {
		if (firstCommand == null) {
			throw new IllegalArgumentException("Invalid command!");
		}
		firstCommand = firstCommand.trim();
		if ("q".equalsIgnoreCase(firstCommand)) {
			System.out.println("Bye");
			System.exit(0);
		}
		String[] splitStrings = firstCommand.split("\\s+");
		if (splitStrings.length == 2 && "i".equalsIgnoreCase(splitStrings[0]) && StringUtils.isNumeric(splitStrings[1])) {
			matrix.initializeMatrix(Integer.parseInt(splitStrings[1]));
			return true;
		} else {
			throw new IllegalArgumentException("Please Initialize the system first");
		}
	}

	public static Matrix getMatrix() {
		return matrix;
	}
}

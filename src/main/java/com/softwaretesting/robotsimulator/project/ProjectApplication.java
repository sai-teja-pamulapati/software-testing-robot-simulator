package com.softwaretesting.robotsimulator.project;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ProjectApplication {

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
			System.out.println(splitStrings.length);
			if (splitStrings.length == 2 && "i".equalsIgnoreCase(splitStrings[0]) && StringUtils.isNumeric(splitStrings[1])) {
				initializeMatrix(Integer.parseInt(splitStrings[1]));
				break;
			} else {
				System.out.println("Please Initialize the system first");
			}
		}

	}

	private static void initializeMatrix(Integer size) {
		System.out.println(size);
		Matrix matrix = new Matrix();

		List<Integer> integerList = new ArrayList<>(Collections.nCopies(size , 0));

		List<List<Integer>> listOfList = new ArrayList<>(size);
		for (int i = 0 ; i < size ; i++) {
			listOfList.add(integerList);
		}
		matrix.show();
	}

}

package Practical7;

import java.util.Scanner;

public class BerkeleyAlgorithm1 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the number of processes (including server): ");
    int numProcesses = scanner.nextInt();

    int[] clocks = new int[numProcesses];
    System.out.println("Enter clock times (in seconds) for each process:");

    for (int i = 0; i < numProcesses; i++) {
      System.out.print("Process " + (i + 1) + ": ");
      clocks[i] = scanner.nextInt();
    }

    int totalDifference = 0;
    for (int i = 1; i < numProcesses; i++) {
      totalDifference += clocks[i] - clocks[0];
    }

    int averageDifference = totalDifference / numProcesses;
    System.out.println(
      "\nAverage time difference: " + Math.abs(averageDifference) + " seconds"
    );

    System.out.println("\nAdjusted clock times:");
    for (int i = 0; i < numProcesses; i++) {
      clocks[i] -= averageDifference;
      System.out.println("Process " + (i + 1) + ": " + clocks[i] + " seconds");
    }

    scanner.close();
  }
}

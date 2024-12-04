package Practical4;

import java.util.Scanner;

public class Ring1 {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("Enter the number of processes: ");
    int num = in.nextInt();
    Rr[] proc = new Rr[num];

    // Initialize processes and input IDs
    for (int i = 0; i < num; i++) {
      proc[i] = new Rr(i, "active");
      System.out.println("Enter the ID of process " + (i + 1) + ": ");
      proc[i].id = in.nextInt();
    }

    // Sort processes by ID
    java.util.Arrays.sort(proc, (a, b) -> a.id - b.id);

    // Display sorted processes
    System.out.print("Sorted Process IDs: ");
    for (Rr p : proc) {
      System.out.print(" [" + p.index + "] " + p.id);
    }
    System.out.println(
      "\nProcess " + proc[num - 1].id + " selected as coordinator"
    );
    proc[num - 1].state = "inactive";

    while (true) {
      System.out.println("\n1. Election  2. Quit");
      int choice = in.nextInt();
      if (choice == 2) {
        System.out.println("Program terminated...");
        break;
      } else if (choice == 1) {
        System.out.println(
          "Enter the process number that initiates the election: "
        );
        int initiator = in.nextInt() - 1;
        startElection(proc, initiator, num);
      } else {
        System.out.println("Invalid choice");
      }
    }
    in.close();
  }

  private static void startElection(Rr[] proc, int initiator, int num) {
    int current = initiator;
    int maxId = proc[initiator].id;
    System.out.println("Election initiated by Process " + proc[initiator].id);

    do {
      current = (current + 1) % num;
      if (proc[current].state.equals("active")) {
        System.out.println(
          "Process " +
          proc[initiator].id +
          " sends message to " +
          proc[current].id
        );
        if (proc[current].id > maxId) {
          maxId = proc[current].id;
        }
      }
    } while (current != initiator);

    System.out.println("Process " + maxId + " selected as coordinator");
    for (Rr p : proc) {
      if (p.id == maxId) {
        p.state = "inactive";
      }
    }
  }
}

class Rr {

  int index, id;
  String state;

  public Rr(int index, String state) {
    this.index = index;
    this.state = state;
  }
}

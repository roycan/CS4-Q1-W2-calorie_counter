import java.util.Scanner;
import java.util.HashMap;

class Exercise {

  int day;
  double hours;
  String type;

  static HashMap<String, Integer> calorie_burn = new HashMap<String, Integer>();

  public static boolean check_activity(String a) {

    calorie_burn.put("cycle", 700);
    calorie_burn.put("walk", 300);
    calorie_burn.put("swim", 550);

    if (calorie_burn.containsKey(a)) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean check_duration(double hours) {
    if (hours > 0 && hours <= 24) {
      return true;
    } else {
      return false;
    }
  }

  public static int get_calories(String activity) {
    return calorie_burn.get(activity);
  }

}

class Person {
  String name;
  int day_num = 0;
  double weekly_kcal = 0;

  public Person(String n) {
    name = n;
  }

  public void log_exercise() {

    int hours;

    System.out.println("");
    System.out.println("Day " + (day_num + 1) + ": ");
    Scanner input = new Scanner(System.in);
    System.out.print("What will you do today? ");
    String activity = input.nextLine();
    boolean valid = Exercise.check_activity(activity);
    if (valid) {
      System.out.print("For how many hours will you " + activity + "? ");
      hours = input.nextInt();
      input.nextLine();
      input.close();

      valid = Exercise.check_duration(hours);

      if (valid) {
        double calories = Exercise.get_calories(activity) * hours;
        weekly_kcal += calories;
        System.out.println("You burned " + calories + " kcal.");
        System.out.println("");

      } else {
        System.out.println("Invalid input.");
        System.out.println("");
      }

    } else {
      System.out.println("Invalid input.");
      System.out.println("");
    }

    day_num++;

    if (day_num == 7) { // day7
      System.out.println("Total calories burned this week: " + weekly_kcal + " kcal");

      // reset the array
      day_num = 0;
      weekly_kcal = 0;
    }

  }
}

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");

    Person p = new Person("Mike");

    boolean restart_application = false;
    do {

      for (int i = 0; i < 7; i++) {
        p.log_exercise();
      }

      System.out.print("Restart application? (yes/no): ");
      Scanner input = new Scanner(System.in);
      String ans = input.nextLine();
      input.close();
      
      if (ans.equals("yes")) {
        restart_application = true;
      } else {
        restart_application = false;
      }

    } while (restart_application == true);

  }
}
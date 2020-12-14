import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println("Creating 3 Gumball Machine Objects");
        GumballMachine first_machine = new Gumball25CentMachine(1);
        GumballMachine second_machine = new Gumball50CentMachine(50);
        GumballMachine third_machine = new Gumball50CentAllCoinMachine(50);

        System.out.println("Turning crank on all 3 machines with no money inserted");
        first_machine.turnCrank();
        second_machine.turnCrank();
        third_machine.turnCrank();

        System.out.println("Inputting 25 Cents into 25 Cent Only Machine and turning crank");
        first_machine.insert(25);
        first_machine.turnCrank();

        System.out.println("Inputting 25 Cents into 2 Quarter Machine and trying to turn the crank");
        second_machine.insert(25);
        second_machine.turnCrank();
        System.out.println("Inputting 25 more Cents into 2 Quarter Machine and turning the crank again");
        second_machine.insert(25);
        second_machine.turnCrank();

        System.out.println("Inputting 40 Cents into 50 Cent Machine and trying to turn the crank");
        third_machine.insert(25);
        third_machine.insert(5);
        third_machine.insert(10);
        third_machine.turnCrank();
        System.out.println("Inputting 10 more Cents into 50 Cent Machine and turning the crank again");
        third_machine.insert(10);
        third_machine.turnCrank();

        System.out.println("Inputting 25 Cents into 25 Cent Only Machine but machine has no gumballs left");
        first_machine.insert(25);
        first_machine.turnCrank();


    } // end main

}

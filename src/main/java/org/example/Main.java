//package org.example;
//
////TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//public class Main {
//    public static void main(String[] args) {
//       BattleShip w = new BattleShip();
//       w.initBoard();
//       w.dispBoard();
//    }
//}
package org.example;

public class Main {
    public static void main(String[] args) {
        WelcomeScreen well = new WelcomeScreen();
        well.display();

        boolean validChoice = false;

        while (!validChoice) {
            // Call levelChoice() once and store the result
            validChoice = well.levelChoice();

            // If the choice is valid, start the BattleShip game
            if (validChoice) {
                BattleShip war = new BattleShip();
                war.initBoard();
                war.dispBoard();
                war.placeMark(8, 3, 'X');
                war.dispBoard();
            } else {
                System.out.println("Invalid choice. Please choose a valid level.");
            }
        }
    }
}


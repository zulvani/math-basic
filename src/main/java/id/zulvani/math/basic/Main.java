package id.zulvani.math.basic;

import id.zulvani.math.basic.arithmetic.Data;
import id.zulvani.math.basic.arithmetic.Game;
import id.zulvani.math.basic.arithmetic.Operator;
import id.zulvani.math.basic.arithmetic.Point;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();

        int rows, columns, min, max;
        Operator op = null;

        Scanner input = new Scanner(System.in);
        System.out.println("How many rows?");
        rows = input.nextInt();

        input = new Scanner(System.in);
        System.out.println("How many columns?");
        columns = input.nextInt();

        input = new Scanner(System.in);
        System.out.println("Minimum question value?");
        min = input.nextInt();

        input = new Scanner(System.in);
        System.out.println("Max question value?");
        max = input.nextInt();

        input = new Scanner(System.in);
        System.out.println("Operator? (+,-,*)");
        String tempOp = input.nextLine().trim();

        op = switch (tempOp) {
            case "-" -> Operator.SUB;
            case "*" -> Operator.MUL;
            default -> Operator.ADD;
        };

        Main m = new Main();
        Data[][] data = game.generate(rows,columns, min,max, op);

        for (Data[] datum : data) {
            for (Data value : datum) {
                System.out.printf("%d%s%d=%d (%d:%d); ",
                        value.getA(),
                        op.getSign(),
                        value.getB(),
                        value.getZ(),
                        value.getSiblingPosition().getRow(),
                        value.getSiblingPosition().getColumn());
            }
            System.out.println();
        }

        String play = "y";
        String inputChoice;
        Point qChoice, aChoice; // point for question choice and answer choice
        do {
            input = new Scanner(System.in);
            System.out.println("Your choice (use comma separator), example: 0,1,2,0");
            inputChoice = input.nextLine();

            String[] c = inputChoice.trim().split(",");
            if (c.length < 4) break;
            qChoice = new Point(Integer.parseInt(c[0]), Integer.parseInt(c[1]));
            aChoice = new Point(Integer.parseInt(c[2]), Integer.parseInt(c[3]));

            if (game.answer(qChoice, aChoice, data)){
                System.out.println("You are correct");
            } else{
                System.out.println("You are wrong");
            }

            input = new Scanner(System.in); 
            System.out.println("Play again? Y = Yes, N = No");
            play = input.nextLine();
        } while (play.equalsIgnoreCase("y"));
    }
}
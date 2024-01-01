package id.zulvani.math.basic;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
        Data[][] data = m.generate(rows,columns, min,max, op);

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

            if (m.answer(qChoice, aChoice, data)){
                System.out.println("You are correct");
            } else{
                System.out.println("You are wrong");
            }

            input = new Scanner(System.in); 
            System.out.println("Play again? Y = Yes, N = No");
            play = input.nextLine();
        } while (play.equalsIgnoreCase("y"));
    }

    public boolean answer(Point q, Point a, Data[][] data) {
        Data question = data[q.getRow()][q.getColumn()];
        return (question.getSiblingPosition().isEqual(a));
    }

    /**
     * @param   r           number of rows
     * @param   c           number of column
     * @param   max         maximum generated question value
     * @param   min         minimum generated question value
     * @param   op          arithmetic operator, only support +,- and *
     * @return  Data[][]    2d array of rows x column Data object
     */
    public Data[][] generate(int r, int c, int min, int max, Operator op) {
        int total = r * c; // total cell
        if (total%2 != 0){ // total cell should be even, we can't generate total odd cell, example: 7x7 =49
            return null;
        }
        int n = total/2; // total question will be generated
        Random random = new Random(); // we use java Random object to generate the variable value, you can use your own random class here
        Data[][] data = new Data[r][c]; // create 2d array of Data object
        int[] answer = new int[n]; // store the answer in array (the answer should be unique)
        int in = 0;

        do {
            int a, b, z;
            Data newData;
            boolean exists;

            // we will loop generate question and answer, until there is no existing answer in array of answer variable
            do  {
                a = random.nextInt(max + 1 - min) + min;
                b = random.nextInt(max + 1 - min) + min;
                z = switch (op) {
                    case ADD -> a + b;
                    case SUB -> a - b;
                    case MUL -> a * b;
                };

                exists = false;
                for (int i = 0; i < n; i++) {
                    if (answer[i] == z) {
                        exists = true;
                        break;
                    }
                }
                newData = new Data(a, b, z);
                answer[in] = z;
            } while (exists);

            Point[] points = new Point[2];

            // put Data into random row and column index
            for (int i = 0; i < 2; i++) {
                boolean indexFound;
                do {
                    indexFound = true;
                    points[i] = new Point(random.nextInt(r), random.nextInt(c));

                    if (data[points[i].getRow()][points[i].getColumn()] == null) {
                        Data d = (i == 0) ? newData : newData.clone();
                        data[points[i].getRow()][points[i].getColumn()] = d;
                        indexFound = false;
                    }
                } while (indexFound);

                // switch sibling
                if (i == 0) {
                    newData.setSiblingPosition(points[i]);
                } else {
                    Data sibling = data[points[0].getRow()][points[0].getColumn()];
                    Point temp = sibling.getSiblingPosition();
                    newData.setSiblingPosition(temp);
                    sibling.setSiblingPosition(points[i]);
                }
            }
            in++;
        } while (in < n);
        return data;
    }
}
package id.zulvani.math.basic.arithmetic;

import java.util.Random;

public class Game {

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
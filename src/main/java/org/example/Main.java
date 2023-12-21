package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Data data[][] = new Main().generate(6, 6, 25);

        for(int i =0; i < data.length;i++){
            for(int j=0; j < data[i].length; j++) {
                System.out.print(data[i][j].getA() + " + " + data[i][j].getB() + " = " + data[i][j].getR() + "; ");
            }
            System.out.println();
        }
    }

    public Data[][] generate(int r, int c, int max) {
        int total = r * c;
        if (total%2 != 0){
            return null;
        }
        int n = total/2;
        Random random = new Random();
        Data data[][] = new Data[r][c];
        int[] answer = new int[n];
        int in = 0;

        do {
            int a, b, z;
            Data newData;
            boolean exists;

            do  {
                a = random.nextInt(max);
                b = random.nextInt(max);
                z = a + b;
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

            boolean indexFound;

            do {
                indexFound = true;
                int iR = random.nextInt(r);
                int iC = random.nextInt(c);

                if (data[iR][iC] == null) {
                    data[iR][iC] = newData;
                    indexFound = false;
                }

            } while (indexFound);

            do {
                indexFound = true;
                int iR = random.nextInt(r);
                int iC = random.nextInt(c);

                if (data[iR][iC] == null) {
                    data[iR][iC] = newData;
                    indexFound = false;
                }

            } while (indexFound);

            in++;
        } while (in < n);
        return data;
    }
}
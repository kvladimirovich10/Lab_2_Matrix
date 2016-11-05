/**
 * Created by Кирилл Киселев on 05.11.2016.
 */

import java.io.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


public class Main {

    static Random rand = new Random();

    public static void main(String[] args) throws MyException, IOException, ClassNotFoundException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Serialize.txt"));

        Matrix A = new Matrix(3, 3);
        Matrix B = new Matrix(3, 3);
        Vector D = new Vector(3);
        Matrix C;

        createMatrix(A);
        createMatrix(B);
        createMatrix(D);

        System.out.println("Матрица А");
        show(A);
        System.out.println("Матрица В");
        show(B);
        System.out.println("Вектор D");
        show(D);

        C = Vector.transposing(D);
        System.out.println("Транспонированный вектор");
        show(C);

        try {
            C = Matrix.summation(A, B);
            System.out.println("Результат сложения двух матриц");
            show(C);

        } catch (IllegalArgumentException e) {
            Logger.getLogger(Main.class.getName()).log(new LogRecord(Level.WARNING, "Размеры матриц не совпадают в методе сложения матриц!"));
        }

        try {
            C = Matrix.multplication(A, B);
            System.out.println("Результат перемножения двух матриц");
            show(C);

            oos.writeObject(C);     // serialize array
            oos.flush();
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Serialize.txt"));    // deserialize array
            Matrix E = (Matrix) ois.readObject();
            System.out.println("Десериализация результата перемножения двух матриц");
            show(E);

        } catch (IllegalArgumentException e) {
            Logger.getLogger(Main.class.getName()).log(new LogRecord(Level.WARNING, "Соответствующие размеры матриц не совпадают в методе перемножения матриц!"));
        }

        C = Matrix.transposing(A);
        System.out.println("Транспонированная матрица");
        show(C);
    }


    public static void createMatrix(Matrix D) {
        for (int i = 0; i < D.N; i++) {
            for (int j = 0; j < D.M; j++) {
                D.data[i][j] = rand.nextInt(42);
            }
        }
    }

    public static void show(Matrix C) {
        for (int i = 0; i < C.N; i++) {
            for (int j = 0; j < C.M; j++) {
                System.out.print(C.data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}


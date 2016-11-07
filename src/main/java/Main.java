/**
 * Created by Кирилл Киселев on 05.11.2016.
 */

import java.io.*;
import java.util.logging.Logger;


public class Main {

    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws MyException, IOException, ClassNotFoundException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Serialize.txt"));

        Matrix A = Matrix.randomMatrix(3, 3);
        Matrix B = Matrix.randomMatrix(3, 3);
        Matrix D = Vector.randomVector(4);
        Matrix C;



        System.out.println("Матрица А");
        A.print(A);
        System.out.println("Матрица В");
        B.print(B);
        System.out.println("Вектор D");
        D.print(D);

        C = Vector.transposing(D);
        System.out.println("Транспонированный вектор");
        C.print(C);

        try {
            C = Matrix.summation(A, B);
            System.out.println("Результат сложения двух матриц");
            C.print(C);

        } catch (IllegalArgumentException e) {
            log.warning("Размеры матриц не совпадают в методе сложения матриц!");
        }

        try {
            C = Matrix.multplication(A, B);
            System.out.println("Результат перемножения двух матриц");
            C.print(C);

            oos.writeObject(C);     // serialize array
            oos.flush();
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Serialize.txt"));    // deserialize array
            Matrix E = (Matrix) ois.readObject();
            System.out.println("Десериализация результата перемножения двух матриц");
            E.print(E);

        } catch (IllegalArgumentException e) {
            log.warning("Соответствующие размеры матриц не совпадают в методе перемножения матриц!");
        }

        C = Matrix.transposing(A);
        System.out.println("Транспонированная матрица");
        C.print(C);
    }




}


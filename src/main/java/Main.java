/**
 * Created by Кирилл Киселев on 05.11.2016.
 */

import java.io.*;
import java.util.logging.Logger;


public class Main {

    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws MyException, IOException, ClassNotFoundException {



        Matrix A = Matrix.randomMatrix(3, 3);
        Matrix B = Matrix.randomMatrix(3, 3);
        Matrix D = Vector.randomVector(4);
        Matrix C;



        System.out.println("Матрица А");
        A.print();
        System.out.println("Матрица В");
        B.print();
        System.out.println("Вектор D");
        D.print();

        C = D.transposing();
        System.out.println("Транспонированный вектор");
        C.print();

        try {
            C = A.summation(B);
            System.out.println("Результат сложения двух матриц");
            C.print();

        } catch (IllegalArgumentException e) {
            log.warning("Размеры матриц не совпадают в методе сложения матриц!");
        }

        try {
            C = A.multiplication(B);
            System.out.println("Результат перемножения двух матриц");
            C.print();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Serialize.txt"));

            // serialize array
            oos.writeObject(C);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Serialize.txt"));    // deserialize array
            Matrix E = (Matrix) ois.readObject();
            ois.close();
            System.out.println("Десериализация результата перемножения двух матриц");
            E.print();

        } catch (IllegalArgumentException e) {
            log.warning("Соответствующие размеры матриц не совпадают в методе перемножения матриц!");
        }

        C = A.transposing();
        System.out.println("Транспонированная матрица");
        C.print();
    }
}


/**
 * Created by Кирилл Киселев on 05.11.2016.
 */

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

class Matrix implements Serializable {

    private static Random rand = new Random();

    int N;
    int M;
    int[][] data;

    Matrix() {
    }

    private Matrix(int N, int M) {
        this.N = N;
        this.M = M;
        data = new int[N][M];
    }

    static Matrix fill(Matrix data) {
        for (int i = 0; i < data.N; i++) {
            for (int j = 0; j < data.M; j++) {
                data.data[i][j] = rand.nextInt(42);
            }
        }
        return data;
    }

    static Matrix randomMatrix(int N, int M) {
        Matrix data = new Matrix(N, M);
        fill(data);
        return data;
    }

    void print(Matrix C) {

        for (int i = 0; i < C.N; i++) {
            System.out.println(Arrays.toString(C.data[i]));
        }
        System.out.println();

        /*for (int i = 0; i < C.N; i++) {
            for (int j = 0; j < C.M; j++) {
                System.out.print(C.data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();*/
    }

    static Matrix summation(Matrix A, Matrix B) {

        if (A.N != B.N || A.M != B.M) throw new IllegalArgumentException("Sizes of matrix must be equal");

        Matrix C = new Matrix(A.N, A.M);

        for (int i = 0; i < A.N; i++) {
            for (int j = 0; j < A.M; j++) {
                C.data[i][j] = A.data[i][j] + B.data[i][j];
            }
        }

        return C;
    }

    private static void swap(Matrix A, int i, int j) {
        int keeper = A.data[i][j];
        A.data[i][j] = A.data[j][i];
        A.data[j][i] = keeper;
    }

    static Matrix transposing(Matrix A) {

        if (A.N == A.M) {
            for (int i = 0; i < A.N; i++) {
                for (int j = i + 1; j < A.M; j++) {
                    swap(A, i, j);
                }
            }
            return A;
        } else {
            Matrix Transposed = new Matrix(A.M, A.N);

            for (int i = 0; i < A.N; i++) {
                for (int j = 0; j < A.M; j++) {
                    Transposed.data[j][i] = A.data[i][j];
                }
            }
            return Transposed;
        }

    }

    static Matrix multplication(Matrix A, Matrix B) {

        if (A.M != B.N) throw new IllegalArgumentException("Sizes of matrix must be equal " + A.M + " != " + B.N + "");

        Matrix C = new Matrix(A.N, B.M);
        int collector = 0;

        for (int i = 0; i < C.N; i++) {
            for (int j = 0; j < C.M; j++) {
                for (int k = 0; k < A.M; k++) {
                    collector += A.data[i][k] * B.data[k][j];
                }
                C.data[i][j] = collector;
                collector = 0;
            }
        }

        return C;
    }
}
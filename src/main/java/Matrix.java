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

    void print() {
        for (int i = 0; i < this.N; i++) {
            System.out.println(Arrays.toString(this.data[i]));
        }
        System.out.println();
    }

    Matrix summation(Matrix B) {

        if (this.N != B.N || this.M != B.M) throw new IllegalArgumentException("Sizes of matrix must be equal");

        Matrix C = new Matrix(this.N, this.M);

        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.M; j++) {
                C.data[i][j] = this.data[i][j] + B.data[i][j];
            }
        }

        return C;
    }

    private static void swap(Matrix A, int i, int j) {
        int keeper = A.data[i][j];
        A.data[i][j] = A.data[j][i];
        A.data[j][i] = keeper;
    }

    Matrix transposing() {
        if (this.N == this.M) {
            for (int i = 0; i < this.N; i++) {
                for (int j = i + 1; j < this.M; j++) {
                    swap(this, i, j);
                }
            }
            return this;
        } else {
            Matrix Transposed = new Matrix(this.M, this.N);

            for (int i = 0; i < this.N; i++) {
                for (int j = 0; j < this.M; j++) {
                    Transposed.data[j][i] = this.data[i][j];
                }
            }
            return Transposed;
        }
    }

    Matrix multiplication(Matrix B) {

        if (this.M != B.N) throw new IllegalArgumentException("Sizes of matrix must be equal " + this.M + " != " + B.N + "");

        Matrix C = new Matrix(this.N, B.M);
        int collector = 0;

        for (int i = 0; i < C.N; i++) {
            for (int j = 0; j < C.M; j++) {
                for (int k = 0; k < this.M; k++) {
                    collector += this.data[i][k] * B.data[k][j];
                }
                C.data[i][j] = collector;
                collector = 0;
            }
        }

        return C;
    }
}
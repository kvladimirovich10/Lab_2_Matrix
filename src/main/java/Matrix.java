/**
 * Created by Кирилл Киселев on 05.11.2016.
 */
import java.io.Serializable;

public class Matrix implements Serializable{
    int N;
    int M;
    int[][] data;

    public Matrix(int N, int M) {
        this.N = N;
        this.M = M;
        data = new int[N][M];
    }

    public Matrix() {
    }

    public static Matrix summation(Matrix A, Matrix B) {

        if (A.N != B.N || A.M != B.M) throw new IllegalArgumentException("Sizes of matrix must be equal");

        Matrix C = new Matrix(A.N, A.M);

        for (int i = 0; i < A.N; i++) {
            for (int j = 0; j < A.M; j++) {
                C.data[i][j] = A.data[i][j] + B.data[i][j];
            }
        }

        return C;
    }

    public static void swap(Matrix A, int i, int j) {

        int keeper = A.data[i][j];
        A.data[i][j] = A.data[j][i];
        A.data[j][i] = keeper;
    }

    public static Matrix transposing(Matrix A) {

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

    public static Matrix multplication(Matrix A, Matrix B) {

        if (A.M != B.N) throw new IllegalArgumentException("Sizes of matrix must be equal "+ A.M +" != "+ B.N +"");

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
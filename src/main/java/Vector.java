/**
 * Created by Кирилл Киселев on 05.11.2016.
 */
public class Vector extends Matrix {

    Vector(int N) {
        this.N = N;
        this.M = 1;
        data = new int[N][M];
    }

    static Matrix randomVector(int N) {
        Vector data = new Vector(N);
        fill(data);
        return data;
    }

}

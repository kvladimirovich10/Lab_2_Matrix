/**
 * Created by Кирилл Киселев on 05.11.2016.
 */
public class Vector extends Matrix{

    public Vector(int N) {
        this.N = N;
        this.M = 1;
        data = new int[N][M];
    }

}

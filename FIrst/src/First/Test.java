package First;

import java.util.Arrays;


class Mechanum {

    private double[][] MODEL = {
            {-1.0, 1.0, 1.0},
            {-1.0, 1.0, -1.0},
            {-1.0, 1.0, 1.0},
            {-1.0, 1.0, -1.0}
    };

    private double[] VECTOR;


    Mechanum(double length, double width)
    {
        VECTOR = new double[4];

        MODEL[0][0] = -length - width;
        MODEL[1][0] = length + width;
        MODEL[2][0] = length + width;
        MODEL[3][0] = -length - width;
        

    }


    double[] tick(double wx, double by, double bx)
    {
        double[] movement = {wx, by, bx};

        for(int i = 0; i < 4; i++) VECTOR[i] = 0.0; //reset

        /* keep track of count, and current movement vector */
        for(int k = 0, curr = 0; k < 4; k++) VECTOR[k] += movement[curr] * MODEL[k][curr];
        for(int k = 0, curr = 1; k < 4; k++) VECTOR[k] += movement[curr] * MODEL[k][curr];
        for(int k = 0, curr = 2; k < 4; k++) VECTOR[k] += movement[curr] * MODEL[k][curr];
       
        //normalize
        double max = 0;
        for(double d : VECTOR) max = Math.max(max, d);
        for(int i = 0; i < 4; i++) VECTOR[i] /= max;
        
        return VECTOR;
    }

}

public class Test {
	public static void main(String[] args) {
		
		Mechanum model = new Mechanum(10,10);
		
		double w = 1 , y = 0, x= 1;
		System.out.println(Arrays.toString(model.tick(w,y,x)));
		
	}

}

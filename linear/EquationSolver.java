//Name: Nazifa Nazrul Rodoshi
//Neptun: W8CZNE

package linear;

import java.util.Arrays;
import linear.algebra.GaussianElimination;

public class EquationSolver 
{

    public static void main(String[] vals) 
    {
        if (vals.length == 0) 
        {
            System.out.println("Usage: java EquationSolver equation1 equation2 ...");
            return;
        }

        String eqn = vals[0];
        String[] rows = eqn.split(" ");
        int numRows = rows.length;
        int numCols = rows[0].split(",").length;
        double[][] matrix = new double[numRows][numCols];
        for (int i = 0; i < numRows; i++) 
        {
            String[] rVals = rows[i].split(",");
            if (rVals.length != numCols) 
            {
                System.out.println("Error");
                return;
            }
            double[] d = stringsToDoubles(rVals);
            for (int j = 0; j < d.length; j++) 
            {
                matrix[i][j] = d[j];
            }
        }

        GaussianElimination solver = new GaussianElimination(numRows, numCols, matrix);
        System.out.println("insert matrix");
            solver.print();
            solver.rowEchelonForm();
        System.out.println("write matrix in row echelon form");
            solver.print();
            solver.backSubstitution();
            System.out.println("write matrix after back substitution");
            solver.print();
    }

    public static double[] stringsToDoubles(String[] values) 
    {
        double[] val = new double[values.length];
        for (int i = 0; i < values.length; i++) 
        {
            val[i] = Double.parseDouble(values[i]);
        }
        return val;
    }
}
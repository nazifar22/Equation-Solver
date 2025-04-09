//Name: Nazifa Nazrul Rodoshi
//Neptun: W8CZNE

package linear.algebra;

import java.util.Arrays;

public class GaussianElimination 
{
    private double[][] matrix;
    private int rows;
    private int columns;

    public GaussianElimination(int rows, int columns, double[][] matrix) 
    {
        this.matrix = new double[rows][columns];
        for (int i = 0; i < rows; i++) 
        {
            if (matrix[i].length != columns) 
            {
                throw new IllegalArgumentException("number of rows have to match number of columns");
            }
            this.matrix[i] = Arrays.copyOf(matrix[i], columns);
        }
        this.rows = rows;
        this.columns = columns;
    }

    public double[][] getMatrix() 
    {
        return matrix;
    }

    public int getRows() 
    {
        return rows;
    }

    public int getCols() 
    {
        return columns;
    }

    public void setMatrix(double[][] matrix) 
    {
        if (matrix.length != this.rows || matrix[0].length != this.columns)
            throw new IllegalArgumentException("number of rows and columns in new matrix does not match the current matrix");
        for (int i = 0; i < this.rows; i++) {
            this.matrix[i] = Arrays.copyOf(matrix[i], this.columns);
        }
    }

    /*public void rowEchelonForm() {
        int l = 0;
        for (int r = 0; r < rows; r++) {
            if (cols <= l) {
                return;
            }
            int i = r;
            while (matrix[i][l] == 0) {
                i++;
                if (rows == i) {
                    i = r;
                    l++;
                    if (cols == l) {
                        return;
                    }
                }
            }*/

    public void rowEchelonForm() 
    {
    //Initialization of the pivot row(h) and colomn(k)
    int h = 0;
    int k = 0;
    
    while (h < this.rows && k < this.columns) 
    {
        //Find the k-th pivot
        int maxRowIndex = argMax(h, k);
        
        if (this.matrix[maxRowIndex][k] == 0) 
        {
            //No pivot in this column, pass to next column
            k++;
        } 
        else 
        {
            swapRows(h, maxRowIndex);
            //Do for all rows below pivot
            for (int i = h + 1; i < this.rows; i++) 
            {
                double rowFactor = this.matrix[i][k] / this.matrix[h][k];
                //fill the lower part of pivot column with zeros
                this.matrix[i][k] = 0;
                //Do for all remaining elements in current row
                for (int j = k + 1; j < this.columns; j++) 
                {
                    this.matrix[i][j] -= this.matrix[h][j] * rowFactor;
                }
            }
            
            //Increase pivot row and column
            h++;
            k++;
        }
    }

for (int i = 0; i < this.rows; i++) 
{
    for (int j = 0; j < this.columns; j++) 
    {
        if (i == j && this.matrix[i][j] != 0) 
        {
        double div = this.matrix[i][j];
            for (int l = j; l < this.columns; l++) 
            {
            this.matrix[i][l] /= div;
            }
        }
    }
}
    if (this.columns >= 2 && this.matrix[1][1] != 0) 
    {
        double div = this.matrix[1][1];
        for (int p = 1; p < this.columns; p++) 
        {
            this.matrix[1][p] /= div;
        }
    }
}

private int argMax(int row, int col) 
{
    int maxRow = row;
    for (int i = row + 1; i < this.rows; i++) 
    {
        if(Math.abs(this.matrix[i][col]) > Math.abs(this.matrix[maxRow][col]))
            maxRow = i;
    }
    return maxRow;
}

private void swapRows(int i, int j) 
{
    double[] temporary = this.matrix[i];
    this.matrix[i] = this.matrix[j];
    this.matrix[j] = temporary;
}

private void multiplyAndAddRow(int addRow, int mulRow, int colIndex) 
{
    double ratio = this.matrix[addRow][colIndex] / this.matrix[mulRow][colIndex];
    for (int i = colIndex; i < this.columns; i++) 
    {
        this.matrix[addRow][i] -= ratio * this.matrix[mulRow][i];
    }
}

private void multiplyRow(int rowIndex, int colIndex) 
{
    double div = matrix[rowIndex][colIndex];
    for (int i = colIndex; i < this.columns; i++) 
    {
        matrix[rowIndex][i] /= div;
    }
}

/*public double[] backSubstitution() {
    double[] solution = new double[numRows];
    for (int i = rows - 1; i >= 0; i--) {
        if (matrix[i][i] == 0) {
            throw new IllegalArgumentException("no unique solution available");
        }
        solution[i] = matrix[i][numCols-1];
        for (int j = i + 1; j < numRows; j++) {
            solution[i] -= matrix[i][j] * solution[j];
        }
        solution[i] /= matrix[i][i];
    }
    return solution;
}*/

public void backSubstitution() 
{
    double[] solution = new double[columns];
    
    for (int i = this.rows - 1; i >= 0; i--) 
    {
        if (matrix[i][i] == 0) 
        {
        throw new IllegalArgumentException("no unique solution available");
        }
        
        int j = 0;
        while (j < this.columns && this.matrix[i][j] == 0) 
        {
            j++;
        }
        
        if (j < this.columns) 
        {
            double div = this.matrix[i][j];
            
            for (int k = j; k < this.columns; k++) 
            {
                this.matrix[i][k] /= div;
            }
            
            for (int k = i - 1; k >= 0; k--) 
            {
                double factor = this.matrix[k][j];
                
                for (int l = j; l < this.columns; l++) 
                {
                    this.matrix[k][l] -= this.matrix[i][l] * factor;
                }
            }
        }
    }
    for (int i = 0; i < this.rows; i++) 
    {
        solution[i] = this.matrix[i][this.columns - 1];
    }

    //return the solution array
    //return solution;
}

private void checkMatrixDimensions(double[][] matrix){}

    public void print() 
    {
        for (int i = 0; i < this.rows; i++) 
        {
            for (int j = 0; j < this.columns; j++) 
            {
                
                /*double coef = matrix[i][j];
                if (coef != 0) {
                    equation.append((coef < 0) ? "-" : "+");
                    if (Math.abs(coef) != 1) {
                        equation.append(String.format("%.1f", Math.abs(coef)));
                    }
                    equation.append("x").append(j+1);
                }*/
                System.out.print(matrix[i][j] + " ");
            }
            
            /*double constant = matrix[i][numCols-1];
            equation.append((constant < 0) ? "-" : "+");
            equation.append(String.format("%.1f", Math.abs(constant)));
            System.out.println(equation + "=0");*/
            System.out.println();
        }
    }
}


# Equation-Solver
This Java program implements a linear algebra equation solver using Gaussian elimination to reduce a system of linear equations into reduced row echelon form and solve it through back substitution. The core functionality is encapsulated in the linear.algebra.GaussianElimination class, which manages a matrix represented as a 2D array of doubles. The class provides getters for dimensions and matrix data, enforces proper matrix shape, and includes operations like argMax, swapRows, multiplyRow, and multiplyAndAddRow to facilitate row transformations during elimination.

The main solver workflow consists of converting the matrix to reduced row echelon form using rowEchelonForm(), followed by backSubstitution() to isolate variables and compute the final solution. If a diagonal element is zero during substitution, an exception is thrown to indicate no unique solution. The print() method outputs the matrix state at each step in a readable format.

The entry point is the linear.EquationSolver class, which parses command-line input formatted as comma-separated coefficients and constants (e.g., 2,1,-1,8 -3,-1,2,-11 -2,1,2,-3), constructs the matrix, and walks the user through the transformation steps, printing the matrix at each stage. The implementation is accompanied by JUnit tests for correctness and structure (GaussianEliminationTest, GaussianEliminationStructureTest, and GaussianEliminationTestSuite) to ensure full compliance with the assignment requirements.

Requirements:
Java Development Kit (JDK) version 17 or higher.
(Recommended: OpenJDK 20)
Terminal or command line access.

Running the Program:
The entry point of the program is the linear.EquationSolver class.

To solve a system of linear equations using the program, follow these steps:
Compile the Program
  javac linear/algebra/GaussianElimination.java linear/EquationSolver.java
Run the Program
Use the command below, passing a quoted string of equations as a single command-line argument.
Each equation should be written as comma-separated coefficients (including the constant term on the right-hand side). Separate multiple equations with a space.
  java linear.EquationSolver "2,1,-1,8 -3,-1,2,-11 -2,1,2,-3"

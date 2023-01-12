package Lab_2;

public class Laba_2_main
{
public static void main(String [] args) throws NotEqualLengthsOfMatrixException {
//    Matrix mat; // Make new matrix
//    mat = new Matrix(3, 3); // Make matrix row/column
//    mat.fillRandomValues(); // Fill matrix with random num
//    mat.displayMatrix(); // Show matrix
//
//    Matrix mat_1 = Matrix.copy(mat); // Copy matrix
//    mat_1.displayMatrix(); // Show matrix
//
//    System.out.println(mat.getElement(1, 2)); // Get el. of matrix
//
//    mat.getRow(0).displayMatrix(); // Get row of matrix
//
//    mat.getColAsMatrixCol(1).displayMatrix(); // Get column of matrix
//    mat.getColAsMatrixRow(1).displayMatrix(); // Get column of matrix as row (transpose)
//
//    System.out.println(mat.getVerticalLength() + " x " + mat.getHorizontalLength()); // Get dimension(-s)
//
//    System.out.println(mat.Equals(mat_1)); // Method equal
//    System.out.println(mat.HashCode() ==  mat_1.HashCode()); // Method hashcode
//
//    int[][] vector_col = {{1},{2},{3}}; // vector-column
//    Matrix mat_2 = new Matrix(vector_col, 1); // Diagonal matrix directed by vector-column
//    mat_2.displayMatrix(); // Show matrix
//
//    int[] vector_row = {1,2,3}; // vector-row
//    mat_2 = new Matrix(vector_row, 1); // Diagonal matrix directed by vector-row
//    mat_2.displayMatrix(); // Show matrix
//
//    mat_2 = new Matrix(3, "diagonal");
//    mat_2.displayMatrix(); // Show matrix
//
//    mat_2 = new Matrix(3, "row random");
//    mat_2.displayMatrix(); // Show matrix
//
//    mat_2 = new Matrix(3, "col random");
//    mat_2.displayMatrix(); // Show matrix
//
//    int[][] tr1 = {{1,2,3,4},{2,5,7,9},{6,4,8,5},{8,3,1,7}};
//    int[][] tr2 = {{1,2,3},{6,4,5},{9,8,7}};
//    int[][] tr3 = {{1,2},{3,7}};
//
//    int[][] tr4 = {{1,2},{3,4}};
//   int[][] tr5 = {{15,9,3},{8,7,6},{4,5,7}};
//    int[][] tr6 = {{8,4,3},{16,10,6},{15,8,6}};

// Matrix mat_1 = new Matrix(tr5);
// Matrix mat_3 = new Matrix(mat_1, "U");
// mat_3.displayMatrix();

//    Matrix A = new Matrix(3,2);
//    A.fillRandomValues();
//    Matrix B = new Matrix(2,3);
//    B.fillRandomValues();
////
//    Matrix C = Matrix.multiply(A,B);
//    A.displayMatrix();
//    B.displayMatrix();
//    C.displayMatrix();
////
//    Matrix D =  new Matrix(C, "U");
//    D.displayMatrix();
////
//    Matrix A1 = Matrix.copy(A);
//    A.displayMatrix();
//    A1.displayMatrix();
//    System.out.println(A.Equals(A1));
//    System.out.println(A.HashCode());
//    System.out.println(A1.HashCode());
////
//    A1.setElement(0,0,-100);
//
//    A.displayMatrix();
//    A1.displayMatrix();
//    System.out.println(A.Equals(A1));
//    System.out.println(A.HashCode());
//    System.out.println(A1.HashCode());
////
//    A.displayMatrix();
//    A = Matrix.MatrixMultiplyNum(A, 2);
//    A.displayMatrix();
//

    Matrix A = new Matrix(2, 5);
    A.displayMatrix();
    A.fillRandomValues();
    A.displayMatrix();

    Matrix B = Matrix.copy(A);
    B.displayMatrix();

    B.setElement(0, 0, 100);
    A.displayMatrix();
    B.displayMatrix();

    int[][] tr1 = {{1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}};
    Matrix C = new Matrix(tr1);

    int[][] tr2 = {{1, 1}, {1, 1}, {1, 0}, {0, 0}, {0, 0}};
    Matrix D = new Matrix(tr2);

    C.displayMatrix();
    D.displayMatrix();
    System.out.println(C.Equals(D));

    Matrix E = new Matrix(4, 4);
    E.fillRandomValues();
    Matrix F = new Matrix(E, "U");
    Matrix G = new Matrix(F, "L");
    E.displayMatrix();
    F.displayMatrix();
    G.displayMatrix();
}
}

class NotEqualLengthsOfMatrixException extends Exception {}

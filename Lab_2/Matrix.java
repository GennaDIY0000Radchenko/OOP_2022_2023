package Lab_2;

import java.util.Objects;
import java.util.Random;

public final class Matrix {

    int row, col;
    int[][] matrix;
    float[][] float_matrix;

    //

    public Matrix(Matrix matrix){
        this.row = matrix.row;
        this.col = matrix.col;
        this.matrix = matrix.matrix;
        this.float_matrix = matrix.float_matrix;
    }

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        this.matrix = new int[this.row][this.col];
    }

    public Matrix(int[][] paramMatrix) {
        this.row = paramMatrix.length;
        this.col = paramMatrix[0].length;
        this.matrix = paramMatrix;
    }
    //
    public Matrix(int[] vector_row, int vector) {
        this.row = vector_row.length;
        this.col = vector_row.length;
        this.matrix = new int[this.row][this.col];
        for (int i = 0; i < vector_row.length; i++)
        {
            setElement(i, i, vector_row[i]);
        }
    }

    public Matrix(int[][] vector_col, int vector) {
        this.row = vector_col.length;
        this.col = vector_col.length;
        this.matrix = new int[this.row][this.col];
        for (int i = 0; i < vector_col.length; i++)
        {
            setElement(i, i, vector_col[i][0]);
        }
    }

    public Matrix(int dimension, String mode){
        Random rand = new Random();
        if (Objects.equals(mode, "diagonal"))
        {
            this.row = dimension;
            this.col = dimension;
            this.matrix = new int[this.row][this.col];
            for (int i = 0; i < dimension; i++)
            {
                setElement(i, i, 1);
            }
        }
        else if(Objects.equals(mode, "row random"))
        {
            this.row = 1;
            this.col = dimension;
            this.matrix = new int[this.row][this.col];
            for (int i = 0; i < dimension; i++)
            {
                this.matrix[0][i] = rand.nextInt(100);
            }
        }
        else if(Objects.equals(mode, "col random"))
        {
            this.row = dimension;
            this.col = 1;
            this.matrix = new int[this.row][this.col];
            for (int i = 0; i < dimension; i++)
            {
                this.matrix[i][0] = rand.nextInt(100);
            }
        }
    }

    public Matrix(Matrix paramMatrix, String mode) throws NotEqualLengthsOfMatrixException {
        if (paramMatrix.getVerticalLength() != paramMatrix.getHorizontalLength()) {
            throw new NotEqualLengthsOfMatrixException();
        }
        else {
            float k;
            float[][] result = new float[paramMatrix.row][paramMatrix.row];
            for (int i = 0; i < paramMatrix.row; i++) {
                for (int j = 0; j < paramMatrix.row; j ++) {
                    result[i][j] = (float) paramMatrix.getElement(i, j);}}
            if (Objects.equals(mode, "U")) {
                for (int j = 0; j < paramMatrix.row - 1; j++) {
                    for (int i = j + 1; i < paramMatrix.row; i++) {
                        k = result[i][j] / result[j][j];
                        for (int q = 0; q < paramMatrix.row; q++) {
                            result[i][q] = result[i][q] - k * result[j][q];
                        }
                    }
                }
            }
            else if (Objects.equals(mode, "L")){
                for (int j = paramMatrix.row - 1; j > 0; j--) {
                    for (int i = j - 1; i >= 0; i--) {
                        k = result[i][j] / result[j][j];
                        for (int q = 0; q <= j; q++) {
                            result[i][q] = result[i][q] - k * result[j][q];
                        }
                    }
                }
            }

            this.row = paramMatrix.row;
            this.col = paramMatrix.row;
            this.float_matrix = result;
        }
    }

    //

    public int getElement(int row, int col) {
        return this.matrix[row][col];
    }

    public void setElement(int row, int col, int value) {
        this.matrix[row][col] = value;
    }

    //

    public Matrix getRow(int row) {
        Matrix rowMatrix = new Matrix(1, this.matrix[row].length);
        for (int i = 0; i < this.matrix[row].length; i++) {
            rowMatrix.setElement(0, i, this.matrix[row][i]);
        }

        return rowMatrix;
    }

    public Matrix getColAsMatrixCol(int col) {
        Matrix colMatrix = new Matrix(this.matrix.length, 1);
        for (int i = 0; i < this.matrix.length; i++) {
            colMatrix.setElement(i, 0, this.matrix[i][col]);
        }

        return colMatrix;
    }

    public Matrix getColAsMatrixRow(int col) {return transpose(getColAsMatrixCol(col));}

    //

    public int getVerticalLength() {return this.matrix.length;}

    public int getHorizontalLength() {return this.matrix[0].length;}

    //

    public static Matrix copy(Matrix paramMatrix) {
        Matrix copyMatrix = new Matrix(paramMatrix.row, paramMatrix.col);
        for (int i = 0; i < paramMatrix.row; i++) {
            for (int j = 0; j < paramMatrix.col; j++) {
                copyMatrix.setElement(i, j, paramMatrix.getElement(i, j));
            }
        }

        return copyMatrix;
    }

    public static int[][] copy(int[][] paramMatrix) {
        int[][] copyMatrix = new int[paramMatrix.length][paramMatrix[0].length];
        for (int i = 0; i < paramMatrix[0].length; i++) {
            System.arraycopy(paramMatrix[i], 0, copyMatrix[i], 0, paramMatrix.length);
        }

        return copyMatrix;
    }

    public void fillRandomValues() {
        Random rand = new Random();

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.matrix[i][j] = rand.nextInt(100);
            }
        }
    }

    //

    public void displayMatrix() {
        if (this.matrix != null) {
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col; j++) {
                    System.out.print(this.matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
        else {
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col; j++) {
                    System.out.print(this.float_matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public boolean Equals(Matrix second) {
        boolean result = true;
        if (this.matrix.length != second.getVerticalLength() || this.matrix[0].length != second.getHorizontalLength()) {
            result = false;
        } else {
            for (int i = 0; i < this.matrix.length; i++) {
                for (int j = 0; j < this.matrix[0].length; j++) {
                    if (this.matrix[i][j] != second.getElement(i, j)) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public int HashCode() {
        StringBuilder result = new StringBuilder();
        result.append(this.row).append("_").append(this.col).append("_");
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                result.append(this.matrix[i][j]).append("_");
            }
        }

        return result.toString().hashCode();
    }

    //

    public static int[][] transpose(int[][] paramMatrix) {
        int[][] tmpMatrix = new int[paramMatrix[0].length][paramMatrix.length];
        for (int i = 0; i < paramMatrix[0].length; i++) {
            for (int j = 0; j < paramMatrix.length; j++) {
                tmpMatrix[i][j] = paramMatrix[j][i];
            }
        }
        return tmpMatrix;
    }

    public static Matrix transpose(Matrix paramMatrix) {
        Matrix tmpMatrix = new Matrix(paramMatrix.col, paramMatrix.row);
        for (int i = 0; i < paramMatrix.col; i++) {
            for (int j = 0; j < paramMatrix.row; j++) {
                tmpMatrix.setElement(i, j, paramMatrix.getElement(j, i));
            }
        }
        return tmpMatrix;
    }

    public static Matrix add(Matrix first, Matrix second) throws NotEqualLengthsOfMatrixException {
        if (first.getVerticalLength() != second.getVerticalLength() ||
                first.getHorizontalLength() != second.getHorizontalLength()) {
            throw new NotEqualLengthsOfMatrixException();
        } else {
            Matrix tmpMatrix = new Matrix(first.getVerticalLength(), second.getHorizontalLength());
            for (int i = 0; i < tmpMatrix.getHorizontalLength(); i++) {
                for (int j = 0; j < tmpMatrix.getVerticalLength(); j++) {
                    tmpMatrix.setElement(i, j, first.getElement(i, j) + second.getElement(i, j));
                }
            }
            return tmpMatrix;
        }
    }

    public static Matrix subtract(Matrix first, Matrix second) throws NotEqualLengthsOfMatrixException {
        if (first.getVerticalLength() != second.getVerticalLength() ||
                first.getHorizontalLength() != second.getHorizontalLength())
            throw new NotEqualLengthsOfMatrixException();
        else {
            Matrix tmpMatrix = new Matrix(first.getVerticalLength(), second.getHorizontalLength());
            for (int i = 0; i < tmpMatrix.getHorizontalLength(); i++) {
                for (int j = 0; j < tmpMatrix.getVerticalLength(); j++) {
                    tmpMatrix.setElement(i, j, first.getElement(i, j) - second.getElement(i, j));
                }
            }
            return tmpMatrix;
        }
    }

    public static Matrix multiply(Matrix first, Matrix second) throws NotEqualLengthsOfMatrixException {
        if (first.getHorizontalLength() != second.getVerticalLength())
            throw new NotEqualLengthsOfMatrixException();
        else {
            Matrix tmpMatrix;
            int n = first.getVerticalLength();
            int m = second.getHorizontalLength();
            int o = second.getVerticalLength();
            int[][] tmpArr = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    for (int k = 0; k < o; k++) {
                        tmpArr[i][j] += first.getElement(i, k) * second.getElement(k, j);
                    }
                }
            }
            tmpMatrix = new Matrix(tmpArr);
            return tmpMatrix;
        }
    }

    public static Matrix MatrixPlusNum(Matrix first, int number) {
        Matrix tmpMatrix = new Matrix(first.getVerticalLength(), first.getHorizontalLength());
        for (int i = 0; i < tmpMatrix.getHorizontalLength(); i++) {
            for (int j = 0; j < tmpMatrix.getVerticalLength(); j++) {
                tmpMatrix.setElement(i, j, first.getElement(i, j) + number);
            }
        }

        return tmpMatrix;
    }

    public static Matrix MatrixMinusNum(Matrix first, int number) {
        Matrix tmpMatrix = new Matrix(first.getVerticalLength(), first.getHorizontalLength());
        for (int i = 0; i < tmpMatrix.getHorizontalLength(); i++) {
            for (int j = 0; j < tmpMatrix.getVerticalLength(); j++) {
                tmpMatrix.setElement(i, j, first.getElement(i, j) - number);
            }
        }

        return tmpMatrix;
    }

    public static Matrix MatrixMultiplyNum(Matrix first, int number) {
        Matrix tmpMatrix = new Matrix(first.getVerticalLength(), first.getHorizontalLength());
        for (int i = 0; i < tmpMatrix.getVerticalLength(); i++) {
            for (int j = 0; j < tmpMatrix.getHorizontalLength(); j++) {
                tmpMatrix.setElement(i, j, first.getElement(i, j) * number);
            }
        }

        return tmpMatrix;
    }

    //

}

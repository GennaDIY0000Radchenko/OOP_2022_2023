package Lab_2;

import java.util.Objects;
import java.util.Random;

public class Matrix_im {

    private final int row;
    private final int col;
    private final int[][] matrix;
    private final float[][] float_matrix;

    public Matrix_im(Matrix_im matrix) {
        this.row = matrix.row;
        this.col = matrix.col;
        this.matrix = matrix.matrix;
        this.float_matrix = matrix.float_matrix;
    }

    public Matrix_im(int row, int col) {
        this.row = row;
        this.col = col;
        this.matrix = new int[this.row][this.col];
        this.float_matrix = new float[this.row][this.col];
    }

    public Matrix_im(int[][] paramMatrix) {
        this.row = paramMatrix.length;
        this.col = paramMatrix[0].length;
        this.matrix = paramMatrix;
        this.float_matrix = null;
    }

    //
    public Matrix_im(int[] vector_row, int vector) {
        this.row = vector_row.length;
        this.col = vector_row.length;
        this.matrix = new int[this.row][this.col];
        for (int i = 0; i < vector_row.length; i++) {
            setElement(i, i, vector_row[i]);
        }
        this.float_matrix = null;
    }

    public Matrix_im(int[][] vector_col, int vector) {
        this.row = vector_col.length;
        this.col = vector_col.length;
        this.matrix = new int[this.row][this.col];
        for (int i = 0; i < vector_col.length; i++) {
            setElement(i, i, vector_col[i][0]);
        }
        this.float_matrix = null;
    }

    public Matrix_im(int dimension, String mode) {
        Random rand = new Random();
        if (Objects.equals(mode, "diagonal")) {
            this.row = dimension;
            this.col = dimension;
            this.matrix = new int[this.row][this.col];
            for (int i = 0; i < dimension; i++) {
                setElement(i, i, 1);
            }
            this.float_matrix = null;
        } else if (Objects.equals(mode, "row random")) {
            this.row = 1;
            this.col = dimension;
            this.matrix = new int[this.row][this.col];
            for (int i = 0; i < dimension; i++) {
                this.matrix[0][i] = rand.nextInt(100);
            }
            this.float_matrix = null;
        } else if (Objects.equals(mode, "col random")) {
            this.row = dimension;
            this.col = 1;
            this.matrix = new int[this.row][this.col];
            for (int i = 0; i < dimension; i++) {
                this.matrix[i][0] = rand.nextInt(100);
            }
            this.float_matrix = null;
        } else {
            this.row = dimension;
            this.col = dimension;
            this.matrix = new int[this.row][this.col];
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    this.matrix[j][i] = rand.nextInt(100);
                }
            }
            this.float_matrix = null;
        }


    }

    public int getElement(int row, int col) {
        return this.matrix[row][col];
    }

    public void setElement(int row, int col, int value) {
        this.matrix[row][col] = value;
    }
}

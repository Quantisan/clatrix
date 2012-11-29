package clatrix;

import org.jblas.*;
import clojure.lang.*;

/*
* Based on https://github.com/forward/incanter-BLAS by Antonio Garrote
*
* */
public class Matrix implements ISeq {
    public boolean oneDimensional = false;
    IPersistentMap meta;
    private DoubleMatrix matrix;

    /*********************
     * Matrix constructors
     *********************/
    public Matrix(int nrow, int ncol) {
        this(nrow,ncol,0);
    }

    public Matrix(int nrow, int ncol, Number initValue) {
        this.matrix = new DoubleMatrix(nrow,ncol);
        this.meta = null;
        for(int i = 0; i < nrow; i++)
            for(int j = 0; j < ncol; j++)
                matrix.put(i, j, initValue.doubleValue());
        if(matrix.rows == 1 || matrix.columns == 1)
            this.oneDimensional = true;
    }

    public Matrix(DoubleMatrix mat) {
        this(mat.rows, mat.columns);
        this.assign(mat);
    }


    /**************************************
     * MATRIX METHODS
     **************************************/

    public void assign(DoubleMatrix matrix) {
        this.matrix = matrix.dup();
        if(matrix.columns == 1 || matrix.rows == 1) {
            this.oneDimensional = true;
        }
    }

    /**************************************
     * ISEQ METHODS
     **************************************/
    @Override
    public Object first() {
        if(this.matrix.rows == 0 || this.matrix.columns == 0) return(null);

        if(this.oneDimensional && (matrix.columns == 1 || matrix.rows == 1))
            return(matrix.get(0, 0));
        else {
            int[] subset = new int[matrix.columns];
            for(int i=0; i<subset.length; i++)
                subset[i] = i;

            return new Matrix(matrix.get(0, subset));
        }
    }

    @Override
    public ISeq next() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ISeq more() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int count() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ISeq cons(Object o) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IPersistentCollection empty() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean equiv(Object o) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ISeq seq() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

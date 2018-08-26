/*
 * Copyright (c) 2009-2017, Peter Abeles. All Rights Reserved.
 *
 * This file is part of Efficient Java Matrix Library (EJML).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ejml2.sparse.triplet;

import org.ejml2.data.DMatrixSparseTriplet;

/**
 * @author Peter Abeles
 */
public class MatrixFeatures_DSTL {

    public static boolean isEquals(DMatrixSparseTriplet a , DMatrixSparseTriplet b ) {
        if( !isSameShape(a,b) )
            return false;

        for (int i = 0; i < a.nz_length; i++) {
            DMatrixSparseTriplet.Element ea = a.nz_data[i];
            DMatrixSparseTriplet.Element eb = b.nz_data[b.nz_index(ea.row, ea.col)];

            if( eb == null || ea.value != eb.value )
                return false;
        }
        return true;
    }

    public static boolean isEquals(DMatrixSparseTriplet a , DMatrixSparseTriplet b , double tol ) {
        if( !isSameShape(a,b) )
            return false;

        for (int i = 0; i < a.nz_length; i++) {
            DMatrixSparseTriplet.Element ea = a.nz_data[i];
            DMatrixSparseTriplet.Element eb = b.nz_data[b.nz_index(ea.row, ea.col)];

            if( eb == null || Math.abs(ea.value-eb.value) > tol )
                return false;
        }
        return true;
    }

    public static boolean isSameShape(DMatrixSparseTriplet a , DMatrixSparseTriplet b) {
        return a.numRows == b.numRows && a.numCols == b.numCols && a.nz_length == b.nz_length;
    }
}

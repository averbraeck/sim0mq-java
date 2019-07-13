package org.sim0mq.test.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.djunits.unit.AreaUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.MoneyPerAreaUnit;
import org.djunits.unit.MoneyPerLengthUnit;
import org.djunits.unit.MoneyUnit;
import org.djunits.value.StorageType;
import org.djunits.value.ValueException;
import org.djunits.value.vdouble.matrix.DurationMatrix;
import org.djunits.value.vdouble.scalar.AbstractDoubleScalar;
import org.djunits.value.vdouble.scalar.Length;
import org.djunits.value.vdouble.scalar.MoneyPerLength;
import org.djunits.value.vdouble.vector.AbstractDoubleVector;
import org.djunits.value.vdouble.vector.DurationVector;
import org.djunits.value.vdouble.vector.MoneyPerAreaVector;
import org.djunits.value.vfloat.matrix.FloatDurationMatrix;
import org.djunits.value.vfloat.scalar.AbstractFloatScalar;
import org.djunits.value.vfloat.scalar.FloatLength;
import org.djunits.value.vfloat.scalar.FloatMoneyPerArea;
import org.djunits.value.vfloat.vector.AbstractFloatVector;
import org.djunits.value.vfloat.vector.FloatDurationVector;
import org.djunits.value.vfloat.vector.FloatMoneyPerAreaVector;
import org.djutils.serialization.EndianUtil;
import org.djutils.serialization.SerializationException;
import org.djutils.serialization.TypedMessage;
import org.junit.Test;
import org.sim0mq.Sim0MQException;

/**
 * test the field types of the messages.
 * <p>
 * Copyright (c) 2013-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class TestFieldTypes
{
    /** the test objects. */
    private static List<TestType> testTypes = new ArrayList<>();

    /** the test cases. */
    protected static class TestType
    {
        /** the content, 1 field. */
        @SuppressWarnings("checkstyle:visibilitymodifier")
        final Object[] content;

        /** the value for comparison. */
        @SuppressWarnings("checkstyle:visibilitymodifier")
        final Object value;

        /** type field we expect. */
        @SuppressWarnings("checkstyle:visibilitymodifier")
        final int type;

        /** the byte pattern we expect, as ints to allow 0..255. */
        @SuppressWarnings("checkstyle:visibilitymodifier")
        final int[] bytes;

        /**
         * @param value the content, 1 field
         * @param type the expected type
         * @param bytes the byte pattern we expect, as ints to allow 0..255
         */
        public TestType(final Object value, final int type, final int[] bytes)
        {
            this.content = new Object[] {value};
            this.value = value;
            this.type = type;
            this.bytes = bytes;
        }
    }

    static
    {
        testTypes.add(new TestType((byte) 0, 0, new int[] {0}));
        testTypes.add(new TestType((byte) 1, 0, new int[] {1}));
        testTypes.add(new TestType((byte) -1, 0, new int[] {0xFF}));
        testTypes.add(new TestType((byte) 127, 0, new int[] {0x7F}));
        testTypes.add(new TestType((byte) -127, 0, new int[] {0x81}));
        testTypes.add(new TestType((byte) -128, 0, new int[] {0x80}));

        testTypes.add(new TestType((short) 0, 1, new int[] {0, 0}));
        testTypes.add(new TestType((short) 1, 1, new int[] {0, 1}));
        testTypes.add(new TestType((short) -1, 1, new int[] {0xFF, 0xFF}));
        testTypes.add(new TestType((short) 127, 1, new int[] {0, 0x7F}));
        testTypes.add(new TestType((short) -127, 1, new int[] {0xFF, 0x81}));
        testTypes.add(new TestType((short) 32767, 1, new int[] {0x7F, 0xFF}));
        testTypes.add(new TestType((short) -32767, 1, new int[] {0x80, 0x01}));
        testTypes.add(new TestType((short) -32768, 1, new int[] {0x80, 0x00}));

        testTypes.add(new TestType(0, 2, new int[] {0, 0, 0, 0}));
        testTypes.add(new TestType(1, 2, new int[] {0, 0, 0, 1}));
        testTypes.add(new TestType(-1, 2, new int[] {0xFF, 0xFF, 0xFF, 0xFF}));
        testTypes.add(new TestType(32767, 2, new int[] {0, 0, 0x7F, 0xFF}));
        testTypes.add(new TestType(-32767, 2, new int[] {0xFF, 0xFF, 0x80, 0x01}));
        testTypes.add(new TestType(-32768, 2, new int[] {0xFF, 0xFF, 0x80, 0x00}));
        testTypes.add(new TestType(2147483647, 2, new int[] {0x7F, 0xFF, 0xFF, 0xFF}));
        testTypes.add(new TestType(-2147483648, 2, new int[] {0x80, 0x00, 0x00, 0x00}));

        testTypes.add(new TestType(0L, 3, new int[] {0, 0, 0, 0, 0, 0, 0, 0}));
        testTypes.add(new TestType(1L, 3, new int[] {0, 0, 0, 0, 0, 0, 0, 1}));
        testTypes.add(new TestType(-1L, 3, new int[] {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF}));
        testTypes.add(new TestType(9223372036854775807L, 3, new int[] {0x7F, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF}));
        testTypes.add(new TestType(-9223372036854775808L, 3, new int[] {0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));

        testTypes.add(new TestType(0.0f, 4, new int[] {0, 0, 0, 0}));
        testTypes.add(new TestType(1.0f, 4, new int[] {0x3F, 0x80, 0x00, 0x00}));
        testTypes.add(new TestType(-1.0f, 4, new int[] {0xBF, 0x80, 0x00, 0x00}));
        testTypes.add(new TestType(1.23E10f, 4, new int[] {0x50, 0x37, 0x48, 0xC7}));
        testTypes.add(new TestType(-1.23E10f, 4, new int[] {0xD0, 0x37, 0x48, 0xC7}));
        testTypes.add(new TestType(1.23E-10f, 4, new int[] {0x2F, 0x07, 0x3D, 0x6C}));
        testTypes.add(new TestType(-1.23E-10f, 4, new int[] {0xAF, 0x07, 0x3D, 0x6C}));
        testTypes.add(new TestType(Float.NaN, 4, new int[] {0x7F, 0xC0, 0x00, 0x00}));
        testTypes.add(new TestType(Float.POSITIVE_INFINITY, 4, new int[] {0x7F, 0x80, 0x00, 0x00}));
        testTypes.add(new TestType(Float.NEGATIVE_INFINITY, 4, new int[] {0xFF, 0x80, 0x00, 0x00}));
        testTypes.add(new TestType(Float.MAX_VALUE, 4, new int[] {0x7F, 0x7F, 0xFF, 0xFF}));
        testTypes.add(new TestType(Float.MIN_VALUE, 4, new int[] {0x00, 0x00, 0x00, 0x01}));
        testTypes.add(new TestType(Float.MIN_NORMAL, 4, new int[] {0x00, 0x80, 0x00, 0x00}));

        testTypes.add(new TestType(0.0, 5, new int[] {0, 0, 0, 0, 0, 0, 0, 0}));
        testTypes.add(new TestType(1.0, 5, new int[] {0x3F, 0xF0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));
        testTypes.add(new TestType(-1.0, 5, new int[] {0xBF, 0xF0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));
        testTypes.add(new TestType(1.23E10, 5, new int[] {0x42, 0x06, 0xE9, 0x18, 0xD8, 0x00, 0x00, 0x00}));
        testTypes.add(new TestType(-1.23E10, 5, new int[] {0xC2, 0x06, 0xE9, 0x18, 0xD8, 0x00, 0x00, 0x00}));
        testTypes.add(new TestType(1.23E-10, 5, new int[] {0x3D, 0xE0, 0xE7, 0xAD, 0x82, 0x22, 0x1E, 0xEC}));
        testTypes.add(new TestType(-1.23E-10, 5, new int[] {0xBD, 0xE0, 0xE7, 0xAD, 0x82, 0x22, 0x1E, 0xEC}));
        testTypes.add(new TestType(Double.NaN, 5, new int[] {0x7F, 0xF8, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));
        testTypes.add(new TestType(Double.POSITIVE_INFINITY, 5, new int[] {0x7F, 0xF0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));
        testTypes.add(new TestType(Double.NEGATIVE_INFINITY, 5, new int[] {0xFF, 0xF0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));
        testTypes.add(new TestType(Double.MAX_VALUE, 5, new int[] {0x7F, 0xEF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF}));
        testTypes.add(new TestType(Double.MIN_VALUE, 5, new int[] {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01}));
        testTypes.add(new TestType(Double.MIN_NORMAL, 5, new int[] {0x00, 0x10, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));

        testTypes.add(new TestType(true, 6, new int[] {1}));
        testTypes.add(new TestType(false, 6, new int[] {0}));

        testTypes.add(new TestType((char) (byte) 65, 7, new int[] {65}));
        testTypes.add(new TestType((char) (byte) 124, 7, new int[] {124}));

        testTypes.add(new TestType('B', 8, new int[] {0, 66}));
        testTypes.add(new TestType('~', 8, new int[] {0, 126}));

        testTypes.add(new TestType("Hello", 9, new int[] {0, 0, 0, 5, 72, 101, 108, 108, 111}));

        testTypes.add(new TestType("UTF16", 10, new int[] {0, 0, 0, 5, 0, 85, 0, 84, 0, 70, 0, 49, 0, 54}));

        byte[] byteArray = new byte[8];
        for (int i = 0; i < 8; i++)
        {
            byteArray[i] = (byte) (10 + i);
        }
        testTypes.add(new TestType(byteArray, 11, new int[] {0, 0, 0, 8, 10, 11, 12, 13, 14, 15, 16, 17}));

        short[] shortArray = new short[8];
        for (int i = 0; i < 8; i++)
        {
            shortArray[i] = (short) (100 + i);
        }
        testTypes.add(new TestType(shortArray, 12,
                new int[] {0, 0, 0, 8, 0, 100, 0, 101, 0, 102, 0, 103, 0, 104, 0, 105, 0, 106, 0, 107}));

        int[] intArray = new int[8];
        for (int i = 0; i < 8; i++)
        {
            intArray[i] = 100 + i;
        }
        testTypes.add(new TestType(intArray, 13, new int[] {0, 0, 0, 8, 0, 0, 0, 100, 0, 0, 0, 101, 0, 0, 0, 102, 0, 0, 0, 103,
                0, 0, 0, 104, 0, 0, 0, 105, 0, 0, 0, 106, 0, 0, 0, 107}));

        long[] longArray = new long[8];
        for (int i = 0; i < 8; i++)
        {
            longArray[i] = 100 + i;
        }
        //@formatter:off
        testTypes.add(new TestType(longArray, 14,
                new int[] { 0, 0, 0, 8, 
                        0, 0, 0, 0, 0, 0, 0, 100, 
                        0, 0, 0, 0, 0, 0, 0, 101, 
                        0, 0, 0, 0, 0, 0, 0, 102, 
                        0, 0, 0, 0, 0, 0, 0, 103, 
                        0, 0, 0, 0, 0, 0, 0, 104, 
                        0, 0, 0, 0, 0, 0, 0, 105, 
                        0, 0, 0, 0, 0, 0, 0, 106, 
                        0, 0, 0, 0, 0, 0, 0, 107 }));
        //@formatter:on

        float[] floatArray = new float[4];
        for (int i = 0; i < 4; i++)
        {
            floatArray[i] = (float) ((i + 1) / 10.0);
        }
        testTypes.add(new TestType(floatArray, 15, new int[] {0, 0, 0, 4, 0x3D, 0xCC, 0xCC, 0xCD, 0x3E, 0x4C, 0xCC, 0xCD, 0x3E,
                0x99, 0x99, 0x9A, 0x3E, 0xCC, 0xCC, 0xCD}));

        double[] doubleArray = new double[4];
        for (int i = 0; i < 4; i++)
        {
            doubleArray[i] = (i + 1) / 10.0;
        }
        //@formatter:off
        testTypes.add(new TestType(doubleArray, 16, new int[] { 0, 0, 0, 4, 
                0x3F, 0xB9, 0x99, 0x99, 0x99, 0x99, 0x99, 0x9A,
                0x3F, 0xC9, 0x99, 0x99, 0x99, 0x99, 0x99, 0x9A,
                0x3F, 0xD3, 0x33, 0x33, 0x33, 0x33, 0x33, 0x33,
                0x3F, 0xD9, 0x99, 0x99, 0x99, 0x99, 0x99, 0x9A }));
        //@formatter:on

        boolean[] booleaneArray = new boolean[4];
        for (int i = 0; i < 4; i++)
        {
            booleaneArray[i] = Integer.lowestOneBit(i) == 1;
        }
        testTypes.add(new TestType(booleaneArray, 17, new int[] {0, 0, 0, 4, 0, 1, 0, 1}));

        byte[][] byteMatrix = new byte[2][3];
        for (int row = 0; row < 2; row++)
        {
            if (row == 0)
            {
                byteMatrix[row] = new byte[3];
            }
            for (int col = 0; col < 3; col++)
            {
                byteMatrix[row][col] = (byte) (10 * (row + 1) + col);
            }
        }
        testTypes.add(new TestType(byteMatrix, 18, new int[] {0, 0, 0, 2, 0, 0, 0, 3, 10, 11, 12, 20, 21, 22}));

        short[][] shortMatrix = new short[2][3];
        for (int row = 0; row < 2; row++)
        {
            if (row == 0)
            {
                shortMatrix[row] = new short[3];
            }
            for (int col = 0; col < 3; col++)
            {
                shortMatrix[row][col] = (short) (10 * (row + 1) + col);
            }
        }
        testTypes.add(
                new TestType(shortMatrix, 19, new int[] {0, 0, 0, 2, 0, 0, 0, 3, 0, 10, 0, 11, 0, 12, 0, 20, 0, 21, 0, 22}));

        int[][] intMatrix = new int[2][3];
        for (int row = 0; row < 2; row++)
        {
            if (row == 0)
            {
                intMatrix[row] = new int[3];
            }
            for (int col = 0; col < 3; col++)
            {
                intMatrix[row][col] = 10 * (row + 1) + col;
            }
        }
        testTypes.add(new TestType(intMatrix, 20, new int[] {0, 0, 0, 2, 0, 0, 0, 3, 0, 0, 0, 10, 0, 0, 0, 11, 0, 0, 0, 12, 0,
                0, 0, 20, 0, 0, 0, 21, 0, 0, 0, 22}));

        long[][] longMatrix = new long[2][3];
        for (int row = 0; row < 2; row++)
        {
            if (row == 0)
            {
                longMatrix[row] = new long[3];
            }
            for (int col = 0; col < 3; col++)
            {
                longMatrix[row][col] = 10 * (row + 1) + col;
            }
        }
        //@formatter:off
        testTypes.add(new TestType(longMatrix, 21, new int[] { 0, 0, 0, 2, 0, 0, 0, 3, 
                0, 0, 0, 0, 0, 0, 0, 10, 
                0, 0, 0, 0, 0, 0, 0, 11, 
                0, 0, 0, 0, 0, 0, 0, 12, 
                0, 0, 0, 0, 0, 0, 0, 20, 
                0, 0, 0, 0, 0, 0, 0, 21, 
                0, 0, 0, 0, 0, 0, 0, 22 }));
        //@formatter:on

        float[][] floatMatrix = new float[2][3];
        for (int row = 0; row < 2; row++)
        {
            if (row == 0)
            {
                floatMatrix[row] = new float[3];
            }
            for (int col = 0; col < 3; col++)
            {
                floatMatrix[row][col] = row + 1.0f + col / 10.0f; // 1.0 1.1 1.2 - 2.0 2.1 2.2
            }
        }
        //@formatter:off
        testTypes.add(new TestType(floatMatrix, 22, new int[] { 0, 0, 0, 2, 0, 0, 0, 3, 
                0x3F, 0x80, 0x00, 0x00,
                0x3F, 0x8C, 0xCC, 0xCD,
                0x3F, 0x99, 0x99, 0x9A,
                0x40, 0x00, 0x00, 0x00,
                0x40, 0x06, 0x66, 0x66,
                0x40, 0x0C, 0xCC, 0xCD
            }));
        //@formatter:on

        double[][] doubleMatrix = new double[2][3];
        for (int row = 0; row < 2; row++)
        {
            if (row == 0)
            {
                doubleMatrix[row] = new double[3];
            }
            for (int col = 0; col < 3; col++)
            {
                doubleMatrix[row][col] = row + 1.0 + col / 10.0; // 1.0 1.1 1.2 - 2.0 2.1 2.2
            }
        }
        //@formatter:off
        testTypes.add(new TestType(doubleMatrix, 23, new int[] { 0, 0, 0, 2, 0, 0, 0, 3, 
                0x3F, 0xF0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x3F, 0xF1, 0x99, 0x99, 0x99, 0x99, 0x99, 0x9A, 
                0x3F, 0xF3, 0x33, 0x33, 0x33, 0x33, 0x33, 0x33,
                0x40, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x40, 0x00, 0xCC, 0xCC, 0xCC, 0xCC, 0xCC, 0xCD,
                0x40, 0x01, 0x99, 0x99, 0x99, 0x99, 0x99, 0x9A
            }));
        //@formatter:on

        boolean[][] booleanMatrix = new boolean[2][3];
        for (int row = 0; row < 2; row++)
        {
            if (row == 0)
            {
                booleanMatrix[row] = new boolean[3];
            }
            for (int col = 0; col < 3; col++)
            {
                booleanMatrix[row][col] = row != 0 && col != 0; // 0 0 0 - 0 1 1
            }
        }
        testTypes.add(new TestType(booleanMatrix, 24, new int[] {0, 0, 0, 2, 0, 0, 0, 3, 0, 0, 0, 0, 1, 1}));

        testTypes
                .add(new TestType(new FloatLength(60.0, LengthUnit.KILOMETER), 25, new int[] {16, 11, 0x47, 0x6A, 0x60, 0x00}));
        testTypes.add(new TestType(
                new FloatMoneyPerArea(200.0, new MoneyPerAreaUnit(MoneyUnit.EUR, AreaUnit.HECTARE, "EUR/ha", "EUR/ha")), 25,
                new int[] {101, 0x03, 0xD2, 21, 0x3C, 0xA3, 0xD7, 0x0A}));

        testTypes.add(new TestType(new Length(60.0, LengthUnit.KILOMETER), 26,
                new int[] {16, 11, 0x40, 0xED, 0x4C, 0x00, 0x00, 0x00, 0x00, 0x00}));
        // note that the below calculation could have ended in 0x3A instead of 0x39, depending on the calculation order.
        // the actual value is 0.12427423844746679392348683687266, which is coded as 3FBFD06FBDDDFF3A
        testTypes.add(
                new TestType(new MoneyPerLength(200.0, new MoneyPerLengthUnit(MoneyUnit.USD, LengthUnit.MILE, "$/mi", "$/mi")),
                        26, new int[] {103, 0x03, 0x48, 16, 0x3F, 0xBF, 0xD0, 0x6F, 0xBD, 0xDD, 0xFF, 0x39}));

        try
        {
            testTypes
                    .add(new TestType(new FloatDurationVector(new float[] {2.0f, 2.5f}, DurationUnit.MINUTE, StorageType.DENSE),
                            27, new int[] {0, 0, 0, 2, 25, 7, 0x42, 0xF0, 0x00, 0x00, 0x43, 0x16, 0x00, 0x00}));
            testTypes.add(new TestType(
                    new FloatMoneyPerAreaVector(new float[] {200.0f, 200.0f},
                            new MoneyPerAreaUnit(MoneyUnit.EUR, AreaUnit.HECTARE, "EUR/ha", "EUR/ha"), StorageType.DENSE),
                    27, new int[] {0, 0, 0, 2, 101, 0x03, 0xD2, 21, 0x3C, 0xA3, 0xD7, 0x0A, 0x3C, 0xA3, 0xD7, 0x0A}));

            testTypes.add(new TestType(new DurationVector(new double[] {20.0, 25.0}, DurationUnit.MINUTE, StorageType.DENSE),
                    28, new int[] {0, 0, 0, 2, 25, 7, 0x40, 0x92, 0xC0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x40, 0x97, 0x70, 0x00,
                            0x00, 0x00, 0x00, 0x00}));
            // 20000 Euro/ha = 2 Euro/m2
            testTypes.add(new TestType(
                    new MoneyPerAreaVector(new double[] {20000.0, 20000.0},
                            new MoneyPerAreaUnit(MoneyUnit.EUR, AreaUnit.HECTARE, "EUR/ha", "EUR/ha"), StorageType.DENSE),
                    28, new int[] {0, 0, 0, 2, 101, 0x03, 0xD2, 21, 0x40, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x40, 0x00,
                            0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));

            testTypes.add(new TestType(
                    new FloatDurationMatrix(new float[][] {{2.0f, 2.5f}, {2.0f, 2.5f}}, DurationUnit.MINUTE, StorageType.DENSE),
                    29, new int[] {0, 0, 0, 2, 0, 0, 0, 2, 25, 7, 0x42, 0xF0, 0x00, 0x00, 0x43, 0x16, 0x00, 0x00, 0x42, 0xF0,
                            0x00, 0x00, 0x43, 0x16, 0x00, 0x00}));

            testTypes.add(new TestType(
                    new DurationMatrix(new double[][] {{20.0, 25.0}, {20.0, 25.0}}, DurationUnit.MINUTE, StorageType.DENSE), 30,
                    new int[] {0, 0, 0, 2, 0, 0, 0, 2, 25, 7, 0x40, 0x92, 0xC0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x40, 0x97, 0x70,
                            0x00, 0x00, 0x00, 0x00, 0x00, 0x40, 0x92, 0xC0, 0x00, 0x00, 0x00, 0x00, 0x00, 0x40, 0x97, 0x70,
                            0x00, 0x00, 0x00, 0x00, 0x00}));

            /*-
            FloatDurationVector fdv = new FloatDurationVector(new float[] {2.0f, 2.5f}, DurationUnit.MINUTE, StorageType.DENSE);
            FloatMoneyPerAreaVector fmv = new FloatMoneyPerAreaVector(new float[] {200.0f, 200.0f},
                    new MoneyPerAreaUnit(MoneyUnit.EUR, AreaUnit.HECTARE, "EUR/ha", "EUR/ha"), StorageType.DENSE);
            AbstractFloatVector<?, ?>[] afv = new AbstractFloatVector[] {fdv, fmv};
            testTypes.add(new TestType(afv, 31, new int[] {0, 0, 0, 2, 0, 0, 0, 2, 25, 7, 101, 0x03, 0xD2, 21, 0x42, 0xF0, 0x00,
                    0x00, 0x3C, 0xA3, 0xD7, 0x0A, 0x43, 0x16, 0x00, 0x00, 0x3C, 0xA3, 0xD7, 0x0A}));
            */

            DurationVector ddv = new DurationVector(new double[] {20.0, 25.0}, DurationUnit.MINUTE, StorageType.DENSE);
            MoneyPerAreaVector dmv = new MoneyPerAreaVector(new double[] {20000.0, 20000.0},
                    new MoneyPerAreaUnit(MoneyUnit.EUR, AreaUnit.HECTARE, "EUR/ha", "EUR/ha"), StorageType.DENSE);
            AbstractDoubleVector<?, ?>[] adv = new AbstractDoubleVector[] {ddv, dmv};
            testTypes.add(new TestType(adv, 32,
                    new int[] {0, 0, 0, 2, 0, 0, 0, 2, 25, 7, 101, 0x03, 0xD2, 21, 0x40, 0x92, 0xC0, 0x00, 0x00, 0x00, 0x00,
                            0x00, 0x40, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x40, 0x97, 0x70, 0x00, 0x00, 0x00, 0x00,
                            0x00, 0x40, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));

        }
        catch (ValueException ve)
        {
            ve.printStackTrace();
            fail(ve.getMessage());
        }
    }

    /**
     * Test method for encodeUTF8 and encodeUTF16. We test Big Endian here.
     * @throws Sim0MQException on encoding error
     * @throws SerializationException on serialization error
     */
    @SuppressWarnings("checkstyle:needbraces")
    @Test
    public void testEncodeBigEndian() throws Sim0MQException, SerializationException
    {
        for (int i = 0; i < testTypes.size(); i++)
        {
            TestType test = testTypes.get(i);
            byte[] message;
            if (test.type == 8 || test.type == 10 | test.type == 12)
                message = TypedMessage.encodeUTF16(EndianUtil.BIG_ENDIAN, test.content);
            else
                message = TypedMessage.encodeUTF8(EndianUtil.BIG_ENDIAN, test.content);
            if (message.length != test.bytes.length + 1)
            {
                fail("testEncodeBigEndian failed for " + test.content[0].getClass() + ", value " + test.content[0].toString()
                        + ", expected length = " + (test.bytes.length + 1) + ", actual length = " + message.length);
            }
            if (message[0] != test.type)
            {
                fail("testEncodeBigEndian failed for " + test.content[0].getClass() + ", value " + test.content[0].toString()
                        + ", expected type = " + test.type + ", actual type = " + message[0]);
            }
            for (int j = 0; j < test.bytes.length; j++)
            {
                int unsigned = message[j + 1];
                unsigned = unsigned < 0 ? 256 + message[j + 1] : message[j + 1];
                if (unsigned != test.bytes[j])
                {
                    fail("testEncodeBigEndian failed for " + test.content[0].getClass() + ", value "
                            + test.content[0].toString() + ", value byte[" + j + "] = " + test.bytes[j] + ", but encoding = "
                            + unsigned);
                }
            }
        }
    }

    /**
     * Test method for encodeUTF8 and encodeUTF16. We test whether decode gives the same results after encode - decode.
     * @throws Sim0MQException on encoding error
     * @throws SerializationException on serialization error
     */
    @SuppressWarnings("checkstyle:needbraces")
    @Test
    public void testEncodeDecodePrimitiveBigEndian() throws Sim0MQException, SerializationException
    {
        for (int i = 0; i < testTypes.size(); i++)
        {
            TestType test = testTypes.get(i);
            if (test.type <= 10)
            {
                byte[] message;
                if (test.type == 8 || test.type == 10 | test.type == 12)
                    message = TypedMessage.encodeUTF16(EndianUtil.BIG_ENDIAN, test.content);
                else
                    message = TypedMessage.encodeUTF8(EndianUtil.BIG_ENDIAN, test.content);

                Object[] decoded = TypedMessage.decodeToPrimitiveDataTypes(message, EndianUtil.BIG_ENDIAN);
                if (test.content.length != decoded.length)
                {
                    fail("testEncodeDecodePrimitiveBigEndian failed for " + test.content[0].getClass() + ", value "
                            + test.content[0].toString() + ", content length = " + (test.content.length) + ", decoded length = "
                            + decoded.length);
                }
                for (int j = 0; j < test.content.length; j++)
                {
                    if (!test.content[j].getClass().equals(decoded[j].getClass()))
                        fail("testEncodeDecodePrimitiveBigEndian failed for " + test.content[0].getClass()
                                + ", original class = " + test.content[j].getClass() + ", decoded class = "
                                + decoded[j].getClass());

                    if (!test.content[j].equals(decoded[j]))
                        fail("testEncodeDecodePrimitiveBigEndian failed for " + test.content[0].getClass()
                                + ", original value = " + test.content[j].toString() + ", decoded value = "
                                + decoded[j].toString());
                }
            }
        }
    }

    /**
     * Test method for encodeUTF8 and encodeUTF16. We test whether decode gives the same results after encode - decode.
     * @throws Sim0MQException on encoding error
     * @throws SerializationException on serialization error
     */
    @SuppressWarnings("checkstyle:needbraces")
    @Test
    public void testEncodeDecodeArrayBigEndian() throws Sim0MQException, SerializationException
    {
        for (int i = 0; i < testTypes.size(); i++)
        {
            TestType test = testTypes.get(i);
            if (test.type >= 11 && test.type <= 17)
            {
                byte[] message;
                if (test.type == 8 || test.type == 10 | test.type == 12)
                    message = TypedMessage.encodeUTF16(EndianUtil.BIG_ENDIAN, test.content);
                else
                    message = TypedMessage.encodeUTF8(EndianUtil.BIG_ENDIAN, test.content);

                Object[] decoded = TypedMessage.decodeToPrimitiveDataTypes(message, EndianUtil.BIG_ENDIAN);
                if (test.content.length != decoded.length)
                {
                    fail("testEncodeDecodeArrayBigEndian failed for " + test.content[0].getClass() + ", value "
                            + test.content[0].toString() + ", content length = " + (test.content.length) + ", decoded length = "
                            + decoded.length);
                }

                for (int j = 0; j < test.content.length; j++)
                {
                    if (!test.content[j].getClass().equals(decoded[j].getClass()))
                        fail("testEncodeDecodeArrayBigEndian failed for " + test.content[0].getClass() + ", original class = "
                                + test.content[j].getClass() + ", decoded class = " + decoded[j].getClass());

                    if (test.type == 11) // byte array types
                    {
                        for (int index = 0; index < ((byte[]) decoded[j]).length; index++)
                        {
                            if (((byte[]) test.content[j])[index] != (((byte[]) decoded[j])[index]))
                                fail("testEncodeDecodeArrayBigEndian failed for array " + test.content[0].getClass()
                                        + ", original value at index " + i + " = " + ((byte[]) test.content[j])[index]
                                        + ", decoded value = " + ((byte[]) decoded[j])[index]);
                        }
                    }
                    else if (test.type == 12) // short array types
                    {
                        for (int index = 0; index < ((short[]) decoded[j]).length; index++)
                        {
                            if (((short[]) test.content[j])[index] != (((short[]) decoded[j])[index]))
                                fail("testEncodeDecodeArrayBigEndian failed for array " + test.content[0].getClass()
                                        + ", original value at index " + i + " = " + ((short[]) test.content[j])[index]
                                        + ", decoded value = " + ((short[]) decoded[j])[index]);
                        }
                    }
                    else if (test.type == 13) // int array types
                    {
                        for (int index = 0; index < ((int[]) decoded[j]).length; index++)
                        {
                            if (((int[]) test.content[j])[index] != (((int[]) decoded[j])[index]))
                                fail("testEncodeDecodeArrayBigEndian failed for array " + test.content[0].getClass()
                                        + ", original value at index " + i + " = " + ((int[]) test.content[j])[index]
                                        + ", decoded value = " + ((int[]) decoded[j])[index]);
                        }
                    }
                    else if (test.type == 14) // long array types
                    {
                        for (int index = 0; index < ((long[]) decoded[j]).length; index++)
                        {
                            if (((long[]) test.content[j])[index] != (((long[]) decoded[j])[index]))
                                fail("testEncodeDecodeArrayBigEndian failed for array " + test.content[0].getClass()
                                        + ", original value at index " + i + " = " + ((long[]) test.content[j])[index]
                                        + ", decoded value = " + ((long[]) decoded[j])[index]);
                        }
                    }
                    else if (test.type == 15) // float array types
                    {
                        for (int index = 0; index < ((float[]) decoded[j]).length; index++)
                        {
                            if (((float[]) test.content[j])[index] != (((float[]) decoded[j])[index]))
                                fail("testEncodeDecodeArrayBigEndian failed for array " + test.content[0].getClass()
                                        + ", original value at index " + i + " = " + ((float[]) test.content[j])[index]
                                        + ", decoded value = " + ((float[]) decoded[j])[index]);
                        }
                    }
                    else if (test.type == 16) // double array types
                    {
                        for (int index = 0; index < ((double[]) decoded[j]).length; index++)
                        {
                            if (((double[]) test.content[j])[index] != (((double[]) decoded[j])[index]))
                                fail("testEncodeDecodeArrayBigEndian failed for array " + test.content[0].getClass()
                                        + ", original value at index " + i + " = " + ((double[]) test.content[j])[index]
                                        + ", decoded value = " + ((double[]) decoded[j])[index]);
                        }
                    }
                    else if (test.type == 17) // boolean array types
                    {
                        for (int index = 0; index < ((boolean[]) decoded[j]).length; index++)
                        {
                            if (((boolean[]) test.content[j])[index] != (((boolean[]) decoded[j])[index]))
                                fail("testEncodeDecodeArrayBigEndian failed for array " + test.content[0].getClass()
                                        + ", original value at index " + i + " = " + ((boolean[]) test.content[j])[index]
                                        + ", decoded value = " + ((boolean[]) decoded[j])[index]);
                        }
                    }
                }
            }
        }
    }

    /**
     * Test method for encodeUTF8 and encodeUTF16. We test whether decode gives the same results after encode - decode.
     * @throws Sim0MQException on encoding error
     * @throws SerializationException on serialization error
     */
    @SuppressWarnings("checkstyle:needbraces")
    @Test
    public void testEncodeDecodeMatrixBigEndian() throws Sim0MQException, SerializationException
    {
        for (int i = 0; i < testTypes.size(); i++)
        {
            TestType test = testTypes.get(i);
            if (test.type >= 18 && test.type <= 24)
            {
                byte[] message;
                if (test.type == 8 || test.type == 10 | test.type == 12)
                    message = TypedMessage.encodeUTF16(EndianUtil.BIG_ENDIAN, test.content);
                else
                    message = TypedMessage.encodeUTF8(EndianUtil.BIG_ENDIAN, test.content);

                Object[] decoded = TypedMessage.decodeToPrimitiveDataTypes(message, EndianUtil.BIG_ENDIAN);
                if (test.content.length != decoded.length)
                {
                    fail("testEncodeDecodeMatrixBigEndian failed for " + test.content[0].getClass() + ", value "
                            + test.content[0].toString() + ", content length = " + (test.content.length) + ", decoded length = "
                            + decoded.length);
                }

                for (int j = 0; j < test.content.length; j++)
                {
                    if (!test.content[j].getClass().equals(decoded[j].getClass()))
                        fail("testEncodeDecodeMatrixBigEndian failed for " + test.content[0].getClass() + ", original class = "
                                + test.content[j].getClass() + ", decoded class = " + decoded[j].getClass());

                    if (test.type == 18) // byte matrix types
                    {
                        for (int row = 0; row < ((byte[][]) decoded[j]).length; row++)
                        {
                            for (int col = 0; col < ((byte[][]) decoded[j])[row].length; col++)
                            {
                                if (((byte[][]) test.content[j])[row][col] != (((byte[][]) decoded[j])[row][col]))
                                    fail("testEncodeDecodeMatrixBigEndian failed for matrix " + test.content[0].getClass()
                                            + ", original value at (r,c) = (" + row + ", " + col + ") = "
                                            + ((byte[][]) test.content[j])[row][col] + ", decoded value = "
                                            + ((byte[][]) decoded[j])[row][col]);
                            }
                        }
                    }

                    else if (test.type == 19) // short matrix types
                    {
                        for (int row = 0; row < ((short[][]) decoded[j]).length; row++)
                        {
                            for (int col = 0; col < ((short[][]) decoded[j])[row].length; col++)
                            {
                                if (((short[][]) test.content[j])[row][col] != (((short[][]) decoded[j])[row][col]))
                                    fail("testEncodeDecodeMatrixBigEndian failed for matrix " + test.content[0].getClass()
                                            + ", original value at (r,c) = (" + row + ", " + col + ") = "
                                            + ((short[][]) test.content[j])[row][col] + ", decoded value = "
                                            + ((short[][]) decoded[j])[row][col]);
                            }
                        }
                    }

                    else if (test.type == 20) // int matrix types
                    {
                        for (int row = 0; row < ((int[][]) decoded[j]).length; row++)
                        {
                            for (int col = 0; col < ((int[][]) decoded[j])[row].length; col++)
                            {
                                if (((int[][]) test.content[j])[row][col] != (((int[][]) decoded[j])[row][col]))
                                    fail("testEncodeDecodeMatrixBigEndian failed for matrix " + test.content[0].getClass()
                                            + ", original value at (r,c) = (" + row + ", " + col + ") = "
                                            + ((int[][]) test.content[j])[row][col] + ", decoded value = "
                                            + ((int[][]) decoded[j])[row][col]);
                            }
                        }
                    }

                    else if (test.type == 21) // long matrix types
                    {
                        for (int row = 0; row < ((long[][]) decoded[j]).length; row++)
                        {
                            for (int col = 0; col < ((long[][]) decoded[j])[row].length; col++)
                            {
                                if (((long[][]) test.content[j])[row][col] != (((long[][]) decoded[j])[row][col]))
                                    fail("testEncodeDecodeMatrixBigEndian failed for matrix " + test.content[0].getClass()
                                            + ", original value at (r,c) = (" + row + ", " + col + ") = "
                                            + ((long[][]) test.content[j])[row][col] + ", decoded value = "
                                            + ((long[][]) decoded[j])[row][col]);
                            }
                        }
                    }

                    else if (test.type == 22) // float matrix types
                    {
                        for (int row = 0; row < ((float[][]) decoded[j]).length; row++)
                        {
                            for (int col = 0; col < ((float[][]) decoded[j])[row].length; col++)
                            {
                                if (((float[][]) test.content[j])[row][col] != (((float[][]) decoded[j])[row][col]))
                                    fail("testEncodeDecodeMatrixBigEndian failed for matrix " + test.content[0].getClass()
                                            + ", original value at (r,c) = (" + row + ", " + col + ") = "
                                            + ((float[][]) test.content[j])[row][col] + ", decoded value = "
                                            + ((float[][]) decoded[j])[row][col]);
                            }
                        }
                    }

                    else if (test.type == 23) // double matrix types
                    {
                        for (int row = 0; row < ((double[][]) decoded[j]).length; row++)
                        {
                            for (int col = 0; col < ((double[][]) decoded[j])[row].length; col++)
                            {
                                if (((double[][]) test.content[j])[row][col] != (((double[][]) decoded[j])[row][col]))
                                    fail("testEncodeDecodeMatrixBigEndian failed for matrix " + test.content[0].getClass()
                                            + ", original value at (r,c) = (" + row + ", " + col + ") = "
                                            + ((double[][]) test.content[j])[row][col] + ", decoded value = "
                                            + ((double[][]) decoded[j])[row][col]);
                            }
                        }
                    }

                    else if (test.type == 24) // boolean matrix types
                    {
                        for (int row = 0; row < ((boolean[][]) decoded[j]).length; row++)
                        {
                            for (int col = 0; col < ((boolean[][]) decoded[j])[row].length; col++)
                            {
                                if (((boolean[][]) test.content[j])[row][col] != (((boolean[][]) decoded[j])[row][col]))
                                    fail("testEncodeDecodeMatrixBigEndian failed for matrix " + test.content[0].getClass()
                                            + ", original value at (r,c) = (" + row + ", " + col + ") = "
                                            + ((boolean[][]) test.content[j])[row][col] + ", decoded value = "
                                            + ((boolean[][]) decoded[j])[row][col]);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Test method for encodeUTF8 and encodeUTF16. We test whether decode gives the same results after encode - decode.
     * @throws Sim0MQException on encoding error
     * @throws ValueException on encoding error
     * @throws SerializationException on serialization error
     */
    @SuppressWarnings("checkstyle:needbraces")
    @Test
    public void testEncodeDecodeUnitsBigEndian() throws Sim0MQException, ValueException, SerializationException
    {
        for (int i = 0; i < testTypes.size(); i++)
        {
            TestType test = testTypes.get(i);
            if (test.type >= 25 && test.type <= 32)
            {
                byte[] message;
                if (test.type == 8 || test.type == 10 | test.type == 12)
                    message = TypedMessage.encodeUTF16(EndianUtil.BIG_ENDIAN, test.content);
                else
                    message = TypedMessage.encodeUTF8(EndianUtil.BIG_ENDIAN, test.content);

                Object[] decoded = TypedMessage.decodeToPrimitiveDataTypes(message, EndianUtil.BIG_ENDIAN);
                if (test.content.length != decoded.length)
                {
                    fail("testEncodeDecodeUnitBigEndian failed for " + test.content[0].getClass() + ", value "
                            + test.content[0].toString() + ", content length = " + (test.content.length) + ", decoded length = "
                            + decoded.length);
                }

                for (int j = 0; j < test.content.length; j++)
                {
                    if (!test.content[j].getClass().equals(decoded[j].getClass()))
                        fail("testEncodeDecodeUnitBigEndian failed for " + test.content[0].getClass() + ", original class = "
                                + test.content[j].getClass() + ", decoded class = " + decoded[j].getClass());

                    if (test.type == 25) // FloatScalar types
                    {
                        if (!test.content[j].equals(decoded[j]))
                            fail("testEncodeDecodeUnitBigEndian failed for " + test.content[0].getClass()
                                    + ", original value = " + test.content[j].toString() + ", decoded value = "
                                    + decoded[j].toString());
                        if (((AbstractFloatScalar<?, ?>) test.content[j]).si != ((AbstractFloatScalar<?, ?>) decoded[j]).si)
                            fail("testEncodeDecodeUnitBigEndian failed for " + test.content[0].getClass()
                                    + ", original si value = " + ((AbstractFloatScalar<?, ?>) test.content[j]).si
                                    + ", decoded si value = " + ((AbstractFloatScalar<?, ?>) decoded[j]).si);
                        if (!((AbstractFloatScalar<?, ?>) test.content[j]).getUnit().getStandardUnit()
                                .equals(((AbstractFloatScalar<?, ?>) decoded[j]).getUnit().getStandardUnit()))
                            fail("testEncodeDecodeUnitBigEndian failed for " + test.content[0].getClass()
                                    + ", original si-unit = "
                                    + ((AbstractFloatScalar<?, ?>) test.content[j]).getUnit().getStandardUnit()
                                    + ", decoded si-unit = "
                                    + ((AbstractFloatScalar<?, ?>) decoded[j]).getUnit().getStandardUnit());
                        if (!((AbstractFloatScalar<?, ?>) test.content[j]).getUnit()
                                .equalsIgnoreNaming(((AbstractFloatScalar<?, ?>) decoded[j]).getUnit()))
                            fail("testEncodeDecodeUnitBigEndian failed for " + test.content[0].getClass() + ", original unit = "
                                    + ((AbstractFloatScalar<?, ?>) test.content[j]).getUnit() + ", decoded unit = "
                                    + ((AbstractFloatScalar<?, ?>) decoded[j]).getUnit());
                    }

                    else if (test.type == 26) // DoubleScalar types
                    {
                        if (!test.content[j].equals(decoded[j]))
                            fail("testEncodeDecodeUnitBigEndian failed for " + test.content[0].getClass()
                                    + ", original value = " + test.content[j].toString() + ", decoded value = "
                                    + decoded[j].toString());
                        if (((AbstractDoubleScalar<?, ?>) test.content[j]).si != ((AbstractDoubleScalar<?, ?>) decoded[j]).si)
                            fail("testEncodeDecodeUnitBigEndian failed for " + test.content[0].getClass()
                                    + ", original si value = " + ((AbstractDoubleScalar<?, ?>) test.content[j]).si
                                    + ", decoded si value = " + ((AbstractDoubleScalar<?, ?>) decoded[j]).si);
                        if (!((AbstractDoubleScalar<?, ?>) test.content[j]).getUnit().getStandardUnit()
                                .equals(((AbstractDoubleScalar<?, ?>) decoded[j]).getUnit().getStandardUnit()))
                            fail("testEncodeDecodeUnitBigEndian failed for " + test.content[0].getClass()
                                    + ", original si-unit = "
                                    + ((AbstractDoubleScalar<?, ?>) test.content[j]).getUnit().getStandardUnit()
                                    + ", decoded si-unit = "
                                    + ((AbstractDoubleScalar<?, ?>) decoded[j]).getUnit().getStandardUnit());
                        if (!((AbstractDoubleScalar<?, ?>) test.content[j]).getUnit()
                                .equalsIgnoreNaming(((AbstractDoubleScalar<?, ?>) decoded[j]).getUnit()))
                            fail("testEncodeDecodeUnitBigEndian failed for " + test.content[0].getClass() + ", original unit = "
                                    + ((AbstractDoubleScalar<?, ?>) test.content[j]).getUnit() + ", decoded unit = "
                                    + ((AbstractDoubleScalar<?, ?>) decoded[j]).getUnit());
                    }

                    else if (test.type == 27) // FloatVector types
                    {
                        AbstractFloatVector<?, ?> fv1 = (AbstractFloatVector<?, ?>) test.content[j];
                        AbstractFloatVector<?, ?> fv2 = (AbstractFloatVector<?, ?>) decoded[j];
                        assertEquals("testEncodeDecodeUnitBigEndian size failed for " + test.content[0].getClass()
                                + ", original value = " + test.content[j].toString() + ", decoded value = "
                                + decoded[j].toString(), fv1.size(), fv2.size());
                        assertTrue("testEncodeDecodeUnitBigEndian unit failed for " + test.content[0].getClass()
                                + ", original value = " + test.content[j].toString() + ", decoded value = "
                                + decoded[j].toString(), fv1.getUnit().equalsIgnoreNaming(fv2.getUnit()));
                        assertEquals("testEncodeDecodeUnitBigEndian class failed for " + test.content[0].getClass()
                                + ", original value = " + test.content[j].toString() + ", decoded value = "
                                + decoded[j].toString(), fv1.getClass(), fv2.getClass());
                        for (int index = 0; index < fv1.size(); index++)
                        {
                            assertEquals(
                                    "testEncodeDecodeUnitBigEndian content[" + index + "] failed for "
                                            + test.content[0].getClass() + ", original value = " + test.content[j].toString()
                                            + ", decoded value = " + decoded[j].toString(),
                                    fv1.getSI(index), fv2.getSI(index), 0.001);
                        }
                    }

                    else if (test.type == 28) // DoubleVector types
                    {

                        AbstractDoubleVector<?, ?> dv1 = (AbstractDoubleVector<?, ?>) test.content[j];
                        AbstractDoubleVector<?, ?> dv2 = (AbstractDoubleVector<?, ?>) decoded[j];
                        assertEquals("testEncodeDecodeUnitBigEndian size failed for " + test.content[0].getClass()
                                + ", original value = " + test.content[j].toString() + ", decoded value = "
                                + decoded[j].toString(), dv1.size(), dv2.size());
                        assertTrue("testEncodeDecodeUnitBigEndian unit failed for " + test.content[0].getClass()
                                + ", original value = " + test.content[j].toString() + ", decoded value = "
                                + decoded[j].toString(), dv1.getUnit().equalsIgnoreNaming(dv2.getUnit()));
                        assertEquals("testEncodeDecodeUnitBigEndian class failed for " + test.content[0].getClass()
                                + ", original value = " + test.content[j].toString() + ", decoded value = "
                                + decoded[j].toString(), dv1.getClass(), dv2.getClass());
                        for (int index = 0; index < dv1.size(); index++)
                        {
                            assertEquals(
                                    "testEncodeDecodeUnitBigEndian content[" + index + "] failed for "
                                            + test.content[0].getClass() + ", original value = " + test.content[j].toString()
                                            + ", decoded value = " + decoded[j].toString(),
                                    dv1.getSI(index), dv2.getSI(index), 0.001);
                        }
                    }

                    else if (test.type == 29) // FloatMatrix types
                    {
                        if (!test.content[j].equals(decoded[j]))
                            fail("testEncodeDecodeUnitBigEndian failed for " + test.content[0].getClass()
                                    + ", original value = " + test.content[j].toString() + ", decoded value = "
                                    + decoded[j].toString());
                    }

                    else if (test.type == 30) // DoubleMattrix types
                    {
                        if (!test.content[j].equals(decoded[j]))
                            fail("testEncodeDecodeUnitBigEndian failed for " + test.content[0].getClass()
                                    + ", original value = " + test.content[j].toString() + ", decoded value = "
                                    + decoded[j].toString());
                    }

                }
            }
        }
    }

}

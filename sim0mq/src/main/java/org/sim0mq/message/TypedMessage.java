package org.sim0mq.message;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.djunits.unit.MoneyPerAreaUnit;
import org.djunits.unit.MoneyPerDurationUnit;
import org.djunits.unit.MoneyPerEnergyUnit;
import org.djunits.unit.MoneyPerLengthUnit;
import org.djunits.unit.MoneyPerMassUnit;
import org.djunits.unit.MoneyPerVolumeUnit;
import org.djunits.unit.MoneyUnit;
import org.djunits.unit.Unit;
import org.djunits.value.StorageType;
import org.djunits.value.ValueException;
import org.djunits.value.vdouble.matrix.AbstractDoubleMatrix;
import org.djunits.value.vdouble.matrix.DoubleMatrixUtil;
import org.djunits.value.vdouble.scalar.AbstractDoubleScalar;
import org.djunits.value.vdouble.scalar.DoubleScalarUtil;
import org.djunits.value.vdouble.scalar.Money;
import org.djunits.value.vdouble.scalar.MoneyPerArea;
import org.djunits.value.vdouble.scalar.MoneyPerDuration;
import org.djunits.value.vdouble.scalar.MoneyPerEnergy;
import org.djunits.value.vdouble.scalar.MoneyPerLength;
import org.djunits.value.vdouble.scalar.MoneyPerMass;
import org.djunits.value.vdouble.scalar.MoneyPerVolume;
import org.djunits.value.vdouble.vector.AbstractDoubleVector;
import org.djunits.value.vdouble.vector.DoubleVectorUtil;
import org.djunits.value.vfloat.matrix.AbstractFloatMatrix;
import org.djunits.value.vfloat.matrix.FloatMatrixUtil;
import org.djunits.value.vfloat.scalar.AbstractFloatScalar;
import org.djunits.value.vfloat.scalar.FloatMoney;
import org.djunits.value.vfloat.scalar.FloatMoneyPerArea;
import org.djunits.value.vfloat.scalar.FloatMoneyPerDuration;
import org.djunits.value.vfloat.scalar.FloatMoneyPerEnergy;
import org.djunits.value.vfloat.scalar.FloatMoneyPerLength;
import org.djunits.value.vfloat.scalar.FloatMoneyPerMass;
import org.djunits.value.vfloat.scalar.FloatMoneyPerVolume;
import org.djunits.value.vfloat.scalar.FloatScalarUtil;
import org.djunits.value.vfloat.vector.AbstractFloatVector;
import org.djunits.value.vfloat.vector.FloatVectorUtil;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.types.Sim0MQDisplayType;
import org.sim0mq.message.types.Sim0MQTypes;
import org.sim0mq.message.types.Sim0MQUnitType;
import org.sim0mq.message.util.EndianUtil;

import nl.tudelft.simulation.language.Throw;

/**
 * Message conversions. These take into account the endianness for coding the different values. Java is by default big-endian.
 * <p>
 * Copyright (c) 2016-2017 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">OpenTrafficSim License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version Mar 1, 2017 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public final class TypedMessage
{
    /** version of the protocol, magic number. */
    protected static final String VERSION = "SIM01";

    /** hashcode of Byte class. */
    protected static final int BYTE_HC = Byte.class.hashCode();

    /** hashcode of Short class. */
    protected static final int SHORT_HC = Short.class.hashCode();

    /** hashcode of Integer class. */
    protected static final int INTEGER_HC = Integer.class.hashCode();

    /** hashcode of Long class. */
    protected static final int LONG_HC = Long.class.hashCode();

    /** hashcode of Float class. */
    protected static final int FLOAT_HC = Float.class.hashCode();

    /** hashcode of Double class. */
    protected static final int DOUBLE_HC = Double.class.hashCode();

    /** hashcode of Boolean class. */
    protected static final int BOOLEAN_HC = Boolean.class.hashCode();

    /** hashcode of Character class. */
    protected static final int CHAR_HC = Character.class.hashCode();

    /** hashcode of String class. */
    protected static final int STRING_HC = String.class.hashCode();

    /** hashcode of byte[] class. */
    protected static final int BYTE_ARRAY_HC = byte[].class.hashCode();

    /** hashcode of short[] class. */
    protected static final int SHORT_ARRAY_HC = short[].class.hashCode();

    /** hashcode of int[] class. */
    protected static final int INT_ARRAY_HC = int[].class.hashCode();

    /** hashcode of long[] class. */
    protected static final int LONG_ARRAY_HC = long[].class.hashCode();

    /** hashcode of float[] class. */
    protected static final int FLOAT_ARRAY_HC = float[].class.hashCode();

    /** hashcode of double[] class. */
    protected static final int DOUBLE_ARRAY_HC = double[].class.hashCode();

    /** hashcode of boolean[] class. */
    protected static final int BOOLEAN_ARRAY_HC = boolean[].class.hashCode();

    /** hashcode of byte[][] class. */
    protected static final int BYTE_MATRIX_HC = byte[][].class.hashCode();

    /** hashcode of short[][] class. */
    protected static final int SHORT_MATRIX_HC = short[][].class.hashCode();

    /** hashcode of int[][] class. */
    protected static final int INT_MATRIX_HC = int[][].class.hashCode();

    /** hashcode of long[][] class. */
    protected static final int LONG_MATRIX_HC = long[][].class.hashCode();

    /** hashcode of float[][] class. */
    protected static final int FLOAT_MATRIX_HC = float[][].class.hashCode();

    /** hashcode of double[][] class. */
    protected static final int DOUBLE_MATRIX_HC = double[][].class.hashCode();

    /** hashcode of boolean[][] class. */
    protected static final int BOOLEAN_MATRIX_HC = boolean[][].class.hashCode();

    /** the UTF-8 charset. */
    protected static final Charset UTF8 = Charset.forName("UTF-8");

    /** the UTF-16 charset, big endian variant. */
    protected static final Charset UTF16 = Charset.forName("UTF-16BE");

    /**
     * Do not instantiate this utility class.
     */
    private TypedMessage()
    {
        // Utility class; do not instantiate.
    }

    /**
     * Encode the object array into a byte[] message. Use UTF8 for the characters and for the String.
     * @param content the objects to encode
     * @return the zeroMQ message to send as a byte array
     * @throws Sim0MQException on unknown data type
     */
    public static byte[] encodeUTF8(final Object... content) throws Sim0MQException
    {
        return encode(true, false, content);
    }

    /**
     * Encode the object array into a byte[] message. Use UTF16 for the characters and for the String.
     * @param content the objects to encode
     * @return the zeroMQ message to send as a byte array
     * @throws Sim0MQException on unknown data type
     */
    public static byte[] encodeUTF16(final Object... content) throws Sim0MQException
    {
        return encode(false, false, content);
    }

    /**
     * Encode the object array into a byte[] message. Use UTF8 for the characters and for the String. Make sure the first field
     * is a String and always encoded as UTF8 (for the "SIM##" magic number at the start).
     * @param content the objects to encode
     * @return the zeroMQ message to send as a byte array
     * @throws Sim0MQException on unknown data type
     */
    public static byte[] encode0MQMessageUTF8(final Object... content) throws Sim0MQException
    {
        Throw.when(content.length == 0, Sim0MQException.class, "empty array to encode");
        Throw.when(!(content[1] instanceof String), Sim0MQException.class, "first field in array is not a String");
        return encode(true, false, content);
    }

    /**
     * Encode the object array into a byte[] message. Use UTF16 for the characters and for the String. Make sure the first field
     * is a String and always encoded as UTF8 (for the "SIM##" magic number at the start).
     * @param content the objects to encode
     * @return the zeroMQ message to send as a byte array
     * @throws Sim0MQException on unknown data type
     */
    public static byte[] encode0MQMessageUTF16(final Object... content) throws Sim0MQException
    {
        Throw.when(content.length == 0, Sim0MQException.class, "empty array to encode");
        Throw.when(!(content[1] instanceof String), Sim0MQException.class, "first field in array is not a String");
        return encode(false, false, content);
    }

    /**
     * Encode the object array into a Big Endian message.
     * @param utf8 whether to encode String fields and characters in utf8 or not
     * @param firstUtf8 whether to encode the first String field in utf8 or not
     * @param content the objects to encode
     * @return the zeroMQ message to send as a byte array
     * @throws Sim0MQException on unknown data type
     */
    @SuppressWarnings({ "checkstyle:methodlength", "checkstyle:needbraces" })
    private static byte[] encode(final boolean utf8, final boolean firstUtf8, final Object... content) throws Sim0MQException
    {
        int size = 0;
        for (int i = 0; i < content.length; i++)
        {
            size++; // for the field type
            int hc = content[i].getClass().hashCode();
            if (hc == BYTE_HC)
                size += 1;
            else if (hc == SHORT_HC)
                size += 2;
            else if (hc == INTEGER_HC)
                size += 4;
            else if (hc == LONG_HC)
                size += 8;
            else if (hc == FLOAT_HC)
                size += 4;
            else if (hc == DOUBLE_HC)
                size += 8;
            else if (hc == BOOLEAN_HC)
                size += 1;
            else if (hc == CHAR_HC && utf8)
                size += 1;
            else if (hc == CHAR_HC && !utf8)
                size += 2;
            else if (hc == STRING_HC)
            {
                if (utf8 || (i == 0 && firstUtf8))
                    size += ((String) content[i]).length() + 4;
                else
                    size += 2 * ((String) content[i]).length() + 4;
            }
            else if (hc == BYTE_ARRAY_HC)
                size += ((byte[]) content[i]).length + 4;
            else if (hc == SHORT_ARRAY_HC)
                size += 2 * ((short[]) content[i]).length + 4;
            else if (hc == INT_ARRAY_HC)
                size += 4 * ((int[]) content[i]).length + 4;
            else if (hc == LONG_ARRAY_HC)
                size += 8 * ((long[]) content[i]).length + 4;
            else if (hc == FLOAT_ARRAY_HC)
                size += 4 * ((float[]) content[i]).length + 4;
            else if (hc == DOUBLE_ARRAY_HC)
                size += 8 * ((double[]) content[i]).length + 4;
            else if (hc == BOOLEAN_ARRAY_HC)
                size += ((boolean[]) content[i]).length + 4;
            else if (hc == BYTE_MATRIX_HC)
                size += ((byte[][]) content[i]).length * ((byte[][]) content[i])[0].length + 8;
            else if (hc == SHORT_MATRIX_HC)
                size += 2 * ((short[][]) content[i]).length * ((short[][]) content[i])[0].length + 8;
            else if (hc == INT_MATRIX_HC)
                size += 4 * ((int[][]) content[i]).length * ((int[][]) content[i])[0].length + 8;
            else if (hc == LONG_MATRIX_HC)
                size += 8 * ((long[][]) content[i]).length * ((long[][]) content[i])[0].length + 8;
            else if (hc == FLOAT_MATRIX_HC)
                size += 4 * ((float[][]) content[i]).length * ((float[][]) content[i])[0].length + 8;
            else if (hc == DOUBLE_MATRIX_HC)
                size += 8 * ((double[][]) content[i]).length * ((double[][]) content[i])[0].length + 8;
            else if (hc == BOOLEAN_MATRIX_HC)
                size += ((boolean[][]) content[i]).length * ((boolean[][]) content[i])[0].length + 8;
            else if (content[i] instanceof AbstractFloatScalar)
                size += 6 + extraBytesMoney(content[i]);
            else if (content[i] instanceof AbstractDoubleScalar)
                size += 10 + extraBytesMoney(content[i]);
            else if (content[i] instanceof AbstractFloatVector)
            {
                AbstractFloatVector<?, ?> afv = (AbstractFloatVector<?, ?>) content[i];
                try
                {
                    size += 4 + 2 + extraBytesMoney(afv.get(0)) + 4 * afv.size();
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }
            else if (content[i] instanceof AbstractDoubleVector)
            {
                AbstractDoubleVector<?, ?> adv = (AbstractDoubleVector<?, ?>) content[i];
                try
                {
                    size += 4 + 2 + extraBytesMoney(adv.get(0)) + 8 * adv.size();
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }
            else if (content[i] instanceof AbstractFloatMatrix)
            {
                AbstractFloatMatrix<?, ?> afm = (AbstractFloatMatrix<?, ?>) content[i];
                try
                {
                    size += 4 + 4 + 2 + extraBytesMoney(afm.get(0, 0)) + 4 * afm.rows() * afm.columns();
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }
            else if (content[i] instanceof AbstractDoubleMatrix)
            {
                AbstractDoubleMatrix<?, ?> adm = (AbstractDoubleMatrix<?, ?>) content[i];
                try
                {
                    size += 4 + 4 + 2 + extraBytesMoney(adm.get(0, 0)) + 8 * adm.rows() * adm.columns();
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }
            else if (content[i] instanceof AbstractFloatVector[])
            {
                AbstractFloatVector<?, ?>[] afvArray = (AbstractFloatVector<?, ?>[]) content[i];
                try
                {
                    size += 4 + 4; // rows, cols
                    for (int j = 0; j < afvArray.length; j++)
                    {
                        size += 2 + extraBytesMoney(afvArray[j].get(0)) + 4 * afvArray[j].size();
                    }
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }
            else if (content[i] instanceof AbstractDoubleVector[])
            {
                AbstractDoubleVector<?, ?>[] afvArray = (AbstractDoubleVector<?, ?>[]) content[i];
                try
                {
                    size += 4 + 4; // rows, cols
                    for (int j = 0; j < afvArray.length; j++)
                    {
                        size += 2 + extraBytesMoney(afvArray[j].get(0)) + 8 * afvArray[j].size();
                    }
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }
            else
                throw new Sim0MQException("Unknown data type " + content[i].getClass() + " for encoding the ZeroMQ message");
        }

        byte[] message = new byte[size];
        int pointer = 0;

        for (int i = 0; i < content.length; i++)
        {
            Object field = content[i];
            int hc = field.getClass().hashCode();

            if (hc == BYTE_HC)
            {
                message[pointer++] = Sim0MQTypes.BYTE_8;
                message[pointer++] = (byte) field;
            }

            else if (hc == SHORT_HC)
            {
                message[pointer++] = Sim0MQTypes.SHORT_16;
                short v = (short) field;
                pointer = EndianUtil.encodeShort(v, message, pointer);
            }

            else if (hc == INTEGER_HC)
            {
                message[pointer++] = Sim0MQTypes.INT_32;
                int v = (int) field;
                pointer = EndianUtil.encodeInt(v, message, pointer);
            }

            else if (hc == LONG_HC)
            {
                message[pointer++] = Sim0MQTypes.LONG_64;
                long v = (long) field;
                pointer = EndianUtil.encodeLong(v, message, pointer);
            }

            else if (hc == FLOAT_HC)
            {
                message[pointer++] = Sim0MQTypes.FLOAT_32;
                float v = (float) field;
                pointer = EndianUtil.encodeFloat(v, message, pointer);
            }

            else if (hc == DOUBLE_HC)
            {
                message[pointer++] = Sim0MQTypes.DOUBLE_64;
                double v = (double) field;
                pointer = EndianUtil.encodeDouble(v, message, pointer);
            }

            else if (hc == BOOLEAN_HC)
            {
                message[pointer++] = Sim0MQTypes.BOOLEAN_8;
                message[pointer++] = (byte) ((boolean) field ? 1 : 0);
            }

            else if (hc == CHAR_HC && utf8)
            {
                message[pointer++] = Sim0MQTypes.CHAR_8;
                char v = (char) field;
                message[pointer++] = (byte) (v & 0xFF);
            }

            else if (hc == CHAR_HC && !utf8)
            {
                message[pointer++] = Sim0MQTypes.CHAR_16;
                char v = (char) field;
                pointer = EndianUtil.encodeChar(v, message, pointer);
            }

            else if (hc == STRING_HC && utf8)
            {
                message[pointer++] = Sim0MQTypes.STRING_8;
                int len = ((String) field).length();
                pointer = EndianUtil.encodeInt(len, message, pointer);
                byte[] s = ((String) field).getBytes(UTF8);
                for (byte b : s)
                {
                    message[pointer++] = b;
                }
            }

            else if (hc == STRING_HC && !utf8)
            {
                message[pointer++] = Sim0MQTypes.STRING_16;
                int len = ((String) field).length();
                pointer = EndianUtil.encodeInt(len, message, pointer);
                byte[] s = ((String) field).getBytes(UTF16);
                for (byte b : s)
                {
                    message[pointer++] = b;
                }
            }

            else if (hc == BYTE_ARRAY_HC)
            {
                message[pointer++] = Sim0MQTypes.BYTE_8_ARRAY;
                int len = ((byte[]) field).length;
                pointer = EndianUtil.encodeInt(len, message, pointer);
                for (byte v : (byte[]) field)
                {
                    message[pointer++] = v;
                }
            }

            else if (hc == SHORT_ARRAY_HC)
            {
                message[pointer++] = Sim0MQTypes.SHORT_16_ARRAY;
                int len = ((short[]) field).length;
                pointer = EndianUtil.encodeInt(len, message, pointer);
                for (short v : (short[]) field)
                {
                    pointer = EndianUtil.encodeShort(v, message, pointer);
                }
            }

            else if (hc == INT_ARRAY_HC)
            {
                message[pointer++] = Sim0MQTypes.INT_32_ARRAY;
                int len = ((int[]) field).length;
                pointer = EndianUtil.encodeInt(len, message, pointer);
                for (int v : (int[]) field)
                {
                    pointer = EndianUtil.encodeInt(v, message, pointer);
                }
            }

            else if (hc == LONG_ARRAY_HC)
            {
                message[pointer++] = Sim0MQTypes.LONG_64_ARRAY;
                int len = ((long[]) field).length;
                pointer = EndianUtil.encodeInt(len, message, pointer);
                for (long v : (long[]) field)
                {
                    pointer = EndianUtil.encodeLong(v, message, pointer);
                }
            }

            else if (hc == FLOAT_ARRAY_HC)
            {
                message[pointer++] = Sim0MQTypes.FLOAT_32_ARRAY;
                int len = ((float[]) field).length;
                pointer = EndianUtil.encodeInt(len, message, pointer);
                for (float v : (float[]) field)
                {
                    pointer = EndianUtil.encodeFloat(v, message, pointer);
                }
            }

            else if (hc == DOUBLE_ARRAY_HC)
            {
                message[pointer++] = Sim0MQTypes.DOUBLE_64_ARRAY;
                int len = ((double[]) field).length;
                pointer = EndianUtil.encodeInt(len, message, pointer);
                for (double v : (double[]) field)
                {
                    pointer = EndianUtil.encodeDouble(v, message, pointer);
                }
            }

            else if (hc == BOOLEAN_ARRAY_HC)
            {
                message[pointer++] = Sim0MQTypes.BOOLEAN_8_ARRAY;
                int len = ((boolean[]) field).length;
                pointer = EndianUtil.encodeInt(len, message, pointer);
                for (boolean v : (boolean[]) field)
                {
                    message[pointer++] = (byte) (v ? 1 : 0);
                }
            }

            else if (hc == BYTE_MATRIX_HC)
            {
                message[pointer++] = Sim0MQTypes.BYTE_8_MATRIX;
                byte[][] matrix = (byte[][]) field;
                int rows = matrix.length;
                pointer = EndianUtil.encodeInt(rows, message, pointer);
                int cols = matrix[0].length;
                pointer = EndianUtil.encodeInt(cols, message, pointer);
                for (int row = 0; row < rows; row++)
                {
                    byte[] vRow = matrix[row];
                    for (byte v : vRow)
                    {
                        message[pointer++] = v;
                    }
                }
            }

            else if (hc == SHORT_MATRIX_HC)
            {
                message[pointer++] = Sim0MQTypes.SHORT_16_MATRIX;
                short[][] matrix = (short[][]) field;
                int rows = matrix.length;
                pointer = EndianUtil.encodeInt(rows, message, pointer);
                int cols = matrix[0].length;
                pointer = EndianUtil.encodeInt(cols, message, pointer);
                for (int row = 0; row < rows; row++)
                {
                    short[] vRow = matrix[row];
                    for (short v : vRow)
                    {
                        pointer = EndianUtil.encodeShort(v, message, pointer);
                    }
                }
            }

            else if (hc == INT_MATRIX_HC)
            {
                message[pointer++] = Sim0MQTypes.INT_32_MATRIX;
                int[][] matrix = (int[][]) field;
                int rows = matrix.length;
                pointer = EndianUtil.encodeInt(rows, message, pointer);
                int cols = matrix[0].length;
                pointer = EndianUtil.encodeInt(cols, message, pointer);
                for (int row = 0; row < rows; row++)
                {
                    int[] vRow = matrix[row];
                    for (int v : vRow)
                    {
                        pointer = EndianUtil.encodeInt(v, message, pointer);
                    }
                }
            }

            else if (hc == LONG_MATRIX_HC)
            {
                message[pointer++] = Sim0MQTypes.LONG_64_MATRIX;
                long[][] matrix = (long[][]) field;
                int rows = matrix.length;
                pointer = EndianUtil.encodeInt(rows, message, pointer);
                int cols = matrix[0].length;
                pointer = EndianUtil.encodeInt(cols, message, pointer);
                for (int row = 0; row < rows; row++)
                {
                    long[] vRow = matrix[row];
                    for (long v : vRow)
                    {
                        pointer = EndianUtil.encodeLong(v, message, pointer);
                    }
                }
            }

            else if (hc == FLOAT_MATRIX_HC)
            {
                message[pointer++] = Sim0MQTypes.FLOAT_32_MATRIX;
                float[][] matrix = (float[][]) field;
                int rows = matrix.length;
                pointer = EndianUtil.encodeInt(rows, message, pointer);
                int cols = matrix[0].length;
                pointer = EndianUtil.encodeInt(cols, message, pointer);
                for (int row = 0; row < rows; row++)
                {
                    float[] vRow = matrix[row];
                    for (float v : vRow)
                    {
                        pointer = EndianUtil.encodeFloat(v, message, pointer);
                    }
                }
            }

            else if (hc == DOUBLE_MATRIX_HC)
            {
                message[pointer++] = Sim0MQTypes.DOUBLE_64_MATRIX;
                double[][] matrix = (double[][]) field;
                int rows = matrix.length;
                pointer = EndianUtil.encodeInt(rows, message, pointer);
                int cols = matrix[0].length;
                pointer = EndianUtil.encodeInt(cols, message, pointer);
                for (int row = 0; row < rows; row++)
                {
                    double[] vRow = matrix[row];
                    for (double v : vRow)
                    {
                        pointer = EndianUtil.encodeDouble(v, message, pointer);
                    }
                }
            }

            else if (hc == BOOLEAN_MATRIX_HC)
            {
                message[pointer++] = Sim0MQTypes.BOOLEAN_8_MATRIX;
                boolean[][] matrix = (boolean[][]) field;
                int rows = matrix.length;
                pointer = EndianUtil.encodeInt(rows, message, pointer);
                int cols = matrix[0].length;
                pointer = EndianUtil.encodeInt(cols, message, pointer);
                for (int row = 0; row < rows; row++)
                {
                    boolean[] vRow = matrix[row];
                    for (boolean v : vRow)
                    {
                        message[pointer++] = (byte) (v ? 1 : 0);
                    }
                }
            }

            else if (field instanceof AbstractFloatScalar)
            {
                message[pointer++] = Sim0MQTypes.FLOAT_32_UNIT;
                pointer = encodeUnit(((AbstractFloatScalar<?, ?>) field).getUnit(), message, pointer);
                float v = ((AbstractFloatScalar<?, ?>) field).si;
                pointer = EndianUtil.encodeFloat(v, message, pointer);
            }

            else if (content[i] instanceof AbstractDoubleScalar)
            {
                message[pointer++] = Sim0MQTypes.DOUBLE_64_UNIT;
                pointer = encodeUnit(((AbstractDoubleScalar<?, ?>) field).getUnit(), message, pointer);
                double v = ((AbstractDoubleScalar<?, ?>) field).si;
                pointer = EndianUtil.encodeDouble(v, message, pointer);
            }

            else if (content[i] instanceof AbstractFloatVector)
            {
                message[pointer++] = Sim0MQTypes.FLOAT_32_UNIT_ARRAY;
                AbstractFloatVector<?, ?> afv = (AbstractFloatVector<?, ?>) content[i];
                pointer = EndianUtil.encodeInt(afv.size(), message, pointer);
                pointer = encodeUnit(afv.getUnit(), message, pointer);
                try
                {
                    for (int j = 0; j < afv.size(); j++)
                    {
                        pointer = EndianUtil.encodeFloat(afv.getSI(j), message, pointer);
                    }
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }

            else if (content[i] instanceof AbstractDoubleVector)
            {
                message[pointer++] = Sim0MQTypes.DOUBLE_64_UNIT_ARRAY;
                AbstractDoubleVector<?, ?> adv = (AbstractDoubleVector<?, ?>) content[i];
                pointer = EndianUtil.encodeInt(adv.size(), message, pointer);
                pointer = encodeUnit(adv.getUnit(), message, pointer);
                try
                {
                    for (int j = 0; j < adv.size(); j++)
                    {
                        pointer = EndianUtil.encodeDouble(adv.getSI(j), message, pointer);
                    }
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }

            else if (content[i] instanceof AbstractFloatMatrix)
            {
                message[pointer++] = Sim0MQTypes.FLOAT_32_UNIT_MATRIX;
                AbstractFloatMatrix<?, ?> afm = (AbstractFloatMatrix<?, ?>) content[i];
                pointer = EndianUtil.encodeInt(afm.rows(), message, pointer);
                pointer = EndianUtil.encodeInt(afm.columns(), message, pointer);
                pointer = encodeUnit(afm.getUnit(), message, pointer);
                try
                {
                    for (int row = 0; row < afm.rows(); row++)
                    {
                        for (int col = 0; col < afm.columns(); col++)
                        {
                            pointer = EndianUtil.encodeFloat(afm.getSI(row, col), message, pointer);
                        }
                    }
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }

            else if (content[i] instanceof AbstractDoubleMatrix)
            {
                message[pointer++] = Sim0MQTypes.DOUBLE_64_UNIT_MATRIX;
                AbstractDoubleMatrix<?, ?> adm = (AbstractDoubleMatrix<?, ?>) content[i];
                pointer = EndianUtil.encodeInt(adm.rows(), message, pointer);
                pointer = EndianUtil.encodeInt(adm.columns(), message, pointer);
                pointer = encodeUnit(adm.getUnit(), message, pointer);
                try
                {
                    for (int row = 0; row < adm.rows(); row++)
                    {
                        for (int col = 0; col < adm.columns(); col++)
                        {
                            pointer = EndianUtil.encodeDouble(adm.getSI(row, col), message, pointer);
                        }
                    }
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }

            else if (content[i] instanceof AbstractFloatVector[])
            {
                message[pointer++] = Sim0MQTypes.FLOAT_32_UNIT_COLUMN_ARRAY;
                AbstractFloatVector<?, ?>[] afvArray = (AbstractFloatVector<?, ?>[]) content[i];
                pointer = EndianUtil.encodeInt(afvArray[0].size(), message, pointer); // rows
                pointer = EndianUtil.encodeInt(afvArray.length, message, pointer); // cols
                for (int col = 0; col < afvArray.length; col++)
                {
                    pointer = encodeUnit(afvArray[col].getUnit(), message, pointer);
                }
                try
                {
                    for (int row = 0; row < afvArray[0].size(); row++)
                    {
                        for (int col = 0; col < afvArray.length; col++)
                        {
                            pointer = EndianUtil.encodeFloat(afvArray[col].getSI(row), message, pointer);
                        }
                    }
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }

            else if (content[i] instanceof AbstractDoubleVector[])
            {
                message[pointer++] = Sim0MQTypes.DOUBLE_64_UNIT_COLUMN_ARRAY;
                AbstractDoubleVector<?, ?>[] advArray = (AbstractDoubleVector<?, ?>[]) content[i];
                pointer = EndianUtil.encodeInt(advArray[0].size(), message, pointer); // rows
                pointer = EndianUtil.encodeInt(advArray.length, message, pointer); // cols
                for (int col = 0; col < advArray.length; col++)
                {
                    pointer = encodeUnit(advArray[col].getUnit(), message, pointer);
                }
                try
                {
                    for (int row = 0; row < advArray[0].size(); row++)
                    {
                        for (int col = 0; col < advArray.length; col++)
                        {
                            pointer = EndianUtil.encodeDouble(advArray[col].getSI(row), message, pointer);
                        }
                    }
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }

            else
                throw new Sim0MQException("Unknown data type " + content[i].getClass() + " for encoding the ZeroMQ message");

        }

        return message;
    }

    /**
     * Code a unit, including MoneyUnits.
     * @param unit the unit to code in the byte array
     * @param message the byte array
     * @param pointer the start pointer in the byte array
     * @return the new pointer in the byte array
     */
    @SuppressWarnings("rawtypes")
    private static int encodeUnit(final Unit unit, final byte[] message, final int pointer)
    {
        int p = pointer;
        @SuppressWarnings("unchecked") // TODO see how this can be solved with type <U extends Unit<U>>
        Sim0MQUnitType unitType = Sim0MQUnitType.getUnitType(unit);
        message[p++] = unitType.getCode();
        if (unit instanceof MoneyUnit)
        {
            @SuppressWarnings("unchecked")
            Sim0MQDisplayType displayType = Sim0MQDisplayType.getDisplayType(unit);
            p = EndianUtil.encodeShort((short) displayType.getIntCode(), message, p);
        }
        else if (unit instanceof MoneyPerAreaUnit)
        {
            Sim0MQDisplayType moneyType = Sim0MQDisplayType.getDisplayType(((MoneyPerAreaUnit) unit).getMoneyUnit());
            p = EndianUtil.encodeShort((short) moneyType.getIntCode(), message, p);
            Sim0MQDisplayType perType = Sim0MQDisplayType.getDisplayType(((MoneyPerAreaUnit) unit).getAreaUnit());
            message[p++] = perType.getByteCode();
        }
        else if (unit instanceof MoneyPerEnergyUnit)
        {
            Sim0MQDisplayType moneyType = Sim0MQDisplayType.getDisplayType(((MoneyPerEnergyUnit) unit).getMoneyUnit());
            p = EndianUtil.encodeShort((short) moneyType.getIntCode(), message, p);
            Sim0MQDisplayType perType = Sim0MQDisplayType.getDisplayType(((MoneyPerEnergyUnit) unit).getEnergyUnit());
            message[p++] = perType.getByteCode();
        }
        else if (unit instanceof MoneyPerLengthUnit)
        {
            Sim0MQDisplayType moneyType = Sim0MQDisplayType.getDisplayType(((MoneyPerLengthUnit) unit).getMoneyUnit());
            p = EndianUtil.encodeShort((short) moneyType.getIntCode(), message, p);
            Sim0MQDisplayType perType = Sim0MQDisplayType.getDisplayType(((MoneyPerLengthUnit) unit).getLengthUnit());
            message[p++] = perType.getByteCode();
        }
        else if (unit instanceof MoneyPerMassUnit)
        {
            Sim0MQDisplayType moneyType = Sim0MQDisplayType.getDisplayType(((MoneyPerMassUnit) unit).getMoneyUnit());
            p = EndianUtil.encodeShort((short) moneyType.getIntCode(), message, p);
            Sim0MQDisplayType perType = Sim0MQDisplayType.getDisplayType(((MoneyPerMassUnit) unit).getMassUnit());
            message[p++] = perType.getByteCode();
        }
        else if (unit instanceof MoneyPerDurationUnit)
        {
            Sim0MQDisplayType moneyType = Sim0MQDisplayType.getDisplayType(((MoneyPerDurationUnit) unit).getMoneyUnit());
            p = EndianUtil.encodeShort((short) moneyType.getIntCode(), message, p);
            Sim0MQDisplayType perType = Sim0MQDisplayType.getDisplayType(((MoneyPerDurationUnit) unit).getDurationUnit());
            message[p++] = perType.getByteCode();
        }
        else if (unit instanceof MoneyPerVolumeUnit)
        {
            Sim0MQDisplayType moneyType = Sim0MQDisplayType.getDisplayType(((MoneyPerVolumeUnit) unit).getMoneyUnit());
            p = EndianUtil.encodeShort((short) moneyType.getIntCode(), message, p);
            Sim0MQDisplayType perType = Sim0MQDisplayType.getDisplayType(((MoneyPerVolumeUnit) unit).getVolumeUnit());
            message[p++] = perType.getByteCode();
        }
        else
        {
            @SuppressWarnings("unchecked")
            Sim0MQDisplayType displayType = Sim0MQDisplayType.getDisplayType(unit);
            message[p++] = displayType.getByteCode();
        }
        return p;
    }

    /**
     * Decode the message into an object array.
     * @param message the ZeroMQ byte array to decode
     * @return an array of objects of the right type
     * @throws Sim0MQException on unknown data type
     */
    @SuppressWarnings({ "checkstyle:methodlength", "checkstyle:needbraces" })
    public static Object[] decodeSim0MQMessage(final byte[] message) throws Sim0MQException
    {
        Throw.when(message.length < 10, Sim0MQException.class, "Message length < 10");
        Object[] array = decode(message);

        // check the magic number
        byte char0 = message[0];
        Throw.when(char0 != 9, Sim0MQException.class, "Message does not start with an UTF8 string");
        int magicLen = EndianUtil.decodeInt(message, 1);
        Throw.when(magicLen < 0, Sim0MQException.class, "Length of magic number < 0");
        Throw.when(Double.isNaN(magicLen), Sim0MQException.class, "Length of magic number = NaN");
        Throw.when(Double.isInfinite(magicLen), Sim0MQException.class, "Length of magic number = Infinite");
        Throw.when(magicLen > 10, Sim0MQException.class, "Length of magic number > 10");
        String magicNumber = EndianUtil.decodeUTF8String(message, 1);
        Throw.when(!magicNumber.startsWith("SIM"), Sim0MQException.class,
                "Magic number does not start with SIM but with " + magicNumber);
        Throw.when(!magicNumber.equals(VERSION), Sim0MQException.class,
                "Message version " + magicNumber + " not compatible with this software version " + VERSION);

        return array;
    }

    /**
     * Decode the message into an object array.
     * @param message the ZeroMQ byte array to decode
     * @return an array of objects of the right type
     * @throws Sim0MQException on unknown data type
     */
    @SuppressWarnings({ "checkstyle:methodlength", "checkstyle:needbraces" })
    public static Object[] decode(final byte[] message) throws Sim0MQException
    {
        List<Object> list = new ArrayList<>();
        MessageBuffer mb = new MessageBuffer(message);
        while (mb.hasMore())
        {
            byte type = mb.getByte();

            if (type == Sim0MQTypes.BYTE_8)
            {
                list.add(mb.getByte());
            }

            else if (type == Sim0MQTypes.SHORT_16)
            {
                list.add(mb.getShort());
            }

            else if (type == Sim0MQTypes.INT_32)
            {
                list.add(mb.getInt());
            }

            else if (type == Sim0MQTypes.LONG_64)
            {
                list.add(mb.getLong());
            }

            else if (type == Sim0MQTypes.FLOAT_32)
            {
                list.add(mb.getFloat());
            }

            else if (type == Sim0MQTypes.DOUBLE_64)
            {
                list.add(mb.getDouble());
            }

            else if (type == Sim0MQTypes.BOOLEAN_8)
            {
                list.add(mb.getBoolean());
            }

            else if (type == Sim0MQTypes.CHAR_8)
            {
                list.add(mb.getCharUTF8());
            }

            else if (type == Sim0MQTypes.CHAR_16)
            {
                list.add(mb.getCharUTF16());
            }

            else if (type == Sim0MQTypes.STRING_8)
            {
                list.add(mb.getStringUTF8());
            }

            else if (type == Sim0MQTypes.STRING_16)
            {
                list.add(mb.getStringUTF16());
            }

            else if (type == Sim0MQTypes.BYTE_8_ARRAY)
            {
                int size = mb.getInt();
                byte[] value = new byte[size];
                for (int i = 0; i < size; i++)
                    value[i] = mb.getByte();
                list.add(value);
            }

            else if (type == Sim0MQTypes.SHORT_16_ARRAY)
            {
                int size = mb.getInt();
                short[] value = new short[size];
                for (int i = 0; i < size; i++)
                    value[i] = mb.getShort();
                list.add(value);
            }

            else if (type == Sim0MQTypes.INT_32_ARRAY)
            {
                int size = mb.getInt();
                int[] value = new int[size];
                for (int i = 0; i < size; i++)
                    value[i] = mb.getInt();
                list.add(value);
            }

            else if (type == Sim0MQTypes.LONG_64_ARRAY)
            {
                int size = mb.getInt();
                long[] value = new long[size];
                for (int i = 0; i < size; i++)
                    value[i] = mb.getLong();
                list.add(value);
            }

            else if (type == Sim0MQTypes.FLOAT_32_ARRAY)
            {
                int size = mb.getInt();
                float[] value = new float[size];
                for (int i = 0; i < size; i++)
                    value[i] = mb.getFloat();
                list.add(value);
            }

            else if (type == Sim0MQTypes.DOUBLE_64_ARRAY)
            {
                int size = mb.getInt();
                double[] value = new double[size];
                for (int i = 0; i < size; i++)
                    value[i] = mb.getDouble();
                list.add(value);
            }

            else if (type == Sim0MQTypes.BOOLEAN_8_ARRAY)
            {
                int size = mb.getInt();
                boolean[] value = new boolean[size];
                for (int i = 0; i < size; i++)
                    value[i] = mb.getBoolean();
                list.add(value);
            }

            else if (type == Sim0MQTypes.BYTE_8_MATRIX)
            {
                int rows = mb.getInt();
                int cols = mb.getInt();
                byte[][] value = new byte[rows][];
                for (int row = 0; row < rows; row++)
                {
                    for (int col = 0; col < cols; col++)
                    {
                        if (col == 0)
                            value[row] = new byte[cols];
                        value[row][col] = mb.getByte();
                    }
                }
                list.add(value);
            }

            else if (type == Sim0MQTypes.SHORT_16_MATRIX)
            {
                int rows = mb.getInt();
                int cols = mb.getInt();
                short[][] value = new short[rows][];
                for (int row = 0; row < rows; row++)
                {
                    for (int col = 0; col < cols; col++)
                    {
                        if (col == 0)
                            value[row] = new short[cols];
                        value[row][col] = mb.getShort();
                    }
                }
                list.add(value);
            }

            else if (type == Sim0MQTypes.INT_32_MATRIX)
            {
                int rows = mb.getInt();
                int cols = mb.getInt();
                int[][] value = new int[rows][];
                for (int row = 0; row < rows; row++)
                {
                    for (int col = 0; col < cols; col++)
                    {
                        if (col == 0)
                            value[row] = new int[cols];
                        value[row][col] = mb.getInt();
                    }
                }
                list.add(value);
            }

            else if (type == Sim0MQTypes.LONG_64_MATRIX)
            {
                int rows = mb.getInt();
                int cols = mb.getInt();
                long[][] value = new long[rows][];
                for (int row = 0; row < rows; row++)
                {
                    for (int col = 0; col < cols; col++)
                    {
                        if (col == 0)
                            value[row] = new long[cols];
                        value[row][col] = mb.getLong();
                    }
                }
                list.add(value);
            }

            else if (type == Sim0MQTypes.FLOAT_32_MATRIX)
            {
                int rows = mb.getInt();
                int cols = mb.getInt();
                float[][] value = new float[rows][];
                for (int row = 0; row < rows; row++)
                {
                    for (int col = 0; col < cols; col++)
                    {
                        if (col == 0)
                            value[row] = new float[cols];
                        value[row][col] = mb.getFloat();
                    }
                }
                list.add(value);
            }

            else if (type == Sim0MQTypes.DOUBLE_64_MATRIX)
            {
                int rows = mb.getInt();
                int cols = mb.getInt();
                double[][] value = new double[rows][];
                for (int row = 0; row < rows; row++)
                {
                    for (int col = 0; col < cols; col++)
                    {
                        if (col == 0)
                            value[row] = new double[cols];
                        value[row][col] = mb.getDouble();
                    }
                }
                list.add(value);
            }

            else if (type == Sim0MQTypes.BOOLEAN_8_MATRIX)
            {
                int rows = mb.getInt();
                int cols = mb.getInt();
                boolean[][] value = new boolean[rows][];
                for (int row = 0; row < rows; row++)
                {
                    for (int col = 0; col < cols; col++)
                    {
                        if (col == 0)
                            value[row] = new boolean[cols];
                        value[row][col] = mb.getBoolean();
                    }
                }
                list.add(value);
            }

            else if (type == Sim0MQTypes.FLOAT_32_UNIT)
            {
                Unit<? extends Unit<?>> unit = mb.getUnit();
                float si = mb.getFloat();
                list.add(FloatScalarUtil.instantiateAnonymousSI(si, unit));
            }

            else if (type == Sim0MQTypes.DOUBLE_64_UNIT)
            {
                Unit<? extends Unit<?>> unit = mb.getUnit();
                double si = mb.getDouble();
                list.add(DoubleScalarUtil.instantiateAnonymousSI(si, unit));
            }

            else if (type == Sim0MQTypes.FLOAT_32_UNIT_ARRAY)
            {
                int size = mb.getInt();
                Unit<? extends Unit<?>> unit = mb.getUnit();
                float[] array = new float[size];
                for (int i = 0; i < size; i++)
                    array[i] = mb.getFloat();
                try
                {
                    list.add(FloatVectorUtil.instantiateAnonymousSI(array, unit, StorageType.DENSE));
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }

            else if (type == Sim0MQTypes.DOUBLE_64_UNIT_ARRAY)
            {
                int size = mb.getInt();
                Unit<? extends Unit<?>> unit = mb.getUnit();
                double[] array = new double[size];
                for (int i = 0; i < size; i++)
                    array[i] = mb.getDouble();
                try
                {
                    list.add(DoubleVectorUtil.instantiateAnonymousSI(array, unit, StorageType.DENSE));
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }

            else if (type == Sim0MQTypes.FLOAT_32_UNIT_MATRIX)
            {
                int rows = mb.getInt();
                int cols = mb.getInt();
                Unit<? extends Unit<?>> unit = mb.getUnit();
                float[][] matrix = new float[rows][cols];
                for (int row = 0; row < rows; row++)
                {
                    for (int col = 0; col < cols; col++)
                    {
                        if (col == 0)
                            matrix[row] = new float[cols];
                        matrix[row][col] = mb.getFloat();
                    }
                }
                try
                {
                    list.add(FloatMatrixUtil.instantiateAnonymousSI(matrix, unit, StorageType.DENSE));
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }

            else if (type == Sim0MQTypes.DOUBLE_64_UNIT_MATRIX)
            {
                int rows = mb.getInt();
                int cols = mb.getInt();
                Unit<? extends Unit<?>> unit = mb.getUnit();
                double[][] matrix = new double[rows][cols];
                for (int row = 0; row < rows; row++)
                {
                    for (int col = 0; col < cols; col++)
                    {
                        if (col == 0)
                            matrix[row] = new double[cols];
                        matrix[row][col] = mb.getDouble();
                    }
                }
                try
                {
                    list.add(DoubleMatrixUtil.instantiateAnonymousSI(matrix, unit, StorageType.DENSE));
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }

            else if (type == Sim0MQTypes.FLOAT_32_UNIT_COLUMN_ARRAY)
            {
                int rows = mb.getInt();
                int cols = mb.getInt();
                Unit<? extends Unit<?>>[] units = new Unit<?>[cols];
                AbstractFloatVector<?, ?>[] vArray = new AbstractFloatVector[cols];
                for (int col = 0; col < cols; col++)
                {
                    units[col] = mb.getUnit();
                }
                // here we use a column-first matrix (!) for storage
                float[][] matrix = new float[cols][rows];
                for (int row = 0; row < rows; row++)
                {
                    for (int col = 0; col < cols; col++)
                    {
                        if (col == 0)
                            matrix[row] = new float[cols];
                        matrix[col][row] = mb.getFloat();
                    }
                }
                try
                {
                    for (int col = 0; col < cols; col++)
                    {
                        vArray[col] = FloatVectorUtil.instantiateAnonymousSI(matrix[col], units[col], StorageType.DENSE);
                    }
                    list.add(vArray);
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }

            else if (type == Sim0MQTypes.DOUBLE_64_UNIT_COLUMN_ARRAY)
            {
                int rows = mb.getInt();
                int cols = mb.getInt();
                Unit<? extends Unit<?>>[] units = new Unit<?>[cols];
                AbstractDoubleVector<?, ?>[] vArray = new AbstractDoubleVector[cols];
                for (int col = 0; col < cols; col++)
                {
                    units[col] = mb.getUnit();
                }
                // here we use a column-first matrix (!) for storage
                double[][] matrix = new double[cols][rows];
                for (int row = 0; row < rows; row++)
                {
                    for (int col = 0; col < cols; col++)
                    {
                        if (col == 0)
                            matrix[row] = new double[cols];
                        matrix[col][row] = mb.getDouble();
                    }
                }
                try
                {
                    for (int col = 0; col < cols; col++)
                    {
                        vArray[col] = DoubleVectorUtil.instantiateAnonymousSI(matrix[col], units[col], StorageType.DENSE);
                    }
                    list.add(vArray);
                }
                catch (ValueException exception)
                {
                    throw new Sim0MQException(exception);
                }
            }

            else
            {
                throw new Sim0MQException("Unknown data type " + type + " in the ZeroMQ message while decoding");
            }
        }

        Object[] array = list.toArray();
        return array;
    }

    /**
     * Indicate whether extra bytes are needed for a Money per quantity type.
     * @param o the object to check
     * @return 0 or 1 to indicate whether an extra byte is needed
     */
    private static int extraBytesMoney(final Object o)
    {
        if (o instanceof Money)
        {
            return 1;
        }
        else if (o instanceof MoneyPerArea || o instanceof MoneyPerEnergy || o instanceof MoneyPerLength
                || o instanceof MoneyPerMass || o instanceof MoneyPerDuration || o instanceof MoneyPerVolume)
        {
            return 2;
        }
        else if (o instanceof FloatMoney)
        {
            return 1;
        }
        else if (o instanceof FloatMoneyPerArea || o instanceof FloatMoneyPerEnergy || o instanceof FloatMoneyPerLength
                || o instanceof FloatMoneyPerMass || o instanceof FloatMoneyPerDuration || o instanceof FloatMoneyPerVolume)
        {
            return 2;
        }
        return 0;
    }

    /**
     * Return a readable string with the bytes in a byte[] message.
     * @param bytes byte[]; the byte array to display
     * @return String; a readable string with the bytes in a byte[] message
     */
    public static String printBytes(final byte[] bytes)
    {
        StringBuffer s = new StringBuffer();
        s.append("|");
        for (int b : bytes)
        {
            if (b < 0)
            {
                b += 128;
            }
            if (b >= 32 && b <= 127)
            {
                s.append("#" + Integer.toString(b, 16).toUpperCase() + "(" + (char) (byte) b + ")|");
            }
            else
            {
                s.append("#" + Integer.toString(b, 16).toUpperCase() + "|");
            }
        }
        return s.toString();
    }

}

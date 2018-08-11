package org.sim0mq.message;

import org.djunits.unit.Unit;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.types.Sim0MQDisplayType;
import org.sim0mq.message.types.Sim0MQUnitType;
import org.sim0mq.message.types.TypesUtil;
import org.sim0mq.message.util.EndianUtil;

/**
 * Container for decoding a message to allow for easy buffer manipulation including the pointer.
 * <p>
 * Copyright (c) 2013-2017 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version Aug 11, 2018 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
class MessageBuffer
{
    /** the message content. */
    private byte[] message;

    /** the pointer into the message content. */
    private int pointer;

    /**
     * Create a message buffer for decoding.
     * @param message the message
     */
    MessageBuffer(final byte[] message)
    {
        this.message = message;
        this.pointer = 0;
    }

    /**
     * @return the pointer into the message buffer
     */
    public int getPointer()
    {
        return this.pointer;
    }

    /**
     * @return whether the buffer has more bytes to process
     */
    public boolean hasMore()
    {
        return this.pointer < this.message.length;
    }

    /**
     * @return the next byte in the buffer
     */
    public byte getByte()
    {
        return this.message[this.pointer++];
    }

    /**
     * @return the next short in the buffer
     */
    public short getShort()
    {
        short value = EndianUtil.decodeShort(this.message, this.pointer);
        this.pointer += 2;
        return value;
    }

    /**
     * @return the next int in the buffer
     */
    public int getInt()
    {
        int value = EndianUtil.decodeInt(this.message, this.pointer);
        this.pointer += 4;
        return value;
    }

    /**
     * @return the next long in the buffer
     */
    public long getLong()
    {
        long value = EndianUtil.decodeLong(this.message, this.pointer);
        this.pointer += 8;
        return value;
    }

    /**
     * @return the next float in the buffer
     */
    public float getFloat()
    {
        float value = EndianUtil.decodeFloat(this.message, this.pointer);
        this.pointer += 4;
        return value;
    }

    /**
     * @return the next double in the buffer
     */
    public double getDouble()
    {
        double value = EndianUtil.decodeDouble(this.message, this.pointer);
        this.pointer += 8;
        return value;
    }

    /**
     * @return the next boolean in the buffer
     */
    public boolean getBoolean()
    {
        return getByte() == 0 ? false : true;
    }

    /**
     * @return the next char with UTF8 in the buffer
     */
    public char getCharUTF8()
    {
        return (char) getByte();
    }

    /**
     * @return the next char with UTF16 in the buffer
     */
    public char getCharUTF16()
    {
        char value = EndianUtil.decodeChar(this.message, this.pointer);
        this.pointer += 2;
        return value;
    }

    /**
     * @return the next String with UTF8 in the buffer
     */
    public String getStringUTF8()
    {
        String s = EndianUtil.decodeUTF8String(this.message, this.pointer);
        this.pointer += 4 + s.length();
        return s;
    }

    /**
     * @return the next String with UTF16 in the buffer
     */
    public String getStringUTF16()
    {
        String s = EndianUtil.decodeUTF16String(this.message, this.pointer);
        this.pointer += 4 + 2 * s.length();
        return s;
    }

    /**
     * @return the next 1, 2, or 3 byte Unit in the buffer
     */
    public Unit<? extends Unit<?>> getUnit()
    {
        Unit<? extends Unit<?>> unit;
        Sim0MQUnitType unitType = Sim0MQUnitType.getUnitType(getByte());
        if (unitType.getCode() == 100) // money
        {
            unit = decodeMoneyUnit();
        }
        else if (unitType.getCode() >= 101 && unitType.getCode() <= 106)
        {
            unit = decodeMoneyPerUnit(unitType);
        }
        else
        {
            Sim0MQDisplayType displayType = Sim0MQDisplayType.getDisplayType(unitType, getByte());
            unit = displayType.getDjunitsType();
        }
        return unit;
    }

    /**
     * Decode the 2-byte Money unit in the message (code 100).
     * @return decoded money unit
     */
    private Unit<? extends Unit<?>> decodeMoneyUnit()
    {
        short moneyCode = getShort();
        Sim0MQDisplayType displayType = Sim0MQDisplayType.getDisplayType(Sim0MQUnitType.MONEY, moneyCode);
        return displayType.getDjunitsType();
    }

    /**
     * Decode the 2-byte MoneyPerUnit unit in the message (code 101 - 106).
     * @param unitType the unit type (e.g., MoneyPerArea)
     * @return decoded MoneyPerUnit unit
     */
    @SuppressWarnings("checkstyle:needbraces")
    private Unit<? extends Unit<?>> decodeMoneyPerUnit(final Sim0MQUnitType unitType)
    {
        short moneyCode = getShort();
        Sim0MQDisplayType moneyDisplayType = Sim0MQDisplayType.getDisplayType(Sim0MQUnitType.MONEY, moneyCode);
        byte perCode = getByte();
        Sim0MQDisplayType perDisplayType;
        if (unitType.getCode() == 101)
            perDisplayType = Sim0MQDisplayType.getDisplayType(Sim0MQUnitType.AREA, perCode);
        else if (unitType.getCode() == 102)
            perDisplayType = Sim0MQDisplayType.getDisplayType(Sim0MQUnitType.ENERGY, perCode);
        else if (unitType.getCode() == 103)
            perDisplayType = Sim0MQDisplayType.getDisplayType(Sim0MQUnitType.LENGTH, perCode);
        else if (unitType.getCode() == 104)
            perDisplayType = Sim0MQDisplayType.getDisplayType(Sim0MQUnitType.MASS, perCode);
        else if (unitType.getCode() == 105)
            perDisplayType = Sim0MQDisplayType.getDisplayType(Sim0MQUnitType.DURATION, perCode);
        else if (unitType.getCode() == 106)
            perDisplayType = Sim0MQDisplayType.getDisplayType(Sim0MQUnitType.VOLUME, perCode);
        else
            throw new RuntimeException(new Sim0MQException("Unknown MoneyPerUnit type with code " + unitType.getCode()));
        return TypesUtil.moneyPerUnitType(moneyDisplayType, perDisplayType);
    }

}

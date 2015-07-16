package com.github.fge.bitflags;

import com.github.fge.bitflags.internal.VisibleForTesting;

import java.util.Arrays;
import java.util.stream.IntStream;

public final class BitFlags
{
    @VisibleForTesting
    static final String ILLEGAL_SIZE
        = "will not create an instance of size 0 or less";

    private final int nrBits;
    private final byte[] bytes;

    public static BitFlags ofSize(final int nrBits)
    {
        if (nrBits <= 0)
            throw new IllegalArgumentException(ILLEGAL_SIZE);
        return new BitFlags(nrBits);
    }

    private BitFlags(final int nrBits)
    {
        final int nrBytes = (nrBits + 7) / 8;
        this.nrBits = nrBits;
        bytes = new byte[nrBytes];
    }

    public boolean get(final int bitOffset)
    {
        if (bitOffset >= nrBits)
            throw new IndexOutOfBoundsException();
        final int byteIndex = bitOffset / 8;
        final byte targetByte = bytes[byteIndex];
        @SuppressWarnings("UnnecessaryParentheses")
        final int value = 1 << (bitOffset % 8);
        return (targetByte & value) == value;
    }

    public BitFlags set(final int bitOffset, final boolean b)
    {
        if (bitOffset >= nrBits)
            throw new IndexOutOfBoundsException();
        final int byteIndex = bitOffset / 8;
        int targetByte = bytes[byteIndex];
        @SuppressWarnings("UnnecessaryParentheses")
        final int mask = 1 << (bitOffset % 8);
        targetByte = b
            ? targetByte | mask
            : targetByte & ~mask;
        bytes[byteIndex] = (byte) targetByte;
        return this;
    }

    @Override
    public String toString()
    {
        final char[] chars = new char[bytes.length * 8];
        Arrays.fill(chars, 'x');
        IntStream.range(0, nrBits)
            .forEach(index -> chars[index] = get(index) ? '1' : '0');
        return new String(chars);
    }
}

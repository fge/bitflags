package com.github.fge.bitflags;

import java.util.Arrays;
import java.util.stream.IntStream;

public final class BitFlags
{
    private final int nrBits;
    private final byte[] bytes;

    public static BitFlags ofSize(final int nrBits)
    {
        if (nrBits <= 0)
            throw new IllegalArgumentException("will not create an instance"
                + " of size 0 or less");
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
        final byte b = bytes[byteIndex];
        @SuppressWarnings("UnnecessaryParentheses")
        final int value = 1 << (bitOffset % 8);
        return (b & value) == value;
    }

    @Override
    public String toString()
    {
        final char[] chars = new char[bytes.length * 8];
        Arrays.fill(chars, 'x');
        IntStream.of(0, nrBits)
            .forEach(index -> chars[index] = get(index) ? '1' : '0');
        return new String(chars);
    }
}

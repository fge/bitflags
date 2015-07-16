package com.github.fge.bitflags;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

public class BitFlagsTest
{
    @Test
    public void illegalSizeTest()
    {
        try {
            BitFlags.ofSize(0);
            shouldHaveThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage(BitFlags.ILLEGAL_SIZE);
        }
    }

    @SuppressWarnings("AutoBoxing")
    @DataProvider
    public Iterator<Object[]> toStringData()
    {
        final List<Object[]> list = new ArrayList<>();

        int constructorArgument;
        String expected;

        constructorArgument = 1;
        expected = "0xxxxxxx";
        list.add(new Object[] { constructorArgument, expected });

        constructorArgument = 4;
        expected = "0000xxxx";
        list.add(new Object[] { constructorArgument, expected });

        constructorArgument = 8;
        expected = "00000000";
        list.add(new Object[] { constructorArgument, expected });

        return list.iterator();
    }

    @Test(dataProvider = "toStringData")
    public void baseToStringTest(final int constructorArgument,
        final String expected)
    {
        final BitFlags bitFlags = BitFlags.ofSize(constructorArgument);
        assertThat(bitFlags.toString()).isEqualTo(expected);
    }
}

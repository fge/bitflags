package com.github.fge.bitflags;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

public class IllegalBitFlagsTest
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
}

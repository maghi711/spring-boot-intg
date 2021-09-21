package com.greeter;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AssertCheck {
    @Test
    public void listCompare() {
        List<String> names = Arrays.asList("Mahesh", "Aadavan");
        List<String> response = Arrays.asList("Mahes");
        Assert.assertTrue("Should not fail", names.containsAll(response));
    }
}

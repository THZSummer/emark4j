package com.mlj.emark4j.regex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

/**
 * 2022/09/03：
 *
 * @author MLJ
 */
public class RegexTest {

    public static final String COLOR_REGEX = "^#([a-fA-F0-9]{8}|([a-fA-F0-9]{6}|[a-fA-F0-9]{3}))$";

    @Test
    public void colorTest() {
        Pattern pattern = Pattern.compile(COLOR_REGEX);
        Assertions.assertFalse(pattern.matcher("").matches());
        Assertions.assertFalse(pattern.matcher("#").matches());
        Assertions.assertFalse(pattern.matcher("#0").matches());
        Assertions.assertFalse(pattern.matcher("#00").matches());
        Assertions.assertTrue(pattern.matcher("#000").matches());
        Assertions.assertFalse(pattern.matcher("#0000").matches());
        Assertions.assertFalse(pattern.matcher("#00000").matches());
        Assertions.assertTrue(pattern.matcher("#000000").matches());
        Assertions.assertFalse(pattern.matcher("#0000000").matches());
        Assertions.assertTrue(pattern.matcher("#00000000").matches());
        Assertions.assertFalse(pattern.matcher("#000000000").matches());
    }
}

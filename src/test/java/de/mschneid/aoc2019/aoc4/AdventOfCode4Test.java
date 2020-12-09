package de.mschneid.aoc2019.aoc4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdventOfCode4Test {

    private AdventOfCode4 sut;

    @BeforeEach
    public void setUp() {
        sut = new AdventOfCode4();
    }

    @Test
    public void test_is_number_six_digit_long() {
        String pw1 = "123456";
        String pw2 = "12345";
        String pw3 = "1234567";

        assertTrue(sut.isNumberSixDigitLong().test(pw1));
        assertFalse(sut.isNumberSixDigitLong().test(pw2));
        assertFalse(sut.isNumberSixDigitLong().test(pw3));
    }

    @Test
    public void test_is_number_increasing() {
        String pw1 = "123456";
        String pw2 = "122456";
        String pw3 = "122450";
        String pw4 = "165450";
        assertTrue(sut.isNumberIncreasing().test(pw1));
        assertTrue(sut.isNumberIncreasing().test(pw2));
        assertFalse(sut.isNumberIncreasing().test(pw3));
        assertFalse(sut.isNumberIncreasing().test(pw4));
    }

    @Test
    public void test_exist_pair() {
        String pw1 = "122456";
        String pw2 = "122256";
        String pw3 = "111111";
        String pw4 = "165450";
        assertTrue(sut.existPair().test(pw1));
        assertTrue(sut.existPair().test(pw2));
        assertTrue(sut.existPair().test(pw3));
        assertFalse(sut.existPair().test(pw4));
    }

    @Test
    public void test_exist_double() {
        String pw1 = "122456";
        String pw2 = "112356";
        String pw3 = "111356";
        String pw4 = "111111";
        String pw5 = "165450";
        String pw6 = "123555";
        String pw7 = "123455";
        String pw8 = "111122";
        String pw9 = "577888";
        assertTrue(sut.existDouble().test(pw1));
        assertTrue(sut.existDouble().test(pw2));
        assertFalse(sut.existDouble().test(pw3));
        assertFalse(sut.existDouble().test(pw4));
        assertFalse(sut.existDouble().test(pw5));
        assertFalse(sut.existDouble().test(pw6));
        assertTrue(sut.existDouble().test(pw7));
        assertTrue(sut.existDouble().test(pw8));
        assertTrue(sut.existDouble().test(pw9));
    }

    @Test
    public void test_get_valid_passwords() {
        assertEquals(710, sut.getValidPasswords(245182, 790572).size());
    }

}
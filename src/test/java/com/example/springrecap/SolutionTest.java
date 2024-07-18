package com.example.springrecap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SolutionTest {

    @Test
    public void testSolution_AllAboveThreshold() {
        Solution solution = new Solution();
        int[] scoville = {6 , 10, 12, 15, 18};
        int K = 7;
        assertEquals(0, solution.solution(scoville, K), "No mixes needed, all elements above threshold");
    }

    @Test
    public void testSolution_SingleMixRequired() {
        Solution solution = new Solution();
        int[] scoville = {1, 2, 3, 10, 12, 15, 18};
        int K = 11;
        assertEquals(3, solution.solution(scoville, K), "Two mixes required to reach threshold");
    }

    @Test
    public void testSolution_ImpossibleToReachThreshold() {
        Solution solution = new Solution();
        int[] scoville = {0, 0, 0, 1};
        int K = 12;
        assertEquals(-1, solution.solution(scoville, K), "Impossible to reach threshold");
    }
}
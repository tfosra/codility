/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tfosra.cgi;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author s.rapaya
 */
public class Main {
    
    public static void main(String[] args) {
//        System.out.println(solution3(123, 67890));
//        int[][] A = new int[5][3];
//        A[0][0] = 2;    A[0][1] = 7;    A[0][2] =  5;
//        A[1][0] = 3;    A[1][1] = 1;    A[1][2] =  1;
//        A[2][0] = 2;    A[2][1] = 1;    A[2][2] = -7;
//        A[3][0] = 0;    A[3][1] = 2;    A[3][2] =  1;
//        A[4][0] = 1;    A[4][1] = 6;    A[4][2] =  8;
//        System.out.println(solution1(A));
        String str = "122151121"; // Should give 22
        System.out.println(nbSegment(str));
    }
    
    public static int nbSegment(String str) {
        Map<Character, Integer> leftCount = new HashMap<>();
        Map<Character, Integer> rightCount = new HashMap<>();
        long hits = 0;
        int size;
        
        // Initialize rightCount
        for (char c : str.toCharArray()) {
            updateMapValue(rightCount, c, 1);
        }
        
        while (!str.isEmpty()) {
            size = str.length();
            
            // Basic case
            if (size == 1) {
                hits++;
                break;
            }
            
            leftCount.clear();
            
            for (int i = 0; i < size; i++) {
                // Test left segment
                if (buySegment(leftCount.values()))
                    hits++;
                
                // Test right segment
                if (buySegment(rightCount.values()))
                    hits++;
                
                // Update left counts
                updateMapValue(leftCount, str.charAt(i), 1);
                
                // Update right counts
                updateMapValue(rightCount, str.charAt(i), -1);
            }
            
            updateMapValue(leftCount, str.charAt(0), -1);
            updateMapValue(leftCount, str.charAt(size - 1), -1);
            rightCount = new HashMap<>(leftCount);
            
            
            // Remove the first and last nodes
            str = str.substring(1, size - 1);
        }
        return (int)(hits % 1000000007);
    }
    
    public static void updateMapValue(Map<Character, Integer> map, Character c, int count) {
        map.merge(c, count, (oldV, newV) -> oldV + newV);
    }
    
    public static boolean buySegment(Collection<Integer> counts) {
        // We can buy a segment only if there is at most one odd occurence of 
        // a color
        return !counts.isEmpty() && 
                counts.stream().filter(i -> i % 2 == 1).count() <= 1;
    }
    
    public static int solution1(int[][] A) {
        int N = A.length;
        int M = A[0].length;
        int nbRowEq = 0;
        int nbColEq = 0;
        
        // Look for row equilibriums
        long[] increasingRowSum = new long[N];
        long[] decreasingRowSum = new long[N];
        for (int i = 1; i < N; i++) {
            increasingRowSum[i] = increasingRowSum[i - 1] + rowSum(A, i - 1);
            decreasingRowSum[N - i - 1] = decreasingRowSum[N - i] + rowSum(A, N - i);
        }
        for (int i = 0; i < N; i++) {
            if (increasingRowSum[i] == decreasingRowSum[i])
                nbRowEq++;
        }
        
        // Performance optimization
        if (nbRowEq == 0) {
            return 0;
        }
        
        // Look for column equilibriums
        long[] increasingColSum = new long[M];
        long[] decreasingColSum = new long[M];
        for (int i = 1; i < M; i++) {
            increasingColSum[i] = increasingColSum[i - 1] + colSum(A, i - 1);
            decreasingColSum[M - i - 1] = decreasingColSum[M - i] + colSum(A, M - i);
        }
        
        for (int i = 0; i < M; i++) {
            if (increasingColSum[i] == decreasingColSum[i])
                nbColEq++;
        }
        
        // Nb of equilibriums = nbRowEq * nbColEq;
        return nbRowEq * nbColEq;
    }
    
    public static long rowSum(int[][] A, int row) {
        return Arrays.stream(A[row]).asLongStream().sum();
    }
    
    public static long colSum(int[][] A, int col) {
        long sum = 0;
        for (int[] row : A) {
            sum += row[col];
        }
        return sum;
    }
    
    
    public static int solution3(int A, int B) {
        int ceiling = 100000000;
        String strA = Integer.toString(A);
        String strB = Integer.toString(B);
        int lengthA = strA.length();
        int lengthB = strB.length();
        int minLength = Math.min(lengthA, lengthB);
        StringBuilder builder = new StringBuilder(minLength * 2);
        
        for (int i = 0; i < minLength; i++) {
            builder.append(strA.charAt(i));
            builder.append(strB.charAt(i));
        }
        for (int i = minLength; i < lengthA; i++) {
            builder.append(strA.charAt(i));
        }
        for (int i = minLength; i < lengthB; i++) {
            builder.append(strB.charAt(i));
        }
        Long l = Long.parseLong(builder.toString());
        if (l > ceiling) {
            return -1;
        }
        return l.intValue();
    }
}

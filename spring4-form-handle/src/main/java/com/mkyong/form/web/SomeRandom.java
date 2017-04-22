package com.mkyong.form.web;

import java.util.Scanner;

public class SomeRandom {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        String s = in.next();
        long n = in.nextLong();
        int n1=s.length();
        long c[]=new long[n1+1];
        for(int i=1;i<=n1;i++){
            if(s.charAt(i-1)=='a')c[i]=c[i-1]+1;
            else c[i]=c[i-1];
        }
        long q=n/n1,r=n%n1;
        long res=q*c[n1]+c[(int) r];
        System.out.println(res);
    }

}

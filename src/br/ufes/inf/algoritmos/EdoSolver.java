package br.ufes.inf.algoritmos;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class EdoSolver {
	public static void main(String[] args) throws IOException {
		

		
		System.out.println("Digite uma opção:");
		System.out.println("1 - RESOLVER o problema 1");
		System.out.println("2 - RESOLVER o problema 2");
		System.out.println("3 - RESOLVER o problema 3");
		System.out.println("4 - Sair");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String a = br.readLine();
		
		System.out.println(a);
		
	}
}

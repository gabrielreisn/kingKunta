package br.ufes.inf.algoritmos;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class EdoSolver {
		
	public static void main(String[] args) throws IOException {
			
		System.out.println("Digite uma opção:");
		System.out.println("1 - RESOLVER o problema 1");
		System.out.println("2 - RESOLVER o problema 2");
		System.out.println("3 - RESOLVER o problema 3");
		System.out.println("4 - Sair");
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		int chosenOption = Integer.parseInt(bufferedReader.readLine());

		bufferedReader.close();
		int m;
		int ya;
		int yb;
		
		switch (chosenOption) {
		
		case 1:
			break;
			//Chamar método para resolução do problema 1
		case 2:
			break;
			//Chamar método para resolução do problema 2
		case 3:
			break;
			//Chamar método para resolução do problema 3
		case 4:
			System.out.println("Programa encerrado.");
			break;
		}
		
		
		
		
		
	}
}

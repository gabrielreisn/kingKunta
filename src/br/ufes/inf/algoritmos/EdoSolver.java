package br.ufes.inf.algoritmos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EdoSolver {

	public static void main(String[] args) throws IOException {

		Scanner teclado = new Scanner(System.in);
		int escolha;

		System.out.println("Digite uma opção:");
		System.out.println("1 - RESOLVER o problema 1");
		System.out.println("2 - RESOLVER o problema 2");
		System.out.println("3 - RESOLVER o problema 3");
		System.out.println("4 - Sair");
		System.out.print("Escolha:");

		escolha = teclado.nextInt();
		
		if(escolha >=4){
			System.out.println("Fim do programa.");
			System.exit(0);
			
		}
		
		System.out.println("Insira quantos intervalos serão usados");
		int m = teclado.nextInt();
		System.out.println("Insira o valor de xa");
		int a = teclado.nextInt();
		System.out.println("Insira o valor de xb");
		int b = teclado.nextInt();

		double[][] matrizCoef = new double[m][m];											// matrizCoef a ser preenchida e resolvida por gauss
		double h = ( (double)b - (double)a )/m;												// Tamanho do passo em x
		double[] vX = new double[m+1];														// Cria e preenche o vetor dos pontos em x
		vX[0] = a;																			//
		for(int i=1; i<=m ; i++) vX[i] = vX[i-1] + h;										// 
		double[] vB = new double[m];														// Cria e preenche o
		vB[0] = a;																			// vetor dos b

		if(escolha == 3)
		{	
			for(int i=1; i<m ; i++)															// Preenchendo o vetor B
			{																				//
				if(i==(m-1)) vB[i] = 2*(h*h)*r_questao3(vX, i) - (2-h)*2.718281828;			//
				else vB[i] = 2*(h*h)*r_questao3(vX, i);										//
			}																				//

			for(int i =1; i<m ; i++)
			{ 														
				preenche_diagonais3(matrizCoef,vX,h,i,m);												
			}
			// A partir daqui aplica-se o método de gauss
			double[][] matriz2 = new double[m][m];											//
			modifica_coeficientes(matriz2, matrizCoef, m);									// Guarda os novos coeficientes na matriz2
			//
			double[] vB2 = new double[m];													// Guarda o novo vetor B em vB2
			modifica_vetorB(vB2, vB, matrizCoef, matriz2, m);								//
			//
			double[] vetor_solucao = new double[m];											// Usa vB2 e matriz2 para calcular soluções e
			calcula_solucoes(vetor_solucao, vB2, matriz2, m);								// guarda em vetor_solucao

			for(int i = 0; i<m ; i++){														// Imprime soluções na tela
				if(i==0)
				{
					System.out.format("%.2f", vX[i]);
					System.out.print(";");
					System.out.println("0,00");
				}
				else
				{
					System.out.format("%.2f", vX[i]);
					System.out.print(";");
					System.out.format("%.2f", vetor_solucao[i]);
					System.out.println("");
				}
			}
			System.out.format("%.2f", 1.00);
			System.out.print(";");
			System.out.println("2,72");
			System.out.println("");


			File grafico = new File("grafico.txt");													// Impressão no arquivo
			try {																					//
				FileWriter out = new FileWriter(grafico.getAbsoluteFile());							//
				BufferedWriter buffer = new BufferedWriter(out);									//
				//
				for(int i =0; i<m ; i++){															//
					if(i==0)																		//
					{																			//
						//
						buffer.write(String.format("%.2f", vX[i]));								//
						buffer.write(";");														//
						buffer.write("0,00");													//  Ya
						buffer.write(System.lineSeparator());									//
					}																			//
					else																			//
					{																			//
						buffer.write(String.format("%.2f", vX[i]));								//
						buffer.write(";");														//
						buffer.write(String.format("%.2f", vetor_solucao[i]));					//
						buffer.write(System.lineSeparator());									//
					}
				}

				buffer.write("1,00");																//  Xb
				buffer.write(";");																	//
				buffer.write("2,72");																//  Yb
				buffer.write(System.lineSeparator());

				buffer.close();

			} catch (IOException e) {
				System.out.println("Não foi possível abrir o arquivo");
				e.printStackTrace();
			}
		}																// FIM DO CASO 3

		else if(escolha == 2)
		{
			for(int i=1; i<m ; i++)															// Preenchendo o vetor B
			{																			
				if(i==(m-1)) vB[i] = 2*(h*h)*0 - (2-h)*2.718281828;		
				else vB[i] = 2*(h*h)*0;
			}	
			for(int i =1; i<m ; i++) 													// Aqui a matrizCoef é preenchida
			{																			//
				preenche_diagonais2(matrizCoef,vX,h,i,m);								//
			}

			double[][] matriz2 = new double[m][m];										//
			modifica_coeficientes(matriz2, matrizCoef, m);								// Guarda os novos coeficientes na matriz2
			//
			double[] vB2 = new double[m];												// Guarda o novo vetor B em vB2
			modifica_vetorB(vB2, vB, matrizCoef, matriz2, m);							//
			//
			double[] vetor_solucao = new double[m];										// Usa vB2 e matriz2 para calcular soluções e
			calcula_solucoes(vetor_solucao, vB2, matriz2, m);							// guarda em vetor_solucao


			for(int i = 0; i<m ; i++){														// Imprime soluções na tela
				if(i==0)
				{
					System.out.format("%.2f", vX[i]);
					System.out.print(";");
					System.out.println("???");
				}
				else
				{
					System.out.format("%.2f", vX[i]);
					System.out.print(";");
					System.out.format("%.2f", vetor_solucao[i]);
					System.out.println("");
				}
			}
			System.out.print("1,00");
			System.out.print(";");
			System.out.println("???");
			System.out.println("");

			File grafico = new File("grafico.txt");													// Impressão no arquivo
			try {																					//
				FileWriter out = new FileWriter(grafico.getAbsoluteFile());							//
				BufferedWriter buffer = new BufferedWriter(out);									//
				//
				for(int i =0; i<m ; i++){															//
					if(i==0)																		//
					{																			//
						buffer.write(String.format("%.2f", vX[i]));								// Xa
						buffer.write(";");														//
						buffer.write("Ya");													// Ya
						buffer.write(System.lineSeparator());									//
					}																			//
					else																			//
					{																			//
						buffer.write(String.format("%.2f", vX[i]));								//
						buffer.write(";");														//
						buffer.write(String.format("%.2f", vetor_solucao[i]));					//
						buffer.write(System.lineSeparator());									//
					}
				}

				buffer.write("1,00");																//  Xb
				buffer.write(";");																	//
				buffer.write("Yb");																//  Yb
				buffer.write(System.lineSeparator());

				buffer.close();

			} catch (IOException e) {
				System.out.println("Não foi possível abrir o arquivo");
				e.printStackTrace();
			}

		}


		/*	
		for(int i =1; i<m ; i++){															// Imprime a matrizCoef
			System.out.println("");
			for(int j =1; j<m ; j++) System.out.print(matrizCoef[i][j] + "    ");
			System.out.println(vB[i]);
		}
		 */	
		teclado.close();																			// Fim da main
	}

	// ---------------- funções


	private static void preenche_diagonais3(double[][] matrizCoef, double[] vX, double h, int i, int m) {

		if (i==0)																				// trata a primeira linha
		{
			matrizCoef[0][0] = diagonal3(i, vX, h);
			matrizCoef[0][1] = diagonal_direita3(i,vX, h);
		}
		else if (i==(m-1))																		// trata a última linha
		{
			matrizCoef[m-1][m-1] = diagonal3(i, vX, h);;
			matrizCoef[m-1][m-2] = diagonal_esquerda3(i,vX, h);
		}else
		{
			matrizCoef[i][i] = diagonal3(i, vX, h);;									// preenche o resto da matrizCoef
			matrizCoef[i][i-1] = diagonal_esquerda3(i,vX, h);
			matrizCoef[i][i+1] = diagonal_direita3(i,vX, h);
		}		
	}

	private static void preenche_diagonais2(double[][] matrizCoef, double[] vX, double h, int i, int m) {

		if (i==0)																				// trata a primeira linha
		{
			matrizCoef[0][0] = diagonal2();
			matrizCoef[0][1] = diagonal_direita2();
		}
		else if (i==(m-1))																		// trata a última linha
		{
			matrizCoef[m-1][m-1] = diagonal2();
			matrizCoef[m-1][m-2] = diagonal_esquerda2();
		}else
		{
			matrizCoef[i][i] = diagonal2();												// preenche o resto da matrizCoef
			matrizCoef[i][i-1] = diagonal_esquerda2();
			matrizCoef[i][i+1] = diagonal_direita2();
		}		
	}


	public static void calcula_solucoes(double[] vetor_solucao, double[] vB2, double[][] matriz2, int m){
		for(int i= m-1; i>0 ; i--){
			if(i== (m-1)) vetor_solucao[i] = vB2[i] ;
			else vetor_solucao[i] = vB2[i] - ( matriz2[i][i+1] * vetor_solucao[i+1] );
		}		
	}
	public static void modifica_vetorB(double[] vB2, double[] vB, double[][] matrizCoef,double[][] matriz2, int m) {
		for(int i =1; i<m ; i++){
			if (i==1) vB2[i] = vB[i] / matrizCoef[i][i];
			else vB2[i] = (vB[i] - (vB2[i-1] * matrizCoef[i][i-1])) / ( matrizCoef[i][i] - (matriz2[i-1][i] * matrizCoef[i][i-1]));
		}		
	}
	public static void modifica_coeficientes(double[][] matriz2, double[][] matrizCoef, int m){	
		for(int i =1; i<m-1 ; i++){
			if (i==1) matriz2[i][i+1] = matrizCoef[i][i+1] / matrizCoef[i][i];
			else matriz2[i][i+1] = matrizCoef[i][i+1] / ( matrizCoef[i][i] - ( matriz2[i-1][i] * matrizCoef[i][i-1]));
		}
	}


	static public double p_questao2(){
		return 0;
	}
	static public double p_questao3(double x){
		return -1;
	}

	static public double q_questao2(double x){
		return 0;
	}	
	static public double q_questao3(double x){
		return x;
	}


	static public double r_questao3(double[] vX, int i){
		return Math.pow((double) 2.718281828, vX[i]) *  (Math.pow(vX[i], (double) 2) + 1) ;
	}
	static public double r_questao2(){
		return 0;
	}


	static public double diagonal3(int i, double[] vX, double h){
		return (2 * (h*h) * vX[i]) - 4 ;
	}
	static public double diagonal2(){
		return -4 ;
	}

	static public double diagonal_esquerda3(int i, double[] vX, double h){
		return 2 - (h * -1) ;
	}

	static public double diagonal_esquerda2(){
		return 2;
	}

	static public double diagonal_direita3(int i, double[] vX, double h)	{
		return 2 + (h*-1);
	}

	static public double diagonal_direita2(){
		return 2;
	}




}

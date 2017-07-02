package br.ufes.inf.algoritmos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/*
  
  		Para a questão 1, y(x) = x², p(x) = -1, q(x) = x, então:
  		
   			y" + p(x)y' + q(x)y = x³-2x+2
   			
   			
*/

public class EdoSolver {


	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int escolha;
		
		System.out.println("Digite uma opção:");
		System.out.println("1 - RESOLVER o problema 1");
		System.out.println("2 - RESOLVER o problema 2");
		System.out.println("3 - RESOLVER o problema 3");
		System.out.println("4 - Sair");
		System.out.print("Escolha:");
		
		escolha = teclado.nextInt();
		
		if(escolha >=4 || escolha < 1){
			System.out.println("Problema inválido. Fim do programa.");
			System.exit(0);
			
		}		
		System.out.println("OS DECIMAIS DEVEM SER SEPARADOS POR VIRGULA");
		System.out.println("");
		System.out.println("Insira quantos intervalos serão usados (m) ");				// Nota para compreensão das variáveis:
		int m = teclado.nextInt();														//
		System.out.println("Insira o valor de xa");										// O sistema a ser resolvido é do tipo
		double a = teclado.nextDouble();														//	
		System.out.println("Insira o valor de xb");										//			Ax = b
		double b = teclado.nextDouble();														//
		System.out.println("Insira o valor de ya");										// Onde A é a matriz dos coeficientes
		double ya = teclado.nextDouble();
		System.out.println("Insira o valor de yb");
		double yb = teclado.nextDouble();
		
		
		double[][] matrizCoef = new double[m][m];											// matrizCoef a ser preenchida e resolvida por gauss
		double h = ( (double)b - (double)a )/m;												// Tamanho do passo em x
		double[] vX = new double[m+1];														// Cria e preenche o vetor dos pontos em x
		vX[0] = a;																			//
		for(int i=1; i<=m ; i++) vX[i] = vX[i-1] + h;										// 
		double[] vB = new double[m];														// Cria e preenche o vetor dos b
		vB[0] = a;																			//
		
		if(escolha == 3)
		{	
			for(int i=1; i<m ; i++)															// Preenchendo o vetor B
			{																				// para o problema 3.
				if(i==1) vB[i] = 2*(h*h)*r_questao3(vX[i]) - (2+h)*ya;						// 
				else if(i==(m-1)) vB[i] = 2*(h*h)*r_questao3(vX[i]) - (2-h)*yb;	            //
					 else vB[i] = 2*(h*h)*r_questao3(vX[i]);								//
			}																				//
			
			for(int i =1; i<m ; i++)
			{ 														
				preenche_diagonais3(matrizCoef,vX,h,i,m);									// Forma o sistema tridiagonal a ser resolvido			
			}
																							// A partir daqui aplica-se o método de gauss
			double[][] matriz2 = new double[m][m];											//
			modifica_coeficientes(matriz2, matrizCoef, m);									// Guarda os coeficientes modificados na matriz2
																							//
			double[] vB2 = new double[m];													// Guarda o novo vetor B em vB2
			modifica_vetorB(vB2, vB, matrizCoef, matriz2, m);								//
																							//
			double[] vetor_solucao = new double[m];											// Faz a retrosubstituição e 
			calcula_solucoes(vetor_solucao, vB2, matriz2, m);								// guarda em vetor_solucao
								
			for(int i = 0; i<m ; i++){														// Imprime soluções na tela
				if(i==0)
					{
						System.out.format("%.4f", vX[i]);
						System.out.print(";");
						System.out.format("%.4f", ya);
						System.out.println("");
					}
				else
					{
						System.out.format("%.4f", vX[i]);
						System.out.print(";");
						System.out.format("%.4f", vetor_solucao[i]);
						System.out.println("");
					}
			}
			System.out.format("%.4f", vX[m]);
			System.out.print(";");
			System.out.format("%.4f", yb);
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
							buffer.write(String.format("%.4f", vX[i]));								//
							buffer.write(";");														//
							buffer.write(String.format("%.4f",ya));									//
							buffer.write(System.lineSeparator());									//
						}																			//
					else																			//
						{																			//
							buffer.write(String.format("%.4f", vX[i]));								//
							buffer.write(";");														//
							buffer.write(String.format("%.4f", vetor_solucao[i]));					//
							buffer.write(System.lineSeparator());									//
						}
				}
				
				buffer.write(String.format("%.4f",vX[m]));											// 
				buffer.write(";");																	//
				buffer.write(String.format("%.4f",yb));												//
				buffer.write(System.lineSeparator());
				
				buffer.close();
				
			} catch (IOException e) {
				System.out.println("Não foi possível abrir o arquivo");
				e.printStackTrace();
			}
		}																// FIM DO CASO 3
		
		else if(escolha == 2)
		{
			for(int i=1; i<m ; i++)																	// Preenchendo o vetor B
			{		
				if(i==1) vB[i] = 2*(h*h)*r_questao2() - (2 - h*p_questao2())*ya;
				else if(i==(m-1)) vB[i] = 2*(h*h)*r_questao2() - (2 + h*p_questao2())*yb;						
					 else vB[i] = 2*(h*h)*r_questao2();
			}	
				for(int i =1; i<m ; i++) 															
				{																			
					preenche_diagonais2(matrizCoef,vX,h,i,m);										//matrizCoef é preenchida
				}																					//
																									//
				double[][] matriz2 = new double[m][m];												//
				modifica_coeficientes(matriz2, matrizCoef, m);										// 
																									//
				double[] vB2 = new double[m];														//
				modifica_vetorB(vB2, vB, matrizCoef, matriz2, m);									//
																									//
				double[] vetor_solucao = new double[m];												//
				calcula_solucoes(vetor_solucao, vB2, matriz2, m);									//
					
				
				for(int i = 0; i<m ; i++){															// Imprime soluções na tela
					if(i==0)
						{
							System.out.format("%.4f", vX[i]);
							System.out.print(";");
							System.out.format("%.4f", ya);
							System.out.println("");
						}
					else
						{
							System.out.format("%.4f", vX[i]);
							System.out.print(";");
							System.out.format("%.4f", vetor_solucao[i]);
							System.out.println("");
						}
				}
				System.out.format("%.4f", vX[m]);
				System.out.print(";");
				System.out.format("%.4f", yb);
				System.out.println("");
				
				File grafico = new File("grafico.txt");													// Impressão no arquivo
				try {																					//
					FileWriter out = new FileWriter(grafico.getAbsoluteFile());							//
					BufferedWriter buffer = new BufferedWriter(out);									//
																										//
					for(int i =0; i<m ; i++){															//
						if(i==0)																		//
							{																			//
								buffer.write(String.format("%.4f", vX[i]));								// 
								buffer.write(";");														//
								buffer.write(String.format("%.4f", ya));								// 
								buffer.write(System.lineSeparator());									//
							}																			//
						else																			//
							{																			//
								buffer.write(String.format("%.4f", vX[i]));								//
								buffer.write(";");														//
								buffer.write(String.format("%.4f", vetor_solucao[i]));					//
								buffer.write(System.lineSeparator());									//
							}
					}
					
					buffer.write(String.format("%.4f", vX[m]));											//
					buffer.write(";");																	//
					buffer.write(String.format("%.4f", yb));											//
					buffer.write(System.lineSeparator());
					
					buffer.close();
					
				} catch (IOException e) {
					System.out.println("Não foi possível abrir o arquivo");
					e.printStackTrace();
				}
				
		}
		else if(escolha == 1)
		{
			for(int i=1; i<m ; i++)																	// Preenchendo o vetor B
			{																			
				if(i==1) vB[i] = 2*(h*h)*(r_questao1(vX[i])) - (2- h*p_questao1(vX[i]) )* ya;
				else if(i==(m-1)) vB[i] = 2*(h*h)*(r_questao1(vX[i])) - (2+ h*p_questao1(vX[i]) )* yb ;		
					 else vB[i] = 2*(h*h)*r_questao1(vX[i]);
			}	
				for(int i =1; i<m ; i++) 															// matrizCoef é preenchida
				{																					//
					preenche_diagonais1(matrizCoef,vX,h,i,m);										//
				}
				
				double[][] matriz2 = new double[m][m];										//
				modifica_coeficientes(matriz2, matrizCoef, m);								// 
																							//
				double[] vB2 = new double[m];												// 
				modifica_vetorB(vB2, vB, matrizCoef, matriz2, m);							//
																							//
				double[] vetor_solucao = new double[m];										// 
				calcula_solucoes(vetor_solucao, vB2, matriz2, m);							// 
					
				
				for(int i = 0; i<m ; i++){														// Imprime soluções na tela
					if(i==0)
						{
							System.out.format("%.4f", vX[i]);
							System.out.print(";");
							System.out.println(String.format("%.4f", ya));
						}
					else
						{
							System.out.format("%.4f", vX[i]);
							System.out.print(";");
							System.out.format("%.4f", vetor_solucao[i]);
							System.out.println("");
						}
				}
				System.out.print(String.format("%.4f", vX[m]));
				System.out.print(";");
				System.out.println(String.format("%.4f", yb));
				System.out.println("");
				
				File grafico = new File("grafico.txt");													// Impressão no arquivo
				try {																					//
					FileWriter out = new FileWriter(grafico.getAbsoluteFile());							//
					BufferedWriter buffer = new BufferedWriter(out);									//
																										//
					for(int i =0; i<m ; i++){															//
						if(i==0)																		//
							{																			//
								buffer.write(String.format("%.4f", vX[i]));								//
								buffer.write(";");														//
								buffer.write(String.format("%.4f", ya));														//
								buffer.write(System.lineSeparator());									//
							}																			//
						else																			//
							{																			//
								buffer.write(String.format("%.4f", vX[i]));								//
								buffer.write(";");														//
								buffer.write(String.format("%.4f", vetor_solucao[i]));					//
								buffer.write(System.lineSeparator());									//
							}
					}
					
					buffer.write(String.format("%.4f", vX[m]));														 
					buffer.write(";");																	
					buffer.write(String.format("%.4f", yb));																    
					buffer.write(System.lineSeparator());
					
					buffer.close();
					
				} catch (IOException e) {
					System.out.println("Não foi possível abrir o arquivo");
					e.printStackTrace();
				}
				
		}
		
		
teclado.close();														// FIM DA MAIN
}

	// ---------------- funções
	
	
private static void preenche_diagonais3(double[][] matrizCoef, double[] vX, double h, int i, int m) {
	
	if (i==0)																				// trata a primeira linha
	{
		matrizCoef[0][0] = diagonal3(vX[i], h);
		matrizCoef[0][1] = diagonal_direita3(h);
	}
	else if (i==(m-1))																		// trata a última linha
		 {
		 	matrizCoef[m-1][m-1] = diagonal3(vX[i], h);
		 	matrizCoef[m-1][m-2] = diagonal_esquerda3(h);
		 }else
		  {
			 	matrizCoef[i][i] = diagonal3(vX[i], h);;									// preenche o resto da matrizCoef
			 	matrizCoef[i][i-1] = diagonal_esquerda3(h);
			 	matrizCoef[i][i+1] = diagonal_direita3(h);
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

private static void preenche_diagonais1(double[][] matrizCoef, double[] vX, double h, int i, int m) {
	
	if (i==0)																				// trata a primeira linha
	{
		matrizCoef[0][0] = diagonal1(vX[i],h);
		matrizCoef[0][1] = diagonal_direita1(vX[i],h);
	}
	else if (i==(m-1))																		// trata a última linha
		 {
		 	matrizCoef[m-1][m-1] = diagonal1(vX[i],h);
		 	matrizCoef[m-1][m-2] = diagonal_esquerda1(vX[i],h);
		 }else
		  {
			 	matrizCoef[i][i] = diagonal1(vX[i],h);												// preenche o resto da matrizCoef
			 	matrizCoef[i][i-1] = diagonal_esquerda1(vX[i],h);
			 	matrizCoef[i][i+1] = diagonal_direita1(vX[i],h);
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
	static public double p_questao1(double x){
		return -1;
	}
		
	static public double q_questao2(double x){
		return 0;
	}	
	static public double q_questao3(double x){
		return x;
	}
	static public double q_questao1(double x){
		return x+1;
	}	
	
	
	static public double r_questao3(double vX){
		return Math.pow((double) 2.718281828, vX) *  (Math.pow(vX, (double) 2) + 1) ;
	}
	static public double r_questao2(){
		return 0;
	}
	static public double r_questao1(double vX){				
		return (vX*vX*vX) - (2*vX) +2;
	}
	
	static public double diagonal3(double vX, double h){
		return (2 * (h*h) * vX) - 4 ;
	}
	static public double diagonal2(){
		return -4 ;
	}
	static public double diagonal1(double vX, double h){
		return  (2 * (h*h) * vX) - 4;
	}
	
	
	static public double diagonal_esquerda3(double h){
		return 2 - (h * -1) ;
	}	
	static public double diagonal_esquerda2(){
		return 2;
	}	
	static public double diagonal_esquerda1(double vX, double h){
		return 2 - (h * -1) ;
	}
	
	
	static public double diagonal_direita3(double h){
		return 2 + (h*-1);
	}	
	static public double diagonal_direita2(){
		return 2;
	}	
	static public double diagonal_direita1(double vX, double h)	{
		return 2 + (h*-1);
	}	
	
	
}

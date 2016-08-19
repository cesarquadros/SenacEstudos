public class exercicio1 {

	public static void main(String[] args) {
		/*--------------------- Exercicio 1.0-------------------*/
		int[] vetor = { 1, 2, 3, 4 };
		for (int i = 0; i < 4; i++) {
			System.out.print(vetor[i] + " ");
		}
		System.out.println("");

		int[] vetor2 = { 6, 5, 4, 3, 2, 1 };
		for (int i = 0; i < vetor2.length; i++) {
			System.out.print(vetor2[i] + " ");
		}
		System.out.println("");
		int[] vetor3 = { 20, 6, 4, 9, 21, 30 };

		imprimeVetorN(vetor3);
		System.out.println("");
		imprimeVetorN(vetor2);
		System.out.println("");
		imprimeVetorN(vetor);
		System.out.println("");
		
		/*--------------------- Exercicio 1.1-------------------*/
		int[] vetorVazio = new int[10];
		int numeroVetor = 5;
		preencherVetor(vetorVazio, numeroVetor);
		
		imprimeVetorN(vetorVazio);
		
		float[]vetorMedia4 = {5,5,5,5};
		System.out.println("");
		mediaVetorQuatro(vetorMedia4);
		
		float[]vetorMediaN = {1.2f,5.4f,3.5f,5};
		mediaVetor(vetorMediaN);

	}

	static void imprimeVetorN(int vetor[]) {
		for (int i = 0; i < vetor.length; i++) {
			System.out.print(vetor[i] + " ");
		}
	}

	static void preencherVetor(int [] vetorVazio, int numeroVetor){
		for (int i = 0; i < vetorVazio.length; i++) {
			vetorVazio[i]= numeroVetor;
		}		
	}
	
	static void mediaVetor(float vetor[]){
		float soma =0;
		for (int i = 0; i < vetor.length; i++) {
			soma += vetor[i];
		}
		float media = soma / vetor.length;
		
		System.out.println(media);
	}

	static void mediaVetorQuatro(float[]vetor){
		float soma =0;
		for (int i = 0; i < 4; i++) {
			soma += vetor[i];
		}
		float media = soma / vetor.length;
		
		System.out.println(media);
	}
}

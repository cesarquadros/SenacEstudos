
public class ExerciciosAula3 {

	static char[] inverterVetor(char vetor[]){
		char vetorInvertido[] = new char[vetor.length];
		int aux = vetor.length-1;
		
		for (int i = 0; i < vetor.length; i++) {
			vetorInvertido[aux] = vetor[i];
			aux--;
		}		
		return vetorInvertido;
	}
	
	static Integer pesquisarInteiro(int vetor[], int numero){
		
		for (int i = 0; i < vetor.length; i++) {
			if(vetor[i] == numero){
				return i;
			}
		}
		return null;
	}
	
	static int acharMaior(int vetor[]){
		int maior = vetor[1];
		
		for (int i = 0; i < vetor.length; i++) {
			if(vetor[i] > maior){
				maior = vetor[i];
			}
		}
		
		return maior;
	}
	
	static boolean comprarSequencia(int vetorA[], int vetorB[]){
		
		for (int i = 0; i < vetorB.length; i++) {
			for (int j = 0; j < vetorB.length; j++) {
				if(vetorA[i]== vetorB[j] && vetorA[i+1]==vetorB[j+1]){
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {

		// imprimir invertido
		char vetor[] = {'s','b','t','a','b'};		
		for (int i = vetor.length-1; i >=0 ; i--) {
			System.out.print(vetor[i]+" ");
		}		
		System.out.println("");
		
		//iverter vetor
		char vetor1[] = {'z','e','z','o'};		
		char vetorInvertido[] = inverterVetor(vetor1);
		
		for (int i = 0; i < vetorInvertido.length; i++) {
			System.out.print(vetorInvertido[i]+" ");
		}		
		System.out.println("");
		
		//pesquisa inteiro
		int vetorInt[]= {5,7,9,6,4};
		System.out.println("Encontrado: "+pesquisarInteiro(vetorInt, 9));		
		System.out.println("Encontrado: "+pesquisarInteiro(vetorInt, 11));
		
		//retorna o maior numero do vetor
		System.out.println("O número maior é: "+acharMaior(vetorInt));
		
		//comprar se um vetor possui uma sequencia iagual do outro
		
		int vatorA[] = {1,2,3,4,5};
		int vatorB[] = {4,12,6,7,11};
		
		System.out.println("possui sequencia: "+ comprarSequencia(vatorA, vatorB));
		
	}
	
}

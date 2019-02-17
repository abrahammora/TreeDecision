import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainArbolDecision {
	static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
	static ArbolDecision nuevoArbol;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		nuevoArbol = new ArbolDecision();
		construyeArbol();
		
		System.out.println("\nSalida del Arbol de decision");
		System.out.println("===============================");
		nuevoArbol.outputArbolBin();
		
		consultaArbol();

	}
	static void construyeArbol() {
		System.out.println("\nGENERAR ARBOL DE DECISION");
		System.out.println("");
		/*nuevoArbol.crearRaiz(1,"El animal come carne?");
		nuevoArbol.agregarNodoSi(1,2,"El animal tiene rayas?");
		nuevoArbol.agregarNodoNo(1,3,"El animal tiene rayas?");
		nuevoArbol.agregarNodoSi(2,4,"El animal es un tigre");
		nuevoArbol.agregarNodoNo(2,5,"El animal es un leopardo");
		nuevoArbol.agregarNodoSi(3,6,"El animal es una cebra");
		nuevoArbol.agregarNodoNo(3,7,"El animal es un caballo");*/
		nuevoArbol.crearRaiz(1, "¿Presión Aterial Baja?");
		nuevoArbol.agregarNodoNo(1, 2, "¿Presión Arterial Media?");
		nuevoArbol.agregarNodoSi(1, 3, "Tomar Medicamento");
		nuevoArbol.agregarNodoNo(2,	4, "¿Azúcar Baja en Sangre?");
		nuevoArbol.agregarNodoSi(2, 5, "¿Indice de Colesterol Alto?");
		nuevoArbol.agregarNodoNo(4, 6, "Alergía al medicamento");
		nuevoArbol.agregarNodoSi(4, 7, "Tomar Medicamento");
		nuevoArbol.agregarNodoNo(5, 8, "Toma Medicamento");
		nuevoArbol.agregarNodoSi(5, 9, "No Tomar Medicamento");
		nuevoArbol.agregarNodoNo(6, 10, "¿Tiene otras Alergías?");
		nuevoArbol.agregarNodoSi(6, 11, "No Tomar Medicamentos");
		nuevoArbol.agregarNodoNo(10, 12, "Tomar Medicamento");
		nuevoArbol.agregarNodoSi(10, 13, "No Tomar Medicamento");
	}
	static void consultaArbol() throws IOException{
		System.out.println("\nCONSULTAR EL ARLBOL DE DECISION");
		System.out.println("");
		nuevoArbol.consultaArbolBin();
		
		optionToExit();
	}
	static void optionToExit() throws IOException{
		System.out.println("Salir?(enter \'Si\' or \'No\')");
		String answer = teclado.readLine();
		if(answer.equals("Si")) return;
		else {
			if(answer.equals("No")) consultaArbol();
			else {
				System.out.println("ERROR: Debe reponder \'Si\' or \'No\'");
				optionToExit();
			}
		}
	}

}

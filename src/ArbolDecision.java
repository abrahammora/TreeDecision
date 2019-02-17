import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.sound.midi.Synthesizer;

public class ArbolDecision {
	private class ArbolBin {
		private int nodoID;
		private String pregunta = null;
		private ArbolBin nodoSi = null;
		private ArbolBin nodoNo = null;
		
		public ArbolBin(int nuevoNodoID, String nuevaPregunta) {
			nodoID = nuevoNodoID;
			pregunta = nuevaPregunta;
		}
	}
	
	static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
	ArbolBin nodoRaiz = null;
	/*public ArbolDecision() {
		
	}*/
	
	public void crearRaiz(int nuevoNodoID,String nuevaPregunta) {
		nodoRaiz = new ArbolBin(nuevoNodoID,nuevaPregunta);
		System.out.println("Nodo raiz creado " + nuevoNodoID);
	}
	
	public void agregarNodoSi(int nodoExistenteID,int nuevoNodoID,String nuevaPregunta) {
		if(nodoRaiz == null) {
			System.out.println("ERROR: No hay nodo raiz!");
			return;
		}
		
		if(buscarArbolAgregarNodoSi(nodoRaiz,nodoExistenteID,nuevoNodoID,nuevaPregunta)) {
			System.out.println("Nodo agregado " +nuevoNodoID +" en respuesta \'Si\' del nodo " + nodoExistenteID);
		}else System.out.println("Nodo " + nodoExistenteID + " no encontrado");
	}
	
	private boolean buscarArbolAgregarNodoSi(ArbolBin nodoActual,int nodoExistenteID,int nuevoNodoID,String nuevaPregunta) {
		if(nodoActual.nodoID == nodoExistenteID) {
			if(nodoActual.nodoSi == null) nodoActual.nodoSi = new ArbolBin(nuevoNodoID, nuevaPregunta);
			else {
				System.out.println("ADVERTENCIA: Sobreescribio el nodo previo "+ "( id = )"+ nodoActual.nodoSi.nodoID + " ) ligado a la respuesta si del nodo " + nodoExistenteID);
				nodoActual.nodoSi = new ArbolBin(nuevoNodoID, nuevaPregunta);
			}
			return true;
		}else {
			if(nodoActual.nodoSi != null) {
				if(buscarArbolAgregarNodoNo(nodoActual.nodoSi, nodoExistenteID, nuevoNodoID, nuevaPregunta))
					 return true;
				else {
					if(nodoActual.nodoNo != null) {
						return(buscarArbolAgregarNodoSi(nodoActual.nodoNo, nodoExistenteID, nuevoNodoID, nuevaPregunta));
					}else return(false);
						
					
				}
			}
			return (false);
		}
	}
	
	
	/*Agregar nodo no*/
	
	public void agregarNodoNo(int nodoExistenteID,int nuevoNodoID,String nuevaPregunta) {
		if(nodoRaiz == null) {
			System.out.println("ERROR: No hay nodo raiz");
			return;
		}
		if(buscarArbolAgregarNodoNo(nodoRaiz, nodoExistenteID, nuevoNodoID, nuevaPregunta)) {
			System.out.println("Nodo agregado " + nuevoNodoID + " a la respuesta \'No\' del nodo " + nodoExistenteID);			
		} else System.out.println("Nodo "+nodoExistenteID+" no encontrado");
		
	}
	
	///Buscar arbol y agregar nodo no
	private boolean buscarArbolAgregarNodoNo(ArbolBin nodoActual,int nodoExistenteID,int nuevoNodoID,String nuevaPregunta) {
		if(nodoActual.nodoID == nodoExistenteID) {
			if(nodoActual.nodoNo == null) nodoActual.nodoNo = new ArbolBin(nuevoNodoID, nuevaPregunta);
			else {
				System.out.println("ADVERTENCIA: Sobreescribio el nodo previo "+ "( id = )"+ nodoActual.nodoNo.nodoID + " ) ligado a la respuesta si del nodo " + nodoExistenteID);
				nodoActual.nodoNo = new ArbolBin(nuevoNodoID, nuevaPregunta);
			}
			return true;
		}else {
			if(nodoActual.nodoSi != null) {
				if(buscarArbolAgregarNodoSi(nodoActual.nodoSi, nodoExistenteID, nuevoNodoID, nuevaPregunta))
					 return true;
				else {
					if(nodoActual.nodoNo != null) {
						return(buscarArbolAgregarNodoNo(nodoActual.nodoNo, nodoExistenteID, nuevoNodoID, nuevaPregunta));
					}else return(false);
						
					
				}
			}
			return (false);
		}
	}
	
	
	public void consultaArbolBin() throws IOException{
		consultaArbolBin(nodoRaiz);
	}
	
	private void consultaArbolBin(ArbolBin nodoActual) throws IOException {
		if(nodoActual.nodoSi == null) {
			if(nodoActual.nodoNo == null) System.out.println(nodoActual.pregunta);
			else System.out.println("Error: La respuesta \'Si\' de la pregunta \'"+ nodoActual.pregunta +"\' no existe");
			return;
		}
		if(nodoActual.nodoNo == null) {
			System.out.println("Error: La respuesta \'No\' de la pregunta \'"+ nodoActual.pregunta +"\' no existe");
			return;
		}
		
		pidePregunta(nodoActual);
	}
	
	private void pidePregunta(ArbolBin nodoActual) throws IOException{
		System.out.println(nodoActual.pregunta + "(enter \'Si\' or \'No\')");
		String answer = teclado.readLine();
		if(answer.equals("Si")) consultaArbolBin(nodoActual.nodoSi);
		else {
			if(answer.equals("No")) consultaArbolBin(nodoActual.nodoNo);
			else {
				System.out.println("ERROR: Debe responder \'Si\' or \'No\'");
				pidePregunta(nodoActual);
			}
		}
		
	}
	
	public void outputArbolBin() {
		outputArbolBin("1",nodoRaiz);
	}
	private void outputArbolBin(String tag, ArbolBin nodoActual) {
		if(nodoActual == null) return;
		System.out.println("[ " +tag+" ] nodoID = " + nodoActual.nodoID + ", pregunta = " + nodoActual.pregunta );
		outputArbolBin(tag + ".1",nodoActual.nodoSi);
		outputArbolBin(tag + ".2",nodoActual.nodoNo);
	}
}

package application;
import java.util.ArrayList;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Library {
	
	/**
	 * Metodo que suma dos numeros complejos y retorna el nuevo
	 * numero complejo
	 * @param num1 primer numero complejo
	 * @param num2 segundo numero complejo
	 * @return nuevo numero complejo
	 */
	public static NumComplejo suma(NumComplejo num1,NumComplejo num2) {
		double sumParteRe = num1.getReal()+num2.getReal();
		double sumParteIm = num1.getImag()+num2.getImag();
		return new NumComplejo(sumParteRe,sumParteIm);
	}
	
	/**
	 * Metodo que resta dos numeros complejos y retorna el nuevo
	 * numero complejo
	 * @param num1 primer numero complejo
	 * @param num2 segundo numero complejo
	 * @return nuevo numero complejo
	 */
	public static NumComplejo resta(NumComplejo num1,NumComplejo num2) {
		double resParteRe = num1.getReal()-num2.getReal();
		double resParteIm = num1.getImag()-num2.getImag();
		return new NumComplejo(resParteRe,resParteIm);
	}
        /**
	 * Metodo que retorna el conjugado del complejo dado
	 * @param num numero complejo al cual se le va a sacar su conjugado
	 * @return numero conjugado del numero dado
	 */
	public static NumComplejo conjugado(NumComplejo num) {
		NumComplejo c = new NumComplejo(num.getReal(),num.getImag()*-1);
		return c;
	}
        
        /**
	 * Metodo que retorna la conjugada de una matriz dada
	 * @param matr Matriz sacar su conjugada
	 * @return Matriz transpuesta de la dada
	 */
	public static NumComplejo[][] conjugada(NumComplejo[][] matr){
		NumComplejo[][] temp = new NumComplejo [matr.length][matr[0].length];
		for (int i = 0; i < matr.length; i++) { 
		       for (int k = 0; k < matr[0].length; k++) {
		    	   temp[i][k] = conjugado(matr[i][k]);
		       }
		 }
		return temp ;
	}
        /**
	 * Metodo que resta dos vectores de complejos
	 * @param vector1 vector 1 a ser restado
	 * @param vector2 vector 2 a ser restado
	 * @return vector resultante de la resta de dos vectores dados
	 */
	public static NumComplejo[] restaVectores(NumComplejo[] vect1 ,NumComplejo[]vect2 ) {
		if(vect1.length == vect2.length) {
			NumComplejo[] resultado = new NumComplejo [vect1.length];
			for (int c = 0; c<vect1.length ; c++) {
					NumComplejo res = resta(vect1[c],vect2[c]);
					resultado[c] = res;
			}
			return resultado;
		}
		else {
			return null;
		}
	}
        /**
	 * Metodo que retorna el conjugado de un vector
	 * @param vector Vector a operar
	 * @return Vector conjugado del dado
	 */
	public static NumComplejo[] conjugadoVector(NumComplejo[] vector) {
		NumComplejo[] conjugado = new NumComplejo[vector.length];
		for (int i = 0; i < vector.length; i++) {
			conjugado[i] = conjugado(vector[i]);
		}
		return conjugado;
	}
        /**
	 * Metodo que Retorna la norma de un vector de complejos
	 * @param vect Vector de complejos a sacar su norma
	 * @return Norma del vector dado
	 */
	public static double normaVector(NumComplejo[] vect) {
		if (vect.length != 0) {
			double res = 0;
			for (int i=0; i<vect.length;i++) {
				res += Math.pow(vect[i].getReal(),2);
				res += Math.pow(vect[i].getImag(),2);
			}
			double norm = Math.pow(res,0.5);
			return norm;
		}
		else {
			return 0;
		}
	}
	/**
	 * Metodo que multiplica dos matrices dadas
	 * @param matr1 Matriz 1 a multiplicar 
	 * @param matr2 Matriz 2 a multiplicar
	 * @return Matriz resultante de la multiplicacion de las dos dadas, null si no se pueden multiplicar
	 */
	public static NumComplejo[][] productoEntreMatrices(NumComplejo[][] matr1, NumComplejo[][] matr2){
		if(matr1.length == matr2.length && matr1[0].length == matr2.length ) {
			NumComplejo [][] resultado= new NumComplejo [matr1.length][matr2[0].length];
			if (matr1[0].length == matr2.length) {
		        for (int i = 0; i < matr1.length; i++) {
		            for (int j = 0; j < matr2[0].length; j++) {
		                for (int k = 0; k < matr1[0].length; k++) {
		                	if (resultado[i][j] == null) {
		                		NumComplejo num = new NumComplejo(0,0);
		                		resultado[i][j] = suma(num,multiplicacion(matr1[i][k], matr2[k][j]));
		                	}
		                	else { resultado[i][j] = suma(resultado[i][j],multiplicacion(matr1[i][k], matr2[k][j]));}
		                }
		            }
		        }
		    }
			return resultado;
		}
		else return null;
	}
        
	/**
	 * Metodo que multiplica dos numeros complejos y retorna el nuevo
	 * numero complejo
	 * @param num1 primer numero complejo
	 * @param num2 segundo numero complejo
	 * @return nuevo numero complejo
	 */
	public static NumComplejo multiplicacion(NumComplejo num1,NumComplejo num2) {
		NumComplejo respuesta = new NumComplejo(num1.getReal()*num2.getReal()+(num1.getImag()*num2.getImag()*-1),
				num1.getReal()*num2.getImag()+num1.getImag()*num2.getReal());
		
		if (respuesta.getImag()<0) {
			//System.out.println(""+respuesta.getReal()+" "+respuesta.getImag()+"i");
		}
		else if(respuesta.getImag()==0){
			//System.out.println(""+respuesta.getReal());
		}
		else {
			//System.out.println(""+respuesta.getReal()+"+"+respuesta.getImag()+"i");
		}
		
		return respuesta;
	}
	
	/**
	 * Metodo que divide dos numeros complejos y retorna el nuevo
	 * numero complejo
	 * @param num1 primer numero complejo
	 * @param num2 segundo numero complejo
	 * @return nuevo numero complejo
	 */
	public static NumComplejo division(NumComplejo num1,NumComplejo num2) {
		
		NumComplejo conju = num2.getConjugado();
		NumComplejo divisor = Library.multiplicacion(num1,conju);
		NumComplejo dividendo = Library.multiplicacion(num2,conju);
		
		NumComplejo respuesta = new NumComplejo(divisor.getReal()/(dividendo.getReal()+dividendo.getImag()),
				    	        divisor.getImag()/(dividendo.getReal()+dividendo.getImag()));
		if (respuesta.getImag()<0) {
			System.out.println(""+respuesta.getReal()+" "+respuesta.getImag()+"i");
		}
		else if(respuesta.getImag()==0){
			System.out.println(""+respuesta.getReal());
		}
		else {
			System.out.println(""+respuesta.getReal()+"+"+respuesta.getImag()+"i");
		}
		return respuesta;
		
	}
	
	/**
	 * metodo que pasa las cordenadas polares a cartesianas
	 * @param num1 numero complejo
	 * @return un arreglo de doubles con las cordenadas
	 */
	
	public static double [] PolaresACartesianas(double r,double ang) {
		double [] arreglo= new double [2];
		arreglo[0] =r * Math.cos(Math.toRadians(ang) ) ;
		arreglo[1] =r*Math.sin(Math.toRadians(ang));
		
		return arreglo;
	}
	
	/**
	 * metodo que pasa las cordenadas cartesianas a polares
	 * @param num1 numero complejo
	 * @return un arreglo de doubles con las cordenadas
	 */
	
	public static double [] CartesianasAPolar(NumComplejo num1) {
		
		double [] arreglo= new double [2];
		arreglo[0] =num1.modulo() ;
		arreglo[1] =num1.fase();
		if(arreglo[1]<0) {
			arreglo[1] = 180 + arreglo[1];
		}
		
		return arreglo;
	}
	
	/**
	 * metodo resta dos matrices
	 * @param num1 numero complejo
	 * @return un arreglo de doubles con las cordenadas
	 */
	
	public static Matriz sumarMatrices(Matriz matrix1,Matriz matrix2) {
		
		if(matrix1.getI()==matrix2.getI()) {
			Matriz matrxNew = new Matriz(matrix1.getI(), matrix2.getJ());
			for(int x = 0;x<matrxNew.getI();x++) {
				for(int y = 0;y<matrxNew.getJ();y++) {
					matrxNew.addNum(x, y,suma( matrix1.getNum(x, y),matrix2.getNum(x, y)));
				}
			}
			return matrxNew;
		}
		return null;
	}
        
        /**
	 * Metodo que calcula el producto interno de dos matrices 
	 * @param vect1 vector 1 a multiplicar
	 * @param vect2 vector 2 a multiplicar
	 * @return Numero complejo el cual es el producto interno de los matrices
	 */
	public static NumComplejo productoInterno(NumComplejo[] vect1 , NumComplejo[] vect2 ) {
		if(vect1.length == vect2.length) {
			NumComplejo[] conjugado1 = conjugadoVector(vect1);
			NumComplejo acum = new NumComplejo(0,0);
	        for (int i = 0; i < vect1.length; i++) {
	        		acum = suma(acum, multiplicacion(conjugado1[i], vect2[i]));
	        }
	        return acum;
		}
		else {
			return null;
		}
	}
	/**
	 * Metodo que calcula el producto interno de dos matrices 
	 * @param vect1 matriz 1 a multiplicar
	 * @param vect2 matriz 2 a multiplicar
	 * @return Numero complejo el cual es el producto interno de los matrices
	 */
	public static NumComplejo productoInterno(NumComplejo[][] vect1 , NumComplejo[][] vect2 ) {
		if(vect1.length == vect2.length && vect1[0].length == vect2[0].length) {
			NumComplejo[][] conjugado1 = conjugada(vect1);
			NumComplejo acum = new NumComplejo(0,0);
	        for (int i = 0; i < vect1.length; i++) {
	        	for (int j = 0;j  < vect1[0].length; j++) {
	        		acum = suma(acum, multiplicacion(conjugado1[i][j], vect2[i][j]));
	        	}
	        }
	        return acum;
		}
		else {
			return null;
		}
	}
	/**
	 * metodo resta dos matrices
	 * @param num1 numero complejo
	 * @return un arreglo de doubles con las cordenadas
	 */
	public static Matriz restaMatrices(Matriz matrix1,Matriz matrix2) {
		
		if(matrix1.getI()==matrix2.getI()) {
			Matriz matrxNew = new Matriz(matrix1.getI(), matrix2.getJ());
			for(int x = 0;x<matrxNew.getI();x++) {
				for(int y = 0;y<matrxNew.getJ();y++) {
					matrxNew.addNum(x, y,resta( matrix1.getNum(x, y),matrix2.getNum(x, y)));
				}
			}
			return matrxNew;
		}
		return null;
	}
        
	/**
	 * metodo multiplicar dos matrices
	 * @param num1 numero complejo
	 * @return un arreglo de doubles con las cordenadas
	 */
	public static Matriz multiplicacionMatrices(Matriz matrix1,Matriz matrix2) {
		
		if(matrix1.getI()==matrix2.getI()) {
			Matriz matrxNew = new Matriz(matrix1.getI(), matrix2.getJ());
			for(int x = 0;x < matrxNew.getI() ; x++) {
				for(int y = 0;y < matrxNew.getJ() ; y++) {
					ArrayList<NumComplejo> datos = new ArrayList<NumComplejo>();
					for(int i=0;i<matrxNew.getI();i++) {
						NumComplejo dato = multiplicacion(matrix1.getNum(x, i),matrix2.getNum(i, y));
						datos.add(dato);
					}
					NumComplejo suma = new NumComplejo(0,0);
					for(NumComplejo num : datos) {
						suma = suma(num,suma);
					}
					matrxNew.addNum(x, y,suma);
				}
			}
			return matrxNew;
		}
		return null;
	}
	
	
	/**
	 * metodo para sacar la normal de una matriz
	 * @param num1 numero complejo
	 * @return un arreglo de doubles con las cordenadas
	 */
	public static double normalMatrices(Matriz matrix1) {
		double resp = 0;
			for(int x = 0;x < matrix1.getI() ; x++) {
				for(int y = 0;y < matrix1.getJ() ; y++) {
					resp += Math.pow(matrix1.getNum(x, y).getReal(),2);
					resp += Math.pow(matrix1.getNum(x, y).getImag(),2);
				}
			}
			return Math.sqrt(resp);
	}
	
	
	/**
	 * metodo para sacar la normal de una matriz
	 * @param num1 numero complejo
	 * @return un arreglo de doubles con las cordenadas
	 */
	public static double DistanciaEntreMatrices(Matriz matrix1,Matriz matrix2) {
		Matriz resultante = restaMatrices(matrix1, matrix2);
		return normalMatrices(resultante);
	}
        
        /**
	 * Metodo que encuentra el estado siguiente dado un estado inicial
	 * @param matrBool Matriz a ver su comportamiento
	 * @param vect Estado inicial el cual inicia
	 */
	public static NumComplejo[] accionVectorMatriz(NumComplejo[][] matrBool, NumComplejo[] vect) {
		if (vect.length == matrBool[0].length) {
			NumComplejo[] resultado = new NumComplejo[matrBool[0].length];
	        for (int i = 0; i < matrBool.length; i++) {
	        	NumComplejo temp = new NumComplejo(0,0);;
	            for (int j = 0; j < matrBool[0].length; j++) {
	            	temp = suma(temp, multiplicacion(matrBool[i][j], vect[j]));
	            }
	            resultado[i] = temp;
	        }
	        return resultado;
		}
		else {
			return null;
		}
	}
        
        /**
	 * metodo para sacar la normal de una matriz
	 * @param num1 numero complejo
	 * @return un arreglo de doubles con las cordenadas
	 */
	public static Matriz productoTensor(Matriz matrix1,Matriz matrix2) {
		Matriz resultante = new Matriz(matrix1.getI()*matrix2.getI(), matrix2.getJ()*matrix1.getJ());
                int temp = 0;
		for(int i = 0;i<resultante.getI();i++){
                    for(int j = 0;j<resultante.getJ();j++){
                        for(int p = 0;p<matrix1.getI();p++){
                            for(int o = 0;o<matrix1.getJ();o++){
                                for(int l = 0;l<matrix2.getI();l++){
                                    for(int m = 0;m<matrix2.getJ();m++){
                                        System.out.println(i+" i "+j+" j "+l+" l "+m+" m ");
                                        resultante.addNum(i, j,multiplicacion(matrix1.getNum(p,o),matrix2.getNum(l,m)));
                                        temp ++;
                                    }
                                }
                            }
                        }
                    }
                }
                return resultante;
	}
        
        /**
	 * Metodo que encuentra el estado siguiente dado un estado inicial
	 * @param matrBool Matriz a ver su comportamiento
	 * @param vect Estado inicial el cual inicia
	 */
	public static NumComplejo[] accionVectorMatriz(double[][] matrBool, NumComplejo[] vect) {
		if (vect.length == matrBool[0].length) {
			NumComplejo[] resultado = new NumComplejo[matrBool[0].length];
	        for (int i = 0; i < matrBool.length; i++) {
	        	NumComplejo temp = new NumComplejo(0,0);;
	            for (int j = 0; j < matrBool[0].length; j++) {
	            	NumComplejo var = new NumComplejo(matrBool[i][j],0);
	            	temp = suma(temp, multiplicacion(var, vect[j]));
	            }
	            resultado[i] = temp;
	        }
	        return resultado;
		}
		else {
			return null;
		}
	}
        
        /**
	 * Metodo que calcula el estado de las canicas en el numero de clicks dado
	 * @param matrBool Matriz Booleana en la cual se conocen los caminos de las canicas
	 * @param initialState Estado inicial de las canicas 
	 * @param clicks Numero de clicks en los cuales se va a calcular su estado
	 * @return Estado final de las canicas despues de n Clicks
	 */
	public static NumComplejo[] canicasCoeficientesComplejos(double[][] matrBool,NumComplejo[] initialState,int clicks ) {
		NumComplejo[] res ;
			NumComplejo[] temp = initialState;
			for (int i = 0; i < clicks; i++) { 
				res = accionVectorMatriz(matrBool, temp);
				temp = res;
			}
		return temp;
	}
        
        /**
	 * Metodo que simula el experimento de multiples rendijas
	 * @param matr Matriz de probabilidades para que pase por dicho camino 
	 * @param numClicks numero de clicks que se quiere hacer la simulacion
	 * @param target Numero de objetivos a los cuales le daran las balas
	 * * @param state estado inicial de el experimento
	 * @return Matriz de probabilidades resultante despues de el numero de clicks dados
	 */
	public static NumComplejo[] multiplesRendijas(double[][] matr,int numClicks,int target,NumComplejo[] state){
		//if() {
		double[][] temp = matr;
		for (int i = 0; i < target; i++) { 
			temp = multiplicacionEntreMatricesDeProbabilidades(temp, matr);
		}
		NumComplejo[] result = state;
		for (int i = 0; i < numClicks; i++) {
			result = accionVectorMatriz(temp,result);
		}
		return result;
	}
        
        /**
	 * Metodo que multiplica dos matrices de probabilidades dadas
	 * @param matr1 Matriz 1 a multiplicar 
	 * @param matr2 Matriz 2 a multiplicar 
	 * @return Matriz resultante de la multiplicacion de las dos dadas
	 */
	private static double[][] multiplicacionEntreMatricesDeProbabilidades(double[][] matr1,double[][] matr2){
		double [][] resultado= new double [matr1.length][matr2[0].length];
		if (matr1[0].length == matr2.length) {
	        for (int i = 0; i < matr1.length; i++) {
	            for (int j = 0; j < matr2[0].length; j++) {
	                for (int k = 0; k < matr1[0].length; k++) {
	                	if (resultado[i][j] == 0) {
	                		double num = 0;
	                		resultado[i][j] = num+(matr1[i][k]* matr2[k][j]);
	                	}
	                	else { resultado[i][j] = resultado[i][j]+(matr1[i][k]* matr2[k][j]);}
	                }
	            }
	        }
	    }
		return resultado;
	}
        
        /**
	 * Metodo que simula el experimento de multiples rendijas con complejos
	 * @param matr Matriz de probabilidades para que pase por dicho camino 
	 * @param numClicks numero de clicks que se quiere hacer la simulacion
	 * @param target Numero de objetivos a los cuales le daran las balas
	 * @param state estado inicial de el experimento
	 * @return Matriz de probabilidades resultante despues de el numero de clicks dados
	 */
	public static NumComplejo[] multiplesRendijasComplejos(NumComplejo[][] matr,int numClicks,int target,NumComplejo[] state){
		NumComplejo[][] temp = matr;
		for (int i = 0; i < target; i++) { 
			temp = productoEntreMatrices(temp, matr);
		}
		NumComplejo[] result = state;
		for (int i = 0; i < numClicks; i++) {
			result = accionVectorMatriz(temp,result);
		}
		return result;
	}
        
        /**
	 * Metodo que calcula la probabilidad de que una particula se encuentre en la posicion dada
	 * @param cantPosiciones Cantidad de posiciones que va a tener el ket dado
	 * @param posicion Posicion en la cual se calculara la probabilidad de la particula
	 * @param ket Estado inicial de la particula
	 * @return Probabilidad de que la particula este en la posicion dada
	 */
	public static double calcularProbabilidadDeParticulaEnUnaPosicionDeSistemaCuantico(int cantPosiciones, int posicion, NumComplejo[] ket){
		double[] todasProba =calcularProbabilidadDeParticulaEnCadaPosicion(cantPosiciones,ket);
		double prob = todasProba[posicion];
		return prob;
	}
        
        /**
	 * Metodo que calcula la las probabilidades de cada posicion del ket
	 * @param numPosiciones numero de posiciones que tiene el ket
	 * @param ket Estado inicial de la particula
	 * @return arreglo de probabilidades de un ket
	 */
	public static double[] calcularProbabilidadDeParticulaEnCadaPosicion(int numPosiciones, NumComplejo[] ket) {
		double norm = normaVector(ket);
		double[] prob = new double[ket.length];
		for(int i=0; i<ket.length; i++) {
			prob[i] = Math.pow(ket[i].modulo(),2)/Math.pow(norm,2);
		}
		return prob;
		
	}
        
        /**
	 * Metodo que calcula la distancia entre dos kets dados
	 * @param ket1 Primer ket a medir su distancia
	 * @param ket2 Segundo ket al cual se le va a medir la distancia respecto al 1
	 * @return Numero complejo resultante de la distancia de los dos kets dados
	 */
	public static double calcularDistanciaEntreKets(NumComplejo[] ket1,NumComplejo[] ket2) {
		if(ket1.length == ket2.length) {
			NumComplejo[] resta = restaVectores(ket1, ket2);
			NumComplejo amplit = productoInterno(resta, resta);
			double distancia =  Math.pow(amplit.getReal(),0.5);
			return distancia;
		}
		else {return 0;}
	}
        
	/**
	 * Prubas pruebitas personales
	 */
	
	public static void main(String[] args) {
		Matriz matrix1 = new Matriz(2,2);
		matrix1.addNum(0,0,new NumComplejo(2,1));
		matrix1.addNum(0,1,new NumComplejo(3,2));
		matrix1.addNum(1,0,new NumComplejo(1,2));
		matrix1.addNum(1,1,new NumComplejo(4,1));
		
		Matriz matrix2 = new Matriz(2,2);
		matrix2.addNum(0,0,new NumComplejo(-2,1));
		matrix2.addNum(0,1,new NumComplejo(-3,2));
		matrix2.addNum(1,0,new NumComplejo(-1,2));
		matrix2.addNum(1,1,new NumComplejo(-4,1));
		Matriz res = matrix1.adjunta();
		System.out.println(res.getNum(0,1).getImag()+"lok"+matrix1.getNum(0,1).getImag());
	}
        
        
	
	
}

package JAG;

import java.util.Arrays;
import java.util.Random;
import java.util.List;

public class AG_Util{

	private static Random rand= new Random();
	
	//Concatena 2 arreglos en un solo arreglo
	@SuppressWarnings("unchecked")
	public static <T> T[] concatenarArreglos(T[] array1, T[] array2, T[] type){
		Object[] ret= new Object[array1.length+array2.length];
		System.arraycopy(array1,0,ret,0,array1.length);
		System.arraycopy(array2,0,ret,array1.length,array2.length);
		return Arrays.asList(ret).toArray(type);
	}
	
	//Concatena todos los arreglos en un solo arreglo
	public static <T> T[] concatenarArreglos(T[][] multiArray, T[] type){
		T[] ret= multiArray[0];
		for(int i=1;i<multiArray.length;i++){
			ret= concatenarArreglos(ret,multiArray[i],type);
		}
		return ret;
	}
	
	//Divide un arreglo en 2 arreglos divididos en la posicion `pos`
	@SuppressWarnings("unchecked")
	public static <T> T[][] dividirEn(T[] array, int pos, T[][] type){
		Object[][] ret= {Arrays.copyOfRange(array,0,pos),Arrays.copyOfRange(array,pos,array.length)};
		return Arrays.asList(ret).toArray(type);
	}
	
	//Divide un arreglo en 2 arreglos, moviendo `cant` objetos seleccionados aleatoriamente al 2do arreglo
	@SuppressWarnings("unchecked")
	public static <T> T[][] divisorAleatorio(T[] array, int cant, T[][] type){
		barajear(array);
		return dividirEn(array,array.length-cant,type);
	}
	
	public static <T> void barajear(T[] array){
		int i,j;
		T obj;
		for(i=0;i<array.length;i++){
			j= rand.nextInt(array.length);
			obj= array[i];
			array[i]= array[j];
			array[j]= obj;
		}
	}
	
}
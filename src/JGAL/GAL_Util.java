package JGAL;

import java.util.Arrays;
import java.util.Random;
import java.util.List;

/**Group of methods that are usefull for the creation of an Genetic Algorithm*/
public class GAL_Util{

	/**Random object wich can be used inside the others methods as Shuffle.*/
	protected static Random rand= new Random();
	
	/**Concatenate two arrays of the same type and return it as an new array.
	*<p>
	*If the third parameter is big enough to store the return, it will be stored there; otherwise a new array will be created.
	*@param array1 One of the arrays to concatenate.
	*@param array2 The other one of the arrays to concatenate.
	*@param type The class of the copy to be returned.
	*@return The result of concatenating the array1 with the array2 
	*/
	@SuppressWarnings("unchecked")
	public static <T> T[] concatArrays(T[] array1, T[] array2, T[] type){
		Object[] ret= new Object[array1.length+array2.length];
		System.arraycopy(array1,0,ret,0,array1.length);
		System.arraycopy(array2,0,ret,array1.length,array2.length);
		return Arrays.asList(ret).toArray(type);
	}
	
	/**Concatenate many arrays of the same type and return it as an new array.
	*<p>
	*If the second parameter is big enough to store the return, it will be stored there; otherwise a new array will be created.
	*@param multiArray All the arrays that will be concatenated.
	*@param type The class of the copy to be returned.
	*@return The result of concatenating all the arrays in multiArray 
	*/
	public static <T> T[] concatArrays(T[][] multiArray, T[] type){
		if(multiArray.length==0)
			return type;
		T[] ret= multiArray[0];
		for(int i=1;i<multiArray.length;i++){
			ret= concatArrays(ret,multiArray[i],type);
		}
		return ret;
	}
	
	/**Split an array of any type at a position given by the second parameter.
	*<p>
	*If the third parameter is big enough to store the return, it will be stored there; otherwise a new array will be created.
	*@param array The array that will be splitted.
	*@param pos The position where the array will be splitted.
	*@param type The class of the copy to be returned.
	*@return An array of 2 arrays, where the first one has the element from 0 to pos and the second one has the elements from pos to array.length 
	*/
	@SuppressWarnings("unchecked")
	public static <T> T[][] divideAt(T[] array, int pos, T[][] type){
		Object[][] ret= {Arrays.copyOfRange(array,0,pos),Arrays.copyOfRange(array,pos,array.length)};
		return Arrays.asList(ret).toArray(type);
	}
	
	/**Split an array of any type, by selecting randomly a number of elements to be part of the second array and the rest the first array.
	*<p>
	*If the third parameter is big enough to store the return, it will be stored there; otherwise a new array will be created.
	*@param array The array that will get the extraction.
	*@param number The number of elements that will be extracted..
	*@param type The class of the copy to be returned.
	*@return An array of 2 arrays, where the first one has the original array with the extraction and the second one the elements that were extracted 
	*/
	@SuppressWarnings("unchecked")
	public static <T> T[][] extractFrom(T[] array, int number, T[][] type){
		shuffle(array);
		return divideAt(array,array.length-number,type);
	}
	
	/**Orders the first array into the second one by using the third one as the ordening base.
	*@param array The array to be ordered.
	*@param array2 The array where the result will be saved. It must be at least the same size than the first array.
	*@param order An int array where each position indicates the new position into array2. As Example. order[2]= 4 means than the 4th position of array must be in the 2nd position of array2.
	*/
	public static <T> void orderBy(T[] array, T[] array2, int[] order){
		for(int i=0;i<order.length;i++)
			array2[i] = array[order[i]];
	}
	
	/**Shuffle an array
	*@param array The array to be shuffled
	*/
	public static <T> void shuffle(T[] array){
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
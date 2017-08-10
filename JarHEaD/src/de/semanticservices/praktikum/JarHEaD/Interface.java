package de.semanticservices.praktikum.JarHEaD;

import org.openrdf.model.URI;

import org.openrdf.query.QueryEvaluationException;

import com.fluidops.iwb.annotation.CallableFromWidget;

/**
 * Interface Class that contains all \@CallAbleFrom widget methods
 *
 */
public class Interface {

	/**
	 * 	Calls the berechneDistance method and calculates the distance
	 * @param URI of the coordinates of the first municipality
	 * @param URI of the coordinates of the second  municipality
	 * @return String of the distance in Km
	 * @throws QueryEvaluationException 
	 */
	@CallableFromWidget
	public static String distance(URI left,URI right) throws QueryEvaluationException{
		if(Util.debug)System.out.println("test");
		
		return "Der Abstand beträgt "+Util.distanceDouble(left,right)+" Km";
	}
	
	@CallableFromWidget
	public static void setDebug(){
		if(Util.debug){
			Util.debug=false;
		}else{
			Util.debug=true;
		}
	}

}

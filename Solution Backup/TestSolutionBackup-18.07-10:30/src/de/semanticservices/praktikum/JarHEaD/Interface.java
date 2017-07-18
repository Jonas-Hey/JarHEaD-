package de.semanticservices.praktikum.JarHEaD;

import org.openrdf.model.URI;
import org.openrdf.query.QueryEvaluationException;

import com.fluidops.iwb.annotation.CallableFromWidget;

public class Interface {

	/**
	 * 	Takes two Gemeinden and calculates the distance between them
	 * @param URI of Gemeinde
	 * @param URI of Gemeinde
	 * @return String of the distance 
	 * @throws QueryEvaluationException 
	 */
	@CallableFromWidget
	public static String distance(URI left,URI right) throws QueryEvaluationException{
		if(Util.debug)System.out.println("test");
		
		return "Der Abstand beträgt "+Util.distanceDouble(left,right)+" Km";
	}

}

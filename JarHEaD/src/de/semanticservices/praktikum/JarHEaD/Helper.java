package de.semanticservices.praktikum.JarHEaD;
import com.fluidops.iwb.api.EndpointImpl;
import com.fluidops.iwb.api.ReadDataManager;
import com.fluidops.iwb.api.query.QueryBuilder;
import com.fluidops.iwb.util.RDFUtil;
import com.fluidops.iwb.annotation.CallableFromWidget;

import java.lang.*;
import java.util.*;

import org.openrdf.model.Literal;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryException;
import org.openrdf.model.impl.ValueFactoryImpl;


/**
 * Helper Class that contains all Helpers
 *
 */
@SuppressWarnings("unused")
public class Helper {
	/**
	 * Splits a literal to a String
	 * @param literal
	 * @return String
	 */
	public static String literalToString(String literal){
		
		return literal.split("\"")[1];
	}
	
	
	/**
	 * Helper Method for Distance, which converts a String of a coordinate to a Double of the coordinate
	 * @param String 
	 * @return Double
	 */
	public static double  stringToDouble(String o){
        double out;
        char c;
        int indexKomma=0;
        if (Util.debug)System.out.println(o);
                      
       /* for (int y =1;y<o.length();y++){
            c=o.charAt(y);
            if(c=='"'){
                indexEnde=y;
                y=o.length();
            }            
        }**/
        if (o.equals(0)){
        	if (Util.debug)System.out.println("double null");
        	return 0;
        }
        for (int y =0;y<o.length();y++){
            c=o.charAt(y);
            if(c==','){
                indexKomma=y;
                y=o.length();
            }            
        }               
        
        //String rechtsString=o.substring(1,indexKomma)+"."+o.substring(indexKomma+1, indexEnde);
        String rechtsString=o.substring(0,indexKomma)+"."+o.substring(indexKomma+1, o.length());
        out=Double.parseDouble(rechtsString);        
        
        return out;
    }
    
	
	
	
	
	
	/**
	 * Only returns first line, if the result is more than one
	 * @param current
	 * @param SPARQL statement
	 * @param parameter 
	 * @return List<String>
	 * @throws QueryEvaluationException 
	 */
	public static String[] query(URI current, String SPARQL,String parameter,String para2) throws QueryEvaluationException 
	   {	  
		String[] out=new String[2];
		
		ReadDataManager dm = EndpointImpl.api().getDataManager();
		  ValueFactory vf = ValueFactoryImpl.getInstance();
		  // setting URI context for ?? in the query
		  //URI valueContext = vf.createURI(current);
		  if (Util.debug)System.err.println(SPARQL);
		  URI valueContext= current;
		  QueryBuilder<TupleQuery> queryBuilder = QueryBuilder
		    .createTupleQuery(SPARQL).resolveValue(valueContext)
		    .infer(false);
		  TupleQuery query = null;
		  try {
		   query = queryBuilder.build(dm);
		  } catch (MalformedQueryException | RepositoryException e) {
			  if (Util.debug)System.err.println(e);
		   }
		  TupleQueryResult iterator = null;
		  
		  try{
		   iterator = query.evaluate();
		   }catch (QueryEvaluationException e) {
			   if (Util.debug)System.err.println(e);}
				    
		  
	  // while (iterator.hasNext()) {
		  if (iterator.hasNext()){  
			  BindingSet bindingSet = null;
		    try {
		     bindingSet = iterator.next();
		    } catch (QueryEvaluationException e) {
		    	if (Util.debug)System.err.println(e);

		    }
		    //out= bindingSet.getValue("x").toString();
		    //out+=bindingSet.getValue("y").toString();
		    String s =bindingSet.getValue(parameter).toString();
		    String o =bindingSet.getValue(para2).toString();
		    if (!s.isEmpty()){
		    	if (Util.debug)System.out.println(s);
		    	out[0]= s;		    	
		    	}else{
			    	if (Util.debug)System.err.println("Error: URI is null!");
			    }
		    if(!o.isEmpty()){
		    		if (Util.debug)System.out.println(o);
		    	out[1]=o;		    	
		    }else{
		    	if (Util.debug)System.err.println("Error: URI is null!");
		    }
		         }  	  
	  return out;
	 
	 }
	
	
	public static String[] query(URI current, String SPARQL,String parameter) throws QueryEvaluationException 
	   {	  
		String[] out=new String[2];
		
		ReadDataManager dm = EndpointImpl.api().getDataManager();
		  ValueFactory vf = ValueFactoryImpl.getInstance();
		  // setting URI context for ?? in the query
		  //URI valueContext = vf.createURI(current);
		  if (Util.debug)System.err.println(SPARQL);
		  URI valueContext= current;
		  QueryBuilder<TupleQuery> queryBuilder = QueryBuilder
		    .createTupleQuery(SPARQL).resolveValue(valueContext)
		    .infer(false);
		  TupleQuery query = null;
		  try {
		   query = queryBuilder.build(dm);
		  } catch (MalformedQueryException | RepositoryException e) {
			  if (Util.debug)System.err.println(e);
		   }
		  TupleQueryResult iterator = null;
		  
		  try{
		   iterator = query.evaluate();
		   }catch (QueryEvaluationException e) {
			   if (Util.debug)System.err.println(e);}
				    
		  
	  // while (iterator.hasNext()) {
		  if (iterator.hasNext()){  
			  BindingSet bindingSet = null;
		    try {
		     bindingSet = iterator.next();
		    } catch (QueryEvaluationException e) {
		    	if (Util.debug)System.err.println(e);

		    }
		    //out= bindingSet.getValue("x").toString();
		    //out+=bindingSet.getValue("y").toString();
		    String s =bindingSet.getValue(parameter).toString();
		   
		    if (!s.isEmpty()){
		    	if (Util.debug)System.out.println(s);
		    	out[0]= s;		    	
		    	}else{
			    	if (Util.debug)System.err.println("Error: URI is null!");
			    }}
		   
		           	  
	  return out;
	 
	 }
	
	
	
	
	
	
	

	/**
	 * Test method for the distance Provider which gives him some municipalities
	 * @param uri RDFs type 
	 * @returnURI list of RDFs type
	 */
	public static List<URI> getURIs(URI uri) {
		List<URI> uris=new ArrayList<URI>();
		uris.add(RDFUtil.uri("GEMEINDE_Apen"));
		uris.add(RDFUtil.uri("GEMEINDE_Maroth"));
		uris.add(RDFUtil.uri("GEMEINDE_Saxler"));
		uris.add(RDFUtil.uri("GEMEINDE_Maroth"));
		 //List<String> uriStrings=query(RDFUtil.uri("GEMEINDE_Apen"),"Select ?x where {?x rdf:type :GEMEINDE}","x");
		 //for(String s:uriStrings){
		 //System.out.println(s);
		 //uris.add(RDFUtil.uri(s));
		
		 
		return uris;
	}
	
	/**
	 * Calling a helper function to get all URIs of rdfs:type type
	 * @param type rdfs:type 
	 * @return List of URIs of type
	 * @throws QueryEvaluationException 
	 */
	public static List<URI> getNewURIs(URI type,int limit) throws QueryEvaluationException{
		List<URI>uris=new ArrayList<URI>();
		List<URI> uriStrings=getNode(RDFUtil.uri("y"),"Select distinct ?x where {?x rdf:type "+type+"}LIMIT "+limit,"x");
		 for(URI uri:uriStrings){
			 if (Util.debug)System.out.println(uri);
		 uris.add(uri);
		 }
		
		return uris;
	}
	
	/**
	 * Calling a helper function to get all URIs of order type
	 * @param type rdfs:type 
	 * @return List of URIs of type
	 * @throws QueryEvaluationException 
	 */
	public static List<URI> getNewURIs(URI current,String type,String parameter) throws QueryEvaluationException{
		List<URI>uris=new ArrayList<URI>();
		List<URI> uriStrings=getNode(current,type,parameter);
		 for(URI uri:uriStrings){
			 if (Util.debug)System.out.println(uri);
		 uris.add(uri);
		 }
		
		return uris;
	}
	
	
	
	/**
	 * Query for getUris
	 * @param current
	 * @param SPARQL
	 * @param parameter
	 * @return
	 * @throws QueryEvaluationException 
	 */
	public static List<URI> getNode(URI current, String SPARQL,String parameter) throws QueryEvaluationException 
	   {	
		List<URI> out=new ArrayList<URI>();
		 TupleQueryResult iterator=getIterator(current,SPARQL,parameter);
	   //Helpermethod that return iterator
	   
	   while (iterator.hasNext()) {
		    BindingSet bindingSet = null;
		    try {
		     bindingSet = iterator.next();
		    } catch (QueryEvaluationException e) {
		    	if (Util.debug)System.err.println(e);

		    }
		    //out= bindingSet.getValue("x").toString();
		    //out+=bindingSet.getValue("y").toString();
		    List <String> s=new ArrayList<String>();
		    int i= bindingSet.size();
		    if(Util.debug)System.out.println(i);
		    s.add(bindingSet.getValue(parameter).toString());
		   
		    if (!s.isEmpty()){
		    	out.add(RDFUtil.fullUri(s.get(0)));
		    }else{
		    	if (Util.debug)System.err.println("Error: URI is null!");
		    }
		         }
	   
	  
	  return out;
	 
	 }

/**
 * Query for getUris
 * @param current
 * @param SPARQL
 * @param parameter
 * @return
 */		
public static TupleQueryResult getIterator(URI current,String SPARQL,String parameter){
	  	  
	  ReadDataManager dm = EndpointImpl.api().getDataManager();
	  ValueFactory vf = ValueFactoryImpl.getInstance();
	  // setting URI context for ?? in the query
	  //URI valueContext = vf.createURI(current);
	  if (Util.debug)System.err.println(SPARQL);
	  URI valueContext= current;
	  QueryBuilder<TupleQuery> queryBuilder = QueryBuilder
	    .createTupleQuery(SPARQL).resolveValue(valueContext)
	    .infer(false);
	  List<URI> out =new ArrayList<URI>();
	  TupleQuery query = null;
	  try {
	   query = queryBuilder.build(dm);
	  } catch (MalformedQueryException | RepositoryException e) {
		  if (Util.debug)System.err.println(e);
	   }
	  TupleQueryResult iterator = null;
	  
	  try{
	   iterator = query.evaluate();
	   }catch (QueryEvaluationException e) {
		   if (Util.debug)System.err.println(e);}
	  return iterator;
	  
		
}	
	
	public static List<Literal> getLiteral(URI current, String SPARQL,String parameter){
		//getNode, which returns a List of Literals
		
		return null;
	}
	public static List<URI> getDistributor(URI type) throws QueryEvaluationException{
		List<URI>uris=new ArrayList<URI>();
		List<URI> uriStrings=getNode(RDFUtil.uri("y"),"Select * where {?x :distributor ?y}","y");
		 for(URI uri:uriStrings){
			 if (Util.debug)System.out.println(uri);
		 uris.add(uri);
		 }
		
		return uris;
	}
	
	public static List<URI> getNewDistributor(URI type) throws QueryEvaluationException{
		List<URI>uris=new ArrayList<URI>();
		List<URI> uriStrings=getNode(RDFUtil.uri("x"),"Select * where {?x :distributor ?y}","x");
		 for(URI uri:uriStrings){
			 if (Util.debug)System.out.println(uri);
		 uris.add(uri);
		 }
		
		return uris;
	}
	
	public static List<URI> getPlz(URI type) throws QueryEvaluationException{
		List<URI>uris=new ArrayList<URI>();
		List<URI> uriStrings=getNode(null,"Select * where {?x :gemeindename ?y}","x");
		 for(URI uri:uriStrings){
			 if (Util.debug)System.out.println(uri);
		 uris.add(uri);
		 }
		
		return uris;
	}
	
	public static List<URI> getZips(URI type) throws QueryEvaluationException{
		List<URI>uris=new ArrayList<URI>();
		List<URI> uriStrings=getNode(RDFUtil.uri("y"),"Select * where {?x :postleitzahl ?y}","x");
		 for(URI uri:uriStrings){
			 if (Util.debug)System.out.println(uri);
		 uris.add(uri);
		 }
		
		return uris;
	}
	
}

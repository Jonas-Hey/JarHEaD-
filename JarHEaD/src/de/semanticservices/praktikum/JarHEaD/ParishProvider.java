package de.semanticservices.praktikum.JarHEaD;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;

import com.fluidops.iwb.model.ParameterConfigDoc;
import com.fluidops.iwb.model.Vocabulary;
import com.fluidops.iwb.provider.AbstractFlexProvider;
import com.fluidops.iwb.provider.ProviderUtils;
import com.fluidops.iwb.util.RDFUtil;

import de.semanticservices.praktikum.JarHEaD.DistanceProvider.Config;

/**
 * Calculates the total profit within a Parish
 */
@SuppressWarnings("unused")
public class ParishProvider extends AbstractFlexProvider<ParishProvider.Config> {
	
	private static final long serialVersionUID = 684345323098327777L;

	public static class Config implements Serializable {
		private static final long serialVersionUID = -6759601022040845557L;

		// in the provider configuration, the user specifies a directory
		@ParameterConfigDoc(desc = "directory", required = true)
		public String directory;
	}

	@Override
	public Class<? extends Config> getConfigClass() {
		return Config.class;
	}
	

	@Override
	public void gather(final List<Statement> res) throws Exception {
			List<URI> plz = Helper.getPlz(RDFUtil.fullUri(Util.distributor));
			List<URI> distributor=Helper.getDistributor(RDFUtil.fullUri(Util.distributor));
			List<URI> order=new ArrayList<URI>();
			List<URI> zip=new ArrayList<URI>();
			String[] o;
			int i=0;
			String a="";
			String p="";
			String za=null;
			String ta=null;
			String var=null;
			double price=0;
			if(!distributor.get(0).equals(null)){
			za=distributor.get(0).toString();}
			List<URI> j;
						
			for(URI par:plz){
				System.out.println(i+"/"+plz.size());
				for (URI dist:distributor){
				j=Helper.getNewURIs(dist, "Select * where{?? :postleitzahlenbereich ?y}","y");
				if(!za.equals(null)){
				if (!za.equals(dist.toString())){
					if(price!=0){
					var="Gesamtgewinn :"+price;
					res.add(ProviderUtils.createStatement(dist, RDFUtil.fullUri(var), par));
					price=0;
					var=null;}
				}}
				if(!j.equals(null)&&!j.isEmpty()){
				if(!par.equals(null)&&!par.toString().isEmpty())
				
					if(par.equals(j.get(0))&&!j.isEmpty()&&!j.equals(null)&&!j.get(0).toString().isEmpty()){
					order=Helper.getNewURIs(j.get(0),"select ?order where {?? :order ?order}","order");
					if(!order.isEmpty()){
						if(!order.get(0).equals(null)){
					System.out.println(order.get(0));
					for (int x=0;x<order.size();x++){	
						o= Helper.query(order.get(x),Util.srSPARQL,"srp");
						ta=Helper.literalToString(o[0]);
						price=price+Double.parseDouble(ta);
						System.out.println(price);
						
						
					}}}					
					
				}
					if(Util.debug)System.out.println(par+""+j.get(0));}}
				
				
			  za=par.toString();
			  i++;
			  
			}
			
			
			
			/*for (URI distributor:distributoren){
				
				if(Util.debug)System.out.println(distributor+" ja ");
				order=Helper.getNewURIs(distributor,"select ?order where {?? :order ?order}","order");
				zip=Helper.getNewURIs(distributor,"select * where {?? :postleitzahlenbereich ?plz}","plz");
								if(Util.debug)System.out.println(order);
				if(Util.debug)System.out.println(distributor+" "+order.size());
				**/
				/*for (URI ZIPs:zip){					
						if(Util.debug)System.out.println(distributor+" "+ZIPs+" ");
						for(int x=0;x<order.size();x++){
						p=ZIPs.toString();						
						
						if(!a.equals(p)){
							price=0;
						for(URI ord:order){
							
							o=Helper.query(ord,Util.srSPARQL,"srp");
							if(Util.debug)System.out.println(o[0]);
							if(o[0]!=null&&o.equals(null)){
							za=Helper.literalToString(o[0]);
							price=price+Double.parseDouble(za);
							a=ZIPs.toString();
							}							
						}}
						
						String dist="Gesamtgewinn der Gemeinde :"+price+" ";
						for(URI orders:order)
						res.add(ProviderUtils.createStatement(zip, RDFUtil.fullUri(dist), orders));
						
					}}
				*/
				
				/*if(order.size()==1){
					if(Util.debug)System.out.println("Order1");
					o= Helper.query(order.get(0),Util.srSPARQL,"srp");
					za=Helper.literalToString(o[0]);
					price=Double.parseDouble(za);
					System.out.println(price);
				}else{
				for (int x=0;x<order.size();x++){
					o= Helper.query(order.get(x),Util.srSPARQL,"srp");
					System.out.println(o[0]+" O ");
					za=Helper.literalToString(o[0]);
					price=price+Double.parseDouble(za);
					System.out.println(price);
				}	}
				price=Math.floor(price*100)/100;;
				if(order.size()==1){
				res.add(ProviderUtils.createStatement(distributor, RDFUtil.uri("Es liegt eine Bestellungen vor mit einem Gesamtwert von "+price+"€"), order.get(0)));
				}else{for (int x=0;x<order.size();x++){
				res.add(ProviderUtils.createStatement(distributor, RDFUtil.uri("Es liegen "+order.size()+" Bestellungen vor mit einem Gesamtwert von "+price+"€"), order.get(x)));
					}
					}Price=0;}
				**/}
				
				
				
				
				}
						
					
					
					
					
				
				//if(Util.debug)System.out.println(o[0]+" ");
				//res.add(ProviderUtils.createStatement(distributor,(URI) order, distributor));
			
			
			
			//zuerst name+id
			//Gesamt einkommen + alle empfohlenerpreis addiert
			
			
		   		//res.add(ProviderUtils.createStatement( RDFUtil.uri("Abstand unter 10Km")));
		   			
		
		
		//select * where {?x :personenid ?y}
		//select ?id where {?? :id ?id}
		//select ?id where {?? rdfs:label ?id}	
		//select ?preis where {?? :srpempfohlenerpreis ?preis}
		
		   				
		   												
				
				
				// convert the file name to a URI
				//URI fileURI = ProviderUtils.objectToUri("test2");

				// using the URI, type the file as foaf:Document ...
				//res.add(ProviderUtils.createStatement(links, RDF.TYPE,
					//	Vocabulary.FOAF.DOCUMENT));
				
				//res.add(ProviderUtils.createStatement(links, RDFUtil.uri("Abstand"),
						//RDFUtil.literal(distance)));
				
				//Abfrag ob Abstand><10/50/100km> ist wenn ja:
				
				//res.add(ProviderUtils.createStatement(links, RDFUtil.uri("Abstand.10Km"),
				//				rechts));
				
				// ... assign a label ...
				//res.add(ProviderUtils.createLiteralStatement(fileURI,
					//	RDFS.LABEL, "Label"));
				
				
			
		

		// that's all, the triples add to res will automatically added to the
		// repository by the surrounding provider framework
	

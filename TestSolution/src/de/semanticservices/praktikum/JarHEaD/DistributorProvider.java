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
 * A Provider that asks for all municipalities and then calculates the distance between them
 * and writes it in the database
 */
@SuppressWarnings("unused")
public class DistributorProvider extends AbstractFlexProvider<DistributorProvider.Config> {
	
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
			
			List<URI> distributoren = Helper.getDistributor(RDFUtil.fullUri(Util.distributor));
			List<URI> order=new ArrayList<URI>();
			String[] o;
			int i=0;
			double price=0;
			String za;
			for (URI distributor:distributoren){
				
				if(Util.debug)System.out.println(distributor+" ja ");
				order=Helper.getNewURIs(distributor,"select ?orderid where {?? :orderid ?orderid}","orderid");
				if(Util.debug)System.out.println(order);
				if(Util.debug)System.out.println(distributor+" "+order.size());
				if(order.size()==1){
					if(Util.debug)System.out.println("Order1");
					o= Helper.query(order.get(0),Util.srSPARQL,"srpempfohlenerpreis");
					za=Helper.literalToString(o[0]);
					price=Double.parseDouble(za);
					System.out.println(price);
				}else{
				for (int x=0;x<order.size();x++){
					o= Helper.query(order.get(x),Util.srSPARQL,"srpempfohlenerpreis");
					System.out.println(o[0]+" O ");
					za=Helper.literalToString(o[0]);
					price=price+Double.parseDouble(za);
					System.out.println(price);
				}	}
				if(order.size()==1){
				res.add(ProviderUtils.createStatement(distributor, RDFUtil.uri("Es liegt eine Bestellungen vor mit einem Gesamtwert von "+price+"€"), order.get(0)));
				}else{for (int x=0;x<order.size();x++){
				res.add(ProviderUtils.createStatement(distributor, RDFUtil.uri("Es liegen "+order.size()+" Bestellungen vor mit einem Gesamtwert von "+price+"€"), order.get(x)));
					
				}}
				
				
				
				price=0;
				}
						
					
					
					
					
				
				//if(Util.debug)System.out.println(o[0]+" ");
				//res.add(ProviderUtils.createStatement(distributor,(URI) order, distributor));
			}
			
			
			//zuerst name+id
			//Gesamt einkommen + alle empfohlenerpreis addiert
			
			
		   		//res.add(ProviderUtils.createStatement( RDFUtil.uri("Abstand unter 10Km")));
		   			
		
		
		//select * where {?x :personenid ?y}
		//select ?id where {?? :id ?id}
		//select ?id where {?? rdfs:label ?id}	
		//select ?preis where {?? :srpempfohlenerpreis ?preis}
		
		   				
		   			}									
				
				
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
	

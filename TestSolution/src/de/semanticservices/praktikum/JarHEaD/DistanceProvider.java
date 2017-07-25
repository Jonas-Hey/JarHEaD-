package de.semanticservices.praktikum.JarHEaD;
import java.io.File;
import java.io.Serializable;
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

/**
 * A Provider that asks for all municipalities and then calculates the distance between them
 * and writes it in the database
 */
@SuppressWarnings("unused")
public class DistanceProvider extends AbstractFlexProvider<DistanceProvider.Config> {
	
	private static final long serialVersionUID = 684345323098327777L;

	public static class Config implements Serializable {
		private static final long serialVersionUID = -6759601022040845557L;

		// in the provider configuration, the user specifies a directory
		@ParameterConfigDoc(desc = "Range", required = true)
		public int range;
	}

	@Override
	public Class<? extends Config> getConfigClass() {
		return Config.class;
	}

	@Override
	public void gather(final List<Statement> res) throws Exception {
				
				//Liste von URI´s die alle Gemeinden der Datenbanken zurückgeben
				List<URI> gemeinden = Helper.getNewURIs(RDFUtil.fullUri(Util.gemeinde));
				int i=0;
				
				//Helper funktion, die aus SPARQLstatement liste von Gemeinde URI´s erstellt /Query umbauen
				//Distance über alle Gemeinden laufen
				for (URI gemeinde:gemeinden){
					if(gemeinde.equals(RDFUtil.fullUri("http://www.fluidops.com/null"))){
						continue;
					}
		   			for (URI vergleich:gemeinden){
		   				i++;
		   				if(vergleich.equals(RDFUtil.fullUri("http://www.fluidops.com/null"))){
							continue;
						}
		   				if(vergleich.equals(gemeinde)){
		   					continue;
		   				}
		   				double distance= Util.distanceDouble(gemeinde,vergleich);
		   				if(Util.debug)System.out.println(gemeinde);
		   				if(distance== -1){
		   					if(Util.debug){System.out.println("distance ist -1");}
		   				continue;}
		   				/*}else if (distance<config.range){		   					
		   					res.add(ProviderUtils.createStatement(gemeinde, RDFUtil.uri("Abstand unter 10Km"),
		   							vergleich));
		   				**/
		   				/*else if (distance<30){
		   					res.add(ProviderUtils.createStatement(gemeinde, RDFUtil.uri("Abstand zwischen 10Km und 30Km"),
		   							vergleich));
		   				}else if (distance<50){res.add(ProviderUtils.createStatement(gemeinde, RDFUtil.uri("Abstand zwischen 30Km und 50Km"),
	   							vergleich));
		   				}else if (distance<100){res.add(ProviderUtils.createStatement(gemeinde, RDFUtil.uri("Abstand zwischen 50Km und 100Km"),
	   							vergleich));
		   				}else{res.add(ProviderUtils.createStatement(gemeinde, RDFUtil.uri("Abstand über 100Km"),
	   		   							vergleich));}
		   					**/
		   				
		   			}
					
				   }
										
				System.out.println(i);
				
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
				
				
			
		}

		// that's all, the triples add to res will automatically added to the
		// repository by the surrounding provider framework
	
}
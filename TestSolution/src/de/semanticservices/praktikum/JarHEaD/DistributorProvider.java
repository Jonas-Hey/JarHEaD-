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
			for (URI distributor:distributoren){	
				if(Util.debug)System.out.println(distributor);
				order=Helper.getNode(distributor,"select ?id where {?? :id ?id}","id");
				if(Util.debug)System.out.println(distributor+" "+order);
			}
			
			
			//zuerst name+id
			//Gesamt einkommen + alle empfohlenerpreis addiert
			
			
		   		//res.add(ProviderUtils.createStatement( RDFUtil.uri("Abstand unter 10Km")));
		   			
		
		
		//select * where {?x :personenid ?y}
		//select ?id where {?? :id ?id}
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
				
				
			
		}

		// that's all, the triples add to res will automatically added to the
		// repository by the surrounding provider framework
	

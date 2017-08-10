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
			List<URI> orders=Helper.getNewDistributor(RDFUtil.fullUri(Util.distributor));
			String[] o;
			int i=0;
						
			String za=null;
			String ta=null;
			String var=null;
			double price=0;
			if(!orders.get(0).equals(null)){
			za=orders.get(0).toString();}
			List<URI> j=new ArrayList<URI>();
						
			for(URI par:plz){
				System.out.println(i+"/"+plz.size());
				for (URI order:orders){
				
				j=Helper.getNewURIs(order, "Select * where{?? :postleitzahl ?y}","y");
				if(Util.debug)System.out.println(j.get(0)+" "+par);
				if(!za.equals(null)){
				if (!za.equals(order.toString())){
					if(price!=0){
					var="Gesamtgewinn :"+price+"€";
					res.add(ProviderUtils.createStatement(RDFUtil.fullUri(za), RDFUtil.fullUri(var), order));
					price=0;
					var=null;}
				}}
				if(!j.equals(null)&&!j.isEmpty()){
				if(!par.equals(null)&&!par.toString().isEmpty())
				
					if(par.equals(j.get(0))&&!j.isEmpty()&&!j.equals(null)&&!j.get(0).toString().isEmpty()){
										
						if(!order.equals(null)){
					System.out.println(order);
						
						o= Helper.query(order,Util.srSPARQL,"srp");
						ta=Helper.literalToString(o[0]);
						price=price+Double.parseDouble(ta);
						System.out.println(price);					
					}}}				
				
				}za=par.toString();
				  i++;
					if(Util.debug)System.out.println(par+""+j.get(0));}
				
						
			  
			  
			}}
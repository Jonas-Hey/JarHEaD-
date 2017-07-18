package com.fluidops.iwb.demo;

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

/**
 * Generic provider skeleton. Can be used as a basis for implementing any type
 * of data provider.
 */
public class DirectoryContentProvider extends AbstractFlexProvider<DirectoryContentProvider.Config> {
	
	private static final long serialVersionUID = 684345323098327777L;

	public static class Config implements Serializable {
		private static final long serialVersionUID = -6759601022040845557L;

		// in the provider configuration, the user specifies a directory
		@ParameterConfigDoc(desc = "directory name", required = true)
		public String directory;
	}

	@Override
	public Class<? extends Config> getConfigClass() {
		return Config.class;
	}

	@Override
	public void gather(final List<Statement> res) throws Exception {
		File f = new File(config.directory);
		if (!f.isDirectory())
			throw new RuntimeException("Not a valid directory");

		String[] files = f.list();
		for (String file : files) {
			File cur = new File(config.directory, file);
			if (cur.isFile()) {
				String fileName = cur.getName();

				// convert the file name to a URI
				URI fileURI = ProviderUtils.objectToUri(fileName);

				// using the URI, type the file as foaf:Document ...
				res.add(ProviderUtils.createStatement(fileURI, RDF.TYPE,
						Vocabulary.FOAF.DOCUMENT));

				// ... assign a label ...
				res.add(ProviderUtils.createLiteralStatement(fileURI,
						RDFS.LABEL, fileName));

				// ... and store the modification date as dcterms:modified
				res.add(ProviderUtils.createLiteralStatement(fileURI,
						Vocabulary.DCTERMS.MODIFIED, cur.lastModified()));
			}
		}

		// that's all, the triples add to res will automatically added to the
		// repository by the surrounding provider framework
	}
}
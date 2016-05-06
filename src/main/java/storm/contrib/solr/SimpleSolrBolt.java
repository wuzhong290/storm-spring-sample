package storm.contrib.solr;

import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
import org.apache.solr.common.SolrInputDocument;

import java.util.Date;

/**
 * A simple implementation of {@link SolrBolt} which attempts to map the input
 * tuple directly to a Solr object.
 *
 * @author Arian Pasquali <me@arianpasquali.com>
 *
 */
public class SimpleSolrBolt extends SolrBolt {

	/**
	 * @param solrAddress The full URL address where Solr is running.
	 */
	public SimpleSolrBolt(String solrAddress) {

		super(solrAddress);
	}

	@Override
	public boolean shouldActOnInput(Tuple input) {
		return true;
	}

	@Override
	public SolrInputDocument getSolrInputDocumentForInput(Tuple input) {
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id",System.currentTimeMillis());
		for (String field : input.getFields()) {
			Object value = input.getValueByField(field);
			if (isValidField(value)) {
				document.addField(field, value);
			}
		}

		return document;
	}
	
	private boolean isValidField(Object value) {
		return value instanceof String
				|| value instanceof Date
				|| value instanceof Integer
				|| value instanceof Float
				|| value instanceof Double
				|| value instanceof Short
				|| value instanceof Long;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) { }
}

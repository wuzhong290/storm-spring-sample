package storm.sample.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/1/12
 */
public class ExclamationBolt extends BaseRichBolt {
    private static final long serialVersionUID = -3086054753171924795L;
    private static final Logger logger = LoggerFactory.getLogger(ExclamationBolt.class);

    OutputCollector _collector;

    public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
        _collector = collector;
    }

    public void execute(Tuple tuple) {
        logger.info(tuple.getString(0));//日志输出到apache-storm-0.9.3\logs\worker-*.log
        _collector.emit(tuple, new Values(tuple.getString(0) + "!!!"));
        _collector.ack(tuple);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}

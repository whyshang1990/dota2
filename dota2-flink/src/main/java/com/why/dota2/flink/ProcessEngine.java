package com.why.dota2.flink;

import com.why.dota2.boot.constant.FlinkConsts;
import com.why.dota2.boot.constant.KafkaConsts;
import com.why.dota2.boot.dto.MatchHistoryDTO;
import com.why.dota2.boot.util.JsonUtils;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Flink程序的五个部分：
 * 1 获取执行环境
 * 2 载入数据
 * 3 对数据进行处理/转换
 * 4 设置数据输出方式
 * 5 启动程序，开始执行
 */
@Component
public class ProcessEngine {
    private final static String KAFKA_HOST = "localhost:9092";

    private static StreamExecutionEnvironment env;

    private void init() {
        // 设置环境
        env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.getConfig().setAutoWatermarkInterval(FlinkConsts.WATER_PERIODIC);
    }

    /**
     * 创建数据源，载入数据
     * 在flink环境中，创建Kafka消费者数据源，并返回
     */
    private DataStreamSource<String> createDataSource() {
        // 获取数据-数据源
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", KAFKA_HOST);
        properties.setProperty("group.id", "match-history-flink");
        return env.addSource(new FlinkKafkaConsumer<>(KafkaConsts.STEAM_MATCH_HISTORY_TOPIC, new SimpleStringSchema(), properties)
                .setStartFromGroupOffsets());
    }

    public void runJob() throws Exception{
        // log.info("开始启动flink任务");
        init();
        DataStreamSource<String> matchHistorySource = this.createDataSource();
        // 对数据流进行简单的处理，将jsonStr转换为对象
        DataStream<MatchHistoryDTO> matchHistoryDTODataStream = matchHistorySource.map(
                (MapFunction<String, MatchHistoryDTO>) jsonStr -> {
                    // log.info("consumer print: {}", jsonStr);
                    return JsonUtils.toObject(jsonStr, MatchHistoryDTO.class);
                }
        );
        matchHistoryDTODataStream.print();

        // log.info("开始运行flink任务！！！");
        /** 懒加载，执行处理程序 */
        env.execute("Socket Window WordCount");
    }
}

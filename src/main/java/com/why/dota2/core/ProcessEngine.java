package com.why.dota2.core;

import com.why.dota2.constant.FlinkConsts;
import com.why.dota2.constant.KafkaConsts;
import com.why.dota2.dto.MatchHistoryDTO;
import com.why.dota2.util.JsonUtils;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
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
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String kafkaHost;

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
        properties.setProperty("bootstrap.servers", kafkaHost);
        properties.setProperty("group.id", "match_history_flink");
        return env.addSource(new FlinkKafkaConsumer<>(KafkaConsts.STEAM_MATCH_HISTORY, new SimpleStringSchema(), properties)
                .setStartFromGroupOffsets());
    }

    public void runFlinkJob() throws Exception {
        init();
        DataStreamSource<String> matchHistorySource = this.createDataSource();
        // 对数据流进行简单的处理，将jsonStr转换为对象
        DataStream<MatchHistoryDTO> matchHistoryDTODataStream = matchHistorySource.map(
                (MapFunction<String, MatchHistoryDTO>) jsonStr -> JsonUtils.toObject(jsonStr, MatchHistoryDTO.class)
        );
        matchHistoryDTODataStream.print();

        /** 懒加载，执行处理程序 */
        env.execute("Socket Window WordCount");
    }
}

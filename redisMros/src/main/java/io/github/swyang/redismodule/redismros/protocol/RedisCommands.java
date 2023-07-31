package io.github.swyang.redismodule.redismros.protocol;

import io.github.swyang.redismodule.redismros.model.ChunksData;
import io.github.swyang.redismodule.redismros.model.MrosInfo;
import io.github.swyang.redismodule.redismros.protocol.deocder.ChunksDecoder;
import io.github.swyang.redismodule.redismros.protocol.deocder.MrosDecoder;
import org.redisson.client.protocol.RedisCommand;
import org.redisson.client.protocol.convertor.BooleanReplayConvertor;
import org.redisson.client.protocol.convertor.DoubleReplayConvertor;
import org.redisson.client.protocol.decoder.ListMultiDecoder2;
import org.redisson.client.protocol.decoder.ObjectListReplayDecoder;

import java.util.List;

public interface RedisCommands {

    RedisCommand<Boolean> MROS_RESERVE = new RedisCommand<>("MROS.RESERVE", new BooleanReplayConvertor());
    RedisCommand<Boolean> MROS_ADD = new RedisCommand<>("MROS.ADD", new BooleanReplayConvertor());
    RedisCommand<List<Boolean>> MROS_MADD = new RedisCommand("MROS.MADD", new ObjectListReplayDecoder<Boolean>(), new BooleanReplayConvertor());
    RedisCommand<List<Boolean>> MROS_INSERT = new RedisCommand("MROS.INSERT", new ObjectListReplayDecoder<Boolean>(), new BooleanReplayConvertor());


    RedisCommand<Double> MROS_JACCARD = new RedisCommand("MROS.JACCARD", new DoubleReplayConvertor());

    RedisCommand<ChunksData> MROS_SCANDUMP = new RedisCommand<>("MROS.SCANDUMP", new ListMultiDecoder2(new ChunksDecoder()));
    RedisCommand<Boolean> MROS_LOADCHUNK = new RedisCommand<>("MROS.LOADCHUNK", new BooleanReplayConvertor());
    RedisCommand<MrosInfo> MROS_INFO = new RedisCommand<>("MROS.INFO", new ListMultiDecoder2(new MrosDecoder()));
}



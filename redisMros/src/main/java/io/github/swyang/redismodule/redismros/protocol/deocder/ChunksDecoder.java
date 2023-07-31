package io.github.swyang.redismodule.redismros.protocol.deocder;

import io.github.swyang.redismodule.redismros.model.ChunksData;
import org.redisson.client.handler.State;
import org.redisson.client.protocol.decoder.MultiDecoder;

import java.util.List;

public class ChunksDecoder implements MultiDecoder<ChunksData> {


    @Override
    public ChunksData decode(List<Object> parts, State state) {
        return new ChunksData(
                ((Long) parts.get(0)).intValue(),
                (byte[]) parts.get(1)
        );
    }

}

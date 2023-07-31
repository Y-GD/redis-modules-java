package io.github.swyang.redismodule.redismros.protocol.deocder;

import io.github.swyang.redismodule.redismros.model.MrosInfo;
import org.redisson.client.handler.State;
import org.redisson.client.protocol.decoder.MultiDecoder;

import java.util.List;

public class MrosDecoder implements MultiDecoder<MrosInfo> {

    @Override
    public MrosInfo decode(List<Object> parts, State state) {
        return new MrosInfo(
                ((Long) parts.get(1)).longValue(),
                ((Long) parts.get(3)).intValue(),
                ((Long) parts.get(5)).intValue(),
                ((Long) parts.get(7)).longValue(),
                ((Long) parts.get(9)).longValue()
        );
    }
}

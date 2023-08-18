package io.github.swyang.redismodule.redismros.client;

import io.github.swyang.redismodule.redismros.Mros;
import org.redisson.Redisson;
import org.redisson.client.codec.StringCodec;
import org.redisson.client.protocol.RedisCommands;
import org.redisson.command.CommandAsyncExecutor;
import org.redisson.config.Config;

public class RedisMrosClient extends Redisson {
    public RedisMrosClient(Config config) {
        super(config);
    }


    public Mros getMros(String name) {
        return new Mros(getCommandExecutor(), name);
    }

    public Void flushall() {
        CommandAsyncExecutor commandAsyncExecutor = getCommandExecutor();
        return
                commandAsyncExecutor.get(commandAsyncExecutor.writeAllAsync(RedisCommands.FLUSHALL));
    }
}

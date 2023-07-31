package io.github.swyang.redismodule.redismros;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.github.swyang.redismodule.redismros.model.ChunksData;
import io.github.swyang.redismodule.redismros.model.InsertArgs;
import io.github.swyang.redismodule.redismros.model.MrosInfo;
import io.github.swyang.redismodule.redismros.protocol.KeyWords;
import io.github.swyang.redismodule.redismros.protocol.RedisCommands;
import io.github.swyang.redismodule.redismros.util.ArgsUtil;
import io.github.swyang.redismodule.redismros.util.RAssert;
import org.redisson.RedissonObject;
import org.redisson.api.RFuture;
import org.redisson.client.codec.ByteArrayCodec;
import org.redisson.client.codec.Codec;
import org.redisson.client.codec.StringCodec;
import org.redisson.command.CommandAsyncExecutor;

import java.util.ArrayList;
import java.util.List;

public class Mros extends RedissonObject {

    public Mros(Codec codec, CommandAsyncExecutor commandExecutor, String name) {
        super(codec, commandExecutor, name);
    }

    public Mros(CommandAsyncExecutor commandExecutor, String name) {
        this(commandExecutor.getConnectionManager().getCodec(), commandExecutor, name);
    }

    /**
     * Creates an empty Mros structure with a given initial capacity
     *
     * @param capacity The number of entries you intend to add to the mros
     * @return True if executed correctly, or false reply otherwise
     */
    public boolean create(long capacity) {
        return get(createAsync(capacity));
    }

    /**
     * Async create mros structure
     *
     * @param capacity
     * @return
     */
    public RFuture<Boolean> createAsync(long capacity) {
        return commandExecutor.writeAsync(
                getName(),
                codec,
                RedisCommands.MROS_RESERVE,
                getName(),
                capacity
        );
    }

    /**
     * adds an item to the mros structure, creating the mros structure if it does not exist
     *
     * @param item
     * @return
     */
    public boolean add(String item) {
        return get(addAsync(item));
    }

    /**
     * Async adds an item to the mros structure
     *
     * @param item
     * @return
     */
    public RFuture<Boolean> addAsync(String item) {
        RAssert.notNull(item, "item must not be null");

        return commandExecutor.writeAsync(
                getName(),
                codec,
                RedisCommands.MROS_ADD,
                item
        );
    }

    /**
     * Adds one or more items to the mros structure
     *
     * @param items one or more items to add
     * @return the list of add results
     */
    public List<Boolean> madd(String... items) {
        return get(maddAsync(items));
    }

    /**
     * Async adds one or more items to the mros structure
     *
     * @param items
     * @return
     */
    public RFuture<List<Boolean>> maddAsync(String... items) {
        RAssert.notEmpty(items, "Items must not be empty");

        return commandExecutor.writeAsync(
                getName(),
                codec,
                RedisCommands.MROS_MADD,
                ArgsUtil.append(getName(), items)
        );
    }


    /**
     * add one or more items to the mros structure
     *
     * @param insertArgs
     * @param items
     * @return
     */
    public List<Boolean> insert(InsertArgs insertArgs, String... items) {
        return get(insertAsync(insertArgs, items));
    }

    /**
     * Async add one or more items to mros structure
     *
     * @param insertArgs
     * @param items
     * @return
     */
    public RFuture<List<Boolean>> insertAsync(InsertArgs insertArgs, String[] items) {
        RAssert.notNull(insertArgs, "insertArgs must not be null");
        RAssert.notNull(items, "Items must not be null");

        List<Object> params = new ArrayList<>();
        params.add(getName());
        insertArgs.build(params);
        params.add(KeyWords.ITEMS);

        for (String item : items) {
            params.add(item);
        }

        return commandExecutor.writeAsync(getName(), codec, RedisCommands.MROS_INSERT, params.toArray());
    }

    /**
     * return the information about key
     *
     * @return
     */
    public MrosInfo getInfo() {
        return get(getInfoAsync());
    }

    /**
     * Async function
     *
     * @return
     */
    public RFuture<MrosInfo> getInfoAsync() {
        return commandExecutor.readAsync(
                getName(),
                codec,
                RedisCommands.MROS_INFO,
                getName()
        );
    }


    /**
     * calculate the Jaccard simialrity between two mros set
     */
    public double getJaccard(Mros m) {
        return get(getJaccardAsync(m));
    }

    public RFuture<Double> getJaccardAsync(Mros m) {
        return commandExecutor.readAsync(
                getName(),
                codec,
                RedisCommands.MROS_JACCARD,
                getName(),
                m.getName()
        );
    }

    /**
     * Begins an incremental save of the mros structure.
     *
     * @param iter Iterator value. This is either 0, or the iterator from a previous invocation of this command
     * @return The iterator-data pair
     */
    public ChunksData scanDump(int iter) {
        return get(scanDumpAsync(iter));
    }

    public RFuture<ChunksData> scanDumpAsync(int iter) {
        return commandExecutor.readAsync(getName(), ByteArrayCodec.INSTANCE, RedisCommands.MROS_SCANDUMP, getName(), iter);
    }

    /**
     * Restores a mros structure previously saved using SCANDUMP
     *
     * @param chunk Data chunk
     * @return True if executed correctly, or false reply otherwise
     */
    public boolean loadChunk(ChunksData chunk) {
        return get(loadChunkAsync(chunk));
    }

    public RFuture<Boolean> loadChunkAsync(ChunksData chunk) {
        return commandExecutor.writeAsync(getName(), StringCodec.INSTANCE, RedisCommands.MROS_LOADCHUNK, getName(), chunk.getIter(), chunk.getData());
    }

}

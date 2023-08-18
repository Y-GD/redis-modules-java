package io.github.swyang.redismodule.redismros;

import io.github.swyang.redismodule.redismros.client.RedisMrosClient;
import io.github.swyang.redismodule.redismros.model.InsertArgs;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RBitSet;
import org.redisson.api.RFuture;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MrosTest {

    CountDownLatch countDownLatch = new CountDownLatch(1);

    @Test
    public void SentinelTest() throws InterruptedException {
        Config config = new Config();

//        config.useSentinelServers()
//                .setMasterName("mymaster")
//                .addSentinelAddress("redis://192.168.10.131:26379", "redis://192.168.10.131:26380")
//                .addSentinelAddress("redis://192.168.10.131:26381");

        config.useSingleServer().setAddress("redis://192.168.10.131:6379");
        config.setCodec(new StringCodec());


        RedisMrosClient client = new RedisMrosClient(config);
        Mros m1 = client.getMros("m1");
        Mros m2 = client.getMros("m2");


        for (int item = 1; item <= 10000000; item++) {
            if (item <= 5000000) {
                m1.add(String.valueOf(item));
            }
            m2.add(String.valueOf(item));
        }


        double estimate_jaccard = m1.getJaccard(m2);

    }


}

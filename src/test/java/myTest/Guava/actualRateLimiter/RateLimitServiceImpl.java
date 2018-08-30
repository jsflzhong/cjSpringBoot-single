package myTest.Guava.actualRateLimiter;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

/**
 * Created by Jian.Cui on 2018/8/30.
 * 限流服务的封装类
 */
@Service
public class RateLimitServiceImpl implements RateLimitService{

    //每秒只向令牌桶中注入5个令牌
    RateLimiter rateLimiter = RateLimiter.create(5.0);

    /**
     * 获取令牌
     * @return true表示获取到许可，反之则是false
     */
    @Override
    public boolean tryAcquire() {
        //true表示获取到许可，反之则是false
        return rateLimiter.tryAcquire();
    }
}

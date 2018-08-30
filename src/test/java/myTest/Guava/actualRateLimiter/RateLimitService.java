package myTest.Guava.actualRateLimiter;

/**
 * Created by Jian.Cui on 2018/8/30.
 */
public interface RateLimitService {
    boolean tryAcquire();
}

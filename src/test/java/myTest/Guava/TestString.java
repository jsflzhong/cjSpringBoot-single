package myTest.Guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.*;

import java.util.List;
import java.util.Map;

/**
 * Created by jsflz on 2018/8/23.
 *
 * @author cj
 */
public class TestString {

    public static void Joiner_doArrayOrList() {
        List<String> list = Lists.newArrayList("test1", "test2", "test3", null, "test5");

        String join = Joiner.on(",").skipNulls().join(list);
        System.out.println(join);//test1,test2,test3,test5
    }

    /**
     * 把map集合转换为特定规则的字符串
     *
     * @author cj
     */
    public static String Joiner_doMap() {
        ImmutableMap<Object, Object> map = ImmutableMap.
                of("test1", "value1", "test2", "value2", "test3", "value3");

        String join = Joiner.on(",").withKeyValueSeparator("|").join(map);
        System.out.println(join); //test1|value1,test2|value2,test3|value3
        return join;
    }

    public static String Splitter_doString() {
        String a = " a,,  b , c ,  ";
        Iterable<String> split = Splitter.on("|").trimResults().omitEmptyStrings().split(a);
        System.out.println(split);//[a,,  b , c ,]
        return split.toString();
    }

    public static void Maps_differentMap() {
        ImmutableMap<String, String> leftMap = ImmutableMap.of("k1", "v1", "k2", "v2");
        ImmutableMap<String, String> rightMap = ImmutableMap.of("k2", "v2", "k3", "v3");
        MapDifference<String, String> diff = Maps.difference(leftMap, rightMap);

        // 获得只在左集合有的部分(右集也有的元素不拿)
        Map<String, String> leftOnly = diff.entriesOnlyOnLeft();
        // 获得只在右集合有的部分(左集也有的元素不拿)
        Map<String, String> rightOnly = diff.entriesOnlyOnRight();
        // 获得交集部分(两个集合中都共有的元素)
        Map<String, String> intersection = diff.entriesInCommon();

        System.out.println(leftOnly); //{k1=v1}
        System.out.println(rightOnly); //{k3=v3}
        System.out.println(intersection); //{k2=v2}
    }


    public static void main(String[] args) {
        Maps_differentMap();
    }
}

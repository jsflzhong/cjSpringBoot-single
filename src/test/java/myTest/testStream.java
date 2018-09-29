package myTest;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Jian.Cui on 2018/9/29.
 */
public class testStream {

    /**
     * 1.构造流的几种方式.
     */
    public void test1() {

        //方式1
        Stream<String> a = Stream.of("1", "2", "c");

        //方式2
        ArrayList<String> list = Lists.newArrayList("a", "b");
        Stream<String> b = list.stream();

        //方式3
        String[] array = {"a", "b"};
        Stream<String> c = Arrays.stream(array);
    }

    /**
     * 2.把流转换成为其他的数据结构.
     * 一个 Stream 只可以使用一次，下面用了多次,只是为了简洁得看.
     * 切记: 流可以转成很多种容器或String!
     */
    public void test2(Stream<String> stream) {
        //1.把流转成Array
        String[] string = stream.toArray(String[]::new);

        //2.把流转成List
        List<String> list1 = stream.collect(Collectors.toList());

        //3.把流转成Set
        Set<String> collect = stream.collect(Collectors.toSet());

        //4.把流转成String
        String s = stream.toString();
    }


    /**
     * 对流的操作.
     *
     * 分三种大类:
     *
     * 1.Intermediate：
     *  map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
     *
     * 2.Terminal：
     *  forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
     *
     * 3.Short-circuiting：
     *  anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
     */


    /**
     * 3.Map(改变转换)
     * <p>
     * 用来改变容器中的元素.
     * 本例,把list1中的元素都转成大写,返回一个新的list2
     * Tested
     */
    public static void test3() {
        ArrayList<String> list1 = Lists.newArrayList("a", "b");

        //这两行可以链式整合成一行. 这样写只是为了看的明白,第一行返回的是Stream流!
        Stream<String> stream = list1.stream().map(String::toUpperCase);
        List<String> list2 = stream.collect(Collectors.toList()); //这里当然也可以调用上面提到过的api,把list1转成Array,Set,或String.

        System.out.println(list2);
    }

    /**
     * 4.filter(过滤筛选)
     * <p>
     * 用来把容器中的元素,按自己制定的规则,过滤筛选出来符合规则的元素,形成一个新的容器.
     * 本例,筛选出list1中值为"a"的元素
     * Tested
     */
    static void test4() {
        ArrayList<String> list1 = Lists.newArrayList("a", "b", "c", "a");

        List<String> list2 = list1.stream().filter(n -> n.equals("a")).collect(Collectors.toList());

        System.out.println(list2);
    }

    /**
     * 5.forEach(迭代循环)
     * <p>
     * 注意1:forEach 是 terminal 操作，因此它执行后，Stream 的元素就被“消费”掉了,你无法对一个 Stream 进行两次 terminal 运算a
     * <p>
     * 注意2:forEach 提供了并行的方式,可以提高多核系统中的运行效率!(但是迭代的元素的顺序就无法保证了)(自己大力推荐!)
     * <p>
     * Tested
     */
    static void test5() {
        ArrayList<String> list1 = Lists.newArrayList("a", "b", "c", "a");

        //非简化版: 看foreach是白底的,提示是,stream.forEach这种链式方式,可以简化成forEach,即不用写前面的stream().函数里面的部分也可以简化.
        list1.stream().forEach(n -> System.out.println(n));

        //简化版: 这是上面的简化方式. 功能都是一样的.(推荐)s
        list1.forEach(System.out::println);

        System.out.println("###############开始并行处理的方式############3");

        //并行的方式(自己大力推荐!)
        list1.parallelStream().forEach(System.out::println); //打印顺序果然是乱的.但是提高了性能,这一点非常重要.
    }

    /**
     * 6.sorted (排序)
     *
     * 好处是,可以先用stream之后的其他操作API,先把list中的元素执行某种操作, 然后再排序.
     *
     * tested
     */
    void test6() {
        List<Person> list1 = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            Person person = new Person(i, "name" + i);
            list1.add(person);
        }

        List<Person> list2 = list1.stream().limit(3)
                .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                .collect(Collectors.toList());

        list2.forEach(n -> System.out.println(n.getName()));

        /**
         * test log:
         * name1
         * name2
         * name3
         */
    }

    /**
     * Match (判断集合中的元素中是否: 有,或没有,或都是 符合某个条件的元素.
     *
     * 有三种Match:
     * allMatch：Stream 中全部元素符合传入的 predicate，返回 true
     * anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
     * noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
     *
     * 该API可以用来, 判断容器中是否含有某个指定特征的元素. 返回boolean. 也挺好用.
     *
     * tested
     */
     void test7() {

        List<Person> list1 = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            Person person = new Person(i, "name" + i);
            list1.add(person);
        }

        //1.allMatch:
         boolean result1 = list1.stream().allMatch(n -> n.getName().equals("name1")); //false

        //2.anyMatch
         boolean result2 = list1.stream().anyMatch(n -> n.getName().equals("name1")); //true

         //3.noneMatch
         boolean result3 = list1.stream().noneMatch(n -> n.getName().equals("name1")); //false

         System.out.println(result1 + "," + result2 + "," + result3); //false,true,false

     }


    //一会捋SpringBoot的邮件
    public static void main(String[] args) {
        //test3();

        //test4();

        //test5();

        testStream testStream = new testStream();
        //testStream.test6();

        testStream.test7();
    }


    class Person {
        private int id;
        private String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public Person setId(int id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public Person setName(String name) {
            this.name = name;
            return this;
        }
    }
}

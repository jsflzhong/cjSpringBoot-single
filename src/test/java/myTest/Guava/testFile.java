package myTest.Guava;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.*;

import java.io.*;
import java.util.List;

/**
 * Created by jsflz on 2018/8/23.
 * Test file operation with guava.
 *
 *
 * @author cj
 */
public class testFile {

    /**
     * 将文件内容一行一行读取出来
     * Tested
     *
     * @throws IOException IOException
     */
    public static void testRead(File file) throws IOException {
        List<String> readLines = Files.readLines(file, Charsets.UTF_8);

        System.out.printf(readLines.toString());

        /**
         * Test log:
         * [111, 22, 33]
         */
    }

    /**
     * 向文件中新增内容
     * Tested
     *
     * @throws IOException IOException
     */
    public static void testWrite(File file) throws IOException {
        String content1 = "New content 1";
        //Files.write(content1, file, Charsets.UTF_8); //Deprecated, 而且会覆盖原内容.
        Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND).write(content1);//Test: 紧跟着写在原内容后面.

        String content2 = "New content 2";
        //Files.append(content2, file, Charsets.UTF_8); //Deprecated, 而且会覆盖原内容.
        Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND).write(content2); //Test: 紧跟着写在原内容后面.

        String content3 = "New content 3";
        Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND).writeLines(Lists.newArrayList(content3)); //Test:紧跟着写在原内容后面,但是结尾会带个回车.

        Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND).writeLines(Lists.newArrayList(""));//空行

        String content4 = "New content 4";
        Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND).writeLines(Lists.newArrayList(content4)); //Test:紧跟着写在原内容后面,但是结尾会带个回车.

        /**
         * Test log:
         * No log in the console.
         * File contents:
         *
         1111
         2222
         333New content 1New content 2New content 3
         New content 4
         */
    }

    /**
     * 读写(复制)
     * 用Sources读  用Sinks写
     * Sources 和 Sinks 不是 streams', readers', 或者 writers' 对象
     * 但是提供相同功能
     * Tested
     *
     * @throws IOException IOException
     */
    public static void testCopy(File fileSource,File fileTarget) throws IOException {
        //源文件
        ByteSource byteSource = Files.asByteSource(fileSource);

        //目标文件
        ByteSink byteSink = Files.asByteSink(fileTarget);

        //把源文件中的内容,复制到目标文件中.
        byteSource.copyTo(byteSink);

        System.out.println(byteSink + ", " + byteSource);

        /**
         * Test log:
         * D:\testFile\1.txt
         * 文件复制成功.
         */
    }

    /**
     * 文件生成一个hashcode
     * Tested
     *
     * @throws IOException IOException
     */
    public static void testGenerateHashCode(File file) throws IOException {
        HashCode hashCode = Files.hash(file, Hashing.md5());
        System.out.println(hashCode);
        /**
         * Test log:
         * ae03487a608fe746ccae93ddd0be6e98
         */
    }

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\\\testFile\\1.txt"); //该文件必须提前存在.

        //testRead(file);

        //testWrite(file);

        //File file2 = new File("D:\\\\testFile\\2.txt"); //该文件必须提前存在.
        //testCopy(file,file2);

        testGenerateHashCode(file);
    }
}

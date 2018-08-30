package myTest.Guava;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by jsflz on 2018/8/23.
 * Test file operation with guava.
 * Unfinished!!
 *
 * @author cj
 */
public class testFile {

    public static void test() throws IOException {
        File file = new File("/test.txt");
        File file2 = new File("/test2.txt");
        List<String> list = null;
        try {
            list = Files.readLines(file, Charsets.UTF_8);
            System.out.println(list);
        } catch (Exception e) {
        }
        Files.copy(file,file2); //复制文件
        //Files.move(file, file2); //移动文件
    }

    public static void main(String[] args) throws IOException {
        test();
    }
}

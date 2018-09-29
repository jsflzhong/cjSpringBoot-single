package myTest.testFile;

import com.google.common.base.Joiner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by jsflz on 2018/7/30.
 */
public class TestFilePath {

    public static String createFilePath() {
        String savePath = Joiner.on(File.separator)
                .join("test1", "test2", "test3.txt");
        return savePath;

        //结果:test1\test2\test3.txt   果然是用来拼接当时环境的路径的.
    }

    public static void testWithJava(File file) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            long begin = System.currentTimeMillis();
            for (int i = 0; i < 3; i++) {
                out.write("测试java 文件操作\r\n".getBytes());
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //String filePath = TestFilePath.createFilePath();
        //System.out.println(filePath);
        File file = new File("D:\\\\testFile\\testCore.txt");
        testWithJava(file);
    }
}

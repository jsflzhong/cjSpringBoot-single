package myTest.testFile;

import com.google.common.base.Joiner;

import java.io.File;

/**
 * Created by jsflz on 2018/7/30.
 */
public class TestFilePath {

    public static String createFilePath() {
        String savePath = Joiner.on(File.separator)
                .join("test1", "test2", "test3.txt");
        return savePath;
    }

    public static void main(String[] args) {
        String filePath = TestFilePath.createFilePath();
        System.out.println(filePath);//结果:test1\test2\test3.txt   果然是用来拼接当时环境的路径的.
    }
}

import ch.qos.logback.core.util.TimeUtil;
import com.alibaba.druid.util.JdbcUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.http.util.TextUtils;
import sun.plugin2.util.SystemUtil;

import java.io.File;

/**
 * Created by Administrator on 2018-09-30.
 */
public class TestClass {

    public static void main(String[] args) throws Exception{


       /* System.out.println(SystemUtils.JAVA_IO_TMPDIR);

        System.out.println(SystemUtils.JAVA_VM_NAME);

        System.out.println(SystemUtils.JAVA_VM_INFO);

        System.out.println(SystemUtils.getJavaIoTmpDir().getPath());*/

       /* File [] files = File.listRoots();
        for (File file : files) {
          System.out.println( file.getPath() + " : " + file.getFreeSpace()/1024/1024/1024);
        }*/

       /* String [] strings = new String[100000000];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = "qewrwqwqewrer";
        }*/


        System.gc();

    }


}

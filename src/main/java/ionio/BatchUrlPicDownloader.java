package ionio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Richard_yyf
 * @version 1.0 2019/8/29
 */
public class BatchUrlPicDownloader {

    private static String[] urls = {"http://ww1.sinaimg.cn/large/005XyHIDly1g509dum4l8j30j5083glt.jpg",
            "http://ww1.sinaimg.cn/large/005XyHIDly1g50b4rpla9j30qk0fztap.jpg",
            "http://ww1.sinaimg.cn/large/005XyHIDly1g50bfs68lfj30qh08bq4e.jpg",
            "http://ww1.sinaimg.cn/large/005XyHIDly1g50b9j5bzuj30e508cjse.jpg",
            "http://ww1.sinaimg.cn/large/005XyHIDly1g50cu9t091j30sc0apmzc.jpg",
            "http://ww1.sinaimg.cn/large/005XyHIDly1g50c2ma8krj32d434s1kx.jpg",
            "http://ww1.sinaimg.cn/large/005XyHIDly1g50c6xs4w5j30pc0c6dhp.jpg",
            "http://ww1.sinaimg.cn/large/005XyHIDly1g50c9xsij3j30qs0e2ju6.jpg",
            "http://ww1.sinaimg.cn/large/005XyHIDly1g50b4rpla9j30qk0fztap.jpg",
            "http://ww1.sinaimg.cn/large/005XyHIDly1g50b52kdjkj30o60im76e.jpg",
            "http://ww1.sinaimg.cn/large/005XyHIDly1g50bge35jsj30o708w0u9.jpg",
            "http://ww1.sinaimg.cn/large/005XyHIDly1g50rde0gj9j30eu052q31.jpg",
            "http://ww1.sinaimg.cn/large/005XyHIDly1g50rj6x69fj30e30eogmp.jpg",
            "http://ww1.sinaimg.cn/large/005XyHIDly1g50rmxtf4lj30ut0gs0v8.jpg"};

    private static final String dirPath = "E:/blogImg/";

    private static String blogTile = "List相关_";

    private static String suffix = ".jpg";

    private static AtomicInteger count = new AtomicInteger(0);



    public static void main(String[] args) {
        try {
            for (String url : urls) {
                downLoadUrlPic(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void downLoadUrlPic(String picUrl) throws Exception {
        URL url = new URL(picUrl);

        String filePath = dirPath + blogTile + count.getAndIncrement() + suffix;

        /* 此为联系获得网络资源的固定格式用法，以便后面的in变量获得url截取网络资源的输入流 */
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        DataInputStream in = new DataInputStream(connection.getInputStream());

        /* 此处也可用BufferedInputStream与BufferedOutputStream  需要保存的路径*/
        DataOutputStream out = new DataOutputStream(new FileOutputStream(filePath));

        /* 将参数savePath，即将截取的图片的存储在本地地址赋值给out输出流所指定的地址 */
        byte[] buffer = new byte[4096];
        int count = 0;
        while ((count = in.read(buffer)) > 0)/* 将输入流以字节的形式读取并写入buffer中 */
        {
            out.write(buffer, 0, count);
        }
        out.close();/* 后面三行为关闭输入输出流以及网络资源的固定格式 */
        in.close();
        connection.disconnect();
    }
}

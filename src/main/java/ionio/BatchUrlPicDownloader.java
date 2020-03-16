package main.java.ionio;

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

    private static String[] urls = {"http://ww1.sinaimg.cn/large/005XyHIDly1g8nha4eup0j31de199n8q.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8olr7clf7j30ze04y3zt.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8pp8k0yggj31d019d13j.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8ppa6d2o9j319r0ufgt9.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8ppayxml9j31bd0ahabq.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8ppcgpi6rj31b00bbgnk.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8ompfmloij30qp05lgma.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8pph5tnqlj318s0f141f.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8ppl7huamj319v2ennf7.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8ppm1d5enj311g0dxgnu.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8ppns4nboj315h0x6gt2.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8ppt7a0m9j30wp0abgmz.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8pptiij2lj30r00d2q4a.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8ppttppk1j311z08jwfg.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8ppu4boylj30rm0ghmys.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8ppuex7p7j318z0bwwgj.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8qda2qkhfj31aw0de42h.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8qdd625fej309y0dlq3d.jpg","http://ww1.sinaimg.cn/large/005XyHIDly1g8qdgikrsvj31gn0co0x0.jpg"};

    private static final String dirPath = "E:/blogImg/";

    private static String blogTile = "Spring_AOP_3_";

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

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        DataInputStream in = new DataInputStream(connection.getInputStream());

        DataOutputStream out = new DataOutputStream(new FileOutputStream(filePath));

        byte[] buffer = new byte[4096];
        int count = 0;

        while ((count = in.read(buffer)) > 0)
        {
            out.write(buffer, 0, count);
        }

        out.close();
        in.close();
        connection.disconnect();
    }
}

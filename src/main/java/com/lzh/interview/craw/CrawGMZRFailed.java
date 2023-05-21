package com.lzh.interview.craw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang3.StringUtils;

/**
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2020-08-07
 */
public class CrawGMZRFailed {

    private static final String BOOK_NAME = "鬼灭之刃";
    private static final String FILE_PATH = "/Users/lizhuohang/Downloads/craw/" + BOOK_NAME;

    private static File fileFailed = new File(FILE_PATH + "/failed");


    public static void main(String[] args) throws IOException, InterruptedException {
        FileReader reader = new FileReader(fileFailed);

        BufferedReader bf = new BufferedReader(reader);
        String s;
        while ((s = bf.readLine()) != null) {
            if (StringUtils.isEmpty(s)) {
                continue;
            }
            String[] infos = s.split("\t");
            String bookName = infos[0];
            String url = infos[2];
            String picName = infos[3] + ".jpg";
            String dirStr = FILE_PATH + "/" + bookName;

            if (download(url, dirStr + "/" + picName)) {
                System.out.println("suc: " + bookName + "\t" + infos[1] + "\t" + url + "\t" + infos[3]);
            } else {
                System.err.println("err: " + bookName + "\t" + infos[1] + "\t" + url + "\t" + infos[3]);
            }
        }
    }

    private static boolean download(String url, String filePath) {
        URL imgUrl;
        URLConnection uc;
        try {
            imgUrl = new URL(url);
            uc = imgUrl.openConnection();
            uc.setReadTimeout(30000);
        } catch (IOException e) {
            return false;
        }

        try (InputStream inputStream = uc.getInputStream(); FileOutputStream out = new FileOutputStream(filePath);) {
            byte[] bs = new byte[1024];
            int len;
            while ((len = inputStream.read(bs)) != -1) {
                out.write(bs, 0, len);
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}

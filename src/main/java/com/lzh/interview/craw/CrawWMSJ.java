package com.lzh.interview.craw;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2020-08-07
 */
public class CrawWMSJ {

    private static final String BOOK_NAME = "完美世界";
    private static final String FILE_PATH = "/Users/lizhuohang/Downloads/craw/" + BOOK_NAME + "-下载地址";
    private static final String URL = "https://www.shudai.org/0_109/";

    public static void main(String[] args) throws IOException {

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        //        for (int i = 0; i <= 2120; i++) {
        //            try {
        //                String downLoadUrl = CrawService.getValue("http://www.ting89.com/down/?" + getBookNum(args)
        //                + "-" + i + ".html",
        //                        ".wrapper|.dleft|.combox|.border|.fl|.fl|.dlink|href",
        //                        (document, a) -> {
        //                            Element e = document.getElementsByClass("wrapper").get(0)
        //                                    .getElementsByClass("dleft").get(0)
        //                                    .getElementsByClass("combox").get(0)
        //                                    .getElementsByClass("border").get(0)
        //                                    .getElementsByClass("fl").get(1)
        //                                    .getElementsByClass("dlink").get(0);
        //                            return e.attr("href");
        //                        });
        //                if (StringUtils.isBlank(downLoadUrl)) {
        //                    i--;
        //                    Thread.sleep(500);
        //                    continue;
        //                }
        //                writer.write(downLoadUrl);
        //                writer.write("\r\n");
        //                writer.flush();
        //                System.out.println(downLoadUrl);
        //                Thread.sleep(200);
        //            } catch (InterruptedException e) {
        //
        //            } catch (Exception e) {
        //                i--;
        //            }
        //        }
    }

    private static String getFilePath(String[] args) {
        if (args != null && args.length > 4) {
            return (args[4].endsWith("/") ? args[4] : args[4] + "/") + BOOK_NAME + "/下载地址";
        }
        return FILE_PATH;
    }

}

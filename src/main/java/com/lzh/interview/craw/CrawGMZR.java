package com.lzh.interview.craw;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author lizhuohang <lizhuohang@kuaishou.com>
 * Created on 2020-08-07
 */
public class CrawGMZR {

    private static final String BOOK_NAME = "鬼灭之刃";
    private static final String FILE_PATH = "/Users/lizhuohang/Downloads/craw/" + BOOK_NAME;
    private static final String SERVER_NAME = "https://res.img.jituoli.com/";

    private static File fileSucc = new File(FILE_PATH + "/success");
    private static File fileFailed = new File(FILE_PATH + "/failed");
    private static FileWriter succWriter;
    private static FileWriter failedWriter;


    public static void main(String[] args) throws IOException, InterruptedException {
        succWriter = new FileWriter(fileSucc);
        failedWriter = new FileWriter(fileFailed);
        List<Book> books = getList();

        for (Book book : books) {
            System.out.println("Wait For Next Craw...{" + book.getName() + "}");
            Thread.sleep(120000);
            CrawService.getValue("https://www.iimanhua.com" + book.getHref(),
                    "#detail|#viewimages",
                    (document, a) -> {
                        Elements es = document.getElementsByTag("script");
                        if (es == null || es.size() == 0) {
                            writeError(book, "documentIsEmpty", -1);
                            return null;
                        }
                        for (int i = 0; i < es.size(); i++) {
                            if (StringUtils.isNotBlank(es.get(i).attr("src"))) {
                                continue;
                            }
                            Element element = es.get(i);
                            String js = element.html();
                            String jsResult = getJsResult(js);
                            List<String> urlList = getUrls(jsResult);

                            if (urlList == null || urlList.size() == 0) {
                                writeError(book, "imgUrlListIsEmpty", -1);
                                break;
                            }
                            String dirStr = FILE_PATH + "/" + book.getName();
                            File dir = new File(dirStr);
                            if (!dir.exists()) {
                                dir.mkdir();
                            }
                            for (int j = 0; j < urlList.size(); j++) {
                                String url = urlList.get(j);
                                if (download(url, dirStr + "/" + j + ".jpg")) {
                                    writeSucc(book, url);
                                } else {
                                    writeError(book, url, j);
                                }
                            }
                            break;
                        }
                        return "";
                    });
        }
    }

    private static void writeError(Book book, String url, int index) {
        try {
            failedWriter.write(book.getName() + "\t" + book.getHref() + "\t" + url + "\t" + index);
            failedWriter.write("\r\n");
            failedWriter.flush();
            System.err.println("err: " + book.getName() + "\t" + book.getHref() + "\t" + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeSucc(Book book, String url) {
        try {
            succWriter.write(book.getName() + "\t" + book.getHref() + "\t" + url);
            succWriter.write("\r\n");
            succWriter.flush();
            System.out.println("suc: " + book.getName() + "\t" + book.getHref() + "\t" + url);
        } catch (IOException e) {
            e.printStackTrace();
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

    private static String getJsResult(String js) {
        js += "function sysOut(){return eval(base64decode(packed).slice(4));}";
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        String result = null;
        try {
            engine.eval(js);
            if (engine instanceof Invocable) {
                Invocable in = (Invocable) engine;
                result = in.invokeFunction("sysOut", "packed").toString();
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private static List<String> getUrls(String jsResult) {
        String[] pars = jsResult.split(";");
        List<String> list = new ArrayList<>();
        for (String s : pars) {
            if (StringUtils.isEmpty(s)) {
                continue;
            }
            String p = s.split("=")[1];
            String url = p.substring(1, p.length() - 1);
            list.add(SERVER_NAME + url);
        }
        return list;
    }

    private static List<Book> getList() {
        List<Book> bookList = new ArrayList<>();
        //        bookList.add(new Book("/comic/2362/608912.html", "特别短篇"));
        //        bookList.add(new Book("/comic/2362/360720.html", "第205话"));
        //        bookList.add(new Book("/comic/2362/360719.html", "第204话"));
        //        bookList.add(new Book("/comic/2362/352919.html", "第203话"));
        //        bookList.add(new Book("/comic/2362/343603.html", "第202话"));
        //        bookList.add(new Book("/comic/2362/339301.html", "第201话"));
        //        bookList.add(new Book("/comic/2362/332384.html", "第200话"));
        //        bookList.add(new Book("/comic/2362/332383.html", "第199话"));
        //        bookList.add(new Book("/comic/2362/332382.html", "第198话"));
        //        bookList.add(new Book("/comic/2362/332381.html", "第197话"));
        //        bookList.add(new Book("/comic/2362/312651.html", "第196话"));
        //        bookList.add(new Book("/comic/2362/309619.html", "第195话"));
        //        bookList.add(new Book("/comic/2362/307754.html", "第194话"));
        //        bookList.add(new Book("/comic/2362/306836.html", "第193话"));
        //        bookList.add(new Book("/comic/2362/305779.html", "第192话"));
        //        bookList.add(new Book("/comic/2362/304521.html", "第191话"));
        //        bookList.add(new Book("/comic/2362/302630.html", "第190话"));
        //        bookList.add(new Book("/comic/2362/298553.html", "第189话"));
        //        bookList.add(new Book("/comic/2362/295517.html", "第188话"));
        //        bookList.add(new Book("/comic/2362/293058.html", "第187话"));
        //        bookList.add(new Book("/comic/2362/293057.html", "第186话"));
        //        bookList.add(new Book("/comic/2362/290654.html", "第185话"));
        //        bookList.add(new Book("/comic/2362/287585.html", "第184话"));
        //        bookList.add(new Book("/comic/2362/286050.html", "第183话"));
        //        bookList.add(new Book("/comic/2362/282556.html", "第182话"));
        //        bookList.add(new Book("/comic/2362/282555.html", "第181话"));
        //        bookList.add(new Book("/comic/2362/282554.html", "第180话"));
        //        bookList.add(new Book("/comic/2362/282553.html", "第179话"));
        //        bookList.add(new Book("/comic/2362/282552.html", "第178话"));
        //        bookList.add(new Book("/comic/2362/282551.html", "第177话"));
        //        bookList.add(new Book("/comic/2362/282550.html", "第176话"));
        //        bookList.add(new Book("/comic/2362/282549.html", "第175话"));
        //        bookList.add(new Book("/comic/2362/282548.html", "第174话"));
        //        bookList.add(new Book("/comic/2362/282547.html", "第173话"));
        //        bookList.add(new Book("/comic/2362/282546.html", "第172话"));
        //        bookList.add(new Book("/comic/2362/282545.html", "第171话"));
        //        bookList.add(new Book("/comic/2362/282544.html", "第170话"));
        //        bookList.add(new Book("/comic/2362/282543.html", "第169话"));
        //        bookList.add(new Book("/comic/2362/282542.html", "第168话"));
        //        bookList.add(new Book("/comic/2362/282541.html", "第167话"));
        //        bookList.add(new Book("/comic/2362/282540.html", "第166话"));
        //        bookList.add(new Book("/comic/2362/282539.html", "第165话"));
        //        bookList.add(new Book("/comic/2362/282538.html", "第164话"));
        //        bookList.add(new Book("/comic/2362/282537.html", "第163话"));
        //        bookList.add(new Book("/comic/2362/282536.html", "第162话"));
        //        bookList.add(new Book("/comic/2362/282535.html", "第161话"));
        //        bookList.add(new Book("/comic/2362/282534.html", "第160话"));
        //        bookList.add(new Book("/comic/2362/282533.html", "第159话"));
        //        bookList.add(new Book("/comic/2362/282532.html", "第158话"));
        //        bookList.add(new Book("/comic/2362/282531.html", "第157话"));
        //        bookList.add(new Book("/comic/2362/282530.html", "第156话"));
        //        bookList.add(new Book("/comic/2362/282529.html", "第155话"));
        //        bookList.add(new Book("/comic/2362/282528.html", "第154话"));
        //        bookList.add(new Book("/comic/2362/282527.html", "第153话"));
        //        bookList.add(new Book("/comic/2362/282526.html", "第152话"));
        //        bookList.add(new Book("/comic/2362/282525.html", "第151话"));
        //        bookList.add(new Book("/comic/2362/282524.html", "第150话"));
        //        bookList.add(new Book("/comic/2362/282523.html", "第149话"));
        //        bookList.add(new Book("/comic/2362/282522.html", "第148话"));
        //        bookList.add(new Book("/comic/2362/282521.html", "第147话"));
        //        bookList.add(new Book("/comic/2362/282520.html", "第146话"));
        //        bookList.add(new Book("/comic/2362/282519.html", "第145话"));
        //        bookList.add(new Book("/comic/2362/282518.html", "第144话"));
        //        bookList.add(new Book("/comic/2362/282517.html", "第143话"));
        //        bookList.add(new Book("/comic/2362/282516.html", "第142话"));
        //        bookList.add(new Book("/comic/2362/282515.html", "第141话"));
        //        bookList.add(new Book("/comic/2362/282514.html", "第140话"));
        //        bookList.add(new Book("/comic/2362/282513.html", "第139话"));
        //        bookList.add(new Book("/comic/2362/282512.html", "第138话"));
        //        bookList.add(new Book("/comic/2362/282511.html", "第137话"));
        //        bookList.add(new Book("/comic/2362/282510.html", "第136话"));
        //        bookList.add(new Book("/comic/2362/282509.html", "第135话"));
        //        bookList.add(new Book("/comic/2362/282508.html", "第134话"));
        //        bookList.add(new Book("/comic/2362/282507.html", "第133话"));
        //        bookList.add(new Book("/comic/2362/282506.html", "第132话"));
        //        bookList.add(new Book("/comic/2362/282505.html", "第131话"));
        //        bookList.add(new Book("/comic/2362/282504.html", "第130话"));
        //        bookList.add(new Book("/comic/2362/282503.html", "第129话"));
        //        bookList.add(new Book("/comic/2362/282502.html", "第128话"));
        //        bookList.add(new Book("/comic/2362/282501.html", "第127话"));
        //        bookList.add(new Book("/comic/2362/282500.html", "第126话"));
        //        bookList.add(new Book("/comic/2362/282499.html", "第125话"));
        //        bookList.add(new Book("/comic/2362/282498.html", "第124话"));
        //        bookList.add(new Book("/comic/2362/282497.html", "第123话"));
        //        bookList.add(new Book("/comic/2362/282496.html", "第122话"));
        //        bookList.add(new Book("/comic/2362/282495.html", "第121话"));
        //        bookList.add(new Book("/comic/2362/282494.html", "第120话"));
        //        bookList.add(new Book("/comic/2362/282493.html", "第119话"));
        //        bookList.add(new Book("/comic/2362/282492.html", "第118话"));
        //        bookList.add(new Book("/comic/2362/282491.html", "第117话"));
        //        bookList.add(new Book("/comic/2362/282490.html", "第116话"));
        //        bookList.add(new Book("/comic/2362/282489.html", "第115话"));
        //        bookList.add(new Book("/comic/2362/282488.html", "第114话"));
        //        bookList.add(new Book("/comic/2362/282487.html", "第113话"));
        //        bookList.add(new Book("/comic/2362/282486.html", "第112话"));
        //        bookList.add(new Book("/comic/2362/282485.html", "第111话"));
        //        bookList.add(new Book("/comic/2362/282484.html", "第110话"));
        //        bookList.add(new Book("/comic/2362/282483.html", "第109话"));
        //        bookList.add(new Book("/comic/2362/282482.html", "第108话"));
        //        bookList.add(new Book("/comic/2362/282481.html", "第107话"));
        //        bookList.add(new Book("/comic/2362/282480.html", "第106话"));
        //        bookList.add(new Book("/comic/2362/282479.html", "第105话"));
        //        bookList.add(new Book("/comic/2362/282478.html", "第104话"));
        //        bookList.add(new Book("/comic/2362/282477.html", "第103话"));
        //        bookList.add(new Book("/comic/2362/282476.html", "第102话"));
        //        bookList.add(new Book("/comic/2362/282475.html", "第101话"));
        //        bookList.add(new Book("/comic/2362/282474.html", "第100话"));
        //        bookList.add(new Book("/comic/2362/282473.html", "第99话"));
        //        bookList.add(new Book("/comic/2362/282472.html", "第98话"));
        //        bookList.add(new Book("/comic/2362/282471.html", "第97话"));
        //        bookList.add(new Book("/comic/2362/282470.html", "第96话"));
        //        bookList.add(new Book("/comic/2362/282469.html", "第95话"));
        //        bookList.add(new Book("/comic/2362/282468.html", "第94话"));
        //        bookList.add(new Book("/comic/2362/282467.html", "第93话"));
        //        bookList.add(new Book("/comic/2362/282466.html", "第92话"));
        //        bookList.add(new Book("/comic/2362/282465.html", "第91话"));
        //        bookList.add(new Book("/comic/2362/282464.html", "第90话"));
        //        bookList.add(new Book("/comic/2362/282463.html", "第89话"));
        //        bookList.add(new Book("/comic/2362/282462.html", "第88话"));
        //        bookList.add(new Book("/comic/2362/282461.html", "第87话"));
        //        bookList.add(new Book("/comic/2362/282460.html", "第86话"));
        //        bookList.add(new Book("/comic/2362/282459.html", "第85话"));
        //        bookList.add(new Book("/comic/2362/282458.html", "第84话"));
        //        bookList.add(new Book("/comic/2362/282457.html", "第83话"));
        //        bookList.add(new Book("/comic/2362/282456.html", "第82话"));
        //        bookList.add(new Book("/comic/2362/282455.html", "第81话"));
        //        bookList.add(new Book("/comic/2362/282454.html", "第80话"));
        //        bookList.add(new Book("/comic/2362/282453.html", "第79话"));
        //        bookList.add(new Book("/comic/2362/282452.html", "第78话"));
        //        bookList.add(new Book("/comic/2362/282451.html", "第77话"));
        //        bookList.add(new Book("/comic/2362/282450.html", "第76话"));
        //        bookList.add(new Book("/comic/2362/282449.html", "第75话"));
        bookList.add(new Book("/comic/2362/282448.html", "第74话"));
        bookList.add(new Book("/comic/2362/282447.html", "第73话"));
        bookList.add(new Book("/comic/2362/282446.html", "第72话"));
        bookList.add(new Book("/comic/2362/282445.html", "第71话"));
        bookList.add(new Book("/comic/2362/282444.html", "第70话"));
        bookList.add(new Book("/comic/2362/282443.html", "第69话"));
        bookList.add(new Book("/comic/2362/282442.html", "第68话"));
        bookList.add(new Book("/comic/2362/282441.html", "第67话"));
        bookList.add(new Book("/comic/2362/282440.html", "第66话"));
        bookList.add(new Book("/comic/2362/282439.html", "第65话"));
        bookList.add(new Book("/comic/2362/282438.html", "第64话"));
        bookList.add(new Book("/comic/2362/282437.html", "第63话"));
        bookList.add(new Book("/comic/2362/282436.html", "第62话"));
        bookList.add(new Book("/comic/2362/282435.html", "第61话"));
        bookList.add(new Book("/comic/2362/282434.html", "第60话"));
        bookList.add(new Book("/comic/2362/282433.html", "第59话"));
        bookList.add(new Book("/comic/2362/282432.html", "第58话"));
        bookList.add(new Book("/comic/2362/282431.html", "第57话"));
        bookList.add(new Book("/comic/2362/282430.html", "第56话"));
        bookList.add(new Book("/comic/2362/282429.html", "第55话"));
        bookList.add(new Book("/comic/2362/282428.html", "第54话"));
        bookList.add(new Book("/comic/2362/282427.html", "第53话"));
        bookList.add(new Book("/comic/2362/282426.html", "第52话"));
        bookList.add(new Book("/comic/2362/282425.html", "第51话"));
        bookList.add(new Book("/comic/2362/282424.html", "第50话"));
        bookList.add(new Book("/comic/2362/282423.html", "第49话"));
        bookList.add(new Book("/comic/2362/282422.html", "第48话"));
        bookList.add(new Book("/comic/2362/282421.html", "第47话"));
        bookList.add(new Book("/comic/2362/282420.html", "第46话"));
        bookList.add(new Book("/comic/2362/282419.html", "第45话"));
        bookList.add(new Book("/comic/2362/282418.html", "第44话"));
        bookList.add(new Book("/comic/2362/282417.html", "第43话"));
        bookList.add(new Book("/comic/2362/282416.html", "第42话"));
        bookList.add(new Book("/comic/2362/282415.html", "第41话"));
        bookList.add(new Book("/comic/2362/282414.html", "第40话"));
        bookList.add(new Book("/comic/2362/282413.html", "第39话"));
        bookList.add(new Book("/comic/2362/282412.html", "第38话"));
        bookList.add(new Book("/comic/2362/282411.html", "第37话"));
        bookList.add(new Book("/comic/2362/282410.html", "第36话"));
        bookList.add(new Book("/comic/2362/282409.html", "第35话"));
        bookList.add(new Book("/comic/2362/282408.html", "第34话"));
        bookList.add(new Book("/comic/2362/282407.html", "第33话"));
        bookList.add(new Book("/comic/2362/282406.html", "第32话"));
        bookList.add(new Book("/comic/2362/282405.html", "第31话"));
        bookList.add(new Book("/comic/2362/282404.html", "第30话"));
        bookList.add(new Book("/comic/2362/282403.html", "第29话"));
        bookList.add(new Book("/comic/2362/282402.html", "第28话"));
        bookList.add(new Book("/comic/2362/282401.html", "第27话"));
        bookList.add(new Book("/comic/2362/282400.html", "第26话"));
        bookList.add(new Book("/comic/2362/282399.html", "第25话"));
        bookList.add(new Book("/comic/2362/282398.html", "第24话"));
        bookList.add(new Book("/comic/2362/282397.html", "第23话"));
        bookList.add(new Book("/comic/2362/282396.html", "第22话"));
        bookList.add(new Book("/comic/2362/282395.html", "第21话"));
        bookList.add(new Book("/comic/2362/282394.html", "第20话"));
        bookList.add(new Book("/comic/2362/282393.html", "第19话"));
        bookList.add(new Book("/comic/2362/282392.html", "第18话"));
        bookList.add(new Book("/comic/2362/282391.html", "第17话"));
        bookList.add(new Book("/comic/2362/282390.html", "第16话"));
        bookList.add(new Book("/comic/2362/282389.html", "第15话"));
        bookList.add(new Book("/comic/2362/282388.html", "第14话"));
        bookList.add(new Book("/comic/2362/282387.html", "第13话"));
        bookList.add(new Book("/comic/2362/282386.html", "第12话"));
        bookList.add(new Book("/comic/2362/282385.html", "第11话"));
        bookList.add(new Book("/comic/2362/282384.html", "第10话"));
        bookList.add(new Book("/comic/2362/282383.html", "第9话"));
        bookList.add(new Book("/comic/2362/282382.html", "第8话"));
        bookList.add(new Book("/comic/2362/282381.html", "第7话"));
        bookList.add(new Book("/comic/2362/282380.html", "第6话"));
        bookList.add(new Book("/comic/2362/282379.html", "第5话"));
        bookList.add(new Book("/comic/2362/282378.html", "第4话"));
        bookList.add(new Book("/comic/2362/282377.html", "第3话"));
        bookList.add(new Book("/comic/2362/282376.html", "第2话"));
        bookList.add(new Book("/comic/2362/282375.html", "第1话"));
        return bookList;
    }

    private static String getFilePath(String[] args) {
        if (args != null && args.length > 4) {
            return (args[4].endsWith("/") ? args[4] : args[4] + "/") + BOOK_NAME + "/下载地址";
        }
        return FILE_PATH;
    }

    private static class Book {
        private String href;
        private String name;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Book(String href, String name) {
            this.href = href;
            this.name = name;
        }
    }

}

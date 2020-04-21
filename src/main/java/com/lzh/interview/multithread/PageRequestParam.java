package com.lzh.interview.multithread;


/**
 * @description: 头条分页请求分页信息
 * @author: lizhuohang
 * @create: 2019-03-05 20:02
 **/
public class PageRequestParam implements Cloneable {

    protected int page;
    protected int pageSize;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageRequestParam() {
    }

    public PageRequestParam(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public PageRequestParam clone() {
        try {
            return (PageRequestParam) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    //CHECKSTYLE:OFF
    public static void main(String[] args) {
        PageRequestParam p1 = new PageRequestParam(1, 1);
        PageRequestParam p2 = p1.clone();
        System.out.println(p1.getPage());
        System.out.println(p1.getPageSize());

        System.out.println(p2.getPage());
        System.out.println(p2.getPageSize());
        System.out.println("----------------------------------------------");
        p2.setPageSize(100);
        p2.setPage(10);
        System.out.println(p1.getPage());
        System.out.println(p1.getPageSize());

        System.out.println(p2.getPage());
        System.out.println(p2.getPageSize());
    }
    //CHECKSTYLE:ON
}

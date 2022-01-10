package yoziming.ad.hospitalmanage.utils;

import com.alibaba.fastjson.JSONArray;

public class PageModel<T> implements java.io.Serializable {

    // 總條數
    private long total;

    // 每頁大小
    private int pageSize;

    // 總頁數
    private int totalPage;

    // 第幾頁
    private int pageNum = 1;

    private int[] navigatepageNums;

    private JSONArray list;

    public PageModel() {
    }

    public PageModel(JSONArray list, int pageNum, long total, int pageSize) {
        this.list = list;
        this.pageNum = pageNum;
        this.total = total;
        this.pageSize = pageSize;

        this.init();
    }

    public void init() {
        // pageSize 默認為5
        if (pageSize <= 0) {
            pageSize = 5;
        }

        totalPage = (int) (total / pageSize);

        if (0 != total % pageSize) {
            totalPage += 1;
        }

        if (pageNum > totalPage) {
            pageNum = totalPage;
        }
        if (pageNum < 1) {
            pageNum = 1;
        }
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public JSONArray getList() {
        return list;
    }

    public void setList(JSONArray list) {
        this.list = list;
    }

    public int[] getNavigatepageNums() {
        int beginPageIndex;//頁碼列表的開始索引
        int endPageIndex;//頁碼列表的結束索引
        if (totalPage <= 10) {
            beginPageIndex = 1;
            endPageIndex = totalPage;
        }
        //總頁數多於10頁，則显示當前頁附近的共10個頁碼
        else {
            //當前頁附近的共10個頁碼（前4個+當前頁+后5個）
            beginPageIndex = pageNum - 4;
            endPageIndex = pageNum + 5;

            //當前面的頁碼不足4個時，則显示前10個頁碼
            if (beginPageIndex < 1) {
                beginPageIndex = 1;
                endPageIndex = 10;
            }
            //當後面的頁碼不足5個時，則显示后10個頁碼
            if (endPageIndex > totalPage) {
                endPageIndex = totalPage;
                beginPageIndex = totalPage - 10 + 1;
            }
        }
        navigatepageNums = new int[endPageIndex - beginPageIndex + 1];
        int j = 0;
        for (int i = beginPageIndex; i <= endPageIndex; i++) {
            navigatepageNums[j] = i;
            j++;
        }
        return navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    @Override
    public String toString() {
        return "Page [total=" + total + ", pageSize=" + pageSize + ", totalPage=" + totalPage + ", pageNum="
                + pageNum + "]";
    }
}
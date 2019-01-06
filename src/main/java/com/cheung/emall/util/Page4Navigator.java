package com.cheung.emall.util;

import org.springframework.data.domain.Page;

import java.util.List;

public class Page4Navigator<T> {
    Page<T> pageFromJPA;
    int navigatePages;  //  导航页显示的数目
    int totalPages;     //  导航页总页数
    int number;         //  第几页
    long totalElements; //  总共有多少条数据
    int size;           //  每页最多多少条数据
    int numberOfElements;   //  当前页有多少条数据
    List<T> content;
    boolean isHasContent;
    boolean first;
    boolean last;
    boolean isHasNext;
    boolean isHasPrevious;
    int[] navigatepageNums; //  展示当前可导航的页面数




    public Page4Navigator() {
        //  使来自 Redis 的数据从 json 格式转换为 Page4Navigator 对象
    }

    public Page4Navigator(Page<T> pageFromJPA, int navigatePages) {
        this.pageFromJPA = pageFromJPA;
        this.navigatePages = navigatePages;

        totalPages = pageFromJPA.getTotalPages();
        number = pageFromJPA.getNumber();
        totalElements = pageFromJPA.getTotalElements();
        size = pageFromJPA.getSize();
        numberOfElements = pageFromJPA.getNumberOfElements();
        content = pageFromJPA.getContent();
        isHasContent = pageFromJPA.hasContent();
        first = pageFromJPA.isFirst();
        last = pageFromJPA.isLast();
        isHasNext = pageFromJPA.hasNext();
        isHasPrevious = pageFromJPA.hasPrevious();

        calcNavigatepageNums();
    }
    public void calcNavigatepageNums(){
        //  显示的导航页数
        int navgatepageNums[];
        //  总页数
        int totalPages = getTotalPages();
        //  当前页数
        int num = getNumber();

        //  总页数 <= 显示的导航页码，显示的导航页数 = 总页数
        if (totalPages <= navigatePages ){
            navgatepageNums = new int[totalPages];
            for (int i = 0; i<totalPages; i++){
                navgatepageNums[i] = i + 1;
            }
        } else {    // 总页数 > 导航页数，显示的导航页数 = 导航页数
            navgatepageNums = new int[navigatePages];
            //  显示的导航页的第一页
            int startNum = num - navigatePages/2;
            int endNum = num + navigatePages/2;

            //  显示的导航页，边界值判断
            //  边界：导航页的第一页
            if (startNum < 1){
                startNum = 1;
                for (int i = 0;i<navigatePages;i++){
                    navgatepageNums[i] = startNum++;
                }
            }// 边界：导航页的最后一页
            else if(endNum > totalPages){
                endNum = totalPages;
                for (int i = navigatePages - 1; i>=0; i++){
                    navgatepageNums[i] = endNum--;
                }
            }else {
                for (int i = 0; i < navigatePages; i++){
                    navgatepageNums[i] = startNum++;
                }
            }
        }
        this.navigatepageNums = navgatepageNums;

    }

    public Page<T> getPageFromJPA() {
        return pageFromJPA;
    }

    public void setPageFromJPA(Page<T> pageFromJPA) {
        this.pageFromJPA = pageFromJPA;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public boolean isHasContent() {
        return isHasContent;
    }

    public void setHasContent(boolean hasContent) {
        isHasContent = hasContent;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isHasNext() {
        return isHasNext;
    }

    public void setHasNext(boolean hasNext) {
        isHasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return isHasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        isHasPrevious = hasPrevious;
    }

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }
}

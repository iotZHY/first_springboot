package com.iotzzhy.first_spring_boot.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO {
    private List<QuestionDTO> questionDTOList;
    private Integer curPage;
    private Integer maxPage;
    private List<Integer> pages = new ArrayList<>();
    private boolean showPrevious;
    private boolean showNext;
    private boolean showFirst;
    private boolean showEnd;

    public void setOthers(int curPage, int maxPage){
        this.curPage = curPage;
        this.maxPage = maxPage;

        //寻找当前页面可显示的page号
        int count = curPage-3;
        while(count<=curPage+3){
            if(count>maxPage) break;
            if(count>=1){
                pages.add(count);
            }
            count++;
        }

        if(curPage == 1){
            showPrevious = false;
        }else{
            showPrevious = true;
        }
        if(curPage == maxPage){
            showNext = false;
        }else{
            showNext = true;
        }
        if(pages.contains(1)){
            showFirst = false;
        }else{
            showFirst = true;
        }
        if(pages.contains(maxPage)){
            showEnd = false;
        }else{
            showEnd = true;
        }
    }
}

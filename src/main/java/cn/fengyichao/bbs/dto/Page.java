package cn.fengyichao.bbs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengyichao
 * @date 2019/11/27 - 14:37
 */
public class Page<T> {

    private Long total;        //总条数
    private List<T> list;         //查询结果
    private Integer pageCount; //总页数
    private Integer pageSize;  //每页条数
    private Integer pageNum;   //当前页数

    public Page(Long total,Integer pageSize,Integer pageNum){
        this.list = new ArrayList<T>();
        this.total = total;

        setPageSize(pageSize);

        if(total % this.pageSize != 0){
            this.pageCount = total.intValue() / this.pageSize + 1;
        }else{
            this.pageCount = total.intValue() / this.pageSize;
        }

        if(pageNum <= 0){
            this.pageNum = 1;
        }else if(pageNum > pageCount){
            this.pageNum = pageCount;
        }else{
            this.pageNum = pageNum;
        }

    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize <= 0){
            this.pageSize = 5;
        }else{
            this.pageSize = pageSize;
        }

    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if(pageNum <= 0){
            this.pageNum = 1;
        }else if(pageNum > pageCount){
            this.pageNum = pageCount;
        }else{
            this.pageNum = pageNum;
        }

    }
}

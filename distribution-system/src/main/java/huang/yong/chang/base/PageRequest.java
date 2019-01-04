package huang.yong.chang.base;

import lombok.Data;


public class PageRequest {
    private Integer page;
    private Integer pageSize;
    private String orderBy;

    private Boolean isAsc;

    public Boolean getAsc() {
        return isAsc;
    }

    public void setAsc(Boolean asc) {
        isAsc = asc;
    }

    public String getOrderByColumn() {
        return OrderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        OrderByColumn = orderByColumn;
    }

    private String OrderByColumn;

    public Integer getPage() {
        return (page - 1) * pageSize;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}

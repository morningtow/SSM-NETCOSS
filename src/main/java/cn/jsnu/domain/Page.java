package cn.jsnu.domain;

public abstract class Page {
    // 分页输入的参数
    private int currentPage = 1;//当前页
    private int pageSize = 5;

    // 写分页SQL需要的条件，由输入参数计算而来
    private int begin;
    private int end;

    // 总行数
    private int rows;

    // 总页数
    private int totalPage;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getBegin() {
        // 计算起始行
        begin = (currentPage - 1) * pageSize;
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        // 计算结束行
        end = currentPage * pageSize + 1;
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
        // 防止总行数发生改变后，总页数的变化
        if (currentPage > getTotalPage()) {
            currentPage = getTotalPage();
        }
    }

    public int getTotalPage() {
        // 计算总页数
        if (rows % pageSize == 0) {
            totalPage = rows / pageSize;
        } else {
            totalPage = rows / pageSize + 1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}

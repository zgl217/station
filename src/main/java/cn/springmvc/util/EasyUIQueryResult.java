package cn.springmvc.util;

import java.io.Serializable;
import java.util.List;

/**
 * 类SimpleQueryResult.java的实现描述：查询列表数据返回
 * 
 * @author yellow
 */
public class EasyUIQueryResult<T extends Serializable> extends ReturnBase {
    private static final long serialVersionUID = 1L;
    
    /**
     * 查询数据总数
     */
    private long total;
    
    /**
     * 查询数据结果
     */
    private List<?> rows;
    
    private List<?> footer;
    
    public EasyUIQueryResult() {
        super();
    }
    
    public EasyUIQueryResult(ReturnCode returnCode) {
        super(returnCode);
    }
    
    public EasyUIQueryResult(ReturnCode returnCode, String message) {
        super(returnCode, message);
    }
    
    public long getTotal() {
        return total;
    }
    
    public void setTotal(long total) {
        this.total = total;
    }
    
    public List<?> getRows() {
        return rows;
    }
    
    public void setRows(List<?> rows) {
        this.rows = rows;
    }

	public List<?> getFooter() {
		return footer;
	}

	public void setFooter(List<?> footer) {
		this.footer = footer;
	}
    
    
    
}

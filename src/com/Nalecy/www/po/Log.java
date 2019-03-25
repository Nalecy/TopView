package com.Nalecy.www.po;

import java.util.Date;

public class Log {
    private Integer id;
    private Integer level;
    private String content;
    private Date logTime;



    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Date getLogTime() { return logTime; }
    public void setLogTime(Date logTime) { this.logTime = logTime; }
}

package com.mango.mif.refclient;

import java.util.concurrent.TimeUnit;

class PerfStat {
    
    private String status;
    private long start;
    private long end;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public long getStart() {
        return start;
    }
    public void setStart(long start) {
        this.start = start;
    }
    public long getEnd() {
        return end;
    }
    public void setEnd(long end) {
        this.end = end;
    }
    
    public long getTimeTakenSecs() {
        return TimeUnit.MILLISECONDS.toSeconds(end - start);
    }
    
}

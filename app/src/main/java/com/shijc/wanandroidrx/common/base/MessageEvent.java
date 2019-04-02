package com.shijc.wanandroidrx.common.base;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.common.base
 * @Description:
 * @date 2019/4/1 下午 4:33
 */
public class MessageEvent {
    public MessageEvent(int msgWhat, Object obj) {
        this.msgWhat = msgWhat;
        this.obj = obj;
    }

    private int msgWhat;
    private Object obj;

    public int getMsgWhat() {
        return msgWhat;
    }

    public void setMsgWhat(int msgWhat) {
        this.msgWhat = msgWhat;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}

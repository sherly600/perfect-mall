package com.pmall.pay.biz.abs;

import java.util.SortedMap;

/**
 *
 * @author sherly
 */
public class Context {

    public Context() {
        super();
    }

    SortedMap<String, Object> sParaTemp;

    public SortedMap<String, Object> getsParaTemp() {
        return sParaTemp;
    }

    public void setsParaTemp(SortedMap<String, Object> sParaTemp) {
        this.sParaTemp = sParaTemp;
    }
}

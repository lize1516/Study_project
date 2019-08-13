package com.dwl;

public class DubboServiceImpl implements DubboService {
    @Override
    public String getMessage(String msg) {
        return "receive your call:——"+ msg;
    }
}

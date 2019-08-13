package com.dwl;

import java.util.List;

public interface DemoService {
    String sayHello(String name);

    public List getUsers();

    String showInfo(String info);

    /** 序列化*/
    public byte[] toBytes(Object out) throws Exception;
    /** 反序列化*/
    public String toPlay(byte[] bs) throws Exception;
}

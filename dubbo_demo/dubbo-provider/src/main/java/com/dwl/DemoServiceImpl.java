package com.dwl;

import com.dwl.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello_ " + name;
    }

    @Override
    public List getUsers() {
        List list = new ArrayList();
        User u1 = new User();
        u1.setName("jack");
        u1.setAge(20);
        u1.setSex("男");

        User u2 = new User();
        u2.setName("tom");
        u2.setAge(21);
        u2.setSex("女");

        User u3 = new User();
        u3.setName("rose");
        u3.setAge(19);
        u3.setSex("女");

        try{
            list.add(toBytes(u1));
            list.add(toBytes(u2));
            list.add(toBytes(u3));
        }catch (Exception e){
            System.out.println("序列化出错," + e);
        }
//        list.add("first");
//        list.add("second");
//        list.add("third");
        return list;
    }

    @Override
    public String showInfo(String info) {
        return "展示provider信息:——" + info;
    }

    @Override
    public byte[] toBytes(Object out) throws Exception {
        //用于序列化后存储对象
        ByteArrayOutputStream byteArrayOutputStream = null;
        //java序列化API
        ObjectOutputStream objectOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            //将out对象进行序列化
            objectOutputStream.writeObject(out);
            //测试验证输入（获取字节数组）
            byte[] bs = byteArrayOutputStream.toByteArray();
            //将数组转化为字符串输入
            System.out.println(Arrays.toString(bs));
            return bs;

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭最外层的流（内部流会自动关闭）
            objectOutputStream.close();
        }
        return null;
    }

    @Override
    public String toPlay(byte[] bs) throws Exception {
//创建存放二进制数据的API
        ByteArrayInputStream byteArrayInputStream = null;
        //创建反序列化对象
        ObjectInputStream objectInputStream = null;
        User user = new User();
        try {
            byteArrayInputStream = new ByteArrayInputStream(bs);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            //校验测试
            user = (User) objectInputStream.readObject();
            //System.out.println(user.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            objectInputStream.close();
            return user.toString();
        }
    }
}

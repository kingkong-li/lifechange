package com.jingang.lifechange.memory;

/**
 * @Description:
 * @Author: jingang.Li
 * @CreateTime: 2021/7/3-10:45 PM
 * @changeTime:
 */
class MyClassLoader extends  ClassLoader{
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
       return super.loadClass(name);
//        return defineClass("path",100,0,1);
    }
}

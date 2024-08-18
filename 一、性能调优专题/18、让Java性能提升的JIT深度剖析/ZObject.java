package enjoy.jvm.zgc;

public class ZObject {
    ZObject instance = null;
    int i=13;
    public  void ReadBarrier(){//调用一次  方法计数器 xxxx.ReadBarrier.count +1
        ZObject A = new ZObject();
        A.instance= new ZObject();//A->B
        System.gc();//todo 这里发生一次ZGC
        //...读屏障代码
        ZObject D =  A.instance; //这里需不需要加读屏障？（1需要，2不需要）
        ZObject E = D;//这里需不需要加读屏障？（2不需要）
        D.hashCode();//这里需不需要加读屏障？（2不需要）
        int j = D.i;//这里需不需要加读屏障？（2不需要）

    }



















    public static void main(String[] args) {
        Object o = new Object();//0  -》13号地址 -》 66号地址
        //GC开始，对象移动开始

        Object o2 =o;//o2  13
        o.hashCode();// 空指针异常

        //GC结束，对象移动结束
    }



}

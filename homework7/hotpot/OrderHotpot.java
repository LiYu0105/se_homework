import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class OrderHotpot {
    //创建一个工厂对象
    private SimpleFactory simpleFactory = null;
    //创建一个火锅
    private Hotpot hotpot1;
    //创建一个构造器
    public OrderHotpot(SimpleFactory simpleFactory) {
        setFactory(simpleFactory);
    }
    public void setFactory(SimpleFactory simpleFactory) {
        String orderType = ""; //用户输入的
        this.simpleFactory = simpleFactory; //设置简单工厂对象
       // do {
            orderType = getType();
            hotpot1 = this.simpleFactory.createHotpot(orderType);
            //输出
            if(hotpot1 != null) { //订购成功
                hotpot1.prepareHotpot();
            } else {
                System.out.println(" 请重新输入 ");
               // break;
            }
       // }
        //while(true);
    }
    // 写一个方法，可以获取客户希望订购的披萨种类
    private String getType() {
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input 火锅种类 (ClearsoupHotpot/Spicyhotpot):");
            String str = strin.readLine();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Spicyhotpot extends Hotpot{
    //private String name;
    public String getName(String str) {
        List<String> list = new ArrayList<String>();//创建集合对象；
        list.add("牛肉");//在集合里存入数据。
        list.add("毛肚");
        list.add("土豆");
        list.add("火锅粉");
        list.add("藕片");
        list.add("青笋");
        list.add("毛肚");
        System.out.println(list);
        try {
            do{
                BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("input dishes (输入一项按下回车键，结束输入over):");
                str = strin.readLine();
                String str2="over";
                if(!list.contains(str)&&!(str.equals(str2))){
                    System.out.println("抱歉，后续会推出");
                    //break;
                }
                else if(str.equals(str2)){
                    break;
                }
                else {
                    continue;
                }}
            while (true);

            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }
}



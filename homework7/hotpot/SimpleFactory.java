public class SimpleFactory {
    //创建工厂方法
    public Hotpot createHotpot(String orderType) {
        Hotpot hotpot1 = null;
        String str;
        if (orderType.equals("Spicyhotpot")) {
            hotpot1 = new Spicyhotpot();
            str=hotpot1.getName(orderType);
            hotpot1.setName(str);
        } else if (orderType.equals("ClearsoupHotpot")) {
            hotpot1 = new ClearsoupHotpot();
            str=hotpot1.getName(orderType);
            hotpot1.setName(str);
        }
        return hotpot1;
    }
}



public class Test {

    public static void main(String[] args) {
        String s = "http://192.168.0.115:8005/xfdj/".replaceAll("http://\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}(:\\d{2,4})?/\\w{2,6}/","");
        System.out.println(s);
    }



}

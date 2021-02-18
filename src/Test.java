import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean bo=true;
        while(bo) {
            System.out.println("请输入用户名");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();
            System.out.println("刚刚输入的用户名:" + username);

            System.out.println("请输入密码");
            String password = sc.next();
            System.out.println("刚刚输入密码是:" + password);//用于检测输入的用户名是否正确

            /*
    开始读取文件
    */
            //File file = new File("D:\\CmdShop\\src\\users.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx");//此方法能返回一个输入流，满足ReadExcel要求的输入流
            InputStream inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
            ReadUserExcel readExcel = new ReadUserExcel();
            User users[] = readExcel.readExcel(in);

            System.out.println(users.length);
            for (int i=0;i<users.length;i++) {
                if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                    System.out.println("登录成功");
                    bo=false;
                    /*
                    显示商品
                     */
                   ReadProductExcel readProductExcel=new ReadProductExcel();
                    Product products[]=readProductExcel.readExcel(inProduct);
                    for(Product product:products){
                        System.out.print(product.getId());
                        System.out.print("\t"+product.getName());
                        System.out.print("\t"+product.getPrice());
                        System.out.println("\t"+product.getDesc());
                    }
                    System.out.println("请输入商品ID把该商品加入购物车");
                    String id=sc.next();
                    /*
                    创建一个购物车的数组：存放商品
                     */
                    Product carts[]=new Product[3];

                    /*
                    根据此ID中去Excel查找是否有该ID的商品信息，如果有则返回该商品信息即可
                     */
                    break;
                } else {
                    System.out.println("登陆失败");
                }
            }
        }
    }
}

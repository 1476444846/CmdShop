import java.io.InputStream;
import java.util.IdentityHashMap;
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
                    Product products[]=readProductExcel.getAllProduct(inProduct);
                    for(Product product:products){
                        System.out.print(product.getId());
                        System.out.print("\t"+product.getName());
                        System.out.print("\t"+product.getPrice());
                        System.out.println("\t"+product.getDesc());
                    }
                    System.out.println("请输入商品ID把该商品加入购物车");
                    String id=sc.next();
                    int count=0;
                    /*
                    创建一个购物车的数组：存放商品
                     */
                    Product carts[]=new Product[3];
                    /*
                    根据此ID中去Excel查找是否有该ID的商品信息，如果有则返回该商品信息即可
                     */
                    inProduct = null;
                    inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
                    Product product=readProductExcel.getProductById(id,inProduct);
                    System.out.println("要购买的商品价格：" + product.getPrice());
                    if(product!=null){
                        carts[count++]=product;
                    }
                    System.out.println("继续购买商品请按1");
                    System.out.println("查看购物车请按2");
                    int choose=sc.nextInt();
                    if(choose==1){
                        inProduct = null;
                        inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
                        readProductExcel=new ReadProductExcel();
                        products = readProductExcel.getAllProduct(inProduct);
                        for(Product product1:products){
                            System.out.print(product1.getId());
                            System.out.print("\t"+product1.getName());
                            System.out.print("\t"+product1.getPrice());
                            System.out.println("\t"+product1.getDesc());
                        }
                        System.out.println("请输入商品ID把该商品加入购物车");
                        id=sc.next();
                        inProduct = null;
                        inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
                        product=readProductExcel.getProductById(id,inProduct);
                        System.out.println("要购买的商品价格：" + product.getPrice());
                        if(product!=null){
                            carts[count++]=product;
                        }
                    }else if(choose==2){
                        /*
                        查看购物车
                         */
                    }
                    break;
                } else {
                    System.out.println("登陆失败");
                }
            }
        }
    }
}

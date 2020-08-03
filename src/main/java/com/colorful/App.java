package com.colorful;

import com.colorful.model.Goods;
import com.colorful.model.User;
import com.colorful.service.GoodsService;
import com.colorful.service.UserService;

import java.util.List;
import java.util.Scanner;

/**
 * @author colorful@TaleLin
 * Imooc Goods
 */
public class App {

    private static final UserService userService = new UserService();

    private static final GoodsService goodsService = new GoodsService();

    /**
     * 主流程方法
     */
    public static void run() {
        User user = null;
        System.out.println("欢迎使用商品管理系统，请输入用户名和密码：");
        do {
            Scanner scanner = new Scanner(System.in);
            // 登录
            System.out.println("用户名：");
            String username = scanner.nextLine();
            System.out.println("密码：");
            String password = scanner.nextLine();
            user = userService.login(username, password);
            if (user == null) {
                System.out.println("用户名密码校验失败，请重新输入！");
            }
        } while (user == null);
        System.out.println("欢迎您！" + user.getNickName());
        // 登录成功后，跳转到仪表盘页面
        dashboard();
    }

    /**
     * 输出仪表盘操作提示
     */
    private static void printDashboardTips() {
        System.out.println("请输入对应数字以进行操作：");
        System.out.println("（1. 管理商品 | 2. 管理分类 | 0. 退出登录）");
    }

    /**
     * 输出商品列表页操作提示
     */
    private static void printGoodsListTips() {
        System.out.println("请输入对应数字以进行操作：");
        System.out.println("（1. 新增商品 | 2. 编辑商品 | 3. 查看商品详情 | 4. 删除商品 | 5. 搜索商品 | 6. 按分类查询商品 | 0. 返回上一级菜单）");
    }

    /**
     * 仪表盘操作
     */
    private static void dashboard() {
        Scanner scanner = new Scanner(System.in);
        int code1 = 0, code2 = 0;
        while (true) {
            printDashboardTips();
            code1 = scanner.nextInt();
            if (code1 == 0) {
                System.out.println("您已退出登录");
                break;
            }
            switch (code1) {
                case 1:
                    while (true) {
                        System.out.println("正在查询商品列表...");
                        List<Goods> goodsList = goodsService.getGoodsList();
                        goodsList.forEach(goods -> System.out.printf("|id：%s|\t|商品名：%s|\t|价格：%s|\n",
                                goods.getId(), goods.getName(), goods.getPrice()));
                        printGoodsListTips();
                        code2 = scanner.nextInt();
                        if (code2 == 0) {
                            // 返回上一级，即跳出本层循环
                            System.out.println("返回上一级");
                            break;
                        }
                        switch (code2) {
                            case 1:
                                System.out.println("新增商品");
                                System.out.println("请输入商品名称");
                                String name = scanner.next();
                                System.out.println("请输入商品描述");
                                String description = scanner.next();
                                System.out.println("请输入商品分类id");
                                int categoryId = scanner.nextInt();
                                System.out.println("请输入商品价格");
                                double price = scanner.nextDouble();
                                System.out.println("请输入商品库存");
                                int stock = scanner.nextInt();
                                Goods goods = new Goods();
                                goods.setName(name);
                                goods.setDescription(description);
                                goods.setCategoryId(categoryId);
                                goods.setPrice(price);
                                goods.setStock(stock);
                                goodsService.addGoods(goods);
                                System.out.println("新增商品成功！");
                                break;
                            case 2:
                                System.out.println("编辑商品");
                                System.out.println("请输入要编辑的商品id");
                                int goodsId = scanner.nextInt();
                                System.out.println("请输入商品名称");
                                name = scanner.next();
                                System.out.println("请输入商品描述");
                                description = scanner.next();
                                System.out.println("请输入商品分类id");
                                categoryId = scanner.nextInt();
                                System.out.println("请输入商品价格");
                                price = scanner.nextDouble();
                                System.out.println("请输入商品库存");
                                stock = scanner.nextInt();
                                goods = new Goods();
                                goods.setId(goodsId);
                                goods.setName(name);
                                goods.setDescription(description);
                                goods.setCategoryId(categoryId);
                                goods.setPrice(price);
                                goods.setStock(stock);
                                goodsService.editGoods(goods);
                                System.out.println("编辑商品成功！");
                                break;
                            case 3:
                                System.out.println("商品详情");
                                System.out.println("请输入要查看的商品id");
                                goodsId = scanner.nextInt();
                                Goods goodsDetail = goodsService.getById(goodsId);
                                System.out.println("*****************************");
                                System.out.println("id：" + goodsDetail.getId());
                                System.out.println("商品名：" + goodsDetail.getName());
                                System.out.println("描述：" + goodsDetail.getDescription());
                                System.out.println("价格：" + goodsDetail.getPrice());
                                System.out.println("库存：" + goodsDetail.getStock());
                                System.out.println("创建时间：" + goodsDetail.getCreateTime());
                                System.out.println("更新时间：" + goodsDetail.getUpdateTime());
                                System.out.println("*****************************");
                                System.out.println("输入0返回上一级");
                                if (scanner.nextInt() == 0) {
                                    break;
                                }
                            case 4:
                                System.out.println("删除商品");
                                System.out.println("请输入要删除的商品id");
                                goodsId = scanner.nextInt();
                                goodsService.removeGoodsById(goodsId);
                                System.out.println("删除商品成功！");
                                break;
                            case 5:
                                System.out.println("搜索商品");
                                System.out.println("请输入商品名称");
                                name = scanner.next();
                                List<Goods> goodsList1 = goodsService.searchGoodsByName(name);
                                if (goodsList1.isEmpty()) {
                                    System.out.println("未找到商品");
                                } else {
                                    goodsList1.forEach(goods1 -> System.out.printf("|id：%s|\t|商品名：%s|\t|价格：%s|\n",
                                            goods1.getId(), goods1.getName(), goods1.getPrice()));
                                }
                                System.out.println("输入0返回上一级");
                                if (scanner.nextInt() == 0) {
                                    break;
                                }
                            case 6:
                                System.out.println("按分类查询");
                                System.out.println("请输入分类id");
                                categoryId = scanner.nextInt();
                                List<Goods> goodsList2 = goodsService.searchGoodsByCategoryId(categoryId);
                                if (goodsList2.isEmpty()) {
                                    System.out.println("未找到商品");
                                } else {
                                    goodsList2.forEach(goods2 -> System.out.printf("|id：%s|\t|商品名：%s|\t|价格：%s|\n",
                                            goods2.getId(), goods2.getName(), goods2.getPrice()));
                                }
                                System.out.println("输入0返回上一级");
                                if (scanner.nextInt() == 0) {
                                    break;
                                }
                            default:
                                System.out.println("不存在您输入的选项，请重新输入");
                        }
                    }
                    break;
                case 2:
                    System.out.println("正在查询分类列表...");
                    // TODO 实现分类模块
                    break;
                default:
                    System.out.println("不存在您输入的选项，请重新输入");
                    break;
            }
        }
    }

    public static void main( String[] args )
    {
        run();
    }
}

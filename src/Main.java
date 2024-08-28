import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;
    TreeNode(int value) {
        this.value = value;
        left = null;
        right = null;
    }
}

class showMenuGUI {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainMenuPanel;
    private LinkedList<Integer> linkedList = new LinkedList<>();
    private Stack<Integer> stack = new Stack<>();
    private LinkedList<Integer> queue = new LinkedList<>();
    private ConcurrentLinkedQueue<Integer> safeQueue = new ConcurrentLinkedQueue<>();
    private TreeNode root;
    private Map<Integer,List<Integer>> adjacencyList = new HashMap<>();
    private LRUCache<Integer, String> lruCache = new LRUCache<>(3);
    private Map<String, Integer> userPointsMap = new HashMap<>();





    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            try {
                showMenuGUI mainMenu = new showMenuGUI();
                mainMenu.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public showMenuGUI() {
        initialize();
    }
    private  void initialize() {
        frame = new JFrame("数据结构系统");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainMenuPanel = new JPanel(cardLayout);

        JPanel mainPanel = createMainPanel();
        JPanel linkListPanel = createLinkListPanel();
        JPanel stackPanel = createStackPanel();
        JPanel queuePanel = createQueuePanel();
        JPanel biTreePanel = createBiTreePanel();
        JPanel graphPanel = createGraphPanel();

        mainMenuPanel.add(mainPanel, "Main");
        mainMenuPanel.add(linkListPanel, "LinkList");
        mainMenuPanel.add(stackPanel, "Stack");
        mainMenuPanel.add(queuePanel, "Queue");
        mainMenuPanel.add(biTreePanel, "BiTree");
        mainMenuPanel.add(graphPanel, "Graph");

        frame.getContentPane().add(mainMenuPanel);
        cardLayout.show(mainMenuPanel, "Main");

    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,10,10));

        JButton linkListButton = new JButton("线性表的基本操作和应用");
        linkListButton.addActionListener(e -> cardLayout.show(mainMenuPanel,"LinkList"));
        panel.add(linkListButton);

        JButton stackButton = new JButton("栈的基本操作和应用");
        stackButton.addActionListener(e -> cardLayout.show(mainMenuPanel,"Stack"));
        panel.add(stackButton);

        JButton queueButton = new JButton("队列的基本操作和应用");
        queueButton.addActionListener(e -> cardLayout.show(mainMenuPanel,"Queue"));
        panel.add(queueButton);

        JButton biTreeButton = new JButton("二叉树的基本操作和应用");
        biTreeButton.addActionListener(e -> cardLayout.show(mainMenuPanel,"BiTree"));
        panel.add(biTreeButton);

        JButton graphButton = new JButton("图的基本操作和应用");
        graphButton.addActionListener(e -> cardLayout.show(mainMenuPanel,"Graph"));
        panel.add(graphButton);

        JButton exitButton = new JButton("退出程序");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(exitButton);
        return panel;
    }

    private JPanel createLinkListPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,10,10));

        JButton createButton = new JButton("创建");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = JOptionPane.showInputDialog(null, "输入元素（用逗号分隔）", "线性表", JOptionPane.INFORMATION_MESSAGE);
                if (s != null && !s.isEmpty()) {
                    String[] elements = s.split(",");
                    linkedList.clear();
                    for (String element : elements) {
                        try {
                            linkedList.add(Integer.parseInt(element.trim()));
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "输入无效，请输入整数", "错误", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    String newElements = new String();
                    for(int i = 0;i<s.length();i++) {

                        if(s.charAt(i)!=',') {
                            newElements = newElements+s.charAt(i);
                        } else {
                            newElements += "->";
                        }
                    }
                    JOptionPane.showMessageDialog(null, newElements, "创建结果", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        panel.add(createButton);


        JButton insertButton = new JButton("插入");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String indexStr = JOptionPane.showInputDialog(null, "输入插入位置", "插入", JOptionPane.INFORMATION_MESSAGE);
                String valueStr = JOptionPane.showInputDialog(null, "输入插入值", "插入", JOptionPane.INFORMATION_MESSAGE);
                try {
                    int index = Integer.parseInt(indexStr);
                    int value = Integer.parseInt(valueStr);
                    if (index >= 0 && index <= linkedList.size()) {
                        linkedList.add(index, value);
                        String s = linkedList.toString();
                        String elements = new String();
                        for(int i = 1;i<s.length()-1;i++) {

                            if(s.charAt(i)!=',') {
                                elements = elements+s.charAt(i);
                            } else {
                                elements += "->";
                            }
                        }
                        JOptionPane.showMessageDialog(null, elements, "插入结果", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "位置无效", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "输入无效，请输入整数", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(insertButton);


        JButton findButton = new JButton("查找");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valueStr = JOptionPane.showInputDialog(null, "输入查找值", "查找", JOptionPane.INFORMATION_MESSAGE);
                try {
                    int value = Integer.parseInt(valueStr);
                    int index = linkedList.indexOf(value);
                    if (index != -1) {
                        JOptionPane.showMessageDialog(null, "元素位置: " + index, "查找结果", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "元素未找到", "查找结果", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "输入无效，请输入整数", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(findButton);


        JButton deleteButton = new JButton("删除");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valueStr = JOptionPane.showInputDialog(null, "输入删除值", "删除", JOptionPane.INFORMATION_MESSAGE);
                try {
                    int value = Integer.parseInt(valueStr);
                    boolean removed = linkedList.remove((Integer) value);
                    if (removed) {
                        String s = linkedList.toString();
                        String elements = new String();
                        for(int i = 1;i<s.length()-1;i++) {
                            if(s.charAt(i)!=',') {
                                elements = elements+s.charAt(i);
                            } else {
                                elements += "->";
                            }
                        }
                        JOptionPane.showMessageDialog(null, elements, "删除结果", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "元素未找到", "删除结果", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "输入无效，请输入整数", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(deleteButton);


        JButton applyButton = new JButton("应用");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeLRUCache();
            }
        });
        panel.add(applyButton);

        JButton exitButton = new JButton("返回");
        exitButton.addActionListener(e -> cardLayout.show(mainMenuPanel,"Main"));
        panel.add(exitButton);
        return panel;
    }
    private void executeLRUCache() {
        String keyStr = JOptionPane.showInputDialog(null, "输入缓存键值对 (格式: 键,值)，输入'完成'结束", "LRU缓存", JOptionPane.INFORMATION_MESSAGE);
        while (keyStr != null && !keyStr.equalsIgnoreCase("完成")) {
            String[] parts = keyStr.split(",");
            try {
                int key = Integer.parseInt(parts[0].trim());
                String value = parts[1].trim();
                lruCache.put(key, value);
                JOptionPane.showMessageDialog(null, lruCache, "LRU缓存结果", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "输入错误,请输入正确格式的键值对", "错误", JOptionPane.ERROR_MESSAGE);
            }
            keyStr = JOptionPane.showInputDialog(null, "输入缓存键值对 (格式: 键,值)，输入'完成'结束", "LRU缓存", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    static class LRUCache<K, V> extends LinkedHashMap<K, V> {
         private final int capacity;

         public LRUCache(int capacity) {
             super(capacity, 0.75f, true);
             this.capacity = capacity;
         }

         @Override
         protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
             return size() > capacity;
         }

         @Override
         public String toString() {
             StringBuilder sb = new StringBuilder();
             for (Map.Entry<K, V> entry : entrySet()) {
                 sb.append(entry.getKey()).append("->").append(entry.getValue()).append("\n");
             }
             return sb.toString();
         }
     }
    private JPanel createStackPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,10,10));

        JButton insertButton = new JButton("进栈");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valueStr = JOptionPane.showInputDialog(null, "输入进栈值", "进栈", JOptionPane.INFORMATION_MESSAGE);
                try {
                    int value = Integer.parseInt(valueStr);
                    stack.push(value);
                    JOptionPane.showMessageDialog(null, stack, "进栈结果", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "输入无效，请输入整数", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(insertButton);


        JButton outButton = new JButton("出栈");
        outButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!stack.isEmpty()) {
                    int value = stack.pop();
                    JOptionPane.showMessageDialog(null, "出栈元素: " + value, "出栈结果", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, stack, "结果", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null, "栈为空", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(outButton);


        JButton findButton = new JButton("取栈顶元素");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!stack.isEmpty()) {
                    int value = stack.peek();
                    JOptionPane.showMessageDialog(null,"栈顶元素" + value, "查找结果",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,"栈为空","错误",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(findButton);

        JButton applyButton = new JButton("应用");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"添加用户积分", "获取积分区间用户"};
                int choice = JOptionPane.showOptionDialog(null, "选择应用", "应用", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                switch (choice) {
                    case 0:
                        String userId = JOptionPane.showInputDialog(null, "输入用户ID", "添加用户积分", JOptionPane.INFORMATION_MESSAGE);
                        String pointsStr = JOptionPane.showInputDialog(null, "输入积分", "添加用户积分", JOptionPane.INFORMATION_MESSAGE);
                        try {
                            int points = Integer.parseInt(pointsStr);
                            userPointsMap.put(userId, points);
                            JOptionPane.showMessageDialog(null, "用户积分添加成功", "添加用户积分", JOptionPane.INFORMATION_MESSAGE);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "输入无效，请输入整数", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case 1:
                        String minPointsStr = JOptionPane.showInputDialog(null, "输入最小积分", "获取积分区间用户", JOptionPane.INFORMATION_MESSAGE);
                        String maxPointsStr = JOptionPane.showInputDialog(null, "输入最大积分", "获取积分区间用户", JOptionPane.INFORMATION_MESSAGE);
                        try {
                            int minPoints = Integer.parseInt(minPointsStr);
                            int maxPoints = Integer.parseInt(maxPointsStr);
                            List<String> usersInRange = getUsersWithinPointsRange(minPoints, maxPoints);
                            StringBuilder result = new StringBuilder("积分在区间 [" + minPoints + ", " + maxPoints + "] 内的用户ID:\n");
                            for (String user : usersInRange) {
                                result.append(user).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, result.toString(), "获取积分区间用户", JOptionPane.INFORMATION_MESSAGE);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "输入无效，请输入整数", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                }
            }
        });
        panel.add(applyButton);

        JButton exitButton = new JButton("返回");
        exitButton.addActionListener(e -> cardLayout.show(mainMenuPanel,"Main"));
        panel.add(exitButton);
        return panel;

    }
    private List<String> getUsersWithinPointsRange(int minPoints, int maxPoints) {
        List<String> usersInRange = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : userPointsMap.entrySet()) {
            int points = entry.getValue();
            if (points >= minPoints && points <= maxPoints) {
                usersInRange.add(entry.getKey());
            }
        }
        return usersInRange;
    }
    private JPanel createQueuePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,10,10));

        JButton inButton = new JButton("入列");
        inButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valuestr = JOptionPane.showInputDialog(null,"输入入列值","入列",JOptionPane.INFORMATION_MESSAGE);
                try {
                    int value = Integer.parseInt(valuestr);
                    queue.addLast(value);
                    JOptionPane.showMessageDialog(null,queue,"入列结果",JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,"输入错误,请输入整数","错误",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        panel.add(inButton);


        JButton outButton = new JButton("出列");
        outButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!queue.isEmpty()) {
                    int value = queue.removeFirst();
                    JOptionPane.showMessageDialog(null,"出列元素: " + value, "出列",JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null,queue,"结果",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,"队列为空","错误",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(outButton);


        JButton findHeadButton = new JButton("取队头元素");
        findHeadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!queue.isEmpty()) {
                    int value = queue.getFirst();
                    JOptionPane.showMessageDialog(null,"队头元素" + value,"取队头元素",JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null,"队列为空","错误",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(findHeadButton);


        JButton findLastButton = new JButton("取队尾元素");
        findLastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!queue.isEmpty()) {
                    int value = queue.getLast();
                    JOptionPane.showMessageDialog(null, "队尾元素: " + value, "查找结果", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "队列为空", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(findLastButton);


        JButton applyButton = new JButton("应用");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread buyer1 = new Thread(() -> {
                    for (int i = 0; i < 10; i++) {
                        safeQueue.offer(i);
                        System.out.println("购买者1需要: " + i);
                    }
                });

                Thread buyer2 = new Thread(() -> {
                    for (int i = 10; i < 20; i++) {
                        safeQueue.offer(i);
                        System.out.println("购买者2需要: " + i);
                    }
                });

                Thread offer1 = new Thread(() -> {
                    for (int i = 0; i < 10; i++) {
                        Integer item = safeQueue.poll();
                        if (item != null) {
                            System.out.println("提供者1提供: " + item);
                        }
                    }
                });

                Thread offer2 = new Thread(() -> {
                    for (int i = 0; i < 10; i++) {
                        Integer item = safeQueue.poll();
                        if (item != null) {
                            System.out.println("提供者2提供: " + item);
                        }
                    }
                });

                buyer1.start();
                buyer2.start();
                offer1.start();
                offer2.start();

                try {
                    buyer1.join();
                    buyer2.join();
                    offer1.join();
                    offer2.join();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "线程操作完成，队列大小: " + safeQueue.size(), "应用结果", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(applyButton);

        JButton exitButton = new JButton("返回");
        exitButton.addActionListener(e -> cardLayout.show(mainMenuPanel,"Main"));
        panel.add(exitButton);
        return panel;
    }
    private JPanel createBiTreePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,10,10));

        JButton createButton = new JButton("创建二叉树");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "输入节点值(用逗号分隔,空节点用null表示)层序遍历输入", "创建二叉树", JOptionPane.INFORMATION_MESSAGE);
                if (input != null && !input.isEmpty()) {
                    String[] values = input.split(",");
                    root = createTree(values);
                    JOptionPane.showMessageDialog(null, "二叉树创建成功", "创建结果", JOptionPane.INFORMATION_MESSAGE);
                }            }
        });
        panel.add(createButton);


        JButton ergodicButton = new JButton("遍历(前/中/后)");
        ergodicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder result1 = new StringBuilder();
                preOrderTraversal(root,result1);
                JOptionPane.showMessageDialog(null, "前序遍历结果: " + result1.toString(), "遍历结果", JOptionPane.INFORMATION_MESSAGE);

                StringBuilder result2 = new StringBuilder();
                inOrderTraversal(root, result2);
                JOptionPane.showMessageDialog(null, "中序遍历结果: " + result2.toString(), "遍历结果", JOptionPane.INFORMATION_MESSAGE);

                StringBuilder result3 = new StringBuilder();
                postOrderTraversal(root, result3);
                JOptionPane.showMessageDialog(null, "后序遍历结果: " + result3.toString(), "遍历结果", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(ergodicButton);


        JButton findPButton = new JButton("查找双亲");
        findPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valueStr = JOptionPane.showInputDialog(null, "输入子节点值", "查找双亲", JOptionPane.INFORMATION_MESSAGE);
                try {
                    int value = Integer.parseInt(valueStr);
                    TreeNode parent = findParent(root, null, value);
                    if (parent != null) {
                        JOptionPane.showMessageDialog(null, "双亲节点值: " + parent.value, "查找结果", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "未找到双亲节点", "查找结果", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "输入无效，请输入整数", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(findPButton);


        JButton findBButton = new JButton("查找兄弟(左/右)");
        findBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valueStr = JOptionPane.showInputDialog(null, "输入节点值", "查找兄弟", JOptionPane.INFORMATION_MESSAGE);
                try {
                    int value = Integer.parseInt(valueStr);
                    TreeNode sibling = findSibling(root, value);
                    if (sibling != null) {
                        JOptionPane.showMessageDialog(null, "兄弟节点值: " + sibling.value, "查找结果", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "未找到兄弟节点", "查找结果", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "输入无效，请输入整数", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(findBButton);

        JButton findSButton = new JButton("查找孩子(左/右)");
        findSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valueStr = JOptionPane.showInputDialog(null, "输入父节点值", "查找孩子", JOptionPane.INFORMATION_MESSAGE);
                try {
                    int value = Integer.parseInt(valueStr);
                    TreeNode node = findNode(root, value);
                    if (node != null) {
                        StringBuilder children = new StringBuilder();
                        if (node.left != null) {
                            children.append("左孩子: ").append(node.left.value).append(" ");
                        }
                        if (node.right != null) {
                            children.append("右孩子: ").append(node.right.value).append(" ");
                        }
                        if (children.length() > 0) {
                            JOptionPane.showMessageDialog(null, children.toString(), "查找结果", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "该节点无孩子", "查找结果", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "未找到该节点", "查找结果", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "输入无效，请输入整数", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(findSButton);


        JButton applyButton = new JButton("应用");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(applyButton);

        JButton exitButton = new JButton("返回");
        exitButton.addActionListener(e -> cardLayout.show(mainMenuPanel,"Main"));
        panel.add(exitButton);
        return panel;
    }
    private TreeNode createTree(String[] values) {
        if (values.length == 0) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        queue.add(root);
        int i = 1;

        while (!queue.isEmpty() && i < values.length) {
            TreeNode current = queue.poll();
            if (!values[i].equals("null")) {
                current.left = new TreeNode(Integer.parseInt(values[i]));
                queue.add(current.left);
            }
            i++;
            if (i < values.length && !values[i].equals("null")) {
                current.right = new TreeNode(Integer.parseInt(values[i]));
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }
    private void preOrderTraversal(TreeNode node, StringBuilder result1) {
        if(node!=null) {
            result1.append(node.value).append(" ");
            preOrderTraversal(node.left,result1);
            preOrderTraversal(node.right,result1);
        }
    }
    private void inOrderTraversal(TreeNode node, StringBuilder result2) {
        if (node != null) {
            inOrderTraversal(node.left, result2);
            result2.append(node.value).append(" ");
            inOrderTraversal(node.right, result2);
        }
    }
    private void postOrderTraversal(TreeNode node,StringBuilder result3) {
        if(node!=null) {
            postOrderTraversal(node.left,result3);
            postOrderTraversal(node.right,result3);
            result3.append(node.value).append(" ");
        }
    }
    private TreeNode findParent(TreeNode node, TreeNode parent, int value) {
        if (node == null) {
            return null;
        }
        if (node.value == value) {
            return parent;
        }
        TreeNode leftResult = findParent(node.left, node, value);
        if (leftResult != null) {
            return leftResult;
        }
        return findParent(node.right, node, value);
    }
    private TreeNode findSibling(TreeNode node, int value) {
        if (node == null || node.left == null && node.right == null) {
            return null;
        }
        if (node.left != null && node.left.value == value) {
            return node.right;
        }
        if (node.right != null && node.right.value == value) {
            return node.left;
        }
        TreeNode leftResult = findSibling(node.left, value);
        if (leftResult != null) {
            return leftResult;
        }
        return findSibling(node.right, value);
    }
    private TreeNode findNode(TreeNode node, int value) {
        if (node == null) {
            return null;
        }
        if (node.value == value) {
            return node;
        }
        TreeNode leftResult = findNode(node.left, value);
        if (leftResult != null) {
            return leftResult;
        }
        return findNode(node.right, value);
    }
    private JPanel createGraphPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JButton createButton = new JButton("创建(邻接表)");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createGraph();
            }
        });
        panel.add(createButton);

        JButton DFSButton = new JButton("深度优先遍历");
        DFSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (adjacencyList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请先创建图", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String startNodeStr = JOptionPane.showInputDialog(null, "输入起始节点", "深度优先遍历", JOptionPane.INFORMATION_MESSAGE);
                try {
                    int startNode = Integer.parseInt(startNodeStr);
                    if (!adjacencyList.containsKey(startNode)) {
                        JOptionPane.showMessageDialog(null, "起始节点不存在", "错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    List<Integer> result = depthFirstSearch(startNode, adjacencyList);
                    JOptionPane.showMessageDialog(null, result, "DFS结果", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "输入错误,请输入整数", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(DFSButton);

        JButton BFSButton = new JButton("广度优先遍历");
        BFSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (adjacencyList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请先创建图", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String startNodeStr = JOptionPane.showInputDialog(null, "输入起始节点", "广度优先遍历", JOptionPane.INFORMATION_MESSAGE);
                try {
                    int startNode = Integer.parseInt(startNodeStr);
                    if (!adjacencyList.containsKey(startNode)) {
                        JOptionPane.showMessageDialog(null, "起始节点不存在", "错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    List<Integer> result = breadthFirstSearch(startNode, adjacencyList);
                    JOptionPane.showMessageDialog(null, result, "BFS结果", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "输入错误,请输入整数", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(BFSButton);

        JButton findFirstButton = new JButton("找第一个邻接点");
        findFirstButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nodeStr = JOptionPane.showInputDialog(null, "输入节点", "找第一个邻接点", JOptionPane.INFORMATION_MESSAGE);
                try {
                    int node = Integer.parseInt(nodeStr);
                    Integer firstAdjacent = findFirstAdjacent(node);
                    JOptionPane.showMessageDialog(null, "第一个邻接点: " + firstAdjacent, "查找结果", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "输入错误,请输入整数", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(findFirstButton);

        JButton findNextButton = new JButton("找下一个邻接点");
        findNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nodesStr = JOptionPane.showInputDialog(null, "输入节点和当前邻接点 (格式: 节点,邻接点)", "找下一个邻接点", JOptionPane.INFORMATION_MESSAGE);
                String[] parts = nodesStr.split(",");
                try {
                    int node = Integer.parseInt(parts[0].trim());
                    int currentAdjacent = Integer.parseInt(parts[1].trim());
                    Integer nextAdjacent = findNextAdjacent(node, currentAdjacent);
                    JOptionPane.showMessageDialog(null, "下一个邻接点: " + nextAdjacent, "查找结果", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "输入错误,请输入正确格式的整数", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(findNextButton);

        JButton applyButton = new JButton("应用");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 这里可以加入你需要的逻辑

            }
        });
        panel.add(applyButton);

        JButton exitButton = new JButton("返回");
        exitButton.addActionListener(e -> cardLayout.show(mainMenuPanel, "Main"));
        panel.add(exitButton);
        return panel;
    }
    private void createGraph() {
        String nodeCountStr = JOptionPane.showInputDialog(null, "输入节点数", "创建邻接表", JOptionPane.INFORMATION_MESSAGE);
        try {
            int nodeCount = Integer.parseInt(nodeCountStr);
            adjacencyList.clear();
            for (int i = 0; i < nodeCount; i++) {
                adjacencyList.put(i, new ArrayList<>());
            }
            String edgeStr;
            while (!(edgeStr = JOptionPane.showInputDialog(null, "输入边 (格式: 节点1,节点2)，输入'完成'结束", "创建邻接表", JOptionPane.INFORMATION_MESSAGE)).equals("完成")) {
                String[] parts = edgeStr.split(",");
                try {
                    int node1 = Integer.parseInt(parts[0].trim());
                    int node2 = Integer.parseInt(parts[1].trim());
                    if (!adjacencyList.containsKey(node1)) {
                        adjacencyList.put(node1, new ArrayList<>());
                    }
                    if (!adjacencyList.containsKey(node2)) {
                        adjacencyList.put(node2, new ArrayList<>());
                    }
                    adjacencyList.get(node1).add(node2);
//                    adjacencyList.get(node2).add(node1); // 无向图的边
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "输入错误,请输入正确格式的整数", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
            JOptionPane.showMessageDialog(null, "邻接表创建成功", "结果", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "输入错误,请输入整数", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
    private List<Integer> depthFirstSearch(int startNode, Map<Integer, List<Integer>> adjacencyList) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(startNode);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);
                result.add(node);
                if (adjacencyList.containsKey(node)) {
                    List<Integer> neighbors = adjacencyList.get(node);
                    for (int i = neighbors.size() - 1; i >= 0; i--) {
                        stack.push(neighbors.get(i));
                    }
                }
            }
        }
        return result;
    }
    private List<Integer> breadthFirstSearch(int startNode, Map<Integer, List<Integer>> adjacencyList) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (!visited.contains(node)) {
                visited.add(node);
                result.add(node);
                if (adjacencyList.containsKey(node)) {
                    queue.addAll(adjacencyList.get(node));
                }
            }
        }
        return result;
    }
    private Integer findFirstAdjacent(int node) {
        if (adjacencyList.containsKey(node) && !adjacencyList.get(node).isEmpty()) {
            return adjacencyList.get(node).get(0);
        }
        return null;
    }
    private Integer findNextAdjacent(int node, int currentAdjacent) {
        if (adjacencyList.containsKey(node)) {
            List<Integer> neighbors = adjacencyList.get(node);
            int index = neighbors.indexOf(currentAdjacent);
            if (index != -1 && index < neighbors.size() - 1) {
                return neighbors.get(index + 1);
            }
        }
        return null;
    }
}
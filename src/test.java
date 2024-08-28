//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.Stack;
//
//class showMenuGUI {
//    private JFrame frame;
//    private CardLayout cardLayout;
//    private JPanel mainMenuPanel;
//    private JTextArea jTextArea;
//    private JTextField linkListField, stackField, queueField, biTreeField, graphField;
//
//    // Data structures
//    private LinkedList<Integer> linkedList = new LinkedList<>();
//    private Stack<Integer> stack = new Stack<>();
//    private Queue<Integer> queue = new LinkedList<>();
//
//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                showMenuGUI mainMenu = new showMenuGUI();
//                mainMenu.frame.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    public showMenuGUI() {
//        initialize();
//    }
//
//    private void initialize() {
//        frame = new JFrame("数据结构系统");
//        frame.setBounds(100, 100, 600, 400);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        cardLayout = new CardLayout();
//        mainMenuPanel = new JPanel(cardLayout);
//
//        JPanel mainPanel = createMainPanel();
//        JPanel linkListPanel = createLinkListPanel();
//        JPanel stackPanel = createStackPanel();
//        JPanel queuePanel = createQueuePanel();
//        JPanel biTreePanel = createBiTreePanel();
//        JPanel graphPanel = createGraphPanel();
//
//        mainMenuPanel.add(mainPanel, "Main");
//        mainMenuPanel.add(linkListPanel, "LinkList");
//        mainMenuPanel.add(stackPanel, "Stack");
//        mainMenuPanel.add(queuePanel, "Queue");
//        mainMenuPanel.add(biTreePanel, "BiTree");
//        mainMenuPanel.add(graphPanel, "Graph");
//
//        frame.getContentPane().add(mainMenuPanel);
//        cardLayout.show(mainMenuPanel, "Main");
//
//    }
//
//    private JPanel createMainPanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(3, 2, 10, 10));
//
//        JButton linkListButton = new JButton("线性表的基本操作和应用");
//        linkListButton.addActionListener(e -> cardLayout.show(mainMenuPanel, "LinkList"));
//        panel.add(linkListButton);
//
//        JButton stackButton = new JButton("栈的基本操作和应用");
//        stackButton.addActionListener(e -> cardLayout.show(mainMenuPanel, "Stack"));
//        panel.add(stackButton);
//
//        JButton queueButton = new JButton("队列的基本操作和应用");
//        queueButton.addActionListener(e -> cardLayout.show(mainMenuPanel, "Queue"));
//        panel.add(queueButton);
//
//        JButton biTreeButton = new JButton("二叉树的基本操作和应用");
//        biTreeButton.addActionListener(e -> cardLayout.show(mainMenuPanel, "BiTree"));
//        panel.add(biTreeButton);
//
//        JButton graphButton = new JButton("图的基本操作和应用");
//        graphButton.addActionListener(e -> cardLayout.show(mainMenuPanel, "Graph"));
//        panel.add(graphButton);
//
//        JButton exitButton = new JButton("退出程序");
//        exitButton.addActionListener(e -> System.exit(0));
//        panel.add(exitButton);
//        return panel;
//    }
//
//    private JPanel createLinkListPanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(3, 2, 10, 10));
//
//        JButton createButton = new JButton("创建");
//        createButton.addActionListener(e -> {
//            linkedList = new LinkedList<>();
//            JOptionPane.showMessageDialog(frame, "已创建新的链表");
//        });
//        panel.add(createButton);
//
//        JButton insertButton = new JButton("插入");
//        insertButton.addActionListener(e -> {
//            String input = JOptionPane.showInputDialog(frame, "输入要插入的元素:");
//            if (input != null) {
//                linkedList.add(Integer.parseInt(input));
//                JOptionPane.showMessageDialog(frame, "已插入元素 " + input);
//            }
//        });
//        panel.add(insertButton);
//
//        JButton findButton = new JButton("查找");
//        findButton.addActionListener(e -> {
//            String input = JOptionPane.showInputDialog(frame, "输入要查找的元素:");
//            if (input != null && linkedList.contains(Integer.parseInt(input))) {
//                JOptionPane.showMessageDialog(frame, "元素 " + input + " 存在于链表中");
//            } else {
//                JOptionPane.showMessageDialog(frame, "元素 " + input + " 不存在于链表中");
//            }
//        });
//        panel.add(findButton);
//
//        JButton deleteButton = new JButton("删除");
//        deleteButton.addActionListener(e -> {
//            String input = JOptionPane.showInputDialog(frame, "输入要删除的元素:");
//            if (input != null && linkedList.remove(Integer.parseInt(input))) {
//                JOptionPane.showMessageDialog(frame, "已删除元素 " + input);
//            } else {
//                JOptionPane.showMessageDialog(frame, "元素 " + input + " 不存在于链表中");
//            }
//        });
//        panel.add(deleteButton);
//
//        JButton applyButton = new JButton("应用");
//        applyButton.addActionListener(e -> {
//            // Application-specific action
//        });
//        panel.add(applyButton);
//
//        JButton exitButton = new JButton("返回");
//        exitButton.addActionListener(e -> cardLayout.show(mainMenuPanel, "Main"));
//        panel.add(exitButton);
//        return panel;
//    }
//
//    private JPanel createStackPanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(3, 2, 10, 10));
//
//        JButton insertButton = new JButton("进栈");
//        insertButton.addActionListener(e -> {
//            String input = JOptionPane.showInputDialog(frame, "输入要进栈的元素:");
//            if (input != null) {
//                stack.push(Integer.parseInt(input));
//                JOptionPane.showMessageDialog(frame, "已进栈元素 " + input);
//            }
//        });
//        panel.add(insertButton);
//
//        JButton outButton = new JButton("出栈");
//        outButton.addActionListener(e -> {
//            if (!stack.isEmpty()) {
//                int element = stack.pop();
//                JOptionPane.showMessageDialog(frame, "已出栈元素 " + element);
//            } else {
//                JOptionPane.showMessageDialog(frame, "栈为空");
//            }
//        });
//        panel.add(outButton);
//
//        JButton findButton = new JButton("取栈顶元素");
//        findButton.addActionListener(e -> {
//            if (!stack.isEmpty()) {
//                int element = stack.peek();
//                JOptionPane.showMessageDialog(frame, "栈顶元素为 " + element);
//            } else {
//                JOptionPane.showMessageDialog(frame, "栈为空");
//            }
//        });
//        panel.add(findButton);
//
//        JButton applyButton = new JButton("应用");
//        applyButton.addActionListener(e -> {
//            // Application-specific action
//        });
//        panel.add(applyButton);
//
//        JButton exitButton = new JButton("返回");
//        exitButton.addActionListener(e -> cardLayout.show(mainMenuPanel, "Main"));
//        panel.add(exitButton);
//        return panel;
//    }
//
//    private JPanel createQueuePanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(3, 2, 10, 10));
//
//        JButton inButton = new JButton("入列");
//        inButton.addActionListener(e -> {
//            String input = JOptionPane.showInputDialog(frame, "输入要入列的元素:");
//            if (input != null) {
//                queue.add(Integer.parseInt(input));
//                JOptionPane.showMessageDialog(frame, "已入列元素 " + input);
//            }
//        });
//        panel.add(inButton);
//
//        JButton outButton = new JButton("出列");
//        outButton.addActionListener(e -> {
//            if (!queue.isEmpty()) {
//                int element = queue.poll();
//                JOptionPane.showMessageDialog(frame, "已出列元素 " + element);
//            } else {
//                JOptionPane.showMessageDialog(frame, "队列为空");
//            }
//        });
//        panel.add(outButton);
//
//        JButton findHeadButton = new JButton("取队头元素");
//        findHeadButton.addActionListener(e -> {
//            if (!queue.isEmpty()) {
//                int element = queue.peek();
//                JOptionPane.showMessageDialog(frame, "队头元素为 " + element);
//            } else {
//                JOptionPane.showMessageDialog(frame, "队列为空");
//            }
//        });
//        panel.add(findHeadButton);
//
//        JButton findLastButton = new JButton("取队尾元素");
//        findLastButton.addActionListener(e -> {
//            if (!queue.isEmpty()) {
//                int element = ((LinkedList<Integer>) queue).getLast();
//                JOptionPane.showMessageDialog(frame, "队尾元素为 " + element);
//            } else {
//                JOptionPane.showMessageDialog(frame, "队列为空");
//            }
//        });
//        panel.add(findLastButton);
//
//        JButton applyButton = new JButton("应用");
//        applyButton.addActionListener(e -> {
//            // Application-specific action
//        });
//        panel.add(applyButton);
//
//        JButton exitButton = new JButton("返回");
//        exitButton.addActionListener(e -> cardLayout.show(mainMenuPanel, "Main"));
//        panel.add(exitButton);
//        return panel;
//    }
//
//    private JPanel createBiTreePanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(3, 2, 10, 10));
//
//        JButton createButton = new JButton("创建二叉树");
//        createButton.addActionListener(e -> {
//            // Placeholder for binary tree creation
//            JOptionPane.showMessageDialog(frame, "二叉树创建成功");
//        });
//        panel.add(createButton);
//
//        JButton ergodicButton = new JButton("遍历(左/中/右)");
//        ergodicButton.addActionListener(e -> {
//            // Placeholder for binary tree traversal
//            JOptionPane.showMessageDialog(frame, "二叉树遍历成功");
//        });
//        panel.add(ergodicButton);
//
//        JButton findPButton = new JButton("查找双亲");
//        findPButton.addActionListener(e -> {
//            // Placeholder for finding parent node
//            JOptionPane.showMessageDialog(frame, "双亲节点查找成功");
//        });
//        panel.add(findPButton);
//
//        JButton findBButton = new JButton("查找兄弟(左/右)");
//        findBButton.addActionListener(e -> {
//            // Placeholder for finding sibling node
//            JOptionPane.showMessageDialog(frame, "兄弟节点查找成功");
//        });
//        panel.add(findBButton);
//
//        JButton findSButton = new JButton("查找孩子(左/右)");
//        findSButton.addActionListener(e -> {
//            // Placeholder for finding child node
//            JOptionPane.showMessageDialog(frame, "子节点查找成功");
//        });
//        panel.add(findSButton);
//
//        JButton applyButton = new JButton("应用");
//        applyButton.addActionListener(e -> {
//            // Application-specific action
//        });
//        panel.add(applyButton);
//
//        JButton exitButton = new JButton("返回");
//        exitButton.addActionListener(e -> cardLayout.show(mainMenuPanel, "Main"));
//        panel.add(exitButton);
//        return panel;
//    }
//
//    private JPanel createGraphPanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(3, 2, 10, 10));
//
//        JButton createButton = new JButton("创建(邻接矩阵/邻接表)");
//        createButton.addActionListener(e -> {
//            // Placeholder for graph creation
//            JOptionPane.showMessageDialog(frame, "图创建成功");
//        });
//        panel.add(createButton);
//
//        JButton DFSButton = new JButton("深度优先遍历");
//        DFSButton.addActionListener(e -> {
//            // Placeholder for depth-first search
//            JOptionPane.showMessageDialog(frame, "深度优先遍历成功");
//        });
//        panel.add(DFSButton);
//
//        JButton BFSButton = new JButton("广度优先遍历");
//        BFSButton.addActionListener(e -> {
//            // Placeholder for breadth-first search
//            JOptionPane.showMessageDialog(frame, "广度优先遍历成功");
//        });
//        panel.add(BFSButton);
//
//        JButton findFirstButton = new JButton("找第一个邻接点");
//        findFirstButton.addActionListener(e -> {
//            // Placeholder for finding the first adjacent node
//            JOptionPane.showMessageDialog(frame, "第一个邻接点查找成功");
//        });
//        panel.add(findFirstButton);
//
//        JButton findNextButton = new JButton("找下一个临界点");
//        findNextButton.addActionListener(e -> {
//            // Placeholder for finding the next adjacent node
//            JOptionPane.showMessageDialog(frame, "下一个临界点查找成功");
//        });
//        panel.add(findNextButton);
//
//        JButton applyButton = new JButton("应用");
//        applyButton.addActionListener(e -> {
//            // Application-specific action
//        });
//        panel.add(applyButton);
//
//        JButton exitButton = new JButton("返回");
//        exitButton.addActionListener(e -> cardLayout.show(mainMenuPanel, "Main"));
//        panel.add(exitButton);
//        return panel;
//    }
//}

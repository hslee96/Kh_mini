import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Turnover extends JFrame {

    DefaultTableModel model;
    JLabel jl1;
    String turnoverInfoFont = "맑은고딕";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String sql = null;
    int orderSumResult = 0;

    public Turnover() {

        setTitle("매출관리");
        JPanel container1 = new JPanel();
        JPanel container2 = new JPanel();

        String[] header = {"주문번호", "메뉴명", "수량", "금액", "주문일시"};
        model = new DefaultTableModel(header, 0);

        JTable table = new JTable(model);

        JScrollPane jsp = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jl1 = new JLabel();
        jl1.setFont(new Font(turnoverInfoFont, Font.BOLD, 20));

        JButton btn1 = new JButton("조회");
        JButton btn2 = new JButton("뒤로가기");
        btn1.setFont(new Font(turnoverInfoFont, Font.BOLD, 14));
        btn2.setFont(new Font(turnoverInfoFont, Font.BOLD, 14));
        btn1.setPreferredSize(new Dimension(100,40));
        btn2.setPreferredSize(new Dimension(100,40));

        container1.add(jl1);

        container2.add(btn1);
        container2.add(btn2);

        add(jsp, BorderLayout.NORTH);
        add(container1);
        container1.setLayout(new FlowLayout(FlowLayout.RIGHT, 50, 50));
        add(container2, BorderLayout.SOUTH);

        setBounds(150, 150, 700, 700);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

//      여기까지 화면 구현

        // 매출 조회 버튼
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
                model.setRowCount(0);
                orderSumResult = 0;
                turnover();
            }
        });

        // 뒤로가기 버튼
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Administrator();
            }
        });
    }

    void connect() {

        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "web";
        String password = "1234";

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 매출 조회하는 메서드
    void turnover() {
        try {
            sql = "select order_num, menu, price, qty, price * qty as \"order_sum\", order_date from cafe";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int orderNum = resultSet.getInt("order_num");
                String menu = resultSet.getString("menu");
                int qty = resultSet.getInt("qty");
                int orderSum = resultSet.getInt("order_sum");
                String orderDate = resultSet.getString("order_date");

                orderSumResult += orderSum;
                Object[] data = {orderNum, menu, qty, orderSum, orderDate};

                model.addRow(data);

                jl1.setText("합계 : " + orderSumResult + " 원");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new Turnover();
    }
}

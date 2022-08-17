import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Enumeration;

public class Stocktaking extends JFrame {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String sql = null;
    DefaultTableModel model;
    String stocktakingFont = "맑은고딕";
    JTextField jtf1;
    JRadioButton jrb1, jrb2, jrb3, jrb4, jrb5, jrb6, jrb7, jrb8, jrb9, jrb10, jrb11, jrb12;
    ButtonGroup bg;

    public Stocktaking() {

        setTitle("재고관리");

        setLayout(null);

        JLabel jl1 = new JLabel("재고현황");
        jl1.setBounds(40, 45, 100, 25);
        jl1.setFont(new Font(stocktakingFont, Font.BOLD, 18));

        String[] header = {"상품ID", "상품명", "재고"};
        model = new DefaultTableModel(header, 0);
        JTable table = new JTable(model);
        JScrollPane jsp = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setBounds(40, 75, 520, 230);

        JButton btn1 = new JButton("조회");
        btn1.setBounds(250, 320, 100, 25);

        JLabel jl2 = new JLabel("재고추가");
        jl2.setBounds(40, 370, 100, 25);
        jl2.setFont(new Font(stocktakingFont, Font.BOLD, 18));

        jrb1 = new JRadioButton("아메리카노");
        jrb2 = new JRadioButton("핫아메리카노");
        jrb3 = new JRadioButton("카페라떼");
        jrb4 = new JRadioButton("청포도에이드");
        jrb5 = new JRadioButton("자몽에이드");
        jrb6 = new JRadioButton("청귤에이드");
        jrb7 = new JRadioButton("치즈케이크");
        jrb8 = new JRadioButton("초코케이크");
        jrb9 = new JRadioButton("7레이어초코");
        jrb10 = new JRadioButton("홍차");
        jrb11 = new JRadioButton("녹차");
        jrb12 = new JRadioButton("핫초코");
        bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);
        bg.add(jrb3);
        bg.add(jrb4);
        bg.add(jrb5);
        bg.add(jrb6);
        bg.add(jrb7);
        bg.add(jrb8);
        bg.add(jrb9);
        bg.add(jrb10);
        bg.add(jrb11);
        bg.add(jrb12);
        jrb1.setBounds(40, 400, 100, 25);
        jrb2.setBounds(185, 400, 100, 25);
        jrb3.setBounds(315, 400, 100, 25);
        jrb4.setBounds(445, 400, 100, 25);
        jrb5.setBounds(40, 430, 100, 25);
        jrb6.setBounds(185, 430, 100, 25);
        jrb7.setBounds(315, 430, 100, 25);
        jrb8.setBounds(445, 430, 100, 25);
        jrb9.setBounds(40, 460, 100, 25);
        jrb10.setBounds(185, 460, 100, 25);
        jrb11.setBounds(315, 460, 100, 25);
        jrb12.setBounds(445, 460, 100, 25);

        JLabel jl3 = new JLabel("수량 : ");
        jl3.setFont(new Font(stocktakingFont, Font.BOLD, 16));
        jtf1 = new JTextField(5);
        jl3.setBounds(185, 510, 100, 25);
        jtf1.setBounds(235, 510, 100, 25);

        JButton btn2 = new JButton("추가");
        btn2.setBounds(345, 510, 60, 25);

        JButton btn3 = new JButton("뒤로가기");
        btn3.setBounds(250, 600, 100, 25);

        add(jl1);
        add(jsp);
        add(jl2);
        add(jrb1);
        add(jrb2);
        add(jrb3);
        add(jrb4);
        add(jrb5);
        add(jrb6);
        add(jrb7);
        add(jrb8);
        add(jrb9);
        add(jrb10);
        add(jrb11);
        add(jrb12);
        add(jl3);
        add(jtf1);
        add(btn1);
        add(btn2);
        add(btn3);

        setBounds(150, 150, 615, 700);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

//      여기까지 화면구현

        // 재고 조회 버튼 (주문 내역 만큼 재고 감소시키는 메서드 필요)
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
                model.setRowCount(0);
                stocktaking();
            }
        });

        // 재고 추가 버튼
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
                model.setRowCount(0);
                addStock();

                jtf1.setText(null);
                bg.clearSelection();

                stocktaking();
            }
        });

        // 뒤로 가기 버튼
        btn3.addActionListener(new ActionListener() {
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

    void stocktaking() {
        try {
            sql = "select product_id, product_name, product_count from product order by product_id";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                int productCount = resultSet.getInt("product_count");

                Object[] data = {productId, productName, productCount};

                model.addRow(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void addStock() {
        try {
            sql = "update product set product_count = product_count + ? where product_name = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, Integer.parseInt(jtf1.getText()));

            Enumeration<AbstractButton> elements = bg.getElements();
            while (elements.hasMoreElements()) {
                AbstractButton abstractButton = elements.nextElement();
                if (abstractButton.isSelected()) {
                    preparedStatement.setString(2, abstractButton.getText());
                }
            }

//            if (jrb1.isSelected()) {
//                preparedStatement.setString(2, jrb1.getText());
//            } else if (jrb2.isSelected()) {
//                preparedStatement.setString(2, jrb2.getText());
//            } else if (jrb3.isSelected()) {
//                preparedStatement.setString(2, jrb3.getText());
//            } else if (jrb4.isSelected()) {
//                preparedStatement.setString(2, jrb4.getText());
//            } else if (jrb5.isSelected()) {
//                preparedStatement.setString(2, jrb5.getText());
//            } else if (jrb6.isSelected()) {
//                preparedStatement.setString(2, jrb6.getText());
//            } else if (jrb7.isSelected()) {
//                preparedStatement.setString(2, jrb7.getText());
//            } else if (jrb8.isSelected()) {
//                preparedStatement.setString(2, jrb8.getText());
//            } else if (jrb9.isSelected()) {
//                preparedStatement.setString(2, jrb9.getText());
//            } else if (jrb10.isSelected()) {
//                preparedStatement.setString(2, jrb10.getText());
//            } else if (jrb11.isSelected()) {
//                preparedStatement.setString(2, jrb11.getText());
//            } else if (jrb12.isSelected()) {
//                preparedStatement.setString(2, jrb12.getText());
//            }

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "재고가 추가되었습니다.");
            }else {
                JOptionPane.showMessageDialog(null, "다시 시도하세요.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

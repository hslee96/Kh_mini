import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MemberInfo extends JFrame {

    DefaultTableModel model;
    JTable table;
    String memberInfoFont = "맑은고딕";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String sql = null;

    public MemberInfo() {

        setTitle("회원정보");

        JPanel container = new JPanel();

        String[] header = {"ID(휴대폰 번호)", "PW", "이름", "생년월일", "적립금"};
        model = new DefaultTableModel(header, 0);
        table = new JTable(model);
        JScrollPane jsp = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton btn1 = new JButton("조회");
        JButton btn2 = new JButton("뒤로가기");

        btn1.setFont(new Font(memberInfoFont, Font.BOLD, 12));
        btn2.setFont(new Font(memberInfoFont, Font.BOLD, 12));

        btn1.setPreferredSize(new Dimension(90,40));
        btn2.setPreferredSize(new Dimension(90,40));

        container.add(btn1);
        container.add(btn2);

        add(jsp, BorderLayout.CENTER);
        add(container, BorderLayout.SOUTH);

        setBounds(200, 200, 500, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

//      여기까지 화면 구현

        // 조회 버튼
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
                model.setRowCount(0);
                memberInfo();

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

    void memberInfo() {
        try {
            sql = "select * from cafe_member";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                int pw = resultSet.getInt("pw");
                String name = resultSet.getString("name");
                String bd = resultSet.getString("bd").substring(0, 8);
                int mileage = resultSet.getInt("mileage");

                Object[] data = {id, pw, name, bd, mileage};

                model.addRow(data);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new MemberInfo();
    }
}

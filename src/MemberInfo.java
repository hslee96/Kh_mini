import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class MemberInfo extends JFrame {

    DefaultTableModel model;
    JTable table;
    JScrollPane jsp;
    String memberInfoFont = "맑은고딕";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String sql = null;

    public MemberInfo(JTable jTable, DefaultTableModel defaultTableModel) {
        this.table = jTable;
        this.model = defaultTableModel;
        connect();
    }

    public MemberInfo() {

        setTitle("회원정보");

        JPanel container = new JPanel();

        String[] header = {"휴대폰 번호", "생년월일", "이름", "스탬프", "소비금액"};
        model = new DefaultTableModel(header, 0);
        table = new JTable(model);
        table.setModel(model);
        jsp = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton btn1 = new JButton("조회");
        JButton btn2 = new JButton("수정");
        JButton btn3 = new JButton("삭제");
        JButton btn4 = new JButton("뒤로가기");

        btn1.setFont(new Font(memberInfoFont, Font.BOLD, 12));
        btn2.setFont(new Font(memberInfoFont, Font.BOLD, 12));
        btn3.setFont(new Font(memberInfoFont, Font.BOLD, 12));
        btn4.setFont(new Font(memberInfoFont, Font.BOLD, 12));

        btn1.setPreferredSize(new Dimension(90,40));
        btn2.setPreferredSize(new Dimension(90,40));
        btn3.setPreferredSize(new Dimension(90,40));
        btn4.setPreferredSize(new Dimension(90,40));

        container.add(btn1);
        container.add(btn2);
        container.add(btn3);
        container.add(btn4);

        add(jsp, BorderLayout.CENTER);
        add(container, BorderLayout.SOUTH);

        setBounds(200, 200, 500, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//      여기까지 화면 구현

        // 조회 버튼
        btn1.addActionListener(e -> {
            connect();
            model.setRowCount(0);
            memberShow();

        });
        // 수정 버튼
        btn2.addActionListener(e ->
                new MemberUpdate(new MemberInfo(table, model)));

        // 삭제 버튼
        btn3.addActionListener(e -> {
            connect();
            memberDelete();
        });

        // 뒤로가기 버튼
        btn4.addActionListener(e -> {
            dispose();
            new Administrator();
        });
    }

    public void connect() {

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

    public void memberShow() {
        try {
            sql = "select * from member_Option order by member_phone";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String memberPhone = resultSet.getString("member_phone");
                String memberPw = resultSet.getString("member_pw");
                String memberName = resultSet.getString("member_name");
                int memberMileage = resultSet.getInt("member_mileage");
                int memberPay = resultSet.getInt("member_pay");

                Object[] data = {memberPhone, memberPw, memberName, memberMileage, memberPay};

                model.addRow(data);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                try {
                    if (resultSet != null) resultSet.close();
                    if (preparedStatement != null) preparedStatement.close();
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public void memberDelete() {
        try {
            sql = "delete from member_option where member_phone = ?";
            preparedStatement = connection.prepareStatement(sql);

            int row = table.getSelectedRow();

            preparedStatement.setString(1, model.getValueAt(row, 0).toString());

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "회원 삭제 성공");
            }else {
                JOptionPane.showMessageDialog(null, "회원 삭제 실패");
            }

            model.removeRow(row);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

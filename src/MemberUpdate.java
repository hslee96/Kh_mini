import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberUpdate extends JFrame {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String sql = null;
    JTextField jtf1, jtf2;

    public MemberUpdate(MemberInfo memberInfo) {

        setTitle("회원정보 수정");

        setLayout(null);

        JLabel jl1 = new JLabel("생년월일 :    ");
        jl1.setBounds(50, 70, 100, 25);
        jtf1 = new JTextField(10);
        jtf1.setBounds(120, 70, 100, 25);

        JLabel jl2 = new JLabel("이름 :    ");
        jl2.setBounds(75, 120, 100, 25);
        jtf2 = new JTextField(15);
        jtf2.setBounds(120, 120, 100, 25);

        JButton btn1 = new JButton("확인");
        btn1.setBounds(78, 210, 60, 25);
        JButton btn2 = new JButton("취소");
        btn2.setBounds(148, 210, 60, 25);

        add(jl1);
        add(jtf1);
        add(jl2);
        add(jtf2);
        add(btn1);
        add(btn2);

        setBounds(300, 300, 300, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

        // 확인 버튼
        btn1.addActionListener(e -> {


            connect();
            memberUpdate(memberInfo.table, memberInfo.model);
            dispose();

            memberInfo.model.setRowCount(0);
            memberInfo.memberShow();
        });

        // 취소 버튼
        btn2.addActionListener(e ->
                dispose());
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

    public void memberUpdate(JTable jTable, DefaultTableModel defaultTableModel) {
        try {
            sql = "update member_option set member_pw = ?, member_name = ? where member_phone = ?";
            preparedStatement = connection.prepareStatement(sql);

            int row = jTable.getSelectedRow();

            preparedStatement.setString(1, jtf1.getText());
            preparedStatement.setString(2, jtf2.getText());
            preparedStatement.setString(3, defaultTableModel.getValueAt(row, 0).toString());

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "회원 정보가 수정되었습니다.");
            }else {
                JOptionPane.showMessageDialog(null, "다시 시도하세요.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

public class Administrator extends JFrame {

    Connection connection = null;
    DefaultTableModel model;
    String adminFont = "맑은고딕";

    public Administrator() {

        setTitle("관리자 메뉴");

        JPanel container1 = new JPanel();
        JPanel container2 = new JPanel();
        JPanel container3 = new JPanel();

        JLabel jl1 = new JLabel("관리자 메뉴");

        jl1.setFont(new Font(adminFont, Font.BOLD, 60));

        JButton btn1 = new JButton("회원정보");
        JButton btn2 = new JButton("매출관리");
        JButton btn3 = new JButton("키오스크 종료");
        JButton btn4 = new JButton("메인으로");

        btn1.setFont(new Font(adminFont, Font.BOLD, 15));
        btn2.setFont(new Font(adminFont, Font.BOLD, 15));
        btn3.setFont(new Font(adminFont, Font.BOLD, 15));
        btn4.setFont(new Font(adminFont, Font.BOLD, 15));

        btn1.setPreferredSize(new Dimension(160,80));
        btn2.setPreferredSize(new Dimension(160,80));
        btn3.setPreferredSize(new Dimension(160,80));
        btn4.setPreferredSize(new Dimension(120,60));

        container1.add(jl1);

        container2.add(btn1);
        container2.add(btn2);
        container2.add(btn3);

        container3.add(btn4);

        add(container1, BorderLayout.NORTH);
        add(container2);
        container2.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        add(container3, BorderLayout.SOUTH);

        setBounds(200, 200, 700, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

//      여기까지 화면 구현

        // 회원정보 버튼
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MemberInfo();
            }
        });

        // 매출관리 버튼
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Turnover();
            }
        });

        // 키오스크 종료 버튼 (다른 기능으로 수정 예정)
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // 메인으로 버튼
        btn4.addActionListener(new ActionListener() {
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

    public static void main(String[] args) {

        new Administrator();
    }
}

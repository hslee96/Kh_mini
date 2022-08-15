import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Manager_Reference extends JFrame {

    Connection con = null;

    PreparedStatement pstmt = null;

    ResultSet rs = null;

    String sql = null;

    DefaultTableModel model;

    JTable table;

    public Manager_Reference() {

        setTitle("관리자");

        JPanel container1 = new JPanel();
        JPanel container2 = new JPanel(new BorderLayout());

        JButton button1 = new JButton("회원정보");
        JButton button2 = new JButton("매출관리");
        JButton button3 = new JButton("재고관리");
        JButton button4 = new JButton("메인화면");

        JTextArea jta = new JTextArea(5, 20);
        JScrollPane jsp = new JScrollPane(jta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jta.setLineWrap(true);

        container1.add(button1);
        container1.add(button2);
        container1.add(button3);
        container1.add(button4);

        container2.add(jsp);

        add(container1, BorderLayout.NORTH);
        add(container2, BorderLayout.CENTER);

        setBounds(200, 200, 700, 700);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

        // 이벤트 처리
        // 1. 회원정보
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
//                con = null;
                if (con != null) {
                    //연동이 되었을 때, JTable로 해당 데이터를 보여준다.

                }else {
                    int result = JOptionPane.showConfirmDialog(null, "샘플 데이터를 보여줄까요?", "경고", JOptionPane.YES_NO_OPTION);
                    // yes를 눌렀을 때
                    if(result == 0) {
                        System.out.println("준비중");

                        SampleMember sampleMember1 = new SampleMember("01012345678", "홍길동", "1995-01-01", 0);
                        SampleMember sampleMember2 = new SampleMember("01023456789", "이몽룡", "1996-02-02", 10000);

                        // JTable에 보여줄 데이터를 선출력
                        System.out.println(sampleMember1.getId() + "\t" + sampleMember1.getName() + "\t" + sampleMember1.getBd() + "\t" + sampleMember1.getMileage());
                        System.out.println(sampleMember2.getId() + "\t" + sampleMember2.getName() + "\t" + sampleMember2.getBd() + "\t" + sampleMember2.getMileage());

                    // no를 눌렀을 때
                    } else if(result == 1){
                        JOptionPane.showMessageDialog(null, "데이터베이스가 연동되지 않았습니다.");
                    // result의 값이 0, 1이 아닌 값이 들어왔을 때
                    } else {
                        System.out.println("잘못된 값이 들어왔습니다. 서비스를 종료합니다.");
                        System.exit(0);
                    }
                }
                // 회원정보 DB 데이터 연동
            }
        });

        // 2. 매출관리
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();


                // 주문내역 + 매출합계 출력

            }
        });

        // 3. 재고관리
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 메뉴 주문 시마다 재고 감소, 재료 주문 버튼으로 재고 추가
            }
        });

        // 4. 메인화면
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 누르면 메인화면으로 돌아가는 기능
                dispose();
                new Manager_Reference();      // Manager 대신 메인화면 클래스 이름 삽입
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
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new Manager_Reference();
    }
}

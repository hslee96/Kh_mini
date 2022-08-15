import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberInfo extends JFrame {

    DefaultTableModel model;
    JTable table;
    String memberInfoFont = "맑은고딕";

    public MemberInfo() {

        setTitle("회원정보");

        JPanel container = new JPanel();

        String[] header = {"ID(휴대폰 번호)", "PW", "생년월일", "적립금"};

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

    public static void main(String[] args) {

        new MemberInfo();
    }
}

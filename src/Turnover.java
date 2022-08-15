import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Turnover extends JFrame {

    DefaultTableModel model;
    JTable table;
    String turnoverInfoFont = "맑은고딕";

    public Turnover() {

        setTitle("매출관리");
        JPanel container1 = new JPanel();
        JPanel container2 = new JPanel();

        String[] header = {"주문번호", "메뉴명", "수량", "금액", "주문일시"};

        model = new DefaultTableModel(header, 0);

        table = new JTable(model);

        JScrollPane jsp = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JLabel jl1 = new JLabel("합계 : " );


        jl1.setFont(new Font(turnoverInfoFont, Font.BOLD, 20));

        JButton btn1 = new JButton("조회");
        JButton btn2 = new JButton("뒤로가기");

        btn1.setFont(new Font(turnoverInfoFont, Font.BOLD, 14));
        btn2.setFont(new Font(turnoverInfoFont, Font.BOLD, 14));

        btn1.setPreferredSize(new Dimension(90,40));
        btn2.setPreferredSize(new Dimension(90,40));

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

        new Turnover();
    }
}

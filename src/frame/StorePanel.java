package frame;

import product.Product;
import product.ProductList;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class StorePanel extends JPanel{


    private JTextField searchField;
    private JPanel cartPanel;
    private JPanel productPanel;

    public StorePanel() {

        setLayout(new BorderLayout());

        /**
         * 상단 컨테이너
         */
        JLabel title = new JLabel("Store", SwingConstants.CENTER);
        title.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);


        /**
         * 중앙 컨테이너
         * 영양제 목록
         */
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        add(centerPanel, BorderLayout.CENTER);

        //영양제 구현
        JLabel productLabel = new JLabel("영양제");
        productLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        productLabel.setAlignmentX(Component.CENTER_ALIGNMENT);               //가운데로 정렬
        centerPanel.add(productLabel);
        productPanel = new JPanel(new GridLayout(2, 5, 5, 5));
        JScrollPane scrollPane = new JScrollPane(productPanel);
        addProductItems();  // 영양제 항목 추가
        centerPanel.add(productPanel);


        /**
         * 오른쪽 컨테이너
         * 장바구니 목록
         */
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        add(rightPanel, BorderLayout.EAST);


        JLabel cartLabel = new JLabel("장바구니");


//        // 검색창
//        JPanel searchPanel = new JPanel(new FlowLayout());
//        searchField = new JTextField(50);
//        JButton searchButton = new JButton("검색");
//        searchButton.addActionListener(e -> searchProduct(searchField.getText()));
//        searchPanel.add(searchField);
//        searchPanel.add(searchButton);
//        centerPanel.add(searchPanel);


        setVisible(true);
    }


    //영양제 목록 추가
    private void addProductItems() {
        for (Product product : ProductList.getAllProduct()) {

            JPanel sp = new JPanel();
            sp.setSize(new Dimension(170, 170));

            //이미지 불러오기
            URL imageUrl = getClass().getResource("/image/" + product.getName() + ".png");
            if (imageUrl != null) {
                ImageIcon img = new ImageIcon(imageUrl);
                Image scaled = img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

                // 버튼 생성
                JButton btn = new JButton(new ImageIcon(scaled));
                btn.setBorderPainted(false);
                btn.setContentAreaFilled(false);
                btn.setPreferredSize(new Dimension(100, 100));
                btn.addActionListener(e -> showProductDetail(product.getName()));

                // 텍스트 라벨 생성
                JLabel label = new JLabel(product.getName(), SwingConstants.LEFT);
                label.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

                //가격 라벨 생성
                JLabel priceLabel = new JLabel(String.valueOf(product.getPrice()) + "원", SwingConstants.LEFT);
                priceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));

                // 버튼 + 라벨 + 가격을 담을 panel 생성
                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS)); // 세로 정렬
                itemPanel.setPreferredSize(new Dimension(120, 100));
                itemPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                itemPanel.setOpaque(false); // 배경 투명

                btn.setAlignmentX(Component.CENTER_ALIGNMENT);
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
                priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                itemPanel.add(btn);
                itemPanel.add(label);
                itemPanel.add(priceLabel);

                productPanel.add(itemPanel);

            }
        }
    }


    private void searchProduct(String query) {
        if (query.equalsIgnoreCase("비타민C")) {
            showProductDetail("비타민C");
        } else {
            JOptionPane.showMessageDialog(this, query + " 해당 상품이 없습니다.");
        }
    }

    private void showProductDetail(String productName) {
        JOptionPane.showMessageDialog(this, productName + " 상세 페이지로 이동합니다.");
    }

}
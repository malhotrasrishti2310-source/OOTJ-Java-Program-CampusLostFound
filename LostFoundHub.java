
package campuslostfound;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LostFoundHub extends JFrame {

    Manager manager = new Manager();

    Color burgundy = new Color(90, 23, 45);
    Color cream = new Color(255, 248, 240);

    DefaultTableModel model;
    JTable table;

    JLabel lost, found, claimed;
    JTextField search;

    public LostFoundHub() {

        setTitle("Campus Lost & Found Hub");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(cream);
        setLayout(new BorderLayout(15, 15));

        // Heading
        JLabel title = new JLabel("CAMPUS LOST & FOUND HUB",
                                  JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setForeground(burgundy);
        add(title, BorderLayout.NORTH);

        // Dashboard cards
        JPanel cards = new JPanel(new GridLayout(1, 3, 15, 15));
        cards.setBackground(cream);

        lost = new JLabel("Lost: 0", JLabel.CENTER);
        found = new JLabel("Found: 0", JLabel.CENTER);
        claimed = new JLabel("Returned: 0", JLabel.CENTER);

        cards.add(createCard(lost));
        cards.add(createCard(found));
        cards.add(createCard(claimed));

        // Item table
        String[] columns = {
            "ID", "Name", "Category", "Location",
            "Type", "Status", "Contact"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setRowHeight(30);

        table.getTableHeader().setBackground(burgundy);
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(table);

        // Search bar
        search = new JTextField(15);

        JButton searchBtn = new JButton("Search");
        JButton showAll = new JButton("Show All");

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(cream);

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(search);
        searchPanel.add(searchBtn);
        searchPanel.add(showAll);

        searchBtn.addActionListener(e -> {
            model.setRowCount(0);

            for (Item i : manager.searchItems(search.getText())) {
                addRow(i);
            }
        });

        showAll.addActionListener(e -> refresh());

        // Action buttons
        JButton lostBtn = new JButton("Report Lost");
        JButton foundBtn = new JButton("Report Found");
        JButton claimBtn = new JButton("Mark Claimed");

        lostBtn.addActionListener(e -> addItem("Lost"));
        foundBtn.addActionListener(e -> addItem("Found"));
        claimBtn.addActionListener(e -> markClaimed());

        JPanel buttons = new JPanel();
        buttons.setBackground(cream);

        buttons.add(lostBtn);
        buttons.add(foundBtn);
        buttons.add(claimBtn);

        // Main layout
        JPanel center = new JPanel(new BorderLayout(10, 15));
        center.setBackground(cream);
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        center.add(cards, BorderLayout.NORTH);
        center.add(scroll, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new GridLayout(2, 1));
        bottom.add(searchPanel);
        bottom.add(buttons);

        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        refresh();
        setVisible(true);
    }

    // Create dashboard card
    JPanel createCard(JLabel label) {

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(burgundy));

        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(burgundy);

        card.add(label);
        return card;
    }

    // Add item to table
    void addRow(Item i) {
        model.addRow(new Object[] {
            i.getId(), i.getName(), i.getCategory(),
            i.getLocation(), i.getType(),
            i.getStatus(), i.getContact()
        });
    }

    // Report a new item
    void addItem(String type) {

        JTextField name = new JTextField();
        JTextField category = new JTextField();
        JTextField location = new JTextField();
        JTextField contact = new JTextField();

        Object[] fields = {
            "Name:", name,
            "Category:", category,
            "Location:", location,
            "Contact:", contact
        };

        int result = JOptionPane.showConfirmDialog(
            this, fields, "Report " + type,
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            if (name.getText().trim().isEmpty()
                || category.getText().trim().isEmpty()
                || location.getText().trim().isEmpty()
                || contact.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                    "Please fill all fields!");
                return;
            }

            manager.addItem(name.getText(), category.getText(),
                location.getText(), type, contact.getText());

            refresh();
        }
    }

    // Update item status
    void markClaimed() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                "Select an item first!");
            return;
        }

        int id = (int) model.getValueAt(row, 0);

        for (Item i : manager.getItems()) {
            if (i.getId() == id) {
                i.markClaimed();
            }
        }

        refresh();
    }

    // Refresh table and dashboard
    void refresh() {

        model.setRowCount(0);

        for (Item i : manager.getItems()) {
            addRow(i);
        }

        lost.setText("Lost: " + manager.countType("Lost"));
        found.setText("Found: " + manager.countType("Found"));
        claimed.setText("Returned: " + manager.countClaimed());
    }

    public static void main(String[] args) {
        new LostFoundHub();
    }
}

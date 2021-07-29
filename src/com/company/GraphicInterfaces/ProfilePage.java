package com.company.GraphicInterfaces;

import com.company.Application.Application;
import com.company.Application.Test;
import com.company.CodeFile.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePage extends JFrame implements ActionListener {
    JPanel panOuter;
    JPanel panUser;
    JPanel panBottom;
    JPanel panConsole;

    JLabel username;
    JTextField userr;
    JButton button;
    JTextArea txtConsole;
    public ProfilePage(String text) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 200));

        panOuter = new JPanel(new BorderLayout());

        panUser = new JPanel(new BorderLayout());
        panUser.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panBottom = new JPanel();
        panBottom.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panConsole = new JPanel(new BorderLayout());

        username = new JLabel("Username", JLabel.CENTER);
        userr = new JTextField(10);
        userr.addActionListener(this);
        button = new JButton("Search!");
        button.addActionListener(this);
        button.setEnabled(false);
        txtConsole = new JTextArea(5, 10);

        JScrollPane srcPane = new JScrollPane(txtConsole,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panBottom.add(button);
        panConsole.add(srcPane, BorderLayout.CENTER);

        panUser.add(username, BorderLayout.NORTH);
        panUser.add(userr, BorderLayout.CENTER);
        panUser.add(panBottom, BorderLayout.SOUTH);

        panOuter.add(panUser, BorderLayout.NORTH);
        panOuter.add(panConsole, BorderLayout.CENTER);


        setContentPane(panOuter);
        pack();
        show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JTextField) {
            button.setEnabled(true);

        } else if(e.getSource() instanceof JButton) {
            int ok = 0;
            for(User u : Application.getInstance().getUsers()) {
                String totalname = u.r.getInformation().getNume() + " " + u.r.getInformation().getPrenume();
                if(userr.getText().equals(totalname)) {
                    ok = 1;
                    txtConsole.append(u.toString());
                }
            }
            if(ok == 0)
                txtConsole.append("User inexistent!");
        }
    }

    public static void main(String[] args) {
        Test.main(null);
        ProfilePage p = new ProfilePage("User Profile");
    }
}

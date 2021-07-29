package com.company.GraphicInterfaces;

import com.company.Application.Application;
import com.company.CodeFile.Company;
import com.company.Application.Test;
import com.company.CodeFile.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Vector;

public class AdminPage extends JFrame implements ListSelectionListener {
    JPanel panOuter;
    JPanel panInput;
    JPanel panCompany;
    JPanel panCuprins;
    JPanel panConsole;

    JLabel label1;
    JLabel label2;
    JList<Vector<String>> uusers;
    DefaultListModel<String> list;
    JList ccompanies;
    JTextArea txtConsole;

    public AdminPage(String text) {
        super(text);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 200));

        panOuter = new JPanel(new BorderLayout());
        panInput = new JPanel(new BorderLayout());
        panInput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panCompany = new JPanel(new BorderLayout());
        panCompany.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panCuprins = new JPanel(new BorderLayout());
        panCuprins.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panConsole = new JPanel(new BorderLayout());

        label1 = new JLabel("Users", JLabel.CENTER);
        label2 = new JLabel("Company", JLabel.CENTER);

        list = new DefaultListModel<>();
        for (Company c : Application.getInstance().getCompanies()) {
            list.addElement(c.name);
        }
        ccompanies = new JList(list);
        ccompanies.addListSelectionListener(this);

        Vector<String> usr = new Vector<>();
        for(User u : Application.getInstance().getUsers()) {
            usr.add(u.r.getInformation().getNume() + " " + u.r.getInformation().getPrenume());
        }
        uusers = new JList(usr);

        txtConsole = new JTextArea(5, 10);

        JScrollPane srcPane = new JScrollPane(txtConsole,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panConsole.add(srcPane, BorderLayout.CENTER);
        panInput.add(label1, BorderLayout.NORTH);
        panInput.add(uusers,BorderLayout.CENTER);

        panCompany.add(label2, BorderLayout.NORTH);
        panCompany.add(ccompanies, BorderLayout.CENTER);

        panCuprins.add(panInput, BorderLayout.NORTH);
        panCuprins.add(panCompany, BorderLayout.SOUTH);

        panOuter.add(panCuprins, BorderLayout.NORTH);
        panOuter.add(panConsole, BorderLayout.CENTER);

        setContentPane(panOuter);

        show();
        pack();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(ccompanies.isSelectionEmpty())
            return;
        String s = (String) ccompanies.getSelectedValue();
        txtConsole.setText("");
        Company c = Application.getInstance().getCompany(s);
        txtConsole.append(c.toString());
    }

    public static void main(String[] args) {
        Test.main(null);
        AdminPage a = new AdminPage("admin");
    }
}


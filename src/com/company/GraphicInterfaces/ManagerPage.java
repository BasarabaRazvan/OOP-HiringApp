package com.company.GraphicInterfaces;

import com.company.Application.Application;
import com.company.Application.Test;
import com.company.CodeFile.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.util.Vector;

public class ManagerPage extends JFrame implements ListSelectionListener {
    JPanel panInput;
    JPanel panConsole;
    JPanel panOuter;

    DefaultListModel<String> list;
    JList<String> req;
    JButton accept;
    JButton reject;
    JTextArea txtConsole;

    public ManagerPage(String text, Manager manager) {
        super(text);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 200));

        panOuter = new JPanel(new BorderLayout());
        panInput = new JPanel(new BorderLayout());
        panInput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panConsole = new JPanel(new BorderLayout());

        list = new DefaultListModel<>();
        for (Request<Job, User> r : manager.requests) {
            list.addElement(r.getValue1().r.getInformation().getNume() + "->" + r.getKey().numeJob);
        }
        req = new JList<>(list);
        req.addListSelectionListener(this);
        accept = new JButton("Accept");
        reject = new JButton("Reject");

        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtConsole.setText("");
                int index = req.getSelectedIndex();
                Vector<String> arr = findtokens(list.get(index));
                int count = 0;

                for (Request<Job, User> re : Application.getInstance().getCompanies().get(0).manager.requests) {
                    if(re.getValue1().r.getInformation().getNume().equals(arr.get(0)) && re.getKey().numeJob.equals(arr.get(1))) {
                        if(Application.getInstance().getUsers().contains(re.getValue1()) && !re.getKey().status && count != re.getKey().nrCanditati) {
                            Employee emp = re.getValue1().convert();
                            emp.setSalariu(re.getKey().salariu);
                            emp.setNumeleCompaniei(re.getKey().numeCompanie);
                            Company c = Application.getInstance().getCompany(re.getKey().numeCompanie);
                            for (Department d : c.departaments) {
                                if(d.jobs != null) {
                                    if (d.jobs.contains(re.getKey())) {
                                        if(d.getEmployees() != null) {
                                            int ok = 0;
                                            for (Employee ey : d.getEmployees()) {
                                                if(ey.r.getInformation().getEmail().equals(emp.r.getInformation().getEmail())) {
                                                    txtConsole.append("Userul a fost deja angajat!");
                                                    ok = 1;
                                                    break;
                                                }
                                            }
                                            if(ok == 0) {
                                                count++;
                                                if(count == re.getKey().nrCanditati)
                                                    re.getKey().status = true;
                                                d.add(emp);
                                                txtConsole.append("Userul a fost angajat!");
                                            }
                                        }
                                    }
                                }
                            }
                        } else  {
                            txtConsole.append("Job ocupat!");
                        }
                    }
                }
            }
        });

        reject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtConsole.setText("");
                int index = req.getSelectedIndex();
                list.remove(index);
            }
        });

        panInput.add(req, BorderLayout.NORTH);
        panInput.add(accept, BorderLayout.WEST);
        panInput.add(reject, BorderLayout.EAST);

        txtConsole = new JTextArea(5, 10);
        JScrollPane srcPane = new JScrollPane(txtConsole,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panConsole.add(srcPane, BorderLayout.CENTER);

        panOuter.add(panInput, BorderLayout.NORTH);
        panOuter.add(panConsole, BorderLayout.CENTER);

        setContentPane(panOuter);

        show();
        pack();
    }

    public Vector<String> findtokens(String string) {
        Vector<String> result = new Vector<>();
        StringTokenizer tokenizer = new StringTokenizer(string, "->");
        while (tokenizer.hasMoreTokens()) {
            result.add(tokenizer.nextToken());
        }
        return result;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(req.isSelectionEmpty())
            return;
        String string = (String) req.getSelectedValue();
        txtConsole.setText("");
        Vector<String> arr = findtokens(string);
        for (Request<Job, User> re : Application.getInstance().getCompanies().get(0).manager.requests) {
            if(re.getValue1().r.getInformation().getNume().equals(arr.get(0)) && re.getKey().numeJob.equals(arr.get(1))) {
                txtConsole.append(re.toString());
            }
        }
    }


    //Pentru testarea acestei interfete ar trebui comentat for - ul din Test in care manageri procezeaza requesturile
    //Am testat doar pentru compania Google, alegand doar managerul de acolo
    public static void main(String[] args) {
        Test.main(null);
        ManagerPage m = new ManagerPage("Manager Page", Application.getInstance().getCompanies().get(0).manager);
    }
}

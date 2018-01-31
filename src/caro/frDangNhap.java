package caro;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.event.*;

//import javax.swing.JOptionPane;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class frDangNhap
    extends JFrame {

  private AgletClient C_aglet = null;
  public frDangNhap(AgletClient aglet) {
    C_aglet = aglet;
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  JTextField tfUserNameDN = new JTextField();
  JButton btDangNhap = new JButton();
  JButton btXoa = new JButton();
  JPasswordField pfPassWordDN = new JPasswordField();
  JButton btThoat = new JButton();
  JLabel lbPassWord = new JLabel();
  JLabel lbUserName = new JLabel();
  JLabel lbTieuDe = new JLabel();
  JButton btDangKy = new JButton();
  XYLayout xYLayout1 = new XYLayout();

  private void jbInit() throws Exception {
    lbTieuDe.setFont(new java.awt.Font("Times New Roman", 1, 25));
    lbTieuDe.setForeground(Color.red);
    lbTieuDe.setText("ÐÃNG NH\u1EACP TÀI KHO\u1EA2N");
    lbUserName.setFont(new java.awt.Font("Times New Roman", 1, 11));
    lbUserName.setText("UserName:");
    lbPassWord.setFont(new java.awt.Font("Times New Roman", 1, 11));
    lbPassWord.setText("PassWord:");
    btThoat.setFont(new java.awt.Font("Times New Roman", 1, 11));
    btThoat.setForeground(Color.blue);
    btThoat.setText("Thoát");
    btThoat.addActionListener(new frDangNhap_btThoat_actionAdapter(this));
    pfPassWordDN.setToolTipText("Nhap vao PassWord!");
    pfPassWordDN.setText("");
    btXoa.setText("Xóa");
    btXoa.addActionListener(new frDangNhap_btXoa_actionAdapter(this));
    btXoa.setFont(new java.awt.Font("Times New Roman", 1, 11));
    btXoa.setForeground(Color.blue);
    btDangNhap.setFont(new java.awt.Font("Times New Roman", 1, 11));
    btDangNhap.setForeground(Color.blue);
    btDangNhap.setText("Ðãng nh\u1EADp");
    btDangNhap.addActionListener(new frDangNhap_btDangNhap_actionAdapter(this));
    tfUserNameDN.setToolTipText("Nhap vao UserName!");
    tfUserNameDN.setText("");
    this.getContentPane().setLayout(xYLayout1);
    btDangKy.setFont(new java.awt.Font("Times New Roman", 1, 11));
    btDangKy.setForeground(Color.blue);
    btDangKy.setText("Ðãng k\u00FD");
    btDangKy.addActionListener(new frDangNhap_btDangKy_actionAdapter(this));
    this.getContentPane().setBackground(Color.cyan);
    this.setCursor(null);
    this.setEnabled(true);
    this.setJMenuBar(null);
    this.setResizable(false);
    this.setTitle("Dang nhap tai khoan");
    xYLayout1.setWidth(325);
    xYLayout1.setHeight(255);
    this.getContentPane().add(btDangNhap, new XYConstraints(12, 170, 95, 34));
    this.getContentPane().add(btDangKy, new XYConstraints(114, 170, 95, 34));
    this.getContentPane().add(btXoa, new XYConstraints(217, 170, 95, 34));
    this.getContentPane().add(btThoat, new XYConstraints(163, 211, 95, 34));
    this.getContentPane().add(tfUserNameDN, new XYConstraints(78, 76, 180, 23));
    this.getContentPane().add(lbTieuDe, new XYConstraints(16, 14, -1, 28));
    this.getContentPane().add(lbUserName, new XYConstraints(46, 48, -1, 20));
    this.getContentPane().add(lbPassWord, new XYConstraints(46, 107, 60, 20));
    this.getContentPane().add(pfPassWordDN, new XYConstraints(78, 133, 180, 23));
  }

  void btThoat_actionPerformed(ActionEvent e) {
    C_aglet.dispose();
    this.setVisible(false);
  }

  void btDangKy_actionPerformed(ActionEvent e) {
    if (tfUserNameDN.getText().length() == 0 ||
        pfPassWordDN.getPassword().length == 0) {
      JOptionPane.showMessageDialog(this,
                                    "Username va Password khong duoc de trong!");
    }
    else {
      C_aglet.username = tfUserNameDN.getText().trim();
      C_aglet.password = "";
      char[] passwordArray = pfPassWordDN.getPassword();
      for (int i = 0; i < pfPassWordDN.getPassword().length; i++) {
        C_aglet.password += passwordArray[i];
      }
      C_aglet.DangKy(C_aglet.username, C_aglet.password);
    }
  }

  void btXoa_actionPerformed(ActionEvent e) {
    tfUserNameDN.setText("");
    pfPassWordDN.setText("");
  }

  void btDangNhap_actionPerformed(ActionEvent e) {
    if (tfUserNameDN.getText().length() == 0 ||
        pfPassWordDN.getPassword().length == 0) {
      JOptionPane.showMessageDialog(this,
                                    "Username va Password khong duoc de trong!");
    }
    else {
      C_aglet.username = tfUserNameDN.getText().trim();
      C_aglet.password = "";
      char[] passwordArray = pfPassWordDN.getPassword();
      for (int i = 0; i < pfPassWordDN.getPassword().length; i++) {
        C_aglet.password += passwordArray[i];
      }
      C_aglet.DangNhap(C_aglet.username, C_aglet.password);
    }
  }

}

class frDangNhap_btThoat_actionAdapter
    implements java.awt.event.ActionListener {
  frDangNhap adaptee;

  frDangNhap_btThoat_actionAdapter(frDangNhap adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btThoat_actionPerformed(e);
  }
}

class frDangNhap_btDangKy_actionAdapter
    implements java.awt.event.ActionListener {
  frDangNhap adaptee;

  frDangNhap_btDangKy_actionAdapter(frDangNhap adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btDangKy_actionPerformed(e);
  }
}

class frDangNhap_btXoa_actionAdapter
    implements java.awt.event.ActionListener {
  frDangNhap adaptee;

  frDangNhap_btXoa_actionAdapter(frDangNhap adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btXoa_actionPerformed(e);
  }
}

class frDangNhap_btDangNhap_actionAdapter
    implements java.awt.event.ActionListener {
  frDangNhap adaptee;

  frDangNhap_btDangNhap_actionAdapter(frDangNhap adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btDangNhap_actionPerformed(e);
  }
}
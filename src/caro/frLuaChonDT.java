package caro;

import javax.swing.*;
import java.awt.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class frLuaChonDT
    extends JFrame {
  JButton btNguoivsNguoi = new JButton();
  XYLayout xYLayout1 = new XYLayout();
  JButton btNguoivsMay = new JButton();
  JLabel lbTieuDe = new JLabel();
  private AgletClient C_aglet = null;
  public frLuaChonDT(AgletClient aglet) {
    C_aglet = aglet;
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    btNguoivsNguoi.setFont(new java.awt.Font("Times New Roman", 1, 15));
    btNguoivsNguoi.setForeground(Color.blue);
    btNguoivsNguoi.setText("Ngý\u1EDDi vs Ngý\u1EDDi");
    btNguoivsNguoi.addActionListener(new
                                     frLuaChonDT_btNguoivsNguoi_actionAdapter(this));
    this.getContentPane().setLayout(xYLayout1);
    btNguoivsMay.setFont(new java.awt.Font("Times New Roman", 1, 15));
    btNguoivsMay.setForeground(Color.blue);
    btNguoivsMay.setText("Ngý\u1EDDi vs Máy");
    btNguoivsMay.addActionListener(new frLuaChonDT_btNguoivsMay_actionAdapter(this));
    lbTieuDe.setFont(new java.awt.Font("Times New Roman", 1, 20));
    lbTieuDe.setForeground(Color.red);
    lbTieuDe.setText("L\u1EF0A CH\u1ECCN Ð\u1ED0I TÝ\u1EE2NG CHÕI");
    xYLayout1.setWidth(305);
    xYLayout1.setHeight(180);
    this.getContentPane().setBackground(Color.cyan);
    this.setResizable(false);
    this.setTitle("Chon doi tuong choi");
    this.addWindowListener(new frLuaChonDT_this_windowAdapter(this));
    this.getContentPane().add(lbTieuDe, new XYConstraints(9, 4, 296, 47));
    this.getContentPane().add(btNguoivsNguoi, new XYConstraints(37, 53, 233, 49));
    this.getContentPane().add(btNguoivsMay, new XYConstraints(37, 116, 233, 49));
  }

  void btNguoivsNguoi_actionPerformed(ActionEvent e) {
    C_aglet.ChoiVoiNguoi();
  }

  void this_windowClosing(WindowEvent e) {
    C_aglet.ThoatDanhSach();
    dispose();
  }

  void btNguoivsMay_actionPerformed(ActionEvent e) {
    C_aglet.ChoiVoiMay();
  }

}

class frLuaChonDT_btNguoivsNguoi_actionAdapter
    implements java.awt.event.ActionListener {
  frLuaChonDT adaptee;

  frLuaChonDT_btNguoivsNguoi_actionAdapter(frLuaChonDT adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btNguoivsNguoi_actionPerformed(e);
  }
}

class frLuaChonDT_this_windowAdapter
    extends java.awt.event.WindowAdapter {
  frLuaChonDT adaptee;

  frLuaChonDT_this_windowAdapter(frLuaChonDT adaptee) {
    this.adaptee = adaptee;
  }

  public void windowClosing(WindowEvent e) {
    adaptee.this_windowClosing(e);
  }
}

class frLuaChonDT_btNguoivsMay_actionAdapter
    implements java.awt.event.ActionListener {
  frLuaChonDT adaptee;

  frLuaChonDT_btNguoivsMay_actionAdapter(frLuaChonDT adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btNguoivsMay_actionPerformed(e);
  }
}
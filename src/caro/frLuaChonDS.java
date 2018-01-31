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

public class frLuaChonDS
    extends JFrame {
  XYLayout xYLayout1 = new XYLayout();
  JLabel lbTieuDe = new JLabel();
  JButton btChon = new JButton();
  JButton btCapNhat = new JButton();
  JButton btThoat = new JButton();
  private AgletClient C_aglet = null;
  JList jlist = new JList();

  public frLuaChonDS(AgletClient aglet) {
    C_aglet = aglet;
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    xYLayout1.setWidth(395);
    xYLayout1.setHeight(274);
    this.getContentPane().setLayout(xYLayout1);
    this.getContentPane().setBackground(Color.cyan);
    this.setLocale(java.util.Locale.getDefault());
    this.setResizable(false);
    this.setTitle("Danh sach nguoi choi");
    this.addWindowListener(new frLuaChonDS_this_windowAdapter(this));
    lbTieuDe.setFont(new java.awt.Font("Times New Roman", 1, 25));
    lbTieuDe.setForeground(Color.red);
    lbTieuDe.setText("DANH SÁCH NGÝ\u1EDCI CHÕI");
    btChon.setFont(new java.awt.Font("Times New Roman", 1, 13));
    btChon.setForeground(Color.blue);
    btChon.setText("Ch\u1ECDn");
    btChon.addActionListener(new frLuaChonDS_btChon_actionAdapter(this));
    btCapNhat.setFont(new java.awt.Font("Times New Roman", 1, 13));
    btCapNhat.setForeground(Color.blue);
    btCapNhat.setText("C\u1EADp nh\u1EADt DS");
    btCapNhat.addActionListener(new frLuaChonDS_btCapNhat_actionAdapter(this));
    btThoat.setFont(new java.awt.Font("Times New Roman", 1, 13));
    btThoat.setForeground(Color.blue);
    btThoat.setText("Thoát");
    btThoat.addActionListener(new frLuaChonDS_btThoat_actionAdapter(this));
    this.getContentPane().add(btChon, new XYConstraints(16, 220, 100, 42));
    this.getContentPane().add(btCapNhat, new XYConstraints(123, 220, 150, 42));
    this.getContentPane().add(btThoat, new XYConstraints(279, 220, 100, 42));
    this.getContentPane().add(jlist, new XYConstraints(17, 57, 361, 148));
    this.getContentPane().add(lbTieuDe, new XYConstraints(40, 11, 309, 37));
  }

  public void lDanhSach(Object DanhSachNguoiChoi) {
  }

  void btThoat_actionPerformed(ActionEvent e) {
    C_aglet.dispose();
    C_aglet.ThoatDanhSach();
    this.setVisible(false);
  }

  void btCapNhat_actionPerformed(ActionEvent e) {
    C_aglet.CapNhatDanhSach();
  }

  void this_windowClosing(WindowEvent e) {
    C_aglet.ThoatDanhSach();
    dispose();
  }

  void btChon_actionPerformed(ActionEvent e) {
    C_aglet.doithu = (String) jlist.getSelectedValue();
    JOptionPane.showMessageDialog(this,
                                  "Da chon duoc nguoi choi: " + C_aglet.doithu);
    if (jlist.getSelectedValue() == null) {
      JOptionPane.showMessageDialog(this, "Vui long chon nguoi de choi!");
    }
    else {
      C_aglet.Chon();
    }
  }
}

class frLuaChonDS_btThoat_actionAdapter
    implements java.awt.event.ActionListener {
  frLuaChonDS adaptee;

  frLuaChonDS_btThoat_actionAdapter(frLuaChonDS adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btThoat_actionPerformed(e);
  }
}

class frLuaChonDS_btCapNhat_actionAdapter
    implements java.awt.event.ActionListener {
  frLuaChonDS adaptee;

  frLuaChonDS_btCapNhat_actionAdapter(frLuaChonDS adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btCapNhat_actionPerformed(e);
  }
}

class frLuaChonDS_this_windowAdapter
    extends java.awt.event.WindowAdapter {
  frLuaChonDS adaptee;

  frLuaChonDS_this_windowAdapter(frLuaChonDS adaptee) {
    this.adaptee = adaptee;
  }

  public void windowClosing(WindowEvent e) {
    adaptee.this_windowClosing(e);
  }
}

class frLuaChonDS_btChon_actionAdapter
    implements java.awt.event.ActionListener {
  frLuaChonDS adaptee;

  frLuaChonDS_btChon_actionAdapter(frLuaChonDS adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btChon_actionPerformed(e);
  }
}
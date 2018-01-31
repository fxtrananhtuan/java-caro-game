package caro;

import com.ibm.aglet.*;
import java.sql.*;
import javax.swing.*;
import java.util.Vector;
import javax.swing.JList.*;
import java.net.*;
import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class AgletServer
    extends Aglet {
  int i = 0;
  static final int ROWS = 100;
  static final int COLS = 2;
  String[][] listofplayer = new String[ROWS][COLS];
  AgletID[] listofid = new AgletID[ROWS];

 public void onCreation(Object Init){

   getAgletContext().setProperty("IDAgletServer", getAgletID());
    System.out.println("Da gan IDAgletServer xong");
    setText("Da gan IDAgletServer xong");

 }



  public int ConnDBDangNhap(String username, String password) {
    int c = 0;
    Connection con;
    Statement stm;
    String url = "jdbc:odbc:Caro";
    ResultSet rss;
    try {
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    }
    catch (ClassNotFoundException ex) {
      System.err.print("Class Driver khong tim thay!");
    }
    try {
      DriverManager.getDriver(url);
      con = DriverManager.getConnection("jdbc:odbc:Caro");
      stm = con.createStatement();
      rss = stm.executeQuery("SELECT PassWord FROM DANGNHAP WHERE UserName='" +
                             username + "'");
      if (rss.next()) {
        if (rss.getObject("PassWord").equals(password)) {
          setText("Dang nhap thanh cong!");
          System.out.println("Dang nhap thanh cong!");
          c = 1;
        }
        else {
          setText("User khong ton tai!");
          System.out.println("User khong ton tai!");
        }
        rss.close();
      }
    }
    catch (SQLException ex) {
      System.out.println("Loi ket noi csdl Caro" + ex.toString());
    }
    return c;
  }

  public int ConnDBDangKy(String username, String password) {
    int c = 0;
    Connection con;
    Statement stm;
    String url = "jdbc:odbc:Caro";
    try {
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    }
    catch (ClassNotFoundException ex) {
      System.err.print("Class Driver khong tim thay!");
    }
    try {
      DriverManager.getDriver(url);
      con = DriverManager.getConnection("jdbc:odbc:Caro");
      stm = con.createStatement();
      String query = "INSERT INTO DANGNHAP VALUES('" + username + "','" +
          password + "')";
      try {
        stm.execute(query);
      }
      catch (SQLException ex) {
        return c;
      }
      stm.close();
      c = 1;
    }
    catch (SQLException ex) {
      System.out.println("Loi ket noi csdl Caro" + ex.toString());
    }
    return c;
  }

  public boolean handleMessage(Message msg) {
    if (msg.sameKind("DangNhap")) {
      String username = (String) msg.getArg("UserName");
      String password = (String) msg.getArg("PassWord");
      setText("Da nhan duoc thong diep voi username: " + username +
              " va password: " + password);
      System.out.println("Da nhan duoc thong diep voi username: " + username +
                         " va password: " + password);
      msg.sendReply(ConnDBDangNhap(username, password));
      return true;
    }
    if (msg.sameKind("DangKy")) {
      String username = (String) msg.getArg("UserName");
      String password = (String) msg.getArg("PassWord");
      setText("Da nhan duoc thong diep voi username: " + username +
              " va password: " + password);
      System.out.println("Da nhan duoc thong diep voi username: " + username +
                         " va password: " + password);
      msg.sendReply(ConnDBDangKy(username, password));
      return true;
    }
    if (msg.sameKind("Data")) {
      listofplayer[i][0] = (String) msg.getArg("UserName");

      setText(listofplayer[i][0]);
      try {
        listofid[i] = (AgletID) msg.getArg("ID");
      }
      catch (Exception ex) {
        setText("Loi List of Player");
      }
      ;
      listofplayer[i][1] = (String) msg.getArg("Address");

      System.out.println(msg.getArg("UserName"));
      System.out.println(msg.getArg("ID"));
      System.out.println(msg.getArg("Address"));
      i++;
      return true;
    }

    if(msg.sameKind("ChoiVoiMay"))
    {
      String address=null;
      for(int j=0;j<i;j++)
        if(listofplayer[j][0].compareTo(msg.getArg("UserName").toString())==0)
        {
          address=listofplayer[j][1];
          break;
        }
      XoaKhoiDanhSach(msg.getArg("UserName").toString());
      try {
        AgletProxy ProxyChoiVoiMay = getAgletContext().createAglet(getCodeBase(),
            "caro.AgletChoiVoiMay", address);
        msg.sendReply(1);
      }
      catch (Exception ex1) {
        System.out.println("Loi tao AgletChoiVoiMay");
        msg.sendReply(0);
      }
      return true;
    }

    if (msg.sameKind("DanhSachNguoiChoi")) {
      String[] data = new String[i];
      Vector v = new Vector();
      for (int j = 0; j < i; j++) {
        if (listofplayer[j][0].compareTo(msg.getArg("UserName").toString()) !=
            0) {
          data[j] = listofplayer[j][0];
          v.add(data[j]);
        }
      }
      msg.sendReply(v);
      return true;
    }
    if (msg.sameKind("CapNhatDanhSach")) {
      String[] data = new String[i];
      Vector v = new Vector();
      for (int j = 0; j < i; j++) {
        if (listofplayer[j][0].compareTo(msg.getArg("UserName").toString()) !=
            0) {
          data[j] = listofplayer[j][0];
          v.add(data[j]);
        }
      }
      msg.sendReply(v);
      return true;
    }
    if (msg.sameKind("ThoatDanhSach")) {
      String username = (String) msg.getArg("UserName");
      XoaKhoiDanhSach(username);
      return true;
    }
    if(msg.sameKind("DoiThu"))
    {
      Object data[]=new Object[2];
      for(int j=0;j<i;j++)
      {
        if(listofplayer[j][0].compareTo(msg.getArg().toString())==0)
        {
          data[0]=listofid[j];
          data[1]=listofplayer[j][1];
          break;
        }
      }
      msg.sendReply(data);
      System.out.println("Data chua: "+msg.getArg().toString());
      return true;
    }
    if (msg.sameKind("Xoa Toi-DoiThu")) {
      String Toi = (String) msg.getArg("Toi");
      String DoiThu = (String) msg.getArg("DoiThu");
      XoaKhoiDanhSach(Toi);
      XoaKhoiDanhSach(DoiThu);
      System.out.println("Da xoa "+Toi+" - "+DoiThu+" danh sach xin cap nhat lai 'Danh Sach'!");
      //JOptionPane.showMessageDialog(null,"Da xoa "+Toi+" - "+DoiThu+" danh sach xin cap nhat lai 'Danh Sach'!");
      return true;
    }
    return false;
  }

  public void XoaKhoiDanhSach(String username) {
    int vt = 0;
    for (int j = 0; j < i; j++)
      if (listofplayer[j][0].compareTo(username) == 0) {
        vt = j;
        break;
      }
    for (int j = vt; j < i - 1; j++) {
      listofplayer[j][0] = listofplayer[j + 1][0];
      listofplayer[j][1] = listofplayer[j + 1][1];
      listofid[j] = listofid[j + 1];
    }
    i--;
  }
}
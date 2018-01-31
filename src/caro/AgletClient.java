package caro;

import java.net.*;
import com.ibm.aglet.*;
import com.ibm.aglets.*;
import com.ibm.aglet.event.*;
import com.ibm.aglet.util.*;
import com.ibm.aglet.*;
import javax.swing.*;
import javax.swing.JList.*;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class AgletClient
    extends Aglet {
  String username = "";
  String password = "";
  AgletProxy ProxyChild = null;
  AgletProxy ProxyServer = null;
  AgletProxy ProxyDoiThu = null;
  AgletID IDAgletServer;
  AgletID IDAgletClient;
  URL urlClient = null;
  String ServerAddress = "atp://BlueSky:4430";
  frDangNhap DangNhap;
  frLuaChonDT LuaChonDT;
  frLuaChonDS LuaChonDS;
  Vector v = new Vector();
  String doithu = "";
  Frame banco;
  String loaico;
  boolean lock = false;
  JButton[][] cell = new JButton[MAXROW][MAXCOL];
  private static int MAXROW = 20, MAXCOL = 20;

  public void run() {
    if (DangNhap == null) {
      DangNhap = new frDangNhap(this);
      DangNhap.pack();
      DangNhap.setSize(340, 290);
    }
    DangNhap.show();
    TaoChild();
  }

  public void TaoChild() {
    urlClient = getAgletContext().getHostingURL();
    IDAgletClient = getAgletID();
    Object[] args = new Object[] {
        urlClient, IDAgletClient};
    try {
      ProxyChild = getAgletContext().createAglet(getCodeBase(),
                                                 "caro.AgletClientChild", args);
      System.out.println("Da tao duoc AgletClientChild");
    }
    catch (Exception e) {
      System.out.println("Chua tao duoc AgletClientChild");
    }
    try {
      ProxyChild.dispatch(new URL(ServerAddress));
    }
    catch (Exception e) {
      setText("Khong the gui aglet child di !");
    }
  }

  public void DangNhap(String username, String password) {
    try {
      ProxyServer = getAgletContext().getAgletProxy(new URL(ServerAddress),
          IDAgletServer);
    }
    catch (MalformedURLException ex) {}
    Message msgDangNhap = new Message("DangNhap");
    msgDangNhap.setArg("UserName", username);
    msgDangNhap.setArg("PassWord", password);
    try {
      Object reply = ProxyServer.sendMessage(msgDangNhap);
      System.out.println("Da gui duoc msg chua Username va Password");
      int thanhcong = ( (Integer) reply).intValue();
      if (thanhcong == 1) {
        setText("Dang nhap thanh cong!");
        JOptionPane.showMessageDialog(null, "Dang nhap thanh cong!");
        System.out.println("Dang nhap thanh cong!");
        DangNhap.setVisible(false);
        SendMyData();
        if (LuaChonDT == null) {
          LuaChonDT = new frLuaChonDT(this);
          LuaChonDT.pack();
          LuaChonDT.setSize(320, 220);
        }
        LuaChonDT.show();
      }
      else {
        setText("Dang nhap that bai!");
        JOptionPane.showMessageDialog(null, "Dang nhap that bai!");
        System.out.println("Dang nhap that bai!");
      }
    }
    catch (MessageException ex1) {
      System.out.println("Thong diep bi loi!");
    }
    catch (NotHandledException ex1) {
      System.out.println("a" + ex1.toString());
    }
    catch (InvalidAgletException ex1) {
      System.out.println("b" + ex1.toString());
    }
  }

  public void DangKy(String username, String password) {
    try {
      ProxyServer = getAgletContext().getAgletProxy(new URL(ServerAddress),
          IDAgletServer);
    }
    catch (MalformedURLException ex) {
    }
    Message msgDangKy = new Message("DangKy");
    msgDangKy.setArg("UserName", username);
    msgDangKy.setArg("PassWord", password);
    try {
      Object reply = ProxyServer.sendMessage(msgDangKy);
      System.out.println("Da gui duoc msg chua Username va Password");
      int thanhcong = ( (Integer) reply).intValue();
      if (thanhcong == 1) {
        setText("Dang ky thanh cong!");
        JOptionPane.showMessageDialog(null,
                                      "Dang ky UserName: " + username +
                                      " thanh cong!");
        System.out.println("Dang ky thanh cong!");
        DangNhap.setVisible(false);
        SendMyData();
        if (LuaChonDT == null) {
          LuaChonDT = new frLuaChonDT(this);
          LuaChonDT.pack();
          LuaChonDT.setSize(300, 220);
        }
        LuaChonDT.show();
      }
      else {
        setText("Dang ky that bai!");
        JOptionPane.showMessageDialog(null, "Dang ky that bai!");
        System.out.println("Dang ky that bai!");
      }
    }
    catch (MessageException ex1) {
      System.out.println("Thong diep bi loi!");
    }
    catch (NotHandledException ex1) {
      System.out.println("Loi khong nhan duoc thong diep!");
    }
    catch (InvalidAgletException ex1) {
      System.out.println("Loi khong nhan duoc thong diep!");
    }
  }

  public void SendMyData() {
    Message msgsend = new Message("Data");
    msgsend.setArg("UserName", username);
    msgsend.setArg("ID", getAgletID());
    msgsend.setArg("Address", getCodeBase().toString());
    System.out.println("UserName " + username);
    System.out.println("ID " + getAgletID());
    System.out.println("Address " + getCodeBase().toString());
    try {
      ProxyServer.sendOnewayMessage(msgsend);
    }
    catch (Exception ex) {
      JOptionPane.showMessageDialog(null,
          "Goi du lieu cua ban bi loi! Hoac Server khong hoat dong.", "Error",
          JOptionPane.INFORMATION_MESSAGE);
      dispose();
    }
  }

  public void ChoiVoiMay() {
    Message msgChoiVoiMay = new Message("ChoiVoiMay");
    msgChoiVoiMay.setArg("UserName", username);
    try {
      Object replyChoiVoiMay = ProxyServer.sendMessage(msgChoiVoiMay);
      int thanhcong = ( (Integer) replyChoiVoiMay).intValue();
      if (thanhcong == 1) {
        LuaChonDT.setVisible(false);
        dispose();
      }
      else
        JOptionPane.showMessageDialog(LuaChonDT,
                                      "Ban khong the choi game voi may!");
    }
    catch (MessageException ex) {
      System.out.println("Loi tao tao msgChoiVoiMay");
    }
    catch (NotHandledException ex) {
      System.out.println("Loi tao tao msgChoiVoiMay");
    }
    catch (InvalidAgletException ex) {
      System.out.println("Loi tao tao msgChoiVoiMay");
    }
  }

  public void ChoiVoiNguoi() {
    Message msgChoiVoiNguoi = new Message("DanhSachNguoiChoi");
    msgChoiVoiNguoi.setArg("UserName", username);
    try {
      Object DanhSachNguoiChoi = ProxyServer.sendMessage(msgChoiVoiNguoi);
      v = (Vector) DanhSachNguoiChoi;
      if (LuaChonDS == null) {
        LuaChonDS = new frLuaChonDS(this);
        LuaChonDS.pack();
        LuaChonDS.setSize(410, 310);
      }
      LuaChonDS.jlist.setListData(v);
      LuaChonDS.show();
      LuaChonDT.setVisible(false);
    }
    catch (MessageException ex) {
    }
    catch (NotHandledException ex) {
    }
    catch (InvalidAgletException ex) {
    }
  }

  public void CapNhatDanhSach() {
    Message msgCapNhat = new Message("CapNhatDanhSach");
    msgCapNhat.setArg("UserName", username);
    setText("Gui cap nhat danh sach: " + username);
    System.out.println("Giu cap nhat danh sach: " + username);
    try {
      Object capnhat = ProxyServer.sendMessage(msgCapNhat);
      v = (Vector) capnhat;
      JOptionPane.showMessageDialog(LuaChonDS, v);
      LuaChonDS.jlist.removeAll();
      LuaChonDS.jlist.setListData(v);
    }
    catch (MessageException ex) {
    }
    catch (NotHandledException ex) {
    }
    catch (InvalidAgletException ex) {
    }
  }

  public void ThoatDanhSach() {
    Message msgThoatDanhSach = new Message("ThoatDanhSach");
    msgThoatDanhSach.setArg("UserName", username);
    try {
      ProxyServer.sendOnewayMessage(msgThoatDanhSach);
    }
    catch (InvalidAgletException ex) {
      System.out.println("Khong gui duoc msgThoatDanhSach!");
    }
  }

  void BanCo() {
    banco = new Frame();
    banco.setResizable(false);
    banco.setLayout(null);
    banco.setLocationRelativeTo(null);
    banco.setTitle(username + " choi voi " + doithu);
    System.out.println(username + " choi voi " + doithu);
    int x = 0, y = 0;
    for (int i = 0; i < MAXROW; i++) {
      x = 0;
      y += 30;
      for (int j = 0; j < MAXCOL; j++) {
        cell[i][j] = new JButton();
        cell[i][j].setName(i + "," + j);
        cell[i][j].setText("");
        cell[i][j].setLocation(x, y);
        cell[i][j].setFont(new java.awt.Font("Times New Roman", Font.BOLD, 16));
        cell[i][j].setMargin(new Insets(0, 0, 0, 0));
        cell[i][j].setAlignmentX(Component.CENTER_ALIGNMENT);
        cell[i][j].setAlignmentY(Component.CENTER_ALIGNMENT);
        cell[i][j].addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            JButton btemp = (JButton) e.getSource();
            if (lock == false && btemp.getText().compareTo("") == 0) {
              String[] toado = btemp.getName().split(",");
              setText(toado[0] + "," + toado[1]);
              btemp.setText(loaico);

              Message msgDiChuyen = new Message("DiChuyen");
              msgDiChuyen.setArg("toadoX", toado[0]);
              msgDiChuyen.setArg("toadoY", toado[1]);
              try {
                ProxyDoiThu.sendOnewayMessage(msgDiChuyen);
              }
              catch (InvalidAgletException ex) {
                System.out.println("Gui toa do di that bai!");
              }
              if (KTThangThua(Integer.parseInt(toado[0]),
                              Integer.parseInt(toado[1]))) {
                Message msgThang = new Message("Win");
                try {
                  ProxyDoiThu.sendOnewayMessage(msgThang);
                }
                catch (InvalidAgletException ex1) {
                  System.out.println("Khong gui duoc tin nhan 'Win'");
                }
                JOptionPane.showMessageDialog(banco, "Ban da thang!");
                banco.setVisible(false);
                SendMyData();
                CapNhatDanhSach();
                LuaChonDS.setVisible(true);
              }
              else
                lock = true;
            }
            else
              JOptionPane.showMessageDialog(banco,
                  "Xin cho den luot cua ban hoac o ban chon da co quan co roi !!");
          }
        }
        );
        cell[i][j].setSize(30, 30);
        banco.add(cell[i][j]);
        x += 30;
      }
    }
    banco.setSize(x, y + 31);
    banco.setLocationRelativeTo(null);
    banco.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        banco.setVisible(false);
        Message msgThoatGame = new Message("ThoatGame");
        try {
          ProxyDoiThu.sendOnewayMessage(msgThoatGame);
        }
        catch (InvalidAgletException ex) {
          System.out.println("Khong gui duoc tin nhan 'ThoatGame'");
        }
        dispose();
      }
    });
    banco.setVisible(true);
  }

  //--------------------------Kiem tra thang thua-----------------------------------------//
  boolean KTNgang(int x, int y, String kt) {
    int i = 1, count = 1;
    while (i < 5) {
      if (x + i >= 0 && x + i < MAXCOL && y >= 0 && y < MAXROW &&
          cell[x + i][y].getText().compareTo(kt) == 0)
        count++;
      else
        break;
      i++;
    }
    i = 1;
    while (i < 5) {
      if (x - i >= 0 && x - i < MAXCOL && y >= 0 && y < MAXROW &&
          cell[x - i][y].getText().compareTo(kt) == 0)
        count++;
      else
        break;
      i++;
    }
    return count == 5 ? true : false;
  }

  boolean KTDoc(int x, int y, String kt) {
    int i = 1, count = 1;
    while (i < 5) {
      if (x >= 0 && x < MAXCOL && y + i >= 0 && y + i < MAXROW &&
          cell[x][y + i].getText().compareTo(kt) == 0)
        count++;
      else
        break;
      i++;
    }
    i = 1;
    while (i < 5) {
      if (x >= 0 && x < MAXCOL && y - i >= 0 && y - i < MAXROW &&
          cell[x][y - i].getText().compareTo(kt) == 0)
        count++;
      else
        break;
      i++;
    }
    return count == 5 ? true : false;
  }

  boolean KTCheoChinh(int x, int y, String kt) {
    int i = 1, count = 1;
    while (i < 5) {
      if (x + i >= 0 && x + i < MAXCOL && y + i >= 0 && y + i < MAXROW &&
          cell[x + i][y + i].getText().compareTo(kt) == 0)
        count++;
      else
        break;
      i++;
    }
    i = 1;
    while (i < 5) {
      if (x - i >= 0 && x - i < MAXCOL && y - i >= 0 && y - i < MAXROW &&
          cell[x - i][y - i].getText().compareTo(kt) == 0)
        count++;
      else
        break;
      i++;
    }
    return count == 5 ? true : false;
  }

  boolean KTCheoPhu(int x, int y, String kt) {
    int i = 1, count = 1;
    while (i < 5) {
      if (x + i >= 0 && x + i < MAXCOL && y - i >= 0 && y - i < MAXROW &&
          cell[x + i][y - i].getText().compareTo(kt) == 0)
        count++;
      else
        break;
      i++;
    }
    i = 1;
    while (i < 5) {
      if (x - i >= 0 && x - i < MAXCOL && y + i >= 0 && y + i < MAXROW &&
          cell[x - i][y + i].getText().compareTo(kt) == 0)
        count++;
      else
        break;
      i++;
    }
    return count == 5 ? true : false;
  }

  //--------------------------Kiem tra thang thua-----------------------------------------//
  public boolean KTThangThua(int x, int y) {
    String kt = cell[x][y].getText();
    return KTNgang(x, y, kt) || KTDoc(x, y, kt) || KTCheoChinh(x, y, kt) ||
        KTCheoPhu(x, y, kt);
  }

  public void Chon() {
    Message msgChon = new Message("DoiThu", doithu);
    System.out.println("Chon doi thu: " + doithu);
    try {
      Object DoiThu[] = (Object[]) ProxyServer.sendMessage(msgChon);
      System.out.println("Nhan duoc data gui ve chua ID: " + (AgletID) DoiThu[0] +
                         " va URL: " + DoiThu[1].toString());
      try {
        ProxyDoiThu = getAgletContext().getAgletProxy(new URL(DoiThu[
            1].toString()), (AgletID) DoiThu[0]);
        Message msgsend = new Message("ChoiVoiToi", username);
        FutureReply future = ProxyDoiThu.sendFutureMessage(msgsend);
        future.waitForReply(10000);
        String reply = (String) future.getReply();
        if (reply.compareTo("Yes") == 0 && future.isAvailable()) {
          //System.out.println("da nhan duoc reply cua sendFutureMessage");
          //JOptionPane.showMessageDialog(null,"da nhan duoc reply cua sendFutureMessage");
          Message msgThongbao = new Message("Xoa Toi-DoiThu");
          msgThongbao.setArg("Toi", username);
          msgThongbao.setArg("DoiThu", doithu);
          ProxyServer.sendOnewayMessage(msgThongbao);
          // System.out.println("Da gui msgThongBao 'Xoa "+username+"-"+doithu+"' den server");
          //JOptionPane.showMessageDialog(null,"Da gui msgThongBao 'Xoa "+username+"-"+doithu+"' den server");

          Message mm = new Message("Second Turn");
          mm.setArg("Address", urlClient);
          mm.setArg("ID", getAgletID());
          ProxyDoiThu.sendOnewayMessage(mm);
          System.out.println("Da gui dc Message mm");
          LuaChonDS.setVisible(false);
          JOptionPane.showMessageDialog(null, "Ban di truoc!");
          loaico = "X";
          //Ban co
          BanCo();
        }
        else
          JOptionPane.showMessageDialog(null,
                                        doithu +
                                        " khong muon choi Caro voi ban!");
      }
      catch (MalformedURLException ex1) {
        System.out.println("Khong tao duoc ProxyDoiThu");
      }
    }
    catch (MessageException ex) {
    }
    catch (NotHandledException ex) {
    }
    catch (InvalidAgletException ex) {
    }
  }

  public boolean handleMessage(Message msg) {
    if (msg.sameKind("IDAgletServer")) {
      IDAgletServer = (AgletID) msg.getArg();
      setText("Da nhan duoc IDAgletServer:" + IDAgletServer.toString());
      System.out.println("Da nhan duoc msg chua IDAgletServer: " +
                         IDAgletServer.toString());
      return true;
    }
    if (msg.sameKind("ChoiVoiToi")) {
      int TraLoi = JOptionPane.showConfirmDialog(null,
                                                 "Ban co muon choi voi: " +
                                                 msg.getArg() + "?", "Question",
                                                 JOptionPane.YES_NO_OPTION);
      if (TraLoi == JOptionPane.YES_OPTION) {
        msg.sendReply("Yes");
        LuaChonDS.setVisible(false);
      }
      else
        msg.sendReply("No");
      return true;
    }
    if (msg.sameKind("Second Turn")) {
      lock = true;
      loaico = "0";
      try {
        ProxyDoiThu = getAgletContext().getAgletProxy(new URL(msg.getArg(
            "Address").toString()), (AgletID) msg.getArg("ID"));
        System.out.println("Da nhan duoc msgSecondTurn voi Address: " +
                           msg.getArg("Address").toString() + " va ID: " +
                           (AgletID) msg.getArg("ID"));
      }
      catch (MalformedURLException ex) {
        System.out.println(
            "Loi tao tao ProxyDoiThu khi nhan duoc Message SendTurn");
      }
      //Ban co
      BanCo();
      return true;
    }
    if (msg.sameKind("DiChuyen")) {
      JButton bttmp = cell[Integer.parseInt(msg.getArg("toadoX").toString())][
          Integer.parseInt(msg.getArg("toadoY").toString())];
      if (loaico.compareTo("X") == 0)
        bttmp.setText("0");
      else
        bttmp.setText("X");
      lock = false;
      return true;
    }
    if (msg.sameKind("Win")) {
      JOptionPane.showMessageDialog(banco, "Ban da 'Thua'");
      banco.setVisible(false);
      LuaChonDS.setVisible(true);
      lock = false;
      SendMyData();
      CapNhatDanhSach();
      return true;
    }
    if (msg.sameKind("ThoatGame")) {
      JOptionPane.showMessageDialog(banco, "Doi thu cua ban da 'Thoat Game'");
      LuaChonDS.setVisible(true);
      banco.setVisible(false);
      lock = false;
      SendMyData();
      CapNhatDanhSach();
      return true;
    }
    return false;
  }
}
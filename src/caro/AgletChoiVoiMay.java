package caro;
import java.io.*;
import java.net.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.ibm.aglet.*;
import java.awt.event.ActionEvent;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class AgletChoiVoiMay extends Aglet implements ActionListener{
  String url=null;
  boolean theRemote=false;

  private static int MAXROW=20,MAXCOL=20;
  Frame fBoard;
  JButton[][] cell=new JButton[MAXROW][MAXCOL];

  public void onCreation(Object init)
  {
    url = init.toString();
  }
  public void run()
  {
    if(theRemote==false)
    {
      theRemote=true;
      try{
        dispatch(new URL(url));
      }catch (MalformedURLException ex1 ) { setText ("Loi tao URL");ex1.printStackTrace() ;}
      catch (IOException ex2) {setText("Loi dispatch") ;ex2.printStackTrace() ; }
      catch (AgletException ex3 ) {ex3.printStackTrace() ;}
    }
    else createBoard();
  }
  void createBoard()
  {
    fBoard=new Frame("Caro Board");
    fBoard.setResizable(false);
    fBoard.setLayout(null);
    int x=0,y=0;
    for (int i = 0; i < MAXROW; i++)
    {
      x = 0;
      y += 30;
      for (int j = 0; j < MAXCOL; j++)
      {
        cell[i][j]=new JButton();
        cell[i][j].setName(i+","+j);
        cell[i][j].setLocation(x, y);
        cell[i][j].setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        cell[i][j].setMargin(new Insets(0, 0, 0, 0));
        cell[i][j].setAlignmentX(Component.CENTER_ALIGNMENT);
        cell[i][j].setAlignmentY(Component.CENTER_ALIGNMENT);
        cell[i][j].addActionListener(this);
        cell[i][j].setSize(30,30);
        fBoard.add(cell[i][j]);
        x += 30;
      }
    }
    fBoard.setSize(x,y+31);
    fBoard.setLocationRelativeTo(null);
    fBoard.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e)
    {dispose();}});
    fBoard.setVisible(true);
  }
  public void actionPerformed(ActionEvent e)
  {
    JButton bt=(JButton)e.getSource();
    String[] toado= bt.getName().toString().split(",");
    bt.setText("X");
    if(KTThangThua(Integer.parseInt(toado[0]),Integer.parseInt(toado[1])))
    {
      JOptionPane.showMessageDialog(fBoard, "Ban da thang !!!", "Messenge",
                                    JOptionPane.INFORMATION_MESSAGE);
      dispose();
    }
    ComPlay();
  }
  boolean KTNgang(int x,int y,String kt)
  {
    int i=1,count=1;
    while(i<5)
    {
      if (x+i>=0&&x+i<MAXCOL&&y>=0&&y<MAXROW&&cell[x + i][y].getText().compareTo(kt) == 0)
        count++;
      else
        break;
      i++;
    }
    i=1;
    while(i<5)
    {
      if (x-i>=0&&x-i<MAXCOL&&y>=0&&y<MAXROW&&cell[x - i][y].getText().compareTo(kt) == 0)
        count++;
      else
        break;
      i++;
    }
    return count==5?true:false;
  }
  boolean KTDoc(int x,int y,String kt)
  {
    int i=1,count=1;
    while(i<5)
    {
      if (x>=0&&x<MAXCOL&&y+i>=0&&y+i<MAXROW&&cell[x][y + i].getText().compareTo(kt) == 0)
        count++;
      else
        break;
      i++;
    }
    i=1;
    while(i<5)
    {
      if (x>=0&&x<MAXCOL&&y-i>=0&&y-i<MAXROW&&cell[x][y - i].getText().compareTo(kt) == 0)
        count++;
      else
        break;
      i++;
    }
    return count==5?true:false;
  }
  boolean KTCheoChinh(int x,int y,String kt)
  {
    int i=1,count=1;
    while(i<5)
    {
      if (x+i>=0&&x+i<MAXCOL&&y+i>=0&&y+i<MAXROW&&cell[x + i][y + i].getText().compareTo(kt) == 0)
        count++;
      else
        break;
      i++;
    }
    i=1;
    while(i<5)
    {
      if (x-i>=0&&x-i<MAXCOL&&y-i>=0&&y-i<MAXROW&&cell[x - i][y - i].getText().compareTo(kt) == 0)
        count++;
      else
        break;
      i++;
    }
    return count==5?true:false;
  }
  boolean KTCheoPhu(int x,int y,String kt)
  {
    int i=1,count=1;
    while(i<5)
    {
      if (x+i>=0&&x+i<MAXCOL&&y-i>=0&&y-i<MAXROW&&cell[x + i][y - i].getText().compareTo(kt) == 0)
        count++;
      else
        break;
      i++;
    }
    i=1;
    while(i<5)
    {
      if (x-i>=0&&x-i<MAXCOL&&y+i>=0&&y+i<MAXROW&&cell[x - i][y + i].getText().compareTo(kt) == 0)
        count++;
      else
        break;
      i++;
    }
    return count==5?true:false;
  }
  public boolean KTThangThua(int x,int y)
  {
    String kt=cell[x][y].getText();
    return KTNgang(x,y,kt)||KTDoc(x,y,kt)||KTCheoChinh(x,y,kt)||KTCheoPhu(x,y,kt);
  }
  int longpro=0;
  void ComPlay()
  {
    if (longpro == 0) {
      int cx, cy, i, j, a = 0;
      for (i = 0; i < MAXROW; i++)
        for (j = 0; j < MAXCOL; j++) {
          if (cell[i][j].getText().compareTo("") != 0) {
            if (j > MAXCOL / 2)
              cx = j - 1;
            else
              cx = j + 1;
            if (i > MAXROW / 2)
              cy = i - 1;
            else
              cy = i + 1;
            cell[cy][cx].setText("O");
            longpro = 1;
            a = 1;
            break;
          }
          if (a == 1)
            break;
        }
      if (a == 0) {
        cell[MAXROW / 2 - 1][MAXCOL / 2 - 1].setText("O");
        longpro = 1;
      }
    }
    else
    {
      int cx=0, cy=0, i, j;
      long cv=0, ctv=0;
      for(i=0;i<MAXROW;i++)
        for(j=0;j<MAXCOL;j++)
          if(cell[i][j].getText().compareTo("")==0)
          {
            cx=j;cy=i;
            cv=valuecong(j,i);
            ctv=valuethu(j,i);
            break;
          }
      int tx=0,ty=0;
      long tv=0,tcv=0;
      for(i=0;i<MAXROW;i++)
        for(j=0;j<MAXCOL;j++)
          if(cell[i][j].getText().compareTo("")==0)
          {
            tx=j;ty=i;
            tv=valuethu(j,i);
            tcv=valuecong(j,i);
            break;
          }
      for(i=0;i<MAXROW;i++)
        for(j=0;j<MAXCOL;j++)
          if(cell[i][j].getText().compareTo("")==0)
          {
            long nc=valuecong(j,i);
            long ntv=valuethu(j,i);
            if((CapDo(cv)==CapDo(nc)&&CapDo(ctv)<CapDo(ntv))||(cv<nc))
            {
              cv=nc;cx=j;cy=i;ctv=ntv;
            }
            long nt=valuethu(j,i);
            long ncv=valuethu(j,i);
            if(( CapDo(tv)==CapDo(nt) && CapDo(tcv)<CapDo(tcv) )||(tv<nt))
            {
              tv=nt;tx=j;ty=i;tcv=ncv;
            }
          }
      if(CapDo(tv)>CapDo(cv))
      {
        cell[ty][tx].setText("O");
        if(KTThangThua(ty,tx))
        {
          JOptionPane.showMessageDialog(fBoard, "Ban da thua!!!", "Messenge",
                                        JOptionPane.INFORMATION_MESSAGE);
          dispose();
        }
      }
      else
      {
        cell[cy][cx].setText("O");
        if(KTThangThua(cy,cx))
        {
          JOptionPane.showMessageDialog(fBoard, "Ban da thua!!!",
                                        "Messenge",
                                        JOptionPane.INFORMATION_MESSAGE);
          dispose();
        }
      }
    }
  }
  int CapDo(long A){return (int)(Math.log(A)/Math.log(4));}
  long mu4(int n)
  {
    long T=1;
    for(int i=1;i<n/2;i++)
      T=T*4;
    if(n%2!=0)
      T=T*2;
    return T;
  }
  long valuethu(int x,int y)
  {
    int dem;
    long s;
    dem=demthu(x,y,1,0);
    if(demchanthu(x,y,1,0)<5)
      dem=dem-4;
    if(dem>=9)
      dem=dem+4;
    s=mu4(dem);
    dem=demthu(x,y,0,1);
    if(demchanthu(x,y,0,1)<5)
      dem=dem-4;
    if(dem>=9)
      dem=dem+4;
    s=s+mu4(dem);
    dem=demthu(x,y,1,1);
    if(demchanthu(x,y,1,1)<5)
      dem=dem-4;
    if(dem>=9)
      dem=dem+4;
    s=s+mu4(dem);
    dem=demthu(x,y,1,-1);
    if(demchanthu(x,y,1,-1)<5)
      dem=dem-4;
    if(dem>=9)
      dem=dem+4;
    s=s+mu4(dem);
    return s;
  }
  long valuecong(int x,int y)
  {
    int dem;
    long s=0;
    dem=demcong(x,y,1,0);
    if(demchancong(x,y,1,0)<25)
      dem=dem-4;
    if(dem>=9)
      dem=dem+4;
    s=mu4(dem);

    dem=demcong(x,y,0,1);
    if(demchancong(x,y,0,1)<5)
      dem=dem-4;
    if(dem>=9)
      dem=dem+4;
    s=s+mu4(dem);

    dem=demcong(x,y,1,1);
    if (demchancong(x, y, 1, 1) < 5)
      dem = dem - 4;
    if (dem >= 9)
      dem = dem + 4;
    s = s + mu4(dem);

    dem=demcong(x,y,1,-1);
    if (demchancong(x, y, 1, -1) < 5)
      dem = dem - 4;
    if (dem >= 9)
      dem = dem + 4;
    s = s + mu4(dem);

    return s;
  }
  int demthu(int x,int y,int nx,int ny)
  {
    int dem=0;
    int tx=x+nx,ty=y+ny;
    while(ty>=0&&ty<MAXROW&&tx>=0&&tx<MAXCOL&&cell[ty][tx].getText().compareTo("O")!=0&&cell[ty][tx].getText().compareTo("")!=0)
    {
      dem=dem+2;
      tx=tx+nx;
      ty=ty+ny;
    }
    if(ty>=0&&ty<MAXROW&&tx>=0&&tx<MAXCOL&&cell[ty][tx].getText().compareTo("")==0)
      dem++;
    tx=x-nx;
    ty=y-ny;
    while(ty>=0&&ty<MAXROW&&tx>=0&&tx<MAXCOL&&cell[ty][tx].getText().compareTo("O")!=0&&cell[ty][tx].getText().compareTo("")!=0)
    {
      dem=dem+2;
      tx=tx+nx;
      ty=ty+ny;
    }
    if(ty>=0&&ty<MAXROW&&tx>=0&&tx<MAXCOL&&cell[ty][tx].getText().compareTo("")==0)
      dem++;
    return dem;
  }
  int demcong(int x,int y,int nx,int ny)
  {
    int dem=0;
    int tx=x+nx,ty=y+ny;
    while(ty>=0&&ty<MAXROW&&tx>=0&&tx<MAXCOL&&cell[ty][tx].getText().compareTo("O")==0)
    {
      dem=dem+2;
      tx=tx+nx;
      ty=ty+ny;
    }
    if(ty>=0&&ty<MAXROW&&tx>=0&&tx<MAXCOL&&cell[ty][tx].getText().compareTo("")==0)
      dem++;
    tx=x-nx;
    ty=y-ny;
    while(ty>=0&&ty<MAXROW&&tx>=0&&tx<MAXCOL&&cell[ty][tx].getText().compareTo("O")==0)
    {
      dem=dem+2;
      tx=tx+nx;
      ty=ty+ny;
    }
    if(ty>=0&&ty<MAXROW&&tx>=0&&tx<MAXCOL&&cell[ty][tx].getText().compareTo("")==0)
      dem++;
    return dem;
  }
  int demchanthu(int x,int y,int nx,int ny)
  {
    int demchan=1;
    int tx=x+nx,ty=y+ny;
    while(ty>=0&&ty<MAXROW&&tx>=0&&tx<MAXCOL&&(cell[ty][tx].getText().compareTo("O")!=0||cell[ty][tx].getText().compareTo("")==0))
    {
      demchan++;
      tx=tx+nx;
      ty=ty+ny;
    }
    tx=x-nx;
    ty=y-ny;
    while(ty>=0&&ty<MAXROW&&tx>=0&&tx<MAXCOL&&(cell[ty][tx].getText().compareTo("O")!=0||cell[ty][tx].getText().compareTo("")==0))
    {
      demchan++;
      tx=tx-nx;
      ty=ty-ny;
    }
    return demchan;
  }
  int demchancong(int x,int y,int nx,int ny)
  {
    int demchan=1;
    int tx=x+nx,ty=y+ny;
    while(ty>=0&&ty<MAXROW&&tx>=0&&tx<MAXCOL&&(cell[ty][tx].getText().compareTo("O")==0||cell[ty][tx].getText().compareTo("")==0))
    {
      demchan++;
      tx=tx+nx;
      ty=ty+ny;
    }
    tx=x-nx;
    ty=y-ny;
    while(ty>=0&&ty<MAXROW&&tx>=0&&tx<MAXCOL&&(cell[ty][tx].getText().compareTo("O")==0||cell[ty][tx].getText().compareTo("")==0))
    {
      demchan++;
      tx=tx-nx;
      ty=ty-ny;
    }
    return demchan;
  }

}
package caro;
import java.net.*;

import com.ibm.aglet.*;
import com.ibm.aglet.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class AgletClientChild extends Aglet{
  AgletID IDAgletServer;
  AgletID IDAgletClient;
  URL urlClient;
  public boolean _theRemote=false;
  public void onCreation(Object Init)
  {
    Object args[]=(Object[]) Init;
    urlClient=(URL) args[0];
    IDAgletClient=(AgletID) args[1];
    addMobilityListener(new MobilityAdapter(){
      public void onArrival(MobilityEvent e){
        setText("Da den duoc host server");
        //waitMessage(2000);
        _theRemote=true;
        if(_theRemote==true)
        {
          IDAgletServer=(AgletID)getAgletContext().getProperty("IDAgletServer");
          System.out.println("da lay duoc idserver");
          setText("Da lay duoc idserver!");
          AgletProxy pxAgletClient=getAgletContext().getAgletProxy(urlClient,IDAgletClient);
          Message msg = new Message("IDAgletServer",IDAgletServer);
          System.out.println("Da tao duoc msg chua IDAgletServer");
          try {
           pxAgletClient.sendMessage(msg);
           System.out.println("Da gui msg chua IDAgletServer");
          }
          catch (MessageException ex) {
          }
          catch (NotHandledException ex) {
          }
          catch (InvalidAgletException ex) {
          }
          System.out.println("Da huy AgletClientChild");
          dispose();
        }
      }
    });
  }

}

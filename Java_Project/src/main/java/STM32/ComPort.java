package STM32;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class ComPort
{
	public static double humidityValue=0.0,TemperaturValue=0.0;
    public ComPort()
    {
        super();
    }
    
    void connect ( String portName ) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use");
        }
        else
        {
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
        
            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
             
                serialPort.setSerialPortParams(921600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                
                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();
                
                (new Thread(new SerialReader(in))).start();
            

            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }     
    }
    
    /** */
    public static class SerialReader implements Runnable 
    {
        InputStream in;
        
        public SerialReader ( InputStream in )
        {
            this.in = in;
        }
        
        public void run ()
        {
            byte[] buffer = new byte[1024];
            int len = -1;
            try
            {
                while ( ( len = this.in.read(buffer)) > -1 )
                {
                	try{
                	String msg=new String(buffer,0,len);
                	if(msg!=null && msg.length()>2)
                	{
                	//	System.out.println("result is = "+msg);
                
                	if(msg.contains("HUM") || msg.contains("TEMP"))
                	{
                		String[] items=msg.split(("\\r?\\n"));
                		for(int i=0;i<items.length;i++)
                		{
                			if(items[i].contains("HUM"))
                			{
                				
                				String[] values=items[i].trim().split(":");
                				if(values.length >= 2)
                				{
                					
                					humidityValue=Double.parseDouble(values[1].trim());
                					System.out.println("humidity is= "+humidityValue);
                				}
                			}
                			if(items[i].contains("TEMP"))
                			{
                				String[] values=items[i].trim().split(":");
                				if(values.length >= 2)
                				{
                					TemperaturValue=Double.parseDouble(values[1].trim());
                					System.out.println("Temperatur is= "+TemperaturValue);
                				}
                			}
                		}
                	
                	}
                	}
                	}
                	catch(Exception ex )
                	{
                		System.out.println("++++++++Error+++++++"+ex.getMessage());
                	}
                	
                   
                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }            
        }
    }

  
    
    public static void main ( String[] args )
    {
        try
        {
        	ComPort con=new ComPort();
        	con.connect("COM6");
        	
         
        }
        catch ( Exception e )
        {
           
            e.printStackTrace();
        }
    }
}
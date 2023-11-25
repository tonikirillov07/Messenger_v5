package com.darkstudio.messenger_v5;

import com.sun.management.OperatingSystemMXBean;

import java.awt.*;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.List;

public class CheckMinimumSysReq {
    private static final int RAM_LIMIT = 1;
    private long RAMSize = 0;
    private String systemName = "";
    private final List<String> supportOS = Arrays.asList("Windows 11", "Windows 10");
    private int code = 0;
    private final int[] screenSize = {1000, 600};
    ShowExceptions showExceptions = new ShowExceptions();

    public void init(){
        try {
            RAMSize = (long) (((((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalMemorySize() / 1024 / 1024)) * 0.000977);
            systemName = System.getProperty("os.name");
        }catch (Exception e){
            showExceptions.showException(e);
        }
    }

    public boolean systemIsMeetMinSysReq(){
        return (RAMSize >= RAM_LIMIT & supportOS.contains(systemName) & checkSizeOfScreen());
    }

    private boolean checkSizeOfScreen(){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        return (dimension.width >= screenSize[0] & dimension.height >= screenSize[1]);
    }

    public String getReason(){
        String msg = null;
        if(!systemIsMeetMinSysReq()){
            if(RAMSize < RAM_LIMIT & !supportOS.contains(systemName) & !checkSizeOfScreen()){
                msg = "The system does not meet the minimum system requirements because: \n+1.Your RAM must to be not little then "+RAM_LIMIT+ "Gb" +"\n 2.Your system is not supported. Supported systems: "+supportOS+"\n" +
                        "3.Your screen size must to be not little then "+ Arrays.toString(screenSize);
                code = 1;
            }else if(RAMSize < RAM_LIMIT){
                msg = "The system does not meet the minimum system requirements because: \n Your RAM must to be not little then "+RAM_LIMIT + "Gb";
                code = 2;
            }else if(!supportOS.contains(systemName)){
                msg = "The system does not meet the minimum system requirements because: \n Your system is not supported. Supported systems: "+supportOS.toString().substring(1, supportOS.toString().length()-1);
                code = 3;
            }else if(!checkSizeOfScreen()){
                msg = "The system does not meet the minimum system requirements because: \n Your screen size must to be not little then "+ Arrays.toString(screenSize);
                code = 4;
            }
        } else {
            msg = "The system meets the minimum system requirements";
            code = 0;
        }
        return msg;
    }

    public int getCode() {
        getReason();
        return code;
    }
}

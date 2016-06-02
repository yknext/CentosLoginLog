package microserver.log;

/**
 * 日志监听器
 * @author next
 *
 */
public abstract interface LogFileTailerListener {      
    public abstract void newLogFileLine(String line);      
}

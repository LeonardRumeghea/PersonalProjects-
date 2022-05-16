import static java.lang.Thread.sleep;

public class Timer implements Runnable{

    private int minutes = 0;
    private int seconds = 0;

    private void display() {
        String buffer = minutes < 10 ? "0" + minutes : "" + minutes;
        buffer = seconds < 10 ? buffer + ":0" + seconds : buffer + ":" + seconds;

        System.out.println(buffer);
    }

    @Override
    public void run() {

        while (true) {
            try {
                sleep(1000);
                seconds++;
                if (seconds == 60) {
                    minutes++;
                    seconds = 0;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        String buffer = minutes < 10 ? "0" + minutes : "" + minutes;
        buffer = seconds < 10 ? buffer + ":0" + seconds : buffer + ":" + seconds;

        return buffer;
    }
}

package reservation.Service.Exception;

public class WaitingException extends Exception{
    int waitingNumber;

    public WaitingException(int waitingNumber) {
        this.waitingNumber = waitingNumber;
    }

    public int getWaitingNumber() {
        return waitingNumber;
    }
}

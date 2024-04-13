package reservation.DTO.Exception;

public class WaitingException extends ResponseException {

    private String message = "아직 대기열에 있습니다. ";
    int waitingNumber;

    public WaitingException(int waitingNumber) {
        this.waitingNumber = waitingNumber;
        this.setRejectedValues(this.waitingNumber);
    }

    public int getWaitingNumber() {
        return waitingNumber;
    }
}

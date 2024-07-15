package ua.comparus.users.aggregator.exception;

public class DatasourceOperationException extends RuntimeException {
    public DatasourceOperationException(String message, Throwable e) {
        super(message, e);
    }
}

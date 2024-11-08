package castaldosamuele.progetto_java_settimana7.Exceptions;

public class BadRequestException extends RuntimeException {
	public BadRequestException(String msg) {
		super(msg);
	}
}

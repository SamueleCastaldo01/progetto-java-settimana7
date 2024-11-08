package castaldosamuele.progetto_java_settimana7.Exceptions;

public class NotFoundException extends RuntimeException {
	public NotFoundException(long id) {
		super("Il record con id " + id + " non è stato trovato!");
	}

	public NotFoundException(String msg) {
		super(msg);
	}
}

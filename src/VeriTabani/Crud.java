package VeriTabani;

import java.util.List;

public interface Crud<T> {

	Boolean Create(T item);
	Boolean Update(T item);
	Boolean Delete(int id);
	List<T> Select( );
	T ListSelectById(int id);
	
}

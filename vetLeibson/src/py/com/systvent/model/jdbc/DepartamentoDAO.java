package py.com.systvent.model.jdbc;
import java.util.List;
import py.com.systvent.bean.dto.DepartamentoDTO;
import java.sql.SQLException;
/**
*Esta interfaz contiene los métodos abstractos con las
* operaciones básicas sobre la tabla de Departamento
* CRUD (Create, Read, Update y Delete)
* Se debe crear una clase concreta para implementar el
* código asociado a cada método
*/
public interface DepartamentoDAO {
    public int insert(DepartamentoDTO departamento)
        throws SQLException;
    public int update(DepartamentoDTO departamento)
        throws SQLException;
    public int delete(DepartamentoDTO departamento)
        throws SQLException;
    public List<DepartamentoDTO> select() 
        throws SQLException;
 }

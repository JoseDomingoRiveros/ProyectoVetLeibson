package py.com.systvent.model.jdbc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import py.com.systvent.bean.dto.DepartamentoDTO;
/**
*
* Esta clase almacena las consultas que vamos a usar (los query INSERT, UPDATE, DELETE y SELECT)
* Esta clase implementa la interface DepartamentoDao es una implementación con la
* tecnología JDBC podría haber otro tipo de implementaciones con tecnologías
* como EclipseLink, Hibernate, JPA; TopLink, etc. La interface es independiente
* de la tecnología, pero en su implementación se puede aplicar JDBC.
* Definimos la clase ProductoDaoJDBC e implementamos la interface ProductoDAO
* Al utilizar la implementación de una interface estamos obligados a agregar una
* implementación de cada uno de los métodos definidos en la interface
* Es en este punto donde estamos usando patrones de diseño y utilizando el API
* JDBC para nuestra capa de datos.
* La variable userConn almacena la dirección de la BD que le proporcionemos
*/
public class DepartamentoDaoJDBC implements DepartamentoDAO{
 private Connection userConn;
 private final String SQL_INSERT = "INSERT INTO departamento(nombre_dep) VALUES(?)";
 private final String SQL_UPDATE = "UPDATE departamento SET nombre_dep=? WHERE id_dep=?";
 private final String SQL_DELETE = "DELETE FROM departamento WHERE id_dep = ?";
 private final String SQL_SELECT = "SELECT id_dep, nombre_dep FROM departamento";
 /**
 * Constructor vacio
 */
 public DepartamentoDaoJDBC() {
 }
 /**
 * Constructor DepartamentoDaoJDBC recibe una conexion, este constructor
 *Es útil para manejo de transacciones ya que nos asegura una única conexion
 * durante el tiempo de vida de este objeto y una vez que asignamos una
 * conexion no la vamos a cerrar durante el tiempo de vida del objeto y cada
 * vez que utilicemos una operación CRUD vamos a utilizar la misma conexion
 * Esto es para que al final se pueda hacer un commit o rollback de toda la
 * transacción
 *
 * @param conn
 */
 public DepartamentoDaoJDBC(Connection conn) {
 this.userConn = conn;
 }
 /**
 * El método insert recibe como argumento un objeto DTO el cual viene de
 * otra capa, y se extraen sus valores para crear un nuevo registro, por eso
 * este método es del tipo Data Transfer Object (DTO)
 * El Overrride aparece porque estamos sobrescribiendo un método definido en la
 * interface
 */
 @Override
 public int insert(DepartamentoDTO departamento) throws SQLException {
 //Declaración de las variables
 Connection conn = null;
 PreparedStatement stmt = null;
 int rows = 0; //para saber el número de registros que han sido modificados
 try {
 conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
 stmt = conn.prepareStatement(SQL_INSERT);
 int index = 1;
 stmt.setString(index++, departamento.getNombre_departamento());
 System.out.println("Ejecutando query:" + SQL_INSERT);
 rows = stmt.executeUpdate();
 System.out.println("Registros afectados:" + rows);
 } finally {
 Conexion.close(stmt);
 if (this.userConn == null) {
 Conexion.close(conn);
 }
 }
 return rows;
 }
 /**
 * El método update recibe un objeto departamentoDTO el cual encapsula la
 * información en un solo objeto y evitamos pasar los 3 parámetros de manera
 * aislada, Después extraemos la información del objeto y actualizamos el
 * registro seleccionado
 *En un DTO transferimos todo el objeto y no solo parte del objeto
 * Este objeto DepartamentoDTO tiene los datos que necesitamos para manejar
 * el metodo update
 */
 @Override
 public int update(DepartamentoDTO departamento)
 throws SQLException {
 Connection conn = null;
 PreparedStatement stmt = null;
 int rows = 0;
 try {
     conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
 System.out.println("Ejecutando query:" + SQL_UPDATE);
 stmt = conn.prepareStatement(SQL_UPDATE);
 int index = 1;//definimos nuestro parámetro con índice 1
 stmt.setString(index++, departamento.getNombre_departamento());
 stmt.setInt(index, departamento.getCod_departamento());
 rows = stmt.executeUpdate();
 System.out.println("Registros actualizados:" + rows);
 } finally {
 Conexion.close(stmt);
 if (this.userConn == null) {
 Conexion.close(conn);
 }
 }
 return rows;
 }
 /**
 * Recibimos un objeto DepartamentoDTO no necesariamente debe venir lleno, sino
 * solo nos importa el atributo id_dep
 */
 @Override
 public int delete(DepartamentoDTO departamento) throws SQLException {
 Connection conn = null;
 PreparedStatement stmt = null;
 int rows = 0;
 try {
 conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
 System.out.println("Ejecutando query:" + SQL_DELETE);
 stmt = conn.prepareStatement(SQL_DELETE);
 stmt.setInt(1, departamento.getCod_departamento());
 rows = stmt.executeUpdate();
 System.out.println("Registros eliminados:" + rows);
 } finally {
 Conexion.close(stmt);
 if (this.userConn == null) {
 Conexion.close(conn);
 }
 }
 return rows;
 }
 /**
 * Por último definimos el método select el cual regresa una lista de objetos
 * de tipo DTO
 * En este método utilizamos el objeto DepartamentoDTO para llenar una lista y
 * regresarla
 *
 */
 @Override
 public List<DepartamentoDTO> select() throws SQLException {
 Connection conn = null;
 PreparedStatement stmt = null;
 ResultSet rs = null;
 DepartamentoDTO departamentoDTO = null;
 List<DepartamentoDTO> departamentos = new ArrayList<DepartamentoDTO>();
 try {
 conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
 stmt = conn.prepareStatement(SQL_SELECT);
 rs = stmt.executeQuery();
 while (rs.next()) {
 //Por cada registro se recuperan los valores
 //de las columnas y se crea un objeto DTO
 //En este punto hay que pasar los valores en el mismo orden que se le paso al select
 int codDepartamentoTemp = rs.getInt(1);
 String nombreDepartamentoTemp = rs.getString(2);

 //Llenamos el DTO y lo agregamos a la lista
 departamentoDTO = new DepartamentoDTO();
 departamentoDTO.setCod_departamento(codDepartamentoTemp);
 departamentoDTO.setNombre_departamento(nombreDepartamentoTemp);
 departamentos.add(departamentoDTO);//agregamos a lista DTO
 }
 } finally {
 Conexion.close(rs);
 Conexion.close(stmt);
 if (this.userConn == null) {
 Conexion.close(conn);
 }
 }
 return departamentos;
 }

}
 
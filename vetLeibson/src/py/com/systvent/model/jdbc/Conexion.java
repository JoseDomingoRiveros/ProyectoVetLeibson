package py.com.systvent.model.jdbc;
import java.sql.*;
/**
* Clase Conexion JDBC
* En esta clase definimos algunas variables
*/
/**
*Creamos el método getConnection
 * Para que no haya problemas al obtener la conexion de
 *manera concurrente, se usa la palabra synchronized
 * Creamos los métodos close para cerrar los objetos de tipo ResulSet
 * Creamos otro método close para cerrar los objetos de tipo PreparedStatement
 * Creamos el ultimo método close para cerrar una Connection
*/
public class Conexion {
 //Valores de conexion a MySql
 private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
 //El puerto es opcional, al igual que el parametro useSSL
 private static final String JDBC_URL = "jdbc:mysql://localhost/systvent?useSSL=false";
 private static final String JDBC_USER = "root";
 private static final String JDBC_PASS = "";
 private static Driver driver = null;
 //Para que no haya problemas al obtener la conexion de
 //manera concurrente, se usa la palabra synchronized
 public static synchronized Connection getConnection()
 throws SQLException {
 if (driver == null) {
 try {
 ////Se registra el driver y se levanta el driver MySQL
 Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
 driver = (Driver) jdbcDriverClass.newInstance();
 DriverManager.registerDriver(driver);
 } catch (Exception e) {
 System.out.println("Fallo en cargar el driver JDBC");
 e.printStackTrace();
 }
 }
 //crea un objeto de tipo conexion
 return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
 }
 //Cierre del resultSet
 public static void close(ResultSet rs) {
 try {
 if (rs != null) {
 rs.close();
 }
 } catch (SQLException sqle) {
 sqle.printStackTrace();
 }
 }
 //Cierre del PrepareStatement
 public static void close(PreparedStatement stmt) {
 try {
 if (stmt != null) {
 stmt.close();
 }
 } catch (SQLException sqle) {
 sqle.printStackTrace();
 }
 }
 //Cierre de la conexion
 public static void close(Connection conn) {
 try {
 if (conn != null) {
 conn.close();
 }
 } catch (SQLException sqle) {
 sqle.printStackTrace();
 }
 }
}
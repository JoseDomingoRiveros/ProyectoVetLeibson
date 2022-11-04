package py.com.systvent.bean.dto;
/**
*La clase tiene una clase vacía que funciona como un constructor y otra con un argumento
* Este argumento debe coincidir con el atributo que se encuentra en la clase Departamento
* por eso proporcionamos id_dep
*/
public class DepartamentoDTO {
    public DepartamentoDTO() {
    }
    public DepartamentoDTO(int id_dep) {
    this.id_dep = id_dep;
    }
 /**Definir los atributos de la clase Departamento
 * Estos atributos deben corresponder a cada una de las columnas
 * de nuestra tabla departamentos
 */
 private int id_dep;
 private String nombre_dep;
 /**
 * Agregamos los métodos get y set para cada uno de los atributos
 * Por ultimo sobrescribimos el método toString() para imprimir el estado
 * de este objeto concatenando el valor de cada atributo del objeto
 */
 public int getCod_departamento() {
 return id_dep;
 }
 public void setCod_departamento(int codDepartamento) {
 id_dep= codDepartamento;
 }
 public String getNombre_departamento() {
 return nombre_dep;
 }
 public void setNombre_departamento(String nombreDepartamento) {
 this.nombre_dep = nombreDepartamento;
 }
 @Override
 public String toString() {
     return "Departamento{" + "id_dep=" + id_dep + ", nombre_dep=" + nombre_dep + '}';
 }

}
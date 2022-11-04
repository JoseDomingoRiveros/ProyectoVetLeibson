package utilitarios;
import java.util.Date;
import java.text.SimpleDateFormat;
public class data
{
 //crear 4 variables, tienen que ser públicas para poder utilizar desde la VP
 public String mes, dia, anho, dia_semana, hora;
 SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
 public void leer_hora()
 {
 Date horaActual = new Date();
 hora = formatoHora.format(horaActual);
 }
 public void el_dato() //método para leer el dato
 {
 Date dato = new Date(); //crear un objeto de tipo Fecha
 // mes = ""+ dato.getMonth();//varia de 0 y el "" convierte a String
 dia = ""+ dato.getDate();
 anho = ""+ (1900+ dato.getYear());//agrega 112 por ser 2012
 // dia_semana = ""+ dato.getDay();//retorna el día de la semana de 0-6
 //dar formato a la fecha
 switch(dato.getMonth()) //0 a 11
 {
 case 0: mes = "enero"; break;
 case 1: mes = "febrero"; break;
 case 2: mes = "marzo"; break;
 case 3: mes = "abril"; break;
 case 4: mes = "mayo"; break;
 case 5: mes = "junio"; break;
 case 6: mes = "julio"; break;
 case 7: mes = "agosto"; break;
 case 8: mes = "septiembre"; break;
 case 9: mes = "octubre"; break;
 case 10: mes = "noviembre"; break;
 case 11: mes = "diciembre"; break;
 }
 switch(dato.getDay())
 {
 case 0: dia_semana = "domingo"; break;
 case 1: dia_semana = "lunes"; break;
 case 2: dia_semana = "martes"; break;
 case 3: dia_semana = "miércoles"; break;
 case 4: dia_semana = "jueves"; break;
 case 5: dia_semana = "viernes"; break;
 case 6: dia_semana = "sábado"; break;
 }
 }
}


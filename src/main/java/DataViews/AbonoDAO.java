
package DataViews;

import Models.Abono;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public class AbonoDAO implements Serializable {

    List<Abono> listaAbonos;
    Conexion conex;
    Abono abono;
    ResultSet result;

    //Constructor sin parámetros, para iniciar una conexion.
    public AbonoDAO() {
        conex = new Conexion();
    }

    //Constructor que recibe el objeto Plan_Pago y abre una nueva conexion.
    public AbonoDAO(Abono abono) {
        conex = new Conexion();
        this.abono = abono;
    }

    public AbonoDAO(List<Abono> listaAbonos, Conexion conex, Abono abono, ResultSet result) {
        this.listaAbonos = listaAbonos;
        this.conex = conex;
        this.abono = abono;
        this.result = result;
    }

    /*Procedimiento para insertar un nuevo abono.
    Nota:Al momento de insertar un nuevo abonos, automaticamente el valor 
    pendiente de el plan de pago se actualiza en el procedimiento de PostGre*/
    public int insertarNuevoAbono(int idPlanPago) {
        /*Se ubica en el siguiente orden: 
        (idplan,idformmadepago,monto a pagar, fecha de pago)*/
        String sentenciaSQL = "Select Ingresar_abono(" + idPlanPago + ","
                + abono.getIdFormaDePago() + "," + abono.getValorAbonado() + ",'"
                + abono.getFechaAbono() + "')";

        //Verificamos la conexion
        if (conex.isEstado()) {

            /*Una vez se asegura que la conexion este correcta.
            Se ejecuta la sentencia ingresada.*/
            return conex.ejecutarProcedimiento(sentenciaSQL);

        }

        return -1;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import constant.EstadoVehiculo;
import exception.ErrorCodVehiculoException;
import exception.ErrorEstadoInvalidoException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author More Siciliano
 */
public class Taller {

    private String nombreTaller;
    private List<Vehiculo> inventarioVehiculos;
    private List<Vehiculo> vehiculosEnReparacion;

    public Taller(String nombreTaller) {
        this.nombreTaller = nombreTaller;
        this.inventarioVehiculos = new ArrayList<>();
        this.vehiculosEnReparacion = new ArrayList<>();
    }

    public void agregarVehiculo(Vehiculo vehiculo) throws ErrorCodVehiculoException {
        for (Vehiculo v : this.inventarioVehiculos) {
            if (v.getCodVehiculo().equals(vehiculo.getCodVehiculo())) {
                throw new ErrorCodVehiculoException("Error. El codigo de este vehiculo ya se encuentra ingresado.");
            }
        }
        this.inventarioVehiculos.add(vehiculo);
    }

    public double calcularCostoReparacion(String codigoVehiculo, int horas) throws ErrorCodVehiculoException {
        for (Vehiculo v : this.vehiculosEnReparacion) {
            if (v.getCodVehiculo().equals(codigoVehiculo)) {
                return v.calcularCostoReparacion(horas);
            }
        }
        throw new ErrorCodVehiculoException("Error. El vehiculo no se encuentra en reparacion.");
    }

    public void iniciarReparacion(String codigoVehiculo, String descripcion) throws ErrorEstadoInvalidoException, ErrorCodVehiculoException {
        for (Vehiculo v : this.inventarioVehiculos) {
            if (v.getCodVehiculo().equals(codigoVehiculo)) {
                if (v.getEstado() != EstadoVehiculo.REPARACION) {
                    v.setEstado(EstadoVehiculo.REPARACION);
                    vehiculosEnReparacion.add(v);
                    v.iniciarReparacion(nombreTaller, descripcion);
                    return;
                } else {
                    throw new ErrorEstadoInvalidoException("Error. El vehiculo ya se encuentra en reparacion");
                }
            }
        }
        throw new ErrorCodVehiculoException("Error. El codigo del vehiculo no existe.");
    }

    public void finalizarReparacion(String codigoVehiculo) throws ErrorEstadoInvalidoException,ErrorCodVehiculoException {
        for (Vehiculo v : this.vehiculosEnReparacion) {
            if (v.getCodVehiculo().equals(codigoVehiculo)) {
                vehiculosEnReparacion.remove(v);
                v.finalizarReparacion();
                return;
            }
        }
        throw new ErrorCodVehiculoException("Error. El codigo del vehiculo no existe.");
        
    }
    
    public List<Vehiculo> traerVehiculoDisponibles() throws ErrorEstadoInvalidoException{
        List<Vehiculo> lstVehiculosDisponible = new ArrayList<>();
        for (Vehiculo v : this.inventarioVehiculos) {
            if(v.getEstado() == EstadoVehiculo.DISPONIBLE){
                lstVehiculosDisponible.add(v);
            }
        }
        if(lstVehiculosDisponible.isEmpty()){
            throw new ErrorEstadoInvalidoException("Error. No existen vehiculos disponibles");
        }
        return lstVehiculosDisponible;
    }
    
    
    public List<Vehiculo> traerVehiculo(EstadoVehiculo estado) throws ErrorEstadoInvalidoException{
        List<Vehiculo> lstVehiculosEstado = new ArrayList<>();
        for (Vehiculo v : this.inventarioVehiculos) {
            if(v.getEstado() == estado){
                lstVehiculosEstado.add(v);
            }
        }
         if(lstVehiculosEstado.isEmpty()){
            throw new ErrorEstadoInvalidoException("Error. No se encontraron vehiculos en el estado: " + estado);
        }
        return lstVehiculosEstado;
    }
    
    public List<String> obtenerHistorialReparaciones(String codigoVehiculo) throws ErrorCodVehiculoException{
        for (Vehiculo v : this.inventarioVehiculos) {
            if (v.getCodVehiculo().equals(codigoVehiculo)) {
                return v.obtenerListaReparacion();
            }
        }
        throw new ErrorCodVehiculoException("Error. El codigo ingresado no existe.");
    }
}

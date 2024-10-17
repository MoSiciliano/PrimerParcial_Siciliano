/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import constant.EstadoVehiculo;
import exception.ErrorCodVehiculoException;
import exception.ErrorEstadoInvalidoException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author More Siciliano
 */
public abstract class Vehiculo {
    protected String codVehiculo;
    protected String modelo;
    protected EstadoVehiculo estado;
    protected double precioBase;
    protected List<String> historialReparaciones = new ArrayList<>();
    protected static final Set<String> codExist = new HashSet<>();

    public Vehiculo(String codVehiculo, String modelo, EstadoVehiculo estado, double precioBase) throws ErrorCodVehiculoException {
        validarCodigoVehiculo(codVehiculo);
        this.codVehiculo = codVehiculo;
        this.modelo = modelo;
        this.estado = estado;
        this.precioBase = precioBase;
        
    }

    public String getCodVehiculo() {
        return codVehiculo;
    }
    
    public EstadoVehiculo getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoVehiculo estado) {
        this.estado = estado;
    }

    public void validarCodigoVehiculo(String codVehiculo) throws ErrorCodVehiculoException{
        if(codVehiculo == null || codVehiculo.length() != 7){
            throw new ErrorCodVehiculoException("Error. El codigo debe contener 7 caracteres.");
        }
        if(!codExist.add(codVehiculo)){
            throw new ErrorCodVehiculoException("Error. El codigo " + codVehiculo +"ya existe.");
        }
        
    }
    protected abstract double calcularCostoReparacion(int horas);
    
    public void iniciarReparacion(String nombreTaller, String descripcion) throws ErrorEstadoInvalidoException{
        if(this.estado == EstadoVehiculo.REPARACION){
            throw new ErrorEstadoInvalidoException("Error. El vehiculo ya se encuentra reparandose");
        }
        setEstado(EstadoVehiculo.REPARACION);
        LocalDate fechaActual = LocalDate.now();
        String mensaje = fechaActual + ": [" + nombreTaller + "]" + descripcion;
        historialReparaciones.add(mensaje);
        System.out.println(mensaje);
    }
    
    public void finalizarReparacion()throws ErrorEstadoInvalidoException {
        if(this.estado != EstadoVehiculo.REPARACION){
            throw new ErrorEstadoInvalidoException("Error. El vehiculo no se esta reparando");
        }
        setEstado(EstadoVehiculo.DISPONIBLE);
        LocalDate fechaActual = LocalDate.now();
        String mensaje = fechaActual + ": fin de la reparacion";
        historialReparaciones.add(mensaje);
        System.out.println(mensaje);
    }
    public List<String> obtenerListaReparacion(){
        for(String mensaje : historialReparaciones){
            System.out.println(mensaje);
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("\nModelo:  %s\nEstado: %s\nPrecio Base: %.2f", modelo, estado, precioBase);
    }
    
    
}

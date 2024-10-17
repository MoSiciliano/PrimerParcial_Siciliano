/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import constant.EstadoVehiculo;
import exception.ErrorCodVehiculoException;

/**
 *
 * @author More Siciliano
 */
public class VehiculoSUV extends Vehiculo{
    private boolean traccionIntegral;
    private static final double INCREMENTO_TRACCION = 1.10; // aumento del 10%
    
    public VehiculoSUV(String codVehiculo, String modelo, EstadoVehiculo estado, double precioBase, boolean traccionIntegral) throws ErrorCodVehiculoException {
        super(codVehiculo, modelo, estado, precioBase);
        this.traccionIntegral = traccionIntegral;
    }

    public boolean esTraccionIntegral(){
       return traccionIntegral;
    }
    
    @Override
    public double calcularCostoReparacion(int horas) {
        double precioFinal = this.precioBase * horas;
        if(esTraccionIntegral()){
            precioFinal *= INCREMENTO_TRACCION;
        }
        return precioFinal;
    }

    @Override
    public String toString() {
        return String.format("%s\n Traccion Integral: %s", super.toString(), traccionIntegral);
    }
    
}

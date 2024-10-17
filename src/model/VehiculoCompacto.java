package model;

import constant.EstadoVehiculo;
import exception.ErrorCodVehiculoException;
import java.time.LocalDateTime;
import java.util.List;

public class VehiculoCompacto extends Vehiculo{
    private int anio;
    private static final double DESCUENTO_NUEVO_VEHICULO = 0.95;

    public VehiculoCompacto(String codVehiculo, String modelo, EstadoVehiculo estado, double precioBase, int anio) throws ErrorCodVehiculoException {
        super(codVehiculo, modelo, estado, precioBase);
        this.anio = anio;
    }

    public boolean esVehiculoNuevo(){
        int anioActual = LocalDateTime.now().getYear();
        return this.anio == anioActual;
        
    }
    
    @Override
    public double calcularCostoReparacion(int horas) {
        double precioFinal = precioBase * horas;
        if(esVehiculoNuevo()){
            precioFinal *= DESCUENTO_NUEVO_VEHICULO;
        }
        return precioFinal;
    }

    @Override
    public String toString() {
        return String.format("%s\n Año %d", super.toString(), anio);
    }
    
    
}


package productotest;

public class Producto {
    // Atributos
    private String nombre;
    private double precio;
    private int cantidad;
    
    // Constructor
    public Producto(String nombre, double precio, int cantidad){
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    
    public Producto(String nombre, double precio){ // Supondrá cantidad = 0
        this(nombre, precio, 0);
    }
    
    public Producto(Producto unProducto){ // Supondrá cantidad = 0
        this(unProducto.getNombre(), unProducto.getPrecio(), 0);
    }
    
    // Métodos
    public String getNombre(){
        char primeraLetra = nombre.toUpperCase().charAt(0);
        String nombreFormateado = primeraLetra + nombre.substring(1);
        return nombreFormateado;
    }
    
    public double getPrecio(){
        return precio;
    }
    
    public int getCantidad(){
        return cantidad;
    }
    
    public double getPrecioTotal(){ // Calcula y devuelve el precio total de la compra
        return cantidad * precio;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
    @Override
    public String toString(){
        String formatToSend = "";
        String formatoEspacios = "%-30s";
        String formatoDecimales;
        String precioFormateado;
        String precioTotalFormateado;
        formatToSend += String.format(formatoEspacios, getNombre());
        formatoEspacios = "%8s";
        formatoDecimales = "%.2f";
        precioFormateado = String.format(formatoDecimales, precio);
        formatToSend += String.format(formatoEspacios, precioFormateado);
        formatToSend += String.format(formatoEspacios, cantidad);
        precioTotalFormateado = String.format(formatoDecimales, getPrecioTotal());
        formatToSend += String.format(formatoEspacios, precioTotalFormateado);
        return formatToSend;
    }
    
}

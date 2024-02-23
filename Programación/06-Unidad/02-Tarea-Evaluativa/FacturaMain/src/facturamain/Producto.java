package facturamain;

/*
   Definición de la clase Producto.
   Necesita la descripción del producto, su precio y la cantidad comprada
*/
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
        this(unProducto.getNombre(), unProducto.getPrecio());
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
        return String.format("%-30s%8.2f%8d%8.2f", getNombre(), precio, cantidad, getPrecioTotal());
    }
    
}
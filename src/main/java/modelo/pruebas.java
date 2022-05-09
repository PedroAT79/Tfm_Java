package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd/HH:mm");

		System.out.println(dtf.format(LocalDateTime.now()));

		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyyMMdd/HHmm");
		int idUsuario = 5;
		String nombreOrden = dtf1.format(LocalDateTime.now()) + "-" + String.valueOf(idUsuario);
		System.out.println("nombre de la orden: " + nombreOrden);

		String hola = "\\esto";

		hola = hola.replace("\\", "fotos/");
		System.out.println(hola);

		ArrayList<Articulo> lista = new ArrayList<>();

		Articulo art = new Articulo(1, "artic1", 20, "marca1", "modelo1", 3, "nuevo", "articulo muestra 1", 1);
		Articulo art1 = new Articulo(2, "artic2", 23, "marca2", "modelo2", 10, "nuevo", "articulo muestra 2", 1);
		Articulo art2 = new Articulo(3, "artic1", 24, "marca1", "modelo1", 1, "nuevo", "articulo muestra 1", 1);
		Articulo art3 = new Articulo(3, "artic3", 25, "marca3", "modelo3", 1, "nuevo", "articulo muestra 3", 1);

		lista.add(art);
		lista.add(art1);
		lista.add(art2);
		lista.add(art3);

		int numeroDeItems = lista.size();
		System.out.println("Total nº de ventas: " + numeroDeItems);
		// media precio en stoco (art1.precio+art2.precio+art3.precio+/2)

		
		//Media Aritmetica sobre el precio de articulos vendidos:
		double mediaAritmetica =0;
		double suma = 0;
		
		for(int i =0; i<lista.size();i++) {
			suma = suma + (lista.get(i).getPrecioArticulo());
		}
		double media =  suma/lista.size();
		System.out.println("Precio medio de los articulos vendidos: "+media);
		
		//Moda: Articulo mas vendido cantidad total:
		int maximoNumRep =0;
		int moda = 0;
		
		
		for(int j=0; j<lista.size();j++) {
			/*int sumaCant = 0;*/
			int idRep =0;
			for(int k=0; k<lista.size();k++) {
				if(lista.get(j).getIdArticulo()== lista.get(k).getIdArticulo()) {
					/*sumaCant = sumaCant + lista.get(j).getCantidad();*/
					idRep = idRep + 1;
				}
				/*if(sumaCant>maximoNumRep) {
					moda = lista.get(j).getIdArticulo();
					maximoNumRep = sumaCant;*/
				if(idRep>maximoNumRep) {
					moda = lista.get(j).getIdArticulo();
					maximoNumRep = idRep;
				
				}
			}
		}
		System.out.println("LA MODA ES: "+moda);

		
		//Promedio precio de articulo vendido:
		
		/*		double promedio =0;
					double suma1 = 0;
					
					for(int i =0; i<lista.size();i++) {
						suma1 = suma1 + (lista.get(i).getPrecioArticulo());
					}
					double media1 =  suma1/lista.size();
					System.out.println("Precio medio de los articulos vendidos: "+media);
			
		
	}
	
	*/
		String resultado;
		LocalDate fechaAyer = LocalDate.now().minusDays(1);
		System.out.println(fechaAyer);
		if(fechaAyer !=LocalDate.now()) {
			 resultado ="falso";
		}else {
			resultado = "verdadero";
		}
		System.out.println(resultado);
	}
	
	
}
	

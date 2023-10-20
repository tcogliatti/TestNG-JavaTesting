package restaurant;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.*;

public class IngredienteTest {
    private final int LARGO_NOMBRE = 10;
    private Ingrediente[] ingredientes;

    @BeforeClass
    public void generarIngredientes() {
        ingredientes = new Ingrediente[]{
                new Ingrediente("papa", "gramos", 5000, 0.50f),
                new Ingrediente("huevo", "unidades", 200, 80.00f),
                new Ingrediente("carne picada", "gramos", 5000, 4.00f),
                new Ingrediente("pan rallado", "gramos", 500, 0.50f),
                new Ingrediente("sal", "gramos", 250, 2.00f),
        };
    }

    @DataProvider
    public Object[] ingredientesDataProvider() {
        System.out.println("Generando ingredientes");
        return ingredientes;
//        return new Ingrediente[] {
//            new Ingrediente("papa","gramos", 5000, 0.50f),
//            new Ingrediente("huevo","unidades", 200, 80.00f),
//            new Ingrediente("carne picada","gramos", 5000, 4.00f),
//            new Ingrediente("pan rallado","gramos", 500, 0.50f),
//            new Ingrediente("sal","gramos", 250, 2.00f),
//        };
    }

    @Test(
            dataProvider = "ingredientesDataProvider",
            testName = "Lenght name test: data provider"
    )
    public void testName(Ingrediente i) {
        int largoNombre = i.getNombre().length();
        assertTrue(largoNombre <= LARGO_NOMBRE);
    }

    public Ingrediente elegirUno(Ingrediente[] i) {
        System.out.println("\tBeforeTest - ramdomly pick a person from an array");
        Random generadorAleatorios = new Random();
        int numeroAleatorio = generadorAleatorios.nextInt(i.length) - 1;
        return i[numeroAleatorio];
    }

    @Test(testName = "Lenght name test: ramdom")
    public void testNameRandom() {
        Ingrediente i = elegirUno(ingredientes);
        int largoNombre = i.getNombre().length();
        assertTrue(largoNombre <= LARGO_NOMBRE);
    }

    @Test(testName = "Stock test")
    public void testStockDemand() {
        Ingrediente i = elegirUno(ingredientes);
        int stock = i.getStock();
        if (stock > 0)
            i.consumirStock(stock);
        try {
            i.consumirStock(10);
            fail("Un ingrediente no debería permitir consumir stock cuando esta en cero");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
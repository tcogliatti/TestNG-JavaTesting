package restaurant;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import restaurant.exceptions.EmailFormatException;
import restaurant.exceptions.SinSaldoException;

import static org.testng.Assert.*;

public class UsuarioTest {
    private Usuario usuario;
    @BeforeClass
    public void loadPersona() throws EmailFormatException {
        this.usuario = new Usuario("Jorge Almiron", 2,100,"jorge.almiron@gmail.com");
    }
    @Test(expectedExceptions = SinSaldoException.class)
    public void testDescontarSaldo() throws SinSaldoException {
        Float monto = this.usuario.getSaldo() + 100;
        this.usuario.descontarSaldo(monto);
    }


}
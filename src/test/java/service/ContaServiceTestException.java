package service;

import model.Conta;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ContaServiceTestException {
    ContaService contaService;

    @Before
    public void setup() {
        contaService = new ContaService();
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void deveLancarExceptionQuandoValorDoSaqueForNegativo() throws Exception {
        //Dado
        Conta conta = contaService.cadastrar("Teste");
        //Expect
        expectedException.expect(Exception.class);
        expectedException.expectMessage("O valor de saque não pode ser negativo");
        //Quando
        contaService.sacar(conta, -5.0);
    }

    @Test(expected = Exception.class)
    public void deveLancarExceptionQuandoValorDoSaqueForNegativo_2() throws Exception {
        //Dado
        Conta conta = contaService.cadastrar("Teste");
        //Quando
        contaService.sacar(conta, -5.0);
    }

    @Test
    public void deveLancarExceptionQuandoValorDoSaqueForNegativo_3() throws Exception {
        //Dado
        Conta conta = contaService.cadastrar("Teste");
        //Quando
        try {
            contaService.sacar(conta, -5.0);
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("O valor de saque não pode ser negativo"));
        }
    }

    @Test
    public void deveLancarExceptionQuandoValorDoSaqueForNegativo_4() throws Exception {
        Conta conta = contaService.cadastrar("Teste");
        Assert.assertThrows(Exception.class,
                () -> contaService.sacar(conta, -5.0));

    }

    @Test
    public void deveLancarExceptionQuandoValorDoSaqueForNegativo_5() {
        Conta conta = contaService.cadastrar("Teste");
        Throwable throwable = Assert.assertThrows(Exception.class,
                () -> contaService.sacar(conta, -5.0));
        Assert.assertEquals("O valor de saque não pode ser negativo", throwable.getMessage());
    }
}

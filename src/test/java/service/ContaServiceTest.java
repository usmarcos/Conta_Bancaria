package service;

import model.Conta;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Objects;

/**
 * Deve ser possível criar uma conta
 * Novas contas iniciam com saldo ZERO
 * Deve ser possível depositar
 * Caso haja saldo deve ser possível sacar
 * Caso haja saldo deve ser possível transferir entre contas
 * Contas com mesmo numero e mesma agência são iguais
 * <p>
 * Assert.assertTrue(true);
 * Assert.assertFalse(false);
 * Assert.assertEquals(1, 1);
 * Assert.assertNotEquals(2 , 10); *
 * Assert equals comparando duas contas
 * Assert.assertSame
 */

public class ContaServiceTest {
    ContaService contaService;

    @Before
    public void setup() {
        contaService = new ContaService();
    }


    @Test
    public void deveSerPossivelCriarUmaConta() {
        //Dado
        Conta conta = contaService.cadastrar("Teste");
        //Quando

        //Então
        assertEquals(Integer.valueOf(1), conta.getAgencia());
        assertEquals((Integer) 1, conta.getNumero());
        assertEquals("Teste", conta.getNomeDoCliente());
    }

    @Test
    public void novasContasDevemIniciarComSaldoZero() {
        //Dado
        Conta conta = contaService.cadastrar("Teste");
        //Quando

        //Então
        //Então
        assertEquals((Double) 0.0, conta.getSaldo());
    }

    @Test
    public void deveSerPossivelDepositar() {
        //Dado
        Conta conta = contaService.cadastrar("Teste");
        //Quando
        contaService.depositar(conta, 10.0);
        //Então
        assertEquals((Double) 10.0, conta.getSaldo());
    }

    @Test
    public void deveSerPossivelSacar() throws Exception {

        //Dado
        Conta conta = contaService.cadastrar("Teste");
        //Quando
        contaService.depositar(conta, 10.0);
        contaService.sacar(conta, 5.0);
        //Então
        assertEquals((Double) 5.0, conta.getSaldo());

    }

    @Test
    public void comSaldoDeveSerPossivelTransferir() {
        //Dado
        Conta de = contaService.cadastrar("De");
        contaService.depositar(de, 50.0);
        Conta para = contaService.cadastrar("Para");
        //Quando
        contaService.transferir(de, para, 30.0);
        //Então
        assertEquals(de.getSaldo() == 20.0, para.getSaldo() == 30.0);
    }

    @Test
    public void naoDeveTerContaComMesmoNumeroAgencia() {
        //Dado
        Conta contaUm = contaService.cadastrar("De");
        Conta contaDois = contaService.cadastrar("Para");
        //Quando

        //Então
        assertFalse(Objects.equals(contaUm.getAgencia(), contaDois.getAgencia()));
        assertFalse(Objects.equals(contaUm.getNumero(), contaDois.getNumero()));
        assertSame(contaUm, contaUm);
        assertSame(contaDois, contaDois);
    }

    @Test
    public void assertThatTest() {
        //verifique que...
        Conta contaMaria = contaService.cadastrar("Maria");
        assertThat(contaMaria.getSaldo(), is(0.0));
        assertThat(contaMaria.getNomeDoCliente(), not("João"));
        //verifica se é uma instância de classe
        assertThat(contaMaria, is(instanceOf(Conta.class)));
    }

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Ignore
    @Test
    public void validandoAsserts() {
        Conta conta = contaService.cadastrar("Marcos");

        assertEquals("Marcos", conta.getNomeDoCliente());
        errorCollector.checkThat("Marcos1", is(conta.getNomeDoCliente()));

        assertEquals((Double) 0.0, conta.getSaldo());
       errorCollector.checkThat(1.0, is(conta.getSaldo()));

        assertEquals((Integer) 1, conta.getNumero());
        errorCollector.checkThat(2, is(conta.getNumero()));
    }


}

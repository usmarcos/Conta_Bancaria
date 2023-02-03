package service;

import model.Conta;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        Assert.assertEquals(Integer.valueOf(1), conta.getAgencia());
        Assert.assertEquals((Integer) 1, conta.getNumero());
        Assert.assertEquals("Teste", conta.getNomeDoCliente());
    }

    @Test
    public void novasContasDevemIniciarComSaldoZero() {
        //Dado
        Conta conta = contaService.cadastrar("Teste");
        //Quando

        //Então
        //Então
        Assert.assertEquals((Double) 0.0, conta.getSaldo());
    }

    @Test
    public void deveSerPossivelDepositar() {
        //Dado
        Conta conta = contaService.cadastrar("Teste");
        //Quando
        contaService.depositar(conta, 10.0);
        //Então
        Assert.assertEquals((Double) 10.0, conta.getSaldo());
    }

    @Test
    public void deveSerPossivelSacar() {
        //Dado
        Conta conta = contaService.cadastrar("Teste");
        //Quando
        contaService.depositar(conta, 10.0);
        contaService.sacar(conta, 5.0);
        //Então
        Assert.assertEquals((Double) 5.0, conta.getSaldo());

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
        Assert.assertEquals(de.getSaldo() == 20.0, para.getSaldo() == 30.0);
    }

    @Test
    public void naoDeveTerContaComMesmoNumeroAgencia() {
        //Dado
        Conta contaUm = contaService.cadastrar("De");
        Conta contaDois = contaService.cadastrar("Para");
        //Quando

        //Então
        Assert.assertFalse(contaUm.getAgencia() ==contaDois.getAgencia());
        Assert.assertFalse(contaUm.getNumero() == contaDois.getNumero());
        Assert.assertSame(contaUm,contaUm);
        Assert.assertSame(contaDois,contaDois);
    }


}

package br.com.estudos.crud.service;

import br.com.estudos.crud.model.Cliente;
import br.com.estudos.crud.repository.ClienteRepository;
import br.com.estudos.crud.service.impl.CadastroClienteServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@RunWith(MockitoJUnitRunner.class)
public class CadastroClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    private CadastroClienteService cadastroClienteService;

    @Before
    public void setUp() {
        openMocks(this);
        cadastroClienteService = new CadastroClienteServiceImpl(clienteRepository);
    }

    @Test
    public void findByCpfComSucesso() {
        when(clienteRepository.findByCpf(anyString())).thenReturn(getDto());
        final var result = cadastroClienteService.findByCpfDto("41462515835");
        assertNotNull(result);
    }

    public Cliente getDto() {
        final var data = new Cliente();
        data.setDescription("descricao");
        data.setCpf("41462515835");
        data.setEmail("rbatistaa321@gmail.com");
        data.setName("Rafael Batista");
        return data;
    }

}

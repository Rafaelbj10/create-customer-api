package br.com.estudos.crud.service.impl;

import br.com.estudos.crud.presenters.cliente.ClienteDto;
import br.com.estudos.crud.repository.ClienteRepository;
import br.com.estudos.crud.service.CadastroClienteService;
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
public class CadastroClienteServiceTest {

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
        final var result = cadastroClienteService.findByCpf("41462515835");
        assertNotNull(result);
    }

    public ClienteDto getDto() {
        final var data = new ClienteDto();
        data.setAddress("Rua Amadeu Amaral");
        data.setDescription("descricao");
        data.setCpf("41462515835");
        data.setEmail("rbatistaa321@gmail.com");
        data.setName("Rafael Batista");
        return data;
    }

}

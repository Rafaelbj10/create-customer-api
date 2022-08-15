package br.com.estudos.crud.service;

import br.com.estudos.crud.presenters.cliente.ClienteDto;
import br.com.estudos.crud.repository.ClienteRepository;
import br.com.estudos.crud.service.Impl.CadastroClienteServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CadastroClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    private CadastroClienteService cadastroClienteService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cadastroClienteService = new CadastroClienteServiceImpl(clienteRepository);
    }

    @Test
    public void findByCpfComSucesso() {
        Mockito.when(clienteRepository.findByCpf("41462515835")).thenReturn(getDto());
        final var result = cadastroClienteService.findByCpf("41462515835");
        Assert.assertNotNull(result);
    }

    public ClienteDto getDto() {
        final var data = new ClienteDto();
        data.setAddress("Rua Amadeu Amaral");
        data.setDescription("descricao");
        data.setEmail("rbatistaa321@gmail.com");
        data.setName("Rafael Batista");
        return data;
    }

}

package br.com.estudos.crud.repository;

import br.com.estudos.crud.presenters.cliente.ClienteDto;
import br.com.estudos.crud.repository.impl.ClienteRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.List;

import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.openMocks;

public class ClienteRepositoryImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @InjectMocks
    private ClienteRepositoryImpl clienteRepository;

    @Before
    public void setUp(){
        openMocks(this);
    }

    @Test
    public void test(){
        Mockito.when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(getClientList());
        var result = clienteRepository.findAll();
        Assert.assertNotNull(result);
    }

    private List<ClienteDto> getClientList(){
        var list = new ClienteDto();
        list.setTelephone("123456");
        list.setName("Nome");
        return newArrayList(list);
    }
}

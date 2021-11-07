package br.com.estudos.crud.controller;

import br.com.estudos.crud.dto.ClienteDto;
import br.com.estudos.crud.mapper.ClienteMapper;
import br.com.estudos.crud.model.Cliente;
import br.com.estudos.crud.service.CadastroClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping(value = "/cadastro-cliente", produces = "application/json", consumes = "application/json")
public class CadastroClienteController {

    @Autowired
    CadastroClienteService cadastroClienteService;

    @ApiOperation(value = "Retorna uma lista de clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de clientes"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    public List<ClienteDto> listar() {

        List<Cliente> cliente = cadastroClienteService.listar();

        ClienteMapper clienteMapper = new ClienteMapper();

        return clienteMapper.getUsuariosDtoByForEachNormal(cliente);

    }

    @GetMapping("/{id}")
    public List<ClienteDto> buscar(@PathVariable Long id) {

        List<Cliente> cliente = cadastroClienteService.listar();
        List<ClienteDto> clienteDto = new ArrayList<>();

        for (Cliente u : cliente) {

            ClienteDto dto = new ClienteDto();
            dto.setId(u.getId());
            dto.setNome(u.getNome());
            dto.setEndereco(u.getEndereco());

            clienteDto.add(dto);
        }
        return clienteDto;
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody ClienteDto clienteDto) {
        Cliente cliente = cadastroClienteService.cadastrar(clienteDto.transformaParaObjeto());
        return new ResponseEntity(clienteDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@RequestBody ClienteDto clienteDto, @PathVariable Long id) {
        cadastroClienteService.alterar(clienteDto.transformaParaObjeto(), id);
        return new ResponseEntity(clienteDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        cadastroClienteService.deletar(id);
    }


}

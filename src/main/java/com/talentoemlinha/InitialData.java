package com.talentoemlinha;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.talentoemlinha.model.DetalhesFuncionario;
import com.talentoemlinha.model.Funcionario;
import com.talentoemlinha.repository.DetalhesFuncionarioRepository;
import com.talentoemlinha.service.FuncionarioService;

@Component
public class InitialData implements CommandLineRunner {

    @Autowired
    private FuncionarioService funcServ;
    @Autowired
    private DetalhesFuncionarioRepository detalhesRepo;

    @Override
    public void run(String... args) throws Exception {
        Funcionario func = new Funcionario();
		func.setNp(123456L);
		func.setNome("Admin");
		func.setHash("123");
		func.setRole("ADMIN");
        funcServ.adicionarFuncionario(func);
        
        DetalhesFuncionario detalhes = new DetalhesFuncionario();
        detalhes.setDataAdmissao(LocalDateTime.now());
        detalhes.setEmail("ADMIN@ADMIN.com");
        detalhes.setTelefone("(12) 99999-9999");
        detalhes.setFuncionario(func);

        detalhesRepo.save(detalhes);

    }
    
}

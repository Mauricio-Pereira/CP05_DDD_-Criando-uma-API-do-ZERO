package org.fiap.services;

import org.fiap.entities.Colecoes;
import org.fiap.repositories.ColecoesRepository;

import java.util.Collections;

public class ColecoesService {
    private ColecoesRepository colecoesRepository = new ColecoesRepository();

    public boolean Create(Colecoes colecoes){
        var validation = colecoes.validate();
        try {
            if(validation.containsKey(false)) {
                throw new IllegalArgumentException(validation.get(false).toString());
            } else {
                colecoesRepository.Create(colecoes);
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean UpdateById(Colecoes colecoes, int id){
        var validation = colecoes.validate();
        try {
            if(validation.containsKey(false)) {
                throw new IllegalArgumentException(validation.get(false).toString());
            } else {
                colecoesRepository.UpdateById(colecoes, id);
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

}

package com.uca.capas.service;

import com.uca.capas.domain.MateriaXEstudiante;
import com.uca.capas.domain.MateriaXEstudianteKey;
import com.uca.capas.repositories.EstudianteRepo;
import com.uca.capas.repositories.MateriaRepo;
import com.uca.capas.repositories.MateriaXEstudianteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaXEstudianteServiceImpl implements MateriaXEstudianteService{

    @Autowired
    MateriaXEstudianteRepo mxeRepo;

    @Autowired
    EstudianteRepo estudianteRepo;

    @Autowired
    MateriaRepo materiaRepo;

    @Override
    public List<MateriaXEstudiante> findAll() throws DataAccessException {
        return mxeRepo.findAll();
    }

    @Override
    public MateriaXEstudiante findOne(MateriaXEstudianteKey id) throws DataAccessException {
        return mxeRepo.getOne(id);
    }

    @Override
    public void insertar(MateriaXEstudiante mxe) throws DataAccessException {

        mxe.setId(new MateriaXEstudianteKey(mxe.getIdMateria(),mxe.getIdEstudiante()));
        mxe.setEstudiante(estudianteRepo.getOne(mxe.getIdEstudiante()));
        mxe.setMateria(materiaRepo.getOne(mxe.getIdMateria()));
        mxeRepo.save(mxe);

    }

}

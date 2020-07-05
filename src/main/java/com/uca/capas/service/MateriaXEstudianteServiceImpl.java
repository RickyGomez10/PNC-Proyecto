package com.uca.capas.service;

import com.uca.capas.dao.MateriaXEstudianteDAO;
import com.uca.capas.domain.MateriaXEstudiante;
import com.uca.capas.domain.MateriaXEstudianteKey;
import com.uca.capas.repositories.EstudianteRepo;
import com.uca.capas.repositories.MateriaRepo;
import com.uca.capas.repositories.MateriaXEstudianteRepo;
import javassist.tools.rmi.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.List;

@Service
public class MateriaXEstudianteServiceImpl implements MateriaXEstudianteService{

    @Autowired
    MateriaXEstudianteRepo mxeRepo;

    @Autowired
    MateriaXEstudianteDAO mxeDao;

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

        if (mxe.getEstudiante() != null && mxe.getMateria() != null) {
            if(mxe.isNew()) {
                mxeRepo.save(mxe);
            }else {
                throw new ConstraintViolationException(new HashSet<>());
            }
        }


    }

    @Override
    public List<MateriaXEstudiante> findByEstudiante(Integer id) throws DataAccessException {
        return mxeRepo.getMaterias(id);
    }

    @Override
    public void modificar(MateriaXEstudiante mxe) throws DataAccessException {
        mxe.setId(new MateriaXEstudianteKey(mxe.getIdMateria(),mxe.getIdEstudiante()));
        mxe.setEstudiante(estudianteRepo.getOne(mxe.getIdEstudiante()));
        mxe.setMateria(materiaRepo.getOne(mxe.getIdMateria()));
        mxeDao.modificar(mxe);
    }

}
